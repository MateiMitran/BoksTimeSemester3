package com.example.demo.repositories;
import com.example.demo.model.Fighter;
import com.example.demo.repo.FighterRepository;
import com.example.demo.service.FighterService;
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
public class FighterServiceTest {

    @Mock
    FighterRepository fighterRepository;

    @MockBean
    FighterService fighterService;

    @MockBean
    UserService userService;

    @Test
    public void isNotEmpty() {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        //act
        when(fighterRepository.save(any(Fighter.class))).thenReturn(fighter1);
        //assert
        Assert.assertEquals(fighter1,fighterRepository.save(fighter1));
    }
}
