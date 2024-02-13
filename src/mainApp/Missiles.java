package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Missiles extends Collidable {
	
	
	private double x;
	private double y;
	private double xVelocity = -20;
	private double yVelocity = 1;
	boolean isTracking;
	private double playerY;
	private static final double SIZE = 10.0;
	
	public Missiles(int ypos, boolean isTracking) {
		this.x = 1500;
		this.y = ypos;
		this.objectY = ypos;
		this.isTracking = isTracking;
	}
	
	
	public boolean fly() {
		this.x += this.xVelocity;
		return this.x < 0;
		
	}
	
	public void drawOn(Graphics2D g) {
		g.setColor(Color.RED);
		Ellipse2D.Double missile = 
				new Ellipse2D.Double(this.x, this.y, SIZE, SIZE);
		g.fill(missile);
		g.setColor(Color.BLACK);
	}
	
	public void playerY(double playerY) {
		this.playerY = playerY;
		if(isTracking==true) {
			if(this.y > playerY) {
				this.y -= this.yVelocity;
			}else if(this.y < playerY){
				
					this.y += this.yVelocity;
			}
		
	}
	}
	
	
	//Use to detect if the missiles hit the box
	//Refactor into Collidable at some point 
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(x,y, SIZE, SIZE);
	}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static final int MISSILE_SIZE = 20;
	public int missileX, missileY, initialX;
	private double xVelocity = 7;
	private double yVelocity = 2;
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
	*/

}
