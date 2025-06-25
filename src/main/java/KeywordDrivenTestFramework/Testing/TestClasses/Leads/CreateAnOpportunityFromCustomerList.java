package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Campaigns.CreateCampaign;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "CreateAnOpportunityFromCustomerList", createNewBrowserInstance = false)
public class CreateAnOpportunityFromCustomerList extends BaseClass {
    String error = "";
    String customerName;
    String platform = getData("Platform");
    CreateCampaign createCampaignObj;

    public CreateAnOpportunityFromCustomerList() {
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
        //Navigate to Performance
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.burgerMenu())) {
            error = "Failed to wait for the burger menu.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.burgerMenu())) {
            error = "Failed to click the burger menu to expand.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully expanded the burger menu.");

        //Customers dropdown
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customersSidebar_drpdwn())) {
            error = "Failed to wait for the Customers dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customersSidebar_drpdwn())) {
            error = "Failed to click the Customers dropdown.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully expanded the Customers dropdown");

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
        pause(3000);
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customerName_txt(), 1000)) {
            error = "Failed to wait for the Customer Name to be visible.";
            return false;
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerName_txt());

        //Action List Dropdown Trigger. Create Opportunity
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionCreateOpportunityFromCustomerList_btn())) {
            error = "Failed to wait for the Action >> Create Opportunity button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionCreateOpportunityFromCustomerList_btn())) {
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
        narrator.stepPassed("Successfully created an opportunity for '" + customerName + "'.");

        try {
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.confirmOpportunity_msg())) {
                error = "Failed to wait for the confirmation message to appear.";
                return false;
            }

            //Confirmation message
            String confirmationMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.confirmOpportunity_msg());

            if (!confirmationMessage.isEmpty()) {
                narrator.ValidateEqual(confirmationMessage, "Opportunity created successfully.");
            } else {
                error = "Failed due to confirmation message doesn't contain anything.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean validateTheCreatedOpportunity() {
        if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(DAE_PageObject.confirmOpportunity_msg())) {
            error = "Failed to wait for the successfully message to disappear.";
            return false;
        }
        if (platform.equalsIgnoreCase("PF")) {
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


        } else if (platform.equalsIgnoreCase("MFC")) {
            //Navigate to Opportunity
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Opportunity_SideBar())) {
                error = "Failed to wait for the Opportunity Tab.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Opportunity_SideBar())) {
                error = "Failed to click the Opportunity Tab.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Opportunity Tab.");

            //Validate the navigation to Opportunity Page
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myOpportunityList_Page())) {
                error = "Failed to wait for the Opportunity to be present.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully navigated to Opportunity Page.");
        }

        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myLeadsListTable_Option("LEADS"))) {
            error = "Failed to scroll down My Leads List.";
            return false;
        }

        //Search for the opportunity just created
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
        WebElement element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.closedMyLeadsSearchBar_txt()));
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);

        pause(4000);
        narrator.stepPassedWithScreenShot("Successfully searched the customer name: " + customerName);

        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.CustomerName())) {
            error = "Failed to scroll down My Leads List: Customer name.";
            return false;
        }

        String status = "";
        String customerName_txt = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());
        try {
            if (customerName_txt.contains(customerName)) {
                status = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListStatus(customerName));
                narrator.ValidateEqual(customerName_txt, customerName);
                narrator.stepPassedWithScreenShot("Successfully manually created an opportunity for Customer Name: '" + customerName + "' and status: '" + status + "'");
            } else {
                error = "Failed to find Customer Name : '" + customerName + "' from My Leads/Opportunity List.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        return true;
    }
}
