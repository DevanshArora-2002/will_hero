package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;

public class Boss_orc extends Orc{
    private double health_blow=7;
    private boolean live=false;
    Boss_orc(ImageView view) {
        super(view);
    }
    public void hit(Will_hero hero){
        health_blow-=0.0001;
        hero.attack_boss(this);
        if(health_blow<=0){
            health_blow=0;
        }
    }
    @Override
    public void collision(Will_hero hero){
        AnimationTimer timer =new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(is_collide(hero)){
                    System.out.println("Collision");
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
                        System.out.println("Ended");
                        //this.stop();
                        //hero.ret_view().relocate(view.getLayoutX()-100,view.getLayoutY());
                        hero.ret_usr().stop();
                    }
                    else {
                        Play.ret_plat().collided();
                        System.out.println(" Boss Collision");
                        timex=0;
                        health_blow--;
                    }
                }
                if(timex<10){
                    timex+=1;
                    change_co(view.getLayoutX()+1,ret_co().ret_y());
                    view.relocate(ret_co().ret_x(),view.getLayoutY());
                }
                if(health_blow<=0){
                    if(live==false){
                        view.relocate(view.getLayoutX(),2000);
                        hero.gain_coin(5);
                    }
                    live=true;
                }
            }
        };
        timer.start();
    }
}
