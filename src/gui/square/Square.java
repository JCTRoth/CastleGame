package gui.square;

import java.awt.Color;
import java.awt.Polygon;

import logic.field.Location;

public class Square extends Polygon{

	private int row;
	private int column;
	private Color color = new Color(0,0,0,0);
	
	
	public Square(int x, int y, int width, int heigth, int row, int column) {
		this.row = row;
		this.column = column;
		
		this.xpoints = new int[4];
		this.ypoints = new int[4];
		
		this.xpoints[0] = width/2 + x;
		this.xpoints[1] = width + x;
		this.xpoints[2] = width/2 + x;
		this.xpoints[3] = 0 + x;
		
		this.ypoints[0] = 0 + y;
		this.ypoints[1] = heigth/2 + y;
		this.ypoints[2] = heigth + y;
		this.ypoints[3] = heigth/2 + y;
		
		this.npoints = 4;
		
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Location getLocation(){
		return new Location(row, column);
	}

}
