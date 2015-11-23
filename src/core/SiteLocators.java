package core;

import java.util.HashMap;
import java.util.Map;

import models.LocatorModel;

public class SiteLocators {
	public TestUnit testUnit;
	/* Locators array */
	public Map<String, LocatorModel<String, String>> locators = new HashMap<String, LocatorModel<String, String>>();
	/* Locator */
	LocatorModel<String, String> locator;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public SiteLocators(TestUnit in_testUnit) {
		testUnit = in_testUnit;
	}

	/**
	 * Set locator
	 * 
	 * @param p_elementName
	 * @param p_locatorType
	 * @param p_locatorValue
	 */
	public void setLocator(String in_elementName, String in_locatorType,
			String in_locatorValue) {
		if (in_locatorType.equals("sikuli")) {
			testUnit.log.debug(testUnit.siteName + "." + testUnit.siteLocation
					+ "." + in_elementName + ".png");
			locator = new LocatorModel<String, String>(in_locatorType,
					testUnit.siteName + "." + testUnit.siteLocation + "."
							+ in_elementName + ".png");

			locators.put(in_elementName, locator);
		} else {
			locator = new LocatorModel<String, String>(in_locatorType,
					in_locatorValue);

			locators.put(in_elementName, locator);
		}

	}
}
