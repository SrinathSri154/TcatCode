/*    */ package com.nokia.cemod.cct.tests.base;
/*    */ 
/*    */ import cucumber.runtime.ClassFinder;
/*    */ import cucumber.runtime.Runtime;
/*    */ import cucumber.runtime.RuntimeOptions;
/*    */ import cucumber.runtime.io.MultiLoader;
/*    */ import cucumber.runtime.io.ResourceLoader;
/*    */ import cucumber.runtime.io.ResourceLoaderClassFinder;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class CCTTestsExecutor
/*    */ {
/*    */   public static void main(String[] arguments) throws Exception {
/* 18 */     String nodeName = null;
/* 19 */     String logsOutputPath = null;
/* 20 */     if (arguments.length >= 1) {
/* 21 */       nodeName = arguments[0];
/* 22 */       logsOutputPath = nodeName;
/* 23 */       if (arguments.length >= 2) {
/* 24 */         logsOutputPath = nodeName + "/" + arguments[1];
/*    */       }
/*    */     } else {
/* 27 */       throw new Exception("Do not directly run this program. Use CCTTestRunnerConsole");
/*    */     } 
/*    */ 
/*    */     
/* 31 */     System.setProperty("cct.runnable.node", nodeName);
/* 32 */     System.setProperty("cct.runnable.node.round", logsOutputPath);
/* 33 */     CCTTestsExecutor testRunner = new CCTTestsExecutor();
/* 34 */     String[] configuration = testRunner.getConfiguration(nodeName, logsOutputPath);
/*    */     try {
/* 36 */       testRunner.executetests(configuration);
/* 37 */     } catch (InterruptedException e) {
/* 38 */       e.printStackTrace();
/* 39 */     } catch (IOException e) {
/* 40 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */   
/*    */   private String[] getConfiguration(String nodeName, String logsOutputPath) {
/* 45 */     String FEATURES_FOLDER = "output/" + nodeName + "/" + "features";
/*    */     
/* 47 */     String LOGS_OUTPUT_FOLDER = "output/" + logsOutputPath + "/";
/* 48 */     List<String> arguments = new ArrayList<String>();
/* 49 */     arguments.add("--glue");
/* 50 */     arguments.add("com.nokia.cemod.cct.tests.cucumber.keywords");
/* 51 */     arguments.add("--glue");
/* 52 */     arguments.add("com.nokia.analytics.test.keywords");
/*    */     
/* 54 */     arguments.add(FEATURES_FOLDER);
/* 55 */     arguments.add("--tags");
/* 56 */     arguments.add("~@ignore");
/*    */     
/* 58 */     arguments.add("--plugin");
/* 59 */     arguments.add("pretty");
/* 60 */     arguments.add("--plugin");
/* 61 */     arguments.add("html:" + LOGS_OUTPUT_FOLDER + "reports/html");
/* 62 */     arguments.add("--plugin");
/* 63 */     arguments.add("json:" + LOGS_OUTPUT_FOLDER + "reports/cucumber.json");
/* 64 */     arguments.add("--plugin");
/* 65 */     arguments.add("junit:" + LOGS_OUTPUT_FOLDER + "reports/cucumber.xml");
/* 66 */     String[] argumentsInArray = arguments.<String>toArray(new String[0]);
/* 67 */     return argumentsInArray;
/*    */   }
/*    */   
/*    */   public byte executetests(String[] configuration) throws InterruptedException, IOException {
/* 71 */     RuntimeOptions runtimeOptions = new RuntimeOptions(new ArrayList(Arrays.asList((Object[])configuration)));
/* 72 */     MultiLoader resourceLoader = new MultiLoader(getClass().getClassLoader());
/*    */     
/* 74 */     ResourceLoaderClassFinder classFinder = new ResourceLoaderClassFinder((ResourceLoader)resourceLoader, getClass().getClassLoader());
/*    */     
/* 76 */     Runtime runtime = new Runtime((ResourceLoader)resourceLoader, (ClassFinder)classFinder, getClass().getClassLoader(), runtimeOptions);
/* 77 */     runtime.run();
/* 78 */     return runtime.exitStatus();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\base\CCTTestsExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */