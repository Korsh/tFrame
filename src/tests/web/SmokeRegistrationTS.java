package tests.web;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import pages.IndexPage;
import core.DateTime;
import core.TestFrame;

public class SmokeRegistrationTS extends TestFrame {
	// private IndexPage indexPage = PageFactory.initElements(getWebDriver(),
	// IndexPage.class);
	public IndexPage indexPage;

	DateTime getDate = new DateTime();
	String uniqueAdding = getDate.getDateTime();
	String gender = "male";
	String userMail = "nicky.t." + gender + "+" + uniqueAdding + "@gmail.com";
	String userLogin = "nicky" + uniqueAdding;
	String userPassword = "123-ab";

	@DataProvider
	public Object[][] checkValidateMailData() {
		return new Object[][] {
		/* Positive test */
		{ userMail, true },
		/* Negative test */
		{ testUnit.getTestConfig("email.nonvalid.confirm"), false },
				{ testUnit.getTestConfig("email.nonvalid.nonconfirm"), false },
				{ testUnit.getTestConfig("email.nonvalid.cancel"), false },
				{ "", false }, };
	}

	@DataProvider
	public Object[][] checkValidatePasswordData() {
		return new Object[][] {
		/* Positive test */
		{ userPassword, true },
		/* Negative test */
		{ "", false }, { "12345`", false }, { "12345@", false },
				{ "12345#", false }, { "12345$", false }, { "12345%", false },
				{ "12345^", false }, { "12345&", false }, { "12345*", false },
				{ "12345 ", false }, { "12345(", false }, { "12345)", false },
				{ "12345+", false }, { "12345=", false }, { "12345{", false },
				{ "12345}", false }, { "12345[", false }, { "12345]", false },
				{ "12345\\", false }, { "12345|", false }, { "12345/", false },
				{ "12345?", false }, { "12345.", false }, { "12345,", false },
				{ "12345~", false }, { "12345!", false }, { "12345\"", false },
				{ "12345'", false }, { "12345;", false }, { "12345:", false },

		};
	}

	@DataProvider
	public Object[][] checkValidateLocationData() {
		return new Object[][] {
		/* Positive test */
		{ "London", true },
		/* Negative test */
		{ "", false }, { " ", false }, };
	}

	@DataProvider
	public Object[][] registerUserData() {
		return new Object[][] {
		/* Positive test */
		{ "24", "London", gender, userMail, userLogin, userPassword, true }, };
	}

	@DataProvider
	public Object[][] checkConfirmData() {
		return new Object[][] {
		/* Positive test */
		{ userMail, true }, };
	}

	@DataProvider
	public Object[][] funnelScreenname() {
		return new Object[][] {
		/* Negative test */
		{ " ", false }, { "ab", false }, { "ab`", false },
		/* Positive test */
		{ userLogin, true },

		};
	}

	@DataProvider
	public Object[][] funnelScreennameVariant() {
		return new Object[][] {
		/* Positive test */
		{ "nicky", true }, };
	}

	@DataProvider
	public Object[][] funnelBirthday() {
		return new Object[][] {
		/* Nagative test */
		{ "31", "02", "1980", false }, { "31", "12", "1995", false },
		/* Positive test */
		{ "10", "10", "1980", true }, };
	}

	@DataProvider
	public Object[][] funnelConfirm() {
		return new Object[][] {
		/* Positive test */
		{ userLogin, "10", "09", "1980" }, };
	}

	@BeforeClass
	public void registerToSite() {
		/**
		 * TODO Login to a site Expected result: User is logged in
		 */

		indexPage = new IndexPage(testUnit).open();
	}

	@Test(dataProvider = "checkValidateMailData", groups = "validate", priority = 1, enabled = true)
	public void checkAgeValidate(String in_regMail, boolean in_expected) {
		indexPage.setRegMail(in_regMail);
		AssertJUnit.assertEquals(indexPage.checkEmailValidation(), in_expected);
	}

	@Test(dataProvider = "checkValidatePasswordData", groups = "validate", priority = 1, enabled = true)
	public void checkPasswordValidate(String in_regPassword, boolean in_expected) {
		indexPage.setRegPassword(in_regPassword);
		System.out.println("Password: " + in_regPassword);
		AssertJUnit.assertEquals(indexPage.checkPasswordValidation(), in_expected);
	}

	@Test(dataProvider = "checkValidateLocationData", groups = "validate", priority = 1, enabled = true)
	public void checkLocationValidate(String in_regLocation, boolean in_expected) {
		indexPage.setRegLocation(in_regLocation);
		System.out.println("Password: " + in_regLocation);
		AssertJUnit.assertEquals(indexPage.checkLocationValidation(), in_expected);
	}

	@Test(dataProvider = "registerUserData", groups = "register", priority = 2, enabled = true)
	public void registerTest(String in_regAge, String in_regLocation,
			String in_regGender, String in_regMail, String in_regLogin,
			String in_regPassword, boolean in_expected) {
		indexPage.registerUser(in_regAge, in_regLocation, in_regGender,
				in_regMail, in_regLogin, in_regPassword);
		AssertJUnit.assertEquals(indexPage.checkIsWelcomePage(), true);
	}

	/*
	 * @Test(dataProvider = "checkWelcomePageResendMailData",
	 * groups="welcomePage", priority=3, enabled=false) public void
	 * checkWelcomePageResendMailTest(String in_regMail, boolean in_expected) {
	 * indexPage.checkWelcomePageResendMail();
	 * Assert.assertEquals(indexPage.checkIsPostConfirmPage(), in_expected); }
	 * 
	 * @Test(dataProvider = "checkWelcomePageChangeMailData",
	 * groups="welcomePage", priority=3, enabled=false) public void
	 * checkWelcomePageChangeMailTest(String in_regMail, boolean in_expected) {
	 * indexPage.checkWelcomePageChangeMail();
	 * Assert.assertEquals(indexPage.checkIsPostConfirmPage(), in_expected); }
	 */

	@Test(dataProvider = "checkConfirmData", groups = "confirm", priority = 3, enabled = true)
	public void confirmTest(String in_regMail, boolean in_expected) {
		indexPage.loginByAutologin(backend.getAutologin(in_regMail));
		AssertJUnit.assertEquals(indexPage.checkIsPostConfirmPage(), in_expected);
	}

	@Test(dataProvider = "funnelScreennameVariant", groups = "funnelScreenname", priority = 5, enabled = true)
	public void checkPostConfirmScreennameVariant(String in_screenname,
			boolean in_expected) {
		// if(testUnit.driver.getCurrentUrl().contains("post_confirm.php"))
		// {
		indexPage.setScreennamePostConfirm(in_screenname);
		String variant = indexPage.lnkFunnStep1ScreennameVariant().getText();
		indexPage.lnkFunnStep1ScreennameVariant().click();
		AssertJUnit.assertEquals(indexPage.fieldPostConfirmScreenname()
				.getAttribute("value").equals(variant), in_expected);
		/*
		 * } else { throw new SkipException("Skip"); }
		 */
	}

	@Test(dataProvider = "funnelScreenname", groups = "funnelScreenname", priority = 6, enabled = false)
	public void checkPostConfirmScreenname(String in_screenname,
			boolean in_expected) {
		if (testUnit.driver.getCurrentUrl().contains("post_confirm.php")) {
			indexPage.setScreennamePostConfirm(in_screenname);
			AssertJUnit.assertEquals(indexPage.isScreennameErrorPostConfirm(),
					in_expected);
		} else {
			throw new SkipException("Skip");
		}
	}

	@Test(dataProvider = "funnelBirthday", groups = "funnelScreenname", priority = 4, enabled = true)
	public void checkPostConfirmBirthday(String in_day, String in_month,
			String in_year, boolean in_expected) {
		if (testUnit.driver.getCurrentUrl().contains("post_confirm.php")) {
			indexPage.setPostConfirmBirthday(in_day, in_month, in_year);
			AssertJUnit.assertEquals(indexPage.isBirthdayErrorPostConfirm(),
					in_expected);
		} else {
			throw new SkipException("Skip");
		}
	}

	@Test(dataProvider = "funnelConfirm", groups = "funnelScreenname", priority = 6, enabled = true)
	public void confirmUser(String in_screenname, String in_day,
			String in_month, String in_year) {
		if (testUnit.driver.getCurrentUrl().contains("post_confirm.php")) {
			//indexPage.setScreennamePostConfirm(in_screenname);
			indexPage.setPostConfirmBirthday(in_day, in_month, in_year);
			indexPage.btnPostConfirmSubmit().click();
			AssertJUnit.assertEquals(testUnit.driver.getCurrentUrl().contains("/profilep.asp?rv=2"), true);
		} else {
			indexPage.openPage("/email_subscribe.php");
			throw new SkipException("Skip");
		}
	}

}
