package mainApp;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GameComponent extends JComponent {
	
	private int playerYPos;
	private int barX, barY;
	Boolean isElectric;
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();
	private ArrayList<Missiles> missiles=new ArrayList<>();
	private ArrayList<Hero> hero=new ArrayList<>();
	
	public void listsIn(ArrayList<Barriers> bars, ArrayList<Coin> coins, ArrayList<Missiles> missiles, ArrayList<Hero> hero) {
		this.barriers = bars;
		this.coins = coins;
		this.missiles=missiles;
		this.hero=hero;
	}
	
	public void updateMissiles() {
		 for (int i = 0; i < this.missiles.size();i++) { 
			 Missiles missile = this.missiles.get(i);
		 
			missile.playerY(playerYPos);
			boolean reset = missile.move();
			if (reset) {
				int missileX=this.getX()+missile.getX();
				int missileY=missile.getY();
				this.missiles.remove(missile);
				this.missiles.add(new Missiles(missileX, missileY, true));
			}
			
		 }
		
	}
	
	public void playerYPos(int yPos) {
		this.playerYPos = yPos;
		
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
		
		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).drawOn(graphics2);
		}
		for (int i = 0; i < hero.size(); i++) {
			hero.get(i).drawOn(graphics2);
		}
		
		updateMissiles();
	}
	

}
