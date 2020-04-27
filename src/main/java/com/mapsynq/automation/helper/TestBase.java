package com.mapsynq.automation.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import com.mapsynq.automation.listeners.ListenerTest;
import org.uncommons.reportng.*;

	@Listeners({ListenerTest.class,HTMLReporter.class,JUnitXMLReporter.class})
	public class TestBase
	{
	    private static Logger log = Logger.getLogger(TestBase.class);
	    public WebDriver driver;
	    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
  
	    Properties defaultProps = new Properties();

	  @BeforeTest
	  public void createDriver() throws Exception {
		  setEnvProperties();
		  System.setProperty(ESCAPE_PROPERTY, "false");
		  BrowserManager browser=new BrowserManager();
		  driver=browser.getDriver();
		  String url = defaultProps.getProperty("url");
		  log.info("Opening the Url - "+ url);
		  driver.get(url);
		  driver.navigate().refresh();
	  }

	 protected void setEnvProperties()  {
			String workingDir = System.getProperty("user.dir");
			String defaultConfigPath = workingDir + "/src/main/resources/configfile/config.properties";
			log.info("defaultConfigPath"+defaultConfigPath);
			try {
				defaultProps.load(new FileInputStream(defaultConfigPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
 
	 protected Properties getDefaultConfig() {	  
		  return defaultProps;
	 }

	 public WebDriver getDriver() throws MalformedURLException, Exception {
		  return driver;
	 }
	 
	 protected Properties getProperties()  {
		 Properties defaultProps = new Properties();
		 String workingDir = System.getProperty("user.dir");
		 String defaultConfigPath = workingDir + "/src/test/resources/configfile/testdataconfig.properties";
		 log.info("Testdata ConfigPath"+defaultConfigPath);
		 try {
		 	defaultProps.load(new FileInputStream(defaultConfigPath));
		 } catch (FileNotFoundException e) {
		 	e.printStackTrace();
		 } catch (IOException e) {
			e.printStackTrace();
		 }
		return defaultProps;
	 }
	  
	 @AfterTest(alwaysRun=true)
	 public void closeDriver(ITestContext testcontext) {
		if(driver!=null)
		{
			try {
				driver.quit();
			}catch(WebDriverException e)
			{
				log.info("**********CAUGHT EXCEPTION IN DRIVER TEAR DOWN**********");
				log.info(e);
			  }
		  }
	}
}
