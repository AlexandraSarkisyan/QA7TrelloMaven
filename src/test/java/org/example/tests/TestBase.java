package org.example.tests;

import java.io.IOException;
import java.net.URL;

import org.example.SuiteConfiguration;
import org.example.util.LogLog4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {
  public static final String LOGIN ="alexandra13sark@gmail.com" ;
  public static final String PASSWORD = "alex613e";

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;
  public static LogLog4j log4j = new LogLog4j();

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver.get(baseUrl);

  }
  @AfterMethod
  public void finishTest() {
    driver.quit();
  }
  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }

}
