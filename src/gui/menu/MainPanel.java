package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel{
	
	private MainPanel mainPanel = this;
	private MenuControll menuControll;
	private JButton startBtn = new JButton("Neues Spiel");
	private JButton settingsBtn = new JButton("Einstellungen");
	private JButton exitBtn = new JButton("Spiel beenden");
	
	
	public MainPanel(MenuControll menuControll) {
		this.menuControll = menuControll;
		initiatePanel();
		initiateListeners();
		
		this.setVisible(false);
	}
	
	
	private void initiatePanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(startBtn)
						.addComponent(settingsBtn)
						.addComponent(exitBtn)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(startBtn)
						.addComponent(settingsBtn)
						.addComponent(exitBtn)));
		
		layout.setAutoCreateGaps(true);

	}
	
	private void initiateListeners(){
		startBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.handleNewGameSelection();
			}
		});
		settingsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.handleSettingsSelection();
			}
		});
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.handleExitSelection();
			}
		});
	}
	
	
}
