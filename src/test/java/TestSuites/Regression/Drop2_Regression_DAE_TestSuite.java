package TestSuites.Regression;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Drop2_Regression_DAE_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public Drop2_Regression_DAE_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    @Test
    public void Drop2_Regression_DAE_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Drop 2 Automation Regression");
        instance = new TestMarshall("TestPacks/Regression/Drop 2 Regression - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
}