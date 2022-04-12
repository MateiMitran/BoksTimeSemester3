import axios from 'axios';
import authHeader from './auth-header';

const CREATE_URL = 'http://localhost:8080/api/coach/create/';
const REQUEST_URL = 'http://localhost:8080/api/request/create';
const ID_URL = 'http://localhost:8080/api/coach/';
const ACCEPTREQUEST_URL = 'http://localhost:8080/api/request/';

class CoachService {

    addCoach(name) {
        console.log(name);
        return axios.post(CREATE_URL, {name});
    }

    sendRequest(coachId, fighterId) {
        console.log(coachId);
        console.log(fighterId);
        return axios.post(REQUEST_URL, {coachId,fighterId}, {headers: authHeader()});
    }

    acceptRequest(id) {
        return axios.post(ACCEPTREQUEST_URL  + id + "/delete", "", {headers:authHeader()});
    }

    getCoachId(name) {
        return axios.get(ID_URL+name,{headers: authHeader()});
    }

    addFighter(coachId, fighterId) {
        return axios.post(ID_URL+coachId + '/add-fighter/' + fighterId, "", {headers:authHeader()});
    }

    getFighters(id) {
        return axios.get(ID_URL+ id + "/fighters", {headers:authHeader()});
    }

}
export default new CoachService();