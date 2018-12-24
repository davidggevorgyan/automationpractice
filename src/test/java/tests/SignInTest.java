package tests;

import org.testng.annotations.Test;
import pages.SignInPage;
import static org.testng.Assert.*;

public class SignInTest extends BaseTest {



    @Test
    public void signIn() {
        SignInPage signInPage = new SignInPage();
        signInPage.open();
        signInPage.with("d1@grr.la","d1@grr.la");
        assertTrue(signInPage.isSignOutLinkDisplayed());
    }




}
