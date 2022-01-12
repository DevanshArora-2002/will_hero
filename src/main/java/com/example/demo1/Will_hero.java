package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class Will_hero extends game_objects {
    private ImageView throw_knife;
    private ImageView addn_weapons_knife;
    private Weapon curr_weapon;
    private Weapon addn_weapon;
    private double velocityY=0;
    private ImageView hero;
    private helmet hel;
    private boolean is_collided;
    private User usr;
    private Text weapon;
    private ImageView w1;
    private ImageView w2;
    public void save_exit(){
        w1=null;
        w2=null;
        weapon=null;
        addn_weapons_knife=null;
        curr_weapon=null;
        addn_weapon=null;
        throw_knife=null;
    }
    public void set_addn_weapon_view(ImageView v){
        this.addn_weapons_knife=v;
        Play.ret_plat().set_throw_knife_2(v);
    }
    public void set_vely(double y){
        velocityY=y;
    }
    public void weapons(Text t1, ImageView w1,ImageView w2){
        this.weapon=t1;
        this.w1=w1;
        this.w2=w2;
    }
    public double ret_vely(){
        return velocityY;
    }
    Will_hero(ImageView view){
        this.hero=view;
        this.set_co(hero.getLayoutX(),hero.getLayoutY());
        hel=new helmet(this);
    }
    public helmet ret_helmet(){
        return hel;
    }
    public void gain_coin(int i){
        usr.add_coin(i);
    }
    public void res_orc(platform plat){
        usr.resurrect_orc(plat);
    }
    public void res_ab(platform plat){
        usr.resurrect_abyss(plat);
    }
    public void collided(){
        is_collided=true;
    }
    public void set_usr(User usr){
        this.usr=usr;
    }
    public User ret_usr(){
        return usr;
    }
    ImageView ret_view(){
        return hero;
    }
    public void set_curr_weapon(Weapon w1){
        ArrayList<Weapon> list=hel.ret_list();
        this.curr_weapon=w1;
        if(w1 instanceof shuriken){
            weapon.setText("Current weapon: Shuriken");
            //this.w1.setImage(img1);
            Image img2=new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                    "src/main/resources/com/example/" +
                    "demo1/game_images/shuriken.png");
            this.throw_knife.setImage(img2);
            for(int i=0;i<list.size();i++){
                if(list.get(i) instanceof shuriken){
                    this.addn_weapon=list.get(i);
                    this.addn_weapons_knife.setImage(img2);
                    break;
                }
                else {
                    this.addn_weapons_knife.setImage(null);
                }
            }
        }
        else {
            this.weapon.setText("Current weapon: Throwing Knife");
            Image img2=new Image("file:/Users/devansh/IdeaProjects/demo1" +
                    "/src/main/resources/com/example/demo1" +
                    "/game_images/throw_knife.png");
            this.throw_knife.setImage(img2);
            for(int i=0;i<list.size();i++){
                if(list.get(i) instanceof knife){
                    this.addn_weapon=list.get(i);
                    this.addn_weapons_knife.setImage(img2);
                    break;
                }
                else {
                    this.addn_weapons_knife.setImage(null);
                }
            }
        }
    }
    public void add_throw_knife(ImageView v){
        this.throw_knife=v;
    }
    public void attack_boss(Boss_orc orc){
        double minx=orc.ret_view().getBoundsInParent().getMinX();
        throw_knife.setLayoutX(minx);
        if(addn_weapon!=null){
            addn_weapons_knife.setLayoutX(minx);
        }
    }
    public void attack(){
        if(curr_weapon!=null){
            ArrayList<game_objects> orcs=usr.ret_game().ret_orcs();
            if(addn_weapon!=null){
                AnimationTimer timer=new AnimationTimer() {
                    int i=0;
                    double x_val=hero.getLayoutX()+50;
                    double y_val=hero.getLayoutY();
                    double y=hero.getLayoutY()+40;
                    @Override
                    public void handle(long l) {
                        if(i<10){
                            throw_knife.relocate(x_val+10,y_val);
                            addn_weapons_knife.relocate(x_val+10,y);
                            x_val+=10;
                            Iterator iter=orcs.iterator();
                            while(iter.hasNext()){
                                Orc orc=(Orc) iter.next();
                                if(throw_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent()) ||
                                addn_weapons_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())){
                                    orc.hit();
                                }
                            }
                            /*Boss_orc orc=(Boss_orc)orcs.get(orcs.size()-1);
                            if(throw_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())||
                            addn_weapons_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())){
                                orc.hit();
                            }
                            System.out.println("Hit");*/
                            i++;
                        }
                        else {
                            throw_knife.relocate(hero.getLayoutX()+30,hero.getLayoutY());
                            addn_weapons_knife.relocate(hero.getLayoutX()+30,hero.getLayoutY()+40);
                            this.stop();
                        }
                    }
                };
                timer.start();
            }
            else {
                AnimationTimer timer=new AnimationTimer() {
                    int i=0;
                    double x_val=hero.getLayoutX()+50;
                    double y_val=hero.getLayoutY();
                    @Override
                    public void handle(long l) {
                        if(i<10){
                            throw_knife.relocate(x_val+10,y_val);
                            x_val+=10;
                            Iterator iter=orcs.iterator();
                            while(iter.hasNext()){
                                Orc orc=(Orc) iter.next();
                                if(throw_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())){
                                    orc.hit();
                                }
                            }
                            /*Boss_orc orc=(Boss_orc)orcs.get(orcs.size()-1);
                            if(throw_knife.getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())){
                                orc.hit();
                            }
                            System.out.println("Hit");*/
                            i++;
                        }
                        else {
                            throw_knife.relocate(hero.getLayoutX()+30,hero.getLayoutY());
                            this.stop();
                        }
                    }
                };
                timer.start();
            }
        }
    }
    public Weapon ret_weapon(){
        return curr_weapon;
    }
    @Override
    public void collision(Will_hero hero) {
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                is_collided=false;
            }
        };
        timer.start();
    }
}
