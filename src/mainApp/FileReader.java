package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileReader {
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();

	public void readFile(String filename) throws InvalidLevelFormatException {
		

		File file = new File(filename);

		try {
			Scanner scanner = new Scanner(file);
			char lineNum = '1';
			int heightSection = 1;
			int yPosIteration = 70;
			int yPos; 
			int xPos; 
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
						System.out.println("Create coin! at " + xPos + " " + yPos);
						//this.coins.add(new Coin(xPos, yPos));
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

						System.out.println("Create Barrier at " + xPos + " " + yPos);
						this.barriers.add(new Barriers(xPos, yPos, false));
						
						
						

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
						System.out.println("Create Electric Barrier at " + xPos + " " + yPos);
						this.barriers.add(new Barriers(xPos, yPos, true));
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
		gameComp.listsIn(barriers, coins);
		
		 MainApp mainApp = new MainApp();
		 mainApp.setVisible(true);
		 mainApp.runApp(gameComp);
		
	}

}