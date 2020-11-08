/*    */ package com.nokia.cemod.cct.pages;
/*    */
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.logger.renderingTime.RenderingTimeUseCaseLoad;
/*    */ import com.nokia.cemod.cct.selenium.SharedDriver;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.net.URL;
/*    */ import java.net.URLConnection;
/*    */ import org.apache.commons.io.IOUtils;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CCTDevelopmentPage
/*    */   extends CCTBasePage
/*    */ {
/* 20 */   private static final Logger logger = Logger.getLogger(CCTDevelopmentPage.class);
/*    */   
/* 22 */   private static String TAB_CHILD_USECASE_1 = "uc-hvci";
/*    */   
/* 24 */   private static String TAB_CHILD_USECASE_2 = "uc-ott";
/*    */   
/* 26 */   private static String TAB_CHILD_USECASE_3 = "uc-ri";
/*    */   
/* 28 */   private static String TAB_CHILD_USECASE_4 = "uc-oth";
/*    */   
/*    */   public CCTDevelopmentPage(SharedDriver driver) {
/* 31 */     super(driver);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void launchUseCase(String useCaseName) {
/* 36 */     RenderingTimeUseCaseLoad logRenderTime = new RenderingTimeUseCaseLoad(useCaseName);
/* 37 */     logRenderTime.start();
/*    */     
/* 39 */     String useCaseLaunchURL = TestConfiguration.getProperty("cct.env.development.useCaseLaunchURL");
/* 40 */     this.driver.get(String.format(useCaseLaunchURL, new Object[] { useCaseName }));
/*    */     try {
/* 42 */       WebUtils.waitForUseCaseToLoad(this.driver);
/* 43 */       setUseCase(useCaseName);
/* 44 */       validateWidgetsRenderedToFindRenderTime();
/* 45 */     } catch (CCTException e) {
/* 46 */       logger.error("Unable to find usecase render time: " + e.getMessage());
/* 47 */       logRenderTime.setError(e.getMessage());
/*    */     } 
/* 49 */     logRenderTime.end();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getUseCaseJSON(String useCaseName) throws CCTException {
/* 55 */     String useCaseJsonURL = getUseCaseLaunchUrl(useCaseName);
/*    */     try {
/* 57 */       URL url = new URL(String.format(useCaseJsonURL, new Object[] { useCaseName }));
/* 58 */       URLConnection connection = url.openConnection();
/* 59 */       InputStream in = connection.getInputStream();
/* 60 */       String encoding = connection.getContentEncoding();
/* 61 */       encoding = (encoding == null) ? "UTF-8" : encoding;
/* 62 */       return IOUtils.toString(in, encoding);
/* 63 */     } catch (IOException e) {
/* 64 */       throw new CCTException("Exception  in getUseCaseJSON " + e.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getChildUseCaseJSON(String useCaseName) throws CCTException {
/* 70 */     return getUseCaseJSON(useCaseName);
/*    */   }
/*    */   
/*    */   private String getUseCaseLaunchUrl(String useCaseName) {
/* 74 */     String useCaseJsonURL = null;
/* 75 */     if (useCaseName.equals(TAB_CHILD_USECASE_1)) {
/*    */       
/* 77 */       useCaseJsonURL = TestConfiguration.getProperty("cct.env.development.useCaseLaunchURL.tabUseCase1");
/* 78 */     } else if (useCaseName.equals(TAB_CHILD_USECASE_2)) {
/*    */       
/* 80 */       useCaseJsonURL = TestConfiguration.getProperty("cct.env.development.useCaseLaunchURL.tabUseCase2");
/* 81 */     } else if (useCaseName.equals(TAB_CHILD_USECASE_3)) {
/*    */       
/* 83 */       useCaseJsonURL = TestConfiguration.getProperty("cct.env.development.useCaseLaunchURL.tabUseCase3");
/* 84 */     } else if (useCaseName.equals(TAB_CHILD_USECASE_4)) {
/*    */       
/* 86 */       useCaseJsonURL = TestConfiguration.getProperty("cct.env.development.useCaseLaunchURL.tabUseCase4");
/*    */     } else {
/* 88 */       useCaseJsonURL = TestConfiguration.getProperty("cct.env.development.useCaseJsonURL");
/*    */     } 
/* 90 */     return useCaseJsonURL;
/*    */   }
/*    */   
/*    */   public void forceLogout() throws CCTException {}
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\pages\CCTDevelopmentPage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */