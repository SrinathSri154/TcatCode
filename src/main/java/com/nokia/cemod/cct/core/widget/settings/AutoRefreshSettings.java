/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutoRefreshSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   private static final String ONE_MIN = "1 min";
/*    */   private static final String FIVE_MINS = "5 mins";
/*    */   private static final String TEN_MINS = "10 mins";
/*    */   private static final String FIFTEEN_MINS = "15 mins";
/*    */   
/*    */   public AutoRefreshSettings(JsonNode settings) {
/* 19 */     super(settings);
/*    */   }
/*    */   
/*    */   public boolean isAutoRefreshEnabled() {
/* 23 */     boolean autoRefreshEnabled = false;
/* 24 */     JsonNode settingsRefreshEnabled = this.settings.get("refreshEnabled");
/* 25 */     if (settingsRefreshEnabled != null && settingsRefreshEnabled.asBoolean()) {
/* 26 */       autoRefreshEnabled = true;
/*    */     }
/* 28 */     return autoRefreshEnabled;
/*    */   }
/*    */   
/*    */   public String getAutoRefreshType() {
/* 32 */     String autoRefreshType = "checkbox";
/* 33 */     JsonNode settingsConfigType = this.settings.get("type");
/* 34 */     if (settingsConfigType != null) {
/* 35 */       autoRefreshType = settingsConfigType.asText();
/*    */     }
/* 37 */     return autoRefreshType;
/*    */   }
/*    */   
/*    */   public String getRefreshInterval() {
/* 41 */     String autoRefreshInterval = "1 min";
/* 42 */     JsonNode settingsRefreshInterval = this.settings.get("refreshInterval");
/* 43 */     if (settingsRefreshInterval != null) {
/* 44 */       autoRefreshInterval = settingsRefreshInterval.asText();
/*    */     }
/* 46 */     return autoRefreshInterval;
/*    */   }
/*    */ 
/*    */   
/*    */   public long convertRefreshIntervalToMilliseconds(String refreshInterval) {
/* 51 */     String timeInMilliseconds = Integer.toString(Integer.parseInt("1 min".split(" min")[0]) * 60 * 1000);
/* 52 */     if (refreshInterval.equalsIgnoreCase("1 min")) {
/* 53 */       timeInMilliseconds = Integer.toString(Integer.parseInt("1 min".split(" min")[0]) * 60 * 1000);
/* 54 */     } else if (refreshInterval.equalsIgnoreCase("5 mins")) {
/* 55 */       timeInMilliseconds = Integer.toString(Integer.parseInt("5 mins".split(" min")[0]) * 60 * 1000);
/* 56 */     } else if (refreshInterval.equalsIgnoreCase("10 mins")) {
/* 57 */       timeInMilliseconds = Integer.toString(Integer.parseInt("10 mins".split(" min")[0]) * 60 * 1000);
/* 58 */     } else if (refreshInterval.equalsIgnoreCase("15 mins")) {
/* 59 */       timeInMilliseconds = Integer.toString(Integer.parseInt("15 mins".split(" min")[0]) * 60 * 1000);
/*    */     } 
/* 61 */     long refreshIntervalInMilliseconds = Long.parseLong(timeInMilliseconds);
/* 62 */     return refreshIntervalInMilliseconds;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\AutoRefreshSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */