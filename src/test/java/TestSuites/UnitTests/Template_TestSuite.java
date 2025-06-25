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
public class Template_TestSuite {
    static TestMarshall instance;

    //Mobile Testing
    public static Enums.DeviceConfig test;

    public Template_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    @Test
    public void Signature_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Create a new Signature");
        instance = new TestMarshall("TestPacks/Communication/PF Create a signature - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    @Test
    public void SendEmailUsingTemplate_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Send an email using an Template");
        instance = new TestMarshall("TestPacks/Communication/PF Send an email using Template - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
}