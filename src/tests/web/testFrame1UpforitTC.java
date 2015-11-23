package tests.web;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.IndexPage;
import core.DateTime;
import core.TestFrame;

public class testFrame1UpforitTC extends TestFrame {
	// private IndexPage indexPage = PageFactory.initElements(getWebDriver(),
	// IndexPage.class);
	public IndexPage indexPage;

	DateTime getDate = new DateTime();
	String uniqueAdding = getDate.getDateTime();
	String gender = "male";
	String userMail = "nicky.t.+" + gender + uniqueAdding + "@gmail.com";
	String userLogin = "nicky" + uniqueAdding;
	String userPassword = "123123";

	@Test(dataProvider = "registerUserData", groups = "register", priority = 1, enabled = true)
	public void registerTest(String in_regAge, String in_regLocation,
			String in_regGender, String in_regMail, String in_regLogin,
			String in_regPassword, boolean in_expected) {
		indexPage.loginToGmail("nicky.t." + gender, "View666Sonic356");
		testUnit.log.info("login to gmail");
		try {
			indexPage.deleteGmailMessages();
			testUnit.log.info("delete messages");
		} catch (Exception e) {

		}

		indexPage.openUrl("http://www.upforit.com");
		testUnit.log.info("try register");
		indexPage.registerUser(in_regAge, in_regLocation, in_regGender,
				in_regMail, in_regLogin, in_regPassword);
		// indexPage.loginByAutologin(backend.getAutologin(userMail));

		Assert.assertEquals(indexPage.checkIsWelcomePage(), true);
	}

	@Test(groups = "welcomePage", priority = 2, enabled = true)
	public void logingMailTest() {
		indexPage.loginToGmail("nicky.t." + gender, "View666Sonic356");

		indexPage.openFirstIncomeLetter();
		Assert.assertEquals(indexPage.checkIsPostConfirmPage(), true);
	}
}
