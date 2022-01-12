package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.net.URI;
import java.util.Objects;
public  class Orc extends game_objects implements  collision{
    protected double velocityY=0;
    private static double velocityX=5;
    protected ImageView view;
    protected int timex=11;
    Orc(ImageView view){
        this.view=view;
        this.set_co(view.getLayoutX(),view.getLayoutY());
    }
    public void set_velocity(double velx,double vely){
        velocityX=velx;
        velocityY=vely;
    }
    public void set_vely(double y){
        velocityY=y;
    }
    public double ret_vely(){
        return velocityY;
    }
    ImageView ret_view(){
        return view;
    }
    @Override
    public void save_exit(){
        view=null;
    }
    @Override
    public boolean is_collide(Will_hero hero){
        if(hero.ret_view()==null){
            return false;
        }
        if(hero.ret_view().getBoundsInParent().intersects(this.view.getBoundsInParent())){
            return true;
        }
        return false;
    }
    public void hit(){
        view.relocate(view.getLayoutX(),1000);
    }
    public void change_co(double x1,double y1){
        this.set_co(x1,y1);
    }
    public boolean is_alive(){
        double orc_ymin=view.getBoundsInParent().getMinY();
        double orc_ymax=view.getBoundsInParent().getMaxY();
        if(orc_ymax<600){
            return true;
        }
        return false;
    }
    @Override
    public void collision(Will_hero hero) {
        AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(is_collide(hero)){
                    //System.out.println("Collision");
                    double orc_xmin=view.getBoundsInParent().getMinX();
                    double orc_xmax=view.getBoundsInParent().getMaxX();
                    double orc_ymin=view.getBoundsInParent().getMinY();
                    double orc_ymax=view.getBoundsInParent().getMaxY();
                    double h_xmin=hero.ret_view().getBoundsInParent().getMinX();
                    double h_xmax=hero.ret_view().getBoundsInParent().getMaxX();
                    double h_ymin=hero.ret_view().getBoundsInParent().getMinY();
                    double h_ymax=hero.ret_view().getBoundsInParent().getMaxY();
                    //System.out.println(orc_xmax+"Orc x max");
                    //System.out.println(orc_xmin+"Orc x min");
                    //System.out.println(orc_ymin+" Orc y min");
                    //System.out.println(orc_ymax+"Orc y max");
                    //System.out.println(h_xmax+"Hero x max");
                    //System.out.println(h_xmin+"Hero x min");
                    //System.out.println(h_ymin+"Hero y min");
                    //System.out.println(h_ymax+"hero y max");
                    if((int)h_ymin<=(int)orc_ymax && (int)h_ymax>(int)orc_ymax && velocityY>0 ){
                        //System.out.println("Ended");
                        //this.stop();
                        //hero.ret_view().relocate(view.getLayoutX()-100,view.getLayoutY());
                        hero.ret_usr().stop();
                        hero.res_orc(Play.ret_plat());
                        this.stop();
                    }
                    else {
                        timex=0;
                        System.out.println("diff_coll"+timex);
                        Weapon w=hero.ret_weapon();
                        //hero.ret_usr().ret_game().ret_platform().collided();
                    }
                }
                if(timex<10){
                    view.relocate(view.getLayoutX()+8.5,view.getLayoutY());
                    change_co(view.getLayoutX(),ret_co().ret_y());
                    timex+=1;
                }
                if(is_alive()==false){
                    System.out.println("Killed");
                    hero.gain_coin(1);
                    this.stop();
                }
            }
        };
        timer.start();
    }
}
