package gui.menu;

import controll.GUIControll;
import gui.MainFrame;
import gui.content.BuyFigurePanel;

/**
 * controls the gui elements in the main menu of the program
 * initiates the components therefore and adds it to the main frame 
 * @author Jonas Roth
 */
public class MenuControll {
	
	MainFrame frame;
	MainPanel mainPanel;
	NewGamePanel newGamePanel;
	SettingsPanel settingsPanel;
	GUIControll guiControll;
	
	public MenuControll(GUIControll guiControll, MainFrame frame) {
		this.frame = frame;
		this.guiControll = guiControll;
		
		initiatePanels();
		addPanelsToContentPane();
		
		start();
	}
	
	public void start(){
		mainPanel.setVisible(true);
	}
	
	private void initiatePanels(){
		mainPanel = new MainPanel(this);
		settingsPanel = new SettingsPanel(this);
		newGamePanel = new NewGamePanel(this);
	}
	
	private void addPanelsToContentPane(){
		frame.add(mainPanel);
		frame.add(settingsPanel);
		frame.add(newGamePanel);
	}
	
	public void handleNewGameSelection(){
		mainPanel.setVisible(false);
		newGamePanel.setVisible(true);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void handleSettingsSelection(){
		settingsPanel.setVisible(true);
		mainPanel.setVisible(false);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void handleExitSelection(){
		System.exit(0);
	}
	
	
	public void SettingsHandleSoundSelection(){
		
	}
	
	public void SettingsHandleGraphicsSelection(){
		
	}
	
	public void SettingsHandleGameSettingsSelection(){
		
	}

	public void settingsHandleBackSelection(){
		settingsPanel.setVisible(false);
		mainPanel.setVisible(true);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void newGameHandleQickStartSelection(){
		
		guiControll.setFieldVisible();
		//missing qick-start settings!!!
		newGamePanel.setVisible(false);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	public void newGameHandleOwnGameSelection(){
		
	}
	
	public void newGameHandleBackSelection(){
		newGamePanel.setVisible(false);
		mainPanel.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	
}
