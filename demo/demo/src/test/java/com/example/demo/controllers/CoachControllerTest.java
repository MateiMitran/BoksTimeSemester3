package com.example.demo.controllers;


import com.example.demo.model.Coach;
import com.example.demo.model.Fighter;
import com.example.demo.repo.CoachRepository;
import com.example.demo.service.CoachService;
import com.example.demo.service.SequenceGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {CoachController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(CoachController.class)
public class CoachControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    CoachService coachService;

    @MockBean
    CoachRepository coachRepository;

    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    Coach coach1 = new Coach(2,"Coach");
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addCoachTest() throws Exception {
        //arrange
        Coach coach = new Coach(1,"Coach");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(coach);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/coach/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                //assert
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addFighterToCoachTest() throws Exception {
        //arrange
        coach1.addFighter(new Fighter(1,"Test"));
        Mockito.when(coachService.addFighter(2,1)).thenReturn(coach1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/coach/{coachId}/add-fighter/{fighterId}",2,1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void removeFighterFromCoachTest() throws Exception {
        //arrange
        coach1.addFighter(new Fighter(1,"Test"));
        Mockito.when(coachService.removeFighter(2,1)).thenReturn(coach1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/coach/{coachId}/remove-fighter/{fighterId}",2,1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getFightersFromCoach() throws Exception {
        //arrange
        Fighter fighter1 = new Fighter(1,"Test");
        Fighter fighter2 = new Fighter(2,"Test1");
        List<Fighter> fighters = new ArrayList<>(Arrays.asList(fighter1,fighter2));
        Mockito.when(coachService.getFightersFromCoach(2)).thenReturn(fighters);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/coach/{id}/fighters",2)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                        .andExpect(status().isOk());
    }

    @Test
    @WithMockUser()
    public void getCoachIdByNameWhenExists() throws Exception {
        //arrange
        Mockito.when(coachService.getIdFromName("Coach")).thenReturn(2);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/coach/{name}","Coach")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }
}
