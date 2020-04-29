package com.mapsynq.automation.lefttabbase.test;

import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveCamerasPage;

public class LiveCamerasTest extends TestBase {

	public HomePage homePage;
	public LeftTabLiveCamerasPage leftTabLiveCamerasPage;

	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyLiveCamerasSearchResult() throws Exception {
		leftTabLiveCamerasPage = homePage.closeAdvPopUp().clickLeftTabLive().clickLeftTabLiveCameras()
						         .selectFirstLocationLink();
		assertTrue("Problem in Getting Live Cameras Info", leftTabLiveCamerasPage.verifyTrafficCamerasPopUp());
	}

}
