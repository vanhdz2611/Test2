package MusicAndSound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class BackgroundMusic implements Runnable {
	
    private File song;
	private Media media;
	private static MediaPlayer mediaPlayer;

    @Override
    public void run() {
        try {
        song = new File("src/Resources/MusicAndSound/Wind.wav");

		media = new Media(song.toURI().toString());
		
        System.out.println(song);
		
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
              mediaPlayer.seek(Duration.ZERO);
            }
        });
		mediaPlayer.play();
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        
        }
        
    }


    public static void setVolume(double d) {
        BackgroundMusic.mediaPlayer.setVolume(d/100);
    }
}
