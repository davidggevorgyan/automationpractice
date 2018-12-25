package tests;

import config.DriverBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BaseTest {
	@BeforeMethod
	public void setUp() {
		DriverBase.get();
	}


	@AfterMethod
	public void tearDown() {
		DriverBase.get().quit();
	}




}
