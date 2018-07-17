package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.Policy;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import org.omg.PortableInterceptor.PolicyFactory;

import controll.GUIControll;

public class MainFrame extends JFrame{
	
	private Dimension FRAME_SIZE = GUIControll.FIELD_SIZE;

	private JPanel contentPane;
	private JLayeredPane contentLayeredPane;
	private JLayeredPane layeredPane;
	private JScrollPane scrollPane;
	private int scrollSpeed;
	
	public static int UP = 1;
	public static int DOWN = 2;
	public static int LEFT = 3;
	public static int RIGHT = 4;
	public static int UP_RIGHT = 5;
	public static int DOWN_RIGHT = 6;
	public static int DOWN_LEFT = 7;
	public static int UP_LEFT = 8;
	
	public MainFrame() {
		initiateContent();
		initiateScrollPane();
		initiateContentLayeredPane();
		initiateContentPane();
		initiateFrame();
		
//		this.setSize(FRAME_SIZE);
		scrollSpeed = 4;
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.repaint();
	}
	
	private void initiateFrame(){
		
		this.setTitle("Castle Game");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		
	}
	
	private void initiateContentLayeredPane(){
		contentLayeredPane = new JLayeredPane();
		contentLayeredPane.setLayout(null);
		contentLayeredPane.add(scrollPane, 1);
		contentLayeredPane.setVisible(false);
		contentLayeredPane.setPreferredSize(GUIControll.FIELD_SIZE);
	}
	
	private void initiateScrollPane(){
		scrollPane = new JScrollPane();
		scrollPane.setVisible(true);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setAutoscrolls(true);
		
		scrollPane.setBounds(0, 0, GUIControll.FIELD_SIZE.width, GUIControll.FIELD_SIZE.height);
	}
	
	private void initiateContentPane(){
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(GUIControll.FIELD_SIZE_ACTUAL);
		layeredPane.setVisible(true);
//		layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(layeredPane);
//		scrollPane.getViewport().scrollRectToVisible(new Rectangle(100, 100, 0, 0));
		
		contentPane.add(contentLayeredPane);
	}
	
	public boolean scroll(int value){
		switch (value){
		//UP
		case 1: scroll(0, -scrollSpeed);
				break;
			
		//DOWN
		case 2: scroll(0, scrollSpeed);
				break;
		
		//LEFT
		case 3: scroll(-scrollSpeed, 0);
				break;
				
		//RIGHT
		case 4: scroll(scrollSpeed, 0);
				break;
				
		//UP_RIGHT
		case 5: scroll(scrollSpeed, -scrollSpeed);
				break;
				
		//DOWN_RIGHT
		case 6: scroll(scrollSpeed, scrollSpeed);
				break;
				
		//DOWN_LEFT
		case 7: scroll(-scrollSpeed, scrollSpeed);
				break;
				
		//UP_LEFT
		case 8: scroll(-scrollSpeed, -scrollSpeed);
				break;
		}
		
		
		repaint();
		return true;
	}
	
	private void scroll(int xSpeed, int ySpeed){
		scrollPane.getViewport().setViewPosition(new Point(scrollPane.getViewport().getViewPosition().x + xSpeed, 
				scrollPane.getViewport().getViewPosition().y + ySpeed));
		
//		System.out.println(xSpeed + " x " + ySpeed);
	}
	
	public Component addContentLayeredPane(Component comp, int index){
		contentLayeredPane.add(comp, new Integer(index));
		return comp;
	}
	
	public Component addLayeredPane(Component comp, int index){
//		super.add(comp, index);
		layeredPane.add(comp, new Integer(index));
		return comp;
	}
	
	/**
	 * removes a component from the layered Pane that contains the playing fields ui
	 * @param comp component to remove
	 * @return true if remove was successfully
	 */
	public boolean removeLayeredPane(Component comp){
		if (Arrays.asList(layeredPane.getComponents()).contains(comp)){
			layeredPane.remove(comp);
			return true;
		}
		return false;
	}
	
	/**
	 * removes all Components of a given type from the layeredPane
	 * @param object expl object
	 */
	public <T> void removeAllComponentsOfOneType(T object){
		List<Component> list = Arrays.asList(layeredPane.getComponents());
		for (Component c : list){
			if (c.getClass() == object.getClass()){
				layeredPane.remove(c);
			}
		}
	}
	
	public void addScrollPane(Component comp, int index){
		scrollPane.add(comp, new Integer(index));
	}
	
	private void initiateContent(){
		
	}
	
	public void setVisibleLayeredPane(boolean b){
		layeredPane.setVisible(b);
		scrollPane.setVisible(b);
		contentLayeredPane.setVisible(b);
	}
	
	public int getViewportXPos(){
		return scrollPane.getViewport().getViewPosition().x;
	}
	
	public int getViewportYPos(){
		return scrollPane.getViewport().getViewPosition().y;
	}
	
	
	public boolean upperEdgeReached(){
		if (getViewportYPos() <= 0){
			return true;
		}
		return false;
	}
	
	public boolean leftEdgeReached(){
		if (getViewportXPos() <= 0)
			return true;
		return false;
	}
	
	public boolean rightEdgeReached(){
		if (getViewportXPos() + scrollPane.getViewport().getWidth() >= GUIControll.FIELD_SIZE_ACTUAL.width)
			return true;
		return false;
	}
	
	public boolean bottomEdgeReached(){
		if (getViewportYPos() + scrollPane.getViewport().getHeight() >= GUIControll.FIELD_SIZE_ACTUAL.height)
			return true;
		return false;
	}
}
