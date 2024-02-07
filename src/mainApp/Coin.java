package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;

public class Coin {
	
	private static final int COIN_SIZE = 20;
	private int coinX, coinY;
	
	public Coin(int coinX, int coinY) {
		this.coinX=coinX;
		this.coinY=coinY;
	}
	

	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fillOval(this.coinX, this.coinY, COIN_SIZE, COIN_SIZE);
	}
}
