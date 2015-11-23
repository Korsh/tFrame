package core;

import java.util.Map;

import models.LocatorModel;

import org.openqa.selenium.By;

public class LocatorFactory {

	public TestUnit testUnit;
	static ISiteLocators site;
	IPage page;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public LocatorFactory(TestUnit in_testUnit) {
		testUnit = in_testUnit;
	}

	/**
	 * Get element locator By
	 * 
	 * @param p_site
	 * @param p_page
	 * @param p_element
	 * @return
	 */
	public By getLocator(String in_site, String in_page, String in_element) {
		Map<String, LocatorModel<String, String>> locator = null;

		locator = getSite(in_site).getPage(in_page).getLocators();

		String locatorValue = locator.get(in_element).getValue();
		String locatorType = locator.get(in_element).getType();
		By loc = getLocatorValue(locatorType, locatorValue);

		return loc;
	}

	/**
	 * Get locator value
	 * 
	 * @param p_site
	 * @param p_page
	 * @param p_element
	 * @param p_locator
	 * @return
	 */
	public String getLocator(String in_site, String in_page, String in_element,
			String in_locator) {
		Map<String, LocatorModel<String, String>> locator;

		locator = getSite(in_site).getPage(in_page).getLocators();
		String locatorValue = locator.get(in_element).getValue();

		return locatorValue;
	}

	/**
	 * Get locator value
	 */
	public By getLocatorValue(String p_locatorType, String p_locatorValue) {
		By locator = null;

		if (p_locatorType == "id") {
			locator = By.id(p_locatorValue);

			return locator;
		} else if (p_locatorType == "name") {
			locator = By.name(p_locatorValue);

			return locator;
		} else if (p_locatorType == "class") {
			locator = By.className(p_locatorValue);

			return locator;
		} else if (p_locatorType == "css") {
			locator = By.cssSelector(p_locatorValue);

			return locator;
		} else if (p_locatorType == "xpath") {
			locator = By.xpath(p_locatorValue);

			return locator;
		} else if (p_locatorType == "link_text") {
			locator = By.linkText(p_locatorValue);

			return locator;
		} else {
			return null;
		}
	}

	/**
	 * Get site object
	 * 
	 * @param p_site
	 * @return
	 */

	public ISiteLocators getSite(String in_site) {
		ISiteLocators site = null;

		if (in_site.contains("upforit")) {
			site = new locators.web.UpforitLocators(testUnit);

			return site;
		} else if (in_site.contains("iwantu")) {
			site = new locators.web.IwantuLocators(testUnit);
			return site;
		} else if (in_site.contains("sololigar")) {
			site = new locators.web.SololigarLocators(testUnit);

			return site;
		} else if (in_site.contains("sexlugar")) {
			site = new locators.web.SexlugarLocators(testUnit);

			return site;
		} else if (in_site.contains("backend")) {
			site = new locators.web.BackendLocators(testUnit);

			return site;
		} else {
			return site;
		}
	}
}
