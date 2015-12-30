package Utility;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import DynamicObjectModule.Animation;


public class AnimationUtil {
	public static Animation createWithFrameName(String name,String action) {
		int index = 1;
		List<BufferedImage> images = new ArrayList<BufferedImage>();
		do {
			String temp = name + "-" + action + "-" + String.valueOf(index);
			try {
				BufferedImage sprite = ImageIO.read(new File("images/" + temp + ".png"));
				images.add(sprite);
				index+=1;
			} catch (IOException e) {
				break;
			}
			
		} while (true);
		
		return new Animation(images,10);
		
	}

}
