/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TabSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public TabSettings(JsonNode settings) {
/* 14 */     super(settings);
/*    */   }
/*    */   
/*    */   public List<String> getButtonGroupValues() {
/* 18 */     List<String> tabValues = new ArrayList<String>();
/* 19 */     String[] valuesFromNode = this.settings.get("values").asText().split(",");
/* 20 */     for (String value : valuesFromNode) {
/* 21 */       if (value != null) {
/* 22 */         tabValues.add(value.trim());
/*    */       }
/*    */     } 
/* 25 */     return tabValues;
/*    */   }
/*    */   
/*    */   public String getLinkedUseCase(String key) {
/* 29 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\TabSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */