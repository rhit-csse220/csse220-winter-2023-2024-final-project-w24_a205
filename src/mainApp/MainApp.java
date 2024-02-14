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
	private boolean isInSlowMo;

	public void MainApp(GameComponent component, int coins, int lives, int level) {
		this.component = component;
		this.lives = lives;
		this.coins = coins;

		if (lives < 0) {

			System.out.println("Game over: out of lives");
			System.exit(0);

		}
		JFrame frame = new JFrame("Arcade Game");
		frame.setSize(1550, 300);

		JPanel panel = new JPanel();

		JButton jumpButton = new JButton("Game On!");

		JButton pauseButton = new JButton("Pause = 'P'");

		JButton slowMoButton = new JButton("Toggle Slow Mo = 'S'");

		JButton upLevelButton = new JButton("UpLevel = 'U'");
		JButton downLevelButton = new JButton("DownLevel = 'D'");

		JButton livesButton = new JButton("Lives left: " + lives);

		JButton levelButton = new JButton("Level: " + (level + 1));

		JButton coinsButton = new JButton("Coins: " + coins);

		panel.add(jumpButton);
		panel.add(pauseButton);
		panel.add(slowMoButton);
		panel.add(upLevelButton);
		panel.add(downLevelButton);
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

				if (e.getKeyCode() == KeyEvent.VK_U) {
					System.out.println("UP A LEVEL");
					int lvl = level + 1;
					if (lvl > 4) {
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

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame = frame;
	}

	public void endLevel() {
		timer.stop();
		frame.setVisible(false);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Game Menu");
		frame.setSize(500, 500);

		JPanel panel = new JPanel();

		JButton GameStart = new JButton("Press 'S' To Start Game");
		JButton GameExit = new JButton("Press 'E' To Exit");

		panel.add(GameStart);
		panel.add(GameExit);

		frame.add(panel);

		GameStart.addKeyListener(new KeyListener() {

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

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * try { FileReader fileReader = new FileReader(); fileReader.readFile(0, 0, 5);
		 * } catch (InvalidLevelFormatException e) { e.printStackTrace(); }
		 */

	}
}
