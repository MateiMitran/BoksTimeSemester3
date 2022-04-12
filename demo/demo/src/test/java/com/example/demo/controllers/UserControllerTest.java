package com.example.demo.controllers;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.SequenceGeneratorService;
import com.example.demo.service.UserService;
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
@SpringBootTest(classes = {UserController.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"security.basic.enabled=false","spring.main.lazy-initialization=true"})
@ActiveProfiles(value = "test")
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService userService;

    @MockBean
            UserRepository userRepository;
    @MockBean
            SequenceGeneratorService sequenceGeneratorService;

    User user1 = new User(1L,"Matei","Mitran","19","Lombokpad","test1234","matei.mitran10@gmail.com");
    User user2 = new User(2L,"Test","Test","21","Lombokpad","test1234","test@gmail.com");
    User user3 = new User(3L,"John","Doe","23","Boschdijk 3.42","test1234","john.doe@gmail.com");


    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void getAllUsers_success() throws Exception {
        //arrange
        List<User> users = new ArrayList<>(Arrays.asList(user1,user2,user3));
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void getByIdWhenExists() throws Exception {
        //arrange
        user1.setUsername("Testing");
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/user/{id}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void getByUsernameWhenExists() throws Exception {
        //arrange
        user1.setUsername("Testing");
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/user/{username}","Testing")
                .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void updateUserWhenExists() throws Exception {
        //arrange
        user1.setUsername("Testing");
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/update?firstName=Test1&lastName=Test2&email=matei.mitran10@gmail.com&age=36&password=test12345")
                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(user("admin").roles("ADMIN"))
                                .with(csrf()))
                //assert
                 .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void deleteUserWhenExists() throws Exception {
        //arrange
        Mockito.when(userService.getById(1L)).thenReturn(user1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/user/{userId}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                        .with(user("admin").roles("ADMIN"))
                        .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void registerUser() throws Exception {
        //arrange
        User user = new User(4L,"Matei","Matei@mail.com","test1234","ROLE_ADMIN");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .with(csrf()))
                //assert
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void saveRoleTest() throws Exception {
        //arrange
        Role role = new Role("1","ROLE_BOSS");
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(role);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/role/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .with(csrf()))
                //assert
                .andExpect(status().isCreated());

    }

    @Test
    @WithMockUser(username="admin",roles= {"ADMIN"})
    public void getRoleIfExists() throws Exception {
        //arrange
        user1.setRoleName("ROLE_ADMIN");
        user1.setUsername("Testing");
        Mockito.when(userService.getUser("Testing")).thenReturn(user1);
        //act
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/role/{username}","Testing")
                        .contentType(MediaType.APPLICATION_JSON))
                //assert
                .andExpect(status().isOk());
    }



}
