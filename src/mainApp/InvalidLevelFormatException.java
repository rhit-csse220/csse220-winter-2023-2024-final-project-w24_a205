package mainApp;

public class InvalidLevelFormatException extends Exception {
private int actual, allowed;
	
	public InvalidLevelFormatException(int actual, int allowed) {
		this.actual=actual;
		this.allowed=allowed;
	}
	
	@Override
	public String getMessage() {
		return "InvalidLevelFormatException: allowed " + this.allowed+", but was actually "+this.actual;
	}


}
