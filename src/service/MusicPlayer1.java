package service;

import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer1 extends Thread {
    public void run(){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(".\\images\\back2.wav"));
            AudioFormat audioFormat = audioInputStream.getFormat();
            final SourceDataLine sourceDataLine;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class,audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            int nByte = 0;
            final int SIZE = 1024 * 64;
            byte[] buffer = new byte[SIZE];
            while(nByte != -1){
                nByte = audioInputStream.read(buffer,0,SIZE);
                sourceDataLine.write(buffer,0,nByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
