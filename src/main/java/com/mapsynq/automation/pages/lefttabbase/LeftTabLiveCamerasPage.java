package com.mapsynq.automation.pages.lefttabbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class LeftTabLiveCamerasPage extends UtilClass{

	private static Logger log = Logger.getLogger(LeftTabLiveCamerasPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "txtSearchCamerasingapore")
    private WebElement txtSearchLocation;

	@FindBy(xpath = "(//ul[@id='camera_location_singapore']//li)[1]")
	private WebElement lnkFirstCameraLocationName;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(xpath = "//label//b")
    private WebElement lblTextInfo;


	public LeftTabLiveCamerasPage(WebDriver driver) {
		log.info("LeftTabLiveCameras Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(txtSearchLocation));
	}

	public LeftTabLiveCamerasPage selectFirstLocationLink() {
		log.info("Click First Link Location of Camera");
		clickElement(driver, lnkFirstCameraLocationName);

		return this;
	}

	public boolean verifyTrafficCamerasPopUp() {
		String getTextFromFirstCameraLinkLocation = lnkFirstCameraLocationName.getText();
		boolean popupTitleHeader = headerPopUpTitle.getText().contains("Traffic Camera");
		driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[contains(@id,'ifCam')])[1]")));
		String getTextInsidePopUp = lblTextInfo.getText();
		driver.switchTo().defaultContent();
		log.info("Text of First Link Location of Camera" + getTextFromFirstCameraLinkLocation);
		log.info("Text From Pop-Up" + getTextInsidePopUp);

		return popupTitleHeader && (getTextInsidePopUp.contains(getTextFromFirstCameraLinkLocation));
	}
}