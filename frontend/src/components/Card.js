import React, { Component } from "react";
import './Card.css';

export default class Card extends Component {

    render() {
        return (
            <div className="card">
                <div className="card-image"
                >
                    <img className="card-image" alt="fight" src={`data:image/jpg;base64,${this.props.imageBinary}`}/>
                <p className="price-title">~{this.props.views} views</p>
                    <div className="card-title">
                         <p>{this.props.title}</p>
                    </div>
                </div>
                <div className="card-text">
                    <div className="card-text-lane">
                        <p className="listing-info">_____________________________________________<a href="/home">BoksTime</a></p>
                        <i className="heart-icon"><ion-icon className="heart-outline"></ion-icon></i>
                </div>
                    <p>{this.props.description}</p>
                    {
                        // eslint-disable-next-line
                    <a id = "viewmatch-button" onClick = {() => {window.location.href = "fight?id="+this.props.id}} className="view-listing-button">View Match</a>
                    }
                </div>
            </div>
        );
    }
}





