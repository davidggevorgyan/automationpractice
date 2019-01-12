package setup;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static setup.Properties.DEFAULT_TIMEOUT;

public class Wait {

	private final WebDriver driver;

	public Wait(WebDriver driver) {
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

	void isElementTextChanged(WebElement element, String originalText, Integer timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(new Function<WebDriver, Boolean>() {
			String initialText = originalText;

			public Boolean apply(WebDriver driver) {
				return !element.getText().equals(initialText);
			}
		});
	}

	public void isElementTextChanged(WebElement element, String originalText) {
		isElementTextChanged(element, originalText, DEFAULT_TIMEOUT);
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

	public Actions hover() {
		return new Actions(driver);
	}

}
