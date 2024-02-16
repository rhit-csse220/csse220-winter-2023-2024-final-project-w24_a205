package mainApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameAdvanceListener implements ActionListener {
	private GameComponent gameComponent;

	public GameAdvanceListener(GameComponent gameComponent) {
		this.gameComponent = gameComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		advanceOneTick();
	}

	public void advanceOneTick() { 
		this.gameComponent.updateState();
		this.gameComponent.drawScreen();
	}

}
