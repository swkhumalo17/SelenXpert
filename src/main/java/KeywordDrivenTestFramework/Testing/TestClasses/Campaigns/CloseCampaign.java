package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(Keyword = "Close_Campaign", createNewBrowserInstance = false)
public class CloseCampaign extends BaseClass {
    String error = "";
    String status1 = getData("Status 1");
    String status2 = getData("Status 2");
    String include_both_pending_and_active = getData("Include Both Pending And Active");
    String ChangeTo = getData("Change To");
    String CampaignName = getData("Campaign Name");

    CreateCampaign CreateCampaignObj;

    public CloseCampaign() {
        this.CreateCampaignObj = new CreateCampaign();
    }

    public TestResult executeTest() throws Exception {
        if (!CreateCampaignObj.Create_Campaign()) {
            return narrator.testFailed(error);
        }
        if (!CloseACampaign()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Closed a Campaign: " + CampaignName);
    }

    public boolean CloseACampaign() {
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
        narrator.stepPassedWithScreenShot("Successfully click the filter Campaign Name: '" + CampaignName + "' checkbox.");
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignName_Filter_drpdwn())) {
//            error = "Failed to scroll up.";
//            return false;
//        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to click the Filter Campaign Name dropdown.";
            return false;
        }

        //Filter Status
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.status_Filter_drpdwn())) {
            error = "Failed to click the Filter Status dropdown.";
            return false;
        }

        switch (include_both_pending_and_active) {
            case "Yes":
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
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
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.FilterOption_chckbx(status1))) {
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

        //Validate filtered list
        pause(2000);
        CampaignName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignName());

        //Click Close option.
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionClose_btn())) {
            error = "Failed to click the Action >> 'Close' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Close' option.");

        //Click Yes to Confirm submitting 1 customer list
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
            error = "Failed to wait for the confirmation pop-up";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
            error = "Failed to click the confirmation pop-up";
            return false;
        }
        narrator.stepPassed("Successfully clicked OK in alert pop-up.");

        String campaignArchive_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

        if (!campaignArchive_Message.isEmpty()) {
            narrator.ValidateEqual(campaignArchive_Message, "Campaign Closed");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message.");
        } else {
            error = "Failed to validate Successful message: " + campaignArchive_Message;
            return false;
        }

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

        //Check the status and a closed campaign name
        //Filter Campaign Name
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to click the Filter Campaign Name dropdown.";
            return false;
        }
        pause(1000);
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.FilterOption_chckbx("Start Date Test"))) {
//            error = "Failed to scroll to the Filter Campaign Name: 'Start Date Test' checkbox.";
//            return false;
//        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx(CreateCampaignObj.CampaignName))) {
            error = "Failed to wait for the Filter Campaign Name: '" + CreateCampaignObj.CampaignName + "' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx(CreateCampaignObj.CampaignName))) {
            error = "Failed to click the Filter Campaign Name: '" + CreateCampaignObj.CampaignName + "' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Campaign Name: '" + CreateCampaignObj.CampaignName + "' checkbox.");
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignsList_Header())) {
//            error = "Failed to scroll up.";
//            return false;
//        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to click the Filter Campaign Name dropdown.";
            return false;
        }

        //Status
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.status_Filter_drpdwn())) {
            error = "Failed to click the Filter Status dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.FilterOption_chckbx("Closed"))) {
            error = "Failed to wait for the Filter Status: 'Closed' checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.FilterOption_chckbx("Closed"))) {
            error = "Failed to click the Filter Status: 'Closed' checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the filter Status: 'Closed' checkbox.");

        //Apply button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to wait for the Apply button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.applyFilter_btn())) {
            error = "Failed to click the Apply button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Apply button.");
        pause(2000);

        //Validate the campaign status is Closed.
        try {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignListStatus_Column())) {
                error = "Failed to scroll to Status Column";
                return false;
            }
            String tableStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignListStatus(CreateCampaignObj.CampaignName));

            if (!tableStatus.isEmpty()) {
                narrator.ValidateEqual(tableStatus, "Closed");
                narrator.stepPassedWithScreenShot("Successfully validated the Status has change to " + tableStatus);
            } else {
                error = "Failed due to Status contain no data.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean upload(String filePath, String Xpath) {
        //Upload Files
        WebElement fileToUpload = SeleniumDriverInstance.Driver.findElement(By.xpath(Xpath));
        fileToUpload.sendKeys(filePath);

        //Set the file path as the value of the file upload element

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.uploadedFile())) {
            error = "Failed to upload the file: " + filePath;
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully uploaded the file: " + filePath);

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
