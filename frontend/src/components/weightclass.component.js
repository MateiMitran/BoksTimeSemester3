import React, { Component } from "react";
import FighterCard from "./FighterCard";
import WeightClassService from "../services/weight_class.service";
export default class WeightclassComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fighters:[]
        };
    }


    componentDidMount() {
        const query = new URLSearchParams(this.props.location.search);
        const name = query.get('name');
        WeightClassService.getFighters(name).then( (response) => {
            this.setState({
                    fighters: response.data
                });
            }
        )
        
    }



    render() {
        console.log(this.state.fighters);
        return(
            <div id="categories-container" className="section-container">
            <div className="row">
                <h2>Browse <span>fighters</span></h2>
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