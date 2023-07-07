package UI.Controller;

import GameManager.GameManager;
import GameManager.Gem;
import GameManager.MandarinGem;
import GameManager.Moves;
import UI.Utils.Language;
import UI.Utils.Resize;
import UI.Utils.Save;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class GameScreenController extends MainPane{
	
	private GameManager gm;
	
    private BooleanProperty end = new SimpleBooleanProperty(true);
	
	public GameScreenController(GameManager GM) {
		this.gm = GM;
	}
    
    @FXML
    private AnchorPane Top;
    
    @FXML
    private AnchorPane Bottom;
    
    @FXML
    private Pane Left;
    
    @FXML
    private Pane Right;
    
    @FXML
    private Label Player0;
    
    @FXML
    private Label Player0Of;
    
    @FXML
    private Label Player0Score;
    
    @FXML
    private HBox P0;
    
    @FXML
    private Label Player1;
    
    @FXML
    private Label Player1Of;
    
    @FXML
    private Label Player1Score;
    
    @FXML
    private HBox P1;
    
    @FXML
    private Label Turn;
    
    @FXML
    private HBox P2;
    
    @FXML
    private Button Setting;
    
    @FXML
    private Label Gems;
    
    @FXML
    private AnchorPane settingPane;
    
    @FXML
    private AnchorPane winPane0;
    
    @FXML
    private AnchorPane winPane1;
    
	@FXML
	private SettingScreenController SSController;
    
    @FXML
    private WinScreenController WS0Controller;
    
    @FXML
    private WinScreenController WS1Controller;
    
	public void initialize() {
				
		for (int i = 0; i < 12; i++) {
			String id = gm.getCellID()[i];
			int gems = gm.getCellIGems()[i];
			Pane p = (Pane) main.lookup("#" + id);
			
			Gem.main(main);
			
			if (id.equals("00")) {
				if (gm.hasMandarin0()) {
					p.getChildren().add(new MandarinGem());
					for (int n = 0; n < gems - 1; n++) {
						p.getChildren().add(new Gem());
					}
				}
				else {
					for (int n = 0; n < gems; n++) {
						p.getChildren().add(new Gem());
					}
				}
			}
			else if (id.equals("66")) {
				if (gm.hasMandarin6()) {
					p.getChildren().add(new MandarinGem());
					for (int n = 0; n < gems - 1; n++) {
						p.getChildren().add(new Gem());
					}
				}
				else {
					for (int n = 0; n < gems; n++) {
						p.getChildren().add(new Gem());
					}
				}
			}
			else {
				for (int n = 0; n < gems; n++) {
					p.getChildren().add(new Gem());
				}
			}
		}
		
		Player0Score.textProperty().bind(gm.getScore0().asString());
		Player1Score.textProperty().bind(gm.getScore1().asString());
		
		end.bind(gm.getEnd());
		end.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (gm.getScore0().intValue() >= gm.getScore1().intValue()) {
					WS0Controller.visualise();	
					WS0Controller.addScore(Integer.toString(gm.getScore0().intValue()));
				}
				else {
					WS1Controller.visualise();	
					WS1Controller.addScore(Integer.toString(gm.getScore1().intValue()));
				}
			}
		});
		
		Resize.bind(new Region[] {Setting, Top, Bottom, Left, Right, P0, P1, settingPane, winPane0, winPane1, Player0, Player0Of, Player0Score, Player1, Player1Of, Player1Score, Gems}, main);
		
		Language.bindLang(new Labeled[] {Setting, Player0, Player0Of, Player1, Player1Of}, main);

	}
	
	public void setting(MouseEvent e) { //Show the Setting pane
		
		new Save(gm);

		SSController.visualise();
	}
	
	public void enter (MouseEvent e) { //Show the number of gems inside
		Gems.setText(Integer.toString(((Pane) e.getSource()).getChildren().size()));
		if (gm.isTurnPlayer0()) {
			Turn.setText("Turn0");
		}
		else {
			Turn.setText("Turn1");
		}
	}
	
	public void exit (MouseEvent e) { //Show to zero
		Gems.setText("00");
		if (gm.isTurnPlayer0()) {
			Turn.setText("Turn0");
		}
		else {
			Turn.setText("Turn1");
		}
	}
	
	public void detect (MouseEvent e) {
		
		String id = (((Node) e.getSource()).getId());
		
		Pane p = (Pane) e.getSource();
		
		if (gm.isTurnPlayer0()) { //If turn player 0
			
			if (id.charAt(0) == '0') {
				
	        	Dragboard db = p.startDragAndDrop(TransferMode.LINK);
	        	ClipboardContent content = new ClipboardContent();
	        	content.putString(p.getId());
	        	
	        	db.setDragView(p.snapshot(null, null), p.getWidth()/2, p.getHeight()/2);
	        	db.setContent(content);
	        	
			}
		}
		
		else { //If turn player 1
			
			if (id.charAt(0) == '1') {
				
	        	Dragboard db = p.startDragAndDrop(TransferMode.LINK);
	        	ClipboardContent content = new ClipboardContent();
	        	content.putString(p.getId());
	        	
	        	db.setDragView(p.snapshot(null, null), p.getWidth()/2, p.getHeight()/2);
	        	db.setContent(content);
	        	
			}
		}
	}
	
	public void over (DragEvent e) {
		String start = (((Node) e.getGestureSource()).getId());
		String end = (((Node) e.getSource()).getId());
		if (end.charAt(1) == '0') { //If move to the BoardEnd 00
			if (start.charAt(1) == '1') {
				e.acceptTransferModes(TransferMode.ANY);
			}
		}
		else if (end.charAt(1) == '6') { //If move to the BoardEnd 66
			if (start.charAt(1) == '5') {
				e.acceptTransferModes(TransferMode.ANY);
			}
		}
		else if (end.charAt(0) == start.charAt(0)) { //If move within board side
			if (end.charAt(1) == (char) (start.charAt(1) + 1) || end.charAt(1) == (char) (start.charAt(1) - 1)) {
				e.acceptTransferModes(TransferMode.ANY);
			}
		}
	}
	
	public void drop (DragEvent e) {
		
		Pane ps = (Pane) e.getGestureSource();
		String start = ps.getId();
		Pane pe = (Pane) e.getSource();
		String end = pe.getId();
		
		int gems = ps.getChildren().size();
		
		boolean clockwise = true;
		if (start.charAt(0) == '0' && end.charAt(1) == (char) (start.charAt(1) - 1)) {
			clockwise = false;
		}
		
		if (start.charAt(0) == '1' && end.charAt(1) == (char) (start.charAt(1) + 1)) {
			clockwise = false;
		}

		
		if (gems > 0) {
			Moves m = new Moves(ps, clockwise, gems, gm);
			m.Side(pe);
		}

		e.setDropCompleted(true);
		e.consume();
	}
}
