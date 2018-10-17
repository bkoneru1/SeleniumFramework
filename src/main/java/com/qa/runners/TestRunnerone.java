
package com.qa.runners;


import org.testng.runner.RunWith;
import cucumber.api.CucumberOptions;
//import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\Koneru\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\Features\\freecrm.feature"
		,glue={"stepDefinitions"}
		)

public class TestRunnerone {

}
