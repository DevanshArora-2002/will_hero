package com.example.demo1;

import java.io.Serializable;

public class co_ordinates implements Serializable {
    private double x;
    private double y;
    co_ordinates(double x,double y){
        this.x=x;
        this.y=y;
    }
    public double ret_x(){
        return x;
    }
    public double ret_y(){
        return  y;
    }
}
