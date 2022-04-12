package com.example.demo.repositories;

import com.example.demo.model.Request;
import com.example.demo.repo.RequestRepository;
import com.example.demo.service.RequestService;
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
public class RequestRepositoryTest {

    @Mock
    RequestRepository requestRepository;

    @MockBean
    RequestService requestService;

    @MockBean
    UserService userService;

    @Test
    public void isNotEmpty() {
        //arrange
        Request request1 = new Request(1,1,1);
        //act
        when(requestRepository.save(any(Request.class))).thenReturn(request1);
        //assert
        Assert.assertEquals(request1,requestRepository.save(request1));
    }
}
