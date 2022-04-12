import React, {Component} from "react";
import Card from "./Card";
import FightService from "../services/fight.service";

export default class MyFights extends Component {

    constructor(props){
        super(props);
        this.state = {
            fights: []
        }
    }

    componentDidMount() {
        FightService.getFightsFromBoxer(localStorage.getItem('username')).then((response)=> {
            console.log(response.data);
            this.setState({
                fights: response.data
            })
        });
    }


    render() {
        return (
            <div id="search-results-container" className="section-container">
                <div className="row">
                    <div id="search-results-wrapper">
                        {
                            this.state.fights.map(
                                fight=>
                                <Card  title = {fight.title}
                                        views = {fight.views}
                                        imageBinary = {fight.image}
                                        id = {fight.id}
                                        description = {fight.description}
                                />
                            )
                        }
                    </div>
                </div>
            </div>
            );
    }

}