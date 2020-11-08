 package com.nokia.cemod.cct.selenium;

 import com.nokia.cemod.cct.utils.WebUtils;
 import com.nokia.cemod.cct.utils.conf.TestConfiguration;
 import cucumber.api.Scenario;
 import java.util.concurrent.TimeUnit;
 import org.apache.log4j.Logger;
 import org.openqa.selenium.Dimension;
 import org.openqa.selenium.OutputType;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebDriverException;
 import org.openqa.selenium.support.events.EventFiringWebDriver;









 public class SharedDriver
   extends EventFiringWebDriver
 {
   private static final Logger logger = Logger.getLogger(SharedDriver.class);

   public static final Dimension WINDOW_DIMENSION = new Dimension(3200, 1800);

   private static final WebDriver REAL_DRIVER;

   private static final Thread CLOSE_THREAD = new Thread()
     {
       public void run()
       {
         SharedDriver.REAL_DRIVER.quit();
       }
     };
   static {
     String browserType = TestConfiguration.getProperty("cct.run.browser");
     if (browserType.equalsIgnoreCase("chrome")) {
       logger.info("Running tests on Chrome");
       REAL_DRIVER = DriverFactory.getDriver(DriverType.CHROME);
     } else if (browserType.equalsIgnoreCase("firefox")) {
       logger.info("Running tests on Firefox");
       REAL_DRIVER = DriverFactory.getDriver(DriverType.FIREFOX);
     } else if (browserType.equalsIgnoreCase("remote")) {
       logger.info("Running tests in remote browser");
       REAL_DRIVER = DriverFactory.getDriver(DriverType.REMOTE);
     } else {

       logger.info("Running tests on Firefox");
       REAL_DRIVER = DriverFactory.getDriver(DriverType.FIREFOX);
     }


     Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);

     REAL_DRIVER.manage().timeouts().implicitlyWait(WebUtils.WEB_ELEMENT_TIME_OUT, TimeUnit.MILLISECONDS);
   }

   public SharedDriver() {
     super(REAL_DRIVER);
   }


   public void close() {
     if (Thread.currentThread() != CLOSE_THREAD) {
       throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
     }

     super.close();
   }

   public void deleteAllCookies() {
     manage().deleteAllCookies();
   }

   public void embedScreenshot(Scenario scenario) {
     try {
       byte[] screenshot = (byte[])getScreenshotAs(OutputType.BYTES);
       scenario.embed(screenshot, "image/png");
     } catch (WebDriverException somePlatformsDontSupportScreenshots) {
       System.err.println(somePlatformsDontSupportScreenshots.getMessage());
     }
   }

   enum DriverType {
     FIREFOX,
     CHROME,
     REMOTE;
   }
 }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\selenium\SharedDriver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */