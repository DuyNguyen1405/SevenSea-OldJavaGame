package gameUI;

import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */
public class AudioPlayerExample1 implements LineListener{
     
    /**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;
     
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     * @throws LineUnavailableException 
     */
    File audioFile;
    AudioInputStream audioStream;
    AudioFormat format;
    DataLine.Info info;
    Clip audioClip;
    
    public AudioPlayerExample1(String audioFilePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    	 audioFile = new File(audioFilePath);
    	 audioStream = AudioSystem.getAudioInputStream(audioFile);
    	 
         format = audioStream.getFormat();

         info = new DataLine.Info(Clip.class, format);

         audioClip = (Clip) AudioSystem.getLine(info);

         audioClip.addLineListener(this);

         audioClip.open(audioStream);
         
    }
    
    void loop() {

        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
         
    }
    
    void play() {
        audioClip.start();
        
        //Thread t = new Thread(this);
        while (!playCompleted) {
            // wait for the playback completes
            try {
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
         
        audioClip.close();
    }
    
    void stop() {
            audioClip.close();        
    }
     
    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            //System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
           // System.out.println("Playback completed.");
        }
 
    }
 

 
}