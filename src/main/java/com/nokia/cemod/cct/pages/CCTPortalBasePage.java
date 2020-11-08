/*     */ package com.nokia.cemod.cct.pages;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeUseCaseLoad;
/*     */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.CEISelectFiltersUtil;
/*     */ import com.nokia.cemod.cct.utils.CGFSelectFiltersUtil;
/*     */ import com.nokia.cemod.cct.utils.PortalUtils;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import junit.framework.Assert;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.Keys;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.interactions.Actions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CCTPortalBasePage
/*     */   extends CCTBasePage
/*     */ {
/*  31 */   private static final Logger logger = Logger.getLogger(CCTPortalBasePage.class);
/*     */   
/*     */   public CCTPortalBasePage(SharedDriver driver) {
/*  34 */     super(driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void loginToPortal(String paramString1, String paramString2) throws CCTException;
/*     */   
/*     */   public abstract void verifyForgotPasswordPage() throws CCTException;
/*     */   
/*     */   public abstract void navigateToLoginPage() throws CCTException;
/*     */   
/*     */   public abstract void verifyLoginPageContent() throws CCTException;
/*     */   
/*     */   public abstract void verifyPlaceholderInUserAuthentication(String paramString) throws CCTException;
/*     */   
/*     */   public abstract void logout() throws CCTException;
/*     */   
/*     */   public abstract void navigateToNewUsecase() throws CCTException;
/*     */   
/*     */   public abstract void createNewUsecase(String paramString) throws CCTException;
/*     */   
/*     */   public abstract void editUsecase(String paramString) throws CCTException;
/*     */   
/*     */   public abstract void deleteUsecase(String paramString) throws CCTException;
/*     */   
/*     */   public abstract void verifyUsecaseNotPresentInDashboard(String paramString) throws CCTException;
/*     */   
/*     */   public void navigateToUsecaseGroup(String usecaseGroup) throws CCTException {
/*  61 */     if (this.driver.getPageSource().contains("Customer Care Insight")) {
/*  62 */       WebUtils.waitAndClickForElementBy(this.driver, By.linkText("Insights Library"));
/*     */     }
/*  64 */     WebUtils.waitAndClickForElementBy(this.driver, 
/*  65 */         By.xpath(".//p[contains(text(), '" + usecaseGroup + "') and @class='content-app-name']"));
/*     */   }
/*     */   
/*     */   public void navigateToUsecasePage(String useCaseName) throws CCTException {
/*     */     try {
/*  70 */       PortalUtils.searchUsecase(useCaseName, this.driver);
/*  71 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.partialLinkText(useCaseName));
/*  72 */       Actions action = new Actions(this.driver);
/*  73 */       action.moveToElement(element).click().perform();
/*  74 */     } catch (CCTException e) {
/*  75 */       throw new CCTException("Failed to find and click on usecase link: " + useCaseName);
/*     */     } 
/*  77 */     loadUsecasePageWithCGF(useCaseName);
/*     */   }
/*     */   
/*     */   public void loadUsecasePageWithCGF(String usecaseName) throws CCTException {
/*  81 */     RenderingTimeUseCaseLoad logRenderTime = new RenderingTimeUseCaseLoad(usecaseName);
/*  82 */     logRenderTime.start();
/*  83 */     loadAndCheckForVisibilityOfCGFFilters(this.driver);
/*     */     
/*     */     try {
/*  86 */       WebUtils.waitForUseCaseToLoad(this.driver);
/*  87 */     } catch (CCTException e) {
/*  88 */       throw new CCTException("Error in loading use case: " + e.getMessage());
/*     */     } 
/*     */     try {
/*  91 */       setUseCase(usecaseName);
/*  92 */       validateWidgetsRenderedToFindRenderTime();
/*  93 */     } catch (CCTException e) {
/*  94 */       logger.error("Unable to find usecase render time: " + e.getMessage());
/*  95 */       logRenderTime.setError(e.getMessage());
/*     */     } 
/*  97 */     logRenderTime.end();
///*  98 */     PortalUtils.hideDrawerAndSliderPanel(this.driver);
/*     */   }
/*     */   
/*     */   private void loadAndCheckForVisibilityOfCGFFilters(WebDriver driver) throws CCTException {
/* 102 */     if (((Long)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage().length")).longValue() != 0L) {
/*     */       try {
/* 104 */         WebUtils.waitForElementBy(driver, By.id("CommonGlobalFilter_ResetAll"), 30L);
/*     */ 
/*     */ 
/*     */         
/* 108 */         WebUtils.waitForElementToDisplay(driver, By.id("CommonGlobalFilter_ResetAll"), 5);
/* 109 */       } catch (Exception e) {
/* 110 */         throw new CCTException("CGF did not load in the specified time which is set as: 30 secs" + e
/* 111 */             .getMessage());
/*     */       } 
/*     */       
/* 114 */       if (WebUtils.findElementsBy(driver, By.className("k-notification-wrap")).size() > 0) {
/* 115 */         WebUtils.waitAndClickForElementBy(driver, 
/* 116 */             By.xpath("//*[contains(@class, 'k-notification-error')]/div/span[2]"));
/* 117 */         CGFSelectFiltersUtil.resetCGFFilter(driver, true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearDefaultCGFFilterSelection(CCTPortalBasePage cctPortalBasePage) throws CCTException {
/* 123 */     CGFSelectFiltersUtil.clearCGFFilterSelection(this.driver, cctPortalBasePage);
/*     */   }
/*     */   
/*     */   public void applyTimeFilter(String timeFilter, String value) throws CCTException {
/* 127 */     CGFSelectFiltersUtil.applyTimeFilter(timeFilter, value, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void selectApplyButton(Map<String, String> cgfFiltersFromFeature, Map<String, String> ceiFiltersFromFeature) throws CCTException {
/* 132 */     CGFSelectFiltersUtil.applyCGFFilter(this.driver);
/* 133 */     for (String key : cgfFiltersFromFeature.keySet()) {
/* 134 */       logger.info("Filter Name: " + key);
/* 135 */       String filterValues = cgfFiltersFromFeature.get(key);
/* 136 */       String[] filters = new String[0];
/* 137 */       if (key.equalsIgnoreCase("Partner operators")) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 142 */       filters = filterValues.split(",");
/* 143 */       for (String filter : filters) {
/* 144 */         logger.info("Filter Value: " + filter);
/* 145 */         Long length = (Long)WebUtils.executeJS(this.driver, "return $('.selected-filters-items:contains(\"" + filter + "\")').length");
/*     */         
/* 147 */         if (length.longValue() < 1L) {
/* 148 */           throw new CCTException("Unable to Applied CGF Filter : " + filter + " in SelectedFilters !!");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     for (String key : ceiFiltersFromFeature.keySet()) {
/* 154 */       logger.info("CEI Filter Name: " + key);
/* 155 */       String filterValues = ceiFiltersFromFeature.get(key);
/* 156 */       String[] filters = new String[0];
/* 157 */       filters = filterValues.split(",");
/* 158 */       for (String filter : filters) {
/* 159 */         logger.info("CEI Filter Value: " + filter);
/* 160 */         Long length = (Long)WebUtils.executeJS(this.driver, "return $('.selected-filters-items:contains(\"" + filter + "\")').length");
/*     */         
/* 162 */         if (length.longValue() < 1L) {
/* 163 */           throw new CCTException("Unable to Applied CGF Filter : " + filter + " in SelectedFilters !!");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void verifyCGFFilters(String cgfFilter) throws CCTException {
/* 170 */     clickCGFFilter(cgfFilter);
/* 171 */     if (cgfFilter.equals("Time")) {
/* 172 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Recent')]"));
/* 173 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Hour')]"));
/* 174 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Day')]"));
/* 175 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Week')]"));
/* 176 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Month')]"));
/* 177 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 178 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Current month')]"));
/* 179 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 180 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Last month')]"));
/* 181 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 182 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Last 3 months')]"));
/* 183 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 184 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Date range')]"));
/* 185 */     } else if (cgfFilter.equals("Technologies")) {
/* 186 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'2G')]"));
/* 187 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'3G')]"));
/* 188 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'4G')]"));
/* 189 */     } else if (cgfFilter.equals("Locations")) {
/* 190 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Regions')]"));
/* 191 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Cities')]"));
/* 192 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Areas')]"));
/* 193 */     } else if (cgfFilter.equals("APNs")) {
/* 194 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf-apns_taglist']"));
/* 195 */     } else if (cgfFilter.equals("Devices")) {
/* 196 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 197 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Manufacturer names')]"));
/* 198 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 199 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device types')]"));
/* 200 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 201 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device models')]"));
/* 202 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 203 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Device capabilities')]"));
/* 204 */     } else if (cgfFilter.equals("Subscribers")) {
/* 205 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Segments')]"));
/* 206 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 207 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Corporations')]"));
/* 208 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 209 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Corporation subgroups')]"));
/* 210 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 211 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription types')]"));
/* 212 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 213 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription plan A')]"));
/* 214 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 215 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscription plan B')]"));
/* 216 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 217 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Subscriber group name')]"));
/* 218 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 219 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Premium subscribers')]"));
/* 220 */     } else if (cgfFilter.equals("Roaming category")) {
/* 221 */       WebUtils.waitAndFindElementBy(this.driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Home')]"));
/* 222 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 223 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Partner operators')]"));
/* 224 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 225 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Inbound roamers')]"));
/* 226 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 227 */           By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Outbound roamers')]"));
/* 228 */     } else if (cgfFilter.equals("Preferences")) {
/* 229 */       WebUtils.waitAndFindElementBy(this.driver, 
/* 230 */           By.xpath(".//*[@id='cgf_menu']//label[contains(text(),'Filter unmapped data')]"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clickCGFFilter(String cgfFilter) throws CCTException {
/* 235 */     CGFSelectFiltersUtil.selectCGFMenuByID(cgfFilter, this.driver);
/*     */   }
/*     */   
/*     */   public void applyTimeFilterDefaultValue(String timeFilter, int value) throws CCTException {
/* 239 */     CGFSelectFiltersUtil.applyTimeFilterDefault(timeFilter, value, this.driver);
/*     */   }
/*     */   
/*     */   public void applyTechnologyFilter(String filters) throws CCTException {
/* 243 */     CGFSelectFiltersUtil.applyTechnologyFilter(filters, this.driver);
/*     */   }
/*     */   
/*     */   public void applyLocationFilter(String location, String filters) throws CCTException {
/* 247 */     CGFSelectFiltersUtil.applyLocationFilter(location, filters, this.driver);
/*     */   }
/*     */   
/*     */   public void applyAPNs(String apnValue) throws CCTException {
/* 251 */     CGFSelectFiltersUtil.applyAPNsFilter(apnValue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyDevices(String device, String value) throws CCTException {
/* 255 */     CGFSelectFiltersUtil.applyDevicesFilter(device, value, this.driver);
/*     */   }
/*     */   
/*     */   public void applySubscribers(String subscriber, String value) throws CCTException {
/* 259 */     CGFSelectFiltersUtil.applySubscribersFilter(subscriber, value, this.driver);
/*     */   }
/*     */   
/*     */   public void applyRoamingCategory(String roamingCategory, String value) throws CCTException {
/* 263 */     CGFSelectFiltersUtil.applyRoamingCategoryFilter(roamingCategory, value, this.driver);
/*     */   }
/*     */   
/*     */   public void applyNetworkTopologies(String aggregationlevel, String value) throws CCTException {
/* 267 */     CGFSelectFiltersUtil.applyNetworkTopologies(aggregationlevel, value, this.driver);
/*     */   }
/*     */   
/*     */   public void applyPreferences(String unmappedData) throws CCTException {
/* 271 */     CGFSelectFiltersUtil.applyPreferences(unmappedData, this.driver);
/*     */   }
/*     */   
/*     */   public void resetCGFFilters() throws CCTException {
/* 275 */     CGFSelectFiltersUtil.resetCGFFilter(this.driver, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyCustomerFilter(String customerFilter, String lifetimeFilter, String lifetimeValue) throws CCTException {
/* 280 */     CEISelectFiltersUtil.applyCustomerFilter(customerFilter, lifetimeFilter, lifetimeValue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyAgeFilter(String ageFilter, String ageFilterValue) throws CCTException {
/* 284 */     CEISelectFiltersUtil.applyAgeFilter(ageFilter, ageFilterValue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyGenderFilter(String genderFilter, String genderFilterValue) throws CCTException {
/* 288 */     CEISelectFiltersUtil.applyGenderFilter(genderFilter, genderFilterValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applySubscriptionFilter(String subscriptionFilter, String enddatefilter, String enddatevalue) throws CCTException {
/* 293 */     CEISelectFiltersUtil.applySubscriptionFilter(subscriptionFilter, enddatefilter, enddatevalue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyDataFilter(String usageFilter, String datafilter, String datafiltervalue) throws CCTException {
/* 297 */     CEISelectFiltersUtil.applyDataFilter(usageFilter, datafilter, datafiltervalue, this.driver);
/*     */   }
/*     */   
/*     */   public void applySmsFilter(String smsFilter, String smssentorreceived, String smsValue) throws CCTException {
/* 301 */     CEISelectFiltersUtil.applySmsFilter(smsFilter, smssentorreceived, smsValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyVoiceFilter(String voiceFilter, String voiceCalls, String voiceCallsValue) throws CCTException, InterruptedException {
/* 306 */     CEISelectFiltersUtil.applyVoiceFilter(voiceFilter, voiceCalls, voiceCallsValue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyVolteFilter(String volteFilter, String volteCalls, String volteCallsValue) throws CCTException {
/* 310 */     CEISelectFiltersUtil.applyVolteFilter(volteFilter, volteCalls, volteCallsValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyQualitySmsFilter(String qualityFilter, String qualitySmsFilter, String qualitySmsValue) throws CCTException {
/* 315 */     CEISelectFiltersUtil.applyQualitySmsFilter(qualityFilter, qualitySmsFilter, qualitySmsValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyQualityDataFilter(String qualityFilter, String qualityDataFilter, String qualityDataValue) throws CCTException {
/* 320 */     CEISelectFiltersUtil.applyQualityDataFilter(qualityFilter, qualityDataFilter, qualityDataValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyQualityRadioFilter(String qualityFilter, String qualityRadioFilter, String qualityRadioValue) throws CCTException {
/* 325 */     CEISelectFiltersUtil.applyQualityRadioFilter(qualityFilter, qualityRadioFilter, qualityRadioValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyQualityVoiceFilter(String qualityFilter, String qualityVoiceFilter, String qualityVoiceValue) throws CCTException {
/* 330 */     CEISelectFiltersUtil.applyQualityVoiceFilter(qualityFilter, qualityVoiceFilter, qualityVoiceValue, this.driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyQualityVolteFilter(String qualityFilter, String qualityVolteFilter, String qualityVolteValue) throws CCTException {
/* 335 */     CEISelectFiltersUtil.applyQualityVolteFilter(qualityFilter, qualityVolteFilter, qualityVolteValue, this.driver);
/*     */   }
/*     */   
/*     */   public void applyIndexFilter(String ceIndex, String ceIndexFilter, String ceIndexValue) throws CCTException {
/* 339 */     CEISelectFiltersUtil.applyIndexFilter(ceIndex, ceIndexFilter, ceIndexValue, this.driver);
/*     */   }
/*     */   
/*     */   public void navigateToUsecaseView(String view) throws CCTException {
/* 343 */     WebUtils.waitAndClickForElementBy(this.driver, By.xpath(".//button[@label='" + view + "']"));
/*     */   }
/*     */   
/*     */   public void loadAllUsecases(String view) throws CCTException {
/* 347 */     List<WebElement> lt = this.driver.findElements(By.xpath(".//p[contains(text(), '" + view + "') and @class='content-app-name']/following-sibling::div//*[contains(@class, 'usecase-list')]//li//a"));
/*     */     
/* 349 */     List<String> usecaseNames = new ArrayList<String>();
/* 350 */     for (WebElement webElement : lt) {
/* 351 */       usecaseNames.add(webElement.getText());
/* 352 */       WebUtils.waitForWidgetsToRender();
/*     */     } 
/* 354 */     for (String usecase : usecaseNames) {
/* 355 */       navigateToUsecasePageOnSeparateTab(usecase);
/*     */     }
/*     */   }
/*     */   
/*     */   private void navigateToUsecasePageOnSeparateTab(String useCaseName) throws CCTException {
/* 360 */     String currentTab, base = this.driver.getWindowHandle();
/*     */     
/*     */     try {
/* 363 */       PortalUtils.searchUsecase(useCaseName, this.driver);
/* 364 */       String selectLinkOpeninNewTab = Keys.chord(new CharSequence[] { (CharSequence)Keys.CONTROL, (CharSequence)Keys.RETURN });
/* 365 */       WebElement element = WebUtils.waitAndFindElementBy(this.driver, By.partialLinkText(useCaseName));
/* 366 */       Actions action = new Actions(this.driver);
/* 367 */       action.moveToElement(element);
/* 368 */       element = WebUtils.waitAndFindElementBy(this.driver, By.partialLinkText(useCaseName));
/* 369 */       element.sendKeys(new CharSequence[] { selectLinkOpeninNewTab });
/* 370 */       Set<String> set = this.driver.getWindowHandles();
/* 371 */       set.remove(base);
/* 372 */       currentTab = (String)set.toArray()[0];
/* 373 */       this.driver.switchTo().window(currentTab);
/* 374 */     } catch (CCTException e) {
/* 375 */       throw new CCTException("Failed to find and click on usecase link: " + useCaseName);
/*     */     } 
/* 377 */     loadUsecasePageWithCGFForAllUsecases(useCaseName);
/* 378 */     this.driver.switchTo().window(currentTab).close();
/* 379 */     this.driver.switchTo().window(base);
/* 380 */     this.driver.findElement(By.xpath(".//*[@id='searchInput1']//input")).clear();
/*     */   }
/*     */   
/*     */   public void loadUsecasePageWithCGFForAllUsecases(String usecaseName) throws CCTException {
/* 384 */     RenderingTimeUseCaseLoad logRenderTime = new RenderingTimeUseCaseLoad(usecaseName);
/* 385 */     logRenderTime.start();
/* 386 */     loadAndCheckForVisibilityOfCGFFilters(this.driver);
/*     */     try {
/* 388 */       WebUtils.waitForUseCaseToLoad(this.driver);
/* 389 */       Assert.assertTrue("Succesfully Loaded:" + usecaseName, true);
/* 390 */     } catch (CCTException e) {
/* 391 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 392 */         .as("Error in loading use case: " + usecaseName + " : " + e.getMessage(), new Object[0])).isEqualTo(true);
/*     */     } 
/*     */     try {
/* 395 */       setUseCase(usecaseName);
/* 396 */       validateWidgetsRenderedToFindRenderTime();
/* 397 */     } catch (CCTException e) {
/* 398 */       logger.error("Unable to find usecase render time: " + e.getMessage());
/* 399 */       logRenderTime.setError(e.getMessage());
/*     */     } 
/* 401 */     logRenderTime.end();
/*     */   }
/*     */   
/*     */   public void searchUsecase(String useCase) throws CCTException {
/* 405 */     PortalUtils.searchUsecase(useCase, this.driver);
/*     */   }
/*     */   
/*     */   public void setUsecaseAsFavourite(String useCase) throws CCTException {
/* 409 */     WebUtils.waitAndClickForElementBy(this.driver, 
/* 410 */         By.xpath(".//a[contains(text(),'" + useCase + "')]/preceding-sibling::div"));
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\pages\CCTPortalBasePage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */