/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ApplyButton
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public ApplyButton(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 17 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 22 */     List<WebElement> elements = new ArrayList<WebElement>();
/* 23 */     elements.add(this.widgetWrapper);
/* 24 */     return new ArrayList(elements);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\ApplyButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */