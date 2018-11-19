package providers;

import model.Drivers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class WebdriverFactory {

    public WebDriver getDriver() throws MalformedURLException {
        switch (getDriverFromConfig()) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFireFox();
            case IE:
                return getIE();
            case EDGE:
                return getEdge();
            case GRID:
                return getGrid();
        }
        return null;
    }

    private WebDriver getGrid() throws MalformedURLException {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        WebDriver driver = new RemoteWebDriver(new URL("http://192.168.56.1:4445/wd/hub"), cap);
        return driver;
    }

    private WebDriver getEdge(){
        System.setProperty("webdriver.edge.driver", "src/main/resources/MicrosoftWebDriver.exe");
        return new EdgeDriver();
    }

    private WebDriver getIE(){
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    private WebDriver getFireFox(){
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        return new FirefoxDriver(options);
    }

    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        return new ChromeDriver(options);
    }

    private Drivers getDriverFromConfig() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src\\main\\resources\\cofig.properties"));
            return Drivers.valueOf(prop.getProperty("browser"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Drivers.CHROME;
    }
}
