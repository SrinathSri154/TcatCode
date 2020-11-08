/*     */ package com.nokia.cemod.cct.pages;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.CCTEmittedEventsStore;
/*     */ import com.nokia.cemod.cct.core.CCTJsonParser;
/*     */ import com.nokia.cemod.cct.core.configuration.UseCase;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.WidgetInstanceFactory;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.Tab;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeWidgetClick;
/*     */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.codehaus.jackson.map.ObjectMapper;
/*     */ import org.json.JSONException;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.support.PageFactory;
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
/*     */ 
/*     */ 
/*     */ public abstract class CCTBasePage
/*     */ {
/*  42 */   private static final Logger logger = Logger.getLogger(CCTBasePage.class);
/*     */   
/*     */   protected UseCase useCase;
/*     */   
/*     */   protected final WebDriver driver;
/*     */   
/*  48 */   private List<WidgetConfiguration> listOfTestedWidges = new ArrayList<WidgetConfiguration>();
/*     */   
/*  50 */   private String testEnvironment = null;
/*     */   
/*     */   public CCTBasePage(SharedDriver driver) {
/*  53 */     PageFactory.initElements((WebDriver)driver, this);
/*  54 */     this.driver = (WebDriver)driver;
/*  55 */     this.testEnvironment = TestConfiguration.getProperty("cct.env.type");
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract String getUseCaseJSON(String paramString) throws CCTException;
/*     */   
/*     */   protected abstract String getChildUseCaseJSON(String paramString) throws CCTException;
/*     */   
/*     */   protected abstract void launchUseCase(String paramString) throws CCTException;
/*     */   
/*     */   public abstract void forceLogout() throws CCTException;
/*     */   
/*     */   public void loadAndValidateUseCase(String useCaseName) throws CCTException {
/*  68 */     logger.debug("Launch environment: " + this.testEnvironment);
/*  69 */     loadUseCase(useCaseName);
/*  70 */     validateUseCase();
/*     */   }
/*     */   
/*     */   public void validateUseCase() throws CCTException {
/*  74 */     List<WidgetConfiguration> renderedWidgets = getRenderedWidgets();
/*  75 */     assertWidgetsRendered(renderedWidgets);
/*  76 */     validateMultipleEmits();
/*  77 */     performClickOperationsAndValidateAllScenarios(renderedWidgets);
/*     */   }
/*     */   
/*     */   public void setUseCase(String useCaseName) throws CCTException {
/*  81 */     this.useCase = processUseCaseJson(useCaseName);
/*     */   }
/*     */   
/*     */   public UseCase getUseCase() {
/*  85 */     return this.useCase;
/*     */   }
/*     */   
/*     */   public UseCase processUseCaseJson(String useCaseName) throws CCTException {
/*  89 */     String useCaseJsonAsString = getUseCaseJSON(useCaseName);
/*  90 */     return CCTJsonParser.parseUseCaseJSON(useCaseName, useCaseJsonAsString);
/*     */   }
/*     */ 
/*     */   
/*     */   protected CCTEmittedEventsStore getEmittedEventsStore() throws CCTException {
/*  95 */     String emittedEventsStoreDataAsString = WebUtils.executeJS(this.driver, "return JSON.stringify(cemboard.getEmittedEventsStore().getStoreData())").toString();
/*  96 */     logger.debug("Emitted events store data: " + emittedEventsStoreDataAsString);
/*  97 */     CCTEmittedEventsStore cctEmittedEventsStore = CCTJsonParser.parseEmittedEventsStoreData(getUseCase(), emittedEventsStoreDataAsString);
/*     */     
/*  99 */     return cctEmittedEventsStore;
/*     */   }
/*     */   
/*     */   protected List<WidgetConfiguration> getRenderedWidgets() throws CCTException {
/* 103 */     List<WidgetConfiguration> parentWidgets = getUseCase().getListOfParentRenderedWidgets();
/* 104 */     List<WidgetConfiguration> renderedWidgets = new ArrayList<WidgetConfiguration>();
/* 105 */     for (WidgetConfiguration parentWidget : parentWidgets) {
/* 106 */       renderedWidgets.add(parentWidget);
/* 107 */       if (parentWidget.isDefaultEmitEnabled() || parentWidget.isResponseDataCacheEnabled()) {
/* 108 */         renderedWidgets.addAll(getRenderedWidgets(parentWidget));
/*     */       }
/*     */     } 
/* 111 */     return renderedWidgets;
/*     */   }
/*     */   
/*     */   protected List<WidgetConfiguration> getRenderedWidgets(WidgetConfiguration parentWidget) throws CCTException {
/* 115 */     logger.debug("Start : Get render widgets");
/* 116 */     CCTRenderedWidget parentWidgetInstance = getRenderedWidgetInstance(parentWidget.getId());
/* 117 */     logger.debug("Get childrens for parent: " + parentWidget.getId());
/* 118 */     List<WidgetConfiguration> renderedWidgets = new ArrayList<WidgetConfiguration>();
/* 119 */     for (String childWidgetId : parentWidget.getChildrenIds()) {
/* 120 */       WidgetConfiguration childWidget = (WidgetConfiguration)getUseCase().getWidgets().get(childWidgetId);
/*     */       try {
/* 122 */         boolean isWidgetRendered = parentWidgetInstance.isWidgetRendered();
/* 123 */         logger.debug("Parent widget " + parentWidget.getId() + " is rendered ~ " + isWidgetRendered + ", to evaluate drill-down editor code of child widget ~ " + childWidgetId);
/*     */         
/* 125 */         if (parentWidgetInstance.isWidgetRendered() && shouldChildWidgetRender(parentWidget, childWidget)) {
/* 126 */           logger.debug("Adding child widget to rendered list : " + childWidgetId);
/* 127 */           renderedWidgets.add(childWidget);
/* 128 */           if (childWidget.isDefaultEmitEnabled()) {
/* 129 */             renderedWidgets.addAll(getRenderedWidgets(childWidget));
/*     */           }
/*     */         } 
/* 132 */       } catch (CCTException e) {
/* 133 */         logger.debug("Parent widget " + parentWidget.getId() + " is not rendered. Cannot find the list of children widgets to be rendered. Child widget id: " + childWidgetId);
/*     */ 
/*     */         
/* 136 */         reportWidgetErrors(parentWidget.getId(), e.getMessage());
/*     */       } 
/*     */     } 
/* 139 */     logger.debug("End : Get render widgets");
/* 140 */     return renderedWidgets;
/*     */   }
/*     */   
/*     */   private boolean isSharedResponseEmitEnabled(WidgetConfiguration parentWidget, WidgetConfiguration childWidget) {
/* 144 */     if (parentWidget.isResponseDataCacheEnabled() && childWidget
/* 145 */       .getSharedResponseParentId().equals(parentWidget.getId())) {
/* 146 */       return true;
/*     */     }
/* 148 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldChildWidgetRender(WidgetConfiguration parentWidget, WidgetConfiguration childWidget) throws CCTException {
/* 154 */     if (isSharedResponseEmitEnabled(parentWidget, childWidget)) {
/* 155 */       return true;
/*     */     }
/* 157 */     boolean shouldWidgetRender = false;
/* 158 */     CCTEmittedEventsStore cctEmittedEventsStore = getEmittedEventsStore();
/* 159 */     logger.debug("Executing drill-down editor code for child widget id: " + childWidget.getId() + " on emitted data of parent: " + parentWidget
/* 160 */         .getId());
/* 161 */     Map<String, Object> customEditorOutput = getDrilldownEditorParams(childWidget, cctEmittedEventsStore
/* 162 */         .getEmittedEventsData(parentWidget.getId()));
/* 163 */     if (cctEmittedEventsStore.getEmittedEventsData(parentWidget.getId()) != null) {
/* 164 */       Map<String, String> emittedStoreData = cctEmittedEventsStore.getEmittedEventsData(parentWidget.getId());
/* 165 */       if (childWidget.getDrillDownConfiguration().keySet().size() == 0) {
/* 166 */         shouldWidgetRender = true;
/*     */       } else {
/* 168 */         for (Map.Entry<String, List<String>> drillDownConfig : (Iterable<Map.Entry<String, List<String>>>)childWidget.getDrillDownConfiguration().entrySet()) {
/* 169 */           if (emittedStoreData.get(drillDownConfig.getKey()) != null && ((List)drillDownConfig
/* 170 */             .getValue()).contains(emittedStoreData.get(drillDownConfig.getKey()))) {
/* 171 */             shouldWidgetRender = true;
/* 172 */           } else if (isDrillDownEditorValueActive(customEditorOutput, drillDownConfig)) {
/* 173 */             shouldWidgetRender = true;
/*     */           } 
/* 175 */           if (shouldWidgetRender) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 181 */     return shouldWidgetRender;
/*     */   }
/*     */   
/*     */   protected void assertWidgetsRendered(List<WidgetConfiguration> renderedWidgets) throws CCTException {
/* 185 */     for (WidgetConfiguration widgetConfiguration : renderedWidgets) {
/* 186 */       logger.debug("Widget should be rendered: " + widgetConfiguration);
/* 187 */       assertWidgetRendered(widgetConfiguration);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void assertWidgetRendered(WidgetConfiguration widgetConfiguration) throws CCTException {
/* 192 */     this.listOfTestedWidges.add(widgetConfiguration);
/* 193 */     CCTRenderedWidget renderedWidget = null;
/*     */     try {
/* 195 */       renderedWidget = WidgetInstanceFactory.getWidgetInstance(this.driver, widgetConfiguration);
/* 196 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(renderedWidget.isWidgetRendered())
/* 197 */         .as(widgetConfiguration.getId() + " is not rendered", new Object[0])).isEqualTo(true);
/* 198 */     } catch (CCTException e) {
/* 199 */       reportWidgetErrors(widgetConfiguration.getId(), e.getMessage());
/*     */     } finally {
/* 201 */       if (renderedWidget != null) {
/* 202 */         renderedWidget.logQueryResponseTime();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void assertListOfNonTestedWidgets() {
/* 208 */     if (shouldReportNoDataAndStaleWidgetFailures()) {
/* 209 */       for (WidgetConfiguration widget : getUseCase().getListOfAllWidgets()) {
/* 210 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(this.listOfTestedWidges.contains(widget))
/* 211 */           .as("This widget is stale or not tested: " + widget.getId(), new Object[0])).isEqualTo(true);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void assertListOfWidgetsWithConsoleLogsInCustomEditors() {
/* 217 */     for (WidgetConfiguration widgetConfiguration : getUseCase().getListOfAllWidgets()) {
/*     */       try {
/* 219 */         validateConsoleLogsInCustomEditors(widgetConfiguration);
/* 220 */       } catch (CCTException e) {
/* 221 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(true).as(e.getMessage(), new Object[0])).isEqualTo(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void validateMultipleEmits() {
/* 228 */     for (Map.Entry<String, WidgetConfiguration> widgetWidgetConfiguration : (Iterable<Map.Entry<String, WidgetConfiguration>>)getUseCase().getWidgets().entrySet()) {
/* 229 */       ArrayList<String> parentIds = (ArrayList<String>)((WidgetConfiguration)widgetWidgetConfiguration.getValue()).getParentIds();
/* 230 */       int noOfParentsWithDefaultEmitEnabled = 0;
/* 231 */       Map<String, WidgetConfiguration> widgets = getUseCase().getWidgets();
/* 232 */       boolean isIndependantParent = true;
/* 233 */       for (String parentId : parentIds) {
/*     */         try {
/* 235 */           if (((WidgetConfiguration)widgets.get(parentId)).isDefaultEmitEnabled()) {
/* 236 */             noOfParentsWithDefaultEmitEnabled++;
/* 237 */             if (((WidgetConfiguration)widgets.get(parentId)).getParentIds().size() > 0)
/* 238 */               isIndependantParent = false; 
/*     */           } 
/* 240 */         } catch (Exception e) {
/* 241 */           ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 242 */             .as("Widget - " + parentId + ": configured as parent for the widget - " + (String)widgetWidgetConfiguration
/* 243 */               .getKey() + ", but not defined in Json", new Object[0]))
/* 244 */             .isEqualTo(true);
/*     */         } 
/*     */       } 
/* 247 */       if (noOfParentsWithDefaultEmitEnabled > 1 && isIndependantParent) {
/* 248 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 249 */           .as((String)widgetWidgetConfiguration.getKey() + " More than 1 parent widget emits change to this component concurrently", new Object[0]))
/*     */           
/* 251 */           .isEqualTo(true);
/*     */       }
/*     */     } 
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isLinkedViaSharedResponse(Map<String, WidgetConfiguration> widgets, String otherComponentId, String id) {
/* 301 */     return (((WidgetConfiguration)widgets.get(otherComponentId)).getSharedResponseParentId().equalsIgnoreCase(id) || ((WidgetConfiguration)widgets
/* 302 */       .get(id)).getSharedResponseParentId().equalsIgnoreCase(otherComponentId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void performClickOperationsAndValidateAllScenarios(List<WidgetConfiguration> renderedWidgets) {
/* 309 */     List<WidgetConfiguration> parentWidgets = new ArrayList<WidgetConfiguration>();
/* 310 */     List<WidgetConfiguration> applyButtonWidgets = new ArrayList<WidgetConfiguration>();
/* 311 */     for (WidgetConfiguration widget : renderedWidgets) {
/* 312 */       if (widget.getParentIds().size() == 0) {
/* 313 */         if (widget.getType().equals("cemboard-apply-button")) {
/* 314 */           applyButtonWidgets.add(widget); continue;
/*     */         } 
/* 316 */         parentWidgets.add(widget);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 321 */     List<WidgetConfiguration> applyButtonsHavingButtonGroupReference = getListOfApplyButtonsHavingVirtualLinksToButtonGroup(parentWidgets, applyButtonWidgets);
/*     */ 
/*     */ 
/*     */     
/* 325 */     for (WidgetConfiguration applyButtonWidget : applyButtonWidgets) {
/* 326 */       if (!applyButtonsHavingButtonGroupReference.contains(applyButtonWidget)) {
/* 327 */         parentWidgets.add(applyButtonWidget);
/*     */       }
/*     */     } 
/* 330 */     clickAndValidateWidgets(parentWidgets);
/*     */   }
/*     */ 
/*     */   
/*     */   private List<WidgetConfiguration> getListOfApplyButtonsHavingVirtualLinksToButtonGroup(List<WidgetConfiguration> parentWidgets, List<WidgetConfiguration> applyButtonWidgets) {
/* 335 */     List<WidgetConfiguration> applyButtonsHavingButtonGroupReference = new ArrayList<WidgetConfiguration>();
/* 336 */     for (WidgetConfiguration parent : parentWidgets) {
/* 337 */       if (parent.getType().equals("cemboard-button-group")) {
/* 338 */         List<String> buttonGroupChildrens = parent.getChildrenIds();
/* 339 */         for (WidgetConfiguration applyButton : applyButtonWidgets) {
/* 340 */           if (buttonGroupChildrens.contains(applyButton.getId())) {
/* 341 */             applyButtonsHavingButtonGroupReference.add(applyButton);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 346 */     return applyButtonsHavingButtonGroupReference;
/*     */   }
/*     */   
/*     */   private void clickAndValidateWidgets(List<WidgetConfiguration> parentWidgets) {
/* 350 */     for (WidgetConfiguration widgetConfiguration : parentWidgets) {
/* 351 */       clickAndValidateWidget(widgetConfiguration);
/*     */     }
/*     */   }
/*     */   
/*     */   private void clickAndValidateWidget(WidgetConfiguration widgetConfiguration) {
/*     */     try {
/* 357 */       CCTRenderedWidget renderedWidget = WidgetInstanceFactory.getWidgetInstance(this.driver, widgetConfiguration);
/* 358 */       if (renderedWidget.isWidgetRendered()) {
/*     */         
/* 360 */         List<Object> listOfClickableElements = renderedWidget.getMinimumClickableElementsForWorkFlow();
/* 361 */         if (listOfClickableElements.size() == 0 && widgetConfiguration.isResponseDataCacheEnabled()) {
/* 362 */           assertAndValidateChildWidgets(widgetConfiguration);
/*     */         }
/* 364 */         for (Object element : listOfClickableElements) {
/* 365 */           performClick(widgetConfiguration, renderedWidget, element);
/* 366 */           if (TestConfiguration.getProperty("cct.drilldown.test").equals("true"))
/*     */           {
/*     */             
/* 369 */             assertAndValidateChildWidgets(widgetConfiguration);
/*     */           }
/*     */         } 
/*     */       } 
/* 373 */     } catch (CCTException e) {
/* 374 */       if (shouldReportTestFailures(e.getMessage())) {
/* 375 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 376 */           .as(widgetConfiguration.getId() + " ~ Cannot perform click on this widget: " + e.getMessage(), new Object[0]))
/* 377 */           .isEqualTo(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void assertAndValidateChildWidgets(WidgetConfiguration widgetConfiguration) throws CCTException {
/* 383 */     List<WidgetConfiguration> renderedChildWidgets = getRenderedWidgets(widgetConfiguration);
/* 384 */     assertWidgetsRendered(renderedChildWidgets);
/* 385 */     clickAndValidateChildWidgets(renderedChildWidgets, widgetConfiguration.getChildrenIds());
/*     */   }
/*     */ 
/*     */   
/*     */   private void clickAndValidateChildWidgets(List<WidgetConfiguration> renderedChildWidgets, List<String> childerenWidgetIds) {
/* 390 */     List<WidgetConfiguration> parentWidgets = new ArrayList<WidgetConfiguration>();
/* 391 */     for (WidgetConfiguration widget : renderedChildWidgets) {
/* 392 */       if (childerenWidgetIds.contains(widget.getId())) {
/* 393 */         parentWidgets.add(widget);
/*     */       }
/*     */     } 
/* 396 */     clickAndValidateWidgets(parentWidgets);
/*     */   }
/*     */ 
/*     */   
/*     */   private void performClick(WidgetConfiguration widgetConfiguration, CCTRenderedWidget renderedWidget, Object clickElement) throws CCTException {
/* 401 */     logger.debug("\nPerform click operation on widget: " + renderedWidget.getId());
/* 402 */     renderedWidget.click(clickElement);
/* 403 */     RenderingTimeWidgetClick logRenderTime = new RenderingTimeWidgetClick(getUseCase().getUseCaseName(), widgetConfiguration, clickElement);
/*     */     
/* 405 */     logRenderTime.start();
/*     */ 
/*     */     
/* 408 */     WebUtils.waitForWidgetsToRender();
/* 409 */     if (renderedWidget.getType().equalsIgnoreCase("cemboard-tab")) {
/* 410 */       loadSubUseCaseIfTabClick(renderedWidget);
/*     */       try {
/* 412 */         validateWidgetsRenderedToFindRenderTime();
/* 413 */       } catch (CCTException e) {
/* 414 */         logger.error("Unable to find usecase render time: " + e.getMessage());
/* 415 */         logRenderTime.setError(e.getMessage());
/*     */       } 
/*     */     } else {
/*     */       try {
/* 419 */         validateWidgetAndItsChildrensRenderedTime(widgetConfiguration, true);
/* 420 */       } catch (CCTException e) {
/* 421 */         logger.error("Unable to find usecase render time: " + e.getMessage());
/* 422 */         logRenderTime.setError(e.getMessage());
/*     */       } 
/*     */     } 
/* 425 */     logRenderTime.end();
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isDrillDownEditorValueActive(Map<String, Object> drillDownoutput, Map.Entry<String, List<String>> drillDownConfig) {
/* 430 */     if (drillDownoutput != null) {
/* 431 */       for (String key : drillDownoutput.keySet()) {
/* 432 */         if (((String)drillDownConfig.getKey()).equals(key.toString()) && ((List)drillDownConfig
/* 433 */           .getValue()).contains(drillDownoutput.get(key).toString())) {
/* 434 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 438 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<String, Object> getDrilldownEditorParams(WidgetConfiguration widgetConfiguration, Map<String, String> emittedData) throws CCTException {
/* 443 */     String customEditorScript = widgetConfiguration.getCustomEditorDrillDownCode();
/* 444 */     Map<String, Object> output = null;
/* 445 */     if (customEditorScript != null && !customEditorScript.isEmpty() && emittedData != null) {
/* 446 */       String jsonResp = "";
/* 447 */       ObjectMapper mapperObj = new ObjectMapper();
/*     */       try {
/* 449 */         jsonResp = mapperObj.writeValueAsString(emittedData);
/* 450 */       } catch (IOException e) {
/* 451 */         logger.error("Exception in getDrilldownEditorParams", e);
/*     */       } 
/* 453 */       String store = "cemboard.getEmittedEventsStore().getStoreData();";
/* 454 */       customEditorScript = initializeCustomEditorInjectionParams(customEditorScript, jsonResp, store);
/* 455 */       customEditorScript = customEditorScript + "\nreturn JSON.stringify(output);";
/*     */       try {
/* 457 */         String drillDownEditorOutput = (String)WebUtils.executeJS(this.driver, customEditorScript);
/* 458 */         output = WebUtils.jsonToMap(drillDownEditorOutput);
/* 459 */       } catch (JSONException e) {
/* 460 */         throw new CCTException("Return type in custom editor is invalid for the widget :" + widgetConfiguration
/* 461 */             .getId());
/*     */       } 
/*     */     } 
/* 464 */     return output;
/*     */   }
/*     */ 
/*     */   
/*     */   private String initializeCustomEditorInjectionParams(String customEditorScript, String drilldownData, String store) {
/* 469 */     String args = "var output ={};var editor = {};editor.locale =  cemboard.core.Locale;editor.moment = cemboard.external.Moment;editor.logger = cemboard.utils.Logger;editor.cgfFilters = cemboard.application.service.getCgfFilterParams();editor.responseStore = cemboard.getResponseDatasStore().getStoreData();editor.getUsecaseConfigurations = cemboard.application.service.getUsecaseConfigurations();editor.templates = cemboard.core.CemboardTemplates;editor.eventsStore = cemboard.getEmittedEventsStore().getStoreData();editor.drillDownParams = " + drilldownData + ";store =" + store + "input = " + drilldownData + ";";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 477 */     customEditorScript = args + customEditorScript;
/* 478 */     return customEditorScript;
/*     */   }
/*     */   
/*     */   public void loadUseCase(String useCaseName) throws CCTException {
/* 482 */     launchUseCase(useCaseName);
/*     */   }
/*     */   
/*     */   public void clickOnWidget(String widgetId, int clickableElementIndex) throws CCTException {
/* 486 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/*     */     try {
/* 488 */       CCTRenderedWidget renderedWidget = getRenderedWidgetInstance(widgetId);
/* 489 */       if (renderedWidget.isWidgetRendered()) {
/*     */         
/* 491 */         List<Object> listOfClickableElements = renderedWidget.getAllPossibleClickableElements();
/* 492 */         if (clickableElementIndex < listOfClickableElements.size()) {
/* 493 */           performClick(widgetConfiguration, renderedWidget, listOfClickableElements
/* 494 */               .get(clickableElementIndex));
/*     */         } else {
/* 496 */           ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Clickable index not found", new Object[0])).isEqualTo(true);
/*     */         } 
/*     */       } 
/* 499 */     } catch (CCTException e) {
/* 500 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 501 */         .as(widgetConfiguration.getId() + " ~ Error Message: " + e.getMessage(), new Object[0])).isEqualTo(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clickOnWidget(String widgetId, String value) throws CCTException {
/* 506 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/*     */     try {
/* 508 */       CCTRenderedWidget renderedWidget = getRenderedWidgetInstance(widgetId);
/*     */       try {
/* 510 */         Object element = renderedWidget.getClickableElementForValue(value);
/* 511 */         performClick(widgetConfiguration, renderedWidget, element);
/* 512 */       } catch (CCTException e) {
/* 513 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Could not find the WebElement with value: " + value + " ~ Error message: " + e
/* 514 */             .getMessage(), new Object[0]))
/* 515 */           .isEqualTo(true);
/*     */       } 
/* 517 */     } catch (CCTException e) {
/* 518 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 519 */         .as(widgetConfiguration.getId() + " ~ Error Message: " + e.getMessage(), new Object[0])).isEqualTo(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void isWidgetRendered(String widgetId) throws CCTException {
/* 524 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/* 525 */     assertWidgetRendered(widgetConfiguration);
/*     */   }
/*     */   
/*     */   public void isWidgetNotRendered(String widgetId) throws CCTException {
/* 529 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/*     */     try {
/* 531 */       this.listOfTestedWidges.add(widgetConfiguration);
/* 532 */       CCTRenderedWidget renderedWidget = getRenderedWidgetInstance(widgetId);
/* 533 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(renderedWidget.isWidgetRendered())
/* 534 */         .as(widgetConfiguration.getId() + " is rendered which is not supposed to...", new Object[0])).isEqualTo(false);
/* 535 */     } catch (CCTException cCTException) {}
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateWorkFlow(String widgetId) throws CCTException {
/* 540 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/* 541 */     if (widgetId.contains("cemboard-tab-")) {
/* 542 */       validateUseCase();
/*     */     } else {
/* 544 */       clickAndValidateWidget(widgetConfiguration);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateApplyButtonWorkFlowForAllWidgetValues(String applyButtonId) throws CCTException {
/* 551 */     String linkedWidgetIds = getApplyButtonLinkedIds((WidgetConfiguration)getUseCase().getWidgets().get(applyButtonId));
/* 552 */     applyChangeOnEachWidgetClick(applyButtonId, linkedWidgetIds);
/*     */   }
/*     */   
/*     */   private String getApplyButtonLinkedIds(WidgetConfiguration applyButtonwidgetConfiguration) throws CCTException {
/* 556 */     String linkedWidgetIds = "";
/* 557 */     if (applyButtonwidgetConfiguration.getSettingsNode().get("customizedComponentIds") != null && 
/* 558 */       !applyButtonwidgetConfiguration.getSettingsNode().get("customizedComponentIds").asText().isEmpty()) {
/* 559 */       String store = "cemboard.getResponseDatasStore().getStoreData();";
/*     */       
/* 561 */       String customEditorScript = applyButtonwidgetConfiguration.getSettingsNode().get("customizedComponentIds").asText();
/* 562 */       customEditorScript = initializeCustomEditorInjectionParams(customEditorScript, null, store);
/* 563 */       customEditorScript = customEditorScript + "\nreturn output;";
/*     */       try {
/* 565 */         linkedWidgetIds = (String)WebUtils.executeJS(this.driver, customEditorScript);
/* 566 */       } catch (ClassCastException e) {
/* 567 */         throw new CCTException("Return type in custom editor is invalid for the widget :" + applyButtonwidgetConfiguration
/* 568 */             .getId());
/*     */       } 
/*     */     } else {
/* 571 */       linkedWidgetIds = applyButtonwidgetConfiguration.getSettingsNode().get("getComponentValues").asText();
/*     */     } 
/* 573 */     return linkedWidgetIds;
/*     */   }
/*     */   
/*     */   private void applyChangeOnEachWidgetClick(String applyWidgetId, String linkedWidgetIds) throws CCTException {
/* 577 */     String[] widgetids = linkedWidgetIds.split(",");
/* 578 */     for (int i = 0; i < widgetids.length; i++) {
/* 579 */       CCTRenderedWidget renderedWidget = WidgetInstanceFactory.getWidgetInstance(this.driver, (WidgetConfiguration)
/* 580 */           getUseCase().getWidgets().get(widgetids[i]));
/* 581 */       List<Object> clickableElements = renderedWidget.getAllPossibleClickableElements();
/* 582 */       for (int j = 0; j < clickableElements.size(); j++) {
/* 583 */         renderedWidget.click(clickableElements.get(j));
/* 584 */         validateWorkFlow(applyWidgetId);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateApplyButtonWorkFlowForAllCombinationsOfWidgetValues(String applyButtonId, String[] widgetIds) throws CCTException {
/* 591 */     performClickForAllCombinations(0, applyButtonId, widgetIds);
/*     */   }
/*     */ 
/*     */   
/*     */   private void performClickForAllCombinations(int index, String applyButtonId, String[] widgetIds) throws CCTException {
/* 596 */     CCTRenderedWidget renderedWidget = getRenderedWidgetInstance(widgetIds[index]);
/* 597 */     if (renderedWidget.isWidgetRendered()) {
/* 598 */       for (Object clickableElement : renderedWidget.getAllPossibleClickableElements()) {
/* 599 */         logger.debug("\nPerform click operation on widget: " + renderedWidget.getId());
/* 600 */         renderedWidget.click(clickableElement);
/* 601 */         WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(renderedWidget.getId());
/*     */         
/* 603 */         RenderingTimeWidgetClick logRenderTime = new RenderingTimeWidgetClick(getUseCase().getUseCaseName(), widgetConfiguration, clickableElement);
/* 604 */         logRenderTime.start();
/*     */ 
/*     */         
/* 607 */         WebUtils.waitForWidgetsToRender();
/* 608 */         logRenderTime.end();
/* 609 */         if (index + 1 < widgetIds.length) {
/* 610 */           performClickForAllCombinations(index + 1, applyButtonId, widgetIds); continue;
/*     */         } 
/* 612 */         validateWorkFlow(applyButtonId);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateWidgetsRendered() throws CCTException {
/* 619 */     logger.debug("Start: Validate widgets rendered");
/* 620 */     for (WidgetConfiguration parentWidget : getUseCase().getListOfParentRenderedWidgets()) {
/* 621 */       validateWidgetAndItsChildrensRendered(parentWidget, false);
/*     */     }
/* 623 */     logger.debug("End: Validate widgets rendered");
/*     */   }
/*     */ 
/*     */   
/*     */   private void validateWidgetAndItsChildrensRendered(WidgetConfiguration parentWidget, boolean isClicked) throws CCTException {
/*     */     try {
/* 629 */       CCTRenderedWidget parentWidgetInstance = getRenderedWidgetInstance(parentWidget.getId());
/* 630 */       logger.debug("Validate that widget is rendered: " + parentWidget.getId());
/* 631 */       if (parentWidgetInstance.isWidgetRendered()) {
/* 632 */         for (String childWidgetId : parentWidget.getChildrenIds()) {
/* 633 */           WidgetConfiguration childWidget = (WidgetConfiguration)getUseCase().getWidgets().get(childWidgetId);
/* 634 */           if ((isClicked || parentWidget.isDefaultEmitEnabled() || 
/* 635 */             isSharedResponseEmitEnabled(parentWidget, childWidget)) && 
/* 636 */             shouldChildWidgetRender(parentWidget, childWidget)) {
/* 637 */             validateWidgetAndItsChildrensRendered(childWidget, false);
/*     */           }
/*     */         } 
/*     */       } else {
/* 641 */         reportWidgetErrors(parentWidget.getId(), "Widget is not rendered");
/*     */       } 
/* 643 */     } catch (CCTException e) {
/* 644 */       reportWidgetErrors(parentWidget.getId(), e.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validateWidgetsRenderedToFindRenderTime() throws CCTException {
/* 649 */     logger.debug("Start: Validate widgets rendered to calculate render time");
/* 650 */     for (WidgetConfiguration parentWidget : getUseCase().getListOfParentRenderedWidgets()) {
/* 651 */       validateWidgetAndItsChildrensRenderedTime(parentWidget, false);
/*     */     }
/* 653 */     logger.debug("End: Validate widgets rendered to calculate render time");
/*     */   }
/*     */ 
/*     */   
/*     */   public void validateWidgetAndItsChildrensRenderedTime(WidgetConfiguration parentWidget, boolean isClicked) throws CCTException {
/* 658 */     CCTRenderedWidget parentWidgetInstance = getRenderedWidgetInstance(parentWidget.getId());
/* 659 */     logger.debug("Validate that widget is rendered: " + parentWidget.getId());
/*     */     try {
/* 661 */       if (parentWidgetInstance.isWidgetRendered()) {
/* 662 */         for (String childWidgetId : parentWidget.getChildrenIds()) {
/* 663 */           WidgetConfiguration childWidget = (WidgetConfiguration)getUseCase().getWidgets().get(childWidgetId);
/* 664 */           if ((isClicked || parentWidget.isDefaultEmitEnabled() || 
/* 665 */             isSharedResponseEmitEnabled(parentWidget, childWidget)) && 
/* 666 */             shouldChildWidgetRender(parentWidget, childWidget)) {
/* 667 */             validateWidgetAndItsChildrensRenderedTime(childWidget, false);
/*     */           }
/*     */         } 
/*     */       } else {
/* 671 */         throw new CCTException("Widget is not rendered");
/*     */       } 
/* 673 */     } catch (CCTException e) {
/* 674 */       if (shouldReportTestFailures(e.getMessage())) {
/* 675 */         throw e;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void loadSubUseCaseIfTabClick(CCTRenderedWidget renderedWidget) throws CCTException {
/* 681 */     if (renderedWidget.getType().equalsIgnoreCase("cemboard-tab")) {
/* 682 */       Tab tab = (Tab)WidgetInstanceFactory.getWidgetInstance(this.driver, (WidgetConfiguration)
/* 683 */           getUseCase().getWidgets().get(renderedWidget.getId()));
/* 684 */       String useCaseJsonAsString = getChildUseCaseJSON(tab.getSelectedUseCaseName());
/* 685 */       this.useCase = CCTJsonParser.parseUseCaseJSON(tab.getSelectedUseCaseName(), useCaseJsonAsString);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CCTRenderedWidget getRenderedWidgetInstance(String widgetId) throws CCTException {
/* 690 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/* 691 */     return WidgetInstanceFactory.getWidgetInstance(this.driver, widgetConfiguration);
/*     */   }
/*     */   
/*     */   private void validateConsoleLogsInCustomEditors(WidgetConfiguration widgetConfiguration) throws CCTException {
/* 695 */     String consoleKey = "console.log(";
/* 696 */     String debuggerKey = "debugger";
/* 697 */     if (widgetConfiguration.getCustomEditorDrillDownCode().indexOf(consoleKey) > -1 || widgetConfiguration
/* 698 */       .getCustomEditorDrillDownCode().indexOf(debuggerKey) > -1) {
/* 699 */       throw new CCTException(widgetConfiguration.getId() + " : either console.log or debugger is been used in custom editor code");
/*     */     }
/*     */     
/* 702 */     if (widgetConfiguration.getCustomEditorQueryFlowControllerCode().indexOf(consoleKey) > -1 || widgetConfiguration
/* 703 */       .getCustomEditorQueryFlowControllerCode().indexOf(debuggerKey) > -1) {
/* 704 */       throw new CCTException(widgetConfiguration.getId() + " : either console.log or debugger is been used in custom query flow contorller code");
/*     */     }
/*     */     
/* 707 */     if (widgetConfiguration.getCustomEditorResponseFormatterCode().indexOf(consoleKey) > -1 || widgetConfiguration
/* 708 */       .getCustomEditorResponseFormatterCode().indexOf(debuggerKey) > -1) {
/* 709 */       throw new CCTException(widgetConfiguration.getId() + " : either console.log or debugger is been used in response editor code");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public WebDriver getWebDriver() {
/* 715 */     return this.driver;
/*     */   }
/*     */   
/*     */   private void reportWidgetErrors(String widgetId, String errorMessage) {
/* 719 */     if (shouldReportTestFailures(errorMessage)) {
/* 720 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 721 */         .as(widgetId + " is not rendered ~ Error Message: " + errorMessage, new Object[0])).isEqualTo(true);
/*     */     }
/*     */   }
/*     */   
/*     */   private boolean shouldReportTestFailures(String errorMessage) {
/* 726 */     boolean reportFailure = true;
/* 727 */     if ("No data available".equalsIgnoreCase(errorMessage) && 
/* 728 */       !shouldReportNoDataAndStaleWidgetFailures()) {
/* 729 */       reportFailure = false;
/*     */     }
/* 731 */     return reportFailure;
/*     */   }
/*     */   
/*     */   private boolean shouldReportNoDataAndStaleWidgetFailures() {
/* 735 */     return "true".equals(
/* 736 */         TestConfiguration.getProperty("cct.env.reportNoDataAndStaleWidgetAsFailures"));
/*     */   }
/*     */   
/*     */   public void clickOnWidget(String widgetId, int rowIndex, int colIndex, String value) {
/* 740 */     WidgetConfiguration widgetConfiguration = (WidgetConfiguration)getUseCase().getWidgets().get(widgetId);
/*     */     try {
/* 742 */       CCTRenderedWidget renderedWidget = getRenderedWidgetInstance(widgetId);
/* 743 */       if (renderedWidget.isWidgetRendered()) {
/* 744 */         List<Object> listOfClickableElements = renderedWidget.getAllPossibleClickableElements();
/* 745 */         if (rowIndex < listOfClickableElements.size()) {
/* 746 */           WebElement rowElement = (WebElement)listOfClickableElements.get(rowIndex);
/* 747 */           List<WebElement> clickableCells = rowElement.findElements(By.tagName("td"));
/* 748 */           WebElement colElement = clickableCells.get(colIndex);
/* 749 */           performClick(widgetConfiguration, renderedWidget, colElement);
/* 750 */           renderedWidget.updateValueForClickableIndex(value, colIndex, rowIndex);
/*     */         } else {
/* 752 */           ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Clickable index not found", new Object[0])).isEqualTo(true);
/*     */         } 
/*     */       } 
/* 755 */     } catch (CCTException e) {
/* 756 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 757 */         .as(widgetConfiguration.getId() + " ~ Error Message: " + e.getMessage(), new Object[0])).isEqualTo(true);
/*     */     } 
/*     */   }
/*     */ }


