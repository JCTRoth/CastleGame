package gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NewGamePanel extends JPanel{
	
	private MenuControll menuControll;
	private JButton quickBtn = new JButton("Schnellstart");
	private JButton ownBtn = new JButton("Eigenes Spiel");
	private JButton backBtn = new JButton("Zurück");
	
	public NewGamePanel(MenuControll menuControll) {
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
						.addComponent(quickBtn)
						.addComponent(ownBtn)
						.addComponent(backBtn))
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(quickBtn)
						.addComponent(ownBtn)
						.addComponent(backBtn))
				);
		
		layout.setAutoCreateGaps(true);
	}
	
	private void initiateListeners(){
		quickBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.newGameHandleQickStartSelection();
			}
		});
		ownBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.newGameHandleOwnGameSelection();
			}
		});
		backBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				menuControll.newGameHandleBackSelection();
			}
		});
	}
}
