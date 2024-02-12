package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class Hero {
	private static final int BOX_SIZE = 40;
	private static final int MOVE_SPEED = 5;
	private static final int JUMP_HEIGHT = 40;
	private int boxX;
	private int boxY;
	private boolean isJumping;
	
	public Hero(int heroX, int heroY) {
		this.boxX=heroX;
		this.boxY=heroY;
	}
	
	public void paint(Graphics g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(this.boxX, this.boxY, BOX_SIZE, BOX_SIZE);
	}

}
  