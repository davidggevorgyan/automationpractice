package setup;

public final class Properties {
	public static final String BASE_URL = System.getProperty("selenium.baseURL", "http://automationpractice.com/");
	public static final String LOGIN = System.getProperty("selenium.login", "d1@grr.la");
	public static final String PASSWORD = System.getProperty("selenium.password", "d1@grr.la");
	public static final String TRAVIS_BUILD_NUMBER = System.getProperty("travis.buildNumber", "LocalRun");
	public static final String TRAVIS_BUILD_WEB_URL = System.getProperty("travis.buildURL", "localhost");
	static final Integer DEFAULT_TIMEOUT = Integer.parseInt(System.getProperty("selenium.defaultTimeout", "5"));
	static final String BROWSER = System.getProperty("selenium.browser", "chrome");
	static final String HOST = System.getProperty("selenium.host", "localhost");
	static final String HOST_URL = System.getProperty("selenium.hostURL", "http://localhost:4444/wd/hub");
}
