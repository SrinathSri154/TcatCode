/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.DatePickerSettings;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.NoSuchElementException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DatePicker
/*     */   extends CCTRenderedWidget
/*     */ {
/*     */   private DatePickerSettings settings;
/*     */   private String dateToBeClicked;
/*  27 */   private String datePickerType = null;
/*     */   
/*     */   DateFormat dateCustomFormat;
/*     */   
/*     */   public DatePicker(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  32 */     super(webdriver, widgetConfiguration);
/*  33 */     this.settings = (DatePickerSettings)widgetConfiguration.getSettings();
/*  34 */     this.datePickerType = this.settings.getdatepickertype();
/*  35 */     this.dateCustomFormat = getDateFormat();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*  40 */     Date today = new Date();
/*  41 */     WebElement elementClickable = elementToBeClicked();
/*     */     try {
/*  43 */       List<WebElement> clickableCells = new ArrayList<WebElement>();
/*  44 */       clickableCells.add(elementClickable);
/*  45 */       if (!this.datePickerType.equals("timePicker")) {
/*  46 */         this.dateToBeClicked = this.dateCustomFormat.format(today);
/*     */       } else {
/*  48 */         this.dateToBeClicked = "12:00 AM";
/*     */       } 
/*  50 */       return new ArrayList(clickableCells);
/*  51 */     } catch (NoSuchElementException e) {
/*  52 */       throw new CCTException("Exception in getListOfClickableElements: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getClickableElementForValue(String value) throws CCTException {
/*  58 */     WebElement elementClickable = elementToBeClicked();
/*     */     try {
/*  60 */       this.dateCustomFormat.setLenient(false);
/*  61 */       Date date = this.dateCustomFormat.parse(value);
/*  62 */       this.dateToBeClicked = this.dateCustomFormat.format(date);
/*  63 */     } catch (Exception e) {
/*  64 */       throw new CCTException("Invalid date format " + e.getMessage());
/*     */     } 
/*  66 */     return elementClickable;
/*     */   }
/*     */   
/*     */   private WebElement elementToBeClicked() throws CCTException {
/*  70 */     WebElement dateOrTimeElement = null;
/*     */     try {
/*  72 */       if (this.datePickerType.equals("timePicker")) {
/*  73 */         dateOrTimeElement = this.webdriver.findElement(
/*  74 */             By.xpath(".//*[@aria-controls='" + this.widgetConfiguration.getId() + "_timeview']/span"));
/*  75 */       } else if (this.datePickerType.equals("datePicker")) {
/*  76 */         dateOrTimeElement = this.webdriver.findElement(
/*  77 */             By.xpath(".//*[@aria-controls='" + this.widgetConfiguration.getId() + "_dateview']/span"));
/*  78 */       } else if (this.datePickerType.equals("dateTimePicker")) {
/*     */         
/*  80 */         dateOrTimeElement = this.webdriver.findElement(By.xpath(".//*[@aria-controls='" + this.widgetConfiguration.getId() + "_dateview']"));
/*     */       } 
/*  82 */     } catch (Exception e) {
/*  83 */       throw new CCTException("Invalid date format " + e.getMessage());
/*     */     } 
/*  85 */     return dateOrTimeElement;
/*     */   }
/*     */   
/*     */   private DateFormat getDateFormat() {
/*  89 */     DateFormat dateCustomFormat = null;
/*  90 */     if (this.datePickerType.equals("timePicker")) {
/*  91 */       dateCustomFormat = new SimpleDateFormat("h:mm a");
/*  92 */     } else if (this.datePickerType.equals("datePicker")) {
/*  93 */       dateCustomFormat = new SimpleDateFormat("M/d/yyyy");
/*  94 */     } else if (this.datePickerType.equals("dateTimePicker")) {
/*  95 */       dateCustomFormat = new SimpleDateFormat("M/d/yyyy h:mm a");
/*     */     } 
/*  97 */     return dateCustomFormat;
/*     */   }
/*     */ 
/*     */   
/*     */   public void click(Object element) throws CCTException {
/* 102 */     WebElement elementId = this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "']"));
/* 103 */     WebElement webElement = (WebElement)element;
/* 104 */     elementId.clear();
/* 105 */     elementId.sendKeys(new CharSequence[] { this.dateToBeClicked });
/*     */     try {
/* 107 */       WebUtils.scrollToElementAndCenter(this.webdriver, webElement);
/* 108 */       webElement.click();
/* 109 */       WebUtils.waitForWidgetsToRender();
/* 110 */       if (this.datePickerType.equals("timePicker")) {
/* 111 */         WebElement base = this.webdriver.findElement(By.xpath(".//*[@id='" + this.widgetConfiguration.getId() + "_timeview']/li[contains(text(),'" + this.dateToBeClicked + "')]"));
/*     */         
/* 113 */         base.click();
/*     */       } 
/* 115 */     } catch (CCTException e) {
/* 116 */       throw new CCTException("Exception in Click element: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\DatePicker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */