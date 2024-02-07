package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Missiles {
	
	private static final int MISSILE_SIZE = 20;
	private int missileX, missileY, initialX;
	private double xVelocity = 10;
	private double yVelocity = 5;
	private boolean isTracking = false;
	private int playerY;
	
	
	public Missiles(int missileX, int missileY, boolean isTracking) {
		this.missileX=missileX;
		this.missileY=missileY;
		this.initialX=missileX;
		this.isTracking=isTracking;
	}
	
	public boolean move() {
		missileX -= this.xVelocity;
		return this.missileX < 0;
			
		}
	
	
	public void resetPosition() {
		
	}
	
	public int getX() {
		return this.initialX;
	}
	
	public int getY() {
		return this.missileY;
	}
	
	public void playerY(int playerY) {
		this.playerY = playerY;
		if(isTracking==true) {
			if(missileY > playerY) {
				missileY -= this.yVelocity;
			}else if(missileY < playerY){
				
					missileY += this.yVelocity;
			}
		
		}
	
	}
	
	
	public void drawOn(Graphics2D g2) {
		g2.setColor(Color.RED);
		Rectangle2D.Double missile=new Rectangle2D.Double(this.missileX, this.missileY, MISSILE_SIZE, MISSILE_SIZE);
		g2.fill(missile);
	}

}
