package com.mapsynq.automation.pages.controlpanel;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class TrafficPage extends UtilClass{

	private static Logger log = Logger.getLogger(TrafficPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "profile_first_name")
    private WebElement txtFirstName;

	@FindBy(xpath = "(//*[name()='image' and @stroke='#000000'])[1]")
    private WebElement parkingLogo;
	
	@FindBy(xpath = "//img[contains(@id,'zoomin_innerImage')]")
	private WebElement mapZoomBtn;

	@FindBy(linkText = "Zoom in")
    private WebElement lnkZoomIn;

	@FindBy(xpath = "//td[contains(@class,'popup_parking_content')]//div[contains(@class,'state')]")
    private WebElement lblParkingLotInfo;


	public TrafficPage(WebDriver driver) {
		log.info("Home Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(parkingLogo));
	}

	public TrafficPage clickFirstParkingLogo() {
		log.info("Clicking First Occurrence of Parking Logo Symbol");
		clickElement(driver, parkingLogo);
		log.info("Clicked First Parking Logo Symbol");

		return this;
	}

	public String getMessage() {
		clickZoomInParkingLotLogo();
		String msg = getElementText(driver, lblParkingLotInfo);
		log.info("Message : "+msg);

		return msg;
	}

	protected void clickZoomInParkingLotLogo() {
		try {
		    log.info("Verify Zoom In Link");
		    waitForSeconds(2);
		    clickElement(driver, lnkZoomIn);
		    log.info("Clicked Zoom In Link");
		    waitForSeconds(5);
		    clickElement(driver, parkingLogo);
		    log.info("Clicked First Parking Lot Logo after Zoom In Link");
		}catch(ElementNotVisibleException e) {
			log.info("Zoom In Link Visibility is not found");
		}catch(Exception e) {
			log.info("Console Error" + e.toString());
		}
	}

}