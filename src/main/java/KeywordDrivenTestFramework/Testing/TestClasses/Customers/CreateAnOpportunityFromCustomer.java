package KeywordDrivenTestFramework.Testing.TestClasses.Customers;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Campaigns.CreateCampaign;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "CreateAnOpportunityFromCustomer", createNewBrowserInstance = false)
public class CreateAnOpportunityFromCustomer extends BaseClass {
    String error = "";
    String customerName;

    CreateCampaign createCampaignObj;

    public CreateAnOpportunityFromCustomer() {
        this.createCampaignObj = new CreateCampaign();
    }

    public TestResult executeTest() {
        if (!navigateToCustomerList()) {
            return narrator.testFailed(error);
        }
        if (!CreateAnOpportunityFromCustomerList()) {
            return narrator.testFailed(error);
        }
        if (!validateTheCreatedOpportunity()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully created an opportunity from a customer list.");
    }

    public boolean navigateToCustomerList() {
        //Navigate to the Customer list
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Customers_SideBar())) {
            error = "Failed to wait for the Customers Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Customers_SideBar())) {
            error = "Failed to click the Customers Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Customers Tab.");

        //Validate the navigation to Customer List Page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.CustomerList_Page())) {
            error = "Failed to wait for the Customer List to be present.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Customer List Page.");

        return true;
    }

    public boolean CreateAnOpportunityFromCustomerList() {
        //Action List Dropdown Trigger. Create Opportunity
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionCreateOpportunityFromCustomerList_btn())) {
            error = "Failed to wait for the Action >> Create Opportunity button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionTrigger_btn())) {
            error = "Failed to click the Action >> Create Opportunity button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> Create Opportunity button.");

        //Confirmation Pop-Up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
            error = "Failed to wait for the confirmation Pop-up.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
            error = "Failed to click the confirmation Pop-up.";
            return false;
        }
        narrator.stepPassed("Successfully confirmed '" + getData("Confirmation") + "' to create an opportunity.");

        try {
            //Confirmation message
            String confirmationMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.confirmOpportunity_msg());

            if (!confirmationMessage.isEmpty()) {
                narrator.ValidateEqual(confirmationMessage, "Opportunity has been created successfully.");
            } else {
                error = "Failed due to confirmation message doesn't contain anything.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerName_txt());

        return true;
    }

    public boolean validateTheCreatedOpportunity() {
        //Navigate to Leads Page
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Leads_SideBar())) {
            error = "Failed to wait for the Leads Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Leads_SideBar())) {
            error = "Failed to click the Leads Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Leads Tab.");

        //Validate the navigation to Customer List Page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.LeadList_Page())) {
            error = "Failed to wait for the Lead List to be present.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Lead List Page.");

        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myLeadsCreate_btn())) {
            error = "Failed to scroll down.";
            return false;
        }

        String status = "";
        String customerName_txt = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());
        try {
            if (customerName_txt.contains(customerName)) {
                status = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListStatus(customerName));
                narrator.stepPassedWithScreenShot("Successfully manually created an opportunity for Customer Name: '" + customerName + "' and status: '" + status + "'");
            } else {
                error = "Failed to find Customer Name : '" + customerName + "' from My Leads List.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }
}
