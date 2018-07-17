package gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controll.GUIControll;

/**
 * is an invisible panel that lies on tob of the other layers.
 * it implements listeners
 * @author Jonas Roth
 */
public class GlassPane extends JPanel{
	
	private GUIControll guiControll;
	public static int DISTANCE = 80;
	
	public GlassPane(GUIControll guiControll) {
		this.guiControll = guiControll;
		this.setOpaque(false);
		this.setBounds(0, 0, GUIControll.FIELD_SIZE.width, GUIControll.FIELD_SIZE.height);
		
		
//		addListeners();
	}
	
//	private void addListeners(){
//		this.addMouseListener(new MouseAdapter(){
//			public void mousePressed(MouseEvent e){
//				guiControll.getFigureField().dispatchEvent(e);
//			}
//		});
//		this.addMouseMotionListener(new MouseAdapter(){
			public void mouseMoved(Point p){
				
				guiControll.setMousePos(p);
				if (p.x > GUIControll.FIELD_SIZE.width - DISTANCE
						|| p.x < DISTANCE
						|| p.y > GUIControll.FIELD_SIZE.height - DISTANCE
						|| p.y < DISTANCE)
					guiControll.edgeReached(true);
				else
					guiControll.edgeReached(false);

//				SwingUtilities.convertMouseEvent(GlassPane.this, e, guiControll.getFigureField());
			}
//		});
//	}
	
}
