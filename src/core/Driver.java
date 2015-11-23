package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Driver {

	protected WebDriver driver;

	/**
	 * Starting driver
	 * 
	 * @param in_browser
	 * @param in_proxy
	 * @return WebDriver driver
	 */
	public WebDriver startDriver(String in_browser, String in_proxy) {

		String PROXY;

		switch (in_proxy) {
		case "us_cupid":
			PROXY = ConfigProperties.getProperty("proxy.us.cupid");
			break;
		case "uk_cupid":
			PROXY = ConfigProperties.getProperty("proxy.uk.cupid");
			break;
		case "us_geo":
			PROXY = ConfigProperties.getProperty("proxy.us");
			break;
		case "uk_geo":
			PROXY = ConfigProperties.getProperty("proxy.uk");
			break;
		case "au_geo":
			PROXY = ConfigProperties.getProperty("proxy.au");
			break;
		case "ca_geo":
			PROXY = ConfigProperties.getProperty("proxy.ca");
			break;
		case "br_geo":
			PROXY = ConfigProperties.getProperty("proxy.br");
			break;
		case "fr_geo":
			PROXY = ConfigProperties.getProperty("proxy.fr");
			break;
		case "de_geo":
			PROXY = ConfigProperties.getProperty("proxy.de");
			break;
		case "in_geo":
			PROXY = ConfigProperties.getProperty("proxy.in");
			break;
		case "ie_geo":
			PROXY = ConfigProperties.getProperty("proxy.ie");
			break;
		case "it_geo":
			PROXY = ConfigProperties.getProperty("proxy.it");
			break;
		case "mx_geo":
			PROXY = ConfigProperties.getProperty("proxy.mx");
			break;
		case "nl_geo":
			PROXY = ConfigProperties.getProperty("proxy.nl");
			break;
		case "nz_geo":
			PROXY = ConfigProperties.getProperty("proxy.nz");
			break;
		case "es_geo":
			PROXY = ConfigProperties.getProperty("proxy.es");
			break;
		case "local":
			PROXY = ConfigProperties.getProperty("proxy.local");
			break;
		default:
			PROXY = ConfigProperties.getProperty("proxy.uk.cupid");
			break;
		}

		//Proxy cap_proxy = new org.openqa.selenium.Proxy();
		//cap_proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		//DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setCapability(CapabilityType.PROXY, cap_proxy);

		switch (in_browser) {
		case "Firefox":
			driver = new FirefoxDriver();
			break;
		case "InternetExplorer":
			driver = new InternetExplorerDriver();
			break;
		case "Chrome":
			driver = new ChromeDriver();
			break;
		/*case "Android":
			driver = new AndroidDriver(capabilities);
			break;*/
		}

		driver.manage()
				.timeouts()
				.implicitlyWait(
						Long.parseLong(ConfigProperties.getProperty("imp.wait")),
						TimeUnit.SECONDS);

		return driver;
	}

	/**
	 * 
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		driver.quit();
	}

}
