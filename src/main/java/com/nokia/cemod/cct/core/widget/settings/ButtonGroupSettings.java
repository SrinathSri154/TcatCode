/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ButtonGroupSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public ButtonGroupSettings(JsonNode settings) {
/* 14 */     super(settings);
/*    */   }
/*    */   
/*    */   public List<String> getButtonGroupValues() {
/* 18 */     List<String> buttonValues = new ArrayList<String>();
/* 19 */     String[] valuesFromNode = this.settings.get("values").asText().split(",");
/* 20 */     for (String value : valuesFromNode) {
/* 21 */       if (value != null) {
/* 22 */         buttonValues.add(value.trim());
/*    */       }
/*    */     } 
/* 25 */     return buttonValues;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\ButtonGroupSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */