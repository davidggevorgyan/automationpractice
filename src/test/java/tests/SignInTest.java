package tests;

import org.testng.annotations.Test;
import pages.SignInPage;

import static org.testng.Assert.assertTrue;

public class SignInTest extends BaseTest {



    @Test
    public void signIn() {
	    SignInPage signInPage = new SignInPage();
	    signInPage.signInWithCredentials("d1@grr.la", "d1@grr.la");
	    assertTrue(signInPage.isSignOutLinkDisplayed());
    }




}
