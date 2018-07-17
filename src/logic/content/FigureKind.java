package logic.content;

import gui.images.Images;

import java.awt.Image;

import javax.swing.ImageIcon;

import controll.GUIControll;

public enum FigureKind {
	LIGHT_KNIGHT, KNIGHT, ARCHER, SWORDMAN, WIZARD, SPEARMAN, CATAPULT;
	
	private int movesPerRound;
	private int costs;
	private ClassType classType;
	private String name;
	private Weapon standardWeapon;
	private int standardLife;
	private int basicDamage;
	private Image image;
	private static Images images = GUIControll.images;
	
	static{
		LIGHT_KNIGHT.setProperties(5, 100, 200, ClassType.CAVALERY, "Leichte Kavalerie", Weapon.SWORD, 
				15, images.getLightKnight());
		KNIGHT.setProperties(4, 350, 250, ClassType.CAVALERY, "Ritter", Weapon.SWORD,
				50, null);
		ARCHER.setProperties(3, 50, 100, ClassType.ARCHER, "Bogenschütze", Weapon.BOW, 
				25, images.getArcher());
		SWORDMAN.setProperties(2, 80, 150, ClassType.INFANTRY_SWORD, "Schwerkämpfer", Weapon.SWORD, 
				45, images.getSwordman());
		SPEARMAN.setProperties(3, 40, 80, ClassType.INFANTRY_SPEER, "Speerträger", Weapon.SPEAR, 
				15, null);
		WIZARD.setProperties(2, 200, 80, ClassType.INFANTRY_SWORD, "Zauberer", Weapon.STAFF, 
				5, null);
		CATAPULT.setProperties(1, 250, 400, ClassType.SIEGE, "Katapult", Weapon.STONE, 
				250, null);
	}

	
	private void setProperties(int movesPerRound, int costs, int standardLife, 
			ClassType classType, String name, Weapon standardWeapon, int basicDamage, 
			Image standardImage){
		setMovesPerRound(movesPerRound);
		setCosts(costs);
		setStandardLife(standardLife);
		setClassType(classType);
		setName(name);
		setStandardWeapon(standardWeapon);
		setBasicDamage(basicDamage);
		setStandardImage(standardImage);
	}
	
	
	public void setCosts(int costs){
		this.costs = costs;
	}
	
	public int getCosts(){
		return costs;
	}
	
	private void setMovesPerRound(int movesPerRound){
		this.movesPerRound = movesPerRound;
	}
	
	public int getMovesPerRound(){
		return movesPerRound;
	}
	
	public void setClassType(ClassType classType){
		this.classType = classType;
	}
	
	public ClassType getClassType(){
		return classType;
	}
	
	private void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	private void setStandardWeapon(Weapon weapon){
		this.standardWeapon = weapon;
	}
	
	public Weapon getStandardWeapon(){
		return standardWeapon;
	}
	
	private void setStandardImage(Image image){
		this.image = image;
	}
	
	public Image getStandardImage(){
		return image;
	}
	
	public int getStandardLife(){
		return standardLife;
	}
	
	private void setStandardLife(int standardLife){
		this.standardLife = standardLife;
	}
	
	public int getBasicDamage(){
		return basicDamage;
	}
	
	private void setBasicDamage(int basicDamage){
		this.basicDamage = basicDamage;
	}
	
	
	
}
