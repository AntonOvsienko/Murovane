package com.sale.ticket.murovane.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "test/resources/features",
        glue = "com.sale.ticket.murovane.cucumber")
public class RunnerTest {
}
