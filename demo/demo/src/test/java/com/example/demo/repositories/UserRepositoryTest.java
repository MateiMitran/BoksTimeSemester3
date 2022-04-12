package com.example.demo.repositories;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
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
public class UserRepositoryTest {

    @Mock
    UserRepository userRepository;

    @MockBean
    UserService userService;

    @Test
    public void isNotEmpty() {
        //arrange
        User user = new User(1L,"Username","Email","test1234");
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        //assert
        Assert.assertEquals(user,userRepository.save(user));
    }
}
