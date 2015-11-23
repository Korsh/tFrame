package web.page_objects;

import org.openqa.selenium.WebElement;

import core.PageFrame;
import core.TestUnit;

public class BackendPagePO extends PageFrame {

	public BackendPagePO(TestUnit in_testUnit) {
		super(in_testUnit);
	}

	public WebElement fieldLogin() {
		return findElement("fieldLogin");
	}

	public WebElement textCopyright() {
		return findElement("textCopyright");
	}

	public WebElement fieldPassword() {
		return findElement("fieldPassword");
	}

	public WebElement btnLogin() {
		return findElement("btnLogin");
	}

	public WebElement fieldSearch() {
		return findElement("fieldSearch");
	}

	public WebElement btnSubmit() {
		return findElement("btnSubmit");
	}

	public WebElement linkAutologin() {
		return findElement("linkAutologin");
	}

}
