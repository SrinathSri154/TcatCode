/*    */ package com.nokia.cemod.cct.core.widget.exception;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ public class CCTException
/*    */   extends Exception
/*    */ {
/*  8 */   private static final Logger logger = Logger.getLogger(CCTException.class);
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public CCTException(String msg) {
/* 13 */     super(msg);
/* 14 */     logger.error("CCTException, " + msg);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\exception\CCTException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */