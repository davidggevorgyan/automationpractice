package setup;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static setup.FrameworkProperties.DEFAULT_TIMEOUT;

public class Action {

	private final WebDriver driver;

	public Action(WebDriver driver) {
		this.driver = driver;
	}

	public void click(WebElement element) {
		click(element, DEFAULT_TIMEOUT);
	}

	void click(WebElement element, Integer timeout) {
		isElementDisplayed(element, timeout);
		element.click();
	}

	public boolean isElementDisplayed(WebElement element) {
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

	public boolean isElementNotDisplayed(WebElement element) {
		return isElementNotDisplayed(element, DEFAULT_TIMEOUT);
	}

	boolean isElementTextChanged(WebElement element, String originalText, Integer timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(new Function<WebDriver, Boolean>() {
			String initialText = originalText;

			public Boolean apply(WebDriver driver) {
				return !element.getText().equals(initialText);
			}
			});
		} catch (WebDriverException e) {
			return false;
		}
		return true;
	}

	public boolean isElementTextChanged(WebElement element, String originalText) {
		return isElementTextChanged(element, originalText, DEFAULT_TIMEOUT);
	}

	void type(WebElement element, String text, Integer timeout) {
		isElementDisplayed(element, timeout);
		element.sendKeys(text);
	}

	public void type(WebElement element, String text) {
		type(element, text, DEFAULT_TIMEOUT);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public Alert switchToAlert() {
		return driver.switchTo().alert();
	}

	public org.openqa.selenium.interactions.Actions hover() {
		return new org.openqa.selenium.interactions.Actions(driver);
	}

	public boolean isPageReady() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
			wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		} catch (WebDriverException e) {
			return false;
		}
		return true;
	}

}
