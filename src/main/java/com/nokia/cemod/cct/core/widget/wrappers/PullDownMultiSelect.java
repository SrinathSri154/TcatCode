/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.PullDownMultiSelectSettings;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PullDownMultiSelect
/*     */   extends CCTRenderedWidget
/*     */ {
/*     */   private PullDownMultiSelectSettings settings;
/*     */   
/*     */   public PullDownMultiSelect(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  23 */     super(webdriver, widgetConfiguration);
/*  24 */     this.settings = (PullDownMultiSelectSettings)widgetConfiguration.getSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isWidgetRendered() throws CCTException {
/*  29 */     String isPullDownRenderedScript = "if ($('#" + this.widgetConfiguration.getId() + "').data('kendoMultiSelect')) {return true; } else {return false; }";
/*     */     
/*  31 */     boolean isWidgetRendered = ((Boolean)WebUtils.executeJS(this.webdriver, isPullDownRenderedScript)).booleanValue();
/*  32 */     validateQueryResponseReceived();
/*  33 */     validateNoDataScenario();
/*  34 */     return isWidgetRendered;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*  39 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/*     */     try {
/*  41 */       String getselectType = this.settings.getSelectType();
/*  42 */       if (getselectType.equals("Multiple with Select/Deselect All")) {
/*  43 */         if (this.settings.getIsSelectAllIntegratedToComponent().booleanValue()) {
/*  44 */           clickableCells.add(this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[1]")));
/*     */         } else {
/*  46 */           WebElement SelectAll = WebUtils.waitAndFindElementBy(this.webdriver, By.xpath(".//*[@id='select-" + this.widgetConfiguration.getId() + "']"));
/*  47 */           clickableCells.add(SelectAll);
/*     */         } 
/*     */       } else {
/*  50 */         clickableCells.add(this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[1]")));
/*     */       } 
/*     */       
/*  53 */       return new ArrayList(clickableCells);
/*  54 */     } catch (NoSuchElementException e) {
/*  55 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getAllPossibleClickableElements() throws CCTException {
/*     */     try {
/*  62 */       List<WebElement> clickableCells = new ArrayList<WebElement>();
/*  63 */       clickableCells.addAll(this.webdriver
/*  64 */           .findElements(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li")));
/*  65 */       return new ArrayList(clickableCells);
/*  66 */     } catch (NoSuchElementException e) {
/*  67 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void click(Object element) throws CCTException {
/*  73 */     WebElement base, webElement = (WebElement)element;
/*  74 */     String title = this.settings.getTitle();
/*     */ 
/*     */     
/*  77 */     if (title == null || title.equals("")) {
/*  78 */       base = (WebElement)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/*  79 */           .getId() + "').parent()[0]");
/*     */     } else {
/*  81 */       base = this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "-label'][text()='" + title + "']/following-sibling::div"));
/*     */     } 
/*     */     
/*  84 */     WebUtils.scrollToElementAndCenter(this.webdriver, base);
/*  85 */     base.click();
/*  86 */     WebUtils.waitForWidgetsToRender();
/*  87 */     WebElement firstelement = this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[1]"));
/*  88 */     firstelement.click();
/*  89 */     WebUtils.waitForWidgetsToRender();
/*  90 */     webElement.click();
/*  91 */     base.click();
/*     */   }
/*     */   
/*     */   private void validateNoDataScenario() throws CCTException {
/*  95 */     long pullDownDataLength = ((Long)WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration
/*  96 */         .getId() + "').data('kendoMultiSelect').dataSource.data().length")).longValue();
/*  97 */     if (pullDownDataLength == 0L) {
/*     */ 
/*     */ 
/*     */       
/* 101 */       String errorMessage = WebUtils.executeJS(this.webdriver, "return $('#" + this.widgetConfiguration.getId() + "').data('kendoMultiSelect').noData.text()").toString();
/* 102 */       throw new CCTException(errorMessage);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClickableElementForValue(String value) throws CCTException {
/* 108 */     List<WebElement> elements = this.webdriver.findElements(
/* 109 */         By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_listbox']/li[text()='" + value + "']"));
/* 110 */     if (elements.size() == 1)
/* 111 */       return elements.get(0); 
/* 112 */     if (elements.size() > 1) {
/* 113 */       throw new CCTException("Mutliple WebElements found with same value: " + value);
/*     */     }
/* 115 */     throw new CCTException("WebElement Not found");
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\PullDownMultiSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */