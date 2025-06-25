package TestSuites.UnitTests;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author SKHUMALO on 2023/08/02.
 * @project DAE-Automation-Framework
 */
public class MyTeam_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;
    public MyTeam_TestSuite() {
        TestMarshall.currentEnvironment = Enums.Environment.QA;
        ApplicationConfig appConfig = new ApplicationConfig();
    }

    //Create test functions Unit tests for My Team functions
    @Test
    public void AdviserInformation_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| My Team");
        instance = new TestMarshall("TestPacks/MyTeam/MFC/Sales Manager-Adviser Infor-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    @Test
    public void OutlookEmailValidation_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| My Team");
        instance = new TestMarshall("TestPacks/MyTeam/MFC/Sales Manager-Email Validation-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    //=======================================================================
    @Test
    public void PF_AdviserInformation_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| My Team");
        instance = new TestMarshall("TestPacks/MyTeam/PF/Sales Manager-Adviser Infor-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    @Test
    public void PF_OutlookEmailValidation_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| My Team");
        instance = new TestMarshall("TestPacks/MyTeam/PF/Sales Manager-Email Validation-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }


}
