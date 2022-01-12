package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class main_game implements Serializable {
    private int highscore=0;
    private int background_no;
    private boolean resurrected=false;
    private int position=0;
    private User usr;
    private AnchorPane main_pane;
    private ArrayList<game_objects> orcs=new ArrayList<>();
    private ArrayList<game_objects> all_objects=new ArrayList<>();
    private ArrayList<game_objects> boxxes=new ArrayList<>();
    private Text prompt;
    private int mode;
    public ArrayList<game_objects>  ret_orcs(){
        return orcs;
    }
    public int ret_pos(){
        return position;
    }
    public void set_mode(int i){
        mode=i;
    }
    public int ret_mode(){
        return mode;
    }
    public void set_res_true(){
        resurrected=true;
    }
    public void set_prompt(Text txt){
        this.prompt=txt;
    }
    public void stop(platform plat){
        prompt.setText("Game ended");
        plat.stop_all();
    }
    public void set_position(int pos){
        this.position=pos;
    }
    public int ret_high_score(){
        return highscore;
    }
    main_game(){
        usr=new User(this);
        orcs=new ArrayList<>();
        all_objects=new ArrayList<>();
    }
    public void resurrect_orc(platform plat) {
        if(!resurrected){
            Text txt=new Text();
            double x=main_pane.getLayoutX();
            x=Math.abs(x);
            System.out.println(x);
            txt.setLayoutX(x+100);
            txt.setLayoutY(320);
            txt.setFont(Font.font(17));
            txt.setFill(Color.WHITE);
            int coins=usr.ret_coins();
            if(coins<1){
                txt.setText("Not enough coins to resurrect");
                main_pane.getChildren().add(txt);
                resurrected=true;
                return;
            }
            txt.setText("Do you want to resurrect using 1 coins");
            Button btn=new Button("Resurrect");
            btn.setPrefHeight(40);
            btn.setPrefWidth(100);
            btn.setLayoutX(x+150);
            btn.setLayoutY(330);
            main_pane.getChildren().add(btn);
            btn.setOnAction((e)->{
                System.out.println("k;jsdk;jsdc;kbjsd");
                usr.add_coin(-1);
                ImageView view=usr.ret_hero().ret_view();
                view.relocate(view.getLayoutX()-100,view.getLayoutY());
                plat.resurrect(all_objects,usr.ret_hero());
                main_pane.getChildren().remove(btn);
                main_pane.getChildren().remove(txt);
                prompt.setText("");
            });
        }
        else {
            if(highscore<usr.ret_coins()){
                highscore=usr.ret_coins();
            }
        }
        resurrected=true;
    }
    public void resurrect_abyss(platform plat){
        if(!resurrected){
            Text txt=new Text();
            double x=main_pane.getLayoutX();
            x=Math.abs(x);
            System.out.println(x);
            txt.setLayoutX(x+100);
            txt.setLayoutY(320);
            txt.setFont(Font.font(17));
            txt.setFill(Color.WHITE);
            int coins=usr.ret_coins();
            if(coins<1){
                txt.setText("Not enough coins to resurrect");
                main_pane.getChildren().add(txt);
                return;
            }
            txt.setText("Do you want to resurrect using 1 coins");
            Button btn=new Button("Resurrect");
            btn.setPrefHeight(40);
            btn.setPrefWidth(100);
            btn.setLayoutX(x+150);
            btn.setLayoutY(330);
            main_pane.getChildren().add(btn);
            btn.setOnAction((e)->{
                System.out.println("k;jsdk;jsdc;kbjsd");
                usr.add_coin(-1);
                ImageView view=usr.ret_hero().ret_view();
                view.relocate(view.getLayoutX()-100,view.getLayoutY()-200);
                plat.resurrect(all_objects,usr.ret_hero());
                main_pane.getChildren().remove(btn);
                main_pane.getChildren().remove(txt);
                prompt.setText("");
            });
        }
        else {
            if(highscore<usr.ret_coins()){
                highscore=usr.ret_coins();
            }
        }
        resurrected=true;
    }
    public void set_ressurct(){
        resurrected=false;
    }
    public int ret_background(){
        return background_no;
    }
    public void set_background_no(int n){
        background_no=n;
    }
    public  User ret_user(){
        return usr;
    }
    public void add_orc(Orc orc){
        all_objects.add(orc);
        if(orcs==null){
            orcs=new ArrayList<>();
        }
        orcs.add(orc);
    }
    public void add_box(Treasure_chest chest){
        if(boxxes==null){
            boxxes=new ArrayList<>();
        }
        boxxes.add(chest);
        all_objects.add(chest);
    }
    public ArrayList<game_objects> ret_boxes(){
        return boxxes;
    }
    public void set_name(String name){
        this.usr.set_name(name);
    }
    public void set_hero(Will_hero hero){
        all_objects=new ArrayList<>();
        all_objects.add(hero);
        this.usr.set_hero(hero);
    }
    public void save() throws IOException {
        usr.save();
        main_pane=null;
        prompt=null;
        boxxes=null;
        orcs=null;
        all_objects=null;
        File f=new File("player_data.txt");
        FileOutputStream fout=new FileOutputStream(f);
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(this);
        fout.close();
        out.close();
    }
    public void set_high(int i){
        highscore=i;
    }
    public static main_game load_main_data(main_game x) throws IOException{
        File f=new File("all_data.txt");
        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream in=new ObjectInputStream(fin);
        main_game obj=null;
        do{
            try{
                obj=(main_game) in.readObject();
                if(obj!=null){
                    if(obj.ret_user().ret_name().equals(x.ret_user().ret_name())){
                        return obj;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Error");
            }
        }while (obj!=null);
        return null;
    }
    public void save_main_data() throws  IOException{
        usr.save();
        main_pane=null;
        prompt=null;
        boxxes=null;
        all_objects=null;
        orcs=null;
        FileOutputStream fout=new FileOutputStream("all_data.txt");
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(this);
        fout.close();
        out.close();
    }
    public void play(platform plat){
        plat.start(all_objects);
    }
    public void pause(){

    }
    public void set_pane(AnchorPane pane){
        this.main_pane=pane;
    }
    public static main_game load() throws IOException{
        File f=new File("player_data.txt");
        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream obj=new ObjectInputStream(fin);
        main_game o = null;
        try {
            o=(main_game) obj.readObject();
        } catch (Exception e) {
            System.out.println("Object not loaded");
        }
        finally {
            fin.close();
            obj.close();
        }
        return o;
    }
    public void save_load() throws  IOException{
        usr.save();
        main_pane=null;
        prompt=null;
        boxxes=null;
        all_objects=null;
        orcs=null;
        File f=new File("load.txt");
        FileOutputStream fout=new FileOutputStream(f);
        ObjectOutputStream out=new ObjectOutputStream(fout);
        out.writeObject(this);
    }
    public static main_game load_norm() throws IOException{
        File f=new File("load.txt");
        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream obj=new ObjectInputStream(fin);
        main_game o = null;
        try {
            o=(main_game) obj.readObject();
        } catch (Exception e) {
            System.out.println("Object not loaded");
        }
        finally {
            fin.close();
            obj.close();
        }
        return o;
    }
}
