package org.automationframework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject() {

        String filepath = System.getProperty("user.dir") + "//reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(filepath);
        reporter.config().setReportName("Web Automation");
        reporter.config().setDocumentTitle("Web Automation Report");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Akash");
        return reports;
    }
}
