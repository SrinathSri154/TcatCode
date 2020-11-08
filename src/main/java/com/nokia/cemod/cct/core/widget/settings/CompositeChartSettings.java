/*     */ package com.nokia.cemod.cct.core.widget.settings;
/*     */
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import org.codehaus.jackson.JsonNode;
/*     */
/*     */
/*     */ public class CompositeChartSettings
/*     */   extends WidgetCoreSettings
/*     */ {
/*     */   public CompositeChartSettings(JsonNode settings) {
/*  14 */     super(settings);
/*     */   }
/*     */
/*     */   public HashMap<String, ArrayList<String>> getKpiNamesandColor() {
/*  18 */     HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
/*  19 */     JsonNode kpiNamesAndColor = null;
/*  20 */     boolean isMultipleQueriesEnabled = (this.settings.get("isMultipleQueries") != null);
/*  21 */     if (isMultipleQueriesEnabled && this.settings.get("multipleSettings").has("Query1")) {
/*  22 */       kpiNamesAndColor = this.settings.get("multipleSettings").get("Query1").get("ySeriesConfig");
/*  23 */     } else if (isMultipleQueriesEnabled && this.settings.get("multipleSettings").has("Query0")) {
/*  24 */       kpiNamesAndColor = this.settings.get("multipleSettings").get("Query0").get("ySeriesConfig");
/*     */     } else {
/*  26 */       kpiNamesAndColor = this.settings.get("ySeriesConfig");
/*     */     }
/*  28 */     if (isCustomLegendsRequired()) {
/*  29 */       JsonNode settingsConfig = getSettingsBasedOnType();
/*  30 */       if (settingsConfig != null) {
/*  31 */         for (int seriesIndex = 1; seriesIndex <= settingsConfig.size(); seriesIndex++) {
/*  32 */           ArrayList<String> values = new ArrayList<String>();
/*  33 */           String serieColor = settingsConfig.get("Serie" + seriesIndex).get("thresholdcolor").asText();
/*  34 */           values.add(serieColor);
/*  35 */           JsonNode kpiNamesAndColorSerie = getYSerie(kpiNamesAndColor);
/*  36 */           if (kpiNamesAndColorSerie.get("chartType") != null) {
/*  37 */             values.add(kpiNamesAndColorSerie.get("chartType").asText());
/*  38 */           } else if (kpiNamesAndColorSerie.get("type") != null) {
/*  39 */             values.add(kpiNamesAndColorSerie.get("type").asText());
/*     */           } else {
/*  41 */             values.add("bar");
/*     */           }
/*  43 */           String serieName = settingsConfig.get("Serie" + seriesIndex).get("legendName").asText();
/*  44 */           if (serieName.isEmpty() || serieName.equalsIgnoreCase("")) {
/*  45 */             serieName = "No Severity Legend for serie color -" + serieColor;
/*     */           }
/*  47 */           map.put(serieName, values);
/*     */         }
/*     */       }
/*     */     } else {
/*  51 */       int serieIndexCounter = kpiNamesAndColor.size();
/*  52 */       for (int seriesIndex = 1; seriesIndex <= serieIndexCounter; seriesIndex++) {
/*  53 */         ArrayList<String> values = new ArrayList<String>();
/*  54 */         JsonNode node = kpiNamesAndColor.get("Serie" + seriesIndex);
/*  55 */         if (node != null) {
/*  56 */           String serieColor = node.get("color").asText();
/*  57 */           values.add(serieColor);
/*  58 */           if (node.get("chartType") != null) {
/*  59 */             values.add(node.get("chartType").asText());
/*  60 */           } else if (node.get("type") != null) {
/*  61 */             values.add(node.get("type").asText());
/*     */           } else {
/*  63 */             values.add("bar");
/*     */           }
/*  65 */           String serieName = node.get("name").asText();
/*  66 */           if (serieName.isEmpty() || serieName.equalsIgnoreCase("")) {
/*  67 */             serieName = "No Legend for serie color -" + serieColor;
/*     */           }
/*  69 */           map.put(serieName, values);
/*     */         } else {
/*  71 */           serieIndexCounter++;
/*     */         }
/*     */       }
/*     */     }
/*  75 */     return map;
/*     */   }
/*     */
/*     */   public JsonNode getThresholdConfig() {
/*  79 */     JsonNode thresholdConfig = this.settings.get("seriesThreshold");
/*  80 */     return thresholdConfig;
/*     */   }
/*     */
/*     */   public JsonNode getSeverityConfig() {
/*  84 */     JsonNode severityConfig = this.settings.get("severity");
/*  85 */     return severityConfig;
/*     */   }
/*     */
/*     */   public boolean isCustomLegendsRequired() {
/*  89 */     boolean customLegendsConfigured = false;
/*  90 */     JsonNode ySeriesConfig = this.settings.get("ySeriesConfig");
/*  91 */     JsonNode thresholdConfig = getThresholdConfig();
/*  92 */     JsonNode severityConfig = getSeverityConfig();
/*  93 */     String chartType = "";
/*  94 */     if (ySeriesConfig != null && ySeriesConfig.size() > 0) {
/*  95 */       JsonNode serie = getYSerie(ySeriesConfig);
/*  96 */       if (serie.get("chartType") != null) {
/*  97 */         chartType = serie.get("chartType").asText();
/*  98 */       } else if (serie.get("type") != null) {
/*  99 */         chartType = serie.get("type").asText();
/*     */       }
/*     */     }
/* 102 */     if (ySeriesConfig != null && ySeriesConfig.size() != 1) {
/* 103 */       customLegendsConfigured = false;
/* 104 */     } else if (chartType.equalsIgnoreCase("bar") && severityConfig != null && severityConfig.size() > 0) {
/* 105 */       customLegendsConfigured = true;
/* 106 */     } else if (chartType.equalsIgnoreCase("column") && thresholdConfig != null && thresholdConfig.size() > 0) {
/* 107 */       customLegendsConfigured = true;
/*     */     }
/* 109 */     return customLegendsConfigured;
/*     */   }
/*     */
/*     */   private JsonNode getYSerie(JsonNode ySeriesConfig) {
/* 113 */     JsonNode Yserie = null;
/* 114 */     Iterator<String> iterSerie = ySeriesConfig.getFieldNames();
/* 115 */     if (iterSerie.hasNext()) {
/* 116 */       Yserie = ySeriesConfig.get(iterSerie.next());
/*     */     }
/*     */
/* 119 */     return Yserie;
/*     */   }
/*     */
/*     */   public JsonNode getSettingsBasedOnType() {
/* 123 */     JsonNode settingsConfig = this.settings.get("ySeriesConfig");
/* 124 */     JsonNode thresholdConfig = getThresholdConfig();
/* 125 */     JsonNode severityConfig = getSeverityConfig();
/* 126 */     if (isCustomLegendsRequired()) {
/* 127 */       if (thresholdConfig != null && thresholdConfig.size() > 0) {
/* 128 */         settingsConfig = thresholdConfig;
/* 129 */       } else if (severityConfig != null && severityConfig.size() > 0) {
/* 130 */         settingsConfig = severityConfig;
/*     */       }
/*     */     }
/* 133 */     return settingsConfig;
/*     */   }
/*     */
/*     */   public boolean isLegendsConfigured() {
/* 137 */     boolean legendsConfigured = false;
/* 138 */     JsonNode settingsConfig = null;
/* 139 */     boolean isMultipleQueriesEnabled = (this.settings.get("isMultipleQueries") != null);
/* 140 */     if (isMultipleQueriesEnabled && this.settings.get("multipleSettings").has("Query1")) {
/* 141 */       settingsConfig = this.settings.get("multipleSettings").get("Query1").get("ySeriesConfig");
/* 142 */     } else if (isMultipleQueriesEnabled) {
/* 143 */       settingsConfig = this.settings.get("multipleSettings").get("Query0").get("ySeriesConfig");
/*     */     } else {
/* 145 */       settingsConfig = this.settings.get("ySeriesConfig");
/*     */     }
/* 147 */     boolean customLegendsConfigured = isCustomLegendsRequired();
/* 148 */     if (customLegendsConfigured) {
/* 149 */       settingsConfig = getSettingsBasedOnType();
/*     */     }
/* 151 */     int seriesIndexCounter = settingsConfig.size();
/* 152 */     for (int seriesIndex = 1; seriesIndex <= seriesIndexCounter; seriesIndex++) {
/*     */
/* 154 */       JsonNode node = settingsConfig.get("Serie" + seriesIndex);
/* 155 */       if (node != null) {
/* 156 */         String serieName; if (customLegendsConfigured) {
/* 157 */           serieName = node.get("legendName").asText();
/*     */         } else {
/* 159 */           serieName = node.get("name").asText();
/*     */         }
/* 161 */         if (!serieName.isEmpty() && !serieName.equalsIgnoreCase("")) {
/* 162 */           legendsConfigured = true;
/*     */           break;
/*     */         }
/*     */       } else {
/* 166 */         seriesIndexCounter++;
/*     */       }
/*     */     }
/* 169 */     return legendsConfigured;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\settings\CompositeChartSettings.class
* Java compiler version: 8 (52.0)
* JD-Core Version:       1.1.3
*/