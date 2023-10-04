package com.example.synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SynthesizeApplication extends Application {

    int frequencySliderNum = 0;

    @Override
    public void start(Stage stage) throws IOException {


        //IMPORTANT FOR MAINLAYOUT
        //ANCHORMAIN
        AnchorPane anchorMain = new AnchorPane();

        //VBOX1
        VBox vBox1 = new VBox();
        //frequency label that will be used in vbox1
        Label frequencyLabel = new Label("Frequency");
        String cssLayout = "-fx-border-color: #000000;\n" +
                "-fx-border-insets: 0;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: line;\n" +
                "-fx-background-color: #9ae59a";
        vBox1.setStyle(cssLayout);

        VBox vBox2 = new VBox();
        vBox2.setStyle("-fx-background-color: #f8cad1");



        //adding things to anchormain and setting the style
        anchorMain.getChildren().add(vBox1);
        anchorMain.getChildren().add(vBox2);
        anchorMain.setStyle("-fx-padding: 30; -fx-background-color: #ccc7c7");

        //Text frequencyFieldBar = new Text("Enter Frequency:");
        //TextField frequencyfield = new TextField();

        //PLAY BUTTON with border pain
        Button playButton = new Button("PLAY");
//        playButton.relocate(450, 100);


        //SLIDER
        //minimum, maximum, default
        Slider freqSlider = new Slider(50, 400, 100);
        freqSlider.relocate(450, 250);

//        Button VolumeUpButton = new Button("Volume up");
//        Button VolumeDownButton = new Button ("Volume down");

        //after setting widget , add to the layout
        //ADDING TO THE VBOX
        vBox1.getChildren().add(freqSlider);
        vBox1.getChildren().add(frequencyLabel);
        //RELOCATING VBOX
        vBox1.relocate(80, 100);

        //ADDING PLAYBUTTON TO VBOX2
        vBox2.getChildren().add(playButton);
        //RELOCATING VBOX2
        vBox2.relocate(10, 10);

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
        
        freqSlider.setOnMouseDragged(e->handleFreqSlider(e, freqSlider, frequencyLabel));
    }

    private void handleFreqSlider(MouseEvent e, Slider freqSlider, Label frequencyLabel) {
        //give value of the slider
        //set the value of the label using the slider's value
        int result = (int)freqSlider.getValue();
        frequencyLabel.setText("frequency: " + result);
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