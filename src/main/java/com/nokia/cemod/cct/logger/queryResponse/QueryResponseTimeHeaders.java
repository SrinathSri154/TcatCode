/*    */ package com.nokia.cemod.cct.logger.queryResponse;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class QueryResponseTimeHeaders
/*    */ {
/*  8 */   private static final Logger logger = Logger.getLogger(QueryResponseTimeHeaders.class);
/*    */   
/*    */   public static void log() {
/* 11 */     StringBuilder headers = new StringBuilder();
/* 12 */     headers.append("Logger Timestamp");
/* 13 */     headers.append(",");
/* 14 */     headers.append("Usecase Name");
/* 15 */     headers.append(",");
/* 16 */     headers.append("Widget Id");
/* 17 */     headers.append(",");
/* 18 */     headers.append("Widget Title");
/* 19 */     headers.append(",");
/* 20 */     headers.append("Query Start Time");
/* 21 */     headers.append(",");
/* 22 */     headers.append("Query End Time");
/* 23 */     headers.append(",");
/* 24 */     headers.append("Response Time in milliseconds");
/* 25 */     headers.append(",");
/* 26 */     headers.append("Records received");
/* 27 */     logger.info(headers.toString());
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\queryResponse\QueryResponseTimeHeaders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */