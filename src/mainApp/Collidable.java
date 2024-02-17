package mainApp;
/**
 * Class: Collidable
 * 
 * @author W24_A205 <br>
 *         Purpose: Ensures that every object can be properly collided with <br>
 *         Restrictions: None
 * 
 */
abstract class Collidable {
	boolean touchingPlayer = false;
	int objectX, objectY;
	boolean isDeadly = false;
	int barrierHeight;
	int barrierWidth; 
	int coinSize;


}
