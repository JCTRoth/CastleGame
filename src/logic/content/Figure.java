package logic.content;

import gui.content.FigureToolBoxInfo;
import gui.square.HealthBar;

import java.util.LinkedList;

import logic.GameLogic;

public class Figure {
	
	private Player player;
	private FigureKind figureKind;
	private int moves;
	private boolean king;
	private String name;
	private Weapon weapon;
	private int level;
	private int health;
	private int maxHealth;
	private LinkedList<Effect> effects = new LinkedList<Effect>();
	private FigureToolBoxInfo figureToolBoxInfo;
	private HealthBar healthBar;
	
	public Figure(Player player, FigureKind figureKind) {
		this.player = player;
		this.figureKind = figureKind;
		this.name = figureKind.getName();
		this.weapon = figureKind.getStandardWeapon();
		this.level = 1;
		this.health = figureKind.getStandardLife();
		this.maxHealth = figureKind.getStandardLife();
		king = false;
		//in the beginning max amount of moves depending on figureKind
		moves = figureKind.getMovesPerRound(); 
		figureToolBoxInfo = new FigureToolBoxInfo(this);
		figureToolBoxInfo.refresh();
	}
	
	/**
	 * checks if figure belongs to given player
	 * @param player potential owner
	 * @return true if belongs
	 */
	public Boolean belongsTo(Player player){
		return player == this.player;
	}
	
	public Boolean hasFigureType(FigureKind figureKind){
		return (this.figureKind == figureKind);
	}
	
	public FigureKind getFigureKind(){
		return figureKind;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public int getMoves(){
		return moves;
	}
	
	public void setMoves(int moves){
		this.moves = moves;
	}
	
	public void resetMoves(){
		this.moves = figureKind.getMovesPerRound();
	}
	
	public boolean isKing(){
		return king;
	}
	
	public void setKing(boolean king){
		this.king = king;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public Weapon getWeapon(){
		return weapon;
	}
	
	public void setWeapon(Weapon weapon){
		this.weapon = weapon;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public LinkedList<Effect> getEffects(){
		return effects;
	}
	
	public void setEffects(LinkedList<Effect> effects){
		this.effects = effects;
	}
	
	public void addEffect(Effect effect){
		if (!effects.contains(effect))
			effects.add(effect);
	}
	
	public boolean hasEffect(Effect effect){
		return effects.contains(effect);
	}
	
	/**
	 * returns in a String with all the effects that act on the figure.
	 * @return String
	 */
	public String getEffectsToString(){
		String s = "";
		for (Effect e : effects){
			if (e == effects.getLast()){
				s = s + e.getName();
				break;
			}
			s = s + e.getName() + ", ";
		}
		
		return s;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void decreaseHealthBy(int value){
		health -= value; 
		if (health < 0){
			health = 0;
		}
	}
	
	public void increaseHealthBy(int value){
		health += value;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth;
	}
	
	public void setVisibleFigureToolBoxInfo(boolean b){
		figureToolBoxInfo.setVisible(b);
	}
	
	public FigureToolBoxInfo getFigureToolBoxInfo(){
		return figureToolBoxInfo;
	}
	
	public HealthBar getHealthBar(){
		return healthBar;
	}
	
	public void setHealthBar(HealthBar healthBar){
		this.healthBar = healthBar;
	}
	
	/**
	 * calculates the damage that this figure deals to the given figure
	 * @param fig attacked figure
	 * @return int value of damage
	 */
	public int getDamage(Figure fig){
		int dmg = this.figureKind.getBasicDamage();
		dmg = (int)(dmg * GameLogic.LEVEL_FACTOR);
		for (ClassType c : figureKind.getClassType().getStrength()){
			if (fig.getFigureKind().getClassType() == c) //enemy has ClassType in strong List
				dmg = (int)(dmg * GameLogic.CLASS_TYPE_FACTOR);
		}
		for (ClassType c : figureKind.getClassType().getWeakness()){
			if (fig.getFigureKind().getClassType() == c) //enemy has ClassType in weak List
				dmg = (int)(dmg / GameLogic.CLASS_TYPE_FACTOR);
		}
		return dmg;
	}
	
	/**
	 * should be called before deleting the object
	 */
	public void removeEverything(){
		this.figureToolBoxInfo = null;
		this.healthBar.setVisible(false);
		this.healthBar = null;
	}
	

}
