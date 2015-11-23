package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUnit {
	public String testName;
	public String testPage;
	public WebDriver backendDriver;
	public WebDriver driver;
	public String siteUrl;
	public String siteName;
	public String siteLocation;
	/* JS Executor */
	public JavascriptExecutor js;
	/* Sikuli */
	public Sikuli sikuli;
	/* Randomizer */
	// public Randomizer random;
	/* Screen */
	public Screen screen;
	public Logger log = LoggerFactory.getLogger(TestFrame.class);

	/**
	 * Initialize a test
	 * 
	 * @param in_site
	 * @param in_build
	 * @param in_browser
	 * @param in_proxy
	 */
	public void init(String in_site, String in_build, String in_browser,
			String in_proxy, String in_location) {
		/* Sikuli initialization */
		sikuli = new Sikuli(this);
		/* Randomizer initialization */
		// random = new Randomizer();
		/* Screen initialization */
		screen = new Screen();
		this.driver = new Driver().startDriver(in_browser, in_proxy);
		/* JS Executor */
		js = (JavascriptExecutor) this.driver;
		siteUrl = getConfig("site." + in_build + "." + in_site);
		siteLocation = in_location;
		siteName = getConfig("site.name." + in_site);
		log.info("TestUnit initialized...");
		log.info("Test settings: \r\n " + "Site: " + in_site + "\r\n "
				+ "Build: " + in_build + "\r\n " + "Proxy: " + in_proxy
				+ "\r\n " + "Browser: " + in_browser);
	}

	public String getConfig(String in_param) {
		return ConfigProperties.getProperty(in_param);
	}

	public String getTestConfig(String in_param) {
		return TestProperties.getProperty(in_param);
	}

}
