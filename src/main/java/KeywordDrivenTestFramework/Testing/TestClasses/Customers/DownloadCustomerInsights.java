package KeywordDrivenTestFramework.Testing.TestClasses.Customers;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author SKHUMALO on 2023/05/15.
 * @project DAE-Automation-Framework
 */

@KeywordAnnotation(Keyword = "DownloadCustomerInsight", createNewBrowserInstance = false)
public class DownloadCustomerInsights extends BaseClass {
    String error = "";
    String customerName, todayDate;
    CreateAnOpportunityFromCustomer CreateAnOpportunityFromCustomerObj;

    public DownloadCustomerInsights() {
        this.CreateAnOpportunityFromCustomerObj = new CreateAnOpportunityFromCustomer();
    }

    public TestResult executeTest() {
        if (!CreateAnOpportunityFromCustomerObj.navigateToCustomerList()) {
            return narrator.testFailed(error);
        }
        if (!downloadCustomerInsight()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully downloaded customer insight pdf document.");
    }

    public boolean downloadCustomerInsight() {
        //Capture thw name of the Customer we want to download the insight document for
        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.customerName_txt());

        //Action List Dropdown Trigger. Download Insights
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionDownloadInsights_btn())) {
            error = "Failed to wait for the Action >> Download Insights button.";
            return false;
        }
        if (!SeleniumDriverInstance.doubleClickElementbyXpath(DAE_PageObject.actionDownloadInsights_btn())) {
            error = "Failed to click the Action >> Download Insights button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Action >> Download Insights button.");

        //Validate if file is validated
        pause(15000);
        try {
            LocalDate currentDate = LocalDate.now();
            String todayDate = currentDate.format(DateTimeFormatter.ofPattern("ddMMyyyy"));

            String fileName = "CustomerInsight_" + customerName + "_" + todayDate + ".pdf";
            String userHome = System.getProperty("user.home");
            String downloadDir = Paths.get(userHome, "Downloads").toString();

            SeleniumDriverInstance.waitForFileDownload(fileName, downloadDir);
            pause(5000);
        }catch (Exception e){
            error = e.getMessage();
            return false;
        }

        return true;
    }
}
