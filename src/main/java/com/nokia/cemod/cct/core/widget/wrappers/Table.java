/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.TableSettings;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Table
/*     */   extends CCTRenderedWidget
/*     */ {
/*     */   private WebElement tableHeader;
/*     */   private WebElement tableRow;
/*     */   private TableSettings settings;
/*     */   
/*     */   public Table(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  27 */     super(webdriver, widgetConfiguration);
/*  28 */     this.settings = (TableSettings)widgetConfiguration.getSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public void processWidget() throws CCTException {
/*  33 */     super.processWidget();
/*  34 */     this.tableHeader = getHeaderTable();
/*  35 */     this.tableRow = getRowTable();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*  41 */     List<WebElement> clickableCells = new ArrayList<WebElement>();
/*  42 */     List<WebElement> numberOfTableRows = findNumberofTableRows();
/*  43 */     String selectableType = this.settings.getSelectableType();
/*  44 */     if (selectableType.equalsIgnoreCase("cell")) {
/*  45 */       if (numberOfTableRows.size() > 0) {
/*  46 */         WebElement firstRow = findNumberofTableRows().get(0);
/*  47 */         List<WebElement> allCells = firstRow.findElements(By.tagName("td"));
/*  48 */         for (WebElement cell : allCells) {
/*  49 */           String visibility = cell.getCssValue("display");
/*  50 */           if (cell.getAttribute("class").contains("clickable-cell") && !visibility.equalsIgnoreCase("none")) {
/*  51 */             clickableCells.add(cell);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*  56 */     } else if (numberOfTableRows.size() > 0) {
/*  57 */       WebElement firstRow = findNumberofTableRows().get(0);
/*  58 */       clickableCells.add(firstRow);
/*     */     } 
/*  61 */     return new ArrayList(clickableCells);
/*     */   }
/*     */   
/*     */   private WebElement getHeaderTable() {
/*  65 */     return this.widgetWrapper.findElements(By.tagName("table")).get(0);
/*     */   }
/*     */   
/*     */   private WebElement getRowTable() {
/*  69 */     return this.widgetWrapper.findElements(By.tagName("table")).get(1);
/*     */   }
/*     */   
/*     */   private List<WebElement> findTableHeaderElements() {
/*  73 */     return this.tableHeader.findElements(By.xpath(".//thead/tr[last()]/th"));
/*     */   }
/*     */   
/*     */   private List<WebElement> findNumberofTableRows() {
/*  77 */     return this.tableRow.findElements(By.xpath(".//tbody/tr"));
/*     */   }
/*     */   
/*     */   public int getNumberOfColumns() {
/*  81 */     return findTableHeaderElements().size();
/*     */   }
/*     */   
/*     */   public int getNumberOfRows() {
/*  85 */     return findNumberofTableRows().size();
/*     */   }
/*     */   
/*     */   public WebElement findTableCell(int row, int column) {
///*  89 */     List<WebElement> rowCells = ((WebElement)findNumberofTableRows().get(row - 1)).findElements(By.tagName("td"));
                List<WebElement> rowCells = ((WebElement)findNumberofTableRows().get(row - 1)).findElements(By.xpath(".//td[not(contains(@style,'display'))]"));
/*  90 */     return rowCells.get(column - 1);
/*     */   }
/*     */   
/*     */   public String getTableCellText(int row, int column) {
/*  94 */     return findTableCell(row, column).getText();
/*     */   }
/*     */   
/*     */   public List<String> getTableColumnsNames() throws InterruptedException, CCTException {
/*  98 */     List<String> columnNames = new ArrayList<String>();
              int headerElement = getNumberOfColumns();
/*  99 */     for (int i = 0; i < headerElement; i++) {
//                WebUtils.scrollToElementAndCenter(this.webdriver, (WebElement)findTableHeaderElements().get(i));
                List<WebElement> elements = ((WebElement)findTableHeaderElements().get(i)).findElements(By.xpath(".//a[contains(@class, 'k-link')]"));
                if(elements.size()>0) {
                    columnNames.add(elements.get(0).getAttribute("text").trim());
                }
/*     */     }
/* 103 */     return columnNames;
/*     */   }
/*     */   
/*     */   public WebElement getExportToExcelElement() {
/* 107 */     return this.widgetWrapper.findElement(By.linkText("Export to Excel"));
/*     */   }
/*     */
           public void clickTableCell(int row, int column) throws CCTException {
               boolean temp=true;
               WebElement cell;
               int n=getNumberOfRows();
               if(n>10)
                   n=10;
               for(int i=row; i<=n; i++) {
                   cell = findTableCell(i, column);
                   String cellValue = cell.getText();
                   if (!cellValue.trim().equalsIgnoreCase("0")) {
                       WebUtils.scrollToElementAndCenter(this.webdriver, cell);
                       cell.click();
                       temp = false;
                       break;
                   }
               }
               if(temp) {
                   cell = findTableCell(row, column);
                   WebUtils.scrollToElementAndCenter(this.webdriver, cell);
                   cell.click();
               }
           }

/*     */   
/*     */   public void validateExportButtonIsRendered(String widgetId, String permission, String action) {
/* 116 */     if (permission.equals("ALLOW_EXPORT")) {
/* 117 */       validateForExportToExcel(widgetId, action);
/*     */     } else {
/* 119 */       validateForExportDB(widgetId, action);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void validateForExportDB(String widgetId, String action) {
/* 124 */     if (action.equals("true")) {
/*     */       try {
/* 126 */         WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']/div[1]/a[2]"));
/* 127 */       } catch (CCTException e) {
/* 128 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Export to DB button not found", new Object[0])).isEqualTo(true);
/*     */       } 
/*     */     } else {
/*     */       try {
/* 132 */         WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']/div[1]/a[2]"));
/* 133 */       } catch (CCTException e) {
/* 134 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(true).as("Export to DB button found", new Object[0])).isEqualTo(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void validateForExportToExcel(String widgetId, String action) {
/* 140 */     if (action.equals("true")) {
/*     */       try {
/* 142 */         WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']/div[1]/a[1]"));
/* 143 */       } catch (CCTException e) {
/* 144 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false).as("Export to excel button not found", new Object[0]))
/* 145 */           .isEqualTo(true);
/*     */       } 
/*     */     } else {
/*     */       try {
/* 149 */         WebUtils.waitAndFindElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']/div[1]/a[1]"));
/* 150 */       } catch (CCTException e) {
/* 151 */         ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(true).as("Export to excel button found", new Object[0])).isEqualTo(true);
/*     */       } 
/*     */     } 
/*     */   }

        public void validateExportDB(String widgetId) throws CCTException {
            try {
                String tblRowXpath = "//table[@id='exportFilesGrid']/tbody/tr[1]";
                WebUtils.waitAndClickForElementBy(this.webdriver, By.xpath("//*[@id='" + widgetId + "']/div[1]/a[2]"));
                LocalDateTime myDateObj = LocalDateTime.now();
                Thread.sleep(8000);
                WebUtils.waitAndClickForElementBy(this.webdriver, By.xpath("//*[@class='tabs-container']/ul/li/a/span[text()='Generated Files']"));
//                WebUtils.waitAndClickForElementBy(this.webdriver, By.xpath("//div[@id='refreshContent']/a"));
                Thread.sleep(8000);
                WebElement element = WebUtils.waitAndFindElementBy(this.webdriver, By.xpath(tblRowXpath+"/td[2]"));
                String filename = element.getText().trim();
                String temp = filename.substring(filename.length()-27, filename.length()-11);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
                String formattedDate = myDateObj.format(myFormatObj);
                String status = WebUtils.waitAndFindElementBy(this.webdriver, By.xpath(tblRowXpath+"/td[3]")).getText();
                WebUtils.waitAndClickForElementBy(this.webdriver, By.xpath("//*[@class='tabs-container']/ul/li/a/span[text()='Generated Files']"));
                if(temp.equalsIgnoreCase(formattedDate)) {
                    if (status.equalsIgnoreCase("Queued") || status.equalsIgnoreCase("Running")
                            || status.equalsIgnoreCase("Completed"))
                        System.out.println("Export successful");
                    else
                        System.out.println("Export failed");
                }
                else
                    throw new CCTException("Export from DB failed");
            } catch (CCTException | InterruptedException e) {
                throw new CCTException("Exception in validateExportDB: " + e.getMessage());
            }
        }

        public void checkTableHeaderValue(String widgetId, String values) throws CCTException {
            try {
                String[] options = values.split("\\s*,\\s*");
                StringBuilder temp = new StringBuilder();
                int count = 0;
                List<String> headers = getTableColumnsNames();
                for (String option : options) {
                    if (!headers.contains(option))
                        temp.append(option).append(", ");
                    else
                        count++;
                }
                if(!(count == options.length))
                    throw new CCTException("Columns not present in table: " + temp.substring(0,temp.length()-2));
                else
                    System.out.println("Columns are present in "+widgetId);
            }catch (CCTException | InterruptedException e) {
               throw new CCTException("Exception in checkTableHeaderValue: " + e.getMessage());
            }
        }

}


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\Table.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */