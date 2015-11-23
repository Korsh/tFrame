package web.page_objects;

import org.openqa.selenium.WebElement;

import core.PageFrame;
import core.TestUnit;

public class IndexPagePO extends PageFrame {

	public IndexPagePO(TestUnit in_testUnit) {
		super(in_testUnit);
	}

	public WebElement fieldRegisterEmail() {
		return findElement("fieldRegisterEmail");
	}

	public WebElement fieldRegisterLocation() {
		return findElement("fieldRegisterLocation");
	}

	public WebElement fieldRegisterPassword() {
		return findElement("fieldRegisterPassword");
	}

	public WebElement btnRegSubmit() {
		return findElement("btnRegSubmit");
	}

	/*
	 * public String regSubmit() { return findElement("regSubmit", "sikuli"); }
	 */

	public WebElement blockRegEmailInvalid() {
		return findElement("blockRegEmailInvalid");
	}

	public WebElement blockRegPasswordInvalid() {
		return findElement("blockRegPasswordInvalid");
	}

	public WebElement blockRegLocationInvalid() {
		return findElement("blockRegLocationInvalid");
	}

	public WebElement blockHeader() {
		return findElement("blockHeader");
	}

	public WebElement selectRegisterAge() {
		return findElement("selectRegisterAge");
	}

	public WebElement fieldPostConfirmScreenname() {
		return findElement("fieldPostConfirmScreenname");
	}

	public WebElement titlePostConfirm() {
		return findElement("titlePostConfirm");
	}

	public WebElement markPostConfirmScreennameInvalid() {
		return findElement("markPostConfirmScreennameInvalid");
	}

	public WebElement fieldPostConfirmDOBDay() {
		return findElement("fieldPostConfirmDOBDay");
	}

	public WebElement fieldPostConfirmDOBMonth() {
		return findElement("fieldPostConfirmDOBMonth");
	}

	public WebElement fieldPostConfirmDOBYear() {
		return findElement("fieldPostConfirmDOBYear");
	}

	public WebElement btnPostConfirmSubmit() {
		return findElement("btnPostConfirmSubmit");
	}

	public WebElement markPostConfirmBirthdayInvalid() {
		return findElement("markPostConfirmBirthdayInvalid");
	}

	public WebElement lnkFunnStep1ScreennameVariant() {
		return findElement("lnkFunnStep1ScreennameVariant");
	}

}
