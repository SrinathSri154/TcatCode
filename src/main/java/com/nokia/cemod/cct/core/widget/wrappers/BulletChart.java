/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.BulletChartSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.NoSuchElementException;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BulletChart
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private BulletChartSettings settings;
/*    */   
/*    */   public BulletChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 22 */     super(webdriver, widgetConfiguration);
/* 23 */     this.settings = this.settings;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 28 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/*    */     try {
/* 30 */       clickableCells.add(this.widgetWrapper.findElement(By.cssSelector("#" + this.widgetConfiguration
/* 31 */               .getId() + " > svg >g > g > g > g > g > g > path:nth-child(1)")));
/* 32 */     } catch (NoSuchElementException e) {
/* 33 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*    */     } 
/* 35 */     return new ArrayList(clickableCells);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\BulletChart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */