package com.example.demo.service;

import com.example.demo.model.Fighter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FighterService {

    Fighter saveFighter(Fighter fighter);
    Fighter getFighterById(int id);
    Fighter getFighterByName(String name);
    boolean deleteFighter(int id);
    List<Fighter> getFighters();
    Fighter updateFighter(int id,int age, String height, String reach, int bouts, String record);
    void setPicture(int id, MultipartFile file) throws IOException;
    byte[] getPicture(int id);
}
