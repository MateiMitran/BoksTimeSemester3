package com.example.demo.service;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import com.example.demo.repo.FightRepository;
import com.example.demo.repo.FighterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepo;

    private final FighterRepository fighterRepository;

    @Override
    public Fight saveFight(Fight fight) {
        return fightRepo.save(fight);
    }
    @Override
    public List<Fight> getFights() {
        return fightRepo.findAll();
    }
    @Override
    public Fight getFight(String title) {
        return fightRepo.findByTitle(title);
    }
    @Override
    public Fight getFightById(int id) {
        return fightRepo.findById(id);
    }
    @Override
    public List<Fight> getFightsByTitle(String title) {
        List<Fight> fights = fightRepo.findAll();
        List<Fight> fights1 = new ArrayList<>();
        for (Fight temp:fights) {
            if (temp.getTitle().contains(title)) {
                fights1.add(temp);
            }
        }
        return fights1;
    }
    @Override
    public void setPicture(int id, MultipartFile image) throws IOException {
        Fight fight = fightRepo.findById(id);
        fightRepo.delete(fight);
        fight.setImage(image.getBytes());
        fightRepo.save(fight);
    }
    @Override
    public byte[] getPicture(int id) {
        Fight fight = fightRepo.findById(id);
        return fight.getImage();
    }
    @Override
    public Fight addFighters(int id, int fighter1Id, int fighter2Id) {
        Fight fight = fightRepo.findById(id);
        fightRepo.delete(fight);
        fight.setFighter1(fighterRepository.findById(fighter1Id));
        fight.setFighter2(fighterRepository.findById(fighter2Id));
        return fightRepo.save(fight);
    }
    @Override
    public Fighter getFirstFighter(int id) {
        Fight fight = fightRepo.findById(id);
        return fight.getFighter1();
    }
    @Override
    public Fighter getSecondFighter(int id) {
        Fight fight = fightRepo.findById(id);
        return fight.getFighter2();
    }
    @Override
    public Fight incrementViews(int id) {
        Fight fight = fightRepo.findById(id);
        fight.setViews(fight.getViews()+1);
        return fightRepo.save(fight);
    }

    @Override
    public int getFightIdWithTitle(String title) {
        Fight fight = fightRepo.findByTitle(title);
        return fight.getId();
    }
    @Override
    public List<Fight> getFightsFromFighter(String name) {
        List<Fight> fights = new ArrayList<>();
        List<Fight> allFights = fightRepo.findAll();
        for(Fight temp : allFights) {
            if (temp.getFighter1().getName().equals(name) || temp.getFighter2().getName().equals(name)) {
                fights.add(temp);
            }
        }
        return fights;
    }
}
