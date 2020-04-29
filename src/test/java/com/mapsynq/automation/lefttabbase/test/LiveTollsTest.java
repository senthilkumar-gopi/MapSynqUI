package com.mapsynq.automation.lefttabbase.test;

import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabLiveTollsPage;

public class LiveTollsTest extends TestBase {

	public HomePage homePage;
	public LeftTabLiveTollsPage leftTabLiveTollsPage;

	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}

	@Test
	public void verifyLiveTolls() throws Exception {
		leftTabLiveTollsPage = homePage.closeAdvPopUp().clickLeftTabLive().clickLeftTabLiveTolls()
						         .selectFirstLocationLink();
		assertTrue("Problem in Getting Live Toll Info", leftTabLiveTollsPage.verifyTollPopUp());
	}

}
