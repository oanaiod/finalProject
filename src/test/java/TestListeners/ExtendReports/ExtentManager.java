package TestListeners.ExtendReports;

import Utils.ConstantUtils;
import Utils.Tools;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(ConstantUtils.EXTENT_FOLDER + Tools.getReportName());
        reporter.config().setReportName("AUT 12 REPORT");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("AUTHOR", "Florin Barbuceanu");
        extentReports.setSystemInfo("ENV", "local");
        extentReports.setSystemInfo("OS NAME", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS VERSION", System.getProperty("os.version"));
        return extentReports;
    }
}