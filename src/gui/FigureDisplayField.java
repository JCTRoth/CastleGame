package gui;

import gui.square.HealthBar;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLayeredPane;

import logic.content.Figure;
import logic.field.Location;
import logic.field.PlayingFieldFigure;

public class FigureDisplayField extends JLayeredPane{
	
	private HealthBar[][] healthArray;
	private Figure[][] figureField;
	
	public FigureDisplayField(PlayingFieldFigure playingFieldFigure) {
		figureField = playingFieldFigure.getField();
		initiatePanel();
		
		healthArray = new HealthBar[GuiField.ROW_COUNT][GuiField.COLUMN_COUNT];
		refresh();
		this.setOpaque(false);
	}
	
	private void initiatePanel(){
		this.setBounds(0, 0, GuiField.FIELD_SIZE.width, GuiField.FIELD_SIZE.height);
//		this.setLayout(null);
	}
	
	public void refresh(){
//		this.removeAll();
		
		for (int r=0; r<GuiField.ROW_COUNT; r++){
			for (int c=0; c<GuiField.COLUMN_COUNT; c++){
				int X;
				if (r % 2 == 1) //uneven
					X = (c+1) * GuiField.WIDTH - GuiField.WIDTH/2 + 15;
				else
					X = (c+1) * GuiField.WIDTH - GuiField.WIDTH + 15;
				int Y = (r+1) * GuiField.HEIGHT/2 - GuiField.HEIGHT/2 + 15;
				if (figureField[r][c] != null){ // if no healthbar is initiated for the figure
					if (figureField[r][c].getHealthBar() == null){
						figureField[r][c].setHealthBar(new HealthBar(X, Y, figureField[r][c].getMaxHealth(),
								figureField[r][c].getHealth()));
	//					healthArray[r][c].setLocation(X, Y);
						this.add(figureField[r][c].getHealthBar());
//						System.out.println("new health: " + figureField[r][c].getHealth());
						}
					else{
						figureField[r][c].getHealthBar().setHealth(figureField[r][c].getHealth());
						figureField[r][c].getHealthBar().setLocation(X, Y);
					}
				}
					
			}
		}
	}
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		refresh();
		
	}
	
	
}
