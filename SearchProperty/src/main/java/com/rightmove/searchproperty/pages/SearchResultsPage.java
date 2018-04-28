package com.rightmove.searchproperty.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchResultsPage {
	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName = "SearchResultsPage : ";

	@FindAll(@FindBy(xpath = "//div[@class='l-searchResult is-list']/div"))
	private List<WebElement> listSearchResults;

	@FindBy(xpath = "//div[@class='l-searchResult is-list']")
	private WebElement listSearchResult;

	@FindBy(xpath = "//select[@id='sortType']")
	private WebElement selSortType;

	@FindBy(xpath = "//h1[@class='searchTitle-heading']")
	private WebElement txt_SearchTitle;

	public SearchResultsPage(WebDriver driver, ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public boolean select_SortType(String strSortType) {
		try {
			if (strSortType != "") {
				Select opt_SortType = new Select(selSortType);
				opt_SortType.selectByVisibleText(strSortType);
			}
			testLog.log(LogStatus.INFO, strPageName + "Select Sort Type as :"
					+ strSortType);
			return true;
		} catch (Exception e) {
			testLog.log(LogStatus.ERROR, strPageName
					+ "Could not Select Sort Type as :" + strSortType);
			return false;
		}
	}

	public String getNonFeaturedResult() {
		String strAddress;
		for (WebElement webEleSearchResult : listSearchResults) {
			//System.out.println(webEleSearchResult.getAttribute("class").contains("featured"));
			if (!(webEleSearchResult.getAttribute("class").contains("featured"))) {
/*				strAddress = webEleSearchResult.findElement(
						By.xpath(".//div[@class='propertyCard-section']"))
						.getText();*/
				strAddress = webEleSearchResult.getText();
				System.out.println(strAddress);
				return strAddress;
			}
		}

		return "-1";
	}

	public boolean is_SearchResultsPage() {
		testLog.log(LogStatus.INFO, strPageName
				+ "Verifying Search Results is visible : ");
		try {
			listSearchResult.isDisplayed();
			testLog.log(LogStatus.INFO, strPageName
					+ " Search Results is visible");
		} catch (Exception e) {
			testLog.log(LogStatus.INFO, strPageName
					+ " Search Results is not visible");

			return false;
		}
		return true;

	}

	public void verifySearchTitle(String strSearchLocation, String strSaleOrRent) {
		String strExpSearchTitle = null;
		
		if (strSaleOrRent.equalsIgnoreCase("ForSale")) {
			strExpSearchTitle = "Properties For Sale in " + strSearchLocation;
		} else if (strSaleOrRent.equalsIgnoreCase("ToRent")) {
			strExpSearchTitle = "Properties To Rent in " + strSearchLocation;
		}
		strExpSearchTitle =strExpSearchTitle.toLowerCase();
		testLog.log(LogStatus.INFO, strPageName + "Verifying Search Title : ");
		String strActHeaderTitle= txt_SearchTitle.getText().trim().toLowerCase();

		if (strActHeaderTitle.contains(strExpSearchTitle)) {
		
			testLog.log(
					LogStatus.INFO,
					strPageName
							+ " Search Title contains expected title and is displayed as: "
							+ strActHeaderTitle + "; Expected :"
							+ strExpSearchTitle);
		} else {
			testLog.log(
					LogStatus.ERROR,
					strPageName
							+ " Search Title not contains expected title and is displayed as: "
							+ strActHeaderTitle + "; Expected :"
							+ strExpSearchTitle);
		}
	}
}