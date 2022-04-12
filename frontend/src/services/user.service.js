import axios from 'axios';
import authHeader from './auth-header';
import authService from './auth.service';

const USER_URL = 'http://localhost:8080/api/users/';
const GETUSER_URL = 'http://localhost:8080/api/user/';
const UPDATEUSER_URL = 'http://localhost:8080/api/update?';
const DELETEUSER_URL = 'http://localhost:8080/api/user/';


class UserService {
  
  getUsers() {
    return axios.get(USER_URL, {headers:authHeader()});
  } 
  getUser() {
    return axios.get(GETUSER_URL + authService.getUsername(), {headers:authHeader()});
  }

  updateUser(firstName, lastName, email, age, password) {
    return axios.post(UPDATEUSER_URL+"firstName="+firstName+
                    "&lastName="+lastName+"&email="+email+"&age="+age+
                    "&password="+password
    , "",{headers:authHeader()});
  }

  deleteUser(id) {
    return axios.delete(DELETEUSER_URL + id, {headers:authHeader()});
  }





}
export default new UserService();