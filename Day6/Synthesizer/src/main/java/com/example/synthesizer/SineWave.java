package com.example.synthesizer;

public class SineWave implements AudioComponent {

    double frequency;
    double sampleRate = 44100;

    //Constructor  for sinewave
    public SineWave(double freq){
        this.frequency=freq;

    }

    public AudioClip getClip(){
        AudioClip clipTest = new AudioClip();
        int result = 0;
        //set array of bytes
        for (int i = 0; i < AudioClip.TOTAL_SAMPLES; i++){
            //fill the array with the formula
            result = (int) (Short.MAX_VALUE * Math.sin(2 * Math.PI * frequency * i / sampleRate));
                    clipTest.setSample(i, result);
        }

        return clipTest;
    }
        //sample[ i ] = maxValue * sine( 2*pi*frequency * i / sampleRate );


    @Override
    public boolean hasInput(){
        return true;
    }

    public void connectInput(AudioComponent input){

        //if my array has input, then set has input to true
    }

}
