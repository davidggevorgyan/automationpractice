package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class CartHoverPage extends BasePage {
	@FindBy(className = "shopping_cart")
	private WebElement cartHover;

	@FindBy(id = "button_order_cart")
	private WebElement checkOutButton;

	@FindBy(css = ".shopping_cart > a")
	private WebElement enableHoverElement;

	public CartHoverPage() {
		super("index.php?id_product=2&controller=product");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(cartHover), "The page load is failed");
	}

	private WebElement getCartHoverItem(String productName) {
		List<WebElement> cartList = getCartItems();
		for (WebElement cartItem : cartList) {
			if (cartItem.findElement(By.cssSelector("[title]")).getAttribute("title").contains(productName)) {
				return cartItem;
			}
		}
		throw new NoSuchElementException("Unable to locate {" + productName + "} product in cart");
	}

	public int getCartHoverNumberInCart() {
		return getCartItems().size();
	}

	private List<WebElement> getCartItems() {
		return cartHover.findElements(By.cssSelector("dt[class*=\"item\"]"));
	}

	public boolean removeItemFromCart(String productName) {
		WebElement itemToRemove = getCartHoverItem(productName).findElement(By.className("remove_link"));
		hover().moveToElement(enableHoverElement).build().perform();
		click(itemToRemove);
		return isElementNotDisplayed(itemToRemove);
	}

	public void checkOut() {
		hover().moveToElement(enableHoverElement).build().perform();
		click(checkOutButton);
	}
}
