package configs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverBase {
	public static final String BASE_URL = System.getProperty("selenium.baseURL", "http://automationpractice.com/");
	public static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
	private static final String BROWSER = System.getProperty("selenium.browser", "chrome");
	private static final String HOST = System.getProperty("selenium.host", "localhost");
	private static final String HOST_URL = System.getProperty("selenium.hostURL", "http://localhost:4444/wd/hub");
	private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	public static DriverBase get() {
		return DriverBaseHolder.HOLDER_INSTANCE;
	}

	private static class DriverBaseHolder {
		static final DriverBase HOLDER_INSTANCE = new DriverBase();
	}

	public WebDriver getDriver(String testName) {
		if (driverThread.get() == null) {
			WebDriver driver;
			switch (HOST) {
				case "zalenium":
					if (BROWSER.equals("firefox")) {
						driver = initRemoteDriver(DesiredCapabilities.firefox());
					} else {
						driver = initRemoteDriver(DesiredCapabilities.chrome());
					}
					break;
				case "cloud":
					if (BROWSER.equals("firefox")) {
						DesiredCapabilities caps = new DesiredCapabilities();
						caps.setCapability("browser", "Firefox");
						caps.setCapability("browser_version", "64.0");
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");
						caps.setCapability("resolution", "1920x1080");
						caps.setCapability("name", testName);
						caps.setCapability("project", "automationpractice");
						driver = initRemoteDriver(caps);
					} else {
						DesiredCapabilities caps = new DesiredCapabilities();
						caps.setCapability("browser", "Chrome");
						caps.setCapability("browser_version", "71.0");
						caps.setCapability("os", "Windows");
						caps.setCapability("os_version", "10");
						caps.setCapability("resolution", "1920x1080");
						caps.setCapability("name", testName);
						caps.setCapability("project", "automationpractice");
						driver = initRemoteDriver(caps);
					}
					break;
				default:
					if (BROWSER.equals("firefox")) {
						System.setProperty("webdriver.gecko.driver",
								"./drivers/geckodriver-mac-64bit");
						driver = new FirefoxDriver();
					} else {
						System.setProperty("webdriver.chrome.driver",
								"./drivers/chromedriver-mac-64bit");
						driver = new ChromeDriver();
					}
					break;
			}
			driverThread.set(driver);
			getDriver().manage().window().maximize();

		}
		return driverThread.get();
	}

	public WebDriver getDriver() {
		return getDriver("Undefined test method");
	}

	private RemoteWebDriver initRemoteDriver(DesiredCapabilities capability) {
		RemoteWebDriver remoteDriver = null;
		try {
			remoteDriver = new RemoteWebDriver(new URL(HOST_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return remoteDriver;
	}

	public void quitDriver(WebDriver driver) {
		driver.quit();
		driverThread.remove();
	}

	public String getSession(WebDriver driver) {
		return String.valueOf(((RemoteWebDriver) driver).getSessionId());
	}
}