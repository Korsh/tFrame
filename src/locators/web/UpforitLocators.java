package locators.web;

import interfaces.web.IIndexPage;

import java.util.Map;


import models.LocatorModel;
import core.IPage;
import core.ISiteLocators;
import core.SiteLocators;
import core.TestUnit;

public class UpforitLocators extends SiteLocators implements ISiteLocators {
	public TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public UpforitLocators(TestUnit in_testUnit) {
		super(in_testUnit);
		testUnit = in_testUnit;
	}

	class IndexPage implements IIndexPage {
		@Override
		public Map<String, LocatorModel<String, String>> getLocators() {
			setPage();
			setRegisterForm();
			setWelcomePage();
			setPsotConfirmPage();

			return locators;
		}

		@Override
		public void setPage() {
			setLocator("page", "url", "/");
		}

		@Override
		public void setRegisterForm() {
			setLocator("fieldRegisterEmail", "css", "#email");
			setLocator("fieldRegisterLocation", "css", "#location");
			setLocator("fieldRegisterPassword", "css", "#password");
			setLocator("selectRegisterAge", "css", "#frmYear");
			// setLocator("regSubmit", "sikuli", null);
			setLocator("btnRegSubmit", "css",
					".submit_block>input[type='submit']");

			setLocator("blockRegEmailInvalid", "css",
					".error_txt[htmlfor=email]");
			setLocator("blockRegPasswordInvalid", "css",
					".error_txt[htmlfor=password]");
			setLocator("blockRegLocationInvalid", "css",
					".error_txt[htmlfor=location]");
			setLocator("blockHeader", "css", "#header_div");

		}

		public void setWelcomePage() {
			setLocator("blockUserEmail", "css", ".user_email");
			setLocator("fieldChangeEmail", "css", "input[id='email']");
			setLocator("blockChangeEmail", "css", "#change_email");
		}

		public void setPsotConfirmPage() {
			setLocator("fieldPostConfirmScreenname", "css", "#screenname");
			setLocator("titlePostConfirm", "css", ".right_box>h2");
			setLocator("markPostConfirmScreennameInvalid", "css",
					"#error_mes_screenname");
			setLocator("markPostConfirmBirthdayInvalid", "css",
					"#error_mes_birthday");
			setLocator("fieldPostConfirmDOBDay", "css", "#frmDay");
			setLocator("fieldPostConfirmDOBMonth", "css", "#frmMonth");
			setLocator("fieldPostConfirmDOBYear", "css", "#frmYear");
			setLocator("btnPostConfirmSubmit", "css", "#submit_button");
			setLocator("lnkFunnStep1ScreennameVariant", "css",
					".screenname_variant");

		}

	}

	/**
	 * Get the page
	 */
	@Override
	public IPage getPage(String p_page) {
		IPage page;

		if (p_page.equals("IndexPagePO") || p_page.equals("IndexPage")) {
			page = this.new IndexPage();

			return page;
		} else {
			return null;
		}
	}
}