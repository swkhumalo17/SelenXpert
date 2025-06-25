package KeywordDrivenTestFramework.Testing.TestClasses.Templates;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Communication.EmailStatusValidation;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author SKHUMALO on 2023/09/19.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "addNewSignature", createNewBrowserInstance = false)
public class Signature extends BaseClass {
    String error = "";

    EmailStatusValidation EmailStatusValidationObj;

    public Signature() {
        this.EmailStatusValidationObj = new EmailStatusValidation();
    }

    public TestResult executeTest() {
        if(!createNewSignature()){
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully created a new Signature.");
    }

    public boolean navigateToTemplate() {
        //Navigate to Templates Library Sidebar
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.templateLibrarySidebar())) {
            error = "Failed to wait for the Template Library sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.templateLibrarySidebar())) {
            error = "Failed to click the Template Library sidebar.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Template Library sidebar.");

        //validate if you navigated to Template page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.templatePage())) {
            error = "Failed to wait for the 'Template' page to be visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'Template Library' page.");

        return true;
    }

    public boolean createNewSignature() {
        //Navigate to Communication Sidebar
        EmailStatusValidationObj.navigateToCommunication();

        //Navigate to Signature Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.signature_tab())) {
            error = "Failed to wait for the 'Signature' tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.signature_tab())) {
            error = "Failed to click the 'Signature' tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Signature' tab.");

        //Validate the Signature page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.signatureHeader())) {
            error = "Failed to wait for 'Signature' page.";
            return false;
        }

        //Create New
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.signatureCreateNew_btn())) {
            error = "Failed to wait for the 'Signature' >> Create New button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.signatureCreateNew_btn())) {
            error = "Failed to click the 'Signature' >> Create New button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Signature' >> Create New button.");

        //Signature Name
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.signatureName_txt())) {
            error = "Failed to wait for the 'Signature Name' textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.signatureName_txt(), getData("Signature Name"))) {
            error = "Failed to enter the 'Signature Name': " + getData("Signature Name");
            return false;
        }
        narrator.stepPassed("Successfully entered the 'Signature Name': " + getData("Signature Name"));

        //Signature Textbox
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.signature_textbox())) {
            error = "Failed to wait for the 'Signature' textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.signature_textbox(), getData("Signature"))) {
            error = "Failed to enter the 'Signature' textbox: " + getData("Signature");
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the 'Signature': " + getData("Signature"));

        //Save Button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.save_btn1())) {
            error = "Failed to wait for the 'Signature' >> Save button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.save_btn1())) {
            error = "Failed to click the 'Signature' >> Save button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Signature' >> Save button.");

        //Created successfully
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.submittedSuccessfully())) {
            error = "Failed to wait for msg to appear.";
            return false;
        }
        String submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());

        //Validate the success message
        try {
            if (!submittedSuccessfully_msg.isEmpty()) {
                narrator.ValidateEqual(submittedSuccessfully_msg, "Created successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the " + submittedSuccessfully_msg);
            } else {
                error = "Failed to validate the success message: " + submittedSuccessfully_msg;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        pause(5000);

//        SeleniumDriverInstance.scrollToElement("//div[text()='Optional Signature']");
//        SeleniumDriverInstance.scrollToElement("//div[text()='New Signature']");

        //validate the new created signature
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.createdSignature(getData("Signature Name")))) {
            error = "Failed to scroll down to the Signature: " + getData("Signature Name");
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.createdSignature(getData("Signature Name")))) {
            error = "Failed to wait for the Signature: " + getData("Signature Name") + " to be visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the " + getData("Signature Name") + " to be visible.");

        return true;
    }
}
