/*    */ package com.nokia.cemod.cct.utils;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CGFFilters
/*    */ {
/* 17 */   public static Map<String, String> multiSelectIds = new HashMap<String, String>(); static {
/* 18 */     multiSelectIds.put("Regions", "cgf-region");
             multiSelectIds.put("State", "cgf-region");
/* 19 */     multiSelectIds.put("Cities", "cgf-city");
             multiSelectIds.put("Cluster", "cgf-city");
/* 20 */     multiSelectIds.put("Areas", "cgf-area");
/* 21 */     multiSelectIds.put("APNs", "cgf-apns");
/* 22 */     multiSelectIds.put("Manufacturer names", "cgf-manufacturenames");
             multiSelectIds.put("Device types", "cgf-devicetypes");
/* 23 */     multiSelectIds.put("Device models", "cgf-devicemodels");
/* 24 */     multiSelectIds.put("Device capabilities", "cgf-devicecapabilities");
/* 25 */     multiSelectIds.put("Segments", "cgf-segments");
/* 26 */     multiSelectIds.put("Segment subcategory", "cgf-subcategory");
/* 27 */     multiSelectIds.put("Corporations", "cgf-corporations");
/* 28 */     multiSelectIds.put("Corporation subgroups", "cgf-corpsubgroups");
/* 29 */     multiSelectIds.put("Subscriber group names", "cgf-subscriptiongroup");
/* 31 */     multiSelectIds.put("Partner operators", "cgf-partneroperators");
/* 32 */     multiSelectIds.put("Aggregation Level 1", "cgf-aggnodelevel1");
/* 33 */     multiSelectIds.put("Aggregation Level 2", "cgf-aggnodelevel2");
/* 34 */     multiSelectIds.put("Subscription plan A", "cgf-subscriptionplanA");
/* 35 */     multiSelectIds.put("Subscription plan B", "cgf-subscriptionplanB");
/* 36 */     multiSelectIds.put("SDS", "cgf-cableaggnodelevel2");
/* 37 */     multiSelectIds.put("CMTS", "cgf-cableaggnodelevel1");
/* 38 */     multiSelectIds.put("Access Node", "cgf-cableaccessnode");
/* 39 */     multiSelectIds.put("CopperFiberBNG", "cgf-copperfiberaggnodelevel2");
/* 40 */     multiSelectIds.put("Copper DSLAM2", "cgf-copperaggnodelevel1");
/* 41 */     multiSelectIds.put("Copper DSLAM1", "cgf-copperaccessnode");
/* 42 */     multiSelectIds.put("Fiber OLT", "cgf-fiberaggnodelevel1");
/* 43 */     multiSelectIds.put("Fiber ONU", "cgf-fiberaccessnode");
/* 44 */     multiSelectIds.put("Subscription types", "cgf-substype");
/* 45 */   } public static Map<String, String> multiSelectCheckBoxIds = new HashMap<String, String>(); static {
/* 46 */     multiSelectCheckBoxIds.put("Subscription types", "subscriptiontypes-cgf-options-table");
/* 47 */     multiSelectCheckBoxIds.put("Plans", "plans-cgf-options-table");
/* 48 */     multiSelectCheckBoxIds.put("Home", "home-cgf-options-table");
/* 49 */     multiSelectCheckBoxIds.put("Inbound roamers", "inboundroamers-cgf-options-table");
/* 50 */     multiSelectCheckBoxIds.put("Outbound roamers", "outboundroamers-cgf-options-table");
/* 51 */   } public static Map<String, String> radioCheckBoxIds = new HashMap<String, String>(); static {
/* 52 */     radioCheckBoxIds.put("Premium subscribers", "premiumsubscriber-cgf-options-table");
/* 53 */   } public static Map<String, String> timeFilterSelectIds = new HashMap<String,String>(); static {
/* 54 */     timeFilterSelectIds.put("Recent", "RECENT_");
/* 55 */     timeFilterSelectIds.put("Hour", "HOURS_");
/* 56 */     timeFilterSelectIds.put("Week", "WEEKS_");
/* 57 */     timeFilterSelectIds.put("Month", "MONTHS_");
/* 58 */     timeFilterSelectIds.put("Current month", "CURRENT_MONTH_");
/* 59 */     timeFilterSelectIds.put("Last month", "LAST_MONTH_");
/* 60 */     timeFilterSelectIds.put("Last 3 months", "LAST_3_MONTHS_");
/*    */   }
/*    */   
/*    */   public static final String TIME = "Time";
/*    */   public static final String TIME_RECENT = "Recent";
/*    */   public static final String TIME_HOUR = "Hour";
/*    */   public static final String TIME_DAY = "Day";
/*    */   public static final String TIME_WEEK = "Week";
/*    */   public static final String TIME_MONTH = "Month";
/*    */   public static final String TIME_CURRENTMONTH = "Current month";
/*    */   public static final String TIME_LASTMONTH = "Last month";
/*    */   public static final String TIME_LAST3MONTHS = "Last 3 months";
/*    */   public static final String TIME_CUSTOMDATES = "Date range";
/*    */   public static final String TECHNOLOGY = "Technologies";
/*    */   public static final String TECHNOLOGIES_2G = "2G";
/*    */   public static final String TECHNOLOGIES_3G = "3G";
/*    */   public static final String TECHNOLOGIES_4G = "4G";
/*    */   public static final String LOCATIONS = "Locations";
/*    */   public static final String LOCATION_REGIONS = "Regions";
/*    */   public static final String LOCATION_CITIES = "Cities";
/*    */   public static final String LOCATION_STATE = "Regions";
/*    */   public static final String LOCATION_CLUSTER = "Cities";
/*    */   public static final String LOCATION_AREAS = "Areas";
/*    */   public static final String APNS = "APNs";
/*    */   public static final String SUBSCRIBERS = "Subscribers";
/*    */   public static final String SUBSCRIBERS_SEGMENTS = "Segments";
/*    */   public static final String SUBSCRIBERS_CORPORATIONS = "Corporations";
/*    */   public static final String SUBSCRIBERS_CORPORATIONS_SUB_GROUPS = "Corporation subgroups";
/*    */   public static final String SUBSCRIBERS_SUBSCRIPTION_TYPES = "Subscription types";
/*    */   public static final String SUBSCRIBERS_SUBSCRIPTIONPLAN_A = "Subscription plan A";
/*    */   public static final String SUBSCRIBERS_SUBSCRIPTIONPLAN_B = "Subscription plan B";
/*    */   public static final String SUBSCRIBERS_SUBSCRIPTION_GROUP_NAME = "Subscription group name";
/*    */   public static final String SUBSCRIBERS_SEGMENT_SUBCATEGORY = "Segment subcategory";
/*    */   public static final String SUBSCRIBERS_PREMIUM = "Premium subscribers";
/*    */   public static final String DEVICES = "Devices";
/*    */   public static final String DEVICES_MANUFACTURER_NAME = "Manufacturer name";
/*    */   public static final String DEVICES_TYPES = "Device types";
/*    */   public static final String DEVICES_MODELS = "Device models";
/*    */   public static final String DEVICES_CAPABILITY = "Device Capability";
/*    */   public static final String NETWORK_TOPOLOGIES = "Network Topologies";
/*    */   public static final String NETWORK_TOPOLOGIES_AGGREGATION_LEVEL_1 = "Aggregation Level 1";
/*    */   public static final String NETWORK_TOPOLOGIES_AGGREGATION_LEVEL_2 = "Aggregation Level 2";
/*    */   public static final String ROAMING_CATEGORY = "Roaming category";
/*    */   public static final String ROAMING_CATEGORY_HOME = "Home";
/*    */   public static final String ROAMING_CATEGORY_PARTNER_OPERATORS = "Partner operators";
/*    */   public static final String ROAMING_CATEGORY_INBOUND_ROAMERS = "Inbound roamers";
/*    */   public static final String ROAMING_CATEGORY_OUTBOUND_ROAMERS = "Outbound roamers";
/*    */   public static final String PREFERENCES = "Preferences";
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\CGFFilters.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */