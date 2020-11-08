/*     */ package com.nokia.cemod.cct.logger.renderingTime;
/*     */ 
/*     */ import com.nokia.cemod.cct.cgf.CGFApiUtil;
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*     */ import java.util.ArrayList;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.StringJoiner;
/*     */ import org.openqa.selenium.StaleElementReferenceException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ public class RenderingTimeWidgetClick
/*     */   extends AbstractRenderingTime
/*     */ {
/*  20 */   private String useCaseName = "";
/*     */   
/*     */   private long startTime;
/*     */   
/*     */   private long endTime;
/*     */   
/*     */   private String widgetId;
/*     */   
/*     */   private String widgetTitle;
/*     */ 
/*     */   
/*     */   public RenderingTimeWidgetClick(String useCaseName, WidgetConfiguration widgetConfiguration, Object clickElement) throws CCTException {
/*  32 */     this.useCaseName = useCaseName;
/*  33 */     this.widgetId = widgetConfiguration.getId();
/*  34 */     this.widgetTitle = widgetConfiguration.getTitle();
/*  35 */     if (this.widgetTitle == null) {
/*  36 */       if (clickElement instanceof WebElement) {
/*     */         try {
/*  38 */           this.widgetTitle = ((WebElement)clickElement).getText();
/*  39 */         } catch (StaleElementReferenceException staleElementReferenceException) {}
/*     */       
/*     */       }
/*  42 */       else if (clickElement instanceof String) {
/*  43 */         this.widgetTitle = (String)clickElement;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void start() {
/*  49 */     this.startTime = (new GregorianCalendar()).getTimeInMillis();
/*     */   }
/*     */   
/*     */   public void end() {
/*  53 */     this.endTime = (new GregorianCalendar()).getTimeInMillis();
/*  54 */     log();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getUseCaseName() {
/*  59 */     return this.useCaseName;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDescription() {
/*  64 */     String appliedFilters = getAppliedFilters();
/*  65 */     return "Clicked widget id: '" + this.widgetId + "' having title: '" + this.widgetTitle + appliedFilters;
/*     */   }
/*     */ 
/*     */   
/*     */   protected long getStartTime() {
/*  70 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   
/*     */   protected long getEndTime() {
/*  75 */     return this.endTime;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getWidgetId() {
/*  80 */     return this.widgetId;
/*     */   }
/*     */ 
/*     */   
/*     */   private String getAppliedFilters() {
/*  85 */     StringBuilder filterApplied = new StringBuilder();
/*     */     try {
/*  87 */       Map<String, Object> appliedFilters = CGFApiUtil.getAppliedFilters((WebDriver)new SharedDriver());
/*  88 */       if (appliedFilters.get("time") != null) {
/*  89 */         Map<String, Object> timeFilters = (Map<String, Object>)appliedFilters.get("time");
/*  90 */         filterApplied.append(" Time filter : " + timeFilters.get("labelWithDate"));
/*     */       } 
/*  92 */       if (appliedFilters.get("technology") != null) {
/*  93 */         filterApplied.append(" Technology filter : " + appliedFilters.get("technology").toString());
/*     */       }
/*  95 */       if (appliedFilters.get("technologies") != null) {
/*  96 */         filterApplied.append(" Technology filter : " + appliedFilters.get("technologies").toString());
/*     */       }
/*  98 */       if (appliedFilters.get("region") != null) {
/*  99 */         filterApplied.append(" Region filter : " + locationFilters(appliedFilters, "region"));
/*     */       }
/* 101 */       if (appliedFilters.get("city") != null) {
/* 102 */         filterApplied.append(" City filter : " + locationFilters(appliedFilters, "city"));
/*     */       }
/* 104 */       if (appliedFilters.get("area") != null) {
/* 105 */         filterApplied.append(" Area filter : " + locationFilters(appliedFilters, "area"));
/*     */       }
/* 107 */       return " for " + filterApplied;
/* 108 */     } catch (Exception e) {
/*     */ 
/*     */ 
/*     */       
/* 112 */       return "";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private StringJoiner locationFilters(Map<String, Object> appliedFilters, String loc) {
/* 118 */     StringJoiner location = new StringJoiner(",");
/*     */     
/* 120 */     ArrayList<HashMap<String, String>> locationFilters = (ArrayList<HashMap<String, String>>)appliedFilters.get(loc);
/* 121 */     for (int i = 0; i < locationFilters.size(); i++) {
/*     */ 
/*     */       
/* 124 */       HashMap<String, String> locMap = (locationFilters.get(i) instanceof HashMap) ? locationFilters.get(i) : new HashMap<String, String>(locationFilters.get(i));
/* 125 */       location.add(locMap.get("displaylabel"));
/*     */     } 
/* 127 */     return location;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\renderingTime\RenderingTimeWidgetClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */