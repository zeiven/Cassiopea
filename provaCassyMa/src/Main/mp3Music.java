package Main;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public class mp3Music {

    private String filename;
    private static Player player;
    Thread playMusic;
    // constructor that takes the name of an MP3 file

    public mp3Music() {
    }

    // play the MP3 file to the sound card
    public void play(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }
    }

    public void start(String filename) {
        play(filename);
        playMusic = new Thread(new PlayMusic());
        playMusic.start();
    }

    public void stop() {
        close();
        playMusic = null;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

    class PlayMusic implements Runnable {

        public void run() {
            try {
                player.play();
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
//public static void main(String[] args){
//        
//        try {
//            mp3Music bm = new mp3Music("Fly.mp3");
//            bm.start();
//            Thread.sleep(10000);
//            bm.stop();
//        } catch (InterruptedException ex) {
//            System.out.println(ex);
//        }
//    
//}
}


