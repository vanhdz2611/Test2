package UI.Controller;

import UI.Utils.Language;
import UI.Utils.Resize;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Region;

public class HelpCreditScreenController extends SubPaneController {
    
    @FXML
    private Label Text;
    
	public void initialize() {
		
		Resize.bind(new Region[] {Return}, main);
		Resize.bind(new Region[] {Text}, main, false);
		
		Language.bindLang(new Labeled[] {Return, Text}, main);
		
	}
}
