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

public class IncidentsPage extends UtilClass{

	private static Logger log = Logger.getLogger(IncidentsPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "(//*[name()='image' and @stroke='#000000'])[1]")
    private WebElement incidentsLogo;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(xpath = "//table[contains(@class,'chart_view')]")
    private WebElement lblIncidentsPopUp;
	
	@FindBy(linkText = "Zoom in")
    private WebElement lnkZoomIn;


	public IncidentsPage(WebDriver driver) {
		log.info("Incidents Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(incidentsLogo));
	}

	public IncidentsPage clickFirstIncidentLogo() {
		log.info("Clicking First Incident Logo Symbol");
		clickElement(driver, incidentsLogo);
		log.info("Clicked First Incident Logo Symbol");

		return this;
	}

	public boolean verifyIncidentPopUp() {
		clickZoomInIncidentLotLogo();
		String popupTitleHeader = headerPopUpTitle.getText();
		
		return (popupTitleHeader.contains("RoadWork") || popupTitleHeader.contains("Accident") || popupTitleHeader.contains("Vehicle Breakdown") 
				|| popupTitleHeader.contains("Heavy Traffic"));
	}
	
	protected void clickZoomInIncidentLotLogo() {
		try {
			log.info("Verify Zoom In Link");
			clickElement(driver, lnkZoomIn);
			log.info("Clicked Zoom In Link");
			waitForSeconds(5);
			clickElement(driver, incidentsLogo);
			log.info("Clicked First Incident Logo after Zoom In Link");
		}catch(ElementNotVisibleException e) {
			log.info("Zoom In Link Visibility is not found");
		}catch(Exception e) {
			log.info("Console Error" + e.toString());
		}
	}
}