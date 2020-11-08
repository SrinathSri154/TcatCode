package com.nokia.cemod.cct.tests.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty", "html:output/node1/reports/html", "json:output/node1/reports/cucumber.json", "junit:output/node1/reports/cucumber.xml"}, tags = {"~@ignore"}, features = {"src/test/resources/features/"}, glue = {"com.nokia.cemod.cct.tests.cucumber.keywords", "com.nokia.analytics.test.keywords"})
public class CCTTestRunnerJUnit {}


/* Location:              D:\portal-ui-tests\libs\portal-ui-tests-1.0-SNAPSHOT-tests.jar!\com\nokia\cemod\cct\tests\runners\CCTTestRunnerJUnit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */