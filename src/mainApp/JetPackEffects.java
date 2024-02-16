package mainApp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class JetPackEffects {
	private double x;
	private double y;
	private double yVelocity = 10;

	private boolean isGravReversed;

	private static final double SIZE = 5.0;

	public JetPackEffects(int range, double playerX, double playerY) {
		this.x = (Math.random() * range) + playerX;

		if (this.isGravReversed == true) {
			this.y = playerY;
		} else {
			this.y = playerY + range;

		}
	}

	public boolean fall(int bottom, boolean isGravReversed) {
		int top = bottom - bottom;
		this.isGravReversed = isGravReversed;
		if (isGravReversed == false) {
			this.y += this.yVelocity;
			return this.y > bottom;
		} else {
			this.y -= this.yVelocity;
			return this.y < top;
		}

	}

	public void drawOn(Graphics2D g) {
		int randColor = (int) (Math.random() * 4);
		Color color;
		if (randColor == 0) {
			color = Color.ORANGE;
		} else if (randColor == 1) {
			color = Color.YELLOW;
		} else if (randColor == 2) {
			color = Color.GRAY;
		} else {
			color = Color.RED;
		}

		g.setColor(color);
		Ellipse2D.Double particle = new Ellipse2D.Double(this.x, this.y, SIZE, SIZE);
		g.fill(particle);
		
	}

}
