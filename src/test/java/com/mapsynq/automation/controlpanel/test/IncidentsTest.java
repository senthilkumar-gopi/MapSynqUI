package com.mapsynq.automation.controlpanel.test;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.controlpanel.IncidentsPage;

public class IncidentsTest extends TestBase {

	public HomePage homePage;
	public IncidentsPage incidentsPage;

	@BeforeClass
	public void setup()
	{
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyTrafficToolTipInfoFromControlPanel() throws Exception {
		incidentsPage = homePage.closeAdvPopUp().clickIncidentsFromControlPanel().clickFirstIncidentLogo();
		assertTrue("Problem in Verfying Incidents", incidentsPage.verifyIncidentPopUp());
	}

}