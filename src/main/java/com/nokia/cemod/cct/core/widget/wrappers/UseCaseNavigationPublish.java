/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.UseCaseNavigationPublishSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UseCaseNavigationPublish
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private UseCaseNavigationPublishSettings settings;
/*    */   
/*    */   public UseCaseNavigationPublish(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 22 */     super(webdriver, widgetConfiguration);
/* 23 */     this.settings = (UseCaseNavigationPublishSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 28 */     if (this.settings.isPopUp()) {
/* 29 */       ArrayList<Object> clickableElements = new ArrayList();
/* 30 */       WebElement window = WebUtils.waitAndFindElementBy(this.webdriver, By.className("k-window"));
/* 31 */       Object windowClose = window.findElement(By.xpath("//div[1]/div[1]/a[4]"));
/* 32 */       clickableElements.add(windowClose);
/* 33 */       return clickableElements;
/*    */     } 
/* 35 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 41 */     WebElement webElement = (WebElement)element;
/* 42 */     webElement.click();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\UseCaseNavigationPublish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */