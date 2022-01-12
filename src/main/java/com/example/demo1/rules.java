package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class rules {
    @FXML
    private Text prompt;
    @FXML
    private Button id1;
    @FXML
    private Button img1;
    @FXML
    private Button img2;
    @FXML
    private Button img3;
    @FXML
    private Button img4;
    @FXML
    private Button img5;
    private main_game obj;
    public void func1(){
        obj.set_background_no(1);
        prompt.setText("Map selected: Plains");
    }
    public void func2(){
        obj.set_background_no(2);
        prompt.setText("Map selected: Downhill");
    }
    public void func3(){
        obj.set_background_no(3);
        prompt.setText("Map selected: Ghostville");
    }
    public void func4(){
        obj.set_background_no(4);
        prompt.setText("Map selected : Jotunheim");
    }
    public void func5(){
        obj.set_background_no(5);
        prompt.setText("Map selected : Underworld");
    }
    public void start() throws IOException {
        obj.save();
        Game gm=new Game();
        gm.changeScene("mode.fxml");
    }
    public void load() throws IOException{

    }
    public void exit() throws IOException{
        File f=new File("all_data.txt");
        FileOutputStream fout=new FileOutputStream(f);
        ObjectOutputStream out=new ObjectOutputStream(fout);
        obj=main_game.load();
        out.writeObject(obj);
        Game gm=new Game();
        gm.changeScene("login.fxml");
    }
    public void initialize() throws IOException {
        obj=main_game.load();
        Image img_1=new Image("file:/Users/devansh/Idea" +
                "Projects/demo1/src/main/java/" +
                "background_images/Background_plain.jpg");
        Image img_2=new Image("file:/Users/devansh/Idea" +
                "Projects/demo1/src/main/java" +
                "/background_images/background_downhill.jpg");
        Image img_3=new Image("file:/Users/devansh/Idea" +
                "Projects/demo1/src/main/java/" +
                "background_images/background_ghostville.jpg");
        Image img_4=new Image("file:/Users/devansh/Idea" +
                "Projects/demo1/src/main/java/" +
                "background_images/background_johutenheim.jpg");
        Image img_5=new Image("file:/Users/devansh/Idea" +
                "Projects/demo1/src/main/java/" +
                "background_images/background_underworld.jpg");

        ImageView view1=new ImageView(img_1);
        view1.setFitWidth(95);
        view1.setFitHeight(65);
        ImageView view2=new ImageView(img_2);
        view2.setFitWidth(95);
        view2.setFitHeight(65);
        ImageView view3=new ImageView(img_3);
        view3.setFitWidth(95);
        view3.setFitHeight(65);
        ImageView view4=new ImageView(img_4);
        view4.setFitWidth(95);
        view4.setFitHeight(65);
        ImageView view5=new ImageView(img_5);
        view5.setFitWidth(95);
        view5.setFitHeight(65);
        img1.setGraphic(view1);
        img1.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        img2.setGraphic(view2);
        img2.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        img3.setGraphic(view3);
        img3.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        img4.setGraphic(view4);
        img4.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        img5.setGraphic(view5);
        img5.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
}
