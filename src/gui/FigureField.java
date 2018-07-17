package gui;

import gui.square.Square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import logic.content.FigureKind;
import logic.content.Move;
import logic.field.Location;

import controll.GUIControll;

public class FigureField extends GuiField{
	
	private GUIControll guiControll;
	private Square saveSquare = null;
	private ArrayList<Location> pathList = new ArrayList<Location>();
	
	public FigureField(GUIControll guiControll) {
		super();
		this.guiControll = guiControll;
		
		initiatePanel();
		this.setOpaque(false);
	}
	
	private void initiatePanel(){
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Point p = e.getPoint();
				for (int r=0;r<ROW_COUNT;r++){
					for (int c=0;c<COLUMN_COUNT;c++){
						if (guiField[r][c].contains(p))
							guiControll.squareClicked(guiField[r][c], p);
					}		
				}
			}
		});
		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(MouseEvent e){
				Point p = e.getPoint();
				guiControll.scrollingInGlassPane(p);
				for (int r=0; r<ROW_COUNT; r++){
					for (int c=0; c<COLUMN_COUNT; c++){
						if (guiField[r][c].contains(p)){
							if (guiField[r][c] != saveSquare){
							guiControll.mouseOverSquareMoved(guiField[r][c], p, true);
							saveSquare = guiField[r][c];
							}
							else{
								guiControll.mouseOverSquareMoved(guiField[r][c], p, false);
							}
						}
					}
				}
				
			}
		});
	}
	
	public void squareClicked(Square s){
		
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int c=0; c<COLUMN_COUNT; c++){
			for (int r=0; r<ROW_COUNT; r++){
				g2.setColor(Color.BLACK);
				g2.draw(guiField[r][c]);
				int X = guiField[r][c].xpoints[0] - WIDTH/2 + WIDTH/8;
				int Y = guiField[r][c].ypoints[0] + WIDTH/8;
				if (pathList != null 
						&& pathList.contains(new Location(r,c)))
					g.drawImage(images.getPath(), guiField[r][c].xpoints[0] - WIDTH/2, 
							guiField[r][c].ypoints[0], this);
				if (figureField[r][c] == null){
					continue;
				}
				if (figureField[r][c].hasFigureType(FigureKind.ARCHER))
					g.drawImage(images.getArcher(), X, Y, this);
				if (figureField[r][c].hasFigureType(FigureKind.LIGHT_KNIGHT))
					g.drawImage(images.getLightKnight(), X, Y, this);
				if (figureField[r][c].hasFigureType(FigureKind.SWORDMAN))
					g.drawImage(images.getSwordman(), X, Y, this);
			}}
	}
	
	public void calcAndShowShortestPath(Move move, int range){
		pathList = playingFieldFigure.getShortestPath(move.getFrom(), move.getTo(), range);
		repaint();
	}
	public void hidePath(){
		pathList = null;
	}
}
