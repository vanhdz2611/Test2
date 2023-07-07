package UI.Controller;

import java.io.File;
import java.io.IOException;

import GameManager.GameManager;
import UI.Utils.Language;
import UI.Utils.Resize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class WinScreenController extends SubPaneController {
	
    @FXML
    private Label Declare;
    
    @FXML
    private Label Player;
    
    @FXML
    private Label Of;
    
    @FXML
    private Label Score;
	
    @FXML
    private Button Again;
    
	public void initialize() {
		
		Resize.bind(new Region[] {Again, Home}, main);
		Resize.bind(new Region[] {Declare, Player, Of, Score}, main, true);
		
		Language.bindLang(new Labeled[] {Again, Home, Declare, Player, Of}, main);
		
	}
	
	public void addScore(String str) { //Add the current score to the "Score" Label
		Score.setText(str);
	}
	
	public void start(MouseEvent e) throws IOException { //Start a new game
		
		GameManager gm = new GameManager();
		
		GameScreenController GSController = new GameScreenController(gm);
		
		String name = "GameScreen";
		FXMLLoader fxml = new FXMLLoader(getClass().getClassLoader().getResource("Resources/fxml/" + name + ".fxml"));

		fxml.setController(GSController);
		
		Parent root = fxml.load();
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.getScene().setRoot(root);
		stage.show();
		
	}
	
	public void home(MouseEvent e) throws IOException { //Back to Home pane
		
		File file = new File("src/Resources/GameState/gamestate.txt");
		file.delete();
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Resources/fxml/HomeScreen.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.getScene().setRoot(root);
		stage.show();
		
	}
    
}
