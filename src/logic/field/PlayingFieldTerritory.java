package logic.field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import controll.GUIControll;
import logic.content.Territory;
import logic.content.TerritoryKind;

public class PlayingFieldTerritory {
	
	private int ROW_COUNT = PlayingFieldFigure.ROW_COUNT;
	private int COLUMN_COUNT = PlayingFieldFigure.COLUMN_COUNT;
	private Territory[][] field = new Territory[ROW_COUNT][COLUMN_COUNT];
	
	/**
	 * creates Territory consisting out of TerritoryFigure.GRASS
	 */
	public PlayingFieldTerritory() {
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++)
				field[r][c] = new Territory(TerritoryKind.GRASS, r, c);
		}
		setRandomGrasMap(9, 4);
	}
	
	/**
	 * creates a random field
	 * @return random field
	 */
	public Territory[][] getRandomField(){
		Territory[][] territory = new Territory[ROW_COUNT][COLUMN_COUNT];
		ArrayList<TerritoryKind> list = new ArrayList<TerritoryKind>();
		int average = (ROW_COUNT * COLUMN_COUNT) / TerritoryKind.values().length;
		
		for (TerritoryKind t: TerritoryKind.values()){
			for (int i=0; i<average; i++)
				list.add(t);
		}
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (list.isEmpty()){
					territory[r][c] = new Territory(TerritoryKind.GRASS, r, c);
					continue;
				}
				int randomNumber = (int)(Math.random() * list.size());
				territory[r][c] = new Territory(list.get(randomNumber), r, c);
				list.remove(randomNumber);
			}
		}
		
		return territory;
	}
	
	public void setField(int r, int c, TerritoryKind kind){
		field[r][c].setTerritoryKind(kind);
	}
	
	public void setField(int r, int c, TerritoryKind kind, Location keepLocation){
		this.setField(r, c, kind);
		field[r][c].setKeepLocation(keepLocation);
	}
	
	public Territory[][] getField(){
		return field;
	}
	
	
	/**
	 * sets a random grass map with mountains
	 * @param mountains number of mountains
	 * @param size of the mountains
	 */
	public void setRandomGrasMap(int mountains, int size){
//		Territory[][] territory = new Territory[ROW_COUNT][COLUMN_COUNT];
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				field[r][c] = new Territory(TerritoryKind.GRASS, r, c);
			}
		}
		
		setRandomMountains(mountains, size);
		setRandomStartingLocation(9);
//		return territory;
	}
	
	/**
	 * sets random mountainTypes at a given field
	 * @param territory Territory[][] that gets new mountains
	 * @param number number of mountain groups
	 * @param fields number of maintain fields per mountain group
	 */
	public void setRandomMountains(int groups, int fields){
		for (Location l : PlayingFieldFigure.getRandomLocations(groups)){
			setTerritoryGroup(TerritoryKind.MOUNTAIN, l, fields);
		}
	}
	
	/**
	 * sets random start locations on the map in opposite locations
	 * @param size size of the castles
	 */
	public void setRandomStartingLocation(int size){
		ArrayList<Location> list = PlayingFieldFigure.getRandomStartingLocations();
		int counter = 0;
		
		for (Location l : list){
			
			counter = 0;
			int sizeLineOfSight = (int)(Math.sqrt((double) size) + 1);
			
			for (Location l2 : PlayingFieldFigure.getLineOfSightAt(l, sizeLineOfSight)){
				if (counter == size){
					break;
				}
				setField(l2.getRow(), l2.getColumn(), TerritoryKind.CASTLE, l);
				counter++;
			}
			setField(l.getRow(), l.getColumn(), TerritoryKind.KEEP);
		}
		
		//TEST WITH LINKED LIST AS A SET CONTAINING ONLY ELEMENTS OF ONE TYPE
		//DIFFERRING EACH OTHER
//		LinkedList<Location> orig = PlayingFieldFigure.getLineOfSightAt(new Location(5, 5), 3);
//		LinkedList<Location> clone = new LinkedList<Location>();
//		for (Location l : orig){
//			if (!clone.contains(l))
//				clone.add(l);
//		}
//		System.out.println(clone.size() == orig.size());
	}
	
	/**
	 * sets a group of given territories
	 * the number represents the number of territoryKinds to be set
	 * @param ter kind of territory
	 * @param loc location where group is located
	 * @param number of elements
	 */
	private void setTerritoryGroup(TerritoryKind ter, Location loc, int number){
		if (number == 0)
			return;
		Location newLoc = setTerritoryInNearLocation(ter, loc);
		setTerritoryGroup(ter, newLoc, number-1);
	}
	
	/**
	 * sets a territoryKind to a random location next to the given one
	 * @param ter TerritoryKind to be set
	 * @param loc given location
	 * @return set location
	 */
	private Location setTerritoryInNearLocation(TerritoryKind ter, Location loc){
	//get line of sight
	HashSet<Location> locList = PlayingFieldFigure.getLineOfSightAt(loc);
	//add all elements in an arrayList to get an element at a specific point
	ArrayList<Location> arrayList = new ArrayList<Location>();
	arrayList.addAll(locList);
	//get an element at a random location
	Location randomLocation = arrayList.get((int)(Math.random() * arrayList.size()));
	//replace the territoryKind of the element with the given one
	field[randomLocation.getRow()][randomLocation.getColumn()].setTerritoryKind(ter);
	
	return randomLocation;
	}
	
	public TerritoryKind getTerritoryKind(Location loc){
		return field[loc.getRow()][loc.getColumn()].getTerritoryKind();
	}
	
	public Territory getTerritory(Location loc){
		return field[loc.getRow()][loc.getColumn()];
	}

}
