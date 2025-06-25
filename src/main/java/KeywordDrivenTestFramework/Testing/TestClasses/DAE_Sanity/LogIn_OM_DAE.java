package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.Locale;


/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(
        Keyword = "Login_DAE",
        createNewBrowserInstance = true
)
public class LogIn_OM_DAE extends BaseClass {
    String error = "";
    String testCaseName = "";

    public LogIn_OM_DAE() {

    }

    public TestResult executeTest() throws AWTException {
        if (!launchSite()) {
            return narrator.testFailed(error);
        }
        if (!DAE_SecureService_LogIn()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully navigated to OM| DAE.");
    }

    public boolean launchSite() {
        //Pause for 2 sec and navigate to the URL
        pause(2000);
        if (!SeleniumDriverInstance.navigateTo(DAE_PageObject.QA_SSO_URL())) {
            error = "Unable to navigate to OM| DAE SSO site.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to OM| DAE SSO Site.");

        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.SSO_LogIn_Page())) {
            error = "Failed to verify the OM| DAE - LogIn page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to OM| DAE - LogIn page.");

        return true;
    }

    public boolean DAE_SecureService_LogIn() {
        //Username
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.txt_Username())) {
            error = "Failed to wait for the Username.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.txt_Username(), testData.getData("Username"))) {
            error = "Failed to enter the Username: '" + testData.getData("Username") + "' .";
            return false;
        }
        narrator.stepPassed("Successfully entered the Username: " + testData.getData("Username"));

        //Password
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.txt_Password())) {
            error = "Failed to wait for the Password.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.txt_Password(), testData.getData("Password"))) {
            error = "Failed to enter the Password: '" + testData.getData("Password") + "' .";
            return false;
        }
        narrator.stepPassed("Successfully entered the Password: " + testData.getData("Password"));

        //Submit
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.btn_LogIn())) {
            error = "Failed to wait for the LogIn Button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.btn_LogIn())) {
            error = "Failed to click the LogIn Button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the LogIn Button.");
        pause(2000);

        //Verify the OM Adviser Portal Under "Your Websites".

        if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.workbenchSidebar())){
            error = "Failed to wait for Workbench sidebar Menu.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully logged In.");

        return true;
    }



}
