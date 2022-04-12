import React, {Component} from "react";
import Card from "./Card";
import FightService from "../services/fight.service";

export default class FightResultComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            fights: []
        };
    }

    componentDidMount() {
        const query = new URLSearchParams(this.props.location.search);
        const title = query.get('title')
        FightService.searchFights(title).then( (response) => {
            this.setState({
                fights: response.data
            });
         }
      )
    }
    

  render() {

    return (
    <div id="search-results-container" className="section-container">
        <div className="row">
            <h2>Found <span>{ this.state.fights.length } fights</span> that match your criteria...</h2>
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