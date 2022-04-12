/* eslint-disable */ 
import axios from 'axios';
import authHeader from './auth-header';

const FIGHTER_URL = 'http://localhost:8080/api/fighter/';
const FIGHTERS_URL = 'http://localhost:8080/api/fighters/';
const REQUESTS_URL = 'http://localhost:8080/api/requests/from-fighter/';

class FighterService {

    getFighter(id) {
        return axios.get(FIGHTER_URL+id,{headers: authHeader()});
    }

    getFighters() {
        return axios.get(FIGHTERS_URL, {headers: authHeader()});
    }

    getFighterByName(name) {
        return axios.get("http://localhost:8080/api/get-fighter/"+name, {headers:authHeader()});
    }
    createFighter(name,age,height,reach,bouts,record,punches,rounds,powerpunches) {
        return axios.post(FIGHTER_URL+"create/", {name,age,height,reach,bouts,record,punches,rounds,powerpunches}, {headers: authHeader()});
    }
    
    getRequests(id) {
        return axios.get(REQUESTS_URL+id,{headers:authHeader()});
    }

    hasCoach(id) {
        return axios.get(FIGHTER_URL + id + "/has-coach", {headers:authHeader()});
    }
    addPicture(fighterId, picture) {
        var formData = new FormData();
        formData.append("file",picture,"file");
        return axios.post(FIGHTER_URL+ fighterId + "/setImage", formData, {headers: authHeader()});
    }

    deleteFighter(id) {
        return axios.delete(FIGHTER_URL+id,{headers: authHeader()});
    }

    updateFighter(id,age,height,reach,bouts,record) {
        return axios.post(FIGHTER_URL + id + `/update/?age=` + age + `&height=${height}` + `&reach=${reach}` + "&bouts="+bouts + "&record="+record, "",
                    {headers:authHeader()});
    }

}
export default new FighterService();