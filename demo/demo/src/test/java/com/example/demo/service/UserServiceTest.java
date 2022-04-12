package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepository;
import com.example.demo.repo.UserRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = {UserService.class})
@ActiveProfiles(value = "test")
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void saveUserTest() {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234");
        //act
        when(userRepository.save(any(User.class))).thenReturn(user);
        User created = userRepository.save(user);
        //assert
        Assert.assertEquals(created.getUsername(),user.getUsername());

    }

    @Test
    public void deleteUserTest() {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234");
        //act
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        Optional optional = userRepository.findById(user.getId());
        //assert
        Assert.assertEquals(Optional.empty(),optional);
    }

    @Test
    public void saveRoleTest() {
        //arrange
        Role role = new Role("1","ROLE_TEST");
        //act
        when(roleRepository.save(any(Role.class))).thenReturn(role);
        Role roleFound = roleRepository.save(role);
        //assert
        Assert.assertEquals(role.getName(),roleFound.getName());
    }

    @Test
    public void addRoleToUserTest() {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234");
        //act
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);
        userService.addRoleToUser("Username","ROLE_ADMIN");
        //assert
        Assert.assertEquals(user.getRoleName(),"ROLE_ADMIN");
    }

    @Test
    public void getUserByUsernameTest() {
        //arrange
        User user = new User(1L,"Username","email@email.com","test1234");
        //act
        when(userRepository.findByUsername(any(String.class))).thenReturn(user);
        User foundUser = userService.getUser(user.getUsername());
        //assert
        Assert.assertEquals(user,foundUser);
    }

    @Test
    public void getUsersTest() {
        //arrange
        User user1 = new User(1L,"Username","email@email.com","test1234");
        User user2 = new User(2L,"Username1","email1@email.com","test1234");
        user1.setRoleName("ROLE_ADMIN");
        user2.setRoleName("ROLE_ADMIN");
        List<User> users = new ArrayList<>(Arrays.asList(user1,user2));
        //act
        when(userRepository.findAll()).thenReturn(users);
        List<User> usersFound = userService.getUsers();
        //assert
        Assert.assertEquals(users.size(),usersFound.size());
    }
}
