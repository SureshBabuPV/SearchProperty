package com.rightmove.searchproperty.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SearchResultsPage {
	WebDriver driver;
	public ExtentTest testLog;
	public String strPageName = "SearchResultsPage : ";

	@FindAll(@FindBy(xpath = "//div[@class='propertyCard']"))
	private List<WebElement> listWebEleSearchResults;

	@FindAll(@FindBy(xpath = "//div[@class='l-searchResult is-list']/div"))
	private List<WebElement> listSearchResults;
	@FindBy(xpath = "//div[@class='l-searchResult is-list']")
	private WebElement listSearchResult;
	public SearchResultsPage(WebDriver driver, ExtentTest testLog) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.testLog = testLog;
	}

	public String getNonFeaturedResult() {
		String strAddress;
		for (WebElement webEleSearchResult : listSearchResults) {
			if (!(webEleSearchResult.getAttribute("class").contains("featured")) ){
			strAddress = webEleSearchResult.findElement(
						By.xpath(".//div[@class='propertyCard-section']"))
						.getText();
				System.out.println(strAddress);
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
			testLog.log(LogStatus.INFO, strPageName + " Search Results is visible");
		} catch (Exception e) {
			testLog.log(LogStatus.INFO, strPageName
					+ " Search Results is not visible");

			return false;
		}
		return true;

	}
}