package com.mapsynq.automation.helper;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import com.mapsynq.automation.listeners.WebDriverListener;

public class BrowserManager extends TestBase {
	
	private WebDriver basedriver;
    public WebDriver webdriver;
    private static Logger log = Logger.getLogger(BrowserManager.class);
    String linux = "linux";
    String chromeDriverPath = "webdriver.chrome.driver";
    String geckoDriverPath = "webdriver.gecko.driver";
    String ieDriverPath = "webdriver.ie.driver";
    String platformName = "platformName";
    String browserVersion = "browserVersion";
    String sauceoptions = "sauce:options";
    

	public WebDriver getWebDriver()
	{
		  setEnvProperties();
		  String mode = defaultProps.getProperty("mode");
		  String os = defaultProps.getProperty("os");
		  String browser = defaultProps.getProperty("browser");
		  String cloudusername = defaultProps.getProperty("cloudusername");
		  String cloudaccesskey = defaultProps.getProperty("cloudaccesskey");
		  String version = defaultProps.getProperty("version");
		  String headless = defaultProps.getProperty("headless");
		  
		  log.info("Current Execution platform is : " + mode);
		  log.info("Current OS is : " + os);
		  log.info("Current Browser is : " + browser);
		  log.info("Headless mode : " + headless);

		  if (mode.equalsIgnoreCase("local")) {
			  if(browser.equalsIgnoreCase("chrome"))
			  {
				  ChromeOptions options=new ChromeOptions();
				  options.addArguments("--no-sandbox");
				  options.addArguments("disable-infobars");
				  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  options.addArguments("disable-infobars");
				  if(headless.equalsIgnoreCase("yes")) {
				      options.addArguments("--headless","--disable-gpu","--test-type","--ignore-certificate-errors");
				      options.addArguments("window-size=1200,1100"); 
				  }
				  if(os.toLowerCase().trim().contains(linux))
				  {
					  options.addArguments("--disable-extensions");
					  options.addArguments("--disable-gpu");
					  options.addArguments("--disable-dev-shm-usage");
					  options.addArguments("--headless");
					  System.setProperty(chromeDriverPath, "src/main/resources/driver/linux/chromedriver");
				  }
				  else if(os.toLowerCase().trim().contains("mac"))
					  System.setProperty(chromeDriverPath, "src/main/resources/driver/mac/chromedriver");
				  else
					  System.setProperty(chromeDriverPath, "src/main/resources/driver/windows/chromedriver.exe");
				  basedriver = new ChromeDriver(options);
			  }
			  else if(browser.equalsIgnoreCase("firefox"))
			  {
				  FirefoxOptions options = new FirefoxOptions();
				  options.setAcceptInsecureCerts(true);
				  DesiredCapabilities cap=new DesiredCapabilities();
				  cap.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
				  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  if(os.toLowerCase().trim().contains(linux))
					  System.setProperty(geckoDriverPath, "src/main/resources/driver/linux/geckodriver");
				  else if(os.toLowerCase().trim().contains("mac"))
					  System.setProperty(geckoDriverPath, "src/main/resources/driver/mac/geckodriver");
				  else
					  System.setProperty(geckoDriverPath, "src/main/resources/driver/windows/geckodriver.exe");
				  basedriver = new FirefoxDriver(options);
			  }
			  else if(browser.equalsIgnoreCase("internet explorer"))
			  {
				  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				  capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
				  if(os.toLowerCase().trim().contains(linux))
					  System.setProperty(ieDriverPath, "src/main/resources/driver/linux/IEDriverServer");
				  else
					  System.setProperty(ieDriverPath, "src/main/resources/driver/windows/IEDriverServer.exe");
				  basedriver = new InternetExplorerDriver(options);
			  }
	      }
		  else if(mode.equalsIgnoreCase("saucelabs"))
		  {
		      String sauceRemoteUrl = "https://ondemand.saucelabs.com/wd/hub";
		      URL sauceurl = null;
		      try {
		    	  sauceurl = new URL(sauceRemoteUrl);
		      } catch (MalformedURLException e) {
				  log.info(e.toString());
		      }
			  MutableCapabilities sauceOptions = new MutableCapabilities();
			  sauceOptions.setCapability("username", cloudusername);
		      sauceOptions.setCapability("accessKey", cloudaccesskey);
		      sauceOptions.setCapability("seleniumVersion", "3.141.59");
		      sauceOptions.setCapability("screenResolution", "1280x1024");
		        
			  if(browser.equalsIgnoreCase("chrome")) {
				  ChromeOptions browserOptions = new ChromeOptions();
				  browserOptions.setExperimentalOption("w3c", true);
				  browserOptions.setCapability(platformName, os);
				  browserOptions.setCapability(browserVersion, version);
				  browserOptions.setCapability(sauceoptions, sauceOptions);
				  basedriver = new RemoteWebDriver(sauceurl, browserOptions);
			  }
			  else if(browser.equalsIgnoreCase("firefox")) {
				  FirefoxOptions browserOptions = new FirefoxOptions();
				  browserOptions.setCapability(platformName, os);
				  browserOptions.setCapability(browserVersion, version);
				  browserOptions.setCapability(sauceoptions, sauceOptions);
				  basedriver = new RemoteWebDriver(sauceurl, browserOptions);
			  }
			  else if(browser.equalsIgnoreCase("internet explorer"))
			  {
				  InternetExplorerOptions browserOptions = new InternetExplorerOptions();
				  browserOptions.setCapability(platformName, os);
				  browserOptions.setCapability(browserVersion, version);
				  browserOptions.setCapability(sauceoptions, sauceOptions);
				  basedriver = new RemoteWebDriver(sauceurl, browserOptions);
			  }
			  else if(browser.equalsIgnoreCase("safari"))
			  {
				  sauceOptions.setCapability("screenResolution", "1280x960");
				  SafariOptions browserOptions = new SafariOptions();
				  browserOptions.setCapability(platformName, os);
				  browserOptions.setCapability(browserVersion, version);
				  browserOptions.setCapability(sauceoptions, sauceOptions);
				  basedriver = new RemoteWebDriver(sauceurl, browserOptions);
			  }
		      RemoteWebDriver remote = (RemoteWebDriver) basedriver;

			  String sessionId = remote.getSessionId().toString();
			  if (sessionId == null) {
				  log.error("Unable to generate an auth key without sessionId: " + sessionId);
				  throw new IllegalStateException();
			  }
		  }
		  else
			  throw new IllegalStateException("Provider is not matching with local/saucelabs");
		  
		  EventFiringWebDriver efwd=new EventFiringWebDriver(basedriver);
		  WebDriverListener eventListener=new WebDriverListener(basedriver);
		  efwd.register(eventListener);
		  webdriver=basedriver;
		
		  webdriver.manage().window().maximize();
		  webdriver.manage().deleteAllCookies();
		  
		return webdriver;
	}
}