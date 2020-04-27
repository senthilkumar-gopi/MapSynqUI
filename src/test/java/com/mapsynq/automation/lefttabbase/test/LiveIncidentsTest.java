package com.mapsynq.automation.lefttabbase.test;

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

	@Test
	public void verifyLiveTollSearchResult() throws Exception {
		leftTabLiveIncidentsPage = homePage.closeAdvPopUp().clickLeftTabLive().clickLeftTabLiveIncidents()
						         .selectFirstLocationInfo();
		assertTrue("Problem in Getting Live Toll Info", leftTabLiveIncidentsPage.verifyIncidentPopUp());
	}

}
