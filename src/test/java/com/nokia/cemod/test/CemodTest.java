package com.nokia.cemod.test;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = {"pretty", "html:output/node1/reports/html", "json:./test_output_result.json", "junit:output/node1/reports/cucumber.xml"}, tags = {"~@ignore"}, features = {"Features/"}, glue = {"com.nokia.cemod.cct.tests.cucumber.keywords", "com.nokia.analytics.test.keywords"})
public class CemodTest {

}