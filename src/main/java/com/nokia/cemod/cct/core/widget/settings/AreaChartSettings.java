/*    */ package com.nokia.cemod.cct.core.widget.settings;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AreaChartSettings
/*    */   extends WidgetCoreSettings
/*    */ {
/*    */   public AreaChartSettings(JsonNode settings) {
/* 14 */     super(settings);
/*    */   }
/*    */   
/*    */   public HashMap<String, ArrayList<String>> getKpiNamesandColor() {
/* 18 */     HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
/* 19 */     int numberOfSeries = this.settings.get("yAxis").size();
/* 20 */     JsonNode kpiNamesAndColor = this.settings.get("yAxis");
/* 21 */     for (int seriesIndex = 1; seriesIndex <= numberOfSeries; seriesIndex++) {
/* 22 */       ArrayList<String> values = new ArrayList<String>();
/* 23 */       values.add(kpiNamesAndColor.get("Serie" + seriesIndex).get("color").asText());
/* 24 */       map.put(kpiNamesAndColor.get("Serie" + seriesIndex).get("serieName").asText(), values);
/*    */     } 
/* 26 */     return map;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\AreaChartSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */