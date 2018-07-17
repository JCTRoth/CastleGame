package gui.content;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.content.Figure;

public class FigureToolBoxInfo extends JPanel{
	
	private Figure figure;
	private JLabel name;
	private JLabel level;
	private JLabel weapon;
	private JLabel effects;
	
	public FigureToolBoxInfo(Figure figure) {
		this.figure = figure;
		
		initiateLabels();
		initiatePanel();
		this.setVisible(true);
	}
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(name)
						.addComponent(level)
						.addComponent(weapon)
						.addComponent(effects)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(name)
						.addComponent(level)
						.addComponent(weapon)
						.addComponent(effects)));
		
		this.setBackground(new Color(191, 96, 0, 255));
		this.setBounds(0, 0, 100, 100);
		this.setMinimumSize(new Dimension(100, 100));
		this.setPreferredSize(new Dimension(100, 100));
		this.setMaximumSize(new Dimension(100, 100));
	}
	
	private void initiateLabels(){
		name = new JLabel("");
		level = new JLabel("");
		weapon = new JLabel("");
		effects = new JLabel("");
		
		refresh();
	}
	
	public void refresh(){
		name.setText(figure.getName());
		if (figure.isKing())
			level.setText(Integer.toString(figure.getLevel())+ ", König");
		else
			level.setText(Integer.toString(figure.getLevel()));
		weapon.setText(figure.getWeapon().getName());
		effects.setText("Effekte: " + figure.getEffectsToString());
	}
	
	
}
