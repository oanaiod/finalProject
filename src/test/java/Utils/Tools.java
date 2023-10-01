package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Tools {
    public static String replaceElements(String element, String valueToBeReplaced, String valueReplaceWith) {
        return element.replaceAll(valueToBeReplaced, valueReplaceWith);
    }

    public static String getReportName() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "extentReport_" + timestamp.getTime() + ".html";
    }

    public static String takeScreenshot(WebDriver driver) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String filename = ConstantUtils.SCREENSHOT_FOLDER + "screenshot_" + timestamp.getTime() +  ".png";
        try {
            FileUtils.copyFile(screenshotFile,
                    new File(filename));
        } catch (IOException e) {
            System.out.println("File could not be saved on the disk!!");
        }
        return filename;
    }
}
