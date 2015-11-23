package core;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;

public class Sikuli {
	private TestUnit testUnit;

	/**
	 * Constructor
	 * 
	 * @param p_qa
	 */
	public Sikuli(TestUnit in_testUnit) {
		testUnit = in_testUnit;
	}

	public boolean click(String in_imageFileName, int in_timeOut) {
		boolean result = false;

		testUnit.log
				.info("Find an element pattern: "
						+ System.getProperty("user.dir")
						+ testUnit.getConfig("sikuli.locators.path")
						+ in_imageFileName);

		try {
			testUnit.screen.exists(
					System.getProperty("user.dir")
							+ testUnit.getConfig("sikuli.locators.path")
							+ in_imageFileName, in_timeOut);
			testUnit.screen.click(
					System.getProperty("user.dir")
							+ testUnit.getConfig("sikuli.locators.path")
							+ in_imageFileName, 0);

			result = true;
		} catch (FindFailed ex) {
			result = false;
			testUnit.log.warn("Error uccured: ", ex);
			ex.printStackTrace();
		}

		return result;
	}

	public boolean type(String in_imageFileName, String in_text, int in_timeOut) {
		boolean result = false;

		testUnit.log
				.info("Find an element pattern: "
						+ System.getProperty("user.dir")
						+ testUnit.getConfig("sikuli.locators.path")
						+ in_imageFileName);

		try {
			testUnit.screen.exists(
					System.getProperty("user.dir")
							+ testUnit.getConfig("sikuli.locators.path")
							+ in_imageFileName, in_timeOut);
			testUnit.screen.wait(
					System.getProperty("user.dir")
							+ testUnit.getConfig("sikuli.locators.path")
							+ in_imageFileName, 0);
			testUnit.screen.type(in_text);
			testUnit.screen.keyUp(Key.ENTER);

			result = true;
		} catch (FindFailed ex) {
			result = false;
			ex.printStackTrace();
		}

		return result;
	}

	public boolean pressEnter(String in_imageFileName, int in_timeOut) {
		boolean result = false;

		testUnit.log
				.info("Find an element pattern: "
						+ System.getProperty("user.dir")
						+ testUnit.getConfig("sikuli.locators.path")
						+ in_imageFileName);

		try {
			testUnit.screen.exists(
					System.getProperty("user.dir")
							+ testUnit.getConfig("sikuli.locators.path")
							+ in_imageFileName, in_timeOut);
			testUnit.screen.type(Key.ENTER);

			result = true;
		} catch (FindFailed ex) {
			result = false;
			ex.printStackTrace();
		}

		return result;
	}

	public boolean screenPageDownUntillVisible(String region,
			int pageDownCountOut) {
		boolean result = false;
		try {
			for (int second = 0;; second++) {
				if (second > pageDownCountOut) {
					result = false;
					break;
				}
				try {
					if (testUnit.screen.exists(region) != null) {
						result = true;
						break;
					}
				} catch (Exception ex) {
				}

				testUnit.screen.type(null, '\ue005' + "", 0);
				Thread.sleep(1000);
			}
		} catch (Exception ex) {
			result = false;
		}

		return result;
	}

}
