/*    */ package com.nokia.cemod.cct.utils.conf;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScenarioConfiguration
/*    */ {
/*    */   private static boolean testAllPulldownCombinationsEnabled = false;
/*  8 */   private static String scenarioName = "";
/*    */   
/*    */   public static String getScenarioName() {
/* 11 */     return scenarioName;
/*    */   }
/*    */   
/*    */   public static void setScenarioName(String scenarioName) {
/* 15 */     ScenarioConfiguration.scenarioName = scenarioName;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void clear() {
/* 22 */     testAllPulldownCombinationsEnabled = false;
/* 23 */     scenarioName = "";
/*    */   }
/*    */   
/*    */   public static boolean isTestAllPulldownCombinationsEnabled() {
/* 27 */     return testAllPulldownCombinationsEnabled;
/*    */   }
/*    */   
/*    */   public static void setTestAllPulldownCombinations(boolean testAllPulldownCombinations) {
/* 31 */     testAllPulldownCombinationsEnabled = testAllPulldownCombinations;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\conf\ScenarioConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */