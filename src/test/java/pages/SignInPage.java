package pages;


import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;


public class SignInPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement signInButton;

    @FindBy(className = "logout")
    private WebElement signOutLink;



    public void with(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        click(signInButton);
    }

    public SignInPage open() {
        openPage(this.getClass());
        return this;
    }

    public void signOut() {
        click(signOutLink);

    }

    public boolean isSignOutLinkDisplayed() {
        return isElementDisplayed(signOutLink);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
       // String url = super(driver).getCurrentUrl();
       // assertTrue( url.endsWith("index.php?controller=authentication&back=my-account"),"Not on the issue entry page: " + url);
    }

    @Override
    public String getPageUrl() {
        return "/index.php?controller=authentication&back=my-account";
    }
}
