/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckBox
/*    */   extends CCTRenderedWidget
/*    */ {
/* 18 */   private static final Logger logger = Logger.getLogger(CheckBox.class);
/*    */   
/*    */   public CheckBox(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 21 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 26 */     List<WebElement> clickableCells = getAllCheckBoxes();
/* 27 */     if (clickableCells.isEmpty()) {
/* 28 */       logger.debug("CheckBox: No clickable elements found");
/*    */     }
/* 30 */     return new ArrayList(clickableCells);
/*    */   }
/*    */ 
/*    */   
/*    */   private List<WebElement> getAllCheckBoxes() throws CCTException {
/* 35 */     List<WebElement> listofcheckboxes = this.widgetWrapper.findElements(By.xpath(".//descendant::input[not(@disabled='')]"));
/* 36 */     List<WebElement> listOfUncheckedWebElements = new ArrayList<WebElement>();
/* 37 */     if (listofcheckboxes.size() > 0) {
/* 38 */       for (WebElement el : listofcheckboxes) {
/* 39 */         String elid = el.getAttribute("id");
/* 40 */         WebElement selectedElement = this.widgetWrapper.findElement(
/* 41 */             By.xpath("/descendant::input[not(@disabled='')][@id='" + elid + "']/following-sibling::label"));
/* 42 */         listOfUncheckedWebElements.add(selectedElement);
/*    */       } 
/*    */     }
/* 45 */     return listOfUncheckedWebElements;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\CheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */