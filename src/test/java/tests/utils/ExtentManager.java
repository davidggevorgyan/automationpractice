package tests.utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Logger;

import java.io.File;

import static setup.DriverBase.TRAVIS_BUILD_NUMBER;
import static setup.DriverBase.TRAVIS_BUILD_WEB_URL;

public class ExtentManager {
	private final static Logger logger = Logger.getLogger(ExtentManager.class);

	public static ExtentReports createInstance() {
		String fileName = "index.html";
		String path = "target/report/";
		createReportPath(path);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path + fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Execution Report for Run: <a target=\"_blank\" href=\"" + TRAVIS_BUILD_WEB_URL + "\">#" + TRAVIS_BUILD_NUMBER + "</a>   |   <a target=\"_blank\" href=\"log4j-application.log\">📚 Log4j Log</a>");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;


	}

	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdir()) {
				logger.info("Directory: " + path + " is created!");
			} else {
				logger.error("Failed to create directory: " + path);
			}
		} else {
			logger.info("Directory already exists: " + path);
		}
	}
}