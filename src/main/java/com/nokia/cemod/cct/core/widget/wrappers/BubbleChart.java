/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.BubbleChartSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
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
/*    */ public class BubbleChart
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private BubbleChartSettings settings;
/*    */   List<String> clickableCells;
/*    */   HashMap<String, ArrayList<String>> kpiAndColorNames;
/*    */   
/*    */   public BubbleChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 28 */     super(webdriver, widgetConfiguration);
/* 29 */     this.settings = (BubbleChartSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 33 */     this.clickableCells = new ArrayList<String>();
/* 34 */     this.kpiAndColorNames = this.settings.getKpiNamesandColor();
/* 35 */     for (String kpiName : this.kpiAndColorNames.keySet()) {
/* 36 */       this.clickableCells.add(kpiName);
/*    */     }
/* 38 */     return new ArrayList(this.clickableCells);
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 43 */     Actions action = new Actions(this.webdriver);
/* 44 */     if (this.settings.isGrandientConfigured()) {
/* 45 */       List<WebElement> bubblesOnChart = getAllRenderedBubblesOnPage();
/* 46 */       if (bubblesOnChart.size() > 0) {
/* 47 */         action.moveToElement(bubblesOnChart.get(0)).click().perform();
/*    */       } else {
/* 49 */         logger.debug("BubbleChart: Could not find any bubbles to click in grandient configured");
/*    */       } 
/*    */     } else {
/* 52 */       disableAllLegends(action);
/* 53 */       WebUtils.waitForWidgetsToRender();
/* 54 */       String serieName = (String)element;
/* 55 */       enableLegend(action, serieName);
/* 56 */       ArrayList<String> kpiVal = this.kpiAndColorNames.get(serieName);
/* 57 */       List<WebElement> bubblesOnChart = findBubblesOnChart(kpiVal.get(0));
/* 58 */       if (bubblesOnChart.size() > 0) {
/* 59 */         action.moveToElement(bubblesOnChart.get(0)).click().perform();
/*    */       } else {
/* 61 */         logger.debug("BubbleChart: Could not find any bubbles to click for serieName: " + serieName);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private List<WebElement> getAllRenderedBubblesOnPage() {
/* 67 */     WebElement clipPath = this.widgetWrapper.findElement(By.tagName("clipPath"));
/* 68 */     return this.widgetWrapper
/* 69 */       .findElements(By.xpath(".//*[@clip-path='url(#" + clipPath.getAttribute("id") + ")']/child::node()"));
/*    */   }
/*    */   
/*    */   private void disableAllLegends(Actions action) throws CCTException {
/* 73 */     for (int kpi = 0; kpi < this.clickableCells.size(); kpi++) {
/* 74 */       WebElement legend = this.webdriver.findElement(By.xpath(".//div[@id='" + this.widgetConfiguration.getId() + "']//*[local-name() = 'text' and ./text() = '" + ((String)this.clickableCells
/* 75 */             .get(kpi)).trim() + "']"));
/* 76 */       if (!legend.getAttribute("fill").equals("#919191")) {
/* 77 */         WebUtils.scrollToElementAndCenter(this.webdriver, legend);
/* 78 */         WebUtils.waitForWidgetsToRender();
/* 79 */         action.moveToElement(legend).click().perform();
/* 80 */         WebUtils.waitForWidgetsToRender();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void enableLegend(Actions action, String serieName) throws CCTException {
/* 86 */     List<WebElement> elementsList = this.webdriver.findElements(By.xpath(".//div[@id='" + this.widgetConfiguration.getId() + "']//*[local-name() = 'text' and ./text() = '" + serieName
/* 87 */           .trim() + "']"));
/* 88 */     if (elementsList.size() > 0) {
/* 89 */       action.moveToElement(elementsList.get(elementsList.size() - 1)).click().perform();
/* 90 */       WebUtils.waitForWidgetsToRender();
/*    */     } 
/*    */   }
/*    */   
/*    */   private List<WebElement> findBubblesOnChart(String kpinamecolor) {
/* 95 */     WebElement elementid = this.widgetWrapper.findElement(By.tagName("clipPath"));
/* 96 */     return this.widgetWrapper.findElements(By.xpath(".//*[@clip-path='url(#" + elementid.getAttribute("id") + ")']/child::node()[@fill='" + kpinamecolor + "']"));
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\BubbleChart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */