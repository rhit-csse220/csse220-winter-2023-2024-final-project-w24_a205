package mainApp;

/**
 * Class: GameAdvanceListener
 * 
 * @author W24_A205 <br>
 *         Purpose: Calls the GameComponent class to update the game <br>
 *         Restrictions: None
 * 
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameAdvanceListener implements ActionListener {
	private GameComponent gameComponent;

	public GameAdvanceListener(GameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	/**
	 * ensures that the game updates every tick
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		advanceOneTick();
	}

	/**
	 * ensures that GameComponent is called to update the screen and redraw it
	 */
	public void advanceOneTick() {

		this.gameComponent.updateState();
		this.gameComponent.drawScreen();
	}

}
