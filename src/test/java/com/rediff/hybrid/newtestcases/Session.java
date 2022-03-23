package com.rediff.hybrid.newtestcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.rediff.hybrid.base.ApplicationKeywords;
import com.rediff.hybrid.base.BaseTest;

public class Session extends BaseTest{
	
	@Test
	public void doLogin(ITestContext context) {
		ApplicationKeywords app = (ApplicationKeywords) context.getAttribute("app");
		app.log("Logging In");
        app.openBrowser("Chrome");
        
		app.navigate("url");
		app.type("username_css", "abc");
        app.type("password_css", "123");
		app.validateElementPresent("loginButton_css");
		app.click("loginButton_css");
		app.validateLogin();
		
		
	}
	
	/*@Test
	public void doLogout() {
	//	test.log(Status.INFO, "Logging out");
	}*/
}