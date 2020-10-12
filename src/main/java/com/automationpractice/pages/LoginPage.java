package com.automationpractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[1]/input")
	WebElement EmailField;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/div[2]/span/input")
	WebElement PasswordField;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div/div/div[2]/form/div/p[2]/button/span")
	WebElement SignInBtn;
	
	public LoginPage() {
		PageFactory.initElements(TestBase.driver, this);
	}
	
	public void submit() {
		
		EmailField.sendKeys(prop.getProperty("email"));
		PasswordField.sendKeys(prop.getProperty("password"));
		SignInBtn.click();
	}

}
