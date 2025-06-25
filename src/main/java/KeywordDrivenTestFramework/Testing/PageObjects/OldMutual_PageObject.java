package KeywordDrivenTestFramework.Testing.PageObjects;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.Enums;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

public class OldMutual_PageObject extends BaseClass {
    //All xPaths are created this side
    public static String OldMutual_ADE_URL() {
        return Enums.Environment.QA.pageUrl;
    }

    public static String OldMutual_SecureServices_LogIn_Page() {
        return "//div[@class='divnotNewRegisteredUser ']";
    }

    public static String txt_Username() {
        return "//input[@id='username']";
    }

    public static String txt_Password() {
        return "//input[@id='password']";
    }

    public static String btn_LogIn() {
        return "//button[@id='login']";
    }

    public static String PastPerfomanceCalculator_Header() {
        return "//span[text()='Past performance ']";
    }

    public static String drpdwn_FundName() {
        return "//p[text()='Fund Name']//..//div//span[@class='om-dropdown-field__arrow']";
    }

    public static String drpdwn_FundName_Option(String Data) {
        return "//ul[@class='dropdown-options-list']//li[text()='" + Data + "']";
    }

    public static String txt_InvestmentAmount() {
        return "//p[text()='Investment Amount (Rands)']//..//input";
    }

    public static String radio_OnceOff() {
        return "//span[@class='radio-button-group-line']//input[@value='ONCE_OFF']//..";
    }

    public static String radio_Monthly() {
        return "//span[@class='radio-button-group-line']//input[@value='MONTHLY']//..";
    }


    public static String txt_InvestmentTerm() {
        return "//p[text()='Investment term (Years)']//..//input";
    }

    public static String btn_Calculate() {
        return "//div[@class='btn-wrapper']//button";
    }

    public static String InflationOverTheSamePeriod() {
        return "(//h6[text()='Inflation over the same period(%)']//..//..//h6)[3]";
    }


    //Group Life Assurance Header Xpath
    public static String groupLifeAssurance_header() {
        return "//h1[normalize-space()='What is Group Life Assurance?']";
    }

    //Get a Quote button xpath
    public static String button_GetQuote() {
        return "(//button[@class='theme-default primary-large'])[1]";
    }

    public static String GroupLifeCalculatorHeader() {
        return "(//strong[normalize-space()='Group Life Assurance'])[1]";
    }

    //Number of employees xpath
    public static String text_numberOfEmployees() {
        return " //input[@for='numberOfEmployees']";
    }


    //Average age of employees xpath
    public static String text_AverageAgeOfEmployees() {
        return "//input[@for='averageAgeOfEmployees']";
    }

    //Total sum assured per member xpath
    public static String text_TotalSumAssurePerMember() {
        return "//input[@for='totalSumAssured']";
    }

    //Select coverGLA xpath
    public static String dropdown_selectCoverGLA() {
        return "//h6[text()='Select Cover']//..//div//span[@class='om-dropdown-field__arrow']";
    }

    public static String dropdown_selectCoverOptionGLA() {
        return "//ul[@class='dropdown-options-list']//li[text()='GLA']";
    }


    //Select range for GLA xpath
    public static String dropdown_selectRange() {
        return "//h6[text()='Range']//..//div//span[@class='om-dropdown-field__arrow']";
    }

    public static String dropdown_selectRangeOptionGLA() {
        return "//ul[@class='dropdown-options-list']//li[text()='1']";
    }

    //Add cover xpath
    public static String addCover() {
        return "//span[normalize-space()='Add Cover']";
    }

    //Select Temp Disability cover dropdown
    public static String dropdown_selectTempDisabilityCover() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[48]";
    }

    public static String dropdown_selectTempDisabilityCoverOption() {
        return "(//li[@id='TEMPORARY_DISABILITY'])[2]";
    }

    //Select Temp Disability range number from dropdown
    public static String dropdown_selectTempDisabilityRange() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[50]";
    }

    public static String dropdown_selectTempDisabilityRangeOption() {
        return "(//li[@id='1'])[2]";
    }

    //Select Perm Disability cover dropdown
    public static String dropdown_selectPermDisabilityCover() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[52]";
    }

    public static String dropdown_selectPermDisabilityCoverOption() {
        return "(//li[@id='PERMANENT_DISABILITY'])[3]";

    }


    //Select Perm Disability range number from dropdown
    public static String dropdown_selectPermDisabilityRange() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[54]";
    }

    public static String dropdown_selectPermDisabilityRangeOption() {
        return "(//li[@id='1'])[3]";
    }

    //Select Critical illness cover dropdown
    public static String dropdown_selectCriticalIllnessCover() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[56]";
    }

    public static String dropdown_selectCriticalIllnessCoverOption() {
        return "(//li[@id='CRITICAL_ILLNESS'])[4]";

    }

    //Select Critical Illness range number from dropdown
    public static String dropdown_selectCriticalIllnessRange() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[58]";
    }

    public static String dropdown_selectCriticalIllnessRangeOption() {
        return "(//li[@id='0.5'])[1]";
    }


    //Select Medical cover dropdown
    public static String dropdown_selectMedicalCover() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[60]";
    }

    public static String dropdown_selectMedicalCoverOption() {
        return " (//li[@id='MEDICAL_COVER'])[5]";

    }

    //Select Medical Cover range number from dropdown
    public static String dropdown_selectMedicalRange() {
        return "(//*[name()='svg'][@class='om-icon-svg'])[62]";
    }

    public static String dropdown_selectMedicalRangeOption() {
        return " (//li[@id='10 %'])[1]";
    }

    //Calculate xpath
    public static String Calculate() {
        return "(//button[@class='theme-default primary-small'])[5]";
    }

    //Annual Premium
    public static String text_annualPremium() {
        return "  (//div[normalize-space()='(Per Staff)'])[1]";

        //div[class='results-section-results__per-staff-right'] div[class='results-section__per-staff-row-two']
    }

    //Annual Premium Due
    public static String text_annualPremiumDue() {
        return " (//div[@class='results-section__monthly-premium-amount'])[1]";
    }

    //call me back  button
    public static String buttonCallMeBack() {
        return " (//button[@class='theme-default primary-small'])[4]";
    }

    //Return On Investment
    public static String returnOnInvestment() {
        return "(//h6[text()='Return of Investment(%)']//..//..//h6)[4]";
    }

    public static String Register_btn() {
        return "//a[text()='Register']";
    }

    public static String IDType_dropdown() {
        return "(//span[contains(text(),'ID Type')]//..//div//div[@id='caret'])[1]";
    }

    public static String IDType_dropdown_Option(String Data) {
        return "//span[contains(text(),'ID Type')]//..//div//div[@id='caret']//..//a[text()='" + Data + "']";
    }

    public static String FirstName_txt() {
        return "//input[@id='FirstName']";
    }

    public static String Surname_txt() {
        return "//input[@id='Surname']";
    }

    public static String ID_Number_txt() {
        return "//input[@id='input-id-number']";
    }

    public static String DateOfBirth_text() {
        return "//input[@id='input-DateOfBirth']";
    }

    public static String Passport_Number_txt() {
        return "//span[contains(text(),'PASSPORT NUMBER')]//..//input[@name='IDPassport']";
    }

    public static String countryListError_text() {
        return "//div[@id='countryListError']";
    }

    public static String gender_rbtn(String Data) {
        return "//label[contains(text(),'GENDER')]//..//div[text()='" + Data + "']";
    }

    public static String CountryCode_dropdown() {
        return "//div[@id='pseudoSelectcountryDialingCode']//div[@id='caret']";
    }

    public static String CountryCode_dropdown_option(String country) {
        return "//div[@id='pseudoOptions1']//a[contains(text(),'" + country + "')]";
    }

    public static String cellPhoneNumber_txt() {
        return "//input[@id='input-cellphone']";
    }

    public static String termsAndConditions_chckbx() {
        return "//div[@id='styledCheckBox0']";
    }

    public static String Next_ChooseYourLogInDetails_btn() {
        return "//button[@id='btnSubmit']";
    }

    public static String LogIn_Username() {
        return "//input[@id='username']";
    }

    public static String RegistrationForm() {
        return "//span[contains(text(),'REGISTRATION')]";
    }

    public static String LogIn_Password() {
        return "//input[@id='password']";
    }

    public static String LogIn_Confirm_Password() {
        return "//input[@id='input-password-confirm']";
    }

    public static String FailedMessage() {
        return "//span[contains(text(),'Technical')]";
    }

    public static String LogIn_Registration_Page() {
        return "//span[text()='REGISTRATION']";
    }

    public static String OTP() {
        return "//div[@id='displayOTP']";
    }

    public static String sendMeMyCode() {
        return "//button[@id='btnSendSMS']";
    }

    public static String OTP_textbox() {
        return "//input[@id='txtLinkCellphoneOTP']";
    }

    public static String completeRegistration() {
        return "//button[text()='COMPLETE REGISTRATION']";
    }

    public static String registeredSuccessfully() {
        return "//strong[text()='You have registered successfully']";
    }
}
