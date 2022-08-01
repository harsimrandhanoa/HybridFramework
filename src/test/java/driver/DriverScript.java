package driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;
import com.rediff.hybrid.base.ApplicationKeywords;

import util.ReadExcel;
import util.ReadTestsJson;

public class DriverScript {
	ApplicationKeywords app;
	JSONObject testData;
	ITestContext context;

	public DriverScript(ExtentTest test) {
		app = new ApplicationKeywords(test);
	}

	public void executeTest(ReadExcel xls, String sheet, String testName)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		int rows = ReadExcel.getRowCount(sheet);

		for (int rNum = 0; rNum < rows; rNum++) {
			String tcid = ReadExcel.getCellDataForColumn(sheet, "TCID", rNum);

			if (tcid.equals(testName)) {

				String keyword = ReadExcel.getCellDataForColumn(sheet, "Keyword", rNum);
				String object = ReadExcel.getCellDataForColumn(sheet, "Object", rNum);
				String dataKey = ReadExcel.getCellDataForColumn(sheet, "Data", rNum);
				String data = (String) testData.get(dataKey);
				
				String storVal = ReadExcel.getCellDataForColumn(sheet, "StorVal", rNum);
				
			  executeTestBasedOnKeyword(keyword, object, data, dataKey, storVal);

			}

		}
	}

	public void executeTestUsingJson(String testName)
			throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, FileNotFoundException, IOException, ParseException {

		ReadTestsJson readTests = new ReadTestsJson();
		JSONArray testCaseArray = readTests.getTestArray(testName);

		for (int tcaId = 0; tcaId < testCaseArray.size(); tcaId++) {

			JSONObject testStep = (JSONObject) testCaseArray.get(tcaId);

			String keyword = (String) testStep.get("keyword");

			String object = (String) testStep.get("object");

			String dataKey = (String) testStep.get("dataKey");

			String data = (String) testData.get(dataKey);

			String storVal = (String) testStep.get("storVal");
			
			
           executeTestBasedOnKeyword(keyword, object, data, dataKey, storVal);
		}
	}

	public void executeTestBasedOnKeyword(String keyword, String object, String data, String dataKey, String storVal)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
      
	switch (keyword) {
		case "click":
		case "clear":
			callMethodByName(keyword, object);
			break;

		case "type":
		case "validateSelectedValueInDropDown":
		case "selectByVisibleText":
			callMethodByName(keyword, object, data);
			break;

		case "typeHalf":
			String dataHalf = data.substring(0, 10);
			callMethodByName(keyword, object, dataHalf);
			break;

		case "findCurrentStockQuantity":
            callMethodByName(keyword, data, storVal);
            break;

		case "threadWait":
			callMethodByName(keyword, Integer.parseInt(dataKey));
			break;

		case "clickOnText":
		case "selectDateFromCalendar":
		case "verifyStockPresent":
		case "goToTransactionHistory":
		case "goToBuySell":
			callMethodByName(keyword, data);
			break;

		case "acceptAlert":
		case "waitForPageToLoad":
			callMethodByName(keyword);
			break;

		case "validateStockModification":
			String quantityBeforeModification = object.split(",")[0];
			String quantityAfterModification = object.split(",")[1];
			String quantity = (String) testData.get(dataKey.split(",")[0]);
			String action = (String) testData.get(dataKey.split(",")[1]);
			callMethodByName(keyword, quantityBeforeModification, quantityAfterModification, quantity, action);
			break;

		case "validateTransactionHistory":
			quantity = (String) testData.get(dataKey.split(",")[0]);
			action = (String) testData.get(dataKey.split(",")[1]);
			callMethodByName(keyword, quantity, action);
			break;

		default:
			System.out.println("Invalid method name");
			break;
		}
	}

	public void setReport(ExtentTest test) {
		app.setReport(test);
	}

	public void defaultLogin() {
		app.defaultLogin();
	}

	public void quit() {
		if (app != null)
			app.quit();

	}

	public void setData(JSONObject data) {
		testData = data;
	}

	public void setTestContext(ITestContext context) {
		this.context = context;
		app.setTestContext(context);
	}

	public void callMethodByName(String keyword, String arg1) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		Method method;
		method = ApplicationKeywords.class.getMethod(keyword, String.class);
		method.invoke(app, arg1);
	}

	public void callMethodByName(String keyword, String arg1, String arg2) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method;
		method = ApplicationKeywords.class.getMethod(keyword, String.class, String.class);
		method.invoke(app, arg1, arg2);
	}

	public void callMethodByName(String keyword, String arg1, String arg2, String arg3) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method;
		method = ApplicationKeywords.class.getMethod(keyword, String.class, String.class, String.class);
		method.invoke(app, arg1, arg2, arg3);
	}

	public void callMethodByName(String keyword, String arg1, String arg2, String arg3, String args4)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method method;
		method = ApplicationKeywords.class.getMethod(keyword, String.class, String.class, String.class, String.class);
		method.invoke(app, arg1, arg2, arg3, args4);
	}

	public void callMethodByName(String keyword, int arg) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method;
		method = ApplicationKeywords.class.getMethod(keyword, int.class);
		method.invoke(app, arg);
	}

	public void callMethodByName(String keyword) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method;
		method = ApplicationKeywords.class.getMethod(keyword);
		method.invoke(app);
	}

}
