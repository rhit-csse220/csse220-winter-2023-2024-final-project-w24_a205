package mainApp;
import java.awt.Color;
import java.awt.Graphics;

public class Barriers {
	
	private static final int STANDARD_BAR_SIZE = 40;
	private int barrierX, barrierY;
	private boolean isElectric;
	
	public Barriers(int barrierX, int barrierY, boolean isElectric) {
		this.barrierX=barrierX;
		this.barrierY=barrierY;
		this.isElectric=isElectric;
	}
	
	public void paintBarrier(Graphics g2) {
		if (this.isElectric==true) {
			g2.setColor(Color.RED);
		} else {
			g2.setColor(Color.GREEN);
		}
		g2.fillRect(this.barrierX, this.barrierY, STANDARD_BAR_SIZE, STANDARD_BAR_SIZE);
	}
}