package com.example.synthesizer;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MixerWidget extends AudioComponentWidgetBase{
    public MixerWidget(AudioComponent ac, AnchorPane parent) {
        super(ac, parent);

        //WIDGETLABEL
        Label titlelabel = new Label("SINEWAVE");
        sliderPanel_.getChildren().add(titlelabel);

        //SLIDER
        Slider SinefreqSlider = new Slider(100, 800, 450);
        Label freqLabel = new Label("Frequency: 450");
        SinefreqSlider.setOnMouseDragged(e->handleFrequencySlider(e, SinefreqSlider, freqLabel));
        sliderPanel_.getChildren().add(freqLabel);
        sliderPanel_.getChildren().add(SinefreqSlider);



    }
    private void handleFrequencySlider(MouseEvent e, Slider freqSlider, Label Flabel) {
        AudioComponent ac1 = getAudioComponent();
        int result = (int)freqSlider.getValue();
        Flabel.setText("Frequency: " + result);
        ((SineWave)ac1).updateSineFrequency(result);

    }



}
