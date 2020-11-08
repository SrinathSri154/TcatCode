/*    */ package com.nokia.cemod.cct.utils;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.junit.Assert;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PortalUtils
/*    */ {
/*    */   public static Map<String, String> getUserDetails() {
/* 18 */     Map<String, String> map = new HashMap<String, String>();
/* 19 */     String RUNNABLE_NODE = System.getProperty("cct.runnable.node");
/* 20 */     String defaultUser = TestConfiguration.getProperty("cct.env.portal.user");
/* 21 */     String defaultUserPassword = TestConfiguration.getProperty("cct.env.portal.password");
/*    */     
/* 23 */     String defaultUserFirstName = TestConfiguration.getProperty("cct.env.portal.user.firstName");
/* 24 */     String defaultUserLastName = TestConfiguration.getProperty("cct.env.portal.user.lastName");
/*    */     
/* 26 */     String portalUser = TestConfiguration.getProperty(String.format("%s.portal.user", new Object[] { RUNNABLE_NODE }), defaultUser);
/* 27 */     String portalUserPassword = TestConfiguration.getProperty(
/* 28 */         String.format("%s", new Object[] { "%s.portal.password", RUNNABLE_NODE }), defaultUserPassword);
/*    */     
/* 30 */     String portalFirstName = TestConfiguration.getProperty(
/* 31 */         String.format("%s.portal.user.firstName", new Object[] { RUNNABLE_NODE }), defaultUserFirstName);
/* 32 */     String portalLastName = TestConfiguration.getProperty(
/* 33 */         String.format("%s.portal.user.lastName", new Object[] { RUNNABLE_NODE }), defaultUserLastName);
/* 34 */     map.put("%s.portal.user", portalUser);
/* 35 */     map.put("%s.portal.password", portalUserPassword);
/* 36 */     map.put("%s.portal.user.firstName", portalFirstName);
/* 37 */     map.put("%s.portal.user.lastName", portalLastName);
/* 38 */     return map;
/*    */   }
/*    */   
/*    */   public static void searchUsecase(String useCase, WebDriver driver) throws CCTException {
/* 42 */     Assert.assertEquals(
/* 43 */         WebUtils.findElementBy(driver, By.xpath(".//*[@id='searchInput1']//input")).getAttribute("placeholder"), "Search...");
/*    */     
/* 45 */     WebUtils.waitAndSendKeysForElementBy(driver, By.xpath(".//*[@id='searchInput1']//input"), useCase);
/* 46 */     WebUtils.waitForWidgetsToRender();
/*    */   }
/*    */   
/*    */   public static void hideDrawerAndSliderPanel(WebDriver driver) throws CCTException {
/* 50 */     WebUtils.executeJS(driver, "$('.drawer').hide()");
/* 51 */     WebUtils.executeJS(driver, "$('#slideout').hide()");
/*    */   }
/*    */   
/*    */   public static String getUseCaseJSON(WebDriver driver) throws CCTException {
/* 55 */     return WebUtils.executeJS(driver, "return cemportalSettings.getJsonByUseCaseURL(cemportalSettings.useCaseURL)")
/* 56 */       .toString();
/*    */   }
/*    */   
/*    */   public static String getChildUseCaseJSON(String useCaseName, WebDriver driver) throws CCTException {
/* 60 */     return 
/* 61 */       WebUtils.executeJS(driver, "return cemportalSettings.getJsonByUseCaseURL(\"" + useCaseName + '"' + ")")
/* 62 */       .toString();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\PortalUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */