package com.example.synthesizer;

import com.example.synthesizer.AudioClip;
import com.example.synthesizer.AudioComponent;

public class VolumeAdjuster implements AudioComponent {

    private AudioComponent input_;
    public double Vscale;

    //constructor
    public VolumeAdjuster(double scale){
        Vscale = scale;

    //AudioClip adjustedSample = new AudioClip();


    }

    @Override
    public AudioClip getClip() {

        AudioClip original = input_.getClip();

        AudioClip result =  new AudioClip();

        for(int i = 0; i < AudioClip.TOTAL_SAMPLES; i++){
            int adjustedSample = (int)(Vscale * original.getSample(i));
            int max = Short.MAX_VALUE;
            int min = Short.MIN_VALUE;

            if(adjustedSample < min){
                adjustedSample = min;
            }
            else if(adjustedSample > max){
                adjustedSample = max;
            }
            result.setSample(i, adjustedSample);

        }

        return result;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        input_ = input;

    }
}

