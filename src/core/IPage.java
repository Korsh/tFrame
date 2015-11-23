package core;

import java.util.Map;

import models.LocatorModel;

public interface IPage {
	/**
	 * Get locators
	 */
	public Map<String, LocatorModel<String, String>> getLocators();

	/**
	 * Set a page URL
	 */
	public void setPage();
}
