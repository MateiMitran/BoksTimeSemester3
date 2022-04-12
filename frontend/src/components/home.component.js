/* eslint-disable */ 
import React, { Component } from "react";
import './home.component.css';
import LPFM from '../images/LPFM.jpg';
import MTEH from '../images/MTEH.jpg';
import TFDW from '../images/TFDW.jpg';

export default class Home extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: "",
    };
  }
  
  componentDidMount() {
        this.setState({
          content: "home page"
        });
        
  }

  render() {
    let roleName = "";
        if (localStorage.getItem("userRole")) {
           roleName = localStorage.getItem("userRole");
        } 
    console.log(roleName);
    return (
      <><div id="jumbotron">
        <div id="jumbotron-overlay">
          <div className="row">
            <div id="jumbotron-moto-container">
              <h1>Boks<span>Time</span></h1>
              <h3>The biggest <span>boxing</span> platform!</h3>
              { (!roleName) && (
              <a href="/register">Join Now</a>
              )}
            </div>
          </div>
        </div>
      </div><div id="recent-listings-container" className="section-container">
          <h2>Check out these <span>exciting</span> boxing matches...</h2>
          <div className="big-slider-wrapper" id="recent-listings-slider">
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
        </div><div id="manufacturers-container" className="section-container">
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