package com.example.synthesizer;

import java.util.ArrayList;

public class Mixer implements AudioComponent {

    ArrayList<AudioClip> input_ = new ArrayList<AudioClip>();

    @Override
    public AudioClip getClip() {
        AudioClip result = new AudioClip();
        //loop through array of waves
        for(AudioClip wave : input_){
            //for each wave through the wave
            for(int i = 0; i < AudioClip.TOTAL_SAMPLES; i++){
                //Add sample to result audio-clip (to add waves you add corresponding amplitudes)
                int sample = (int)(result.getSample(i) + wave.getSample(i));
                //Ensure the adjusted Sample value stays within the valid range (clamping the sound)
                int max = Short.MAX_VALUE;
                int min = Short.MIN_VALUE;

                if(sample < min){
                   sample = min;
                }
                else if(sample > max){
                    sample = max;
                }
                result.setSample(i, sample);
            }
        }
    return result;
    }

    @Override
    public boolean hasInput() {
        return true;
    }

    @Override
    public void connectInput(AudioComponent input) {

        VolumeAdjuster lowerVolume = new VolumeAdjuster(.5);
        lowerVolume.connectInput(input);

        input_.add(lowerVolume.getClip());

    }
}
