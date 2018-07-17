package controll;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import gui.FigureDisplayField;
import gui.FigureField;
import gui.GlassPane;
import gui.GuiField;
import gui.LineOfSight;
import gui.MainFrame;
import gui.TerritoryField;
import gui.content.BuyFigurePanel;
import gui.content.FigureToolBoxInfo;
import gui.content.InfoBar;
import gui.images.Images;
import gui.menu.MenuControll;
import gui.square.Square;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import logic.GameLogic;
import logic.content.Figure;
import logic.content.FigureKind;
import logic.content.Move;
import logic.content.Player;
import logic.content.TerritoryKind;
import logic.field.Location;
import logic.field.PlayingFieldFigure;
import logic.field.PlayingFieldTerritory;

public class GUIControll {

	private MainFrame frame;
	private GameLogic gameLogic;
	private GuiField guiField;
		private FigureField guiFigureField;
		private LineOfSight guiLineOfSight;
		private TerritoryField guiTerritoryField;
		private FigureDisplayField guiFigureDisplayField;
		private BuyFigurePanel buyFigurePanel;
		private InfoBar infoBar;
		private GlassPane glassPane;
		private ArrayList<FigureToolBoxInfo> figureToolBoxInfoList;
	private PlayingFieldFigure figureField;
	private PlayingFieldTerritory territoryField;
	public static Images images = new Images();
	private MenuControll menuControll;
	
	private Player local;
	private Player remote;
	private int state = 2; //state before move
	private Square squareSave = null;
	private Location buyLocation;
	private boolean edgeReached;
	private Thread th;
	private Point mousePos;

	public static Dimension FIELD_SIZE = new Dimension(1200,700);
	public static Dimension FIELD_SIZE_ACTUAL = 
			new Dimension(PlayingFieldFigure.COLUMN_COUNT * GuiField.WIDTH + 100,
					PlayingFieldFigure.ROW_COUNT * GuiField.HEIGHT / 2 + 100);
	private int ROW_COUNT = PlayingFieldFigure.ROW_COUNT;
	private int COLUMN_COUNT = PlayingFieldFigure.COLUMN_COUNT;
	
	/**
	 * set the local and the enemy remote player
	 * @param local you
	 * @param remote your enemy
	 */
	public void setPlayers(Player local, Player remote){
		this.local = local;
		this.remote = remote;
		
		setAllLineOfSights(2);
		guiFigureField.repaint();
	}
	
	public GUIControll(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		figureField = gameLogic.getFigureField();
		territoryField = gameLogic.getTerritoryField();
		
		initiateComponents();
		addComponentsToFrame();
		start();
	}
	
	public void start(){
		menuControll.start();
//		guiFigureDisplayField.refresh();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
//		guiFigureField.setVisible(true);
	}
	
	private void initiateComponents(){
//		guiField = new GuiField();
//		guiField.setComponents(gameLogic.getField());
		
		guiFigureField = new FigureField(this);
		guiLineOfSight = new LineOfSight();
		guiTerritoryField = new TerritoryField();
		guiFigureDisplayField = new FigureDisplayField(gameLogic.getFigureField());
		guiFigureField.setComponents(gameLogic.getFigureField());
		guiLineOfSight.setComponents(gameLogic.getFigureField());
		guiTerritoryField.setComponents(gameLogic.getTerritoryField());
		buyFigurePanel = new BuyFigurePanel(this);
		infoBar = new InfoBar();
		figureToolBoxInfoList = figureField.getAllFigureToolBoxInfosOf(local);
		glassPane = new GlassPane(this);
		
		frame = new MainFrame();
		menuControll = new MenuControll(this, frame);
		th = new Thread(){
			@Override
			public void run(){
//				super.run();
				while(true){
					if(edgeReached){
						while(true){
							if (GUIControll.this.edgeReached == false){
//								System.out.println("EdgeNotReached");
								break;
							}
//							System.out.println("EdgeReached");
							if (mousePos != null){
								//UP
								if (mousePos.x > GlassPane.DISTANCE
										&& mousePos.x < FIELD_SIZE.width-GlassPane.DISTANCE
										&& mousePos.y < GlassPane.DISTANCE){
//									System.out.println("up");
									if (!frame.upperEdgeReached())
										frame.scroll(MainFrame.UP);
								}
								//DOWN
								else if (mousePos.x < FIELD_SIZE.width-GlassPane.DISTANCE
										&& mousePos.x > GlassPane.DISTANCE
										&& mousePos.y > FIELD_SIZE.height-GlassPane.DISTANCE){
//									System.out.println("down");
									if (!frame.bottomEdgeReached())
										frame.scroll(MainFrame.DOWN);
								}
								//LEFT
								else if (mousePos.x < GlassPane.DISTANCE
										&& mousePos.y < FIELD_SIZE.height-GlassPane.DISTANCE
										&& mousePos.y > GlassPane.DISTANCE){
//									System.out.println("left");
									if (!frame.leftEdgeReached())
										frame.scroll(MainFrame.LEFT);
								}
								//RIGHT
								else if (mousePos.x > FIELD_SIZE.width-GlassPane.DISTANCE
										&& mousePos.y < FIELD_SIZE.height-GlassPane.DISTANCE
										&& mousePos.y > GlassPane.DISTANCE){
//									System.out.println("right");
									if (!frame.rightEdgeReached())
										frame.scroll(MainFrame.RIGHT);
								}
								//DOWN_RIGHT
								else if (mousePos.x > FIELD_SIZE.width-GlassPane.DISTANCE
										&& mousePos.y > FIELD_SIZE.height-GlassPane.DISTANCE){
//									System.out.println("down right");
									if (!frame.rightEdgeReached() || !frame.bottomEdgeReached()){
										if (frame.rightEdgeReached())
											frame.scroll(MainFrame.DOWN);
										else if (frame.bottomEdgeReached())
											frame.scroll(MainFrame.RIGHT);
										else frame.scroll(MainFrame.DOWN_RIGHT);
										}
								}
								//DOWN_LEFT
								else if (mousePos.x < GlassPane.DISTANCE
										&& mousePos.y > FIELD_SIZE.height-GlassPane.DISTANCE){
//									System.out.println("down left");
									if (!frame.leftEdgeReached() || !frame.bottomEdgeReached()){
										if (frame.leftEdgeReached())
											frame.scroll(MainFrame.DOWN);
										else if (frame.bottomEdgeReached())
											frame.scroll(MainFrame.LEFT);
										else frame.scroll(MainFrame.DOWN_LEFT);
									}
								}
								//UP_LEFT
								else if (mousePos.x < GlassPane.DISTANCE
										&& mousePos.y < GlassPane.DISTANCE){
//									System.out.println("up left");
									if (!frame.upperEdgeReached() || !frame.leftEdgeReached()){
										if (frame.upperEdgeReached())
											frame.scroll(MainFrame.LEFT);
										else if (frame.leftEdgeReached())
											frame.scroll(MainFrame.UP);
										else
											frame.scroll(MainFrame.UP_LEFT);
									}
								}
								
								//UP_RIGHT
								else if (mousePos.x > FIELD_SIZE.width-GlassPane.DISTANCE
										&& mousePos.y < GlassPane.DISTANCE){
//									System.out.println("up right");
									if (!frame.upperEdgeReached() || !frame.rightEdgeReached()){
										if (frame.upperEdgeReached())
											frame.scroll(MainFrame.RIGHT);
										else if (frame.rightEdgeReached())
											frame.scroll(MainFrame.UP);
										else
											frame.scroll(MainFrame.UP_RIGHT);
									}
								}
							}
							
							frame.repaint();
							try {
								Thread.sleep(30);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		th.start();
		
	}

	
	private void addComponentsToFrame(){
		frame.addLayeredPane(buyFigurePanel, 5);
		frame.addLayeredPane(guiTerritoryField, 0);
		frame.addLayeredPane(guiFigureField, 1);
		frame.addLayeredPane(guiLineOfSight, 3);
		frame.addLayeredPane(guiFigureDisplayField, 2);
		for (FigureToolBoxInfo t : figureToolBoxInfoList){
			frame.addLayeredPane(t, 4);
		}
		frame.addContentLayeredPane(infoBar, 1);
		frame.addContentLayeredPane(glassPane, 2);
		
	}
	
	public void removeAllFigureToolBoxInfos(){
		frame.removeAllComponentsOfOneType(new FigureToolBoxInfo(new Figure(local, FigureKind.ARCHER)));
	}
	
	public void addAllFigureToolBoxInfos(){
		for (FigureToolBoxInfo t : figureField.getAllFigureToolBoxInfosOf(local)){
			frame.addLayeredPane(t, 4);
		}
	}
	
	/**
	 * refreshes the figureToolBoxInfos
	 */
	public void refreshFigureToolBoxInfos(){
		removeAllFigureToolBoxInfos();
		addAllFigureToolBoxInfos();
	}
	
	
	
	/**
	 * handles what happens if a square gets clicked
	 * @param s clicked square
	 * @param p mouse position
	 */
	public void squareClicked(Square s, Point p){
		//location of clicked square
		Location loc = s.getLocation();
		
		guiFigureField.squareClicked(s);
		if (isStateBeforeMove()){ //if a castle is selected which's keep has a king then buy menu is opened
			if (territoryField.getTerritoryKind(loc) == TerritoryKind.CASTLE
					&& figureField.isNull(loc)
					&& territoryField.getTerritory(loc).getKeepLocation() != null
					&& figureField.getFigureAt(territoryField.getTerritory(loc).getKeepLocation()) != null
					&& figureField.getFigureAt(territoryField.getTerritory(loc).getKeepLocation()).isKing()){
				int xPos = p.x - BuyFigurePanel.PANEL_SIZE.width/2;
				int yPos = p.y - BuyFigurePanel.PANEL_SIZE.height / 2;
				if (xPos < 0)
					xPos = 0;
				if (yPos < 0)
					yPos = 0;
				if (xPos > FIELD_SIZE.width)
					xPos = FIELD_SIZE.width;
				if (yPos > FIELD_SIZE.height)
					yPos = FIELD_SIZE.height;
				buyFigurePanel.setLocation(xPos, yPos);
				buyLocation = loc;
				buyFigurePanel.setVisible(true);
			}
			//field is empty
			if (figureField.isNull(loc)){
				setAllLineOfSights(2);
				return;
			}
			if (figureField.getPlayerAt(loc) == local){
				guiLineOfSight.setLineOfSightPossibleMoves(loc, figureField.getFigureAt(loc).getMoves());
				squareSave = s;
				setStateActivatedFigure();
				return;
			}
		}
		
		if (isStateActivatedFigure()){
			if (s == squareSave){
				setAllLineOfSights(2);
				setStateBeforeMove();
				return;
			}
			else{
				if(gameLogic.move(new Move(squareSave.getLocation(), s.getLocation()), local)){
					setAllLineOfSights(2);
					guiFigureField.repaint();
					setStateMovePerformed();
					guiFigureField.hidePath();
//					guiFigureDisplayField.refresh();
					return;
					}
				else{ //illegal move
					setAllLineOfSights(2);
					setStateBeforeMove();
					return;
				}
			}
			//movePerformed as next state
		}
		guiFigureField.repaint();
//		System.out.println("Square: " + s.getRow() + " x " + s.getColumn());
	}
	
	public void mouseOverSquareMoved(Square s, Point p, boolean transition){
		Location loc = s.getLocation();
		
		if (transition){
			hideAllFigureToolBoxInfos();
			if (isStateActivatedFigure()){
				if (PlayingFieldFigure.getPossibleLocationsAt(squareSave.getLocation(), 
						figureField.getFigureAt(squareSave.getLocation()).getMoves()).contains(s.getLocation()))
				guiFigureField.calcAndShowShortestPath(new Move(squareSave.getLocation(), s.getLocation()), 
						figureField.getFigureAt(squareSave.getLocation()).getMoves()); 
			}

				
			if (!figureField.isNull(loc))
				figureField.getFigureAt(loc).getFigureToolBoxInfo().refresh();
			
		}
		else if (!figureField.isNull(loc)){
			refreshFigureToolBoxInfos();
			figureField.getFigureAt(loc).setVisibleFigureToolBoxInfo(true);
			figureField.getFigureAt(loc).getFigureToolBoxInfo().setLocation(p.x + 10, p.y + 10);
		}
		else {
			hideAllFigureToolBoxInfos();
		}
		
		
	}
	
	public void edgeReached(boolean edgeReached){
		if (this.edgeReached != edgeReached){
			this.edgeReached = edgeReached;
			
		}
	}
	
	
	
	/**
	 * hides all tool boxes of figures 
	 */
	public void hideAllFigureToolBoxInfos(){
		for (Figure f : figureField.getAllFiguresOf(local)){
			f.setVisibleFigureToolBoxInfo(false);
		}
	}
	
	
	public void buyFigure(FigureKind figKind){
		gameLogic.buyNewFigure(local, figKind, buyLocation);
		buyFigurePanel.setVisible(false);
	}
	
	public void setFieldVisible(){
		frame.setVisibleLayeredPane(true);
		infoBar.setVisible(true);
	}
	
	
	public void setAllLineOfSights(int range){
		guiLineOfSight.setLineOfSight(figureField.getAllFigurePositionsOf(local), range);
	}
	
	public void setStateBeforeMove(){
		state = 2;
	}
	
	public void setStateActivatedFigure(){
		state = 3;
	}
	
	public void setStateMovePerformed(){
		state = 2; //TEST
	}
	
	public boolean isStateBeforeMove(){
		return state == 2;
	}
	
	public boolean isStateActivatedFigure(){
		return state == 3;
	}
	
	public boolean isStateMovePerformed(){
		return state == 4;
	}
	
	public void refreshGold(){
		infoBar.updateGold(gameLogic.getGold(local));
//		guiFigureDisplayField.refresh();
	}
	
	public void refresh(){
		guiFigureDisplayField.refresh();
	}
	
	public void setMousePos(Point mousePos){
		this.mousePos = mousePos;
		
	}
	
	public FigureField getFigureField(){
		return guiFigureField;
	}
	
	public void scrollingInGlassPane(Point p){
		glassPane.mouseMoved(new Point(p.x - frame.getViewportXPos(),
				p.y - frame.getViewportYPos()));
	}
	
	public void removeFigureAt(Location loc){
		guiFigureDisplayField.remove(figureField.getFigureAt(loc).getHealthBar());
	}
	
	
}
