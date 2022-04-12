import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/fights/';
const SEARCH_URL = 'http://localhost:8080/api/fight/';
const ID_URL = 'http://localhost:8080/api/fight/id/';
const CREATE_URL = 'http://localhost:8080/api/fight/create/';
const PICTURE_URL = 'http://localhost:8080/api/fight/';
const TITLE_URL = 'http://localhost:8080/api/fight-with-name/'

class FightService {

    getFights() {
        return axios.get(API_URL, {headers: authHeader()});
      }

    searchFights(title) {
      return axios.get(SEARCH_URL + title, {headers: authHeader()});
    }
    getFight(id) {
      return axios.get(ID_URL + id, {headers:authHeader()});
    }

    getFightWithTitle(title) {
      return axios.get(TITLE_URL + title + '/get-fight/', {headers:authHeader()});
    }

    getPicture(id) {
      return axios.get('http://localhost:8080/api/fight/' + id + '/image/', {headers: authHeader()}) 
      .then(response => Buffer.from(response.data, 'binary').toString('base64'));
    }

    incrementViews(id) {
      return axios.post(SEARCH_URL+ id + '/increment-views/', "", {headers:authHeader()});
    }

    createFight(title,embedId,description) {
      return axios.post(CREATE_URL, {title, embedId, description}, {headers: authHeader()});
    }
    
    addPicture(fightId, picture) {
      var formData = new FormData();
      formData.append("file",picture,"file");
      return axios.post(PICTURE_URL+fightId+'/setImage/', formData,{headers: authHeader()});
    }
    
    addFighters(fightId, fighter1Id, fighter2Id) {
      return axios.post(SEARCH_URL + fightId + "/add-fighters/" + fighter1Id + "/" + fighter2Id , "", {headers: authHeader()});
    }

    getFightsFromBoxer(name) {
      return axios.get(API_URL + name, {headers:authHeader()});
    }

}

export default new FightService();