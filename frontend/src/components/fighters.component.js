import React, {Component} from "react";
import FighterService from '../services/fighter.service';
import "./weightclass.component.css";
import FighterCard from "./FighterCard";

export default class Fighters extends Component {
    constructor(props){
        super(props);
        this.state = {
            fighters: []
        }
    }

    componentDidMount() {
        FighterService.getFighters().then((response)=> {
            this.setState({
                fighters:response.data
            });
        });
    }

    render() {
        console.log(this.state.fighters);
        return(
            <div id="categories-container" className="section-container">
            <div className="row">
                <h2 id="title-thing">Browse <span id="span-thing">fighters</span></h2>
                <div id="categories-wrapper">
                    {
                        this.state.fighters.map(
                            fighter =>
                            <FighterCard
                                        id = {fighter.id}
                                        fighterName = {fighter.name}
                                        imageBinary = {fighter.image}
                            />
                        )
                    }
                </div>
            </div>
        </div>
        );
    }
}
