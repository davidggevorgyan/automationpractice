package tests;

import configs.DriverBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;



public class BaseTest {
	@BeforeMethod
	public void setUp(Method method) {
		DriverBase.get().getDriver(method.getName());
	}


	@AfterMethod
	public void tearDown() {
		DriverBase.get().quitDriver(DriverBase.get().getDriver());
	}

}
