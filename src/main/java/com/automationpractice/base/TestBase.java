package com.automationpractice.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automationpractice.util.TestUtil;
import com.automationpractice.util.WebEventListener;

public class TestBase {

	public static Properties prop;
	public static JavascriptExecutor js;
	public static WebDriverWait wait; 
	public static Actions action;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static WebDriver driver = null;
	

	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/automationpractice"
					+ "/config/config.properties");
			prop.load(ip);                       
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {

		if(driver==null) {

			if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/res/chromedriver.exe");
				driver = new ChromeDriver();
			}

			else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"/res/msedgedriver.exe");
				driver = new EdgeDriver();
			}
			
			e_driver = new EventFiringWebDriver(driver);
			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("url"));
		}
	}
	
	public static void checkPageIsReady() {

		  js = (JavascriptExecutor) driver;

		  // Initially bellow given if condition will check ready state of page.
		  if (js.executeScript("return document.readyState").toString().equals("complete")) {
		   System.out.println("Page Is loaded.");
		   return;
		  }

		  // This loop will iterate for 25 times to check If page Is ready after
		  // every 1 second.
		  // If the page loaded successfully, it will terminate the for loop
		  for (int i = 0; i < 25; i++) {
		   try {
		    Thread.sleep(1000);
		   } catch (InterruptedException e) {
		   }

		   // To check page ready state.
		   if (js.executeScript("return document.readyState").toString().equals("complete")) {
		    break;
		   }
		  }
		 } 	
		

	public static void quit() {
			driver.quit();
			driver = null;
		}

}
