package com.example.demo.controllers;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.SequenceGeneratorService;
import com.example.demo.service.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class UserController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    private final SequenceGeneratorService sequenceGeneratorService;

    public UserController(UserService userService, SequenceGeneratorService sequenceGeneratorService) {
        this.userService = userService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        LOG.info("Getting all users.");
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username) {
        LOG.info("Getting user with username: {}, username");
        return userService.getUser(username);
    }

    @PostMapping("/update")
    public User updateUser(@PathParam("firstName")String firstName, @PathParam("lastName") String lastName, @PathParam("email") String email, @PathParam("age") String age
                           , @PathParam("password")String password) {
        LOG.info("Updating user with name: {}", firstName + " " + lastName);
        return userService.updateUser(firstName,lastName,email,age,password);
    }

    @DeleteMapping("/user/{userId}")
    public boolean deleteUser(@PathVariable long userId) {
        LOG.info("Deleting user with ID: {}", userId);
        return userService.deleteUser(userId);
    }


    @PostMapping("/register")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        LOG.info("Saving user.");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        Set<Role> roles = new HashSet<>();
        String roleName = user.getRoleName();
        LOG.info("Role name is " + roleName);
        Role newRole;
        if (roleName.equals("ROLE_USER")) {
            LOG.info("Got to user");
            newRole = new Role("1","ROLE_USER");
        } else if (roleName.equals("ROLE_BOXER")) {
            LOG.info("Got to boxer");
            newRole = new Role("2","ROLE_BOXER");
        } else if (roleName.equals("ROLE_ADMIN")) {
            LOG.info("Got to admin");
            newRole = new Role("3","ROLE_ADMIN");
        } else {
            LOG.info("Got to nothing");
            newRole = null;
        }
        LOG.info("New role added is " + newRole);
        roles.add(newRole);
        user.setRoles(roles);
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        LOG.info("Saving role");
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/role/{username}")
    public String getRole(@PathVariable String username) {
        LOG.info("username : {}", username);
        User temp = userService.getUser(username);
        return temp.getRoleName();

    }
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.refreshToken(request,response);
    }

}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}