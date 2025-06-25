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
public class MFC_Customers_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public MFC_Customers_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }
    @Test
    public void CreateAnOpportunityFromCustomerList() throws FileNotFoundException {
        Narrator.logDebug("DAE| Customers");
        instance = new TestMarshall("TestPacks/Customers/MFC/Create an opportunity from customer list-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void DownloadCustomerInsights() throws FileNotFoundException {
        Narrator.logDebug("DAE| Customers");
        instance = new TestMarshall("TestPacks/Customers/MFC/Download Insights-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void PreviewCustomerInsights() throws FileNotFoundException {
        Narrator.logDebug("DAE| Customers");
        instance = new TestMarshall("TestPacks/Customers/MFC/Preview Insights-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

}