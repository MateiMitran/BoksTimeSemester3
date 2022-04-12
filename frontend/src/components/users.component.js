import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import "./users.component.css";


 export default function UsersComponent() {

  const [users, setUsers] = useState([]);


   
  
  useEffect(() => {
    UserService.getUsers().then(response => setUsers(response.data));
  },[]);
  
 
    console.log(users);
    if (users) {
      return (
      <div>
          <h1 className = "text-center">Users List</h1>
            <table className = "table table-striped">
              <thead>
                <tr>
                    <td>User Id</td>
                    <td>User First Name</td>
                    <td>User Last Name</td>
                    <td>Username</td>
                    <td>User Email</td>
                </tr>
              </thead>
                <tbody>
                    {
                        users.filter(user => user.name !== localStorage.getItem('username')).map(
                        user => 
                          <tr key = {user.id}>
                              <td> {user.id} </td>
                              <td> {user.firstName} </td>
                              <td> {user.lastName} </td>
                              <td> {user.username} </td>
                              <td> {user.email} </td>
                              <td> <button id = "delete-button" onClick = {() =>
                               [ UserService.deleteUser(user.id)
                               , window.location.reload()]} > X </button></td>
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
          <h1 className = "text-center">Fight List</h1>
            <table className = "table table-striped">
              <thead>
                <tr>
                    <td>User Id</td>
                    <td>User First Name</td>
                    <td>User Last Name</td>
                    <td>Username</td>
                    <td>User Email</td>
                </tr>
              </thead>
              </table>
            </div>
        );
    }
  }
  