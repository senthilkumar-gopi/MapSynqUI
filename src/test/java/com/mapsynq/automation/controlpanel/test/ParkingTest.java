package com.mapsynq.automation.controlpanel.test;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.controlpanel.TrafficPage;

public class ParkingTest extends TestBase {

	public HomePage homePage;
	public TrafficPage trafficPage;

	@BeforeClass
	public void setup()
	{
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyTrafficToolTipInfoFromControlPanel() throws Exception {
		trafficPage = homePage.closeAdvPopUp().clickParkingFromControlPanel().clickFirstParkingLogo();
		assertTrue("Problem in Verfying Parking Lot", trafficPage.getMessage().contains("Available lots"));
	}

}