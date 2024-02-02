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
 * @author W24_A205
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 * 
 */


public class MainApp extends JFrame implements ActionListener, KeyListener {
	ArrayList<Coin> coins;
    private int collectedCoins;
	
	private static final int FRAME_WIDTH = 1550 ;
    private static final int FRAME_HEIGHT = 300;
    private static final int BOX_SIZE = 50;
    private static final int MOVE_SPEED = 5;
    private static final int JUMP_HEIGHT = 50;
    private static final int BAR_WIDTH = 30;
    private static final int BAR_HEIGHT = 150;
	
    private int boxX;
    private int boxY;
    private boolean isJumping;

    private int elBarrierX1;
    private int elBarrierY1;
    private int elBarrierX2;
    private int elBarrierY2;
    private int elBarrierX3;
    private int elBarrierY3;
    
    //BR1
    private int barrierX1;
    private int barrierY1;
    
    //BR2
    private int barrierX2;
    private int barrierY2;
    //BR3
    private int barrierX3;
    private int barrierY3;
    
  //BR4
    private int barrierX4;
    private int barrierY4;
    
  //BR5
    private int barrierX5;
    private int barrierY5;
    
    //For now, we'll have a list of  predetermined barrier sizes and positions. 
    //The scanner will look for the presets, add them to elementsConfigurationString, then pass to runApp
    //We'll handle rotation last
    
    private void readFile(String filename){
    	String elementsConfiguration = "";
    	
    	File file=new File(filename);
    	
		try {
			Scanner scanner=new Scanner(file);
			while (scanner.hasNext()) {
				String line=scanner.nextLine();
				if (line.equals("BR1")) {
					elementsConfiguration = elementsConfiguration.concat("BR1"); 
				}
				if (line.equals("BR2")) {
					elementsConfiguration = elementsConfiguration.concat("BR2"); 
				}
			/*	
				for (int i=0;i<line.length();i++) {
					if (line.charAt(i)=='C') {
						System.out.println("Create coin!");
					}
					if (line.charAt(i)=='B') {
						System.out.println("Create Barrier");
					
					}
					if (line.charAt(i)=='E') {
						System.out.println("Create Electric Barrier");
					}
				}
				*/
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File was not found: "+filename);
			e.printStackTrace();
		}
		runApp();
		
    }
    
	private void runApp() {
		
		
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        boxX = 50;
        boxY = FRAME_HEIGHT - BOX_SIZE - 30; // Initial Y position at the bottom

        elBarrierX1 = FRAME_WIDTH - 100;
        elBarrierY1= FRAME_HEIGHT - BAR_HEIGHT - 30; // Initial Y position at the bottom

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
	        if (boxX + BOX_SIZE > elBarrierX1 && boxX < elBarrierX1 + BAR_WIDTH &&
	                boxY + BOX_SIZE > elBarrierY1) {
	            System.out.println("Game Over: You died to electrified barrier!");
	            System.exit(0);
	        }
	        if (boxX + BOX_SIZE > elBarrierX2 && boxX < elBarrierX2 + BAR_WIDTH &&
	                boxY + BOX_SIZE > elBarrierY2) {
	            System.out.println("Game Over: You died to electrified barrier!");
	            System.exit(0);
	        }
	        if (boxX + BOX_SIZE > elBarrierX3 && boxX < elBarrierX3 + BAR_WIDTH &&
	                boxY + BOX_SIZE > elBarrierY3) {
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
        g.fillRect(0,270,1550,50);


	        g.setColor(Color.RED);
	        g.fillRect(elBarrierX1, elBarrierY1, BAR_WIDTH, BAR_HEIGHT);
	       
	    
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
	 * @param args unused
	 */
	
	
	public static void main(String[] args) {
		
		
        
		SwingUtilities.invokeLater(() -> {
		MainApp mainApp = new MainApp();
		mainApp.setVisible(true);
		mainApp.readFile("levels/level1.txt"); 
		
		});
	} // main

}