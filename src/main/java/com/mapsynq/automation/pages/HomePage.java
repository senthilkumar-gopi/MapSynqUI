package com.mapsynq.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mapsynq.automation.helper.UtilClass;
import com.mapsynq.automation.pages.controlpanel.CamerasPage;
import com.mapsynq.automation.pages.controlpanel.IncidentsPage;
import com.mapsynq.automation.pages.controlpanel.TollPage;
import com.mapsynq.automation.pages.controlpanel.TrafficPage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabDirectionsPage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveCamerasPage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveIncidentsPage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveTollsPage;
import com.mapsynq.automation.pages.profile.UserRegistrationPage;

public class HomePage extends UtilClass{

	private static Logger log = Logger.getLogger(HomePage.class);
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id = "txtGlobalSearch")
    private WebElement txtGlobalSearchBox;

	@FindBy(xpath = "//span[contains(@class,'search')]")
    private WebElement btnSearch;

	@FindBy(xpath = "//div[contains(@class,'ad_bar_collapse')]")
    private WebElement btnAdvToggleCollapse;
	
	@FindBy(xpath = "//div[contains(@class,'ad_bar_expand')]")
    private WebElement btnAdvToggleExpand;

	@FindBy(linkText = "Register")
    private WebElement lnkUserRegister;

	@FindBy(xpath = "//a[contains(@class,'directions_tab')]")
	private WebElement lnkDirections;

	@FindBy(xpath = "//a[contains(@class,'live_tab')]")
	private WebElement lnkLive;

	@FindBy(xpath = "//h2[contains(text(),'Incidents')]")
	private WebElement lnkLiveIncidents;

	@FindBy(xpath = "//h2[contains(text(),'Cameras')]")
	private WebElement lnkLiveCameras;

	@FindBy(xpath = "//h2[contains(text(),'Tolls')]")
	private WebElement lnkLiveTolls;

	@FindBy(xpath = "//img[contains(@id,'zoomin_innerImage')]")
	private WebElement mapZoomBtn;

	@FindBy(xpath = "(//div[@title='Parking'])[2]")
    private WebElement btnParking;

	@FindBy(xpath = "(//div[@title='Incidents/Alerts'])[2]")
    private WebElement btnIncidents;

	@FindBy(xpath = "(//div[@title='Traffic Camera'])[2]")
    private WebElement btnCameras;

	@FindBy(xpath = "(//div[@title='Toll'])[2]")
    private WebElement btnToll;
	
	@FindBy(xpath = "//div[@id='div_header']/a")
    private WebElement imgHome;


	public HomePage(WebDriver driver) {
		log.info("Home Page constructor is Invoked");
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
		PageFactory.initElements(driver, this);
		wait.until(ExpectedConditions.titleContains("mapSYNQ"));
	}

	public UserRegistrationPage clickRegisterLink() {
		log.info("Clicking User Register Link for the Application");
		clickElement(driver, lnkUserRegister);
		log.info("Clicked Register Link for the Application");

		return new UserRegistrationPage(driver);
	}

	public LeftTabDirectionsPage clickLeftTabDirections() {
		log.info("Clicking Left Tab Directions from Application");
		clickElement(driver, lnkDirections);
		log.info("Clicked Left Tab Directions from Application");

		return new LeftTabDirectionsPage(driver);
	}

	public HomePage clickLeftTabLive() {
		log.info("Clicking Left Tab Live Option from Application");
		clickElement(driver, lnkLive);
		log.info("Clicked Left Tab Live Option from Application");

		return this;
	}

	public LeftTabLiveIncidentsPage clickLeftTabLiveIncidents() {
		log.info("Clicking Left Tab Live Incidents from Application");
		clickElement(driver, lnkLiveIncidents);
		log.info("Clicked Left Tab Live Incidents from Application");

		return new LeftTabLiveIncidentsPage(driver);
	}

	public LeftTabLiveCamerasPage clickLeftTabLiveCameras() {
		log.info("Clicking Left Tab Live Cameras from Application");
		clickElement(driver, lnkLiveCameras);
		log.info("Clicked Left Tab Live Cameras from Application");

		return new LeftTabLiveCamerasPage(driver);
	}

	public LeftTabLiveTollsPage clickLeftTabLiveTolls() {
		log.info("Clicking Left Tab Live Tolls from Application");
		clickElement(driver, lnkLiveTolls);
		log.info("Clicked Left Tab Live Tolls from Application");

		return new LeftTabLiveTollsPage(driver);
	}

	public TrafficPage clickParkingFromControlPanel() {
		prerequisite();
		log.info("Clicking Parking Option from Control Panel Area");
		clickElement(driver, btnParking);
		log.info("Clicked Parking Option");

		return new TrafficPage(driver);
	}

	public CamerasPage clickCamerasFromControlPanel() {
		prerequisite();
		log.info("Clicking Cameras Option from Control Panel Area");
		clickElement(driver, btnCameras);
		log.info("Clicked Cameras Option");

		return new CamerasPage(driver);
	}

	public TollPage clickTollFromControlPanel() {
		prerequisite();
		log.info("Clicking Toll Option from Control Panel Area");
		clickElement(driver, btnToll);
		log.info("Clicked Toll Option");

		return new TollPage(driver);
	}

	public IncidentsPage clickIncidentsFromControlPanel() {
		prerequisite();
		log.info("Clicking Incidents Option from Control Panel Area");
		clickElement(driver, btnIncidents);
		log.info("Clicked Incidents Option");

		return new IncidentsPage(driver);
	}

	public HomePage setTextGlobalSearchOption(String searchInputText) {
		setText(driver, txtGlobalSearchBox, searchInputText);
		log.info("Entered First Name : " + searchInputText);

		return this;		
	}
	
	public HomePage clickSearchIcon() {
		clickElement(driver, btnSearch);
		log.info("Clicked Search Icon");

		return this;		
	}

	public boolean verifySearchResult() {
		log.info("Gloabl Search Result is not working in Application. Making result as False");
		return false;
	}

	protected void prerequisite() {
		log.info("Un-Select Incidents/Alerts Option from Control Panel Area");
		clickElement(driver, btnIncidents);
	}

	public HomePage closeAdvPopUp() {
		try {
			log.info("Check for Adv. Pop-Up & Close if found");
			wait.until(ExpectedConditions.visibilityOf(btnAdvToggleCollapse));
			wait.until(ExpectedConditions.elementToBeClickable(btnAdvToggleCollapse));
			waitForSeconds(2);
			clickElement(driver, btnAdvToggleCollapse);
			log.info("Closed Pop-up");
		}catch(ElementNotVisibleException e) {
			log.info("Adv. Pop-Up Visibility is not found");
		}catch(Exception e) {
		    log.info(e.toString());
		}
		waitForElementToAppear(driver, btnAdvToggleExpand);
		
		return this;
	}
	
	public HomePage clickHomeIcon() {
		imgHome.click();
		log.info("Clicked on Home Image Icon");
		
		return this;
	}
}