package pages;

import config.DriverBase;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T>  {
	private static final String BASE_URL = "http://automationpractice.com";
	private WebDriver driver;
	private WebDriverWait wait;

	public BasePage(String path) {
		this.driver = DriverBase.get();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 10);
		driver.get(BASE_URL + path);
	}


	public void click(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element)).click();
	}

	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
