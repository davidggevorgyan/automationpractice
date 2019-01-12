package tests;

import org.testng.annotations.Test;
import pages.ItemPage;
import pages.SignInPage;
import pages.WishlistPage;

import static java.util.UUID.randomUUID;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static setup.Properties.LOGIN;
import static setup.Properties.PASSWORD;

public class WishlistTest extends BaseTest {
	@Test
	public void createUpdateDeleteWishlist() {
		SignInPage signInPage = new SignInPage();
		signInPage.signInWithCredentials(LOGIN, PASSWORD);

		WishlistPage wishlistPage = new WishlistPage();
		String wishlistName = String.valueOf(randomUUID()).substring(0, 8);
		wishlistPage.create(wishlistName);
		assertTrue(wishlistPage.isWishlistPresent(wishlistName), "Specified Wishlist was not found");

		ItemPage itemPage = new ItemPage();
		itemPage.clickAddToWishlist();
		assertEquals(itemPage.getFancyBoxText(), "Added to your wishlist.", "FancyBox text was not valid");

		wishlistPage.load();
		assertTrue(wishlistPage.deletelWishlist(wishlistName), "The wishlist was not removed");
	}
}
