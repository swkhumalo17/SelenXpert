package KeywordDrivenTestFramework.Testing.TestClasses.Calendar;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/08/15.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "Attendance", createNewBrowserInstance = false)
public class Attendance extends BaseClass {
    String error = "";
    String AppointmentStatus = getData("Appointment Status");
    String AppointmentStatus2 = getData("Appointment Status2");
    String claims = getData("S & T Claims");

    public TestResult executeTest() {
        if (!navigateToCalendar()) {
            return narrator.testFailed(error);
        }
        if (!attendance()) {
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

    public boolean attendance() {
        //navigate to Attendance Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.attendance_Tab())) {
            error = "Failed to wait for the attendance tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.attendance_Tab())) {
            error = "Failed to click the attendance tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Attendance tab.");

        //Validate the 'Confirm Attendance For The Meetings In The Past' on the attendance tab
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.attendance_Header())) {
            error = "Failed to validate the 'Confirm Attendance For The Meetings In The Past' header is visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'Attendance' tab.");

        pause(1500);
        //Appointment Status
        String appointmentStatus;
        try {
            appointmentStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.appointmentStatus());
        } catch (Exception e) {
            error = "Failed to retrieve the appointment status.";
            return false;
        }

        //Action Change Status
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionChangeStatus_btn())) {
            error = "Failed to wait for the attendance >> Action >> Change Status.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionChangeStatus_btn())) {
            error = "Failed to click the attendance >> Action >> Change Status.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Attendance tab.");

        //Change Status popup
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.changeStatus_popup())) {
            error = "Failed to wait for the change status pop-up.";
            return false;
        }
        pause(1500);

        //Re-schedule / Attended
        if (appointmentStatus.equalsIgnoreCase("Re-scheduled") || appointmentStatus.equalsIgnoreCase("Scheduled")) {
            //Change status dropdown
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.appointmentStatus_drpdwn())) {
                error = "Failed to click the change status dropdown.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.changeStatus_option(AppointmentStatus))) {
                error = "Failed to click the refer the Calendar >> Change Status option: '" + AppointmentStatus + "'.";
                return false;
            }
            narrator.stepPassed("Successfully clicked the change status option: " + AppointmentStatus);

            //label[text()='Do you want to claim S & T for this meeting?']
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.doClaims())) {
                error = "Failed to wait for the 'Do you want to claim S & T for this meeting?' to be visible.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.doClaims_option(claims))) {
                error = "Failed to click the 'Do you want to claim S & T for this meeting?' checkbox: " + claims;
                return false;
            }

        } else if (appointmentStatus.equalsIgnoreCase("Attended")) {
            //Appointment status dropdown
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.appointmentStatus_drpdwn())) {
                error = "Failed to click the Change Status >> Appointment Status dropdown.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.changeStatus_option(AppointmentStatus2))) {
                error = "Failed to click the refer the Calendar >> Appointment Status option: '" + AppointmentStatus2 + "'.";
                return false;
            }
            narrator.stepPassed("Successfully clicked the Appointment Status option: " + AppointmentStatus2);
        }

        //Submit button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.submit_btn())) {
            error = "Failed to click the Submit button for 'Do you want to claim S & T for this meeting?'";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Submit button.");

        //Status changed success message
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.submittedSuccessfully())) {
            error = "Failed to wait for msg to appear.";
            return false;
        }
        String submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());

        //Validate the success message
        try {
            if (!submittedSuccessfully_msg.isEmpty()) {
                narrator.ValidateEqual(submittedSuccessfully_msg, "Changed successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the " + submittedSuccessfully_msg);
            } else {
                error = "Failed to validate the success message: " + submittedSuccessfully_msg;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        pause(3000);

        return true;
    }
}
