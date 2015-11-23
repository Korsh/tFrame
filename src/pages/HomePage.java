package pages;

import org.openqa.selenium.By;

import web.page_objects.IndexPagePO;
import core.TestUnit;

public class HomePage extends IndexPagePO {
	private TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param in_testUnit
	 */
	public HomePage(TestUnit in_testUnit) {
		super(in_testUnit);
		this.testUnit = in_testUnit;
	}

	/**
	 * Move to page
	 * 
	 * @return IndexPage this
	 */
	@Override
	public HomePage open() {
		try {
			openPage("/");

		} catch (Exception ex) {
			testUnit.log.error("Page didn't load: ", ex);
			testUnit.driver.quit();
		}

		return this;
	}

	/**
	 * Check is welcome page
	 * 
	 * @return boolean
	 */
	public boolean checkIsWelcomePage() {
		return isElementPresent(By.id("email_domain"));
	}
}
