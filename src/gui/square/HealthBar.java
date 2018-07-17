package gui.square;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.content.Figure;

public class HealthBar extends JLabel{
	
	private Polygon healthPolygon;
	private Polygon actualHealthPolygon;
	private int health;
	private int maxHealth;
	private int X;
	private int Y;
	public static int WIDTH = 20;
	public static int WIDTH_BAR = 5;
	public static int FACTOR = 6;

	
	
	/**
	 * creates a polygon representing the health bar of a figure
	 * @param x int X position
	 * @param y int Y position
	 * @param health  int health count of the figure
	 */
	public HealthBar(int x, int y, int maxHealth, int health) {
		this.health = health/FACTOR;
		this.maxHealth = maxHealth/FACTOR;
		this.X = x;
		this.Y = y;
		this.setOpaque(true);
		this.setBackground(new Color(0, 0, 0, 0));
//		this.setMinimumSize(new Dimension(WIDTH, maxHealth));
//		this.setPreferredSize(new Dimension(WIDTH, maxHealth));
//		this.setMaximumSize(new Dimension(WIDTH, maxHealth));
//		this.setLocation(x, y);
		this.setBounds(x, y, WIDTH, maxHealth);
		
		initiatePolygonActual();
		initiatePolygon();
		this.setVisible(true);
		repaint();

	}
	
	private void initiatePolygonActual(){
		actualHealthPolygon = new Polygon();
		actualHealthPolygon.xpoints = new int[4];
		actualHealthPolygon.ypoints = new int[4];
		
		
		actualHealthPolygon.xpoints[0] = 1;
		actualHealthPolygon.ypoints[0] = 1;
		
		actualHealthPolygon.xpoints[1] = 1+WIDTH_BAR;
		actualHealthPolygon.ypoints[1] = 1;
		
		actualHealthPolygon.xpoints[2] = 1+WIDTH_BAR;
		actualHealthPolygon.ypoints[2] = 1+maxHealth;
		
		actualHealthPolygon.xpoints[3] = 1;
		actualHealthPolygon.ypoints[3] = 1+maxHealth;

		actualHealthPolygon.npoints = 4;
	}
	
	private void initiatePolygon(){
		healthPolygon = new Polygon();
		healthPolygon.xpoints = new int[4];
		healthPolygon.ypoints = new int[4];
		
		healthPolygon.xpoints[0] = 1;
		healthPolygon.ypoints[0] = 1+maxHealth;
		
		healthPolygon.xpoints[1] = 1;
		healthPolygon.ypoints[1] = 1+maxHealth-health;
		
		healthPolygon.xpoints[2] = 1+WIDTH_BAR;
		healthPolygon.ypoints[2] = 1+maxHealth-health;
		
		healthPolygon.xpoints[3] = 1+WIDTH_BAR;
		healthPolygon.ypoints[3] = 1+maxHealth;
		
		healthPolygon.npoints = 4;
		
	}
	
	/**
	 * returns the color depending on the health count
	 * @return Color
	 */
	private Color getColor(){
		if ((health * 100 / maxHealth) >= 75){
			return Color.GREEN;
		}
		if ((health * 100 / maxHealth) >= 50){
			return Color.YELLOW;
		}
		if ((health * 100 / maxHealth) >= 25){
			return Color.ORANGE;
		}
		else
			return Color.RED;
	}
	
	public void setHealth(int health){
		this.health = health/FACTOR;
		initiatePolygon();
	}
	
	public void setMaxHealth(int maxHealth){
		this.maxHealth = maxHealth/FACTOR;
		initiatePolygonActual();
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getColor());
		g2.fill(healthPolygon);
		g2.setColor(Color.BLACK);
		g2.draw(actualHealthPolygon);
		g2.draw(healthPolygon);
		
		
	}
	
	
	

	
}
