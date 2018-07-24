package com.xero.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xero.base.TestBase;
import com.xero.pages.HomePage;
import com.xero.pages.LoginPage;
import com.xero.util.TestSuiteConstants;
import com.xero.util.Util;
	
public class HomePageTest  extends TestBase{
	HomePage homepage;
	LoginPage loginpage;
	public HomePageTest() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		homepage = new HomePage();
		
	}

	@Test(priority = 1, description="Verify Home Page title")
	public void verifyHomeTitle(){
		String title = homepage.validateHomeTitle();
		Assert.assertEquals(title, "Accounting Software & Online Bookkeeping | Xero NZ");
	}
	
	/*@Test(priority = 2, description="Verify Home Page Url")
	public void verifyHomeUrl() {
		String url = driver.getCurrentUrl();
		Util.waitForUrl(url);
		Assert.assertEquals(url, TestSuiteConstants.homePageUrl);
	}*/
	
	@Test(priority = 3, description="Click on Login Button on Home Page")
	public void loginTest() {
		loginpage = homepage.login();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}	
}
