package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "ContactACustomer", createNewBrowserInstance = false)
public class ContactACustomer extends BaseClass {
    String error = "";
    public static String customerName;
    String submittedSuccessfully_msg;
    String pipeLineType = getData("Pipeline Type");
    String platform = getData("Platform");
    String isCustomerContactedSuccessfully = getData("isCustomerContactedSuccessfully");
    String callNote = getData("Call Note");
    String status = getData("Status");
    String leadStatus = getData("Lead Status");
    private SetUpAppointment SetUpAppointmentObj;

    public ContactACustomer() {

    }

    public TestResult executeTest() {
        if (!navigateToLeadsSideBar()) {
            return narrator.testFailed(error);
        }
        if (!ContactACustomerRevised()) {
            return narrator.testFailed(error);
        }
        if (!validateTheCustomerContacted()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Contacted the customer: '" + customerName);
    }

    public boolean navigateToLeadsSideBar() {
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

            //Validate the navigation to Leads List Page
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

        return true;
    }

    public boolean ContactACustomerRevised() {
        //Click FILTER button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.filter_btn())) {
            error = "Failed to wait for the Filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.filter_btn())) {
            error = "Failed to click the Filter button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Filter button.");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //Close Lead Source
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadSource_drpdwn())) {
            error = "Failed to wait for the Lead Source dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadSource_drpdwn())) {
            error = "Failed to wait for the Lead Source dropdown.";
            return false;
        }
        //Expand Lead Status
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadStatus_drpdwn())) {
            error = "Failed to wait for the Lead Status dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(leadStatus))) {
            error = "Failed to wait for the Filter Lead Status: '" + leadStatus + "' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(leadStatus))) {
            error = "Failed to click the Filter Lead Status: '" + leadStatus + "' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Lead Status: '" + leadStatus + "' checkbox.");

        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to click the Filter >> Apply button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Filter >> Apply button.");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        //Action List Dropdown Trigger. Contact a Customer
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to wait for the Action >> 'Contact Customer' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to click the Action >> 'Contact Customer' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> 'Contact Customer' button.");


        if (platform.equalsIgnoreCase("PF")) {
            //confirmation popup
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
                error = "Failed to wait for the confirmation pop-up";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
                error = "Failed to click the confirmation pop-up";
                return false;
            }
            narrator.stepPassed("Successfully clicked OK in alert pop-up.");

            pause(1000);


            // Switch the focus back to the web browser
            SeleniumDriverInstance.Driver.switchTo().window(SeleniumDriverInstance.Driver.getWindowHandle());
        }

        //Contact Customer pop-up
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referType_option_rdbtn(isCustomerContactedSuccessfully))) {
            error = "Failed to click the refer the lead >> Contact Customer radio option: '" + isCustomerContactedSuccessfully + "'.";
            return false;
        }
        //Click the Confirm Button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked Confirm button.");

        if (isCustomerContactedSuccessfully.equalsIgnoreCase("Yes")) {
            //Call log should appear
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.callLog_popup())) {
                error = "Failed to wait for the Call Log to appear.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Call Log successfully visible.");

            //Add note text area
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.addCallNote(), callNote)) {
                error = "Failed to enter a Add Call Note: " + callNote;
                return false;
            }
            narrator.stepPassed("Successfully entered a Call Note: " + callNote);

            //Click the Submit Button
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
                error = "Failed to click the Submit button.";
                return false;
            }
            submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());
            narrator.stepPassedWithScreenShot("Successfully clicked Submit button.");
        }

        return true;
    }

    public boolean ContactACustomer() {
        //Select Opportunities
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myLeadsListTable_Option(pipeLineType))) {
            error = "Failed to wait for the Pipeline Type: " + pipeLineType;
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myLeadsListTable_Option(pipeLineType))) {
            error = "Failed to click the Pipeline Type: " + pipeLineType;
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Pipeline Type: '" + pipeLineType + "' and navigated to '" + pipeLineType + "'");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        if (platform.equalsIgnoreCase("MFC")) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myOpportunityList_Page())) {
                error = "Failed to scroll to my opportunity list header.";
                return false;
            }
        } else {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.LeadList_Page())) {
                error = "Failed to scroll to myLeads List header.";
                return false;
            }
        }

        //Click FILTER button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.filter_btn())) {
            error = "Failed to wait for the Filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.filter_btn())) {
            error = "Failed to click the Filter button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Filter button.");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //Close Lead Source
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.leadSource_drpdwn())) {
            error = "Failed to wait for the Lead Source dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadSource_drpdwn())) {
            error = "Failed to wait for the Lead Source dropdown.";
            return false;
        }
        //Expand Lead Status
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.leadStatus_drpdwn())) {
            error = "Failed to wait for the Lead Status dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(leadStatus))) {
            error = "Failed to wait for the Filter Lead Status: '" + leadStatus + "' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(leadStatus))) {
            error = "Failed to click the Filter Lead Status: '" + leadStatus + "' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Lead Status: '" + leadStatus + "' checkbox.");

        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to click the Filter >> Apply button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Filter >> Apply button.");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        //Action List Dropdown Trigger. Contact a Customer
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to wait for the Action >> 'Contact Customer' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to click the Action >> 'Contact Customer' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> 'Contact Customer' button.");


        if (platform.equalsIgnoreCase("PF")) {
            //confirmation popup
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
                error = "Failed to wait for the confirmation pop-up";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
                error = "Failed to click the confirmation pop-up";
                return false;
            }
            narrator.stepPassed("Successfully clicked OK in alert pop-up.");

            pause(1000);

            // Switch the focus back to the web browser
            SeleniumDriverInstance.Driver.switchTo().window(SeleniumDriverInstance.Driver.getWindowHandle());
        }

        //Contact Customer pop-up
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.referType_option_rdbtn(isCustomerContactedSuccessfully))) {
            error = "Failed to click the refer the lead >> Contact Customer radio option: '" + isCustomerContactedSuccessfully + "'.";
            return false;
        }
        //Click the Confirm Button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked Confirm button.");

        if (isCustomerContactedSuccessfully.equalsIgnoreCase("Yes")) {
            //Call log should appear
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.callLog_popup())) {
                error = "Failed to wait for the Call Log to appear.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Call Log successfully visible.");

            //Add note text area
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.addCallNote(), callNote)) {
                error = "Failed to enter a Add Call Note: " + callNote;
                return false;
            }
            narrator.stepPassed("Successfully entered a Call Note: " + callNote);

            //Click the Submit Button
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
                error = "Failed to click the Submit button.";
                return false;
            }
            submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());
            narrator.stepPassedWithScreenShot("Successfully clicked Submit button.");
        }

        return true;
    }

    public boolean validateTheCustomerContacted() {
        //validate the status and the successfully submitted message
        try {
            //Message
            if (!submittedSuccessfully_msg.isEmpty()) {
                narrator.ValidateEqual(submittedSuccessfully_msg, "Submitted successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the success message: '" + submittedSuccessfully_msg);
            } else {
                error = "Failed to validate the success message: '" + submittedSuccessfully_msg;
                return false;
            }

            //Click FILTER button
            if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.filter_btn())) {
                error = "Failed to wait for the Filter button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.filter_btn())) {
                error = "Failed to click the Filter button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Filter button.");

            //Clear button
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.clear_btn())) {
                error = "Failed to wait for the Clear button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.clear_btn())) {
                error = "Failed to click the Clear button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Clear button.");

            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(status))) {
                error = "Failed to click the Filter Lead Status: '" + status + "' checkbox.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully click the filter Lead Status: '" + status + "' checkbox.");

            //Apply Filter
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
                error = "Failed to click the Filter >> Apply button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Filter >> Apply button.");

            searchCustomer(customerName);

            if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.myLeadsListStatus(customerName))) {
                error = "Failed to wait for the Changed Status.";
                return false;
            }
            String expected_status = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListStatus(customerName));

            //Status
            if (SeleniumDriverInstance.ValidateByText(DAE_PageObject.myLeadsListStatus(customerName), status)) {
                narrator.ValidateEqual(expected_status, status);
                narrator.stepPassedWithScreenShot("Successfully validated the status: '" + expected_status + "' with status: '" + status + "'.");
            } else {
                error = "Failed to validate the status: '" + expected_status + "' with with status: '" + status + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

    private static boolean isMacApplicationOpen(String appName) {
        ProcessBuilder processBuilder = new ProcessBuilder("osascript", "-e",
                "tell application System Events to count (every process whose name is '" + appName + "')");

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            int count = Integer.parseInt(output.trim());

            return count > 0;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchCustomer(String customerName){
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
        WebElement element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.closedMyLeadsSearchBar_txt()));
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);
        narrator.stepPassedWithScreenShot("Successfully searched the customer name: " + customerName);

        return true;
    }
}
