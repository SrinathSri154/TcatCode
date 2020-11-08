/*    */ package com.nokia.cemod.cct.tests.runners;
/*    */ 
/*    */ import cucumber.runtime.ClassFinder;
/*    */ import cucumber.runtime.Runtime;
/*    */ import cucumber.runtime.RuntimeOptions;
/*    */ import cucumber.runtime.io.MultiLoader;
/*    */ import cucumber.runtime.io.ResourceLoader;
/*    */ import cucumber.runtime.io.ResourceLoaderClassFinder;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.FileVisitResult;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.nio.file.SimpleFileVisitor;
/*    */ import java.nio.file.attribute.BasicFileAttributes;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ReplaceKeywordsRunner
/*    */ {
/*    */   public static void main(String[] args) throws Exception {
/* 24 */     replacingKeyWord();
/*    */   }
/*    */   
/*    */   private static void replacingKeyWord() {
/*    */     try {
/* 29 */       String FEATURES_DIRECTORY = "replacekeywords/features";
/*    */       
/* 31 */       Path outputDirectory = Paths.get("replacekeywords/", new String[0]);
/* 32 */       deleteDirectory(outputDirectory);
/*    */       
/* 34 */       Path nodeDirectory = Paths.get("replacekeywords", new String[0]);
/* 35 */       Files.createDirectories(nodeDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*    */       
/* 37 */       Path featuresDirectory = Paths.get(FEATURES_DIRECTORY, new String[0]);
/* 38 */       Files.createDirectories(featuresDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/* 39 */       Path sourcePath = Paths.get("utilities/ReplaceKeywords.feature", new String[0]);
/* 40 */       Files.copy(sourcePath, featuresDirectory.resolve(sourcePath.getFileName()), new java.nio.file.CopyOption[0]);
/* 41 */       String FEATURES_FOLDER = "replacekeywords/features";
/* 42 */       String LOGS_OUTPUT_FOLDER = "replacekeywords/logs/";
/* 43 */       List<String> arguments = new ArrayList<String>();
/* 44 */       arguments.add("--glue");
/* 45 */       arguments.add("com.nokia.cemod.cct.tests.cucumber.keywords.utilities");
/*    */       
/* 47 */       arguments.add("replacekeywords/features");
/* 48 */       arguments.add("--tags");
/* 49 */       arguments.add("~@ignore");
/*    */       
/* 51 */       arguments.add("--plugin");
/* 52 */       arguments.add("pretty");
/* 53 */       arguments.add("--plugin");
/* 54 */       arguments.add("html:" + LOGS_OUTPUT_FOLDER + "reports/html");
/* 55 */       arguments.add("--plugin");
/* 56 */       arguments.add("json:" + LOGS_OUTPUT_FOLDER + "reports/cucumber.json");
/* 57 */       arguments.add("--plugin");
/* 58 */       arguments.add("junit:" + LOGS_OUTPUT_FOLDER + "reports/cucumber.xml");
/* 59 */       String[] argumentsInArray = arguments.<String>toArray(new String[0]);
/* 60 */       ReplaceKeywordsRunner test = new ReplaceKeywordsRunner();
/* 61 */       test.executetests(argumentsInArray);
/* 62 */     } catch (Exception exception) {
/*    */     
/*    */     } finally {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static void deleteDirectory(Path directory) throws IOException {
/* 70 */     if (Files.exists(directory, new java.nio.file.LinkOption[0])) {
/* 71 */       Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
/*    */           {
/*    */             public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
/*    */             {
/* 75 */               Files.delete(file);
/* 76 */               return FileVisitResult.CONTINUE;
/*    */             }
/*    */ 
/*    */             
/*    */             public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
/* 81 */               Files.delete(dir);
/* 82 */               return FileVisitResult.CONTINUE;
/*    */             }
/*    */           });
/*    */     }
/*    */   }
/*    */   
/*    */   public void executetests(String[] configuration) throws InterruptedException, IOException {
/* 89 */     RuntimeOptions runtimeOptions = new RuntimeOptions(new ArrayList(Arrays.asList((Object[])configuration)));
/* 90 */     MultiLoader resourceLoader = new MultiLoader(getClass().getClassLoader());
/*    */     
/* 92 */     ResourceLoaderClassFinder classFinder = new ResourceLoaderClassFinder((ResourceLoader)resourceLoader, getClass().getClassLoader());
/*    */     
/* 94 */     Runtime runtime = new Runtime((ResourceLoader)resourceLoader, (ClassFinder)classFinder, getClass().getClassLoader(), runtimeOptions);
/* 95 */     runtime.run();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\runners\ReplaceKeywordsRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */