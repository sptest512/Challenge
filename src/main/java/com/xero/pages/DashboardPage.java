package com.xero.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.xero.base.TestBase;
import com.xero.util.TestSuiteConstants;
import com.xero.util.Util;

public class DashboardPage extends TestBase{

	
	@FindBy(linkText = "Accounts")
	WebElement accounts;
	
	@FindBy(linkText = "Bank Accounts")
	WebElement bankAccounts;
		
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Validate Dashboard Page title
	public String validateTitle(){
		 return	driver.getTitle();
	}
	
	// Verify Dashboard Page Url
	public void validateDashPageUrl() {
		String url = driver.getCurrentUrl();
		Util.waitForUrl(url);
		Assert.assertEquals(url, TestSuiteConstants.dashboardUrl);
		
	}
	
	//Verify if Accounts Page is displayed
	public AccountsPage accountsPageDisplayed() {
		accounts.click();
		bankAccounts.click();
		return new AccountsPage();
	}
	
	
}
