package controll;

import logic.GameLogic;
import logic.field.Location;
import logic.field.PlayingFieldFigure;

public class GameControll implements GameControllInterface {

	private GameLogic gameLogic;
	private GUIControll guiControll;

	public GameControll(GameLogic gameLogic, GUIControll guiControll) {
		this.gameLogic = gameLogic;
		this.guiControll = guiControll;

		initiateContent();
	}

	private void initiateContent() {

	}

	public void provideNewTurn() {

	}

	public void purchaseMade() {
		guiControll.refreshGold();
	}

	@Override
	public void startGame() {
		guiControll.refreshGold();

	}

	@Override
	public void figureRemovedAt(Location loc) {
		guiControll.removeFigureAt(loc);

	}

}
