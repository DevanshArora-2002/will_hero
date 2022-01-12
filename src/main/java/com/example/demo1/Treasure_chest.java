package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Treasure_chest extends game_objects implements collision{
    private static int ival=0;
    private boolean openned=false;
    @FXML
    private ImageView view;
    @Override
    public void save_exit(){
        view=null;
    }
    @Override
    public void collision(Will_hero hero) {
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(is_collide(hero) && !openned){
                    ival++;
                    int i=random();
                    if(i==1){
                        hero.gain_coin(10);
                        Image img=new Image("file:/Users/devansh/IdeaProjects/demo1" +
                                "/src/main/resources/com/example/demo1" +
                                "/game_images/Coins_box.png");
                        view.setImage(img);
                    }
                    else if(i==0){
                        //sword sw=new sword(hero.ret_helmet());
                        shuriken sw=new shuriken(hero.ret_helmet());
                        hero.ret_helmet().add_weapon(sw);
                    }
                    else {
                        knife kn=new knife(hero.ret_helmet());
                        hero.ret_helmet().add_weapon(kn);
                    }
                    System.out.println(i);
                    openned=true;
                    this.stop();
                }
            }
        };
        timer.start();
    }
    Treasure_chest(ImageView view){
        this.view=view;
        set_co(view.getLayoutX(),view.getLayoutY());
    }
    public int random(){
        Random random=new Random();
        int val=random.nextInt();
        val=Math.abs(val%3);
        return val;
    }

    @Override
    public boolean is_collide(Will_hero hero) {
        if(view.getBoundsInParent().intersects(hero.ret_view().getBoundsInParent())){
            return true;
        }
        return false;
    }
}
