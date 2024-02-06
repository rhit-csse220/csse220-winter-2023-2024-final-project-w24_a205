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

	Timer timer = new Timer(20, this);

	// For now, we'll have a list of predetermined barrier sizes and positions.
	// The scanner will look for the presets, add them to
	// elementsConfigurationString, then pass to runApp
	// We'll handle rotation last

	

	public void runApp(String elementsConfiguration) {
		String inputElements;
		String barElement1;
		String barElement2;
		String barElement3;
		String elBarElement1;
		String elBarElement2;
		String elBarElement3;
		String coinElement1;
		String coinElement2;
		String coinElement3;
		String coinElement4;
		String coinElement5;

		inputElements = elementsConfiguration;
		barElement1 = inputElements.substring(0, 3);
	/*if (barElement1.equals("BR1")) {
			bar1 = true;
		}
		if (barElement1.equals("BR2")) {
			bar2 = true;
		}
		if (barElement1.equals("BR3")) {
			bar3 = true;
		}
		if (barElement1.equals("BR4")) {
			bar4 = true;
		}
		if (barElement1.equals("BR5")) {
			bar5 = true;
		}
		barElement2 = inputElements.substring(3, 6);
		if (barElement2.equals("BR1")) {
			bar1 = true;
		}
		if (barElement2.equals("BR2")) {
			bar2 = true;
		}
		if (barElement2.equals("BR3")) {
			bar3 = true;
		}
		if (barElement2.equals("BR4")) {
			bar4 = true;
		}
		if (barElement2.equals("BR5")) {
			bar5 = true;
		}
		barElement3 = inputElements.substring(6, 9);
		if (barElement3.equals("BR1")) {
			bar1 = true;
		}
		if (barElement3.equals("BR2")) {
			bar2 = true;
		}
		if (barElement3.equals("BR3")) {
			bar3 = true;
		}
		if (barElement3.equals("BR4")) {
			bar4 = true;
		}
		if (barElement3.equals("BR5")) {
			bar5 = true;
		}

		elBarElement1 = inputElements.substring(9, 12);
		if (elBarElement1.equals("EL1")) {
			elBar1 = true;
		}
		if (elBarElement1.equals("EL2")) {
			elBar2 = true;
		}
		if (elBarElement1.equals("EL3")) {
			elBar3 = true;
		}
		if (elBarElement1.equals("EL4")) {
			elBar4 = true;
		}
		if (elBarElement1.equals("EL5")) {
			elBar5 = true;
		}

		elBarElement2 = inputElements.substring(12, 15);
		if (elBarElement2.equals("EL1")) {
			elBar1 = true;
		}
		if (elBarElement2.equals("EL2")) {
			elBar2 = true;
		}
		if (elBarElement2.equals("EL3")) {
			elBar3 = true;
		}
		if (elBarElement2.equals("EL4")) {
			elBar4 = true;
		}
		if (elBarElement2.equals("EL5")) {
			elBar5 = true;
		}

		elBarElement3 = inputElements.substring(15, 18);
		if (elBarElement3.equals("EL1")) {
			elBar1 = true;
		}
		if (elBarElement3.equals("EL2")) {
			elBar2 = true;
		}
		if (elBarElement3.equals("EL3")) {
			elBar3 = true;
		}
		if (elBarElement3.equals("EL4")) {
			elBar4 = true;
		}
		if (elBarElement3.equals("EL5")) {
			elBar5 = true;
		}
		
		coinElement1 = inputElements.substring(18, 21);
		if (coinElement1.equals("CO1")) {
			c1 = true;
		}
		if (coinElement1.equals("CO2")) {
			c2 = true;
		}
		if (coinElement1.equals("CO3")) {
			c3 = true;
		}
		if (coinElement1.equals("CO4")) {
			c4 = true;
		}
		if (coinElement1.equals("CO5")) {
			c5 = true;
		}
		
		coinElement2 = inputElements.substring(21, 24);
		if (coinElement2.equals("CO1")) {
			c1 = true;
		}
		if (coinElement2.equals("CO2")) {
			c2 = true;
		}
		if (coinElement2.equals("CO3")) {
			c3 = true;
		}
		if (coinElement2.equals("CO4")) {
			c4 = true;
		}
		if (coinElement2.equals("CO5")) {
			c5 = true;
		}
		
		coinElement3 = inputElements.substring(24, 27);
		if (coinElement3.equals("CO1")) {
			c1 = true;
		}
		if (coinElement3.equals("CO2")) {
			c2 = true;
		}
		if (coinElement3.equals("CO3")) {
			c3 = true;
		}
		if (coinElement3.equals("CO4")) {
			c4 = true;
		}
		if (coinElement3.equals("CO5")) {
			c5 = true;
		}
		
		coinElement4 = inputElements.substring(27, 30);
		if (coinElement4.equals("CO1")) {
			c1 = true;
		}
		if (coinElement4.equals("CO2")) {
			c2 = true;
		}
		if (coinElement4.equals("CO3")) {
			c3 = true;
		}
		if (coinElement4.equals("CO4")) {
			c4 = true;
		}
		if (coinElement4.equals("CO5")) {
			c5 = true;
		}
		
		coinElement5 = inputElements.substring(30, 33 );
		if (coinElement5.equals("CO1")) {
			c1 = true;
		}
		if (coinElement5.equals("CO2")) {
			c2 = true;
		}
		if (coinElement5.equals("CO3")) {
			c3 = true;
		}
		if (coinElement5.equals("CO4")) {
			c4 = true;
		}
		if (coinElement5.equals("CO5")) {
			c5 = true;
	}
	*/	 
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

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
				boxY += 2; // Adjust this value for fall speed
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
	/*
		if (e.getKeyCode() == KeyEvent.VK_U) {
			
			
			bar1 = false;
			bar2 = false;
			bar3 = false;
			bar4 = false;
			bar5 = false;
			elBar1 = false;
			elBar2 = false;
			elBar3 = false;
			elBar4 = false;
			elBar5 = false;
			boxX = 0;
			c1 = false;
			c2 = false;
			c3 = false;
			c4 = false;
			c5 = false;
			timer.stop();
			
			

			try {
				FileReader.readFile("levels/level2.txt");
				setVisible(false);
			} catch (InvalidLevelFormatException e1) {

				e1.printStackTrace();
			}
			

		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			bar1 = false;
			bar2 = false;
			bar3 = false;
			bar4 = false;
			bar5 = false;
			elBar1 = false;
			elBar2 = false;
			elBar3 = false;     
			elBar4 = false; 
			elBar5 = false;
			boxX = 0;
			timer.stop();

			try {
				FileReader.readFile("levels/level1.txt");
				setVisible(false);
				  
			} catch (InvalidLevelFormatException e1) {

				e1.printStackTrace();
				
				 
			}
			
			

		}
		*/
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

		SwingUtilities.invokeLater(() -> {
			
			try {
				FileReader fileReader = new FileReader();
				fileReader.readFile("levels/level3.txt");
			} catch (InvalidLevelFormatException e) {
				e.printStackTrace();
			}

		});
	} // main

}