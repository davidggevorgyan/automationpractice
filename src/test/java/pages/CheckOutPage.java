package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class CheckOutPage extends BasePage {
	@FindBy(css = "[href*='order&step=1']")
	private WebElement proceedToStep1;

	@FindBy(id = "summary_products_quantity")
	private WebElement itemsInCart;

	@FindBy(id = "cart_summary")
	private WebElement cartTable;

	public CheckOutPage() {
		super("index.php?controller=order");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(proceedToStep1), "The page load is failed");
	}

	public int getItemsInCart() {
		isElementDisplayed(itemsInCart);
		return Integer.parseInt(itemsInCart.getText().replaceAll("[^0-9]", ""));
	}

	private WebElement getItem(String itemName, Column column) {
		List<WebElement> allRows = cartTable.findElements(By.cssSelector("[class*='cart_item'"));
		for (WebElement row : allRows) {
			if (row.findElement(By.cssSelector("td.cart_description > p > a")).getText().contains(itemName)) {
				return row.findElement(By.className(column.columnName));
			}
		}
		throw new NoSuchElementException("Unable to locate {" + itemName + "} wishlist");
	}


	public void pressIncreaseQuantity(String itemName) {
		String originalText = itemsInCart.getText();
		WebElement quantity = getItem(itemName, Column.QTY);
		click(quantity.findElement(By.className("icon-plus")));
		isElementTextChanged(itemsInCart, originalText);
	}


	public void pressDecreaseQuantity(String itemName) {
		String originalText = itemsInCart.getText();
		WebElement quantity = getItem(itemName, Column.QTY);
		click(quantity.findElement(By.className("icon-minus")));
		isElementTextChanged(itemsInCart, originalText);
	}

	enum Column {
		PRODUCT("cart_product"),
		DESCRIPTION("cart_description"),
		AVAIL("cart_avail"),
		UNITPRICE("cart_unit"),
		QTY("cart_quantity"),
		TOTAL("cart_total"),
		DELETE("cart_delete");

		private final String columnName;

		Column(String columnName) {
			this.columnName = columnName;
		}
	}
}
