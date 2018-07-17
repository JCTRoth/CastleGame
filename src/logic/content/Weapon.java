package logic.content;

public enum Weapon {
	SWORD, BOW, STAFF, SPEAR, STONE;
	
	private String name;
	
	static{
		SWORD.setName("Schwert");
		BOW.setName("Bogen");
		STAFF.setName("Stab");
		SPEAR.setName("Speer");
		STONE.setName("Stein");
	}
	
	public String getName(){
		return name;
	}
	
	private void setName(String name){
		this.name = name;
	}
	
}
