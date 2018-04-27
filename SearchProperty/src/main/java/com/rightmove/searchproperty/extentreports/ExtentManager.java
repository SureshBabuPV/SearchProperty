package com.rightmove.searchproperty.extentreports;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentManager {
	private static ExtentReports extent;
	private static ExtentTest testLog;
	public static ExtentReports getInstance() {
		if (extent == null) {
			Date d=new Date();
			String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
			extent = new ExtentReports(System.getProperty("user.dir")+"\\reports\\" +fileName, true, DisplayOrder.NEWEST_FIRST);

			
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\config\\"+"ReportsConfig.xml"));
			// optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo(
					"Environment", "QA");
		}
		return extent;
	}
	public static ExtentTest getInstanceTestLog() {
		if (testLog != null) {
			return testLog;
		}
		return null;
	}
}

