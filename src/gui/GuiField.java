package gui;

import gui.images.Images;
import gui.square.Square;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.content.Figure;
import logic.field.Location;
import logic.field.PlayingFieldFigure;
import logic.field.PlayingFieldTerritory;

import controll.GUIControll;

public class GuiField extends JPanel{
	

	
	public static int ROW_COUNT = PlayingFieldFigure.ROW_COUNT;
	public static int COLUMN_COUNT = PlayingFieldFigure.COLUMN_COUNT;
	public static int WIDTH = 80;
	public static int HEIGHT = 80;
	public static Dimension FIELD_SIZE = new Dimension(2000, 2000);
	
	public Square[][] guiField;
	public PlayingFieldFigure playingFieldFigure;
	public PlayingFieldTerritory playingFieldTerritory;
	public Images images = GUIControll.images;
	public Figure[][] figureField;
	
	
	public void setComponents(PlayingFieldFigure playingField){
		this.playingFieldFigure = playingField;
		this.figureField = playingFieldFigure.getField();
	}
	
	public void setComponents(PlayingFieldTerritory playingFieldTerritory){
		this.playingFieldTerritory = playingFieldTerritory;
	}
	
	public GuiField(){
//		this.setMinimumSize(GUIControll.FIELD_SIZE);
//		this.setPreferredSize(GUIControll.FIELD_SIZE);
//		this.setMaximumSize(GUIControll.FIELD_SIZE);
		this.setBounds(0, 0, FIELD_SIZE.width, FIELD_SIZE.height);
		
		initiateField();
	}
	
	private void initiateField(){
		guiField = new Square[ROW_COUNT][COLUMN_COUNT];
		
		for (int c=0; c<COLUMN_COUNT; c++){
			for (int r=0; r<ROW_COUNT; r++){
				if (r % 2 == 0)
					guiField[r][c] = new Square(WIDTH * c, WIDTH/2 * r, WIDTH, WIDTH, r, c);
				else{
					guiField[r][c] = new Square(WIDTH * c + WIDTH/2, WIDTH/2 * r, WIDTH, WIDTH, r, c);
					}
			}}
	}
	
	
}
