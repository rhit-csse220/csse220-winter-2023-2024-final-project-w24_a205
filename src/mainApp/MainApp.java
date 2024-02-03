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
	ArrayList<Coin> coins;
	private int collectedCoins;

	private static final int FRAME_WIDTH = 1550;
	private static final int FRAME_HEIGHT = 300;
	private static final int BOX_SIZE = 40;
	private static final int MOVE_SPEED = 5;
	private static final int JUMP_HEIGHT = 40;
	private static final int STANDARD_BAR_WIDTH = 90;
	private static final int STANDARD_BAR_HEIGHT = 50;

	private int boxX;
	private int boxY;
	private boolean isJumping;

	// EL1
	private int elBarrierX1 = 300;
	private int elBarrierY1 = 200;
	private boolean elBar1 = false;

	// EL2
	private int elBarrierX2 = 600;
	private int elBarrierY2 = 50;
	private boolean elBar2 = false;

	// EL3
	private int elBarrierX3 = 900;
	private int elBarrierY3 = 200;
	private boolean elBar3 = false;

	// EL4
	private int elBarrierX4 = 1200;
	private int elBarrierY4 = 50;
	private boolean elBar4 = false;

	// EL5
	private int elBarrierX5 = 1400;
	private int elBarrierY5 = 200;
	private boolean elBar5 = false;

	// BR1
	private int barrierX1 = 300;
	private int barrierY1 = 50;
	private boolean bar1 = false;

	// BR2
	private int barrierX2 = 600;
	private int barrierY2 = 200;
	private boolean bar2 = false;

	// BR3
	private int barrierX3 = 900;
	private int barrierY3 = 50;
	private boolean bar3 = false;

	// BR4
	private int barrierX4 = 1200;
	private int barrierY4 = 200 ;
	private boolean bar4 = false;

	// BR5
	private int barrierX5 = 1400;
	private int barrierY5 = 50;
	private boolean bar5 = false;

	// For now, we'll have a list of predetermined barrier sizes and positions.
	// The scanner will look for the presets, add them to
	// elementsConfigurationString, then pass to runApp
	// We'll handle rotation last

	private void readFile(String filename) throws InvalidLevelFormatException {
		String elementsConfiguration = "";

		File file = new File(filename);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				if (line.length()>3) {
					throw new InvalidLevelFormatException(line.length(),3);
				}
				elementsConfiguration = elementsConfiguration.concat(line);
				/*
				 * for (int i=0;i<line.length();i++) { if (line.charAt(i)=='C') {
				 * System.out.println("Create coin!"); } if (line.charAt(i)=='B') {
				 * System.out.println("Create Barrier");
				 * 
				 * } if (line.charAt(i)=='E') { System.out.println("Create Electric Barrier"); }
				 * }
				 */
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File was not found: " + filename);
			e.printStackTrace();
		}
		runApp(elementsConfiguration);

	}

	private void runApp(String elementsConfiguration) {
		String inputElements;
		String barElement1;
		String barElement2;
		String barElement3;
		String elBarElement1;
		String elBarElement2;
		String elBarElement3;

		inputElements = elementsConfiguration;
		barElement1 = inputElements.substring(0, 3);
		if (barElement1.equals("BR1")) {
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

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		boxX = 50;
		boxY = FRAME_HEIGHT - BOX_SIZE - 30; // Initial Y position at the bottom

		Timer timer = new Timer(20, this);
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

		// Check for collision with the barrier
		if (bar1 == true && boxX + BOX_SIZE > barrierX1 && boxX < barrierX1 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > barrierY1 && boxY < barrierY1 + STANDARD_BAR_HEIGHT) {
			boxX = barrierX1 - BOX_SIZE;
		}
		if (bar2 == true && boxX + BOX_SIZE > barrierX2 && boxX < barrierX2 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > barrierY2 && boxY < barrierY2 + STANDARD_BAR_HEIGHT) {
			boxX = barrierX2 - BOX_SIZE;
		}
		if (bar3 == true && boxX + BOX_SIZE > barrierX3 && boxX < barrierX3 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > barrierY3 && boxY < barrierY3 + STANDARD_BAR_HEIGHT) {
			boxX = barrierX3 - BOX_SIZE;
		}
		if (bar4 == true && boxX + BOX_SIZE > barrierX4 && boxX < barrierX4 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > barrierY4 && boxY < barrierY4 + STANDARD_BAR_HEIGHT) {
			boxX = barrierX4 - BOX_SIZE;
		}
		if (bar5 == true && boxX + BOX_SIZE > barrierX5 && boxX < barrierX5 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > barrierY5 && boxY < barrierY5 + STANDARD_BAR_HEIGHT) {
			boxX = barrierX5 - BOX_SIZE;
		}

		/////////////

		if (elBar1 == true && boxX + BOX_SIZE > elBarrierX1 && boxX < elBarrierX1 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > elBarrierY1 && boxY < elBarrierY1 + STANDARD_BAR_HEIGHT) {
			System.out.println("Game Over: You died to electrified barrier!");
			System.exit(0);

		}

		if (elBar2 == true && boxX + BOX_SIZE > elBarrierX2 && boxX < elBarrierX2 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > elBarrierY2 && boxY < elBarrierY2 + STANDARD_BAR_HEIGHT) {
			System.out.println("Game Over: You died to electrified barrier!");
			System.exit(0);
		}
		if (elBar3 == true && boxX + BOX_SIZE > elBarrierX3 && boxX < elBarrierX3 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > elBarrierY3 && boxY < elBarrierY3 + STANDARD_BAR_HEIGHT) {
			System.out.println("Game Over: You died to electrified barrier!");
			System.exit(0);
		}
		if (elBar4 == true && boxX + BOX_SIZE > elBarrierX4 && boxX < elBarrierX4 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > elBarrierY4 && boxY < elBarrierY4 + STANDARD_BAR_HEIGHT) {
			System.out.println("Game Over: You died to electrified barrier!");
			System.exit(0);
		}

		if (elBar5 == true && boxX + BOX_SIZE > elBarrierX5 && boxX < elBarrierX5 + STANDARD_BAR_WIDTH
				&& boxY + BOX_SIZE > elBarrierY5 && boxY < elBarrierY5 + STANDARD_BAR_HEIGHT) {
			System.out.println("Game Over: You died to electrified barrier!");
			System.exit(0);
		}

		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(boxX, boxY, BOX_SIZE, BOX_SIZE);

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 270, 1550, 50);

		if (bar1 == true) {
			g.setColor(Color.GREEN);
			g.fillRect(barrierX1, barrierY1, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (bar2 == true) {
			g.setColor(Color.GREEN);
			g.fillRect(barrierX2, barrierY2, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (bar3 == true) {
			g.setColor(Color.GREEN);
			g.fillRect(barrierX3, barrierY3, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (bar4 == true) {
			g.setColor(Color.GREEN);
			g.fillRect(barrierX4, barrierY4, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (bar5 == true) {
			g.setColor(Color.GREEN);
			g.fillRect(barrierX5, barrierY5, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}

		///////////

		if (elBar1 == true) {
			g.setColor(Color.RED);
			g.fillRect(elBarrierX1, elBarrierY1, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (elBar2 == true) {
			g.setColor(Color.RED);
			g.fillRect(elBarrierX2, elBarrierY2, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (elBar3 == true) {
			g.setColor(Color.RED);
			g.fillRect(elBarrierX3, elBarrierY3, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (elBar4 == true) {
			g.setColor(Color.RED);
			g.fillRect(elBarrierX4, elBarrierY4, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}
		if (elBar5 == true) {
			g.setColor(Color.RED);
			g.fillRect(elBarrierX5, elBarrierY5, STANDARD_BAR_WIDTH, STANDARD_BAR_HEIGHT);
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		// When the space bar is pressed, trigger the jump
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			isJumping = true;
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

	public static void main(String[] args)  {

		SwingUtilities.invokeLater(() -> {
			MainApp mainApp = new MainApp();
			mainApp.setVisible(true);
			try {
				mainApp.readFile("levels/level2.txt");
			} catch (InvalidLevelFormatException e) {
				e.printStackTrace();
			}

		});
	} // main

}