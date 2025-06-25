package KeywordDrivenTestFramework.Testing;

import KeywordDrivenTestFramework.Core.BaseClass;
import static KeywordDrivenTestFramework.Core.BaseClass.SeleniumDriverInstance;
import static KeywordDrivenTestFramework.Core.BaseClass.inputFilePath;
import static KeywordDrivenTestFramework.Core.BaseClass.reportGenerator;
import static KeywordDrivenTestFramework.Core.BaseClass.testCaseId;
import static KeywordDrivenTestFramework.Core.BaseClass.testDataList;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestEntity;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Reporting.Narrator;
import KeywordDrivenTestFramework.Reporting.ReportGenerator;
import KeywordDrivenTestFramework.Utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import java.io.*;
import static java.lang.System.err;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public final class TestMarshall extends BaseClass{
    // Handles calling test methods based on test parameters , instantiates Selenium Driver object
    ExtentReports extentReports;
    ExcelReaderUtility excelInputReader;
    CSVReportUtility cSVReportUtility;
    //James removed(Moved to Narrator)
//    PrintStream errorOutputStream;
//    PrintStream infoOutputStream;
    private String dateTime;
    private int totalIgnore = 0;
    boolean lastTestStatus = true;
    String networkReportZip;

    // Implementation  for Appium only
    public TestMarshall(TestEntity testdata, boolean cucumber) {

        testData = testdata;
        // For screenshot
        testCaseId = testdata.TestCaseId;
        reportGenerator = new ReportGenerator();
        this.generateReportDirectory();
        currentBrowser = ApplicationConfig.SelectedBrowser();

    }

    public TestMarshall(String inputFilePathIn) {
        inputFilePath = inputFilePathIn;
        testDataList = new ArrayList<>();
        excelInputReader = new ExcelReaderUtility();
        cSVReportUtility = new CSVReportUtility(inputFilePath);
        cSVReportUtility.createCSVReportDirectoryAndFile();
        currentBrowser = ApplicationConfig.SelectedBrowser();
        reportGenerator = new ReportGenerator(inputFilePath, ApplicationConfig.ReportFileDirectory());
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        AutoItInstance = new AutoItDriverUtility();
        this.generateReportDirectory();

    }

    /**
     * Constructor for Selenium
     *
     * @param featureName
     * @param browserTypeOverride
     * @param cucumber
     */
    public TestMarshall(String featureName, Enums.BrowserType browserTypeOverride, boolean cucumber, boolean isCopyToNetwork) {
        inputFilePath = featureName;
        currentBrowser = browserTypeOverride;
        reportGenerator = new ReportGenerator();
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        this.generateReportDirectory();

    }

    public TestMarshall(String inputFilePathIn, Enums.BrowserType browserTypeOverride, boolean isCopyToNetwork) {
        inputFilePath = inputFilePathIn;
        testDataList = new ArrayList<>();
        excelInputReader = new ExcelReaderUtility();
        cSVReportUtility = new CSVReportUtility(inputFilePath);
        cSVReportUtility.createCSVReportDirectoryAndFile();
        currentBrowser = browserTypeOverride;
        reportGenerator = new ReportGenerator();
        //reportGenerator = new ReportGenerator(inputFilePath, ApplicationConfig.ReportFileDirectory());
        SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
        this.generateReportDirectory();

    }

    public void runKeywordDrivenTests() throws FileNotFoundException {
        narrator = new Narrator();
        int numberOfTest = 0;
        testDataList = loadTestData(inputFilePath);

        if (testDataList.size() < 1) {
            narrator.logError("Test data object is empty - spreadsheet not found or is empty");
        } else {
            try {
                // Each case represents a test keyword found in the excel spreadsheet
                for (TestEntity testData : testDataList) {
                    this.testData = testData;
                    numberOfTest++;
                    testCaseId = testData.TestCaseId;
                    username = testData.getData("Username");

                    // Skip test methods and test case id's starting with ';'
                    if (!testData.TestMethod.startsWith(";") && !testData.TestCaseId.startsWith(";")) {
                        narrator.logDebug("Executing test - " + testData.TestMethod + " | " + numberOfTest + " of " + testDataList.size());

                        try {
                            ClassPath.ClassInfo testClassInfo = getKeywordTestClass(testData.TestMethod);
                            Class testClass = testClassInfo.load();
                            Constructor constructor = testClass.getConstructor();
                            Object testClassInstance = constructor.newInstance();
                            narrator.startTest();

                            if (isBlocked(testClassInstance)) {
                                narrator.BlockedTest();
                                continue;
                            }

                            if (testClassInstance.getClass().getAnnotation(KeywordAnnotation.class).createNewBrowserInstance()) {
                                ensureNewBrowserInstance();
                            }

                            Method executeTestMethod = testClassInstance.getClass().getMethod("executeTest");
                            TestResult result = (TestResult) executeTestMethod.invoke(testClassInstance);
                            lastTestStatus = (result.testStatus == Enums.ResultStatus.PASS) ? true : false;
                            reportGenerator.testResults.add(result);

                        } catch (Exception ex) {
                            err.println("[ERROR] Critical Error during keyword execution - " + testData.TestMethod + " - error: " + ex.getMessage());
                            System.out.println(ex.getMessage());
                        }

                        narrator.logDebug("Continuing to next test method");
                    } else {
                        //Test was disabled in the test pack
                        totalIgnore++;
                        narrator.skipTest_TestPack_Disabled();
                    }
                }
            } catch (Exception e) {

            } finally {
                if (SeleniumDriverInstance != null && SeleniumDriverInstance.isDriverRunning()) {
                    SeleniumDriverInstance.shutDown();
                }

                narrator.flushReports();
                reportGenerator.generateTestReport();
            }
        }
    }

    public boolean isBlocked(Object testClassInstance) {
        if (testClassInstance.getClass().getAnnotation(KeywordAnnotation.class).blockable() && !lastTestStatus) {
            return true;
        }

        return false;
    }

    public ClassPath.ClassInfo getKeywordTestClass(String keywordName) {
        try {

            //Get list of all loaded classes for the package - defined at runtime - we need to be able to isolate just the TestClasses
            // in order to extract the one matching the keyword to be executed
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());

            //Next we set up Predicates (a type of query in java) to isolate the list of classes to only those pertaining to the framework
            // i.e. no dependencies included
            ImmutableSet<ClassPath.ClassInfo> allClasses = classPath.getTopLevelClassesRecursive("KeywordDrivenTestFramework.Testing");

            //We then filter the classes to only those who have the required annotations - annotations used to add meta
            //data to the TestClasses sothat we can scan them to read their Keywords - this uses Lambda notation only available in Java 8 and above.
            Predicate<ClassPath.ClassInfo> hasAnnotationPredicate = c -> c.load().isAnnotationPresent(KeywordAnnotation.class);
            Stream<ClassPath.ClassInfo> annotatedClasses = allClasses.stream().filter(hasAnnotationPredicate);

            //The filtered list is then queried a second time in order to retrieve the valid TestClass based on the keywordName
            Predicate<ClassPath.ClassInfo> checkKeywordPredicate = c -> c.load().getAnnotation(KeywordAnnotation.class).Keyword().equals(keywordName);
            ClassPath.ClassInfo testClass = annotatedClasses.filter(checkKeywordPredicate).findFirst().get();

            if (testClass == null) {
                err.println("[ERROR] Failed to resolve TestClass for keyword - " + keywordName + " - error: Keyword not found");
            }

            return testClass;

        } catch (Exception ex) {
            err.println("[ERROR] Failed to resolve TestClass for keyword - " + keywordName + " - error: " + ex.getMessage());

            return null;
        }
    }

    private List<TestEntity> loadTestData(String inputFilePath) {
        return excelInputReader.getTestDataFromExcelFile(inputFilePath);
    }

    public static String getFeaturename(String scenarioId) {
        return scenarioId.split(";")[0].toUpperCase();
    }

    public static void CheckBrowserExists() {
        if (SeleniumDriverInstance == null) {
            SeleniumDriverInstance = new SeleniumDriverUtility(currentBrowser);
            SeleniumDriverInstance.startDriver();
        }

        if (!SeleniumDriverInstance.isDriverRunning()) {
            SeleniumDriverInstance.startDriver();
        }
    }

    public static void ensureNewBrowserInstance() {
        if (SeleniumDriverInstance.isDriverRunning()) {
            SeleniumDriverInstance.shutDown();
        }
        SeleniumDriverInstance.startDriver();
    }

    public void generateReportDirectory() {
        reportDirectory = ApplicationConfig.ReportFileDirectory() + resolveScenarioName() + "_" + this.generateDateTimeString();
        String[] reportsFolderPathSplit = TestMarshall.reportDirectory.split("//");
        TestMarshall.currentTestDirectory = ApplicationConfig.ReportFileDirectory() + "/" + reportsFolderPathSplit[reportsFolderPathSplit.length - 1];
        this.networkReportZip = reportsFolderPathSplit[reportsFolderPathSplit.length - 1];
    }
}
