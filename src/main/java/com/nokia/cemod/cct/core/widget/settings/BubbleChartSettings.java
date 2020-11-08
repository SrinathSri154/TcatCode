/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BubbleChartSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public BubbleChartSettings(JsonNode settings) {
/* 15 */     super(settings);
/*    */   }
/*    */   
/*    */   public HashMap<String, ArrayList<String>> getKpiNamesandColor() {
/* 19 */     HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
/* 20 */     JsonNode kpiNamesAndColor = this.settings.get("yAxis");
/* 21 */     Iterator<JsonNode> seriesIterator = kpiNamesAndColor.iterator();
/* 22 */     while (seriesIterator.hasNext()) {
/* 23 */       ArrayList<String> values = new ArrayList<String>();
/* 24 */       JsonNode serie = seriesIterator.next();
/* 25 */       String serieName = serie.get("serieName").asText();
/* 26 */       String serieColor = serie.get("color").asText();
/* 27 */       values.add(serieColor);
/* 28 */       map.put(serieName, values);
/*    */     } 
/* 30 */     return map;
/*    */   }
/*    */   
/*    */   public boolean isGrandientConfigured() {
/* 34 */     JsonNode colorGradient = this.settings.get("colorGradient");
/* 35 */     if (colorGradient != null && colorGradient.iterator().hasNext()) {
/* 36 */       return true;
/*    */     }
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\BubbleChartSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */