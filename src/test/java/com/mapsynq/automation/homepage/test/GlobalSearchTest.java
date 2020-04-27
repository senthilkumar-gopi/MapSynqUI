package com.mapsynq.automation.homepage.test;

import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;

public class GlobalSearchTest extends TestBase {

	public HomePage homePage;
	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}

	private String getGlobalSearchTestInput() {
		return config.getProperty("globalSearchTextInput").toString();
	}

	@Test
	public void verifyGloabalSearchResult() throws Exception {
		assertTrue("Problem in Search Result(s)", homePage.setTextGlobalSearchOption(getGlobalSearchTestInput()).clickSearchIcon().verifySearchResult());
	}
}