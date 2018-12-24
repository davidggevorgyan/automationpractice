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
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "http://automationpractice.com";

    public BasePage() {
        this.driver = DriverBase.get().getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public T openPage(Class<T> clazz) {
        T page = PageFactory.initElements(driver, clazz);
        open(BASE_URL + getPageUrl());
        return page.get();
    }


    public void open(String url) {
        driver.get(url);
    }

    public abstract String getPageUrl();

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
