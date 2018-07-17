package gui.content;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;

import logic.content.FigureKind;

public class BuyButton extends JToggleButton implements ItemListener{
	
	private BuyButton button = this;
	private BevelBorder borderRaised;
	private BevelBorder borderLowered;
	
	public BuyButton(FigureKind figureKind) {
		this.add(new FigureInfoPanel(figureKind));
		
		
		borderRaised = new BevelBorder(BevelBorder.RAISED, new Color(255, 150, 45, 255), new Color(64, 32, 0, 255));
		borderLowered = new BevelBorder(BevelBorder.LOWERED, new Color(255, 150, 45, 255), new Color(64, 32, 0, 255));
		this.setBorder(borderRaised);

		this.addItemListener((ItemListener) this);
	}
	
	@Override
	public void setSelected(boolean b){
		super.setSelected(b);
		if (b)
			this.setBorder(borderLowered);
		else
			this.setBorder(borderRaised);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (!this.isSelected()){
			this.setBorder(borderRaised);
		}
		else{
			this.setBorder(borderLowered);
		}
//		System.out.println("ItemStateEvent");
	}
	
}
