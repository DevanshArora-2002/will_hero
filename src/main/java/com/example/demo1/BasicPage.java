package com.example.demo1;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BasicPage {
    @FXML
    private ImageView orc_id;
    @FXML
    private Rectangle platform;
    AnimationTimer collisiontimer=new AnimationTimer(){
        @Override
        public void handle(long timestamp){
            if(platform.getBoundsInParent().intersects(orc_id.getBoundsInParent())){
                jump();
            }
        }
    };
    public void hit(){
        collisiontimer.start();
    }
    public void jump(){
        System.out.println("jumped");
        TranslateTransition transition=new TranslateTransition();
        transition.setToY(-50);
        transition.setDuration(Duration.seconds(.5));
        transition.setAutoReverse(true);
        transition.setCycleCount(2);
        transition.setNode(orc_id);
        transition.play();
    }
}
