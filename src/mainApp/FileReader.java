package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileReader {
	private ArrayList<Barriers> barriers=new ArrayList<>();
	private ArrayList<Coin> coins=new ArrayList<>();

	public void readFile(String filename) throws InvalidLevelFormatException {
		String elementsConfiguration = "";

		File file = new File(filename);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();

				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == 'C') {
						System.out.println("Create coin! at " + i * 50);
						this.coins.add(new Coin(i, i));
					}
					if (line.charAt(i) == 'B') {

						System.out.println("Create Barrier at " + i * 50);
						this.barriers.add(new Barriers(i, i, false));

					}
					if (line.charAt(i) == 'E') {
						System.out.println("Create Electric Barrier");
						this.barriers.add(new Barriers(i, i, true));
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
		// MainApp mainApp = new MainApp();
		// mainApp.setVisible(true);
		// mainApp.runApp(elementsConfiguration);

	}

}
