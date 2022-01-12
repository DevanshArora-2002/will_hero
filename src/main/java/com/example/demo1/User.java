package com.example.demo1;

import javafx.scene.control.Button;

import java.io.IOException;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private main_game game;
    private Will_hero hero;
    private int coins=0;
    public Will_hero ret_hero(){
        return hero;
    }
    public main_game ret_game(){
        return game;
    }
    public int ret_coins(){
        return coins;
    }
    public void stop(){
        game.stop(Play.ret_plat());
    }
    public void save(){
        hero=null;
    }
    public void add_coin(int i){
        coins+=i;
    }
    User(main_game game){
        this.game=game;
    }
    public void set_name(String name){
        this.name=name;
    }
    public String ret_name(){
        return this.name;
    }
    public void set_hero(Will_hero hero){
        this.hero=hero;
        hero.set_usr(this);
    }
    public void play(platform plat){
        game.play(plat);
    }
    public void pause(){
        game.pause();
    }
    public void resurrect_orc(platform plat) {
        game.resurrect_orc(plat);
    }
    public void resurrect_abyss(platform plat){
        game.resurrect_abyss(plat);
    }
    @Override
    public String toString(){
        return name;
    }
}
