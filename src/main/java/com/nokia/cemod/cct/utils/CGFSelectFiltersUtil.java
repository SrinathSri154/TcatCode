/*     */ package com.nokia.cemod.cct.utils;
/*     */ 
/*     */ import com.nokia.cemod.cct.cgf.CGFApiUtil;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.pages.CCTPortalBasePage;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.StaleElementReferenceException;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CGFSelectFiltersUtil
/*     */ {
/*  26 */   static Set<String> timeFilterDatePickerList = new HashSet<String>(
/*  27 */       Arrays.asList(new String[] { "Day", "Date range" }));
/*     */   
/*  29 */   static Set<String> subscriberFilterMultiCheckList = new HashSet<String>(
/*  30 */       Arrays.asList(new String[] { "Subscription types" }));
/*     */   
/*  32 */   static Set<String> subscriberFilterRadioCheckList = new HashSet<String>(
/*  33 */       Arrays.asList(new String[] { "Premium subscribers" }));
/*     */   
/*  35 */   static Set<String> roamingCategoryFilterMultiCheckList = new HashSet<String>(
/*  36 */       Arrays.asList(new String[] { "Home", "Inbound roamers", "Outbound roamers" }));
/*     */ 
/*     */   
/*     */   public static void applyTimeFilter(String timeFilter, String value, WebDriver driver) throws CCTException {
/*  40 */     selectCGFMenuByID("Time", driver);
/*  41 */     selectCGFMenuByID(timeFilter, driver);
/*  42 */     if (timeFilterDatePickerList.contains(timeFilter)) {
/*  43 */       if (timeFilter.equals("Day")) {
/*  44 */         addDateForDayCGFFilter(value, driver);
/*  45 */       } else if (timeFilter.equals("Date range")) {
/*  46 */         addDateForCustomDatesCGFFilter(value, driver);
/*     */       } 
/*     */     } else {
/*  49 */       selectCGFMenuElementValue(timeFilter, value, driver);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void applyTimeFilterDefault(String timeFilter, int value, WebDriver driver) throws CCTException {
/*  54 */     selectCGFMenuByID("Time", driver);
/*  55 */     selectCGFMenuByID(timeFilter, driver);
/*  56 */     selectCGFMenuElementDefaultValue(timeFilter, value, driver);
/*     */   }
/*     */   
/*     */   public static void applyTechnologyFilter(String values, WebDriver driver) throws CCTException {
/*  60 */     selectCGFMenuByID("Technologies", driver);
/*  63 */     selectCGFTechnologyMenuItem(values, driver);
/*     */   }
/*     */   
/*     */   public static void applyLocationFilter(String locationFilter, String value, WebDriver driver) throws CCTException {
/*  68 */     selectCGFMenuByID("Locations", driver);
/*  69 */     selectCGFMenuByID(locationFilter, driver);
/*  70 */     selectMenuItem(locationFilter, getElementValues(value), driver);
/*     */   }
/*     */   
/*     */   public static void applyAPNsFilter(String apnsFilter, WebDriver driver) throws CCTException {
/*  74 */     selectCGFMenuByID("APNs", driver);
/*  75 */     selectMenuItem("APNs", getElementValues(apnsFilter), driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applySubscribersFilter(String subscribersFilter, String value, WebDriver driver) throws CCTException {
/*  80 */     selectCGFMenuByID("Subscribers", driver);
/*  81 */     selectCGFMenuByID(subscribersFilter, driver);
/*  82 */     if (subscriberFilterMultiCheckList.contains(subscribersFilter)) {
/*  83 */       selectMultiSelectCheckBoxMenuItem(subscribersFilter, value, driver, true);
/*  84 */     } else if (subscriberFilterRadioCheckList.contains(subscribersFilter)) {
/*  85 */       selectRadioCheckBoxMenuItem(subscribersFilter, value, driver, true);
/*     */     } else {
/*  87 */       String[] multiSelectLabels = null;
/*  88 */       if (value != null && value.length() > 0) {
/*  89 */         multiSelectLabels = value.split("\\s*,\\s*");
/*     */       }
/*  91 */       selectMenuItem(subscribersFilter, multiSelectLabels, driver);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyRoamingCategoryFilter(String roamingCategoryFilter, String value, WebDriver driver) throws CCTException {
/*  97 */     selectCGFMenuByID("Roaming category", driver);
/*  98 */     selectCGFMenuByID(roamingCategoryFilter, driver);
/*  99 */     if (roamingCategoryFilterMultiCheckList.contains(roamingCategoryFilter)) {
/* 100 */       selectMultiSelectCheckBoxMenuItem(roamingCategoryFilter, value, driver, true);
/*     */     } else {
/* 102 */       String[] multiSelectLabels = null;
/* 103 */       if (value != null && value.length() > 0) {
/* 104 */         multiSelectLabels = value.split("[|]");
/*     */       }
/* 106 */       selectMenuItem(roamingCategoryFilter, multiSelectLabels, driver);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void applyDevicesFilter(String devicesFilter, String value, WebDriver driver) throws CCTException {
/* 111 */     selectCGFMenuByID("Devices", driver);
/* 112 */     selectCGFMenuByID(devicesFilter, driver);
/* 113 */     selectMenuItem(devicesFilter, getElementValues(value), driver);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void applyNetworkTopologies(String networktopologyfilter, String value, WebDriver driver) throws CCTException {
/* 118 */     selectCGFMenuByID("Network Topologies", driver);
/* 119 */     selectCGFMenuByID(networktopologyfilter, driver);
/* 120 */     selectMenuItem(networktopologyfilter, getElementValues(value), driver);
/*     */   }
/*     */   
/*     */   public static void applyPreferences(String upmappedData, WebDriver driver) throws CCTException {
/* 124 */     selectCGFMenuByID("Preferences", driver);
/* 125 */     selectCGFPreferencesMenuItem(upmappedData, driver);
/*     */   }
/*     */   
/*     */   private static String[] getElementValues(String elementNames) {
/* 129 */     String[] multiSelectLabels = null;
/* 130 */     if (elementNames != null && elementNames.length() > 0) {
/* 131 */       multiSelectLabels = elementNames.split("\\s*,\\s*");
/*     */     }
/* 133 */     return multiSelectLabels;
/*     */   }
/*     */   
/*     */   public static void selectCGFMenuByID(String menuFilterName, WebDriver driver) throws CCTException {
/* 137 */     WebUtils.waitAndClickForElementBy(driver, 
/* 138 */         By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'" + menuFilterName + "')]"));
/* 139 */     WebUtils.waitForWidgetsToRender();
/*     */   }
/*     */   
// /*     */   private static void selectCGFTechnologyMenuItem(String elementName, WebDriver driver) throws CCTException {
// /* 143 */     if (((Long)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage().indexOf('technology')")).longValue() > -1L) {
// /*     */       
// /* 145 */       WebUtils.waitAndClickForElementBy(driver, 
// /* 146 */           By.xpath(".//*[@id='technology-cgf-options-table']//label[contains(text(),'" + elementName + "')]"));
// /* 147 */     } else if (((Long)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage().indexOf('technologies')")).longValue() > -1L) {
// /*     */       
// /* 149 */       WebUtils.waitAndClickForElementBy(driver, By.xpath(".//*[@id='technologies-cgf-options-table']//label[contains(text(),'" + elementName + "')]"));
// /*     */     } 
// /*     */   }

            private static void selectCGFTechnologyMenuItem(String values, WebDriver driver) throws CCTException {
                List<WebElement> chkTechnology = WebUtils.findElementsBy(driver,
                        By.xpath("//input[@name='technology' and @type='checkbox']"));
                for (WebElement tech : chkTechnology) {
                    if(tech.isSelected()) {
                        tech.click();
                    }
                }
                 String[] technologies = values.split("\\s*,\\s*");
                 for (String tech : technologies) {
                     WebUtils.waitAndClickForElementBy(driver,
                             By.xpath(".//*[@id='technology-cgf-options-table']//label[contains(text(),'" + tech + "')]"));
                 }
            }

/*     */ 
/*     */   
/*     */   private static void selectCGFPreferencesMenuItem(String elementName, WebDriver driver) throws CCTException {
///* 155 */     WebUtils.waitAndClickForElementBy(driver,
///* 156 */         By.xpath(".//*[@id='unmapped-cgf-options-table']//label[contains(text(),'" + elementName + "')]"));
                String xpath = ".//*[@id='unmapped-cgf-options-table']//label[contains(text(),'" + elementName + "')]//parent::td/input";
                WebElement element = WebUtils.waitAndFindElementBy(driver, By.xpath(xpath));
                if(!element.isSelected())
                    element.click();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectMenuItem(String filterName, String[] multiSelectLabels, WebDriver driver) throws CCTException {
/* 162 */     Object list = WebUtils.executeJS(driver, "return $('#" + (String)CGFFilters.multiSelectIds
/* 163 */         .get(filterName) + "').attr('data-role');");
/* 164 */     if (multiSelectLabels != null) {
/* 165 */       for (String multiSelectLabel : multiSelectLabels) {
/* 166 */         if (list != null && list.toString().equalsIgnoreCase("dropdownlist")) {
/* 167 */           selectItemFromSingleSelectDropDownList(driver, multiSelectLabel, filterName);
/*     */         } else {
/* 169 */           selectItemFromMultiSelectDropDownList(driver, multiSelectLabel, filterName);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectItemFromMultiSelectDropDownList(WebDriver driver, String multiSelectLabel, String filterName) throws CCTException {
/* 178 */     WebUtils.executeJS(driver, "$('#" + (String)CGFFilters.multiSelectIds.get(filterName) + "_taglist').parent().parent().trigger('mousedown');");
/*     */     
/* 180 */     WebUtils.executeJS(driver, "$($('#" + (String)CGFFilters.multiSelectIds.get(filterName) + "_taglist').siblings()[0]).val('" + multiSelectLabel + "');");
/*     */     
/* 182 */     WebUtils.waitForWidgetsToRender();
/* 183 */     WebUtils.executeJS(driver, "$($('#" + (String)CGFFilters.multiSelectIds
/* 184 */         .get(filterName) + "_taglist').siblings()[0]).trigger('keydown');");
/* 185 */     WebUtils.waitForWidgetsToRender();
/*     */     
/* 187 */     List<Object> obj = (List<Object>)WebUtils.executeJS(driver, "return $($('#" + (String)CGFFilters.multiSelectIds
/* 188 */         .get(filterName) + "_listbox').find('li').filter(function(){return $(this).text() === '" + multiSelectLabel + "';})).trigger('click');");
/*     */ 
/*     */     
/* 191 */     WebUtils.waitForWidgetsToRender();
/* 192 */     if (obj != null && obj.size() < 1) {
/* 193 */       throw new CCTException("Unable to Find Element : " + multiSelectLabel + " in " + filterName + " MultiSelect !!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectItemFromSingleSelectDropDownList(WebDriver driver, String multiSelectLabel, String filterName) throws CCTException {
/* 201 */     WebUtils.executeJS(driver, "$('#" + (String)CGFFilters.multiSelectIds.get(filterName) + "').trigger('click');");
/* 202 */     WebUtils.executeJS(driver, "$('#" + (String)CGFFilters.multiSelectIds.get(filterName) + "').data('kendoDropDownList').value('" + multiSelectLabel + "');");
/*     */     
/* 204 */     List<Object> obj = (List<Object>)WebUtils.executeJS(driver, "return $($('#" + (String)CGFFilters.multiSelectIds
/* 205 */         .get(filterName) + "_listbox').find('li:contains(" + multiSelectLabel + ")')).trigger('click');");
/*     */     
/* 207 */     WebUtils.waitForWidgetsToRender();
/* 208 */     if (obj != null && obj.size() < 1) {
/* 209 */       throw new CCTException("Unable to Find Element : " + multiSelectLabel + " in " + filterName + " Single Select !!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectMultiSelectCheckBoxMenuItem(String filterName, String elementNames, WebDriver driver, boolean isChecked) throws CCTException {
/*     */     try {
/* 217 */       if (elementNames != null && elementNames.length() > 0) {
/* 218 */         String[] labels = elementNames.split("\\s*,\\s*");
/* 219 */         for (String label : labels) {
/* 220 */           WebUtils.executeJS(driver, "$('#" + (String)CGFFilters.multiSelectCheckBoxIds.get(filterName) + "').find(\"td:contains('" + label + "')\").children()[0].checked = \"isChecked\"");
/*     */           
/* 222 */           WebUtils.executeJS(driver, "$($('#" + (String)CGFFilters.multiSelectCheckBoxIds.get(filterName) + "').find(\"td:contains('" + label + "')\").children()[0]).trigger('change')");
/*     */         }
/*     */       
/*     */       } 
/* 226 */     } catch (CCTException cctEx) {
/* 227 */       String[] multiSelectLabels = null;
/* 228 */       if (elementNames != null && elementNames.length() > 0) {
/* 229 */         multiSelectLabels = elementNames.split("\\s*,\\s*");
/*     */       }
/* 231 */       selectMenuItem(filterName, multiSelectLabels, driver);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void selectRadioCheckBoxMenuItem(String filterName, String elementNames, WebDriver driver, boolean isChecked) throws CCTException {
/* 237 */     if (elementNames != null && elementNames.length() > 0) {
/* 238 */       String[] labels = elementNames.split("\\s*,\\s*");
/* 239 */       for (String label : labels) {
/* 240 */         WebUtils.executeJS(driver, "$('#" + (String)CGFFilters.radioCheckBoxIds.get(filterName) + "').find(\"td:contains('" + label + "')\").children()[0].checked = \"isChecked\"");
/*     */         
/* 242 */         WebUtils.executeJS(driver, "$($('#" + (String)CGFFilters.radioCheckBoxIds.get(filterName) + "').find(\"td:contains('" + label + "')\").children()[0]).trigger('change')");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void clearCGFFilterSelection(WebDriver driver, CCTPortalBasePage cctPage) throws CCTException {
/* 250 */     resetCGFFilter(driver, false);
/*     */ 
/*     */     
/*     */     try {
/* 254 */       cctPage.validateWidgetsRendered();
/* 255 */       clearTechnologyFilter(driver);
/* 256 */     } catch (CCTException e) {
/*     */ 
/*     */       
/*     */       try {
/* 260 */         clearTechnologyFilter(driver);
/* 261 */       } catch (Exception e1) {
/* 262 */         WebUtils.captureScreenShot(driver);
/* 263 */         throw new CCTException("Unable to clear default CGF Filters" + e.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void clearTechnologyFilter(WebDriver driver) throws CCTException {
/* 270 */     if (((Long)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage().indexOf('technology')")).longValue() > -1L || (
/*     */       
/* 272 */       (Long)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage().indexOf('technologies')")).longValue() > -1L) {
/*     */       
/* 274 */       Map<String, Object> appliedFilters = CGFApiUtil.getAppliedFilters(driver);
/* 275 */       ArrayList<String> appliedTechnologies = new ArrayList<String>();
/* 276 */       String technologyKeyWord = "";
/*     */ 
/*     */       
/* 279 */       if (appliedFilters.get("technology") != null) {
/* 280 */         technologyKeyWord = "technology";
/* 281 */         appliedTechnologies = (ArrayList<String>)appliedFilters.get(technologyKeyWord);
/* 282 */       } else if (appliedFilters.get("technologies") != null) {
/* 283 */         technologyKeyWord = "technologies";
/*     */         
/* 285 */         ArrayList<Map<String, Object>> technologyList = (ArrayList<Map<String, Object>>)appliedFilters.get(technologyKeyWord);
/*     */         
/* 287 */         Iterator<Map<String, Object>> iterator = technologyList.iterator();
/* 288 */         while (iterator.hasNext()) {
/* 289 */           Map<String, Object> technology = iterator.next();
/* 290 */           appliedTechnologies.add(technology.get("Technology").toString());
/*     */         } 
/*     */       } 
/* 293 */       Iterator<String> technologyIterator = appliedTechnologies.iterator();
/*     */       
/* 295 */       while (technologyIterator.hasNext()) {
/* 296 */         String technology = technologyIterator.next();
/* 297 */         List<WebElement> selectedTechnology = driver.findElements(By.xpath(".//*[@id='" + technologyKeyWord + "-cgf-options-table']//label[contains(text(),'" + technology + "')]"));
/*     */         
/* 299 */         WebUtils.waitAndClickForElementBy(driver, 
/* 300 */             By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'Technologies')]"));
/* 301 */         if (selectedTechnology.size() != 0) {
/* 302 */           WebUtils.waitForWidgetsToRender();
/* 303 */           WebUtils.waitAndClickForElementBy(driver, By.xpath(".//*[@id='" + technologyKeyWord + "-cgf-options-table']//label[contains(text(),'" + technology + "')]"));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void resetCGFFilter(WebDriver driver, boolean timeOut) throws CCTException {
/* 312 */     if (timeOut) {
/* 313 */       WebUtils.waitForWidgetsToRender();
/*     */     }
/* 315 */     WebUtils.waitAndClickForElementBy(driver, By.id("CommonGlobalFilter_ResetAll"));
/* 316 */     WebUtils.waitAndAssertForElementBy(driver, By.id("cgfConfirmDialog"), "Reset will remove all selections and apply only default filters.");
/*     */     
/* 318 */     WebUtils.waitAndClickForElementBy(driver, By.xpath(".//div[@role='toolbar']//button[1]"));
/*     */   }
/*     */   
/*     */   public static void applyCGFFilter(WebDriver driver) throws CCTException {
/* 322 */     clearCGFNotificationErrorMsg(driver);
/* 323 */     WebUtils.waitAndClickForElementBy(driver, By.id("CommonGlobalFilter_Apply"));
/*     */ 
/*     */     
/* 326 */     WebUtils.waitForWidgetsToRender();
/*     */   }
/*     */   
/*     */   public static void clearCGFNotificationErrorMsg(WebDriver driver) throws CCTException {
/* 330 */     if (WebUtils.findElementsBy(driver, By.className("k-notification-wrap")).size() > 0) {
/*     */       try {
/* 332 */         WebUtils.waitAndClickForElementBy(driver, 
/* 333 */             By.xpath(".//div[@class='k-notification-wrap']//span[@title='Hide']"));
/* 334 */         WebUtils.waitForWidgetsToRender();
/* 335 */       } catch (StaleElementReferenceException staleElementReferenceException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectCGFMenuElementValue(String filter, String value, WebDriver driver) throws CCTException {
/* 344 */     WebUtils.executeJS(driver, "$($(\"select>option[id*='" + (String)CGFFilters.timeFilterSelectIds.get(filter) + "']:contains('" + value + "')\")).trigger('click');");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void selectCGFMenuElementDefaultValue(String filter, int value, WebDriver driver) throws CCTException {
/* 350 */     if (!filter.equals("Day")) {
/* 351 */       WebElement filterEle = WebUtils.waitAndFindElementBy(driver, By.xpath(".//*[@id='cgf_menu']//span[contains(text(),'" + filter + "')]/parent::span/parent::li"));
/* 352 */       List<WebElement> options = filterEle.findElements(By.xpath(".//select/option"));
/* 353 */       WebUtils.waitAndClickElement(driver, options.get(value));
/*     */     } else {
/* 355 */       WebUtils.waitAndSendKeysForElementBy(driver, By.id("dayFilterDatePicker"), getDateAndTime(0, 3));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void addDateForDayCGFFilter(String value, WebDriver driver) throws CCTException {
/* 360 */     WebUtils.waitAndSendKeysForElementBy(driver, By.id("dayFilterDatePicker"), value);
/*     */   }
/*     */   
/*     */   private static void addDateForCustomDatesCGFFilter(String value, WebDriver driver) throws CCTException {
/* 364 */     if (value.equals("15 minutes")) {
/* 365 */       WebUtils.findElementBy(driver, By.id("FIFTEEN_MINSgran-option")).click();
/* 366 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("startDatePicker"), getDateAndTime(0, 3));
/* 367 */       WebUtils.performEnterOperationOnElement(driver, By.id("startDatePicker"));
/* 368 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("endDatePicker"), getDateAndTime(0, 1));
/* 369 */     } else if (value.equals("Hourly")) {
/* 370 */       WebUtils.findElementBy(driver, By.id("HOURLYgran-option")).click();
/* 371 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("startDatePicker"), getDateAndTime(0, 3));
/* 372 */       WebUtils.performEnterOperationOnElement(driver, By.id("startDatePicker"));
/* 373 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("endDatePicker"), getDateAndTime(0, 1));
/* 374 */     } else if (value.equals("Daily")) {
/* 375 */       WebUtils.findElementBy(driver, By.id("DAILYgran-option")).click();
/* 376 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("startDatePicker"), getDateAndTime(1, 7));
/* 377 */       WebUtils.performEnterOperationOnElement(driver, By.id("startDatePicker"));
/* 378 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("endDatePicker"), getDateAndTime(1, 1));
/* 379 */     } else if (value.equals("Weekly")) {
/* 380 */       WebUtils.findElementBy(driver, By.id("WEEKLYgran-option")).click();
/* 381 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("startDatePicker"), getDateAndTime(1, 7));
/* 382 */       WebUtils.performEnterOperationOnElement(driver, By.id("startDatePicker"));
/* 383 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("endDatePicker"), getDateAndTime(1, 1));
/* 384 */     } else if (value.equals("Monthly")) {
/* 385 */       WebUtils.findElementBy(driver, By.id("MONTHLYgran-option")).click();
/* 386 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("startDatePicker"), getDateAndTime(1, 7));
/* 387 */       WebUtils.performEnterOperationOnElement(driver, By.id("startDatePicker"));
/* 388 */       WebUtils.waitForElementToBeClickabableAndSendKeys(driver, By.id("endDatePicker"), getDateAndTime(1, 1));
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getDateAndTime(int date, int hour) {
/* 393 */     Calendar cal = Calendar.getInstance();
/* 394 */     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
/* 395 */     cal.add(5, -date);
/* 396 */     cal.add(10, -hour);
/* 397 */     return dateFormat.format(cal.getTime());
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\CGFSelectFiltersUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */