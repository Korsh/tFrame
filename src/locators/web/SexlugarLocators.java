package locators.web;

import interfaces.web.IIndexPage;

import java.util.Map;


import models.LocatorModel;
import core.IPage;
import core.ISiteLocators;
import core.SiteLocators;
import core.TestUnit;

public class SexlugarLocators extends SiteLocators implements ISiteLocators {
	public TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public SexlugarLocators(TestUnit in_testUnit) {
		super(in_testUnit);
		testUnit = in_testUnit;
	}

	class IndexPage implements IIndexPage {
		@Override
		public Map<String, LocatorModel<String, String>> getLocators() {
			setPage();
			setRegisterForm();

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
			setLocator("regSubmit", "sikuli", null);
			setLocator("btn2Submit", "css", ".submit_block>input[type=submit]");
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