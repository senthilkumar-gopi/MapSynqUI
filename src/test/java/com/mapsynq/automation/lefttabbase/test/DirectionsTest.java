package com.mapsynq.automation.lefttabbase.test;

import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.lefttabbase.LeftTabDirectionsPage;

public class DirectionsTest extends TestBase {

	public HomePage homePage;
	public LeftTabDirectionsPage leftTabDirectionsPage;

	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}

	private String getSourceSearchTestInput() {
		return config.getProperty("source").toString();
	}

	private String getDestinationSearchTestInput() {
		return config.getProperty("destination").toString();
	}

	@Test
	public void verifyDirectionsSearchResult() throws Exception {
		leftTabDirectionsPage = homePage.clickLeftTabDirections().setSourcePlace(getSourceSearchTestInput())
						.setDestinationPlace(getDestinationSearchTestInput())//.clickChkBoxTrafficAware()
						.clickchkBoxTollAware().clickChkBoxFastestAware().clickChkBoxShortestAware().clickbtnGetDirections();
		assertTrue("Problem in Getting Direction Search Result(s)", leftTabDirectionsPage.verifySearchResult());
	}

}
