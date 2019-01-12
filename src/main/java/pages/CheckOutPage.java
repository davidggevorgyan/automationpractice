package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static setup.DriverBase.LOGIN;
import static setup.DriverBase.PASSWORD;

public class CheckOutPage extends BasePage {
	@FindBy(css = "[href*='order&step=1']")
	private WebElement proceedToStep2;

	@FindBy(css = "[name='processAddress']")
	private WebElement proceedToStep4;

	@FindBy(css = "[name='processCarrier']")
	private WebElement proceedToStep5;

	@FindBy(className = "checker")
	private WebElement termsOfServiceCheckobx;

	@FindBy(className = "bankwire")
	private WebElement payBankWire;

	@FindBy(css = "#cart_navigation > button")
	private WebElement completeOrder;

	@FindBy(css = "#center_column > div")
	private WebElement successMessage;


	@FindBy(id = "summary_products_quantity")
	private WebElement itemsInCart;

	@FindBy(id = "cart_summary")
	private WebElement cartTable;

	@FindBy(css = "[class*='alert-warning']")
	private WebElement emptyCart;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "passwd")
	private WebElement passwordField;

	@FindBy(id = "SubmitLogin")
	private WebElement signInButton;


	public CheckOutPage() {
		super("index.php?controller=order");
	}

	@Override
	protected void isLoaded() throws Error {
		getWait().isElementDisplayed(proceedToStep2);
	}

	public int getItemsInCart() {
		getWait().isElementDisplayed(itemsInCart);
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
		getWait().click(quantity.findElement(By.className("icon-plus")));
		getWait().isElementTextChanged(itemsInCart, originalText);
	}


	public void pressDecreaseQuantity(String itemName) {
		String originalText = itemsInCart.getText();
		WebElement quantity = getItem(itemName, Column.QTY);
		int originalQuantity = Integer.valueOf(quantity.findElement(By.tagName("input")).getAttribute("value"));
		getWait().click(quantity.findElement(By.className("icon-minus")));
		if (originalQuantity != 1) {
			getWait().isElementTextChanged(itemsInCart, originalText);
		}
	}

	public boolean isCartEmpty() {
		return getWait().isElementDisplayed(emptyCart);
	}

	public boolean purchase() {
		getWait().click(proceedToStep2);
		getWait().type(emailField, LOGIN);
		getWait().type(passwordField, PASSWORD);
		getWait().click(signInButton);
		getWait().click(proceedToStep4);
		getWait().click(termsOfServiceCheckobx);
		getWait().click(proceedToStep5);
		getWait().click(payBankWire);
		getWait().click(completeOrder);
		return successMessage.getText().contains("is complete");
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
