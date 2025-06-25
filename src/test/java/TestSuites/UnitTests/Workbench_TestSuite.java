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
public class Workbench_TestSuite {
    static TestMarshall instance;

    //Mobile Testing
    public static Enums.DeviceConfig test;

    public Workbench_TestSuite() {
        ApplicationConfig appConfig = new ApplicationConfig();
        TestMarshall.currentEnvironment = Enums.Environment.QA;
    }

    @Test
    public void MFC_Workbench_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| MFC Workbench");
        instance = new TestMarshall("TestPacks/Workbench/MFC_Workbench_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

    @Test
    public void PF_Workbench_TestSuite() throws FileNotFoundException {
        Narrator.logDebug("DAE| PF Workbench");
        instance = new TestMarshall("TestPacks/Workbench/PF_Workbench_TestPack.xlsx", Enums.BrowserType.FireFox, false);
        instance.runKeywordDrivenTests();
    }

}