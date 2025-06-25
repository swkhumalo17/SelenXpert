package KeywordDrivenTestFramework.Testing.TestClasses.Calendar;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Leads.ContactACustomer;

/**
 * @author SKHUMALO on 2023/08/15.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "ScheduleAMeeting", createNewBrowserInstance = false)
public class ScheduleAMeeting extends BaseClass {
    String error = "";

    ContactACustomer ContactACustomerObj;

    public ScheduleAMeeting() {
        this.ContactACustomerObj = new ContactACustomer();
    }

    public TestResult executeTest() {
        if (!navigateToCalendar()) {
            return narrator.testFailed(error);
        }
        if (!scheduleAMeeting()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully confirmed the attendance for the meetings in the past.");
    }

    public boolean navigateToCalendar() {
        //Navigate to Calendar
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.calendarSidebar())) {
            error = "Failed to wait for the Calendar Sidebar.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.calendarSidebar())) {
            error = "Failed to wait for the Calendar Sidebar.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'Calendar' Sidebar.");

        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myCalender())) {
            error = "Failed to wait for the 'Calendar' header.";
            return false;
        }

        return true;
    }

    public boolean scheduleAMeeting() {
        //Add new button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.AddNew_btn())) {
            error = "Failed to wait for the ADD NEW button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.AddNew_btn())) {
            error = "Failed to click the ADD NEW button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'ADD NEW' button.");

        //New Event
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Event_btn())) {
            error = "Failed to wait for the Event button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Event_btn())) {
            error = "Failed to click the Event button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Event' button.");


        //Customer name
        String customerName = ContactACustomerObj.customerName;

        //Link to an opportunity/customer
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.LinkToAnOpportunity_Customer_drpdwn())) {
            error = "Failed to wait for the opportunity/customer dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.LinkToAnOpportunity_Customer_drpdwn())) {
            error = "Failed to click the opportunity/customer dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Link to an opportunity/customer' dropdown.");

        //Select first opportunity
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.opportunityCustomer_rdbtn())) {
            error = "Failed to wait for the opportunity/customer radio button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.opportunityCustomer_rdbtn())) {
            error = "Failed to click the opportunity/customer radio button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Link to an opportunity/customer' dropdown.");

        //Confirm button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to click the Confirm button for 'Link to an opportunity/customer.'";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm button.");

        pause(2000);
        //Confirm button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.save_btn())) {
            error = "Failed to click the Confirm button for 'Link to an opportunity/customer.'";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm button.");

        return true;
    }
}
