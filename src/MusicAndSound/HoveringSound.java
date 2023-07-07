package MusicAndSound;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HoveringSound extends Sound implements Runnable {

    private static MediaPlayer mediaPlayer;
    @Override
    public void run() {
        try {
        	if(!muted) {
        		
        		this.setSong(new File("src/Resources/MusicAndSound/hovering.wav"));

				this.setMedia(new Media(this.getSong().toURI().toString()));
		
        		System.out.println(this.getSong());
		
        		HoveringSound.mediaPlayer = new MediaPlayer(this.getMedia());
        
        
				HoveringSound.mediaPlayer.play();
		
        	}

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        
        }
        
    }


    public static void setVolume(double d) {
        HoveringSound.mediaPlayer.setVolume(d/100);
    }

}