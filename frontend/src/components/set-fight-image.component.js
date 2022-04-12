import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
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

export default class SetImageComponent extends Component {

    constructor(props) {
        super(props);
        this.onChangeImage = this.onChangeImage.bind(this);
        this.handleSetImage = this.handleSetImage.bind(this);
        this.state = {
            image: undefined,
            fightId: "",
        }
    }

    componentDidMount() {
        const query = new URLSearchParams(this.props.location.search);
        const id = query.get('id');
        this.setState({
            fightId: id
        });
        console.log(this.state.fightId);
        FightService.addFighters(id, localStorage.getItem("fighter1ID"), localStorage.getItem("fighter2ID")).then( (response) => {
          console.log(response.data);
        });
        
    }

    onChangeImage(e) {
        let file = e.target.files;
        this.setState({
          image: file
        });
      }

    handleSetImage(e) {
        let Image = this.state.image[0];
        e.preventDefault();
        this.form.validateAll();
        if (this.checkBtn.context._errors.length === 0) {
         FightService.addPicture(this.state.fightId,Image).then( (response) => {
            console.log(response.data);
            localStorage.removeItem("fighter1ID");
            localStorage.removeItem("fighter2ID");
            window.location.href="/fight?id=" + this.state.fightId;
         });
        }
    }

    render() {
        return(
            <Form className="simple-form listing-form"
            onSubmit={this.handleSetImage}
            ref={c => {
              this.form = c;
            }}
            >
                <div className="form-section">
                <h3>Image</h3>
                <div className="form-group full">
                    <label htmlFor="image">Upload Image:</label>
                    <div className="select-images-wrapper">
                        <div className="select-image-box">
                            <Input type="file" name="image" id="image" className="inputfile" accept="image/heic, image/jpg, image/png, image/jpeg, image/webp"
                            onChange = {this.onChangeImage}
                            validations = {[required]}
                            />
                            <label htmlFor="image"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"/></svg> <span>Choose a file...</span></label>
                        </div>
                    </div>
                </div>
            </div>
            <div className="form-section">
                <div className="form-group full submit">
                    <button id = "create-fight-btn"> Set Image </button>
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