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
/*    */ public class RadioButton
/*    */   extends CCTRenderedWidget
/*    */ {
/* 18 */   private static final Logger logger = Logger.getLogger(RadioButton.class);
/*    */   
/*    */   public RadioButton(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 21 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 27 */     List<WebElement> clickableCells = this.widgetWrapper.findElements(By.xpath(".//descendant::input[not(@disabled='')]/following-sibling::label"));
/* 28 */     logger.debug("RadioButton: Clickable Radio button size: " + clickableCells.size());
/* 29 */     return new ArrayList(clickableCells);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\RadioButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */