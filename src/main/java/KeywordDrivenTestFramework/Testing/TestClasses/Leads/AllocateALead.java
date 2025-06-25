package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "AllocateALead", createNewBrowserInstance = false)
public class AllocateALead extends BaseClass {
    String error = "";
    String customerName;
    String worksite_Team = getData("Worksite");
    String multipleAdvisers = getData("Multiple Advisers");
    String[] advisers = getData("Advisers").split(",");
    String platform2 = getData("Platform 2");
    String platform = getData("Platform");
    CreateAnOpportunity createAnOpportunityObj;

    public AllocateALead() {
        this.createAnOpportunityObj = new CreateAnOpportunity();
    }


    public TestResult executeTest() {
        if (!AllocateALead()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Allocated a lead.");
    }

    public boolean AllocateALead() {
        if (platform2.equalsIgnoreCase("Sales Manager")) {
            //Navigate to the Leads Tab
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Leads_SideBar())) {
                error = "Failed to wait for the Leads Tab.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Leads_SideBar())) {
                error = "Failed to click the Leads Tab.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Leads SideBar.");

            //To Be Actioned By Me page
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.toBeActionedByMe_Tab())) {
                error = "Failed to click the 'To Be Actioned By Me' Tab.";
                return false;
            }
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.toBeActionedByMe())) {
                error = "Failed to wait for the 'To Be Actioned By Me' page.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        if (platform2.equalsIgnoreCase("Sales Manager")) {
            //Action Allocate and Close button
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionAllocate_btn())) {
                error = "Failed to wait for the Action >> Allocate button.";
                return false;
            }
            //Click Close option.
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionAllocate_btn())) {
                error = "Failed to click the Action >> Close button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Allocate button.");

        } else {
            //Validate Edit, Close options are inactive and Archive, Revive are active.
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionCloseLead_btn())) {
                error = "Failed to wait for the Action >> Close button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionCloseLead_btn())) {
                error = "Failed to click the Action >> Close button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Close button.");
        }

        //Lead Allocate pop up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadAllocate_popup())) {
            error = "Failed to wait for the close the lead pop-up.";
            return false;
        }

        //Worksite/Team
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadAllocateWorksite(worksite_Team))) {
            error = "Failed to wait for the Lead Allocate >> Worksite/Team: " + worksite_Team;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadAllocateWorksite(worksite_Team))) {
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
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.adviser_chckbx(advisers[0]))) {
                error = "Failed to wait for adviser '" + advisers[0] + "' checkbox.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.adviser_chckbx(advisers[0]))) {
                error = "Failed to click the adviser '" + advisers[0] + "' checkbox.";
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
            narrator.ValidateEqual(leadsClose_Message, "Allocated successfully");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message: " + leadsClose_Message);
        } else {
            error = "Failed to validate Successful message: " + leadsClose_Message;
            return false;
        }

        return true;
    }
}
