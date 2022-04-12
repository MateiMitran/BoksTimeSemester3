package com.example.demo.repositories;

import com.example.demo.model.Coach;
import com.example.demo.repo.CoachRepository;
import com.example.demo.service.CoachService;
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
public class CoachRepositoryTest {

    @Mock
    CoachRepository coachRepository;

    @MockBean
    CoachService coachService;

    @MockBean
    UserService userService;

    @Test
    public void isNotEmpty() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        //act
        when(coachRepository.save(any(Coach.class))).thenReturn(coach);
        //assert
        Assert.assertEquals(coach,coachRepository.save(coach));
    }
}
