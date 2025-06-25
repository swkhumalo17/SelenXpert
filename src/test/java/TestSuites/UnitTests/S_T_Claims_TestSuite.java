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
public class S_T_Claims_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;
    public S_T_Claims_TestSuite() {
        TestMarshall.currentEnvironment = Enums.Environment.QA;
        ApplicationConfig appConfig = new ApplicationConfig();
    }

    //Create test functions Unit tests for S&T Claims MFC
    @Test
    public void ApproveClaims_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| S & T - Claims");
        instance = new TestMarshall("TestPacks/S&T_Claims/SalesManager_ApproveClaims_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ReturnClaims_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| S & T - Claims");
        instance = new TestMarshall("TestPacks/S&T_Claims/SalesManager_ReturnClaims_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //Adviser
    @Test
    public void SubmitClaims_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| S & T Claims");
        instance = new TestMarshall("TestPacks/S&T_Claims/Adviser_SubmitClaims_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void HistoryClaims_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| S & T Claims");
        instance = new TestMarshall("TestPacks/S&T_Claims/Adviser_HistoryClaims_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ReturnedClaims_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| S & T Claims");
        instance = new TestMarshall("TestPacks/S&T_Claims/Adviser_ReturnedClaims_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }


}
