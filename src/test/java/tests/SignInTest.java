package tests;

import org.testng.annotations.Test;
import pages.SignInPage;

import static org.testng.Assert.assertTrue;

public class SignInTest extends BaseTest {



    @Test
    public void signIn() {
	    SignInPage signInPage = new SignInPage(driver);
	    signInPage.signInWithCredentials("d1@grr.la", "d1@grr.la");
	    assertTrue(signInPage.isSignOutLinkDisplayed(), "Sign out link was not displayed");
    }

	@Test
	public void forgotPassword() {
		SignInPage signInPage = new SignInPage(driver);
		signInPage.forgotPasswordWithEmail("d1@grr.la");
		assertTrue(signInPage.isAlertBarSuccessDisplayed(), "Success message was not displayed");
	}

	@Test
	public void signInWithInvalidCredentials() {
		SignInPage signInPage = new SignInPage(driver);
		signInPage.signInWithCredentials("a3@grr.la", "a3@grr.la");
		assertTrue(signInPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
	}

	@Test
	public void forgotPasswordWithInvalidCredentials() {
		SignInPage signInPage = new SignInPage(driver);
		signInPage.forgotPasswordWithEmail("a3@grr.la");
		assertTrue(signInPage.isAlertBarDangerDisplayed(), "Error message was not displayed");
	}


}
