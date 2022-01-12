package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;

public class Play {
    @FXML
    private Button main_menu;
    @FXML
    private Text highscore;
    @FXML
    private ImageView add_weapon;
    @FXML
    private ImageView carried_weapon;
    @FXML
    private Text weapon;
    @FXML
    private ImageView weapon1;
    @FXML
    private ImageView weapon2;
    private ArrayList<game_objects> boxxes;
    @FXML
    private ImageView box1;
    @FXML
    private ImageView box2;
    @FXML
    private ImageView box3;
    @FXML
    private ImageView box4;
    @FXML
    private ImageView box5;
    @FXML
    private Text position;
    private static int value=0;
    @FXML
    private Text coins;
    @FXML
    private Rectangle platform_3;
    @FXML
    private Rectangle platform_4;
    @FXML
    private Rectangle platform_5;
    @FXML
    private Rectangle platform_6;
    @FXML
    private Rectangle platform_7;
    @FXML
    private Rectangle platform_8;
    @FXML
    private Rectangle platform_9;
    @FXML
    private Rectangle platform_10;
    @FXML
    private Rectangle platform_11;
    @FXML
    private Rectangle platform_12;
    @FXML
    private Rectangle platform_13;
    @FXML
    private Rectangle platform_14;
    @FXML
    private AnchorPane main_screen;
    private ArrayList<game_objects> orcs;
    private static platform pl;
    private boolean play_val=false;
    private User usr;
    private main_game obj;
    @FXML
    private Text prompt;
    @FXML
    private Button button;
    @FXML
    private ImageView background;
    @FXML
    private ImageView hero;
    @FXML
    private ImageView orc1;
    @FXML
    private ImageView orc2;
    @FXML
    private ImageView orc3;
    @FXML
    private ImageView orc4;
    @FXML
    private ImageView orc5;
    @FXML
    private ImageView orc6;
    @FXML
    private ImageView orc7;
    @FXML
    private ImageView orc8;
    @FXML
    private ImageView orc9;
    @FXML
    private ImageView boss_orc;
    @FXML
    private Rectangle platform_1;
    @FXML
    private Rectangle platform_2;
    private Button save_and_exit=null;
    private Will_hero will_hero;
    private ArrayList<game_objects> wind_mills=new ArrayList<>();
    public static int ret_pos(){
        return value;
    }
    public static void set_pos(int i){
        value=i;
    }
    //hit key event called when key is pressed
    public void hit_key(KeyEvent keyEvent){
        if(button.getText().equals("Resume")||button.getText().equals("Play")){
            //prompt.setText(prompt.getText()+"\n Can't play while game is paused");
        }
        String a=keyEvent.getCode().toString();
        if(a.equals("ENTER")){
            co_ordinates co=pl.dash();
            value+=1;
            obj.set_position(value);
            position.setText("Position :"+value);
            will_hero.attack();
            System.out.println("count:"+value);
        }
    }
    //setting background for our game
    public void set_back(int n){
        if(n==1){
            background.setImage(new Image("file:/Users/devansh/IdeaProjects/demo1" +
                    "/src/main/java/background_images/Background_plain.jpg"));
            coins.setStyle("-fx-text-inner-color: #12e035");
        }
        else if(n==2){
            background.setImage(new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                    "src/main/java/background_images/background_downhill.jpg"));
            coins.setStyle("-fx-text-inner-color: #de2712");
        }
        else if(n==3){
            background.setImage(new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                    "src/main/java/background_images/background_ghostville.jpg"));
            coins.setStyle("-fx-text-inner-color: #ffffff");
        }
        else if(n==4){
            background.setImage(new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                    "src/main/java/background_images/background_johutenheim.jpg"));
            coins.setStyle("-fx-text-inner-color: #83e18e");
        }
        else {
            background.setImage(new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                    "src/main/java/background_images/background_underworld.jpg"));
            coins.setStyle("-fx-text-inner-color: #ffffff");
        }
        background.setVisible(true);
    }
    public static platform ret_plat(){
        return pl;
    }
    public void initialize() throws IOException{
        this.obj=main_game.load();
        value=this.obj.ret_pos();
        int mode=this.obj.ret_mode();
        System.out.println(mode);
        System.out.println("loaded again");
        this.usr=obj.ret_user();
        System.out.println(obj.ret_background());
        set_back(obj.ret_background());
       this.pl=new platform();
       obj.set_pane(main_screen);
       //Adding all rectangles to the platform
        pl.add_rectangle(platform_1);
        pl.add_rectangle(platform_2);
        pl.add_rectangle(platform_3);
        pl.add_rectangle(platform_4);
        pl.add_rectangle(platform_5);
        pl.add_rectangle(platform_6);
        pl.add_rectangle(platform_7);
        pl.add_rectangle(platform_8);
        pl.add_rectangle(platform_9);
        pl.add_rectangle(platform_10);
        pl.add_rectangle(platform_11);
        pl.add_rectangle(platform_12);
        pl.add_rectangle(platform_13);
        pl.add_rectangle(platform_14);
        //adding all image views to platform
        pl.weapons(weapon,weapon1,weapon2);
        this.will_hero=new Will_hero(hero);
        //initializing all orc type objects
        orc1.setLayoutX(orc1.getLayoutX()+(obj.ret_background()-1)*10);
        Orc orc_1=new Orc(orc1);
        Orc orc_2=new Orc(orc2);
        orc3.setLayoutX(orc3.getLayoutX()+(obj.ret_background()-1)*10);
        Orc orc_3=new Orc(orc3);
        Orc orc_4=new Orc(orc4);
        Orc orc_5=new Orc(orc5);
        orc6.setLayoutX(orc6.getLayoutX()+(obj.ret_background()-1)*10);
        Orc orc_6=new Orc(orc6);
        orc7.setLayoutX(orc7.getLayoutX()-(obj.ret_background()-1)*10);
        Orc orc_7=new Orc(orc7);
        Orc orc_8=new Orc(orc8);
        Orc orc_9=new Orc(orc9);
        boss_orc.setLayoutX(boss_orc.getLayoutX()+(obj.ret_background()-1)*50);
        Boss_orc boss_orc1=new Boss_orc(boss_orc);
        //Initializing all game objects for game
        ArrayList<game_objects> arr=new ArrayList<>();
        arr.add(will_hero);
        //Initializing all treasure chest objects
        Treasure_chest chest1=new Treasure_chest(box1);
        Treasure_chest chest2=new Treasure_chest(box2);
        Treasure_chest chest3=new Treasure_chest(box3);
        Treasure_chest chest4=new Treasure_chest(box4);
        Treasure_chest chest5=new Treasure_chest(box5);
        obj.set_hero(will_hero);//setting hero
        if(value>0){
            int dist=29+50*value;
            if(value>112){
                int d=29+50*112;
                main_screen.setLayoutX(main_screen.getLayoutX()-d);
            }
            else {
                main_screen.setLayoutX(main_screen.getLayoutX()-dist);
            }
            hero.setLayoutX(hero.getLayoutX()+dist);
            button.setLayoutX(button.getLayoutX()+dist);
            weapon.setLayoutX(weapon.getLayoutX()+dist);
            weapon1.setLayoutX(weapon1.getLayoutX()+dist);
            weapon2.setLayoutX(weapon2.getLayoutX()+dist);
            position.setLayoutX(position.getLayoutX()+dist);
            prompt.setLayoutX(prompt.getLayoutX()+dist);
            coins.setLayoutX(coins.getLayoutX()+dist);
            highscore.setLayoutX(highscore.getLayoutX()+dist);
            carried_weapon.setLayoutX(carried_weapon.getLayoutX()+dist);
            add_weapon.setLayoutX(add_weapon.getLayoutX()+dist);
        }
        //adding orcs list
        obj.add_orc(orc_1);
        obj.add_orc(orc_2);
        obj.add_orc(orc_3);
        obj.add_orc(orc_4);
        obj.add_orc(orc_5);
        obj.add_orc(orc_6);
        obj.add_orc(orc_7);
        obj.add_orc(orc_8);
        obj.add_orc(orc_9);
        obj.add_orc(boss_orc1);
        //adding treasure chests
        obj.add_box(chest1);
        obj.add_box(chest2);
        obj.add_box(chest3);
        obj.add_box(chest4);
        obj.add_box(chest5);
        boxxes=obj.ret_boxes();
        orcs=obj.ret_orcs();
        //setting anchor pane
        pl.set_pane(main_screen);
        //adding button
        pl.set_button(button);
        pl.set_Text_for_coins(coins);//text display for coins
        pl.set_text_for_position(position);//text display for position
        pl.set_text_for_highscore(highscore);//text display for high score
        pl.set_main(main_menu);
        highscore.setText("High score:"+obj.ret_high_score());
        position.setText("Position :"+0);
        obj.set_prompt(prompt);//text for display for text
        pl.set_text_for_prompt(prompt);
        pl.add_throw_knife(carried_weapon);
        pl.set_hero(will_hero);
        will_hero.add_throw_knife(carried_weapon);
        will_hero.set_addn_weapon_view(add_weapon);
        will_hero.weapons(weapon,weapon1,weapon2);
        Image img=new Image("file:/Users/devansh/IdeaProjects/demo1/" +
                "src/main/resources/com/example/demo1/" +
                "game_images/windmill3_new.png");
        //setting parameters for windmill
        windmill obj_wind=new windmill(main_screen,2560);
        windmill obj2=new windmill(main_screen,4000);
        wind_mills.add(obj_wind);
        wind_mills.add(obj2);
    }
    public void play_game(){
        //System.out.println(orc1.ret_view());
        prompt.setText("");
        if(button.getText().equals("Play") || button.getText().equals("Resume")){
            Will_hero hero_obj=usr.ret_hero();
            ArrayList<game_objects> orcs_list=orcs;
            /*
                Initializing all collision threads with hero's collision
            */
            Iterator iter=orcs_list.iterator();
            while (iter.hasNext()){
                Orc or=(Orc) iter.next();
                or.collision(hero_obj);
            }
            /*
                Initializing treasure boxes with weapons and coins
            */
            iter=boxxes.iterator();
            while (iter.hasNext()){
                Treasure_chest tr=(Treasure_chest) iter.next();
                tr.collision(hero_obj);
            }
            /*
                Initializing windmills in the game
            */
            iter=wind_mills.iterator();
            while (iter.hasNext()){
                windmill wd=(windmill) iter.next();
                wd.collision(hero_obj);
            }
            if(obj.ret_mode()==1){
                obj.set_res_true();
                usr.play(pl);
            }
            usr.play(pl);
            button.setText("Pause");
        }
        else {
            usr.stop();
            prompt.setText("Game is paused");
            button.setText("Resume");
        }
        System.out.println("pressed");
    }
}
