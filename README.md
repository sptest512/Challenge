# TATechnicalChallenge

Project is written in Java using Selenium Webdriver with usage of Page Object Model along with data driven approach using TestNG Data Providers.

### Tools used : Version
	Chromedriver version : 2.38
	Selenium version : 2.47.1
	TestNG version : 6.11
	Operating System : Linux 64-bit
	Chrome Browser : 66.0.3359.181 

### Pre-requisites
 1. Java needs to be installed on the system and environment variable JAVA_HOME should be set correctly to the JDK path.
	Check by running below command in command prompt
		java -version
 2. Maven needs to be installed on the system.
 	Check by running below command in command prompt
		mvn -v

### Set Up
 1. Clone the project to your system using the below command if git is installed or download the project from the browser UI.
 	git clone https://github.com/SwethaPatil/TATechnicalChallenge.git

 2. In the location where project is downloaded, locate config.properties inside ./TATechnicalChallenge/config folder and make changes to email, password, osVersion in the properties as per your system.

### Run the test suite
 1. Open the project base folder in a command prompt.

 2. Run the below command to execute the test suite
		mvn clean test -DsuiteXmlFile=testng.xml
 
 3. Following test cases are not enabled, but can be enabled by changing the enabled flag to true in the test case class.
 	Invalid Login credentials - LoginPageTest
 	Invalid Login credentials, user locked - LoginPageTest 

### Output
	Once the test suite is run, two bank accounts(One with bank Account Type as"Other" and another with "Credit Card") should be added successfully.
	An HTML report for the execution would be generated in target/surefire-reports folder. Open Extent.html in browser to view the results.
	In case any test case fails, screenshots would be present in screenshots folder.

### Desired Capabilities
Tests are run in Linux based system (Ubuntu 16.04) on Chrome Browser. However the OS preference can be changed in config.properties file present in config folder in the workspace

### Test Cases
	Valid and Invalid test cases have been written in the testcase class files present in "testcases" package. The parameters for test cases can be changed accordingly to test various scenarios.
	Test data can be provided using data provider parameter in testng test classes.
	Test case can be enabled/disabled by setting enable parameter to true/false.

### Few Observations:
 1. Navigate to Accounts > Bank Accounts > Add Bank Account
    Enter Account Name ="ABC", Account Type="Loan", Account Number ="1234567"
    Now change the Account Type value to "Credit Card", the Account Number field is replaced with Credit Card Details and pre-populated  with previous Account Number value which is "1234567", whereas the Credit Card field accepts only 4 digits value
 2. Account Number and Credit Card field is alphanumeric# TechnicalChallenge
