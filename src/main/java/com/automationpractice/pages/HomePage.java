package com.automationpractice.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a")
	WebElement SignIn;
	
	@FindBy(xpath = "/html/body/div/div[1]/header/div[2]/div/div/nav/div[1]/a/span")
	WebElement UserAccount;
	
	@FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[2]/form/input[4]")
	WebElement MainSearchBar;
	
	@FindBy(xpath = "/html/body/div[2]/ul/li/strong")
	WebElement AutoSuggestList;
	
	@FindBy(xpath = "/html/body/div[1]/div[1]/header/div[3]/div/div/div[2]/form/button")
	WebElement SearchIcon;
	
	
	public HomePage() {
		PageFactory.initElements(TestBase.driver, this);
	}
	
	public String autoSuggestCheck() {
		MainSearchBar.sendKeys(prop.getProperty("searchterm"));
		return AutoSuggestList.getText().toLowerCase();
	}
	
	public void search() {
		MainSearchBar.sendKeys(prop.getProperty("searchterm"));
		SearchIcon.click();
	}
	
	public boolean signInLink() {
		return SignIn.isDisplayed();
	}
	
	public void signIn() {
		SignIn.click();
	}
	
	public boolean signInCheck() {
		return UserAccount.isDisplayed();
	}
	
	public String loggedInUserCheck() {
		return UserAccount.getText();
	}
}
