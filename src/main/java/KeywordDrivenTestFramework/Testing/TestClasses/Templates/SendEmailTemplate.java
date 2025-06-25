package KeywordDrivenTestFramework.Testing.TestClasses.Templates;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Communication.EmailStatusValidation;

/**
 * @author SKHUMALO on 2023/09/19.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "sendEmailFromTemplate", createNewBrowserInstance = false)
public class SendEmailTemplate extends BaseClass {
    String error = "";
    EmailStatusValidation EmailStatusValidationObj;

    public SendEmailTemplate() {
        this.EmailStatusValidationObj = new EmailStatusValidation();
    }

    public TestResult executeTest() {
        if (!sendAnEmailThroughTemplate()) {
            return narrator.testFailed(error);
        }
        if(!recipientList()){
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully sent an email through email template.");
    }

    public boolean sendAnEmailThroughTemplate() {
        EmailStatusValidationObj.navigateToCommunication();

        //Templates
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.templates_tab())) {
            error = "Failed to wait for the 'Templates' tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.templates_tab())) {
            error = "Failed to click the 'Templates' tab.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Templates' tab.");

        //Change Grid to List
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.gridToList_btn())) {
            error = "Failed to wait for the 'Change Grid to List' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.gridToList_btn())) {
            error = "Failed to click the 'Change Grid to List' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Change Grid to List' button.");

        //Action Email
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionEmail_btn())) {
            error = "Failed to wait for the Email icon.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionEmail_btn())) {
            error = "Failed to click the Email icon.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> Email icon button.");

        //Scroll down to Signature Info
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.signatureInfo())) {
            error = "Failed to scroll to the 'Signature Info'.";
            return false;
        }

        //Signature dropdown
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.templateContentSignature_drpdwn())) {
            error = "Failed to wait for the 'Signature' dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.templateContentSignature_drpdwn())) {
            error = "Failed to click the 'Signature' dropdown.";
            return false;
        }

        //Select Signature
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.selectSignature_popup())) {
            error = "Failed to wait for the 'Select Signature' popup.";
            return false;
        }
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.signature(getData("Signature Name")))) {
            error = "Failed to scroll to the 'Signature' " + getData("Signature Name");
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.signature(getData("Signature Name")))) {
            error = "Failed to click the 'Signature' " + getData("Signature Name");
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Signature: '" + getData("Signature Name") + "'.");

        //Confirm button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to wait for the Confirm button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm button.");

        //Next button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.next_btn2())) {
            error = "Failed to wait for the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn2())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Next button.");

        return true;
    }

    public boolean recipientList() {
        //Recipient List
        //Add button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.recipientAdd_btn())) {
            error = "Failed to wait for the Add button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.recipientAdd_btn())) {
            error = "Failed to click the Add button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Add button.");

        //Select Customers Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customers_tab())) {
            error = "Failed to wait for the 'Customers' tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customers_tab())) {
            error = "Failed to click the 'Customers' tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'Customers' tab.");

        //Search a Lead
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.addLeadCustomerSearch_btn())) {
            error = "Failed to wait for the Search button.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.addLeadCustomerSearch_btn(), getData("Customer/Lead"))) {
            error = "Failed to enter '" + getData("Customer/Lead") + "' into the Search box.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the Customer/Leads: " + getData("Customer/Lead"));

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //Customer checkbox
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customer_chckbx(getData("Customer/Lead")))) {
            error = "Failed to wait for the 'Customer' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Customer/Lead: '" + getData("Customer/Lead") + "' checkbox.");

        //Confirm button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to wait for the Confirm button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm button.");

        pause(2000);
        //Next button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.next_btn2())) {
            error = "Failed to wait for the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn2())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Next button.");

        //Scroll down to send button
        //Send button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.send_btn())) {
            error = "Failed to wait for the Send button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.send_btn())) {
            error = "Failed to click the Send button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Send button.");

        //Send Email Confirmation message
        if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.send_btn2())){
            error = "Failed to wait for the Send Email Confirmation Popup.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.send_btn2())){
            error = "Failed to click the Send Email Confirmation Popup.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Send Email Confirmation popup button.");

        pause(2000);
        //Email has been sent
        if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.Done_btn())){
            error = "Failed to wait for the 'Email has been sent' message.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Done_btn())){
            error = "Failed to click the 'Email has been sent' >> Done button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Done button.");

        return true;
    }
}
