package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/10/20.
 * @project DAE-Automation-Framework
 */

@KeywordAnnotation(Keyword = "SendToXplan_MFC_ChangeLeadStatus", createNewBrowserInstance = false)
public class sendTo extends BaseClass {
    String error = "";

    public TestResult executeTest() {
        if (!SendToXplan()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully sent a lead to " + getData("Send To"));
    }

    public boolean SendToXplan() {
        //More options
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to wait for the action >> More button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to click the action >> More button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> More button.");

        //SendTo
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionSendTo_btn())) {
            error = "Failed to wait for the Action >> More >> Send To.";
            return false;
        }
        if (!SeleniumDriverInstance.hoverOverElementByXpath(DAE_PageObject.actionSendTo_btn())) {
            error = "Failed to click the Action >> More >> Send To.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionSendTo_option(getData("Send To")))) {
            error = "Failed to wait for the Action >> More >> Send To: " + getData("Send To");
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionSendTo_option(getData("Send To")))) {
            error = "Failed to click the Action >> More >> Send To: " + getData("Send To");
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> More >> Send To: " + getData("Send To"));

        //Successful message
        String submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());

        //Message
        if (!submittedSuccessfully_msg.isEmpty()) {
            narrator.ValidateEqual(submittedSuccessfully_msg, "Submitted successfully");
            narrator.stepPassedWithScreenShot("Successfully validated the success message: '" + submittedSuccessfully_msg);
        } else {
            error = "Failed to validate the success message: '" + submittedSuccessfully_msg;
            return false;
        }


        return true;
    }
}
