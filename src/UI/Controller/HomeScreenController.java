package UI.Controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import GameManager.GameManager;
import UI.Utils.Language;
import UI.Utils.Load;
import UI.Utils.Resize;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Labeled;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class HomeScreenController extends MainPane{
	
	private GameManager gm = new GameManager();
	
	@FXML
	private Pane background;
	
	@FXML
	private Button Start;
	
	@FXML
	private Button Load;
	
	@FXML
	private Button Setting;
	
	@FXML
	private Button Exit;
	
	@FXML
	private AnchorPane settingPane;
	
	@FXML
	private SettingScreenController SSController;
	
	public void initialize() {
		
		try {
			new Load(gm);
		} catch (FileNotFoundException e) {
			Load.setDisable(true);
		}
		
		Resize.bind(new Region[] {Start, Load, Setting, Exit, background, settingPane}, main);
		
		Language.bindLang(new Labeled[] {Start, Load, Setting, Exit}, main);
		
	}
	
	public void start(MouseEvent e) throws IOException { //Start a new game
		
		gm = new GameManager();
		load(e);
		
	}
	
	public void load(MouseEvent e) throws IOException { //Load existed game
		
		GameScreenController gsc = new GameScreenController(gm);
		String name = "GameScreen";
		FXMLLoader fxml = new FXMLLoader(getClass().getClassLoader().getResource("Resources/fxml/" + name + ".fxml"));

		fxml.setController(gsc);
		
		Parent root = fxml.load();
		
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		
		stage.getScene().setRoot(root);
		stage.show();
		
	}
	
	public void setting(MouseEvent e) { //Show the Setting pane
		SSController.visualise();
	}
	
	public void exit(MouseEvent e) { //Terminate the application
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setHeaderText("Are you sure that you want to exit the game?");
		DialogPane dp = alert.getDialogPane();
		dp.getStylesheets().add("Resources/Style/Style.css");
		if(alert.showAndWait().get() == ButtonType.OK) {
			System.exit(0);
		}
	}
	
}