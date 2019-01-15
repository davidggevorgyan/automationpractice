package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import setup.Action;
import setup.DriverBase;

import static setup.FrameworkProperties.BASE_URL;


public abstract class BasePage<T extends LoadableComponent<T>> extends LoadableComponent<T> {
	private String pageUrl;
	private WebDriver driver;
	private Action action;


	BasePage(String pageUrl) {
		this.pageUrl = pageUrl;
		this.driver = DriverBase.get().getDriver();
		this.action = new Action(driver);
		PageFactory.initElements(driver, this);
	}

	Action getActions() {
		return action;
	}

	@Override
	public void load() {
		if (pageUrl.contains("http")) {
			driver.get(pageUrl);
		} else {
			driver.get(BASE_URL + pageUrl);
		}
	}

	@Override
	protected void isLoaded() throws Error {
		if (!this.driver.getCurrentUrl().contains(pageUrl) && getActions().isPageReady()) {
			throw new Error(action.getCurrentUrl() + " is not loaded");
		}
	}



}
