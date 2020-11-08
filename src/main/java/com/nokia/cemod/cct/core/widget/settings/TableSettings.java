/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.List;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TableSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public TableSettings(JsonNode settings) {
/* 13 */     super(settings);
/*    */   }
/*    */   
/*    */   public List<String> getClickableColumns() {
/* 17 */     return null;
/*    */   }
/*    */   
/*    */   public String getSelectableType() {
/* 21 */     if (this.settings.get("selectable") != null) {
/* 22 */       return this.settings.get("selectable").asText();
/*    */     }
/* 24 */     return "cell";
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\TableSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */