import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import './create-fight.component.css'; 
import FighterService from '../services/fighter.service';
import FightService from '../services/fight.service';

const required = value => {
    if (!value) {
      return (
        <div className="alert alert-danger" role="alert">
          This field is required!
        </div>
      );
    }
  };

export default class CreateFight extends Component {

    constructor(props) {
        super(props);
        this.handleCreateFight = this.handleCreateFight.bind(this);
        this.onChangeTitle = this.onChangeTitle.bind(this);
        this.onChangeEmbedID = this.onChangeEmbedID.bind(this);
        this.onChangeImage = this.onChangeImage.bind(this);
        this.onChangeDescription = this.onChangeDescription.bind(this);
        this.onChangeFighter1ID = this.onChangeFighter1ID.bind(this);
        this.onChangeFighter2ID = this.onChangeFighter2ID.bind(this);
        this.state = {
            fighters: [],
            title: "",
            embedId: "",
            image: "",
            description: "",
            fighter1ID: "",
            fighter2ID: "",
            newFightID: "",
            successful: false,
            message: ""
        }
    }
    onChangeTitle(e) {
        this.setState({
          title: e.target.value
        });
      }

    onChangeEmbedID(e) {
        this.setState({
          embedId: e.target.value
        });
      }
    onChangeImage(e) {
        this.setState({
          image: e.target.value
        });
      }
    onChangeDescription(e) {
        this.setState({
          description: e.target.value
        });
      }
    onChangeFighter1ID(e) {
        this.setState({
          fighter1ID: e.target.value
        });
      }
    onChangeFighter2ID(e) {
        this.setState({
          fighter2ID: e.target.value
        });
      }


    handleCreateFight(e) {
        e.preventDefault();
        this.setState({
          message: "",
          successful: false
        });
        this.form.validateAll();
        if (this.checkBtn.context._errors.length === 0) {
            FightService.createFight(this.state.title,this.state.embedId,this.state.description).then( (response) => {
              this.setState({
                message: response.data.message,
                successful: true
              })
                console.log(response.data);
                localStorage.setItem("fighter1ID", this.state.fighter1ID);
                localStorage.setItem("fighter2ID", this.state.fighter2ID);
                window.location.href= "/set-image?id=" + response.data.id;
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
  }
    componentDidMount() {
        FighterService.getFighters().then( (response) => {
            this.setState({
                fighters: response.data
            })
        });
       
    }

    render() {
        return (
            
            <Form className="simple-form listing-form"
            onSubmit={this.handleCreateFight}
            ref={c => {
              this.form = c;
            }}
            >
        <h2>Create Fight</h2>
        <div className="form-background">
            <div className="form-section">
                <div className="form-group full clearfix">
                    <h3>General</h3>
                    
                    <div className="form-group full clearfix">
                        <label htmlFor="name">Title:</label>
                        <Input type="text" name="title" id="title"
                        onChange = {this.onChangeTitle}
                        validations = {[required]}
                        />
                    </div>
                    
                </div>
                <div className="form-group full clearfix">
                    <div className="form-group full">
                        <label htmlFor="embedId">YouTube Embed ID:</label>
                        <Input type="text" name="embedId" id="embedId" 
                        onChange = {this.onChangeEmbedID}
                        validations = {[required]}
                        />
                    </div>
                </div>
                <div className="form-group-half">
                        <label htmlFor="fighter-2-id">Fighter 2:</label>
                        <label className="select-arrow-label">
                        <select name="fighter-2-id" id="fighter-2-id"
                        onChange = {this.onChangeFighter2ID}
                        validations = {[required]}
                        >
                        <option value="default" selected disabled>Select Fighter</option>
                            {
                                this.state.fighters.map( fighter => 
                                    <option value={fighter.id}>{fighter.name}</option>
                                )
                            }
                        </select></label>
                    </div>
                <div className="form-group-half">
                        <label htmlFor="fighter-1-id">Fighter 1:</label>
                        <label className="select-arrow-label">
                        <select name="fighter-1-id" id="fighter-1-id"
                        onChange = {this.onChangeFighter1ID}
                        validations = {[required]}
                        >
                        <option value="default" selected disabled>Select Fighter</option>
                            {
                                this.state.fighters.map( fighter => 
                                    <option value={fighter.id}>{fighter.name}</option>
                                )
                            }
                        </select></label>
                </div>
                
                <div className="form-group full clearfix">
                    
                </div>
                <div className="form-group full">
                    <label htmlFor="description">Description:</label>
                    <textarea name="description" id="description"
                    onChange = {this.onChangeDescription}
                    validations = {[required]}
                    ></textarea>
                </div>
            </div>
            
            <div className="form-section">
                <div className="form-group full submit">
                    <button id = "create-fight-btn"> Create Fight </button>
                </div>
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
        );
    }

}
