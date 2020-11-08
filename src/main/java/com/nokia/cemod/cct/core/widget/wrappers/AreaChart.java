/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.interactions.Actions;
/*     */ 
/*     */ 
/*     */ public class AreaChart
/*     */   extends CCTRenderedWidget
/*     */ {
/*  22 */   List<String> clickableCells = new ArrayList<String>();
/*     */   
/*     */   public AreaChart(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  25 */     super(webdriver, widgetConfiguration);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*     */     try {
/*  31 */       List<WebElement> kpinames = getKPINames();
/*  32 */       for (WebElement eachkpi : kpinames) {
/*  33 */         this.clickableCells.add(eachkpi.getText());
/*     */       }
/*  35 */     } catch (NoSuchElementException e) {
/*  36 */       throw new CCTException("AreaChart: Exception occurred " + e.getMessage());
/*     */     } 
/*  38 */     return new ArrayList(this.clickableCells);
/*     */   }
/*     */   
/*     */   private List<WebElement> getKPINames() {
/*  42 */     return this.widgetWrapper.findElements(
/*  43 */         By.cssSelector("#" + this.widgetConfiguration.getId() + " > svg > g > g:nth-child(5) > g > g > g > text"));
/*     */   }
/*     */   
/*     */   private String getKpiColor(String kpiname) {
/*  47 */     List<String> nameofkpis = new ArrayList<String>();
/*  48 */     HashMap<String, String> map = new HashMap<String, String>();
/*  49 */     List<WebElement> getkpilist = this.widgetWrapper.findElements(
/*  50 */         By.cssSelector("#" + this.widgetConfiguration.getId() + " > svg > g > g:nth-child(5) > g > g > g > text"));
/*  51 */     List<WebElement> getkpicolor = this.widgetWrapper.findElements(By.cssSelector("#" + this.widgetConfiguration
/*  52 */           .getId() + " > svg > g > g:nth-child(5) > g > g > path:nth-child(1)"));
/*  53 */     int kpiIndex = 0;
/*  54 */     for (WebElement color : getkpicolor) {
/*  55 */       for (WebElement kpinamelist : getkpilist) {
/*  56 */         nameofkpis.add(kpinamelist.getText());
/*  57 */         map.put(nameofkpis.get(kpiIndex), color.getAttribute("fill"));
/*     */       } 
/*  59 */       kpiIndex++;
/*     */     } 
/*  61 */     return map.get(kpiname);
/*     */   }
/*     */ 
/*     */   
/*     */   public void click(Object element) throws CCTException {
/*  66 */     Actions action = new Actions(this.webdriver);
/*  67 */     String serieName = (String)element;
/*  68 */     disableAllLegends();
/*  69 */     WebElement serieElement = getSerieLegendElement(serieName);
/*  70 */     if (serieElement != null && !serieName.equalsIgnoreCase("")) {
/*     */       
/*  72 */       action.moveToElement(serieElement).click().perform();
/*  73 */       WebUtils.waitForWidgetsToRender();
/*     */     } 
/*  75 */     String kpiNameColor = getKpiColor(serieName);
/*  76 */     WebElement circleElementToClick = getCircleElementInAreaChart(kpiNameColor);
/*  77 */     if (circleElementToClick != null) {
/*  78 */       WebUtils.scrollToElementAndCenter(this.webdriver, circleElementToClick);
/*  79 */       action.moveToElement(circleElementToClick).click().perform();
/*     */     } else {
/*  81 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/*  82 */         .as(this.widgetConfiguration.getId() + " ~ Cannot perform click on this widget: Chart is empty for the Kpi : " + serieName, new Object[0]))
/*     */         
/*  84 */         .isEqualTo(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void disableAllLegends() throws CCTException {
/*  89 */     Actions action = new Actions(this.webdriver);
/*  90 */     for (int kpi = 0; kpi < this.clickableCells.size(); kpi++) {
/*  91 */       String text = this.clickableCells.get(kpi);
/*  92 */       WebElement legend = this.webdriver.findElement(By.xpath(".//div[@id='" + this.widgetConfiguration.getId() + "']//*[local-name() = 'text' and ./text() = '" + text + "']"));
/*     */       
/*  94 */       if (!checkIfLegendAlreadyDisabled(legend)) {
/*  95 */         WebUtils.scrollToElementAndCenter(this.webdriver, legend);
/*  96 */         WebUtils.waitForWidgetsToRender();
/*  97 */         action.moveToElement(legend).click().perform();
/*  98 */         WebUtils.waitForWidgetsToRender();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean checkIfLegendAlreadyDisabled(WebElement legend) {
/* 104 */     boolean isLegendAlreadyDisabled = false;
/* 105 */     if (legend.getAttribute("fill") != null && !legend.getAttribute("fill").isEmpty()) {
/* 106 */       isLegendAlreadyDisabled = legend.getAttribute("fill").equals("#919191");
/*     */     } else {
/* 108 */       WebElement parentDiv = legend.findElement(By.xpath(".."));
/* 109 */       if (parentDiv != null && 
/* 110 */         !parentDiv.getCssValue("opacity").isEmpty() && parentDiv.getCssValue("opacity") != null) {
/* 111 */         isLegendAlreadyDisabled = parentDiv.getCssValue("opacity").toString().equalsIgnoreCase("0.5");
/*     */       }
/*     */     } 
/* 114 */     return isLegendAlreadyDisabled;
/*     */   }
/*     */   
/*     */   public WebElement getSerieLegendElement(String serieName) {
/* 118 */     List<WebElement> elementsList = this.webdriver.findElements(By.xpath(".//div[@id='" + this.widgetConfiguration.getId() + "']//*[local-name() = 'text' and ./text() = '" + serieName
/* 119 */           .trim() + "']"));
/* 120 */     if (elementsList.size() > 0) {
/* 121 */       return elementsList.get(elementsList.size() - 1);
/*     */     }
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private WebElement getCircleElementInAreaChart(String kpiNameColor) throws CCTException {
/* 128 */     List<WebElement> circleElements = (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/* 129 */         .getId() + " > svg > g > g:nth-child(3) > g:nth-child(4) >circle[fill=" + kpiNameColor + "]');");
/*     */     
/* 131 */     if (circleElements.size() == 1) {
/* 132 */       WebUtils.executeJS(this.webdriver, "return $($('#" + this.widgetConfiguration
/* 133 */           .getId() + " > svg > g > g:nth-child(3) > g:nth-child(4) > circle[fill=" + kpiNameColor + "]')[0]).css('display', 'block');");
/*     */ 
/*     */       
/* 136 */       return circleElements.get(0);
/* 137 */     }  if (circleElements.size() > 1) {
/* 138 */       WebUtils.executeJS(this.webdriver, "return $($('#" + this.widgetConfiguration
/* 139 */           .getId() + " > svg > g > g:nth-child(3) > g:nth-child(4) > circle[fill=" + kpiNameColor + "]')[1]).css('display', 'block');");
/*     */ 
/*     */       
/* 142 */       return circleElements.get(1);
/*     */     } 
/*     */     
/* 145 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\AreaChart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */