package tests;

import org.testng.annotations.Test;
import pages.ItemPage;

import static org.testng.Assert.*;

public class ItemTest extends BaseTest {
	@Test
	public void addItemToCart() {
		ItemPage itemPage = new ItemPage();
		itemPage.clickAddToCart();
		assertTrue(itemPage.isCheckoutFrameDisplayed(), "Checkout popup was not displayed");
	}

	@Test(enabled = false)
	public void exampleOfDisabledFailingTest() {
		ItemPage itemPage = new ItemPage();
		itemPage.clickAddToCart();
		assertFalse(itemPage.isCheckoutFrameDisplayed(), "Checkout popup was not displayed");
	}

	@Test
	public void addSeveralItemsToCart() {
		ItemPage itemPage = new ItemPage();
		itemPage.setQuantity(3);
		itemPage.setSize("M");
		itemPage.setColor("Orange");
		itemPage.clickAddToCart();
		assertEquals(itemPage.getQuantityFromCartFrame(), 3, "Quantity doesn't match to values in the cart frame");
		assertEquals(itemPage.getSizeFromCartFrame(), "M", "Size doesn't match to values in the cart frame");
		assertEquals(itemPage.getColorFromCartFrame(), "Orange", "Color doesn't match to values in the cart frame");
		assertEquals(itemPage.getPrice() * itemPage.getQuantityFromCartFrame(), itemPage.getPriceFromCartFrame(), "Final total price is not valid");
	}

	@Test
	public void colorSelection() {
		ItemPage itemPage = new ItemPage();
		itemPage.setColor("Blue");
		assertEquals("http://automationpractice.com/index.php?id_product=5&controller=product#/size-s/color-blue", itemPage.getCurrentUrl(), "Images are not filtered based on color");
	}

}
