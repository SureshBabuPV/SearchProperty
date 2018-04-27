package com.rightmove.searchproperty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage {

	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName = "HomePage : ";

	// Declaring all web elements in login page
	@FindBy(xpath = "//input[@id='searchLocation']")
	private WebElement txtbx_SearchLocation;
	@FindBy(xpath = "//button[@id='buy']")
	private WebElement btn_ForSale;
	@FindBy(xpath = "//button[@id='rent']")
	private WebElement btn_ToRent;

	public HomePage(WebDriver driver, ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public void enter_SearchLocation(String strSearchLocation) {
		txtbx_SearchLocation.sendKeys(strSearchLocation);
		testLog.log(LogStatus.INFO, strPageName
				+ "Entered Search Location as :" + strSearchLocation);
	}

	public void clickOn_ForSale() {
		btn_ForSale.click();
		testLog.log(LogStatus.INFO, strPageName + "Clicked on ForSale ");
	}

	public void clickOn_ToRent() {
		btn_ToRent.click();
		testLog.log(LogStatus.INFO, strPageName + "Clicked on ToRent ");
	}

	public void searchPropertySaleOrRent(String strSearchLocation, String strSaleOrRent) {
		enter_SearchLocation(strSearchLocation);
		if (strSaleOrRent.equalsIgnoreCase("ForSale")) {
			clickOn_ForSale();
		} else if (strSaleOrRent.equalsIgnoreCase("ToRent")) {
			clickOn_ToRent();
		}
		else
		{
			testLog.log(LogStatus.ERROR, strPageName + "Invalid option : "+ strSaleOrRent);
		}
	}

	public boolean is_HomePage() {
		testLog.log(LogStatus.INFO, strPageName
				+ "Verifying User Name is visible : ");
		try {
			txtbx_SearchLocation.isDisplayed();
			testLog.log(LogStatus.INFO, strPageName + " User Name is visible");
		} catch (Exception e) {
			testLog.log(LogStatus.INFO, strPageName
					+ " User Name is not visible");

			return false;
		}
		return true;

	}
}
