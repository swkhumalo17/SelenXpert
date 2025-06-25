package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "reAssignedAnOpportunity", createNewBrowserInstance = false)
public class reAssignAnOpportunity extends BaseClass {
    String error = "";
    String customerName;
    String worksite_Team = getData("Worksite");
    String multipleAdvisers = getData("Multiple Advisers");
    String[] advisers = getData("Advisers").split(",");

    public TestResult executeTest() {
        if (!reAssignedAnOpportunity()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully re-assigned an opportunity.");
    }

    public boolean reAssignedAnOpportunity() {
        //Navigate to opportunity tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Opportunity_SideBar())) {
            error = "Failed to wait for the Opportunities Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Opportunity_SideBar())) {
            error = "Failed to click the Opportunities Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Opportunities SideBar.");

        //To Be Actioned By Me page
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.toBeActionedByMe())) {
            error = "Failed to wait for the 'To Be Actioned By Me' page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");

        //Capture the name of the customer we are re-assign it to an a
        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        //Under Action Column Click Dropdown Trigger.
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionFirstTrigger_btn())) {
            error = "Failed to wait for the action Trigger button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionFirstTrigger_btn())) {
            error = "Failed to click the action Trigger button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action Trigger button.");

        //Validate the trigger is expanded
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionOption3("Re-assign"))) {
            error = "Failed to wait for the Action >> Re-assign button.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionOption("Close"))) {
            error = "Failed to wait for the Action >> Close button.";
            return false;
        }
        narrator.stepPassed("Successfully validated the Re-assign and Close are visible.");

        //Click Re-assign option.
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionOption3("Re-assign"))) {
            error = "Failed to click the Action >> Re-assign button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Re-assign option.");

        //Lead Re-assign pop up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadReAssign_popup())) {
            error = "Failed to wait for the close the lead re-assign pop-up.";
            return false;
        }

        //Worksite/Team
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.leadReAssignWorksite(worksite_Team))) {
            error = "Failed to wait for the Lead Allocate >> Worksite/Team: " + worksite_Team;
            return false;
        }
        if (!SeleniumDriverInstance.doubleClickElementbyXpath(DAE_PageObject.leadReAssignWorksite(worksite_Team))) {
            error = "Failed to click the Lead Allocate >> Worksite/Team: " + worksite_Team;
            return false;
        }
        narrator.stepPassed("Successfully clicked the Lead Allocate >> Worksite/Team: " + worksite_Team);

        if (multipleAdvisers.equalsIgnoreCase("Yes")) {
            for (int i = 0; i < 2; i++) {
                //Multiple advisers
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.adviser_chckbx(advisers[i]))) {
                    error = "Failed to wait for adviser '" + advisers[i] + "' checkbox.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.adviser_chckbx(advisers[i]))) {
                    error = "Failed to click the adviser '" + advisers[i] + "' checkbox.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully clicked the adviser: " + advisers[i]);

            }
        } else {
            //Just a single adviser
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadReAssignedAdviser_radiobtn(advisers[0]))) {
                error = "Failed to wait for adviser '" + advisers[0] + "' radio button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadReAssignedAdviser_radiobtn(advisers[0]))) {
                error = "Failed to click the adviser '" + advisers[0] + "' radio button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the adviser: " + advisers[0]);
        }

        //Confirm button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Submit button.");

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignArchiveMessage())) {
            error = "Failed to wait for the error message to be visible";
            return false;
        }
        String leadsClose_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

        if (!leadsClose_Message.isEmpty()) {
            narrator.ValidateEqual(leadsClose_Message, "Assigned successfully");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message: " + leadsClose_Message);
        } else {
            error = "Failed to validate Successful message: " + leadsClose_Message;
            return false;
        }

        return true;
    }
}
