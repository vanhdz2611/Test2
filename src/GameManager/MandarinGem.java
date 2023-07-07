package GameManager;

public class MandarinGem extends Gem {
	
	public MandarinGem() {
		super();
	}

	public void bind() {
		this.getStyleClass().add("Gem");
		this.centerXProperty().bind(main.widthProperty().divide(16));
		this.centerYProperty().bind(main.heightProperty().divide(4.5));
		
		this.radiusProperty().bind(main.widthProperty().divide(40));
	}

}
