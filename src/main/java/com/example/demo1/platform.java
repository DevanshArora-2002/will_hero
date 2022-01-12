package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class platform extends game_objects implements collision{
    private Button butn;
    private Will_hero hero;
    private boolean val=false;
    private co_ordinates co;
    private double timex=21;
    private ImageView throw_knife;
    private ImageView throw_knife_2;
    private AnchorPane pane;
    private Button play_pause;
    private Rectangle current_area;
    private Text coins;
    private Text position;
    private Text prompt;
    private double sample=2.5;
    private Text weapon;
    private ImageView weapon1;
    private ImageView weapon2;
    private Text high;
    ArrayList<Rectangle> areas=new ArrayList<>();
    public void set_hero(Will_hero he){
        this.hero=he;
    }
    public void set_pane(AnchorPane pane){
        this.pane=pane;
    }
    void add_rectangle(Rectangle area){
        areas.add(area);
    }
    {
        val=false;
    }
    @Override
    public void save_exit(){
        throw_knife=null;
        throw_knife_2=null;
        pane=null;
        play_pause=null;
        coins=null;
        weapon1=null;
        weapon2=null;
        weapon=null;
        high=null;
        areas=null;
        position=null;
    }
    public void collided(){
        timex=21;
    }
    public void set_throw_knife_2(ImageView v){
        this.throw_knife_2=v;
    }
    public void weapons(Text t1,ImageView w1,ImageView w2){
        this.weapon=t1;
        this.weapon1=w1;
        this.weapon2=w2;
    }
    public void resurrect(ArrayList<game_objects> obj,Will_hero hero){
        System.out.println("inside res");
        pane.setLayoutX(pane.getLayoutX()+100);
        play_pause.setLayoutX(play_pause.getLayoutX()-100);
        coins.setLayoutX(coins.getLayoutX()-100);
        position.setLayoutX(position.getLayoutX()-100);
        prompt.setLayoutX(prompt.getLayoutX()-100);
        weapon.setLayoutX(weapon.getLayoutX()-100);
        weapon1.setLayoutX(weapon1.getLayoutX()-100);
        weapon2.setLayoutX(weapon2.getLayoutX()-100);
        throw_knife.setLayoutX(throw_knife.getLayoutX()-100);
        throw_knife_2.setLayoutX(throw_knife_2.getLayoutX()-100);
        high.setLayoutX(high.getLayoutX()-100);
        int val=Play.ret_pos();
        position.setText("Position :"+(val-2));
        play_pause.setText("Pause");
        Play.set_pos(val-2);
        start(obj);
    }
    public void set_main(Button btn){
        this.butn=btn;
    }
    public void add_throw_knife(ImageView img){
        this.throw_knife=img;
    }
    public void set_text_for_position(Text txt){
        this.position=txt;
    }
    public void set_text_for_prompt(Text txt){
        this.prompt=txt;
    }
    public co_ordinates dash(){
        timex-=21;
        co_ordinates co=new co_ordinates(1,2);
        return co;
    }
    public void set_button(Button but){
        this.play_pause=but;
    }
    public void set_Text_for_coins(Text txt){
        coins=txt;
    }
    public void start(ArrayList<game_objects> arr){
        val=false;
        int size=arr.size();
        for(int i=0;i<size;i++){
            if(arr.get(i) instanceof Will_hero){
                collision((Will_hero) arr.get(i));
            }
            else if(arr.get(i) instanceof  Boss_orc){
                collision((Boss_orc) arr.get(i));
                System.out.println("Boss init");
            }
            else if(arr.get(i) instanceof Orc){
                collision((Orc) arr.get(i));
            }
        }
    }
    public void set_text_for_highscore(Text txt){
        this.high=txt;
    }
    public void stop_all(){
        this.val=true;
        Button btn=new Button("Main menu");
        btn.setLayoutX(play_pause.getLayoutX());
        btn.setLayoutY(play_pause.getLayoutY()+50);
        btn.setPrefWidth(100);
        btn.setPrefHeight(50);
        pane.getChildren().add(btn);
        btn.setId("button exit");
        this.butn=btn;
        btn.setOnMouseClicked((e)->{
            Game gm=new Game();
            System.out.println("Called");
            try {
                if(play_pause.getText().equals("Resume")){
                    hero.ret_usr().ret_game().save();
                    System.out.println("aaouhaohi");
                    gm.changeScene("afterlogin.fxml");
                }
                else {
                    if(hero.ret_usr().ret_coins()>hero.ret_usr().ret_game().ret_high_score()){
                        hero.ret_usr().ret_game().set_high(hero.ret_usr().ret_coins());
                    }
                    System.out.println(hero.ret_usr().ret_coins()+" "+hero.ret_usr().ret_game().ret_high_score());
                    hero.ret_usr().ret_game().set_ressurct();
                    int coins=hero.ret_usr().ret_coins();
                    hero.ret_usr().add_coin(-coins);
                    hero.ret_usr().ret_game().set_position(0);
                    hero.ret_usr().ret_game().save();
                    gm.changeScene("afterlogin.fxml");
                }
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        });
        //System.out.println("Called");
    }
    @Override
    public boolean is_collide(Will_hero hero){
        for (int i=0;i<areas.size();i++){
            if(areas.get(i).getBoundsInParent().intersects(hero.ret_view().getBoundsInParent())){
                current_area=areas.get(i);
                //hero.set_co(hero.ret_view().getLayoutX(),current_area.getBoundsInParent().getMinY()-hero.ret_view().getFitHeight()-3.6);
                return true;
            }
        }
        return false;
    }
    public boolean is_collide(Orc orc){
        for(int i=0;i<areas.size();i++){
            if(areas.get(i).getBoundsInParent().intersects(orc.ret_view().getBoundsInParent())){
                return true;
            }
        }
        return false;
    }
    public boolean is_alive(Will_hero hero){
        double y_max=hero.ret_view().getBoundsInParent().getMaxY();
        if(y_max>600){
            return false;
        }
        return true;
    }
    @Override
    public void collision(Will_hero hero) {
        platform pl=this;
        AnimationTimer timer = new AnimationTimer() {
            double drag = 1.75;
            double mytime = 0.0;
            double velocityY = hero.ret_vely();
            double gravity = 1;
            double prev_velocity;
            double diff=0;
            int counter = 0;
            double velX=sample;
            @Override
            public void handle(long l) {
                throw_knife.setLayoutY(hero.ret_view().getLayoutY());
                throw_knife_2.setLayoutY(hero.ret_view().getLayoutY()+40);
                coins.setText("Coins collected :"+hero.ret_usr().ret_coins());
                double currX = hero.ret_view().getLayoutX();
                double currY = hero.ret_view().getLayoutY();
                if(val){
                    this.stop();
                    hero.set_vely(velocityY);
                }
                int pos=hero.ret_usr().ret_game().ret_pos();
                if(timex<20){
                    diff=velX*1;
                    //System.out.println(currX);
                    currX+=diff;
                    timex+=1;
                    if(pos<111){
                        pane.setLayoutX(pane.getLayoutX()-diff);
                    }
                }
                else {
                    timex=21;
                    diff=0;
                }
                counter++;
                velocityY += gravity * mytime;
                currY = currY + velocityY * mytime + 0.5 * gravity * mytime * mytime;
                hero.ret_view().relocate(currX, currY);
                if(pos<111){
                    //high_score
                    high.setLayoutX(high.getLayoutX()+diff);
                    //play_pause
                    play_pause.setLayoutX(play_pause.getLayoutX()+diff);
                    //coins
                    coins.setLayoutX(coins.getLayoutX()+diff);
                    //position
                    position.setLayoutX(position.getLayoutX()+diff);
                    //prompt
                    prompt.setLayoutX(prompt.getLayoutX()+diff);
                    //all weapon views
                    weapon.setLayoutX(weapon.getLayoutX()+diff);
                    weapon1.setLayoutX(weapon1.getLayoutX()+diff);
                    weapon2.setLayoutX(weapon2.getLayoutX()+diff);
                }
                throw_knife.setLayoutX(throw_knife.getLayoutX()+diff);
                if(throw_knife_2!=null){
                    throw_knife_2.setLayoutX(throw_knife_2.getLayoutX()+diff);
                }
                if (is_collide(hero)) {
                    //System.out.println(hero.ret_view().getLayoutX()+"  "+hero.ret_view().getLayoutY());
                    hero.ret_view().relocate(hero.ret_view().getLayoutX(),hero.ret_co().ret_y());
                    mytime = 0;
                    velocityY = -12;
                    hero.ret_view().relocate(hero.ret_view().getLayoutX(),hero.ret_co().ret_y()-1);
                }
                mytime += 0.005;
                if(is_alive(hero)==false){
                    hero.ret_usr().stop();
                    hero.res_ab(pl);
                }
                if(pos>=121){
                    if(hero.ret_usr().ret_coins()>hero.ret_usr().ret_game().ret_high_score()){
                        hero.ret_usr().ret_game().set_high(hero.ret_usr().ret_coins());
                    }
                    hero.ret_usr().stop();
                }
            }
        };
        timer.start();
    }
    public void collision(Orc orc){
        AnimationTimer timer = new AnimationTimer() {
            double drag = 0.1;
            double velocityX = 0;
            double mytime = 0.0;
            double velocityY = orc.ret_vely();
            double gravity = 1;
            double prev_velocity;
            int counter = 0;
            boolean play=val;
            @Override
            public void handle(long l) {
                if(val){
                    this.stop();
                    orc.set_vely(velocityY);
                }
                double currX = orc.ret_view().getLayoutX();
                double currY = orc.ret_view().getLayoutY();
                counter++;
                velocityY += gravity * mytime;
                currY = currY + velocityY * mytime + 0.5 * gravity * mytime * mytime;
                orc.set_velocity(velocityX,velocityY);
                orc.ret_view().relocate(currX, currY);
                if (is_collide(orc)) {
                    mytime = 0;
                    velocityY = -17;
                    orc.ret_view().relocate(orc.ret_co().ret_x(),orc.ret_co().ret_y());
                }
                mytime += 0.003;
            }
        };
        timer.start();
    }
    public void collision(Boss_orc orc){
        AnimationTimer timer = new AnimationTimer() {
            double drag = 0.1;
            double velocityX = 0;
            double mytime = 0.0;
            double velocityY = orc.ret_vely();
            double gravity = 1;
            double prev_velocity;
            int counter = 0;
            boolean play=val;
            @Override
            public void handle(long l) {
                if(val){
                    this.stop();
                    orc.set_vely(velocityY);
                }
                double currX = orc.ret_view().getLayoutX();
                double currY = orc.ret_view().getLayoutY();
                counter++;
                velocityY += gravity * mytime;
                currY = currY + velocityY * mytime + 0.5 * gravity * mytime * mytime;
                orc.set_velocity(velocityX,velocityY);
                orc.ret_view().relocate(currX, currY);
                if (is_collide(orc)) {
                    mytime = 0;
                    velocityY = -10;
                    orc.ret_view().relocate(orc.ret_co().ret_x(),orc.ret_co().ret_y());
                }
                mytime += 0.002;
            }
        };
        timer.start();
    }
}



