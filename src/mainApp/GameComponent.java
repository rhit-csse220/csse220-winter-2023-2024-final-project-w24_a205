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
	private ArrayList<Missiles> missiles=new ArrayList<>();
	
	public void listsIn(ArrayList bars, ArrayList coins) {
		this.barriers = bars;
		this.coins = coins; 
	}
	
	public void updateMissiles() {
		for(Missiles missile: this.missiles) {
			if (missile.move()==true) {
				int missileX=this.getX()+missile.getX();
				int missileY=this.getY();
				this.missiles.remove(missile);
				this.missiles.add(new Missiles(missileX, missileY));
			}
		}
	}

	
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D graphics2 = (Graphics2D) graphics;

		for (int i = 0; i < barriers.size(); i++) {
			barriers.get(i).drawOn(graphics2);
		}
		for (int i = 0; i < coins.size(); i++) {
			coins.get(i).drawOn(graphics2);
		}
		
		
		
	}
	

}
