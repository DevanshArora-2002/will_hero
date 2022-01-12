package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.*;

public class Load {
    public static void copy(String s1, String s2) throws IOException{
        File f1=new File(s1);
        File f2=new File(s2);
        FileInputStream fin=new FileInputStream(f1);
        FileOutputStream fout=new FileOutputStream(f2);
        ObjectInputStream in=new ObjectInputStream(fin);
        ObjectOutputStream out=new ObjectOutputStream(fout);
        main_game pl=null;
        do{
            try{
                out.writeObject(in.readObject());
            }catch (Exception e){
                System.out.println("error");
            }
        }while (pl!=null);
    }
    @FXML
    private TextField name;
    @FXML
    private Text prompt;
    @FXML
    private AnchorPane pane;
    private main_game obj;
    public void initialize() throws IOException{
        obj=main_game.load();
    }
    public void search() throws IOException{
        copy("all_data.txt","sample.txt");
        String name=obj.ret_user().ret_name();
        File f=new File("sample.txt");
        FileInputStream fin=new FileInputStream(f);
        ObjectInputStream in=new ObjectInputStream(fin);
        main_game gm=null;
        do{
            try{
                gm=(main_game) in.readObject();
                if(gm!=null){
                    if(name.equals(gm.ret_user().ret_name())){
                        gm.save();
                        Game gam=new Game();
                        gam.changeScene("rules.fxml");
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println("Error");
            }
        }while (gm!=null);
        prompt.setText("No such ");
    }
}