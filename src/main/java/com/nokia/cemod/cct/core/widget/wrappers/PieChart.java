/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.PieChartSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.NoSuchElementException;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebDriverException;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PieChart
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private PieChartSettings settings;
/*    */   
/*    */   public PieChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 24 */     super(webdriver, widgetConfiguration);
/* 25 */     this.settings = this.settings;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 30 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/*    */     try {
/* 32 */       List<WebElement> pieelement = getListOfClickableElements();
/* 33 */       if (pieelement.size() == 0) {
/* 34 */         pieelement = this.widgetWrapper.findElements(
/* 35 */             By.cssSelector("#" + this.widgetConfiguration.getId() + " > svg >g > g:nth-child(4) > g > g"));
/*    */       }
/* 37 */       clickableCells.add(pieelement.get(0));
/* 38 */     } catch (NoSuchElementException e) {
/* 39 */       throw new CCTException("Exception in getting Pie Chart Elements: " + e.getMessage());
/*    */     } 
/* 41 */     return new ArrayList(clickableCells);
/*    */   }
/*    */   
/*    */   private List<WebElement> getListOfClickableElements() {
/* 45 */     return this.widgetWrapper.findElements(
/* 46 */         By.cssSelector("#" + this.widgetConfiguration.getId() + " > svg > g > g:nth-child(6) > g > g"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 54 */     WebUtils.waitForWidgetsToRender();
/* 55 */     WebUtils.waitForWidgetsToRender();
/* 56 */     WebElement webElement = (WebElement)element;
/* 57 */     WebUtils.scrollToElementAndCenter(this.webdriver, webElement);
/*    */     try {
/* 59 */       webElement.click();
/* 60 */     } catch (WebDriverException e) {
/*    */       try {
/* 62 */         List<WebElement> elements = getListOfClickableElements();
/* 63 */         if (elements.size() > 1) {
/* 64 */           ((WebElement)elements.get(1)).click();
/*    */         }
/* 66 */       } catch (WebDriverException ex) {
/* 67 */         WebUtils.captureScreenShot(this.webdriver);
/* 68 */         throw new CCTException("PieChart -> Click -> Unable to click on the widget. " + ex.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\PieChart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */