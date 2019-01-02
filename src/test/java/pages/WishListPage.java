package pages;

public class WishListPage extends BasePage {
	WishListPage(String pageUrl) {
		super("index.php?fc=module&module=blockwishlist&controller=mywishlist");
	}

	@Override
	protected void isLoaded() throws Error {

	}
}
