package gui;

import gui.images.Images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import logic.content.Territory;
import logic.content.TerritoryKind;
import logic.field.PlayingFieldTerritory;

import controll.GUIControll;

public class TerritoryField extends GuiField{
	
	Territory[][] field;
	
	@Override
	public void setComponents(PlayingFieldTerritory territoryField){
		super.setComponents(territoryField);
		field = playingFieldTerritory.getField();
	}
	
	public TerritoryField() {
		
		initiateField();
	}
	
	private void initiateField(){
		
	}
	
	
	public void refresh(){
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (int r=0; r<ROW_COUNT; r++){
			for (int c=0; c<COLUMN_COUNT; c++){
				int X = guiField[r][c].xpoints[0] - WIDTH/2;
				int Y = guiField[r][c].ypoints[0];
				
				if (field[r][c].hasTerritoryKind(TerritoryKind.GRASS))
					g2.drawImage(images.getTerrainGrass(), X, Y, this);
				if (field[r][c].hasTerritoryKind(TerritoryKind.ICE))
					g2.setColor(Color.CYAN);
				if (field[r][c].hasTerritoryKind(TerritoryKind.BOG))
					g2.setColor(new Color(0x632E00));
				if (field[r][c].hasTerritoryKind(TerritoryKind.FOREST))
					g2.setColor(new Color(0x106300));
				if (field[r][c].hasTerritoryKind(TerritoryKind.MOUNTAIN))
					g2.drawImage(images.getTerrainMountain(), X, Y, this);
				if (field[r][c].hasTerritoryKind(TerritoryKind.WATER))
					g2.setColor(Color.BLUE);
				if (field[r][c].hasTerritoryKind(TerritoryKind.VILLAGE))
					g2.setColor(Color.RED);
				if (field[r][c].hasTerritoryKind(TerritoryKind.CASTLE))
					g2.drawImage(images.getTerrainCastle(), calcX(images.getTerrainCastle(), X), 
							calcY(images.getTerrainCastle(), Y), this);
				if (field[r][c].hasTerritoryKind(TerritoryKind.KEEP))
					g2.drawImage(images.getTerrainKeep(), calcX(images.getTerrainKeep(), X), 
							calcY(images.getTerrainKeep(), Y), this);
					
//				g2.fill(guiField[r][c]);
			}
		}
		
	}
	private static int DEFAULT = 51;
	
	
	public static int calcX(Image image, int x){
		return x - (image.getWidth(null) - GuiField.WIDTH);
	}
	
	public static int calcY(Image image, int y){
		return y - (image.getHeight(null) - GuiField.HEIGHT);
	}
	
	public static int calcWIDTH(Image image){
		return GuiField.WIDTH + image.getWidth(null) - DEFAULT;
	}
	
	public static int calcHEIGHT(Image image){
		return GuiField.HEIGHT + image.getHeight(null) - DEFAULT;
	}

}
