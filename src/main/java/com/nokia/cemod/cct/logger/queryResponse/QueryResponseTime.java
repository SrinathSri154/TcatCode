/*    */ package com.nokia.cemod.cct.logger.queryResponse;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class QueryResponseTime
/*    */ {
/* 12 */   private static final Logger logger = Logger.getLogger(QueryResponseTime.class);
/*    */   
/* 14 */   private String loggerTimestamp = "";
/*    */   
/* 16 */   private String usecaseName = "";
/*    */   
/* 18 */   private String widgetId = "";
/*    */   
/* 20 */   private String widgetTitle = "";
/*    */   
/* 22 */   private String queryStartTime = "";
/*    */   
/* 24 */   private String queryEndTime = "";
/*    */   
/* 26 */   private String queryResponseTime = "";
/*    */   
/* 28 */   private String responseMessage = "";
/*    */   
/* 30 */   private String timestampFormat = "yyyy-MM-dd hh:mm:ss:SSS";
/*    */   
/*    */   public void setLoggerTimestamp(Date date) {
/* 33 */     DateFormat df = new SimpleDateFormat(this.timestampFormat);
/* 34 */     this.loggerTimestamp = df.format(date).toString();
/*    */   }
/*    */   
/*    */   public void setUseCaseName(String usecaseName) {
/* 38 */     this.usecaseName = usecaseName;
/*    */   }
/*    */   
/*    */   public void setWidgetId(String widgetId) {
/* 42 */     this.widgetId = widgetId;
/*    */   }
/*    */   
/*    */   public void setWidgetTitle(String widgetTitle) {
/* 46 */     this.widgetTitle = widgetTitle;
/*    */   }
/*    */   
/*    */   public void setQueryStartTime(long queryStartTime) {
/* 50 */     this.queryStartTime = "NA";
/* 51 */     if (queryStartTime != 0L) {
/* 52 */       Date date = new Date(queryStartTime);
/* 53 */       DateFormat df = new SimpleDateFormat(this.timestampFormat);
/* 54 */       this.queryStartTime = df.format(date).toString();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setQueryEndTime(long queryEndTime) {
/* 59 */     this.queryEndTime = "NA";
/* 60 */     if (queryEndTime != 0L) {
/* 61 */       Date date = new Date(queryEndTime);
/* 62 */       DateFormat df = new SimpleDateFormat(this.timestampFormat);
/* 63 */       this.queryEndTime = df.format(date).toString();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setResponseTime(String queryResponseTime) {
/* 68 */     this.queryResponseTime = queryResponseTime;
/*    */   }
/*    */   
/*    */   public void setResponseMessage(String responseMessage) {
/* 72 */     this.responseMessage = responseMessage;
/*    */   }
/*    */   
/*    */   public void log() {
/* 76 */     StringBuilder message = new StringBuilder();
/* 77 */     setLoggerTimestamp(new Date());
/* 78 */     message.append(this.loggerTimestamp);
/* 79 */     message.append(",");
/* 80 */     message.append(this.usecaseName);
/* 81 */     message.append(",");
/* 82 */     message.append(this.widgetId);
/* 83 */     message.append(",");
/* 84 */     message.append(this.widgetTitle);
/* 85 */     message.append(",");
/* 86 */     message.append(this.queryStartTime);
/* 87 */     message.append(",");
/* 88 */     message.append(this.queryEndTime);
/* 89 */     message.append(",");
/* 90 */     message.append(this.queryResponseTime);
/* 91 */     message.append(",");
/* 92 */     message.append(this.responseMessage);
/* 93 */     logger.info(message.toString());
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\queryResponse\QueryResponseTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */