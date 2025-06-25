package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.awt.*;


/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(
        Keyword = "LogOut_DAE",
        createNewBrowserInstance = false
)
public class LogOut_OM_DAE extends BaseClass {
    String error = "";
    String testCaseName = "";

    public LogOut_OM_DAE() {

    }

    public TestResult executeTest() throws AWTException {
        if (!LogOut()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully logged Out of OM| DAE.");
    }

    public boolean LogOut() {
        //Pause for 2 sec and log out
        //wait for an element to be present before attempting to use that element
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.userProfile_dropDown())) {
            error = "Failed to wait for the user profile dropDown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.userProfile_dropDown())) {
            error = "Failed to click the user profile dropDown.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the user profile dropDown.");


        //Verify the LogOut option appears
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.btn_LogOut())) {
            error = "Failed to wait for the logOut button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully verified the logOut button is visible.");

        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.btn_LogOut())) {
            error = "Failed to click the logOut button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the logOut button.");

        try {
            //Verify the site has been logged Out
            String logAgain = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.txt_logAgain());

            if (!logAgain.isEmpty()) {
                narrator.ValidateEqual(logAgain, "Log in again", "Successfully validated the user has logged out.");
            } else {
                error = "Failed to validate the user: '" + testData.getData("Username") + "' logged out.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully validated the user '" + getData("Username") + "' has logged out.");
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }


}
