/*    */ package com.nokia.cemod.cct.tests.cucumber.keywords.utilities;
/*    */ 
/*    */ import com.nokia.cemod.cct.utils.ReplaceKeyword;
/*    */ import cucumber.api.java.en.Given;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Utilities
/*    */ {
/*    */   @Given("^Select Directory as \"([^\"]*)\" replace keyword \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*    */   public void select_Directory_as_replace_keyword_with_value_as(String arg1, String arg2, String arg3) throws Throwable {
/* 13 */     ReplaceKeyword.getInstance().listFolders(arg1, arg2, arg3);
/*    */   }
/*    */ 
/*    */   
/*    */   @Given("^Select Filename as \"([^\"]*)\" replace keyword \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*    */   public void select_Filename_as_replace_keyword_with_value_as(String arg1, String arg2, String arg3) throws Throwable {
/* 19 */     ReplaceKeyword.getInstance().modifyFile(arg1, arg2, arg3);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\cucumber\keyword\\utilities\Utilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */