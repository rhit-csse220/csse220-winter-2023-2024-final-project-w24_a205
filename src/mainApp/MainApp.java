package mainApp;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class: MainApp
 * 
 * @author W24_A205 <br>
 *         Purpose: Top level class for CSSE220 Project containing main method
 *         <br>
 *         Restrictions: None
 * 
 */

public class MainApp extends JFrame {
	public static final int DELAY = 50;
	private boolean isJumping;
	private Timer timer;
	JFrame frame;
	JButton livesButton;
	JButton coinsButton;
	JPanel panel;
	private boolean isPaused = false;
	private int lives;
	private int coins;
	private GameComponent component;
	private int level;

	public void MainApp(GameComponent component, int coins, int lives, int level) {
		this.component = component;
		this.lives = lives;
		this.coins = coins;
		
		if(lives < 0) {
			
			System.out.println("Game over: out of lives");
			System.exit(0);
			
		}
		JFrame frame = new JFrame("Arcade Game");
		frame.setSize(1550, 300);
		
		
		JPanel panel = new JPanel();
		
		JButton jumpButton = new JButton("Jump");
		
		JButton livesButton = new JButton ("Lives left: " + lives );
		
		JButton levelButton = new JButton ("Level: " + (level + 1) );
		
		JButton coinsButton = new JButton ("Coins: " + coins);
		
		panel.add(jumpButton);
		panel.add(livesButton);
		panel.add(coinsButton);
		panel.add(levelButton);
		
		frame.add(panel, BorderLayout.SOUTH);
		
		frame.add(component, BorderLayout.CENTER);

		GameAdvanceListener advanceListener = new GameAdvanceListener(component);

	
		Timer timer = new Timer(DELAY, advanceListener);
		this.timer = timer;
		timer.start();
		/*
		jumpButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				isJumping = true;
				 
				component.jumpUpdate(isJumping);
			}
		
		});
		*/
		jumpButton.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					isJumping = true;
					component.jumpUpdate(isJumping);
				}
				
				
				if (e.getKeyCode() == KeyEvent.VK_U) {
					System.out.println("UP A LEVEL");
					int lvl = level +1;
					if(lvl > 4) {
						lvl = 4;
					}
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(lvl, 0, 5);
					} catch (InvalidLevelFormatException e1) {
						e1.printStackTrace();
					}
					timer.stop();
					frame.setVisible(false);
					
				}
				
				
				if (e.getKeyCode() == KeyEvent.VK_D) {
					System.out.println("DOWN A LEVEL");
					int lvl = level -1;
					if(lvl < 0) {
						lvl = 0;
					}
					
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(lvl, 0, 5);
					} catch (InvalidLevelFormatException e1) {
						e1.printStackTrace();
					}
					timer.stop();
					frame.setVisible(false);
					
					
				}if (e.getKeyCode() == KeyEvent.VK_P) {
					{ if (isPaused == true) { 
						isPaused = false;
						timer.start(); 
						}else { 
						isPaused = true;
						timer.stop(); } }
						}
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
 
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame = frame;
	}

	public void endLevel() {
		timer.stop();
		frame.setVisible(false);

	}

	/*
	 * private static final int FRAME_WIDTH = 1550; private static final int
	 * FRAME_HEIGHT = 300; private static final int BOX_SIZE = 40; private static
	 * final int MOVE_SPEED = 5; private static final int JUMP_HEIGHT = 40; private
	 * boolean isPaused = false; private String level;
	 * 
	 * private int coinCount; private int lives; private int boxX; private int boxY;
	 * private boolean isJumping; private GameComponent gComp; private
	 * ArrayList<Barriers> barriers=new ArrayList<>(); private ArrayList<Coin>
	 * coins=new ArrayList<>(); private ArrayList<Missiles> missiles=new
	 * ArrayList<>(); Timer timer = new Timer(20, this);
	 * 
	 * 
	 * 
	 * 
	 * public void runApp(GameComponent gComp, String filename, ArrayList<Barriers>
	 * bars, ArrayList<Coin> coins, ArrayList<Missiles> missiles, int coinCount, int
	 * lives) { this.barriers = bars; this.coins = coins; this.missiles=missiles;
	 * this.level = filename; this.coinCount = coinCount; this.lives = lives;
	 * System.out.println("You have " + lives + " lives remaining");
	 * 
	 * setSize(FRAME_WIDTH, FRAME_HEIGHT); setTitle("Jetpack Joyride!");
	 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setResizable(false);
	 * 
	 * 
	 * 
	 * add(gComp);
	 * 
	 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setVisible(true);
	 * 
	 * 
	 * 
	 * 
	 * this.gComp = gComp; boxX = 50; boxY = FRAME_HEIGHT - BOX_SIZE - 30; //
	 * Initial Y position at the bottom
	 * 
	 * timer.restart(); timer.start();
	 * 
	 * addKeyListener(this); setFocusable(true);
	 * 
	 * } public void playerPos(int xPos, int yPos) { this.boxX = xPos; this.boxY =
	 * yPos; }
	 * 
	 * public void actionPerformed(ActionEvent e) { // Move the box to the right
	 * boxX += MOVE_SPEED;
	 * 
	 * // If jumping, move the box upward if (isJumping) { boxY -= JUMP_HEIGHT;
	 * isJumping = false; // Stop jumping after one jump } else { // Otherwise,
	 * simulate gravity by moving the box downward if (boxY < FRAME_HEIGHT -
	 * BOX_SIZE - 30) { boxY += 3; // Adjust this value for fall speed } }
	 * 
	 * 
	 * for (Barriers barrier: this.barriers) { if (boxX > barrier.objectX + 10) {
	 * barrier.playerHasPassed = true; }
	 * 
	 * 
	 * 
	 * 
	 * if (boxX + BOX_SIZE > barrier.objectX && boxX < barrier.objectX +
	 * barrier.barrierWidth && boxY + BOX_SIZE > barrier.objectY && boxY <
	 * barrier.objectY + barrier.barrierHeight && barrier.playerHasPassed ==false) {
	 * if(barrier.isDeadly==true){ System.out.println("died to barrier"); lives =
	 * lives -1; boxX = 0; timer.stop(); if (lives == 0) {
	 * System.out.println("Game Over"); System.exit(0); } try { FileReader
	 * fileReader2 = new FileReader(); fileReader2.readFile(level, 0, lives);
	 * setVisible(false); } catch (InvalidLevelFormatException e1) {
	 * 
	 * e1.printStackTrace(); }
	 * 
	 * 
	 * } boxX = barrier.objectX - BOX_SIZE;
	 * 
	 * 
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * for (int i = 0; i < this.coins.size();i++) {
	 * 
	 * Coin coin = this.coins.get(i); if (boxX + BOX_SIZE > coin.objectX && boxX <
	 * coin.objectX + coin.coinSize ) { coins.remove(i); this.coinCount++;
	 * System.out.println("Coin Collected! You now have " + coinCount + " coins!");
	 * } i++; }
	 * 
	 * for (int i = 0; i < this.missiles.size();i++) { Missiles missile =
	 * this.missiles.get(i); if (missile.missileX < boxX + BOX_SIZE &&
	 * missile.missileX > boxX && missile.missileY > boxY && missile.missileY < boxY
	 * + BOX_SIZE ) { System.out.println("died to missile"); lives = lives -1; boxX
	 * = 0; timer.stop(); if (lives == 0) { System.out.println("Game Over");
	 * System.exit(0); } try { FileReader fileReader2 = new FileReader();
	 * fileReader2.readFile(level, 0, lives); setVisible(false); } catch
	 * (InvalidLevelFormatException e1) {
	 * 
	 * e1.printStackTrace(); } } }
	 * 
	 * 
	 * 
	 * // Ensure the box stays within the frame if (boxX > FRAME_WIDTH - BOX_SIZE) {
	 * boxX = FRAME_WIDTH - BOX_SIZE;
	 * 
	 * 
	 * if (this.level.equals("levels/level2.txt")) {
	 * System.out.println("Game Complete!!"); System.exit(0);
	 * 
	 * }else { System.out.println("End Of Level, next level!!"); boxX = 0;
	 * timer.stop();
	 * 
	 * 
	 * 
	 * try { FileReader fileReader2 = new FileReader();
	 * fileReader2.readFile("levels/level2.txt", coinCount, lives);
	 * setVisible(false); } catch (InvalidLevelFormatException e1) {
	 * 
	 * e1.printStackTrace(); } } }
	 * 
	 * if (boxY > FRAME_HEIGHT - BOX_SIZE - 30) { boxY = FRAME_HEIGHT - BOX_SIZE -
	 * 30; } if (boxY < 0 + BOX_SIZE) { boxY = 0 + BOX_SIZE; }
	 * gComp.playerYPos(boxY); repaint(); }
	 * 
	 * 
	 * 
	 * public void paint(Graphics g) { super.paint(g); g.setColor(Color.BLACK);
	 * g.fillRect(boxX, boxY, BOX_SIZE, BOX_SIZE);
	 * 
	 * 
	 * g.setColor(Color.DARK_GRAY); g.fillRect(0, 270, 1550, 50);
	 * 
	 * }
	 * 
	 * public void keyTyped(KeyEvent e) { }
	 * 
	 * public void keyPressed(KeyEvent e) { // When the space bar is pressed,
	 * trigger the jump if (e.getKeyCode() == KeyEvent.VK_SPACE) { isJumping = true;
	 * }
	 * 
	 * if (e.getKeyCode() == KeyEvent.VK_U) {
	 * 
	 * boxX = 0; timer.stop();
	 * 
	 * 
	 * 
	 * try { FileReader fileReader2 = new FileReader();
	 * fileReader2.readFile("levels/level2.txt", 0, 5); setVisible(false); } catch
	 * (InvalidLevelFormatException e1) {
	 * 
	 * e1.printStackTrace(); }
	 * 
	 * 
	 * } if (e.getKeyCode() == KeyEvent.VK_D) {
	 * 
	 * boxX = 0; timer.stop();
	 * 
	 * try { FileReader fileReader = new FileReader();
	 * fileReader.readFile("levels/level1.txt", 0, 5); setVisible(false);
	 * 
	 * } catch (InvalidLevelFormatException e1) {
	 * 
	 * e1.printStackTrace();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * if (e.getKeyCode() == KeyEvent.VK_P) { if (isPaused == true) { isPaused =
	 * false; timer.start(); }else { isPaused = true; timer.stop(); } }
	 * 
	 * 
	 * }
	 * 
	 * public void keyReleased(KeyEvent e) { }
	 * 
	 * // runApp
	 * 
	 * /** ensures: runs the application
	 * 
	 * @param args unused
	 */

	public static void main(String[] args) {

		try {
			FileReader fileReader = new FileReader();
			fileReader.readFile(0, 0, 5);
		} catch (InvalidLevelFormatException e) {
			e.printStackTrace();
		}

	}
}
