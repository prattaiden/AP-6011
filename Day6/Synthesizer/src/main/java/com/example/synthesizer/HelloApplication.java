package com.example.synthesizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

//        VBox MainLayout = new VBox();
//        MainLayout.setStyle("-fx-padding: 20; -fx-background-color: #9adcf1");
//        Label numLabel1 = new Label("Number1");
//        Label numLabel2 = new Label("Number2");
//        TextField text1 = new TextField();
//        TextField text2 = new TextField();
//        TextField result = new TextField();
//        Button submitButton = new Button("Submit");
//        submitButton.setOnAction(e->handlePress(e, numLabel1, numLabel2, result);
//        //setting an action, calling a function "handlePress" will calculate sum of numbers
//
//        MainLayout.getChildren().add(numLabel1);
//        MainLayout.getChildren().add(text1);
//
//        MainLayout.getChildren().add(numLabel2);
//        MainLayout.getChildren().add(text2);
//
//        MainLayout.getChildren().add(submitButton);
//        MainLayout.getChildren().add(result);
//
//
//        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(MainLayout, 320, 240);
//        stage.setTitle("Synthesizer");
//        stage.setScene(scene);
//        stage.show();
    }

    private void handlePress(Object e, Label numLabel1, Label numLabel2, TextField result) {
        //add the values in number1 and number2
        int value = Integer.parseInt(numLabel1.getText() + numLabel2.getText());
        result.setText(String.valueOf(value));

    }

    public static void main(String[] args) {
        launch();
    }
}