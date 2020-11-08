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
/*    */ public class Quote
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public Quote(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 19 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 24 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWidgetRendered() throws CCTException {
/* 29 */     boolean isWidgetRendered = false;
/* 30 */     isWidgetRendered = this.widgetWrapper.isDisplayed();
/* 31 */     validateQueryResponseReceived();
/* 32 */     validateErrorScenario();
/* 33 */     return isWidgetRendered;
/*    */   }
/*    */ 
/*    */   
/*    */   private void validateErrorScenario() throws CCTException {
/* 38 */     By quoteErrorPath = By.xpath(String.format(".//*[@id='" + this.widgetConfiguration.getId() + "']//*[@class='quoteErrorMessage']", new Object[0]));
/* 39 */     List<WebElement> quoteElement = WebUtils.findElementsBy(this.webdriver, quoteErrorPath);
/* 40 */     if (quoteElement.size() != 0) {
/* 41 */       String quoteError = ((WebElement)quoteElement.get(0)).getText();
/* 42 */       throw new CCTException(quoteError);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Quote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */