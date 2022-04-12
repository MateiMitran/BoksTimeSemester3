import React, {Component} from "react";
import "./fight.component.css";
import {Bar} from 'react-chartjs-2';
// eslint-disable-next-line
import { Chart as ChartJS } from 'chart.js/auto'
// eslint-disable-next-line
import { Chart }            from 'react-chartjs-2'
import FighterService from "../services/fighter.service";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import "./fighter.component.css";


const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class FighterComponent extends Component {

    
    constructor(props) {
        super(props);
        this.onChangeAge = this.onChangeAge.bind(this);
        this.onChangeHeight = this.onChangeHeight.bind(this);
        this.onChangeReach = this.onChangeReach.bind(this);
        this.onChangeBouts = this.onChangeBouts.bind(this);
        this.onChangeRecord = this.onChangeRecord.bind(this);
        this.handleUpdate = this.handleUpdate.bind(this);
        this.state = {
            id:"",
            name: "",
            age: "",
            height: "",
            reach: "",
            bouts: "",
            record: "",
            image: "",
            punches: [],
            rounds: [],
            powerpunches: [],
            successful: false,
            message: ""
        };
    }
    componentDidMount() {
        const query = new URLSearchParams(this.props.location.search);
        const id = query.get('id');
        console.log(id);
        this.setState({
            id: id
        });
        FighterService.getFighter(id).then( (response) => {
            this.setState({
                name: response.data.name,
                age: response.data.age,
                height: response.data.height,
                reach: response.data.reach,
                bouts: response.data.bouts,
                record: response.data.record,
                image: response.data.image,
                punches: response.data.punches,
                rounds: response.data.rounds,
                powerpunches: response.data.powerpunches
            });
        }
    )
    
}
onChangeAge(e) {
    this.setState({
        age: e.target.value
    });
}
onChangeHeight(e) {
    this.setState({
        height: e.target.value
    })
}
onChangeReach(e) {
    this.setState({
        reach: e.target.value
    })
}
onChangeBouts(e) {
    this.setState({
        bouts: e.target.value
    })
}
onChangeRecord(e) {
    this.setState({
        record: e.target.value
    })
}
handleUpdate(e) {
    e.preventDefault();
    this.setState({
        message: "",
        successful: false
      });
    this.form.validateAll();
    FighterService.updateFighter(this.state.id,this.state.age, this.state.height, this.state.reach, this.state.bouts, this.state.record).then((response)=> {
        console.log(response.data);
        this.setState({
            message: response.data.message,
            successful: true
          });
    },error => {
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        this.setState({
          successful: false,
          message: resMessage
        });
      }
    );
}
    
    render() {
        const data = {
            labels: ['January', 'February', 'March',
                     'April', 'May','June','July','August','September','October','November','December'],
            datasets: [
              {
                label: 'Punches',
                backgroundColor: 'rgba(242, 97, 1,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.punches
              },
              {
                label: 'Rounds',
                backgroundColor: 'rgba(30, 180, 1,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.rounds
              },
              {
                label: 'Power Punches',
                backgroundColor: 'rgba(159, 90, 253,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.powerpunches
              }
            ]
          }
          let roleName = "";
          if (localStorage.getItem("userRole")) {
             roleName = localStorage.getItem("userRole");
          } else {
            roleName = "Nothing";
          }
        return(
            <div id="listing-container" className="section-wrapper">
        <div className="row clearfix" >
            <div id="listing-images-wrapper">
                <img id="fighter-image" alt = "fighter" src={`data:image/jpg;base64,${this.state.image}`} width='750px' height = '800px' />
            </div>
                   
                
            
            <div className="listing-section left">
                 <Form className = "simple-form"
                        onSubmit={this.handleUpdate}
                        ref={c => {
                        this.form = c;
                         }}
                    >
                <div className="listing-info-wrapper" id="title-info-wrapper">
                    <h2>{this.state.name}</h2>
                </div>
                <div className="listing-info-wrapper">
                    <ul className="listing-info-wrapper" id="fighter-list">
                        {roleName==="ROLE_USER" && (
                        <li>
                            <span>Age:</span> {this.state.age}
                        </li>
                        )
                        }
                        { roleName==="ROLE_ADMIN" && (
                            <div className="form-group">
                            <label htmlFor="age">Age:</label>
                            <Input
                            type="number"
                            className="form-control"
                            name="age"
                            value={this.state.age}
                            onChange = {this.onChangeAge}
                            validations = {[required]}
                            />
                            </div>
                        )
                        }
                        
                        {roleName==="ROLE_USER" && (
                        <li>
                        <span>Height:</span> {this.state.height}
                        </li>
                        )
                        }
                        { roleName==="ROLE_ADMIN" && (
                            <div className="form-group">
                            <label htmlFor="height">Height:</label>
                            <Input
                            type="text"
                            className="form-control"
                            name="height"
                            value={this.state.height}
                            onChange = {this.onChangeHeight}
                            validations = {[required]}
                            />
                            </div>
                        )
                        }
                       
                        {roleName==="ROLE_USER" && (
                         <li>
                         <span>Reach:</span> {this.state.reach}
                         </li>
                        )
                        }
                        { roleName==="ROLE_ADMIN" && (
                            <div className="form-group">
                            <label htmlFor="reach">Reach:</label>
                            <Input
                            type="text"
                            className="form-control"
                            name="reach"
                            value={this.state.reach}
                            onChange = {this.onChangeReach}
                            validations = {[required]}
                            />
                            </div>
                        )
                        }
                        
                        {roleName==="ROLE_USER" && (
                        <li>
                        <span>Bouts:</span> {this.state.bouts}
                        </li>
                        )
                        }
                        { roleName==="ROLE_ADMIN" && (
                            <div className="form-group">
                            <label htmlFor="bouts">Bouts:</label>
                            <Input
                            type="text"
                            className="form-control"
                            name="bouts"
                            value={this.state.bouts}
                            onChange = {this.onChangeBouts}
                            validations = {[required]}
                            />
                            </div>
                        )
                        }
                        
                        {roleName==="ROLE_USER" && (
                        <li>
                        <span>Record:</span> {this.state.record}
                        </li>
                        )
                        }
                        { roleName==="ROLE_ADMIN" && (
                            <div className="form-group">
                            <label htmlFor="record">Record:</label>
                            <Input
                            type="text"
                            className="form-control"
                            name="record"
                            value={this.state.record}
                            onChange = {this.onChangeRecord}
                            validations = {[required]}
                            />
                            </div>
                        )
                        }
                       
                    </ul>
                    <h2 id="statistics-h2">Fighting Statistics 2021</h2>
                    <Bar id= "chart"
                         data={data}
                         options={{
                         title:{
                         display:true,
                         fontSize:20
                        },
                        legend:{
                        display:true,
                        position:'right'
                         }
                        }}
                         />
                    
                    <div className="form-group">
                    { roleName==="ROLE_ADMIN" && (
                  <button className="btn btn-primary btn-block">Update</button>
                    )
                    }
                </div>
                </div>
                {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}
                <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
            </div>
            
        </div>
    </div>
        );
    }
}