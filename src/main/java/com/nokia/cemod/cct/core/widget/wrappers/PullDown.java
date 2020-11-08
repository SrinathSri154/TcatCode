/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import com.nokia.cemod.cct.utils.conf.ScenarioConfiguration;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/*     */
/*     */ 
/*     */ 
/*     */ public class PullDown
/*     */   extends CCTRenderedWidget
/*     */ {
/*  21 */   private static final Logger logger = Logger.getLogger(PullDown.class);
/*     */   
/*     */   public PullDown(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  24 */     super(webdriver, widgetConfiguration);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWidgetRendered() throws CCTException {
/*  29 */     boolean isWidgetRendered = false;
/*  30 */     String isPullDownRenderedScript = "if ($('#" + this.widgetConfiguration.getId() + "').data('kendoDropDownList')) {return true; } else {return false; }";
/*     */     
/*  32 */     isWidgetRendered = ((Boolean)WebUtils.executeJS(this.webdriver, isPullDownRenderedScript)).booleanValue();
/*  33 */     validateQueryResponseReceived();
/*  34 */     validateNoDataScenario();
/*  35 */     return isWidgetRendered;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*     */     try {
/*  41 */       if (ScenarioConfiguration.isTestAllPulldownCombinationsEnabled()) {
/*  42 */         return getAllPossibleClickableElements();
/*     */       }
/*  44 */       List<WebElement> clickableCells = new ArrayList<WebElement>();
/*  45 */       clickableCells.add(this.webdriver
/*  46 */           .findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[1]")));
/*  47 */       return new ArrayList(clickableCells);
/*     */     }
/*  49 */     catch (NoSuchElementException e) {
/*  50 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getAllPossibleClickableElements() throws CCTException {
/*     */     try {
/*  57 */       List<WebElement> clickableCells = new ArrayList<WebElement>();
/*  58 */       clickableCells.addAll(this.webdriver
/*  59 */           .findElements(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li")));
/*  60 */       return new ArrayList(clickableCells);
/*  61 */     } catch (NoSuchElementException e) {
/*  62 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClickableElementForValue(String value) throws CCTException {
/*  68 */     clickOnBaseElement();
/*  69 */     clickOnSearchElement(value);
/*  70 */     List<WebElement> elements = this.webdriver.findElements(
/*  71 */         By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[text()='" + value + "']"));
/*  72 */     clickOnBaseElement();
/*  73 */     if (elements.size() == 1)
/*  74 */       return elements.get(0); 
/*  75 */     if (elements.size() > 1) {
/*  76 */       throw new CCTException("Mutliple WebElements found with same value: " + value);
/*     */     }
/*  78 */     throw new CCTException("WebElement Not found");
/*     */   }
/*     */ 
/*     */   
/*     */   private void clickOnSearchElement(String value) throws CCTException {
/*  83 */     this.webdriver.findElement(By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "-list']/span/input")).click();
/*  84 */     this.webdriver.findElement(By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "-list']/span/input"))
/*  85 */       .sendKeys(new CharSequence[] { value });
/*  86 */     WebUtils.waitForWidgetsToRender();
/*     */   }
/*     */   
/*     */   private void clickOnBaseElement() throws CCTException {
/*     */     WebElement base;
/*  91 */     if (this.widgetConfiguration.getTitle() == null || this.widgetConfiguration.getTitle().equals("")) {
/*  92 */       base = (WebElement)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/*  93 */           .getId() + "').parent()[0]");
/*     */     } else {
/*     */       
/*  96 */       base = this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "-label-wrapper']/span"));
/*     */     } 
/*  98 */     WebUtils.scrollToElementAndCenter(this.webdriver, base);
/*  99 */     logger.info(this.widgetConfiguration.getId() + "," + base.getText());
/* 100 */     WebUtils.waitAndClickElement(this.webdriver, base);
/* 101 */     WebUtils.waitForWidgetsToRender();
/*     */   }
/*     */ 
/*     */   
/*     */   public void click(Object element) throws CCTException {
/* 106 */     WebElement webElement = (WebElement)element;
/* 107 */     clickOnBaseElement();
/* 108 */     WebUtils.waitAndClickElement(this.webdriver, webElement);
/*     */   }
/*     */   
/*     */   private void validateNoDataScenario() throws CCTException {
/* 112 */     long pullDownDataLength = ((Long)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/* 113 */         .getId() + "').data('kendoDropDownList').dataSource.data().length")).longValue();
/* 114 */     if (pullDownDataLength == 0L) {
/* 118 */       String errorMessage = WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration.getId() + "').data('kendoDropDownList').noData.text()").toString();
/* 119 */       throw new CCTException(errorMessage);
/*     */     } 
/*     */   }

            public void verifyListItems(String values) throws CCTException {
                try {
                    String[] options = values.split("\\s*,\\s*");
                    StringBuilder temp = new StringBuilder();
                    int count=0;
                    List<Object> listItems = getAllPossibleClickableElements();
                    for (String option : options) {
                        List<WebElement> elements = this.webdriver.findElements(
                                By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[contains(text(),'" + option + "')]"));
                        if (elements.size() == 1)
                            count++;
                        else if(elements.size()>1)  {
                            for(WebElement li : elements) {
                                if(option.equalsIgnoreCase(li.getAttribute("textContent").trim())){
                                    count++;
                                    break;
                                }
                            }
                        }
                        else
                            temp.append(option).append(", ");
                    }
                    if (!(count == options.length))
                        throw new CCTException("Values not present in pulldown: " +temp.substring(0,temp.length()-2));
                } catch (CCTException e) {
                    throw new CCTException("Exception in verifyListItems: " + e.getMessage());
                }
            }

/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\PullDown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */