/*    */ package com.nokia.cemod.cct.core.configuration;
/*    */ 
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public abstract class WidgetCoreSettings
/*    */ {
/*    */   protected JsonNode settings;
/*    */   
/*    */   public WidgetCoreSettings(JsonNode settings) {
/* 11 */     this.settings = settings;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\configuration\WidgetCoreSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */