package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "passwd")
	private WebElement passwordField;

	@FindBy(id = "SubmitLogin")
	private WebElement signInButton;

	@FindBy(className = "logout")
	private WebElement signOutLink;

	@FindBy(className = "login")
	private WebElement signInLink;

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

	@Override
	protected void isLoaded() throws Error {
		getWait().isElementDisplayed(signInLink);
	}

	public void signInWithCredentials(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		getWait().click(signInButton);
	}

	public void forgotPasswordWithEmail(String email) {
		getWait().click(forgotPasswordLink);
		emailField.sendKeys(email);
		getWait().click(retrievePasswordButton);
	}


	public void signOut() {
		getWait().click(signOutLink);

	}

	public boolean isSignInLinkDisplayed() {
		return getWait().isElementDisplayed(signInButton);
	}

	public boolean isSignOutLinkDisplayed() {
		return getWait().isElementDisplayed(signOutLink);
	}

	public boolean isAlertBarDangerDisplayed() {
		return getWait().isElementDisplayed(alertBarDanger);
	}

	public boolean isAlertBarSuccessDisplayed() {
		return getWait().isElementDisplayed(alertBarSuccess);
	}

}
