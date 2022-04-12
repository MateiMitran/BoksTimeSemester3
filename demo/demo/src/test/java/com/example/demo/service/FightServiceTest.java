package com.example.demo.service;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import com.example.demo.repo.FightRepository;
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
@SpringBootTest(classes = {FightService.class})
@ActiveProfiles(value = "test")
public class FightServiceTest {

    @Mock
    FightRepository fightRepository;

    @InjectMocks
    FightServiceImpl fightService;


    //arrange
    Fight fight1 = new Fight(1,"Title1","embed1","Desc");
    Fight fight2 = new Fight(2,"Title2","embed2","Desc");
    Fight fight3 = new Fight(3,"Title3","embed3","Desc");

    @Test
    public void saveFightTest() {
        //act
        when(fightRepository.save(any(Fight.class))).thenReturn(fight1);
        Fight saved = fightService.saveFight(fight1);
        //assert
        Assert.assertEquals(fight1,saved);
    }

    @Test
    public void getFightsTest() {
        //arrange
        List<Fight> fights = new ArrayList<>(Arrays.asList(fight1,fight2,fight3));
        //act
        when(fightRepository.findAll()).thenReturn(fights);
        List<Fight> found = fightService.getFights();
        //assert
        Assert.assertEquals(fights,found);
    }

    @Test
    public void getFightByTitleTest() {
        //act
        when(fightRepository.findByTitle(any(String.class))).thenReturn(fight1);
        Fight found = fightService.getFight(fight1.getTitle());
        //assert
        Assert.assertEquals(fight1,found);
    }

    @Test
    public void getFightByIdTest() {
        //act
        when(fightRepository.findById(1)).thenReturn(fight1);
        Fight found = fightService.getFightById(fight1.getId());
        //assert
        Assert.assertEquals(fight1,found);
    }

    @Test
    public void getFightsByTitleTest() {
        //arrange
        List<Fight> fights = new ArrayList<>(Arrays.asList(fight1,fight2,fight3));
        //act
        when(fightRepository.findAll()).thenReturn(fights);
        List<Fight> found = fightService.getFightsByTitle("Title");
        //assert
        Assert.assertEquals(fights,found);
    }

    @Test
    public void getFirstFighterTest() {
        //arrange
        Fighter fighter = new Fighter(1,"Mike Tyson");
        fight1.setFighter1(fighter);
        //act
        when(fightRepository.findById(1)).thenReturn(fight1);
        Fighter found = fightService.getFirstFighter(fight1.getId());
        //assert
        Assert.assertEquals(fighter,found);

    }
    @Test
    public void getSecondFighterTest() {
        //arrange
        Fighter fighter = new Fighter(1,"Mike Tyson");
        fight1.setFighter2(fighter);
        //act
        when(fightRepository.findById(1)).thenReturn(fight1);
        Fighter found = fightService.getSecondFighter(fight1.getId());
        //assert
        Assert.assertEquals(fighter,found);

    }

}
