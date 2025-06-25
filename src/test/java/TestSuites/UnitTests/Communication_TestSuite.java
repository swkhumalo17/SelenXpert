package TestSuites.UnitTests;

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
public class Communication_TestSuite {
    static TestMarshall instance;

    //Mobile Testing
    public static Enums.DeviceConfig test;

    public Communication_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    @Test
    public void EmailStatusValidation_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Communication");
        instance = new TestMarshall("TestPacks/Communication/PF email validation - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
}