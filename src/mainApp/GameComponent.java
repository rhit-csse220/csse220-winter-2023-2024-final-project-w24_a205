package mainApp;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class GameComponent extends JComponent {
	// Here is the game state. In a bigger game, this would live
	// in another class like Level.
	private Rectangle2D.Double box;
	private int STARTING_DX = 10;
	private int dx;
	private List<Missiles> missiles = new ArrayList<>();
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();
	
	private int lives;
	private int coinCount;
	
	
	private int numTicks;
	private static final int BOX_SIZE = 20;
	private static final int BOX_X = 10;
	private static final int BOX_Y = 180;
	private static final int JUMP_HEIGHT = 20;
	private String filename;
	private MainApp main;
	
	private boolean isJumping;
	
	public void jumpUpdate(boolean isJumping) {
		this.isJumping = isJumping; 
	}
	
	public GameComponent(String filename, int coins, int lives) {
		this.box = new Rectangle2D.Double(BOX_X, BOX_Y , BOX_SIZE, BOX_SIZE);
		this.dx = STARTING_DX;
		this.filename = filename;
		this.coinCount = coins;
		this.lives = lives;
		
		}
	public void mainIn(MainApp main) {
		this.main = main;
	}

	public void updateState() {
		
		updateBox();
		updateBarriers();
		updateMissiles();
		updateCoins();
		this.numTicks++;
	}
	public void listsIn(ArrayList<Barriers> bars, ArrayList<Coin> coins, ArrayList<Missiles> missiles) {
		this.barriers = bars;
		this.coins = coins;
		this.missiles=missiles;
		
	}
	
	
	public void drawScreen() {
		this.repaint();
		//System.out.println("Tick " + this.numTicks);
		
	}
	
	public void updateCoins() {
			List<Coin>  coinsToRemove= new ArrayList<>();
			for (Coin coin : this.coins) {
			boolean hitBox = coin.insideBox( this.box );
			if (hitBox ) {
				
				coinsToRemove.add(coin);
				System.out.println("coin Collected");
				this.coinCount++;
				
				
			}
			
		}
		for (Coin coin : coinsToRemove) {
			this.coins.remove(coin);
		}
		
	}
	
	public void updateBox() {
		
		this.box.x += this.dx;
		if (dx == 1) {
			dx = 10;
		}
		if (isJumping) {
			box.y -= JUMP_HEIGHT;
			isJumping = false; // Stop jumping after one jump
		} else {
			// Otherwise, simulate gravity by moving the box downward
			if (box.y < this.getHeight() - BOX_SIZE-20) {
				box.y += 3; // Adjust this value for fall speed
			}
		}
		
		if (this.box.y < 0) {
			this.box.y = 0;
		}
		
		
		//Keep the box from going off the screen 
		if ( this.box.x > this.getWidth() - box.getWidth() ) {
			this.box.x = this.getWidth() - box.getWidth();
			
			System.out.println("end of level!!!");
			
			if(filename == "levels/level1.txt") {
				this.main.endLevel();
			try {
				FileReader fileReader = new FileReader();
				fileReader.readFile("levels/level2.txt", coinCount, lives);
			} catch (InvalidLevelFormatException e) {
				e.printStackTrace();
			}
			}else {
				System.exit(0);
			}
			
			
			
			
		} else if (this.box.x < 0 ) {
			this.box.x = 0;
		}
	}
	private void updateBarriers() {
		for (Barriers barrier : this.barriers) {
		
		boolean hitBox = barrier.insideBox( this.box );
		if(hitBox) {
			System.out.println("Collision");
			this.box.x = barrier.objectX - BOX_SIZE;
			this.dx = 1;
			if(barrier.isDeadly) {
				
				System.out.println("Died to barrier!");
				lives = lives -1;
				this.main.endLevel();
				try {
					FileReader fileReader = new FileReader();
					fileReader.readFile(filename, coinCount, lives);
				} catch (InvalidLevelFormatException e) {
					e.printStackTrace();
				}
			}
			
		
	}
		
		}
	}
	private void updateMissiles() {
		/*if (Math.random() < 0.5) {
			this.missiles.add(new Missiles(this.getHeight() ));
		}
		*/
		
		// Alternatively, we could use the number of ticks to get more regular rain.
		//if (this.numTicks % 2 == 0) {
		//	this.raindrops.add(new Raindrop(this.getWidth()));
		//}
		
		List<Missiles> missilesToRemove = new ArrayList<>();
		
		// Any drops that fall off the bottom of the 
		// screen should be removed. Removing within the foreach
		// loop gives a concurrent modification exception.
		// We can make a list of ones to remove (which I think is most general), 
		// or use an iterator,
		// or use an indexed loop and iterate backwards.
		for (int i = 0; i < this.missiles.size();i++) { 
			 Missiles missile = this.missiles.get(i);
			 missile.playerY(this.box.y);
			
			
			boolean hitBox = missile.insideBox( this.box );
			if (hitBox ) {
				
				missilesToRemove.add(missile);
				System.out.println("died to missile");
				
				
				lives = lives -1;
				this.main.endLevel();
				try {
					FileReader fileReader = new FileReader();
					fileReader.readFile(filename, coinCount, lives);
				} catch (InvalidLevelFormatException e) {
					e.printStackTrace();
				}
				
				
			}
			boolean hasLeftScreen = missile.fly();
			if (hasLeftScreen) {
				missiles.remove(missile);
				boolean missileTracking = missile.isTracking;
				int missileY=missile.objectY;
				this.missiles.remove(missile);
				this.missiles.add(new Missiles(missileY, missileTracking));
				
				
			}
			
		}
		for (Missiles missile : missilesToRemove) {
			this.missiles.remove(missile);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D)g;
		
		g2.fill(this.box);
		
		for (Missiles missile : this.missiles) {
			missile.drawOn(g2);
			
		for (Barriers bar: this.barriers) {
			bar.drawOn(g2);
			
		}
		for (Coin coin: this.coins) {
			coin.drawOn(g2);
		}
		
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
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
	*/
	

}
