package transactionservicetest;

import java.io.IOException;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
//@CucumberOptions(features = { "classpath:TestFeatures/TransactionType.feature" }, strict = false, tags = {"@smoke_test"})
@CucumberOptions(
		
		features = "classpath:transactionservicetest/TransactionType.feature", strict = false)
public class TransactionTypeTest {
	
	
}
