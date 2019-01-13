package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ItemPage extends BasePage<ItemPage> {
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

	public void clickAddToCart() {
		getActions().click(addToCartButton);
		getActions().isElementDisplayed(proceedToCheckoutButton);
	}

	public void clickAddToWishlist() {
		getActions().click(addToWishlist);
	}

	public boolean isCheckoutFrameDisplayed() {
		return getActions().isElementDisplayed(cartFrame);
	}


	public void setQuantity(int count) {
		quantityField.clear();
		getActions().type(quantityField, String.valueOf(count));
	}

	public int getQuantityFromCartFrame() {
		getActions().isElementDisplayed(cartFrame);
		return Integer.parseInt(quantityInCartFrame.getText());
	}

	public void setSize(String size) {
		Select selectList = new Select(sizesDropdown);
		selectList.selectByVisibleText(size);
	}

	public String getSizeFromCartFrame() {
		getActions().isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(sizeAndColorInCartFrame.getText().length() - 1);
	}

	public void setColor(String color) {
		List<WebElement> colors = colorOptions.findElements(By.xpath(".//*"));
		for (WebElement li : colors) {
			if (li.getAttribute("title").equals(color)) {
				getActions().click(li);
			}
		}
	}

	public String getColorFromCartFrame() {
		getActions().isElementDisplayed(cartFrame);
		return sizeAndColorInCartFrame.getText().substring(0, sizeAndColorInCartFrame.getText().length() - 3);
	}

	public double getPrice() {
		getActions().isElementDisplayed(cartFrame);
		return Double.parseDouble(price.getText().substring(1));
	}

	public double getPriceFromCartFrame() {
		getActions().isElementDisplayed(cartFrame);
		return Double.parseDouble(priceInCartFrame.getText().substring(1));
	}

	public String getFancyBoxText() {
		getActions().isElementDisplayed(fancyBox);
		return fancyBox.getText();
	}

	public String getUrl() {
		return getActions().getCurrentUrl();
	}

	public void closeCartFrame() {
		getActions().click(closeCartFrame);
		getActions().isElementNotDisplayed(cartFrame);
	}

	public CheckOutPage clickCheckout() {
		getActions().click(proceedToCheckoutButton);
		CheckOutPage checkOutPage = new CheckOutPage();
		checkOutPage.isLoaded();
		return new CheckOutPage();
	}
}
