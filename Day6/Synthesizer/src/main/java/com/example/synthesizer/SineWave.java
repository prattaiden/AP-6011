package com.example.synthesizer;

public class SineWave implements AudioComponent {

    //Constructor  for sinewave
    SineWave(){
        double frequency = 0.0d;
        //amplitude
        //rate
    }
    double frequency;

    AudioClip getClip(){
        AudioClip clipTest = new AudioClip();
        for (int i = 0; i < clipTest.audioClipArray.length; i++){


            clipTest.audioClipArray[i] = (byte) ((byte) Short.MAX_VALUE * Math.sin
            (2 * Math.PI * frequency * i / clipTest._sampleRate));
        }
        return clipTest;
    }
        //sample[ i ] = maxValue * sine( 2*pi*frequency * i / sampleRate );

    }
    boolean hasInput(){
        return true;
    }

    void connectInput(AudioComponent input){

    }

}
