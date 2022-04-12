import React, { Component } from "react";
import userService from "../services/user.service";
import './profile.component.css';
import { isEmail } from "validator";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const email = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vname = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The name must be between 3 and 20 characters.
      </div>
    );
  }
};

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.handleUpdate = this.handleUpdate.bind(this);
    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangeAge = this.onChangeAge.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeNewPassword = this.onChangeNewPassword.bind(this);
    this.onChangeCurrentPassword = this.onChangeCurrentPassword.bind(this);
   
    this.state = {
      id: "",
      firstName: "",
      lastName: "",
      age: "",
      username: "",
      email: "",
      currentPassword: "",
      newPassword: "",
      message: "",
      successful: false
    };

  }
  onChangeFirstName(e) {
    this.setState({
      firstName: e.target.value
    });
  }
  
  onChangeLastName(e) {
    this.setState({
      lastName: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangeAge(e) {
    this.setState({
      age: e.target.value
    });
  }

  onChangeNewPassword(e) {
    this.setState({
      newPassword: e.target.value
    });
  }

  onChangeCurrentPassword(e) {
    this.setState({
      currentPassword: e.target.value
    });
  }

  handleUpdate(e) {
    e.preventDefault();
    this.setState({
      message: "",
      successful: false
    });
    console.log(this.state.firstName);
    console.log(this.state.lastName);
    console.log(this.state.email);
    console.log(this.state.age);
    console.log(this.state.newPassword);
    console.log(this.state.currentPassword);
    this.form.validateAll();
    userService.updateUser(
      this.state.firstName,
      this.state.lastName,
      this.state.email,
      this.state.age,
      this.state.newPassword
    ).then(response => {
      this.setState({
        message: "Worked",
        successful: true
      });
    },
    error => {
      const resMessage =
      (error.response && error.response.data && error.response.data.message)
      || error.message || error.toString();

      this.setState({
        successful: false,
        message: resMessage
      });
      }
    );
  }


  componentDidMount() {
    userService.getUser().then(value => localStorage.setItem("userRole",value.data.roleName));
    userService.getUser().then(response => {
    this.setState({id: response.data.id, 
    firstName: response.data.firstName,
    lastName: response.data.lastName, 
    age: response.data.age,
    password: response.data.password,
    username: response.data.username, 
    email: response.data.email})
    });
  }

render() {
  return (    
    <Form className = "simple-form"
      onSubmit={this.handleUpdate}
      ref={c => {
      this.form = c;
    }}
     >
    <h2>Account Preferences</h2>
      <div className="form-group full clearfix">
      <div className="form-group-half">
          <label htmlFor="first-name">First Name:</label>
            <Input
              type="text"
              className="form-control"
              name="firstName"
              value= {this.state.firstName || ""}
              onChange={this.onChangeFirstName}
              validations={[required, vname]}
              placeholder="First Name"
            />
         </div>
        <div className="form-group-half">
         <label htmlFor="last-name">Last Name:</label>
            <Input
              type="text"
              className="form-control"
              name="lastName"
              value= {this.state.lastName || ""}
              onChange={this.onChangeLastName}
              validations={[required, vname]}
              placeholder="Last Name"
            />
        </div>
      </div>
      <div className="form-group full">
        <label htmlFor="email">Email:</label>
            <Input
              type="text"
              className="form-control"
              name="email"
              value= {this.state.email || ""}
              onChange={this.onChangeEmail}
              validations={[required, email]}
              disabled
            />
        </div>
        <div className="form-group full">
          <label htmlFor="age">Age:</label>
            <Input
              type="text"
              className="form-control"
              name="age"
              value= {this.state.age || ""}
              onChange={this.onChangeAge}
              validations={[required]}
              placeholder="Age"
            />
        </div>
        <div className="form-group full">
          <label htmlFor="current-password">Current Password:</label>
            <Input
              type="password"
              className="form-control"
              name="currentPassword"
              onChange={this.onChangeCurrentPassword }
              validations={[required]}
              placeholder="Current Password"
            />
        </div>
        <div className="form-group full">
          <label htmlFor="new-password">New Password:</label>
            <Input
              type="password"
              className="form-control"
              name="newPassword"
              onChange={this.onChangeNewPassword}
              validations={[required]}
              placeholder="New Password"
            />
        </div>
        <div className="form-group">
              <button className="btn btn-primary btn-block">
                <span>Save</span>
              </button>
        </div>
        {this.state.message && (
              <div className="form-group">
                <div className="alert alert-success" role="alert">
                  { this.state.message }
                </div>
              </div>
            )}
        <CheckButton
          style= {{display: "none"}}
          ref={c => {
          this.checkBtn = c;
           }}
        />
        </Form>
    );
  }
}
