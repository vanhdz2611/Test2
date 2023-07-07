package UI.Controller;

import java.io.IOException;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class SubPaneController extends MainPane{
    
    @FXML
    protected Button Return;
    
    @FXML
    protected Button Home;
    
	
	public void visualise() { //Move the pane up
		
		TranslateTransition t = new TranslateTransition();
		
		t.setByY(-1000);
		t.setDuration(Duration.seconds(0.5));
		t.setNode(main);
		
		t.play();
		
	}
	
	public void rturn(MouseEvent e) { //Move the pane down
		
		TranslateTransition t = new TranslateTransition();
		
		t.setByY(1000);
		t.setDuration(Duration.seconds(0.5));
		t.setNode(main);
		
		t.play();
		
	}
	
	public void home(MouseEvent e) throws IOException { // Back to Home pane

		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Resources/fxml/HomeScreen.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

		stage.getScene().setRoot(root);
		stage.show();

	}

}
