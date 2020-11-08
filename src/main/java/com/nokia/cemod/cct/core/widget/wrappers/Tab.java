/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.TabSettings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.codehaus.jackson.JsonNode;
/*    */ import org.junit.Assert;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Tab
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private TabSettings settings;
/*    */   
/*    */   public Tab(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 24 */     super(webdriver, widgetConfiguration);
/* 25 */     this.settings = (TabSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 30 */     List<WebElement> tabValues = this.widgetWrapper.findElements(By.className("buttonlist"));
/* 31 */     Assert.assertTrue("Button group rendered and configured values does not match: " + this.widgetConfiguration.getId(), 
/*    */         
/* 33 */         (this.widgetWrapper.findElements(By.className("buttonlist")).size() == this.settings.getButtonGroupValues().size()));
/* 34 */     return new ArrayList(tabValues);
/*    */   }

           public Object getClickableElementForValue(String value) throws CCTException {
             List<Object> elements = getMinimumClickableElementsForWorkFlow();
             for (Object element : elements) {
               WebElement webElement = (WebElement)element;
               String tabText = webElement.findElement(By.className("km-text")).getText();
               if (value.equalsIgnoreCase(tabText)) {
                 return webElement;
               }
             }
             throw new CCTException("WebElement Not found");
           }
/*    */   
/*    */   public String getSelectedValue() {
/* 38 */     return ((WebElement)this.widgetWrapper.findElements(By.className("km-state-active")).get(0)).getText();
/*    */   }
/*    */   
/*    */   public String getSelectedUseCaseName() {
/* 42 */     String selectedTabValue = getSelectedValue();
/* 43 */     Iterator<String> fields = this.widgetConfiguration.getSettingsNode().get("useCaseMappingSeries").getFieldNames();
/* 44 */     while (fields.hasNext()) {
/*    */       
/* 46 */       JsonNode serieNode = this.widgetConfiguration.getSettingsNode().get("useCaseMappingSeries").get(((String)fields.next()).toString());
/* 47 */       if (serieNode.get("tabName").asText().equals(selectedTabValue)) {
/* 48 */         return serieNode.get("useCase").asText();
/*    */       }
/*    */     } 
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Tab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */