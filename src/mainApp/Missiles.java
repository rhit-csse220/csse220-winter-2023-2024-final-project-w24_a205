package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Missiles {
	
	private static final int MISSILE_SIZE = 20;
	private int missileX, missileY;
	private double xVelocity = 5;
	
	public Missiles(int missileX, int missileY) {
		this.missileX=missileX;
		this.missileY=missileY;
	}
	
	public boolean move() {
		this.missileX -= this.xVelocity;
		return this.missileX < 0;
	}
	
	public int getX() {
		return this.missileX;
	}
	
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.RED);
		Rectangle2D.Double missile=new Rectangle2D.Double(this.missileX, this.missileY, MISSILE_SIZE, MISSILE_SIZE);
		g2.fill(missile);
	}

}
