package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.math.*;

public class Barriers extends Collidable{
	
	private static final int STANDARD_BAR_WIDTH = 70;
	private static final int STANDARD_BAR_HEIGHT = 10;
	private int barrierX, barrierY;
	private boolean isElectric;
	private char rotation;
	
	public Barriers(int barrierX, int barrierY, boolean isElectric, char rotation) {
		this.barrierX=barrierX;
		this.barrierY=barrierY;
		this.isElectric=isElectric;
		this.rotation = rotation; 
		this.objectX = barrierX;
		this.objectY = barrierY;
		this.barrierHeight = STANDARD_BAR_HEIGHT;
		this.barrierWidth = STANDARD_BAR_WIDTH;
		
		
	}
	
	
	public void drawOn(Graphics2D g2) {
		if (this.isElectric==true) {
			g2.setColor(Color.RED);
		} else {
			g2.setColor(Color.GREEN);
		}
		if (this.rotation == 'R') {
			Polygon barR = new Polygon();
			barR.addPoint(this.barrierX, this.barrierY);
			
			barR.addPoint((int) (this.barrierX + (STANDARD_BAR_WIDTH * Math.cos(45) )),
					(int) (this.barrierY + (STANDARD_BAR_WIDTH * Math.sin(45) )));
			
			barR.addPoint((int) (this.barrierX + (STANDARD_BAR_WIDTH * Math.cos(45)) - 
					(STANDARD_BAR_HEIGHT * Math.cos(45))),
					(int) (this.barrierY + (STANDARD_BAR_WIDTH * Math.sin(45)) +
							(STANDARD_BAR_HEIGHT * Math.sin(45))));
			
			barR.addPoint((int) (this.barrierX  - 
					(STANDARD_BAR_HEIGHT * Math.cos(45))),
					(int) (this.barrierY  +
							(STANDARD_BAR_HEIGHT * Math.sin(45))));
			barR.npoints = 4; 
			g2.draw(barR);
			g2.fill(barR);
		
		}else {
		g2.fillRect(this.barrierX, this.barrierY, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
	}
	}
}