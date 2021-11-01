package com.rediff.hybrid.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.rediff.hybrid.base.ApplicationKeywords;

public class CreatePortfolioTest {
	 ApplicationKeywords app;
	

    @Test
	public void createPortFolioTest() {
	   // no webdriver code
    }
    @AfterMethod
    public void quit(){
 	   app.quit();
 }
	
	/*@Test
	public void createReservation() {
		
	}*/
	



}
