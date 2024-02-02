package mainApp;

import java.awt.Color;
import java.awt.Graphics;

public class Coin {
	
	    private int x;
	    private int y;
	    private static final int COIN_SIZE = 20;

	    public Coin(int x, int y) {
	        this.x = x;
	        this.y = y;
	    }

	    public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }

	    public void draw(Graphics g) {
	        g.setColor(Color.YELLOW);
	        g.fillOval(x, y, COIN_SIZE, COIN_SIZE);
	    }
	}

