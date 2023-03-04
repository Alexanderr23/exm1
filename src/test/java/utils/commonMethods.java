package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class commonMethods extends pageInit {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        configReader.readProperties(utils.constants.CONFIGURATION_FILEPATH);

        switch (configReader.getPropertyValue("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }

        driver.manage().window().maximize();
        driver.get(configReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(utils.constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //this method is used to initialize all the objects of the pages at the very beginning
        initializePageObjects();
    }

    public static void closeBrowser() {
        driver.quit();
    }

    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }


    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, utils.constants.EXPLICIT_WAIT);
        return wait;
    }


    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }



    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }


    public static void selectDropdown(WebElement element, String text) {
        Select s = new Select(element);
        s.selectByVisibleText(text);
    }


    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }


    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }



    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);



        try {
            FileUtils.copyFile(sourceFile, new File(constants.SCREENSHOT_FILEPATH +
                    fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        //to format the date according to the choice of our own
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
