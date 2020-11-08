/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class EditableTableSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public EditableTableSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public String getSelectableType() {
/* 15 */     if (this.settings.get("selectable") != null) {
/* 16 */       return this.settings.get("selectable").asText();
/*    */     }
/* 18 */     return "cell";
/*    */   }
/*    */ 
/*    */   
/*    */   public String isBulkUpdate() {
/* 23 */     if (this.settings.get("tableEdit").get("isBulkUpdate") != null) {
/* 24 */       return this.settings.get("tableEdit").get("isBulkUpdate").asText();
/*    */     }
/* 26 */     return "false";
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\EditableTableSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */