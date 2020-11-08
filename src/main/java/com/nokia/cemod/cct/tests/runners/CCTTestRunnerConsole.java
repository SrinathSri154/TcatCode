/*     */ package com.nokia.cemod.cct.tests.runners;
/*     */ 
/*     */ import com.nokia.cemod.cct.utils.conf.TestConfiguration;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.FileVisitResult;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.SimpleFileVisitor;
/*     */ import java.nio.file.attribute.BasicFileAttributes;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import org.apache.commons.exec.CommandLine;
/*     */ import org.apache.commons.exec.DefaultExecutor;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCTTestRunnerConsole
/*     */ {
/*     */   public static void main(String[] args) throws Exception {
/*  23 */     String nodesToRun = TestConfiguration.getProperty("cct.parallelExecution.nodes");
/*     */     
/*  25 */     int numberOfRounds = Integer.parseInt(TestConfiguration.getProperty("cct.stability.numberOfRounds", "1"));
/*     */     
/*  27 */     Path outputDirectory = Paths.get("output", new String[0]);
/*  28 */     deleteDirectory(outputDirectory);
/*  29 */     if (nodesToRun != null) {
/*  30 */       String[] nodes = nodesToRun.split(",");
/*  31 */       for (String node : nodes) {
/*  32 */         String FEATURES_DIRECTORY = "output/" + node + "/" + "features";
/*     */ 
/*     */         
/*  35 */         Files.createDirectories(outputDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         
/*  37 */         Path nodeDirectory = Paths.get("output/" + node, new String[0]);
/*  38 */         Files.createDirectories(nodeDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         
/*  40 */         Path featuresDirectory = Paths.get(FEATURES_DIRECTORY, new String[0]);
/*  41 */         Files.createDirectories(featuresDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*     */ 
/*     */         
/*  44 */         String[] sourceDirectoriesAndFolders = TestConfiguration.getProperty(String.format("%s.features", new Object[] { node })).split(",");
/*  45 */         for (String sourceFileorFolderName : sourceDirectoriesAndFolders) {
/*  46 */           Path sourcePath = Paths.get(sourceFileorFolderName, new String[0]);
/*  47 */           if (Files.isDirectory(sourcePath, new java.nio.file.LinkOption[0])) {
/*  48 */             FileUtils.copyDirectory(new File(sourceFileorFolderName), new File(FEATURES_DIRECTORY));
/*     */           } else {
/*  50 */             Files.copy(sourcePath, featuresDirectory.resolve(sourcePath.getFileName()), new java.nio.file.CopyOption[0]);
/*     */           } 
/*     */         } 
/*     */         
/*  54 */         if (numberOfRounds > 1) {
/*  55 */           for (int index = 1; index <= numberOfRounds; index++) {
/*  56 */             String SCREEN_SHOT_DIRECTORY = "output/" + node + "/" + "round" + "-" + index + "/" + "screenshots";
/*     */ 
/*     */             
/*  59 */             Path screenShotDirectory = Paths.get(SCREEN_SHOT_DIRECTORY, new String[0]);
/*  60 */             Files.createDirectories(screenShotDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*     */           } 
/*     */         } else {
/*  63 */           String SCREEN_SHOT_DIRECTORY = "output/" + node + "/" + "screenshots";
/*     */           
/*  65 */           Path screenShotDirectory = Paths.get(SCREEN_SHOT_DIRECTORY, new String[0]);
/*  66 */           Files.createDirectories(screenShotDirectory, (FileAttribute<?>[])new FileAttribute[0]);
/*     */         } 
/*     */         
/*  69 */         Runnable testRunner = TestRunner(node, numberOfRounds);
/*  70 */         Thread testRunnerThread = new Thread(testRunner);
/*  71 */         testRunnerThread.start();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void deleteDirectory(Path directory) throws IOException {
/*  77 */     if (Files.exists(directory, new java.nio.file.LinkOption[0])) {
/*  78 */       Files.walkFileTree(directory, new SimpleFileVisitor<Path>()
/*     */           {
/*     */             public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
/*     */             {
/*  82 */               Files.delete(file);
/*  83 */               return FileVisitResult.CONTINUE;
/*     */             }
/*     */ 
/*     */             
/*     */             public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
/*  88 */               Files.delete(dir);
/*  89 */               return FileVisitResult.CONTINUE;
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   private static Runnable TestRunner(final String node, final int numberOfRounds) {
/*  96 */     Runnable runnable = new Runnable()
/*     */       {
/*  98 */         int currentRound = 1;
/*     */ 
/*     */         
/*     */         public void run() {
/* 102 */           while (this.currentRound <= numberOfRounds) {
/* 103 */             String roundFolderName = "";
/* 104 */             if (numberOfRounds > 1) {
/* 105 */               roundFolderName = "round-" + this.currentRound;
/*     */             }
/*     */             try {
/* 108 */               String line = "";
/* 109 */               if (TestConfiguration.getProperty("cct.run.os").equals("windows")) {
/* 110 */                 line = "java -Dlog4j.configuration=file:\"./conf/log4j.properties\" -DtestEnv=\"standalone\" -cp libs/* com.nokia.cemod.cct.tests.base.CCTTestsExecutor " + node + " " + roundFolderName;
/*     */               
/*     */               }
/* 113 */               else if (TestConfiguration.getProperty("cct.run.os").equals("linux")) {
/* 114 */                 line = "java -Dlog4j.configuration=file:\"./conf/log4j.properties\" -DtestEnv=\"standalone\" -classpath \"libs/*:.\" com.nokia.cemod.cct.tests.base.CCTTestsExecutor " + node + " " + roundFolderName;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 124 */               CommandLine commandLine = CommandLine.parse(line);
/* 125 */               DefaultExecutor executor = new DefaultExecutor();
/* 126 */               executor.setExitValue(1);
/* 127 */               executor.execute(commandLine);
/* 128 */             } catch (Exception exception) {
/*     */ 
/*     */             
/*     */             } finally {
/* 132 */               this.currentRound++;
/* 133 */               run();
/*     */             } 
/*     */           } 
/*     */         }
/*     */       };
/* 138 */     return runnable;
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\runners\CCTTestRunnerConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */