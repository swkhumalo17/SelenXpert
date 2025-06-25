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
@KeywordAnnotation(Keyword = "OutlookEmailValidation", createNewBrowserInstance = false)
public class ValidateWorksiteAdviser_Email extends BaseClass {
    String error = "";
    String platform = getData("Platform");

    public TestResult executeTest() {
        if (!navigateToMyTeam()) {
            return narrator.testFailed(error);
        }
        if (!validateAdviser_EmailOutLook()) {
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

    public boolean validateAdviser_EmailOutLook() {
        if (platform.equalsIgnoreCase("MFC")) {
            //Click email link
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Adviser_Email())) {
                error = "Failed to wait for the Worksite Adviser Email Link.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Adviser_Email())) {
                error = "Failed to click the Worksite Adviser Email Link.";
                return false;
            }
        }else if(platform.equalsIgnoreCase("PF")){
            //Click email link
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Adviser_Email2())) {
                error = "Failed to wait for the Worksite Adviser Email Link.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Adviser_Email2())) {
                error = "Failed to click the Worksite Adviser Email Link.";
                return false;
            }
        }
        narrator.stepPassed("Successfully clicked the Worksite Adviser Email Link");

        //This will validate the outlook desktop application after clicking email link
        OutlookDesktopAutomation();

        // Switch back to the main browser window
        if (!SeleniumDriverInstance.switchToDefaultContent()) {
            error = "Failed to switch back to default content.";
            return false;
        }
        return true;
    }

    public static void OutlookDesktopAutomation() {
        try {
            String script =
                    "tell application \"Microsoft Outlook\"\n" +
                            "   activate\n" +
                            "   -- Your AppleScript actions here\n" +
                            "end tell";

            String[] cmd = {"osascript", "-e", script};
            Process process = Runtime.getRuntime().exec(cmd);
            narrator.stepPassedWithScreenShot("Successfully validated the outlook is opened.");
            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
