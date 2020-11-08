/*     */ package com.nokia.cemod.cct.cgf;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CGFPageFilters
/*     */ {
/*  20 */   private ArrayList<String> technologyFilters = new ArrayList<String>();
/*     */   
/*  22 */   private ArrayList<String> apnFilter = new ArrayList<String>();
/*     */   
/*  24 */   private Map<String, ArrayList<String>> timeFilter = new HashMap<String, ArrayList<String>>();
/*     */   
/*  26 */   private Map<String, ArrayList<String>> locationFilter = new HashMap<String, ArrayList<String>>();
/*     */   
/*  28 */   private Map<String, ArrayList<String>> deviceFilter = new HashMap<String, ArrayList<String>>();
/*     */   
/*  30 */   private Map<String, ArrayList<String>> subscriberFilter = new HashMap<String, ArrayList<String>>();
/*     */   
/*  32 */   private Map<String, ArrayList<String>> roamingCategoryFilter = new HashMap<String, ArrayList<String>>();
/*     */   
/*     */   public CGFPageFilters(WebDriver driver) throws CCTException {
/*  35 */     processCGFFilters(driver);
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getTimeFilter() {
/*  39 */     return this.timeFilter;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getTechnologyFilter() {
/*  43 */     return this.technologyFilters;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getLocationFilter() {
/*  47 */     return this.locationFilter;
/*     */   }
/*     */   
/*     */   public ArrayList<String> getApnFilter() {
/*  51 */     return this.apnFilter;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getDeviceFilter() {
/*  55 */     return this.deviceFilter;
/*     */   }
/*     */   
/*     */   public Map<String, ArrayList<String>> getSubscriberFilter() {
/*  59 */     return this.subscriberFilter;
/*     */   }
/*     */   
/*     */   public List<String> getRandomTechnologyFilters() {
/*  63 */     return getRandomList(this.technologyFilters, 2);
/*     */   }
/*     */   
/*     */   public List<String> getRandomApnFilters() {
/*  67 */     return getRandomList(this.apnFilter, 2);
/*     */   }
/*     */   
/*     */   public HashMap<String, List<String>> getRandomLocationFilters() {
/*  71 */     return getRandomMap(this.locationFilter, 2);
/*     */   }
/*     */   
/*     */   public HashMap<String, List<String>> getRandomdeviceFilters() {
/*  75 */     return getRandomMap(this.deviceFilter, 2);
/*     */   }
/*     */   
/*     */   public HashMap<String, List<String>> getRandomSubscriberFilters() {
/*  79 */     return getRandomMap(this.subscriberFilter, 2);
/*     */   }
/*     */   
/*     */   public HashMap<String, List<String>> getRandomTimeFilters() {
/*  83 */     return getRandomMap(this.timeFilter, 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private HashMap<String, List<String>> getRandomMap(Map<String, ArrayList<String>> mapFilter, int numberOfItemsToSelect) {
/*  88 */     HashMap<String, List<String>> randomMap = new HashMap<String, List<String>>();
/*  89 */     String randomKey = getRandomMapKey(mapFilter);
/*  90 */     ArrayList<String> locations = mapFilter.get(randomKey);
/*  91 */     randomMap.put(randomKey, getRandomList(locations, numberOfItemsToSelect));
/*  92 */     return randomMap;
/*     */   }
/*     */   
/*     */   private String getRandomMapKey(Map<String, ArrayList<String>> filter) {
/*  96 */     Random random = new Random();
/*  97 */     List<String> keys = new ArrayList<String>(filter.keySet());
/*  98 */     return keys.get(random.nextInt(keys.size()));
/*     */   }
/*     */   
/*     */   private List<String> getRandomList(ArrayList<String> originalList, int numberOfItemsToSelect) {
/* 102 */     List<String> listCopy = new ArrayList<String>(originalList);
/* 103 */     Collections.shuffle(listCopy);
/* 104 */     return listCopy.subList(0, numberOfItemsToSelect);
/*     */   }
/*     */   
/*     */   public void processCGFFilters(WebDriver driver) throws CCTException {
/* 108 */     Map<String, Object> applicableFilterData = CGFApiUtil.getAllApplicableFilterItemsMap(driver);
/* 109 */     ArrayList<String> applicableFilters = CGFApiUtil.getAllApplicableFilterKeysForCurrentPage(driver);
/* 110 */     if (applicableFilters.indexOf("technology") > -1) {
/* 111 */       setTechnologyFilter(applicableFilterData);
/*     */     }
/* 113 */     if (applicableFilters.indexOf("time") > -1) {
/* 114 */       setTimeFilter(applicableFilterData);
/*     */     }
/* 116 */     if (applicableFilters.indexOf("apns") > -1) {
/* 117 */       setApnFilter(applicableFilterData);
/*     */     }
/* 119 */     if (applicableFilters.indexOf("devicetypes") > -1) {
/* 120 */       setDeviceFilter(applicableFilterData);
/*     */     }
/* 122 */     if (applicableFilters.indexOf("region") > -1) {
/* 123 */       setLocationFilter(applicableFilterData);
/*     */     }
/* 125 */     if (applicableFilters.indexOf("corporations") > -1) {
/* 126 */       setSubscriberFilter(applicableFilterData);
/*     */     }
/* 128 */     if (applicableFilters.indexOf("home") > -1) {
/* 129 */       setRoamingCategoryFilter(applicableFilterData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTechnologyFilter(Map<String, Object> cgfApplicableFilters) {
/* 137 */     ArrayList<Map<String, String>> filters = (ArrayList<Map<String, String>>)((Map)((Map)cgfApplicableFilters.get("filter_technology")).get("technology")).get("filteritems");
/* 138 */     for (Iterator<Map<String, String>> iterator = filters.iterator(); iterator.hasNext(); ) {
/* 139 */       Map<String, String> technology = iterator.next();
/* 140 */       this.technologyFilters.add(technology.get("filtervalue"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setApnFilter(Map<String, Object> cgfApplicableFilters) {
/* 148 */     ArrayList<Map<String, Map<String, String>>> filters = (ArrayList<Map<String, Map<String, String>>>)((Map)((Map)cgfApplicableFilters.get("filter_apns")).get("apns")).get("filteritems");
/* 149 */     for (Iterator<Map<String, Map<String, String>>> iterator = filters.iterator(); iterator.hasNext(); ) {
/* 150 */       Map<String, Map<String, String>> apn = iterator.next();
/* 151 */       this.apnFilter.add(((String)((Map)apn.get("filtervalue")).get("APN")).toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setTimeFilter(Map<String, Object> cgfApplicableFilters) {
/* 158 */     Map<String, Map> time = (Map<String, Map>)((Map)((Map)cgfApplicableFilters.get("filter_time")).get("time")).get("filteritems");
/* 159 */     Iterator<String> timeIterator = time.keySet().iterator();
/* 160 */     String filterKey = "labelWithDate";
/* 161 */     while (timeIterator.hasNext()) {
/* 162 */       String granularity = timeIterator.next();
/* 163 */       String timeKey = getAssociatedTimeKey(granularity);
/* 164 */       Map item = time.get(granularity);
/* 165 */       ArrayList<Map<String, String>> items = (ArrayList<Map<String, String>>)item.get("filteritems");
/* 166 */       addfilterItems(items, filterKey, this.timeFilter, timeKey);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getAssociatedTimeKey(String timeKey) {
/* 171 */     switch (timeKey) {
/*     */       case "last3Months":
/* 173 */         return "Last 3 months";
/*     */       case "currentMonth":
/* 175 */         return "Current month";
/*     */       case "customDates":
/* 177 */         return "Date range";
/*     */       case "lastMonth":
/* 179 */         return "Last month";
/*     */     } 
/* 181 */     return timeKey.substring(0, 1).toUpperCase() + timeKey.substring(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDeviceFilter(Map<String, Object> cgfApplicableFilters) {
/* 188 */     ArrayList<Map<String, Map<String, String>>> deviceTypesFilter = (ArrayList<Map<String, Map<String, String>>>)((Map)((Map)cgfApplicableFilters.get("filter_devices")).get("devicetypes")).get("filteritems");
/*     */     
/* 190 */     ArrayList<Map<String, Map<String, String>>> manufactureNamefilters = (ArrayList<Map<String, Map<String, String>>>)((Map)((Map)cgfApplicableFilters.get("filter_devices")).get("manufacturenames")).get("filteritems");
/* 191 */     ArrayList<String> deviceList = new ArrayList<String>();
/* 192 */     ArrayList<String> manufacturerList = new ArrayList<>(); Iterator<Map<String, Map<String, String>>> iterator;
/* 193 */     for (iterator = deviceTypesFilter.iterator(); iterator.hasNext(); ) {
/* 194 */       Map<String, Map<String, String>> device = iterator.next();
/* 195 */       deviceList.add(((String)((Map)device.get("filtervalue")).get("DeviceType")).toString());
/*     */     } 
/* 197 */     iterator = manufactureNamefilters.iterator();
/* 198 */     while (iterator.hasNext()) {
/* 199 */       Map<String, Map<String, String>> manufacturer = iterator.next();
/* 200 */       manufacturerList.add(((String)((Map)manufacturer.get("filtervalue")).get("ManufacturerName")).toString());
/*     */     } 
/* 202 */     this.deviceFilter.put("Device types", deviceList);
/* 203 */     this.deviceFilter.put("Manufacturer name", manufacturerList);
/*     */   }
/*     */ 
/*     */   
/*     */   private void setLocationFilter(Map<String, Object> cgfApplicableFilters) {
/* 208 */     Map locationFilters = (Map)cgfApplicableFilters.get("filter_location");
/*     */     
/* 210 */     ArrayList<Map<String, String>> areas = (ArrayList<Map<String, String>>)((Map)locationFilters.get("area")).get("filteritems");
/*     */     
/* 212 */     ArrayList<Map<String, String>> locations = (ArrayList<Map<String, String>>)((Map)locationFilters.get("city")).get("filteritems");
/*     */     
/* 214 */     ArrayList<Map<String, String>> regions = (ArrayList<Map<String, String>>)((Map)locationFilters.get("region")).get("filteritems");
/* 215 */     String filterKey = "displaylabel";
/* 216 */     addfilterItems(areas, filterKey, this.locationFilter, "Areas");
/* 217 */     addfilterItems(locations, filterKey, this.locationFilter, "Cities");
/* 218 */     addfilterItems(regions, filterKey, this.locationFilter, "Regions");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSubscriberFilter(Map<String, Object> cgfApplicableFilters) {
/* 223 */     Map subscriberFilters = (Map)cgfApplicableFilters.get("filter_subscribers");
/*     */     
/* 225 */     ArrayList<Map<String, String>> corpSubGroups = (ArrayList<Map<String, String>>)((Map)subscriberFilters.get("corpsubgroups")).get("filteritems");
/*     */     
/* 227 */     ArrayList<Map<String, String>> subscriptionTypes = (ArrayList<Map<String, String>>)((Map)subscriberFilters.get("subscriptiontypes")).get("filteritems");
/*     */     
/* 229 */     ArrayList<Map<String, String>> corporations = (ArrayList<Map<String, String>>)((Map)subscriberFilters.get("corporations")).get("filteritems");
/*     */     
/* 231 */     ArrayList<Map<String, String>> segments = (ArrayList<Map<String, String>>)((Map)subscriberFilters.get("segments")).get("filteritems");
/* 232 */     String filterKey = "displaylabel";
/* 233 */     addfilterItems(corpSubGroups, filterKey, this.subscriberFilter, "Corporation subgroups");
/* 234 */     addfilterItems(subscriptionTypes, filterKey, this.subscriberFilter, "Subscription types");
/* 235 */     addfilterItems(corporations, filterKey, this.subscriberFilter, "Corporations");
/* 236 */     addfilterItems(segments, filterKey, this.subscriberFilter, "Segments");
/*     */   }
/*     */ 
/*     */   
/*     */   private void setRoamingCategoryFilter(Map<String, Object> cgfApplicableFilters) {
/* 241 */     Map roamingCategoryFilters = (Map)cgfApplicableFilters.get("filter_roamingcategory");
/*     */     
/* 243 */     ArrayList<Map<String, String>> home = (ArrayList<Map<String, String>>)((Map)roamingCategoryFilters.get("home")).get("filteritems");
/*     */     
/* 245 */     ArrayList<Map<String, String>> partnerOperators = (ArrayList<Map<String, String>>)((Map)roamingCategoryFilters.get("partneroperators")).get("filteritems");
/*     */     
/* 247 */     ArrayList<Map<String, String>> inboundRoamers = (ArrayList<Map<String, String>>)((Map)roamingCategoryFilters.get("inboundroamers")).get("filteritems");
/*     */     
/* 249 */     ArrayList<Map<String, String>> outboundRoamers = (ArrayList<Map<String, String>>)((Map)roamingCategoryFilters.get("outboundroamers")).get("filteritems");
/* 250 */     String filterKey = "displaylabel";
/* 251 */     addfilterItems(home, filterKey, this.roamingCategoryFilter, "Home");
/* 252 */     addfilterItems(inboundRoamers, filterKey, this.roamingCategoryFilter, "Inbound roamers");
/* 253 */     addfilterItems(partnerOperators, filterKey, this.roamingCategoryFilter, "Partner operators");
/*     */     
/* 255 */     addfilterItems(outboundRoamers, filterKey, this.roamingCategoryFilter, "Outbound roamers");
/*     */   }
/*     */ 
/*     */   
/*     */   private void addfilterItems(ArrayList<Map<String, String>> filterList, String filterKey, Map<String, ArrayList<String>> resultMap, String mapKey) {
/* 260 */     ArrayList<String> resultlist = new ArrayList<>();
/* 261 */     for (Iterator<Map<String, String>> iterator = filterList.iterator(); iterator.hasNext(); ) {
/* 262 */       Map<String, String> item = iterator.next();
/* 263 */       resultlist.add(((String)item.get(filterKey)).toString());
/*     */     } 
/* 265 */     resultMap.put(mapKey, resultlist);
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\cgf\CGFPageFilters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */