package com.example.demo1;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class mill_rotate {
    private AnchorPane pane;
    private double w = 200;
    private double h = 200;
    private double radius = Math.min(h,w)*0.5;
    private Arc arr[] = new Arc[3];
    private double start_angle=90;
    private Circle circle = new Circle(w/2,h/2,radius+100);
    private Circle circle_small = new Circle(w/2,h/2,4);
    private Rectangle poll = new Rectangle(w/2-3.5,h/2,7,radius*2);
    public mill_rotate(AnchorPane bp,double x) {
        this.pane=bp;
        circle.setStroke(Color.TRANSPARENT);
        circle.setFill(Color.TRANSPARENT);
        circle_small.setStroke(Color.RED);
        circle_small.setFill(Color.RED);
        poll.setFill(Color.RED);
        poll.setLayoutX(x-100);
        poll.setLayoutY(poll.getLayoutY()+215);
        circle_small.setCenterX(x);
        circle_small.setCenterY(circle_small.getCenterY()+215);
        pane.getChildren().add(circle);
        pane.getChildren().add(circle_small);
        pane.getChildren().add(poll);
        for(int i=0;i<3;i++) {
            arr[i]=new Arc(w/2,h/2,radius,radius,start_angle+120*i,35);
            arr[i].setFill(Color.WHITE);
            arr[i].setType(ArcType.ROUND);
            arr[i].setLength(55);
            pane.getChildren().addAll(arr[i]);
        }
    }
    public Arc[] ret_arc(){
        return arr;
    }
    public void setvalues() {
        radius=Math.min(h,w)*0.5;
        circle.setRadius(radius);
        circle.setCenterX(w/2);
        circle.setCenterY(h/2);
        for(int i=0;i<3;i++) {
            arr[i].setRadiusX(radius+10);
            arr[i].setRadiusY(radius+10);
            arr[i].setCenterX(circle_small.getCenterX());
            arr[i].setCenterY(circle_small.getCenterY());
            arr[i].setStartAngle(start_angle+i*120);
        }
    }
    public void rotate()
    {
        setStartAngle(start_angle+5);
    }
    public void setStartAngle(double startangle) {
        this.start_angle=startangle;
        setvalues();
    }
}