package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class Hero {
	
	private static final int BOX_SIZE = 50;
	private int heroX, heroY;
	
	public Hero(int heroX, int heroY) {
		this.heroX=heroX;
		this.heroY=heroY;
	}
	
	public void paint(Graphics g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(this.heroX, this.heroY, BOX_SIZE, BOX_SIZE);
	}

}
