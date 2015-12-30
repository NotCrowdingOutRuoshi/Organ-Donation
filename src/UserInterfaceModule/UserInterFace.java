package UserInterfaceModule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DynamicObjectModule.DynamicObjectModule;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInterFace extends JFrame {


	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterFace frame = new UserInterFace();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInterFace() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		MenuScene ms = new MenuScene();
		contentPane.add(ms);
		Director director = new Director(this);
		DynamicObjectModule dom = new DynamicObjectModule(null);
		GameManager.getInstance().setDom(dom);
		GameManager.getInstance().setDirector(director);

	}


}
