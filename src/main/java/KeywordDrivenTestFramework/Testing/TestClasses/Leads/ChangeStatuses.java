package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/04/04.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "ChangeStatus", createNewBrowserInstance = false)
public class ChangeStatuses extends BaseClass {
    String error = "";
    String customerName, submittedSuccessfully_msg;
    String leadStatus = getData("Lead Status");
    String pipeLineType = getData("Pipeline Type");
    String platform = getData("Platform");
    String[] Status = testData.getData("Statuses").split(",");
    CreateAnOpportunity createAnOpportunityObj;

    public ChangeStatuses() {
        this.createAnOpportunityObj = new CreateAnOpportunity();
    }


    public TestResult executeTest() {
        if (!ChangeStatus()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully change a status for customer name: " + customerName);
    }

    public boolean ChangeStatus() {
        //Scroll up for a clear view
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.overviewHeader())) {
            error = "Failed to scroll up.";
            return false;
        }
        narrator.stepPassed("Successfully scrolled up.");

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

        if (platform.equalsIgnoreCase("PF")) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.LeadList_Page())) {
                error = "Failed to scroll to myLeads List header.";
                return false;
            }
        } else {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myOpportunityList_Page())) {
                error = "Failed to scroll to my opportunity list header.";
                return false;
            }
        }
        //Change statuses
        for (int i = 0; i < Status.length; i++) {

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
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionOption1("Change Status"))) {
                error = "Failed to click the Action >> Change Status button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Change Status option.");

            //Change Status popup
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.changeStatus_popup())) {
                error = "Failed to wait for the change status pop-up.";
                return false;
            }
            pause(1500);
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.changeStatus_drpdwn())) {
                error = "Failed to click the change status dropdown.";
                return false;
            }

            if (Status[i].equalsIgnoreCase("Customer Engagement")) {
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.changeStatus_option(Status[i]))) {
                    error = "Failed to click the refer the lead >> change status option: '" + Status[i] + "'.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the change status option: " + Status[i]);
            } else {
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.changeStatus_option2(Status[i]))) {
                    error = "Failed to click the refer the lead >> change status option: '" + Status[i] + "'.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the change status option: " + Status[i]);
            }
            //Click the Submit Button
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
                error = "Failed to click the Submit button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked Submit button.");

//            if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.submittedSuccessfully())){
//                error = "Failed to wait for msg to appear.";
//                return false;
//            }
//            submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());
//
//            //Validate the success message
//            try {
//                if (!submittedSuccessfully_msg.isEmpty()) {
//                    narrator.ValidateEqual(submittedSuccessfully_msg, "Lead status changed successfully");
//                    narrator.stepPassedWithScreenShot("Successfully validated the " + submittedSuccessfully_msg);
//                } else {
//                    error = "Failed to validate the success message: " + submittedSuccessfully_msg;
//                    return false;
//                }
//            } catch (Exception e) {
//                error = e.getMessage();
//                return false;
//            }

            //Scroll up
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.overviewHeader())) {
                error = "Failed to scroll up";
                return false;
            }
            pause(1000);
            if (Status[i].equalsIgnoreCase("Customer Engagement")) {
                //Validate for Customer Engagement through filter
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

                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(Status[i]))) {
                    error = "Failed to wait for the Filter Lead Status: '" + Status[i] + "' checkbox.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(Status[i]))) {
                    error = "Failed to click the Filter Lead Status: '" + Status[i] + "' checkbox.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully click the filter Lead Status: '" + Status[i] + "' checkbox.");

            } else {
                //validate if the status changed and moved to the right tile
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.statusTile(Status[i]))) {
                    error = "Failed to wait for the status tile: " + Status[i];
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.statusTile(Status[i]))) {
                    error = "Failed to wait for the status tile: " + Status[i];
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully navigated to the status tile: " + Status[i]);
                pause(1000);
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

            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
                error = "Failed to click the Filter >> Apply button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the Filter >> Apply button.");

            //Validate the status
            validateTheCustomerContacted(customerName, Status[i]);

        }

        return true;
    }

    public boolean validateTheCustomerContacted(String customerName, String Status) {
        //validate the status and the successfully submitted message
        try {
            String expected_status = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListStatus(customerName));

            //Status
            if (SeleniumDriverInstance.ValidateByText(DAE_PageObject.myLeadsListStatus(customerName), Status)) {
                narrator.ValidateEqual(expected_status, Status);
                narrator.stepPassedWithScreenShot("Successfully validated the status: '" + expected_status + "' with status: '" + Status + "'.");
            } else {
                error = "Failed to validate the status: '" + expected_status + "' with with status: '" + Status + "'.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

}
