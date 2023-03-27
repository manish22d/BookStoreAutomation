package com.scb.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;


@CucumberOptions(
        features = {"src/test/java/com/scb/features"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = "com.scb.stepdef",
        tags = "not @wip")
public class TestRunner extends AbstractTestNGCucumberTests {
}
