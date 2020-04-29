package com.mapsynq.automation.pages.lefttabbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class LeftTabLiveTollsPage extends UtilClass{

	private static Logger log = Logger.getLogger(LeftTabLiveTollsPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "txtSearchERPsingapore")
    private WebElement txtSearchLocation;

	@FindBy(xpath = "(//ul[@id='erp_locationsingapore']//li)[1]")
	private WebElement lnkFirstTollLocationName;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(xpath = "//label//b")
    private WebElement lblTextInfo;
	
	@FindBy(xpath = "(//iframe[contains(@id,'myDropdownList')])[1]")
    private WebElement frameLoc;
	

	public LeftTabLiveTollsPage(WebDriver driver) {
		log.info("LeftTabLiveTollsPage constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(txtSearchLocation));
	}

	public LeftTabLiveTollsPage selectFirstLocationLink() {
		log.info("Click First Link Location of Toll");
		clickElement(driver, lnkFirstTollLocationName);

		return this;
	}

	public boolean verifyTollPopUp() {
		String getTextFromFirstTollLinkLocation = lnkFirstTollLocationName.getText().toUpperCase();
		boolean popupTitleHeader = headerPopUpTitle.getText().contains("ERP");
		driver.switchTo().frame(frameLoc);
		String getTextInsidePopUp = lblTextInfo.getText().toUpperCase();
		driver.switchTo().defaultContent();
		log.info("Text of First Link Location of Toll" + getTextFromFirstTollLinkLocation);
		log.info("Text From Pop-Up" + getTextInsidePopUp);

		return popupTitleHeader && getTextInsidePopUp.contains(getTextFromFirstTollLinkLocation);
	}

}