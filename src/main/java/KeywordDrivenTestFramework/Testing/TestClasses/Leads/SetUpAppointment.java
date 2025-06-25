package KeywordDrivenTestFramework.Testing.TestClasses.Leads;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

/**
 * @author SKHUMALO on 2023/08/15.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "setUpAppointment", createNewBrowserInstance = false)
public class SetUpAppointment extends BaseClass {
    String error = "";

    ContactACustomer ContactACustomerObj;
    ManageAppointments ManageAppointmentsObj;

    public SetUpAppointment() {
        this.ContactACustomerObj = new ContactACustomer();
        this.ManageAppointmentsObj = new ManageAppointments();
    }

    public TestResult executeTest() {
        if (!setUpAnAppointment()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully confirmed appointment has been set up successfully.");
    }

    public boolean setUpAnAppointment() {
        //Customer name
        String customerName = ContactACustomerObj.customerName;

        ManageAppointmentsObj.manageAppointments();

        //Validate the status have changed
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.closeCustomerContactedFilters())) {
            error = "Failed to wait for the close button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.closeCustomerContactedFilters())) {
            error = "Failed to click the close button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the close button.");

        //Loading Mask
        if (SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath(DAE_PageObject.loadingMask())) {
                error = "Failed to wait for pop-up loading mask to disappear";
                return false;
            }
        }

        //Validate the Status has changed
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.newAppointmentStatus())) {
            error = "Failed to wait for the new Appointment Status.";
            return false;
        }

        try {
            String newAppointmentStatus_Message = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.myLeadsListAppointmentStatus2(customerName));

            if (!newAppointmentStatus_Message.isEmpty()) {
                narrator.ValidateEqual(newAppointmentStatus_Message, "Scheduled");
                narrator.stepPassedWithScreenShot("Successfully validated the new 'Appointment Status': " + "Scheduled");
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
}
