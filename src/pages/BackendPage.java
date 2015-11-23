package pages;

import web.page_objects.BackendPagePO;
import core.Driver;
import core.TestUnit;

public class BackendPage extends BackendPagePO {

	private TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param in_testUnit
	 */
	public BackendPage(TestUnit in_testUnit) {
		super(in_testUnit);
		this.testUnit = in_testUnit;
	}

	public BackendPage startLocalBrowser() {
		if (testUnit.backendDriver == null) {
			testUnit.backendDriver = new Driver().startDriver("Firefox",
					"local");
		}

		goToAdmin();

		if (testUnit.backendDriver.getCurrentUrl().contains("login.php")) {
			loginToBase().goToAdmin();
		}
		return this;
	}

	public BackendPage loginToBase() {
		fieldLogin().clear();
		fieldLogin().sendKeys(testUnit.getConfig("admin.live.login"));
		fieldPassword().clear();
		fieldPassword().sendKeys(testUnit.getConfig("admin.live.password"));
		btnLogin().click();
		return this;
	}

	public void goToAdmin() {
		if (testUnit.siteName.equalsIgnoreCase("iwantu")) {
			testUnit.backendDriver.get("https://www.iwantu.com/admin/find.php");
		} else {
			testUnit.backendDriver
					.get("https://www.upforit.com/admin/find.php");
		}
	}

	public String getAutologin(String in_searchValue) {
		startLocalBrowser();
		fieldSearch().sendKeys(in_searchValue);
		fieldSearch().submit();
		linkAutologin().getText().equalsIgnoreCase(
				testUnit.getConfig("admin.site." + testUnit.siteName));
		String autologin = linkAutologin().getAttribute("href");
		testUnit.backendDriver.quit();
		return autologin;
	}

	public String getId() {
		return null;
	}

}
