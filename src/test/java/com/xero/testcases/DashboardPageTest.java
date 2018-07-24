package com.xero.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.xero.base.TestBase;
import com.xero.pages.AccountsPage;
import com.xero.pages.DashboardPage;
import com.xero.pages.HomePage;
import com.xero.pages.LoginPage;

public class DashboardPageTest extends TestBase {
	AccountsPage accountspage;
	LoginPage loginpage;
	HomePage homepage;
	DashboardPage dashboardPage;

	public DashboardPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		homepage = new HomePage();
		loginpage = homepage.login();
		dashboardPage = loginpage.login(prop.getProperty("email"), prop.getProperty("password"));

	}

	@Test(priority = 2, description = "Verify the dashboard page title", enabled= false)
	public void verifyTitle() {
		String title = dashboardPage.validateTitle();
		Assert.assertEquals(title, "Xero | Bank Accounts | demo");
	}

	@Test(priority = 1, description = "Verify Dashboard Page Url")
	public void verifyDashBoardPageUrl() {
		dashboardPage.validateDashPageUrl();
	}

	@Test(priority = 3, description = "Verify that the accounts page is displayed after you click on Accounts > Bank accounts")
	public void isAccountsPage() {
		accountspage = dashboardPage.accountsPageDisplayed();
	}
	// Close all the browsers
	@AfterMethod
	public void tearDown() {
		System.out.println("closing browser in dash");
		driver.quit();
	}
}
