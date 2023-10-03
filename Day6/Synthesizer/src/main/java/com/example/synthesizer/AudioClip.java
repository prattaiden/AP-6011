package com.example.synthesizer;

import java.util.Arrays;

public class AudioClip {

    //duration
    static public double _duration = 2.0;

    static public int _sampleRate = 44100;

    public byte[] audioClipArray;
    public static final int TOTAL_SAMPLES = ((int) (_duration * _sampleRate));


    public AudioClip(){
        audioClipArray = new byte[(int) ((_duration*_sampleRate) * 2)];

    }

    public int getSample (int index){

        byte b1 = audioClipArray[index*2];
        byte b2 = audioClipArray[index*2 +1];

        int result = 0;

//        System.out.println("Get the first byte : " + Integer.toBinaryString(b2 << 8));
//        System.out.println("Get the second byte: " + Integer.toBinaryString(b1));

        result = b2<<8 | (b1 & 0xFF);
        return result;

    }


        //0000-0000-0000-0000-0000-0000-0000-0000
    public void setSample (int index, int value){
        audioClipArray[index * 2] = (byte) value;
        audioClipArray[index * 2 + 1] = (byte) (value >> 8);

    }

    public byte[] getData(){
        return Arrays.copyOf(audioClipArray, (int) (_duration * _sampleRate * 2));
    }

};


