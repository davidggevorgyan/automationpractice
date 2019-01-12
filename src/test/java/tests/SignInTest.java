package tests;

import org.testng.annotations.Test;
import pages.SignInPage;

import static org.testng.Assert.assertTrue;
import static setup.Properties.LOGIN;
import static setup.Properties.PASSWORD;

public class SignInTest extends BaseTest {



    @Test
    public void signIn() {
	    SignInPage signInPage = new SignInPage().get();
	    signInPage.signInWithCredentials(LOGIN, PASSWORD);
	    assertTrue(signInPage.isSignOutLinkDisplayed(), "Sign out link was not displayed");
    }

	@Test
	public void forgotPassword() {
		SignInPage signInPage = new SignInPage().get();
		signInPage.forgotPasswordWithEmail(LOGIN);
		assertTrue(signInPage.isAlertBarSuccessDisplayed(), "Success message was not displayed");
	}

	@Test
	public void signInWithInvalidCredentials() {
		SignInPage signInPage = new SignInPage().get();
		signInPage.signInWithCredentials("a3@grr.la", "a3@grr.la");
		assertTrue(signInPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
	}

	@Test
	public void forgotPasswordWithInvalidCredentials() {
		SignInPage signInPage = new SignInPage().get();
		signInPage.forgotPasswordWithEmail("a3@grr.la");
		assertTrue(signInPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
	}

	@Test
	public void signOut() {
		SignInPage signInPage = new SignInPage().get();
		signInPage.signInWithCredentials(LOGIN, PASSWORD);
		signInPage.signOut();
		assertTrue(signInPage.isSignInLinkDisplayed(), "SignIn link was not displayed after sign out action");
	}



}
