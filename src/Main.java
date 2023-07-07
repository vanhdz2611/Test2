import java.io.IOException;

import MusicAndSound.BackgroundMusic;
import UI.Utils.Language;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws IOException {
		
		Language.setLang("en");
		
		BackgroundMusic bgm = new BackgroundMusic();
		bgm.run();

		String name = "HomeScreen";
		FXMLLoader fxml = new FXMLLoader(getClass().getClassLoader().getResource("Resources/fxml/" + name + ".fxml"));
		
		Parent root = fxml.load();

		Scene scene = new Scene(root, 800d, 450d);
		scene.getStylesheets().add("Resources/Style/Style.css");

		stage.setScene(scene);
		stage.setMinWidth(800);
		stage.setMinHeight(450);
		stage.setTitle("O An Quan");
		stage.getIcons().add(new Image("Resources/Image/icon.png"));
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}


}
