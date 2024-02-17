package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

/**
 * Class: Coin
 * 
 * @author W24_A205 <br>
 *         Purpose: Establishes and creates coin objects <br>
 *         Restrictions: None
 * 
 */
public class Coin extends Collidable {
	private Image coin;

	private static final int COIN_SIZE = 15;
	private int coinX, coinY;

	public Coin(int coinX, int coinY) {
		this.coinX = coinX;
		this.coinY = coinY;
		this.coinSize = COIN_SIZE;
		this.objectX = coinX;
		this.objectY = coinY;

	}

	/**
	 * ensures that the game recognizes whenever the hero collides with a coin
	 * 
	 * @param b
	 * @return
	 */
	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(this.coinX, this.coinY, COIN_SIZE, COIN_SIZE);
	}

	/**
	 * ensures that the coins are created in the right location and drawn in the
	 * level
	 * 
	 * @param g2
	 */
	public void drawOn(Graphics2D g2) {
		coin = new ImageIcon("coin2.png").getImage();
		g2.drawImage(coin, this.coinX, this.coinY, null);

	}
}
