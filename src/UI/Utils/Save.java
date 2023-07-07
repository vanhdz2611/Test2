package UI.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import GameManager.GameManager;
import javafx.scene.control.Slider;

public class Save {
	
	public Save(Slider Music, Slider SFX) {
		
		File file = new File("src/Resources/GameState/setting.txt");
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
			
		FileWriter writer = new FileWriter(file);
		
		writer.write(String.valueOf(Music.getValue()) + " ");
		writer.write(String.valueOf(SFX.getValue()) + " ");
		
		writer.close();
		} catch (IOException e) {
		}
	}
	
	public Save(GameManager gm) {
		
		File file = new File("src/Resources/GameState/gamestate.txt");
		
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			FileWriter writer = new FileWriter(file);
			
			writer.write(String.valueOf(gm.isTurnPlayer0() + " "));
			
			writer.write(String.valueOf(gm.hasMandarin0() + " "));
			writer.write(String.valueOf(gm.hasMandarin6() + " "));
			
			writer.write(String.valueOf(gm.getCellIGems()[0] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[1] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[2] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[3] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[4] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[5] + " "));
			
			writer.write(String.valueOf(gm.getCellIGems()[6] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[7] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[8] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[9] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[10] + " "));
			writer.write(String.valueOf(gm.getCellIGems()[11] + " "));
			
			writer.write(String.valueOf(gm.getScore0().intValue() + " "));
			writer.write(String.valueOf(gm.getScore1().intValue() + " "));

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
