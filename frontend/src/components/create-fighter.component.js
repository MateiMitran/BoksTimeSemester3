import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import './create-fight.component.css'; 
import FighterService from '../services/fighter.service';
import AuthService from "../services/auth.service";

const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class CreateFighter extends Component {
    constructor(props) {
        super(props);
        this.onChangeName = this.onChangeName.bind(this);
        this.onChangeAge = this.onChangeAge.bind(this);
        this.onChangeHeight = this.onChangeHeight.bind(this);
        this.onChangeReach = this.onChangeReach.bind(this);
        this.onChangeBouts = this.onChangeBouts.bind(this);
        this.onChangeRecord = this.onChangeRecord.bind(this);
        this.onChangePunches = this.onChangePunches.bind(this);
        this.onChangeRounds = this.onChangeRounds.bind(this);
        this.onChangePowerpunches = this.onChangePowerpunches.bind(this);
        this.onChangeWeightclass = this.onChangeWeightclass.bind(this);
        this.handleCreateFighter = this.handleCreateFighter.bind(this);
        this.state = {
            name: "",
            age: "",
            height: "",
            reach: "",
            bouts: "",
            record: "",
            punches: "",
            rounds: "",
            powerpunches: "",
            weightclass: ""
        }
    }
    onChangeName(e) {
        this.setState({
          name: e.target.value
        });
    }
    onChangeAge(e) {
        this.setState({
          age: e.target.value
        });
    }
    onChangeHeight(e) {
        this.setState({
          height: e.target.value
        });
    }
    onChangeReach(e) {
        this.setState({
          reach: e.target.value
        });
    }
    onChangeBouts(e) {
        this.setState({
          bouts: e.target.value
        });
    }
    onChangeRecord(e) {
        this.setState({
          record: e.target.value
        });
    }
    onChangePunches(e) {
        this.setState({
          punches: e.target.value
        });
    }
    onChangeRounds(e) {
        this.setState({
          rounds: e.target.value
        });
    }
    onChangePowerpunches(e) {
        this.setState({
          powerpunches: e.target.value
        });
    }
    onChangeWeightclass(e) {
        this.setState({
          weightclass: e.target.value
        });
    }


    handleCreateFighter(e) {
        e.preventDefault();
        this.form.validateAll();
        if (this.checkBtn.context._errors.length === 0) {

            var punches = this.state.punches.split(',').map(Number);
            var rounds = this.state.rounds.split(',').map(Number);
            var powerpunches = this.state.powerpunches.split(',').map(Number);
            AuthService.register(this.state.name, "test@email.com","test1234","ROLE_BOXER").then((response)=> {
                console.log(response.data);
            })
            FighterService.createFighter(this.state.name, this.state.age, this.state.height, this.state.reach,
                                        this.state.bouts, this.state.record,punches,rounds,powerpunches).then((response) => {
                                            console.log(response.data);
                                            localStorage.setItem("weight_class", this.state.weightclass);
                                            window.location.href="/set-fighter-image?id="+response.data.id;
                                        });

        }

    }
    render() {
        return (
            <Form className="simple-form listing-form"
            onSubmit={this.handleCreateFighter}
            ref={c => {
              this.form = c;
            }}
            >
        <h2>Create Fighter</h2>
        <div className="form-background">
            <div className="form-section">
                <div className="form-group full clearfix">
                    <h3>General</h3>
                    
                    <div className="form-group full clearfix">
                        <label htmlFor="name">Name:</label>
                        <Input type="text" name="name" id="name"
                        onChange = {this.onChangeName}
                        validations = {[required]}
                        />
                    </div>
                    
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="age">Age:</label>
                        <Input type="number" min="0" name="age" id="age" 
                        onChange = {this.onChangeAge}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="height">Height:</label>
                        <Input type="text" name="height" id="height" 
                        onChange = {this.onChangeHeight}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="reach">Reach:</label>
                        <Input type="text" name="reach" id="reach" 
                        onChange = {this.onChangeReach}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="bouts">Bouts:</label>
                        <Input type="text" name="bouts" id="bouts" 
                        onChange = {this.onChangeBouts}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="record">Record:</label>
                        <Input type="text" name="record" id="record" 
                        onChange = {this.onChangeRecord}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                        <label htmlFor="weight-class">Weight Class:</label>
                        <label className="select-arrow-label">
                        <select name="weight-class" id="weight-class"
                        onChange = {this.onChangeWeightclass}
                        validations = {[required]}
                        >
                        <option value="default" selected disabled>Select Weight Class</option>
                        <option value="Featherweight">Featherweight</option>
                        <option value="Lightweight">Lightweight</option>
                        <option value="Welterweight">Welterweight</option>
                        <option value="Middleweight">Middleweight</option>
                        <option value="Heavyweight">Heavyweight</option>
                        </select></label>
                    </div>
                </div>
                <div className="form-section">
                    <h3>Statistics</h3>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="punches">Punches (per month):</label>
                        <Input type="text" name="punches" id="punches" placeholder="1,2,3,4,5,6,7,8,9,10,11,12" 
                        onChange = {this.onChangePunches}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="rounds">Rounds (per month):</label>
                        <Input type="text" name="rounds" id="rounds" placeholder="1,2,3,4,5,6,7,8,9,10,11,12" 
                        onChange = {this.onChangeRounds}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="powerpunches">Power Punches (per month):</label>
                        <Input type="text" name="powerpunches" id="powerpunches" placeholder="1,2,3,4,5,6,7,8,9,10,11,12" 
                        onChange = {this.onChangePowerpunches}
                        validations = {[required]}
                        />
                    </div>
                </div>
            </div>
            <div className="form-section">
                <div className="form-group full submit">
                    <button id = "create-fight-btn"> Create Fighter </button>
                </div>
            </div>
        </div>
        <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
    </Form>
        );
    }

}