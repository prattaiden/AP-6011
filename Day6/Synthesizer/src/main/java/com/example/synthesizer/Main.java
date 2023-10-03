package com.example.synthesizer;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;


public class Main {
    public static void main(String[] args) throws LineUnavailableException {
        // Get properties from the system about samples rates, etc.
// AudioSystem is a class from the Java standard library.
        Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.

// This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
        AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

        //making a new sinewave of type audiocomponent
        AudioComponent gen = new SineWave(300); // Your code
        AudioComponent gen2 = new SineWave(100);
        AudioComponent gen3 = new SineWave(400);

        Mixer mixewaves = new Mixer();
        mixewaves.connectInput(gen2);
        mixewaves.connectInput(gen3);

        AudioClip clip = mixewaves.getClip();

        //AudioClip clip = gen.getClip();         // Your code

        //VOLUME
//        VolumeAdjuster lowerVolume = new VolumeAdjuster( .3);
//
//        lowerVolume.connectInput(gen);
//
//        AudioClip clip = lowerVolume.getClip();


        c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

        System.out.println( "About to play..." );
        c.start(); // Plays it.
        c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total

// Makes sure the program doesn't quit before the sound plays.
        while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
            // Do nothing while we wait for the note to play.
        }

        System.out.println( "Done." );
        c.close();



    }

}