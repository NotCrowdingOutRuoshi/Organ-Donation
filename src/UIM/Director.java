package UIM;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Director {

	JFrame frame;

	public void setpanel(JPanel panel)
	{
		//this.frame.setContentPane(panel);
		JPanel contentPane = (JPanel) frame.getContentPane();
		
		contentPane.removeAll();
		contentPane.add(panel);
		contentPane.revalidate(); 
		contentPane.repaint();
	}

	public Director(JFrame frame)
	{
	    this.frame=frame;
	}
}
