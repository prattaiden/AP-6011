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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SynthesizeApplication extends Application {

    //CALL SUPER CONSTRUCTOR
    //AudioComponentWidgetBase();


    int frequencySliderNum = 0;

    @Override
    public void start(Stage stage) throws IOException {


        //IMPORTANT FOR MAINLAYOUT
        //ANCHORMAIN
        AnchorPane anchorMain = new AnchorPane();

        //HBOX
        HBox menu = new HBox();
        menu.setStyle("-fx-background-color: #b4b4ea");
        menu.setSpacing(30);
        menu.setPadding(new Insets(5, 5, 5, 5));


        //VBOX1
        VBox vBox1 = new VBox();

        String cssLayout = "-fx-border-color: #000000;\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: line;\n" +
                "-fx-background-color: #9ae59a";
        vBox1.setStyle(cssLayout);

//        VBox playbuttonbox = new VBox();
//        playbuttonbox.setStyle("-fx-background-color: #f8cad1");


        //adding things to anchormain and setting the style
        anchorMain.getChildren().add(vBox1);
        anchorMain.getChildren().add(menu);
        anchorMain.setStyle("-fx-padding: 30; -fx-background-color: #ccc7c7");

        //Text frequencyFieldBar = new Text("Enter Frequency:");
        //TextField frequencyfield = new TextField();

        //PLAY BUTTON with border pain
        Button playButton = new Button("PLAY");
//        playButton.relocate(450, 100);


        //SLIDER
        //minimum, maximum, default
        //frequency label that will be used in vbox1 for the slider
        Label frequencyLabel = new Label("Frequency: ");
        Slider freqSlider = new Slider(50, 400, 100);
        freqSlider.relocate(450, 250);

//        Button VolumeUpButton = new Button("Volume up");
//        Button VolumeDownButton = new Button ("Volume down");

        //after setting widget , add to the layout
        //ADDING TO THE VBOX
        vBox1.getChildren().add(freqSlider);
        vBox1.getChildren().add(frequencyLabel);
        //RELOCATING VBOX
        vBox1.relocate(40, 100);

        //ADDING PLAYBUTTON TO HBOX MENU
        menu.getChildren().add(playButton);
        menu.relocate(150, 200);
        menu.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(anchorMain, 350, 350);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();



        //setting an action, calling a function "handlePLayPress"
        playButton.setOnAction(e-> {
            try {
                handlePlayPress(frequencySliderNum);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

        //handle slider
        freqSlider.setOnMouseDragged(e->handleFreqSlider(e, freqSlider, frequencyLabel));
    }


    //FUNCTIONS HANDLING SLIDER AND BUTTON
    private void handleFreqSlider(MouseEvent e, Slider freqSlider, Label frequencyLabel) {
        //give value of the slider
        //set the value of the label using the slider's value
        int result = (int)freqSlider.getValue();
        frequencyLabel.setText("Frequency: " + result);
       frequencySliderNum = result;

    }

    //updated to not need text box, using freqSlider function
    private void handlePlayPress(int freq) throws LineUnavailableException {
        //add the values in number1 and number2

         //Integer.parseInt(freq.getText());


        AudioComponent gen = new SquareWave(freq);
        AudioClip clip = gen.getClip();

        //SETTING CLIP C AND THE DEFAULT AUDIOFORMAT
        Clip c = AudioSystem.getClip();
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        c.start(); // Plays it.

       //GOT RID OF LOOP AND IT WORKED?????

// Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        c.close();


    }

//    private void handlePress2(TextField freq) throws LineUnavailableException{
//
//    }

    public static void main(String[] args) {

        launch();
    }
}