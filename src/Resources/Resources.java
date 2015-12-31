package Resources;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class Resources {
	private Resources() {

	}

	/**
	 * Gets the InputStream of resource file. Example:
	 * Resources.getResource("Scene/Scene.jpg");
	 * 
	 * @param fileName
	 *            The name of resource file.
	 * @return Returns a InputStream if resource file is found, returns null
	 *         otherwise.
	 */
	public static InputStream getResourceStream(String fileName) {
		return Resources.class.getResourceAsStream(fileName);
	}

	public static File getResourceFile(String fileName) {
		URL url = Resources.class.getResource(fileName);
		if (url == null)
			return null;

		try {
			File file = new File(url.toURI());
			if (file.exists()) {
				return file;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
