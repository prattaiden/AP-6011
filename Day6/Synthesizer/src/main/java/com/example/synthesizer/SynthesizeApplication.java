package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class SynthesizeApplication extends Application {

    //CALL SUPER CONSTRUCTOR


   public static ArrayList<AudioComponentWidgetBase> widgets_ = new ArrayList<>();

    //AUDIO LISTENER

    //FOR PLAY


    //ANCHOR PANE
    AnchorPane anchorMain = new AnchorPane();

    @Override
    public void start(Stage stage) throws IOException {


        //--------------IMPORTANT FOR MAINLAYOUT-------------------
        //--------------------------BORDER---------------------------
        BorderPane main = new BorderPane();

        //-------------------------LEFT PANEL----------------------------
        //HBOX
        HBox leftpanel = new HBox();
        String cssLayoutleft = "-fx-border-color: #000000;\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: line;\n" +
                "-fx-background-color: #8d9edc";
       leftpanel.setStyle(cssLayoutleft);
        leftpanel.setSpacing(30);
        leftpanel.setPadding(new Insets(5, 5, 5, 5));

        //Square widget button
        Button squarewaveBTN = new Button("SQAURE");
        leftpanel.getChildren().add(squarewaveBTN);

        //Volume widget button
        Button sinewaveBTN = new Button("SINEWAVE");
        leftpanel.getChildren().add(sinewaveBTN);

        //SETTING IT TO THE LEFT
        main.setLeft(leftpanel);


        //---------------------------BOTTOM PANEL---------------------------
        HBox botPanel = new HBox();
        String cssLayoutbot = "-fx-border-color: #000000;\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: line;\n" +
                "-fx-background-color: #f1a2b9";
        botPanel.setStyle(cssLayoutbot);
        //PLAY BUTTON with border pain
        Button playButton = new Button("PLAY");
        //ADDING PLAYBUTTON TO HBOX MENU
        botPanel.getChildren().add(playButton);
        botPanel.setAlignment(Pos.CENTER);
        main.setBottom(botPanel);

       // ---------------------------CIRCLE----------------------------------
        Circle speaker = new Circle(400, 200 , 15);
        anchorMain.getChildren().add(speaker);
        speaker.relocate(600, 20);
        //COLoR?

        main.setCenter(anchorMain);



        //----------------------SCENE FOR BORDER PANE-----------------------------
        Scene scene = new Scene(main, 1000, 600);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();

        //--------------------buttons to open the widget---------------------------
        squarewaveBTN.setOnAction(e -> {
            createWidget(Components.SQAURE_WAVE);
        });

        sinewaveBTN.setOnAction(e -> {
            createWidget(Components.SINE_WAVE);
        });


        //setting an action, calling a function "handlePLayPress"
        playButton.setOnAction(e-> {
            try {
                handlePlayPress();
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    //------------------------METHODS----------------------------------
    private void createWidget(Components components) {
        AudioComponent audioComponent = null;
        if(components.equals(Components.SINE_WAVE)){
            audioComponent = new SineWave(450);
            SineWaveWidget sinewidg = new SineWaveWidget(audioComponent, anchorMain);
            anchorMain.getChildren().add(sinewidg);
            sinewidg.relocate(100, 200);
            widgets_.add(sinewidg);
        }

        else if(components.equals(Components.SQAURE_WAVE)){
            audioComponent = new SquareWave(450);
            SquareWaveWidget squarewidg = new SquareWaveWidget(audioComponent, anchorMain);
            anchorMain.getChildren().add(squarewidg);
            squarewidg.relocate(100, 400);
            widgets_.add(squarewidg);
        }
//        AudioComponentWidgetBase acw = new AudioComponentWidgetBase(audioComponent, anchorMain);
//        anchorMain.getChildren().add(acw);
        //adding this to my array list of widgets
//        widgets_.add(acw);
    }

    //updated to not need text box, using freqSlider function
    private void handlePlayPress() throws LineUnavailableException {


            //SETTING CLIP C AND THE DEFAULT AUDIOFORMAT
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            //ARRAY LIST OF BYTES FROM THE WIDGET
            //this is where it is getting the widget
        System.out.println("# wigets" + widgets_.size());
            byte[] data = widgets_.get(0).ac_.getClip().getData();


            AudioListener listener = new AudioListener(c);

            c.open(format16, data, 0, data.length); // Reads data from our byte array to play it.

            c.start(); // Plays it.

            c.addLineListener(listener);


    }

    public static void main(String[] args) {

        launch();
    }
}



//RIGHT PANEL
//VBOX1
//        VBox rightpanel = new VBox();
//
//        String cssLayoutright = "-fx-border-color: #000000;\n" +
//                "-fx-border-insets: 0;\n" +
//                "-fx-border-width: 3;\n" +
//                "-fx-border-style: line;\n" +
//                "-fx-background-color: #9ae59a";
//        rightpanel.setStyle(cssLayoutright);

//SLIDER
//minimum, maximum, default
//frequency label that will be used in vbox1 for the slider
//        Label frequencyLabel = new Label("Frequency: ");
//        Slider freqSlider = new Slider(50, 400, 100);
//        freqSlider.relocate(450, 250);
//        Label widgetTitle = new Label("Square Wave");

//MOVING IT TO MAINBORDER
//main.setRight(rightpanel);

//after setting widget , add to the layout
//ADDING TO THE VBOX
//        rightpanel.getChildren().add(widgetTitle);
//        rightpanel.getChildren().add(freqSlider);
//        rightpanel.getChildren().add(frequencyLabel);
//
//        //RELOCATING VBOX
//        rightpanel.relocate(40, 100);

//CENTER PANEL
//ANCHORMAIN
//AnchorPane anchorMain = new AnchorPane();