package com.xero.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.xero.base.TestBase;
import com.xero.util.TestSuiteConstants;
import com.xero.util.Util;

public class AccountsPage extends TestBase {

	public AccountsPage() {
		PageFactory.initElements(driver, this);
	}

	// Click on Add Bank Account
	public void addBankAccount() {
		driver.findElement(By.xpath("//*[@data-automationid='Add Bank Account-button']")).click();
		;
	}

	public void verifyAddBankAccTitle() {
		WebElement addBankAccTitle = driver.findElement(By.xpath("//*[@id='view']//h1"));
		String text = TestSuiteConstants.addBankAccTitle;
		Util.waitForElementText(addBankAccTitle, text);
		Assert.assertEquals(addBankAccTitle.getText(), "Add Bank Accounts");
	}
	
	// Enter 'ANZ' in Search For Your Bank textbox
	public void searchForYourBank(String bankName) {
		driver.findElement(By.xpath("//input[@type='search'][@role='textbox'][@placeholder='Search for your bank...']"))
				.sendKeys(bankName);
		
	}

	// Verify if ANZ (NZ) is displayed and click on it
	public void verifyAndclickDropdown() {

		WebElement searchResult = driver.findElement(By.xpath("//div[@data-automationid='searchBanksList']/ul/*[1]"));
		String ss = TestSuiteConstants.searchResult;

		if (searchResult.getText().equals(ss)) {
			searchResult.click();
		} else {
			System.out.println("No records found for ANZ (NZ) bank");
		}
	}

	public void verifyAccountTitle() {
		WebElement enterAccountTitle = driver.findElement(By.xpath("//h1[@class='ba-page-title']"));
		String text = TestSuiteConstants.enterAccountTitle;
		Util.waitForElementText(enterAccountTitle, text);
		Assert.assertEquals(enterAccountTitle.getText(), "Enter your ANZ (NZ) account details");
	}
	// Enter Account Name
	public void getAccName(String accName) {
		driver.findElement(By.xpath("//div[@data-automationid='accountName']//div[@data-ref='inputWrap']/input"))
				.sendKeys(accName);
	}

	// Click on Account Type
	public void findAccTypeField() {
		driver.findElement(By.xpath("//div[@data-automationid='accountType']//div[@data-ref='bodyEl']")).click();

	}

	// Choose Account Type from dropdown
	public void getAccTypeByParam(String accType) {
		driver.findElement(
				By.xpath("//div[@data-ref='listWrap']/ul[@data-ref='listEl']/li[text() = '" + accType + "']")).click();

	}

	// Enter Account Number
	public void getAccNumber(String accNumber) {

		try {

			List<WebElement> accountNumberElems = driver.findElements(
					By.xpath("//div[@data-automationid='accountNumber']//div[@data-ref='inputWrap']/input"));

			Iterator<WebElement> accountNumberItr = accountNumberElems.iterator();
			while (accountNumberItr.hasNext()) {
				WebElement accountNumberElem = accountNumberItr.next();
				if (accountNumberElem.isDisplayed()) {
					accountNumberElem.sendKeys(accNumber);
				}
			}
		} catch (Exception e) {
			System.out.println("error in elements fetch");
		}
	}

	// Enter Credit Card Details when Account Type is "Credit Card"
	public void getCreditCardDetails(String creditCard) {
		driver.findElement(By.xpath("//div[@data-automationid='creditCardNumber']//div[@data-ref='inputWrap']/input"))
				.sendKeys(creditCard);
	}

	// Click on Continue
	public void ContinueBtn() {
		driver.findElement(By.linkText("Continue")).click();
	}

	// Click on Back
	public void BackBtn() {
		driver.findElement(By.linkText("Back")).click();
		;
	}

	// Click on Add Another Account
	public void AddAnotherAccount() {
		driver.findElement(By.partialLinkText("Add another ANZ (NZ) account")).click();
	}

	// Verify Account Name error message when no value is entered
	public String verifyAcctNameErrMsg() {
		return driver.findElement(By.xpath("//*[@id='accountname-1037-errorEl']/ul/li")).getText();
	}

	// Verify Account Type error message when no value is entered
	public String verifyAcctTypeErrMsg() {
		return driver.findElement(By.xpath("//*[@id='accounttype-1039-errorEl']/ul/li")).getText();
	}

	// Verify Account Number error message when no value is entered
	public String verifyAcctNumberErrMsg() {
		return driver.findElement(By.xpath("//*[@id='accountnumber-1068-errorEl']/ul/li")).getText();
	}

	// Verify Credit Card error message when no value is entered
	public String verifyCreditCardErrMsg() {
		return driver.findElement(By.xpath("//*[@id='accountnumber-1063-errorEl']/ul/li")).getText();
	}

	/*
	 * public String verifyUniqueAccNameErrMsg() { return
	 * driver.findElement(By.xpath("//*[@id='accountname-1037-errorEl']/ul/li")).
	 * getText(); }
	 */

	// Verify Success Display message after adding bank account
	public String getDisplayMsg() {
		String msg = driver.findElement(By.xpath("//div[@class='message']/p")).getText();
		if (msg.contains("added.")) {
			System.out.println("Bank Account added successfully");
		}
		return msg;
	}

}