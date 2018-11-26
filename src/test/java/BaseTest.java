import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import providers.WebdriverFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    WebDriver driver;
    WebdriverFactory webdriverFactory = new WebdriverFactory();


    @BeforeEach
    public void setUp() throws MalformedURLException {

//        DesiredCapabilities cap = DesiredCapabilities.chrome();
//        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), cap);

        driver = webdriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://store.demoqa.com/");
    }

    @AfterEach
    public void teadDown() {
        System.out.println("Last url: " + driver.getCurrentUrl());
        getBrowserLogs();
        createScreenshot();
        driver.close();
    }

    private void getBrowserLogs() {
        System.out.println("Browser logs ");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }

    }

    private void createScreenshot() {
        File screenFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File fileToSave = new File("c:\\ScreenShot\\src" + LocalDate.now() + ".png");
            FileUtils.copyFile(screenFile, fileToSave);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

}
