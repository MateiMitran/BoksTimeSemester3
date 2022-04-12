package com.example.demo.controllers;

import com.example.demo.model.Request;
import com.example.demo.repo.RequestRepository;
import com.example.demo.service.RequestService;
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
@SpringBootTest(classes = {RequestController.class}, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
@Import(RequestController.class)
public class RequestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @MockBean
    RequestService requestService;

    @MockBean
    RequestRepository requestRepository;

    @MockBean
    SequenceGeneratorService sequenceGeneratorService;

    Request request1 = new Request(1,1,1);
    Request request2 = new Request(2,2,2);
    Request request3 = new Request(3,3,3);

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void createRequestTest() throws Exception {
        //arrange
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(request1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/request/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                //assert
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void deleteRequestWhenExists() throws Exception {
        //arrange
        Mockito.when(requestService.deleteRequest(1)).thenReturn(true);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/request/{id}/delete",1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllRequests() throws Exception {
        //arrange
        List<Request> requests = new ArrayList<>(Arrays.asList(request1,request2,request3));
        Mockito.when(requestService.getRequests()).thenReturn(requests);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/requests")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getRequestById() throws Exception {
        //arrange
        Mockito.when(requestService.getRequest(1)).thenReturn(request1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/request/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());

    }
}
