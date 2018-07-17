package gui;

import gui.square.Square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import logic.field.Location;
import logic.field.PlayingFieldFigure;

public class LineOfSight extends GuiField{
	
	public static Color fog = new Color(Color.BLACK.getRed(),
			Color.BLACK.getGreen(),
			Color.BLACK.getBlue(), 180);

	public LineOfSight() {
		//whole field from beginning in fog
		makeDark();
		this.setOpaque(false);
		
	}
	
	/**
	 * sets the lineOfSight for location with given range
	 * @param loc Location
	 * @param range int range of lineOfSight
	 */
	public void setLineOfSight(Location loc, int range){
		makeDark();
		lineOfSight(loc, range);
		repaint();
	}
	
	/**
	 * sets the lineOfSight for locations with given range
	 * @param loc List<Location> getting line of sights
	 * @param range int range of lineOfSight
	 */
	public void setLineOfSight(List<Location> loc, int range){
		makeDark();
		for (Location l: loc){
			lineOfSight(l, range);
		}
		repaint();
	}
	
	/**
	 * sets the line of sight but not for the vision range but rather
	 * for the move range
	 * @param loc Location position of the figure
	 * @param moves int move range
	 */
	public void setLineOfSightPossibleMoves(Location loc, int moves){
		makeDark();
		
		for (Location l: PlayingFieldFigure.getPossibleLocationsAt(loc, moves))
			guiField[l.getRow()][l.getColumn()].setColor(new Color(0, 0, 0, 0));
		
		repaint();
//		System.out.println(PlayingFieldFigure.getPossibleLocationsAt(loc, moves).size());
	}
	
	public void makeDark(){
		for (int c=0; c<COLUMN_COUNT; c++){
			for (int r=0; r<ROW_COUNT; r++){
				guiField[r][c].setColor(fog);
			}
		}
	}
	
	/**
	 * sets a part with the location in the middle revealed
	 * if range == -1 do nothing
	 * @param loc
	 * @param range
	 */
	public void lineOfSight(Location loc, int range){
		if (range == -1){
			return;
		}
		
		for (Location l : PlayingFieldFigure.getLineOfSightAt(loc, range)){
			guiField[l.getRow()][l.getColumn()].setColor(new Color(0, 0, 0, 0));
			}
		}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int c=0; c<COLUMN_COUNT; c++){
			for (int r=0; r<ROW_COUNT; r++){
				g2.setColor(guiField[r][c].getColor());
				g2.fill(guiField[r][c]);
			}}
	}

}
