import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/api/";
const JWT_URL = "http://localhost:8080/api/jwt/decode/";

const params = new URLSearchParams();

const config = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
}

class AuthService {

  login(username, password) {
    console.log(username,password);
    localStorage.setItem("username",username);
    params.append('username',username);
    params.append('password',password);
    return axios
      .post(API_URL + "login", params, config)
      .then((result) => {
        if (result.data.access_token) {
          console.log(result.data.access_token);
          console.log(JSON.stringify(result.data));
          localStorage.setItem("access_token", result.data.access_token);
          localStorage.setItem("currentUser", "yep");
        }
        console.log(result.data);
        return result.data;
      });
  }

  logout() {
    localStorage.clear();
  }

  register(username, email, password, roleName) {
    console.log(username,email,password,roleName);
    return axios.post(API_URL + "register", {
      username,
      email,
      password,
      roleName
    });
  }

  getCurrentUser() {
    return localStorage.getItem('currentUser');
  }
  getAccessToken() {
    return localStorage.getItem('access_token');
  }
  getUsername() {
    return localStorage.getItem('username');
  }

  getDecodedJWT(token) {
    return axios.post(JWT_URL, token, {headers:authHeader()});
  }
  
}

export default new AuthService();