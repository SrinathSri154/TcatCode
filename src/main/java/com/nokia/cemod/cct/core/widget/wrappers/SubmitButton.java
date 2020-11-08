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
/*    */ 
/*    */ public class SubmitButton
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public SubmitButton(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 19 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 24 */     List<WebElement> elements = new ArrayList<WebElement>();
/* 25 */     elements.add(this.widgetWrapper);
/* 26 */     return new ArrayList(elements);
/*    */   }
/*    */   
/*    */   public void validateSubmitButtonMessage(String message) throws CCTException {
/* 30 */     WebUtils.waitAndAssertForElementBy(this.webdriver, By.className("submitCustomMessage"), message);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\SubmitButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */