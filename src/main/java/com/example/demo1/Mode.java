package com.example.demo1;

import java.io.IOException;

public class Mode {
    main_game obj;
    public void initialize() throws IOException {
        obj=main_game.load();
    }
    public void hard() throws IOException{
        obj.set_mode(1);
        obj.save();
        Game gm=new Game();
        gm.changeScene("Play.fxml");
    }
    public void slow() throws IOException{
        obj.set_mode(0);
        obj.save();
        Game gm=new Game();
        gm.changeScene("Play.fxml");
    }
}
