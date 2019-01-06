package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class CheckOutPage extends BasePage {
	@FindBy(css = "[href*='order&step=1']")
	private WebElement proceedToStep1;
	@FindBy(id = "summary_products_quantity")
	private WebElement itemsInCart;

	public CheckOutPage() {
		super("index.php?controller=order");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(proceedToStep1), "The page load is failed");
	}

	public int getItemsInCart() {
		isElementDisplayed(proceedToStep1);
		return Integer.parseInt(itemsInCart.getText().replace(" Product", ""));
	}
}
