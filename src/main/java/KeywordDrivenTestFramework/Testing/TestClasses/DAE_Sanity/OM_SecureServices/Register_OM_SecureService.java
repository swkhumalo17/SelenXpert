package KeywordDrivenTestFramework.Testing.TestClasses.DAE_Sanity.OM_SecureServices;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.OldMutual_PageObject;
import KeywordDrivenTestFramework.Utilities.IDGenerator.DataGenUtils;
import KeywordDrivenTestFramework.Utilities.IDGenerator.GenerateDOB;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

@KeywordAnnotation(
        Keyword = "Register_SecureService",
        createNewBrowserInstance = true
)
public class Register_OM_SecureService extends BaseClass {
    String error = "";
    String testCaseName = "";

    GenerateDOB GenerateDOBSt = new GenerateDOB(18, 65);
    DataGenUtils DataGenUtilsSt = new DataGenUtils();
    LogIn_OM_SecureService LogIn_OM_SecureService_obj = new LogIn_OM_SecureService();
    public static String Actual_DateOfBirth, Gender, FirstName, Surname, IDNumber, CellPhone_Number, Email;

    public Register_OM_SecureService() {

    }

    public TestResult executeTest() throws Exception {
        if (!LogIn_OM_SecureService_obj.SecureService_launchSite()) {
            return narrator.testFailed(error);
        }
        for(int i = 0; i <= 5; i++) {
            if (!Personal_Details()) {
                return narrator.testFailed(error);
            }
            if (!LogIn_Details()) {
                return narrator.testFailed(error);
            }
            if (!Done()) {
                return narrator.testFailed(error);
            }
        }
        return narrator.finalizeTest("Successfully Registered a client to Old Mutual - Secure Services");
    }

    public boolean Personal_Details() {
        GenerateDOBSt.getIdDateOfBirth();

        Actual_DateOfBirth = GenerateDOBSt.getDateOfBirth();
        Gender = DataGenUtilsSt.getGender();

        //Name
        DataGenUtils.generateFullName(Gender);
        Surname = DataGenUtilsSt.getSurname();
        FirstName = DataGenUtilsSt.getName();
        //IDNo
        IDNumber = DataGenUtilsSt.saidGen(Actual_DateOfBirth, Gender);

        String Country = getData("Country Code");
        CellPhone_Number = DataGenUtilsSt.RandomPhoneNum();

        String IDType = getData("ID Type");
        String Passport = getData("Passport");


        //Register button
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.Register_btn())) {
            error = "Failed to wait for the OM Secure Service Register button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.Register_btn())) {
            error = "Failed to click the OM Secure Service Register button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Register button.");

        // Registration Form
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.RegistrationForm())) {
            error = "Failed to wait for Registration Form.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully viewed the Registration form.");

        //FirstName
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.FirstName_txt())) {
            error = "Failed to wait for the FirstName textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.FirstName_txt(), FirstName)) {
            error = "Failed to enter '" + FirstName + "' into the FirstName textbox.";
            return false;
        }
        narrator.stepPassed("Successfully entered FirstName: " + FirstName);

        //SurName
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.Surname_txt())) {
            error = "Failed to wait for the Surname textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.Surname_txt(), Surname)) {
            error = "Failed to enter '" + Surname + "' into the Surname textbox.";
            return false;
        }
        narrator.stepPassed("Successfully entered Surname: " + Surname);

        //ID Type
        //dropdown
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.IDType_dropdown())) {
            error = "Failed to wait for the ID Type dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.IDType_dropdown())) {
            error = "Failed to click the ID Type dropdown.";
            return false;
        }
        //Option
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.IDType_dropdown_Option(IDType))) {
            error = "Failed to click the ID Type dropdown option.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the ID Type: " + IDType);

        switch (IDType) {
            case "South African ID":
                if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.ID_Number_txt())) {
                    error = "Failed to wait for the ID Number.";
                    return false;
                }
                if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.ID_Number_txt(), IDNumber)) {
                    error = "Failed to enter the ID Number: " + IDNumber;
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully entered the ID Number: " + IDNumber);

                pause(2000);

                /*Validate the DATE OF BIRTH (dd,mm/yyyy)
                String DateOfBirth = SeleniumDriverInstance.retrieveTextByXpath(OldMutual_PageObject.DateOfBirth_text());

                if(!narrator.ValidateEqual(DateOfBirth, Actual_DateOfBirth.replace(',','/'))){
                    error = "Failed to validate Date of Birth: '" + DateOfBirth + "' with '" + Actual_DateOfBirth + "'.";
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully validated the Date of Birth: '" + DateOfBirth + "' with '" + Actual_DateOfBirth + "'.");
*/
                break;
            case "Passport":
            case "Non-South African ID":
                if (IDType.equalsIgnoreCase("Passport")) {
                    //Passport
                    if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.Passport_Number_txt())) {
                        error = "Failed to wait for the Passport Number.";
                        return false;
                    }
                    if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.Passport_Number_txt(), Passport)) {
                        error = "Failed to enter the Passport Number: " + Passport;
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully entered the Passport Number: " + Passport);
                } else if (IDType.equalsIgnoreCase("Non-South African ID")) {
                    if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.ID_Number_txt())) {
                        error = "Failed to wait for the ID Number.";
                        return false;
                    }
                    if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.ID_Number_txt(), IDNumber)) {
                        error = "Failed to enter the ID Number: " + IDNumber;
                        return false;
                    }
                    narrator.stepPassedWithScreenShot("Successfully entered the ID Number: " + IDNumber);
                }
                //Country Error message
                if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.countryListError_text())) {
                    error = "Failed to wait for the Passport Number.";
                    return false;
                }
                narrator.stepPassed("Successfully waited for Country Error Message.");

                //Gender
                if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.gender_rbtn(Gender))) {
                    error = "Failed to wait for the gender: " + Gender;
                    return false;
                }
                if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.gender_rbtn(Gender))) {
                    error = "Failed to click gender: " + Gender;
                    return false;
                }
                narrator.stepPassedWithScreenShot("Successfully clicked Gender: " + Gender);

                break;
            default:
                error = "Failed due to no ID type exist on the list.";
        }

        //CellPhone Country Code
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.CountryCode_dropdown())) {
            error = "Failed to wait for the Country Code Dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.CountryCode_dropdown())) {
            error = "Failed to click the Country Code Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Country Code dropdown.");

        //Country code
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.CountryCode_dropdown_option(Country))) {
            error = "Failed to wait for the Country Code option.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.CountryCode_dropdown_option(Country))) {
            error = "Failed to wait for the Country Code option.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Country Code dropdown option: " + Country);

        //CellPhone Number
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.cellPhoneNumber_txt())) {
            error = "Failed to wait for the Cell Number.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.cellPhoneNumber_txt(), CellPhone_Number)) {
            error = "Failed to wait for the Cell Number: " + CellPhone_Number;
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the CellPhone Number: " + CellPhone_Number);

        //Terms and Conditions
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.termsAndConditions_chckbx())) {
            error = "Failed to wait for the Terms and Conditions checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.termsAndConditions_chckbx())) {
            error = "Failed to wait for the Terms and Conditions checkbox.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Country Code dropdown option: " + Country);

        //Next: choose your login details
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.Next_ChooseYourLogInDetails_btn())) {
            error = "Failed to wait for the Next: choose your login details button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.Next_ChooseYourLogInDetails_btn())) {
            error = "Failed to wait for the Next: choose your login details button button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Next: choose your login details button.");

        return true;
    }

    public boolean LogIn_Details() {
        String Email = DataGenUtilsSt.emailGen();
        String Password = DataGenUtilsSt.generatePassayPassword();

        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.LogIn_Registration_Page())) {
            String FailedMessage = SeleniumDriverInstance.retrieveTextByXpath(OldMutual_PageObject.FailedMessage());
            error = "Failed due to " + FailedMessage;
            return false;
        }
        //Username
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.LogIn_Username())) {
            error = "Failed to wait for the Username textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.LogIn_Username(), Email)) {
            error = "Failed to wait for the Username textbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the username: " + Email);

        //Password
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.LogIn_Password())) {
            error = "Failed to wait for the Password textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.LogIn_Password(), Password)) {
            error = "Failed to wait for the Password textbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the Password: " + Password);

        //Confirm password
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.LogIn_Confirm_Password())) {
            error = "Failed to wait for the Confirm Password textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.LogIn_Confirm_Password(), Password)) {
            error = "Failed to wait for the Confirm Password textbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the Confirm Password: " + Password);

        //OTP
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.sendMeMyCode())) {
            error = "Failed to wait for the Send Me My Code button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.sendMeMyCode())) {
            error = "Failed to click the Send Me My Code button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Send Me My Code button");

        //One Time Pin
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.OTP())) {
            error = "Failed to wait for the One Time Pin.";
            return false;
        }
        String OneTimePin = SeleniumDriverInstance.retrieveTextByXpath(OldMutual_PageObject.OTP());

        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.OTP_textbox())) {
            error = "Failed to wait for the One Time Pin.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(OldMutual_PageObject.OTP_textbox(), OneTimePin)) {
            error = "Failed to enter the One Time Pin.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully entered the OTP: " + OneTimePin);

        //Complete regestration
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.completeRegistration())) {
            error = "Failed to wait for the Complete Registration button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(OldMutual_PageObject.completeRegistration())) {
            error = "Failed to click the Complete Registration button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Complete Registration button");

        //Successfully created the user message
        if (!SeleniumDriverInstance.waitForElementByXpath(OldMutual_PageObject.registeredSuccessfully())) {
            error = "Failed to wait for the registration message.";
            return false;
        }
        String registrationMessage = SeleniumDriverInstance.retrieveTextByXpath(OldMutual_PageObject.registeredSuccessfully());

        if (narrator.ValidateEqual("You have registered successfully", registrationMessage)) {
            narrator.stepPassedWithScreenShot("Successfully registered the client.");
        } else {
            error = "Failed to register the client and no successfully registration message.";
            return false;
        }

        return true;
    }

    public boolean Done() throws Exception {
        // Existing Excel file to add more rows
        String filePath = "/Users/SKHUMALO/Desktop/ClientList/ClientList.xlsx";

        // List of Employee objects to add to Excel file
        List<Client> clientList = new ArrayList<>();
        clientList.add(new Client(FirstName, Surname, Email, IDNumber));

        // Add more rows to Excel file
        WriteToExcel excelFileService = new WriteToExcel();
        excelFileService.addRows(filePath, clientList);

        return true;
    }

}
