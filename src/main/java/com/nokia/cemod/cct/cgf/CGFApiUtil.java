/*    */ package com.nokia.cemod.cct.cgf;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Map;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CGFApiUtil
/*    */ {
/*    */   public static Map<String, Object> getAppliedFilters(WebDriver driver) throws CCTException {
/* 16 */     Map<String, Object> appliedFilters = (Map<String, Object>)WebUtils.executeJS(driver, "return CGF.getAppliedFilters()");
/*    */     
/* 18 */     return appliedFilters;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Map<String, Object> getAllApplicableFilterItemsMap(WebDriver driver) throws CCTException {
/* 23 */     Map<String, Object> applicableFilterData = (Map<String, Object>)WebUtils.executeJS(driver, "return CGF.getAllFilterItemsMap()");
/*    */     
/* 25 */     return applicableFilterData;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ArrayList<String> getAllApplicableFilterKeysForCurrentPage(WebDriver driver) throws CCTException {
/* 30 */     ArrayList<String> applicableFilters = (ArrayList<String>)WebUtils.executeJS(driver, "return  CGF.getFiltersApplicableForCurrentPage()");
/*    */     
/* 32 */     return applicableFilters;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\cgf\CGFApiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */