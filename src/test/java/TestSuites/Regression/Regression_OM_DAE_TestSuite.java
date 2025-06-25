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
public class Regression_OM_DAE_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public Regression_OM_DAE_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }
    //====================== Sanity Regression ========================//
    @Test
    public void OM_DAE_SanityTesting_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("OM| DAE - Sanity Testing");
        instance = new TestMarshall("TestPacks/Regression/DAE_Sanity_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //====================== Campaign Regression ========================//
    @Test
    public void PF_Campaign_Regression_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Campaign Regression");
        instance = new TestMarshall("TestPacks/Regression/PF Campaign Regression - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void MFC_Campaign_Regression_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Campaign Regression");
        instance = new TestMarshall("TestPacks/Regression/MFC Campaign Regression - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //====================== Leads / Opportunity Regression ========================//
    @Test
    public void PF_Leads_Regression_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Leads Regression");
        instance = new TestMarshall("TestPacks/Regression/PF Leads Regression - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void MFC_Opportunity_Regression_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Opportunity Regression");
        instance = new TestMarshall("TestPacks/Regression/MFC Opportunity Regression - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //====================== Customers Regression ========================//
    @Test
    public void PF_Customers_Regression_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Customers Regression");
        instance = new TestMarshall("TestPacks/Regression/PF Customers - TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //====================== Workbench Regression ========================//

    //====================== Sales & Services Regression ========================//

    //====================== Calendar Regression ========================//
}