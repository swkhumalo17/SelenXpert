package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(Keyword = "Edit_Campaign", createNewBrowserInstance = false)
public class EditCampaign extends BaseClass {
    String error = "";
    String CampaignName = getData("Campaign Name");
    String include_both_closed_and_expired = getData("Include Both Closed and Expired");
    String status1 = getData("Status 1");
    String status2 = getData("Status 2");
    String platform = getData("Platform");

    CreateCampaign CreateCampaignObj;

    public EditCampaign() {
        this.CreateCampaignObj = new CreateCampaign();
    }

    public TestResult executeTest() throws Exception {
        if (!CreateCampaignObj.Create_Campaign()) {
            return narrator.testFailed(error);
        }
        if (!EditACampaign()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Edited a Campaign: " + CampaignName + " and Campaign Code: " + CreateCampaignObj.newCampaignCode);
    }

    public boolean EditACampaign() {
        //Click FILTER button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignFilter_btn())) {
            error = "Failed to wait for the Filter button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignFilter_btn())) {
            error = "Failed to click the Filter button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Filter button.");

        //Validate all filter option list
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Campaign Name dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.categorization_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Categorization dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.startDateEndDate_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Start Date - End Date dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.priority_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Priority dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.dateCreated_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Date Created dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.creator_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Creator dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.status_Filter_drpdwn())) {
            error = "Failed to wait for the Filter Status dropdown.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated all filter option list is visible");

        //Filter Campaign Name
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.FilterOption_chckbx("Start Date Test"))) {
//            error = "Failed to scroll to the Filter Campaign Name: 'Start Date Test' checkbox.";
//            return false;
//        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(CampaignName))) {
            error = "Failed to wait for the Filter Campaign Name: '" + CampaignName + "' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(CampaignName))) {
            error = "Failed to click the Filter Campaign Name: '" + CampaignName + "' checkbox.";
            return false;
        }
        narrator.stepPassed("Successfully click the filter Campaign Name: '" + CampaignName + "' checkbox.");
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignsList_Header())) {
//            error = "Failed to scroll up.";
//            return false;
//        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to click the Filter Campaign Name dropdown.";
            return false;
        }

        //Categorization
        pause(2000);
        String categorySearch = getData("Category Search");
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.categorization_Filter_drpdwn())) {
            error = "Failed to click the Categorization Status dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(categorySearch))) {
            error = "Failed to wait for the Filter Categorization: '" + categorySearch + "' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(categorySearch))) {
            error = "Failed to click the Filter Categorization: '" + categorySearch + "' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Categorization: '" + categorySearch + "' checkbox.");
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.categorization_Filter_drpdwn())) {
            error = "Failed to click the Categorization Status dropdown.";
            return false;
        }

        //Filter Status
        pause(2000);
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.status_Filter_drpdwn())) {
            error = "Failed to click the Filter Status dropdown.";
            return false;
        }
        switch (include_both_closed_and_expired) {
            case "Yes":
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
                    error = "Failed to wait for the Filter Status: '" + status1 + "' checkbox.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
                    error = "Failed to click the Filter Status: '" + status1 + "' checkbox.";
                    return false;
                }
                narrator.stepPassed("Successfully click the filter Status: '" + status1 + "' checkbox.");

                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(status2))) {
                    error = "Failed to click the Filter Status: '" + status2 + "' checkbox.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully click the filter Status: '" + status2 + "' checkbox.");
                break;
            case "No":
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
                    error = "Failed to wait for the Filter Status: '" + status1 + "' checkbox.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
                    error = "Failed to click the Filter Status: '" + status1 + "' checkbox.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully click the filter Status: '" + status1 + "' checkbox.");
                break;

            default:
                error = "No such option is available in this list. please re-check.";
        }

        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.status_Filter_drpdwn())) {
            error = "Failed to click the Filter status dropdown.";
            return false;
        }
        pause(2000);

        //Click APPLY button.
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to wait for the Filter Apply button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to click the Filter Apply button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Apply button.");

        pause(2000);
        //Validate filtered list
        try {
            isDataFoundInTable(CampaignName);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Campaign Code
        //String campaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignCode());

        pause(1000);
        //Under Action Column Click Dropdown Trigger.
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to wait for the Action >> Edit button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionEdit_btn())) {
            error = "Failed to click the Action >> Edit button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> Edit button.");

        //Validate Edit campaign Page
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.editCampaign_Page())) {
            error = "Failed to wait for the Edit Campaign Page.";
            return false;
        }

        if (platform.equalsIgnoreCase("PF")) {
            if (getData("is Adviser").equalsIgnoreCase("Yes")) {
                //Edit the start date to current date so the campaign can be active
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.startDate_drpdwn())) {
                    error = "Failed to click the Start Date Dropdown.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the Start Dropdown.");

                //Get the currentDate
                LocalDate currentDate = LocalDate.now();
                try {
                    WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + currentDate + "'])[1]"));
                    startDateToSelectElement.click();
                } catch (Exception e) {
                    error = e.getMessage();
                    return false;
                }
                narrator.stepPassed("Successfully captured startDate: '" + currentDate + "'");
            } else {
                //Category
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Category_drpdwn())) {
                    error = "Failed to click the Category Dropdown.";
                    return false;
                }
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(CreateCampaignObj.Category))) {
                    error = "Failed to wait for Category textbox.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(CreateCampaignObj.Category))) {
                    error = "Failed to select Category'" + CreateCampaignObj.Category + "' into Category option.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully selected Category: " + CreateCampaignObj.Category);
            }
        } else if (platform.equalsIgnoreCase("MFC")) {
            if (getData("is Adviser").equalsIgnoreCase("NO")) {
                //Edit the start date to current date so the campaign can be active
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.endDate_drpdwn())) {
                    error = "Failed to click the Start Date Dropdown.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the End Dropdown.");

                //Get the currentDate
                LocalDate currentDate = LocalDate.now();
                LocalDate endDate = currentDate.plusDays(3);
                try {
                    WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + endDate + "'])[1]"));
                    startDateToSelectElement.click();
                } catch (Exception e) {
                    error = e.getMessage();
                    return false;
                }
                narrator.stepPassed("Successfully captured startDate: '" + currentDate + "'");
            }
        }
        pause(2000);

        //Next button
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.next_btn())) {
            error = "Failed to scroll to the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click the next button";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully captured the campaign.");

        if (getData("is Adviser").equalsIgnoreCase("No")) {
            try {
                CreateCampaignObj.MarketingMaterial();
            } catch (IOException ioe) {
                error = ioe.getMessage();
                return false;
            } catch (InterruptedException ie) {
                error = ie.getMessage();
                return false;
            }
        } else if (getData("is Adviser").equalsIgnoreCase("Yes")) {
            String expectedStartDate = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_StartDate());
            //Start Date
            if (!expectedStartDate.isEmpty()) {
                narrator.ValidateEqual(CreateCampaignObj.MFCstartDateToSelect, expectedStartDate);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Start Date: '" + CreateCampaignObj.MFCstartDateToSelect + "' with Expected Start Date: '" + expectedStartDate + "'.";
                return false;
            }
        }

        pause(2000);
        //Allocation Group

//        if(!SeleniumDriverInstance.scrollToElement(DAE_PageObject.expected_EndDate())){
//            error = "Failed to scroll to expected End Date.";
//            return false;
//        }
//
//        if (platform.equalsIgnoreCase("PF")) {
//            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
//                error = "Failed to click the Confirm button 2";
//                return false;
//            }
//        } else if (platform.equalsIgnoreCase("MFC")) {
//            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn3())) {
//                error = "Failed to click the next button";
//                return false;
//            }
//        }
//        narrator.stepPassedWithScreenShot("Successfully captured the campaign.");

        pause(2000);
        //Click the Confirm Button
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.confirm_btn())) {
            error = "Failed to scroll to the Confirm button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated all the Campaign Information.");

        //Validate the Campaign has been successfully launched
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.expected_SuccessfulMessage())) {
            error = "Failed to wait for the Successfully message.";
            return false;
        }

        String successfullyMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SuccessfulMessage());
        narrator.stepPassedWithScreenShot(successfullyMessage);

        String campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus(CampaignName));

        if(campaignStatus.equalsIgnoreCase("Active")){
            campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus2(CampaignName));
        }else{
            CreateCampaignObj.Campaign_Details();
        }

        return true;
    }

    public boolean isDataFoundInTable(String expectedData) {
        WebElement table = SeleniumDriverInstance.Driver.findElement(By.cssSelector("table"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(expectedData)) {
                    return true;
                }
            }
        }
        return false;
    }

}
