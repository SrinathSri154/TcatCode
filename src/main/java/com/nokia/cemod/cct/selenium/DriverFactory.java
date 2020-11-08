package com.nokia.cemod.cct.selenium;

import com.nokia.cemod.cct.utils.conf.TestConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;





class DriverFactory
{
  private static final Logger logger = Logger.getLogger(DriverFactory.class);

  public static final WebDriver getDriver(SharedDriver.DriverType driverType) {
    if (driverType.equals(SharedDriver.DriverType.CHROME))
      return (WebDriver)getChromeDriver();
    if (driverType.equals(SharedDriver.DriverType.FIREFOX))
      return (WebDriver)getFirefoxDriver();
    if (driverType.equals(SharedDriver.DriverType.REMOTE)) {
      return getRemoteDriver();
    }
    return (WebDriver)getFirefoxDriver();
  }


  private static final ChromeDriver getChromeDriver() {

    String chromeDriverPath = "";

    if (TestConfiguration.getProperty("cct.run.os").equals("windows")) {

      chromeDriverPath = TestConfiguration.getProperty("cct.run.browser.windows.chromeDriverPath");
    } else if (TestConfiguration.getProperty("cct.run.os").equals("linux")) {

      chromeDriverPath = TestConfiguration.getProperty("cct.run.browser.linux.chromeDriverPath");
    }
    ChromeOptions options = new ChromeOptions();
    logger.info("Chrome driver path: " + chromeDriverPath);
    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    if (TestConfiguration.getProperty("cct.run.browser.headless").equalsIgnoreCase("true")) {
      logger.info("Running browser headless...");

      options.addArguments("no-sandbox");
      options.addArguments("disable-dev-shm-usage");
      options.addArguments("--headless");
      options.addArguments("--start-maximized");
      //options.addArguments("--window-size=1980,1080");
      //capabilities.setCapability("goog:chromeOptions", chromeOptions);
    }


    options.addArguments("--disable-web-security");
    options.addArguments("--allow-running-insecure-content");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-gpu");
    options.addArguments("--allow-insecure-localhost");


    return new ChromeDriver(options);
  }

  private static final WebDriver getRemoteDriver() {
    System.setProperty("webdriver.chrome.driver",TestConfiguration.getProperty("cct.run.browser.windows.chromeDriverPath"));



    ChromeDriverService service = (ChromeDriverService)((ChromeDriverService.Builder)((ChromeDriverService.Builder)(new ChromeDriverService.Builder()).usingDriverExecutable(new File(TestConfiguration.getProperty("cct.run.browser.windows.chromeDriverPath")))).usingAnyFreePort()).build();
    try {
      service.start();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return (WebDriver)new RemoteWebDriver(service.getUrl(), (Capabilities)DesiredCapabilities.chrome());
  }

  private static void setProxy(DesiredCapabilities capabilities) {
    Proxy proxy = new Proxy();
    proxy.setProxyType(Proxy.ProxyType.PAC);
    proxy.setProxyAutoconfigUrl("http://proxyconf.glb.nsn-net.net/proxy.pac");
    capabilities.setCapability("proxy", proxy);
  }


  private static final FirefoxDriver getFirefoxDriver() {
    String firefoxExecutablePath = "";
    if (TestConfiguration.getProperty("cct.run.os").equals("windows")) {

      firefoxExecutablePath = TestConfiguration.getProperty("cct.run.browser.windows.firefoxPath");
    } else if (TestConfiguration.getProperty("cct.run.os").equals("linux")) {

      firefoxExecutablePath = TestConfiguration.getProperty("cct.run.browser.linux.firefoxPath");
    }
    System.setProperty("webdriver.gecko.driver", firefoxExecutablePath);
    logger.info("Firefox executable path: " + firefoxExecutablePath);
    FirefoxBinary firefox = new FirefoxBinary(new File(firefoxExecutablePath));
    firefox.addCommandLineOptions(new String[] { "-new-instance" });
    if (TestConfiguration.getProperty("cct.run.browser.headless").equalsIgnoreCase("true")) {
      // firefox.setEnvironmentProperty("DISPLAY", "localhost:20.0");
    }
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();

    if (TestConfiguration.getProperty("cct.proxy").equals("true")) {
      setProxy(capabilities);
    }

    FirefoxProfile profile = new FirefoxProfile();
    profile.setAcceptUntrustedCertificates(true);
    return new FirefoxDriver((Capabilities)capabilities);
  }
}

