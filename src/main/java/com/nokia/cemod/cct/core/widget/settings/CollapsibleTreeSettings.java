/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class CollapsibleTreeSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public CollapsibleTreeSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public int getExpansionLevel() {
/* 15 */     return this.settings.get("expandLevel").asInt();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\CollapsibleTreeSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */