package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class windmill extends game_objects implements collision{
    private Timeline rotate;
    private mill_rotate mill;
    private Arc[] arc;
    public windmill(AnchorPane pane,double x){
        this.mill=new mill_rotate(pane,x);
        arc=mill.ret_arc();
        rotate = new Timeline(new KeyFrame(Duration.millis(50), e->mill.rotate()));
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();
        //collision(hero);
    }
    public void save_exit(){
        mill=null;
        arc=null;
    }
    @Override
    public boolean is_collide(Will_hero hero) {
        for(int i=0;i<3;i++){
            if(hero.ret_view().getBoundsInParent().intersects(arc[i].getBoundsInParent())){
                double x=hero.ret_view().getLayoutX();
                double y=hero.ret_view().getLayoutY();
                double x_dist=x-arc[i].getCenterX();
                double y_dist=y-arc[i].getCenterY();
                double dist=Math.sqrt(x_dist*x_dist+y_dist*y_dist);
                if(dist<arc[i].getRadiusX()){
                    System.out.println(hero.ret_view().getLayoutX()+" "+
                            hero.ret_view().getLayoutY());
                    System.out.println(arc[i].getBoundsInParent());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void collision(Will_hero hero) {
        AnimationTimer timer=new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(is_collide(hero)){
                    rotate.stop();
                    hero.ret_usr().stop();
                    hero.res_orc(Play.ret_plat());
                    this.stop();
                }
            }
        };
        timer.start();
    }
}
