package pages;

import config.DriverBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import static config.DriverBase.BASE_URL;
import static config.DriverBase.DEFAULT_TIMEOUT;


public abstract class BasePage extends LoadableComponent {
	private String pageUrl;
	private WebDriver driver;


	BasePage(String pageUrl) {
		this.pageUrl = pageUrl;
		this.driver = DriverBase.get().getDriver();
		PageFactory.initElements(driver, this);
		this.load();
		this.isLoaded();
	}

	@Override
	public void load() {
		if (pageUrl.contains("http")) {
			driver.get(pageUrl);
		} else {
			driver.get(BASE_URL + pageUrl);
		}
	}

	void click(WebElement element) {
		click(element, DEFAULT_TIMEOUT);
	}

	void click(WebElement element, Integer timeout) {
		isElementDisplayed(element, timeout);
		element.click();
	}

	boolean isElementDisplayed(WebElement element) {
		return isElementDisplayed(element, DEFAULT_TIMEOUT);
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
