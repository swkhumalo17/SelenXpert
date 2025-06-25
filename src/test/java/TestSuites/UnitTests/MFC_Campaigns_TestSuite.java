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
public class MFC_Campaigns_TestSuite {
    static TestMarshall instance;

    //Mobile Testing
    public static Enums.DeviceConfig test;

    public MFC_Campaigns_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    @Test
    public void Create_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/MFC/MFC_Create_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Close_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/MFC/MFC_Close_Campaign_TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Archive_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/MFC/MFC_Archive_Campaign_TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Revive_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/MFC/MFC_Revive_Campaign_TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Edit_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/MFC/MFC_Edit_Campaign_TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }
}