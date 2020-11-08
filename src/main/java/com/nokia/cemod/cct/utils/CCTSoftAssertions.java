/*    */ package com.nokia.cemod.cct.utils;
/*    */ 
/*    */ import org.assertj.core.api.SoftAssertions;
/*    */ 
/*    */ public class CCTSoftAssertions
/*    */   extends SoftAssertions
/*    */ {
/*  8 */   private static CCTSoftAssertions cctSoftAssertions = new CCTSoftAssertions();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CCTSoftAssertions getInstance() {
/* 14 */     return cctSoftAssertions;
/*    */   }
/*    */   
/*    */   public void clearAssertions() {
/* 18 */     cctSoftAssertions = new CCTSoftAssertions();
/*    */   }
/*    */ 
/*    */   
/*    */   public void assertAll() {
/* 23 */     super.assertAll();
/* 24 */     clearAssertions();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\CCTSoftAssertions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */