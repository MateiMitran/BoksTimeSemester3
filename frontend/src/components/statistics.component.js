import React, {Component} from 'react';
import "./fight.component.css";
import {Bar} from 'react-chartjs-2';
// eslint-disable-next-line
import { Chart as ChartJS } from 'chart.js/auto'
// eslint-disable-next-line
import { Chart }            from 'react-chartjs-2'
import FighterService from '../services/fighter.service';

export default class MyStatistics extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id:"",
            age: "",
            height: "",
            reach: "",
            bouts: "",
            record: "",
            image: "",
            punches: [],
            rounds: [],
            powerpunches: []
        }
    }

    componentDidMount() {
        const name = localStorage.getItem('username');
        FighterService.getFighterByName(name).then((response)=> {
            console.log(response.data);
            this.setState({
                id: response.data.id,
                height: response.data.height,
                reach: response.data.reach,
                bouts: response.data.bouts,
                record: response.data.record,
                image: response.data.image,
                punches: response.data.punches,
                rounds: response.data.rounds,
                powerpunches: response.data.powerpunches
            })
        });
    }

    render() {
        const data = {
            labels: ['January', 'February', 'March',
                     'April', 'May','June','July','August','September','October','November','December'],
            datasets: [
              {
                label: 'Punches',
                backgroundColor: 'rgba(242, 97, 1,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.punches
              },
              {
                label: 'Rounds',
                backgroundColor: 'rgba(30, 180, 1,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.rounds
              },
              {
                label: 'Power Punches',
                backgroundColor: 'rgba(159, 90, 253,1)',
                borderColor: 'rgba(0,0,0,1)',
                borderWidth: 2,
                data: this.state.powerpunches
              }
            ]
          }
        return(
            <div id="listing-container" className="section-wrapper">
        <div className="row clearfix" >
            <div id="listing-images-wrapper">
                <img id="fighter-image" alt = "fighter" src={`data:image/jpg;base64,${this.state.image}`} width='750px' height = '800px' />
            </div>
            <div className="listing-section left">
                <div className="listing-info-wrapper" id="title-info-wrapper">
                    <h2>{localStorage.getItem('username')}</h2>
                </div>
                <div className="listing-info-wrapper">
                    <ul className="listing-info-wrapper">
                        <li>
                            <span>Age:</span> {this.state.age}
                        </li>
                        <li>
                        <span>Height:</span> {this.state.height}
                        </li>
                         <li>
                         <span>Reach:</span> {this.state.reach}
                         </li>
                        <li>
                        <span>Bouts:</span> {this.state.bouts}
                        </li>
                        <li>
                        <span>Record:</span> {this.state.record}
                        </li>
                    </ul>
                    <h2 id="statistics-h2">Fighting Statistics 2021</h2>
                    <Bar id= "chart"
                         data={data}
                         options={{
                         title:{
                         display:true,
                         fontSize:20
                        },
                        legend:{
                        display:true,
                        position:'right'
                         }
                        }}
                         />
                    
                </div>
            </div>
        </div>
    </div>
        );
    }
}