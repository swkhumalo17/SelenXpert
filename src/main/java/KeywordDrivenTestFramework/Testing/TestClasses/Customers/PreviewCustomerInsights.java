package KeywordDrivenTestFramework.Testing.TestClasses.Customers;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author SKHUMALO on 2023/05/15.
 * @project DAE-Automation-Framework
 */

@KeywordAnnotation(Keyword = "PreviewCustomerInsight", createNewBrowserInstance = false)
public class PreviewCustomerInsights extends BaseClass {
    String error = "";
    String customerName, todayDate;
    CreateAnOpportunityFromCustomer CreateAnOpportunityFromCustomerObj;

    public PreviewCustomerInsights() {
        this.CreateAnOpportunityFromCustomerObj = new CreateAnOpportunityFromCustomer();
    }

    public TestResult executeTest() {
        if (!CreateAnOpportunityFromCustomerObj.navigateToCustomerList()) {
            return narrator.testFailed(error);
        }
        if (!downloadCustomerInsight()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully viewed and downloaded customer insight pdf document.");
    }

    public boolean downloadCustomerInsight() {
        //Capture thw name of the Customer we want to download the insight document for
        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerName_txt());

        //Preview
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionPreviewInsights_btn())) {
            error = "Failed to wait for the Action >> Preview Insights button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionPreviewInsights_btn())) {
            error = "Failed to click the Action >> Preview Insights button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> Preview Insights button.");

        //Loading mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //wait for the preview insight page
        if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.previewInsights_page())){
            error = "Failed to wait for the Preview Insights page.";
            return false;
        }
        // Split the string into an array using ","
        String[] nameArray = customerName.split(",");

        // Combine the names in reverse order
        StringBuilder combinedNames = new StringBuilder();
        for (int i = nameArray.length - 1; i >= 0; i--) {
            combinedNames.append(nameArray[i]).append(" ");
        }

        //Wait for the pdf to be visible
        if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.previewInsights_pdf(combinedNames.toString().trim()))){
            error = "Failed to wait for the Content of the customer.";
            return false;
        }
        String expectedCustomerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.previewInsights_pdf(combinedNames.toString().trim()));

        narrator.ValidateEqual(combinedNames.toString().trim(), expectedCustomerName);
        narrator.stepPassedWithScreenShot("Successfully validated the pdf name: " + expectedCustomerName);

        //Download button
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.previewInsightsDownload_btn())){
            error = "Failed to click the download button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the download button.");

        //Validate if file is validated
        try {
            LocalDate currentDate = LocalDate.now();
            String todayDate = currentDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            String fileName = "CustomerInsight_" + customerName + "_" + todayDate + ".pdf";
            String userHome = System.getProperty("user.home");
            String downloadDir = Paths.get(userHome, "Downloads").toString();

            SeleniumDriverInstance.waitForFileDownload(fileName, downloadDir);
        }catch (Exception e){
            error = e.getMessage();
            return false;
        }

        if (!SeleniumDriverInstance.closeTab(2)) {
            error = "Failed to switch back to default tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully switched back to default tab.");

        //Return to home page
        if(!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.previewInsights_page_X())){
            error = "Failed to wait for the X button.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.previewInsights_page_X())){
            error = "Failed to click the X button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the X button from preview insight page.");

        return true;
    }


}
