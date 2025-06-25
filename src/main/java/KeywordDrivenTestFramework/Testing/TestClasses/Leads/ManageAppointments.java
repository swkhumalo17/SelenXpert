package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/09/26.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "ManageAllAppointments", createNewBrowserInstance = false)
public class ManageAppointments extends BaseClass {
    String customerName;
    String error = "";

    public TestResult executeTest() {
        if (!navigateToLeads()) {
            return narrator.testFailed(error);
        }
        if (!manageAppointments()) {
            return narrator.testFailed(error);
        }

        return narrator.finalizeTest("Successfully managed the appointment for customer: " + customerName);
    }

    public boolean navigateToLeads() {
        //Navigate to the Leads Tab
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.Leads_SideBar())) {
            error = "Failed to wait for the Leads Tab.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.Leads_SideBar())) {
            error = "Failed to click the Leads Tab.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Leads SideBar.");

        //To Be Actioned By Me page
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myLeadsListTable())) {
            error = "Failed to wait for the 'My Leads Table' page.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigated to 'To Be Actioned By Me' page.");

        return true;
    }

    public boolean manageAppointments() {
        pause(5000);
        //scroll down a bit to get a better view of the table
        if (!SeleniumDriverInstance.scrollToElement("//div[@class='TreeSelectPopover-overViewHeaderLeft']")) {
            error = "Failed to scroll to My Leads List page.";
            return false;
        }

        customerName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.CustomerName());

        //If Status isEmpty we need to set up an appointment whereby we'll able to manage those appointments
        String appointmentStatus = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListAppointmentStatus2(customerName));

        try {
            if (appointmentStatus.isEmpty() || appointmentStatus.equalsIgnoreCase("-")) {
                //Set up an appointment
                actionSetUpAppointments();
            }
        } catch (Exception e) {
            error = e.getMessage();
        }

        pause(2000);
        if (appointmentStatus.equalsIgnoreCase("Scheduled") || appointmentStatus.equalsIgnoreCase("Re-scheduled")) {
            //Manage Appointments
            actionManageAppointments();
        } else if (appointmentStatus.equalsIgnoreCase("Attended")) {
            //Undo an appointment
            undoAttendedAppointment();
            System.out.println("Just checking");
        } else {
            error = "Please check the 'Appointment Status'.";
            return false;
        }

        return true;
    }

    public boolean actionSetUpAppointments() {
        //Action Set Up Appointments
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionSetUpAppointment_btn())) {
            error = "Failed to wait for the Action >> Set Up Appointment.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionSetUpAppointment_btn())) {
            error = "Failed to click the Action >> Set Up Appointment.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> Set Up Appointment button.");

        //New Event header
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.eventHeader())) {
            error = "Failed to wait for the New Event header.";
            return false;
        }
        narrator.stepPassed("Successfully validated the 'New Event' header is visible.");

        //New Event Name
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.newEventName_txt())) {
            error = "Failed to wait for the Event Name textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.newEventName_txt(), getData("New Event Name"))) {
            error = "Failed to click the Event Name: " + getData("New Event Name");
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Event Name': " + getData("New Event Name"));

        //Save button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.save_btn())) {
            error = "Failed to wait for the 'Save' button.";
            return false;
        }
        if (!SeleniumDriverInstance.doubleClickElementbyXpath(DAE_PageObject.save_btn())) {
            error = "Failed to click the 'Save' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Save' button.");

        if (!SeleniumDriverInstance.waitForElementToBeVisibleByXpath(DAE_PageObject.submittedSuccessfully())) {
            error = "Failed to wait for msg to appear.";
            return false;
        }
        String submittedSuccessfully_msg = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.submittedSuccessfully());

        //Validate the success message
        try {
            if (!submittedSuccessfully_msg.isEmpty()) {
                narrator.ValidateEqual(submittedSuccessfully_msg, "Created successfully");
                narrator.stepPassedWithScreenShot("Successfully validated the " + submittedSuccessfully_msg);
            } else {
                error = "Failed to validate the success message: " + submittedSuccessfully_msg;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean actionManageAppointments() {
        //Action >> More  pause(2000);
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to wait for the Action >> More button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to click the Action >> More button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> More button.");

        //Manage Appointments
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.manageAppointment_list())) {
            error = "Failed to wait for the Manage Appointment button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.manageAppointment_list())) {
            error = "Failed to click the Manage Appointment button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the Manage Appointment button.");

        //Manage Appointment Card Header
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.manageAppointmentCardHeader())) {
            error = "Failed to wait for the 'Manage Appointment' card header.";
            return false;
        }
        narrator.stepPassed("Successfully validated the 'Manage Appointment' card header is visible.");

        String manageAppointmentChoice = getData("Manage Appointment Choice");
        switch (manageAppointmentChoice) {
            case "Cancel":
                //Cancel an Appointment
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.cancelAppointment())) {
                    error = "Failed to wait for the 'Cancel' button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.cancelAppointment())) {
                    error = "Failed to click the 'Cancel' button.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the 'Cancel' button.");

                //Confirmation message
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
                    error = "Failed to wait for the confirmation pop-up";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
                    error = "Failed to click the confirmation pop-up";
                    return false;
                }
                narrator.stepPassed("Successfully clicked 'Yes' in alert pop-up.");

                //Cancel Success message
                String manageAppointmentSuccess_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

                if (!manageAppointmentSuccess_Message.isEmpty()) {
                    narrator.ValidateEqual(manageAppointmentSuccess_Message, "Success!");
                    narrator.stepPassedWithScreenShot("Successfully validated the successfully message.");
                } else {
                    error = "Failed to validate Successful message: " + manageAppointmentSuccess_Message;
                    return false;
                }

                break;
            case "Re-schedule":
                //Re-schedule an Appointment
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.rescheduleAppointment())) {
                    error = "Failed to wait for the 'Re-schedule' button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.rescheduleAppointment())) {
                    error = "Failed to click the 'Re-schedule' button.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the 'Re-schedule' button.");

                pause(2000);
                //Edit Event popup
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.editEventCard_popup())) {
                    error = "Failed to wait for the Edit Event Card pop-up";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.startTime_dropdown())) {
                    error = "Failed to click the Edit Event Card >> Start Time dropdown.";
                    return false;
                }
                if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.startTime_option("08"))) {
                    error = "Failed to scroll to the Edit Event Card >> Start Time dropdown.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.startTime_option("10"))) {
                    error = "Failed to click the Edit Event Card >> Start Time dropdown.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked 'Yes' in alert pop-up.");

                //Ok button
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.ok_btn())) {
                    error = "Failed to click the 'OK' button to confirm selected time.";
                    return false;
                }

                //Save button
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.save_btn())) {
                    error = "Failed to wait for the 'Save' button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.save_btn())) {
                    error = "Failed to click the 'Save' button.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully clicked the 'Save' button.");

                //Cancel Success message
                manageAppointmentSuccess_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.appointmentSuccessMessage());

                if (!manageAppointmentSuccess_Message.isEmpty()) {
                    narrator.ValidateEqual(manageAppointmentSuccess_Message, "Success!");
                    narrator.stepPassedWithScreenShot("Successfully validated the successfully message.");
                } else {
                    error = "Failed to validate Successful message: " + manageAppointmentSuccess_Message;
                    return false;
                }

                break;
            case "Confirm Attendance":
                //Confirm Attendance for scheduled appointment
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmAttendence())) {
                    error = "Failed to wait for the 'Confirm Attendance' button.";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmAttendence())) {
                    error = "Failed to click the 'Confirm Attendance' button.";
                    return false;
                }
                narrator.stepPassed("Successfully clicked the 'Confirm Attendance' button.");

                //Confirmation message
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
                    error = "Failed to wait for the confirmation pop-up";
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
                    error = "Failed to click the confirmation pop-up";
                    return false;
                }
                narrator.stepPassed("Successfully clicked 'Yes' in alert pop-up.");

                //Success message
                manageAppointmentSuccess_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

                if (!manageAppointmentSuccess_Message.isEmpty()) {
                    narrator.ValidateEqual(manageAppointmentSuccess_Message, "Success!");
                    narrator.stepPassedWithScreenShot("Successfully validated the successfully message.");
                } else {
                    error = "Failed to validate Successful message: " + manageAppointmentSuccess_Message;
                    return false;
                }

                break;
            default:
                error = "Failed due to data set is not applicable.";
                return false;
        }

        //Validate the Status has changed
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.newAppointmentStatus())) {
            error = "Failed to wait for the new Appointment Status.";
            return false;
        }

        try {
            String newAppointmentStatus_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListAppointmentStatus(customerName));

            if (!newAppointmentStatus_Message.isEmpty()) {
                narrator.ValidateEqual(newAppointmentStatus_Message, manageAppointmentChoice);
                narrator.stepPassedWithScreenShot("Successfully validated the new 'Appointment Status': " + manageAppointmentChoice);
            } else {
                error = "Failed to validate Successful message: " + newAppointmentStatus_Message;
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        return true;
    }

    public boolean undoAttendedAppointment() {
        //Action >> More
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to wait for the Action >> More button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.actionMore_btn())) {
            error = "Failed to click the Action >> More button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Action >> More button.");

        //Manage Appointments
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.manageAppointment_list())) {
            error = "Failed to wait for the Manage Appointment button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.manageAppointment_list())) {
            error = "Failed to click the Manage Appointment button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully click the Manage Appointment button.");

        //Manage Appointment Card Header
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.manageAppointmentCardHeader())) {
            error = "Failed to wait for the 'Manage Appointment' card header.";
            return false;
        }
        narrator.stepPassed("Successfully validated the 'Manage Appointment' card header is visible.");


        //Undo an Appointment
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.cancelAppointment())) {
            error = "Failed to wait for the 'Undo' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.cancelAppointment())) {
            error = "Failed to click the 'Undo' button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Undo' button.");

        //Confirmation message
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.confirmCustomerList_popup())) {
            error = "Failed to wait for the confirmation pop-up";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirmCustomerList_popup_option("YES"))) {
            error = "Failed to click the confirmation pop-up";
            return false;
        }
        narrator.stepPassed("Successfully clicked 'Yes' in alert pop-up.");

        //Cancel Success message
        String manageAppointmentSuccess_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.campaignArchiveMessage());

        if (!manageAppointmentSuccess_Message.isEmpty()) {
            narrator.ValidateEqual(manageAppointmentSuccess_Message, "Success!");
            narrator.stepPassedWithScreenShot("Successfully validated the successfully message.");
        } else {
            error = "Failed to validate Successful message: " + manageAppointmentSuccess_Message;
            return false;
        }

        //Validate the Status has changed
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.newAppointmentStatus())) {
            error = "Failed to wait for the new Appointment Status.";
            return false;
        }

        return true;
    }
}
