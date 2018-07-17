package logic.content;

import logic.field.Location;

public class Territory {
	
	private TerritoryKind kind;
	private Location keepLocation;
	private int row;
	private int column;
	
	public Territory(TerritoryKind kind, int row, int column) {
		this.kind = kind;
		this.row = row;
		this.column = column;
	}
	
	public Territory(TerritoryKind kind, int row, int column, Location keepLocation){
		this(kind, row, column);
		this.keepLocation = keepLocation;
	}
	
	public void setTerritoryKind(TerritoryKind kind){
		this.kind = kind;
	}
	
	public TerritoryKind getTerritoryKind(){
		return kind;
	}
	
	public Boolean hasTerritoryKind(TerritoryKind kind){
		if (this.kind == kind)
			return true;
		return false;
	}
	
	@Override
	public String toString(){
		return "Territory: " + kind.toString();
	}
	
	public Location getKeepLocation(){
		return keepLocation;
	}
	
	public void setKeepLocation(Location keepLocation){
		this.keepLocation = keepLocation;
	}
	
}
