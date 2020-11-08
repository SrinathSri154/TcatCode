/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Hyperlink
/*    */   extends CCTRenderedWidget
/*    */ {
/* 18 */   private static final Logger logger = Logger.getLogger(Hyperlink.class);
/*    */   
/*    */   public Hyperlink(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 21 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 26 */     List<WebElement> hyperLinks = new ArrayList<WebElement>();
/*    */     
/* 28 */     WebElement hyperLink = this.widgetWrapper.findElement(By.cssSelector("#" + this.widgetConfiguration.getId() + " > a"));
/* 29 */     if (hyperLink != null) {
/* 30 */       hyperLinks.add(hyperLink);
/*    */     }
/* 32 */     logger.debug("Hyperlink clickable elements size :" + hyperLinks.size());
/* 33 */     return new ArrayList(hyperLinks);
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Hyperlink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */