/*     */ package com.nokia.cemod.cct.tests.cucumber.keywords;
/*     */ 
/*     */ import com.nokia.cemod.cct.pages.CCTPortalBasePage;
/*     */ import com.nokia.cemod.cct.tests.base.CCTBaseTest;
/*     */ import com.nokia.cemod.cct.utils.CEISelectFiltersUtil;
/*     */ import cucumber.api.java.en.When;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CEIHandler
/*     */   extends CCTBaseTest
/*     */ {
/*     */   @When("^Select CEI filter Customer lifetime as \"([^\"]*)\" value \"([^\"]*)\" as \"([^\"]*)\"$")
/*     */   public void applyceiFilterForUsecase(String customerFilter, String lifetimeFilter, String lifetimeValue) throws Throwable {
/*  16 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  17 */     addCEIFiltersForGivenFeature(customerFilter, lifetimeFilter + "," + lifetimeValue);
/*  18 */     cctPortalBasePage.applyCustomerFilter(customerFilter, lifetimeFilter, lifetimeValue);
/*     */   }
/*     */   
/*     */   @When("^Select CEI filter Customer age as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyAgeFilterForUsecase(String ageFilter, String ageFilterValue) throws Throwable {
/*  23 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  24 */     addCEIFiltersForGivenFeature(ageFilter, ageFilterValue);
/*  25 */     cctPortalBasePage.applyAgeFilter(ageFilter, ageFilterValue);
/*     */   }
/*     */   
/*     */   @When("^Select CEI filter Customer gender as \"([^\"]*)\" with value \"([^\"]*)\"$")
/*     */   public void applyGenderFilterForUsecase(String genderFilter, String genderFilterValue) throws Throwable {
/*  30 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  31 */     if (!genderFilterValue.equals("ALL")) {
/*  32 */       addCEIFiltersForGivenFeature(genderFilter, genderFilterValue);
/*     */     }
/*  34 */     cctPortalBasePage.applyGenderFilter(genderFilter, genderFilterValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Subscription as \"([^\"]*)\" value \"([^\"]*)\" as \"([^\"]*)\"$")
/*     */   public void applySubscriptionFilterForUsecase(String subscriptionFilter, String enddatefilter, String enddatevalue) throws Throwable {
/*  40 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  41 */     addCEIFiltersForGivenFeature(subscriptionFilter, CEISelectFiltersUtil.convertDateFormat(enddatevalue));
/*  42 */     cctPortalBasePage.applySubscriptionFilter(subscriptionFilter, enddatefilter, enddatevalue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Usage Data \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyDataFilterForUsecase(String usageFilter, String datafilter, String datafiltervalue) throws Throwable {
/*  48 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  49 */     addCEIFiltersForGivenFeature(usageFilter, datafilter + "," + datafiltervalue);
/*  50 */     cctPortalBasePage.applyDataFilter(usageFilter, datafilter, datafiltervalue);
/*     */   }
/*     */   
/*     */   @When("^Select CEI filter Usage SMS \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applySmsFilterForUsecase(String smsFilter, String smssentorreceived, String smsValue) throws Throwable {
/*  55 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  56 */     addCEIFiltersForGivenFeature(smsFilter, smssentorreceived + "," + smsValue);
/*  57 */     cctPortalBasePage.applySmsFilter(smsFilter, smssentorreceived, smsValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Usage Voice \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyVoiceFilterForUsecase(String voiceFilter, String voiceCalls, String voiceCallsValue) throws Throwable {
/*  63 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  64 */     addCEIFiltersForGivenFeature(voiceFilter, voiceCalls + "," + voiceCallsValue);
/*  65 */     cctPortalBasePage.applyVoiceFilter(voiceFilter, voiceCalls, voiceCallsValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Usage Volte \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyVolteFilterForUsecase(String volteFilter, String volteCalls, String volteCallsValue) throws Throwable {
/*  71 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  72 */     addCEIFiltersForGivenFeature(volteFilter, volteCalls + "," + volteCallsValue);
/*  73 */     cctPortalBasePage.applyVolteFilter(volteFilter, volteCalls, volteCallsValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Quality Data \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyQualityDataFilterForUsecase(String qualityFilter, String qualityDataFilter, String qualityDataValue) throws Throwable {
/*  79 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  80 */     addCEIFiltersForGivenFeature(qualityFilter, qualityDataFilter + "," + qualityDataValue);
/*  81 */     cctPortalBasePage.applyQualityDataFilter(qualityFilter, qualityDataFilter, qualityDataValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Quality Radio \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyQualityRadioFilterForUsecase(String qualityFilter, String qualityRadioFilter, String qualityRadioValue) throws Throwable {
/*  87 */     CCTPortalBasePage cctPortalPage = (CCTPortalBasePage)getCCTPage();
/*  88 */     addCEIFiltersForGivenFeature(qualityFilter, qualityRadioFilter + "," + qualityRadioValue);
/*  89 */     cctPortalPage.applyQualityRadioFilter(qualityFilter, qualityRadioFilter, qualityRadioValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Quality SMS \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyQualitySmsFilterForUsecase(String qualityFilter, String qualitySmsFilter, String qualitySmsValue) throws Throwable {
/*  95 */     CCTPortalBasePage cctPortalPage = (CCTPortalBasePage)getCCTPage();
/*  96 */     addCEIFiltersForGivenFeature(qualityFilter, qualitySmsFilter + "," + qualitySmsValue);
/*  97 */     cctPortalPage.applyQualitySmsFilter(qualityFilter, qualitySmsFilter, qualitySmsValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Quality Voice \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyQualityVoiceFilterForUsecase(String qualityFilter, String qualityVoiceFilter, String qualityVoiceValue) throws Throwable {
/* 103 */     CCTPortalBasePage cctPortalPage = (CCTPortalBasePage)getCCTPage();
/* 104 */     addCEIFiltersForGivenFeature(qualityFilter, qualityVoiceFilter + "," + qualityVoiceValue);
/* 105 */     cctPortalPage.applyQualityVoiceFilter(qualityFilter, qualityVoiceFilter, qualityVoiceValue);
/*     */   }
/*     */ 
/*     */   
/*     */   @When("^Select CEI filter Quality Volte \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyQualityVolteFilterForUsecase(String qualityFilter, String qualityVolteFilter, String qualityVolteValue) throws Throwable {
/* 111 */     CCTPortalBasePage cctPortalPage = (CCTPortalBasePage)getCCTPage();
/* 112 */     addCEIFiltersForGivenFeature(qualityFilter, qualityVolteFilter + "," + qualityVolteValue);
/* 113 */     cctPortalPage.applyQualityVolteFilter(qualityFilter, qualityVolteFilter, qualityVolteValue);
/*     */   }
/*     */   
/*     */   @When("^Select CEI filter CE Index \"([^\"]*)\" as \"([^\"]*)\" value as \"([^\"]*)\"$")
/*     */   public void applyIndexFilterForUsecase(String ceIndex, String ceIndexFilter, String ceIndexValue) throws Throwable {
/* 118 */     CCTPortalBasePage cctPortalPage = (CCTPortalBasePage)getCCTPage();
/* 119 */     addCEIFiltersForGivenFeature(ceIndex, ceIndexFilter + "," + ceIndexValue);
/* 120 */     cctPortalPage.applyIndexFilter(ceIndex, ceIndexFilter, ceIndexValue);
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\cucumber\keywords\CEIHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */