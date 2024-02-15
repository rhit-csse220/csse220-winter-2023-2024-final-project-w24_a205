package mainApp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel{

	final int PANEL_WIDTH = 500;
	final int PANEL_HEIGHT = 500;
	Image menu;
	Timer timer;
	
	
	MyPanel(){
		
		this.setBackground(Color.DARK_GRAY);
		menu = new ImageIcon("BarryMenu.png").getImage();	
		//backgroundImage = new ImageIcon("space.png").getImage();
		
	}

	public void paint(Graphics g) {
		
		super.paint(g); 
		
		Graphics2D g2D = (Graphics2D) g;
		
		
		g2D.drawImage(menu, 516, 0, null);
	}
	
	
		
	
}