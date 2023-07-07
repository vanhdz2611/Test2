package GameManager;

import java.util.Random;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class Gem extends Circle {
	
	protected static AnchorPane main;
	
	public Gem() {
		bind();
	}
	
	public static void main(AnchorPane p) {
		main = p;
	}
	
	public void bind() {
		this.getStyleClass().add("Gem");
		Random rand = new Random();
		Double valueX = 0.2 + (0.8 - 0.2) * rand.nextDouble();
		this.centerXProperty().bind(main.widthProperty().divide(8).multiply(valueX));
		Double valueY = 0.2 + (0.8 - 0.2) * rand.nextDouble();
		this.centerYProperty().bind(main.heightProperty().divide(4.5).multiply(valueY));
		
		this.radiusProperty().bind(main.widthProperty().divide(80));
	}
	
}
