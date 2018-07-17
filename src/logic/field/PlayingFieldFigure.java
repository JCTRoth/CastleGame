package logic.field;

import gui.content.FigureToolBoxInfo;

import java.awt.Component;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.text.Position;

import logic.content.Figure;
import logic.content.FigureKind;
import logic.content.Move;
import logic.content.Player;

public class PlayingFieldFigure {
	
	public static int ROW_COUNT = 21;
	public static int COLUMN_COUNT = 16;
	
	private Figure[][] field = new Figure[ROW_COUNT][COLUMN_COUNT];
	private Figure save = null;
	
	public static int LEFT_UPPER = 0;
	public static int LEFT_BOTTOM = 1;
	public static int RIGHT_UPPER = 2;
	public static int RIGHT_BOTTOM = 3;
	
	/**
	 * creates empty field
	 */
	public PlayingFieldFigure() {
		for (int r=0;r<ROW_COUNT;r++){
			for(int c=0;c<COLUMN_COUNT;c++){
				field[r][c] = null;
				}
			}
//		System.out.println(getDiagonal(new Location(5,5), 5, this.LEFT_UPPER));
		
//		System.out.println(getRange(new Move(new Location(0,0), new Location(5,5)), 14));
//		for (Move m : calcShortestWay(new Move(new Location(0,0), new Location(5,5)), 15)){
//			System.out.println(m.toString());
//		}
		
//		for (Location l : getShortestPath(new Location(0, 0), new Location(5, 0), 20))
//			System.out.println(l);
		
//		System.out.println(this.getIntersectingLocation(getDiagonal(new Location(0, 0), 8, RIGHT_BOTTOM),
//				getDiagonal(new Location(5, 0), 8, RIGHT_UPPER)));
//
//		System.out.println(intersect(getDiagonal(new Location(5, 0), 20, RIGHT_BOTTOM), 
//				getDiagonal(new Location(0, 0), 20, RIGHT_UPPER)));
		
//		for (Location l2 : getShortestPath(new Location(5, 0), new Location(0, 0), 20))
//			System.out.println(l2);
		
		
		
//		for (Location l : getDiagonal(new Location(0, 0), 20, RIGHT_BOTTOM))
//			System.out.println(l);
//		
//		System.out.println("----------");
//		for (Location l : getDiagonal(new Location(5, 0), 20, RIGHT_UPPER))
//			System.out.println(l);
//		for (Location l : getRandomLocations(10))
//			System.out.println(l);
		
//		for (Location l : getAllParts(4)[1][1])
//			System.out.println(l);
		
		}
	
	/**
	 * sets a figure at given position
	 * @param r ROW position
	 * @param c COLUMN position
	 * @param fig figure to be set
	 */
	public void setField(int r, int c, Figure fig){
		field[r][c] = fig;
	}
	
	public void setField(Location loc, Figure fig){
		field[loc.getRow()][loc.getColumn()] = fig;
	}
	
	
	/**
	 * saves a figure at a given location
	 * can be reloaded with load(Location)
	 * @param loc location with figure
	 */
	public void save(Location loc){
		this.save = field[loc.getRow()][loc.getColumn()];
	}
	
	/**
	 * loads the with save(Location) saved figure
	 * to the given location
	 * @param loc given location
	 */
	public void load(Location loc){
		this.setField(loc, save);
	}
	
	/**
	 * sets a field with given array structure
	 * @param figArray set to the field
	 */
	public void setField(Figure[][] figArray){
		for (int r=0;r<ROW_COUNT;r++){
			for (int c=0;c<COLUMN_COUNT;c++){
				field[r][c] = figArray[r][c];
				}
			}
		}
	
	
	public Figure[][] getField(){
		return field;
	}
	
	/**
	 * calculates and returns possible moves of all figures at the field
	 * @return ArrayList<Location>[][]
	 */
	public ArrayList<Location>[][] getPossibleLocations(){
		@SuppressWarnings("unchecked")
		ArrayList<Location>[][] list = new ArrayList[ROW_COUNT][COLUMN_COUNT];
		
		//initiate every arrayList
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				list[r][c] = getPossibleLocationsAt(new Location(r, c));
			}
		}

		return list;
	}
	
	
	/**
	 * gives the possible move locations of a figure at a given position
	 * @param loc figure at this location
	 * @return ArrayList<Location>
	 */
	public static ArrayList<Location> getPossibleLocationsAt(Location loc){
		ArrayList<Location> list = new ArrayList<Location>();
		int r = loc.getRow();
		int c = loc.getColumn();
		
		//even
		if (r%2 == 0){
			list.add(new Location(r-1, c-1)); //UP-LEFT
			list.add(new Location(r+1, c-1)); //DOWN-LEFT
		}
		//uneven
		else{
			list.add(new Location(r-1, c+1)); //UP-RIGHT
			list.add(new Location(r+1, c+1)); //DOWN-RIGHT
		}
		
		list.add(new Location(r+1, c));
		list.add(new Location(r-1, c));
		
		removeIfOutOfBounds(list);
		
		return list;
	}
	
	/**
	 * returns the possible move locations depending on locations and
	 * takeable moves
	 * @param loc location of the figure
	 * @param moves takeable moves
	 * @return Arraylist<Location> containing all the moves
	 */
	public static ArrayList<Location> getPossibleLocationsAt(Location loc, int moves){
		HashSet<Location> set = new HashSet<Location>();
		ArrayList<Location> list = new ArrayList<Location>();
		
		set.add(loc);
		
		if (moves==0){
			list.addAll(set);
			return list;
		}
		
		for (Location l: getPossibleLocationsAt(loc))
			set.addAll(getPossibleLocationsAt(l, moves-1));
		
		list.addAll(set);
		return list;
	}
	
	public static HashSet<Location> getLineOfSightAt(Location loc){
		HashSet<Location> set = new HashSet<Location>(); 
		
		int r = loc.getRow();
		int c = loc.getColumn();
		
		set.addAll(getPossibleLocationsAt(loc));
		
		set.add(new Location(r-2, c)); //UP
		set.add(new Location(r+2, c)); //DOWN
		set.add(new Location(r, c+1)); //RIGHT
		set.add(new Location(r, c-1)); //LEFT
		
		removeIfOutOfBounds(set);
		
		return set;
	}
	
	/**
	 * adds all the fields within the sight range of a place on the field
	 * @param loc
	 * @param deep
	 * @return HashSet<Location>
	 */
	public static LinkedList<Location> getLineOfSightAt(Location loc, int deep){
		LinkedList<Location> h = new LinkedList<Location>();
		
		if (!h.contains(loc))
			h.add(loc);
		
		LinkedList<Location> hClone = new LinkedList<Location>(h);
		for (int i=0; i<deep; i++){
			for (Location l : h){
				for (Location l2 : getLineOfSightAt(l))
					if (!hClone.contains(l2))
						hClone.add(l2);
			}
			for (Location l : hClone){
				if (!h.contains(l))
					h.add(l);
			}
			
		}
		
		
		//TESTEN DER FUNKTIONSWEISE VON HashSet
//		Location[] s1 = {new Location(1, 1), new Location(1, 2), new Location(1, 3), new Location(1, 1)};
//		
//		List<Location> l1 = (List<Location>) Arrays.asList(s1);
//		LinkedList<Location> hSet1 = new LinkedList<Location>();
//		hSet1.addAll(l1);
//		hSet1.addAll(hSet1);
//		for (Location l : l1){
//			if (!hSet1.contains(l))
//				hSet1.add(l);
//		}
//		System.out.println(hSet1);
//		System.out.println(hSet1.contains(new Location(1, 1)));
		
		return h;
	}
	
	/**
	 * removes the locations of the given Collection if it represents a
	 * field that is out of playingField
	 * @param col Collection<Location> list of locations
	 * @return Collection<Location> new list of locations
	 */
	private static Collection<Location> removeIfOutOfBounds(Collection<Location> col){
		Location[] locArray = new Location[col.size()];
		locArray = col.toArray(locArray);
		
		//remove all locations that are out of bounds/field
		for (Location l: col){
			if (l.getRow() < 0 ||
					l.getRow() >= ROW_COUNT ||
					l.getColumn() < 0 ||
					l.getColumn() >= COLUMN_COUNT){
				for (int i=0; i<locArray.length; i++){
					if (l.equals(locArray[i]))
						locArray[i] = null;
				}
			}
		}
		col.clear();
		for (int i=0; i<locArray.length; i++){
			if (locArray[i] != null)
			col.add(locArray[i]);
		}
		
		return col;
	}
	
	/**
	 * returns a number of random locations at the map that are in even
	 * distances to them-self
	 * the field is split in number parts and gives every location its own
	 * part for random spawn
	 * @param number of random locations
	 * @return ArrayList<Location> consisting the random locations
	 */
	public static ArrayList<Location> getRandomLocations(int number){
		ArrayList<Location> listLoc = new ArrayList<Location>();
		
		int ROW_COUNT_PART = (int) (Math.sqrt((double) number));
		int COLUMN_COUNT_PART = (int) (Math.sqrt((double) number));
		
		ArrayList<Location>[][] allParts = getAllParts(number);
		
		for (int r=0; r<ROW_COUNT_PART; r++){
			for (int c=0; c<COLUMN_COUNT_PART; c++){
				int randomNumber = (int) (Math.random()*allParts[r][c].size());
				listLoc.add(allParts[r][c].get(randomNumber));
			}
		}
		
		return listLoc;
	}
	
	/**
	 * returns the locations that are included in the part in which
	 * given location is included as well.
	 * @param parts number of parts
	 * @param removeLoc included in the part
	 * @return ArrayList<Location>
	 */
	public static ArrayList<Location> getFieldPart(int parts, Location loc){
		int ROW_COUNT_PART = ROW_COUNT / (int) (Math.sqrt((double) parts));
		int COLUMN_COUNT_PART = COLUMN_COUNT / (int) (Math.sqrt((double) parts));
		
		ArrayList<Location>[][] allParts =  getAllParts(parts);
		
		for (int r=0; r<ROW_COUNT_PART; r++){
			for (int c=0; c<COLUMN_COUNT_PART; c++){
				for (Location l : allParts[r][c])
					if (l.equals(loc))
						return allParts[r][c];
			}
		}
		return null;
	}
	
	/**
	 * returns all the possible field parts of the field
	 * NOTE: it is a good idea to take a number that is dividable
	 * by ROW as well as COLUMN_COUNT
	 * @param parts number of parts
	 * @return ArrayList<Location>[][] all locations
	 */
	private static ArrayList<Location>[][] getAllParts(int parts){
		int parts_sqrt = (int)(Math.sqrt((double) parts));
		
		//number of rows per part
		int ROW_COUNT_PART = ROW_COUNT/parts_sqrt;
		//number of columns per part
		int COLUMN_COUNT_PART = COLUMN_COUNT/parts_sqrt;
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Location>[][] listArray 
			= new ArrayList[ROW_COUNT_PART][COLUMN_COUNT_PART];
		
		
		
		//r_parts, c_parts are row and column of the array consisting the parts
		for (int r_parts=0; r_parts<ROW_COUNT_PART; r_parts++){
			for (int c_parts=0; c_parts<COLUMN_COUNT_PART; c_parts++){
				ArrayList<Location> list = new ArrayList<Location>();
				int r_begin = (int)(r_parts * ROW_COUNT_PART);
				int r_end = (int) (r_begin + ROW_COUNT_PART);
				int c_begin = (int)(c_parts * COLUMN_COUNT_PART);
				int c_end = (int) (c_begin + COLUMN_COUNT_PART);
				
				for (int r=r_begin; r<r_end; r++){
					for (int c=c_begin; c<c_end; c++){
						list.add(new Location(r, c));
					}
				}
				listArray[r_parts][c_parts] = list;
			}
		}
		
		
		return listArray;
	}
	
	/**
	 * returns all the positions of figures of a give player
	 * @param player owner of the figures
	 * @return ArrayList<Location> containing all the positions
	 */
	public ArrayList<Location> getAllFigurePositionsOf(Player player){
		ArrayList<Location> list = new ArrayList<Location>();
		
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				if (field[r][c] != null && 
						field[r][c].belongsTo(player))
					list.add(new Location(r, c));
			}
		}
		
		return list;
	}
	
	/**
	 * returns all figure of a player standing on the field
	 * @param player owner of the figures
	 * @return ArrayList<Figure> containing all figures of the player
	 */
	public ArrayList<Figure> getAllFiguresOf(Player player){
		ArrayList<Figure> list = new ArrayList<Figure>();
		
		for (Location loc : getAllFigurePositionsOf(player)){
			list.add(getFigureAt(loc));
		}
		return list;
	}
	
	/**
	 * returns all figreToolBoxInfos of a figure standing on the field
	 * @param player owner of figures
	 * @return ArrayList<FigureToolBoxInfo> containing all the figureToolBoxInfos of the player
	 */
	public ArrayList<FigureToolBoxInfo> getAllFigureToolBoxInfosOf(Player player){
		ArrayList<FigureToolBoxInfo> list = new ArrayList<FigureToolBoxInfo>();
		
		for (Figure f : getAllFiguresOf(player)){
			list.add(f.getFigureToolBoxInfo());
		}
		return list;
	}
	
	/**
	 * returns random locations that are on opposite sites of the field
	 * @return ArrayList<Location> containing opposite Locations
	 */
	public static ArrayList<Location> getRandomStartingLocations(){
		ArrayList<Location> list = new ArrayList<Location>();
		
		ArrayList<Location>[][] listArray = getAllParts(9);
		list.add(listArray[0][0].get((int)(Math.random()*listArray[0][0].size())));
		list.add(listArray[2][2].get((int)(Math.random()*listArray[2][2].size())));

		return list;
	}
	
	public FigureKind getFigureKindAt(Location loc){
		return field[loc.getRow()][loc.getColumn()].getFigureKind();
	}
	
	public Player getPlayerAt(Location loc){
		return field[loc.getRow()][loc.getColumn()].getPlayer();
	}
	
	public Figure getFigureAt(Location loc){
		return field[loc.getRow()][loc.getColumn()];
	}
	
	public Boolean isNull(Location loc){
		return field[loc.getRow()][loc.getColumn()] == null;
	}
	
	
	
	public int getMovesPerRound(Location loc){
		return field[loc.getRow()][loc.getColumn()].getFigureKind().getMovesPerRound();
	}
	
	/**
	 * should be called if a figure has to be removed
	 * @param loc
	 */
	public void removeFigureAt(Location loc) {
		
		
	}
	
	/**
	 * help function for calcShortestWay
	 * @param move
	 * @param maxRange
	 * @param list
	 * @return
	 */
	private LinkedList<Move> calcShortestWayIt(Move move, int maxRange, LinkedList<Move> list){
		int range = Integer.MAX_VALUE;
		Location nearestLocation = null;
		for (Location l : getPossibleLocationsAt(move.getFrom(), 1)){
			if (l.equals(move.getTo()))
				return list;
			if (getRange(new Move(l, move.getTo()), maxRange)< range){
				nearestLocation = l;
				
			}
			range = getRange(new Move(l, move.getTo()), maxRange);
		}
		System.out.println("rekursiv");
		list.add(new Move(nearestLocation, move.getTo()));
		return calcShortestWayIt(list.getFirst(), maxRange-1, list);
	}
	
	/**
	 * calculates the shortest way of moves to a given aim location
	 * @param move long range move
	 * @return LinkedList<Move>
	 */
	public LinkedList<Move> calcShortestWay(Move move, int maxRange){
		return calcShortestWayIt(move, maxRange, new LinkedList<Move>());
	}
	
	/**
	 * get the range of a move
	 * @param move move with a range
	 * @param maxRange maximum range of the move
	 * @return int range of the move
	 */
	public int getRange(Move move, int maxRange){
		for (int i=0; i<maxRange; i++){
			for (Location l : getPossibleLocationsAt(move.getFrom(), i)){
				if (l.equals(move.getTo())){
					return i;
				}
			}
		}
		return 0;
	}
	
	public LinkedList<Location> getShortestWay(Move move, LinkedList<Location> list){
		Location from = move.getFrom();
		Location to = move.getTo();
		
		while(list.getLast().equals(to)){
			if (from.getColumn() == to.getColumn()){ 
				if (from.getRow() < to.getRow())
					from = new Location(from.getRow()+1, from.getColumn());//go down
				else
					from = new Location(from.getRow()-1, from.getColumn());//go up
			}
			else if (from.getRow() == to.getRow()){ //go right
				if (from.getRow() < to.getRow())
				from = new Location(from.getRow(), from.getColumn()+1);
			}
			list.add(from);
		}
			
		
		return list;
	}
	
	
	
	/**
	 * returns the left upper location from this location
	 * @param loc source location
	 * @return left upper location
	 */
	public Location getLeftUpperLocation(Location loc){
		if (loc.getRow() % 2 == 0) //even
			return new Location(loc.getRow()-1, loc.getColumn()-1);
		return new Location(loc.getRow()-1, loc.getColumn());
			
	}
	
	/**
	 * returns the right upper location from source location
	 * @param loc source location
	 * @return right upper location
	 */
	public Location getRightUpperLocation(Location loc){
		if (loc.getRow() % 2 == 0) //even
			return new Location(loc.getRow()-1, loc.getColumn());
		return new Location(loc.getRow()-1, loc.getColumn()+1);
	}
	
	/**
	 * returns left bottom location from source location
	 * @param loc source location
	 * @return left bottom location
	 */
	public Location getLeftBottomLocation(Location loc){
		if (loc.getRow() % 2 == 0) //even
			return new Location(loc.getRow()+1, loc.getColumn()-1);
		return new Location(loc.getRow()+1, loc.getColumn());
	}
	
	/**
	 * returns right bottom location from source location
	 * @param loc source location
	 * @return right bottom location
	 */
	public Location getRightBottomLocation(Location loc){
		if (loc.getRow() % 2 == 0) //even
			return new Location(loc.getRow()+1, loc.getColumn());
		return new Location(loc.getRow()+1, loc.getColumn()+1);
	}
	
	
	/**
	 * returns an ArrayList<Location> containing the Locations representing a diagonal
	 * of given type
	 * @param loc start location
	 * @param range range of the diagonal
	 * @param type given types:
	 * LEFT_UPPER
	 * LEFT_BOTTOM
	 * RIGHT_UPPER
	 * RIGHT_BOTTOM
	 * @return ArrayList<Location> with diagonal representing locations
	 */
	public ArrayList<Location> getDiagonal(Location loc, int range, int type){
		ArrayList<Location> list = getDiagonalWithSource(loc, range, type);
		list.remove(0);
		return list;
	}
	
	public ArrayList<Location> getDiagonalWithSource(Location loc, int range, int type){
		ArrayList<Location> list = new ArrayList<Location>();
		Location l = loc;
		list.add(loc);
		for (int i=0; i<range; i++){
			if (type == LEFT_UPPER)
				l = getLeftUpperLocation(l);
			if (type == LEFT_BOTTOM)
				l = getLeftBottomLocation(l);
			if (type == RIGHT_UPPER)
				l = getRightUpperLocation(l);
			if (type == RIGHT_BOTTOM)
				l = getRightBottomLocation(l);
			list.add(l);
		}
		return list;
	}
	
	/**
	 * checks if two given diagonals intersect
	 * @param source first diagonal
	 * @param aim second diagonal
	 * @return true if they intersect, false if not
	 */
	public boolean intersect(ArrayList<Location> source, ArrayList<Location> aim){
		for (Location l : source){
			if (l.getRow() < 0 
					|| l.getColumn() < 0)
				return false;
			if (aim.contains(l)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * returns the intersectionLocation of two diagonals represented by diagonal1 and 2
	 * @param diagonal1 ArrayList<Location> diagonal
	 * @param diagonal2 ArrayList<Location> diagonal
	 * @return Location location where diagonals intersect
	 */
	public Location getIntersectingLocation(ArrayList<Location> diagonal1, ArrayList<Location> diagonal2){
		for (Location l : diagonal1){
			if (diagonal2.contains(l))
				return l;
		}
		return null;
	}

	public ArrayList<Location> getIntersectingPath(ArrayList<Location> sourceList, ArrayList<Location> aimList, int range){
		ArrayList<Location> list = new ArrayList<Location>();
		Location intersectingLocation = getIntersectingLocation(sourceList, aimList);
		for (Location l : sourceList){
			list.add(l);
			if(l == intersectingLocation){
				break;
			}
		}
		for (Location l2 : aimList){
			if(l2.equals(intersectingLocation)){
				for (int i=aimList.indexOf(l2); i>-1; i--){
					list.add(aimList.get(i));
				}
				break;
			}
		}
		return list;
	}
	
	/**
	 * returns the path that gets created when two diagonals from two locations hit each other
	 * @param source location
	 * @param aim location
	 * @return s.o.
	 */
	public ArrayList<Location> getShortestPath(Location source, Location aim, int range){
		if (getDiagonal(source, range, RIGHT_UPPER).contains(aim))
			return getDiagonalPath(source, aim, this.getFigureAt(source).getMoves()+10
					, RIGHT_UPPER);
		if (getDiagonal(source, range, RIGHT_BOTTOM).contains(aim))
			return getDiagonalPath(source, aim, this.getFigureAt(source).getMoves()+10
					, RIGHT_BOTTOM);
		if (getDiagonal(source, range, LEFT_UPPER).contains(aim))
			return getDiagonalPath(source, aim, this.getFigureAt(source).getMoves()+10
					, LEFT_UPPER);
		if (getDiagonal(source, range, LEFT_BOTTOM).contains(aim))
			return getDiagonalPath(source, aim, this.getFigureAt(source).getMoves()+10
					, LEFT_BOTTOM);
		
		if (intersect(getDiagonal(source, range, RIGHT_UPPER), getDiagonal(aim, range, LEFT_UPPER))){
			return getIntersectingPath(getDiagonal(source, range, RIGHT_UPPER), 
					getDiagonalWithSource(aim, range, LEFT_UPPER), range);
		}
		if (intersect(getDiagonal(source, range, LEFT_UPPER), getDiagonal(aim, range, RIGHT_UPPER))){
			return getIntersectingPath(getDiagonal(source, range, LEFT_UPPER), 
					getDiagonalWithSource(aim, range, RIGHT_UPPER), range);
		}
		
		if (intersect(getDiagonal(source, range, LEFT_BOTTOM), getDiagonal(aim, range, LEFT_UPPER))){
			return getIntersectingPath(getDiagonal(source, range, LEFT_BOTTOM), 
					getDiagonalWithSource(aim, range, LEFT_UPPER), range);
		}
		if (intersect(getDiagonal(source, range, LEFT_UPPER), getDiagonal(aim, range, LEFT_BOTTOM))){
			return getIntersectingPath(getDiagonal(source, range, LEFT_UPPER), 
					getDiagonalWithSource(aim, range, LEFT_BOTTOM), range);
		}
		
		if (intersect(getDiagonal(source, range, RIGHT_BOTTOM), getDiagonal(aim, range, RIGHT_UPPER))){
			return getIntersectingPath(getDiagonal(source, range, RIGHT_BOTTOM), 
					getDiagonalWithSource(aim, range, RIGHT_UPPER), range);
		}
		if (intersect(getDiagonal(source, range, RIGHT_UPPER), getDiagonal(aim, range, RIGHT_BOTTOM))){
			return getIntersectingPath(getDiagonal(source, range, RIGHT_UPPER), 
					getDiagonalWithSource(aim, range, RIGHT_BOTTOM), range);
		}
		if (intersect(getDiagonal(source, range, LEFT_BOTTOM), getDiagonal(aim, range, RIGHT_BOTTOM))){
			return getIntersectingPath(getDiagonal(source, range, LEFT_BOTTOM), 
					getDiagonalWithSource(aim, range, RIGHT_BOTTOM), range);
		}
		if (intersect(getDiagonal(source, range, RIGHT_BOTTOM), getDiagonal(aim, range, LEFT_BOTTOM))){
			return getIntersectingPath(getDiagonal(source, range, RIGHT_BOTTOM), 
					getDiagonalWithSource(aim, range, LEFT_BOTTOM), range);
		}
		
		return null;
	}
	
	/**
	 * returns a diagonal with 
	 * @param diagonal
	 * @param source
	 * @param range
	 * @param type
	 * @return
	 */
	public ArrayList<Location> getDiagonalPath(Location source, Location aim, int maxRange, int type){
		ArrayList<Location> list;
		list = getDiagonal(source, this.getRange(new Move(source, aim), maxRange), type);
//		if (list != null)
//			list.remove(list.size()-1);
		return list;
	}
	
	
	
	
	
	
}
