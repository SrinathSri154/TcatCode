/*    */ package com.nokia.cemod.cct.core.widget.wrappers;
/*    */ 
/*    */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*    */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*    */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*    */ import com.nokia.cemod.cct.core.widget.settings.TreeViewSettings;
/*    */ import com.nokia.cemod.cct.utils.WebUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.assertj.core.util.Strings;
/*    */ import org.openqa.selenium.By;
/*    */ import org.openqa.selenium.WebDriver;
/*    */ import org.openqa.selenium.WebElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TreeView
/*    */   extends CCTRenderedWidget
/*    */ {
/*    */   private TreeViewSettings settings;
/* 22 */   private static final Logger logger = Logger.getLogger(TreeView.class);
/*    */   
/*    */   public TreeView(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/* 25 */     super(webdriver, widgetConfiguration);
/* 26 */     this.settings = (TreeViewSettings)widgetConfiguration.getSettings();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/* 31 */     return new ArrayList();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getClickableElementForValue(String value) throws CCTException {
/* 36 */     WebElement clickTreeViewElement = null;
/* 37 */     WebUtils.findElementBy(this.webdriver, 
/* 38 */         By.xpath("//*[@id='expand-collapse-button-" + this.widgetConfiguration.getId() + "']")).click();
/* 39 */     if ((!this.settings.isCheckBoxEnabled() && !this.settings.isSearchEnabled()) || (this.settings
/* 40 */       .isCheckBoxEnabled() && !this.settings.isSearchEnabled())) {
/* 41 */       clickTreeViewElement = getCheckBoxElementWithoutSearchbyText(this.widgetConfiguration.getId(), value);
/* 42 */     } else if (this.settings.isSearchEnabled() && !Strings.isNullOrEmpty(value)) {
/* 43 */       WebUtils.waitAndSendKeysForElementBy(this.webdriver, 
/* 44 */           By.xpath(".//input[@id='treeview-search-" + this.widgetConfiguration.getId() + "']"), value);
/* 45 */       clickTreeViewElement = getElementbyText(this.widgetConfiguration.getId(), value);
/*    */     } 
/* 47 */     return clickTreeViewElement;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private WebElement getCheckBoxElementWithoutSearchbyText(String widgetID, String value) {
/* 53 */     List<WebElement> elementsList = this.webdriver.findElements(By.xpath(".//*[@id='attribute-tree-" + widgetID + "']//span[text() = '" + value + "']"));
/* 54 */     if (elementsList.size() > 0) {
/* 55 */       return elementsList.get(0);
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public WebElement getElementbyText(String widgetID, String value) {
/* 62 */     List<WebElement> elementsList = this.webdriver.findElements(
/* 63 */         By.xpath(".//*[@id='attribute-tree-" + widgetID + "']//*[span[text() = '" + value + "']]"));
/* 64 */     if (elementsList.size() > 0) {
/* 65 */       return elementsList.get(0);
/*    */     }
/* 67 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\TreeView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */