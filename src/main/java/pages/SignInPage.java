package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage<SignInPage> {
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

	@FindBy(className = "lnk_wishlist")
	private WebElement wishListButton;


	public SignInPage() {
		super("index.php?controller=authentication&back=my-account");
	}


	public void signInWithCredentials(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		getActions().click(signInButton);
	}

	public void forgotPasswordWithEmail(String email) {
		getActions().click(forgotPasswordLink);
		emailField.sendKeys(email);
		getActions().click(retrievePasswordButton);
	}


	public void signOut() {
		getActions().click(signOutLink);

	}

	public boolean isSignInLinkDisplayed() {
		return getActions().isElementDisplayed(signInButton);
	}

	public boolean isSignOutLinkDisplayed() {
		return getActions().isElementDisplayed(signOutLink);
	}

	public boolean isAlertBarDangerDisplayed() {
		return getActions().isElementDisplayed(alertBarDanger);
	}

	public boolean isAlertBarSuccessDisplayed() {
		return getActions().isElementDisplayed(alertBarSuccess);
	}

	public WishlistPage openWishListPage() {
		getActions().click(wishListButton);
		getActions().isPageReady();
		return new WishlistPage();
	}
}
