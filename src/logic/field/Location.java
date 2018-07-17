package logic.field;

public class Location {
	
	private int row;
	private int column;
	
	public Location(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	@Override
	public boolean equals(Object loc){
		Location loc2;
		if (loc != null && 
				loc instanceof Location){
			loc2 = (Location) loc;
			if (row == (int)loc2.getRow() &&
					column == (int)loc2.getColumn())
				return true;
			return false;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String s = "Location[" + row + ", " + column + "]";
		return s;
	}
	
}
