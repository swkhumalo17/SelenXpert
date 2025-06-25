package KeywordDrivenTestFramework.Testing.TestClasses.S_T_Claims;

import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.awt.*;

/**
 * @author SKHUMALO on 2023/08/01.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "Return_Claim", createNewBrowserInstance = false)
public class Return_Claims extends BaseClass {
    String error = "";
    String adviserName = "";
    Approve_Claims Approve_ClaimsObj;
    String waitingStatus;
    String approvalStatus;
    String reasonForReturning = getData("Reason For Returning");

    public Return_Claims() {
        this.Approve_ClaimsObj = new Approve_Claims();
        ;
    }

    public TestResult executeTest() {
        if (!claimReturn()) {
            narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully Returned a claim for Adviser: " + adviserName);
    }

    public boolean claimReturn() {
        //access navigate function from approve claims class
        Approve_ClaimsObj.navigateToSTClaims();

        //Approve a claim from a waiting list
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.waitingApproval_Tab())) {
            error = "Failed to wait for the waiting list Tab.";
            return false;
        }

        //Extract data for later validation
        adviserName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.adviserName());
        waitingStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.waitingStatus());

        if (!waitingStatus.isEmpty()) {
            narrator.ValidateEqual(waitingStatus, "Awaiting Approval");
            narrator.stepPassed("Successfully validated the status is '" + waitingStatus + "'.");
        } else {
            error = "Failed to validate the Status is 'Awaiting Approval'";
            return false;
        }

        //click view on the first claim
        if(!SeleniumDriverInstance.scrollToElement(DAE_PageObject.waitingView_btn())){
            error = "Failed to scroll to an element.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.waitingView_btn())) {
            error = "Failed to wait for view button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.waitingView_btn())) {
            error = "Failed to click the view button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the view button.");

        approvalStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.approvalStatus());

        //Click Return button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.return_btn())) {
            error = "Failed to wait for the return button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.return_btn())) {
            error = "Failed to click the return button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the return button.");

        //Return to Adviser
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.returnToAdviser())) {
            error = "Failed to wait for the Return to adviser pop up.";
            return false;
        }

        //Reason for returning to adviser
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.reasonToReturn_drpdwn())) {
            error = "Failed to wait for the 'Reason for returning to adviser' pop up ";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.reasonToReturn_drpdwn())) {
            error = "Failed to click the 'Reason for returning to adviser' pop up dropdown.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked 'Reason for returning to adviser' dropdown.");
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.reasonToReturn_option(reasonForReturning))) {
            error = "Failed to wait for the 'Reason for returning to adviser' pop up option.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.reasonToReturn_option(reasonForReturning))) {
            error = "Failed to click the 'Reason for returning to adviser' pop up option.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked 'Reason for returning to adviser' option: '" + reasonForReturning + "' .");

       //Save button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.returnToAdviser_Save_btn())) {
            error = "Failed to wait for Save button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.returnToAdviser_Save_btn())) {
            error = "Failed to click Save button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the save button");

        pause(4000);
        approvalStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.approvalStatus());

        if(!approvalStatus.isEmpty()){
            narrator.ValidateEqual(approvalStatus, "Adviser to update");
        }else{
            error = "Failed to validate the status after returning a claim to an adviser.";
            return false;
        }

        return true;
    }
}
