package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Document(collection = "coaches")
public class Coach {


    @Transient
    public static final String SEQUENCE_NAME = "coach_sequence";


    @Id
    private long id;
    @NotNull
    private String name;

    private List<Fighter> fighters = new ArrayList<>();

    public Coach(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Coach(long id, String name, List<Fighter> fighters) {
        this.id = id;
        this.name = name;
        this.fighters = fighters;
    }
    public Coach() {

    }

    public void addFighter(Fighter fighter) {
        fighters.add(fighter);
    }

    public void removeFighter(Fighter fighter) {
        fighters.remove(fighter);
    }
}
