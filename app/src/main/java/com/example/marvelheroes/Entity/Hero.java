package com.example.marvelheroes.Entity;

import java.util.List;

public class Hero {

    private int id;
    private String name;
    private String description;
    private HeroImage thumbnail;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setThumbnail(HeroImage thumbnail) {
        this.thumbnail = thumbnail;
    }

    public HeroImage getThumbnail() {
        return thumbnail;
    }
}
