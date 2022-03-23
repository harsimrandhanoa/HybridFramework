package com.rediff.hybrid.newtestcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.rediff.hybrid.base.BaseTest;
public class PortfolioManagement extends BaseTest {

	@Test
	public void createPortfolio(ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);

		/* To run keywords from excel file we can comment the above line of
		 code and uncomment the below two lines
		 We can repeat the same logic in all the test cases of this project
		 ReadExcel xls = new ReadExcel(System.getProperty("user.dir")+"\\src\\test\\resources\\json\\testcases\\Testcases.xlsx");
		 ds.executeTest(xls, "TestCases", testCase);
    */
	}

	@Test
	public void deletePortfolio(ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);
	}

	@Test
	public void selectPortfolio(ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);
	}

}
