package mainApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class: MainApp
 * @author W24_A205
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * <br>Restrictions: None
 * 
 */


public class MainApp extends JFrame implements ActionListener, KeyListener {
	
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

    private int barrierX;
    private int barrierY;
    
	private void runApp() {
		 
	        setSize(FRAME_WIDTH, FRAME_HEIGHT);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false);

	        boxX = 50;
	        boxY = FRAME_HEIGHT - BOX_SIZE - 30; // Initial Y position at the bottom

	        barrierX = FRAME_WIDTH - 100;
	        barrierY = FRAME_HEIGHT - BAR_HEIGHT - 30; // Initial Y position at the bottom

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
	        if (boxX + BOX_SIZE > barrierX && boxX < barrierX + BAR_WIDTH &&
	                boxY + BOX_SIZE > barrierY) {
	            System.out.println("Game Over");
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
	        g.fillRect(barrierX, barrierY, BAR_WIDTH, BAR_HEIGHT);
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
		mainApp.runApp();
		});
	} // main

}