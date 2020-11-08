/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ public class UseCaseNavigationPublishSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public UseCaseNavigationPublishSettings(JsonNode settings) {
/* 11 */     super(settings);
/*    */   }
/*    */   
/*    */   public boolean isPopUp() {
/* 15 */     if (this.settings.get("isPopUpWindow") != null) {
/* 16 */       return this.settings.get("isPopUpWindow").asBoolean();
/*    */     }
/* 18 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\UseCaseNavigationPublishSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */