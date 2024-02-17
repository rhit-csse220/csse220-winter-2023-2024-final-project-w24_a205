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
	private static final int DELAY = 50;
	private boolean isJumping;
	private Timer timer;
	private JFrame frame;
	private JButton livesButton;
	private JButton coinsButton;
	private JPanel panel;
	private boolean isPaused = false;
	private int lives;
	private int coins;
	private GameComponent component;
	private int level;
	private boolean isInSlowMo;

	/**
	 * ensures that the level window is created and that all the key presses do
	 * their appropriate actions
	 * 
	 * @param component
	 * @param coins
	 * @param lives
	 * @param level
	 */
	public void MainApp(GameComponent component, int coins, int lives, int level) {
		this.component = component;
		this.lives = lives;
		this.coins = coins;

		if (lives < 0) {

			System.out.println("Game over: out of lives");
			System.exit(0);

		}
		JFrame frame = new JFrame();
		frame.setTitle("Barry's Adventure");

		frame.setSize(1550, 300);
		frame.setResizable(false);

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon image = new ImageIcon("barry.png");
		frame.setIconImage(image.getImage());

		frame.getContentPane().setBackground(new Color(204, 245, 255));

		JPanel panel = new JPanel();
		panel.setBackground(Color.gray);

		JButton jumpButton = new JButton("Game On!");

		JButton pauseButton = new JButton("Pause = 'P'");
		JButton exitButton = new JButton("Exit to Menu = 'E'");

		JButton slowMoButton = new JButton("Toggle Slow Mo = 'S'");

		JButton upLevelButton = new JButton("UpLevel = 'U'");
		JButton downLevelButton = new JButton("DownLevel = 'D'");

		JButton livesButton = new JButton("Lives left: " + lives);

		JButton levelButton = new JButton("Level: " + (level + 1));

		if (level == 5) {
			levelButton.setText("Bonus Level!");
		}
		JButton coinsButton = new JButton("Coins: " + coins);

		panel.add(jumpButton);
		panel.add(pauseButton);
		panel.add(slowMoButton);
		panel.add(upLevelButton);
		panel.add(downLevelButton);
		panel.add(exitButton);
		panel.add(livesButton);
		panel.add(coinsButton);
		panel.add(levelButton);

		frame.add(panel, BorderLayout.SOUTH);

		frame.add(component, BorderLayout.CENTER);

		GameAdvanceListener advanceListener = new GameAdvanceListener(component);

		Timer timer = new Timer(DELAY, advanceListener);
		this.timer = timer;
		timer.start();

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
				if (e.getKeyCode() == KeyEvent.VK_E) {
					mainApp.MainApp.main(null);
					timer.stop();
					frame.setVisible(false);

				}

				if (e.getKeyCode() == KeyEvent.VK_U) {
					System.out.println("UP A LEVEL");
					int lvl = level + 1;
					if (lvl > 5) {
						lvl = 5;
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
					int lvl = level - 1;
					if (lvl < 0) {
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

				}
				if (e.getKeyCode() == KeyEvent.VK_P) {
					{
						if (isPaused == true) {
							isPaused = false;
							timer.start();
						} else {
							isPaused = true;
							timer.stop();

						}
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_S) {
					{
						if (isInSlowMo == true) {
							isInSlowMo = false;
							timer.setDelay(DELAY);
						} else {
							isInSlowMo = true;
							timer.setDelay(100);

						}
					}

				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		this.frame = frame;

	}

	/**
	 * ensures that the game window is closed when a level ends
	 */
	public void endLevel() {
		timer.stop();
		frame.setVisible(false);
	}

	/**
	 * ensures that a unique menu is displayed asking if you want to play again or
	 * quit
	 */
	public void endMenu(int coins, int lives) {
		
		int finalCoinCount = coins;
		int finalLivesLeft = lives;
		timer.stop();
		frame.setVisible(false);

		JFrame f2 = new JFrame("End Menu");
		f2.setSize(300, 300);
		JButton playAgain = new JButton("Press 'S' To Play Again");
		JButton gameExit = new JButton("Press 'E' To Exit");
		JButton finalStats = new JButton("Lives Left: " + lives + ", Coins Collected: " + coins);
		JPanel panel = new JPanel();
		JPanel panelStats = new JPanel();
		

		panel.add(playAgain);
		panel.add(gameExit);
		panelStats.add(finalStats);
		
		f2.add(panelStats, BorderLayout.SOUTH); 

		f2.add(panel, BorderLayout.CENTER);
		

		ImageIcon barry = new ImageIcon("barry.png");
		f2.setIconImage(barry.getImage());

		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setResizable(false);
		f2.setVisible(true);

		playAgain.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_S) {
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(0, 0, 5);
					} catch (InvalidLevelFormatException e1) {
						e1.printStackTrace();
					}

				}
				if (e.getKeyCode() == KeyEvent.VK_E) {
					System.exit(0);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	/**
	 * ensures that the starting menu appears when the game is opened, and that the
	 * game runs if 'S' is clicked
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyPanel p2;

		JFrame frame = new JFrame("Game Menu");
		frame.setSize(1550, 300);

		p2 = new MyPanel();
		JPanel panel = new JPanel();

		panel.setBackground(Color.GRAY);

		JButton gameStart = new JButton("Press 'S' To Start Game");
		JButton gameExit = new JButton("Press 'E' To Exit");

		panel.add(gameStart);
		panel.add(gameExit);
		frame.add(p2, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);

		gameStart.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_S) {
					try {
						FileReader fileReader = new FileReader();
						fileReader.readFile(0, 0, 5);
					} catch (InvalidLevelFormatException e1) {
						e1.printStackTrace();
					}
					frame.setVisible(false);

				}
				if (e.getKeyCode() == KeyEvent.VK_E) {
					System.exit(0);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

		});

		ImageIcon barry = new ImageIcon("barry.png");
		frame.setIconImage(barry.getImage());

		frame.setResizable(false);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}
