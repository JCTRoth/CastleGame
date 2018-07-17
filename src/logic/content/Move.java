package logic.content;

import logic.field.Location;
import logic.field.PlayingFieldFigure;

public class Move {
	
	private Location from;
	private Location to;
	
	public Move(Location from, Location to) {
		this.from = from;
		this.to = to;
	}

	public Location getFrom() {
		return from;
	}

	public Location getTo() {
		return to;
	}
	
	public String toString(){
		return from + " x " + to;
	}
	

	
}
