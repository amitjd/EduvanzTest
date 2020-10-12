package com.automationpractice.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automationpractice.base.TestBase;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.LoginPage;

public class HomePageTest extends TestBase{
	
	HomePage ObjHome;
	LoginPage ObjLogin;

	@BeforeClass
	public void setUP() {
		TestBase.initialization();
		TestBase.checkPageIsReady();
		ObjHome = new HomePage();
		ObjLogin = new LoginPage();
	}
	
	
	@Test(priority=0)
	public void verifyPageTitle() {
		Assert.assertEquals(TestBase.driver.getTitle(), "My Store", "Page Title mismatch");
	}
	
	@Test(priority=1)
	public void autoSuggestCheck() {
		Assert.assertEquals(ObjHome.autoSuggestCheck(), prop.getProperty("searchterm"));
	}
	
	@Test(priority=2)
	public void signInLink() {
		Assert.assertTrue(ObjHome.signInLink(), "SignIn Link not found");
	}
	
	@Test(priority=3)
	public void LogniInCheck() {
		ObjHome.signIn();
		ObjLogin.submit();
		Assert.assertTrue(ObjHome.signInCheck(), "SignIn Failed");
		Assert.assertEquals(ObjHome.loggedInUserCheck(), prop.getProperty("username"), "Incorrect user login");
	}
	
	@AfterClass
	public void tearDown() {
		TestBase.quit();
		
	}
	
}
