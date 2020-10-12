package com.automationpractice.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automationpractice.base.TestBase;

public class ResultPage extends TestBase{
	
	WebElement element1;
	
	@FindBy(className = "product-container")
	List<WebElement> Items;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[1]/div/div[2]/span[1]")
	WebElement PriceOnResultHover;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/h5/a")
	WebElement ItemName;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/span/span")
	WebElement StockStatus;
	
	@FindBy(xpath = "/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/div[3]/ul")
	List<WebElement> Colours;
	
	
	
	public ResultPage() {
		PageFactory.initElements(TestBase.driver, this);
		action = new Actions(driver);
	}
	
	public boolean resultAvailable() {
		
		//js.executeScript("arguments[0].scrollIntoView()", Items);
		System.out.println(Items.size());
		return Items.size()>=1;
	}
	
	public double stringToNum(String s) {
		return Double.parseDouble(s);
		
	}
	
	public void selectItem() { 
		
		for (int i=1; i<=Items.size(); i++) {
			element1 = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li["+i+"]/div"));
			js.executeScript("arguments[0].scrollIntoView()", element1);
			action.moveToElement(element1).build().perform();
			
			boolean flag = false;
			js.executeScript("arguments[0].scrollIntoView()", StockStatus);
			System.out.println(StockStatus.getText());
			if(StockStatus.getText()=="In stock") {
				double price = this.stringToNum(PriceOnResultHover.getText().substring(1));
				double price_prop = this.stringToNum(prop.getProperty("price"));
				if(price < price_prop) {
					for(int j=1; j<Colours.size(); j++) {
						System.out.println(Colours.size());
						if(driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li[1]/div/div[2]/div[3]/ul/li["+j+"]/a")).getAttribute("id") == prop.getProperty("colour"))
							flag = true;
						else continue;
					}
				}
				else System.out.println("Item's price is not less than $50");
			}
			else System.out.println("Item is Out Of Stock");
			
			if(flag)
				driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div[2]/ul/li["+i+"]/div/div[2]/div[2]/a[2]/span")).click();
		}
	}
}
