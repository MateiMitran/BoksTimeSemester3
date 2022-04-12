/* eslint-disable */ 
import React, {Component} from "react";
import "./fight.component.css";
import Video from "./Video";
import FightService from "../services/fight.service";
export default class FightComponent extends Component {


    constructor(props) {
        super(props);
        this.state = {
            title: "",
            embedId: "",
            views: "",
            fighter1: {},
            fighter2: {}
        };
    }
    componentDidMount() {
        const query = new URLSearchParams(this.props.location.search);
        const id = query.get('id');

        FightService.getFight(id).then( (response) => {
            this.setState({
                title: response.data.title,
                embedId: response.data.embedId,
                views: response.data.views,
                fighter1: response.data.fighter1,
                fighter2: response.data.fighter2
            });
        }
        )

        FightService.incrementViews(id);
    }
    
    render() {
        console.log(this.state.fighter1);
        console.log(this.state.fighter2);
        return(
            <div id="listing-container" className="section-wrapper">
        <div className="row clearfix">
        <Video embedId = {this.state.embedId} />
            <div id="listing-images-wrapper">
           
            </div>
            <div className="listing-section left">
                <div className="listing-info-wrapper" id="title-info-wrapper">
                    <h2>{this.state.title}</h2>
                    <p id="listing-km">{this.state.views} views</p>
                </div>
                <div className="listing-info-wrapper">
                    <a onClick = {()=> {window.location.href = "/fighter?id="+ this.state.fighter1.id}}>
                    <h3 id="firstFighter">{this.state.fighter1.name}</h3>
                    </a>
                    <a onClick = {()=> {window.location.href = "/fighter?id="+ this.state.fighter2.id}}>
                    <h3 id="secondFighter">{this.state.fighter2.name}</h3>
                    </a>
                    <ul>
                        <li>
                            <span>Age:</span> {this.state.fighter1.age}
                        </li>
                        <li>
                            <span>Height:</span> {this.state.fighter1.height}
                        </li>
                        <li>
                            <span>Reach:</span> {this.state.fighter1.reach}
                        </li>
                        <li>
                            <span>Bouts:</span> {this.state.fighter1.bouts}
                        </li>
                        <li>
                            <span>Record:</span> {this.state.fighter1.record}
                        </li>
                        <li>
                            <span>Age:</span> {this.state.fighter2.age}
                        </li>
                        <li>
                            <span>Height:</span> {this.state.fighter2.height}
                        </li>
                        <li>
                            <span>Reach:</span> {this.state.fighter2.reach}
                        </li>
                        <li>
                            <span>Bouts:</span> {this.state.fighter2.bouts}
                        </li>
                        <li>
                            <span>Record:</span> {this.state.fighter2.record}
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
        );
    }
}