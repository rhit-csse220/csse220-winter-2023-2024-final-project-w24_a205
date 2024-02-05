 package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	static void readFile(String filename) throws InvalidLevelFormatException {
		String elementsConfiguration = "";

		File file = new File(filename);

		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				if (line.length() > 3) {
					throw new InvalidLevelFormatException(line.length(), 3);
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
		MainApp mainApp = new MainApp();
		mainApp.setVisible(true);
		mainApp.runApp(elementsConfiguration);

	}

}
