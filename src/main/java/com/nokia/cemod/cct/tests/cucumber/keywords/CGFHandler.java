/*     */ package com.nokia.cemod.cct.tests.cucumber.keywords;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.pages.CCTPortalBasePage;
/*     */ import com.nokia.cemod.cct.tests.base.CCTBaseTest;
/*     */ import cucumber.api.java.en.Then;
/*     */ import cucumber.api.java.en.When;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CGFHandler
/*     */   extends CCTBaseTest
/*     */ {
/*     */   @When("^Clear default CGF Filters$")
/*     */   public void clearCGFFilters() throws Exception {
/*  16 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  17 */     cctPortalBasePage.clearDefaultCGFFilterSelection(cctPortalBasePage);
/*     */   }
/*     */   
/*     */   @When("^Select time filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyTimeFilterForUsecase(String timeFilter, String value) throws Throwable {
            if(!(timeFilter.isEmpty() && value.isEmpty())){
    /*  22 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  23 */     addCGFFiltersForGivenFeature(timeFilter, value);
    /*  24 */     cctPortalBasePage.applyTimeFilter(timeFilter, value);
                }
/*     */   }
/*     */   
/*     */   @When("^Verify CGF filter \"([^\"]*)\"$")
/*     */   public void verifyTimeFilters(String cgfFilter) throws CCTException {
            if(!cgfFilter.isEmpty()) {
    /*  29 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  30 */     cctPortalBasePage.verifyCGFFilters(cgfFilter);
                }
/*     */   }
/*     */   
/*     */   @When("^Select time filter as \"([^\"]*)\" with default value for \"([^\"]*)\"$")
/*     */   public void applyTimeFilterWithDefaultValueForUsecase(String timeFilter, int value) throws Throwable {
                if(!timeFilter.isEmpty()) {
    /*  35 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  36 */     cctPortalBasePage.applyTimeFilterDefaultValue(timeFilter, value);
                }
/*     */   }
/*     */   
/*     */   @When("^Select technology filter as \"([^\"]*)\"$")
/*     */   public void applyTechnologyFilterForUsecase(String technologyFilter) throws Throwable {
              if(!technologyFilter.isEmpty()) {
                  CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage) getCCTPage();
                  addCGFFiltersForGivenFeature("Technologies", technologyFilter);
                  cctPortalBasePage.applyTechnologyFilter(technologyFilter);
              }
/*     */   }
/*     */   
/*     */   @When("^Select location filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyLocationFilterForUsecase(String location, String filters) throws Throwable {
                if(!(location.isEmpty() && filters.isEmpty())){
                    CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
                    String[] loc = location.trim().split("\\s*;\\s*");
                    String[] values = filters.trim().split("\\s*;\\s*");
                    for (int i=0; i<loc.length; i++)
                    {
        /*  49 */     addCGFFiltersForGivenFeature(loc[i], values[i]);
        /*  50 */     cctPortalBasePage.applyLocationFilter(loc[i], values[i]);
                    }
                }
/*     */   }
/*     */   
/*     */   @When("^Select APNs filter as \"([^\"]*)\"$")
/*     */   public void applyAPNsFilterForUsecase(String apns) throws Throwable {
                if(!apns.isEmpty()) {
    /*  55 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  56 */     addCGFFiltersForGivenFeature("APNs", apns);
    /*  57 */     cctPortalBasePage.applyAPNs(apns);
                }
/*     */   }
/*     */   
/*     */   @When("^Select Subscribers filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applySubscribersFilterForUsecase(String subscribersFilter, String filters) throws Throwable {
                if(!(subscribersFilter.isEmpty() && filters.isEmpty())) {
    /*  62 */       CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
                    String[] subFilter = subscribersFilter.trim().split("\\s*;\\s*");
                    String[] values = filters.trim().split("\\s*;\\s*");
                    for (int i=0; i<subFilter.length; i++)
                    {
        /*  63 */     addCGFFiltersForGivenFeature(subFilter[i], values[i]);
        /*  64 */     cctPortalBasePage.applySubscribers(subFilter[i], values[i]);
                    }
                }
/*     */   }
/*     */   
/*     */   @When("^Select Devices filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyDevicesFilterForUsecase(String devicesFilter, String filters) throws Throwable {
                if(!(devicesFilter.isEmpty() && filters.isEmpty())) {
                    CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
                    String[] devices = devicesFilter.trim().split("\\s*;\\s*");
                    String[] values = filters.trim().split("\\s*;\\s*");
                    for (int i=0; i<devices.length; i++){
        /*  70 */     addCGFFiltersForGivenFeature(devices[i], values[i]);
        /*  71 */     cctPortalBasePage.applyDevices(devices[i], values[i]);
                    }
                }
/*     */   }
/*     */   
/*     */   @When("^Select Roaming category filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyRoamingCategoryForUsecase(String roamingCategoryFilter, String filters) throws Throwable {
                if(!(roamingCategoryFilter.isEmpty() && filters.isEmpty())) {
    /*  76 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  77 */     addCGFFiltersForGivenFeature(roamingCategoryFilter, filters);
    /*  78 */     cctPortalBasePage.applyRoamingCategory(roamingCategoryFilter, filters);
                }
/*     */   }
/*     */   
/*     */   @When("^Select networktopology filter as \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void applyNetworkTopologyFilterForUsecase(String aggregationlevel, String filters) throws Throwable {
                if(!(aggregationlevel.isEmpty() && filters.isEmpty())) {
    /*  83 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  84 */     addCGFFiltersForGivenFeature(aggregationlevel, filters);
    /*  85 */     cctPortalBasePage.applyNetworkTopologies(aggregationlevel, filters);
                }
/*     */   }
/*     */   
/*     */   @When("^Select preferences filter with \"([^\"]*)\" as true$")
/*     */   public void applyPreferencesForUsecase(String unmappedDataFilter) throws Throwable {
                if(!unmappedDataFilter.isEmpty()) {
    /*  90 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
    /*  91 */     addCGFFiltersForGivenFeature("Preferences", unmappedDataFilter);
    /*  92 */     cctPortalBasePage.applyPreferences(unmappedDataFilter);
                }
/*     */   }
/*     */   
/*     */   @Then("^Apply filters$")
/*     */   public void applyFilters() throws Throwable {
/*  97 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/*  98 */     cctPortalBasePage.selectApplyButton(getCGFFiltersForGivenFeature(), getCEIFiltersForGivenFeature());
/*  99 */     clearCGFFiltersForGivenFeature();
/* 100 */     clearCEIFiltersForGivenFeature();
/*     */   }
/*     */   
/*     */   @Then("^Reset filters$")
/*     */   public void resetFilters() throws Throwable {
/* 105 */     CCTPortalBasePage cctPortalBasePage = (CCTPortalBasePage)getCCTPage();
/* 106 */     clearCGFFiltersForGivenFeature();
/* 107 */     cctPortalBasePage.resetCGFFilters();
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\cucumber\keywords\CGFHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */