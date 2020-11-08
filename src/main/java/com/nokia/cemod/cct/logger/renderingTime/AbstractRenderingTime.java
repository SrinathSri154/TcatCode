/*    */ package com.nokia.cemod.cct.logger.renderingTime;
/*    */ 
/*    */ import java.text.DateFormat;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.StringJoiner;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractRenderingTime
/*    */ {
/* 13 */   private static final Logger logger = Logger.getLogger(AbstractRenderingTime.class);
/*    */   
/* 15 */   private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
/*    */   
/*    */   private String error;
/*    */   
/*    */   protected abstract String getUseCaseName();
/*    */   
/*    */   protected abstract String getDescription();
/*    */   
/*    */   protected abstract long getStartTime();
/*    */   
/*    */   protected abstract long getEndTime();
/*    */   
/*    */   protected abstract String getWidgetId();
/*    */   
/*    */   public void log() {
/* 30 */     StringJoiner message = new StringJoiner("#");
/* 31 */     message.add(DATE_FORMAT.format(new Date()).toString());
/* 32 */     message.add(getUseCaseName());
/* 33 */     message.add(getWidgetId());
/* 34 */     message.add(((getError() != null) ? getError() : Long.valueOf(getEndTime() - getStartTime())).toString());
/* 35 */     message.add(getDescription());
/* 36 */     logger.info(message.toString());
/*    */   }
/*    */   
/*    */   public String getError() {
/* 40 */     return this.error;
/*    */   }
/*    */   
/*    */   public void setError(String error) {
/* 44 */     this.error = error;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\renderingTime\AbstractRenderingTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */