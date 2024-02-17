package mainApp;

/**
 * Class: InvalidLevelFormatException
 * 
 * @author W24_A205 <br>
 *         Purpose: Recognizes when an invalid level is put through the
 *         FileReader class <br>
 *         Restrictions: None
 * 
 */
public class InvalidLevelFormatException extends Exception {
	private int actual, allowed;

	public InvalidLevelFormatException(int actual, int allowed) {
		this.actual = actual;
		this.allowed = allowed;
	}

	/**
	 * ensures that a message is returned if the exception is triggered
	 */
	@Override
	public String getMessage() {
		return "InvalidLevelFormatException: allowed " + this.allowed + ", but was actually " + this.actual;
	}

}
