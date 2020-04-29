package com.mapsynq.automation.pages.lefttabbase;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;

public class LeftTabLiveIncidentsPage extends UtilClass{

	private static Logger log = Logger.getLogger(LeftTabLiveIncidentsPage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "txtSearchIncidentsingapore")
    private WebElement txtSearchLocation;

	@FindBy(xpath = "(//ul[@id='search_incident_singapore']//li)[1]")
	private WebElement lnkFirstIncidentLocationName;

	@FindBy(xpath = "(//li//a//div[@class='item_time'])[1]")
	private WebElement txtFirstIncidentTimeInfo;

	@FindBy(xpath = "(//li//a//div[@class='item_content'])[1]")
	private WebElement txtFirstIncidentLocationInfo;

	@FindBy(xpath = "//div[@class='popuptitle']")
    private WebElement headerPopUpTitle;

	@FindBy(id = "popup_contentDiv")
    private WebElement lblTextInfo;
	
	@FindBy(xpath = "//ul/li/a")
	private List<WebElement> listSearchResults;
	
	@FindBy(xpath = "//div[@id='incidentsingapore']/div[contains(@class,'no_result')]")
    private WebElement txtNoSearch;
	


	public LeftTabLiveIncidentsPage(WebDriver driver) {
		log.info("LeftTabLiveIncidentsPage constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.visibilityOf(txtSearchLocation));
	}

	public LeftTabLiveIncidentsPage selectFirstLocationInfo() {
		log.info("Click First Link Location of Incident");
		clickElement(driver, lnkFirstIncidentLocationName);

		return this;
	}

	public boolean verifyIncidentPopUp() {
		String getTextFromFirstIncidentList = txtFirstIncidentLocationInfo.getText().trim();
		String popupTitleHeader = headerPopUpTitle.getText().trim();
		String getTextInsidePopUp = lblTextInfo.getText().trim();
		log.info("Text of First Link Location of Incident" + getTextFromFirstIncidentList);
		log.info("Text From Pop-Up Header "+ popupTitleHeader);
		String[] getTextForPopUpHeader = getTextFromFirstIncidentList.split(" ");

		return getTextInsidePopUp.contains(getTextFromFirstIncidentList) && 
			(getTextForPopUpHeader[0].toUpperCase().trim().contains(popupTitleHeader.toUpperCase().trim()));
	}
	
	public LeftTabLiveIncidentsPage setSearchText(String search) {
		setText(driver, txtSearchLocation, search);
		log.info("Entered Search text field as " + search);
		waitForSeconds(2);

		return this;
	}
	
	public boolean verifySearchResults(String search) {
		if(listSearchResults.size()>0) {
			for(WebElement element : listSearchResults) {
				if(element.getText().toLowerCase().contains(search.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getMessage() {
		return txtNoSearch.getText().trim();
	}
}