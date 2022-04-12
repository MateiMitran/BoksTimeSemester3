package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document(collection = "requests")
public class Request {
    @Transient
    public static final String SEQUENCE_NAME = "request_sequence";
    @Id
    private int id;

    @NotNull
    private int fighterId;

    @NotNull
    private int coachId;

    public Request(int id,int fighterId, int coachId) {
        this.id = id;
        this.fighterId = fighterId;
        this.coachId = coachId;
    }
}
