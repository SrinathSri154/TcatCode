/*    */ package com.nokia.cemod.cct.utils;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplaceKeyword
/*    */ {
/* 14 */   private static ReplaceKeyword replaceKeyword = new ReplaceKeyword();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ReplaceKeyword getInstance() {
/* 20 */     return replaceKeyword;
/*    */   }
/*    */   
/*    */   public void modifyFile(String filePath, String oldString, String newString) throws CCTException {
/* 24 */     File fileToBeModified = new File(filePath);
/* 25 */     String oldContent = "";
/* 26 */     BufferedReader reader = null;
/* 27 */     FileWriter writer = null;
/*    */     try {
/* 29 */       reader = new BufferedReader(new FileReader(fileToBeModified));
/*    */       
/* 31 */       String line = reader.readLine();
/* 32 */       while (line != null) {
/* 33 */         oldContent = oldContent + line + System.lineSeparator();
/* 34 */         line = reader.readLine();
/*    */       } 
/* 36 */       if (oldContent.contains(oldString)) {
/*    */         
/* 38 */         String newContent = oldContent.replaceAll(oldString, newString);
/*    */         
/* 40 */         writer = new FileWriter(fileToBeModified);
/* 41 */         writer.write(newContent);
/*    */       } else {
/* 43 */         System.out.println("Keyword " + oldString + " not found in file " + filePath);
/*    */       } 
/* 45 */     } catch (IOException e) {
/* 46 */       throw new CCTException("Filename not found" + e.getMessage());
/*    */     } finally {
/*    */       
/*    */       try {
/* 50 */         if (oldContent.contains(oldString)) {
/* 51 */           reader.close();
/* 52 */           writer.close();
/*    */         } 
/* 54 */       } catch (IOException e) {
/* 55 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void listFolders(String directoryName, String keyWord, String stringToBeReplaced) throws CCTException {
/* 61 */     File directory = new File(directoryName);
/*    */     
/* 63 */     if ((directory.listFiles()).length > 0)
/* 64 */     { File[] fList = directory.listFiles();
/* 65 */       for (File file : fList) {
/* 66 */         if (file.isFile()) {
/* 67 */           modifyFile(file.getAbsolutePath(), keyWord, stringToBeReplaced);
/* 68 */         } else if (file.isDirectory()) {
/* 69 */           listFolders(file.getAbsolutePath(), keyWord, stringToBeReplaced);
/*    */         } 
/*    */       }  }
/* 72 */     else { System.out.println("DirectoryName " + directoryName + " is empty. Please check either directory name is incorrect or error in copying files in the directory"); }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cc\\utils\ReplaceKeyword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */