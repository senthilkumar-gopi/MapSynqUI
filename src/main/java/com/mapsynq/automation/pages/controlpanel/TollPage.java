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

public class TollPage extends UtilClass{

	private static Logger log = Logger.getLogger(TollPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "(//*[name()='image' and @stroke='#000000'])[1]")
    private WebElement tollLogo;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(xpath = "//table[contains(@class,'chart_view')]")
    private WebElement tableTollChargeChart;
	
	@FindBy(xpath = "(//iframe[contains(@id,'myDropdownList')])[1]")
    private WebElement frameLoc;
	
	@FindBy(linkText = "Zoom in")
    private WebElement lnkZoomIn;
	
	@FindBy(xpath = "//img[contains(@id,'zoomin_innerImage')]")
	private WebElement mapZoomBtn;
	

	public TollPage(WebDriver driver) {
		log.info("Toll Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(tollLogo));
	}

	public TollPage clickFirstTollLogo() {
		clickElement(driver, mapZoomBtn);
		log.info("Clicked Zoom In map");
		log.info("Clicking First Occurrence of Toll Logo Symbol");
		clickElement(driver, tollLogo);
		log.info("Clicked First Toll Logo Symbol");

		return this;
	}

	public boolean verifyTollPopUp() {
		if(isElementPresent(lnkZoomIn))
			clickZoomInTollLogo();
		boolean popupTitleHeader = headerPopUpTitle.getText().contains("Toll");
		driver.switchTo().frame(frameLoc);
		boolean tableTollChargePopUp = tableTollChargeChart.isDisplayed();
		driver.switchTo().defaultContent();
		
		return popupTitleHeader && tableTollChargePopUp;
	}
	
	protected void clickZoomInTollLogo() {
		try {
			log.info("Verify Zoom In Link");
			clickElement(driver, lnkZoomIn);
			log.info("Clicked Zoom In Link");
			waitForSeconds(5);
			clickElement(driver, tollLogo);
			log.info("Clicked First Toll Logo after Zoom In Link");
		}catch(ElementNotVisibleException e) {
			log.info("Zoom In Link Visibility is not found");
		}catch(Exception e) {
			log.info("Console Error" + e.toString());
		}
	}
}