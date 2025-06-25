package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import lombok.var;

/**
 * @author SKHUMALO on 2023/10/12.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "SendEmailForReviving", createNewBrowserInstance = false)
public class SendEmailAfterReviving extends BaseClass {
    String error = "";
    String submittedSuccessfully_msg;

    public TestResult executeTest() {
        if (!sendEmailForRevivedCampaign()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully sent an email.");
    }

    public boolean sendEmailForRevivedCampaign() {
        //Send button
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.sendEmail_btn())) {
            error = "Failed to wait for the 'Send' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.sendEmail_btn())) {
            error = "Failed to click the 'Send' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Send' button.");

        //validate the Send Email page
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.sendEmail_page())) {
            error = "Failed to wait for the 'Send Email' page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to the 'Send Email' page.");

        //Please select an email template
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.emailTemplateCard())) {
            error = "Failed to wait for the email template card.";
            return false;
        }
        //Email template option
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.emailTemplateOption(getData("Email Template")))) {
            error = "Failed to wait for the email template";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.emailTemplateOption(getData("Email Template")))) {
            error = "Failed to click the email template";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked  the email template: " + getData("Email Template"));

        //Click the Confirm Button
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to wait for the Confirm button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked Confirm button.");

        //Email sent successfully
        submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.confirmOpportunity_msg());

        if (!submittedSuccessfully_msg.isEmpty()) {
            narrator.ValidateEqual(submittedSuccessfully_msg, "Email sent successfully!");
            narrator.stepPassedWithScreenShot("Successfully validated the success message: '" + submittedSuccessfully_msg);
        } else {
            error = "Failed to validate the success message: '" + submittedSuccessfully_msg;
            return false;
        }

        return true;
    }
}
