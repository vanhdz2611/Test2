package UI.Controller;

import MusicAndSound.HoveringSound;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public abstract class MainPane {
	
    @FXML
    protected AnchorPane main;
    
    public abstract void initialize();
    
	public void hovering(MouseEvent e) { //Make sound when mouse enter
		HoveringSound hs = new HoveringSound();
		hs.run();
	}

}
