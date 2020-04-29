package com.mapsynq.automation.lefttabbase.test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveIncidentsPage;

public class LiveIncidentsTest extends TestBase {

	public HomePage homePage;
	public LeftTabLiveIncidentsPage leftTabLiveIncidentsPage;

	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}
	
	private String getSearchTestInput() {
		return config.getProperty("searchText").toString();
	}

	@Test
	public void verifyLiveIncidents() {
		leftTabLiveIncidentsPage = homePage.closeAdvPopUp().clickLeftTabLive().clickLeftTabLiveIncidents()
						         .selectFirstLocationInfo();
		assertTrue("Problem in Getting Live Toll Info", leftTabLiveIncidentsPage.verifyIncidentPopUp());
	}
	
	@Test
	public void verifySearchResult() {
		homePage.clickHomeIcon();
		leftTabLiveIncidentsPage = homePage.clickLeftTabLive().clickLeftTabLiveIncidents()
						         .setSearchText(getSearchTestInput());
		assertTrue("Search Results not matching", leftTabLiveIncidentsPage.verifySearchResults(getSearchTestInput()));
	}
	
	@Test
	public void verifyNoSearchResult() {
		homePage.clickHomeIcon();
		leftTabLiveIncidentsPage = homePage.clickLeftTabLive().clickLeftTabLiveIncidents()
						         .setSearchText("Not valid search");
		assertFalse("Search Results not matching", leftTabLiveIncidentsPage.verifySearchResults("Not valid search"));
		assertEquals("Mismatch in No search results text", "No incidents found for you query.", leftTabLiveIncidentsPage.getMessage());
	}
}
