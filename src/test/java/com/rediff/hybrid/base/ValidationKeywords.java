package com.rediff.hybrid.base;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ValidationKeywords extends GenericKeywords {

	public void validateTitle() {
		log("Validating title");
	}

	public void validateText() {
		log("Validating text");
	}

	public void validateElementPresent(String locatorKey) {

		String locator = prop.getProperty(locatorKey);
		// failure
		boolean result = isElementPresent(locator);
	   // reportFailure("Element not found "+ locator,true);
	}

	public void validateLogin() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://portfolio.rediff.com/portfolio"));
	}

	public void validateSelectedValueInDropDown(String locatorKey, String option) {
		String locator = prop.getProperty(locatorKey);

		Select s = new Select(getElement(locator));
		String text = s.getFirstSelectedOption().getText();
		if (!text.equals(option)) {
			reportFailure("Option" + option + " not present in Drop Down " + locatorKey, true);
		}

	}

	public void validateSelectedValueNotInDropDown(String locatorKey, String option) {
		String locator = prop.getProperty(locatorKey);
		Select s = new Select(getElement(locator));
		String text = s.getFirstSelectedOption().getText();
		if (text.equals(option)) {
			reportFailure("Option" + option + " present in Drop Down " + locatorKey, true);
		}

	}

	public void verifyStockPresent(String companyName) {
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if (row == -1)
			reportFailure("Stock Not present " + companyName, true);
		log("Stock Found in list " + companyName);

	}

	public void validateStockModification(String beforeModificationQuantity, String modifiedQuantity, String quantity,
			String action) {

		int quantityBeforeModification = Integer.parseInt((String) context.getAttribute(beforeModificationQuantity));

		int quantityAfterModification = Integer.parseInt((String) context.getAttribute(modifiedQuantity));

		int expectedModifiedQuantity = 0;

		if (action.equals("Buy"))
			expectedModifiedQuantity = quantityAfterModification - quantityBeforeModification;
		else if (action.equals("Sell"))
			expectedModifiedQuantity = quantityBeforeModification - quantityAfterModification;

		log("Old Stock Quantity--------------------------------------------> " + quantityBeforeModification);
		log("New Stock Quantity-------------------------------------------> " + quantityAfterModification);

		if (Integer.parseInt(quantity) != expectedModifiedQuantity)
			reportFailure("Quantity did not match", true);

		log("---Stock Quantity Changed as per expected--- " + quantity);
	}

	public void validateTransactionHistory(String quantity, String action) {

		String changedQuantityDisplayed = getText("latestShareChangeQuantity_xpath");
		log("Got Changed Quantity " + changedQuantityDisplayed);

		if (action.equals("Sell"))
			quantity = "-" + quantity;

		if (!changedQuantityDisplayed.equals(quantity))
			reportFailure("Got changed quantity in transaction history as " + changedQuantityDisplayed, true);

		log("---Transaction History OK---");

	}

}
