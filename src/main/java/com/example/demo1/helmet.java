package com.example.demo1;

import java.util.ArrayList;

public class helmet {
    ArrayList<Weapon> weapon_list=new ArrayList<>();
    Will_hero hero;
    helmet(Will_hero h){
        this.hero=h;
    }
    public void add_weapon(Weapon w1){
        hero.set_curr_weapon(w1);
        weapon_list.add(w1);
    }
    public ArrayList<Weapon> ret_list(){
        return weapon_list;
    }
    public Will_hero ret_hero(){
        return hero;
    }
}
