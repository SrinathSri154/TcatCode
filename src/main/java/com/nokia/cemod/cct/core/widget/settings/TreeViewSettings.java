/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class TreeViewSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public TreeViewSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public boolean isSearchEnabled() {
/* 15 */     return this.settings.get("isSearchEnabled").asBoolean();
/*    */   }
/*    */   
/*    */   public boolean isCheckBoxEnabled() {
/* 19 */     return this.settings.get("isMultiSelect").asBoolean();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\TreeViewSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */