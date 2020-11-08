/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.assertj.core.api.StringAssert;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ public class TextBox
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public TextBox(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 20 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 25 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClickableElementForValue(String value) throws CCTException {
/* 30 */     this.widgetWrapper.clear();
/* 31 */     this.widgetWrapper.sendKeys(new CharSequence[] { value });
/* 32 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {}
/*    */ 
/*    */   
/*    */   public void validateTextBox(String widgetId, String value) throws CCTException {
/* 41 */     WebElement element = WebUtils.findElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']"));
/* 42 */     ((StringAssert)CCTSoftAssertions.getInstance().assertThat(element.getAttribute("value")).as(widgetId, new Object[0])).isEqualTo(value);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\TextBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */