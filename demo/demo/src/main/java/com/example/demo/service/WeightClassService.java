package com.example.demo.service;

import com.example.demo.model.Fighter;
import com.example.demo.model.Weight_Class;

import java.util.List;

public interface WeightClassService {

    Weight_Class saveWeightClass(Weight_Class weightClass);
    List<Weight_Class> getWeightClasses();
    Weight_Class getWeightClass(String name);
    Weight_Class getWeightClassById(int id);
    List<Fighter> getFighters(String name);
    void addFighterToWeightClass(String name, Fighter fighter);

}
