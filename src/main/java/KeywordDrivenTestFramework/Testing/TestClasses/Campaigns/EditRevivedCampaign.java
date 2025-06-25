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

@KeywordAnnotation(Keyword = "EditRevivedCampaigns", createNewBrowserInstance = false)
public class EditRevivedCampaign extends BaseClass {
    String error = "";
    int count = Integer.parseInt(getData("No of Customers"));
    String CampaignName = getData("Campaign Name");
    String platform = getData("Platform");
    String newCampaignCode, campaignStatus;
    private CreateCampaign CreateCampaignObj = new CreateCampaign();
    private CreateCampaignAdviser CreateCampaignAdviserObj = new CreateCampaignAdviser();

    public EditRevivedCampaign() {
    }

    public TestResult executeTest() throws Exception {
        TestResult result = null;
        boolean finished = false;
        if (!EditACampaign()) {
            result = narrator.testFailed(error);
        }
        if (campaignStatus.equalsIgnoreCase("Active")) {
            if (!CreateCampaignAdviserObj.Campaign_Details(count, campaignStatus)) {
                result = narrator.testFailed(error);
                finished = true;
            } else if (!CreateCampaignAdviserObj.validateCreatedCampaign(count)) {
                result = narrator.testFailed(error);
                finished = true;
            }
        }
        if (!finished) {
            result = narrator.finalizeTest("Successfully Edited a Campaign: " + CampaignName + " and Campaign Code: " + CreateCampaignObj.newCampaignCode);
        }

        return result;
    }

    public boolean EditACampaign() {
        //Verify the status is pending
        campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus(CampaignName));
        newCampaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.newCampaignCode());


        if (!campaignStatus.isEmpty() && getData("Platform").equalsIgnoreCase("PF")) {
            narrator.ValidateEqual(campaignStatus, "Pending");
            narrator.stepPassedWithScreenShot("Successfully validated the Campaign Status.");
        } else if (!campaignStatus.isEmpty() && getData("Platform").equalsIgnoreCase("MFC")) {
            narrator.ValidateEqual(campaignStatus, "Active");
            narrator.stepPassedWithScreenShot("Successfully validated the Campaign Status.");
        } else {
            error = "Failed to validate Campaign Status '" + campaignStatus + "' with 'Pending'.";
            return false;
        }

        pause(2000);
        //Edit button
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.editRevivedCampaign_btn())) {
            error = "Failed to wait for the edit button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.editRevivedCampaign_btn())) {
            error = "Failed to click the edit button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the edit button.");

        //Validate Edit campaign Page
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.editCampaign_Page())) {
            error = "Failed to wait for the Edit Campaign Page.";
            return false;
        }
        narrator.stepPassed("Successfully navigated to the edit page.");

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
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.expected_SuccessfulMessage())) {
            error = "Failed to wait for the Successfully message.";
            return false;
        }

        String successfullyMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SuccessfulMessage());
        narrator.stepPassedWithScreenShot(successfullyMessage);

        //Status should be active inorder to add Customers
        if(!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(DAE_PageObject.expected_SuccessfulMessage())){
            error = "Failed to wait for the status to appear.";
            return false;
        }
        pause(5000);
        campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus(CampaignName));
        newCampaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.newCampaignCode());

        if (!campaignStatus.isEmpty() && campaignStatus.contains("Active")) {
            narrator.ValidateEqual(campaignStatus, "Active");
            narrator.stepPassedWithScreenShot("Successfully validated the Campaign Status.");
        } else {
            error = "Failed to validate Campaign Status '" + campaignStatus + "' changed from 'Pending' to 'Active'.";
            return false;
        }

        return true;
    }
    public boolean Campaign_Details() {
        //Validate necessary information
        //Status

        newCampaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.newCampaignCode());

        if (!campaignStatus.isEmpty() && getData("Platform").equalsIgnoreCase("PF")) {
            narrator.ValidateEqual(campaignStatus, "Active");
            narrator.stepPassedWithScreenShot("Successfully validated the Campaign Status.");
        } else {
            error = "Failed to validate Campaign Status '" + campaignStatus + "'.";
            return false;
        }

        //=======================================================================================//
        //Navigate to Target Customer Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.targetAdviserCustomer_tab())) {
            error = "Failed to wait for the Target Customer Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.targetAdviserCustomer_tab())) {
            error = "Failed to click the Target Customer Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Target Customer Tab.");

        //Add target customer button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.addTargetCustomer())) {
            error = "Failed to wait for the Add Target Customer Button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.addTargetCustomer())) {
            error = "Failed to click the Add Target Customer Button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Add Target Customer Button.");


        int j = 1;
        String[] customerNames = new String[count];
        for (int i = 0; i < count; i++) {
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customerName_chckbx(j + 1))) {
                error = "Failed to wait for the customer number: " + (i + 1) + " checkbox.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.customerName_chckbx(j + 1))) {
                error = "Failed to click the customer number: " + (i + 1) + " checkbox.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the customer " + count + " checkbox.");

            //retrieve all customer names and add them to an array
            try {
                String customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerName_txt(i + 1));
                customerNames[i] = customerName;
            } catch (Exception e) {
                error = "Failed to retrieve the Customer name: " + (i + 1);
                return false;
            }
            j++;
        }

        //Confirm selected number of customers
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmSelectedCustomers_btn())) {
            error = "Failed to wait for the Confirm Selected " + count + " Customers button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmSelectedCustomers_btn())) {
            error = "Failed to click the Confirm Selected " + count + " Customers button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm Selected " + count + " Customers button.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.expected_SuccessfulMessage())) {
            error = "Failed to wait for the successful message.";
            return false;
        }

        //Validate the number of customers uploaded
        String noOfCustomersUploaded_SM = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SuccessfulMessage());

        if (!noOfCustomersUploaded_SM.isEmpty()) {
            if (count == 1) {
                narrator.ValidateEqual(noOfCustomersUploaded_SM, count + " customer upload successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the success message: '" + count + " customer upload successfully' with " + noOfCustomersUploaded_SM);
            } else {
                narrator.ValidateEqual(noOfCustomersUploaded_SM, count + " customers uploaded successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the success message: '" + count + " customers  upload successfully' with " + noOfCustomersUploaded_SM);
            }
        } else {
            error = "Failed to validate the success message: '" + count + "  customers  upload successfully' with " + noOfCustomersUploaded_SM;
            return false;
        }

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //Validate the selected customers
        String customerName = "";
        int xy = count;
        int index = 0;
        try {
            for (int i = 1; i <= customerNames.length; i++) {
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.targetCustomer_CustomerName(i + 1))) {
                    error = "Failed to wait for the Customer Name to appear";
                    return false;
                }
                customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.targetCustomer_CustomerName(i + 1));
                if (!customerName.isEmpty()) {
                    narrator.ValidateEqual(customerNames[index], customerName);
                    narrator.stepPassedWithScreenShot("Successfully validated the customer name: " + customerNames[index]);
                } else {
                    error = "Failed to validate the customer name: " + customerNames[index];
                    return false;
                }
                xy--;
                index++;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the added customers.");

        return true;
    }
    public boolean validateCreatedCampaign() {
        //Navigate to Overview and validate campaigns created
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Overview_tab())) {
            error = "Failed to wait for the Overview tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Overview_tab())) {
            error = "Failed to click the Overview tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Overview tab.");

        //Total Open Ellipsis
        try {
            String totalOpenEllipsis = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.totalOpenEllipsis());

            if (!narrator.ValidateEqual(totalOpenEllipsis, Integer.toString(count))) {
                error = "Failed to validate the open ellipsis '" + totalOpenEllipsis + "' with '" + count + "'.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully validated the open ellipsis '" + totalOpenEllipsis + "' with '" + count + "'.");
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Total Ellipsis -> Open + Close + Issued + Converted
        try {
            String totalEllipsis = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.totalEllipsis());

            if (!narrator.ValidateEqual(totalEllipsis, Integer.toString(count))) {
                error = "Failed to validate the total ellipsis '" + totalEllipsis + "' with '" + count + "'.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully validated the total ellipsis '" + totalEllipsis + "' with '" + count + "'.");
        } catch (Exception e) {
            error = e.getMessage();
            return false;
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
