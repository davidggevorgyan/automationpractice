package tests;

import org.testng.annotations.Test;
import pages.ItemPage;
import pages.SignInPage;
import pages.WishlistPage;

import static java.util.UUID.randomUUID;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishlistTest extends BaseTest {
	@Test
	public void createWishlist() {
		SignInPage signInPage = new SignInPage();
		signInPage.signInWithCredentials("d1@grr.la", "d1@grr.la");

		WishlistPage wishlistPage = new WishlistPage();
		String whishlistName = String.valueOf(randomUUID()).substring(0, 8);
		wishlistPage.create(whishlistName);
		assertTrue(wishlistPage.isWishilistPresent(whishlistName), "Specified Whishilist was not found");

		ItemPage itemPage = new ItemPage();
		itemPage.clickAddToWishlist();
		assertEquals(itemPage.getFancyBoxText(), "Added to your wishlist.", "FancyBox text was not valid");

		wishlistPage.load();
		wishlistPage.deleteAllWishlists();
	}
}
