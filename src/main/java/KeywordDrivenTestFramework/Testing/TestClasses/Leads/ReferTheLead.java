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
@KeywordAnnotation(Keyword = "ReferTheLead", createNewBrowserInstance = false)
public class ReferTheLead extends BaseClass {
    String error = "";
    String customerName, chooseList;
    String referType = getData("Refer Type");
    String referTo = getData("Refer To");
    String adviser = getData("Adviser");
    String salesManager = getData("Sales Manager");
    String externalParty = getData("External Party");
    String platform = testData.getData("Platform");
    String customerNeeds = getData("Customer Needs");
    CreateAnOpportunity createAnOpportunityObj;

    public ReferTheLead() {
        this.createAnOpportunityObj = new CreateAnOpportunity();
    }


    public TestResult executeTest() {
        if (!ReferALead()) {
            return narrator.testFailed(error);
        }
        if (!validateTheReferredLead()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Referred a lead to '" + referTo + "': '" + chooseList + "'.");
    }

    public boolean ReferALead() {
        if(getData("Is regression").equalsIgnoreCase("Yes")){
            if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myLeads_Tab())){
                error = "Failed to wait for My Leads Tab.";
                return false;
            }
            if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myLeads_Tab())){
                error = "Failed to click the My Leads Tab.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully navigated to My Leads Tab.");

            if(!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myLeadsListTable())){
                error = "Failed to scroll to My Leads List page.";
                return false;
            }

        }
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

        //Click Archive option.
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionOption1("Refer"))) {
            error = "Failed to click the Action >> Refer button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Refer option.");

        //Refer Lead popup
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.referTheLead_popup())) {
            error = "Failed to wait for the refer the lead pop-up.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referType_option_rdbtn(referType))) {
            error = "Failed to click the refer the lead >> Refer Type radio option: '" + referType + "'.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referTo_drpdwn())) {
            error = "Failed to click the refer to dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.referTo_option(referTo))) {
            error = "Failed to wait for the refer to: " + referTo;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referTo_option(referTo))) {
            error = "Failed to click the refer to option: " + referTo;
            return false;
        }
        narrator.stepPassed("Successfully clicked the refer to option: " + referTo);

        if (referTo.equalsIgnoreCase("Adviser")) {
            chooseList = adviser;
        } else if (referTo.equalsIgnoreCase("Sales Manager")) {
            chooseList = salesManager;
        } else if (referTo.equalsIgnoreCase("External Party")) {
            chooseList = externalParty;
        }

        switch (referTo) {
            case "Adviser":
                //Please choose an Adviser placeholder is visible
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.chooseAnAdviser_placeholder())) {
                    error = "Failed to wait for the 'Please choose an Adviser placeholder' to be visible.";
                    return false;
                }

                String adviser_placeholder = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.chooseAnAdviser_placeholder());

                if (narrator.ValidateEqual(adviser_placeholder, "Please Choose an Adviser*")) {
                    narrator.stepPassed("Successfully validated the 'Please choose an Adviser' placeholder is visible.");
                } else {
                    error = "Failed to validate the Placeholder: '" + adviser_placeholder + "' with expected placeholder: 'Please Choose an Adviser*'.";
                    return false;
                }

                break;
            case "Sales Manager":
                //Please choose a Sales Manager placeholder is visible
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.chooseAnAdviser_placeholder())) {
                    error = "Failed to wait for the 'Please Choose a Sales Manager' to be visible.";
                    return false;
                }

                adviser_placeholder = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.chooseAnAdviser_placeholder());

                if (narrator.ValidateEqual(adviser_placeholder, "Please Choose a Sales Manager*")) {
                    narrator.stepPassed("Successfully validated the 'Please Choose a Sales Manager' placeholder is visible.");
                } else {
                    error = "Failed to validate the Placeholder: '" + adviser_placeholder + "' with expected placeholder: 'Please Choose a Sales Manager*'.";
                    return false;
                }

                break;
            case "External Party":
                //External Party placeholder is visible
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.chooseAnAdviser_placeholder())) {
                    error = "Failed to wait for the 'External Party*' to be visible.";
                    return false;
                }

                adviser_placeholder = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.chooseAnAdviser_placeholder());

                if (narrator.ValidateEqual(adviser_placeholder, "External Party*")) {
                    narrator.stepPassed("Successfully validated the 'External Party*' placeholder is visible.");
                } else {
                    error = "Failed to validate the Placeholder: '" + adviser_placeholder + "' with expected placeholder: 'External Party*'.";
                    return false;
                }

                break;
            default:
                error = "Failed due to no Refer To: '" + referTo + "' is available.";
                return false;
        }

        if (platform.equalsIgnoreCase("MFC") && referTo.equalsIgnoreCase("Adviser")) {
            //Customer Needs
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customerNeeds_drpdwn())) {
                error = "Failed to click the Customer Needs dropdown.";
                return false;
            }
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customerNeeds_option(customerNeeds))) {
                error = "Failed to click the Refer Lead >> Please Choose a Customer need option: '" + customerNeeds + "'.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customerNeeds_option(customerNeeds))) {
                error = "Failed to click the Refer Lead >> Please Choose a Customer need option: '" + customerNeeds + "'.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully selected the Customer needs '" + customerNeeds + "' from Customer Needs field.");

        }
        //Choose an chooseList
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referLeadChoose_drpdwn())) {
            error = "Failed to click the Refer Lead >> Please Choose an " + referTo + " dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.referLeadChoose_option(chooseList))) {
            error = "Failed to click the Refer Lead >> Please Choose an " + referTo + " option: '" + chooseList + "'.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referLeadChoose_option(chooseList))) {
            error = "Failed to click the Refer Lead >> Please Choose an " + referTo + " option: '" + chooseList + "'.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully selected the '" + chooseList + "' to refer a lead to.");

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
        String referred_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

        if (!referred_Message.isEmpty()) {
            narrator.ValidateEqual(referred_Message, "Referred successfully");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message: " + referred_Message);
        } else {
            error = "Failed to validate Successful message: " + referred_Message;
            return false;
        }

        return true;
    }

    public boolean validateTheReferredLead() {
        //Navigate to Leads >> My Referrals Tab
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myReferrals_Tab())) {
            error = "Failed to wait for the Leads >> My Referrals Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked and Navigated to the Closed Tab.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myReferralsCustomerName())) {
            error = "Failed to wait for the My Referrals table list to be visible.";
            return false;
        }

        //Search for the Customer Name
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myReferralsSearchBar_btn())) {
            error = "Failed to wait for the Search filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myReferralsSearchBar_btn())) {
            error = "Failed to click the Search filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.myReferralsSearchBar_txt(), customerName)) {
            error = "Failed to enter '" + customerName + "' into the Search.";
            return false;
        }
        WebElement element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.myReferralsSearchBar_txt()));
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);
        narrator.stepPassedWithScreenShot("Successfully searched the customer name: " + customerName);

        pause(4000);

        String referredCustomerName_tb = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myReferralsCustomerName());
        String referralType_tb = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.referralType());
        String referTo_tb = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.referTo());
        String myReferralsStatus_tb = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myReferralsStatus());

        //validate the customer Name
        try {
            if (referredCustomerName_tb.equalsIgnoreCase(customerName)) {
                narrator.ValidateEqual(customerName, referredCustomerName_tb);
                narrator.stepPassedWithScreenShot("Successfully validated the referred customer name: " + referredCustomerName_tb);
            } else {
                error = "Failed to validated the referred customer name: '" + referredCustomerName_tb + "' with expected customer name: '" + customerName + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Referral Type
        try {
            if (referralType_tb.equalsIgnoreCase(referType)) {
                narrator.ValidateEqual(referralType_tb, referType);
                narrator.stepPassedWithScreenShot("Successfully validated the Referral Type: '" + referralType_tb + "' with the expected referral Type: " + referType);
            } else {
                error = "Failed to validated the Referral Type: '" + referralType_tb + "' with expected Referral Type: '" + referType;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Validate the Refer To
        try {
            if (referTo_tb.equalsIgnoreCase(chooseList)) {
                narrator.ValidateEqual(referTo_tb, chooseList);
                narrator.stepPassedWithScreenShot("Successfully validated the Refer To: '" + referTo_tb + "' with expected Refer To: " + chooseList);
            } else {
                error = "Failed to validate the Refer To: '" + referTo_tb + "' with expected Refer To: " + chooseList;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //validate the status
        try {
            if (myReferralsStatus_tb.equalsIgnoreCase("Referred")) {
                narrator.ValidateEqual(myReferralsStatus_tb, "Referred");
                narrator.stepPassedWithScreenShot("Successfully validated the status: '" + myReferralsStatus_tb + "' with 'Referred'.");
            } else {
                error = "Failed to validate the status: '" + myReferralsStatus_tb + "' with 'Referred'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }


}
