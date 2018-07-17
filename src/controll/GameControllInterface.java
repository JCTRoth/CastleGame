package controll;

import logic.field.Location;

public interface GameControllInterface {
	
	/**
	 * indicates that the game is started
	 */
	void startGame();
	
	/**
	 * is called when a new Turn has to be provided
	 */
	void provideNewTurn();
	
	/**
	 * is called when a purchase has been made
	 */
	void purchaseMade();
	
	/**
	 * is called when a figure gets removed from the field
	 */
	void figureRemovedAt(Location loc);
	
	
	
}
