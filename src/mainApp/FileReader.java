package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class: FileReader
 * 
 * @author W24_A205 <br>
 *         Purpose: Reads the level files and creates objects accordingly <br>
 *         Restrictions: None
 * 
 */
public class FileReader {
	private ArrayList<Barriers> barriers = new ArrayList<>();
	private ArrayList<Coin> coins = new ArrayList<>();
	private ArrayList<GravityPowerUp> gravPowerUps = new ArrayList<>();
	private ArrayList<Missiles> missiles = new ArrayList<>();

	private String filename;

	/**
	 * ensures that appropriate level files are read and objects are created and
	 * assigned to ArrayLists
	 * 
	 * @param level
	 * @param coinCount
	 * @param lives
	 * @throws InvalidLevelFormatException
	 */
	public void readFile(int level, int coinCount, int lives) throws InvalidLevelFormatException {

		ArrayList<String> levels = new ArrayList<String>();

		levels.add("levels/level1.txt");
		levels.add("levels/level2.txt");
		levels.add("levels/level3.txt");
		levels.add("levels/level4.txt");
		levels.add("levels/level5.txt");
		levels.add("levels/BONUSLEVEL.txt");

		this.filename = levels.get(level);

		File file = new File(levels.get(level));

		try {
			Scanner scanner = new Scanner(file);
			char lineNum = '1';
			int heightSection = 1;
			int yPosIteration = 47;
			int yPos;
			int xPos;
			char rotR = 'R';
			char rotL = 'L';
			char rotNull = 'N';
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				for (int i = 0; i < line.length(); i++) {
					char test = line.charAt(i);
					if (line.charAt(i) == 'C') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;

						this.coins.add(new Coin(xPos, yPos));
					}
					if (line.charAt(i) == 'P') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;
						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;

						this.gravPowerUps.add(new GravityPowerUp(xPos, yPos));
					}

					if (line.charAt(i) == 'B') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;

						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
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

						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
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

						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;

						this.barriers.add(new Barriers(xPos, yPos, true, rotR));
					}

					if (line.charAt(i) == 'M') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;

						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;

						this.missiles.add(new Missiles(yPos, false));
					}
					if (line.charAt(i) == 'm') {
						if (line.charAt(0) == '1') {
							lineNum = '1';
							heightSection = 1;

						} else if (line.charAt(0) == '2') {
							lineNum = '2';
							heightSection = 2;
						} else {
							lineNum = '3';
							heightSection = 3;
						}
						xPos = 0;
						xPos = i * 50;
						yPos = 0;
						yPos = heightSection * yPosIteration;

						this.missiles.add(new Missiles(yPos, true));
					}

				}
				if (line.length() > 31) {
					throw new InvalidLevelFormatException(line.length(), 31);
				}

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File was not found: " + filename);
			e.printStackTrace();
		}
		GameComponent component = new GameComponent(level, coinCount, lives);
		component.listsIn(barriers, coins, missiles, gravPowerUps);

		MainApp main = new MainApp();
		main.MainApp(component, coinCount, lives, level);

		component.mainIn(main);

	}

}