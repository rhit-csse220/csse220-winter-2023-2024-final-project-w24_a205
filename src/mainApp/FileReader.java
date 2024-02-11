package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileReader {
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();
	private ArrayList<Missiles> missiles=new ArrayList<>();
	private ArrayList<Collidable> collidables=new ArrayList<>();

public void readFile(String filename) throws InvalidLevelFormatException {
		

		File file = new File(filename);

		try {
			Scanner scanner = new Scanner(file);
			char lineNum = '1';
			int heightSection = 1;
			int yPosIteration = 50;
			int yPos; 
			int xPos; 
			char rotR = 'R';
			char rotL = 'L';
			char rotNull = 'N';
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == 'C') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
						}else if(line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						}else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;
						
						this.coins.add(new Coin(xPos, yPos));
					}
					
					if (line.charAt(i) == 'B') {
							if (line.charAt(0) == '1') {
								lineNum = '1';
								heightSection = 1;
								
							}else if(line.charAt(0) == '2') {
								lineNum = '2';
								heightSection = 2;
							}else {
								lineNum = '3';
								heightSection = 3;
							}
							xPos = 0;
							xPos = i * 50;
							yPos = 0;
							yPos = heightSection * yPosIteration;

						
						this.barriers.add(new Barriers(xPos, yPos, false, rotNull));
						
						
						

					}
					if (line.charAt(i) == 'E') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
							
						}else if(line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						}else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;
						
						this.barriers.add(new Barriers(xPos, yPos, true, rotNull));
					}
					
				
				if (line.charAt(i) == 'R') {
					if (line.charAt(0) == '1') {
						lineNum = '1';
						heightSection = 1;
						
					}else if(line.charAt(0) == '2') {
						lineNum = '2';
						heightSection = 2;
					}else {
						lineNum = '3';
						heightSection = 3;
					}
					xPos = 0;
					xPos = i * 50;
					yPos = 0;
					yPos = heightSection * yPosIteration;
					
					this.barriers.add(new Barriers(xPos, yPos, true, rotR));
				}
				if (line.charAt(i) == 'L') {
					if (line.charAt(0) == '1') {
						lineNum = '1';
						heightSection = 1;
						
					}else if(line.charAt(0) == '2') {
						lineNum = '2';
						heightSection = 2;
					}else {
						lineNum = '3';
						heightSection = 3;
					}
					xPos = 0;
					xPos = i * 50;
					yPos = 0;
					yPos = heightSection * yPosIteration;
				
					this.barriers.add(new Barriers(xPos, yPos, false, rotL));
				}
					
					if (line.charAt(i) == 'M') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
							
						}else if(line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						}else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;
						
						this.missiles.add(new Missiles(xPos, yPos, false));
					}
					if (line.charAt(i) == 'm') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
							
						}else if(line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						}else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;
						
						this.missiles.add(new Missiles(xPos, yPos, true));
					}
				}
				if (line.length() > 31) {
					throw new InvalidLevelFormatException(line.length(), 31);
				}
				// elementsConfiguration = elementsConfiguration.concat(line);

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File was not found: " + filename);
			e.printStackTrace();
		}
		
		
		GameComponent gameComp = new GameComponent();
		gameComp.listsIn(barriers, coins, missiles);
		
		
		 MainApp mainApp = new MainApp();
		 mainApp.setVisible(true);
		 mainApp.runApp(gameComp, filename, barriers);
		
	}

}