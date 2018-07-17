package logic.content;

import logic.GameLogic;

public class Player {
	
	private String name;
	private int gold;
	
	public Player(String name) {
		this.name = name;
		this.gold = GameLogic.DEFAULT_GOLD_AMOUNT;
	}
	
	public String getName(){
		return name;
	}
	
	public int getGold(){
		return gold;
	}
	
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * decrease the gold amount of the player by amount
	 * @param amount gold amount to be decreased
	 * @return true, if decrease was successful or false
	 * if the gold amount is smaller than the amount to be decreased
	 */
	public boolean decreaseGoldBy(int amount){
		if (gold - amount < 0)
			return false;
		gold = gold - amount;
		return true;
	}
	
	
	
}
