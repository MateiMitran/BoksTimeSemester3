package com.example.demo.repositories;

import com.example.demo.model.Fight;
import com.example.demo.repo.FightRepository;
import com.example.demo.service.FightService;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataMongoTest
@ActiveProfiles(value = "test")
public class FightRepositoryTest {

    @Mock
    FightRepository fightRepository;

    @MockBean
    FightService fightService;

    @MockBean
    UserService userService;

    @Test
    public void isNotEmpty() throws Exception {
        //arrange
        Fight fight = new Fight(1,"Fight","embedId","Desc");
        //act
        when(fightRepository.save(any(Fight.class))).thenReturn(fight);
        //assert
        Assert.assertEquals(fight,fightRepository.save(fight));
    }
}
