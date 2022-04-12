package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    @NotNull
    private String name;

    public Role(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public Role() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
