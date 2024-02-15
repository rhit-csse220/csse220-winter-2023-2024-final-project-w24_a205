package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class GravityPowerUp extends Collidable {

	private Image grav;
	
	private static final int GRAV_POWER_UP_SIZE = 15;
	private int gpX, gpY;
	
	
	public GravityPowerUp(int xPos, int yPos) {
		this.gpX = xPos;
		this.gpY = yPos;
		
	}
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(this.gpX, this.gpY, GRAV_POWER_UP_SIZE, GRAV_POWER_UP_SIZE);
	}

	public void drawOn(Graphics2D g2) {
		grav = new ImageIcon("gravPowerUp2.png").getImage();
		g2.drawImage(grav, (int)this.gpX, (int)this.gpY, null);
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
