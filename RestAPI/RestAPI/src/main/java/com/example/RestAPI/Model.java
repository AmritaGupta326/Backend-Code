package com.example.RestAPI;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;

    public void setName(String name){

        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setDescription(String description){

        this.description = description;
    }
    public String getDescription(){

        return description;
    }
}
