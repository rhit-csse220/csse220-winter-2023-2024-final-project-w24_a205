package mainApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LevelReadWrite {
	
	
	
	public void test(String filename)throws FileNotFoundException, IOException, InvalidLevelFormatException  {
		FileReader f1 = new FileReader(filename);
		Scanner s1 = new Scanner(f1);
		
		while(s1.hasNext()) {
			String name = s1.next();
			if (name.length()>3) {
				throw new InvalidLevelFormatException(name.length(),3);
			}
			try {
				String testString = s1.nextLine();
				
			
			}
			finally{
				System.out.println("check");
			}
		
	} // end while
	s1.close();
	f1.close();
	System.out.println("Testing Completed!");
} // computeBestScore

		
	
	
	private void runApp() throws InvalidLevelFormatException  {
		Scanner s = new Scanner(System.in);
		
		boolean isDone = false;
		while ( !isDone ) {
			String filename = null;
			System.out.println("What file should I load?  (e.g. GameLevel1.txt)");
			filename = s.nextLine();
			try {
				test(filename);
				isDone = true;
			} catch (FileNotFoundException e) {
				System.err.println("File not found. Please try again.");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		s.close();		
		System.out.println("App terminated");
	} // runIt
	
	//-------------------------------------------------------------------------------

	public static void main(String[] args) throws InvalidLevelFormatException  {
		LevelReadWrite app = new LevelReadWrite();
		app.runApp();
	} // main
	}

