package UI.Utils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class Resize {
	
	public static void bind(Region[] R, Pane main) {
		
		for (int i = 0; i < R.length; i++) {
			
			Region r = R[i];
			
			r.prefWidthProperty().bind(main.widthProperty().divide(800/r.getPrefWidth()));
			r.prefHeightProperty().bind(main.heightProperty().divide(450/r.getPrefHeight()));
			
			if (r instanceof Button) {
				r.widthProperty().addListener(event -> r.setStyle("-fx-font-size: " + r.getPrefWidth()/8));
			}
			
			if (r instanceof Label) {
				r.widthProperty().addListener(event -> r.setStyle("-fx-font-size: " + r.getPrefWidth()/6));
			}
		}
	}
	
	public static void bind(Region[] R, Pane main, boolean Big) {
		
		for (int i=0; i < R.length; i++) {
			
			Region r = R[i];
			
			r.prefWidthProperty().bind(main.widthProperty().divide(800/r.getPrefWidth()));
			r.prefHeightProperty().bind(main.heightProperty().divide(450/r.getPrefHeight()));
			
			if (!Big) {
				r.widthProperty().addListener(event -> r.setStyle("-fx-font-size: " + r.getPrefWidth()/45));
				((Labeled) r).setWrapText(true);
			}
			else {
				r.widthProperty().addListener(event -> r.setStyle("-fx-font-size: " + r.getPrefWidth()/15));
			}
		}
	}
}
