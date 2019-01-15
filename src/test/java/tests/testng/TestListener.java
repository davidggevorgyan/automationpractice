package tests.testng;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import setup.DriverBase;

public class TestListener implements ITestListener {
	private final static Logger logger = Logger.getLogger(TestListener.class);

	@Override
	public synchronized void onStart(ITestContext context) {
		logger.info("Extent Reports Version 4 Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		logger.info(("Extent Reports Version 4  Test Suite is ending!"));
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " started!"));
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " passed!"));
		browserStackLinkGenerator();
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " failed!"));
		browserStackLinkGenerator();
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " skipped!"));
		browserStackLinkGenerator();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private void browserStackLinkGenerator() {
		//test.get().info("<a href=\"https://automate.browserstack.com/builds/f535bea9b4c4f1050bccd80507ab9b175b959d8a/sessions/" + DriverBase.get().getSession(DriverBase.get().getDriver()) + "\" target=\"_blank\">Link to the Run</a>");
	}

}
