package configs.TestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import configs.DriverBase;
import configs.ExtentManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	private final static Logger logger = Logger.getLogger(TestListener.class);
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		logger.info("Extent Reports Version 4 Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		logger.info(("Extent Reports Version 4  Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " passed!"));
		browserStackLinkGenerator();
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " failed!"));
		browserStackLinkGenerator();
		test.get().fail(result.getThrowable());
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " skipped!"));
		browserStackLinkGenerator();
		test.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private void browserStackLinkGenerator() {
		test.get().info("<a href=\"https://automate.browserstack.com/builds/f535bea9b4c4f1050bccd80507ab9b175b959d8a/sessions/" + DriverBase.get().getSession(DriverBase.get().getDriver()) + "\" target=\"_blank\">Link to the Run</a>");
	}

}
