package KeywordDrivenTestFramework.Entities;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class Enums {
    public enum BrowserType {

        none, IE, FireFox, Chrome, Safari, mobileSafari, mobileChrome, PhantomJs, EDGE;
    }

    public enum ResultStatus {

        PASS, FAIL, WARNING, UNCERTAIN
    }

    public enum RelativePosition {

        Above, Below, Right, Left
    }

    public enum TestLogResult {

        PASSED, FAILED, INCOMPLETE, TODO
    }

    public enum ExecutionMethod {

        AUTOMATE, MANUAL
    }

    public enum MobilePlatform {
        Android, iOS
    }

    public enum Device {
        Emulator("udid", "emulator-5554", "http://127.0.0.1:4722/wd/hub", MobilePlatform.Android),
        geny_motion_Tablet("udid", "192.168.188.101:5555", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        Huawei("udid", "FUH7N16928004797", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        iPhone_4_SE("udid", "d419f3fbaeedaa7b3f0959f2bae016711a3e39a9", "http://localhost:8889", MobilePlatform.iOS),
        iPhone_4_1("udid", "FC701A3EE383A8A98CDFC3FEB17AB3D55ADB6251", "http://10.0.1.97:4724/wd/hub", MobilePlatform.iOS),
        iPhone_4_2("udid", "069750BDDE450E9E1A8483347C2FD1B8CAABAC0C", "http://10.0.1.97:4726/wd/hub", MobilePlatform.iOS),
        iPhone_6("udid", "871bcf2677f6c0460be47b70353cc91c1b476510", "http://localhost:8889", MobilePlatform.iOS),
        iPhone_7("udid", "ad38fd9c4354dc6b917e106175da10e7bbc936f0", "http://localhost:8889", MobilePlatform.iOS),
        Nexus_7("udid", "0921880b", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        Nexus_5("udid", "074514c9d007b8f8", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        PowerTablet("udid", "0123456789ABCDEF", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        Samsung_Tablet("udid", "52004947bca12100", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        Samsung_Tablet_2("udid", "4572a1631dd5857e", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        Samsung_Note_2_Tablet("udid", "c3206a701e5aa11", "http://127.0.0.1:4495/wd/hub", MobilePlatform.Android),
        Samsung_Note_2("udid", "4df090ae6af88fc1", "http://127.0.0.1:4491/wd/hub", MobilePlatform.Android),
        SamSung_S3("udid", "4df0b16c57519fa5", "http://127.0.0.1:4492/wd/hub", MobilePlatform.Android),
        SamSung_S3_JP("udid", "4df0ec687dfeafd5", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
        SamSung_S3_J6("udid", "4200450c4fc43400", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        SamSung_S4("udid", "4d00cb02c5ce4001", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
        SamSung_neo("udid", "1101fa465595724a", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
        SamSung_S6("udid", "0816081d9fc60505", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
        SamSung_S7_edge("udid", "ad08160338b35f4a41", "http://127.0.0.1:4493/wd/hub", MobilePlatform.Android),
        SamSung_S4_Rio("udid", "4d00168240c03049", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        SamSung_Custom_Phone("udid", "emulator-5554", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        Techno_C9("udid", "01H535X671102995", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraMC40("udid", "15269522500185", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraMC40_DVT_HUB("udid", "15269522500185", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraTC70_DVT_HUB("udid", "16072522500917", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraTC70_DVT("udid", "16072522500917", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraTC70_Doddle_HUB("udid", "16235522503431", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraTC70_Doddle("udid", "16235522503431", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android),
        ZebraTC70_Wireless("udid", "10.20.10.73:5554", "http://127.0.0.1:4723/wd/hub", MobilePlatform.Android);

        public final String CapabilityName;
        public final String DeviceID;
        public final String ServerURL;
        public final MobilePlatform platform;

        // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will
        // define the order in which the enum types' properties are specified.
        Device(String CapabilityName, String DeviceID, String ServerURL, MobilePlatform _platform) {
            this.CapabilityName = CapabilityName;
            this.DeviceID = DeviceID;
            this.ServerURL = ServerURL;
            this.platform = _platform;
        }

    }

    public enum DeviceConfig {

        //Capability information is stored here.
        //AppName and AppFilePath are for both APK and IPA.
        //Uses the format Name(deviceName, platformName, automationName, Version, appPackage, appActivity, appName, appFilePath)
        Santam("5554", "Android", "Appium", "5.1", "com.experitest.ExperiBank", ".LoginActivity", "Santam_1.3.1.apk", System.getProperty("user.dir") + "\\Applications"),
        Santam_iOS("871bcf2677f6c0460be47b70353cc91c1b476510", "iOS", "XCUITest", "10.2.1", "za.co.santam.mobile.sos", "za.co.santam.mobile.sos.LoginActivity", "SantamSOS_2.0.0.ipa", System.getProperty("user.dir") + "/Applications"),
        TestRoboNedbank("4200450c4fc43400", "Android", "Appium", "5.1", "", "", "", System.getProperty("user.dir") + "\\Applications"),
        Test("5554", "Android", "Appium", "8.0", "", "", "", ""),
        zebraMC40("15269522500185", "Android", "Appium", "4.4.", "com.doddle.concession", "com.doddle.concession.login.splash.SplashActivity", "12.13.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        _AUS("15269522500185", "Android", "Appium", "4.4.", "com.doddle.concession", "com.doddle.concession.login.splash.SplashActivity", "11.11.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraTC70_DVT("16072522500917", "Android", "Appium", "4.4.", "com.doddle.concession", "com.doddle.concession.login.splash.SplashActivity", "12.11.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraTC70_DVT_HUB("16072522500917", "Android", "Appium", "5.1.1", "com.doddle.hubapp", "com.doddle.hubapp.workflow.login.SplashActivity", "3.9.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraMC40_DVT_HUB("15269522500185", "Android", "Appium", "4.4", "com.doddle.hubapp", "com.doddle.hubapp.workflow.login.SplashActivity", "3.9.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraTC70_HUB("16235522503431", "Android", "Appium", "5.1.1", "com.doddle.hubapp", "com.doddle.hubapp.workflow.login.SplashActivity", "3.9.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraTC70("16235522503431", "Android", "Appium", "5.1.1", "com.doddle.concession", "com.doddle.concession.login.splash.SplashActivity", "12.13.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications"),
        zebraTC70_Wireless("10.20.10.73:5554", "Android", "Appium", "5.1.1", "com.doddle.concession", "com.doddle.concession.login.splash.SplashActivity", "12.13.2-Stage-EU.apk", System.getProperty("user.dir") + "\\Applications");

        public final String deviceName;
        public final String platformName;
        public final String automationName;
        public final String Version;
        public final String appPackage;
        public final String appActivity;
        public final String ApplicationName;
        public final String ApplicationFilePath;

        // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will
        // define the order in which the enum types' properties are specified.
        DeviceConfig(String deviceName, String platformName, String automationName, String Version, String appPackage, String appActivity, String ApplicationName, String ApplicationFilePath) {
            this.deviceName = deviceName;
            this.platformName = platformName;
            this.automationName = automationName;
            this.Version = Version;
            this.appPackage = appPackage;
            this.appActivity = appActivity;
            this.ApplicationName = ApplicationName;
            this.ApplicationFilePath = ApplicationFilePath;
        }
    }

    public enum TestRailConfig {
        ThirdbridgeTestRail("http://localhost/", "nkelechi@dvt.co.za", "123QWEasd");
        public final String username;
        public final String baseUrl;
        public final String password;

        TestRailConfig(String baseUrl, String username, String password) {
            this.baseUrl = baseUrl;
            this.username = username;
            this.password = password;
        }
    }

    public enum DesktopApplication {
        ZKAccess("C:\\ZKTeco\\ZKAccess3.5\\Access.exe");

        public final String filePath;

        DesktopApplication(String FilePath) {
            this.filePath = FilePath;
        }
    }

    public enum Database {
        // Set Database Connection Information Here.
        DestinationData("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby://localhost:1527/DestinationDB", "app", "app"),
        SourceData("org.apache.derby.jdbc.EmbeddedDriver", "jdbc:derby://localhost:1527/SourceDB", "app", "app");

        public final String Driver;
        public final String ConnectionString;
        public final String username;
        public final String password;

        Database(String Driver, String ConnectionString, String username, String password) {
            this.Driver = Driver;
            this.ConnectionString = ConnectionString;
            this.username = username;
            this.password = password;
        }

    }

    public enum Environment {
        // Add environment urls here, parameter order is defined by the constructor (Environment) definition below
        // Please note that adding an addtional environment type will require you to comma-seperate them.
        // Visit http://docs.oracle.com/javase/tutorial/java/javaOO/enum.html to learn more about Java Enum declarations.

        // Here we are declaring the Dev Environment type, and passing the following two properties, a url and a connection string,
        // which are defined below as both being string literals:
         //[FirstPageURL,FirstDatabaseConnectionString]
        QA("https://secure.adviser.qa.oldmutual.co.za/iicSeaUmsWeb/index.html#/login");
        //Prod("https://secure.adviser.oldmutual.co.za/iicSeaUmsWeb/index.html#/login");
        // For each system (website1, database1, website2 etc.) within the defined environment (Dev, QA, Prod etc.)
        // you will have to declare the appropriate string to store its properties (URL or connection string etc.).
        //public final String RoboAdvisorAzureURL;
        public final String pageUrl;

        //        public final String ForgotPasswordURL;
        // This constructor defines and instantiates the parameters declared above. Parameter order is specified here and will
        // define the order in which the enum types' properties are specified.
        Environment(String pageUrl) {
            this.pageUrl = pageUrl;
        }

    }

    public static Device resolveDevice(String device) {
        switch (device.toUpperCase()) {
            case "EMULATOR":
                return Device.Emulator;
            case "SAMSUNG_TABLET":
                return Device.Samsung_Tablet;
            case "SAMSUNG_CUSTOM_PHONE":
                return Device.SamSung_Custom_Phone;
            case "NEXUS_5":
                return Device.Nexus_5;
            case "IPHONE_7":
                return Device.iPhone_7;
            case "ZEBRATC70_DVT":
                return Device.ZebraTC70_DVT;
            case "ZEBRATC70_DODDLE":
                return Device.ZebraTC70_DVT;
            case "ZEBRAMC40":
                return Device.ZebraMC40;
            case "ZEBRATC70_DVT_HUB":
                return Device.ZebraTC70_DVT_HUB;

            case "ZEBRATC70_DODDLE_HUB":
                return Device.ZebraTC70_Doddle_HUB;
            case "ZEBRAMC40_DVT_HUB":
                return Device.ZebraMC40_DVT_HUB;
            default:
                return null;
        }
    }

    public static DeviceConfig resolveDeviceConfig(String deviceConfig) {
        switch (deviceConfig.toUpperCase()) {
            case "SANTAM":
                return DeviceConfig.Santam;
            case "TEST":
                return DeviceConfig.Test;
            case "ZEBRATC70":
                return DeviceConfig.zebraTC70;
            case "ZEBRATC70_DVT":
                return DeviceConfig.zebraTC70_DVT;
            case "ZEBRAMC40":
                return DeviceConfig.zebraMC40;
            case "ZEBRATC70_DVT_HUB":
                return DeviceConfig.zebraTC70_DVT_HUB;
            case "ZEBRATC70_DODDLE_HUB":
                return DeviceConfig.zebraTC70_HUB;
            case "ZEBRAMC40_DVT_HUB":
                return DeviceConfig.zebraMC40_DVT_HUB;
            default:
                return null;
        }
    }

    public static MobilePlatform resolveDMobilePlatform(String mobilePlatform) {
        switch (mobilePlatform.toUpperCase()) {
            case "ANDROID":
                return MobilePlatform.Android;
            case "IOS":
                return MobilePlatform.iOS;
            default:
                return null;
        }
    }

    //    public static DeviceConfig resolveDeviceConfig(String deviceConfig)
//    {
//        switch (deviceConfig.toUpperCase()) {
//            case "HOLLARDANDROID":
//                return DeviceConfig.HollardAndroid;
//
//            default:
//                return null;
//        }
//    }
    public static Environment resolveTestEnvironment(String environment) {
        switch (environment.toUpperCase()) {
            case "Dev":
                //return Environment.Dev;
            case "QA":
                return Environment.QA;
            case "Prod":
                //return Environment.Prod;
            default:
                return null;
        }
    }

    public static BrowserType resolveBrowserType(String browserType) {

        switch (browserType) {
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
}
