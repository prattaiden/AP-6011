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

    //NOTES FROM DAVE
    //BUTTON TO CREATE WIDGET IN THE PAGE
    //component called mixer
    //has input port and output port
    @Override
    public void start(Stage stage) throws IOException {

        //NEW IN CLASS


        AnchorPane MainLayout2 = new AnchorPane();
        VBox frequencybox = new VBox();
        Label frequencyLabel = new Label("SqaureWave");

        MainLayout2.getChildren().add(frequencybox);
        MainLayout2.setStyle("-fx-padding:10; -fx-background-color: White ");




        frequencyLabel.setStyle("-fx-backround-color: Blue");


        frequencybox.getChildren().add(frequencyLabel);
        frequencyLabel.relocate(50, 20);

        //SLIDER
        //minimum, maximum, default
        Slider freqSlider = new Slider(50, 400, 100);
        freqSlider.relocate(70, 120);
        frequencybox.getChildren().add(freqSlider);
        freqSlider.setOnMouseDragged(e->handleSlider(e, freqSlider, frequencyLabel));

        MainLayout2.getChildren().add(frequencyLabel);


        //IMPORTANT FOR MAINLAYOUT
        //VBox MainLayout = new VBox()
        // MainLayout.setStyle("-fx-padding: 30; -fx-background-color: rgba(35,122,13,0.43)");

        //borderPane
        BorderPane borderp = new BorderPane();
        Text frequencyFieldBar = new Text("Enter Frequency:");
        TextField frequencyfield = new TextField();

        //PLAY BUTTON with border pain
        Button playButton = new Button("PLAY");
        borderp.setCenter(playButton);

        //THIS SHOULD SET THE PLAYBUTTON TO CENTER BUT IT ISNT?
        //borderp.setCenter(playButton);



//        Button VolumeUpButton = new Button("Volume up");
//        borderp.setLeft(VolumeUpButton);
//        Button VolumeDownButton = new Button ("Volume down");
//        borderp.setRight(VolumeDownButton);




        //Clip clipTest = AudioSystem.getClip();

        //setting an action, calling a function "handlePress"
        playButton.setOnAction(e-> {
            try {
                handlePlayPress(frequencyfield);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });



        //after setting widgest , add to the layout

        // MainLayout.getChildren().add(frequencyFieldBar);
        // MainLayout.getChildren().add(frequencyfield);
        playButton.relocate(100, 50);
        frequencybox.getChildren().add(playButton);

        // MainLayout2.getChildren().add(borderp);

        //MainLayout.getChildren().add(VolumeUpButton);
        //MainLayout.getChildren().add(VolumeDownButton);



        Scene scene = new Scene(MainLayout2, 400, 200);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

    private void handleSlider(MouseEvent e, Slider freqSlider, Label frequencyLabel) {
        //give value of the slider
        //set the value of the label using the slider's value
        int result = (int)freqSlider.getValue();
        frequencyLabel.setText("SineWave" + result + "Hz");

    }

    private void handlePlayPress(TextField freq) throws LineUnavailableException {
        //add the values in number1 and number2
        int x = Integer.parseInt(freq.getText());


        AudioComponent gen = new SquareWave(200);
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