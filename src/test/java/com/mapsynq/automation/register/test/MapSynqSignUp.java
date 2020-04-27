package com.mapsynq.automation.register.test;

import static org.testng.AssertJUnit.assertTrue;
import java.util.Properties;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mapsynq.automation.helper.TestBase;
import com.mapsynq.automation.pages.HomePage;
import com.mapsynq.automation.pages.profile.UserRegistrationPage;

public class MapSynqSignUp extends TestBase {

	public HomePage homePage;
	public UserRegistrationPage userRegistrationPage;

	Properties config = new Properties();

	@BeforeClass
	public void setup()
	{
		config = getProperties();
		homePage = new HomePage(driver);
	}

	private String getFirstname() {
		return config.getProperty("firstName").toString();
	}

	private String getLastName() {
		return config.getProperty("lastName").toString();
	}

	private String getCountry() {
		return config.getProperty("country").toString();
	}
	
	private String getDOB() {
		return config.getProperty("dob").toString();
	}
	
	private String getEmail() {
		return config.getProperty("email").toString();
	}
	
	private String getPassword() {
		return config.getProperty("password").toString();
	}

	@Test
	public void accountSignUp() throws Exception {
		userRegistrationPage = homePage.clickRegisterLink();
		userRegistrationPage.setFirstName(getFirstname()).setLastName(getLastName()).selectCountry(getCountry()).selectGender().setDOB(getDOB())
							.setEmail(getEmail()).setPassword(getPassword()).setConfirmPassword(getPassword()).clickAgreeCheckBox().clickSubmitBtn();
		assertTrue("Problem in Creating Account Registration", userRegistrationPage.getMessage().contains("Incorrect word verification! please try again..."));
	}

}
