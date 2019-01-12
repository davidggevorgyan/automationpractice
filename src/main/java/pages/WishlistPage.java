package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class WishlistPage extends BasePage<WishlistPage> {
	@FindBy(xpath = "//*[@id=\"best-sellers_block_right\"]/div/ul/li[1]/a")
	private WebElement firstTopSellingItem;

	@FindBy(className = "page-heading")
	private WebElement myWhishlistHeader;

	@FindBy(id = "name")
	private WebElement newWishlistNameField;

	@FindBy(id = "submitWishlist")
	private WebElement submitNewWishlistButton;

	@FindBy(className = "table")
	private WebElement wishlistTable;

	WishlistPage() {
		super("index.php?fc=module&module=blockwishlist&controller=mywishlist");
	}

	public void create(String wishlistName) {
		getActions().type(newWishlistNameField, wishlistName);
		getActions().click(submitNewWishlistButton);
	}

	public boolean isWishlistPresent(String wishlistName) {
		getActions().isElementDisplayed(wishlistTable);
		return getCell(wishlistName, Column.NAME) != null;
	}

	public boolean deletelWishlist(String wishlistName) {
		getActions().isElementDisplayed(wishlistTable);
		WebElement wishlistDeleteButton = getCell(wishlistName, Column.DELETE);
		wishlistDeleteButton.findElement(By.className("icon-remove")).click();
		Alert popup = getActions().switchToAlert();
		popup.accept();
		return getActions().isElementNotDisplayed(wishlistDeleteButton);
	}

	private WebElement getCell(String wishlistName, Column columnName) {
		List<WebElement> allRows = wishlistTable.findElements(By.xpath("//*[contains(@class,\"table\")]/tbody/tr[*]/td[1]"));
		int rowNumberCounter = 1;
		for (WebElement row : allRows) {
			if (row.getText().equals(wishlistName)) {
				return wishlistTable.findElement(By.xpath("//*[contains(@class,\"table\")]/tbody/tr[" + rowNumberCounter + "]/td[" + columnName.columnNumber + "]"));
			} else {
				rowNumberCounter++;
			}
		}
		throw new NoSuchElementException("Unable to locate {" + wishlistName + "} wishlist");
	}

	public ItemPage openFirstTopSellingItem() {
		getActions().click(firstTopSellingItem);
		getActions().isPageReady();
		return new ItemPage();
	}

	enum Column {
		NAME(1),
		QTY(2),
		VIEWED(3),
		CREATED(4),
		DIRECTLINK(5),
		DELETE(6);

		private final int columnNumber;

		Column(int columnNumber) {
			this.columnNumber = columnNumber;
		}
	}


}

