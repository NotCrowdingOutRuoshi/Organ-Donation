package Resources;

import java.io.InputStream;

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
	public static InputStream getResource(String fileName) {
		return Resources.class.getResourceAsStream(fileName);
	}
}
