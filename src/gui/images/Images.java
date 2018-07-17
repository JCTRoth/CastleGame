package gui.images;

import gui.GuiField;
import gui.TerritoryField;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Images {
	
	private String imagePath = "/gui/images/png/";
	private String archerPath;
	private String lightKnightPath;
	private String swordmanPath;
	private String terrainMountainPath;
	private String terrainGrassPath;
	private String terrainCastlePath;
	private String terrainKeepPath;
	private String pathPath;
	
	private Image archer;
	private Image lightKnight;
	private Image swordman;
	private BufferedImage terrainMountain;
	private BufferedImage terrainGrass;
	private BufferedImage terrainCastle;
	private BufferedImage terrainKeep;
	private BufferedImage path;


	public Images() {
		initiatePaths();
		initiateIcons();
		scaleImages();
	}
	
	private void initiatePaths(){
		archerPath = imagePath + "archer.png";
		lightKnightPath = imagePath + "lightKnight.png";
		swordmanPath = imagePath + "swordman.png";
		terrainMountainPath = imagePath + "mountain.png";
		terrainGrassPath = imagePath + "grass.png";
		terrainCastlePath = imagePath + "castle.png";
		terrainKeepPath = imagePath + "keep.png";
		pathPath = imagePath + "path.png";
		
		List<Integer> list = new LinkedList<Integer>();
		
	}
	
	private void initiateIcons(){
		archer = Toolkit.getDefaultToolkit().getImage(getClass().getResource(archerPath));
		lightKnight = Toolkit.getDefaultToolkit().getImage(getClass().getResource(lightKnightPath));
		swordman = Toolkit.getDefaultToolkit().getImage(getClass().getResource(swordmanPath));
		try {
			terrainMountain = ImageIO.read(getClass().getResource(terrainMountainPath));
			terrainGrass = ImageIO.read(getClass().getResource(terrainGrassPath));
			terrainCastle = ImageIO.read(getClass().getResource(terrainCastlePath));
			terrainKeep = ImageIO.read(getClass().getResource(terrainKeepPath));
			path = ImageIO.read(getClass().getResource(pathPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private BufferedImage scaleImage(BufferedImage bi){
		BufferedImage biNew = new BufferedImage(TerritoryField.calcWIDTH(bi), TerritoryField.calcHEIGHT(bi), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2 = (Graphics2D) biNew.createGraphics();
//		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
//				RenderingHints.VALUE_RENDER_QUALITY));
		g2.drawImage(bi, 0, 0, TerritoryField.calcWIDTH(bi), TerritoryField.calcHEIGHT(bi), 0, 0, bi.getWidth(), bi.getHeight(), null);
		g2.dispose();
		return biNew;
	}
	
	private void scaleImages(){
		terrainGrass = scaleImage(terrainGrass);
		terrainMountain = scaleImage(terrainMountain);
		terrainCastle = scaleImage(terrainCastle);
		terrainKeep = scaleImage(terrainKeep);
		path = scaleImage(path);
	}
	
	/**
	 * returns all images in a list
	 * @return ArrayList<Image> containing all images
	 */
	public ArrayList<Image> getImages(){
		ArrayList<Image> list = new ArrayList<Image>();
		for (Field f : Arrays.asList(this.getClass().getDeclaredFields())){
			try {
				if (f.get(this) instanceof Image)
					list.add((Image)f.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public BufferedImage getResizedImage(int width, int heigth, Image image){
		BufferedImage bi = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) bi.createGraphics();
		g2.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, 
				RenderingHints.VALUE_RENDER_QUALITY));
		g2.drawImage(image, 0, 0, width, heigth, 0, 0, image.getWidth(null), image.getHeight(null), null);
		g2.dispose();
		return bi;
	}

	public Image getArcher() {
		return archer;
	}

	public Image getLightKnight() {
		return lightKnight;
	}

	public Image getSwordman() {
		return swordman;
	}
	
	public Image getTerrainMountain(){
		return terrainMountain;
	}
	
	public Image getTerrainGrass(){
		return terrainGrass;
	}
	
	public Image getTerrainCastle(){
		return terrainCastle;
	}
	
	public Image getTerrainKeep(){
		return terrainKeep;
	}
	
	public Image getPath(){
		return path;
	}
	


}
