/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class DatePickerSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public DatePickerSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public String getdatepickertype() {
/* 15 */     return this.settings.get("picker").asText();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\DatePickerSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */