package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity.OM_SecureServices;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import KeywordDrivenTestFramework.Utilities.SeleniumDriverUtility;

import java.io.File;
import java.util.Properties;

import static KeywordDrivenTestFramework.Core.BaseClass.*;
import static java.lang.System.out;



import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.currentBrowser;
import static KeywordDrivenTestFramework.Core.BaseClass.currentEnvironment;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberTesting;
import static KeywordDrivenTestFramework.Core.BaseClass.isCucumberMobileTesting;

import static KeywordDrivenTestFramework.Core.BaseClass.reportDirectory;
import KeywordDrivenTestFramework.Entities.Enums;
import static KeywordDrivenTestFramework.Entities.Enums.Environment.QA;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import KeywordDrivenTestFramework.Utilities.SeleniumDriverUtility;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.File;
import static java.lang.System.out;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


/**
 * @author SKHUMALO on 2023/02/09.
 * @project DAE-Automation-Framework
 */
@RunWith(Cucumber.class)
@CucumberOptions
        (
                plugin = {"usage","json:target/cucumber.json","html:target/cucumber", "com.cucumber.listener.ExtentCucumberFormatter:"},
                glue ={"BDD.step_definitions"},
                features ={"Unit-Tests\\BDD\\Features\\"},
                tags = {"@SmokeTest"},
                dryRun = false,
                monochrome = false,
                strict = true
        )

public class CucumberRunner
{
    public static boolean isDeviceTesting = false;

    @BeforeClass
    public static void setup()
    {


        Properties props = System.getProperties();

        String browser = "Chrome";/*props.getProperty("Browser");*/

        String environment = "QA"; /* props.getProperty("Environment");*/

        out.println("[INFO] Executing tests: Browser - " + browser + ", Environment - " + environment);

        ApplicationConfig appConfig = new ApplicationConfig();


        currentBrowser = Enums.resolveBrowserType(browser);

        currentEnvironment = Enums.Environment.QA;
        //    Enums.resolveTestEnvironment(environment)
        isCucumberTesting = true;

        isCucumberMobileTesting = true;

        ExtentProperties extentProperties = ExtentProperties.INSTANCE;

        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);

        reportDirectory = ApplicationConfig.ReportFileDirectory() + "BDD_" + SeleniumDriverInstance.generateDateTimeString();
        extentProperties.setReportPath(reportDirectory + "//ExtentReport.html");

    }

    @AfterClass
    public static void teardown() {

        Reporter.getExtentReport().setReportUsesManualConfiguration(true);
        Reporter.loadXMLConfig(new File("extent-config.xml"));
        Reporter.setSystemInfo("User", System.getProperty("user.name"));
        Reporter.setSystemInfo("Environment", currentEnvironment.name());
        Reporter.setSystemInfo("Browser", currentBrowser.name());
    }
}

