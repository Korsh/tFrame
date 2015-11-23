package core;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class TestProperties {

	private static Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		URL props = ClassLoader.getSystemResource("test.properties");

		try {
			PROPERTIES.load(props.openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String in_key) {
		return PROPERTIES.getProperty(in_key);
	}

}
