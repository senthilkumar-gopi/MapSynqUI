package com.mapsynq.automation.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilClass {

    private static Logger log = Logger.getLogger(UtilClass.class);
	private WebDriverWait wait;

	public void clickElement(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void clickElementByLocator(WebDriver driver, By bylocator)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		wait.until(ExpectedConditions.elementToBeClickable(bylocator));

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(bylocator)).click().build().perform();
	}

	public void mouseoverElementAndClick(WebDriver driver, WebElement element, By locator)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions a = new Actions(driver);
		a.moveToElement(element).click().build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		a.moveToElement(driver.findElement(locator)).click().build().perform();
	}

	public String getElementText(WebDriver driver, WebElement element)
	{
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void selectByValue(WebElement element, String value)
	{
		new Select(element).selectByVisibleText(value);
	}

	public void selectByIndex(WebElement element, int value)
	{
		new Select(element).selectByIndex(value);
	}

	public void setText(WebDriver driver, WebElement element, String text)
	{
		wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(text);
	}

	public void waitForSeconds(int sec)
	{
		try
		{
			log.info("Sleeping for "+sec+" seconds");
			Thread.sleep(sec * 1000L);
		}
		catch(Exception e)
		{
			log.info("Problem in sleep");
		}
	}
	
	public void waitForElementToAppear(WebDriver driver, WebElement ele) {
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public boolean isElementPresent(WebElement ele) {
		try {
			return ele.isDisplayed();
		} catch(Exception e) {
			log.info("Problem in locating element : " + e.toString());
		}
		return false;
	}
}