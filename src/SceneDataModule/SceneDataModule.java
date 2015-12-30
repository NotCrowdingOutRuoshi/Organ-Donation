package SceneDataModule;

import java.awt.Image;

public class SceneDataModule {
	private Image _image;
	
	public SceneDataModule(Image backgroundImage) {
		assert (backgroundImage != null);
		
		_image = backgroundImage;
	}
	
	public Image getImage() {
		return _image;
	}
	
	public void setImage(Image backgroundImage) {
		assert (backgroundImage != null);
		
		_image = backgroundImage;
	}
}
