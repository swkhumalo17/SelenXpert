package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(Keyword = "Create_Campaign_Adviser", createNewBrowserInstance = false)
public class CreateCampaignAdviser extends BaseClass {
    String error = "";
    String CampaignName = getData("Campaign Name");
    String Category = getData("Category");
    String Product1 = getData("Product 1");
    String Product2 = getData("Product 2");
    String Product3 = getData("Product 3");
    String Priority = testData.getData("Priority");
    String AllocationGroup = getData("Allocation Group");
    String AdditionalInformation = getData("Additional Information");
    String isMandatory = getData("Mandatory Campaign");
    int count = Integer.parseInt(getData("No of Customers"));

    LocalDate currentDate = LocalDate.now();
    LocalDate nextDay = currentDate.plusDays(1);
    String startDateToSelect = nextDay.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    String MFCstartDateToSelect = currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    LocalDate endDate = currentDate.plusDays(3);
    String endDateToSelect = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    String CampaignLeadsType = getData("Campaign Leads Type");
    String platform = getData("Platform");
    String newCampaignCode, campaignStatus;

    public CreateCampaignAdviser() {

    }

    public TestResult executeTest() throws Exception {
        if (!Create_Campaign()) {
            return narrator.testFailed(error);
        }
        if (!CampaignInfor()) {
            return narrator.testFailed(error);
        }
        if (!Confirm()) {
            return narrator.testFailed(error);
        }
        if (campaignStatus.equalsIgnoreCase("Active")) {
            if (!Campaign_Details(count, campaignStatus)) {
                return narrator.testFailed(error);
            }
            if (!validateCreatedCampaign(count)) {
                return narrator.testFailed(error);
            }
        }

        return narrator.finalizeTest("Successfully Created a Campaign: " + CampaignName + " and Campaign Code: " + newCampaignCode);
    }

    public boolean Create_Campaign() {
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
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignsList_Header())) {
            error = "Failed to click the Campaign List Header.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Campaign Sidebar.");

        return true;
    }

    public boolean CampaignInfor() {
        //Create New Button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.CreateNew_btn())) {
            error = "Failed to wait for Create New Button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.CreateNew_btn())) {
            error = "Failed to click the Create New Button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Create New Button.");

        //Campaign Name
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignName_drpdwn())) {
            error = "Failed to wait for Campaign Name Dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_drpdwn())) {
            error = "Failed to click the Campaign Name Dropdown.";
            return false;
        }
        pause(200);
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignName_txtbx())) {
            error = "Failed to wait for Campaign Name textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.campaignName_txtbx(), CampaignName)) {
            error = "Failed to enter Campaign Name '" + CampaignName + "' into Campaign Name textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignName_Option(CampaignName))) {
            error = "Failed to click Campaign Name '" + CampaignName + "'.";
            return false;
        }
        narrator.stepPassed("Successfully captured Campaign Name: " + CampaignName);

        //Category
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Category_drpdwn())) {
            error = "Failed to click the Category Dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.categoryOption(Category))) {
            error = "Failed to wait for Category textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.categoryOption(Category))) {
            error = "Failed to select Category'" + Category + "' into Category option.";
            return false;
        }
        narrator.stepPassed("Successfully selected Category: " + Category);

        //Product
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Product_drpdwn())) {
            error = "Failed to click the Product Dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(Product1))) {
            error = "Failed to wait for Product textbox.";
            return false;
        }
        switch (platform) {
            case "PF":
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(Product1))) {
                    error = "Failed to enter Product '" + Product1 + "' into Product Main Option.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(Product2))) {
                    error = "Failed to enter Product '" + Product2 + "' into Product Sub Option.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option_chkbx(Product3))) {
                    error = "Failed to enter Product '" + Product3 + "' into Product Select Option.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Product_drpdwn())) {
                    error = "Failed to click the Product Dropdown.";
                    return false;
                }
                narrator.stepPassed("Successfully captured Product: " + Product1 + " >> '" + Product2 + "' >> " + Product3);
                break;

            case "MFC":
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(Product1))) {
                    error = "Failed to enter Product '" + Product1 + "' into Product Main Option.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option_chkbx(Product2))) {
                    error = "Failed to enter Product '" + Product2 + "' into Product Select Option.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Product_drpdwn())) {
                    error = "Failed to click the Product Dropdown.";
                    return false;
                }
                narrator.stepPassed("Successfully captured Product: " + Product1 + " >> '" + Product2);
                break;
            default:
                error = "Failed to find the platform.";
                break;
        }

        //Start Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.startDate_drpdwn())) {
            error = "Failed to click the Start Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Start Dropdown.");

        //Get the currentDate
        if (getData("Current Date").equalsIgnoreCase("Yes")) {
            try {
                WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + currentDate + "'])[1]"));
                startDateToSelectElement.click();
            } catch (Exception e) {
                error = e.getMessage();
                return false;
            }
            narrator.stepPassed("Successfully captured startDate: '" + currentDate + "'");
        } else {
            try {
                WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + nextDay + "'])[1]"));
                startDateToSelectElement.click();
            } catch (Exception e) {
                error = e.getMessage();
                return false;
            }
            narrator.stepPassed("Successfully captured startDate: '" + nextDay + "'");
        }

        pause(2000);
        //End Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.endDate_drpdwn())) {
            error = "Failed to click the End Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the End Date Dropdown.");

        //Get the End date
        try {
            WebElement endDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + endDate + "'])[2]"));
            endDateToSelectElement.click();
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassed("Successfully captured startDate: '" + endDate + "'");


        //Additional Information
        if (getData("Additional Infor").equalsIgnoreCase("Yes")) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.additionalGroup_txt())) {
                error = "Failed to scroll to Additional Information.";
                return false;
            }
            if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.additionalGroup_txt(), AdditionalInformation)) {
                error = "Failed to enter Additional Information.";
                return false;
            }
        }
        narrator.stepPassed("Successfully captured additional information.");
        narrator.stepPassedWithScreenShot("Successfully captured Campaign Information.");

        //Next button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully captured the campaign.");

        return true;
    }

    public boolean Confirm() {
        //Validate all information
        //Campaign Name
        String expectedCampaignName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_CampaignName());

        if (!expectedCampaignName.isEmpty()) {
            narrator.ValidateEqual(CampaignName, expectedCampaignName);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual Campaign Name: '" + CampaignName + "' with Expected Campaign Name: '" + expectedCampaignName + "'.";
            return false;
        }

        //Product
        String expectedProduct = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Product());
        if (platform.equalsIgnoreCase("PF")) {
            if (Product3.equalsIgnoreCase(expectedProduct)) {
                narrator.ValidateEqual(Product3, expectedProduct);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Product: '" + Product3 + "' with Expected Product: '" + expectedProduct + "'.";
                return false;
            }
        } else {
            if (Product2.equalsIgnoreCase(expectedProduct)) {
                narrator.ValidateEqual(Product2, expectedProduct);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Product: '" + Product2 + "' with Expected Product: '" + expectedProduct + "'.";
                return false;
            }
        }

        //Category
        String expectedCategory = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Category());
        if (!expectedCategory.isEmpty()) {
            narrator.ValidateEqual(Category, expectedCategory);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual Category: '" + Category + "' with Expected Category: '" + expectedCategory + "'.";
            return false;
        }

        String expectedStartDate = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_StartDate());
        String expectedEndDate = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_EndDate());

        //Start Date
        if (!expectedStartDate.isEmpty()) {
            if (getData("Current Date").equalsIgnoreCase("No")) {
                narrator.ValidateEqual(startDateToSelect, expectedStartDate);
                narrator.stepPassedWithScreenShot("Successfully validated");
            }else {
                narrator.ValidateEqual(MFCstartDateToSelect, expectedStartDate);
                narrator.stepPassedWithScreenShot("Successfully validated");
            }
        } else {
            error = "Failed to validate Actual Start Date: '" + MFCstartDateToSelect + "' with Expected Start Date: '" + expectedStartDate + "'.";
            return false;
        }

        //End Date
        if (!expectedEndDate.isEmpty()) {
            narrator.ValidateEqual(endDateToSelect, expectedEndDate);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual End Date: '" + endDateToSelect + "' with Expected End Date: '" + expectedEndDate + "'.";
            return false;
        }

        //Additional Information
        if (getData("Additional Infor").equalsIgnoreCase("Yes")) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.expected_AdditionalInformation())) {
                error = "Failed to scroll to the element.";
                return false;
            }
            String expectedAdditionalInformation = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_AdditionalInformation());
            if (!expectedAdditionalInformation.isEmpty()) {
                narrator.ValidateEqual(AdditionalInformation, expectedAdditionalInformation);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Additional Information: '" + AdditionalInformation + "' with Expected Additional Information: '" + expectedAdditionalInformation + "'.";
                return false;
            }
        }

        //Click the Confirm Button
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
        campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignAdviserStatus(CampaignName));

        return true;
    }

    public boolean Campaign_Details(int count, String campaignStatus) {
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

    public boolean validateCreatedCampaign(int count) {
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
