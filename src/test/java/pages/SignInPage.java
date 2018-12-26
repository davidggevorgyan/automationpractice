package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;


public class SignInPage extends BasePage {
	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "passwd")
	private WebElement passwordField;

	@FindBy(id = "SubmitLogin")
	private WebElement signInButton;

	@FindBy(className = "logout")
	private WebElement signOutLink;

	@FindBy(css = ".lost_password > a")
	private WebElement forgotPasswordLink;

	@FindBy(className = "alert-danger")
	private WebElement alertBarDanger;

	@FindBy(className = "alert-success")
	private WebElement alertBarSuccess;


	@FindBy(css = "#form_forgotpassword > fieldset > p > button")
	private WebElement retrievePasswordButton;

	public SignInPage() {
		super("index.php?controller=authentication&back=my-account");
	}


	public void signInWithCredentials(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		click(signInButton);
	}

	public void forgotPasswordWithEmail(String email) {
		click(forgotPasswordLink);
		emailField.sendKeys(email);
		click(retrievePasswordButton);
	}


	public void signOut() {
		click(signOutLink);

	}

	public boolean isSignOutLinkDisplayed() {
		return isElementDisplayed(signOutLink);
	}

	public boolean isAlertBarDangerDisplayed() {
		return isElementDisplayed(alertBarDanger);
	}

	public boolean isAlertBarSuccessDisplayed() {
		return isElementDisplayed(alertBarSuccess);
	}


	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(signInButton));
	}
}
