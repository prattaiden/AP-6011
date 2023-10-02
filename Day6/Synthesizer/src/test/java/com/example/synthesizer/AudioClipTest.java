package com.example.synthesizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AudioClipTest {



    @Test
    public void Tests(){
        int[] test = new int[];
        Random random = new Random();
        for (int i = 0; i < test.length; i++) {
            test[i] = (random.nextInt(32767));

            AudioClip clip = new AudioClip();
            Assertions.assertEquals(clip.getSample(test[i]));
        }



    }





    @Test
    void getSample() {


    }

    @Test
    void setSample() {
    }

    @Test
    void getData() {
    }
}