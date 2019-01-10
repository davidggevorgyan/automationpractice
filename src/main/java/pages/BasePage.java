package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverBase;

import java.util.function.Function;

import static setup.DriverBase.BASE_URL;
import static setup.DriverBase.DEFAULT_TIMEOUT;


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

	boolean isElementNotDisplayed(WebElement element, Integer timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}

	boolean isElementNotDisplayed(WebElement element) {
		return isElementNotDisplayed(element, DEFAULT_TIMEOUT);
	}

	void isElementTextChanged(WebElement element, String originalText, Integer timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(new Function<WebDriver, Boolean>() {
			String initialText = originalText;

			public Boolean apply(WebDriver driver) {
				return !element.getText().equals(initialText);
			}
		});
	}

	void isElementTextChanged(WebElement element, String originalText) {
		isElementTextChanged(element, originalText, DEFAULT_TIMEOUT);
	}

	void type(WebElement element, String text, Integer timeout) {
		isElementDisplayed(element, timeout);
		element.sendKeys(text);
	}

	void type(WebElement element, String text) {
		type(element, text, DEFAULT_TIMEOUT);
	}

	String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	Actions hover() {
		return new Actions(driver);
	}
}
