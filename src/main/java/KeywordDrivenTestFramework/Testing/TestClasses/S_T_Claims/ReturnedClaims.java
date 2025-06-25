package KeywordDrivenTestFramework.Testing.TestClasses.S_T_Claims;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2023/08/29.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "Returned_Claim", createNewBrowserInstance = false)
public class ReturnedClaims extends BaseClass {
    String error = "";
    String StartOdometer = getData("Start Odometer");
    String EndOdometer = getData("End Odometer");
    Approve_Claims approveClaimsObj;
    public ReturnedClaims() {
        this.approveClaimsObj = new Approve_Claims();
    }

    public TestResult executeTest() {
        if (!claimReturned()) {
            narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully submitted a returned claim.");
    }

    public boolean claimReturned() {
        approveClaimsObj.navigateToSTClaims();

        //Approve a claim from a waiting list
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.waitingApproval_Tab())) {
            error = "Failed to wait for the waiting list Tab.";
            return false;
        }

        //Navigate to Claim Returned
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.claimReturned_Tab())) {
            error = "Failed to wait for the Claim Returned Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.claimReturned_Tab())) {
            error = "Failed to click the Claim Returned Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Claim Returned Tab.");


        //expand the first claim
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.claim_drpdwn())) {
            error = "Failed to wait for the 1st claim dropdown button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.claim_drpdwn())) {
            error = "Failed to click the 1st claim dropdown button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 1st claim dropdown.");

        //Start Odometer
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.StartOdometer_txt())) {
            error = "Failed to wait for the Start Odometer textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.StartOdometer_txt(), StartOdometer)) {
            error = "Failed to enter Start Odometer: " + StartOdometer;
            return false;
        }
        narrator.stepPassed("Successfully entered Start Odometer.");

        //End Odometer
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.EndOdometer_txt())) {
            error = "Failed to wait for the End Odometer textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.EndOdometer_txt(), EndOdometer)) {
            error = "Failed to enter End Odometer: " + EndOdometer;
            return false;
        }
        narrator.stepPassed("Successfully entered Start Odometer.");

        //click somewhere
        WebElement element = SeleniumDriverInstance.Driver.findElement(By.xpath(DAE_PageObject.EndOdometer_txt()));
        element.sendKeys(Keys.ENTER);
        element.sendKeys(Keys.TAB);

        //extract the total odometer to claim
        String distanceTravelled = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.distanceTravelled());
        final double rate = Double.parseDouble(SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.ST_RateApplication_xpath()));
        double totalAmount = (Integer.parseInt(distanceTravelled) * rate);

        // Find the button element by its locator (e.g., XPath, ID, CSS selector)
        WebElement button = SeleniumDriverInstance.Driver.findElement(By.xpath("(//button[@class='omd-btn omd-btn-primary'])[1]")); // Replace with your button's locator

        // Get the text of the button
        String buttonText = button.getText();

        // Check if the button text is "Save" or "Update"
        if (buttonText.equals("SAVE CLAIM")) {
            //Update Claim Button
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.saveClaim_btn())) {
                error = "Failed to wait for the 'Save Claim' button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.saveClaim_btn())) {
                error = "Failed to click the 'Save Claim' button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the 'Save Claim' button.");
        } else if (buttonText.equals("UPDATE CLAIM")) {
            //Update Claim Button
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.updateClaim_btn())) {
                error = "Failed to wait for the 'Update Claim' button.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.updateClaim_btn())) {
                error = "Failed to click the 'Update Claim' button.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the 'Update Claim' button.");
        } else {
            System.out.println("The button label is neither 'Save' nor 'Update'.");
        }

        //Wait for Claim for this week has been saved successfully message
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalClaimAmount_popup())) {
            error = "Failed to wait for the 'Claim for this week has been saved Successfully message'.";
            return false;
        }

        String totalClaimAmount_popup = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.totalClaimAmount_popup());

        if (!totalClaimAmount_popup.isEmpty()) {
            narrator.ValidateEqual(totalClaimAmount_popup, "R " + totalAmount);
            narrator.stepPassedWithScreenShot("Successfully validate the total claim amount '" + totalClaimAmount_popup + "' with 'R " + totalAmount + "'.");
        } else {
            error = "Failed to retrieve 'Total Claim Amount'.";
            return false;
        }

        //Done button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.doneClaim_btn())) {
            error = "Failed to wait for the Done button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.doneClaim_btn())) {
            error = "Failed to click the Done button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Done button.");

        //Declaration checkbox
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.adviserDeclaration_chckbx())) {
            error = "Failed to wait for the Adviser Declaration checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.adviserDeclaration_chckbx())) {
            error = "Failed to click the Adviser Declaration checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the adviser declaration checkbox.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.submitClaimForApproval_btn())) {
            error = "Failed to wait for the 'Submit Claim For Approval' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submitClaimForApproval_btn())) {
            error = "Failed to click the 'Submit Claim For Approval' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Submit Claim For Approval' button.");

        //Submitted successfully message pop up
        String successfullyClam_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.claimSuccessfullyMessage());

        if (!successfullyClam_Message.isEmpty()) {
            narrator.ValidateEqual(successfullyClam_Message, "Claim has been submitted Successfully");
            narrator.stepPassedWithScreenShot("Successfully validated the 'Claim has been submitted Successfully' message.");
        } else {
            error = "Failed to retrieve text 'Claim has been submitted Successfully'.";
            return false;
        }

        //Done button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.doneClaimMassage_btn())) {
            error = "Failed to wait for the Done button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.doneClaimMassage_btn())) {
            error = "Failed to click the Done button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Done button.");

        return true;
    }

}
