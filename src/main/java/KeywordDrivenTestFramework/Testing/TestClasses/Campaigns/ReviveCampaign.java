package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(Keyword = "Revive_Campaign", createNewBrowserInstance = false)
public class ReviveCampaign extends BaseClass {
    String error = "";
    String CampaignName = getData("Campaign Name");
    String include_both_closed_and_expired = getData("Include Both Closed and Expired");
    String status1 = getData("Status 1");
    String status2 = getData("Status 2");

    CreateCampaign CreateCampaignObj;

    public ReviveCampaign() {
        this.CreateCampaignObj = new CreateCampaign();
    }

    public TestResult executeTest() throws Exception {
        if (!Create_Campaign()) {
            return narrator.testFailed(error);
        }
        if (!ReviveACampaign()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Revive a Campaign: " + CreateCampaignObj.CampaignName + " and Campaign Code: " + CreateCampaignObj.newCampaignCode);
    }

    public boolean Create_Campaign() {
        pause(1000);
        //Campaigns sidebar
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignsSidebar())) {
            error = "Failed to wait for the Campaigns Sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignsSidebar())) {
            error = "Failed to click the Campaigns Sidebar.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Campaign Sidebar.");

        //Campaign List
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignsList_Header())) {
            error = "Failed to wait for the Campaign list Header.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the Campaign List Header.");

        return true;
    }

    public boolean ReviveACampaign() {
        pause(2000);
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
        narrator.stepPassedWithScreenShot("Successfully validated the Filter options are visible.");

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
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignsList_Header())) {
//            error = "Failed to scroll up.";
//            return false;
//        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Filter_drpdwn())) {
            error = "Failed to click the Filter Campaign Name dropdown.";
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
        String campaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignCode());

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
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionOption1("Revive"))) {
            error = "Failed to click the Action >> Revive button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Revive option.");

        //Validate Revive campaign Page
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.reviveCampaign_Page())) {
            error = "Failed to wait for the Revive Campaign Page.";
            return false;
        }
        pause(2000);

        //Start Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.startDate_drpdwn())) {
            error = "Failed to click the Start Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Product Dropdown.");

        //Get the currentDate
        try {
            WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + CreateCampaignObj.nextDay + "'])[1]"));
            startDateToSelectElement.click();
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassed("Successfully captured startDate: '" + CreateCampaignObj.nextDay + "'");

        //End Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.endDate_drpdwn())) {
            error = "Failed to click the End Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the End Date Dropdown.");

        //Get the End date
        try {
            WebElement endDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + CreateCampaignObj.endDate + "'])[2]"));
            endDateToSelectElement.click();
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassed("Successfully captured startDate: '" + CreateCampaignObj.endDate + "'");

        //Next button
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.next_btn())) {
            error = "Failed to scroll to the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully captured the campaign.");

        try {
            CreateCampaignObj.MarketingMaterial();
        } catch (IOException ioe) {
            error = ioe.getMessage();
            return false;
        } catch (InterruptedException ie) {
            error = ie.getMessage();
            return false;
        }

        //Confirm
        //Click the Confirm Button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated all the Campaign Information.");

        //Validate the the Campaign has been successfully launched
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.expected_SuccessfulMessage())) {
            error = "Failed to wait for the Successfully massege.";
            return false;
        }

        String successfullyMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SuccessfulMessage());
        narrator.stepPassedWithScreenShot(successfullyMessage);

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
