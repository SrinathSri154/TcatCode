/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeMap
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public TreeMap(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 18 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public void processWidget() throws CCTException {
/* 23 */     super.processWidget();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 28 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/* 29 */     List<WebElement> parentTileCells = getParentTileElements();
/* 30 */     if (parentTileCells.size() > 0) {
/* 31 */       List<WebElement> childTileCells = getChildTileElements(parentTileCells.get(0));
/* 32 */       if (childTileCells.size() > 0) {
/* 33 */         clickableCells.add(childTileCells.get(0));
/*    */       }
/*    */     } 
/* 36 */     return new ArrayList(clickableCells);
/*    */   }
/*    */   
/*    */   private List<WebElement> getParentTileElements() {
/* 40 */     return this.widgetWrapper.findElements(
/* 41 */         By.xpath("/descendant::*[@class='k-treemap-wrap']/descendant::*[@class='k-treemap-title']"));
/*    */   }
/*    */   
/*    */   private List<WebElement> getChildTileElements(WebElement parentTilename) {
/* 45 */     return this.widgetWrapper.findElements(By.xpath("/descendant::*[@class='k-treemap-wrap']/descendant::*[@class='k-treemap-title']/child::div[text()='" + parentTilename
/*    */           
/* 47 */           .getText() + "']/parent::div/following-sibling::*/child::node()/child::*"));
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\TreeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */