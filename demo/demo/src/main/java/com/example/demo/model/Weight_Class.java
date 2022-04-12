package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "weight_classes")
public class Weight_Class {
    @Id
    private int id;
    @NotNull
    private String name;
    private List<Fighter> fighters;
    public Weight_Class (int id, String name)
    {
        this.id = id;
        this.name = name;
        fighters = new ArrayList<>();
    }
    public int getID()
    {
        return this.id;
    }
    public void setID(int id)
    {
        this.id = id;
    }
    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public List<Fighter> getFighters() {return this.fighters;}
    public void setFighters(List<Fighter> fighters) {this.fighters = fighters;}
   // public int getNumberOfFighters() {return this.fighters.size();}
}
