package com.mapsynq.automation.controlpanel.test;

import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.controlpanel.TollPage;

public class TollTest extends TestBase {

	public HomePage homePage;
	public TollPage tollPage;

	@BeforeClass
	public void setup()
	{
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyTrafficToolTipInfoFromControlPanel() throws Exception {
		tollPage = homePage.closeAdvPopUp().clickTollFromControlPanel().clickFirstTollLogo();
		assertTrue("Problem in Verfying Toll", tollPage.verifyTollPopUp());
	}

}