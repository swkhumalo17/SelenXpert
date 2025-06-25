package KeywordDrivenTestFramework.Testing.PageObjects;
import KeywordDrivenTestFramework.Core.BaseClass;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class GmailPageObject extends BaseClass{
    public static String GmailURL() {
        // Use ENUM
        return "https://mail.google.com/";
    }

    public static String Google() {
        // Use ENUM
        return "https://www.google.com/";
    }

    public static String search() {
        // Use ENUM
        return "//input[@type = 'submit']";
    }

    public static String emailTextBoxXpath() {
        return "//input[@type='email']";
    }

    public static String emailOrPhoneTextBoxXpath() {
        return "//input[@id='identifierId']";
    }

    public static String nextButtonXpath() {
        return "//input[@id='next']";
    }

    public static String newNextButtonXpath() {
        return "(//span[text()='Next']//..//div)[1]";
    }

    public static String passwordTextBoxXpath() {
        return "//input[@id='Passwd']";
    }

    public static String newPasswordTextBoxXpath() {
        return "//input[@type='password']";
    }

    public static String signInButtonXpath() {
        return "//input[@id='signIn']";
    }

    public static String composeButtonXpath() {
        return "//div[text()='Compose']";
    }

    public static String recipientsTextBoxXpath() {
        return "//textarea[@aria-label='To']";
    }

    public static String subjectTextBoxXpath() {
        return "//input[@name='subjectbox']";
    }

    public static String messageBodyTextBoxXpath() {
        return "//div[@aria-label='Message Body']";
    }

    public static String sendButtonXpath() {
        return "//div[text()='Send']";
    }

    public static String sendConfirmationMessageXpath() {
        return "//div//span[contains(text(),'Message sent')]";
    }

    public static String inboxItemSelectCheckboxGenericXpath(String subjectLineText) {
        return "//span/b[contains(text(),'" + subjectLineText + "')]/../../../../../..//div[@role='checkbox']";
    }

    public static String deleteButtonXpath() {
        return "//div[@data-tooltip='Delete']";
    }

    public static String doneButton() {
        return "//span[text() = 'Done']";
    }

    public static String trackerURL() {
        return "https://ievolveqa.tracker.co.za/UI";
    }

}
