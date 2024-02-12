package mainApp;

public class Collidable {
boolean touchingPlayer = false;
int objectX, objectY;
boolean isDeadly = false;
int barrierHeight;
int barrierWidth;
int coinSize;
int playerX, playerY;
boolean playerHasPassed = false;


public void collision(int boxX, int boxY) {
	playerX = boxX;
	playerY = boxY;
	

	if (boxX > objectX &&  playerHasPassed ==false) {
	
	}
	
	
	
}








}
