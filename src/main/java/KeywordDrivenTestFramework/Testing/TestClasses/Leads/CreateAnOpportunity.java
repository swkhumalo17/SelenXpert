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
@KeywordAnnotation(Keyword = "CreateAnOpportunity", createNewBrowserInstance = false)
public class CreateAnOpportunity extends BaseClass {
    String error = "";
    String customerName;
    String title = getData("Title");
    String firstName = getData("First Name");
    String lastName = getData("Last Name");
    String identityNumber = getData("Identity Number");
    String numberType = getData("Number Type");
    String numberType2 = getData("Number Type 2");
    String contactNumber = getData("Contact Number");
    String description = getData("Description");
    String workSite = getData("Work Site");
    String platform = getData("Platform");
    String platform2 = getData("Platform 2");
    String allocateTo = getData("Allocate To");
    CreateCampaign createCampaignObj;

    public CreateAnOpportunity() {
        this.createCampaignObj = new CreateCampaign();
    }

    public TestResult executeTest() {
        if (!navigateToLeadsSideBar()) {
            return narrator.testFailed(error);
        }
        if (!CreateAnOpportunity()) {
            return narrator.testFailed(error);
        }
        if(platform2.equalsIgnoreCase("Sales Manager")) {
            if (platform.equalsIgnoreCase("MFC")) {
                if (!validateTheCreatedOpportunity()) {
                    return narrator.testFailed(error);
                }
            }
        } else if(!platform2.equalsIgnoreCase("Sales Manager")) {
            if (!validateTheCreatedOpportunity()) {
                return narrator.testFailed(error);
            }
        }

        return narrator.finalizeTest("Successfully manually created an opportunity.");
    }

    public boolean navigateToLeadsSideBar() {
        switch (platform) {
            case "PF":
                //Navigate to Leads Tab
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Leads_SideBar())) {
                    error = "Failed to wait for the Leads Tab.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Leads_SideBar())) {
                    error = "Failed to click the Leads Tab.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully clicked the Leads Tab.");

                if (platform2.equalsIgnoreCase("Sales Manager")) {
                    //To Be Actioned By Me page
                    if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.toBeActionedByMe())) {
                        error = "Failed to wait for the 'To Be Actioned By Me' page.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");

                    //Navigate to Leads In Progress
                    if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadsInProgress_tab())){
                        error = "Failed to wait for the 'Leads In Progress' tab.";
                        return false;
                    }
                    if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadsInProgress_tab())){
                        error = "Failed to click the 'Leads In Progress' tab.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to 'Leads In Progress' page.");

                } else {
                    //Validate the navigation to Customer List Page
                    if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.LeadList_Page())) {
                        error = "Failed to wait for the Lead List to be present.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to Lead List Page.");
                }
                break;
            case "MFC":
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

                if (platform2.equalsIgnoreCase("Sales Manager")) {
                    //To Be Actioned By Me page
                    if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.toBeActionedByMe())) {
                        error = "Failed to wait for the 'To Be Actioned By Me' page.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");

                    //Navigate to Opportunities In Progress
                    if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.opportunitiesInProgress_tab())){
                        error = "Failed to wait for the 'Opportunity In Progress' tab.";
                        return false;
                    }
                    if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.opportunitiesInProgress_tab())){
                        error = "Failed to click the 'Opportunity In Progress' tab.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to 'Opportunities In Progress' page.");
                } else {
                    //Validate the navigation to Opportunity Page
                    if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myOpportunityList_Page())) {
                        error = "Failed to wait for the Opportunity to be present.";
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully navigated to Opportunity Page.");
                }
                break;
            default:
                error = "No such platform";
        }

        return true;
    }

    public boolean CreateAnOpportunity() {
        //Create a New Opportunity
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myLeadsCreate_btn())) {
            error = "Failed to wait for Create New button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myLeadsCreate_btn())) {
            error = "Failed to wait for Create New button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Create New button.");

        //Create New Page
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.createNewOpportunity_page())) {
            error = "Failed to wait for the Create New Page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Create New page.");

        //Title
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Title_dropdown())) {
            error = "Failed to click the Title dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Title_option(title))) {
            error = "Failed to wait for the Title option: " + title;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Title_option(title))) {
            error = "Failed to click the Title option: " + title;
            return false;
        }
        narrator.stepPassed("Successfully clicked the title: " + title);

        //First Name
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.firstName_txt(), firstName)) {
            error = "Failed to enter the First Name: " + firstName;
            return false;
        }
        narrator.stepPassed("Successfully enter the First Name: " + firstName);

        //Last Name
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.lastName_txt(), lastName)) {
            error = "Failed to enter the Last Name: " + lastName;
            return false;
        }
        narrator.stepPassed("Successfully enter the Last Name: " + lastName);

        //Identity Number
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.identityNumber_txt(), identityNumber)) {
            error = "Failed to enter the Identity Number: " + identityNumber;
            return false;
        }
        narrator.stepPassed("Successfully enter the Identity Number: " + identityNumber);

        //Number type
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.numberType_dropdown())) {
            error = "Failed to click the Number Type dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.numberType_option(numberType))) {
            error = "Failed to wait for the Number Type option: " + numberType;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.numberType_option(numberType))) {
            error = "Failed to click the Number Type option: " + numberType;
            return false;
        }
        narrator.stepPassed("Successfully clicked the Number Type: " + numberType);

        //Country Code
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.countryCode_dropdown())) {
            error = "Failed to click the Country Code dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.countryCode_option("South Africa (+27)"))) {
            error = "Failed to wait for the Country Code option: " + "South Africa (+27)";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.countryCode_option("South Africa (+27)"))) {
            error = "Failed to click the Country Code option: " + "South Africa (+27)";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Country Code: " + "South Africa (+27)");

        //Contact Number
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.contactNumber_txt(), contactNumber)) {
            error = "Failed to enter the Contact Number: " + contactNumber;
            return false;
        }
        narrator.stepPassed("Successfully enter the Contact Number: " + contactNumber);

        //Description
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.description_txt(), description)) {
            error = "Failed to enter the Description: " + description;
            return false;
        }
        narrator.stepPassed("Successfully enter the Description: " + description);

        //Worksite/Team
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.workSite_dropdown())) {
            error = "Failed to click the Worksite/Team dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.workSite_option(workSite))) {
            error = "Failed to wait for the Worksite/Team option: " + workSite;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.workSite_option(workSite))) {
            error = "Failed to click the Worksite/Team option: " + workSite;
            return false;
        }
        narrator.stepPassed("Successfully clicked the Worksite/Team: " + workSite);

        //Agreement radio button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.declationToShareInfor_rdbtn())) {
            error = "Failed to click the declaration to share information radio button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the declaration to share information radio button.");

        if (platform2.equalsIgnoreCase("Sales Manager")) {
            //Allocate To
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.allocateTo_drpdwn())) {
                error = "Failed to wait for the 'Allocate To' dropdown.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.allocateTo_drpdwn())) {
                error = "Failed to clicked for the 'Allocate To' dropdown.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the 'Allocate To' dropdown.");

            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.allocateTo_option(allocateTo))) {
                error = "Failed to wait for the Allocate To: '" + allocateTo + "'.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.allocateTo_option(allocateTo))) {
                error = "Failed to click the Allocate To: '" + allocateTo + "'.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked Allocate To: '" + allocateTo + "' dropdown.");
        }

        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.submit_btn())) {
            error = "Failed to scroll to the Submit button.";
            return false;
        }
        pause(2000);

        //Number type
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.numberType_dropdown())) {
            error = "Failed to click the Number Type dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.numberType_option(numberType2))) {
            error = "Failed to wait for the Number Type option: " + numberType2;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.numberType_option(numberType2))) {
            error = "Failed to click the Number Type option: " + numberType2;
            return false;
        }
        narrator.stepPassed("Successfully clicked the Number Type: " + numberType2);

        pause(1000);
        //Submit button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
            error = "Failed to click the Submit button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Submit button.");

//        try {
//            //Confirmation message
//            String confirmationMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.confirmOpportunity_msg());
//
//            if (!confirmationMessage.isEmpty()) {
//                narrator.ValidateEqual(confirmationMessage, "Create successfully");
//            } else {
//                error = "Failed due to confirmation message doesn't contain anything.";
//                return false;
//            }
//        } catch (Exception e) {
//            error = e.getMessage();
//            return false;
//        }

        customerName = lastName + ", " + firstName;

        return true;
    }

    public boolean validateTheCreatedOpportunity() {
        //Navigate to Leads Page
        navigateToLeadsSideBar();

        pause(2000);
        //Search for the Customer Name
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.searchBar_btn())) {
            error = "Failed to wait for the Search filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.searchBar_btn())) {
            error = "Failed to click the Search filter button.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.header())){
            error = "Failed to click the header.";
            return false;
        }
        //Clear the text field
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.searchBar_closeBtn())){
            error = "Failed to click the close button.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.searchBar_txt(), customerName)) {
            error = "Failed to enter '" + customerName + "' into the Search.";
            return false;
        }
        WebElement element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.searchBar_txt()));
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);
        narrator.stepPassedWithScreenShot("Successfully searched the customer name: " + customerName);

        pause(5000);

        if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.CustomerName())){
            error = "Failed to wait for the Customer Name: '" + customerName + "' from My Leads List.";
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
