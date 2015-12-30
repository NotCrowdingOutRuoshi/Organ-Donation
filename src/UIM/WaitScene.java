package UIM;
import javax.swing.JPanel;

import DynamicObjectModule.DynamicObjectModule;

import java.awt.Color;

import javax.swing.JLabel;

public class WaitScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public WaitScene(DynamicObjectModule dom) {
		this.setSize(900, 600);
		this.setLayout(null);
		
		/* Temporary comment out
		JLabel lblWait = new JLabel("wait"+dom.getPlayer().size());
		lblWait.setBounds((this.getWidth()-100)/2, 20, 100, 30);
		add(lblWait);
		
		for(int i=0;i<4;i++){
			String s = "null";
			if(i<dom.getPlayer().size()){
				s = dom.getPlayer().get(i);
			}
			JLabel Player1 = new JLabel(s);
			Player1.setBounds(120+i*200, (this.getHeight()-100)/2, 100, 30);
			add(Player1);
		}
		*/
		


		//tcp³s½u
		//dom.getplayer

	}

}
