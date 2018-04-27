/**
 * 
 */
package com.rightmove.searchproperty.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;
import com.rightmove.searchproperty.basetest.BaseTest;
import com.rightmove.searchproperty.pages.HomePage;
import com.rightmove.searchproperty.pages.SearchPage;
import com.rightmove.searchproperty.pages.SearchResultsPage;

/**
 * @author sbabu
 *
 */
public class SearchPropertyTest extends BaseTest {
	HomePage homePage;
	SearchPage searchPage;
	SearchResultsPage searchResultsPage;

	@Test(dataProvider = "getData")
	public void logintest(Hashtable<String, String> testData) throws Exception {

		openBrowser(); // open chrome browser
		// Starting Test for reports
		strTestName = "SearchProperty";
		testLog = extReport.startTest(strTestName);
		testLog.log(LogStatus.INFO, "Search Property Started");

		// Creating page objects
		homePage = new HomePage(driver, testLog);
		searchPage = new SearchPage(driver, testLog);
		searchResultsPage = new SearchResultsPage(driver, testLog);

		// Getting login page
		driver.get(config_prop.getProperty("url"));
		testLog.log(LogStatus.INFO,
				"Navigated to :" + config_prop.getProperty("url"));

		homePage.searchPropertySaleOrRent(testData.get("SearchLocation"),
				testData.get("SaleOrRent"));
		if (searchPage.is_SearchPage()) {
			searchPage.findProperty(testData.get("SearchRadius"),
					testData.get("MinPrice"), testData.get("MaxPrice"),
					testData.get("MinBedrooms"),
					testData.get("MaxBedrooms"),
					testData.get("DaysSince"),
					testData.get("PropertyType"));
		}
		if(searchResultsPage.is_SearchResultsPage()){
			System.out.println(searchResultsPage.getNonFeaturedResult());
		}
		
		/*
		 * loginpage .login_User(testData.get("UserName"),
		 * testData.get("Password")); //Checking login is successful and it
		 * matches with expected if
		 * (testData.get("LoginStatus").equals("LoginSuccessful")) {
		 * 
		 * if (!(homepage.is_HomePage())) { softAssertion.assertTrue( false,
		 * "\n Login is UnSuccessful for user :" + testData.get("UserName") +
		 * " ; Expecting Successful login"); testLog.log( LogStatus.FAIL,
		 * "\n Login is UnSuccessful for user :" + testData.get("UserName") +
		 * " ; Expecting Successful login"); } else { testLog.log(
		 * LogStatus.PASS, "\n Login is Successful for user : " +
		 * testData.get("UserName")); homepage.user_SignOut(); //Checking logout
		 * is successful if (!(loginpage.is_LoginPage())) {
		 * softAssertion.assertTrue( false,
		 * "\n Logout is UnSuccessful for user : " + testData.get("UserName") +
		 * " ; Expecting to logout"); testLog.log( LogStatus.FAIL,
		 * "\n Logout is UnSuccessful for user : " + testData.get("UserName") +
		 * " ; Expecting to logout"); } else { testLog.log( LogStatus.FAIL,
		 * "\n Logout is Successful for user : " + testData.get("UserName")); }
		 * 
		 * } } else if (testData.get("LoginStatus").equals("LoginUnSuccessful"))
		 * { if (homepage.is_HomePage()) { softAssertion.assertTrue( false,
		 * "\n Login is Successful for user : " + testData.get("UserName") +
		 * " ; Expecting login Error"); testLog.log( LogStatus.FAIL,
		 * "\n Login is Successful for user : " + testData.get("UserName") +
		 * " ; Expecting login Error"); } }
		 */

	}

	@AfterMethod
	public void aftermethod(Method result) throws Exception {

		// Ending Test for reports
		if (extReport != null) {
			extReport.endTest(testLog);
			extReport.flush();
		}
		// Closing Browser
		if (driver != null)
			driver.quit();
	}

	@AfterClass
	public void afterTest() {
		softAssertion.assertAll();
	}

	@DataProvider(name = "getData")
	public Object[][] getData() throws IOException {
		softAssertion = new SoftAssert();
		getConfigProperties(); // Initialising Configuration properties
		readTestDatFromXLS(); // Initialising Test Case Data using xls
		return dataHashTable;
	}

}