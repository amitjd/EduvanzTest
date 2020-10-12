package com.automationpractice.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.ResultPage;

public class ResultPageTest extends TestBase {
	
	HomePage ObjHome;
	ResultPage ObjResult;
	
	@BeforeClass
	public void setUp() {
		TestBase.initialization();
		TestBase.checkPageIsReady();
		ObjHome = new HomePage();
		ObjResult = new ResultPage();
		ObjHome.search();
	}
	
	
	@Test(priority=0)
	public void resultsDisplayCheck() {
		Assert.assertTrue(ObjResult.resultAvailable(), "No results to display");
	}
	
	@Test(priority=1)
	public void selectProduct() throws InterruptedException {
		ObjResult.selectItem();
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void tearDown() {
		TestBase.quit();
	}
	

}
