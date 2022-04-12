package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@Document(collection = "fighters")
public class Fighter {



    @Transient
    public static final String SEQUENCE_NAME = "fighter_sequence";

    @Id
    private int id;
    @NotNull
    private String name;
    private List<Fight> fights;
    @NotNull @Min(18) @Max(50)
    private int age;
    @NotNull
    private String height;
    @NotNull
    private String reach;
    @NotNull
    private int bouts;
    @NotNull
    private String record;
    private byte[] image;
    @NotNull
    private int[] punches;
    @NotNull
    private int[] rounds;
    @NotNull
    private int[] powerpunches;

    private boolean hasCoach = false;

    public Fighter(int id, String name, List<Fight> fights) {
        this.id = id;
        this.name = name;
        this.fights = fights;
    }
    public Fighter(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Fighter(int id, String name, int age, String height, String reach, int bouts, String record,int[] punches, int[] rounds, int[] powerpunches)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.reach = reach;
        this.bouts = bouts;
        this.record = record;
        this.punches = punches;
        this.rounds = rounds;
        this.powerpunches = powerpunches; //gitlab-runner-windows-amd64.exe
    }
    public Fighter() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public int getBouts() {
        return bouts;
    }

    public void setBouts(int bouts) {
        this.bouts = bouts;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int[] getPunches() {
        return punches;
    }

    public void setPunches(int[] punches) {
        this.punches = punches;
    }

    public int[] getRounds() {
        return rounds;
    }

    public void setRounds(int[] rounds) {
        this.rounds = rounds;
    }

    public int[] getPowerpunches() {
        return powerpunches;
    }

    public void setPowerpunches(int[] powerpunches) {
        this.powerpunches = powerpunches;
    }

    public boolean isHasCoach() {
        return hasCoach;
    }

    public void setHasCoach(boolean hasCoach) {
        this.hasCoach = hasCoach;
    }
}
