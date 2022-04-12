package com.example.demo.controllers;

import com.example.demo.model.Fighter;
import com.example.demo.repo.FighterRepository;
import com.example.demo.service.FighterService;
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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@SpringBootTest(classes = {FighterController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(FighterController.class)
public class FighterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    FighterService fighterService;

    @MockBean
    FighterRepository fighterRepository;

    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    Fighter fighter1 = new Fighter(1,"Testing Test");
    Fighter fighter2 = new Fighter(2,"Test Test");
    Fighter fighter3 = new Fighter(3,"Te Test");

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllFighters() throws Exception {
        //arrange
        List<Fighter> fighters = new ArrayList<>(Arrays.asList(fighter1,fighter2,fighter3));
        Mockito.when(fighterService.getFighters()).thenReturn(fighters);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fighters")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createFighter() throws Exception {
        //arrange
        Fighter fighter = new Fighter(4,"Mitran Matei");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(fighter);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fighter/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                //assert
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void updateFighterWhenExists() throws Exception {
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/fighter/{id}/update?age=25&height=1.76m&reach=1.05m&bouts=36&record=30-5-1",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void getFighterByIdWhenExists() throws Exception {
        //arrange
        Mockito.when(fighterService.getFighterById(1)).thenReturn(fighter1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fighter/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void checkCoachWhenExists() throws Exception {
        //arrange
        Mockito.when(fighterService.getFighterById(1)).thenReturn(fighter1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/fighter/{id}/has-coach",1)
                        .contentType(MediaType.APPLICATION_JSON))
                    //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void getFighterByNameWhenExists() throws Exception {
        //arrange
        fighter1.setHasCoach(true);
        Mockito.when(fighterService.getFighterByName("Testing Test")).thenReturn(fighter1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/get-fighter/{name}","Testing Test")
                        .contentType(MediaType.APPLICATION_JSON))
                    //assert
                .andExpect(status().isOk());
    }


}
