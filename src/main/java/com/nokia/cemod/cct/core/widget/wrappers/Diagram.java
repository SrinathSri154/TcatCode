/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ import org.openqa.selenium.interactions.Actions;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Diagram
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   public Diagram(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 20 */     super(webdriver, widgetConfiguration);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 25 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/* 26 */     WebElement diagram = this.widgetWrapper.findElement(By.cssSelector("#" + this.widgetConfiguration.getId() + " > div > div.km-scroll-container > div > svg > g > g:nth-child(1)"));
/*    */     
/* 28 */     clickableCells = diagram.findElements(By.xpath("./*[@*[contains(.,'matrix')]]"));
/* 29 */     return new ArrayList(clickableCells);
/*    */   }
/*    */ 
/*    */   
/*    */   public void click(Object element) throws CCTException {
/* 34 */     WebElement diagramBlock = (WebElement)element;
/* 35 */     WebElement diagramElement = WebUtils.findElementBy(this.webdriver, 
/* 36 */         By.xpath(".//div[@id='" + this.widgetConfiguration.getId() + "']"));
/* 37 */     WebUtils.scrollToElementAndCenter(this.webdriver, diagramElement);
/* 38 */     Actions action = new Actions(this.webdriver);
/* 39 */     action.moveToElement(diagramBlock).click().perform();
/* 40 */     WebUtils.waitForWidgetsToRender();
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Diagram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */