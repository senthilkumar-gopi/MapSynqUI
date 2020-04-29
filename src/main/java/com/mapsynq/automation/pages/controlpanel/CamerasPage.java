package com.mapsynq.automation.pages.controlpanel;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class CamerasPage extends UtilClass{

	private static Logger log = Logger.getLogger(CamerasPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "(//*[name()='image' and @stroke='#000000'])[1]")
    private WebElement camerasLogo;
	
	@FindBy(xpath = "//img[contains(@id,'zoomin_innerImage')]")
	private WebElement mapZoomBtn;

	@FindBy(linkText = "Zoom in")
    private WebElement lnkZoomIn;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(xpath = "//img[contains(@src,'cameras')]")
    private WebElement imgTrafficPhoto;
	
	@FindBy(xpath = "(//iframe[contains(@id,'ifCam')])[1]")
    private WebElement frameLoc;


	public CamerasPage(WebDriver driver) {
		log.info("Cameras Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(camerasLogo));
	}

	public CamerasPage clickFirstCamerasLogo() {
		log.info("Clicking First Occurrence of Cameras Logo Symbol");
		clickElement(driver, camerasLogo);
		log.info("Clicked First Cameras Logo Symbol");

		return this;
	}

	public boolean verifyTrafficCamerasPopUp() {
		boolean popupTitleHeader = headerPopUpTitle.getText().contains("Traffic camera");
		driver.switchTo().frame(frameLoc);
		boolean imgCameraPopUp = imgTrafficPhoto.isDisplayed();
		driver.switchTo().defaultContent();

		return popupTitleHeader && imgCameraPopUp;
	}

}