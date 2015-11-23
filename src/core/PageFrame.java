package core;

import java.io.File;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

public class PageFrame {

	private TestUnit testUnit;
	public String testPage;
	private LocatorFactory locatorFactory;
	private WebElement webElement;

	/**
	 * Constructor
	 * 
	 * @param WebDriver
	 *            in_driver
	 */
	public PageFrame(TestUnit in_testUnit) {
		testUnit = in_testUnit;
		String callerPath = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		testUnit.testPage = getCallerName(callerPath);
		locatorFactory = new LocatorFactory(testUnit);
	}

	/**
	 * Type text to field
	 * 
	 * @param in_webElement
	 * @param in_text
	 * @return void
	 */
	protected void type(WebElement in_webElement, String in_text) {
		in_webElement.clear();
		in_webElement.sendKeys(in_text);
	}

	/**
	 * Open page
	 * 
	 * @return null
	 */
	public PageFrame open() {
		return null;
	}

	/**
	 * Check is element present
	 * 
	 * @param in_by
	 * @return boolean
	 */
	protected boolean isElementPresent(By in_by) {
		try {
			testUnit.driver.findElement(in_by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Check element for presence
	 * 
	 * @param p_element
	 * @return boolean
	 */
	public boolean isElementPresent(WebElement in_element) {
		testUnit.log.info("Check for is an element present");

		if (in_element != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Get page
	 * 
	 * @return String out_page
	 */
	public String getPage() {
		// System.out.println("page: "+qa.siteName+";"+qa.testPage);

		String out_page = "";// locatorFactory.getPageURL(qa.siteName,
								// qa.testPage);
		return out_page;
	}

	/**
	 * Open page by URL
	 * 
	 * @param in_pageUrl
	 * @return void
	 */
	public void openPage(String in_pageUrl) {
		testUnit.log
				.info("Load page: " + testUnit.testPage + ": " + in_pageUrl);
		testUnit.driver.get(testUnit.siteUrl + in_pageUrl);

		try {
			File screenshot = ((TakesScreenshot) testUnit.driver)
					.getScreenshotAs(OutputType.FILE);
			String path = "./target/screenshots/" + screenshot.getName();
			FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {
			testUnit.log.warn(
					"Warning: somesing wromg while taking screenshot: ", e);
		}
		// getJSErrors();
	}

	/**
	 * Open page by URL
	 * 
	 * @param in_pageUrl
	 * @return void
	 */
	public void openUrl(String in_pageUrl) {
		testUnit.log.info("Load page: " + in_pageUrl);
		testUnit.driver.get(in_pageUrl);

		try {
			File screenshot = ((TakesScreenshot) testUnit.driver)
					.getScreenshotAs(OutputType.FILE);
			String path = "./target/screenshots/" + screenshot.getName();
			FileUtils.copyFile(screenshot, new File(path));
		} catch (Exception e) {
			testUnit.log.warn(
					"Warning: somesing wromg while taking screenshot: ", e);
		}
		// getJSErrors();
	}

	/**
	 * Get caller name
	 * 
	 * @param in_callerPath
	 * @return String out_callerName
	 */
	public String getCallerName(String in_callerPath) {
		String[] callerPathSplit = in_callerPath.split(Pattern.quote("."));
		String out_callerName = callerPathSplit[callerPathSplit.length - 1];
		return out_callerName;
	}

	/**
	 * Get Element Locator
	 * 
	 * @param p_elementName
	 * @param p_elementType
	 * @return String
	 */
	public String findElement(String p_elementName, String p_elementType) {
		String locatorValue = locatorFactory.getLocator(testUnit.siteName,
				testUnit.testPage, p_elementName, p_elementType);

		return locatorValue;
	}

	/**
	 * Find Element
	 * 
	 * @param p_elementLocator
	 * @return WebElement
	 */
	public WebElement findElement(String p_locator) {
		String callerPath = Thread.currentThread().getStackTrace()[2]
				.getClassName();
		testUnit.testPage = this.getCallerName(callerPath);
		return findElement(getLocator(p_locator));
	}

	/**
	 * Find element
	 * 
	 * @param element
	 * @return
	 */
	public WebElement findElement(By p_locatorBy) {
		if (p_locatorBy != null) {
			try {
				if (testUnit.testPage.contains("BackendPagePO")
						|| testUnit.testPage.contains("BackendPage")) {
					webElement = testUnit.backendDriver
							.findElement(p_locatorBy);
				} else {
					webElement = testUnit.driver.findElement(p_locatorBy);
				}
				// WebElement webElement =
				// testUnit.wait.until(ExpectedConditions.elementToBeClickable(p_locatorBy));

				if (webElement != null) {
					// testUnit.webDriverFeatures.flashElement(webElement);

					return webElement;
				} else {
					testUnit.log.warn("Web Element not initialized: "
							+ p_locatorBy);
					return null;
				}
			} catch (Exception ex) {
				testUnit.log.warn("Web Element not found: " + ex);
				return null;
			}

		} else {
			testUnit.log.warn("Locator not found: " + p_locatorBy);
			return null;
		}
	}

	/**
	 * Get Element Locator
	 * 
	 * @param p_elementName
	 * @return By
	 */
	public By getLocator(String p_elementName) {
		By locator = null;

		try {
			if (testUnit.testPage.contains("BackendPagePO")
					|| testUnit.testPage.contains("BackendPage")) {
				locator = locatorFactory.getLocator("backend",
						testUnit.testPage, p_elementName);
			} else {
				locator = locatorFactory.getLocator(testUnit.siteName,
						testUnit.testPage, p_elementName);
			}
			return locator;
		} catch (Exception ex) {
			testUnit.log
					.warn("Locator not found: " + p_elementName + ": " + ex);
			return null;
		}

	}
}
