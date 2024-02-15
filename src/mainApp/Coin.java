package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

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

	public boolean insideBox(Rectangle2D.Double b) {
		return b.intersects(this.coinX, this.coinY, COIN_SIZE, COIN_SIZE);
	}

	public void drawOn(Graphics2D g2) {
		coin = new ImageIcon("coin2.png").getImage();
		g2.drawImage(coin, this.coinX, this.coinY, null);
		

	}
}
