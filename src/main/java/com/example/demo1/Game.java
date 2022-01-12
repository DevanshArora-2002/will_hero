package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.*;

public class Game extends Application implements Serializable {
    main_game main_game_obj;
   @FXML
   private TextField player_name;
   @FXML
   private Text welcome;
    public static Stage primary_stage;
    public static Parent root;
    @FXML
    private Text prompt;
    @Override
    public void start(Stage stage) throws IOException  {
        primary_stage=stage;
        root= FXMLLoader.load(getClass().getResource("login.fxml"));
        //Parent  root2= FXMLLoader.load(getClass().getResource("Menu.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(root,700,700);
        //scene.getStylesheets().add("application.css");
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //scene.getStylesheets().add("sample.css");
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public  void On_first_page() throws IOException {
        String name=player_name.getText();
        if(name.length()==0){
            System.out.println("no name");
            prompt.setText("Invalid name");

        }
        else {
            main_game_obj=new main_game();
            main_game_obj.set_name(name);
            main_game_obj.save();
            root=FXMLLoader.load(getClass().getResource("afterlogin.fxml"));
            primary_stage.getScene().setRoot(root);
        }
    }
    public void changeScene(String str) throws IOException{
        Parent sc=FXMLLoader.load(getClass().getResource(str));
        primary_stage.getScene().setRoot(sc);
    }
    public static void main(String[] args) {
        launch();
    }
}