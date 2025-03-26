package com.demo.runners;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/login.feature",  
    glue = "com.demo.stepdefinitions",        
    plugin = {"pretty", "html:target/cucumber-repo.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    public static String BrowserType;

    @BeforeMethod
    @Parameters("browser")
    public void setupBrowser(String browser) {
        BrowserType = browser;
    }
    
	/*
	 * @DataProvider(parallel=true) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */
}