package KeywordDrivenTestFramework.Testing.TestClasses.MyTeam;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.io.IOException;

/**
 * @author SKHUMALO on 2023/08/17.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "Worksite_Adviser_Infor", createNewBrowserInstance = false)
public class ValidateWorksiteAdviser_Information extends BaseClass {
    String error = "";
    String platform = getData("Platform");

    public TestResult executeTest() {
        if (!navigateToMyTeam()) {
            return narrator.testFailed(error);
        }
        if (!validateAdviserInfor()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully validated the worksite adviser information.");
    }

    public boolean navigateToMyTeam() {
        //Navigate to My Team
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.MyTeamSidebar())) {
            error = "Failed to wait for the My Team Sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.MyTeamSidebar())) {
            error = "Failed to wait for the My Team Sidebar.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'My Team' Sidebar.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.MyTeamHeader())) {
            error = "Failed to wait for the 'My Team List' header.";
            return false;
        }

        return true;
    }

    public boolean validateAdviserInfor() {
        //extract data
        pause(2000);
        String adviser_Name = null, adviser_PhoneNo = null, adviser_Email = null, adviser_Staffcode = null;
        if (platform.equalsIgnoreCase("MFC")) {
            adviser_Name = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myTeamAdviserName());
            adviser_PhoneNo = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.Adviser_PhoneNo());
            adviser_Email = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.Adviser_Email());
            adviser_Staffcode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.Adviser_Staffcode());
        } else if (platform.equalsIgnoreCase("PF")) {
            adviser_Name = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myTeamAdviserName());
            adviser_PhoneNo = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.Adviser_PhoneNo());
            adviser_Email = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.Adviser_Email2());
        }

        //Click 1 Adviser
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myTeamAdviserName())) {
            error = "Failed to wait for the My Team >> Adviser Name.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myTeamAdviserName())) {
            error = "Failed to click the My Team >> Adviser Name.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the My Name >> Adviser Name.");

        pause(2000);
        String expected_adviser_Name = null, expected_adviser_PhoneNo = null, expected_adviser_Email = null, expected_adviser_Staffcode = null;
        if (platform.equalsIgnoreCase("MFC")) {
            //personal Information
            expected_adviser_Name = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_AdviserName());
            expected_adviser_PhoneNo = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_PhoneNo());
            expected_adviser_Email = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Email());
            expected_adviser_Staffcode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Staffcode());
        } else if (platform.equalsIgnoreCase("PF")) {
            expected_adviser_Name = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_AdviserName());
            expected_adviser_PhoneNo = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_PhoneNo2());
            expected_adviser_Email = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Email());
        }
        //Adviser Name
        if (adviser_Name.equalsIgnoreCase(expected_adviser_Name)) {
            narrator.ValidateEqual(adviser_Name, expected_adviser_Name);
            narrator.stepPassed("Successfully validated Adviser Name.");
        } else {
            error = "Failed to validate the Adviser Name.";
            return false;
        }

        //Adviser Phone No.
        if (adviser_PhoneNo.equalsIgnoreCase(expected_adviser_PhoneNo)) {
            narrator.ValidateEqual(adviser_PhoneNo, expected_adviser_PhoneNo);
            narrator.stepPassed("Successfully validated Phone No.");
        } else {
            error = "Failed to validate the Phone No.";
            return false;
        }

        //Adviser Email
        if (adviser_Email.equalsIgnoreCase(expected_adviser_Email)) {
            narrator.ValidateEqual(adviser_Email, expected_adviser_Email);
            narrator.stepPassed("Successfully validated Adviser Email.");
        } else {
            error = "Failed to validate the Adviser Email.";
            return false;
        }

        if (platform.equalsIgnoreCase("MFC")) {
            //Adviser Staffcode
            if (adviser_Staffcode.equalsIgnoreCase(expected_adviser_Staffcode)) {
                narrator.ValidateEqual(adviser_Staffcode, expected_adviser_Staffcode);
                narrator.stepPassed("Successfully validated Adviser Staffcode.");
            } else {
                error = "Failed to validate the Adviser Staffcode.";
                return false;
            }
        }

        return true;
    }

    public static void OutlookDesktopAutomation() {
        try {
            String script = "tell application \"Microsoft Outlook\"\n" + "   activate\n" + "   -- Your AppleScript actions here\n" + "end tell";

            String[] cmd = {"osascript", "-e", script};
            Process process = Runtime.getRuntime().exec(cmd);

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
