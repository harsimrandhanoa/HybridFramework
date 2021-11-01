package com.rediff.hybrid.newtestcases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.rediff.hybrid.base.BaseTest;

import util.ReadExcel;

public class StockManagement extends BaseTest {

	@Test
	public void addNewStock(ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {
		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);
	}

	// sell or buy existing stock

	@Parameters({ "action" })
	@Test
	public void modifyStock(String action, ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);

	}

	// checks if stock is present in the table
	@Test
	public void verifyStockPresent()
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);
	}

	// checks the stock quantity
	@Parameters({ "action" })
	@Test
	public void verifyStockQuantity(String action, ITestContext context)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);
	}

	@Test
	public void verifyStockAvgBuyPrice() {
	}

	// verifies the transaction history
	@Parameters({ "action" })
	@Test
	public void verifyTransactionHistory(String action, ITestContext context)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, InterruptedException, FileNotFoundException, IOException, ParseException {

		String testCase = Thread.currentThread().getStackTrace()[1].getMethodName();
		ds.executeTestUsingJson(testCase);

	}

}
