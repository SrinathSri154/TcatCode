/*    */ package com.nokia.cemod.cct.tests.base;
/*    */ 
/*    */ import com.nokia.cemod.cct.logger.queryResponse.QueryResponseTimeHeaders;
/*    */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeHeaders;
/*    */ import com.nokia.cemod.cct.pages.CCTBasePage;
/*    */ import com.nokia.cemod.cct.pages.CCTDevelopmentPage;
/*    */ import com.nokia.cemod.cct.pages.CCTPortalPage;
/*    */ import com.nokia.cemod.cct.pages.CEMNovaPage;
/*    */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*    */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CCTBaseTest
/*    */ {
/* 18 */   private static volatile CCTBasePage cctBasePage = null;
/*    */   
/* 20 */   private static volatile SharedDriver sharedDriverInstance = new SharedDriver();
/*    */   
/* 22 */   private static volatile Map<String, String> cgfFiltersForGivenFeature = new HashMap<String, String>();
/*    */   
/* 24 */   private static volatile Map<String, String> ceiFiltersForGivenFeature = new HashMap<String, String>();
/*    */   static {
/* 26 */     QueryResponseTimeHeaders.log();
/* 27 */     RenderingTimeHeaders.log();
/*    */   }
/*    */   
/*    */   protected CCTBasePage getCCTPage() {
/* 31 */     if (cctBasePage == null) {
/* 32 */       String environment = TestConfiguration.getProperty("cct.env.type");
/* 33 */       switch (environment) {
/*    */         case "portal":
/* 35 */           cctBasePage = (CCTBasePage)new CCTPortalPage(sharedDriverInstance);
/*    */           break;
/*    */         case "development":
/* 38 */           cctBasePage = (CCTBasePage)new CCTDevelopmentPage(sharedDriverInstance);
/*    */           break;
/*    */         case "CEMNova":
/* 41 */           cctBasePage = (CCTBasePage)new CEMNovaPage(sharedDriverInstance); break;
/*    */       } 
/*    */     } 
/* 44 */     return cctBasePage;
/*    */   }
/*    */   
/*    */   protected void addCGFFiltersForGivenFeature(String key, String value) {
/* 48 */     cgfFiltersForGivenFeature.put(key, value);
/*    */   }
/*    */   
/*    */   protected Map<String, String> getCGFFiltersForGivenFeature() {
/* 52 */     return cgfFiltersForGivenFeature;
/*    */   }
/*    */   
/*    */   protected void clearCGFFiltersForGivenFeature() {
/* 56 */     cgfFiltersForGivenFeature.clear();
/*    */   }
/*    */   
/*    */   protected void addCEIFiltersForGivenFeature(String key, String value) {
/* 60 */     ceiFiltersForGivenFeature.put(key, value);
/*    */   }
/*    */   
/*    */   protected Map<String, String> getCEIFiltersForGivenFeature() {
/* 64 */     return ceiFiltersForGivenFeature;
/*    */   }
/*    */   
/*    */   protected void clearCEIFiltersForGivenFeature() {
/* 68 */     ceiFiltersForGivenFeature.clear();
/*    */   }
/*    */   
/*    */   protected void clearCCTPageInstance() {
/* 72 */     cctBasePage = null;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\base\CCTBaseTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */