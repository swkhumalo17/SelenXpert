package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Entities.Enums.BrowserType;
import KeywordDrivenTestFramework.Reporting.Narrator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */

public final class ApplicationConfig {
    public Properties appConfig;
    private String appConfigFilePath = "config.properties";

    private static String ReportFileDirectory, browserTypeConfig, mailingList;
    private static String emailSender, emailHost, projectName, runName;
    private static int WaitTimeout;
    public static BrowserType browserType;

    public static int WaitTimeout() {
        return WaitTimeout;
    }


    public static String ReportFileDirectory() {
        return ReportFileDirectory;
    }

    public static String NetworkReportDirectory() {
        return "share-drive directory";
    }


    public static BrowserType SelectedBrowser() {
        return browserType;
    }

    public static String EmailHost() {
        return emailHost;
    }

    public static String[] MailingList() {
        return mailingList.split(";");
    }

    public static String EmailSender() {
        return emailSender;
    }

    public static String projectName() {
        return projectName;
    }

    public static String runName() {
        return runName;
    }


    public ApplicationConfig() {

        try {
            loadConfigurationSettings();
        } catch (Exception e) {
            // One or more of the appConfig values could not be found in the config file -
            // Reload default values and read from file.
            generateDefaultConfigurationFile();
            loadExistingConfigurationFile();
            loadConfigurationSettings();
        }

    }

    private void loadConfigurationSettings() {
        if (!loadExistingConfigurationFile()) {
            generateDefaultConfigurationFile();
        }
        try {
            ReportFileDirectory = appConfig.getProperty("ReportFileDirectory");

            mailingList = appConfig.getProperty("MailingList");
            emailSender = appConfig.getProperty("EmailSender");
            emailHost = appConfig.getProperty("EmailHost");

            WaitTimeout = Integer.parseInt(appConfig.getProperty("WaitTimeout"));

            projectName = appConfig.getProperty("ProjectName");
            runName = appConfig.getProperty("RunName");
            browserType = resolveBrowserType();
        } catch (Exception e) {
            Narrator.logError(" Loading application configuration...see stack trace:");
            System.out.println(e.getMessage());
        }

    }


    public BrowserType resolveBrowserType() {
        browserTypeConfig = appConfig.getProperty("BrowserType");

        switch (browserTypeConfig) {
            case "Chrome":
                return BrowserType.Chrome;
            case "IE":
                return BrowserType.IE;
            case "FireFox":
                return BrowserType.FireFox;
            case "Safari":
                return BrowserType.Safari;
            default:
                return BrowserType.Chrome;
        }
    }

    public BrowserType resolveBrowserType(String browserType) {
        browserTypeConfig = browserType;

        switch (browserTypeConfig) {
            case "IE":
                return BrowserType.IE;
            case "FireFox":
                return BrowserType.FireFox;
            case "Chrome":
                return BrowserType.Chrome;
            case "Safari":
                return BrowserType.Safari;
            default:
                return BrowserType.IE;
        }
    }


    private void generateDefaultConfigurationFile() {
        try {
            appConfig = new Properties();
            appConfig.setProperty("ExcelInputFile", "Keyword Input.xls");
            appConfig.setProperty("ReportFileDirectory", "KeywordDrivenTestReports/");
            appConfig.setProperty("BrowserType", "Chrome");
            appConfig.setProperty("WaitTimeout", "15");
            appConfig.setProperty("MailingList", "skhumalo@oldmutual.com");
            appConfig.setProperty("EmailSender", "skhumalo@oldmutual.com");
            appConfig.setProperty("EmailHost", "localhost");

            appConfig.store(new FileOutputStream(appConfigFilePath), null);

        } catch (Exception e) {
            Narrator.logError(" Loading default configuration...see stack trace:");
            System.out.println(e.getMessage());
        }
    }

    private boolean loadExistingConfigurationFile() {
        try {
            if (appConfig == null) {
                appConfig = new Properties();
            }
            appConfig.load(new FileInputStream(appConfigFilePath));
            return true;

        } catch (Exception e) {
            Narrator.logError("Configuration file not found, reverting to default configuration...see stack trace:");
            System.out.println(e.getMessage());
            Narrator.logError("Loading default configuration");
            return false;
        }
    }
}
