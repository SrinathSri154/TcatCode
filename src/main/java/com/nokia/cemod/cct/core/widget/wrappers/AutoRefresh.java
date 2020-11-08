/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.AutoRefreshSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoRefresh
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private AutoRefreshSettings settings;
/*    */   
/*    */   public AutoRefresh(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 21 */     super(webdriver, widgetConfiguration);
/* 22 */     this.settings = (AutoRefreshSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isWidgetRendered() throws CCTException {
/* 27 */     boolean isWidgetRendered = false;
/* 28 */     String fetchAutoRefreshElementScript = "return ($('#" + this.widgetConfiguration.getId() + "-label-wrapper').length == 1);";
/*    */     
/* 30 */     isWidgetRendered = ((Boolean)WebUtils.executeJS(this.webdriver, fetchAutoRefreshElementScript)).booleanValue();
/* 31 */     return isWidgetRendered;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 36 */     List<Object> simpleReturnText = new ArrayList();
/* 37 */     if (this.settings.isAutoRefreshEnabled()) {
/* 38 */       simpleReturnText.add("sending a dummy text");
/*    */     }
/* 40 */     return simpleReturnText;
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 45 */     String refreshInterval = this.settings.getRefreshInterval();
/* 46 */     long refreshIntervalInMilliseconds = this.settings.convertRefreshIntervalToMilliseconds(refreshInterval);
/*    */     
/* 48 */     long emitTimeBeforeRefreshInterval = getAutoRefreshEmitTime();
/*    */     
/* 50 */     WebUtils.waitForAutoRefreshTimeInterval(refreshIntervalInMilliseconds);
/*    */     
/* 52 */     long emitTimeAfterRefreshInterval = getAutoRefreshEmitTime();
/*    */ 
/*    */     
/* 55 */     disableAutoRefresh();
/* 56 */     if (emitTimeAfterRefreshInterval - emitTimeBeforeRefreshInterval < refreshIntervalInMilliseconds) {
/* 57 */       throw new CCTException("AutoRefresh did not trigger for the configured refreshInterval of " + refreshInterval);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private long getAutoRefreshEmitTime() throws CCTException {
/* 64 */     long autoRefreshEmitTime = 0L;
/*    */     
/* 66 */     String fetchAutoRefreshEmitObjectScript = "return cemboard.getEmittedEventsStore().getEmittedData('" + this.widgetConfiguration.getId() + "');";
/* 67 */     Map<String, Object> autoRefreshEmitObject = (Map<String, Object>)WebUtils.executeJS(this.webdriver, fetchAutoRefreshEmitObjectScript);
/*    */     
/* 69 */     if (!autoRefreshEmitObject.isEmpty()) {
/* 70 */       autoRefreshEmitTime = ((Long)autoRefreshEmitObject.get("emitTime")).longValue();
/*    */     }
/* 72 */     return autoRefreshEmitTime;
/*    */   }
/*    */   
/*    */   private void disableAutoRefresh() throws CCTException {
/* 76 */     String disableAutoRefreshScript = "return";
/* 77 */     if (this.settings.getAutoRefreshType().equalsIgnoreCase("checkbox")) {
/* 78 */       disableAutoRefreshScript = "$('#" + this.widgetConfiguration.getId() + "-checkbox').click();";
/* 79 */     } else if (this.settings.getAutoRefreshType().equalsIgnoreCase("pulldown")) {
/*    */       
/* 81 */       disableAutoRefreshScript = "$('#" + this.widgetConfiguration.getId() + "').data('kendoDropDownList').value('off'); $('#" + this.widgetConfiguration.getId() + "').data('kendoDropDownList').trigger('change');";
/*    */     } 
/*    */     
/* 84 */     WebUtils.executeJS(this.webdriver, disableAutoRefreshScript);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\AutoRefresh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */