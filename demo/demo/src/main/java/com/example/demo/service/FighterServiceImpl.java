package com.example.demo.service;

import com.example.demo.model.Fighter;
import com.example.demo.repo.FighterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FighterServiceImpl implements FighterService {

    private final FighterRepository fighterRepository;

    @Override
    public Fighter saveFighter(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    @Override
    public Fighter getFighterById(int id) {
        return fighterRepository.findById(id);
    }

    @Override
    public Fighter getFighterByName(String name) {
        return fighterRepository.findByName(name);
    }

    @Override
    public List<Fighter> getFighters() {
        return fighterRepository.findAll();
    }

    @Override
    public boolean deleteFighter(int id) {
        Fighter fighter = fighterRepository.findById(id);
        if (fighter!=null) {
            fighterRepository.delete(fighter);
            return true;
        }
        return false;
    }

    @Override
    public Fighter updateFighter(int id,int age, String height, String reach, int bouts, String record) {
        Fighter fighter = fighterRepository.findById(id);
        fighter.setAge(age);
        fighter.setHeight(height);
        fighter.setReach(reach);
        fighter.setBouts(bouts);
        fighter.setRecord(record);
        return fighterRepository.save(fighter);
    }
    @Override
    public void setPicture(int id, MultipartFile image) throws IOException {
        Fighter fighter = fighterRepository.findById(id);
        fighterRepository.delete(fighter);
        fighter.setImage(image.getBytes());
        fighterRepository.save(fighter);
    }
    @Override
    public byte[] getPicture(int id) {
        Fighter fighter = fighterRepository.findById(id);
        return fighter.getImage();
    }
}
