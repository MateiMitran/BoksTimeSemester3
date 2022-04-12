package com.example.demo.controllers;

import com.example.demo.model.Fight;
import com.example.demo.model.Fighter;
import com.example.demo.repo.FightRepository;
import com.example.demo.service.FightService;
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
@SpringBootTest(classes = {FightController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(FightController.class)
public class FightControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    FightService fightService;

    @MockBean
    FightRepository fightRepository;

    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    Fight fight1 = new Fight(1,"Title1","embed1","Desc");
    Fight fight2 = new Fight(2,"Title2","embed2","Desc");
    Fight fight3 = new Fight(3,"Title3","embed3","Desc");

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllFights() throws Exception {
        //arrange
        List<Fight> fights = new ArrayList<>(Arrays.asList(fight1,fight2,fight3));
        Mockito.when(fightService.getFights()).thenReturn(fights);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fights")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getFightById() throws Exception {
        //arrange
        Mockito.when(fightService.getFightById(1)).thenReturn(fight1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fight/id/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getFightByTitle() throws Exception {
        //arrange
        Mockito.when(fightService.getFight("Title1")).thenReturn(fight1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fight/{title}","Title1")
                        .contentType(MediaType.APPLICATION_JSON))
                    //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createFightTest() throws Exception {
        //arrange
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(fight1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fight/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                //assert
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void addFightersToFightTest() throws Exception {
        //arrange
        Fighter fighter1 = new Fighter(1,"Testing Test");
        Fighter fighter2 = new Fighter(2,"Test Test");
        fight1.setFighter1(fighter1);
        fight1.setFighter2(fighter2);
        Mockito.when(fightService.addFighters(1,1,2)).thenReturn(fight1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fight/{fightId}/add-fighters/{fighter1Id}/{fighter2Id}",1,1,2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());
    }

}
