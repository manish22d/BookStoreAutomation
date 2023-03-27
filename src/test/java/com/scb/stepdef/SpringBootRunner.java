package com.scb.stepdef;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@CucumberContextConfiguration
@SpringBootTest
public class SpringBootRunner {

    @After
    public void tearDownCucumberSpringContext(Scenario scenario) {
    }
}
