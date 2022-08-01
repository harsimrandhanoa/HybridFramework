package com.rediff.hybrid.base;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import driver.DriverScript;
import reporting.ExtentManager;
import runner.DataUtil;
import util.ReadDataSample;

public class BaseTest {

	public DriverScript ds;
	public ExtentReports rep;
	public ExtentTest test;

	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context)
			throws NumberFormatException, FileNotFoundException, IOException, ParseException {

		String dataFilePath = context.getCurrentXmlTest().getParameter("datafilepath");
		String dataFlag = context.getCurrentXmlTest().getParameter("dataflag");
		String iteration = context.getCurrentXmlTest().getParameter("iteration");
		String sheetName = context.getCurrentXmlTest().getParameter("suitename");

		JSONObject data = new DataUtil().getTestData(dataFilePath, dataFlag, Integer.parseInt(iteration));

		/* To read data from excel
        JSONObject data = new ReadDataSample().excelData(dataFilePath,sheetName,dataFlag,(Integer.parseInt(iteration)+1));
       */
		
		context.setAttribute("data", data);
		String runmode = (String) data.get("runmode");

		rep = ExtentManager.getReports(); // made object of rep
		test = rep.createTest(context.getCurrentXmlTest().getName()); // made object of test
		test.log(Status.INFO, "Starting Test " + context.getCurrentXmlTest().getName());
		test.log(Status.INFO, "Data " + data.toString());

		context.setAttribute("report", rep); // set rep and test objects in context
		context.setAttribute("test", test);

		if (runmode.equals("N")) {

			test.log(Status.SKIP, "Skipping as runmod is N");
			throw new SkipException("Skipping as runmod is N");
        }

		ds = new DriverScript(test); // 1 app driver script object for entire test -All @Test
		//ds.setReport(test); // passed the test object created above to Driver Script Class
		// Things to note test object is send to ds but also set in context
		// above to be shared between various methods in this context. same is true for data object
		ds.setData(data); // send json data object to ds as well.
		ds.setTestContext(context); // send context object to ds so that everything saved in context like data,
									// report ,test etc is used there
		context.setAttribute("driverScript", ds); // set object of app

		ds.defaultLogin(); // do login

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {

		String criticalFailure = (String) context.getAttribute("criticalFailure");
		if (criticalFailure != null && criticalFailure.equals("Y")) {
			throw new SkipException("Critical Failure in Prevoius Tests");// skip in testNG
		}

		// Use these variables set in before test in each method

		ds = (DriverScript) context.getAttribute("driverScript");
	//	rep = (ExtentReports) context.getAttribute("report");
	}

	@AfterTest(alwaysRun = true)
	public void quit(ITestContext context) {
		//ds = (DriverScript) context.getAttribute("driverScript");
		if (ds != null)
			ds.quit();
	//	rep = (ExtentReports) context.getAttribute("report");
		if (rep != null)
			rep.flush();

	}

}
