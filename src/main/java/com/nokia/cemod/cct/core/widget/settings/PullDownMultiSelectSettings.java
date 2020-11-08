/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class PullDownMultiSelectSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public PullDownMultiSelectSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public String getTitle() {
/* 15 */     String pulldownTitle = "";
/* 16 */     if (this.settings.has("title")) {
/* 17 */       pulldownTitle = this.settings.get("title").asText();
/*    */     }
/* 19 */     return pulldownTitle;
/*    */   }
/*    */   
/*    */   public String getSelectType() {
/* 23 */     String pulldownSelectType = "Multiple";
/* 24 */     pulldownSelectType = this.settings.get("selectType").asText();
/* 25 */     return pulldownSelectType;
/*    */   }
/*    */   
/*    */   public Boolean getIsSelectAllIntegratedToComponent() {
/* 29 */     boolean integrated = false;
/*    */     try {
/* 31 */       integrated = (this.settings.get("isSelectAllIntegratedToComponent") != null);
/* 32 */     } catch (NullPointerException nullPointerException) {}
/*    */ 
/*    */     
/* 35 */     return Boolean.valueOf(integrated);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\PullDownMultiSelectSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */