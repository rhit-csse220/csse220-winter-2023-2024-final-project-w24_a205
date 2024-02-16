package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Missiles extends Collidable {

	Image mis;
	private double x;
	private double y;
	private double xVelocity = -20;
	private double yVelocity = 1;
	boolean isTracking;
	private double playerY;
	private static final double HEIGHT = 10.0;
	private static final double WIDTH = 15.0;

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
		mis = new ImageIcon("missile2.png").getImage();
		g.drawImage(mis, (int) this.x, (int) this.y, null);
		g.setColor(Color.BLACK);
	}

	public void playerY(double playerY) {
		this.playerY = playerY;
		if (isTracking == true) {
			if (this.y > playerY) {
				this.y -= this.yVelocity;
			} else if (this.y < playerY) {

				this.y += this.yVelocity;
			}

		}
	}

	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(x, y, WIDTH, HEIGHT);
	}

}
