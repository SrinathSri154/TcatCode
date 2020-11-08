/*     */ package com.nokia.cemod.cct.tests.cucumber.keywords;
/*     */ 
/*     */ import com.nokia.cemod.cct.core.widget.exception.CCTException;
/*     */ import com.nokia.cemod.cct.core.widget.wrappers.*;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ import com.nokia.cemod.cct.tests.base.CCTBaseTest;
/*     */ import com.nokia.cemod.cct.utils.CCTSoftAssertions;
/*     */ import com.nokia.cemod.cct.utils.conf.ScenarioConfiguration;
/*     */ import cucumber.api.Scenario;
/*     */ import cucumber.api.java.After;
/*     */ import cucumber.api.java.Before;
/*     */ import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
/*     */ import cucumber.api.java.en.Then;
/*     */ import cucumber.api.java.en.When;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CCTHandler
/*     */   extends CCTBaseTest
/*     */ {
/*  26 */   private static final Logger logger = Logger.getLogger(CCTException.class);
/*     */   
/*     */   @Before
/*     */   public void beforeScenario(Scenario scenario) {
/*  30 */     logger.debug("Start Scenario: " + scenario.getName());
/*  31 */     ScenarioConfiguration.setScenarioName(scenario.getName());
/*  32 */     clearCCTPageInstance();
/*  33 */     clearCGFFiltersForGivenFeature();
/*  34 */     CCTSoftAssertions.getInstance().clearAssertions();
/*     */   }
/*     */   
/*     */   @After
/*     */   public void afterScenario(Scenario scenario) throws CCTException {
/*  39 */     getCCTPage().forceLogout();
///*  40 */     CCTSoftAssertions.getInstance().assertAll();
///*  41 */     clearCGFFiltersForGivenFeature();
/*  42 */     clearCCTPageInstance();
/*  43 */     ScenarioConfiguration.clear();
/*  44 */     logger.debug("End Scenario: " + scenario.getName());
/*     */   }
/*     */   
/*     */   @Given("^Load and validate use-case \"([^\"]*)\"$")
/*     */   public void load_and_validate_use_case(String useCaseName) throws Throwable {
/*  49 */     getCCTPage().loadAndValidateUseCase(useCaseName);
/*  50 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Given("^Load use-case \"([^\"]*)\"$")
/*     */   public void load_use_case(String useCaseName) throws Throwable {
/*  55 */     getCCTPage().loadUseCase(useCaseName);
/*  56 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @When("^Click on widget \"([^\"]*)\"$")
/*     */   public void click_on_widget(String widgetId) throws Throwable {
/*  61 */     getCCTPage().clickOnWidget(widgetId, 0);
/*     */     
// /*  63 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @When("^Click on widget \"([^\"]*)\" \"([^\"]*)\"$")
/*     */   public void click_on_widget(String widgetId, int clickableElementIndex) throws Throwable {
/*  68 */     getCCTPage().clickOnWidget(widgetId, clickableElementIndex);
// /*  69 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Given("^Click on widget \"([^\"]*)\" value \"([^\"]*)\"$")
/*     */   public void click_on_widget_value(String widgetId, String value) throws Throwable {
/*  74 */     getCCTPage().clickOnWidget(widgetId, value);
/*  75 */  //   CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Widget should be rendered \"([^\"]*)\"$")
/*     */   public void widget_should_be_rendered(String widgetId) throws Throwable {
/*  80 */     getCCTPage().isWidgetRendered(widgetId);
/*  81 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Widget should not be rendered \"([^\"]*)\"$")
/*     */   public void widget_should_not_be_rendered(String widgetId) throws Throwable {
/*  86 */     getCCTPage().isWidgetNotRendered(widgetId);
/*  87 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Given("^Test all pulldown values$")
/*     */   public void test_all_pulldown_values() throws Throwable {
/*  92 */     ScenarioConfiguration.setTestAllPulldownCombinations(true);
/*     */   }
/*     */ 
/*     */   
/*     */   @Then("^Validate Apply Button workflow for all input widget values in order \"([^\"]*)\"$")
/*     */   public void validate_Apply_Button_workflow_for_all_input_widget_values_in_order(String applyButtonId) throws Throwable {
/*  98 */     getCCTPage().validateApplyButtonWorkFlowForAllWidgetValues(applyButtonId);
/*     */   }
/*     */ 
/*     */   
/*     */   @Then("^Validate Apply Button workflow \"([^\"]*)\" for all possible combinations of widget values \"([^\"]*)\"$")
/*     */   public void validate_Apply_Button_workflow_for_all_possible_combinations_of_widget_values(String applyButtonId, String widgetIdsSeperatedByComma) throws Throwable {
/* 104 */     String[] widgetIds = new String[0];
/* 105 */     if (widgetIdsSeperatedByComma != null) {
/* 106 */       widgetIds = widgetIdsSeperatedByComma.split(",");
/*     */     }
/* 108 */     getCCTPage().validateApplyButtonWorkFlowForAllCombinationsOfWidgetValues(applyButtonId, widgetIds);
/*     */   }
/*     */   
/*     */   @Then("^Validate workflow of the widget \"([^\"]*)\"$")
/*     */   public void validate_workflow_of_the_widget(String widgetId) throws Throwable {
/* 113 */     getCCTPage().validateWorkFlow(widgetId);
// /* 114 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate workflow$")
/*     */   public void validate_workflow() throws Throwable {
/* 119 */     getCCTPage().validateUseCase();
/* 120 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate widgets rendered$")
/*     */   public void validate_widgets_rendered() throws Throwable {
/* 125 */     getCCTPage().validateWidgetsRendered();
/*     */   }
/*     */ 
/*     */   
/*     */   @Then("^Validate widget \"([^\"]*)\" for a row \"([^\"]*)\" and column \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void validateEditableTableData(String widgetId, int rowIndex, int colIndex, String value) throws CCTException {
/* 131 */     getCCTPage().clickOnWidget(widgetId, rowIndex, colIndex, value);
/* 132 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */ 
/*     */   
/*     */   @Then("^Validate widget \"([^\"]*)\" for a row value \"([^\"]*)\" and column \"([^\"]*)\" with value as \"([^\"]*)\"$")
/*     */   public void performActionforEditableColumns(String widgetId, String rowValue, int colIndex, String value) throws CCTException {
/* 138 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 139 */     table.performActionforEditableColumns(widgetId, rowValue, colIndex, value);
/* 140 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Click Save \"([^\"]*)\"$")
/*     */   public void ClickOnSave(String widgetId) throws CCTException {
/* 145 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 146 */     table.clickOnSave(widgetId);
/* 147 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Click Cancel \"([^\"]*)\"$")
/*     */   public void ClickOnCancel(String widgetId) throws CCTException {
/* 152 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 153 */     table.clickOnCancel(widgetId);
/* 154 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Click Refresh \"([^\"]*)\"$")
/*     */   public void ClickOnRefresh(String widgetId) throws CCTException {
/* 159 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 160 */     table.clickOnRefresh(widgetId);
/* 161 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate Submit Button \"([^\"]*)\" for the message \"([^\"]*)\"$")
/*     */   public void ValidateSubmitButtonMessage(String widgetId, String message) throws CCTException {
/* 166 */     SubmitButton button = (SubmitButton)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 167 */     button.validateSubmitButtonMessage(message);
/* 168 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate Export Button \"([^\"]*)\" for the message \"([^\"]*)\"$")
/*     */   public void validateExportButtonMessage(String widgetId, String message) throws CCTException {
/* 173 */     ExportButton exportButton = (ExportButton)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 174 */     exportButton.validateExportButtonMessage(message);
/* 175 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate Map Settings \"([^\"]*)\" for the map provider \"([^\"]*)\"$")
/*     */   public void validateMapProvider(String widgetId, String provider) throws CCTException {
/* 180 */     Map map = (Map)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 181 */     map.validateMapProvider(provider);
/* 182 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate Editable Table \"([^\"]*)\" for the message \"([^\"]*)\"$")
/*     */   public void ValidateEditableTableMessage(String widgetId, String message) throws CCTException {
/* 187 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 188 */     table.validateSaveButtonMessage(message);
/* 189 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate widget \"([^\"]*)\" with action as \"([^\"]*)\" for value \"([^\"]*)\"$")
/*     */   public void validateEditableTableDeleteOrPublish(String widgetId, String action, String value) throws CCTException {
/* 194 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 195 */     table.performAction(widgetId, action, value);
/* 196 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Search value \"([^\"]*)\" for a widget \"([^\"]*)\" as \"([^\"]*)\"$")
/*     */   public void searchValue(String value, String widgetId, boolean exists) throws CCTException {
/* 201 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 202 */     table.searchValue(value, widgetId, exists);
/* 203 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Add Record for widget \"([^\"]*)\" with values \"([^\"]*)\"$")
/*     */   public void addRecord(String widgetId, String values) throws CCTException {
/* 208 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 209 */     table.addRecord(widgetId, values);
/* 210 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate widget \"([^\"]*)\" for value \"([^\"]*)\"$")
/*     */   public void validateWidgetForValue(String widgetId, String value) throws CCTException {
/* 215 */     TextBox textBox = (TextBox)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 216 */     textBox.validateTextBox(widgetId, value);
/* 217 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }

            @Then("^Validate widget \"([^\"]*)\" for list of values \"([^\"]*)\"$")
            public void validateWidgetForListOfValues(String widgetId, String value) throws CCTException {
                getCCTPage().clickOnWidget(widgetId, 0);
                PullDown pullDown = (PullDown)getCCTPage().getRenderedWidgetInstance(widgetId);
                pullDown.verifyListItems(value);
             //   CCTSoftAssertions.getInstance().assertAll();
            }

            @Then("^Validate Export from DB button of table \"([^\"]*)\"$")
            public void validateExportDB(String widgetId) throws CCTException {
                getCCTPage().clickOnWidget(widgetId, 0);
                Table table = (Table)getCCTPage().getRenderedWidgetInstance(widgetId);
                table.validateExportDB(widgetId);
            }

            @And("^Click on Table \"([^\"]*)\" row \"([^\"]*)\" and column \"([^\"]*)\"$")
            public void clickOnTableCell(String widgetId, int row, int column) throws CCTException {
                getCCTPage().clickOnWidget(widgetId, 0);
                Table table = (Table)getCCTPage().getRenderedWidgetInstance(widgetId);
                table.clickTableCell(row, column);
            }

            @Then("^Validate Table with id \"([^\"]*)\" header contains value \"([^\"]*)\"$")
            public void table_should_contain_header(String widgetId,String header) throws Throwable {
                getCCTPage().clickOnWidget(widgetId, 0);
                Table table = (Table)getCCTPage().getRenderedWidgetInstance(widgetId);
                table.checkTableHeaderValue(widgetId,header);
                //CCTSoftAssertions.getInstance().assertAll();
            }


    /*     */
/*     */   @Then("^Validate Widget \"([^\"]*)\" with Export Permission \"([^\"]*)\" and action \"([^\"]*)\"$")
/*     */   public void ValidateExportPermissions(String widgetId, String permission, String action) throws CCTException {
/* 222 */     Table table = (Table)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 223 */     table.validateExportButtonIsRendered(widgetId, permission, action);
/* 224 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */   
/*     */   @Then("^Validate Editable Table \"([^\"]*)\" for Timezone \"([^\"]*)\" with date \"([^\"]*)\"$")
/*     */   public void verifyTimeZone(String widgetId, String date, String timezone) throws Throwable {
/* 229 */     EditableTable table = (EditableTable)getCCTPage().getRenderedWidgetInstance(widgetId);
/* 230 */     table.verifyTimeZone(widgetId, date, timezone);
/* 231 */     CCTSoftAssertions.getInstance().assertAll();
/*     */   }
/*     */ }


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\cucumber\keywords\CCTHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */