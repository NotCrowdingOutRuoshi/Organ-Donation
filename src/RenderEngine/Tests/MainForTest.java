package RenderEngine.Tests;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Common.Constants;
import Common.StateType;
import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.VirtualCharacter;
import RenderEngine.RenderEngine;
import Resources.Resources;
import SceneDataModule.SceneDataModule;

public class MainForTest {
	public static void main(String[] args) throws IOException {
		SceneDataModule sdm = new SceneDataModule(ImageIO.read(Resources.getResourceStream("Scene/Scene.jpg")));
		DynamicObjectModule dom = new DynamicObjectModule();
		VirtualCharacter v = new VirtualCharacter(0, 0, 0, Constants.ACTIONCODE_EAST, 0);
		dom.addSprite(v);

		RenderEngine renderEngine = new RenderEngine(dom, sdm);
		JPanel panel = renderEngine.getPanel();
		panel.addKeyListener(new HeyMyListener(v));
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		v.setState(StateType.DEATH);

		JFrame frame = new JFrame("Organ Donation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setSize(1450, 850);
		frame.setVisible(true);

		renderEngine.startRendering();
		// renderEngine.stopRendering();
	}
}
