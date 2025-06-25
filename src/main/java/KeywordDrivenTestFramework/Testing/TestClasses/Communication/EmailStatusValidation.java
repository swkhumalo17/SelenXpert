package KeywordDrivenTestFramework.Testing.TestClasses.Communication;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import lombok.Builder;

/**
 * @author SKHUMALO on 2023/09/13.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "emailStatus", createNewBrowserInstance = false)
public class EmailStatusValidation extends BaseClass {
    String error = "";
    String afterEmailStatus;

    public TestResult executeTest() {
        if (!navigateToCommunication()) {
            return narrator.testFailed(error);
        }
        if(!resendEmail()){
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully validated 'Send To Customer' email status is " + afterEmailStatus);
    }

    public boolean navigateToCommunication() {
        //Navigate to Communication Sidebar and validate the if it has been Delivered or Failed to deliver
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.communicationSidebar())) {
            error = "Failed to wait for the 'Communications' sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.communicationSidebar())) {
            error = "Failed to click the 'Communications' sidebar.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Communications' sidebar.");

        //validate if you navigated to Communications page >> Templates page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.templatePage())) {
            error = "Failed to wait for the 'Template' page to be visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'Template' page.");

        return true;
    }

    public boolean resendEmail() {
        //History Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.history_Tab())) {
            error = "Failed to wait for the 'History' tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionResend_btn())) {
            error = "Failed to click the 'History' tab.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'History' tab.");

        //validate if you navigated to Communications page >> History page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.templatePage())) {
            error = "Failed to wait for the 'History' page to be visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'History' page.");

        //Resend an email under Action
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionResend_btn())) {
            error = "Failed to wait for the 'Re-send' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionResend_btn())) {
            error = "Failed to click the 'Re-send' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Re-send' button.");

        //Wait for Resend to Recipient card pop up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.resendToRecipient_Card())) {
            error = "Failed to wait for the 'Resend to Recipient' card pop up.";
            return false;
        }
        narrator.stepPassed("'Resend to Recipient' card pop up is visible.");

        //Edit an email
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.emailEdit_btn())) {
            error = "Failed to wait for the 'Edit' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.emailEdit_btn())) {
            error = "Failed to click the 'Edit' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Edit' button.");

        //Clear the
        if (getData("Status").equalsIgnoreCase("Failed")) {
            //Edit the email to a wrong email
            //Clear textbox
            if (!SeleniumDriverInstance.clearTextBoxByXPath(DAE_PageObject.emailEdit_txt())) {
                error = "Failed to clear the email edit textbox.";
                return false;
            }
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.emailEdit_txt(), getData("Email"))) {
                error = "Failed to enter the email edit textbox.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully entered '" + getData("Email") + "'.");
        }

        //Save button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.save_btn())) {
            error = "Failed to wait for the 'Save' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.save_btn())) {
            error = "Failed to click the 'Save' button.";
            return false;
        }
        narrator.stepPassed("Successfully edited the email.");

        //Resend button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Resend_btn())) {
            error = "Failed to wait for the 'Resend' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Resend_btn())) {
            error = "Failed to click the 'Resend' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked resend button to the recipient email.");

        //email sent success message
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.submittedSuccessfully())) {
            error = "Failed to wait for msg to appear.";
            return false;
        }
        String submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());

        //Validate the success message
        try {
            if (!submittedSuccessfully_msg.isEmpty()) {
                narrator.ValidateEqual(submittedSuccessfully_msg, "Communication have been resent successfully.");
                narrator.stepPassedWithScreenShot("Successfully validated the " + submittedSuccessfully_msg);
            } else {
                error = "Failed to validate the success message: " + submittedSuccessfully_msg;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        pause(10000);

        //refresh the page
        if(!SeleniumDriverInstance.refreshPageBrowser()){
            error = "Failed to refresh the page.";
            return false;
        }

        String communicationEmailStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.communicationEmailStatus());

        try {
            if (!communicationEmailStatus.isEmpty()) {
                narrator.ValidateEqual(communicationEmailStatus, getData("Status"));
                narrator.stepPassedWithScreenShot("Successfully validated the " + communicationEmailStatus);
            } else {
                error = "Failed to validate the email status: " + communicationEmailStatus;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }
}
