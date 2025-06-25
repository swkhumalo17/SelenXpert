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
public class PF_Leads_TestSuite {
    static TestMarshall instance;
    public static Enums.DeviceConfig test;

    public PF_Leads_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    //===========================================================
    //This are the unit tests/ test suits for PF Campaigns
    //Leads PF Adviser
    @Test
    public void CreateAnOpportunityFromCustomerList() throws FileNotFoundException {
        Narrator.logDebug("DAE| Leads");
        instance = new TestMarshall("TestPacks/Leads/PF/Create an opportunity from customer list-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void CreateAnOpportunity() throws FileNotFoundException {
        Narrator.logDebug("DAE| Leads");
        instance = new TestMarshall("TestPacks/Leads/PF/Create an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_CloseALead() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Leads"));
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Close a lead-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_ReferALead() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Leads"));
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Refer a lead-TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_ChangeStatus() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Leads"));
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Change statuses-TestPack.xlsx", Enums.BrowserType.Chrome, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_ContactACustomer() throws FileNotFoundException {
        Narrator.logDebug(("DAE| Leads"));
        instance = new TestMarshall("TestPacks/Leads/PF/Contact a Customer-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_ManageAppointments() throws FileNotFoundException{
       Narrator.logDebug("DAE| Manage Appointments");
       instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Re-schedule an appointment-TestPack.xlsx", Enums.BrowserType.FireFox, false);
       instance.runKeywordDrivenTests();
    }

    @Test
    public void ActionALead_ReSchedule() throws FileNotFoundException{
        Narrator.logDebug("DAE| Manage Appointments");
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Re-schedule an appointment-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    @Test
    public void ActionALead_ConfirmAppointments() throws FileNotFoundException{
        Narrator.logDebug("DAE| Manage Appointments");
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Confirm Attendance-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }
    @Test
    public void ActionALead_CancelAnAppointment() throws FileNotFoundException{
        Narrator.logDebug("DAE| Manage Appointments");
        instance = new TestMarshall("TestPacks/Leads/PF/ActionAlead - Cancel an appointment-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    //========================================================================================
    //Sales Manager
    @Test
    public void CreateAnOpportunity_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Leads");
        instance = new TestMarshall("TestPacks/Leads/PF/SalesManager-Create an opportunity-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void CloseALead_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Leads");
        instance = new TestMarshall("TestPacks/Leads/PF/SalesManager-Close a lead-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void AllocateALead_SalesManager() throws FileNotFoundException {
        Narrator.logDebug("DAE| Leads");
        instance = new TestMarshall("TestPacks/Leads/PF/SalesManager-Allocate a lead-TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

}