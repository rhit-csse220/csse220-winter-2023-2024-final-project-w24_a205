package mainApp;

public abstract class Collidable {
	boolean touchingPlayer = false;
	int objectX, objectY;
	boolean isDeadly = false;
	int barrierHeight;
	int barrierWidth; 
	int coinSize;

	boolean playerHasPassed = false;

}
