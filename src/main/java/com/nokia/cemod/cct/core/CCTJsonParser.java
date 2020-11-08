/*     */ package com.nokia.cemod.cct.core;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.UseCase;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.codehaus.jackson.JsonFactory;
/*     */ import org.codehaus.jackson.JsonNode;
/*     */ import org.codehaus.jackson.JsonParser;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCTJsonParser
/*     */ {
/*     */   public static UseCase parseUseCaseJSON(String useCaseName, String useCaseJsonAsString) throws CCTException {
/*  29 */     UseCase useCase = new UseCase();
/*  30 */     useCase.setUseCaseName(useCaseName);
/*  31 */     JsonNode node = parse(useCaseJsonAsString);
/*  32 */     processPanes(useCase, node);
/*  33 */     constructWidgetParentChildRelationship(useCase, node);
/*  34 */     return useCase;
/*     */   }
/*     */   private static JsonNode parse(String useCaseJsonAsString) throws CCTException {
/*     */     JsonNode node;
/*  38 */     ObjectMapper mapper = new ObjectMapper();
/*  39 */     JsonFactory factory = mapper.getJsonFactory();
/*     */ 
/*     */     
/*     */     try {
/*  43 */       JsonParser parser = factory.createJsonParser(useCaseJsonAsString);
/*  44 */       node = parser.readValueAsTree();
/*  45 */     } catch (IOException e) {
/*  46 */       throw new CCTException("Exception in parse useCaseJsonAsString" + e.getMessage());
/*     */     } 
/*  48 */     return node;
/*     */   }
/*     */   
/*     */   private static void constructWidgetParentChildRelationship(UseCase useCase, JsonNode node) throws CCTException {
/*  52 */     Map<String, WidgetConfiguration> useCaseWidgets = useCase.getWidgets();
/*  53 */     boolean applyButtonAssociatedWithAutoRefresh = false;
/*  54 */     for (JsonNode pane : node.path("panes")) {
/*  55 */       JsonNode widgetNodes = pane.path("widgets");
/*  56 */       for (JsonNode widgetNode : widgetNodes) {
/*  57 */         JsonNode listenForComponentsNode = widgetNode.path("settings").get("listenForComponents");
/*  58 */         JsonNode shareResponseComponentNode = widgetNode.path("settings").get("shareResponseOfComponentId");
/*  59 */         if (listenForComponentsNode != null && listenForComponentsNode.size() != 0) {
/*  60 */           for (JsonNode listenForComponent : widgetNode.path("settings").get("listenForComponents"))
/*  61 */             addParentChildNodes(useCaseWidgets, widgetNode, listenForComponent);  continue;
/*     */         } 
/*  63 */         if (shareResponseComponentNode != null && 
/*  64 */           !shareResponseComponentNode.getTextValue().equalsIgnoreCase("")) {
/*  65 */           addParentChildNodes(useCaseWidgets, widgetNode, widgetNode
/*  66 */               .path("settings").get("shareResponseOfComponentId"));
/*  67 */           ((WidgetConfiguration)useCaseWidgets.get(widgetNode.path("settings").get("id").asText())).setSharedResponseParentId(widgetNode
/*  68 */               .path("settings").get("shareResponseOfComponentId").asText()); continue;
/*  69 */         }  if (isApplyWidgetLinkedToButtonGroup(useCaseWidgets, widgetNode)) {
/*     */           
/*  71 */           WidgetConfiguration applyButtonConfig = useCaseWidgets.get(widgetNode.path("settings").get("id").asText());
/*  72 */           ((WidgetConfiguration)useCaseWidgets.get(applyButtonConfig.getSettingsNode().get("buttonGroupId").asText()))
/*  73 */             .addChildWidgetId(applyButtonConfig.getId()); continue;
/*  74 */         }  if (isAutoRefreshEnabled(useCaseWidgets, widgetNode)) {
/*  75 */           addChildrenForAutoRefresh(useCaseWidgets, widgetNode);
/*  76 */           applyButtonAssociatedWithAutoRefresh = isApplyButtonAssociatedWithAutoRefresh(useCaseWidgets, widgetNode);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     addApplyButtonAsLastChilOfButtonGroup(useCaseWidgets);
/*  82 */     if (applyButtonAssociatedWithAutoRefresh) {
/*  83 */       addChildrenOfApplyButtonAsChildrenOfAutoRefresh(useCaseWidgets);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addParentChildNodes(Map<String, WidgetConfiguration> useCaseWidgets, JsonNode widgetNode, JsonNode listenForComponent) throws CCTException {
/*  89 */     if (useCaseWidgets.get(widgetNode.path("settings").get("id").asText()) != null) {
/*  90 */       ((WidgetConfiguration)useCaseWidgets.get(widgetNode.path("settings").get("id").asText())).addParentId(listenForComponent.asText());
/*     */     }
/*  92 */     if (useCaseWidgets.get(listenForComponent.asText()) != null) {
/*  93 */       ((WidgetConfiguration)useCaseWidgets.get(listenForComponent.asText()))
/*  94 */         .addChildWidgetId(widgetNode.path("settings").get("id").asText());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isApplyWidgetLinkedToButtonGroup(Map<String, WidgetConfiguration> useCaseWidgets, JsonNode widgetNode) {
/* 100 */     WidgetConfiguration config = useCaseWidgets.get(widgetNode.path("settings").get("id").asText());
/* 101 */     if (config.getType().equals("cemboard-apply-button")) {
/* 102 */       return (config.getSettingsNode().get("buttonGroupId") != null && config.getSettingsNode()
/* 103 */         .get("buttonGroupId").asText().contains("cemboard-button-group"));
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isAutoRefreshEnabled(Map<String, WidgetConfiguration> useCaseWidgets, JsonNode widgetNode) {
/* 109 */     WidgetConfiguration config = useCaseWidgets.get(widgetNode.path("settings").get("id").asText());
/* 110 */     if (config.getType().equals("cemboard-autoRefresh")) {
/* 111 */       return (config.getSettingsNode().get("refreshEnabled") != null && config
/* 112 */         .getSettingsNode().get("refreshEnabled").asBoolean());
/*     */     }
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addChildrenForAutoRefresh(Map<String, WidgetConfiguration> useCaseWidgets, JsonNode widgetNode) {
/* 119 */     WidgetConfiguration autoRefreshConfig = useCaseWidgets.get(widgetNode.path("settings").get("id").asText());
/* 120 */     if (isApplyButtonAssociatedWithAutoRefresh(useCaseWidgets, widgetNode)) {
/* 121 */       ((WidgetConfiguration)useCaseWidgets.get(widgetNode.path("settings").get("id").asText()))
/* 122 */         .addChildWidgetId(autoRefreshConfig.getSettingsNode().get("applyButtonId").asText());
/*     */     } else {
/* 124 */       JsonNode refreshComponentsNode = autoRefreshConfig.getSettingsNode().get("refreshComponents");
/* 125 */       if (refreshComponentsNode != null && !refreshComponentsNode.asText().isEmpty()) {
/* 126 */         String childrenIds = refreshComponentsNode.asText();
/* 127 */         String[] childrenIdsList = childrenIds.split(",");
/* 128 */         for (String childId : childrenIdsList) {
/* 129 */           ((WidgetConfiguration)useCaseWidgets.get(widgetNode.path("settings").get("id").asText())).addChildWidgetId(childId);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isApplyButtonAssociatedWithAutoRefresh(Map<String, WidgetConfiguration> useCaseWidgets, JsonNode widgetNode) {
/* 137 */     boolean applyButtonAssociatedWithAutoRefresh = false;
/* 138 */     WidgetConfiguration autoRefreshConfig = useCaseWidgets.get(widgetNode.path("settings").get("id").asText());
/* 139 */     JsonNode applyButtonNode = autoRefreshConfig.getSettingsNode().get("applyButtonId");
/* 140 */     if (applyButtonNode != null && !applyButtonNode.asText().isEmpty()) {
/* 141 */       applyButtonAssociatedWithAutoRefresh = true;
/*     */     }
/* 143 */     return applyButtonAssociatedWithAutoRefresh;
/*     */   }
/*     */   
/*     */   private static void addApplyButtonAsLastChilOfButtonGroup(Map<String, WidgetConfiguration> useCaseWidgets) {
/* 147 */     Iterator<String> widgetIdIterator = useCaseWidgets.keySet().iterator();
/* 148 */     while (widgetIdIterator.hasNext()) {
/* 149 */       WidgetConfiguration widgetConfig = useCaseWidgets.get(((String)widgetIdIterator.next()).toString());
/* 150 */       if (widgetConfig.getType().equals("cemboard-button-group")) {
/* 151 */         ArrayList<String> applyButtonIds = new ArrayList<String>();
/* 152 */         Iterator<String> childrenIterator = widgetConfig.getChildrenIds().iterator();
/* 153 */         while (childrenIterator.hasNext()) {
/* 154 */           WidgetConfiguration childConfig = useCaseWidgets.get(((String)childrenIterator.next()).toString());
/* 155 */           if (childConfig.getType().equals("cemboard-apply-button")) {
/* 156 */             applyButtonIds.add(childConfig.getId());
/* 157 */             childrenIterator.remove();
/*     */           } 
/*     */         } 
/* 160 */         if (applyButtonIds.size() > 0) {
/* 161 */           Iterator<String> ids = applyButtonIds.iterator();
/* 162 */           while (ids.hasNext()) {
/* 163 */             ((WidgetConfiguration)useCaseWidgets.get(widgetConfig.getId())).addChildWidgetId(ids.next());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void addChildrenOfApplyButtonAsChildrenOfAutoRefresh(Map<String, WidgetConfiguration> useCaseWidgets) {
/* 172 */     Iterator<String> widgetIdIterator = useCaseWidgets.keySet().iterator();
/* 173 */     while (widgetIdIterator.hasNext()) {
/* 174 */       WidgetConfiguration widgetConfig = useCaseWidgets.get(((String)widgetIdIterator.next()).toString());
/* 175 */       if (widgetConfig.getType().equals("cemboard-autoRefresh")) {
/* 176 */         ArrayList<String> applyButtonIds = new ArrayList<String>();
/* 177 */         Iterator<String> childrenIterator = widgetConfig.getChildrenIds().iterator();
/* 178 */         while (childrenIterator.hasNext()) {
/* 179 */           WidgetConfiguration childConfig = useCaseWidgets.get(((String)childrenIterator.next()).toString());
/* 180 */           if (childConfig.getType().equals("cemboard-apply-button")) {
/* 181 */             applyButtonIds.add(childConfig.getId());
/* 182 */             childrenIterator.remove();
/*     */           } 
/*     */         } 
/* 185 */         if (applyButtonIds.size() > 0) {
/* 186 */           Iterator<String> ids = applyButtonIds.iterator();
/* 187 */           while (ids.hasNext()) {
/* 188 */             List<String> applyButtonChildrenIdList = ((WidgetConfiguration)useCaseWidgets.get(ids.next())).getChildrenIds();
/* 189 */             for (String childId : applyButtonChildrenIdList) {
/* 190 */               ((WidgetConfiguration)useCaseWidgets.get(widgetConfig.getId())).addChildWidgetId(childId);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void processPanes(UseCase useCase, JsonNode node) {
/* 199 */     for (JsonNode pane : node.path("panes")) {
/* 200 */       JsonNode widgetNodes = pane.path("widgets");
/* 201 */       for (JsonNode widgetNode : widgetNodes) {
/* 202 */         WidgetConfiguration widgetConfiguration = new WidgetConfiguration();
/* 203 */         widgetConfiguration.setId(widgetNode.path("settings").get("id").asText());
/* 204 */         widgetConfiguration.setType(widgetNode.get("type").asText());
/* 205 */         if (widgetNode.path("settings").get("title") != null) {
/* 206 */           widgetConfiguration.setTitle(widgetNode.path("settings").get("title").asText());
/*     */         }
/* 208 */         if (widgetNode.path("settings").get("isDefaultEmitEnabled") != null && (
/* 209 */           !widgetConfiguration.getType().equals("cemboard-table") || widgetNode
/* 210 */           .get("settings").get("selectable") == null || 
/* 211 */           !widgetNode.get("settings").get("selectable").asText().equalsIgnoreCase("multiple"))) {
/* 212 */           widgetConfiguration
/* 213 */             .setDefaultEmitEnabled(widgetNode.path("settings").get("isDefaultEmitEnabled").asBoolean());
/*     */         }
/* 215 */         widgetConfiguration
/* 216 */           .setDrillDownSerieConfiguration(widgetNode.path("settings").get("listenForComponentValues"));
/* 217 */         if (widgetNode.path("settings").get("responseDataCache") != null) {
/* 218 */           widgetConfiguration.setResponseDataCacheEnabled(widgetNode
/* 219 */               .path("settings").get("responseDataCache").asBoolean());
/*     */         }
/* 221 */         Map<String, List<String>> drillDownConfiguration = processDrillDownConfigurations(widgetNode);
/* 222 */         widgetConfiguration.setDrillDownConfiguration(drillDownConfiguration);
/* 223 */         if (widgetNode.path("settings").get("customEditor") != null) {
/* 224 */           widgetConfiguration
/* 225 */             .setCustomEditorDrillDownCode(widgetNode.path("settings").get("customEditor").asText());
/*     */         }
/* 227 */         if (widgetNode.path("settings").get("queryFlowEditor") != null) {
/* 228 */           widgetConfiguration.setCustomEditorQueryFlowControllerCode(widgetNode
/* 229 */               .path("settings").get("queryFlowEditor").asText());
/*     */         }
/* 231 */         if (widgetNode.path("settings").get("responseFormatter") != null) {
/* 232 */           widgetConfiguration.setCustomEditorResponseFormatterCode(widgetNode
/* 233 */               .path("settings").get("responseFormatter").asText());
/*     */         }
/* 235 */         widgetConfiguration.setSettingsNode(widgetNode.get("settings"));
/* 236 */         useCase.addWidget(widgetConfiguration);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Map<String, List<String>> processDrillDownConfigurations(JsonNode widgetNode) {
/* 242 */     JsonNode listenForComponentValuesNode = widgetNode.path("settings").get("listenForComponentValues");
/* 243 */     Map<String, List<String>> drillDownConfiguration = new HashMap<String, List<String>>();
/* 244 */     if (listenForComponentValuesNode != null) {
/* 245 */       Iterator<String> valueIterator = listenForComponentValuesNode.getFieldNames();
/* 246 */       while (valueIterator.hasNext()) {
/* 247 */         JsonNode listenForComponentValuesSerie = listenForComponentValuesNode.get(valueIterator.next());
/* 248 */         String key = listenForComponentValuesSerie.get("emittedFields").asText();
/* 249 */         List<String> listOfEmittedValues = new ArrayList<String>();
/* 250 */         JsonNode listenForComponentActionNode = listenForComponentValuesSerie.get("listenToSpecificAction");
/* 251 */         if (listenForComponentActionNode != null) {
/* 252 */           Iterator<String> actionIterator = listenForComponentActionNode.getFieldNames();
/* 253 */           while (actionIterator.hasNext()) {
/* 254 */             listOfEmittedValues
/* 255 */               .add(listenForComponentActionNode.get(actionIterator.next()).get("values").asText());
/*     */           }
/*     */         } 
/* 258 */         if (listOfEmittedValues.size() > 0) {
/* 259 */           drillDownConfiguration.put(key, listOfEmittedValues);
/*     */         }
/*     */       } 
/*     */     } 
/* 263 */     return drillDownConfiguration;
/*     */   }
/*     */ 
/*     */   
/*     */   public static CCTEmittedEventsStore parseEmittedEventsStoreData(UseCase useCase, String emittedEventsStoreDataAsString) throws CCTException {
/* 268 */     JsonNode node = parse(emittedEventsStoreDataAsString);
/* 269 */     CCTEmittedEventsStore cCTEmittedEventsStore = new CCTEmittedEventsStore();
/* 270 */     for (WidgetConfiguration widgetConfiguration : useCase.getListOfAllWidgets()) {
/* 271 */       JsonNode widgetListenerValuesNode = node.get(widgetConfiguration.getId());
/* 272 */       if (widgetListenerValuesNode != null) {
/* 273 */         if (widgetListenerValuesNode.isArray()) {
/* 274 */           cCTEmittedEventsStore.addEmittedEvent(widgetConfiguration.getId(), "customArray", widgetListenerValuesNode);
/*     */           continue;
/*     */         } 
/* 277 */         Iterator<Map.Entry<String, JsonNode>> nodeElements = widgetListenerValuesNode.getFields();
/* 278 */         while (nodeElements.hasNext()) {
/* 279 */           Map.Entry<String, JsonNode> element = nodeElements.next();
/* 280 */           cCTEmittedEventsStore.addEmittedEvent(widgetConfiguration.getId(), element.getKey(), element
/* 281 */               .getValue());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 286 */     return cCTEmittedEventsStore;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\CCTJsonParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */