package com.xero.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.xero.base.TestBase;
import com.xero.util.TestSuiteConstants;
import com.xero.util.Util;

public class LoginPage extends TestBase {

	@FindBy(id = "email")
	WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(id = "submitButton")
	WebElement loginSubmitBtn;

	@FindBy(xpath = "//a[@href='http://www.xero.com']")
	WebElement imgLogo;

	@FindBy(xpath = "//div[@class='x-boxed warning']")
	WebElement errMsg;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Validate Login Page title
	public String validateLoginTitle() {
		return driver.getTitle();
	}

	// Verify Xero Logo on Login Page
	public boolean verifyImage() {
		return imgLogo.isDisplayed();
	}

	// Verify Login Page url
	public void validateLoginPageUrl() {
		String url = driver.getCurrentUrl();
		Util.waitForUrl(url);
		Assert.assertEquals(url, TestSuiteConstants.loginPageUrl);
		
	}
	// Verify Error Msg when invalid credentials entered
	public String getErrorMsg() {
		return errMsg.getText();
	}

	// Enter valid credentials to login
	public DashboardPage login(String emailId, String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		loginSubmitBtn.click();
		return new DashboardPage();
	}

	// Enter invalid credentials to login
	public void invalidLogin(String emailId, String pwd) {
		email.sendKeys(emailId);
		password.sendKeys(pwd);
		loginSubmitBtn.click();

	}
}
