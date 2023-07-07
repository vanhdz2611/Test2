package UI.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import GameManager.GameManager;
import MusicAndSound.BackgroundMusic;
import javafx.scene.control.Slider;

public class Load {
	
	public Load(GameManager gm) throws FileNotFoundException {
		
		File file = new File("src/Resources/GameState/gamestate.txt");
		
		try (Scanner reader = new Scanner(file)) {
			
			if (!gm.isTurnPlayer0() == reader.nextBoolean()) {
				gm.changeTurn();
			}
			
			if (!gm.hasMandarin0() == reader.nextBoolean()) {
				gm.changeMandarin0();
			}
			
			if (!gm.hasMandarin6() == reader.nextBoolean()) {
				gm.changeMandarin6();
			}
			
			int[] CellGems = new int[12];
			for (int i = 0; i < 12; i++) {
				CellGems[i] = reader.nextInt();
			}
			gm.set(CellGems);
			
			gm.setScore0(reader.nextInt());
			gm.setScore1(reader.nextInt());
			
		}
	}
	
	public Load(Slider Music, Slider SFX) throws FileNotFoundException {
		
		File file = new File("src/Resources/GameState/setting.txt");
		
		try (Scanner reader = new Scanner(file)) {
			
			Double d = reader.nextDouble();
			Music.setValue(d);
			
			d = reader.nextDouble();
			SFX.setValue(d);
			
			BackgroundMusic.setVolume(d);
		}
	}

}