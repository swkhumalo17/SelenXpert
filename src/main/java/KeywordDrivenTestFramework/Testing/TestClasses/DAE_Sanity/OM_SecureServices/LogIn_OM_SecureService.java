package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity.OM_SecureServices;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.OldMutual_PageObject;

import java.awt.AWTException;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(
        Keyword = "Login",
        createNewBrowserInstance = true
)
public class LogIn_OM_SecureService extends BaseClass {
    String error = "";
    String testCaseName = "";

    public LogIn_OM_SecureService() {

    }
    public TestResult executeTest() throws AWTException {
        if (!SecureService_launchSite()) {
            return narrator.testFailed(error);
        }
        if (!SecureService_LogIn()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully Navigated to Old Mutual - Secure Services");
    }
    public boolean SecureService_launchSite(){
        //Pause for 2 sec and navigate to the URL
        SeleniumDriverInstance.pause(2000);
        if (!SeleniumDriverInstance.navigateTo(OldMutual_PageObject.OldMutual_ADE_URL())) {
            error = "Unable to navigate to Old Mutual - Secure Services site.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.OldMutual_SecureServices_LogIn_Page())) {
            error = "Failed to navigate to Old Mutual - Secure Services LogIn page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to Old Mutual - Secure Services LogIn page.");

        return true;
    }

    public boolean SecureService_LogIn() {
        //Username
        if(!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.txt_Username())){
            error = "Failed to wait for the Username.";
            return false;
        }
        if(!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.txt_Username(), testData.getData("Username"))){
            error = "Failed to enter the Username: '" + testData.getData("Username") + "' .";
            return false;
        }
        narrator.stepPassed("Successfully entered the Username: " + testData.getData("Username"));

        //Password
        if(!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.txt_Password())){
            error = "Failed to wait for the Password.";
            return false;
        }
        if(!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.txt_Password(), testData.getData("Password"))){
            error = "Failed to enter the Password: '" + testData.getData("Password") + "' .";
            return false;
        }
        narrator.stepPassed("Successfully entered the Password: " + testData.getData("Password"));

        //Submit
        if(!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.btn_LogIn())){
            error = "Failed to wait for the LogIn Button.";
            return false;
        }
        if(!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.btn_LogIn())){
            error = "Failed to click the LogIn Button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the LogIn Button.");

        //Home page
        narrator.stepPassedWithScreenShot("Successfully Navigated to Old Mutual - Secure Services");
        /*User is able to view the Adviser sites on the list of available sites
        System should display the Sites available to the Adiviser under "Your Websites".
                Successfully Adiver view the sites available uner Your websites
        7
        Verify the OM Adviser Portal Under "Your Websites".
                System should display the OM Adivser Portal Under "Your Websites".
                OM Adviser Portal available successfully under your websites
        8
        Click on OM Adviser Portal Under Your Websites
        User should be redirected to ADE Dashboard
        Successfully clicks on OM Adviser portal and redirect to ADE Dashboard*/

        return true;
    }

}
