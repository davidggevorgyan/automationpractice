package tests;

import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.ItemPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckOutTest extends BaseTest {
	@Test
	public void changeQuantityInCart() {
		ItemPage itemPage1 = new ItemPage().get();
		itemPage1.clickAddToCart();
		itemPage1.closeCartFrame();
		ItemPage itemPage2 = new ItemPage("index.php?id_product=2&controller=product").get();
		itemPage2.clickAddToCart();
		CheckOutPage checkOutPage = itemPage2.clickCheckout();
		assertEquals(checkOutPage.getItemsInCart(), 2, "Incorrect number of items in cart");
		checkOutPage.pressIncreaseQuantity("Blouse");
		checkOutPage.pressIncreaseQuantity("Blouse");
		assertEquals(checkOutPage.getItemsInCart(), 4, "Incorrect number of items in cart");
		checkOutPage.pressDecreaseQuantity("Blouse");
		assertEquals(checkOutPage.getItemsInCart(), 3, "Incorrect number of items in cart");
	}

	@Test
	public void removeLastItemFromCart() {
		ItemPage itemPage = new ItemPage().get();
		itemPage.clickAddToCart();
		CheckOutPage checkOutPage = itemPage.clickCheckout();
		checkOutPage.pressDecreaseQuantity("Printed Summer Dress");
		assertTrue(checkOutPage.isCartEmpty(), "The cart was not empty");
	}

	@Test
	public void buyItemsFromCart() {
		ItemPage itemPage = new ItemPage().get();
		itemPage.clickAddToCart();
		CheckOutPage checkOutPage = itemPage.clickCheckout();
		assertTrue(checkOutPage.purchase(), "Purchase process failed");
	}
}
