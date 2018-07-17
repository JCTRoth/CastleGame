package logic.content;

import java.util.LinkedList;
import java.util.List;

public enum ClassType {
	INFANTRY_SWORD, INFANTRY_SPEER, CAVALERY, ARCHER, SIEGE;
	
	private LinkedList<ClassType> weakness = new LinkedList<ClassType>();
	private LinkedList<ClassType> strength = new LinkedList<ClassType>();
	private String name;
	
	static{
		INFANTRY_SWORD.setWeakness(CAVALERY);
		INFANTRY_SWORD.setStrength(ARCHER);
		
		INFANTRY_SPEER.setWeakness(INFANTRY_SPEER);
		INFANTRY_SPEER.setWeakness(ARCHER);
		INFANTRY_SPEER.setWeakness(INFANTRY_SWORD);
		INFANTRY_SPEER.setStrength(CAVALERY);
		
		ARCHER.setWeakness(CAVALERY);
		ARCHER.setStrength(INFANTRY_SPEER);
		ARCHER.setStrength(INFANTRY_SWORD);
		
		CAVALERY.setStrength(ARCHER);
	}
	static{ //NAMES
		INFANTRY_SWORD.setName("Schwertinfanterie");
		INFANTRY_SPEER.setName("Speerinfanterie");
		CAVALERY.setName("Kavalerie");
		ARCHER.setName("Fernkämpfer");
		SIEGE.setName("Belagerung");
	}

	public LinkedList<ClassType> getWeakness() {
		return weakness;
	}

	public LinkedList<ClassType> getStrength() {
		return strength;
	}
	
	private boolean setWeakness(ClassType weakness){
		if (!this.weakness.contains(weakness)){
			this.weakness.add(weakness);
			return true;
		}
		return false;
	}

	private boolean setStrength(ClassType strength) {
		if (!this.strength.contains(strength)){
			this.strength.add(strength);
			return true;
			}
		return false;
	}
	
	public String createHtmlListStrength(){
		return createHtmlList(false);
	}
	
	public String createHtmlListWeakness(){
		return createHtmlList(true);
	}
	
	private String createHtmlList(boolean showWeakness){
		List<String> list = new LinkedList<String>();
		List<ClassType> listClass;
		if (showWeakness)
			listClass = weakness;
		else
			listClass = strength;
		
		for (ClassType c : listClass){
			list.add(c.getName());
		}
		
		return createHtmlString(list);
	}
	
	/**
	 * creates a string written in html that represents a list
	 * @param list containing the string elements in the list
	 * @return html-string
	 */
	private String createHtmlString(List<String> listElements){
		String htmlString;
		
		htmlString = "<html><body><p style=padding-left:5px>";
		for (String s : listElements){
			htmlString = htmlString + "<font size = -2>- " + s + "</font><br>";
		}
		htmlString = htmlString + "</p></body></html>"; 
		
		return htmlString;
	}
	
	private void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
