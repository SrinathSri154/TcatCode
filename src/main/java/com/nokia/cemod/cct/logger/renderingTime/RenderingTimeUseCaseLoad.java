/*    */ package com.nokia.cemod.cct.logger.renderingTime;
/*    */ 
/*    */ import java.util.GregorianCalendar;
/*    */ 
/*    */ public class RenderingTimeUseCaseLoad
/*    */   extends AbstractRenderingTime
/*    */ {
/*  8 */   private String useCaseName = "";
/*    */   
/*    */   private long startTime;
/*    */   
/*    */   private long endTime;
/*    */   
/*    */   public RenderingTimeUseCaseLoad(String useCaseName) {
/* 15 */     this.useCaseName = useCaseName;
/*    */   }
/*    */   
/*    */   public void start() {
/* 19 */     this.startTime = (new GregorianCalendar()).getTimeInMillis();
/*    */   }
/*    */   
/*    */   public void end() {
/* 23 */     this.endTime = (new GregorianCalendar()).getTimeInMillis();
/* 24 */     log();
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getUseCaseName() {
/* 29 */     return this.useCaseName;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getDescription() {
/* 34 */     return "Usecase load time";
/*    */   }
/*    */ 
/*    */   
/*    */   protected long getStartTime() {
/* 39 */     return this.startTime;
/*    */   }
/*    */ 
/*    */   
/*    */   protected long getEndTime() {
/* 44 */     return this.endTime;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getWidgetId() {
/* 50 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\renderingTime\RenderingTimeUseCaseLoad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */