package com.xero.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xero.base.TestBase;
import com.xero.pages.DashboardPage;
import com.xero.pages.HomePage;
import com.xero.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	DashboardPage dashboardPage;

	public LoginPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		homepage = new HomePage();
		loginpage = homepage.login();

	}

	@Test(priority = 1, description = "Verify Login Page title")
	public void verifyLoginTitle() {
		String title = loginpage.validateLoginTitle();
		Assert.assertEquals(title, "Login | Xero Accounting Software");
	}

	@Test(priority = 2, enabled = true, description = "Verify Xero Logo Image on Login Page")
	public void logoCheck() {
		boolean imgPresent = loginpage.verifyImage();
		Assert.assertTrue(imgPresent);
	}

	@Test(priority = 3, description="Verify Login Page Url")
	public void verifyLoginUrl() {
		loginpage.validateLoginPageUrl();
	}
	
	//enable for valid login
	@Test(priority = 4, enabled = true, description = "Enter valid credentials and click on Login, Accounts Page should be dispalyed")
	public void loginTest() {
		dashboardPage = loginpage.login(prop.getProperty("email"), prop.getProperty("password"));
	}

	//enable when you enter invalid credentials for few attempts to check lock error message
	@Test(priority = 5, enabled = false, description = "Account locked error message thrown when you enter invalid credentials for few attempts")
	public void verifyLockErrMsg() {
		loginpage.invalidLogin(prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertEquals(loginpage.getErrorMsg(),
				"Your account has been temporarily locked. Please try again in 15 minutes or reset your password.");

	}
	
	//enable when you enter invalid credentials
	@Test(priority = 6, enabled = false, description = "Error Message thrown when you enter invalid credentials")
	public void verifyErrorMsgInvalidPwd() {
		loginpage.invalidLogin(prop.getProperty("email"), prop.getProperty("password"));
		Assert.assertEquals(loginpage.getErrorMsg(), "Your email or password is incorrect");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}	
}
