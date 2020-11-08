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
/*    */ 
/*    */ 
/*    */ public class Label
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public Label(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 19 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 24 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getClickableElementForValue(String value) throws CCTException {
/* 30 */     String labelText = WebUtils.findElementBy(this.webdriver, By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "']")).getText();
/* 31 */     ((StringAssert)CCTSoftAssertions.getInstance().assertThat(labelText)
/* 32 */       .as(this.widgetConfiguration.getId() + " Label Text not available", new Object[0])).isEqualTo(value);
/* 33 */     return null;
/*    */   }
/*    */   
/*    */   public void click(Object element) throws CCTException {}
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Label.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */