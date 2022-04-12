import React, {Component} from 'react';
import CoachService from '../services/coach.service';
import FighterCard from './FighterCard';

export default class MyFighters extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fighters: []
        }
    }

    componentDidMount() {
        CoachService.getCoachId(localStorage.getItem('username')).then((response) => {
            CoachService.getFighters(response.data).then((result)=> {
                console.log(result.data);
                this.setState({
                    fighters: result.data
                })
            })
        });
    }

    render() {
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