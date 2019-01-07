package tests;

import org.testng.annotations.Test;
import pages.CheckOutPage;
import pages.ItemPage;

import static org.testng.Assert.assertEquals;

public class CheckOutTest extends BaseTest {
	@Test
	public void changeQuantityInCart() {
		ItemPage itemPage1 = new ItemPage();
		itemPage1.clickAddToCart();
		ItemPage itemPage2 = new ItemPage("index.php?id_product=2&controller=product");
		itemPage2.clickAddToCart();
		CheckOutPage checkOutPage = new CheckOutPage();
		assertEquals(checkOutPage.getItemsInCart(), 2, "Incorrect number of items in cart");
		checkOutPage.pressIncreaseQuantity("Blouse");
		checkOutPage.pressIncreaseQuantity("Blouse");
		assertEquals(checkOutPage.getItemsInCart(), 4, "Incorrect number of items in cart");
		checkOutPage.pressDecreaseQuantity("Blouse");
		assertEquals(checkOutPage.getItemsInCart(), 3, "Incorrect number of items in cart");
	}
}
