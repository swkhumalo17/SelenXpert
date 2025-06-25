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
public class MFC_Leads_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public MFC_Leads_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }
    //===========================================================
    //This are the unit tests/ test suits for PF Campaigns
    //Leads PF Adviser
    @Test
    public void CreateAnOpportunityFromCustomerList() throws FileNotFoundException {
        Narrator.logDebug("DAE| Opportunities");
        instance = new TestMarshall("TestPacks/Leads/MFC/Create an opportunity from customer list-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void CreateAnOpportunity() throws FileNotFoundException {
        Narrator.logDebug("DAE| Opportunities");
        instance = new TestMarshall("TestPacks/Leads/MFC/Create an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionAnOpportunity_CloseAnOpportunity() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Opportunities"));
        instance = new TestMarshall("TestPacks/Leads/MFC/Close an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionAnOpportunity_ReferAnOpportunity() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Opportunities"));
        instance = new TestMarshall("TestPacks/Leads/MFC/Refer an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionAnOpportunity_ChangeStatus() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Opportunities"));
        instance = new TestMarshall("TestPacks/Leads/MFC/Change statuses-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionAnOpportunity_ContactACustomer() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Opportunities"));
        instance = new TestMarshall("TestPacks/Leads/MFC/Contact a Customer-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //========================================================================================
    //Sales Manager
    @Test
    public void CreateAnOpportunity_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Opportunities");
        instance = new TestMarshall("TestPacks/Leads/MFC/SalesManager-Create an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void CloseAnOpportunity_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Opportunities");
        instance = new TestMarshall("TestPacks/Leads/MFC/SalesManager-Close an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ReAssignAnOpportunity_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Opportunities");
        instance = new TestMarshall("TestPacks/Leads/MFC/SalesManager-Re-assign an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

}