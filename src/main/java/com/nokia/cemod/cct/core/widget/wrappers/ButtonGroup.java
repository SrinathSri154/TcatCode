/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.ButtonGroupSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.junit.Assert;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonGroup
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private ButtonGroupSettings settings;
/*    */   
/*    */   public ButtonGroup(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 22 */     super(webdriver, widgetConfiguration);
/* 23 */     this.settings = (ButtonGroupSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 28 */     List<WebElement> buttonGroupValues = this.widgetWrapper.findElements(By.className("buttonlist"));
/* 29 */     Assert.assertTrue("Button group rendered and configured values does not match: " + this.widgetConfiguration.getId(), 
/*    */         
/* 31 */         (this.widgetWrapper.findElements(By.className("buttonlist")).size() == this.settings.getButtonGroupValues().size()));
/* 32 */     return new ArrayList(buttonGroupValues);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClickableElementForValue(String value) throws CCTException {
/* 37 */     List<Object> elements = getAllPossibleClickableElements();
/* 38 */     for (Object element : elements) {
/* 39 */       WebElement webElement = (WebElement)element;
/* 40 */       String buttonText = webElement.findElement(By.className("km-text")).getText();
/* 41 */       if (value.equalsIgnoreCase(buttonText)) {
/* 42 */         return webElement;
/*    */       }
/*    */     } 
/* 45 */     throw new CCTException("WebElement Not found");
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\ButtonGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */