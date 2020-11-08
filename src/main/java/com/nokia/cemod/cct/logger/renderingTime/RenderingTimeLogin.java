/*    */ package com.nokia.cemod.cct.logger.renderingTime;
/*    */ 
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class RenderingTimeLogin
/*    */   extends AbstractRenderingTime
/*    */ {
/*  8 */   private String user = "";
/*    */   
/* 10 */   private String useCaseName = "";
/*    */   
/*    */   private long startTime;
/*    */   
/*    */   private long endTime;
/*    */   
/*    */   public RenderingTimeLogin(String user, String useCaseName) {
/* 17 */     this.user = user;
/* 18 */     this.useCaseName = useCaseName;
/*    */   }
/*    */   
/*    */   public void start() {
/* 22 */     this.startTime = (new GregorianCalendar()).getTimeInMillis();
/*    */   }
/*    */   
/*    */   public void end() {
/* 26 */     this.endTime = (new GregorianCalendar()).getTimeInMillis();
/* 27 */     log();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getUseCaseName() {
/* 32 */     return this.useCaseName;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getDescription() {
/* 37 */     return "Login time for user ~ " + this.user;
/*    */   }
/*    */ 
/*    */   
/*    */   protected long getStartTime() {
/* 42 */     return this.startTime;
/*    */   }
/*    */ 
/*    */   
/*    */   protected long getEndTime() {
/* 47 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getWidgetId() {
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\renderingTime\RenderingTimeLogin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */