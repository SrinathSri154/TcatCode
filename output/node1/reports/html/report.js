$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CQI_SMS Trend Analysis.feature");
formatter.feature({
  "line": 1,
  "name": "CQI",
  "description": "",
  "id": "cqi",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Verify Locations tab of CQI SMS Failure Analysis for all possible combinations of By pulldown",
  "description": "",
  "id": "cqi;verify-locations-tab-of-cqi-sms-failure-analysis-for-all-possible-combinations-of-by-pulldown",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@ID_TC_CQI_SMSFailureAnalysis_05"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 5,
      "value": "#[\"REGRESSION\"]"
    }
  ],
  "line": 6,
  "name": "Login into application as \"User\"",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Navigate to Usecase Group \"CUSTOMER QUALITY INSIGHT\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Navigate to Usecase \"CQI SMS Failure Analysis\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Reset filters",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "Select time filter as \"\u003cTime\u003e\" with value as \"\u003cTimeValue\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Select technology filter as \"\u003cTechnology\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Select location filter as \"\u003cLocation\u003e\" with value as \"\u003cLocationValue\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Select Subscribers filter as \"\u003cSubscriber\u003e\" with value as \"\u003cSubscriberValue\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Apply filters",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Click on widget \"cemboard-tab-494772\" value \"Locations\"",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Validate Apply Button workflow \"cemboard-apply-button-2195061\" for all possible combinations of widget values \"cemboard-pulldown-1564541\"",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Logoff",
  "keyword": "And "
});
formatter.examples({
  "line": 19,
  "name": "",
  "description": "",
  "id": "cqi;verify-locations-tab-of-cqi-sms-failure-analysis-for-all-possible-combinations-of-by-pulldown;",
  "rows": [
    {
      "cells": [
        "Top",
        "Show",
        "By",
        "LocationTabTable",
        "LocationTabTable1",
        "LocationTabTable2",
        "Time",
        "TimeValue",
        "Technology",
        "Location",
        "LocationValue",
        "Subscriber",
        "SubscriberValue"
      ],
      "line": 20,
      "id": "cqi;verify-locations-tab-of-cqi-sms-failure-analysis-for-all-possible-combinations-of-by-pulldown;;1"
    },
    {
      "cells": [
        "Regions, Cities, Areas, Cells",
        "Total, Outgoing, Incoming",
        "Affected Subscribers, Failures",
        "Area[City/Region], Cell ID, Cell Name, Active Subscribers, Affected Subscribers, SMS, Failures, Failure (%)",
        "IMSI, MSISDN, Subscription Type, Customer Segment, Home/ Roamer, Device Type, Device Model, Device Capability, Total, Outgoing, Incoming",
        "Cause Code, Description, Category, Error Type, Affected Subscribers, Failures",
        "Day",
        "05/17/2020",
        "2G",
        "Areas",
        "Sulawesi - Kota Manado - M.tbg Teling Bawah, Sulawesi - Kota Manado - Camar",
        "",
        ""
      ],
      "line": 21,
      "id": "cqi;verify-locations-tab-of-cqi-sms-failure-analysis-for-all-possible-combinations-of-by-pulldown;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 4441312400,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Verify Locations tab of CQI SMS Failure Analysis for all possible combinations of By pulldown",
  "description": "",
  "id": "cqi;verify-locations-tab-of-cqi-sms-failure-analysis-for-all-possible-combinations-of-by-pulldown;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 3,
      "name": "@ID_TC_CQI_SMSFailureAnalysis_05"
    }
  ]
});
formatter.step({
  "comments": [
    {
      "line": 5,
      "value": "#[\"REGRESSION\"]"
    }
  ],
  "line": 6,
  "name": "Login into application as \"User\"",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Navigate to Usecase Group \"CUSTOMER QUALITY INSIGHT\"",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "Navigate to Usecase \"CQI SMS Failure Analysis\"",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "Reset filters",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "Select time filter as \"Day\" with value as \"05/17/2020\"",
  "matchedColumns": [
    6,
    7
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "Select technology filter as \"2G\"",
  "matchedColumns": [
    8
  ],
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Select location filter as \"Areas\" with value as \"Sulawesi - Kota Manado - M.tbg Teling Bawah, Sulawesi - Kota Manado - Camar\"",
  "matchedColumns": [
    9,
    10
  ],
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "Select Subscribers filter as \"\" with value as \"\"",
  "matchedColumns": [
    11,
    12
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "Apply filters",
  "keyword": "And "
});
formatter.step({
  "line": 15,
  "name": "Click on widget \"cemboard-tab-494772\" value \"Locations\"",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "Validate Apply Button workflow \"cemboard-apply-button-2195061\" for all possible combinations of widget values \"cemboard-pulldown-1564541\"",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Logoff",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "User",
      "offset": 27
    }
  ],
  "location": "PortalHandler.login(String)"
});
formatter.result({
  "duration": 30782717000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CUSTOMER QUALITY INSIGHT",
      "offset": 27
    }
  ],
  "location": "PortalHandler.navigateToUsecaseGroup(String)"
});
formatter.result({
  "duration": 363184700,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "CQI SMS Failure Analysis",
      "offset": 21
    }
  ],
  "location": "PortalHandler.navigateToUsecase(String)"
});
formatter.result({
  "duration": 31427812700,
  "status": "passed"
});
formatter.match({
  "location": "CGFHandler.resetFilters()"
});
formatter.result({
  "duration": 1418636600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Day",
      "offset": 23
    },
    {
      "val": "05/17/2020",
      "offset": 43
    }
  ],
  "location": "CGFHandler.applyTimeFilterForUsecase(String,String)"
});
formatter.result({
  "duration": 18505992400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2G",
      "offset": 29
    }
  ],
  "location": "CGFHandler.applyTechnologyFilterForUsecase(String)"
});
formatter.result({
  "duration": 8968397100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Areas",
      "offset": 27
    },
    {
      "val": "Sulawesi - Kota Manado - M.tbg Teling Bawah, Sulawesi - Kota Manado - Camar",
      "offset": 49
    }
  ],
  "location": "CGFHandler.applyLocationFilterForUsecase(String,String)"
});
formatter.result({
  "duration": 65668281000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "",
      "offset": 30
    },
    {
      "val": "",
      "offset": 47
    }
  ],
  "location": "CGFHandler.applySubscribersFilterForUsecase(String,String)"
});
formatter.result({
  "duration": 776100,
  "status": "passed"
});
formatter.match({
  "location": "CGFHandler.applyFilters()"
});
formatter.result({
  "duration": 9349222500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "cemboard-tab-494772",
      "offset": 17
    },
    {
      "val": "Locations",
      "offset": 45
    }
  ],
  "location": "CCTHandler.click_on_widget_value(String,String)"
});
formatter.result({
  "duration": 11786934900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "cemboard-apply-button-2195061",
      "offset": 32
    },
    {
      "val": "cemboard-pulldown-1564541",
      "offset": 111
    }
  ],
  "location": "CCTHandler.validate_Apply_Button_workflow_for_all_possible_combinations_of_widget_values(String,String)"
});
formatter.result({
  "duration": 141652269400,
  "error_message": "java.lang.NullPointerException\r\n\tat com.nokia.cemod.cct.core.widget.settings.CompositeChartSettings.getKpiNamesandColor(CompositeChartSettings.java:51)\r\n\tat com.nokia.cemod.cct.core.widget.wrappers.CompositeChart.getMinimumClickableElementsForWorkFlow(CompositeChart.java:42)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateWidget(CCTBasePage.java:360)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateWidgets(CCTBasePage.java:351)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateChildWidgets(CCTBasePage.java:396)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.assertAndValidateChildWidgets(CCTBasePage.java:385)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateWidget(CCTBasePage.java:369)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateWidgets(CCTBasePage.java:351)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateChildWidgets(CCTBasePage.java:396)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.assertAndValidateChildWidgets(CCTBasePage.java:385)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.clickAndValidateWidget(CCTBasePage.java:369)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.validateWorkFlow(CCTBasePage.java:544)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.performClickForAllCombinations(CCTBasePage.java:612)\r\n\tat com.nokia.cemod.cct.pages.CCTBasePage.validateApplyButtonWorkFlowForAllCombinationsOfWidgetValues(CCTBasePage.java:591)\r\n\tat com.nokia.cemod.cct.tests.cucumber.keywords.CCTHandler.validate_Apply_Button_workflow_for_all_possible_combinations_of_widget_values(CCTHandler.java:109)\r\n\tat ✽.And Validate Apply Button workflow \"cemboard-apply-button-2195061\" for all possible combinations of widget values \"cemboard-pulldown-1564541\"(CQI_SMS Trend Analysis.feature:16)\r\n",
  "status": "failed"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.after({
  "duration": 84300,
  "status": "passed"
});
});