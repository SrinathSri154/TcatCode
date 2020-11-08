/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RadarChart
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public RadarChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 16 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 21 */     return new ArrayList();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\RadarChart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */