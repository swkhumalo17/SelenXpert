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
public class PF_Campaigns_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public PF_Campaigns_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }
    //===========================================================
    //This will are the unit tests/ test suits for PF Campaigns

    // PF Campaign Manager
    @Test
    public void Create_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Create_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Close_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Close_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Archive_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Archive_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Revive_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Revive_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Edit_Campaign_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Manager");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Edit_Campaign_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //======================================================================================//
    // PF Campaign Adviser
    @Test
    public void Create_Campaign_Adviser_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Campaign Adviser");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Create_Campaign_Adviser_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Close_Campaign_Adviser_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Campaign Adviser");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Close_Campaign_Adviser_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Archive_Campaign_Adviser_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Campaign Adviser");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Archive_Campaign_Adviser_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Revive_Campaign_Adviser_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Campaign Adviser");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Revive_Campaign_Adviser_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void Edit_Campaign_Adviser_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| Campaign Adviser");
        instance = new TestMarshall("TestPacks/Campaigns/PF/Edit_Campaign_Adviser_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

}