package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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

	@FindBy(id = "wishlist_button")
	private WebElement addToWishlist;

	@FindBy(className = "fancybox-error")
	private WebElement fancyBox;

	@FindBy(className = "cross")
	private WebElement closeCartFrame;


	public ItemPage() {
		super("index.php?id_product=5&controller=product");
	}

	public ItemPage(String url) {
		super(url);
	}

	@Override
	protected void isLoaded() throws Error {
		getWait().isElementDisplayed(itemSection);
	}

	public void clickAddToCart() {
		getWait().click(addToCartButton);
		getWait().isElementDisplayed(proceedToCheckoutButton);
	}

	public void clickAddToWishlist() {
		getWait().click(addToWishlist);
	}

	public boolean isCheckoutFrameDisplayed() {
		return getWait().isElementDisplayed(cartFrame);
	}


	public void setQuantity(int count) {
		quantityField.clear();
		getWait().type(quantityField, String.valueOf(count));
	}

	public int getQuantityFromCartFrame() {
		getWait().isElementDisplayed(cartFrame);
		return Integer.parseInt(quantityInCartFrame.getText());
	}

	public void setSize(String size) {
		Select selectList = new Select(sizesDropdown);
		selectList.selectByVisibleText(size);
	}

	public String getSizeFromCartFrame() {
		getWait().isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(sizeAndColorInCartFrame.getText().length() - 1);
	}

	public void setColor(String color) {
		List<WebElement> colors = colorOptions.findElements(By.xpath(".//*"));
		for (WebElement li : colors) {
			if (li.getAttribute("title").equals(color)) {
				getWait().click(li);
			}
		}
	}

	public String getColorFromCartFrame() {
		getWait().isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(0, sizeAndColorInCartFrame.getText().length() - 3);
	}

	public double getPrice() {
		getWait().isElementDisplayed(cartFrame);
		return Double.parseDouble(price.getText().substring(1));
	}

	public double getPriceFromCartFrame() {
		getWait().isElementDisplayed(cartFrame);
		return Double.parseDouble(priceInCartFrame.getText().substring(1));
	}

	public String getFancyBoxText() {
		getWait().isElementDisplayed(fancyBox);
		return fancyBox.getText();
	}

	public String getUrl() {
		return getWait().getCurrentUrl();
	}

	public void closeCartFrame() {
		getWait().click(closeCartFrame);
		getWait().isElementNotDisplayed(cartFrame);
	}

}
