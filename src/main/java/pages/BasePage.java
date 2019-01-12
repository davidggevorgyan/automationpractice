package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import setup.DriverBase;
import setup.Wait;

import static setup.Properties.BASE_URL;


public abstract class BasePage extends LoadableComponent {
	private String pageUrl;
	private WebDriver driver;
	private Wait wait;


	BasePage(String pageUrl) {
		this.pageUrl = pageUrl;
		this.driver = DriverBase.get().getDriver();
		this.wait = new Wait(driver);
		PageFactory.initElements(driver, this);
		this.load();
		this.isLoaded();
	}

	Wait getWait() {
		return wait;
	}

	@Override
	public void load() {
		if (pageUrl.contains("http")) {
			driver.get(pageUrl);
		} else {
			driver.get(BASE_URL + pageUrl);
		}
	}


}
