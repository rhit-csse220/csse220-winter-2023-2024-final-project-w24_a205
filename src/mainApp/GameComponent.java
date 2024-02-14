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

	private Rectangle2D.Double box;
	private int STARTING_DX = 10;
	private int dx;
	private List<Missiles> missiles = new ArrayList<>();
	private ArrayList<Barriers> barriers = new ArrayList<>();
	private ArrayList<Coin> coins = new ArrayList<>();
	private ArrayList<GravityPowerUp> gravPowerUps = new ArrayList<>();
	private List<JetPackEffects> jetEffects = new ArrayList<>();

	private int lives;
	private int coinCount;

	private int numTicks;
	private static final int BOX_SIZE = 20;
	private static final int BOX_X = 10;
	private static final int BOX_Y = 180;
	private static final int JUMP_HEIGHT = 20;
	private int level;
	private MainApp main;

	private boolean isJumping;
	private boolean gravReverse;

	public void jumpUpdate(boolean isJumping) {
		this.isJumping = isJumping;
	}

	public void gravityReversed() {
		if (this.gravReverse == true) {
			this.gravReverse = false;
		} else {
			this.gravReverse = true;
		}

	}

	public GameComponent(int level, int coins, int lives) {
		this.box = new Rectangle2D.Double(BOX_X, BOX_Y, BOX_SIZE, BOX_SIZE);
		this.dx = STARTING_DX;
		this.level = level;
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
		updateGravPowerUps();
		updateJetpackEffects();
		this.numTicks++;

	}

	public void listsIn(ArrayList<Barriers> bars, ArrayList<Coin> coins, ArrayList<Missiles> missiles,
			ArrayList<GravityPowerUp> gravPowerUps) {
		this.barriers = bars;
		this.coins = coins;
		this.missiles = missiles;
		this.gravPowerUps = gravPowerUps;

	}

	public void drawScreen() {
		this.repaint();
		// System.out.println("Tick " + this.numTicks);

	}

	public void updateCoins() {
		List<Coin> coinsToRemove = new ArrayList<>();
		for (Coin coin : this.coins) {
			boolean hitBox = coin.insideBox(this.box);
			if (hitBox) {

				coinsToRemove.add(coin);
				System.out.println("coin Collected");
				this.coinCount++;

			}

		}
		for (Coin coin : coinsToRemove) {
			this.coins.remove(coin);
		}

	}

	public void updateGravPowerUps() {
		List<GravityPowerUp> gravPUpsToRemove = new ArrayList<>();
		for (GravityPowerUp powerUp : this.gravPowerUps) {
			boolean hitBox = powerUp.insideBox(this.box);
			if (hitBox) {

				gravPUpsToRemove.add(powerUp);
				gravityReversed();
				System.out.println("Gravity Reversed!");

			}

		}
		for (GravityPowerUp powerUp : gravPUpsToRemove) {
			this.gravPowerUps.remove(powerUp);
		}

	}

	public void updateBox() {

		this.box.x += this.dx;
		if (dx == 1) {
			dx = 10;
		}

		if (gravReverse == false) {
			if (isJumping) {
				box.y -= JUMP_HEIGHT;
				isJumping = false; // Stop jumping after one jump
			} else {
				// Otherwise, simulate gravity by moving the box downward
				if (box.y < this.getHeight() - BOX_SIZE - 20) {
					box.y += 4; // Adjust this value for fall speed
				}
			}
			if (this.box.y < 30) {
				this.box.y = 30;
			}
		}

		if (gravReverse == true) {
			if (isJumping) {
				box.y += JUMP_HEIGHT;
				isJumping = false; // Stop jumping after one jump
			} else {
				// Otherwise, simulate gravity by moving the box downward
				if (box.y > 30) {
					box.y -= 4; // Adjust this value for fall speed
				}
			}
			if (this.box.y > this.getHeight() - BOX_SIZE - 20) {
				this.box.y = this.getHeight() - BOX_SIZE - 20;
			}
		}

		// Keep the box from going off the screen
		if (this.box.x > this.getWidth() - box.getWidth()) {
			this.box.x = this.getWidth() - box.getWidth();

			System.out.println("end of level!!!");
			int lvl = level + 1;
			if (lvl > 4) {
				if (lives==5) {
					this.main.endLevel(); 
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(5, coinCount, lives);
					} catch (InvalidLevelFormatException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("Game complete!");
					System.exit(0);
				}
			}else {
			this.main.endLevel();
			try {
				FileReader fileReader = new FileReader();
				fileReader.readFile(lvl, coinCount, lives);
			} catch (InvalidLevelFormatException e) {
				e.printStackTrace();
			}
			}

		}   if (this.box.x < 0) {
			this.box.x = 0;
		}
	}

	private void updateBarriers() {
		for (Barriers barrier : this.barriers) {

			boolean hitBox = barrier.insideBox(this.box);
			if (hitBox) {

				this.box.x = barrier.objectX - BOX_SIZE;
				this.dx = 1;
				if (barrier.isDeadly) {

					System.out.println("Died to barrier!");
					lives = lives - 1;
					this.main.endLevel();
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(level, coinCount, lives);
					} catch (InvalidLevelFormatException e) {
						e.printStackTrace();
					}
				}

			}

		}
	}

	private void updateMissiles() {

		List<Missiles> missilesToRemove = new ArrayList<>();

		for (int i = 0; i < this.missiles.size(); i++) {
			Missiles missile = this.missiles.get(i);
			missile.playerY(this.box.y);

			boolean hitBox = missile.insideBox(this.box);
			if (hitBox) {

				missilesToRemove.add(missile);
				System.out.println("died to missile");

				lives = lives - 1;
				this.main.endLevel();
				try {
					FileReader fileReader = new FileReader();
					fileReader.readFile(level, coinCount, lives);
				} catch (InvalidLevelFormatException e) {
					e.printStackTrace();
				}

			}
			boolean hasLeftScreen = missile.fly();
			if (hasLeftScreen) {
				missiles.remove(missile);
				boolean missileTracking = missile.isTracking;
				int missileY = missile.objectY;
				this.missiles.remove(missile);
				this.missiles.add(new Missiles(missileY, missileTracking));

			}

		}
		for (Missiles missile : missilesToRemove) {
			this.missiles.remove(missile);
		}
	}

	private void updateJetpackEffects() {

		if (Math.random() < 0.5) {
			this.jetEffects.add(new JetPackEffects(this.BOX_SIZE, this.box.x, this.box.y));
		}

		List<JetPackEffects> effectsToRemove = new ArrayList<>();
		for (JetPackEffects effects : this.jetEffects) {
			boolean shouldRemove = effects.fall(this.getHeight(), this.gravReverse);
			if (shouldRemove) {
				effectsToRemove.add(effects);
			}

		}
		for (JetPackEffects effects : effectsToRemove) {
			this.jetEffects.remove(effects);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.fillRect(0, this.getHeight() - 20, this.getWidth(), this.getHeight());
		g2.fillRect(0, 0, this.getWidth(), 30);
		g2.fill(this.box);

		for (Missiles missile : this.missiles) {
			missile.drawOn(g2);

			for (Barriers bar : this.barriers) {
				bar.drawOn(g2);

			}
			for (Coin coin : this.coins) {
				coin.drawOn(g2);
			}
			for (GravityPowerUp powerUp : this.gravPowerUps) {
				powerUp.drawOn(g2);
			}
			for (JetPackEffects effects : this.jetEffects) {
				effects.drawOn(g2);

			}
		}

	}
}
