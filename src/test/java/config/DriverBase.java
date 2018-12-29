//package config;
//
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//public class DriverBase {
//	private static final String BROWSER = System.getProperty("selenium.browser", "chrome");
//	private static final String REMOTE = System.getProperty("selenium.remote", "false");
//	public static final String BASE_URL = System.getProperty("selenium.baseURL", "http://automationpractice.com/");
//	public static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
//
//	public static WebDriver get(WebDriver driver) {
//			switch (BROWSER) {
//				case "chrome":
//					System.setProperty("webdriver.chrome.driver",
//							"./drivers/chromedriver-mac-64bit");
//					if (Boolean.valueOf(REMOTE)) {
//						driver = initRemoteDriver(DesiredCapabilities.chrome());
//					} else {
//						driver = new ChromeDriver();
//					}
//					break;
//
//				case "firefox":
//					System.setProperty("webdriver.gecko.driver",
//							"./drivers/geckodriver-mac-64bit");
//					if (Boolean.valueOf(REMOTE)) {
//						driver = initRemoteDriver(DesiredCapabilities.firefox());
//					} else {
//						driver = new FirefoxDriver();
//					}
//					break;
//			}
//		return driver;
//	}
//
//
//	private static RemoteWebDriver initRemoteDriver(DesiredCapabilities capability) {
//		RemoteWebDriver remoteDriver = null;
//		try {
//			remoteDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//
//		return remoteDriver;
//	}
//}