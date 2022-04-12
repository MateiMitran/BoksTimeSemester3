/* eslint-disable */ 
import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import FightService from "../services/fight.service";
import UserService from "../services/user.service";
import "./fights.component.css";
import LPFM from '../images/LPFM.jpg';
import MTEH from '../images/MTEH.jpg';
import TFDW from '../images/TFDW.jpg';

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

export default class FightsComponent extends Component {

  constructor(props) {
    super(props);
    this.handleSearch = this.handleSearch.bind(this);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.reload = this.reload.bind(this);

    this.state = {
      searchedFights:[],
      title:""
    };
  }
  onChangeTitle(e) {
    this.setState({
      title: e.target.value
    });
  }
  handleSearch(e) {
    e.preventDefault();
    this.form.validateAll();
    if (this.checkBtn.context._errors.length === 0) {
        FightService.searchFights(this.state.title).then((response) => {
          this.setState({searchedFights:response.data})
          window.location.href = "fight-result?title="+this.state.title;
          console.log(this.state.searchedFights);
        });
    }
    
  }
  componentDidMount() {
    this.reload();

   UserService.getUser().then( (response) => {
   localStorage.setItem("userRole",response.data.roleName);
    });
  }

  reload() {
    this.forceUpdate();
  }
      render(){
      return (
    <><div id="search-jumbotron-container">
          <div id="search-jumbotron">
            <div id="search-jumbotron-overlay">
              <div className="row-small">
                <h1>Endless fights <span>await</span> you.</h1>
                <Form 
                 id="search-form" 
                 onSubmit={this.handleSearch}
                 ref={c => {
                 this.form = c;
                }}>
                    <label id="search-select-arrow-label">
                      </label>
                      <Input type="text"
                      name="title"
                      onChange={this.onChangeTitle}
                      validations={[required]}
                      placeholder="Title"
                      />
                      <div className="form-group" id ="btnSearch">
                      <button className="btn btn-primary btn-block">Search</button>
                      </div>

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
        </div>
        <div id="recent-listings-container" className="section-container">
            <h2>Check out these <span>recent</span> fights</h2>
            <div className="big-slider-wrapper" id="recent-listings-slider">
            <button className="slider-navigation-button" id="previous-slide">
                <i><ion-icon name="chevron-back-outline"></ion-icon></i>
              </button>
              <div className="slider-container">
                <div className="slider clearfix">
                <a onClick = {()=> {window.location.href =  "/fight?id=3"}} className="item-link">
                  <div
                    className="item"
                    style={{ backgroundImage: `url(${LPFM})` }}
                  >
                    <div className="item-content">
                      <h3>Mayweather vs Paul</h3>
                    </div>
                  </div>
                  <div className="item-desc-container">
                    <p className="item-desc bold">Posted 10 days ago</p>
                    <p className="item-desc">The money fight! The undefeated vs the youtube star!</p>
                  </div>
                </a>
                <a onClick = {()=> {window.location.href =  "/fight?id=2"}} className="item-link">
                  <div
                    className="item"
                    style={{ backgroundImage: `url(${MTEH})` }}
                  >
                    <div className="item-content">
                      <h3>Tyson vs Holyfield</h3>
                    </div>
                  </div>
                  <div className="item-desc-container">
                    <p className="item-desc bold">Posted 3 years ago</p>
                    <p className="item-desc">Biggest fight in the world! Expect the unexpected!</p>
                  </div>
                </a>
                <a onClick = {()=> {window.location.href =  "/fight?id=4"}} className="item-link">
                  <div
                    className="item"
                    style={{ backgroundImage: `url(${TFDW})` }}
                  >
                    <div className="item-content">
                      <h3>Fury vs Wilder 3</h3>
                    </div>
                  </div>
                  <div className="item-desc-container">
                    <p className="item-desc bold">Listed 2 weeks ago</p>
                    <p className="item-desc">Biggest fight of 2021! Two undefeated champions!</p>
                  </div>
                </a>
                </div>
              </div>
              
            </div>
          </div>
          <div id="manufacturers-container" className="section-container">
            <div className="row">
              <h2>Browse <span>boxers</span></h2>
              <ul>
                <li><a href="# "><p>Anthony Joshua</p></a></li>
                <li><a href="# "><p>Alfredo Angulo</p></a></li>
                <li><a href="# "><p>Alexis Argüello</p></a></li>
                <li><a href="# "><p>Alex Arthur</p></a></li>
                <li><a href="# "><p>Billy Backus</p></a></li>
                <li><a href="# "><p>Bob Baker</p></a></li>
                <li><a href="# "><p>Brian Battease</p></a></li>
                <li><a href="# "><p>Canelo Álvarez</p></a></li>
                <li><a href="# "><p>Deontay Wilder</p></a></li>
                <li><a href="# "><p>David Haye</p></a></li>
                <li><a href="# "><p>Denton Daley</p></a></li>
                <li><a href="# "><p>Fury Tommy</p></a></li>
                <li><a href="# "><p>Fury Tyson</p></a></li>
                <li><a href="# "><p>Farr Tommy</p></a></li>
                <li><a href="# "><p>Garcia Ryan</p></a></li>
                <li><a href="# "><p>Ganse Joe</p></a></li>
                <li><a href="# "><p>Gomez Michael</p></a></li>
                <li><a href="# "><p>Holyfield Evander</p></a></li>
                <li><a href="# "><p>Ike Ibeabuchi</p></a></li>
                <li><a href="# "><p>Julian Jackson</p></a></li>
                <li><a href="# "><p>Jack Johnson</p></a></li>
                <li><a href="# "><p>Klitschko Wladimir</p></a></li>
                <li><a href="# "><p>Lopez Ricardo</p></a></li>
                <li><a href="# "><p>Lyle Ron</p></a></li>
                <li><a href="# "><p>Limón Rafael</p></a></li>
                <li><a href="# "><p>Lennox Lewis</p></a></li>
                <li><a href="# "><p>Lee Andy</p></a></li>
                <li><a href="# "><p>Mendoza Daniel</p></a></li>
                <li><a href="# "><p>Mayweather Floyd</p></a></li>
                <li><a href="# "><p>Mabuza Kaiser</p></a></li>
                <li><a href="# "><p>Mancini Ray</p></a></li>
                <li><a href="# "><p>Martin Bob</p></a></li>
                <li><a href="# "><p>Maske Henry</p></a></li>
                <li><a href="# "><p>Norris Terry</p></a></li>
                <li><a href="# "><p>Pacquiao Manny</p></a></li>
                <li><a href="# "><p>Ramos Sugar</p></a></li>
                <li><a href="# "><p>Roberto Paolo</p></a></li>
                <li><a href="# "><p>Sugar Ramos</p></a></li>
                <li><a href="# "><p>Sam Sexton</p></a></li>
                <li><a href="# "><p>Sharkey Tom</p></a></li>
                <li><a href="# "><p>Tony Thompson</p></a></li>
                <li><a href="# "><p>Tunney Gene</p></a></li>
                <li><a href="# "><p>Valero Edwin</p></a></li>
                <li><a href="# "><p>Vrij James</p></a></li>
              </ul>
            </div>
          </div></>
    
    );
  } 
}
  