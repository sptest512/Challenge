package com.xero.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.xero.base.TestBase;
import com.xero.pages.AccountsPage;
import com.xero.pages.DashboardPage;
import com.xero.pages.HomePage;
import com.xero.pages.LoginPage;

public class AccountsPageTest extends TestBase {
	AccountsPage accountspage;
	LoginPage loginpage;
	HomePage homepage;
	DashboardPage dashboardPage;

	public AccountsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() throws Exception {
		initialization();
		homepage = new HomePage();
		loginpage = homepage.login();
		dashboardPage = loginpage.login(prop.getProperty("email"), prop.getProperty("password"));
		accountspage = dashboardPage.accountsPageDisplayed();

	}

	@DataProvider(name = "BankDetails")
	public static Object[][] getAllDetails() {
		String uniqueId = System.currentTimeMillis()+"";
		return new Object[][] { { "ANZ", "Personal Account"+uniqueId, "Other", "1234" } };

	}
	//enable this test case test this scenario for valid details with account type selected other than credit card

	@Test(priority = 1, enabled = true, dataProvider = "BankDetails", description = "Enter all the bank details and choose acc type other than credit card,and verify that new bank account should be added successfully")

	public void enterBankDetails(String stxt, String aname, String atype, String anum) {
		accountspage.addBankAccount();
		accountspage.searchForYourBank(stxt);
		accountspage.verifyAddBankAccTitle();
		accountspage.verifyAndclickDropdown();
		accountspage.verifyAccountTitle();
		accountspage.getAccName(aname);
		accountspage.findAccTypeField();
		accountspage.getAccTypeByParam(atype);
		accountspage.getAccNumber(anum);
		accountspage.ContinueBtn();
		accountspage.getDisplayMsg();

	}

	@DataProvider(name = "BankDetailsCC")
	public static Object[][] getAllDetailsCC() {
		String uniqueId = System.currentTimeMillis()+"";
		return new Object[][] { { "ANZ", "Business Account"+uniqueId, "Credit Card", "5454" } };

	}
	
	//enable this test case to test this scenario for valid details with account type selected as credit card
	@Test(priority = 2, enabled = true, dataProvider = "BankDetailsCC", description = "Enter all bank details and choose account type as credit card and click on continue, bank account should be added successfully")
	public void enterBankDetailsWithCC(String stxt, String aname, String cc, String anum) {
		accountspage.addBankAccount();
		accountspage.verifyAddBankAccTitle();
		accountspage.searchForYourBank(stxt);
		accountspage.verifyAndclickDropdown();
		accountspage.verifyAccountTitle();
		accountspage.getAccName(aname);
		accountspage.findAccTypeField();
		accountspage.getAccTypeByParam(cc);
		accountspage.getCreditCardDetails(anum);
		accountspage.ContinueBtn();
		accountspage.getDisplayMsg();
	}
		
	
	@Test(priority = 3, enabled = true, description = "Do not enter Account Name  and click on Continue and verify that the error message is thrown")
	public void verifyAccnameErrorMsg() {
		accountspage.addBankAccount();
		accountspage.searchForYourBank("ANZ");
		accountspage.verifyAndclickDropdown();
		accountspage.ContinueBtn();
		Assert.assertEquals(accountspage.verifyAcctNameErrMsg(), "Account Name required");
	}

	@Test(priority = 4, enabled = true, description = "Do not enter Account Type  and click on Continue and verify that the error message is thrown")
	public void verifyAccTypeErrorMsg() {
		accountspage.addBankAccount();
		accountspage.searchForYourBank("ANZ");
		accountspage.verifyAndclickDropdown();
		accountspage.verifyAccountTitle();
		accountspage.getAccName("Sample");
		accountspage.ContinueBtn();
		Assert.assertEquals(accountspage.verifyAcctTypeErrMsg(), "Account Type required");
	}

	@Test(priority = 5, enabled = true, description = "Do not enter Account Number  and click on Continue and verify that the error message is thrown")
	public void verifyAccNumberErrorMsg() {
		accountspage.addBankAccount();
		accountspage.searchForYourBank("ANZ");
		accountspage.verifyAndclickDropdown();
		accountspage.verifyAccountTitle();
		accountspage.getAccName("Sample");
		accountspage.findAccTypeField();
		accountspage.getAccTypeByParam("Term Deposit");
		accountspage.ContinueBtn();
		Assert.assertEquals(accountspage.verifyAcctNumberErrMsg(), "Account Number required");
	}

	@Test(priority = 6, enabled = true,description = "Do not enter Credit Card details  and click on Continue and verify that the error message is thrown")
	public void verifyCreditCardErrorMsg() {
		accountspage.addBankAccount();
		accountspage.searchForYourBank("ANZ");
		accountspage.verifyAddBankAccTitle();
		accountspage.verifyAndclickDropdown();
		accountspage.verifyAccountTitle();
		accountspage.getAccName("Saver");
		accountspage.findAccTypeField();
		accountspage.getAccTypeByParam("Credit Card");
		accountspage.getCreditCardDetails("");
		accountspage.ContinueBtn();
		Assert.assertEquals(accountspage.verifyCreditCardErrMsg(), "Last 4 digits required");
	}

	/*
	 * @Test(priority = 3, enabled=true) public void verifyUniqueAccnameErrorMsg(){
	 * Assert.assertEquals(accountspage.verifyAcctNameErrMsg(),
	 * "Please enter a unique Name"); }
	 */

	@Test(priority= 7, enabled = true, description="Click on Back button should load previous screen")
	public void clickOnBack() {
		accountspage.addBankAccount();
		accountspage.searchForYourBank("ANZ");
		accountspage.verifyAndclickDropdown();
		accountspage.BackBtn();
	}

	// This test case can be further developed to add another bank account functionality
	/*
	 * @Test(priority = 6, enabled=false, dependsOnMethods="enterBankDetails",
	 * description="Click on Add Another Bank Account Button") public void
	 * addAnotherBankAccount(){ accountspage.AddAnotherAccount(); }
	 */

	// This test case can be further developed to add another bank account
	/*
	 * @Test(priority=7, enabled = false, dependsOnMethods= { "enterBankDetails",
	 * "addAnotherBankAccount" },
	 * description="Click on Add Another Bank Account Button and enter the bankdetails with account type as credit card"
	 * )
	 * 
	 * public void enterAnotherBankDetailsCC(){ accountspage.getAccName("Retail");
	 * accountspage.findAccTypeField();
	 * accountspage.getAccTypeByParam("Credit Card");
	 * accountspage.getCreditCardDetails("4444");
	 * 
	 * }
	 */

	// Close all the browsers
	@AfterMethod
	public void tearDown() {
		System.out.println("closing browser in accs");
		driver.quit();
	}

}
