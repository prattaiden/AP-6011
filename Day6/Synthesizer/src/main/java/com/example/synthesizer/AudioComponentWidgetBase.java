package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class AudioComponentWidgetBase {

    public VBox BasevBox = new VBox();
    public Label Baselabel;

    //Constructor
    AudioComponentWidgetBase(String label){
        //b = new VBox();
        BasevBox.setStyle("-fx-background-color: blue");


        label = String.valueOf(Baselabel);


    }



    //methods that can be used in any of the widgets

public VBox fillVbox(int lowrange, int highrange, int start, String Slidername){
        Slider slider = new Slider(lowrange, highrange, start);
        Label label = new Label(Slidername);
        VBox vBox = new VBox();
        vBox.relocate(40, 40);
        vBox.getChildren().addAll(label, slider);


 return vBox;

}

















}
