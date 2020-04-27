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
    public static WebDriver driver;
    private static Logger Log = Logger.getLogger(BrowserManager.class);

	public WebDriver getDriver() throws Exception
	{
		  setEnvProperties();
		  String mode = defaultProps.getProperty("mode");
		  String os = defaultProps.getProperty("os");
		  String browser = defaultProps.getProperty("browser");
		  String cloudusername = defaultProps.getProperty("cloudusername");
		  String cloudaccesskey = defaultProps.getProperty("cloudaccesskey");
		  String version = defaultProps.getProperty("version");
		  
		  Log.info("Current Execution platform is : " + mode);

		  if (mode.trim().toLowerCase().equals("local")) {
			  if(browser.trim().toLowerCase().equals("chrome"))
			  {
				  ChromeOptions options=new ChromeOptions();
				  options.addArguments("--no-sandbox");
				  options.addArguments("disable-infobars");
				  options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  options.addArguments("disable-infobars");
				  if(os.toLowerCase().trim().contains("linux"))
				  {
					  options.addArguments("--disable-extensions");
					  options.addArguments("--disable-gpu");
					  options.addArguments("--disable-dev-shm-usage");
					  options.addArguments("--headless");
					  System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/linux/chromedriver");
				  }
				  else if(os.toLowerCase().trim().contains("mac"))
					  System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/mac/chromedriver");
				  else
					  System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/windows/chromedriver.exe");
				  basedriver = new ChromeDriver(options);
			  }
			  else if(browser.trim().toLowerCase().equals("firefox"))
			  {
				  FirefoxOptions options = new FirefoxOptions();
				  options.setAcceptInsecureCerts(true);
				  DesiredCapabilities cap=new DesiredCapabilities();
				  cap.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
				  cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  if(os.toLowerCase().trim().contains("linux"))
					  System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/linux/geckodriver");
				  else if(os.toLowerCase().trim().contains("mac"))
					  System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/mac/geckodriver");
				  else
					  System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/windows/geckodriver.exe");
				  basedriver = new FirefoxDriver(options);
			  }
			  else if(browser.trim().toLowerCase().equals("internet explorer"))
			  {
				  DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				  capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				  capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				  capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				  InternetExplorerOptions options = new InternetExplorerOptions(capabilities);
				  if(os.toLowerCase().trim().contains("linux"))
					  System.setProperty("webdriver.ie.driver", "src/main/resources/driver/linux/IEDriverServer");
				  else
					  System.setProperty("webdriver.ie.driver", "src/main/resources/driver/windows/IEDriverServer.exe");
				  basedriver = new InternetExplorerDriver(options);
			  }
	      }
		  else if(mode.trim().toLowerCase().equals("saucelabs"))
		  {
		      String SAUCE_REMOTE_URL = "https://ondemand.saucelabs.com/wd/hub";
			  MutableCapabilities sauceOptions = new MutableCapabilities();
			  sauceOptions.setCapability("username", cloudusername);
		      sauceOptions.setCapability("accessKey", cloudaccesskey);
		      sauceOptions.setCapability("seleniumVersion", "3.141.59");
		      sauceOptions.setCapability("screenResolution", "1280x1024");
		        
			  if(browser.trim().toLowerCase().equals("chrome")) {
				  ChromeOptions browserOptions = new ChromeOptions();
				  browserOptions.setExperimentalOption("w3c", true);
				  browserOptions.setCapability("platformName", os);
				  browserOptions.setCapability("browserVersion", version);
				  browserOptions.setCapability("sauce:options", sauceOptions);
				  basedriver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), browserOptions);
			  }
			  else if(browser.trim().toLowerCase().equals("firefox")) {
				  ChromeOptions browserOptions = new ChromeOptions();
				  browserOptions.setExperimentalOption("w3c", true);
				  browserOptions.setCapability("platformName", os);
				  browserOptions.setCapability("browserVersion", version);
				  browserOptions.setCapability("sauce:options", sauceOptions);
				  basedriver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), browserOptions);
			  }
			  else if(browser.trim().toLowerCase().equals("internet explorer"))
			  {
				  InternetExplorerOptions browserOptions = new InternetExplorerOptions();
				  browserOptions.setCapability("platformName", os);
				  browserOptions.setCapability("browserVersion", version);
				  browserOptions.setCapability("sauce:options", sauceOptions);
				  basedriver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), browserOptions);
			  }
			  else if(browser.trim().toLowerCase().equals("safari"))
			  {
				  sauceOptions.setCapability("screenResolution", "1280x960");
				  SafariOptions browserOptions = new SafariOptions();
				  browserOptions.setCapability("platformName", os);
				  browserOptions.setCapability("browserVersion", version);
				  browserOptions.setCapability("sauce:options", sauceOptions);
				  basedriver = new RemoteWebDriver(new URL(SAUCE_REMOTE_URL), browserOptions);
			  }
		      RemoteWebDriver remote = (RemoteWebDriver) basedriver;

			  String sessionId = remote.getSessionId().toString();
			  System.out.println(sessionId);
			  if (sessionId == null) {
					Log.error("Unable to generate an auth key without sessionId: " + sessionId);
					throw new Exception();
			  }
		  }
		  else
			  throw new Exception("Provider is not matching with local/saucelabs");
		  
		  EventFiringWebDriver efwd=new EventFiringWebDriver(basedriver);
		  WebDriverListener eventListener=new WebDriverListener(basedriver);
		  efwd.register(eventListener);
		  driver=basedriver;
		
		  driver.manage().window().maximize();
		  driver.manage().deleteAllCookies();
		  
		return driver;
	}
	
    public static WebDriver getWebDriver() throws MalformedURLException, InterruptedException {	
		if (driver != null) {
			Log.info("WebDriver: " + driver);			 
			return driver;
		}	
		else {
			return null;
		}
	}
}