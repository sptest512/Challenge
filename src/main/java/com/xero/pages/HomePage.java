package com.xero.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.xero.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(linkText = "Login")
	WebElement loginBtn;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	//Validate Home Page Title
	public String validateHomeTitle() {
		return driver.getTitle();
	}
	
	// Verify Home Page Url
	/*public String validateHomePageUrl() {
		return driver.getCurrentUrl();
	}*/
	
	//Click on Login Button
	public LoginPage login() {
		loginBtn.click();
		return new LoginPage();
	}
}
