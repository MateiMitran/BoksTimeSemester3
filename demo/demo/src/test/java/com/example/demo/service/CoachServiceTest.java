package com.example.demo.service;

import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.repo.CoachRepository;
import com.example.demo.repo.FighterRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {CoachService.class})
@ActiveProfiles(value = "test")
public class CoachServiceTest {

    @Mock
    CoachRepository coachRepository;

    @Mock
    FighterRepository fighterRepository;

    @InjectMocks
    CoachServiceImpl coachService;

    @Test
    public void addCoachTest() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        //act
        when(coachRepository.save(any(Coach.class))).thenReturn(coach);
        Coach saved = coachService.saveCoach(coach);
        //assert
        Assert.assertEquals(coach,saved);
    }

    @Test
    public void addFighterToCoachTest() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(coachRepository.findById(1)).thenReturn(coach);
        when(fighterRepository.findById(1)).thenReturn(fighter1);
        when(coachRepository.save(any(Coach.class))).thenReturn(coach);
        Coach foundCoach = coachService.addFighter(1,1);
        //assert
        Assert.assertEquals(coach,foundCoach);
    }

    @Test
    public void removeFighterFromCoachTest() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(coachRepository.findById(1)).thenReturn(coach);
        when(fighterRepository.findById(1)).thenReturn(fighter1);
        when(coachRepository.save(any(Coach.class))).thenReturn(coach);
        Coach foundCoach = coachService.removeFighter(1,1);
        //assert
        Assert.assertEquals(coach,foundCoach);
    }

    @Test
    public void getCoachIdFromNameTest() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        //act
        when(coachRepository.findByName(any(String.class))).thenReturn(coach);
        int id = coachService.getIdFromName(coach.getName());
        //assert
        Assert.assertEquals(coach.getId(),id);

    }

}
