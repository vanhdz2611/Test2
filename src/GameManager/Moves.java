package GameManager;

import java.util.Arrays;

import MusicAndSound.GemSound;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Moves {
	
	private Pane ps;
	private double x;
	private double y;
	private boolean clockwise;
	private int gems;
	private GameManager gm;
	
	public Moves(Pane ps, boolean clockwise, int gems, GameManager gm) {
		this.ps = ps;
		this.x = ps.getWidth();
		this.y = ps.getHeight();
		this.clockwise = clockwise;
		this.gems = gems;
		this.gm = gm;
		gm.rmv(ps.getId());
	}
	
	public void Side(Pane pe) { //Move gems to the left or right
		ps.getParent().toFront();
		String start = ps.getId();
		String end = pe.getId();
		double nx = 0;
		double ny = 0;
		
		nx = end.charAt(1) - start.charAt(1);
		if (end.charAt(1) == '0' || end.charAt(1) == '6') { //If move to the BoardEnd
			if (start.charAt(0) == '0') {
				ny = 0.5;
			}
			else if(start.charAt(0) == '1') {
				ny = -0.5;
			}
			
		}
		else { //If move within board
			
			ny = end.charAt(0) - start.charAt(0);
			
			}
		
		Node n = ps.getChildren().get(0);
			
		TranslateTransition t = new TranslateTransition();

		t.setByX(x * nx);
		t.setByY(y * ny);
		t.setDuration(Duration.seconds(0.5));
		t.setNode(n);
			
		t.play();
		t.setOnFinished(e -> {
			
			{
				GemSound gs = new GemSound();
				gs.run();
				this.add(pe, n);
				gems -= 1;
				if (gems > 0) {
					this.Side(next(pe));
				}
				else if (gems == 0) {
					Pane p = next(pe);
					int gems = p.getChildren().size();
					if (gems > 0) {
						if (p.getId().charAt(1) != '0' && p.getId().charAt(1) != '6') {
							Moves m = new Moves(p, clockwise, gems, gm);
							m.Side(m.next(p));
						}
						else {
							gm.changeTurn();
						}
					}
					else if (gems == 0) {
						if (p.getId().charAt(1) != '0' && p.getId().charAt(1) != '6') {
							if (next(p).getChildren().size() > 0) {
								this.eat(next(p));
							}
							else {
								gm.changeTurn();
							}
						}
						else {
							gm.changeTurn();
						}
					}
				}
			}
				
		});
		ps.getChildren().remove(0);
	}
	
	public void add(Pane pe, Node n) { //Add gems into cell
		
		pe.getChildren().add(n);
		n.setTranslateX(0);
		n.setTranslateY(0);
		gm.add(pe.getId());
		
		String end = pe.getId();
		
		if (end.charAt(1) == '0' || end.charAt(1) == '6') { //If move to the BoardEnd
			n.setTranslateY(y/2);
		}
		
	}
	
	public Pane next(Pane pe) { //Get the next cell
		String end = pe.getId();
		String newEnd = "";
		
		if (clockwise) { //If move to the clockwise
			newEnd = gm.getCellID()[(Arrays.asList(gm.getCellID()).indexOf(end) + 1) % 12];
		}
		
		else {
			newEnd = gm.getCellID()[(Arrays.asList(gm.getCellID()).indexOf(end) + 11) % 12];
		}
		
		return (Pane) pe.getScene().lookup("#" + newEnd);
		
	}
	
	public void eat(Pane p) { //Eat the cell
		
		for (int i = 0; i < p.getChildren().size(); i++) {
			
			Node n = p.getChildren().get(i);
			
			TranslateTransition t = new TranslateTransition();
			
			int turn = -1;
			
			if (gm.isTurnPlayer0()) {
				turn = 1;
			}

			t.setByY(1000 * turn);
			t.setDuration(Duration.seconds(0.5));
			t.setNode(n);
				
			t.play();
			t.setOnFinished(e -> {
				
				if (p.getId().equals("00")) {
					if (gm.hasMandarin0()) {
						gm.addScores(!gm.isTurnPlayer0(), p.getChildren().size() + 4);
						gm.changeMandarin0();
						gm.rmv("00");
					}
				}
				else if (p.getId().equals("66")) {
					if (gm.hasMandarin6()) {
						gm.addScores(!gm.isTurnPlayer0(), p.getChildren().size() + 4);
						gm.changeMandarin6();
						gm.rmv("66");
					}
				}
				else{
					gm.addScores(!gm.isTurnPlayer0(), p.getChildren().size());
					gm.rmv(p.getId());
				}
				
				Pane np = next(p);
				
				if (np.getChildren().size() > 0) {
					if (p.getChildren().size() > 0) {
						gm.changeTurn();
					}
				}
				else {
					if (next(np).getChildren().size() == 0) {
						if (p.getChildren().size() > 0) {
							gm.changeTurn();
						}
					}
				}
				
				p.getChildren().clear();
				
				
				
				if (np.getChildren().size() > 0) {
					gm.changeTurn();
				}
				else if (np.getChildren().size() == 0){
					if (next(np).getChildren().size() > 0) {
						eat(np);
					}
					else if (next(np).getChildren().size() == 0) {
						gm.changeTurn();
					}
				}
				
				spread();
				checkEnd();
				
			});
			
		}
		System.out.println("Eaten " + p.getId());
	}
	
	public void spread() {
		String[] side0 = new String[] {"01", "02", "03", "04", "05"};
		int i0 = 0;
		String[] side1 = new String[] {"11", "12", "13", "14", "15"};
		int i1 = 0;
		for (int i = 0; i < 5; i++) {
			if (((Pane) ps.getScene().lookup("#" + side0[i])).getChildren().size() == 0) {
				i0 += 1;
			}
			
			if (((Pane) ps.getScene().lookup("#" + side1[i])).getChildren().size() == 0) {
				i1 += 1;
			}
		}
		
		if (i0 == 5) {
			
			for (int i = 0; i < 5; i++) {
				((Pane) ps.getScene().lookup("#" + side0[i])).getChildren().add(new Gem());
				gm.addScores(true, -5);
			}
			
		}
		
		if (i1 == 5) {
			
			for (int i = 0; i < 5; i++) {
				((Pane) ps.getScene().lookup("#" + side1[i])).getChildren().add(new Gem());
				gm.addScores(false, -5);
			}
			
		}
		
	}
	
	public void checkEnd() {
		if (((Pane) ps.getScene().lookup("#00")).getChildren().size() == 0
				&& ((Pane) ps.getScene().lookup("#66")).getChildren().size() == 0) {
			System.out.println("Game is ended");
			gm.Ended();
		}
	}
	
}

