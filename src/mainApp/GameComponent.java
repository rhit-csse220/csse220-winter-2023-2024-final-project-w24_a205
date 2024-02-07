package mainApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GameComponent extends JComponent {
	
	int barX, barY;
	Boolean isElectric;
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();
	
	public void listsIn(ArrayList bars, ArrayList coins) {
		this.barriers = bars;
		this.coins = coins; 
	}
	
	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;

		for (int i = 0; i < barriers.size(); i++) {
			barriers.get(i).drawOn(graphics2);
		}
		
		
		
	}
	

}
