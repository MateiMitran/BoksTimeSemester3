import React, { Component } from "react";
import { Switch, Route, Link} from "react-router-dom";
import "./Navbar.css";

import CoachRoute from "./CoachRoute";
import BoxerRoute from "./BoxerRoute";
import UserRoute from "./UserRoute";
import AdminRoute from "./AdminRoute";
import Login from "./login.component";
import Register from "./register.component";
import Home from "./home.component";
import Profile from "./profile.component";
import FightsComponent from "./fights.component";
import UsersComponent from "./users.component";
import WeightClassesComponent from "./weightclasses.component";
import FightResultComponent from "./fight.result.component";
import AuthService from "../services/auth.service";
import FightComponent from "./fight.component";
import Chat from "./chat.component";
import WeightclassComponent from "./weightclass.component";
import FighterComponent from "./fighter.component";
import CreateFight from "./create-fight.component";
import SetImageComponent from "./set-fight-image.component";
import CreateFighter from "./create-fighter.component";
import SetFighterImageComponent from "./set-fighter-image.component";
import Fighters from "./fighters.component";
import MyFights from "./my-fights.component";
import MyStatistics from "./statistics.component";
import MyFighters from "./my-fighters.component";
import Requests from "./requests.component";
import Unauthorized from "./Unauthorized";


class Navbar extends Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);
        this.state = {
          currentUser: undefined,
        };
      }
      componentDidMount() {
        const user = AuthService.getCurrentUser();
        
        if (user) {
          this.setState({
            currentUser: user,
          });
        }
        
      }
      
      logOut() {
        AuthService.logout();
      }
     
    render() {

      
        const { currentUser } = this.state;
        let roleName = this.props.role;
        console.log(roleName);
        return (
            <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/"} className="navbar-brand">
            BoksTime
          </Link>
          <div className="navbar-nav mr-auto">
              {!currentUser && (
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home
              </Link>
            </li>
              )}


            {roleName === "ROLE_ADMIN" && (
              <li className="nav-item">
                <Link to={"/users"} className="nav-link">
                  Users
                </Link>
              </li>
            )}

            {currentUser && roleName !== "ROLE_BOXER" && roleName !=="ROLE_COACH" && (
              <li className="nav-item">
                <Link to={"/fights"} className="nav-link">
                  Fights
                </Link>
              </li>
            )}
            {currentUser && roleName !== "ROLE_ADMIN" && (
              <li className="nav-item">
                <Link to={"/chat"} className="nav-link">
                  Chat
                </Link>
              </li>
            )}

            {currentUser && roleName !== "ROLE_ADMIN" && roleName !== "ROLE_BOXER" && roleName !== "ROLE_COACH" && (
              <li className="nav-item">
                <Link to={"/weight-classes"} className="nav-link">
                  Weight Classes
                </Link>
              </li>
            )}
            {currentUser && (roleName === "ROLE_ADMIN" || roleName ==="ROLE_COACH") && (
              <li className="nav-item">
                <Link to={"/fighters"} className="nav-link">
                  Fighters
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_ADMIN" && (
              <li className="nav-item">
                <Link to={"/create-fight"} className="nav-link">
                  Create Fight
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_ADMIN" && (
              <li className="nav-item">
                <Link to={"/create-fighter"} className="nav-link">
                  Create Fighter
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_BOXER" && (
              <li className="nav-item">
                <Link to={"/my-fights"} className="nav-link">
                  My Fights
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_BOXER" && (
              <li className="nav-item">
                <Link to={"/requests"} className="nav-link">
                  Requests
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_BOXER" && (
              <li className="nav-item">
                <Link to={"/statistics"} className="nav-link">
                  Statistics
                </Link>
              </li>
            )}
            {currentUser && roleName === "ROLE_COACH" && (
              <li className="nav-item">
                <Link to={"/my-fighters"} className="nav-link">
                  My Fighters
                </Link>
              </li>
            )}
          </div>

          {currentUser ? (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/profile"} className="nav-link">
                  Profile
                </Link>
              </li>
              <li className="nav-item">
                <a href="/login" className="nav-link" id="logout-button" onClick={this.logOut}>
                  LogOut
                </a>
              </li>
            </div>
          ) : (
            <div className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to={"/login"} className="nav-link">
                  Login
                </Link>
              </li>

              <li className="nav-item">
                <Link to={"/register"} className="nav-link">
                  Sign Up
                </Link>
              </li>
            </div>
          )}
        </nav>

        <div className="container mt-3">
            <Switch>
              <Route exact path={["/", "/home"]} component={Home} />
              <Route exact path="/login" component={Login}/>
              <Route exact path="/register" component={Register}/>
              <Route exact path="/unauthorized" component={Unauthorized} />
              <UserRoute exact path="/profile" component={Profile}/>
              <AdminRoute exact path="/users" component={UsersComponent} />
              <UserRoute exact path="/fights" component={FightsComponent} />
              <UserRoute exact path="/fight-result" component={FightResultComponent} />
              <UserRoute exact path="/fight" component={FightComponent} />
              <UserRoute exact path="/weight-classes" component={WeightClassesComponent} />
              <UserRoute exact path="/chat" component={Chat} />
              <UserRoute exact path="/weight-class" component={WeightclassComponent} />
              <UserRoute exact path="/fighter" component={FighterComponent} />
              <AdminRoute exact path= "/create-fight" component={CreateFight} />
              <AdminRoute exact path="/set-image" component={SetImageComponent} />
              <AdminRoute exact path="/create-fighter" component={CreateFighter} />
              <AdminRoute exact path="/set-fighter-image" component={SetFighterImageComponent} />
              <CoachRoute exact path="/fighters" component={Fighters} />
              <CoachRoute exact path="/my-fighters" component={MyFighters} />
              <BoxerRoute exact path="/my-fights" component={MyFights} />
              <BoxerRoute exact path="/statistics" component={MyStatistics} />
              <BoxerRoute exact path="/requests" component={Requests} />
            </Switch>
        </div>
      </div>
        );

    }
    
    
}
export default Navbar;