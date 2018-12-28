package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;


public class ItemPage extends BasePage {
	@FindBy(className = "primary_block")
	private WebElement itemSection;

	@FindBy(id = "layer_cart")
	private WebElement cartFrame;


	@FindBy(css = "#add_to_cart > button > span")
	private WebElement addToCartButton;

	@FindBy(css = "a[title='Proceed to checkout']")
	private WebElement proceedToCheckoutButton;


	public ItemPage() {
		super("index.php?id_product=2&controller=product");
	}

	public void addItemToCart() {
		click(addToCartButton);
	}

	public boolean isCheckoutFrameDisplayed() {
		return isElementDisplayed(cartFrame);
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(itemSection), "The page load is failed");
	}
}
