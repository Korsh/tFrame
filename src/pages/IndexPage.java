package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import web.page_objects.IndexPagePO;
import core.DateTime;
import core.TestUnit;

public class IndexPage extends IndexPagePO {
	private TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param in_testUnit
	 */
	public IndexPage(TestUnit in_testUnit) {
		super(in_testUnit);
		this.testUnit = in_testUnit;
	}

	/**
	 * Move to page
	 * 
	 * @return IndexPage this
	 */
	@Override
	public IndexPage open() {
		try {
			openPage("/");

		} catch (Exception ex) {
			testUnit.log.error("Page didn't load: ", ex);
			testUnit.driver.quit();
		}

		return this;
	}

	/**
	 * Register user
	 * 
	 * @param in_userMail
	 * @param in_userPassword
	 */
	public void registerUser(String in_regAge, String in_regLocation,
			String in_regGender, String in_regMail, String in_regLogin,
			String in_regPassword) {
		testUnit.log.info("registering user...");
		setRegAge("10", "10", in_regAge).setRegMail(in_regMail)
				.setRegGender(in_regGender).setRegPassword(in_regPassword)
				.setRegLocation(in_regLocation).btnRegSubmit().click();
	}

	/**
	 * Check is welcome page
	 * 
	 * @return boolean
	 */
	public boolean checkIsWelcomePage() {
		return isElementPresent(By.className("user_email"));
	}

	public HomePage loginByAutologin(String in_autologin) {
		openUrl(in_autologin);
		if (testUnit.driver.getCurrentUrl().contains("post_confirm")) {
			// new PostConfirmPage(testUnit);
		} else if (testUnit.driver.getCurrentUrl().contains("profile")) {
			// new FunnelPage(testUnit);
		}

		return new HomePage(testUnit);

	}

	public IndexPage setRegGender(String in_regGender) {
		String gender;
		if (in_regGender == "male") {
			gender = "1";
		} else if (in_regGender == "female") {
			gender = "2";
		} else {
			gender = "3";
		}

		List<WebElement> optionsGender = selectRegisterAge().findElements(
				By.tagName("option"));

		for (WebElement option : optionsGender) {
			if (option.getText().equals(gender)) {
				option.click();
				break;
			}
		}

		testUnit.log.info("Enter registration gender: " + in_regGender);
		return this;
	}

	public IndexPage setRegMail(String in_regMail) {
		fieldRegisterEmail().clear();
		fieldRegisterEmail().sendKeys(in_regMail);
		blockHeader().click();
		testUnit.log.info("Enter registration email: " + in_regMail);
		return this;
	}

	public boolean checkEmailValidation() {
		return !isElementPresent(blockRegEmailInvalid());
	}

	public IndexPage setRegPassword(String in_regPassword) {
		fieldRegisterPassword().clear();
		fieldRegisterPassword().sendKeys(in_regPassword);
		blockHeader().click();
		testUnit.log.info("Enter registration password: " + in_regPassword);
		return this;
	}

	public boolean checkPasswordValidation() {
		return !isElementPresent(blockRegPasswordInvalid());
	}

	public IndexPage setRegLocation(String in_regLocation) {
		fieldRegisterLocation().clear();
		fieldRegisterLocation().sendKeys(in_regLocation);
		blockHeader().click();
		testUnit.log.info("Enter registration location: " + in_regLocation);
		return this;
	}

	public boolean checkLocationValidation() {
		return !isElementPresent(blockRegLocationInvalid());
	}

	private IndexPage setRegAgeBetween(String p_regYear) {
		Integer int_year = Integer.parseInt(p_regYear);
		DateTime getDate = new DateTime();
		Integer yearsOld = Integer.parseInt(getDate.getYear()) - int_year;

		List<WebElement> optionsYear = selectRegisterAge().findElements(
				By.tagName("option"));

		for (WebElement option : optionsYear) {
			Integer optionValue = Integer.parseInt(option.getText().substring(
					0, 2));

			if (optionValue < yearsOld) {
				option.click();
			} else if (optionValue > yearsOld) {
				break;
			}

		}
		testUnit.log.info("Enter registration year: " + p_regYear);
		return this;
	}

	public IndexPage setRegAge(String in_regDay, String in_regMonth,
			String in_regYear) {
		// if(isElementPresent(selectRegAgeDay()) &&
		// isElementPresent(selectRegAgeMonth()))
		// {
		// setRegAgeDay(in_regDay).setRegAgeMonth(in_regMonth).setRegYear(in_regYear);
		// QA.log.info("Enter registration DOB: "+in_regDay+"-"+in_regMonth+"-"+in_regYear);
		// }
		// else
		// {
		setRegAgeBetween(in_regYear);
		testUnit.log.info("Enter registration age between: " + in_regYear);
		// }

		return this;
	}

	public boolean checkIsPostConfirmPage() {

		if (testUnit.driver.getCurrentUrl().contains("post_confirm.php")) {
			return true;
		} else {
			return false;
		}
	}

	public void checkWelcomePageResendMail() {
		// TODO Auto-generated method stub

	}

	public void checkWelcomePageChangeMail() {
		// TODO Auto-generated method stub

	}

	public void loginToGmail(String string, String string2) {
		openUrl("http://gmail.com");
		try {
			findElement(By.id("Email")).sendKeys(string);
			findElement(By.id("Passwd")).sendKeys(string2);
			findElement(By.id("signIn")).click();
		} catch (Exception e) {

		}
		openUrl("http://gmail.com");
	}

	public void openFirstIncomeLetter() {
		while (!isElementPresent(findElement(By
				.cssSelector("a[title*='Входящие']")))) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		findElement(By.cssSelector("a[title*='Входящие']")).click();
		while (!isElementPresent(findElement(By
				.cssSelector("div[role='main'] tr:nth-of-type(1) span[name='UpForIt']")))) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			findElement(By.cssSelector("a[title*='Входящие']")).click();
		}
		findElement(
				By.cssSelector("div[role='main'] tr:nth-of-type(1) span[name='UpForIt']"))
				.click();
		findElement(By.cssSelector("a[href*='autologin.php']")).click();
	}

	public void deleteGmailMessages() {
		findElement(By.cssSelector("div[data-tooltip='Выбрать']")).click();
		findElement(By.cssSelector("div[selector='all']")).click();

		while (isElementPresent(findElement(By
				.cssSelector("div[data-tooltip='Удалить']")))) {
			findElement(By.cssSelector("div[data-tooltip='Удалить']")).click();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			findElement(By.cssSelector("div[data-tooltip='Выбрать']")).click();
			findElement(By.cssSelector("div[selector='all']")).click();
		}

	}

	public IndexPage setScreennamePostConfirm(String p_screenname) {

		if (isElementPresent(fieldPostConfirmScreenname())) {
			fieldPostConfirmScreenname().clear();
			fieldPostConfirmScreenname().sendKeys(p_screenname);
			btnPostConfirmSubmit().click();
		}
		testUnit.log.info("Enter screenname: " + p_screenname);

		return this;

	}

	public boolean isScreennameErrorPostConfirm() {
		if (!markPostConfirmScreennameInvalid().isDisplayed()) {
			testUnit.log.info("Check is an element present: "
					+ markPostConfirmScreennameInvalid().toString());
			return true;
		} else {
			return false;
		}
	}

	public IndexPage setPostConfirmBirthday(String p_day, String p_month,
			String p_year) {
		if (isElementPresent(fieldPostConfirmDOBDay())
				&& isElementPresent(fieldPostConfirmDOBMonth())
				&& isElementPresent(fieldPostConfirmDOBYear())) {
			fieldPostConfirmDOBDay().clear();
			fieldPostConfirmDOBDay().sendKeys(p_day);
			fieldPostConfirmDOBMonth().clear();
			fieldPostConfirmDOBMonth().sendKeys(p_month);
			fieldPostConfirmDOBYear().clear();
			fieldPostConfirmDOBYear().sendKeys(p_year);
			btnPostConfirmSubmit().click();
		}
		return this;
	}

	public boolean isBirthdayErrorPostConfirm() {
		System.out.println(markPostConfirmBirthdayInvalid().isDisplayed());
		if (!markPostConfirmBirthdayInvalid().isDisplayed()) {
			testUnit.log.info("Check is an element present: "
					+ markPostConfirmBirthdayInvalid().toString());
			return true;
		} else {
			return false;
		}
	}

}
