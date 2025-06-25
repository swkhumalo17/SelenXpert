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
@KeywordAnnotation(Keyword = "CloseTheLead", createNewBrowserInstance = false)
public class CloseTheLead extends BaseClass {
    String error = "";
    String customerName;
    String reason = getData("Reason");
    String status = getData("Status");
    String closeReason_txt;
    String platform = getData("Platform");
    String platform2 = getData("Platform 2");
    String Description = getData("Description");
    CreateAnOpportunity createAnOpportunityObj;

    public CloseTheLead() {
        this.createAnOpportunityObj = new CreateAnOpportunity();
    }


    public TestResult executeTest() {
        if (!CloseALead()) {
            return narrator.testFailed(error);
        }
        if (!validateTheCreatedLeadIsClosed()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Closed a lead.");
    }

    public boolean CloseALead() {
        if (platform2.equalsIgnoreCase("Sales Manager") && platform.equalsIgnoreCase("PF")) {
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
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.toBeActionedByMe())) {
                error = "Failed to wait for the 'To Be Actioned By Me' page.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");
        } else if (platform2.equalsIgnoreCase("Sales Manager") && platform.equalsIgnoreCase("MFC")) {
            //Navigate to the Opportunities Tab
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
        }
        pause(2000);
        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        //Under Action Column Click Dropdown Trigger.
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to wait for the Action >> More button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to click the Action >> More button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> More button.");

        //Click Close option.
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionOption1("Close"))) {
            error = "Failed to click the Action >> Close button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Close option.");

        //Close pop up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.closeTheLead_popup())) {
            error = "Failed to wait for the close the lead pop-up.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closeTheLead_Reason_drpdwn())) {
            error = "Failed to click the close the lead >> Reason dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.closeTheLead_Reason_option(reason))) {
            error = "Failed to wait for the close the lead >> Reason option: " + reason;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closeTheLead_Reason_option(reason))) {
            error = "Failed to click the close the lead >> Reason option: " + reason;
            return false;
        }
        narrator.stepPassed("Successfully closed the lead >> Reason option: " + reason);

        if (!reason.equalsIgnoreCase("Other")) {
            //Description
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closeTheLead_Description_drpdwn())) {
                error = "Failed to click the close the lead >> Description dropdown.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closeTheLead_Description_option(Description))) {
                error = "Failed to click the close the lead >> Description option: " + Description;
                return false;
            }
            narrator.stepPassed("Successfully closed the lead >> Description option: " + Description);
        } else {
            //if reason is 'Other' you'll have to provide the description in 80 or less words
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.closeTheLead_Description_txt(), Description)) {
                error = "Failed to enter '" + Description + "' from the close the lead >> Description ";
                return false;
            }
            narrator.stepPassed("Successfully closed the lead >> Description option: " + Description);
        }

        closeReason_txt = reason + "-" + Description;

        //Submit button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
            error = "Failed to click the Submit button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Submit button.");

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignArchiveMessage())) {
            error = "Failed to wait for the error message to be visible";
            return false;
        }
        String leadsClose_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

        if (!leadsClose_Message.isEmpty()) {
            narrator.ValidateEqual(leadsClose_Message, "Lead closed");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message: " + leadsClose_Message);
        } else {
            error = "Failed to validate Successful message: " + leadsClose_Message;
            return false;
        }

        return true;
    }

    public boolean validateTheCreatedLeadIsClosed() {
        //Navigate to Leads >> Closed Tab
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Closed_Tab())) {
            error = "Failed to wait for the Leads >> Closed Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked and Navigated to the Closed Tab.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.closedCustomerName())) {
            error = "Failed to wait for the closed table list to be visible.";
            return false;
        }

        WebElement element;

        if (!platform2.equalsIgnoreCase("Sales Manager")) {
            if (platform.equalsIgnoreCase("MFC") || platform.equalsIgnoreCase("PF")) {
                //Search for the Customer Name
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.closedOpportunitySearch_btn())) {
                    error = "Failed to wait for the Search filter button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closedOpportunitySearch_btn())) {
                    error = "Failed to click the Search filter button.";
                    return false;
                }
                if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.closedOpportunitySearchBar_txt(), customerName)) {
                    error = "Failed to enter '" + customerName + "' into the Search.";
                    return false;
                }
            }
            element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.closedOpportunitySearchBar_txt()));
        } else {
            //Search for the Customer Name
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.closedMyLeadsSearch_btn())) {
                error = "Failed to wait for the Search filter button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closedMyLeadsSearch_btn())) {
                error = "Failed to click the Search filter button.";
                return false;
            }
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.closedMyLeadsSearchBar_txt(), customerName)) {
                error = "Failed to enter '" + customerName + "' into the Search.";
                return false;
            }
            element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.closedMyLeadsSearchBar_txt()));
        }

        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);
        narrator.stepPassedWithScreenShot("Successfully searched the customer name: " + customerName);

        pause(4000);
        String closedCustomerName, customerStatus, closeReason;

        if (!platform2.equalsIgnoreCase("Sales Manager") && platform.equalsIgnoreCase("MFC")) {
            customerStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerStatus());
            closeReason = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.closeReason());
        } else {
            customerStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerStatus2(customerName));
            closeReason = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.closeReason2(customerName));
        }

        closedCustomerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.closedCustomerName());

        //validate the customer Name
        try {
            if (closedCustomerName.equalsIgnoreCase(customerName)) {
                narrator.ValidateEqual(customerName, closedCustomerName);
                narrator.stepPassedWithScreenShot("Successfully validated the closed customer name: " + closedCustomerName);
            } else {
                error = "Failed to validated the closed customer name: '" + closedCustomerName + "' with expected customer name: '" + customerName + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //validate the status
        try {
            if (status.equalsIgnoreCase(customerStatus)) {
                narrator.ValidateEqual(status, customerStatus);
                narrator.stepPassedWithScreenShot("Successfully validated the status is '" + status + "' of the customer name: " + closedCustomerName);
            } else {
                error = "Failed to validated the status is '" + status + "' with expected status of '" + customerStatus + "' for customer name '" + closedCustomerName + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Validate the close reason
        try {
            if (closeReason_txt.equalsIgnoreCase(closeReason)) {
                narrator.ValidateEqual(closeReason_txt, closeReason);
                narrator.stepPassedWithScreenShot("Successfully validated the close reason '" + closeReason_txt + "' with expected close reason of '" + closeReason + "' of the customer name: " + closedCustomerName);
            } else {
                error = "Failed to validated the close reason is '" + closeReason_txt + "' with expected close reason of '" + closeReason + "' for the customer name '" + closedCustomerName + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }
}
