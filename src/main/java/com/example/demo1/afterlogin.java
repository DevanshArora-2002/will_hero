package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class afterlogin {
    @FXML
    private AnchorPane pane;
    @FXML
    private  Text welcome;
    private  main_game obj;
    public void initialize() throws IOException {
        obj = null;
        try {
            obj=main_game.load();  
        }
        catch (Exception ex){
            System.out.println("loading issue");
        }
        String name= obj.ret_user().ret_name();
        welcome.setText("Welcome "+ name);
        obj.set_background_no(1);
    }
    public void start_game() throws IOException {
        Game gm=new Game();
        gm.changeScene("rules.fxml");
    }
    public void load_game() throws IOException{
        main_game obj=main_game.load();
        if(obj==null){
            Text txt=new Text("No game exists");
            txt.setLayoutX(300);
            txt.setLayoutY(300);
            txt.setFont(Font.font(21));
            pane.getChildren().add(txt);
        }
        else {
            obj.save();
            Game gm=new Game();
            gm.changeScene("Play.fxml");
        }
    }
    public void exit_game() throws IOException{
        Game gm=new Game();
        obj.save_main_data();
        gm.changeScene("login.fxml");
    }
}
