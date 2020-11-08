/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ public class ExportButton
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public ExportButton(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 18 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 24 */     List<WebElement> clickableCells = this.widgetWrapper.findElements(By.tagName("button"));
/* 25 */     return new ArrayList(clickableCells);
/*    */   }
/*    */   
/*    */   public void validateExportButtonMessage(String message) throws CCTException {
/* 29 */     WebUtils.waitAndAssertForElementBy(this.webdriver, By.className("customMessage"), message);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\ExportButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */