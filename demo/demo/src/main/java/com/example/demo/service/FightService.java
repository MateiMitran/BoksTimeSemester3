package com.example.demo.service;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FightService {

    Fight saveFight(Fight fight);
    List<Fight> getFights();
    Fight getFight(String title);
    Fight getFightById(int id);
    List<Fight> getFightsByTitle(String title);
    void setPicture(int id, MultipartFile file) throws IOException;
    byte[] getPicture(int id);
    Fight addFighters(int id, int fighter1Id,int fighter2Id);
    Fighter getFirstFighter(int id);
    Fighter getSecondFighter(int id);
    Fight incrementViews(int id);
    int getFightIdWithTitle(String title);
    List<Fight> getFightsFromFighter(String name);
}
