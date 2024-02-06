package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class Barriers {
	
	private static final int STANDARD_BAR_SIZE = 40;
	private int barrierX, barrierY;
	
	public Barriers(int barrierX, int barrierY) {
		this.barrierX=barrierX;
		this.barrierY=barrierY;
	}
	
	public void paintBarrier(Graphics g2) {
		g2.setColor(Color.GREEN);
		g2.fillRect(this.barrierX, this.barrierY, STANDARD_BAR_SIZE, STANDARD_BAR_SIZE);
	}
	public void paintELectricBarrier(Graphics g2) {
		g2.setColor(Color.RED);
		g2.fillRect(this.barrierX, this.barrierY, STANDARD_BAR_SIZE, STANDARD_BAR_SIZE);
	}
}
