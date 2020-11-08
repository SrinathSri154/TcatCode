/*    */ package com.nokia.cemod.cct.logger.renderingTime;
/*    */ 
/*    */ import java.util.StringJoiner;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderingTimeHeaders
/*    */ {
/* 10 */   private static final Logger logger = Logger.getLogger(RenderingTimeHeaders.class);
/*    */   
/*    */   public static void log() {
/* 13 */     StringJoiner headers = new StringJoiner("#");
/* 14 */     headers.add("Logger Timestamp");
/* 15 */     headers.add("Usecase Name");
/* 16 */     headers.add("Widget ID");
/* 17 */     headers.add("Rendering Time in milliseconds");
/* 18 */     headers.add("Description");
/* 19 */     logger.info(headers.toString());
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\renderingTime\RenderingTimeHeaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */