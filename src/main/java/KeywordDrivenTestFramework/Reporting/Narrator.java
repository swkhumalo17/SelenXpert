package KeywordDrivenTestFramework.Reporting;

import KeywordDrivenTestFramework.Core.BaseClass;

import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.SikuliDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.reportDirectory;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Utilities.ZipDirectory;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import static java.lang.System.err;
import static java.lang.System.out;

import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Narrator extends BaseClass {
    private static final String formatStr = "%n%-24s %-20s %-60s %-25s";
    private String line = "______________________________________________________________________________________________________________________________________";
    private static String logMessage = "";
    private static int counter = 0;
    //private long totalTime = 0;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:ms");
    //private static Date startDate;

    private static ExtentSparkReporter htmlReporter;
    private static ExtentReports extentReports;

    private String ExtentLocation;
    public static int ScreenShotCounter = 0;
    public static String ScreenShotName;

    private boolean testRailPassed = true;
    String directory;
    String extentReportDirectory = "";

    private static ExtentTest currentTest;
    private static ExtentTest childTest;

    public File logfile;

    List<String> filesListInDir = new ArrayList<String>();


    //Initializes the Narrator class
    public Narrator() {
        this.setStartTime();
        directory = reportDirectory + "/Narrator_Log.txt";
        extentReportDirectory = reportDirectory + "/extentReport.html";

        //checks if file exists if not, create it
        try {
            createReportFiles();

            //initializes the text file with new test class data
            counter = 0;

            if (testData != null) {
                Files.write(logfile.toPath(), (String.format(formatStr, dateFormat.format(new Date()), "- [KEYS] START KEYWORD:", testData.TestCaseId, "")).getBytes());
            }

        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }

        System.setProperty("LatestReportFolder", reportDirectory);

        htmlReporter = new ExtentSparkReporter(extentReportDirectory);
        htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");
        Locale.setDefault(Locale.ENGLISH);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        //Set up DB Reporter - Enable for GTC CPT Projects
        //initialiseKlovReporter("UTAH-V4", resolveScenarioName());
        //Set System Info
        extentReports.setSystemInfo("Testing Machine Operating System", System.getProperty("os.name"));
//        extentReports.setSystemInfo("Environment", currentEnvironment.name());
        //       extentReports.setSystemInfo("Testing Device", currentDevice.name());
//        extentReports.setSystemInfo("Mobile Operating System", currentDevice.platform.name());
        extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);

    }

    public Narrator(String TestCaseId, String TestDescription) {
        this.setStartTime();
        directory = reportDirectory + "/Narrator_Log.txt";
        extentReportDirectory = reportDirectory + "/extentReport.html";

        try {
            createReportFiles();

            //initializes the text file with new test class data
            counter = 0;
            PrintWriter writer = new PrintWriter(new FileWriter(logfile, true));
            //writer.println(" ");
            writer.println(String.format(formatStr, dateFormat.format(new Date()), "- [KEYS] START KEYWORD:", TestCaseId, ""));
            writer.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }

        System.setProperty("LatestReportFolder", reportDirectory);

        htmlReporter = new ExtentSparkReporter(extentReportDirectory);
        htmlReporter.config().setTimeStampFormat("mm/dd/yyyy hh:mm:ss a");
        Locale.setDefault(Locale.ENGLISH);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);

        //Set up DB Reporter - Enable for GTC CPT Projects
        //initialiseKlovReporter("UTAH-V4", resolveScenarioName());
        //Set System Info
        extentReports.setSystemInfo("Testing Machine Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", Enums.Environment.QA.name());
        extentReports.setSystemInfo("Mobile Operating System", Enums.MobilePlatform.Android.name());

        extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
        currentTest = extentReports.createTest(testCaseId, TestDescription);

    }

    public void createReportFiles() throws IOException {
        logfile = new File(directory);
        File reportfile = new File(extentReportDirectory);
        File reportDirectoryFile = new File(reportDirectory);

        if (!logfile.exists()) {
            reportDirectoryFile.mkdirs();
            //logfile.createNewFile();
        }
        if (!reportfile.exists()) {
            //Files.createDirectories(reportDirectoryFile.toPath());
            //Files.createFile(reportfile.toPath());

            //reportDirectoryFile.mkdirs();
            //reportfile.createNewFile();
        }
    }

    public void startTest() {
        extentReports.flush();
        currentTest = extentReports.createTest(testData.TestCaseId, testData.TestDescription);
    }

    public void skipTest_TestPack_Disabled() {
        extentReports.flush();
        currentTest = extentReports.createTest(testData.TestCaseId, testData.TestDescription);

        currentTest.skip("Test Skipped in test pack");

        extentReports.flush();
    }

    public void flushReports() {
        extentReports.flush();
    }

    //Takes the screenshot
    public static boolean takeScreenShot(boolean testStatus, String message) {
        if (SeleniumDriverInstance != null && SeleniumDriverInstance.isDriverRunning()) {
            SeleniumDriverInstance.takeScreenShot(Integer.toString(++counter), !testStatus);
            return true;
        } else if (SikuliDriverInstance != null && SikuliDriverInstance.isDriverRunning()) {
            SikuliDriverInstance.TakeScreenshot(Integer.toString(++counter), !testStatus);
            return true;
        }

        return false;
    }

    public void takeScreenShot2(boolean testStatus, String message) {
        if (SeleniumDriverInstance != null && SeleniumDriverInstance.isDriverRunning()) {
            SeleniumDriverInstance.takeScreenShot(Integer.toString(++counter), !testStatus);

        } else if (SikuliDriverInstance != null && SikuliDriverInstance.isDriverRunning()) {
            SikuliDriverInstance.TakeScreenshot(Integer.toString(++counter), !testStatus);
        }

    }

    //Used when a test passes
    //Writes to the text file and writes the html file
    public void stepPassed(String message) {
        ScreenShotCounter++;

        logMessage = formatMessage(message);
        logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);
        currentTest.log(Status.PASS, message);
    }

    public void stepFailed(String message) {
        ScreenShotCounter++;

        logMessage = formatMessage(message);
        logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);
        currentTest.log(Status.FAIL, message);
    }

    public void stepInformation(String message) {
        ScreenShotCounter++;

        logMessage = formatMessage(message);
        logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);

        currentTest.log(Status.INFO, message);
    }

    public void stepPassedWithScreenShot(String message) {
        try {
            ScreenShotCounter++;

            logMessage = formatMessage(message);
            logInfo("- [INFO] STEP " + ++counter + ":" + logMessage);

            takeScreenShot(true, logMessage);
            currentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(getRelativeScreenshotPath()).build());

        } catch (Exception ex) {
            /// TO-DO Add Exception Hanlding
            System.out.println("");
        }

    }

    public boolean ValidateEqual(String valueOne, String valueTwo) {
        //Boolean validation result flag:
        boolean ValidationSuccess = false;
        try {
            //Log message:
            String message = " Actual Value: " + valueOne + "<br> Expected Value: " + valueTwo + "";

            logMessage = formatMessage(message);
            logInfo("- VALIDATION: " + ++counter + ":" + logMessage);

            //If statement for validation pass/fail:
            if (valueOne.equalsIgnoreCase(valueTwo)) {
                currentTest.log(Status.PASS, message);
                ValidationSuccess = true;
            } else {
                currentTest.log(Status.FAIL, message);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ValidationSuccess;
    }

    public boolean ValidateEqual(String valueOne, String valueTwo, String validation) {
        //Boolean validation result flag:
        boolean ValidationSuccess = false;
        try {
            //Log message:
            String message = " Actual " + validation + " : " + valueOne + "<br> Expected " + validation + " : " + valueTwo + "";

            logMessage = formatMessage(message);
            logInfo("- VALIDATION: " + ++counter + ":" + logMessage);

            //If statement for validation pass/fail:
            if (valueOne.equalsIgnoreCase(valueTwo)) {
                currentTest.log(Status.PASS, message);
                ValidationSuccess = true;
            } else {
                currentTest.log(Status.FAIL, message);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ValidationSuccess;
    }

    public boolean ValidateEqual(String valueOne, String valueTwo, String FirstMessage, String SecondMessage) {
        //Boolean validation result flag:
        boolean ValidationSuccess = false;
        try {
            //Log message:
            String message = " Expected Couchbase: " + FirstMessage + valueOne + "<br> Actual Couchbase:" + SecondMessage + valueTwo + "";

            logMessage = formatMessage(message);
            logInfo("- VALIDATION: " + ++counter + ":" + logMessage);

            //If statement for validation pass/fail:
            if (valueOne.equalsIgnoreCase(valueTwo)) {
                currentTest.log(Status.PASS, message);
                ValidationSuccess = true;
            } else {
                currentTest.log(Status.FAIL, message);
            }

        } catch (Exception e) {
            System.out.println("Failed to validate: " + valueOne + ": " + e.getMessage());
        }

        return ValidationSuccess;
    }

    public void BlockedTest() {
        currentTest.skip("Test was blocked by previous test failure.");
        extentReports.flush();
    }

    //Used where a test fails
    //Writes to the  text file and writes to the html file
    public TestResult testFailed(String message) {
        try {
            ScreenShotCounter++;
            ScreenShotName = ScreenShotCounter + "";
            //takes sceenshot
            if (!takeScreenShot(false, ScreenShotName)) {
                logError("Failed to take a screenshot.");
            }
            addExtractedParameters(this.testData);

            logMessage = formatMessage(message);
            currentTest.fail(message, MediaEntityBuilder.createScreenCaptureFromPath(getRelativeScreenshotPath()).build());
            extentReports.flush();
            //Writes info to the text file

            logFailure(" STEP " + ++counter + ":" + logMessage);

            testRailPassed = false;

        } catch (Exception ex) {
            //failed to log extent failure
        }

        //returns results
        return new TestResult(testData, Enums.ResultStatus.FAIL, message, this.getTotalExecutionTime());
    }

    public void testFailedCucumber(String message) {
        try {
            ScreenShotCounter++;
            ScreenShotName = ScreenShotCounter + "";
            //takes sceenshot
            if (!takeScreenShot(false, ScreenShotName)) {
                logError("Failed to take a screenshot.");
            }

            logMessage = formatMessage(message);
//
            currentTest.addScreenCaptureFromPath(getRelativeScreenshotPath());

            extentReports.flush();
            //Writes info to the text file

            logFailure(" STEP " + ++counter + ":" + logMessage);

            testRailPassed = false;
        } catch (Exception ex) {
            //failed to log extent failure
        }

    }

    //Use at the end of a test class when everything passes
    //Writes to the text file and writes the html file
    public TestResult finalizeTest(String message) {
        try {
            ScreenShotCounter++;
            screenshotPath = "" + ScreenShotCounter;
            logMessage = formatMessage(message);

            //takes sceenshot
            takeScreenShot(true, screenshotPath);

            currentTest.pass(message, MediaEntityBuilder.createScreenCaptureFromPath(getRelativeScreenshotPath()).build());
            addExtractedParameters(this.testData);

            extentReports.flush();

            copyReportFileForJenkins();
        } catch (Exception ex) {
            //failed to log extent failure
        }
        //returns results
        return new TestResult(testData, Enums.ResultStatus.PASS, message, this.getTotalExecutionTime());
    }

    public void stepWarning(String message) {
        try {
            logMessage = formatMessage(message);
            currentTest.warning("<span style='font-weight:bold;font-family: Roboto, sans-serif; '>" + message + "</span>");
            //Writes info to the text file
            logInfo("- [WARNING] STEP " + ++counter + ":" + logMessage);
        } catch (Exception e) {
            System.out.println("Could not find current URL - " + e.getMessage());
        }

    }

    public void addExtractedParameters(TestEntity testData) {
        ArrayList keys = new ArrayList();
        ArrayList values = new ArrayList();
        ArrayList status = new ArrayList();
        if (testData.ExtractedParameters != null) {
            logMessage = "Extracted Parameters:";

            String extractedParameters = "<span style='font-weight:bold;font-family: Georgia;'>" + logMessage + "</span></br><table>";

            for (String key : testData.ExtractedParameters.keySet()) {
                keys.add(key);
                for (String value : testData.ExtractedParameters.get(key)) {
                    status.add(testData.ExtractedParameters.get(key).get(1));
                    values.add(value);
                    break;
                }
            }

            for (int i = 0; i < keys.size(); i++) {
                if (status.get(i).equals("PASS")) {
                    extractedParameters += "<tr style='background: #60A84D;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                } else if (status.get(i).equals("FAIL")) {
                    extractedParameters += "<tr style='background: #FF4536;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                } else if (status.get(i).equals("WARNING")) {
                    extractedParameters += "<tr style='background: #FF8E1A;'><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                } else {
                    extractedParameters += "<tr><td>" + keys.get(i) + "</td><td>" + values.get(i) + "</td></tr>";
                }
            }

            extractedParameters += "</table>";

            currentTest.log(Status.INFO, extractedParameters);
        }
    }

    //Creating a new text file
    public static void createNewTextFile() {
        //Creates the file and initializes the header
        try {
            String directory = reportDirectory + "/Narrator_Log.txt";
            File file = new File(directory);
            file.createNewFile();
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(String.format(formatStr, "", "-- NARRATOR LOG FILE --", "", ""));
            writer.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    //Returns data from the spreadsheet
//    public String getData(String data) {
//        return testData.getData(data);
//    }
    //Checks the length of the message
    public String formatMessage(String message) {
        String newMessage = message.replace("\\.", "");
        newMessage = newMessage.replace(":", "");
        newMessage = newMessage.replace("\\", "");
        newMessage = newMessage.replace("/", "");
        newMessage = newMessage.replace("\\*", "");
        newMessage = newMessage.replace("\\?", "");
        newMessage = newMessage.replace("\"", "");
        newMessage = newMessage.replace("<", "");
        newMessage = newMessage.replace(">", "");
        newMessage = newMessage.replace("\\|", "");

        try {
            //checks if message is longer than 60 characters, if so then remove a word. LOOP
            while (newMessage.length() > 60) {
                newMessage = newMessage.split(" ", 2)[1];
            }
        } catch (Exception ex) {
            logError("Failed to reduce message length - " + ex.getMessage());
        }
        return newMessage;
    }

    public static void logError(String error) {
        writeToLogFile("- [EROR] " + error);
    }

    public static void logDebug(String debug) {
        writeToLogFile("- [DBUG] " + debug);

    }

    public static void logPass(String failure) {
        writeToLogFile("- [PASS] " + failure);

    }

    public static void logFailure(String failure) {
        writeToLogFile("- [FAIL] " + failure);

    }

    public static void logInfo(String info) {
        writeToLogFile("- [INFO] " + info);

    }

    private static void writeToLogFile(String logMessage) {
        String directory = reportDirectory + "/Narrator_Log.txt";
        File file = new File(directory);

        if (!file.exists()) {
            createNewTextFile();
        }

        //Writes info to the text file
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println(String.format(formatStr, dateFormat.format(new Date()), logMessage, "", ""));
            writer.close();
        } catch (IOException e) {
            System.out.printf(e.getMessage());
        }
    }

    public String convertPNGToBase64(String imageFilePath) {
        String base64ReturnString = "";

        try {
            out.println("[INFO] Converting error screenshot to Base64 format...");
            File image = new File(imageFilePath);

            FileInputStream imageInputStream = new FileInputStream(image);

            byte imageByteArray[] = new byte[(int) image.length()];

            imageInputStream.read(imageByteArray);

            base64ReturnString = Base64.encodeBase64String(imageByteArray);

            out.println("[INFO] Converting completed, image ready for embedding.");
        } catch (Exception ex) {
            out.println("[ERROR] Failed to convert image to Base64 format - " + ex.getMessage());
        }

        return base64ReturnString;
    }

    public void copyReportFileForJenkins() {
        try {
            //New reportDirectory
            File CompleteExtentReport = new File(reportDirectory);

            File extentReport = new File(extentReportDirectory);

            //File has to be zipped at this point

            File extentReportDependancies = new File(reportDirectory + "/extentreports");
            File htmlReportDirectory = new File("HTMLTestReport");

            if (!htmlReportDirectory.exists()) {
                htmlReportDirectory.mkdirs();
            }

            FileUtils.copyFileToDirectory(extentReport, htmlReportDirectory);

            if (extentReportDependancies.exists()) {
                FileUtils.copyDirectory(extentReportDependancies, htmlReportDirectory);
            }

            try {
                ZipDirectory.zip(new File(reportDirectory), new File("HTMLTestReport/KeywordDrivenTestReports.zip"));

            } catch (Exception e) {
                err.println("[ERROR] Failed to Zip KeywordDrivenTestReports file to HTML Test Report Directory - " + e.getMessage());
            }

        } catch (Exception ex) {
            err.println("[ERROR] Failed to copy extentReport file to HTML Test Report Directory - " + ex.getMessage());
        }
    }
}
