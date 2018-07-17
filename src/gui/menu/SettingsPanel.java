package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel{
	
	private SettingsPanel panel = this;
	private MenuControll menuControll;
	private JButton soundBtn = new JButton("Sound");
	private JButton graphicsBtn = new JButton("Grafik");
	private JButton gameSettingsBtn = new JButton("Spiel");
	private JButton backBtn = new JButton("Zurück");
	
	
	public SettingsPanel(MenuControll menuControll){
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
						.addComponent(soundBtn)
						.addComponent(graphicsBtn)
						.addComponent(gameSettingsBtn)
						.addComponent(backBtn)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(soundBtn)
						.addComponent(graphicsBtn)
						.addComponent(gameSettingsBtn)
						.addComponent(backBtn)));
		
		layout.setAutoCreateGaps(true);
		
	}
	
	
	private void initiateListeners(){
		soundBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.SettingsHandleSoundSelection();
			}
		});
		graphicsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.SettingsHandleGraphicsSelection();
			}
		});
		gameSettingsBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.SettingsHandleGameSettingsSelection();
			}
		});
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.settingsHandleBackSelection();
			}
		});
	}
	
}
