package gui.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import controll.GUIControll;

import logic.content.FigureKind;

public class BuyFigurePanel extends JInternalFrame{
	
	private JPanel contentPane;
	private GUIControll guiControll;
	private BuyButton swordmen = new BuyButton(FigureKind.SWORDMAN);
	private BuyButton archer = new BuyButton(FigureKind.ARCHER);
	private BuyButton lightKnight = new BuyButton(FigureKind.LIGHT_KNIGHT);
	public static Dimension PANEL_SIZE = new Dimension(300, 400);
	
	private JToggleButton saveBtn;
	private ButtonGroup buttonGroup;
	
	
	public BuyFigurePanel(GUIControll guiControll) {
		this.guiControll = guiControll;
		initiatePanel();
		initiateListeners();
		swordmen.revalidate();
		swordmen.repaint();
	}
	
	private void initiatePanel(){
		contentPane = (JPanel) this.getContentPane();
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(swordmen)
					.addComponent(archer)
					.addComponent(lightKnight)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
					.addComponent(swordmen)
					.addComponent(archer)
					.addComponent(lightKnight)));
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(swordmen);
		buttonGroup.add(archer);
		buttonGroup.add(lightKnight);
		contentPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 50, 10));
		contentPane.setBackground(new Color(191, 96, 0, 255));
		this.setVisible(false);
		this.setBounds(0, 0, PANEL_SIZE.width, PANEL_SIZE.height);
	}
	
	
	
	private void initiateListeners(){
		setListener(swordmen);
		setListener(archer);
		setListener(lightKnight);
	}
	
	private void setListener(AbstractButton button){
//		button.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent e) {
		button.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if (saveBtn == e.getSource()){
					if (saveBtn == swordmen)
						guiControll.buyFigure(FigureKind.SWORDMAN);
					if (saveBtn == archer)
						guiControll.buyFigure(FigureKind.ARCHER);
					if (saveBtn == lightKnight)
						guiControll.buyFigure(FigureKind.LIGHT_KNIGHT);	
					
					return;
				}
				
				
			}
			public void mouseReleased(MouseEvent e){
				if (saveBtn == e.getSource()){
					buttonGroup.clearSelection();
					saveBtn = null;
					return;
				}
				saveBtn = (JToggleButton) e.getSource();
			}
			
		});
	}
	
	
	public LinkedList<BuyButton> getBuyButtons(){
		LinkedList<BuyButton> buttonList = new LinkedList<BuyButton>();
		buttonList.add(archer);
		buttonList.add(swordmen);
		buttonList.add(lightKnight);
		return buttonList;
	}
	
	
	
	
}
