package com.example.demo1;

import java.io.Serializable;

public abstract class game_objects implements Serializable {
    private co_ordinates co;
    public abstract void save_exit();
    public abstract void collision(Will_hero hero);
    public co_ordinates ret_co(){
        return co;
    }
    public void set_co(double x,double y){
        co=new co_ordinates(x,y);
    }
    public co_ordinates ret_init_co(){
        return co;
    }
}
