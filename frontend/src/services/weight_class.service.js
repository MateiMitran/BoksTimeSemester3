import axios from 'axios';
import authHeader from './auth-header';

const WC_URL = 'http://localhost:8080/api/weight_classes/';
const FIGTHTERS_URL = 'http://localhost:8080/api/weight_class/';


class WeightClassService {
  
  getWeightClasses() {
    return axios.get(WC_URL, {headers:authHeader()});
  } 

  getFighters(name) {
    return axios.get(FIGTHTERS_URL+name+'/fighters/', {headers:authHeader()});
  }

  addFighter(wcName, fighterName) {
    return axios.post(FIGTHTERS_URL + wcName + "/add/" + fighterName, "", {headers: authHeader()});
  }

}
export default new WeightClassService();