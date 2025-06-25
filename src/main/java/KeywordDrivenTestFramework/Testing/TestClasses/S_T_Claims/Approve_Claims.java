package KeywordDrivenTestFramework.Testing.TestClasses.S_T_Claims;

import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2023/08/01.
 * @project DAE-Automation-Framework
 */

@KeywordAnnotation(Keyword = "Approve_Claims", createNewBrowserInstance = false)
public class Approve_Claims extends BaseClass {
    String error = "";
    String adviserName, waitingStatus, approvalStatus;

    public Approve_Claims() {
    }

    public TestResult executeTest() {
        if (!navigateToSTClaims()) {
            return narrator.testFailed(error);
        }
        if(!claimApproval()){
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully approved a claim for Adviser: " + adviserName);
    }

    public boolean navigateToSTClaims() {
        //Navigate to S&T Claims
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.S_T_ClaimsSidebar())) {
            error = "Failed to wait for the S&T Claims Sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.S_T_ClaimsSidebar())) {
            error = "Failed to wait for the S&T Claims Sidebar.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'S & T Claims' Sidebar.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.S_T_ClaimsHeader())) {
            error = "Failed to wait for the 'S & T Claims' header.";
            return false;
        }

        return true;
    }

    public boolean claimApproval() {
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
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.waitingView_btn())) {
            error = "Failed to wait for view button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.waitingView_btn())) {
            error = "Failed to click the view button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the view button.");

        //Click approve button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.approve_btn())) {
            error = "Failed to wait for the approve button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.approve_btn())) {
            error = "Failed to click the approve button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the approve button.");

        pause(4000);
        approvalStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.approvalStatus());

        //check if the status changed from waiting approval to approved
        if (narrator.ValidateEqual(waitingStatus, approvalStatus)) {
            error = "Failed to validate '" + waitingStatus + "' with '" + approvalStatus + "'";
            return false;
        } else {
            narrator.ValidateEqual(waitingStatus, approvalStatus);
            narrator.stepPassedWithScreenShot("Successfully validate the status changed from '" + waitingStatus + "' to '" + approvalStatus + "'.");
        }

        //Approve Claim button
        if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.approveClaim_btn())){
            error = "Failed to wait for the 'Approve Claim' button.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.approveClaim_btn())){
            error = "Failed to click the 'Approve Claim' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Approve Claim' button.");

        //wait for the Declaration pop up
        if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.declaration_popup())){
            error = "Failed to wait for the Declaration pop up.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.declaration_chckbx())){
            error = "Failed to click the Declaration checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the declaration checkbox.");

        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.declaration_OK_btn())){
            error = "Failed to click the OK button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully approved claim for Adviser: " + adviserName);

        return true;
    }
}
