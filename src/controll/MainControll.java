package controll;

import gui.GuiField;
import gui.MainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.GameLogic;
import logic.content.Player;

public class MainControll {

	private GUIControll guiControll;
	private GameControll gameControll;
	private GameLogic gameLogic;
	
	private Player player1 = new Player("Spieler1");
	private Player player2 = new Player("Spieler2");

	
	public MainControll() {
		gameLogic = new GameLogic();
		gameLogic.addPlayer(player1);
		gameLogic.addPlayer(player2);
		
		//TEST
		gameLogic.setTestField();
		
		guiControll = new GUIControll(gameLogic);
		guiControll.setPlayers(player1, player2);
		gameControll = new GameControll(gameLogic, guiControll);
		
		//ADDING OF GAME CONTROLLS
		gameLogic.addGameControll(gameControll);
		gameLogic.addGameControll(new GameControll(gameLogic, guiControll));
		
		gameLogic.startGame(player1);
		gameLogic.startGame(player2);

	}
	
	public static void main(String[] args){
		new MainControll();
	}

}
