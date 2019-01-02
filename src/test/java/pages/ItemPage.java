package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

	@FindBy(name = "qty")
	private WebElement quantityField;

	@FindBy(id = "group_1")
	private WebElement sizesDropdown;

	@FindBy(id = "color_to_pick_list")
	private WebElement colorOptions;

	@FindBy(id = "our_price_display")
	private WebElement price;

	@FindBy(id = "layer_cart_product_quantity")
	private WebElement quantityInCartFrame;

	@FindBy(id = "layer_cart_product_attributes")
	private WebElement sizeAndColorInCartFrame;

	@FindBy(id = "layer_cart_product_price")
	private WebElement priceInCartFrame;


	public ItemPage() {
		super("index.php?id_product=5&controller=product");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(itemSection), "The page load is failed");
	}

	public void clickAddToCart() {
		click(addToCartButton);
	}

	public boolean isCheckoutFrameDisplayed() {
		return isElementDisplayed(cartFrame);
	}


	public void setQuantity(int count) {
		quantityField.clear();
		type(quantityField, String.valueOf(count));
	}

	public int getQuantityFromCartFrame() {
		isElementDisplayed(cartFrame);
		return Integer.parseInt(quantityInCartFrame.getText());
	}

	public void setSize(String size) {
		Select selectList = new Select(sizesDropdown);
		selectList.selectByVisibleText(size);
	}

	public String getSizeFromCartFrame() {
		isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(sizeAndColorInCartFrame.getText().length() - 1);
	}

	public void setColor(String color) {
		List<WebElement> colors = colorOptions.findElements(By.xpath(".//*"));
		for (WebElement li : colors) {
			if (li.getAttribute("title").equals(color)) {
				click(li);
			}
		}
	}

	public String getColorFromCartFrame() {
		isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(0, sizeAndColorInCartFrame.getText().length() - 3);
	}

	public double getPrice() {
		isElementDisplayed(cartFrame);
		return Double.parseDouble(price.getText().substring(1));
	}

	public double getPriceFromCartFrame() {
		isElementDisplayed(cartFrame);
		return Double.parseDouble(priceInCartFrame.getText().substring(1));
	}







}
