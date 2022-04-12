package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.repo.CoachRepository;
import com.example.demo.repo.FighterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CoachServiceImpl implements CoachService{

    private final CoachRepository coachRepository;

    private final FighterRepository fighterRepository;

    @Override
    public Coach saveCoach(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public Coach addFighter(int id,int fighterId) {
        Coach coach = coachRepository.findById(id);
        Fighter fighter = fighterRepository.findById(fighterId);
        coach.getFighters().add(fighter);
        return coachRepository.save(coach);
    }

    @Override
    public Coach removeFighter(int id, int fighterId) {
        Coach coach = coachRepository.findById(id);
        Fighter fighter = fighterRepository.findById(fighterId);
        coach.getFighters().remove(fighter);
        return coachRepository.save(coach);
    }

    @Override
    public List<Fighter> getFightersFromCoach(int id) {
        Coach coach = coachRepository.findById(id);
        return coach.getFighters();
    }

    @Override
    public int getIdFromName(String name) {
        Coach coach = coachRepository.findByName(name);
        return (int) coach.getId();
    }
}
