package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Coin extends Collidable {
	public Color color = Color.yellow;

	private static final int COIN_SIZE = 20;
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
		g2.setColor(this.color);
		Ellipse2D.Double coin = new Ellipse2D.Double(this.coinX, this.coinY, COIN_SIZE, COIN_SIZE);
		g2.fill(coin);

	}
}
