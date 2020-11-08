/*    */ package com.nokia.cemod.cct.core.configuration;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UseCase
/*    */ {
/* 12 */   private Map<String, WidgetConfiguration> widgetConfigurations = new HashMap<String, WidgetConfiguration>();
/*    */   
/*    */   private String useCaseName;
/*    */   
/*    */   public void addWidget(WidgetConfiguration widgetConfiguration) {
/* 17 */     this.widgetConfigurations.put(widgetConfiguration.getId(), widgetConfiguration);
/*    */   }
/*    */   
/*    */   public Map<String, WidgetConfiguration> getWidgets() {
/* 21 */     return this.widgetConfigurations;
/*    */   }
/*    */   
/*    */   public List<WidgetConfiguration> getListOfAllWidgets() {
/* 25 */     return new ArrayList<WidgetConfiguration>(this.widgetConfigurations.values());
/*    */   }
/*    */   
/*    */   public List<WidgetConfiguration> getListOfParentRenderedWidgets() {
/* 29 */     List<WidgetConfiguration> parentWidgets = new ArrayList<WidgetConfiguration>();
/*    */     
/* 31 */     for (WidgetConfiguration widgetConfiguration : this.widgetConfigurations.values()) {
/* 32 */       if (widgetConfiguration.getParentIds().size() == 0) {
/* 33 */         parentWidgets.add(widgetConfiguration);
/*    */       }
/*    */     } 
/* 36 */     return parentWidgets;
/*    */   }
/*    */   
/*    */   public String getUseCaseName() {
/* 40 */     return this.useCaseName;
/*    */   }
/*    */   
/*    */   public void setUseCaseName(String useCaseName) {
/* 44 */     this.useCaseName = useCaseName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     StringBuilder sb = new StringBuilder();
/* 50 */     for (Map.Entry<String, WidgetConfiguration> widgetConfiguration : this.widgetConfigurations.entrySet()) {
/* 51 */       sb.append(widgetConfiguration.getValue());
/* 52 */       sb.append("\n");
/*    */     } 
/* 54 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\configuration\UseCase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */