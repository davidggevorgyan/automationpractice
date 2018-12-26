package pages;

import config.DriverBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage extends LoadableComponent {
	private static final String BASE_URL = "http://automationpractice.com/";
	private String pageUrl;
	private WebDriver driver;
	private WebDriverWait wait;

	BasePage(String pageUrl) {
		this.pageUrl = pageUrl;
		this.driver = DriverBase.get();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 5);
		this.load();
		this.isLoaded();
	}

	@Override
	public void load() {
		driver.get(BASE_URL + pageUrl);
	}

	void click(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).click();
	}

	boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	void waitForElementToBeVisible(final WebElement element) {
		try {
			new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(element));
		} catch (WebDriverException e) {
			throw new Error("Element is not visible");
		}
	}


}
