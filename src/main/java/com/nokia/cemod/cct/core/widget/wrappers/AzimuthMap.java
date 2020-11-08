/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.Keys;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ import org.openqa.selenium.interactions.Actions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AzimuthMap
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public AzimuthMap(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 21 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 26 */     List<WebElement> circleAndAzimuth = new ArrayList<WebElement>();
/* 27 */     List<WebElement> circles = this.widgetWrapper.findElements(By.tagName("circle"));
/* 28 */     WebElement circle = null;
/* 29 */     if (circles.size() > 0) {
/* 30 */       circle = circles.get(0);
/*    */     } else {
/* 32 */       clickZoomoutButton();
/* 33 */       circles = this.widgetWrapper.findElements(By.tagName("circle"));
/* 34 */       if (circles.size() > 0) {
/* 35 */         circle = circles.get(0);
/*    */       }
/*    */     } 
/* 38 */     if (circle != null) {
/* 39 */       circleAndAzimuth.add(circle);
/* 40 */       List<WebElement> siblings = circle.findElements(By.xpath("following-sibling::*"));
/* 41 */       for (WebElement siblingElement : siblings) {
/* 42 */         if (siblingElement.getTagName().equals("path")) {
/* 43 */           circleAndAzimuth.add(siblingElement);
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 48 */     return new ArrayList(circleAndAzimuth);
/*    */   }
/*    */   
/*    */   private void clickZoomoutButton() throws CCTException {
/* 52 */     for (int i = 0; i <= 5; i++) {
/* 53 */       WebElement zoomOut = WebUtils.findElementBy(this.webdriver, By.id(this.widgetConfiguration.getId()));
/* 54 */       zoomOut.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { (CharSequence)Keys.CONTROL, (CharSequence)Keys.SUBTRACT }) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 60 */     WebElement mapElement = (WebElement)element;
/* 61 */     Actions action = new Actions(this.webdriver);
/* 62 */     action.moveToElement(mapElement).click().perform();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\AzimuthMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */