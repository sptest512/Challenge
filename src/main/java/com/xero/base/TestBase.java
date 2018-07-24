package com.xero.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.xero.util.WebEventListener;

public class TestBase {

	public static Properties prop;
	public static WebDriver driver;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		try {
			//load properties file 
			prop = new Properties();
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+
					"/config/config.properties");
			prop.load(fi);
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void initialization() throws Exception {
		
		validateConfig();
		
		//get OS version name from properties file
		String osName = prop.getProperty("osVersion");
		//get browser name from properties file
		String browserName = prop.getProperty("browser");
				
		//Check for OS Version and browser name and instantiate the web driver accordingly
		if(osName.equalsIgnoreCase("Linux")) {
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver//Linux/chromedriver");
				driver = new ChromeDriver(); 
			}
			/*else if(browserName.equals("FF")){
				System.setProperty("webdriver.gecko.driver", "media/karan/New Volume/Swetha/WorkSpace/test/");	
				driver = new FirefoxDriver(); 
				}
				*/
			
		}
		else if (osName.equalsIgnoreCase("Windows")){
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//chromedriver//Windows/chromedriver.exe");
				driver = new ChromeDriver(); 
			}
		}
		
		e_driver = new EventFiringWebDriver(driver);
		//Create an object of EventListerHandler and register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		//maximize browser
		driver.manage().window().maximize();
		//delete all cookies
		driver.manage().deleteAllCookies();
		//implicit wait for 20 seconds
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//load URL from properties file
		driver.get(prop.getProperty("url"));
		
	}


	private static void validateConfig() throws Exception {

		if(prop.getProperty("osVersion")==null || prop.getProperty("osVersion").length()==0 
				|| !(prop.getProperty("osVersion").equalsIgnoreCase("Linux")
						|| prop.getProperty("osVersion").equalsIgnoreCase("Windows"))) {
			throw new Exception("Invalid configuration for osVersion property, valid options are Linux or Windows");
		}
		
		if(prop.getProperty("email")==null || prop.getProperty("email").length()==0 
				|| prop.getProperty("email").equalsIgnoreCase("<replace_this_with_your_username>")) {
			throw new Exception("Invalid configuration for email property, please replace placeholder with actual email");
		}
		
		if(prop.getProperty("password")==null || prop.getProperty("password").length()==0 
				|| prop.getProperty("password").equalsIgnoreCase("<replace_this_with_your_password>")) {
			throw new Exception("Invalid configuration for password property, please replace placeholder with actual password");
		}
		
	}
}

