package com.example.demo.service;
import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.model.Request;
import com.example.demo.repo.CoachRepository;
import com.example.demo.repo.FighterRepository;
import com.example.demo.repo.RequestRepository;
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
@SpringBootTest(classes = {RequestService.class})
@ActiveProfiles(value = "test")
public class RequestsServiceTest {

    @Mock
    RequestRepository requestRepository;

    @Mock
    CoachRepository coachRepository;

    @Mock
    FighterRepository fighterRepository;

    @InjectMocks
    RequestServiceImpl requestService;

    Request request1 = new Request(1,1,1);
    Request request2 = new Request(2,2,2);
    Request request3 = new Request(3,3,3);

    @Test
    public void getRequestsTest() {
        //arrange
        List<Request> requests = new ArrayList<>(Arrays.asList(request2,request3));
        //act
        when(requestRepository.findAll()).thenReturn(requests);
        List<Request> found = requestService.getRequests();
        //assert
        Assert.assertEquals(requests,found);
    }

    @Test
    public void getRequestsByFighterTest() {
        //arrange
        List<Request> requests = new ArrayList<>(Arrays.asList(request1));
        when(requestRepository.findAll()).thenReturn(requests);
        //act
        List<Request> found = requestService.getRequestsByFighter(1);
        //assert
        Assert.assertEquals(requests,found);
    }

    @Test
    public void getRequestsByCoachTest() {
        //arrange
        List<Request> requests = new ArrayList<>(Arrays.asList(request1));
        //act
        when(requestRepository.findAll()).thenReturn(requests);
        List<Request> found = requestService.getRequestsByCoach(1);
        //assert
        Assert.assertEquals(requests,found);
    }

    @Test
    public void saveRequestTest() {
        //act
        when(requestRepository.save(any(Request.class))).thenReturn(request1);
        Request saved = requestRepository.save(request1);
        //assert
        Assert.assertEquals(request1,saved);
    }

    @Test
    public void deleteRequestTest() {
        //arrange
        Coach coach = new Coach(1,"Coach");
        Fighter fighter = new Fighter(1,"Mike Tyson");
        //act
        when(requestRepository.findById(1)).thenReturn(request1);
        when(coachRepository.findById(1)).thenReturn(coach);
        when(fighterRepository.findById(1)).thenReturn(fighter);
        boolean deleted = requestService.deleteRequest(request1.getId());
        //assert
        Assert.assertEquals(deleted,true);
    }

    @Test
    public void getRequestByIdTest() {
        //act
        when(requestRepository.findById(1)).thenReturn(request1);
        Request found = requestService.getRequest(request1.getId());
        //assert
        Assert.assertEquals(request1,found);
    }

}
