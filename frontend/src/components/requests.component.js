import React, {Component} from 'react';
import FighterService from '../services/fighter.service';
import CoachService from '../services/coach.service';

export default class Requests extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            requests : []
        }
    }

    componentDidMount() {
        console.log(localStorage.getItem("username"));
        FighterService.getFighterByName(localStorage.getItem("username")).then((response) => {
            console.log(response.data);
            FighterService.getRequests(response.data.id).then((result) => {
                this.setState({
                    requests: result.data
                })
            });
        });
        
    }

    render() {
        
        if (this.state.requests.length>0) {

        
        return (
            
            <div>
          <h1 className = "text-center">Request List</h1>
            <table className = "table table-striped">
              <thead>
                <tr>
                    <td>Request Id</td>
                    <td>Coach Id</td>
                </tr>
              </thead>
                <tbody>
                    {
                        this.state.requests.map(
                        request => 
                          <tr key = {request.id}>
                              <td> {request.id} </td>
                              <td> {request.coachId} </td>
                              <td> <button id = "delete-button" onClick = {() =>
                               [ CoachService.acceptRequest(request.id)
                               , window.location.reload()]} > Accept </button></td>
                          </tr>
                        )
                    }
                 </tbody>
              </table>
        </div>
        );
                }
        else {
            return (
            <div>
                <h2>No Requests</h2>
            </div>
            );
        }
    }
}