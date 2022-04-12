package com.example.demo.service;

import com.example.demo.model.Fighter;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {FighterService.class})
@ActiveProfiles(value = "test")
public class FighterServiceTest {

    @Mock
    FighterRepository fighterRepository;

    @InjectMocks
    FighterServiceImpl fighterService;


    @Test
    public void addFighterTest() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(fighterRepository.save(any(Fighter.class))).thenReturn(fighter1);
        Fighter saved = fighterService.saveFighter(fighter1);
        //assert
        Assert.assertEquals(fighter1,saved);
    }

    @Test
    public void getByIdTest() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(fighterRepository.findById(1)).thenReturn(fighter1);
        Fighter found = fighterService.getFighterById(fighter1.getId());
        //assert
        Assert.assertEquals(fighter1,found);
    }

    @Test
    public void getByNameTest() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(fighterRepository.findByName(any(String.class))).thenReturn(fighter1);
        Fighter found = fighterService.getFighterByName(fighter1.getName());
        //assert
        Assert.assertEquals(fighter1,found);
    }

    @Test
    public void getFightersTest() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        Fighter fighter2 = new Fighter(2,"Test Test");
        Fighter fighter3 = new Fighter(3,"Te Test");
        List<Fighter> fighters = new ArrayList<>(Arrays.asList(fighter1,fighter2,fighter3));
        //act
        when(fighterRepository.findAll()).thenReturn(fighters);
        List<Fighter> found = fighterService.getFighters();
        //assert
        Assert.assertEquals(fighters,found);
    }

    @Test
    public void updateFightertest() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(fighterRepository.findById(1)).thenReturn(fighter1);
        when(fighterRepository.save(any(Fighter.class))).thenReturn(fighter1);
        Fighter updated = fighterService.updateFighter(1,25,"1.80m","1.20m",24,"22-2-0");
        //assert
        Assert.assertEquals(fighter1,updated);
    }
}
