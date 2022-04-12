package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;


@Document(collection = "users")
public class User implements Serializable{



    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id @Size(min=2)
    private Long id;
    private String firstName;
    private String lastName;
    private String age;
    private String address;
    private String password;
    @Email
    private String email;
    @NotNull
    private String username;
    private Map<String, String> tokens;

    @NotNull
    private String roleName;

    @DBRef
    private Set<Role> roles = new HashSet<>();


    public String getPassword() {
        return this.password;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) { this.username=username;}
    public String getEmail() {
        return this.email;
    }
    public Map<String,String> getTokens() { return this.tokens;}
    public void setTokens(Map<String,String> tokens) {this.tokens = tokens;}
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getRoleName() {
        return this.roleName;
    }
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User(Long id, String firstName, String lastName, String age, String address, String password, String email)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.password = password;
        this.email = email;
    }
    public User(Long id,String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(Long id,String username, String email, String password, String roleName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
    }
    public User() {

    }

    public String getAge()
    {
        return this.age;
    }
    public void setAge(String age)
    {
        this.age = age;
    }
    public String getAddress()
    {
        return this.address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getFirstName() { return this.firstName;}
    public void setFirstName(String firstName) { this.firstName=firstName;}
    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) { this.lastName = lastName;}
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) {this.email = email;}
    public String getName() { return this.firstName + ' ' + this.lastName; }
    @Override
    public boolean equals(Object o)
    {
        if (this==o)
            return true;
        if (!(o instanceof User))
            return false;
        User u = (User)o;
        return Objects.equals(this.id,u.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName,this.password);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

       private User user = new User();

        public Builder id(Long id){
            user.setId(id);
            return this;
        }

        public Builder username(String username){
            user.setUsername(username);
            return this;
        }

        public Builder email(String email){
            user.setEmail(email);
            return this;
        }

        public Builder password(String password){
           user.setPassword(password);
            return this;
        }


        public User build(){
            return this.user;
        }
    }
}
