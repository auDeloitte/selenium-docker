package com.seleniumgridfollowalong;

import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
// import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;

import com.google.common.util.concurrent.Uninterruptibles;
import com.seleniumgridfollowalong.listener.TestListener;
import com.util.Configuration;
import com.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public abstract class AbstractTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setupConfig(){
        Configuration.initialize();
    }

    @BeforeTest
    // @Parameters({"browser"})
    public void setDriver(ITestContext ctx) throws Exception {
        this.driver = Boolean.parseBoolean(Configuration.get(Constants.GRID_ENABLED)) ? getRemoteDriver()
                : getLocalDriver();
        ctx.setAttribute(Constants.DRIVER, this.driver);
        // if (Boolean.getBoolean("selenium.grid.enabled")) {
        // this.driver = getRemoteDriver();
        // }
    }

    private WebDriver getRemoteDriver() throws Exception {
        // String browser = System.getProperty("browser");
        // URI uri = URI.create("http://localhost:4444/");
        // URL url = uri.toURL();
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Configuration.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Configuration.get(Constants.GRID_URL_FORMAT);
        String hubHost = Configuration.get(Constants.GRID_HUB_HOST);
        // String url = String.format(urlFormat, hubHost);
        URI uri = URI.create(String.format(urlFormat, hubHost));
        URL url = uri.toURL();
        log.info("Grid url: {}", url);
        return new RemoteWebDriver(url, capabilities);
        // if (browser.equalsIgnoreCase("chrome")) {
        // ChromeOptions options = new ChromeOptions();
        // // Add any Chrome-specific options here (headless, arguments, etc.)
        // return new RemoteWebDriver(url, options);
        // } else if (browser.equalsIgnoreCase("firefox")) {
        // FirefoxOptions options = new FirefoxOptions();
        // // Add any Firefox-specific options here
        // return new RemoteWebDriver(url, options);
        // } else {
        // throw new IllegalArgumentException("Unsupported browser: " + browser);
        // }
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

    @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
    }

}
