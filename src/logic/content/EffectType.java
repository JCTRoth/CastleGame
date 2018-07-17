package logic.content;

public enum EffectType {
	MOVEMENT_SPEED, LIVE, MANA, STRENGTH; 
	
	private String name;
	
	static{
		MOVEMENT_SPEED.setName("Bewegungsgeschwindigkeit");
		LIVE.setName("Leben");
		MANA.setName("Mana");
		STRENGTH.setName("Stärke");
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
