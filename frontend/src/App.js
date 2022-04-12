import React, { Component } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";


import Navbar from "./components/Navbar";

class App extends Component {
  render() {
    return (
      <div className="application">
        <Navbar role= {localStorage.getItem("userRole")}></Navbar> 
      </div>
    );
  }
  
}

export default App;
