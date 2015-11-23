package core;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pages.BackendPage;

public class TestFrame {

	public TestUnit testUnit;
	public PageFrame pageFrame;
	public BackendPage backend;

	/**
	 * Initialize parameters for test
	 */
	@BeforeSuite
	public void init() {
		/* Create QA object */
		this.testUnit = new TestUnit();
		this.testUnit.testName = this.getClass().getSimpleName();
		pageFrame = new PageFrame(this.testUnit);
		backend = new BackendPage(this.testUnit);
	}

	@Parameters({ "site", "build", "browser", "proxy", "userGender", "location" })
	@BeforeTest
	public void testInit(String in_site, String in_build, String in_browser,
			String in_proxy, String in_userGender, String in_location) {
		testUnit.init(in_site, in_build, in_browser, in_proxy, in_location);
		// this.driver = new
		// Driver().startDriver("Firefox","proxy-uk.cupidplc.com:3128");

	}

	/*
	 * @AfterTest public void tearDown() throws Exception { driver.quit(); }
	 */

}
