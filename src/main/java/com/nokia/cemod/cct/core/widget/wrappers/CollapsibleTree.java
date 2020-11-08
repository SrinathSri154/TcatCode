/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.CollapsibleTreeSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ import org.openqa.selenium.interactions.Actions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollapsibleTree
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private CollapsibleTreeSettings settings;
/*    */   
/*    */   public CollapsibleTree(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 24 */     super(webdriver, widgetConfiguration);
/* 25 */     this.settings = (CollapsibleTreeSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */ 
/*    */   
/*    */   public void processWidget() throws CCTException {
/* 30 */     super.processWidget();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 36 */     List<WebElement> circleElements = getCurrentOpenNodes();
/*    */ 
/*    */     
/* 39 */     circleElements.remove(0);
/* 40 */     circleElements.remove(0);
/*    */ 
/*    */     
/* 43 */     if (this.settings.getExpansionLevel() > 0) {
/* 44 */       Collections.reverse(circleElements);
/*    */     } else {
/* 46 */       circleElements = openAllNodesAndGetAllNodes(circleElements);
/* 47 */       Collections.reverse(circleElements);
/*    */     } 
/*    */     
/* 50 */     return new ArrayList(circleElements);
/*    */   }
/*    */   
/*    */   private List<WebElement> openAllNodesAndGetAllNodes(List<WebElement> circleElements) throws CCTException {
/* 54 */     for (int i = 0; i < circleElements.size(); i++) {
/*    */       try {
/* 56 */         ((WebElement)circleElements.get(i)).findElement(By.tagName("text")).click();
/* 57 */         WebUtils.waitForWidgetsToRender();
/* 58 */       } catch (Exception e) {
/* 59 */         logger.error("Exception occured while clicking : Nodes may be overlapping with each other" + e.getMessage());
/*    */       } 
/*    */       
/* 62 */       if (i == circleElements.size() - 1) {
/* 63 */         List<WebElement> newCircleElements = getCurrentOpenNodes();
/* 64 */         circleElements = newCircleElements;
/*    */       } 
/*    */     } 
/* 67 */     return circleElements;
/*    */   }
/*    */   
/*    */   private List<WebElement> getCurrentOpenNodes() throws CCTException {
/* 71 */     return (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $(\"#" + this.widgetConfiguration.getId() + "\")[0].getElementsByTagName('g')");
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 76 */     WebElement circleElement = (WebElement)element;
/* 77 */     Actions action = new Actions(this.webdriver);
/*    */     try {
/* 79 */       WebElement elementToClick = circleElement.findElement(By.tagName("text"));
/* 80 */       WebUtils.scrollToElementAndCenter(this.webdriver, elementToClick);
/* 81 */       action.moveToElement(elementToClick).click().perform();
/* 82 */     } catch (Exception e) {
/* 83 */       logger.error("Exception occured while clicking : Nodes may be overlapping with each other" + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\CollapsibleTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */