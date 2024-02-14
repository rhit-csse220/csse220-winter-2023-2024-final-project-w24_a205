package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class GravityPowerUp extends Collidable {

public Color color = Color.MAGENTA;
	
	private static final int GRAV_POWER_UP_SIZE = 10;
	private int gpX, gpY;
	
	
	public GravityPowerUp(int xPos, int yPos) {
		this.gpX = xPos;
		this.gpY = yPos;
		
	}
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(this.gpX, this.gpY, GRAV_POWER_UP_SIZE, GRAV_POWER_UP_SIZE);
	}

	public void drawOn(Graphics2D g2) {
		g2.setColor(this.color);
		Ellipse2D.Double powerUp= 
				new Ellipse2D.Double(this.gpX, this.gpY, GRAV_POWER_UP_SIZE, GRAV_POWER_UP_SIZE);
		g2.fill(powerUp);
		
	}
	
	
	
	
	
	
	
	
	
}
