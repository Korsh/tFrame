package core;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

public class DriverUtils {

	public static File getScreenShot(WebDriver driver) {
	    WebDriver d;
	    try {
		    if (driver.getClass().getName().equals("org.openqa.selenium.remote.RemoteWebDriver")) {
		      d = new Augmenter().augment(driver);
		    } else {
		      d = driver;
		    }
		    return ((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
		    
	    } catch (Exception e) {
	    	
	    }
	    return null;
	}

}