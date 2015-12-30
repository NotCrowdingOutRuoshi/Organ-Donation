package UIM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuScene() {
		this.setSize(900, 600);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		
		JButton btn = new JButton("遊戲開始");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().setGameStatus("wait");
				GameManager.getInstance().setClientId(1);
				GameManager.getInstance().setGameStatus("game");
			}
		});
		btn.setBounds((this.getWidth()-100)/2, 50, 100, 30);
		
		JButton btn2 = new JButton("遊戲說明");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"搶器官","遊戲說明",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn2.setBounds((this.getWidth()-100)/2, 100, 100, 30);
		
		JButton btn3 = new JButton("離開");
		btn3.setBounds((this.getWidth()-100)/2, 150, 100, 30);
		
		add(btn);
		add(btn2);
		add(btn3);
	}

}
