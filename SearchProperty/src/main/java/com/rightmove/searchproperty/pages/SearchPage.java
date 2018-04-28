package com.rightmove.searchproperty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchPage {

	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName = "SearchPage : ";

	// Declaring all web elements in login page
	@FindBy(xpath = "//select[@id='radius']")
	private WebElement sel_Radius;
	@FindBy(xpath = "//select[@id='displayPropertyType']")
	private WebElement sel_PropertyType;
	@FindBy(xpath = "//select[@id='minPrice']")
	private WebElement sel_MinPrice;
	@FindBy(xpath = "//select[@id='maxPrice']")
	private WebElement sel_MaxPrice;
	@FindBy(xpath = "//select[@id='minBedrooms']")
	private WebElement sel_MinBedrooms;
	@FindBy(xpath = "//select[@id='maxBedrooms']")
	private WebElement sel_MaxBedrooms;
	@FindBy(xpath = "//select[@id='maxDaysSinceAdded']")
	private WebElement sel_DaysSince;
	@FindBy(xpath = "//button[@id='submit']")
	private WebElement btn_FindProperties;
	// when no results found on search page
	@FindBy(xpath = "//div[@id='zeroResults' and @class='zeroResults']")
	private WebElement txt_zeroResults;
	@FindBy(xpath = "//h1[@id='headerTitle']")
	private WebElement txt_HeaderTitle;

	public SearchPage(WebDriver driver, ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public boolean select_Radius(String strRadius) {
		try {
			if (strRadius != "") {
				Select opt_Radius = new Select(sel_Radius);
				opt_Radius.selectByVisibleText(strRadius);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select Radius as :"
					+ strRadius);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select Radius as :" + strRadius);
			return false;
		}
	}

	public boolean select_MinPrice(String strMinPrice) {
		try {
			if (strMinPrice != "") {
				Select opt_MinPrice = new Select(sel_MinPrice);
				opt_MinPrice.selectByVisibleText(strMinPrice);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select MinPrice as :"
					+ strMinPrice);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select Radius as :" + strMinPrice);
			return false;
		}
	}

	public boolean select_MaxPrice(String strMaxPrice) {
		try {
			if (strMaxPrice != "") {
				Select opt_MaxPrice = new Select(sel_MaxPrice);
				opt_MaxPrice.selectByVisibleText(strMaxPrice);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select MaxPrice as :"
					+ strMaxPrice);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select MaxPrice as :" + strMaxPrice);
			return false;
		}
	}

	public boolean select_MinBedrooms(String strMinBedrooms) {
		try {
			if (strMinBedrooms != "") {
				Select opt_MinBedrooms = new Select(sel_MinBedrooms);
				opt_MinBedrooms.selectByVisibleText(strMinBedrooms);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select MinBedrooms as :"
					+ strMinBedrooms);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select MinBedrooms as :" + strMinBedrooms);
			return false;
		}
	}

	public boolean select_MaxBedrooms(String strMaxBedrooms) {
		try {
			if (strMaxBedrooms != "") {

				Select opt_MaxBedrooms = new Select(sel_MaxBedrooms);
				opt_MaxBedrooms.selectByVisibleText(strMaxBedrooms);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select MaxBedrooms as :"
					+ strMaxBedrooms);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select MaxBedrooms as :" + strMaxBedrooms);
			return false;
		}
	}

	public boolean select_DaysSince(String strDaysSince) {
		try {
			if (strDaysSince != "") {

				Select opt_DaysSince = new Select(sel_DaysSince);
				opt_DaysSince.selectByVisibleText(strDaysSince);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select MaxBedrooms as :"
					+ strDaysSince);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select MaxBedrooms as :" + strDaysSince);
			return false;
		}
	}

	public boolean select_PropertyType(String strPropertyType) {
		try {
			if (strPropertyType != "") {

				Select opt_PropertyType = new Select(sel_PropertyType);
				opt_PropertyType.selectByVisibleText(strPropertyType);
			}
			testLog.log(LogStatus.INFO, strPageName
					+ "Select Property Type as :" + strPropertyType);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.INFO, strPageName
					+ "Could not Select Property Type as :" + strPropertyType);
			return false;
		}
	}

	public void clickOn_btnFindProperties() {
		btn_FindProperties.click();
		testLog.log(LogStatus.INFO, strPageName + "Clicked on Submit ");
	}

	public boolean findProperty(String strRadius, String strMinPrice,
			String strMaxPrice, String strMinBedrooms, String strMaxBedrooms,
			String strDaysSince, String strPropertyType) {
		select_Radius(strRadius);
		select_MinPrice(strMinPrice);
		select_MaxPrice(strMaxPrice);
		select_MinBedrooms(strMinBedrooms);
		select_MaxBedrooms(strMaxBedrooms);
		select_DaysSince(strDaysSince);
		select_PropertyType(strPropertyType);
		clickOn_btnFindProperties();
		if (is_elementDisplayed(txt_zeroResults)) {
			return false;
		}
		if (is_elementDisplayed(sel_Radius)) {
			return false;
		}
		return true;

	}

	public boolean is_SearchPage() {
		testLog.log(LogStatus.INFO, strPageName
				+ "Verifying Property Type List is visible : ");
		try {

			sel_PropertyType.isDisplayed();

			testLog.log(LogStatus.INFO, strPageName
					+ " Property Type List is visible");
		} catch (Exception e) {
			testLog.log(LogStatus.INFO, strPageName
					+ " Property Type List is not visible");

			return false;
		}
		return true;

	}

	/*
	 * public boolean verifySearchString(String strSearchString) {
	 * testLog.log(LogStatus.INFO, strPageName + "Verifying Search String : ");
	 * try {
	 * 
	 * sel_PropertyType.isDisplayed();
	 * 
	 * testLog.log(LogStatus.INFO, strPageName +
	 * " Property Type List is visible"); } catch (Exception e) {
	 * testLog.log(LogStatus.INFO, strPageName +
	 * " Property Type List is not visible");
	 * 
	 * return false; } return true;
	 * 
	 * }
	 */

	public boolean is_elementDisplayed(WebElement webElement) {

		try {
			webElement.isDisplayed();
			return true;

		} catch (Exception e) {

			return false;
		}

	}

	public String getErrorMessage() {
		String strErrMsg = "-1";
		if (is_elementDisplayed(txt_zeroResults))
			strErrMsg = txt_zeroResults.getText();
		return strErrMsg;
	}

	public void verifyHeaderTitle(String strSearchLocation, String strSaleOrRent) {
		
		String strExpHeaderTitle = null;
		
		if (strSaleOrRent.equalsIgnoreCase("ForSale")) {
			strExpHeaderTitle = "property for sale in " + strSearchLocation;
		} else if (strSaleOrRent.equalsIgnoreCase("ToRent")) {
			strExpHeaderTitle = "property to rent in " + strSearchLocation;
		}
		strExpHeaderTitle =strExpHeaderTitle.toLowerCase();
		testLog.log(LogStatus.INFO, strPageName + "Verifying Header Title : ");
		String strActHeaderTitle= txt_HeaderTitle.getText().trim().toLowerCase();

		if (strActHeaderTitle.contains(strExpHeaderTitle)) {
			testLog.log(
					LogStatus.INFO,
					strPageName
							+ " Header Title contains expected title and is displayed as: "
							+ txt_HeaderTitle.getText() + "; Expected :"
							+ strExpHeaderTitle);
		} else {
			testLog.log(
					LogStatus.ERROR,
					strPageName
							+ " Header Title not contains expected title and is displayed as: "
							+ txt_HeaderTitle.getText() + "; Expected :"
							+ strExpHeaderTitle);
		}
	}
}
