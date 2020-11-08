/*     */ package com.nokia.cemod.cct.core.widget.wrappers;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.configuration.WidgetConfiguration;
/*     */ import com.nokia.cemod.cct.core.widget.CCTRenderedWidget;
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.settings.EditableTableSettings;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.WebUtils;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.TimeZone;
/*     */ import org.assertj.core.api.BooleanAssert;
/*     */ import org.openqa.selenium.By;
/*     */ import org.openqa.selenium.WebDriver;
/*     */ import org.openqa.selenium.WebElement;
/*     */ import org.openqa.selenium.interactions.Actions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditableTable
/*     */   extends CCTRenderedWidget
/*     */ {
/*     */   private EditableTableSettings settings;
/*     */   private WebElement tableRow;
/*  28 */   List<WebElement> clickableCells = new ArrayList<WebElement>();
/*     */   
/*     */   public EditableTable(WebDriver webdriver, WidgetConfiguration widgetConfiguration) {
/*  31 */     super(webdriver, widgetConfiguration);
/*  32 */     this.settings = (EditableTableSettings)widgetConfiguration.getSettings();
/*     */   }
/*     */ 
/*     */   
/*     */   public void processWidget() throws CCTException {
/*  37 */     super.processWidget();
/*  38 */     this.tableRow = getRowTable();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Object> getMinimumClickableElementsForWorkFlow() throws CCTException {
/*  43 */     if (this.settings.isBulkUpdate().equalsIgnoreCase("true")) {
/*  44 */       getAllClickableElements();
/*     */     } else {
/*  46 */       getClickableElementsBasedOnCellType();
/*     */     } 
/*  48 */     return new ArrayList(this.clickableCells);
/*     */   }
/*     */   
/*     */   private void getAllClickableElements() {
/*  52 */     for (WebElement row : findNumberofTableRows()) {
/*  53 */       this.clickableCells.add(row);
/*     */     }
/*     */   }
/*     */   
/*     */   private void getClickableElementsBasedOnCellType() {
/*  58 */     String selectableType = this.settings.getSelectableType();
/*  59 */     if (selectableType.equalsIgnoreCase("cell")) {
/*  60 */       addClickableCellsForCellType();
/*     */     } else {
/*  62 */       addClickableCellsForRowType();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addClickableCellsForRowType() {
/*  67 */     if (findNumberofTableRows().size() > 0) {
/*  68 */       WebElement firstRow = findNumberofTableRows().get(0);
/*  69 */       this.clickableCells.add(firstRow);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addClickableCellsForCellType() {
/*  74 */     if (findNumberofTableRows().size() > 0) {
/*  75 */       WebElement firstRow = findNumberofTableRows().get(0);
/*  76 */       List<WebElement> allCells = firstRow.findElements(By.tagName("td"));
/*  77 */       for (WebElement cell : allCells) {
/*  78 */         String visibility = cell.getCssValue("display");
/*  79 */         if (cell.getAttribute("class").contains("clickable-cell") && !visibility.equalsIgnoreCase("none")) {
/*  80 */           this.clickableCells.add(cell);
/*     */         }
/*     */       } 
/*     */       
/*  84 */       WebElement checkBoxCell = this.webdriver.findElement(By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "']//tbody/tr[1]/td/child::*"));
/*  85 */       if (checkBoxCell != null) {
/*  86 */         getAllClickableElements();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<WebElement> findNumberofTableRows() {
/*  92 */     return this.tableRow.findElements(By.xpath(".//tbody/tr"));
/*     */   }
/*     */   
/*     */   private WebElement getRowTable() {
/*  96 */     return this.widgetWrapper.findElements(By.tagName("table")).get(1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateValueForClickableIndex(String value, int colIndex, int rowIndex) throws CCTException {
/* 101 */     WebUtils.executeJS(this.webdriver, "$('#" + this.widgetConfiguration.getId() + "_active_cell').trigger('click');");
/*     */     
/* 103 */     WebElement activeCell = this.webdriver.findElement(By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "_active_cell']/child::*"));
/* 104 */     if (activeCell.getAttribute("class").contains("k-textbox")) {
/* 105 */       saveDataForTextBoxColumnType(value, colIndex, rowIndex);
/* 106 */     } else if (activeCell.getAttribute("class").contains("k-dropdown")) {
/* 107 */       saveDataForSingleSelectDropDownColType(value, colIndex, rowIndex);
/* 108 */     } else if (activeCell.getAttribute("class").contains("k-numerictextbox")) {
/* 109 */       saveDataForNumericColType(value, colIndex, rowIndex);
/* 110 */     } else if (activeCell.getAttribute("class").contains("k-multiselect")) {
/* 111 */       saveDataForMultiSelectColType(value, colIndex, rowIndex);
/* 112 */     } else if (activeCell.getAttribute("class").contains("chkbx")) {
/* 113 */       updateValueForCheckBox(value, activeCell);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateValueForCheckBox(String value, WebElement activeCell) throws CCTException {
/* 118 */     boolean flag = Boolean.parseBoolean(activeCell.getAttribute("checked"));
/* 119 */     if (value.equalsIgnoreCase("true")) {
/* 120 */       if (!flag) {
/* 121 */         activeCell.click();
/*     */       }
/*     */     }
/* 124 */     else if (flag) {
/* 125 */       activeCell.click();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void saveDataForMultiSelectColType(String value, int colIndex, int rowIndex) throws CCTException {
/* 132 */     String[] multipleValues = null;
/* 133 */     if (value != null && value.length() > 0) {
/* 134 */       multipleValues = value.split(",");
/*     */     }
/* 136 */     WebUtils.executeJS(this.webdriver, "$($('#" + this.widgetConfiguration
/* 137 */         .getId() + "_active_cell .k-i-close')[0]).trigger('click')");
/* 138 */     for (String values : multipleValues) {
/* 139 */       WebUtils.executeJS(this.webdriver, "$($('#" + this.widgetConfiguration.getId() + "_active_cell .k-multiselect input')[1]).trigger('mousedown');");
/*     */       
/* 141 */       List<WebElement> obj = (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $('.k-list-scroller').find('li:contains(" + values + ")').trigger('click');");
/*     */       
/* 143 */       WebUtils.waitForWidgetsToRender();
/* 144 */       if (obj != null && obj.size() < 1) {
/* 145 */         throw new CCTException("Unable to Find Element : " + values + " in  Multiselect !!");
/*     */       }
/* 147 */       updateDataSource(values, colIndex, rowIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void saveDataForNumericColType(String value, int colIndex, int rowIndex) throws CCTException {
/* 152 */     String output = (String)WebUtils.executeJS(this.webdriver, "$('#" + this.widgetConfiguration
/* 153 */         .getId() + "_active_cell').trigger('click'); var output = $($('#" + this.widgetConfiguration
/* 154 */         .getId() + "_active_cell .k-numerictextbox input')[1]).data('kendoNumericTextBox').value('" + value + "');return $($('#" + this.widgetConfiguration
/*     */         
/* 156 */         .getId() + "_active_cell .k-numerictextbox input')[1]).attr('aria-valuenow')");
/*     */     
/* 158 */     WebUtils.waitForWidgetsToRender();
/* 159 */     if (output == null) {
/* 160 */       throw new CCTException("String values  : " + value + " are not allowed in  Numeric Text Box !!");
/*     */     }
/* 162 */     updateDataSource(value, colIndex, rowIndex);
/*     */   }
/*     */ 
/*     */   
/*     */   private void saveDataForSingleSelectDropDownColType(String value, int colIndex, int rowIndex) throws CCTException {
/* 167 */     WebUtils.executeJS(this.webdriver, "$('#" + this.widgetConfiguration
/* 168 */         .getId() + "_active_cell .k-dropdown').trigger('click');");
/* 169 */     WebUtils.executeJS(this.webdriver, "$('#" + this.widgetConfiguration.getId() + "_active_cell .k-dropdown input').data('kendoDropDownList').value('" + value + "');");
/*     */     
/* 171 */     List<WebElement> obj = (List<WebElement>)WebUtils.executeJS(this.webdriver, "return $('.k-list-scroller .k-state-selected').trigger('click')");
/*     */     
/* 173 */     WebUtils.waitForWidgetsToRender();
/* 174 */     if (obj != null && obj.size() < 1) {
/* 175 */       throw new CCTException("Unable to Find Element : " + value + " in  Drop Down !!");
/*     */     }
/* 177 */     updateDataSource(value, colIndex, rowIndex);
/*     */   }
/*     */   
/*     */   private void saveDataForTextBoxColumnType(String value, int colIndex, int rowIndex) throws CCTException {
/* 181 */     WebUtils.waitAndSendKeysForElementBy(this.webdriver, 
/* 182 */         By.xpath("//*[@id='" + this.widgetConfiguration.getId() + "_active_cell']/descendant::input[1]"), value);
/* 183 */     updateDataSource(value, colIndex, rowIndex);
/*     */   }
/*     */   
/*     */   private void updateDataSource(String value, int colIndex, int rowIndex) throws CCTException {
/* 187 */     List<WebElement> colHeader = this.webdriver.findElements(By.xpath("//thead/tr/th[contains(@class,'k-with-icon')]"));
/* 188 */     WebElement headerName = colHeader.get(colIndex);
/* 189 */     String fieldName = headerName.getAttribute("data-field");
/* 190 */     WebUtils.executeJS(this.webdriver, "var data = $('#" + this.widgetConfiguration
/* 191 */         .getId() + "').data('kendoGrid').dataSource.data()[" + rowIndex + "];data['" + fieldName + "'] = '" + value + "'");
/*     */   }
/*     */ 
/*     */   
/*     */   public void clickOnSave(String widgetId) throws CCTException {
/* 196 */     WebUtils.executeJS(this.webdriver, "$('#" + widgetId + " .k-grid-save-changes').trigger('click')");
/*     */   }
/*     */   
/*     */   public void clickOnCancel(String widgetId) throws CCTException {
/* 200 */     WebUtils.executeJS(this.webdriver, "$('#" + widgetId + " .k-grid-cancel-changes').trigger('click')");
/*     */   }
/*     */   
/*     */   public void validateSaveButtonMessage(String message) throws CCTException {
/* 204 */     WebUtils.waitAndAssertForElementBy(this.webdriver, By.className("customMessage"), message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void performAction(String widgetId, String action, String value) throws CCTException {
/* 209 */     Actions actions = new Actions(this.webdriver);
/* 210 */     WebElement rowElement = getRowElement(widgetId, value);
/* 211 */     List<WebElement> colElement = rowElement.findElements(By.className("k-command-cell"));
/* 212 */     if (action.equalsIgnoreCase("Delete")) {
/* 213 */       WebElement elementToClick = ((WebElement)colElement.get(0)).findElement(By.className("k-grid-Delete"));
/* 214 */       WebUtils.scrollToElementAndCenter(this.webdriver, elementToClick);
/* 215 */       actions.moveToElement(elementToClick).click().perform();
/* 216 */       WebUtils.executeJS(this.webdriver, "$('#yesButton').trigger('click')");
/* 217 */     } else if (action.equalsIgnoreCase("Publish")) {
/* 218 */       WebElement elementToClick = ((WebElement)colElement.get(0)).findElement(By.className("k-grid-Export"));
/* 219 */       WebUtils.scrollToElementAndCenter(this.webdriver, elementToClick);
/* 220 */       actions.moveToElement(elementToClick).click().perform();
/*     */     } else {
/* 222 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 223 */         .as("Only Publish and Delete are allowed, " + action + "is not allowed", new Object[0])).isEqualTo(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private WebElement getRowElement(String widgetId, String value) throws CCTException {
/* 229 */     WebElement rowElement = (WebElement)WebUtils.executeJS(this.webdriver, "var navigatePage = true; var row; while(navigatePage) {var nextPage = $('#" + widgetId + " .k-pager-wrap').find('[title=\"Go to the next page\"]'); if(! $(nextPage).attr('class').includes('k-state-disabled')) {row = $('#" + widgetId + " .k-grid-content').find('td:contains(" + value + ")').parent()[0]; if(row) {navigatePage=false;} else{$(nextPage).trigger('click');}} else {row = $('#" + widgetId + " .k-grid-content').find('td:contains(" + value + ")').parent()[0]; navigatePage=false;}}; return row;");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     return rowElement;
/*     */   }
/*     */   
/*     */   public void searchValue(String value, String widgetId, boolean exists) throws CCTException {
/* 242 */     WebElement colElement = getRowElement(widgetId, value);
/* 243 */     if (colElement == null && exists) {
/* 244 */       ((BooleanAssert)CCTSoftAssertions.getInstance().assertThat(false)
/* 245 */         .as(this.widgetConfiguration.getId() + " Search Text:" + value + " not available", new Object[0])).isEqualTo(true);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clickOnRefresh(String widgetId) throws CCTException {
/* 250 */     WebUtils.executeJS(this.webdriver, "$('#" + widgetId + " .k-grid-refresh').trigger('click')");
/*     */   }
/*     */ 
/*     */   
/*     */   public void performActionforEditableColumns(String widgetId, String rowValue, int colIndex, String value) throws CCTException {
/* 255 */     WebElement rowElement = getRowElement(widgetId, rowValue);
/* 256 */     rowElement.click();
/* 257 */     updateValueForClickableIndex(value, colIndex, 0);
/*     */   }
/*     */   
/*     */   public void addRecord(String widgetId, String values) throws CCTException {
/* 261 */     WebUtils.executeJS(this.webdriver, "$('#" + widgetId + " .k-grid-add').trigger('click')");
/* 262 */     List<WebElement> colElements = WebUtils.findElementsBy(this.webdriver, 
/* 263 */         By.xpath("//*[@id='" + widgetId + "']//tr[contains(@class,'k-grid-edit-row')]//td"));
/* 264 */     String[] columns = values.split(";");
/* 265 */     for (String col : columns) {
/* 266 */       int rowIndex = Integer.parseInt(col.split("-")[0]);
/* 267 */       int colIndex = Integer.parseInt(col.split("-")[1]);
/* 268 */       String colValue = col.split("-")[2];
/* 269 */       ((WebElement)colElements.get(colIndex)).click();
/* 270 */       updateValueForClickableIndex(colValue, colIndex, rowIndex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void verifyTimeZone(String widgetId, String timeZone, String date) throws CCTException {
/* 275 */     Date newDate = new Date(Long.parseLong(date));
/* 276 */     TimeZone zone = TimeZone.getTimeZone(timeZone);
/* 277 */     SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, YYYY hh:mm:ss a");
/* 278 */     sdf.setTimeZone(zone);
/* 279 */     String finalDate = sdf.format(newDate);
/* 280 */     finalDate = finalDate.replace("AM", "am");
/* 281 */     finalDate = finalDate.replace("PM", "pm");
/* 282 */     searchValue(finalDate, widgetId, true);
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\core\widget\wrappers\EditableTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */