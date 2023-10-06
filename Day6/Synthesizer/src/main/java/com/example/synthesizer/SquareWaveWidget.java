package com.example.synthesizer;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class SquareWaveWidget extends AudioComponentWidgetBase {
    public SquareWaveWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);

        //WIDGETLABEL
        Label titlelabel = new Label("SQAUREWAVE");
        titlePanel_.getChildren().add(titlelabel);

        //SLIDER
        Slider squareFreqSlider = new Slider(100, 800, 450);
        Label freqLabel = new Label("Frequency: ");
        squareFreqSlider.setOnMouseDragged(e->handleFrequencySlider(e, squareFreqSlider, freqLabel));
        sliderPanel_.getChildren().add(freqLabel);
        sliderPanel_.getChildren().add(squareFreqSlider);



    }

    private void handleFrequencySlider(MouseEvent e, Slider freqSlider, Label Flabel) {
        AudioComponent ac1 = getAudioComponent();
        int result = (int)freqSlider.getValue();
        Flabel.setText("Frequency: " + result);
        ((SquareWave)ac1).updateSquareFrequency(result);

    }
}

