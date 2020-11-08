/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.Keys;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ import org.openqa.selenium.interactions.Actions;
/*    */ import org.openqa.selenium.support.ui.ExpectedConditions;
/*    */ import org.openqa.selenium.support.ui.WebDriverWait;
/*    */ 
/*    */ 
/*    */ public class Map
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public Map(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 24 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 29 */     clickZoomoutButton();
/* 30 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/* 31 */     List<WebElement> mapBubbles = getMapBubbles();
/* 32 */     if (mapBubbles.size() > 0) {
/* 33 */       clickableCells.add(mapBubbles.get(0));
/*    */     }
/* 35 */     return new ArrayList(clickableCells);
/*    */   }
/*    */   
/*    */   private List<WebElement> getMapBubbles() {
/* 39 */     return this.widgetWrapper.findElements(By.tagName("circle"));
/*    */   }
/*    */   
/*    */   private void clickZoomoutButton() throws CCTException {
/* 43 */     for (int i = 0; i <= 5; i++) {
/* 44 */       WebElement zoomOut = WebUtils.findElementBy(this.webdriver, By.id(this.widgetConfiguration.getId()));
/* 45 */       (new WebDriverWait(this.webdriver, 10L)).until((Function)ExpectedConditions.elementToBeClickable(zoomOut));
/* 46 */       zoomOut.sendKeys(new CharSequence[] { Keys.chord(new CharSequence[] { (CharSequence)Keys.CONTROL, (CharSequence)Keys.SUBTRACT }) });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 52 */     WebElement firstBubble = (WebElement)element;
/* 53 */     WebUtils.scrollToElementAndCenter(this.webdriver, firstBubble);
/* 54 */     Actions action = new Actions(this.webdriver);
/* 55 */     action.moveToElement(firstBubble).click().perform();
/*    */   }
/*    */   
/*    */   public void validateMapProvider(String provider) throws CCTException {
/* 59 */     String result = "invalid";
/* 60 */     if (provider.equals("Google Map")) {
/*    */       
/* 62 */       result = WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "']/div[2]/div")).getText();
/* 63 */     } else if (provider.equals("OpenStreetMap")) {
/*    */       
/* 65 */       result = WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "']/div[2]/div/a")).getText();
/*    */     } 
/* 67 */     CCTSoftAssertions.getInstance().assertThat(true).isEqualTo(result.contains(provider));
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Map.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */