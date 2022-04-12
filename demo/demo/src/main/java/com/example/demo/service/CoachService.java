package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;

import java.util.List;

public interface CoachService {

    Coach saveCoach(Coach coach);
    Coach addFighter(int id,int fighterId);
    Coach removeFighter(int id,int fighterId);
    List<Fighter> getFightersFromCoach(int id);
    int getIdFromName(String name);

}
