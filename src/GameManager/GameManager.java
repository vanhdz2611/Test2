package GameManager;

import java.util.Arrays;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameManager{

	private boolean turnPlayer0 = true;
	
	private BooleanProperty end = new SimpleBooleanProperty(false);

	private boolean mandarin0 = true;
	private boolean mandarin6 = true;
	
	private final String[] cellID = new String[] {"00", "01", "02", "03", "04", "05", "66", "15", "14", "13", "12", "11"};
	private int[] cellGems = new int[] {1, 5, 5, 5, 5, 5, 1, 5, 5, 5, 5, 5};
	
	private IntegerProperty Player0 = new SimpleIntegerProperty(0);
	private IntegerProperty Player1 = new SimpleIntegerProperty(0);
	
	public boolean isTurnPlayer0() {
		return turnPlayer0;
	}
	
	public void changeTurn() {
		this.turnPlayer0 = !turnPlayer0;
	}
	
	public BooleanProperty getEnd() {
		return end;
	}
	
	public void Ended() {
		this.end.set(true);
	}
	
	public boolean hasMandarin0() {
		return mandarin0;
	}
	
	public void changeMandarin0() {
		mandarin0 = !mandarin0;
	}
	
	public boolean hasMandarin6() {
		return mandarin6;
	}
	
	public void changeMandarin6() {
		mandarin6 = !mandarin6;
	}
	
	public String[] getCellID() {
		return cellID;
	}
	
	public int[] getCellIGems() {
		return cellGems;
	}
	
	public void add(String id) {
		int index = Arrays.asList(cellID).indexOf(id);
		
		cellGems[index] = cellGems[index] + 1;
	}
	
	public void rmv(String id) {
		int index = Arrays.asList(cellID).indexOf(id);
		
		cellGems[index] = 0;
	}
	
	public void set(int[] cellGems) {
		this.cellGems = cellGems;
	}
	
	public void addScores(boolean side0, int i) {
		if (side0) {
			Player0.set(Player0.intValue() + i);
		}
		else {
			Player1.set(Player1.intValue() + i);
		}
	}
	
	public IntegerProperty getScore0() {
		return Player0;
	}
	
	public IntegerProperty getScore1() {
		return Player1;
	}
	
	public void setScore0(int i) {
		Player0.set(i);
	}
	
	public void setScore1(int i) {
		Player1.set(i);
	}
}

