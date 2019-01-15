package setup;

import org.apache.log4j.*;


import java.io.*;
import java.util.Properties;


public final class FrameworkProperties {
	private final static Logger logger = Logger.getLogger(FrameworkProperties.class);
	public static final String BASE_URL = System.getProperty("selenium.baseURL", "http://automationpractice.com/");
	public static final String LOGIN = System.getProperty("selenium.login", "d1@grr.la");
	public static final String PASSWORD = System.getProperty("selenium.password", "d1@grr.la");
	private static final String TRAVIS_BUILD_NUMBER = System.getProperty("travis.buildNumber", "LocalRun");
	private static final String TRAVIS_BUILD_WEB_URL = System.getProperty("travis.buildURL", "localhost");
	static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
	static final String BROWSER = System.getProperty("selenium.browser", "chrome");
	static final String HOST = System.getProperty("selenium.host", "localhost");
	static final String HOST_URL = System.getProperty("selenium.hostURL", "http://localhost:4444/wd/hub");

	public static void writeProperties() {
		Properties properties = new Properties();
		properties.setProperty("Travis URL", TRAVIS_BUILD_WEB_URL);
		properties.setProperty("Travis Run", TRAVIS_BUILD_NUMBER);
		properties.setProperty("Browser", BROWSER);

		try {
			properties.store(new FileOutputStream("target/allure-results/environment.properties"), null);
		} catch (IOException e) {
			logger.error(e);
		}


	}
}
