/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.CompositeChartSettings;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.interactions.Actions;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class CompositeChart
/*     */   extends CCTRenderedWidget
/*     */ {
/*     */   private CompositeChartSettings settings;
/*     */   List<String> clickableCells;
/*     */   HashMap<String, ArrayList<String>> kpiAndColorNames;
/*     */   ArrayList<Map<String, String>> kpiNamesAndColorFromDOM;
/*  33 */   private static final Logger logger = Logger.getLogger(CompositeChart.class);
/*     */
/*     */   public CompositeChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  36 */     super(webdriver, widgetConfiguration);
/*  37 */     this.settings = (CompositeChartSettings)widgetConfiguration.getSettings();
/*     */   }
/*     */
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*  41 */     this.clickableCells = new ArrayList<String>();
/*  42 */     this.kpiAndColorNames = this.settings.getKpiNamesandColor();
/*  43 */     if (this.settings.isLegendsConfigured()) {
/*  44 */       if (isCustomizeWidgetSettings()) {
/*  45 */         formClickableCellsFromDOM();
/*     */       } else {
/*  47 */         for (String kpiName : this.kpiAndColorNames.keySet()) {
/*  48 */           if (!Pattern.matches("^%.*%$", kpiName)) {
/*  49 */             this.clickableCells.add(kpiName);
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/*  54 */       for (String kpiName : this.kpiAndColorNames.keySet()) {
/*  55 */         ArrayList<String> kpiVal = this.kpiAndColorNames.get(kpiName);
/*  56 */         WebElement pathElement = getSvgPathElementsForChart(kpiVal.get(0), kpiVal.get(1));
/*  57 */         if (pathElement != null) {
/*  58 */           this.clickableCells.add(kpiName);
/*     */         }
/*     */       }
/*     */     }
/*  62 */     return new ArrayList(this.clickableCells);
/*     */   }
/*     */
/*     */   private void formClickableCellsFromDOM() {
/*  66 */     this.kpiNamesAndColorFromDOM = getKpiNamesFromDOM();
/*  67 */     for (Map<String, String> iter : this.kpiNamesAndColorFromDOM) {
/*  68 */       for (String keys : iter.keySet()) {
/*  69 */         this.clickableCells.add(keys);
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   private ArrayList<Map<String, String>> getKpiNamesFromDOM() {
/*  76 */     ArrayList<Map<String, String>> element = new ArrayList<Map<String, String>>();
/*     */
/*     */
/*     */
/*     */     try {
/*  81 */       String kpiNames = "var _ = cemboard.external.Underscore;var textElements = $('#" + this.widgetConfiguration.getId() + " path').siblings('g').children('text');var elements = []; var obj = {}; textElements.each(function(id,value){var textValue = $(value).text() ; var series = $('#" + this.widgetConfiguration.getId() + "').data('kendoChart').options.series;var kpiColor; var chartType;var foundSeries = _.find(series,{name:textValue}); if(foundSeries) {chartType = foundSeries.chartType; kpiColor = foundSeries.color}obj[textValue] = kpiColor+'-'+chartType;}); elements.push(obj); return elements;";
/*     */
/*     */
/*     */
/*  85 */       element = (ArrayList<Map<String, String>>)WebUtils.executeJS(this.webdriver, kpiNames);
/*  86 */     } catch (CCTException e) {
/*  87 */       logger.error("Unable to get Kpi Names from DOM : " + e.getMessage());
/*     */     }
/*  89 */     return element;
/*     */   }
/*     */
/*     */
/*     */   public boolean isCustomizeWidgetSettings() {
/*     */     try {
/*  95 */       String customizedWidgetSettings = "var customizedWidgetSettings = cemboard.getWidgetSettings('" + this.widgetConfiguration.getId() + "').queryFlowEditor; return customizedWidgetSettings;";
/*  96 */       String queryExecutionDetailsOutput = (String)WebUtils.executeJS(this.webdriver, customizedWidgetSettings);
/*  97 */       if (queryExecutionDetailsOutput != null && queryExecutionDetailsOutput
/*  98 */         .contains("customizeWidgetSettings")) {
/*  99 */         return true;
/*     */       }
/* 101 */     } catch (CCTException e) {
/* 102 */       logger.error("Error occured in getting customized widget settings : " + e.getMessage());
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */
/*     */
/*     */   public void click(Object element) throws CCTException {
/* 109 */     Actions action = new Actions(this.webdriver);
/* 110 */     String serieName = (String)element;
/* 111 */     String legendId = getLegendId();
/*     */
/* 113 */     disableAllLegends(legendId);
/* 114 */     WebElement serieElement = getSerieLegendElement(legendId, serieName);
/* 115 */     clickOnClickableElementInChart(serieElement, serieName, action);
/*     */   }
/*     */
/*     */
/*     */   private void clickOnClickableElementInChart(WebElement serieElement, String serieName, Actions action) throws CCTException {
/* 120 */     if (serieElement != null && !serieName.equalsIgnoreCase("")) {
/*     */
/* 122 */       action.moveToElement(serieElement).click().perform();
/* 123 */       WebUtils.waitForWidgetsToRender();
/*     */     }
/* 125 */     String kpiColor = "";
/* 126 */     String kpiChartType = "";
/* 127 */     if (isCustomizeWidgetSettings()) {
/* 128 */       for (Map<String, String> iter : this.kpiNamesAndColorFromDOM) {
/* 129 */         String series = iter.get(serieName);
/* 130 */         kpiColor = series.split("-")[0];
/* 131 */         kpiChartType = series.split("-")[1];
/*     */       }
/*     */     } else {
/* 134 */       kpiColor = ((ArrayList<String>)this.kpiAndColorNames.get(serieName)).get(0);
/* 135 */       kpiChartType = ((ArrayList<String>)this.kpiAndColorNames.get(serieName)).get(1);
/*     */     }
/* 137 */     if (kpiChartType.equals("column") || kpiChartType.equals("bar")) {
/* 138 */       clickOnBarOrColumn(action, serieName, kpiColor);
/* 139 */     } else if (kpiChartType.equals("line")) {
/* 140 */       clickOnLine(action, serieName, kpiColor);
/* 141 */     } else if (kpiChartType.equals("step")) {
/* 142 */       if (serieElement != null && !serieName.equalsIgnoreCase("")) {
/* 144 */         action.moveToElement(getSerieLegendElement(getLegendId(), serieName)).build().perform();
/* 145 */         clickOnStep(action, serieName, kpiColor);
/*     */       } else {
/*     */
/* 148 */         WebElement pathElement = getSvgPathElementsForChart(kpiColor, kpiChartType);
/* 149 */         if (pathElement != null) {
/* 150 */           String initMouseOverEventScript = "var evObj = document.createEvent('MouseEvents');evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);arguments[0].dispatchEvent(evObj);";
/*     */
/*     */
/* 153 */           WebUtils.executeJSWithWebElement(this.webdriver, initMouseOverEventScript, pathElement);
/* 154 */           clickOnStep(action, serieName, kpiColor);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   private WebElement getSvgPathElementsForChart(String kpiNameColor, String chartType) throws CCTException {
/* 162 */     String propertyToMatch = "fill";
/* 163 */     if (chartType.equalsIgnoreCase("line") || chartType.equalsIgnoreCase("step")) {
/* 164 */       propertyToMatch = "stroke";
/*     */     }
/* 166 */     List<WebElement> pathElements = (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/* 167 */         .getId() + " path[" + propertyToMatch + "=" + kpiNameColor + "]')");
/* 168 */     if (pathElements.size() > 0) {
/* 169 */       return pathElements.get(0);
/*     */     }
/* 171 */     return null;
/*     */   }
/*     */
/*     */   private void clickOnBarOrColumn(Actions action, String serieName, String serieColor) throws CCTException {
                List <WebElement> seriesInChart = findNumberOfSeriesInChart(serieColor);
/* 175 */     if (seriesInChart != null && !seriesInChart.isEmpty()) {
                WebUtils.scrollToElementAndCenter(this.webdriver, seriesInChart.get(0));
/* 177 */       action.moveToElement(seriesInChart.get(0)).click().perform();
/*     */     } else {
/* 179 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 180 */         .as(this.widgetConfiguration.getId() + " clickOnBarOrColumn ~ Cannot perform click on this widget: Chart is empty for the Kpi : " + serieName, new Object[0]))
/* 182 */         .isEqualTo(true);
/*     */     }
/*     */   }
/*     */
/*     */   private void clickOnLine(Actions action, String serieName, String serieColor) throws CCTException {
/* 187 */     WebElement circleElementToClick = getCircleElementInLineChart(serieColor);
/* 188 */     if (circleElementToClick != null) {
                WebUtils.scrollToElementAndCenter(this.webdriver, circleElementToClick);
                action.moveToElement(circleElementToClick).click().perform();
/*     */     } else {
/* 191 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 192 */         .as(this.widgetConfiguration.getId() + " clickOnLine ~ Cannot perform click on this widget: Chart is empty for the Kpi : " + serieName, new Object[0]))
/*     */
/* 194 */         .isEqualTo(true);
/*     */     }
/*     */   }
/*     */
/*     */   private void clickOnStep(Actions action, String serieName, String serieColor) throws CCTException {
/* 199 */     WebElement circleElementToClick = getCircleElementInStepChart(serieColor);
/* 200 */     if (circleElementToClick != null) {
/* 201 */       WebUtils.scrollToElementAndCenter(this.webdriver, circleElementToClick);
/* 202 */       action.moveToElement(circleElementToClick).click().perform();
/*     */     } else {
/* 204 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 205 */         .as(this.widgetConfiguration.getId() + " clickOnStep ~ Cannot perform click on this widget: Chart is empty for the Kpi : " + serieName, new Object[0]))
/*     */
/* 207 */         .isEqualTo(true);
/*     */     }
/*     */   }
/*     */
/*     */   private void disableAllLegends(String lookupId) throws CCTException {
/* 212 */     Actions action = new Actions(this.webdriver);
/* 213 */     for (int kpi = 0; kpi < this.clickableCells.size(); kpi++) {
/* 214 */       String serieName = this.clickableCells.get(kpi);
/* 215 */       if (!checkIfNoLegendIsAvaiableForSerie(serieName) &&
/* 216 */         !checkIfLegendAlreadyDisabled(lookupId, serieName)) {
/* 217 */         WebElement legendElement = getLegendElement(lookupId, serieName);
/* 218 */         WebUtils.scrollToElementAndCenter(this.webdriver, legendElement);
/* 219 */         WebUtils.waitForWidgetsToRender();
/* 220 */         WebElement legend = getLegendElement(lookupId, serieName);
/* 221 */         action.moveToElement(legend).click().perform();
/* 222 */         WebUtils.waitForWidgetsToRender();
//                    List<WebElement> legends = getLegendElements(lookupId);
//                    for(WebElement legend : legends){
//                        WebUtils.scrollToElementAndCenter(this.webdriver, legend);
//                        action.moveToElement(legend).click().perform();
//                        WebUtils.waitForWidgetsToRender();
//                    }
/*     */       }
/*     */     }
/*     */   }
/*     */
/*     */
/*     */   private WebElement getLegendElement(String lookupId, String serieName) {
/* 229 */     if (lookupId != null && lookupId.contains("legend")) {
/* 230 */       return this.webdriver.findElement(By.xpath(".//div[@id='" + lookupId + "']//*[local-name() = 'text' and ./text() = '" + serieName
/* 231 */             .trim() + "']"));
/*     */     }
/* 233 */     return this.webdriver.findElement(By.xpath(".//div[@id='" + lookupId + "']/*[name()='svg']/*[name()='g']/*[name()='g'][3]//*[local-name() = 'text' and ./text() = '" + serieName
/*     */
/* 235 */           .trim() + "']"));
/*     */   }
/*     */
/*     */
/*     */   private boolean checkIfNoLegendIsAvaiableForSerie(String serieName) {
/* 240 */     return serieName.contains("No Legend for serie color");
/*     */   }
/*     */
/*     */   public WebElement getSerieLegendElement(String legendId, String serieName) {
/* 244 */     if (!checkIfNoLegendIsAvaiableForSerie(serieName)) {
/* 245 */       return getLegendElement(legendId, serieName);
/*     */     }
/* 247 */     return null;
/*     */   }
/*     */
/*     */   private boolean checkIfLegendAlreadyDisabled(String lookupId, String serieName) {
/* 251 */     WebElement legend = getLegendElement(lookupId, serieName);
/* 252 */     boolean isLegendAlreadyDisabled = false;
/* 253 */     if (legend.getAttribute("fill") != null && !legend.getAttribute("fill").isEmpty()) {
/* 254 */       isLegendAlreadyDisabled = legend.getAttribute("fill").equals("#919191");
/*     */     } else {
/* 256 */       WebElement parentDiv = legend.findElement(By.xpath(".."));
/* 257 */       if (parentDiv != null &&
/* 258 */         !parentDiv.getCssValue("opacity").isEmpty() && parentDiv.getCssValue("opacity") != null) {
/* 259 */         isLegendAlreadyDisabled = parentDiv.getCssValue("opacity").toString().equalsIgnoreCase("0.5");
/*     */       }
/*     */     }
/* 262 */     return isLegendAlreadyDisabled;
/*     */   }
/*     */
/*     */   private WebElement getCircleElementInLineChart(String kpiNameColor) throws CCTException {
///* 266 */     List<WebElement> circleElements = this.widgetWrapper.findElements(By.cssSelector("#" + this.widgetConfiguration
///* 267 */           .getId() + " > svg > g > g:nth-child(3) > g:nth-child(5) > g > circle"));
                List<WebElement> circleElements = this.widgetWrapper.findElements(By.cssSelector("#" + this.widgetConfiguration
                .getId() + ">svg g[clip-path] circle"));
/* 268 */     for (WebElement circleElement : circleElements) {
///* 269 */       if (circleElement.getAttribute("stroke").toString().equalsIgnoreCase(kpiNameColor)) {
                if (circleElement.getAttribute("fill").toString().equalsIgnoreCase(kpiNameColor)) {
/* 270 */         return circleElement;
/*     */       }
/*     */     }
/* 273 */     return null;
/*     */   }
/*     */
/*     */
/*     */   private WebElement getCircleElementInStepChart(String serieColor) throws CCTException {
/* 278 */     List<WebElement> circleElements = (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $('#" +
/* 279 */         getId() + " svg g circle[fill=" + serieColor + "]');");
/* 280 */     if (circleElements.size() > 0) {
/* 281 */       WebUtils.executeJS(this.webdriver, "return $($('#" + getId() + " svg g circle[fill=" + serieColor + "]')[0]).css('display', 'block');");
/*     */
/* 283 */       return circleElements.get(0);
/*     */     }
/* 285 */     return null;
/*     */   }
/*     */
/*     */   private List<WebElement> findNumberOfSeriesInChart(String kpiNameColor) {
/*     */     try {
/* 290 */       WebElement element = this.widgetWrapper.findElement(By.tagName("linearGradient"));
/* 291 */       String elementId = element.getAttribute("id");
/* 292 */       return this.widgetWrapper.findElements(By.xpath("//*[@fill='" + kpiNameColor + "']/following-sibling::node()[@fill='url(#" + elementId + ")']"));
/*     */     }
/* 294 */     catch (Exception e) {
/* 295 */       return null;
/*     */     }
/*     */   }
/*     */
/*     */   private String getLegendId() {
/* 300 */     String lookupId = this.widgetConfiguration.getId();
/* 301 */     if (this.settings.isCustomLegendsRequired()) {
/* 302 */       lookupId = "legend-" + this.widgetConfiguration.getId();
/*     */     }
/* 304 */     return lookupId;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\CompositeChart.class
* Java compiler version: 8 (52.0)
* JD-Core Version:       1.1.3
*/