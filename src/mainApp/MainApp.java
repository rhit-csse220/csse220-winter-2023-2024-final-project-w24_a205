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

public class MainApp extends JFrame implements ActionListener, KeyListener {

	private static final int FRAME_WIDTH = 1550;
	private static final int FRAME_HEIGHT = 300;
	private static final int BOX_SIZE = 40;
	private static final int MOVE_SPEED = 5;
	private static final int JUMP_HEIGHT = 40;
	private boolean isPaused = false;

	private int boxX;
	private int boxY;
	private boolean isJumping;
	private GameComponent gComp;

	Timer timer = new Timer(20, this);
	

	

	public void runApp(GameComponent gComp) {
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Jetpack Joyride!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		

		add(gComp); 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		
		/*
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	*/
		this.gComp = gComp; 
		boxX = 50;
		boxY = FRAME_HEIGHT - BOX_SIZE - 30; // Initial Y position at the bottom

		timer.restart();
		timer.start();

		addKeyListener(this);
		setFocusable(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		// Move the box to the right
		boxX += MOVE_SPEED;

		// If jumping, move the box upward
		if (isJumping) {
			boxY -= JUMP_HEIGHT;
			isJumping = false; // Stop jumping after one jump
		} else {
			// Otherwise, simulate gravity by moving the box downward
			if (boxY < FRAME_HEIGHT - BOX_SIZE - 30) {
				boxY += 3; // Adjust this value for fall speed
			}
		}
		
		// Ensure the box stays within the frame
		if (boxX > FRAME_WIDTH - BOX_SIZE) {
			boxX = FRAME_WIDTH - BOX_SIZE;
			System.out.println("End Of Level!!");
			System.exit(0);
		}

		if (boxY > FRAME_HEIGHT - BOX_SIZE - 30) {
			boxY = FRAME_HEIGHT - BOX_SIZE - 30;
		}
		if (boxY < 0 + BOX_SIZE) {
			boxY = 0 + BOX_SIZE;
		}
		gComp.playerYPos(boxY);
		repaint();
	}
	
	

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(boxX, boxY, BOX_SIZE, BOX_SIZE);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 270, 1550, 50);

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		// When the space bar is pressed, trigger the jump
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isJumping = true;
		}
	
		if (e.getKeyCode() == KeyEvent.VK_U) {
			
			boxX = 0;
			timer.stop();
			
			

			try {
				FileReader fileReader2 = new FileReader();
				fileReader2.readFile("levels/level2.txt");
				setVisible(false);
			} catch (InvalidLevelFormatException e1) {

				e1.printStackTrace();
			}
			

		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			
			boxX = 0;
			timer.stop();

			try {
				FileReader fileReader = new FileReader();
				fileReader.readFile("levels/level1.txt");
				setVisible(false);
				  
			} catch (InvalidLevelFormatException e1) {

				e1.printStackTrace();
				
				 
			}
			
			

		}
		
		if (e.getKeyCode() == KeyEvent.VK_P) {
			if (isPaused == true) {
				isPaused = false;
				timer.start();
			}else {
				isPaused = true; 
			timer.stop();
		}
		}
		

	}

	public void keyReleased(KeyEvent e) {
	}

	// runApp

	/**
	 * ensures: runs the application
	 * 
	 * @param args unused
	 */

	public static void main(String[] args) {

	/*
		JFrame frame = new JFrame();

		frame.setSize(1550, 300);
		frame.setTitle("Game Time!!");

		frame.add(new GameComponent()); 

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		*/
		
		

		SwingUtilities.invokeLater(() -> {
			
			try {
				FileReader fileReader = new FileReader();
				fileReader.readFile("levels/level1.txt");
			} catch (InvalidLevelFormatException e) {
				e.printStackTrace();
			}

		});
		
		
	}
}

