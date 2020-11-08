/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.HeatMapSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ import org.openqa.selenium.interactions.Actions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeatMap
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private WebElement heatMapRow;
/*    */   private HeatMapSettings settings;
/*    */   private List<WebElement> clickableCells;
/*    */   
/*    */   public HeatMap(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 27 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 32 */     List<WebElement> numberOfHeatMapRows = findNumberOfHeatMapRows();
/* 33 */     if (numberOfHeatMapRows.size() > 0) {
/* 34 */       WebElement firstRow = findNumberOfHeatMapRows().get(0);
/* 35 */       this.clickableCells = firstRow.findElements(By.tagName("td"));
/* 36 */       return new ArrayList(this.clickableCells);
/*    */     } 
/* 38 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void processWidget() throws CCTException {
/* 44 */     super.processWidget();
/* 45 */     this.heatMapRow = getHeatMapRow();
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 50 */     Actions action = new Actions(this.webdriver);
/* 51 */     WebElement row = (WebElement)element;
/* 52 */     action.moveToElement(row).click().perform();
/*    */   }
/*    */   
/*    */   private List<WebElement> findNumberOfHeatMapRows() throws CCTException {
/* 56 */     WebUtils.waitForWidgetsToRender();
/* 57 */     return this.heatMapRow.findElements(By.xpath(".//tbody/tr"));
/*    */   }
/*    */   
/*    */   public WebElement getHeatMapRow() {
/* 61 */     return this.widgetWrapper.findElements(By.tagName("table")).get(1);
/*    */   }
/*    */   
/*    */   public void setHeatMapRow(WebElement heatMapRow) {
/* 65 */     this.heatMapRow = heatMapRow;
/*    */   }
/*    */   
/*    */   public HeatMapSettings getSettings() {
/* 69 */     return this.settings;
/*    */   }
/*    */   
/*    */   public void setSettings(HeatMapSettings settings) {
/* 73 */     this.settings = settings;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\HeatMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */