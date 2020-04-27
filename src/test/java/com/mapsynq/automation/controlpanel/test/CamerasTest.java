package com.mapsynq.automation.controlpanel.test;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.controlpanel.CamerasPage;

public class CamerasTest extends TestBase {

	public HomePage homePage;
	public CamerasPage camerasPage;

	@BeforeClass
	public void setup()
	{
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyTrafficToolTipInfoFromControlPanel() throws Exception {
		camerasPage = homePage.closeAdvPopUp().clickCamerasFromControlPanel().clickFirstCamerasLogo();
		assertTrue("Problem in Verfying Traffic Cameras", camerasPage.verifyTrafficCamerasPopUp());
	}

}