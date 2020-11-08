/*     */ package com.nokia.cemod.cct.core.widget;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetCoreSettings;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.*;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.ApplyButton;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.AreaChart;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.AttributeSelector;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.AutoRefresh;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.AzimuthMap;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.BoxPlot;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.BubbleChart;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.ButtonGroup;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.CheckBox;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.CollapsibleTree;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.CompositeChart;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.DatePicker;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Diagram;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.EditableTable;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.ExportButton;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Filter;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.HeatMap;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Hyperlink;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.ImportCSV;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Label;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Line;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Map;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.PieChart;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.PullDown;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.PullDownMultiSelect;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.QueryExecutor;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Quote;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.RadarChart;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.RadioButton;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.SpacingWidget;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.SubmitButton;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Tab;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Table;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.TextBox;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.TreeMap;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.TreeView;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.UseCaseNavigationPublish;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.UseCaseNavigationSubscribe;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */// import java.util.*;
/*     */ import org.codehaus.jackson.JsonNode;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ import org.json.JSONException;
import com.fasterxml.jackson.databind.node.BooleanNode;
import org.openqa.selenium.WebDriver;
/*     */
/*     */ public class WidgetInstanceFactory {
    /*     */   public static CCTRenderedWidget getWidgetInstance(WebDriver driver, WidgetConfiguration widgetConfiguration) throws CCTException {
        CCTRenderedWidget cctRenderedWidget=null;
        /*     */     Table table;
        /*     */     Map map;
        /*     */     CompositeChart compositeChart;
        /*     */     BubbleChart bubbleChart;
        /*     */     ButtonGroup buttonGroup;
        /*     */     Tab tab;
        /*     */     PieChart pieChart;
        /*     */     ApplyButton applyButton;
        /*     */     PullDown pullDown;
        /*     */     PullDownMultiSelect pullDownMultiSelect;
        /*     */     TextBox textBox;
        /*     */     RadioButton radioButton;
        /*     */     TreeMap treeMap;
        /*     */     Label label;
        /*     */     SpacingWidget spacingWidget;
        /*     */     QueryExecutor queryExecutor;
        /*     */     AreaChart areaChart;
        /*     */     Quote quote;
        /*     */     CheckBox checkBox;
        /*     */     Diagram diagram;
        /*     */     Hyperlink hyperlink;
        /*     */     DatePicker datePicker;
        /*     */     RadarChart radarChart;
        /*     */     AutoRefresh autoRefresh;
        /*     */     UseCaseNavigationPublish useCaseNavigationPublish;
        /*     */     UseCaseNavigationSubscribe useCaseNavigationSubscribe;
        /*     */     AzimuthMap azimuthMap;
        /*     */     Filter filter;
        /*     */     EditableTable editableTable;
        /*     */     BoxPlot boxPlot;
        /*     */     AttributeSelector attributeSelector;
        /*     */     Line line;
        /*     */     HeatMap heatMap;
        /*     */     TreeView treeView;
        /*     */     ImportCSV importCSV;
        /*     */     SubmitButton submitButton;
        /*     */     CollapsibleTree collapsibleTree;
        /*     */     ExportButton exportButton = new ExportButton(driver, widgetConfiguration);;
        /*  96 */     if (widgetConfiguration == null) {
            /*  97 */       throw new CCTException("Invalid Widget ID, please re-check the feature files");
            /*     */     }
        /*  99 */     CCTRenderedWidget widgetInstance = null;
        /* 100 */     JsonNode updatedSettingsBasedOnQueryFlowController = getUpdatedSettingsBasedOnQueryFlowController(driver, widgetConfiguration);
        /*     */
        /* 102 */     switch (widgetConfiguration.getType()) {
            /*     */       case "cemboard-table":
                /* 104 */         widgetConfiguration.setSettings((WidgetCoreSettings)new TableSettings(updatedSettingsBasedOnQueryFlowController));
                /* 105 */         cctRenderedWidget = new Table(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-map":
                /* 108 */         widgetConfiguration.setSettings((WidgetCoreSettings)new MapSettings(updatedSettingsBasedOnQueryFlowController));
                /* 109 */         cctRenderedWidget = new Map(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-composite-chart":
                /* 112 */         widgetConfiguration.setSettings((WidgetCoreSettings)new CompositeChartSettings(updatedSettingsBasedOnQueryFlowController));
                /* 113 */         cctRenderedWidget = new CompositeChart(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-bubble-chart":
                /* 116 */         widgetConfiguration.setSettings((WidgetCoreSettings)new BubbleChartSettings(updatedSettingsBasedOnQueryFlowController));
                /* 117 */         cctRenderedWidget = new BubbleChart(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-button-group":
                /* 120 */         widgetConfiguration.setSettings((WidgetCoreSettings)new ButtonGroupSettings(updatedSettingsBasedOnQueryFlowController));
                /* 121 */         cctRenderedWidget = new ButtonGroup(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-tab":
                /* 124 */         widgetConfiguration.setSettings((WidgetCoreSettings)new TabSettings(updatedSettingsBasedOnQueryFlowController));
                /* 125 */         cctRenderedWidget = new Tab(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-pie-chart":
                /* 128 */         widgetConfiguration.setSettings((WidgetCoreSettings)new PieChartSettings(updatedSettingsBasedOnQueryFlowController));
                /* 129 */         cctRenderedWidget = new PieChart(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-apply-button":
                /* 132 */         widgetConfiguration.setSettings((WidgetCoreSettings)new ApplyButtonSettings(updatedSettingsBasedOnQueryFlowController));
                /* 133 */         cctRenderedWidget = new ApplyButton(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-pulldown":
                /* 136 */         widgetConfiguration.setSettings((WidgetCoreSettings)new PullDownSettings(updatedSettingsBasedOnQueryFlowController));
                /* 137 */         cctRenderedWidget = new PullDown(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-multiselectpulldown":
                /* 140 */         widgetConfiguration.setSettings((WidgetCoreSettings)new PullDownMultiSelectSettings(updatedSettingsBasedOnQueryFlowController));
                /* 141 */         cctRenderedWidget = new PullDownMultiSelect(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "kendo-Text":
                /* 144 */         widgetConfiguration.setSettings((WidgetCoreSettings)new TextBoxSettings(updatedSettingsBasedOnQueryFlowController));
                /* 145 */         cctRenderedWidget = new TextBox(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-radio":
                /* 148 */         widgetConfiguration.setSettings((WidgetCoreSettings)new RadioButtonSettings(updatedSettingsBasedOnQueryFlowController));
                /* 149 */         cctRenderedWidget = new RadioButton(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-treemap":
                /* 152 */         widgetConfiguration.setSettings((WidgetCoreSettings)new TreeMapSettings(updatedSettingsBasedOnQueryFlowController));
                /* 153 */         cctRenderedWidget = new TreeMap(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-label":
                /* 156 */         widgetConfiguration.setSettings((WidgetCoreSettings)new LabelSettings(updatedSettingsBasedOnQueryFlowController));
                /* 157 */         cctRenderedWidget = new Label(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "spacingWidget":
                /* 160 */         widgetConfiguration.setSettings((WidgetCoreSettings)new SpacingWidgetSettings(updatedSettingsBasedOnQueryFlowController));
                /* 161 */         cctRenderedWidget = new SpacingWidget(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-queryExecutor":
                /* 164 */         widgetConfiguration.setSettings((WidgetCoreSettings)new QueryExecutorSettings(updatedSettingsBasedOnQueryFlowController));
                /* 165 */         cctRenderedWidget = new QueryExecutor(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-Area-chart":
                /* 168 */         widgetConfiguration.setSettings((WidgetCoreSettings)new AreaChartSettings(updatedSettingsBasedOnQueryFlowController));
                /* 169 */         cctRenderedWidget = new AreaChart(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-quote":
                /* 172 */         widgetConfiguration.setSettings((WidgetCoreSettings)new QuoteSettings(updatedSettingsBasedOnQueryFlowController));
                /* 173 */         cctRenderedWidget = new Quote(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-checkbox":
                /* 176 */         widgetConfiguration.setSettings((WidgetCoreSettings)new CheckBoxSettings(updatedSettingsBasedOnQueryFlowController));
                /* 177 */         cctRenderedWidget = new CheckBox(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-diagram":
                /* 180 */         widgetConfiguration.setSettings((WidgetCoreSettings)new DiagramSettings(updatedSettingsBasedOnQueryFlowController));
                /* 181 */         cctRenderedWidget = new Diagram(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-hyperlink":
                /* 184 */         widgetConfiguration.setSettings((WidgetCoreSettings)new HyperlinkSettings(updatedSettingsBasedOnQueryFlowController));
                /* 185 */         cctRenderedWidget = new Hyperlink(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-date-picker":
                /* 188 */         widgetConfiguration.setSettings((WidgetCoreSettings)new DatePickerSettings(updatedSettingsBasedOnQueryFlowController));
                /* 189 */         cctRenderedWidget = new DatePicker(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-radar-chart":
                /* 192 */         widgetConfiguration.setSettings((WidgetCoreSettings)new RadarChartSettings(updatedSettingsBasedOnQueryFlowController));
                /* 193 */         cctRenderedWidget = new RadarChart(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-autoRefresh":
                /* 196 */         widgetConfiguration.setSettings((WidgetCoreSettings)new AutoRefreshSettings(updatedSettingsBasedOnQueryFlowController));
                /* 197 */         cctRenderedWidget = new AutoRefresh(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "usecase-navigation-publish-widget":
                /* 200 */         widgetConfiguration
/* 201 */           .setSettings((WidgetCoreSettings)new UseCaseNavigationPublishSettings(updatedSettingsBasedOnQueryFlowController));
                /* 202 */         cctRenderedWidget = new UseCaseNavigationPublish(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "usecase-navigation-subscription-widget":
                /* 205 */         widgetConfiguration
/* 206 */           .setSettings((WidgetCoreSettings)new UseCaseNavigationSubscribeSettings(updatedSettingsBasedOnQueryFlowController));
                /* 207 */         cctRenderedWidget = new UseCaseNavigationSubscribe(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-cellmap":
                /* 210 */         widgetConfiguration.setSettings((WidgetCoreSettings)new AzimuthMapSettings(updatedSettingsBasedOnQueryFlowController));
                /* 211 */         cctRenderedWidget = new AzimuthMap(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-filters":
                /* 214 */         widgetConfiguration.setSettings((WidgetCoreSettings)new FilterSettings(updatedSettingsBasedOnQueryFlowController));
                /* 215 */         cctRenderedWidget = new Filter(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-editable-table":
                /* 218 */         widgetConfiguration.setSettings((WidgetCoreSettings)new EditableTableSettings(updatedSettingsBasedOnQueryFlowController));
                /* 219 */         cctRenderedWidget = new EditableTable(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-box-plot-chart":
                /* 222 */         widgetConfiguration.setSettings((WidgetCoreSettings)new BoxPlotSettings(updatedSettingsBasedOnQueryFlowController));
                /* 223 */         cctRenderedWidget = new BoxPlot(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-attribute-selector":
                /* 226 */         widgetConfiguration.setSettings((WidgetCoreSettings)new AttributeSelectorSettings(updatedSettingsBasedOnQueryFlowController));
                /* 227 */         cctRenderedWidget = new AttributeSelector(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-line":
                /* 230 */         widgetConfiguration.setSettings((WidgetCoreSettings)new LineSettings(updatedSettingsBasedOnQueryFlowController));
                /* 231 */         cctRenderedWidget = new Line(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-heat-map":
                /* 234 */         widgetConfiguration.setSettings((WidgetCoreSettings)new HeatMapSettings(updatedSettingsBasedOnQueryFlowController));
                /* 235 */         cctRenderedWidget = new HeatMap(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-treeview":
                /* 238 */         widgetConfiguration.setSettings((WidgetCoreSettings)new TreeViewSettings(updatedSettingsBasedOnQueryFlowController));
                /* 239 */         cctRenderedWidget = new TreeView(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-import-csv":
                /* 242 */         widgetConfiguration.setSettings((WidgetCoreSettings)new ImportCSVSettings(updatedSettingsBasedOnQueryFlowController));
                /* 243 */         cctRenderedWidget = new ImportCSV(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-submit-button":
                /* 246 */         widgetConfiguration.setSettings((WidgetCoreSettings)new SubmitButtonSettings(updatedSettingsBasedOnQueryFlowController));
                /* 247 */         cctRenderedWidget = new SubmitButton(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-collapsible-tree":
                /* 250 */         widgetConfiguration.setSettings((WidgetCoreSettings)new CollapsibleTreeSettings(updatedSettingsBasedOnQueryFlowController));
                /* 251 */         cctRenderedWidget = new CollapsibleTree(driver, widgetConfiguration);
                /*     */         break;
            /*     */       case "cemboard-exportButton":
                /* 254 */         widgetConfiguration.setSettings((WidgetCoreSettings)new ExportButtonSettings(updatedSettingsBasedOnQueryFlowController));
                /* 255 */         cctRenderedWidget = new ExportButton(driver, widgetConfiguration);

                /*     */         break;
            /*     */       default:
                /* 258 */         org.junit.Assert.fail("Widget type in not defined in CCT Test Automation Framework: " + widgetConfiguration
/* 259 */             .getType());
                /*     */         break;
            /*     */     }
        /* 262 */     if (cctRenderedWidget != null) {
            /* 263 */       cctRenderedWidget.processWidget();
            /*     */     }
        /* 265 */     return cctRenderedWidget;
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */   private static JsonNode getUpdatedSettingsBasedOnQueryFlowController(WebDriver driver, WidgetConfiguration widgetConfiguration) throws CCTException {
        /* 272 */     JsonNode queryBasedWidgetSettings = widgetConfiguration.getSettingsNode();
        /* 273 */     String queryName = "Query0";
        /* 274 */     String fetchQueryExecutionDetailsObjectScript = getQueryExecutionDetails(widgetConfiguration);
        /* 275 */     java.util.Map<String, Object> queryExecutionDetailsObject = null;
        /* 276 */     WebUtils.validateQueryResponseReceived(driver, widgetConfiguration);
        /* 277 */     String queryExecutionDetailsOutput = (String)WebUtils.executeJS(driver, fetchQueryExecutionDetailsObjectScript);
        /*     */
        /*     */     try {
            /* 280 */       queryExecutionDetailsObject = WebUtils.jsonToMap(queryExecutionDetailsOutput);
            /* 281 */     } catch (JSONException e) {
            /* 282 */       throw new CCTException("Return type in custom editor is invalid for the widget :" + widgetConfiguration
/* 283 */           .getId());
            /*     */     }
        /* 285 */     if (!queryExecutionDetailsObject.isEmpty() && queryExecutionDetailsObject.get("queryName") != null) {
            /* 286 */       queryName = queryExecutionDetailsObject.get("queryName").toString();
            /*     */     }
        /* 288 */     if (widgetConfiguration.getSettingsNode().get("isMultipleQueries").equals(BooleanNode.TRUE) && widgetConfiguration.getSettingsNode().get("multipleSettings") != null && widgetConfiguration
/* 289 */       .getSettingsNode().get("multipleSettings").has(queryName)) {
            /* 290 */       queryBasedWidgetSettings = widgetConfiguration.getSettingsNode().get("multipleSettings").get(queryName);
            /*     */     }
        /* 292 */     JsonNode settingsUpdatedBasedOnQueryFlowController = replaceListeningOrQueryParamsInSettings(driver, queryBasedWidgetSettings, widgetConfiguration
/* 293 */         .getId());
        /* 294 */     return settingsUpdatedBasedOnQueryFlowController;
        /*     */   }
    /*     */
    /*     */   private static String getQueryExecutionDetails(WidgetConfiguration widgetConfiguration) {
        /* 298 */     String fetchQueryExecutionDetails = "";
        /* 299 */     if (widgetConfiguration.getSharedResponseParentId() != null && !widgetConfiguration.getSharedResponseParentId().isEmpty()) {
            /*     */
            /* 301 */       fetchQueryExecutionDetails = "var queryExecutionDetailsObj = cemboard.getQueryExecutionDetailsStore().getQueryExecutionDetails('" + widgetConfiguration.getSharedResponseParentId() + "'); if(!$.isEmptyObject(queryExecutionDetailsObj)) { return JSON.stringify(queryExecutionDetailsObj) } else { return '{}'; }";
            /*     */     }
        /*     */     else {
            /*     */
            /* 305 */       fetchQueryExecutionDetails = "var queryExecutionDetailsObj = cemboard.getQueryExecutionDetailsStore().getQueryExecutionDetails('" + widgetConfiguration.getId() + "'); if(!$.isEmptyObject(queryExecutionDetailsObj)) { return JSON.stringify(queryExecutionDetailsObj) } else { return '{}'; }";
            /*     */     }
        /*     */
        /* 308 */     return fetchQueryExecutionDetails;
        /*     */   }
    /*     */
    /*     */
    /*     */
    /*     */   private static JsonNode replaceListeningOrQueryParamsInSettings(WebDriver driver, JsonNode queryBasedWidgetSettings, String widgetId) throws CCTException {
        /* 314 */     JsonNode updatedSettingsWithExpandedParams = queryBasedWidgetSettings;
        /* 315 */     String fetchupdatedSettingsWithExpandedParamsScript = "var widgetSettings = JSON.stringify(" + queryBasedWidgetSettings + ");var queryExecutionDetailsObj = cemboard.getQueryExecutionDetailsStore().getQueryExecutionDetails('" + widgetId + "'); if(!$.isEmptyObject(queryExecutionDetailsObj)) { var _ = cemboard.external.Underscore; var isParamsReplaced = false; var paramsToBeReplaced = _.extend({}, queryExecutionDetailsObj.queryParams, queryExecutionDetailsObj.listenerParams);$.each(paramsToBeReplaced, function(paramKey, paramValue) {var re = new RegExp('%' + paramKey + '%', 'g');if (!isParamsReplaced && widgetSettings.indexOf('%' + paramKey + '%') > -1) { isParamsReplaced = true; }widgetSettings = widgetSettings.replace(re, paramValue); }); }return widgetSettings";
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */
        /*     */     try {
            /* 326 */       String updatedSettingsWithExpandedParamsString = WebUtils.executeJS(driver, fetchupdatedSettingsWithExpandedParamsScript).toString();
            /* 327 */       ObjectMapper mapper = new ObjectMapper();
            /* 328 */       updatedSettingsWithExpandedParams = mapper.readTree(updatedSettingsWithExpandedParamsString);
            /* 329 */     } catch (Exception e) {
            /* 330 */       updatedSettingsWithExpandedParams = queryBasedWidgetSettings;
            /*     */     }
        /* 332 */     return updatedSettingsWithExpandedParams;
        /*     */   }
    /*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\WidgetInstanceFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */