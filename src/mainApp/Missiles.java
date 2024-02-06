package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class Missiles {
	
	private static final int MISSILE_SIZE = 50;
	private int missileX, missileY;
	
	public Missiles(int missleX, int missleY) {
		this.missileX=missileX;
		this.missileY=missileY;
	}
	
	public void paint(Graphics g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(this.missileX, this.missileY, MISSILE_SIZE, MISSILE_SIZE);
	}

}
