package com.example.synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.awt.*;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.Node;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.border.TitledBorder;

public class SynthesizeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       //IMPORTANT FOR MAINLAYOUT
        VBox MainLayout = new VBox();
        MainLayout.setStyle("-fx-padding: 40; -fx-background-color: rgba(184,199,44,0.31)");


        //borderPane
        BorderPane borderp = new BorderPane();
        Text atext = new Text("Frequency:");
        TextField frequencyfield = new TextField();

        //PLAY BUTTON with border pain
       // Button playButton = new Button("PLAY");
        Button playButton = new Button("PLAY");

        //THIS SHOULD SET THE PLAYBUTTON TO CENTER BUT IT ISNT?
        //borderp.setCenter(playButton);



        Button VolumeUpButton = new Button("Volume up");
        borderp.setRight(VolumeUpButton);
        Button VolumeDownButton = new Button ("Volume down");
        borderp.setLeft(VolumeDownButton);




       //Clip clipTest = AudioSystem.getClip();

        //setting an action, calling a function "handlePress"
        playButton.setOnAction(e-> {
            try {
                handlePress(frequencyfield);
            } catch (LineUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });



        //after setting widgest , add to the layout
        MainLayout.getChildren().add(playButton);
        MainLayout.getChildren().add(atext);
        MainLayout.getChildren().add(frequencyfield);

        //for text-----
       // MainLayout.getChildren().add(atext);

        MainLayout.getChildren().add(VolumeDownButton);
        MainLayout.getChildren().add(VolumeUpButton);


        Scene scene = new Scene(MainLayout, 400, 240);
        stage.setTitle("Synthesizer");
        stage.setScene(scene);
        stage.show();
    }

    private void handlePress(TextField freq) throws LineUnavailableException {
        //add the values in number1 and number2
        int x;

        x = Integer.parseInt(freq.getText());


        AudioComponent gen = new SquareWave(x);
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

    public static void main(String[] args) {

        launch();
    }
}