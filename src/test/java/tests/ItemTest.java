package tests;

import org.testng.annotations.Test;
import pages.ItemPage;

import static org.testng.Assert.assertTrue;

public class ItemTest extends BaseTest {
	@Test
	public void addItemToCart() {
		ItemPage itemPage = new ItemPage();
		itemPage.addItemToCart();
		assertTrue(itemPage.isCheckoutFrameDisplayed(), "Checkout popup was not displayed");
	}


}
