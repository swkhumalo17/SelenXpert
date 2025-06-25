package KeywordDrivenTestFramework.Testing.TestClasses.Campaigns;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(Keyword = "Create_Campaign", createNewBrowserInstance = false)
public class CreateCampaign extends BaseClass {
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
    String reAllocateLeads = getData("Re-allocate Leads");
    String engagementPreference = getData("Engagement Preference");
    LocalDate currentDate = LocalDate.now();
    LocalDate nextDay = currentDate.plusDays(1);
    String startDateToSelect = nextDay.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    String MFCstartDateToSelect = currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    LocalDate endDate = currentDate.plusDays(3);
    String endDateToSelect = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    String CampaignLeadsType = getData("Campaign Leads Type");
    String platform = getData("Platform");
    String newCampaignCode;

    public CreateCampaign() {

    }

    public TestResult executeTest() throws Exception {
        if (!Create_Campaign()) {
            return narrator.testFailed(error);
        }
        if (!CampaignInfor()) {
            return narrator.testFailed(error);
        }
        if (!MarketingMaterial()) {
            return narrator.testFailed(error);
        }
        if (!Confirm()) {
            return narrator.testFailed(error);
        }
        if (!Campaign_Details()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully Created a Campaign: " + CampaignName + " and Campaign Code: " + newCampaignCode);
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

        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) SeleniumDriverInstance.Driver;

        // Scroll up
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

        //Campaign List
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignsList_Header())) {
            error = "Failed to wait for the Campaign list Header.";
            return false;
        }
//        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignListHeader())) {
//            error = "Failed to click the Campaign List Header.";
//            return false;
//        }
        narrator.stepPassedWithScreenShot("Successfully validated the Campaign List Header.");

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

        if (platform.equalsIgnoreCase("PF")) {
            //Get the nextDate
            try {
                WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + nextDay + "'])[1]"));
                startDateToSelectElement.click();
            } catch (Exception e) {
                error = e.getMessage();
                return false;
            }
            narrator.stepPassed("Successfully captured startDate: '" + nextDay + "'");
        } else {
            //Get the currentDate
            try {
                WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + currentDate + "'])[1]"));
                startDateToSelectElement.click();
            } catch (Exception e) {
                error = e.getMessage();
                return false;
            }
            narrator.stepPassed("Successfully captured startDate: '" + currentDate + "'");
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

        //Engagement Preference
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.engagementPreference_drpdwn())) {
            error = "Failed to click the Engagement Preference";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(engagementPreference))) {
            error = "Failed to wait for Engagement Preference textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(engagementPreference))) {
            error = "Failed to select Engagement Preference'" + engagementPreference + "' into Engagement Preference option.";
            return false;
        }
        narrator.stepPassed("Successfully selected Engagement Preference: " + engagementPreference);

        //Priority
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.Priority_drpdwn())) {
            error = "Failed to scroll to the Priority Dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Priority_drpdwn())) {
            error = "Failed to click the Priority Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Priority Dropdown.");

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(Priority))) {
            error = "Failed to wait for Priority textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(Priority))) {
            error = "Failed to select Priority'" + Priority + "' into Priority option.";
            return false;
        }
        narrator.stepPassed("Successfully selected Priority: " + Priority);

        //Allocation group
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.AllocationGroup_drpdwn())) {
            error = "Failed to click the Allocation Group Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Allocation Group Dropdown.");

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(AllocationGroup))) {
            error = "Failed to wait for Allocation Group textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(AllocationGroup))) {
            error = "Failed to enter Allocation Group: '" + AllocationGroup + "' into Allocation Group.";
            return false;
        }
        narrator.stepPassed("Successfully captured Allocation Group: " + AllocationGroup);

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

        if (isMandatory.equalsIgnoreCase("Yes")) {
            //the mandatory campaign
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.mandatory_rdbtn(isMandatory))) {
                error = "Failed to wait for the mandatory campaign radio button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.mandatory_rdbtn(isMandatory))) {
                error = "Failed to wait for the mandatory campaign radio button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the radio button: " + isMandatory);
        }

        if (isMandatory.equalsIgnoreCase("Yes")) {
            //the mandatory campaign
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.reAllocateLeads_rdbtn(reAllocateLeads))) {
                error = "Failed to wait for the re-allocate leads campaign radio button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.reAllocateLeads_rdbtn(reAllocateLeads))) {
                error = "Failed to wait for the re-allocate leads campaign radio button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the radio button: " + reAllocateLeads);
        }
        //Next button
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.next_btn())) {
            error = "Failed to wait for the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully captured the campaign.");

        return true;
    }

    public boolean MarketingMaterial() throws IOException, InterruptedException {
        //Marketing Material
        //Validating the Marketing Material Page is visible
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.marketingMaterial_Header())) {
            error = "Failed to wait for the Marketing Material Page/Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.uploadFile_xpath())) {
            error = "Failed to click the Marketing Material Upload button.";
            return false;
        }
        //narrator.stepPassedWithScreenShot("Successfully navigated to Marketing Material.");

        pause(2000);
        //Run the Applescript to upload a PF document file
        if (platform.equalsIgnoreCase("PF")) {
            try {
                Runtime.getRuntime().exec("osascript " + "/Users/SKHUMALO/DAE-Automation-Framework/src/main/java/AutoIT_Scripts/UploadPF.scpt");
                pause(2000);
            } catch (Exception e) {
                error = e.getMessage();
            }
        } else {
            try {
                Runtime.getRuntime().exec("osascript " + "/Users/SKHUMALO/DAE-Automation-Framework/src/main/java/AutoIT_Scripts/UploadPF.scpt");
                pause(2000);
            } catch (Exception e) {
                error = e.getMessage();
            }
        }

        //Validate the File has been uploaded
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.uploadedFile())) {
            error = "Failed to wait for the Uploaded file.";
            return false;
        }
        String uploadedFileName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.uploadedFile());

        //Next button
        pause(4000);
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.marketingMaterialNext_btn())) {
            error = "Failed to wait for the Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.marketingMaterialNext_btn())) {
            error = "Failed to click the Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully uploaded the file: " + uploadedFileName);

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
        if (platform.equalsIgnoreCase("PF")) {
            //Start Date
            if (!expectedStartDate.isEmpty()) {
                narrator.ValidateEqual(startDateToSelect, expectedStartDate);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Start Date: '" + startDateToSelect + "' with Expected Start Date: '" + expectedStartDate + "'.";
                return false;
            }
        } else {
            //Start Date
            if (!expectedStartDate.isEmpty()) {
                narrator.ValidateEqual(MFCstartDateToSelect, expectedStartDate);
                narrator.stepPassedWithScreenShot("Successfully validated");
            } else {
                error = "Failed to validate Actual Start Date: '" + MFCstartDateToSelect + "' with Expected Start Date: '" + expectedStartDate + "'.";
                return false;
            }
        }
        //End Date
        if (!expectedEndDate.isEmpty()) {
            narrator.ValidateEqual(endDateToSelect, expectedEndDate);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual End Date: '" + endDateToSelect + "' with Expected End Date: '" + expectedEndDate + "'.";
            return false;
        }

        //Priority
        String expectedPriority = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_Priority());
        if (!expectedPriority.isEmpty()) {
            narrator.ValidateEqual(Priority, expectedPriority);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual Priority: '" + Priority + "' with Expected Priority: '" + expectedPriority + "'.";
            return false;
        }
        pause(2000);

        //Allocation Group
        if(!SeleniumDriverInstance.scrollToElement(DAE_PageObject.expected_EndDate())){
            error = "Failed to scroll to expected End Date.";
            return false;
        };

        String expectedAllocationGroup = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_AllocationGroup());
        if (!expectedAllocationGroup.isEmpty()) {
            narrator.ValidateEqual(AllocationGroup, expectedAllocationGroup);
            narrator.stepPassedWithScreenShot("Successfully validated");
        } else {
            error = "Failed to validate Actual Allocation Group: '" + AllocationGroup + "' with Expected Allocation Group: '" + expectedAllocationGroup + "'.";
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

        //Marketing Material
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmUploadedFile())) {
            error = "Failed to wait for the Uploaded file.";
            return false;
        }
        String uploadedFileName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.confirmUploadedFile());
        narrator.stepPassedWithScreenShot("Successfully validated the uploaded file: " + uploadedFileName);

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

        return true;
    }

    public boolean Campaign_Details() {
        //Validate necessary information
        //Status
        String campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus(CampaignName));
        newCampaignCode = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.newCampaignCode());

        if(campaignStatus.equalsIgnoreCase("High")){
            campaignStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CampaignStatus2(CampaignName));
        }

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

        //=======================================================================================//
        //Navigate to Target Customer Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.targetCustomer_tab2())) {
            error = "Failed to wait for the Target Customer Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.targetCustomer_tab2())) {
            error = "Failed to click the Target Customer Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Target Customer Tab.");

        //Upload target customer file
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.uploadTargetCustomer_btn())) {
            error = "Failed to wait for the Upload Target Customer Button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.uploadTargetCustomer_btn())) {
            error = "Failed to click the Upload Target Customer Button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Upload Target Customer Button.");

        //Bulk Upload Pop-Up Dialog
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.bulkUpload_Header())) {
            error = "Failed to wait for the Bulk Upload dialog.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignLeadsType_drpdwn())) {
            error = "Failed to wait for the campaign leads type dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.campaignLeadsType_drpdwn())) {
            error = "Failed to wait for the campaign leads type dropdown.";
            return false;
        }

        //Option
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Option(CampaignLeadsType))) {
            error = "Failed to wait for campaign leads type option.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Option(CampaignLeadsType))) {
            error = "Failed to select campaign leads type'" + CampaignLeadsType + "' into campaign leads type option.";
            return false;
        }
        narrator.stepPassed("Successfully selected Campaign Leads Type: " + CampaignLeadsType);

        //Upload Bulk Files
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.bulkUpload_btn())) {
            error = "Failed to click upload button for the bulk upload.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the upload button for the bulk upload.");

        //Run the Applescript to upload a PF document file
        if (getData("Platform").equalsIgnoreCase("PF")) {
            try {
                Runtime.getRuntime().exec("osascript " + "/Users/SKHUMALO/DAE-Automation-Framework/src/main/java/AutoIT_Scripts/fileUploadPFExcel.scpt");
                pause(2000);
            } catch (Exception e) {
                error = e.getMessage();
            }
        } else {
            try {
                Runtime.getRuntime().exec("osascript " + "/Users/SKHUMALO/DAE-Automation-Framework/src/main/java/AutoIT_Scripts/fileUploadMFCExcel.scpt");
                pause(3000);
            } catch (Exception e) {
                error = e.getMessage();
            }
        }

        //Validate the File has been uploaded
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.uploadedFile())) {
            error = "Failed to wait for the Uploaded file.";
            return false;
        }
        String uploadedFileName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.uploadedFile());
        narrator.stepPassedWithScreenShot(uploadedFileName);

        //Confirm Button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm Button.");

        //10  customers  upload successfully
        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.expected_SuccessfulMessage())) {
            error = "Failed to wait for the expected successful massage to appear.";
            return false;
        }
        String expectedSuccessfulMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SuccessfulMessage());

        pause(1000);
        if (!expectedSuccessfulMessage.isEmpty()) {
            if (platform.equalsIgnoreCase("PF")) {
                if (expectedSuccessfulMessage.equalsIgnoreCase("10 customers have been already uploaded")) {
                    narrator.ValidateEqual(expectedSuccessfulMessage, "10 customers upload successfully");
                    narrator.stepPassedWithScreenShot("Successfully validated the success message: '10 customers  upload successfully' with " + expectedSuccessfulMessage);
                } else if (expectedSuccessfulMessage.equalsIgnoreCase("10 customers upload successfully")) {
                    narrator.ValidateEqual(expectedSuccessfulMessage, "10 customers upload successfully");
                    narrator.stepPassedWithScreenShot("Successfully validated the success message: '10 customers  upload successfully' with " + expectedSuccessfulMessage);
                }
            } else {
                if (expectedSuccessfulMessage.equalsIgnoreCase("4 customers have been already uploaded")) {
                    narrator.ValidateEqual(expectedSuccessfulMessage, "4 customers have been already uploaded");
                    narrator.stepPassedWithScreenShot("Successfully validated the success message: '4 customers have been already uploaded' with " + expectedSuccessfulMessage);
                } else {
                    narrator.ValidateEqual(expectedSuccessfulMessage, "4 customers uploaded successfully");
                    narrator.stepPassedWithScreenShot("Successfully validated the success message: '4 customers  uploaded successfully' with " + expectedSuccessfulMessage);
                }
            }
        } else {
            error = "Failed to validate the success message: " + expectedSuccessfulMessage;
            return false;
        }

        //Submit Button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.submit_btn())) {
            error = "Failed to click the Submit button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
            error = "Failed to click the Submit button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Submit Button.");

        pause(2000);
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

        //Submit Status window
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.submitStatus_window(), 1000)) {
            error = "Failed to wait for the submit status window.";
            return false;
        }

        try {
            String submitStatuMessage = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submitStatuMessage());
            narrator.ValidateEqual("Submitted Successfully", submitStatuMessage);
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Done button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Done_btn())) {
            error = "Failed to click the Done button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Done_btn())) {
            error = "Failed to click the Done button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Done Button.");

        //===================================================================//
        //Validate the campaign has been created under campaign list

        //Navigate back to the campaign list
        Create_Campaign();

//        //Search for the Campaign Name
//        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.searchBar_btn())) {
//            error = "Failed to wait for the search bar button.";
//            return false;
//        }
//        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.searchBar_btn())) {
//            error = "Failed to click the search bar button.";
//            return false;
//        }
//        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.searchBar_txt(), CampaignName)) {
//            error = "Failed to enter the campaign name: '" + CampaignName + "' into the search bar button.";
//            return false;
//        }
//        narrator.stepPassedWithScreenShot("Successfully searched campaign name: '" + CampaignName);
//
//        //Press Enter button
//        //WebElement.sendKeys(Keys.RETURN);
//        SeleniumDriverInstance.Driver.findElement(By.id("Value")).sendKeys(Keys.ENTER);
//
//        pause(2000);
//        try {
//            isDataFoundInTable(newCampaignCode);
//        } catch (Exception e) {
//            error = e.getMessage();
//            return false;
//        }

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
