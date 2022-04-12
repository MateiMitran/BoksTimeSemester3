package com.example.demo.service;


import com.example.demo.model.Fighter;
import com.example.demo.model.Weight_Class;
import com.example.demo.repo.WeightClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class WeightClassServiceImpl implements WeightClassService {

    private final WeightClassRepository weightClassRepo;

    @Override
    public Weight_Class saveWeightClass(Weight_Class weightClass) {
        return weightClassRepo.save(weightClass);
    }
    @Override
    public List<Weight_Class> getWeightClasses()  {
        return weightClassRepo.findAll();
    }
    @Override
    public Weight_Class getWeightClass(String name) {
        return weightClassRepo.findByName(name);
    }
    @Override
    public Weight_Class getWeightClassById(int id) {
        return weightClassRepo.findById(id);
    }
    @Override
    public List<Fighter> getFighters(String name) {
        Weight_Class weightClass = weightClassRepo.findByName(name);
        return weightClass.getFighters();
    }
    @Override
    public void addFighterToWeightClass(String name, Fighter fighter) {
        Weight_Class weightClass = weightClassRepo.findByName(name);
        weightClass.getFighters().add(fighter);
        weightClassRepo.save(weightClass);
    }
}
