package locators.web;

import interfaces.web.IBackendPage;

import java.util.Map;


import models.LocatorModel;
import core.IPage;
import core.ISiteLocators;
import core.SiteLocators;
import core.TestUnit;

public class BackendLocators extends SiteLocators implements ISiteLocators {
	public TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public BackendLocators(TestUnit in_testUnit) {
		super(in_testUnit);
		testUnit = in_testUnit;
	}

	class BackendPage implements IBackendPage {
		@Override
		public Map<String, LocatorModel<String, String>> getLocators() {
			setPage();
			setLoginForm();
			setFindForm();
			return locators;
		}

		@Override
		public void setPage() {
			setLocator("page", "url", "/");
		}

		@Override
		public void setLoginForm() {
			setLocator("fieldLogin", "css", "input[name=guid]");
			setLocator("fieldPassword", "css", "input[name=pwd]");
			setLocator("btnLogin", "name", "login");
		}

		@Override
		public void setFindForm() {
			setLocator("fieldSearch", "css", "#user");
			setLocator("btnSubmit", "css", "#search_fields>form>input");
			setLocator("linkAutologin", "css", ".autologin");
			setLocator("linkOpenInSearch", "xpath",
					".//*[@class='site_name']/a[2]");
		}

	}

	/**
	 * Get the page
	 */
	@Override
	public IPage getPage(String p_page) {
		IPage page;

		if (p_page.equals("BackendPagePO") || p_page.equals("BackendPage")) {

			page = this.new BackendPage();

			return page;
		} else {
			return null;
		}
	}
}