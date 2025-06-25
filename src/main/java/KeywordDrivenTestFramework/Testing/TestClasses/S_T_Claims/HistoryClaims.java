package KeywordDrivenTestFramework.Testing.TestClasses.S_T_Claims;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author SKHUMALO on 2023/08/29.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "HistoryClaims", createNewBrowserInstance = false)
public class HistoryClaims extends BaseClass {
    String error = "";
    Approve_Claims approveClaimsObj;

    public HistoryClaims() {
        this.approveClaimsObj = new Approve_Claims();
    }

    public TestResult executeTest() {
        if (!validateClaims()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully Submitted a claim for approval from Sales Manager.");
    }

    public boolean validateClaims() {
        approveClaimsObj.navigateToSTClaims();

        //History Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.history_Tab())) {
            error = "Failed to wait for the History Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.history_Tab())) {
            error = "Failed to wait for the History Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to History Tab.");

        //Validate S&T Claims table is visible
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.S_T_ClaimsHistoryHeader())) {
            error = "Failed to wait for S&T Claims table to be visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the S&T Claims table is visible.");

        String action = getData("Action");
        switch (action) {
            case "View":
                //Extract SalesWeek
                String salesWeek = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.salesWeek());

                //View details
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.viewDetails_btn())) {
                    error = "Failed to wait for the view button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.viewDetails_btn())) {
                    error = "Failed to click the view button.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully clicked the view button.");

                //Validate the expected data
                if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.expected_SalesWeek())) {
                    error = "Failed to wait for the week: " + salesWeek + " Claims.";
                    return false;
                }

                //Extract SalesWeek
                String expectedSalesWeek = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_SalesWeek());

                if (!expectedSalesWeek.isEmpty()) {
                    narrator.ValidateEqual(expectedSalesWeek, salesWeek);
                    narrator.stepPassedWithScreenShot("Successfully validated sales week details: " + expectedSalesWeek);
                } else {
                    error = "No data extracted.";
                    return false;
                }

                //Action List Dropdown Trigger. Download Insights
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.claimDetailsDownload_btn())) {
                    error = "Failed to wait for the Action >> View Details >> Download button.";
                    return false;
                }
                if (!SeleniumDriverInstance.doubleClickElementbyXpath(DAE_PageObject.claimDetailsDownload_btn())) {
                    error = "Failed to click the Action >> View Details >> Download button.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the Action >> View Details >> Download button.");

                //Validate if file is validated
                try {
                    Thread.sleep(5000);

                    // Verify the downloaded PDF file
                    String userHome = System.getProperty("user.home");
                    String downloadDir = Paths.get(userHome, "Downloads").toString();

                    // Verify the downloaded PDF file
                    String partialUsername = "LacosteNick"; // The partial username you want to check for
                    SeleniumDriverInstance.validateFileDownloaded(partialUsername, downloadDir);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            case "Download":
                //Action >> Download
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.downloadDetails_btn())) {
                    error = "Failed to wait for the Action >> Download button.";
                    return false;
                }
                if (!SeleniumDriverInstance.doubleClickElementbyXpath(DAE_PageObject.downloadDetails_btn())) {
                    error = "Failed to click the Action >> Download button.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the Action >> Download button.");

                //Validate if file is validated
                try {
                    Thread.sleep(5000);

                    // Verify the downloaded PDF file
                    String userHome = System.getProperty("user.home");
                    String downloadDir = Paths.get(userHome, "Downloads").toString();

                    // Verify the downloaded PDF file
                    String partialUsername = "LacosteNick"; // The partial username you want to check for
                    SeleniumDriverInstance.validateFileDownloaded(partialUsername, downloadDir);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break;
            default:
                error = "Failed due to no Action options.";
        }

        return true;
    }
}
