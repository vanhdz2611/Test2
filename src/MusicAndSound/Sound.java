package MusicAndSound;

import java.io.File;

import javafx.scene.media.Media;

public class Sound {
	
    private File song;
    private Media media;
    protected static boolean muted = false;

    public File getSong() {
        return this.song;
    }

    public void setSong(File song) {
        this.song = song;
    }

    public Media getMedia() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
    
    public static void mute() {
    	muted = true;
    }
    
    public static void unmute() {
    	muted = false;
    }

}
