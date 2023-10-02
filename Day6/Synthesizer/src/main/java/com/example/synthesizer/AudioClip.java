package com.example.synthesizer;

import java.util.Arrays;

public class AudioClip {

    //duration
    static public double _duration = 2.0;

    static public int _sampleRate = 44100;

    static public byte[] audioClipArray = new byte[(int) (_duration*_sampleRate * 2)];


    public static int getSample (int index){
        //declaring variables
        byte b1 = 0;
        byte b2 = 0;
        int sample;

        //b1 and b2 are equal to these indexes in the audiocliparray
        b1 = audioClipArray[(index*2)+1];
        b2 = audioClipArray[(index*2)];

        //left shifting b1, it is now a short
        //(b1-0)
        b1 <<= 8;

        //or | b1 with b2 to combine them, creating a short
        //(b1-b2)
        sample = b1 | b2;

        //saved in sample int and returned, sets it to 4 bytes (0-0-b1-b2)
        return sample;
    }
 //0000-0000-0000-0000-0000-0000-0000-0000
    public static void setSample (int index, int value){
        audioClipArray[index * 2] = (byte) value;
        audioClipArray[index * 2 + 1] = (byte) (value >> 8);

    }

    public byte[] getData(){
        return Arrays.copyOf(audioClipArray, (int) (_duration * _sampleRate * 2));
    }

};


