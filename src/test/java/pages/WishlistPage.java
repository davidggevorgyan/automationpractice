package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishlistPage extends BasePage {
	@FindBy(className = "page-heading")
	private WebElement myWhishlistHeader;

	@FindBy(id = "name")
	private WebElement newWishlistNameField;

	@FindBy(id = "submitWishlist")
	private WebElement submitNewWishlistButton;

	@FindBy(className = "table")
	private WebElement wishlistTable;


	public WishlistPage() {
		super("index.php?fc=module&module=blockwishlist&controller=mywishlist");
	}

	@Override
	protected void isLoaded() throws Error {
		assertTrue(isElementDisplayed(myWhishlistHeader), "The page load is failed");
		assertEquals(myWhishlistHeader.getText(), "MY WISHLISTS", "The page load is failed");
	}

	public void create(String name) {
		type(newWishlistNameField, name);
		click(submitNewWishlistButton);
	}

	public boolean isWishilistPresent(String name) {
		isElementDisplayed(wishlistTable);
		List<WebElement> allRows = wishlistTable.findElements(By.tagName("tr"));
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			if (cells.size() != 0 && cells.get(0).getText().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public void deleteAllWishlists() {
		isElementDisplayed(wishlistTable);
		List<WebElement> cells = wishlistTable.findElements(By.className("icon-remove"));
		for (WebElement cell : cells) {
			click(cell);
			Alert popup = switchToAlert();
			popup.accept();
		}
	}
}
