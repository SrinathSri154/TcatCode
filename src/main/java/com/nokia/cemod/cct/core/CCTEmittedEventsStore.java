/*    */ package com.nokia.cemod.cct.core;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CCTEmittedEventsStore
/*    */ {
/* 13 */   private Map<String, Map> emittedEventsStore = new HashMap<String, Map>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void addEmittedEvent(String widgetId, String key, JsonNode value) {
/* 18 */     if (this.emittedEventsStore.get(widgetId) == null) {
/* 19 */       this.emittedEventsStore.put(widgetId, new HashMap<String, Integer>());
/*    */     }
/* 21 */     Map<String, Object> emittedEventsData = this.emittedEventsStore.get(widgetId);
/* 22 */     if (value.isInt()) {
/* 23 */       emittedEventsData.put(key, Integer.valueOf(value.asInt()));
/* 24 */     } else if (value.isBoolean()) {
/* 25 */       emittedEventsData.put(key, Boolean.valueOf(value.asBoolean()));
/* 26 */     } else if (value.isTextual()) {
/* 27 */       emittedEventsData.put(key, value.getTextValue());
/*    */     } else {
/* 29 */       emittedEventsData.put(key, value);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Map getEmittedEventsData(String WidgetId) {
/* 35 */     return this.emittedEventsStore.get(WidgetId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     StringBuilder sb = new StringBuilder();
/* 42 */     for (Map.Entry<String, Map> widgetMap : this.emittedEventsStore.entrySet()) {
/* 43 */       sb.append("\nEmitted data by widget Id: ");
/* 44 */       sb.append(widgetMap.getKey());
/* 45 */       sb.append("\n");
/* 46 */       Iterator<String> it = ((Map)widgetMap.getValue()).keySet().iterator();
/* 47 */       while (it.hasNext()) {
/* 48 */         String key = it.next();
/* 49 */         sb.append("Key: " + key);
/* 50 */         sb.append(", Value: " + ((Map)widgetMap.getValue()).get(it.next()));
/* 51 */         sb.append("\n");
/*    */       } 
/*    */     } 
/* 54 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\CCTEmittedEventsStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */