/* eslint-disable */ 
import React, { Component } from "react";
import FighterService from "../services/fighter.service";
import CoachService from "../services/coach.service";

export default class FighterCard extends Component {


    constructor(props) {
        super(props);
        this.sendRequest = this.sendRequest.bind(this);
        this.deleteFighter = this.deleteFighter.bind(this);
        this.state = {
            x : false,
            successful: false,
            message: "",
            sent: false
        }
    }
    

    componentDidMount() {
        var x;

        if (localStorage.getItem("userRole")==="ROLE_COACH") {
            CoachService.getCoachId(localStorage.getItem("username")).then((response)=> {
                localStorage.setItem("coachid",response.data)
            });
            FighterService.hasCoach(this.props.id).then((response) => {
                this.setState({
                    x : response.data
                })
                console.log(x);
            });
        }
    }

    sendRequest() {
        this.setState({
            message: "",
            successful: false
          });
          CoachService.sendRequest(localStorage.getItem("coachid"),this.props.id).then(response => {
            this.setState({
                message: response.data.message,
                successful: true,
                sent: true
              });
          },
          error => {
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

    deleteFighter() {
        this.setState({
            message: "",
            successful: false
          });
          FighterService.deleteFighter(this.props.id).then(response => {
            this.setState({
                message: response.data.message,
                successful: true
              });
          },
          error => {
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
        return (
            <div className="category-card">
                {
                     localStorage.getItem("userRole")==="ROLE_ADMIN" && (
                        <button id = "delete-button" onClick = {() =>
                            [this.deleteFighter(), window.location.reload()]} > X </button> )
                }
                {
                    
                    localStorage.getItem("userRole")==="ROLE_COACH" && this.state.x===false && this.state.sent === false  && (
                        <button id = "delete-button" onClick = {() =>
                            [this.sendRequest()]} > Send </button> )
                }
                 
                            <a onClick= {()=> {window.location.href = "fighter?id="+ this.props.id}}>
                                <div className="category-card-image">
                                <img className="card-image" alt="fighter-image" src={`data:image/jpg;base64,${this.props.imageBinary}`} width='250px' height = '200px'/>
                                </div>
                                <div className="category-card-text">
                                    <p>{this.props.fighterName}</p>
                                </div>
                            </a> 
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
                     
                
            </div>
            
        );
    }
    
}