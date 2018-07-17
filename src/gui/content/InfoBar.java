package gui.content;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controll.GUIControll;

public class InfoBar extends JPanel{
	
	private JLabel goldWhite;
	private JLabel goldBlack;
	private JLayeredPane goldLayeredPane;
	private JLabel goldSymbol;
	
	public InfoBar() {
		initiateLayers();
		initiatePanel();
	}
	
	private void initiatePanel(){
		this.setBounds(0, 0, GUIControll.FIELD_SIZE.width, 30);
		this.setOpaque(false);
		this.setVisible(false);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGap(GUIControll.FIELD_SIZE.width - 100)
				.addComponent(goldSymbol)
				.addComponent(goldLayeredPane)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(goldSymbol)
						.addComponent(goldLayeredPane)));
		goldWhite.setToolTipText("Gold");
		goldSymbol.setToolTipText("Gold");
	}
	
	private void initiateLayers(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		goldLayeredPane = new JLayeredPane();
//		goldLayeredPane.setLayout(null);
//		Dimension size = new Dimension(100, 20);
//		goldLayeredPane.setPreferredSize(size);
//		goldLayeredPane.setMinimumSize(size);
//		goldLayeredPane.setMaximumSize(size);
		goldLayeredPane.setOpaque(false);
		//GOLD_BLACK
		goldBlack = new JLabel("0");
		goldBlack.setForeground(Color.ORANGE);
		goldBlack.setFont(goldBlack.getFont().deriveFont(18f));
		goldBlack.setBounds(2, 2, 100, 30);
		goldBlack.setOpaque(false);
		//GOLD_WHITE
		goldWhite = new JLabel("0");
		goldWhite.setForeground(Color.BLACK);
		goldWhite.setFont(goldWhite.getFont().deriveFont(18f));
		goldWhite.setBounds(0, 0, 100, 30);
		
		goldLayeredPane.add(goldWhite, new Integer(0));
		goldLayeredPane.add(goldBlack, new Integer(1));
		goldSymbol = new JLabel();
//		for (int i = 0; i< ge.getAvailableFontFamilyNames().length; i++){
//			System.out.println(ge.getAvailableFontFamilyNames()[i]);
//		}
	}
	
	public void updateGold(int amount){
		goldWhite.setText(String.valueOf(amount));
		goldBlack.setText(String.valueOf(amount));
//		System.out.println(amount);
	}
	
}
