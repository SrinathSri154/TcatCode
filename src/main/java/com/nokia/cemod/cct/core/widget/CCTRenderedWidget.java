/*     */ package com.nokia.cemod.cct.core.widget;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.logger.queryResponse.QueryResponseTime;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.json.JSONException;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebDriverException;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CCTRenderedWidget
/*     */ {
/*     */   protected WebDriver webdriver;
/*     */   protected WidgetConfiguration widgetConfiguration;
/*     */   protected WebElement widgetWrapper;
/*  34 */   protected static final Logger logger = Logger.getLogger(CCTRenderedWidget.class);
/*     */   
/*     */   public CCTRenderedWidget(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  37 */     this.webdriver = webdriver;
/*  38 */     this.widgetConfiguration = widgetConfiguration;
/*     */   }
/*     */   
/*     */   public void processWidget() throws CCTException {
/*     */     try {
/*  43 */       this.widgetWrapper = WebUtils.waitAndFindElementBy(this.webdriver, By.id(this.widgetConfiguration.getId()));
/*  44 */     } catch (NoSuchElementException e) {
/*  45 */       throw new CCTException("Widget not found within the configured Timeout");
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isWidgetRendered() throws CCTException {
/*  50 */     boolean isWidgetRendered = false;
/*  51 */     isWidgetRendered = this.widgetWrapper.isDisplayed();
/*  52 */     validateQueryResponseReceived();
/*  53 */     validateErrorScenarios();
/*  54 */     validateWidgetTitle();
/*  55 */     return isWidgetRendered;
/*     */   }
/*     */   
/*     */   public abstract List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException;
/*     */   
/*     */   public List<Object> getAllPossibleClickableElements() throws CCTException {
/*  61 */     return getMinimumClickableElementsForWorkFlow();
/*     */   }
/*     */   
/*     */   public Object getClickableElementForValue(String value) throws CCTException {
/*  65 */     throw new CCTException("Widget wrapper " + getType() + " does not support - select element by value");
/*     */   }
/*     */   
/*     */   public void click(Object element) throws CCTException {
/*  69 */     if (element instanceof WebElement) {
/*  70 */       WebElement webElement = (WebElement)element;
/*  71 */       WebUtils.scrollToElementAndCenter(this.webdriver, webElement);
/*     */       try {
/*  73 */         webElement.click();
/*  74 */       } catch (WebDriverException e) {
/*  75 */         WebUtils.captureScreenShot(this.webdriver);
/*  76 */         throw new CCTException("Unable to click on the widget. " + e.getMessage());
/*     */       } 
/*     */     } else {
/*  79 */       throw new CCTException("Unable to click: Widget wrapper error - Not a WebElement Instance");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void validateQueryResponseReceived() throws CCTException {
/*  84 */     WebUtils.validateQueryResponseReceived(this.webdriver, this.widgetConfiguration);
/*     */   }
/*     */   
/*     */   protected void validateErrorScenarios() throws CCTException {
/*  88 */     String errorMessage = getWidgetRenderedErrorMessage();
/*  89 */     if (!"".equals(errorMessage)) {
/*  90 */       throw new CCTException(errorMessage);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void validateWidgetTitle() throws CCTException {
/*  95 */     Pattern pattern = Pattern.compile("%(.*?)%");
/*  96 */     List<WebElement> element = this.webdriver.findElements(By.xpath(".//p[contains(text(),'" + this.widgetConfiguration
/*  97 */           .getId() + "')]/following-sibling::div[@id='widgetHeader']"));
/*     */     
/*  99 */     if (element.size() != 0 && ((WebElement)element.get(0)).getText() != null && !((WebElement)element.get(0)).getText().isEmpty()) {
/* 100 */       Matcher matcher = pattern.matcher(((WebElement)element.get(0)).getText());
/* 101 */       if (matcher.find()) {
/* 102 */         logger.debug(this.widgetConfiguration.getId() + " with title - " + ((WebElement)element.get(0)).getText() + " has not rendered properly.");
/*     */         
/* 104 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(true).as(this.widgetConfiguration.getId() + " with title - " + ((WebElement)element
/* 105 */             .get(0)).getText() + " has not rendered properly.", new Object[0])).isEqualTo(false);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String getWidgetRenderedErrorMessage() throws CCTException {
/* 111 */     String message = "";
/*     */     
/* 113 */     List<WebElement> elementsList = WebUtils.findElementsBy(this.webdriver, 
/* 114 */         By.className("customMessage_" + this.widgetConfiguration.getId()));
/* 115 */     if (elementsList.size() > 0) {
/* 116 */       message = ((WebElement)elementsList.get(0)).getText();
/*     */     }
/* 118 */     return message;
/*     */   }
/*     */   
/*     */   public void logQueryResponseTime() throws CCTException {
/* 122 */     Map<String, Object> queryExecutionDetailsObject = new HashMap<String, Object>();
/* 123 */     queryExecutionDetailsObject.put("usecaseName", getUseCaseName());
/* 124 */     queryExecutionDetailsObject.put("queryStartTime", Long.valueOf(0L));
/* 125 */     queryExecutionDetailsObject.put("queryEndTime", Long.valueOf(0L));
/* 126 */     queryExecutionDetailsObject.put("queryResponseTimeInMs", "Query Response Time not captured");
/* 127 */     queryExecutionDetailsObject.put("message", "Query Response Message not captured");
/* 128 */     if (this.widgetConfiguration.getSettingsNode().get("queryAttributes") == null || this.widgetConfiguration
/* 129 */       .getSettingsNode().get("queryAttributes").asText().isEmpty()) {
/* 130 */       queryExecutionDetailsObject.put("queryResponseTimeInMs", "No query to capture response time");
/* 131 */       queryExecutionDetailsObject.put("message", "NA");
/*     */     } else {
/* 133 */       queryExecutionDetailsObject = updateQueryExecutionDetailsObj(queryExecutionDetailsObject);
/*     */     } 
/* 135 */     QueryResponseTime responseTime = new QueryResponseTime();
/* 136 */     responseTime.setLoggerTimestamp(new Date());
/* 137 */     responseTime.setUseCaseName(queryExecutionDetailsObject.get("usecaseName").toString());
/* 138 */     responseTime.setWidgetId(this.widgetConfiguration.getId());
/* 139 */     responseTime.setWidgetTitle(this.widgetConfiguration.getTitle());
/* 140 */     responseTime.setQueryStartTime(((Long)queryExecutionDetailsObject.get("queryStartTime")).longValue());
/* 141 */     responseTime.setQueryEndTime(((Long)queryExecutionDetailsObject.get("queryEndTime")).longValue());
/* 142 */     responseTime.setResponseTime(queryExecutionDetailsObject.get("queryResponseTimeInMs").toString());
/* 143 */     responseTime.setResponseMessage(queryExecutionDetailsObject.get("message").toString());
/* 144 */     responseTime.log();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Object> updateQueryExecutionDetailsObj(Map<String, Object> queryExecutionDetailsObject) throws CCTException {
/* 150 */     String fetchQueryExecutionDetailsObjectScript = "var queryExecutionDetailsObj = cemboard.getQueryExecutionDetailsStore().getQueryExecutionDetails('" + this.widgetConfiguration.getId() + "'); if(!$.isEmptyObject(queryExecutionDetailsObj)) { return JSON.stringify(queryExecutionDetailsObj) } else { return '{}'; }";
/*     */     
/* 152 */     Map<String, Object> queryExecutionDetailsObjFetched = null;
/* 153 */     String queryExecutionDetailsString = (String)WebUtils.executeJS(this.webdriver, fetchQueryExecutionDetailsObjectScript);
/*     */     
/*     */     try {
/* 156 */       queryExecutionDetailsObjFetched = WebUtils.jsonToMap(queryExecutionDetailsString);
/* 157 */     } catch (JSONException e) {
/* 158 */       throw new CCTException("Return type in custom editor is invalid for the widget :" + this.widgetConfiguration
/* 159 */           .getId());
/*     */     } 
/* 161 */     if (!queryExecutionDetailsObjFetched.isEmpty()) {
/* 162 */       queryExecutionDetailsObject.put("queryStartTime", queryExecutionDetailsObjFetched
/* 163 */           .get("queryStartTime"));
/* 164 */       queryExecutionDetailsObject.put("queryEndTime", queryExecutionDetailsObjFetched.get("queryEndTime"));
/* 165 */       queryExecutionDetailsObject.put("queryResponseTimeInMs", queryExecutionDetailsObjFetched
/* 166 */           .get("queryResponseTimeInMs"));
/* 167 */       queryExecutionDetailsObject.put("message", queryExecutionDetailsObjFetched.get("message"));
/*     */     } 
/* 169 */     return queryExecutionDetailsObject;
/*     */   }
/*     */   
/*     */   private String getUseCaseName() throws CCTException {
/* 173 */     String usecaseName = "Cemportal";
/* 174 */     String fetchUsecaseNameScript = "var useCaseName = 'Cemportal'; if (cemportalSettings && cemportalSettings.useCaseName) {useCaseName = cemportalSettings.useCaseName; } return useCaseName;";
/*     */     
/* 176 */     usecaseName = WebUtils.executeJS(this.webdriver, fetchUsecaseNameScript).toString();
/* 177 */     return usecaseName;
/*     */   }
/*     */   
/*     */   public String getId() {
/* 181 */     return this.widgetConfiguration.getId();
/*     */   }
/*     */   
/*     */   public String getType() {
/* 185 */     return this.widgetConfiguration.getType();
/*     */   }
/*     */   
/*     */   public String getWidgetHeader() {
/* 189 */     return null;
/*     */   }
/*     */   
/*     */   public String getWidgetSubHeader() {
/* 193 */     return null;
/*     */   }
/*     */   
/*     */   public void updateValueForClickableIndex(String value, int colIndex, int rowIndex) throws CCTException {}
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\CCTRenderedWidget.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */