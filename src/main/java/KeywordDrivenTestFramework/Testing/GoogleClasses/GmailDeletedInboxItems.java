package KeywordDrivenTestFramework.Testing.GoogleClasses;

import KeywordDrivenTestFramework.Core.BaseClass;

import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.narrator;

import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Testing.PageObjects.GmailPageObject;
import org.kohsuke.rngom.parse.host.Base;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation
        (
                Keyword = "Gmail Deleted Inbox Items",
                createNewBrowserInstance = false
        )

public class GmailDeletedInboxItems extends BaseClass {
    String error = "";

    public GmailDeletedInboxItems(){

    }

    public TestResult executeTest() {
        if (!VerifyGmailPageHasLoaded()) {
            return narrator.testFailed("Failed to navigate to Metropolitan Health Group contact us tab page");
        }
        if (!DeletedInboxItems()) {
            return narrator.testFailed("Failed to deleted inbox items");
        }

        return narrator.finalizeTest("Successfully deleted inbox items");
    }

    public boolean VerifyGmailPageHasLoaded() {
        if (!SeleniumDriverInstance.waitForElementByXpath(GmailPageObject.composeButtonXpath())) {
            error = "Failed to verify page load";
            return false;
        }

        return true;
    }

    public boolean DeletedInboxItems() {
        while (SeleniumDriverInstance.waitForElementByXpath(GmailPageObject.inboxItemSelectCheckboxGenericXpath(narrator.getData("Subject Line Text")))) {
            if (!SeleniumDriverInstance.clickElementbyXpath(GmailPageObject.inboxItemSelectCheckboxGenericXpath(narrator.getData("Subject Line Text")))) {
                error = "Failed to click checkbox.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(GmailPageObject.deleteButtonXpath())) {
                error = "Failed to click delete.";
                return false;
            }
        }

        return true;
    }
}
