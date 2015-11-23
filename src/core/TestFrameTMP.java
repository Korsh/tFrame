package core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

@Listeners({ TestFrameTMP.LogListener.class })
public class TestFrameTMP {
	public WebDriver driver;
	public String baseUrl;

	/**
	 * Initialize parameters for test
	 */
	@BeforeSuite
	public void init() {

		String PROXY = "proxy-uk.cupidplc.com:3128";

		org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
		proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		driver = new FirefoxDriver(capabilities);
		/*
		 * Proxy proxy = new Proxy();
		 * proxy.setHttpProxy("p-uk1.biscience.com:80");
		 * //ana4@idegroup.com:kwef925 DesiredCapabilities capabilities = new
		 * DesiredCapabilities();
		 * capabilities.setCapability(CapabilityType.PROXY, proxy); driver = new
		 * FirefoxDriver(capabilities);
		 */
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Parameters({ "server", "os", "platform", "browser", "browserVersion",
			"site", "build", "proxy", "testUser", "userEmail", "userGender",
			"userIsPaid", "userSexuality", "userCountry" })
	@BeforeTest
	public void testInit() {
		// baseUrl = "http://joyreactor.cc/";
	}

	@AfterTest
	public void testQuit() {
		// driver.quit();
	}

	/**
	 * Shutdown a test
	 */
	@AfterSuite
	public void shutdown() {
		driver.quit();
	}

	public static class LogListener implements IInvokedMethodListener {
		@Override
		public void afterInvocation(IInvokedMethod p_message,
				ITestResult p_result) {
			System.out.println("message: " + p_message);
		}

		@Override
		public void beforeInvocation(IInvokedMethod p_message,
				ITestResult p_result) {
			System.out.println("message: " + p_message);
		}
	}

}
