/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PullDownSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public PullDownSettings(JsonNode settings) {
/* 15 */     super(settings);
/*    */   }
/*    */   
/*    */   public List<String> getNodeValues() {
/* 19 */     List<String> pulldownValues = new ArrayList<String>();
/* 20 */     int numberOfpullDownValues = this.settings.get("kpi").size();
/* 21 */     JsonNode valuesFromNode = this.settings.get("kpi");
/* 22 */     for (int i = 1; i <= numberOfpullDownValues; i++) {
/* 23 */       if (valuesFromNode != null) {
/* 24 */         String pvalue = valuesFromNode.get("Serie" + i).get("displayKpi").asText();
/* 25 */         pulldownValues.add(pvalue);
/*    */       } 
/*    */     } 
/* 28 */     return pulldownValues;
/*    */   }
/*    */   
/*    */   public boolean isStaticOrParent(WidgetConfiguration widgetConfiguration) {
/* 32 */     if ((widgetConfiguration.getSettingsNode().get("data") != null && 
/* 33 */       !widgetConfiguration.getSettingsNode().get("data").asText().isEmpty()) || 
/* 34 */       !widgetConfiguration.getCustomEditorQueryFlowControllerCode().isEmpty() || widgetConfiguration
/* 35 */       .getParentIds().size() > 0) {
/* 36 */       return false;
/*    */     }
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\PullDownSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */