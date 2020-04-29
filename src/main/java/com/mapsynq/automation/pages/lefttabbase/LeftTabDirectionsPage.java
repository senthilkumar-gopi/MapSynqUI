package com.mapsynq.automation.pages.lefttabbase;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class LeftTabDirectionsPage extends UtilClass{

	private static Logger log = Logger.getLogger(LeftTabDirectionsPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "poi_from")
    private WebElement txtSourceSearch;

	@FindBy(id = "poi_to")
    private WebElement txtDestinationSearch;

	@FindBy(id = "also_traffic")
    private WebElement chkTrafficAware;

	@FindBy(id = "also_erp")
    private WebElement chkTollAware;

	@FindBy(id = "also_fastest")
    private WebElement chkFastestAware;

	@FindBy(id = "also_shortest")
    private WebElement chkShortestAware;

	@FindBy(id = "get_direction")
    private WebElement btnGetDirections;


	public LeftTabDirectionsPage(WebDriver driver) {
		log.info("Home Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(txtSourceSearch));
	}

	public LeftTabDirectionsPage setSourcePlace(String firstName) {
		setText(driver, txtSourceSearch, firstName);
		log.info("Entered Source Place/Location for Search : " + firstName);

		return this;
	}

	public LeftTabDirectionsPage setDestinationPlace(String firstName) {
		setText(driver, txtDestinationSearch, firstName);
		log.info("Entered Destination Place/Location for Search : " + firstName);

		return this;
	}

	public LeftTabDirectionsPage clickchkBoxTollAware() {
		log.info("Click Check Box for Toll Aware");
		clickElement(driver, chkTollAware);
		log.info("Clicked Check Box of Traffic Aware");

		return this;
	}

	public LeftTabDirectionsPage clickChkBoxFastestAware() {
		log.info("Click Check Box for Fastest Traffic Aware");
		clickElement(driver, chkFastestAware);
		log.info("Clicked Check Box of Fastest Aware");

		return this;
	}

	public LeftTabDirectionsPage clickChkBoxShortestAware() {
		log.info("Click Check Box for Shortest Aware");
		clickElement(driver, chkShortestAware);
		log.info("Clicked Check Box for Shortest Aware");

		return this;
	}

	public LeftTabDirectionsPage clickbtnGetDirections() {
		log.info("Click Get Directions Button");
		clickElement(driver, btnGetDirections);
		log.info("Clicked Get Directions Button");

		return this;
	}

	public boolean verifySearchResult() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String getAlertText = alert.getText();
        alert.accept();

        log.info("Alert Texts : " + getAlertText);
        log.info("Directions Search Result is not working in Application. Making result as False");

        return false;
	}
}