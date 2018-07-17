package logic;

import gui.FigureField;

import java.util.ArrayList;
import java.util.LinkedList;

import controll.GameControll;
import controll.GameControllInterface;

import logic.content.FightResult;
import logic.content.Figure;
import logic.content.FigureKind;
import logic.content.Move;
import logic.content.Player;
import logic.content.Territory;
import logic.content.TerritoryKind;
import logic.field.Location;
import logic.field.PlayingFieldFigure;
import logic.field.PlayingFieldTerritory;

public class GameLogic {
	
	private int ROW_COUNT = PlayingFieldFigure.ROW_COUNT;
	private int COLUMN_COUNT = PlayingFieldFigure.COLUMN_COUNT;
	public static int DEFAULT_GOLD_AMOUNT = 1000;
	public static double LEVEL_FACTOR = 1.5;
	public static double CLASS_TYPE_FACTOR = 2.0;
	
	private PlayingFieldFigure fieldFigure;
	private PlayingFieldTerritory fieldTerritory;
	private LinkedList<GameControllInterface> gameControllList = 
			new LinkedList<GameControllInterface>();
	private Figure[][] fFigure;
	private Territory[][] fTerritory;
	private LinkedList<Player> playerList = new LinkedList<Player>(); 
	private ArrayList<Player> playersReady = new ArrayList<Player>();
	private int numberOfPlayers = 2;
	private Player playerHavingTurn;
	private ArrayList<Figure> movableFigures;
	
	public boolean addPlayer(Player player){
		if (numberOfPlayers <= playerList.size())
			return false;
		else{
			playerList.add(player);
			return true;
		}
	}
	
	public boolean addGameControll(GameControll controll){
		gameControllList.add(controll);
		return true;
	}
	
	/**
	 * returns the gameControll depending on the player
	 * @param player owner of gameControll
	 * @return gameControll
	 */
	private GameControll getGameControll(Player player){
		int index = 0;
		
		for (Player p : playerList){
			if (p == player)
				return (GameControll) gameControllList.get(index);
			index++;
		}
		
		return (GameControll) gameControllList.get(index);
	}
	
	public void setTestField(){
		//TEST
		Figure lightKnight = new Figure(playerList.get(0), FigureKind.LIGHT_KNIGHT);
		lightKnight.setKing(true);
		lightKnight.setHealth(20);
		fieldFigure.setField(1, 1, lightKnight);
		fieldFigure.setField(1, 2, new Figure(playerList.get(0), FigureKind.SWORDMAN));
		fieldFigure.setField(1, 3, new Figure(playerList.get(0), FigureKind.ARCHER));
		fieldFigure.setField(4, 3, new Figure(playerList.get(1), FigureKind.LIGHT_KNIGHT));
		fieldFigure.setField(4, 7, new Figure(playerList.get(1), FigureKind.ARCHER));
		
	}
	
	public GameLogic() {
		fieldFigure = new PlayingFieldFigure();
		fieldTerritory = new PlayingFieldTerritory();
		
		fFigure = fieldFigure.getField();
		fTerritory = fieldTerritory.getField();
	}
	
	public PlayingFieldFigure getFigureField(){
		return fieldFigure;
	}
	
	public PlayingFieldTerritory getTerritoryField(){
		return fieldTerritory;
	}
	
	/**
	 * perform a move on the playing field
	 * @param move performed move
	 * @return true if move is legit else false
	 */
	public Boolean move(Move move, Player player){
		int fromC = move.getFrom().getColumn();
		int fromR = move.getFrom().getRow();
		int toC = move.getTo().getColumn();
		int toR = move.getTo().getRow();
		if (hasTurn(player)){
			if (isInRange(move, fieldFigure.getFigureAt(move.getFrom()))){ //move is in field
				if (fieldFigure.isNull(move.getTo())){ //field is empty -> standard move
					handleLeftOverMoves(move, fieldFigure.getFigureAt(move.getFrom()));
					fieldFigure.save(move.getFrom());
					fieldFigure.setField(move.getFrom(), null);
					fieldFigure.load(move.getTo());
					return true;
				}
				//enemy figure -> fight
				if (fieldFigure.getPlayerAt(move.getTo()) != fieldFigure.getPlayerAt(move.getFrom())){
					Figure fig1 = fieldFigure.getFigureAt(move.getFrom());
					Figure fig2 = fieldFigure.getFigureAt(move.getTo());
					if (attack(fig1, fig2) == FightResult.LOOSE){
						removeFigureAt(move.getFrom());
					}
					if (attack(fig1, fig2) == FightResult.WIN){
						removeFigureAt(move.getTo());
						fieldFigure.setField(move.getTo(), fig1);
						fieldFigure.setField(move.getFrom(), null);
					}
					if (attack(fig1, fig2) == FightResult.DRAW_BOTH_ALIVE){
						
					}
					if (attack(fig1, fig2) == FightResult.DRAW_BOTH_DEAD){
						removeFigureAt(move.getFrom());
						removeFigureAt(move.getTo());
					}
				}
			}
		}
		return false;

	}
	
	private void removeFigureAt(Location loc){
		for (GameControllInterface g : gameControllList){
			g.figureRemovedAt(loc);
		}
		fieldFigure.removeFigureAt(loc);
		
	}
	
	private void handleLeftOverMoves(Move move, Figure fig){
		for (int i=0; i<fig.getMoves(); i++){
			if (PlayingFieldFigure.getPossibleLocationsAt(move.getFrom(), (i+1)).contains(move.getTo())){
				fig.setMoves(fig.getMoves()-(i + 1));
				break;
			}
		}

		
		//after move is executed figure can't move again
		if (fig.getMoves() == 0)
			movableFigures.remove(fig);
	}
	
	/**
	 * can be called, when the given player is ready to start the game
	 * @param Player given player ready to start the game
	 * @return boolean indicates if startGame is already executed, true if it is not
	 */
	public boolean startGame(Player player){
		if (playersReady.contains(player)){
			System.out.println("player already ready");
			return false;
		}
		playersReady.add(player);
		if (playersReady.size() == playerList.size()){ //GAME START
			setTurn(playerList.get(0));
		}
		getGameControll(player).startGame();
		return true;
	}
	
	/**
	 * sets a new Figure with given figureKind owned by given player
	 * @param player owner of the figure
	 * @param figureKind of the new figure
	 * @param loc location of the figure to be set
	 * @return true if set was successful otherwise false
	 */
	public boolean buyNewFigure(Player player, FigureKind figureKind, Location loc){
		
		if (hasTurn(player)
				&& fieldTerritory.getTerritoryKind(loc).equals(TerritoryKind.CASTLE)
				&& fieldFigure.isNull(loc)){
			if (player.decreaseGoldBy(figureKind.getCosts())){
				fieldFigure.setField(loc, new Figure(player, figureKind));
				getGameControll(player).purchaseMade();
				return true;
				}
		}
		return false;
	}
	
	/**
	 * checks if the given player is in turn
	 * @param player that is in turn
	 * @return true if he is
	 */
	public boolean hasTurn(Player player){
		if (playerHavingTurn == null)
			return false;
		return player.equals(playerHavingTurn);
	}
	
	/**
	 * changes the turn  having Player to given player
	 * @param player getting turn
	 */
	private void setTurn(Player player){
		playerHavingTurn = player;
		movableFigures = getFigures(player); //all figures are in the beginning movable
		for (Figure f : getFigures(player)){
			f.resetMoves();
		}
	}
	
	/**
	 * checks if the given move can be performed in terms of the range of the given
	 * figureKind
	 * @param move given move
	 * @param figKind given figureKind
	 * @return true, if the move is not a problem
	 */
	private Boolean isInRange(Move move, Figure fig){
		for (Location l : PlayingFieldFigure.getPossibleLocationsAt(move.getFrom(), 
				fig.getMoves())){
			if (move.getTo().equals(l)){
				return true;}
		}
		System.out.println("not in range");
		return false;
	}
	
	/**
	 * returns the number of figures of a player that are left over
	 * @param player owner of the figures
	 * @return int number of left over figures
	 */
	public int getNumberOfFigures(Player player){
		int number = 0;
		for (int c=0; c<COLUMN_COUNT; c++){
			for (int r=0; r<ROW_COUNT; r++){
				if (fieldFigure.getPlayerAt(new Location(r, c)).equals(player))
					number++;
			}
		}
		return number;
	}
	
	/**
	 * returns the figures of Player
	 * @param player owner of the figures
	 * @return ArrayList<Figure> containing the figures
	 */
	public ArrayList<Figure> getFigures(Player player){
		ArrayList<Figure> list = new ArrayList<Figure>();
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (!fieldFigure.isNull(new Location(r,c)) && 
						fieldFigure.getPlayerAt(new Location(r, c)).equals(player))
					list.add(fieldFigure.getFigureAt(new Location(r, c)));
			}
		}
		return list;
	}
	
	public int getGold(Player player){
		for (Player p : playerList){
			if (player == p)
				return p.getGold();
		}
		return 0;
	}
	
	/**
	 * fig1 attacks fig2. this method decides what happens with the figures
	 * @param fig1 attacking Figure
	 * @param fig2 defending Figure
	 */
	public FightResult attack(Figure fig1, Figure fig2){
		fig2.decreaseHealthBy(fig1.getDamage(fig2));
		if (fig1.getHealth() == 0
				&& fig2.getHealth() == 0)
			return FightResult.DRAW_BOTH_DEAD;
		if (fig1.getHealth() > 0
				&& fig2.getHealth() > 0)
			return FightResult.DRAW_BOTH_ALIVE;
		if (fig2.getHealth()== 0)
			return FightResult.WIN;
		return FightResult.LOOSE;
	}
	
}
