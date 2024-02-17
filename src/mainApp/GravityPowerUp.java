package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

/**
 * Class: GravityPowerUp
 * 
 * @author W24_A205 <br>
 *         Purpose: Establishes and creates power-up objects <br>
 *         Restrictions: None
 * 
 */
public class GravityPowerUp extends Collidable {

	private Image grav;

	private static final int GRAV_POWER_UP_SIZE = 15;
	private int gpX, gpY;

	public GravityPowerUp(int xPos, int yPos) {
		this.gpX = xPos;
		this.gpY = yPos;

	}

	/**
	 * ensures that the game recognizes whenever the hero collides with a power-up
	 * 
	 * @param b
	 * @return
	 */
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(this.gpX, this.gpY, GRAV_POWER_UP_SIZE, GRAV_POWER_UP_SIZE);
	}

	/**
	 * ensures that the power-ups are created in the right location and drawn in the
	 * level
	 * 
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		grav = new ImageIcon("gravPowerUp2.png").getImage();
		g2.drawImage(grav, (int) this.gpX, (int) this.gpY, null);

	}

}
