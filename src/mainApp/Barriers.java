package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.math.*;

import javax.swing.ImageIcon;

/**
 * Class: Barriers
 * 
 * @author W24_A205 <br>
 *         Purpose: Establishes and creates barrier objects <br>
 *         Restrictions: None
 * 
 */
public class Barriers extends Collidable {

	private static final int STANDARD_BAR_WIDTH = 10;
	private static final int STANDARD_BAR_HEIGHT = 54;
	private int barrierX, barrierY;
	private boolean isElectric;
	private char rotation;
	private Image barrier;
	private Image electricBarrier;

	public Barriers(int barrierX, int barrierY, boolean isElectric, char rotation) {

		this.barrierX = barrierX;
		this.barrierY = barrierY;
		this.isElectric = isElectric;
		this.rotation = rotation;
		this.objectX = barrierX;
		this.objectY = barrierY;
		this.barrierHeight = STANDARD_BAR_HEIGHT;
		this.barrierWidth = STANDARD_BAR_WIDTH;
		this.isDeadly = isElectric;

	}

	/**
	 * ensures that the game recognizes whenever the hero collides with a barrier
	 * 
	 * @param b
	 * @return
	 */
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(barrierX, barrierY, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
	}

	/**
	 * ensures that the barrier object is created in the right location and drawn in
	 * the level
	 * 
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		if (this.isElectric == true) {
			g2.setColor(Color.RED);
		} else {
			g2.setColor(Color.GRAY);
		}
		if (this.rotation == 'R') {
			Polygon barR = new Polygon();
			barR.addPoint(this.barrierX, this.barrierY);

			barR.addPoint((int) (this.barrierX + (STANDARD_BAR_WIDTH * Math.cos(45))),
					(int) (this.barrierY + (STANDARD_BAR_WIDTH * Math.sin(45))));

			barR.addPoint(
					(int) (this.barrierX + (STANDARD_BAR_WIDTH * Math.cos(45)) - (STANDARD_BAR_HEIGHT * Math.cos(45))),
					(int) (this.barrierY + (STANDARD_BAR_WIDTH * Math.sin(45)) + (STANDARD_BAR_HEIGHT * Math.sin(45))));

			barR.addPoint((int) (this.barrierX - (STANDARD_BAR_HEIGHT * Math.cos(45))),
					(int) (this.barrierY + (STANDARD_BAR_HEIGHT * Math.sin(45))));
			barR.npoints = 4;
			g2.draw(barR);
			g2.fill(barR);

		} else if(this.isElectric == true) {
			electricBarrier = new ImageIcon("electric barrier.png").getImage();
			g2.drawImage(electricBarrier, this.barrierX, this.barrierY, null);
			
		}else {
			barrier = new ImageIcon("barrier.png").getImage();
			g2.drawImage(barrier, this.barrierX, this.barrierY, null);
		}
	}
}