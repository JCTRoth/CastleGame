package gui.content;


import gui.images.Images;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controll.GUIControll;

import logic.content.FigureKind;

public class FigureInfoPanel extends JPanel{
	
	private FigureInfoPanel panel = this;
	private FigureKind figureKind;
	private JLabel picture;
	private JLabel name;
	private JLabel weapon;
	private JLabel weaponInfo;
	private JLabel strength;
	private JLabel strengthInfo;
	private JLabel weakness;
	private JLabel weaknessInfo;
	private JLabel costs;
	private JLabel costsInfo;
	
	private Images images = GUIControll.images;
	
	public FigureInfoPanel(FigureKind figureKind) {
		this.figureKind = figureKind;
		initiateLayers();
		initiatePanel();
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(picture)
//				.addGap(30)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup()
								.addComponent(name)
								.addGroup(layout.createSequentialGroup()
										.addComponent(weapon)
										.addComponent(weaponInfo))
								.addGroup(layout.createSequentialGroup()
										.addComponent(costs)
										.addComponent(costsInfo))
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(strength)
											.addComponent(weakness))
									.addGroup(layout.createParallelGroup()
											.addComponent(strengthInfo)
											.addComponent(weaknessInfo)))
								)
						)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(picture)
						.addGroup(layout.createSequentialGroup()
								.addComponent(name)
								.addGroup(layout.createParallelGroup()
										.addComponent(weapon)
										.addComponent(weaponInfo))
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(costs)
											.addComponent(costsInfo))
									.addGroup(layout.createParallelGroup()
											.addComponent(strength)
											.addComponent(strengthInfo))
									.addGroup(layout.createParallelGroup()
											.addComponent(weakness)
											.addComponent(weaknessInfo))
									)
								)
						)
				);
		
		this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		this.setBackground(new Color(191, 96, 0, 255));
	}
	
	private void initiateLayers(){
		initiatePicture();
		name = new JLabel(figureKind.getName());
		weapon = new JLabel("Waffe: ");
		weaponInfo = new JLabel(figureKind.getStandardWeapon().getName());
		strength = new JLabel("Stark gegen:");
		strengthInfo = new JLabel(figureKind.getClassType().createHtmlListStrength());
		weakness = new JLabel("Schwach gegen:");
		weaknessInfo = new JLabel(figureKind.getClassType().createHtmlListWeakness());
		costs = new JLabel("Kosten: ");
		costsInfo = new JLabel(Integer.toString(figureKind.getCosts()));
		
		
		name.setFont(name.getFont().deriveFont(14f));
		smalerFont(weapon);
		smalerFont(strength);
		smalerFont(weakness);
		smalerFont(costs);
	}
	
	private void smalerFont(JLabel l){
		l.setFont(l.getFont().deriveFont(l.getFont().getSize() - 2));
	}
	
//	private String smalerFont(String s){
//		s = "<html><body><font size = -2>" + s + "</font></body></html>";
//		return s;
//	}
	
	private void initiatePicture(){
		final Dimension size = new Dimension(75, 75);

		picture = new JLabel(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public void paint(Graphics g){
				super.paint(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(images.getResizedImage((int)size.getWidth(), (int)size.getHeight(), figureKind.getStandardImage()), 0, 0, null);
				
			}
		};
		
		picture.setMinimumSize(size);
		picture.setPreferredSize(size);
		picture.setMaximumSize(size);
		picture.setBackground(new Color(0, 0, 0, 0));
		picture.setOpaque(true);
		ImageIcon img = new ImageIcon(images.getResizedImage((int)size.getWidth(), (int)size.getHeight(), figureKind.getStandardImage()));
		picture.setIcon(img);
		picture.repaint();
		
	}
	
}
