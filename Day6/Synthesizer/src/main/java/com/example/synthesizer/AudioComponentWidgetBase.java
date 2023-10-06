package com.example.synthesizer;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//class to create object of this class
public class AudioComponentWidgetBase extends Pane {

    AudioComponent ac_;
    AnchorPane parent_;

    //member so that it can be used in the other widgets
    HBox sliderPanel_;
    HBox titlePanel_;


    public AudioComponentWidgetBase(AudioComponent ac, AnchorPane parent) {
        ac_ = ac;
        parent_ = parent;


        //------------------------WHOLE THING--------------------------------
        HBox baseLayout = new HBox();
        baseLayout.setStyle("-fx-border-color: #9bcb92; -fx-background-color: #d9c093");



        //-----------------VBOX FOR SPEAKER AND X------------------------
        VBox side = new VBox();

        side.setStyle("-fx-background-color: pink; -fx-border-color: black");

        Button Closebtn = new Button("X");
        Closebtn.setOnAction(e -> closeWidget(e));
        Closebtn.setPadding(new Insets(2));

        Circle outputSpeaker = new Circle(100, 100, 10);

        outputSpeaker.setFill(Color.AQUA);

        side.getChildren().add(Closebtn);

        side.getChildren().add(outputSpeaker);
        side.setSpacing(5);
        //side.setAlignment(Pos.CENTER);
        side.setPadding(new Insets(10));


        //------------------------BOX FOR SLIDER-------------------------
        sliderPanel_ = new HBox();
        sliderPanel_.setStyle("-fx-background-color: #7f90d2; -fx-border-color: black");
        sliderPanel_.setPadding(new Insets(30));
        sliderPanel_.setSpacing(10);
        //PRESET DIMENSIONSHOW?????


        //-----------------------BOX FOR TITLE-----------------------
        titlePanel_ = new HBox();
        titlePanel_.setStyle("-fx-background-color: #e7aa8d; -fx-border-color: black");



        //SETTING THEM TO THE BASE WIDGET
        baseLayout.getChildren().add(titlePanel_);
        baseLayout.getChildren().add(sliderPanel_);
        baseLayout.getChildren().add(side);
        //moving the baselout to the main panel"this"
        this.getChildren().add(baseLayout);

    }

    private void closeWidget(ActionEvent e) {

        //removing from parent
        parent_.getChildren().remove(this);

        //removing from arraylist of widgets
        SynthesizeApplication.widgets_.remove(this);

    }

    public AudioComponent getAudioComponent(){
        return ac_;
    }


    }





