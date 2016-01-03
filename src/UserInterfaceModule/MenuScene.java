package UserInterfaceModule;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Common.Constants;
import Resources.Resources;

public class MenuScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuScene() {
		this.setSize(1450, 850);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);

		JButton btn = new JButton("遊戲開始");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().setGameStatus(Constants.GAME_STATE_WAIT);
				//GameManager.getInstance().setGameStatus(Constants.GAME_STATE_OVER);
			}
		});
		btn.setBounds((this.getWidth()-200)/2, 100, 200, 60);
		
		JButton btn2 = new JButton("遊戲說明");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"搶器官","遊戲說明",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn2.setBounds((this.getWidth()-200)/2, 200, 200, 60);
		
		JButton btn3 = new JButton("離開");
		btn3.setBounds((this.getWidth()-200)/2, 300, 200, 60);
		
		add(btn);
		add(btn2);
		add(btn3);
	}
	
	 public void paintComponent(Graphics g){
		 
		 try {
			ImageIcon icon=new ImageIcon(ImageIO.read(Resources.getResourceStream("Scene/menu.jpg")));
			 g.drawImage(icon.getImage(),0,0,getSize().width,getSize().height,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	 }

}
