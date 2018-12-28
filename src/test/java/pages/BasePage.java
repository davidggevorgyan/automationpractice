package pages;

import config.DriverBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage extends LoadableComponent {
	private static final String BASE_URL = "http://automationpractice.com/";
	private String pageUrl;
	private WebDriver driver;
	private Integer defaultTimeout = 5;

	BasePage(String pageUrl) {
		this.pageUrl = pageUrl;
		this.driver = DriverBase.get();
		PageFactory.initElements(driver, this);
		this.load();
		this.isLoaded();
	}

	@Override
	public void load() {
		driver.get(BASE_URL + pageUrl);
	}

	void click(WebElement element) {
		click(element, defaultTimeout);
	}

	void click(WebElement element, Integer timeout) {
		isElementDisplayed(element, timeout);
		element.click();
	}

	boolean isElementDisplayed(WebElement element) {
		return isElementDisplayed(element, defaultTimeout);
	}

	boolean isElementDisplayed(WebElement element, Integer timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

}
