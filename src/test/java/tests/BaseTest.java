package tests;

import configs.DriverBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest {
	@BeforeMethod
	public void setUp() {
		DriverBase.get().getDriver();
	}


	@AfterMethod
	public void tearDown() {
		DriverBase.get().quitDriver(DriverBase.get().getDriver());
	}

}
