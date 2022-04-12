package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Document(collection = "fights")
public class Fight {

    @Transient
    public static final String SEQUENCE_NAME = "fights_sequence";

    @Id
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String embedId;
    private byte[] image;
    private int views;
    @NotNull
    private String description;
    private Fighter fighter1;
    private Fighter fighter2;
    public Fight(int id,String title,String embedId, String description)
    {
        this.id = id;
        this.title = title;
        this.views=0;
        this.embedId = embedId;
        this.description = description;
    }
    public Fight() {

    }
    public int getId()
    {
        return this.id;
    }
    public void setId(int id) { this.id = id;}
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public byte[] getImage() {return this.image;}
    public void setImage(byte[] image) {this.image = image;}
    public String getEmbedId() {
        return embedId;
    }
    public void setEmbedId(String embedId) {
        this.embedId = embedId;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public String getDescription() {return this.description;}
    public void setDescription(String description) {this.description=description;}
    public Fighter getFighter1() {return this.fighter1;}
    public void setFighter1(Fighter fighter1) {this.fighter1 = fighter1;}
    public Fighter getFighter2() {return this.fighter2;}
    public void setFighter2(Fighter fighter2) {this.fighter2 = fighter2;}

    @Override
    public boolean equals(Object o)
    {
        if (this==o)
            return true;
        if (!(o instanceof Fight))
            return false;
        Fight u = (Fight) o;
        return Objects.equals(this.id,u.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title);
    }


    @Override
    public String toString() {
        return "Fight{" +
                "id=" + id +
                ", title='" + title;
    }
}