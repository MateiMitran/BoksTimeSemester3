package com.example.demo.service;


import com.example.demo.model.Role;
import com.example.demo.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    User updateUser(String firstName, String lastName, String email, String age, String password);
    User getById(long id);
    List<User> getUsers();
    boolean deleteUser(long id);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

