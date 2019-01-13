package tests;

import org.testng.annotations.Test;
import pages.CartHoverPage;
import pages.CheckOutPage;
import pages.ItemPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartHoverTest extends BaseTest {
	@Test
	public void checkCountInHover() {
		ItemPage itemPage = new ItemPage().get();
		CartHoverPage cartHoverPage = new CartHoverPage();
		assertEquals(cartHoverPage.getCartHoverNumberInCart(), 0, "The number of items in the cart for a new user is more than zero");
		itemPage.clickAddToCart();
		assertEquals(cartHoverPage.getCartHoverNumberInCart(), 1, "The number of items in the cart is increased after adding an item to it");
	}

	@Test
	public void removeItemFromHover() {
		ItemPage itemPage = new ItemPage("index.php?id_product=2&controller=product").get();
		itemPage.clickAddToCart();
		itemPage.closeCartFrame();
		CartHoverPage cartHoverPage = new CartHoverPage();
		assertTrue(cartHoverPage.removeItemFromCart("Blouse"), "The item was not removed from Hover menu");
	}

	@Test
	public void checkOut() {
		ItemPage itemPage = new ItemPage("index.php?id_product=2&controller=product").get();
		itemPage.clickAddToCart();
		itemPage.closeCartFrame();
		CartHoverPage cartHoverPage = new CartHoverPage();
		cartHoverPage.checkOut();
		CheckOutPage checkOutPage = new CheckOutPage();
		assertEquals(checkOutPage.getItemsInCart(), 1, "Incorrect number of items in the cart");
	}
}
