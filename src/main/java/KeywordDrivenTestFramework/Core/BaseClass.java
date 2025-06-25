package KeywordDrivenTestFramework.Core;

import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Reporting.ReportGenerator;
import KeywordDrivenTestFramework.Utilities.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class BaseClass {
    public static List<TestEntity> testDataList;
    public static Enums.BrowserType currentBrowser;
    public static ReportGenerator reportGenerator;

    public static SeleniumDriverUtility SeleniumDriverInstance;
    public static SikuliDriverUtility SikuliDriverInstance;
    public static AutoItDriverUtility AutoItInstance;
    public static String username;
    public static ApplicationConfig appConfig = new ApplicationConfig();
    private LocalDateTime startTime, endTime;
    private Duration testDuration;
    public static String testCaseId;
    public static String reportDirectory;
    public static String ReportDirectoryTimestamp;
    public static String currentTestDirectory;
    public static Enums.Environment currentEnvironment;

    public static boolean requiresBrowser = true; //For appium set this false in the @Test
    public static boolean requiresQAS = false;

    public static String inputFilePath;
    public static String screenshotPath;
    public static String relativeScreenShotPath;
    public static TestEntity testData;

    public static Narrator narrator;

    // Cucumber
    public static boolean isCucumberTesting = false;
    public static boolean isCucumberMobileTesting = false;

    public int pauseCount = 30;

    public static String getRelativeScreenshotPath() {
        return "./" + relativeScreenShotPath;
    }

    public static void setScreenshotPath(String screenshotPath) {
        BaseClass.screenshotPath = screenshotPath;
    }

    public BaseClass() {
        System.setProperty("java.awt.headless", "false");
    }

    public void setStartTime() {
        this.startTime = LocalDateTime.now();
    }

    public long getTotalExecutionTime() {
        this.endTime = LocalDateTime.now();
        testDuration = Duration.between(this.startTime, this.endTime);
        return testDuration.getSeconds();
    }

    public String resolveScenarioName() {
        String isolatedFileNameString = "";
        String[] splitFileName;
        String[] inputFileNameArray;
        String resolvedScenarioName = "";

        if (inputFilePath == null || inputFilePath == "") {
            return "NoTestPackRun";
        }

        // Get file name from inputFilePath (remove file extension)
        inputFileNameArray = inputFilePath.split("\\.");
        isolatedFileNameString = inputFileNameArray[0];
        if (isolatedFileNameString.contains("/")) {
            inputFileNameArray = isolatedFileNameString.split("/");
        } else if (isolatedFileNameString.contains("\\")) {
            inputFileNameArray = isolatedFileNameString.split("\\\\");
        }

        isolatedFileNameString = inputFileNameArray[inputFileNameArray.length - 1];

        splitFileName = isolatedFileNameString.split("(?=\\p{Upper})");
        for (String word : splitFileName) {
            resolvedScenarioName += word + " ";
        }

        return resolvedScenarioName.trim();
    }

    public String retrieveTestParameterUsingTestCaseId(String testCaseId, String parameterName) {
        String defaultReturn = "parameter not found";
        for (TestEntity testEntity : testDataList) {
            if (testEntity.TestCaseId.toUpperCase().equals(testCaseId.toUpperCase())) {
                if (testEntity.TestParameters.containsKey(parameterName)) {
                    return testEntity.TestParameters.get(parameterName);
                } else {
                    return defaultReturn;
                }
            }
        }
        return defaultReturn;
    }

    public void pause(int milisecondsToWait) {
        try {
            Thread.sleep(milisecondsToWait);
        } catch (Exception e) {

        }
    }

    public String generateDateTimeString() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");

        return dateFormat.format(dateNow);
    }

    public String getData(String parameterName) {
        String returnedValue = testData.TestParameters.get(parameterName);

        if (returnedValue == null) {
            narrator.logError(" Parameter ' " + parameterName + " ' not found");
            returnedValue = "";
        }

        return returnedValue;
    }
}
