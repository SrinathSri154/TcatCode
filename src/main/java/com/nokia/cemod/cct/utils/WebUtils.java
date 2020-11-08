/*     */ package com.nokia.cemod.cct.utils;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.utils.conf.ScenarioConfiguration;
/*     */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.junit.Assert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.ElementNotVisibleException;
/*     */ import org.openqa.selenium.JavascriptExecutor;
/*     */ import org.openqa.selenium.Keys;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.OutputType;
/*     */ import org.openqa.selenium.StaleElementReferenceException;
/*     */ import org.openqa.selenium.TakesScreenshot;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebDriverException;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.support.ui.ExpectedCondition;
/*     */ import org.openqa.selenium.support.ui.ExpectedConditions;
/*     */ import org.openqa.selenium.support.ui.WebDriverWait;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WebUtils
/*     */ {
/*  44 */   public static final long WEB_ELEMENT_TIME_OUT = Long.parseLong(TestConfiguration.getProperty("cct.waitForWebElementTimeOut")) / 1000L;
/*     */   
/*  46 */   protected static final long QUERY_RESPONSE_TIME_OUT = Long.parseLong(
/*  47 */       TestConfiguration.getProperty("cct.waitForQueryResponseTimeOut")) / 1000L;
/*     */ 
/*     */   
/*  50 */   protected static final long WIDGETS_TO_RENDER_WAIT_TIME = Long.parseLong(TestConfiguration.getProperty("cct.waitForWidgetsToRenderTime"));
/*     */   
/*  52 */   private static final Logger logger = Logger.getLogger(WebUtils.class);
/*     */   
/*     */   public static void waitForElementBy(final WebDriver driver, final By by, long time) throws CCTException {
/*     */     try {
/*  56 */       (new WebDriverWait(driver, time)).until((Function)new ExpectedCondition<Boolean>()
/*     */           {
/*     */             public Boolean apply(WebDriver webdriver) {
/*     */               try {
/*  60 */                 WebUtils.logger.debug("\nWaiting for element: " + WebUtils.findElementByInLoop(driver, by));
/*  61 */                 return Boolean.valueOf(WebUtils.findElementByInLoop(driver, by).isDisplayed());
/*  62 */               } catch (CCTException e) {
/*  63 */                 WebUtils.logger.error("\nException in waitForElementBy findElementBy" + by);
/*     */                 
/*  65 */                 return Boolean.valueOf(false);
/*     */               }  }
/*     */           });
/*  68 */     } catch (Exception e) {
/*  69 */       captureScreenShot(driver);
/*  70 */       throw new CCTException("Exception in waitForElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitForElementToDisplay(WebDriver driver, By by, int maxWaitTime) throws InterruptedException, CCTException {
/*  76 */     int waitIntervalSeconds = 1;
/*  77 */     int totalWaitTimeInSeconds = 0;
/*  78 */     WebElement element = findElementBy(driver, by);
/*  79 */     while (!element.isDisplayed() && totalWaitTimeInSeconds < maxWaitTime) {
/*  80 */       totalWaitTimeInSeconds++;
/*  81 */       Thread.sleep((waitIntervalSeconds * 1000));
/*     */     } 
/*  83 */     if (!element.isDisplayed()) {
/*  84 */       throw new CCTException("Exception in waitForElementToDisplay: element is not displayed");
/*     */     }
/*     */   }
/*     */   
/*     */   public static WebElement findElementByInLoop(WebDriver driver, By by) throws CCTException {
/*     */     try {
/*  90 */       WebElement element = driver.findElement(by);
/*  91 */       return element;
/*  92 */     } catch (Exception e) {
/*  93 */       throw new CCTException("Exception in findElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void waitForElementBy(WebDriver driver, By by) throws CCTException {
/*  98 */     waitForElementBy(driver, by, WEB_ELEMENT_TIME_OUT);
/*     */   }
/*     */   
/*     */   public static WebElement waitAndFindElementBy(WebDriver driver, By by) throws CCTException {
/*     */     try {
/* 103 */       return (WebElement)(new WebDriverWait(driver, WEB_ELEMENT_TIME_OUT))
/* 104 */         .until((Function)ExpectedConditions.presenceOfElementLocated(by));
/* 105 */     } catch (Exception e) {
/* 106 */       captureScreenShot(driver);
/* 107 */       throw new CCTException("Exception in waitAndFindElementBy: " + e.getMessage());
/*     */     } 
/*     */   }

            public static List<WebElement> waitAndFindElementsBy(WebDriver driver, By by) throws CCTException {
                 try {
                   return (List<WebElement>) (new WebDriverWait(driver, WEB_ELEMENT_TIME_OUT))
                     .until((Function)ExpectedConditions.presenceOfAllElementsLocatedBy(by));
                 } catch (Exception e) {
                   captureScreenShot(driver);
                   throw new CCTException("Exception in waitAndFindElementsBy: " + e.getMessage());
                 }
               }
/*     */   
/*     */   public static void waitForQueryResponseCompletion(WebDriver driver, By by) throws CCTException {
/*     */     try {
/* 113 */       (new WebDriverWait(driver, QUERY_RESPONSE_TIME_OUT))
/* 114 */         .until((Function)ExpectedConditions.invisibilityOfElementLocated(by));
/* 115 */     } catch (Exception e) {
/* 116 */       captureScreenShot(driver);
/* 117 */       throw new CCTException("Exception in waitForQueryResponseCompletion - Query Response not received within the configured Timeout");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void waitForElementToBeVisibleAndClick(WebDriver driver, WebElement element) throws CCTException {
/*     */     try {
/* 125 */       (new WebDriverWait(driver, WEB_ELEMENT_TIME_OUT)).until((Function)ExpectedConditions.visibilityOf(element));
/* 126 */       element.click();
/* 127 */     } catch (Exception e) {
/* 128 */       captureScreenShot(driver);
/* 129 */       throw new CCTException("Exception in waitForElementToBeVisibleAndClick");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitForElementToBeClickabableAndSendKeys(WebDriver driver, By locator, String value) throws CCTException {
/*     */     try {
/* 136 */       (new WebDriverWait(driver, WEB_ELEMENT_TIME_OUT)).until((Function)ExpectedConditions.elementToBeClickable(locator));
/* 137 */       findElementBy(driver, locator).sendKeys(new CharSequence[] { value });
/* 138 */     } catch (Exception e) {
/* 139 */       captureScreenShot(driver);
/* 140 */       throw new CCTException("Exception in waitForElementToBeVisibleAndClick");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void performEnterOperationOnElement(WebDriver driver, By locator) throws CCTException {
/* 145 */     findElementBy(driver, locator).sendKeys(new CharSequence[] { (CharSequence)Keys.ENTER });
/*     */   }
/*     */   
/*     */   public static void waitAndClickForElementBy(WebDriver driver, By by) throws CCTException {
/*     */     try {
/* 150 */       waitForElementBy(driver, by);
/* 151 */       WebElement webElement = driver.findElement(by);
/* 152 */       scrollToElementAndCenter(driver, webElement);
/* 153 */       webElement.click();
/* 154 */     } catch (Exception e) {
/* 155 */       captureScreenShot(driver);
/* 156 */       throw new CCTException("Exception in waitAndClickForElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void waitAndClickElement(WebDriver driver, WebElement element) throws CCTException {
/*     */     try {
/* 162 */       waitForElement(driver, element);
/*     */       
/* 164 */       element.click();
/* 165 */     } catch (Exception e) {
/* 166 */       captureScreenShot(driver);
/* 167 */       throw new CCTException("Exception in waitAndClickElement: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void waitForElement(WebDriver driver, WebElement element) throws CCTException {
/* 172 */     waitForElement(driver, element, WEB_ELEMENT_TIME_OUT);
/*     */   }
/*     */   
/*     */   public static void waitForElement(WebDriver driver, final WebElement element, long time) throws CCTException {
/*     */     try {
/* 177 */       (new WebDriverWait(driver, time)).until((Function)new ExpectedCondition<Boolean>()
/*     */           {
/*     */             public Boolean apply(WebDriver webdriver) {
/*     */               try {
/* 181 */                 WebUtils.logger.debug("\nWaiting for element: " + element);
/* 182 */                 return Boolean.valueOf(element.isDisplayed());
/* 183 */               } catch (ElementNotVisibleException e) {
/* 184 */                 WebUtils.logger.error("\nElementNotVisibleException in waitForElement " + element);
/*     */                 
/* 186 */                 return Boolean.valueOf(false);
/*     */               }  }
/*     */           });
/* 189 */     } catch (Exception e) {
/* 190 */       captureScreenShot(driver);
/* 191 */       throw new CCTException("Exception in waitForElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static WebElement findElementBy(WebDriver driver, By by) throws CCTException {
/*     */     try {
/* 197 */       WebElement element = driver.findElement(by);
/* 198 */       return element;
/* 199 */     } catch (Exception e) {
/* 200 */       captureScreenShot(driver);
/* 201 */       throw new CCTException("Exception in findElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static List<WebElement> findElementsBy(WebDriver driver, By by) throws CCTException {
/*     */     try {
/* 207 */       return driver.findElements(by);
/* 208 */     } catch (Exception e) {
/* 209 */       captureScreenShot(driver);
/* 210 */       throw new CCTException("Exception in findElementsBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitAndAssertForElementBy(WebDriver driver, By by, String strValue) throws CCTException {
/*     */     try {
/* 217 */       waitForElementBy(driver, by);
/* 218 */       Assert.assertEquals(strValue, findElementBy(driver, by).getText());
/* 219 */     } catch (Exception e) {
/* 220 */       captureScreenShot(driver);
/* 221 */       throw new CCTException("Exception in waitAndAssertForElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitAndSendKeysForElementBy(WebDriver driver, By by, String fieldValue) throws CCTException {
/*     */     try {
/* 228 */       waitForElementBy(driver, by);
/* 229 */       driver.findElement(by).clear();
/* 230 */       driver.findElement(by).sendKeys(new CharSequence[] { fieldValue });
/* 231 */     } catch (Exception e) {
/* 232 */       captureScreenShot(driver);
/* 233 */       throw new CCTException("Exception in waitAndSendKeysForElementBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object executeJS(WebDriver driver, String script) throws CCTException {
/* 238 */     JavascriptExecutor js = (JavascriptExecutor)driver;
/*     */     try {
/* 240 */       return js.executeScript(script, new Object[0]);
/* 241 */     } catch (WebDriverException e) {
/* 242 */       throw new CCTException("Exception in executeJS: " + e.getMessage() + "- Detailed Cause: " + e.getCause());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Object executeJSWithWebElement(WebDriver driver, String script, WebElement element) throws CCTException {
/*     */     try {
/* 249 */       JavascriptExecutor js = (JavascriptExecutor)driver;
/* 250 */       return js.executeScript(script, new Object[] { element });
/* 251 */     } catch (WebDriverException e) {
/* 252 */       throw new CCTException("Exception in executeJS: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void scrollElementDown(WebDriver driver, WebElement element) {
/* 257 */     ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", new Object[] { element });
/*     */   }
/*     */   
/*     */   public static void scrollElementUp(WebDriver driver, WebElement element) {
/* 261 */     ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", new Object[] { element });
/*     */   }
/*     */   
/*     */   public static void scrollToElementAndCenter(WebDriver driver, WebElement element) {
/* 265 */     String scrollElementIntoMiddleScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);var viewPortWidth = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);var elementTop = arguments[0].getBoundingClientRect().top;var elementLeft = arguments[0].getBoundingClientRect().left;var scrollByPositionY = elementTop-(viewPortHeight/2);var scrollByPositionX = elementLeft-(viewPortWidth/2);window.scrollBy(scrollByPositionX, scrollByPositionY);";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     ((JavascriptExecutor)driver).executeScript(scrollElementIntoMiddleScript, new Object[] { element });
/*     */   }
/*     */   
/*     */   public static void waitForWidgetsToRender() throws CCTException {
/*     */     try {
/* 277 */       Thread.sleep(WIDGETS_TO_RENDER_WAIT_TIME);
/* 278 */     } catch (InterruptedException e) {
/* 279 */       throw new CCTException("Timeout - Could not wait for widgets to render");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void waitForUseCaseToLoad(WebDriver driver) throws CCTException {
/* 284 */     boolean isUseCaseLoaded = false;
/* 285 */     long maxUseCaseLoadTime = 40000L;
/* 286 */     long startTime = System.currentTimeMillis();
/* 287 */     while (!isUseCaseLoaded) {
/*     */       try {
/* 289 */         if ("true".equals(executeJS(driver, "return cemboard.isDashboardLoaded;").toString())) {
/* 290 */           isUseCaseLoaded = true; continue;
/* 291 */         }  if (System.currentTimeMillis() - startTime > maxUseCaseLoadTime) {
/* 292 */           throw new CCTException("Timeout - Could not load use case within 40 seconds. Please check errors in use case.");
/*     */         }
/*     */       }
/* 295 */       catch (CCTException e) {
/* 296 */         logger.error(e.getMessage());
/* 297 */         throw new CCTException("Timeout - Could not load use case within 40 seconds. Please check errors in use case.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitForAutoRefreshTimeInterval(long refreshInterval) throws CCTException {
/*     */     try {
/* 305 */       Thread.sleep(refreshInterval);
/* 306 */     } catch (InterruptedException e) {
/* 307 */       throw new CCTException("Timeout - Could not wait for refresh interval to refresh");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void waitForElementPresentBy(final WebDriver driver, final By by) throws CCTException {
/*     */     try {
/* 314 */       WebDriverWait wait = (WebDriverWait)(new WebDriverWait(driver, WEB_ELEMENT_TIME_OUT)).ignoring(StaleElementReferenceException.class);
/* 315 */       wait.until((Function)new ExpectedCondition<Boolean>()
/*     */           {
/*     */             public Boolean apply(WebDriver webDriver) {
/*     */               try {
/* 319 */                 WebElement element = webDriver.findElement(by);
/* 320 */                 WebUtils.logger.debug("\nWaiting for element: " + WebUtils.findElementBy(driver, by));
/* 321 */                 return Boolean.valueOf((element != null && element.isDisplayed()));
/* 322 */               } catch (CCTException e) {
/* 323 */                 WebUtils.logger.error("\nException in waitForElementPresentBy findElementBy");
/*     */                 
/* 325 */                 return Boolean.valueOf(false);
/*     */               }  }
/*     */           });
/* 328 */     } catch (NoSuchElementException|org.openqa.selenium.TimeoutException|ElementNotVisibleException e) {
/* 329 */       captureScreenShot(driver);
/* 330 */       throw new CCTException("Exception in waitForElementPresentBy: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void captureScreenShot(WebDriver driver) {
/* 335 */     if (TestConfiguration.getProperty("cct.env.captureScreenshots", "true").equals("true")) {
/* 336 */       String timestampFormat = "yyyy-MM-dd_hh-mm-ss-SSS";
/* 337 */       Date date = new Date();
/* 338 */       SimpleDateFormat format = new SimpleDateFormat(timestampFormat);
/* 339 */       String scenarioName = ScenarioConfiguration.getScenarioName().replaceAll("[ ,:]", "_");
/*     */       
/* 341 */       String fileName = "output/" + System.getProperty("cct.runnable.node.round") + "/" + "screenshots" + "/" + scenarioName;
/*     */       
/* 343 */       fileName = fileName + "_" + format.format(date) + ".png";
/* 344 */       logger.debug("Capturing screen shot with file name as  : " + fileName);
/* 345 */       TakesScreenshot screen = (TakesScreenshot)driver;
/* 346 */       File srcFile = (File)screen.getScreenshotAs(OutputType.FILE);
/* 347 */       File destFile = new File(fileName);
/*     */       try {
/* 349 */         FileUtils.copyFile(srcFile, destFile);
/* 350 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<String, Object> jsonToMap(String jsonString) throws JSONException {
/* 356 */     HashMap<String, Object> map = new HashMap<String, Object>();
/* 357 */     JSONObject jObject = new JSONObject(jsonString);
/* 358 */     Iterator<?> keys = jObject.keys();
/* 359 */     while (keys.hasNext()) {
/* 360 */       String key = (String)keys.next();
/* 361 */       Object value = jObject.get(key);
/* 362 */       map.put(key, value);
/*     */     } 
/* 364 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void validateQueryResponseReceived(WebDriver driver, WidgetConfiguration widgetConfiguration) throws CCTException {
/* 371 */     By loadingIndicatorElementLocator = By.xpath(String.format("//p[contains(text(), '%s') and @class= 'componentDetails']/parent::div/parent::div/parent::div//div[contains(@id, 'progressIndicatorCircular')]", new Object[] { widgetConfiguration.getId() }));
/* 372 */     int loadingIndicatorElementLength = findElementsBy(driver, loadingIndicatorElementLocator).size();
/* 373 */     if (loadingIndicatorElementLength > 0)
/* 374 */       waitForQueryResponseCompletion(driver, loadingIndicatorElementLocator); 
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\WebUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */