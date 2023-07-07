package UI.Utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;

public class Language {
	
	private static ObjectProperty<ResourceBundle> resources = new SimpleObjectProperty<>();
	
	public static void setLang(String lang) {
		
		Locale language = new Locale(lang);
		
		ResourceBundle rb = ResourceBundle.getBundle("Resources/Bundle/lang", language);
		
		resources.set(rb);
		
	}
	
	public static void bindLang(Labeled[] L, Pane main) {
		
		for (int i = 0; i < L.length; i++) {
			
			Labeled l = L[i];
			String str = ((Labeled) l).getText();
			
			l.textProperty().bind(new StringBinding() {	
				
				{ bind(resources); }
				
				@Override
				protected String computeValue() {
					
					resources.get().getString(str);
					return resources.get().getString(str);
					
				}
			});
		}
	}
	
}
