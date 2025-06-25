package TestSuites.UnitTests;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.TestMarshall;
import KeywordDrivenTestFramework.Utilities.ApplicationConfig;
import org.apache.poi.ss.formula.functions.Na;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author SKHUMALO on 2023/08/02.
 * @project DAE-Automation-Framework
 */
public class PerformanceManagement_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public PerformanceManagement_TestSuite() {
        TestMarshall.currentEnvironment = Enums.Environment.QA;
        ApplicationConfig appConfig = new ApplicationConfig();
    }

    //Create test functions Unit tests for My Performance functions
    //Sales Manager
    @Test
    public void SalesManagerMFC_PerformanceManagement_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Team Performance");
        instance = new TestMarshall("TestPacks/Performance/MFC_SalesManager_TeamValidation_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void SalesManagerMFC_AddTeamPerformance_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Team Performance");
        instance = new TestMarshall("TestPacks/Performance/MFC_SalesManager_AddTeamPerformance_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //Adviser
    @Test
    public void AdviserMFC_PerformanceManagement_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| My Performance");
        instance = new TestMarshall("TestPacks/Performance/MFC_Adviser_TeamValidation_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }


}
