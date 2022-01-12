package com.example.demo1;

import javafx.fxml.FXML;

public abstract class Weapon{
    private helmet hel;
    Weapon(helmet hel){
        this.hel=hel;
    }
    abstract public void attack();
    public helmet ret_helmet(){
        return hel;
    }
}
