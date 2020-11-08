/*    */ package com.nokia.cemod.cct.logger;
/*    */ 
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import org.apache.log4j.FileAppender;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomFileAppender
/*    */   extends FileAppender
/*    */ {
/* 13 */   private String timestampFormat = "yyyy-MM-dd_hh-mm-ss";
/*    */ 
/*    */   
/*    */   public void setFile(String fileName) {
/* 17 */     String node = System.getProperty("cct.runnable.node.round", "node1");
/*    */     
/* 19 */     if (fileName.indexOf("%timestamp") >= 0) {
/* 20 */       Date d = new Date();
/* 21 */       SimpleDateFormat format = new SimpleDateFormat(this.timestampFormat);
/* 22 */       fileName = fileName.replaceAll("%timestamp", format.format(d));
/*    */     } 
/*    */     
/* 25 */     if (fileName.indexOf("%node") >= 0) {
/* 26 */       fileName = fileName.replaceAll("%node", "output/" + node);
/*    */     }
/* 28 */     super.setFile(fileName);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\logger\CustomFileAppender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */