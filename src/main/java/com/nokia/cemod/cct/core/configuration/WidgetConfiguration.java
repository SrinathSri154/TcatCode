/*     */ package com.nokia.cemod.cct.core.configuration;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.codehaus.jackson.JsonNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetConfiguration
/*     */ {
/*     */   private String id;
/*     */   private String type;
/*     */   private String title;
/*     */   private boolean isDefaultEmitEnabled;
/*  21 */   private List<String> parentIds = new ArrayList<String>();
/*     */   
/*  23 */   private List<String> childrenIds = new ArrayList<String>();
/*     */   
/*     */   private JsonNode drillDownSerieConfiguration;
/*     */   
/*  27 */   private String customEditorDrillDownCode = "";
/*     */   
/*  29 */   private String customEditorQueryFlowControllerCode = "";
/*     */   
/*  31 */   private String customEditorResponseFormatterCode = "";
/*     */   
/*     */   private boolean isResponseDataCacheEnabled;
/*     */   
/*  35 */   private String sharedResponseParentId = "";
/*     */   
/*     */   private Map<String, List<String>> drillDownConfiguration;
/*     */   
/*     */   private JsonNode settingsNode;
/*     */   
/*     */   private WidgetCoreSettings widgetCoreSettings;
/*     */ 
/*     */   
/*     */   public String toString() {
/*  45 */     StringBuilder drillDownConfigurationSB = new StringBuilder("\n");
/*  46 */     for (Map.Entry<String, List<String>> config : this.drillDownConfiguration.entrySet()) {
/*  47 */       drillDownConfigurationSB.append("Key: ");
/*  48 */       drillDownConfigurationSB.append(config.getKey());
/*  49 */       drillDownConfigurationSB.append(", Values: ");
/*  50 */       drillDownConfigurationSB.append(config.getValue());
/*     */     } 
/*  52 */     StringBuilder sb = new StringBuilder();
/*  53 */     sb.append("Widget id: ");
/*  54 */     sb.append(this.id);
/*  55 */     sb.append("\n");
/*  56 */     sb.append("Widget type: ");
/*  57 */     sb.append(this.type);
/*  58 */     sb.append("\n");
/*  59 */     sb.append("Widget title: ");
/*  60 */     sb.append(this.title);
/*  61 */     sb.append("\n");
/*  62 */     sb.append("ParentId: ");
/*  63 */     sb.append(this.parentIds);
/*  64 */     sb.append("\n");
/*  65 */     sb.append("isDefaultEmitEnabled: ");
/*  66 */     sb.append(this.isDefaultEmitEnabled);
/*  67 */     sb.append("\n");
/*  68 */     sb.append("Number of childrens: ");
/*  69 */     sb.append(this.childrenIds);
/*  70 */     sb.append("\n");
/*  71 */     sb.append("DrillDown Serie Configuration: ");
/*  72 */     sb.append(this.drillDownSerieConfiguration);
/*  73 */     sb.append("\n");
/*  74 */     sb.append("DrillDown CustomEditor: ");
/*  75 */     sb.append(this.customEditorDrillDownCode);
/*  76 */     sb.append("\n");
/*  77 */     sb.append("DrillDown Configuration: ");
/*  78 */     sb.append(drillDownConfigurationSB.toString());
/*  79 */     sb.append("\n");
/*  80 */     sb.append("isResponseDataCacheEnabled: ");
/*  81 */     sb.append(this.isResponseDataCacheEnabled);
/*  82 */     sb.append("\n");
/*  83 */     sb.append("sharedResponseParentId: ");
/*  84 */     sb.append(this.sharedResponseParentId);
/*  85 */     sb.append("\n");
/*  86 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  91 */     WidgetConfiguration eq = (WidgetConfiguration)obj;
/*  92 */     return getId().equals(eq.getId());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  97 */     return this.id.hashCode();
/*     */   }
/*     */   
/*     */   public String getId() {
/* 101 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 105 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 109 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 113 */     this.type = type;
/*     */   }
/*     */   
/*     */   public boolean isDefaultEmitEnabled() {
/* 117 */     return this.isDefaultEmitEnabled;
/*     */   }
/*     */   
/*     */   public void setDefaultEmitEnabled(boolean isDefaultEmitEnabled) {
/* 121 */     this.isDefaultEmitEnabled = isDefaultEmitEnabled;
/*     */   }
/*     */   
/*     */   public List<String> getParentIds() {
/* 125 */     return this.parentIds;
/*     */   }
/*     */   
/*     */   public void addParentId(String parentId) {
/* 129 */     this.parentIds.add(parentId);
/*     */   }
/*     */   
/*     */   public List<String> getChildrenIds() {
/* 133 */     return this.childrenIds;
/*     */   }
/*     */   
/*     */   public void addChildWidgetId(String childWidgetId) {
/* 137 */     this.childrenIds.add(childWidgetId);
/*     */   }
/*     */   
/*     */   public JsonNode getDrillDownSerieConfiguration() {
/* 141 */     return this.drillDownSerieConfiguration;
/*     */   }
/*     */   
/*     */   public void setDrillDownSerieConfiguration(JsonNode drillDownSerieConfiguration) {
/* 145 */     this.drillDownSerieConfiguration = drillDownSerieConfiguration;
/*     */   }
/*     */   
/*     */   public String getCustomEditorDrillDownCode() {
/* 149 */     return this.customEditorDrillDownCode;
/*     */   }
/*     */   
/*     */   public void setCustomEditorDrillDownCode(String drillDownCustomEditor) {
/* 153 */     this.customEditorDrillDownCode = drillDownCustomEditor;
/*     */   }
/*     */   
/*     */   public String getCustomEditorQueryFlowControllerCode() {
/* 157 */     return this.customEditorQueryFlowControllerCode;
/*     */   }
/*     */   
/*     */   public void setCustomEditorQueryFlowControllerCode(String customEditorQueryFlowControllerCode) {
/* 161 */     this.customEditorQueryFlowControllerCode = customEditorQueryFlowControllerCode;
/*     */   }
/*     */   
/*     */   public String getCustomEditorResponseFormatterCode() {
/* 165 */     return this.customEditorResponseFormatterCode;
/*     */   }
/*     */   
/*     */   public void setCustomEditorResponseFormatterCode(String customEditorResponseFormatterCode) {
/* 169 */     this.customEditorResponseFormatterCode = customEditorResponseFormatterCode;
/*     */   }
/*     */   
/*     */   public Map<String, List<String>> getDrillDownConfiguration() {
/* 173 */     return this.drillDownConfiguration;
/*     */   }
/*     */   
/*     */   public void setDrillDownConfiguration(Map<String, List<String>> drillDownConfiguration) {
/* 177 */     this.drillDownConfiguration = drillDownConfiguration;
/*     */   }
/*     */   
/*     */   public boolean isResponseDataCacheEnabled() {
/* 181 */     return this.isResponseDataCacheEnabled;
/*     */   }
/*     */   
/*     */   public void setResponseDataCacheEnabled(boolean isResponseDataCacheEnabled) {
/* 185 */     this.isResponseDataCacheEnabled = isResponseDataCacheEnabled;
/*     */   }
/*     */   
/*     */   public String getSharedResponseParentId() {
/* 189 */     return this.sharedResponseParentId;
/*     */   }
/*     */   
/*     */   public void setSharedResponseParentId(String sharedResponseParentId) {
/* 193 */     this.sharedResponseParentId = sharedResponseParentId;
/*     */   }
/*     */   
/*     */   public JsonNode getSettingsNode() {
/* 197 */     return this.settingsNode;
/*     */   }
/*     */   
/*     */   public void setSettingsNode(JsonNode settingsNode) {
/* 201 */     this.settingsNode = settingsNode;
/*     */   }
/*     */   
/*     */   public WidgetCoreSettings getSettings() {
/* 205 */     return this.widgetCoreSettings;
/*     */   }
/*     */   
/*     */   public void setSettings(WidgetCoreSettings widgetCoreSettings) {
/* 209 */     this.widgetCoreSettings = widgetCoreSettings;
/*     */   }
/*     */   
/*     */   public String getTitle() {
/* 213 */     return this.title;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 217 */     this.title = title;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\configuration\WidgetConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */