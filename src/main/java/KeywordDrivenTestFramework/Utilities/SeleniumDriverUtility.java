package KeywordDrivenTestFramework.Utilities;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.DataColumn;
import KeywordDrivenTestFramework.Entities.DataRow;
import KeywordDrivenTestFramework.Entities.Enums;
import KeywordDrivenTestFramework.Entities.RetrievedTestValues;
import KeywordDrivenTestFramework.Reporting.Narrator;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * @author SKHUMALO on 2022/05/25.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class SeleniumDriverUtility extends BaseClass {
    public WebDriver Driver;
    private Enums.BrowserType browserType;
    File fileIEDriver;
    File fileChromeDriver;
    File filePhantomJsDriver;
    File fileEdgeDriver;
    File firefoxDriver;
    public Boolean _isDriverRunning = false;
    public RetrievedTestValues retrievedTestValues;
    public String DriverExceptionDetail = "";
    //    TestEntity testData;
    public Object document;
    public String mainWindowsHandle;
    public String content;
    public String startDate, endDate, expectedYear;

    EventFiringWebDriver eventDriver;

    public SeleniumDriverUtility(Enums.BrowserType selectedBrowser) {
        retrievedTestValues = new RetrievedTestValues();

        _isDriverRunning = false;
        browserType = selectedBrowser;

        String OperatingSystem = System.getProperty("os.name").toLowerCase();

        if (OperatingSystem.contains("mac")) {
            fileChromeDriver = new File("chromedriver");
            System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());

            firefoxDriver = new File("geckodriver");
            System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());

            fileEdgeDriver = new File("MicrosoftWebDriver");
            System.setProperty("webdriver.edge.driver", fileEdgeDriver.getAbsolutePath());
        } else {
            fileIEDriver = new File("IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", fileIEDriver.getAbsolutePath());

            fileChromeDriver = new File("chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", fileChromeDriver.getAbsolutePath());

            fileEdgeDriver = new File("MicrosoftWebDriver.exe");
            System.setProperty("webdriver.edge.driver", fileEdgeDriver.getAbsolutePath());

            filePhantomJsDriver = new File("phantomjs.exe");
            System.setProperty("phantomjs.binary.path", filePhantomJsDriver.getAbsolutePath());

            firefoxDriver = new File("geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", firefoxDriver.getAbsolutePath());
        }

    }

    public boolean isDriverRunning() {
        return _isDriverRunning;
    }

    public void startDriver() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");

        switch (browserType) {
            case EDGE:
                Driver = new EdgeDriver();
                _isDriverRunning = true;
                break;
            case IE:
                Driver = new InternetExplorerDriver();
                _isDriverRunning = true;
                break;
            case FireFox:

                try {
                    String profileName = "/Users/SKHUMALO/Library/Application Support/Firefox/Profiles/wtupm1eb.Automation";

                    // Create FirefoxProfile with the custom profile name
                    FirefoxProfile profile = new FirefoxProfile(new File(profileName));
                    FirefoxOptions options = new FirefoxOptions().setProfile(profile);

                    // Initialize WebDriver with the custom profile
                    Driver = new FirefoxDriver(options);
                    _isDriverRunning = true;
                    Driver.manage().window().maximize();
                }catch(Exception e){
                    Narrator.logDebug(e.getMessage());
                    break;
                }

                break;
            case Chrome:
                Driver = new ChromeDriver();
                _isDriverRunning = true;
                Driver.manage().window().maximize();
                break;

            case Safari:
                ;
                break;
        }

        if (browserType != Enums.BrowserType.mobileChrome && browserType != Enums.BrowserType.FireFox) {
            retrievedTestValues = new RetrievedTestValues();
            Driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            Driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
            Driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
            Driver.manage().window().maximize();
        }

    }

    public boolean navigateTo(String pageUrl) {
        try {
            Driver.get(pageUrl);
            // Driver.navigate().to(pageUrl);
            return true;
        } catch (Exception e) {
            Narrator.logError(" navigating to URL  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean hoverOverElementByXpath(String elementXpath) {
        try {
            if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']", 3)) {
                while (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']")) {

                }
            }

            waitForElementByXpath(elementXpath);

            pause(50);

            Robot mouse = new Robot();

            WebElement hoverOverElement = Driver.findElement(By.xpath(elementXpath));
            Locatable elementLocation = (Locatable) hoverOverElement;
            Point locationPoint = elementLocation.getCoordinates().inViewPort();

            int webElementX = locationPoint.x;
            int webElementY = locationPoint.y + 118;

            mouse.mouseMove(webElementX, webElementY);
            pause(50);

//            int windowHieght = dim.getHieght();
//            Locatable hoverItem = (Locatable)  hoverOverElement;
//            Mouse mouse = ((HasInputDevices)Driver).getMouse();
//
//            mouse.mouseMove(hoverItem.getCoordinates());
//            Actions action = new Actions(Driver);
//            action.moveToElement(hoverOverElement).moveToElement(Driver.findElement(By.xpath(elementXpath))).build().perform();
//            Narrator.logDebug("Hovered over element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Hovered over element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public static boolean isNumeric(String string) {
        try {
            Double.parseDouble(string);
            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean hover(String elementXpath, int n) {
        try {
            waitForElementByXpath(elementXpath);
            WebElement hoverOverElement = Driver.findElement(By.xpath(elementXpath));

            int xaxis = hoverOverElement.getLocation().x;

            int yaxis = hoverOverElement.getLocation().y;

            int width = hoverOverElement.getSize().width;
            int height = hoverOverElement.getSize().height;

            Robot r = new Robot();
            r.mouseMove(xaxis + width / 3, yaxis + height / 2 + n);

            Narrator.logDebug("Hovered over element by co-ordinates : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to hover over element by co-ordinates - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean hoverOverElementByXpathBrowser(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebElement hoverOverElement = Driver.findElement(By.xpath(elementXpath));
            Locatable hoverItem = (Locatable) hoverOverElement;
            Mouse mouse = ((HasInputDevices) Driver).getMouse();

            mouse.mouseMove(hoverItem.getCoordinates());
            Actions action = new Actions(Driver);
            action.moveToElement(hoverOverElement).moveToElement(Driver.findElement(By.xpath(elementXpath))).build().perform();
            Narrator.logDebug("Hovered over element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to hover over element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean hoverOverElementByXpathNoCoOrdinates(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebElement hoverOverElement = Driver.findElement(By.xpath(elementXpath));
            Locatable hoverItem = (Locatable) hoverOverElement;
            Mouse mouse = ((HasInputDevices) Driver).getMouse();

            //mouse.mouseMove(hoverItem.getCoordinates());
            Actions action = new Actions(Driver);
            action.moveToElement(hoverOverElement).moveToElement(Driver.findElement(By.xpath(elementXpath))).build().perform();
            Narrator.logDebug("Hovered over element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to hover over element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean refreshPageBrowser() {
        try {
            Driver.navigate().refresh();
            Narrator.logDebug("Successfully refresh the browser page.");
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to refresh the browser page.");
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean hoverAndWaitForElementByXpath(String waitXpath, String posXpath) {
        try {
            waitForElementPresentByXpath(posXpath);
            WebElement hoverOverElement = Driver.findElement(By.xpath(waitXpath));
            Locatable hoverItem = (Locatable) hoverOverElement;
            Mouse mouse = ((HasInputDevices) Driver).getMouse();
            mouse.mouseMove(hoverItem.getCoordinates());
            Actions action = new Actions(Driver);
            action.moveToElement(hoverOverElement).moveToElement(Driver.findElement(By.xpath(posXpath))).build().perform();
            waitForElementNoLongerPresentByXpath(posXpath);
            waitForElementPresentByXpath(waitXpath);
            action.moveToElement(hoverOverElement).moveToElement(Driver.findElement(By.xpath(waitXpath))).build().perform();
            Narrator.logDebug("Hovered over element by Xpath : " + waitXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Hovered over element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean hoverOverElementByXpathAndClick(String hoverXpath, String clickXpath) {
        try {
            waitForElementByXpath(hoverXpath);
            WebElement hoverOverElement = Driver.findElement(By.xpath(hoverXpath));
            Actions action = new Actions(Driver);
            action.moveToElement(hoverOverElement).build().perform();
            Driver.findElement(By.xpath(clickXpath)).click();
            Narrator.logDebug("Hovered over element by Xpath : " + hoverXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Hovered over element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean SelectULformUIListByXpath(String elementXpath) {
        try {
            // To identify Create Account button.
            WebElement elementLinkCheck = Driver.findElement(By.xpath(elementXpath));
            java.util.List<WebElement> listElementLinkCheck = Driver.findElements(By.xpath(elementXpath));

            for (WebElement element : listElementLinkCheck) {
                System.out.println("Element inside ul -->" + element.getText());
                System.out.println("Attrbite for that element-->" + element.getTagName());

                if ("Create an account".equalsIgnoreCase(element.getText())) {

                    System.out.println("Create account is identified.-->" + element.getText());
                    element.click();
                }
            }
            return true;
        } catch (Exception e) {
            Narrator.logError(" Retrieved value of attribute element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean selectTheDateOfBirthByXpath(String dateOfBirth) {
        try {
            // Select the year of birth
            WebElement yearDropdown = Driver.findElement(By.className("ui-datepicker-year"));
            Select yearSelect = new Select(yearDropdown);
            yearSelect.selectByValue("1995");

            // Select the month of birth
            WebElement monthDropdown = Driver.findElement(By.className("ui-datepicker-month"));
            Select monthSelect = new Select(monthDropdown);
            monthSelect.selectByVisibleText("May");

            // Select the day of birth
            WebElement dayElement = Driver.findElement(By.xpath("//td[@data-handler='selectDay' and text()='15']"));
            dayElement.click();
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean clickElementbyXpath(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    //When element is hidden/overlapped by some other element, use JavascriptExecutor to click element
    public boolean clickTheElementInTheShadowDOM(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            JavascriptExecutor executor = (JavascriptExecutor) Driver;
            executor.executeScript("arguments[0].click();", elementToClick);
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    //When element is hidden/overlapped by some other element, use JavascriptExecutor to enter element
    public boolean enterTextByXpathInTheShadowDOM(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
            //elementToTypeIn.clear();

            JavascriptExecutor executor = (JavascriptExecutor) Driver;
            executor.executeScript("arguments[0].value='" + textToEnter + "'", elementToTypeIn);
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);

            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();

            return false;
        }
    }

    //When element is hidden/overlapped by some other element, use JavascriptExecutor to enter element
    public String retrieveTextByXpathInTheShadowDOM(String elementXpath) {
        String retrievedText = "";
        try {
            this.waitForElementPresentByXpath(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));

            JavascriptExecutor executor = (JavascriptExecutor) Driver;
            retrievedText = (String) executor.executeScript("return arguments[0].getText()", elementToRead);

            WebElement shadowRoot = (WebElement) ((JavascriptExecutor) Driver).executeScript("return arguments[0].shadowRoot", elementToRead);

            retrievedText = shadowRoot.findElement(By.id("child-element")).getText();

            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from element - " + elementXpath);

            return retrievedText;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public String retrieveTextByCssSelectorInTheShadowDOM(String cssSelector) {
        String retrievedText = "";
        try {
            this.waitForElementPresentByXpath(cssSelector);
            WebElement elementToRead = Driver.findElement(By.cssSelector(cssSelector));

            JavascriptExecutor executor = (JavascriptExecutor) Driver;
            retrievedText = (String) executor.executeScript("return arguments[0].getText()", elementToRead);

            WebElement shadowRoot = (WebElement) ((JavascriptExecutor) Driver).executeScript("return arguments[0].shadowRoot", elementToRead);

            retrievedText = shadowRoot.findElement(By.id("child-element")).getText();

            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from element - " + cssSelector);

            return retrievedText;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }


    public boolean isElementClickableByXpath(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public String retrieveTextByWebElement(WebElement webElement) {
        try {
            String retrievedText = "";
            retrievedText = webElement.getText();
            Narrator.logDebug("Retrieved text by WebElement : " + webElement);
            return retrievedText;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text by WebElement - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return "";
        }
    }

    public String retrieveTextByXpathFromTextbox(String xpath) {
        try {
            String retrievedText = "";
            retrievedText = Driver.findElement(By.xpath(xpath)).getAttribute("value");
            Narrator.logDebug("Retrieved text by xpath : " + xpath);
            return retrievedText;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text by WebElement - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return "";
        }
    }

    public boolean clickElementbyXpathWithoutWaitingForClickable(String elementXpath) {
        try {
//            SeleniumDriverInstance.pause(2000);
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean roboClickLatestElementbyXpath(String elementXpath) {
        try {
            java.util.List<WebElement> elementList = SeleniumDriverInstance.Driver.findElements(By.xpath(elementXpath));
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(elementList.get(elementList.size() - 1)));
            WebElement elementToClick = ((elementList.get(elementList.size() - 1)));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean roboWaitForLatestElementbyXpath(String elementXpath) {
        try {
            java.util.List<WebElement> elementList = SeleniumDriverInstance.Driver.findElements(By.xpath(elementXpath));
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(elementList.get(elementList.size() - 1)));
            //WebElement elementToClick = ((elementList.get(elementList.size()-1)));
            //elementToClick.click();
            Narrator.logDebug("Waited for element by Xpath : " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean clickElementbyXpathUsingActions(String elementXpath) {
        try {
            waitForElementByXpath(elementXpath);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
            WebElement elementToClick = Driver.findElement(By.xpath(elementXpath));
            Actions action = new Actions(Driver);
            action.moveToElement(elementToClick);
            action.click(elementToClick);
            action.perform();
            Narrator.logDebug(" Clicked element by Xpath : " + elementXpath);

            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean doubleClickElementbyXpath(String elementLinkText) {
        try {
            //waitForElementByLinkText(elementLinkText);
            Actions act = new Actions(Driver);
            WebElement elementToClick = Driver.findElement(By.xpath(elementLinkText));

            act.doubleClick(elementToClick).perform();
            Narrator.logDebug(" Double-clicked element by Xpath : " + elementLinkText);
            return true;
        } catch (Exception e) {
            Narrator.logError(" failed to double click element by link text  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean openBrowser(String newURL) {
        if (Driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) Driver).executeScript("window.open('" + newURL + "','_blank');");
            return true;
        }
        return false;
    }

    public boolean openInNewTab(String newURL) {
        try {
            ((JavascriptExecutor) Driver).executeScript("window.open();");
            ArrayList<String> tabs = new ArrayList<String>(Driver.getWindowHandles());
            Driver.switchTo().window(tabs.get(1));
            Driver.get(newURL);
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to open up a new tab  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean waitForElementToChangeByXPath(String elementXpath, String changeTo) {
        try {
            waitForElementByXpath(elementXpath);
            while (!retrieveTextByXpath(elementXpath).equals(changeTo)) {
                pause(500);
            }
            if (!retrieveTextByXpath(elementXpath).equals(changeTo)) {
                return false;
            }
        } catch (Exception e) {
            Narrator.logError("Failed to wait for element to change  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean waitForElementToChangeByXPath(String elementXpath, String changeTo, int count) {
        try {
            waitForElementByXpath(elementXpath);
            int i = 0;
            while (!retrieveTextByXpath(elementXpath).equals(changeTo) && i <= count) {
                pause(1000);
                i++;
            }
            if (!retrieveTextByXpath(elementXpath).equals(changeTo)) {
                return false;
            }
        } catch (Exception e) {
            Narrator.logError("Failed to wait for element to change  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean waitForElementByXpath(String elementXpath) {
        boolean elementFound = false;
        try {
            int waitCount = 0;

            while (!elementFound && waitCount < (ApplicationConfig.WaitTimeout())) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
//                    if (!isCucumberMobileTesting) {
//                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
//                    }
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }
            if (waitCount == (ApplicationConfig.WaitTimeout())) {
                Narrator.logError("Reached TimeOut whilst waiting for element by Xpath '" + elementXpath + "'");

                return elementFound;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementPresentByXpath(String elementXpath) {

        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < (ApplicationConfig.WaitTimeout())) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementPresentByXpath(String elementXpath, int timeout) {
        //timeout = 30;
        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementByXpath(String elementXpath, int timeout) {
        //timeout = 30;
        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementToBeVisibleByXpath(String elementXpath) {
        int timeout = 30;
        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean waitForElementInvisibleByXpath(String elementXpath) {
        boolean elementNoLongerFound = false;
        int timeout = 40;//Default timeout of 20 seconds is too short for this method.
        try {
            int waitCount = 0;
            while (!elementNoLongerFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));
                    if (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXpath)))) {//If wait passes, element is still present.
                        elementNoLongerFound = true;
                    }
                } catch (Exception e) {//If Exception is thrown, element is not present anymore.
                    elementNoLongerFound = false;
                }
                waitCount++;
                pause(1000);//Pause required due to the nature of this method.
            }
        } catch (Exception e) {
            Narrator.logError(" Failed to wait for element to no longer be present by Xpath  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementNoLongerFound;
    }

    public boolean waitForElementNoLongerPresentByXpath(String elementXpath) {
        boolean elementNoLongerFound = false;
        int timeout = 20;//Default timeout of 20 seconds is too short for this method.
        try {
            int waitCount = 0;
            while (!elementNoLongerFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null) {//If wait passes, element is still present.
                        elementNoLongerFound = false;
                    }
                } catch (Exception e) {//If Exception is thrown, element is not present anymore.
                    elementNoLongerFound = true;
                }
                waitCount++;
                pause(1000);//Pause required due to the nature of this method.
            }
        } catch (Exception e) {
            Narrator.logError(" Failed to wait for element to no longer be present by Xpath  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementNoLongerFound;
    }

    public boolean enterTextByXpath(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            elementToTypeIn.sendKeys(textToEnter);
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean enterFileByXpath(String elementXpath, String filePath) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            elementToTypeIn.sendKeys(filePath);
            Narrator.logDebug("Entered file of: " + filePath + " to: " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean copyAndPasteTextByXpath(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(textToEnter);
            clipboard.setContents(selection, null);
            elementToTypeIn.sendKeys(Keys.chord(Keys.CONTROL, "v"));
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean pressEscapeKey() {
        try {

//            elementToTypeIn.clear();
//            elementToTypeIn.sendKeys(Keys.chord(Keys.CONTROL, "v"));
//            Actions act = new Actions(Driver);
//            act.sendKeys(Keys.ESCAPE).build().perform();
//            Narrator.logDebug("Entered Escape Key");
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ESCAPE);
            r.keyRelease(KeyEvent.VK_ESCAPE);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering escape key - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean pressEnterKey() {
        try {

//            elementToTypeIn.clear();
//            elementToTypeIn.sendKeys(Keys.chord(Keys.CONTROL, "v"));
//            Actions act = new Actions(Driver);
//            act.sendKeys(Keys.ESCAPE).build().perform();
//            Narrator.logDebug("Entered Escape Key");
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering escape key - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    //implement still...
    public boolean roboEnterLastTextByXpath(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
//            elementToTypeIn.clear();
            elementToTypeIn.sendKeys(textToEnter);
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean enterTextByXpathUsingActions(String elementXpath, String textToEnter) {
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToTypeIn = Driver.findElement(By.xpath(elementXpath));
            elementToTypeIn.clear();
            Actions typeText = new Actions(Driver);
            typeText.moveToElement(elementToTypeIn);
            typeText.click(elementToTypeIn);
            typeText.sendKeys(elementToTypeIn, textToEnter);
            typeText.click(elementToTypeIn);
            typeText.perform();
            Narrator.logDebug("Entered Text of: " + textToEnter + " to: " + elementXpath);

            return true;
        } catch (Exception e) {
            Narrator.logError(" entering text - " + elementXpath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean selectFromDropDownListUsingXpath(String elementXpath, String valueToSelect) {
        try {
            waitForElementByXpath(elementXpath);
            Select dropDownList = new Select(Driver.findElement(By.xpath(elementXpath)));
            WebElement formxpath = Driver.findElement(By.xpath(elementXpath));
            formxpath.click();
            dropDownList.selectByVisibleText(valueToSelect);
            Narrator.logDebug("Selected Text of: " + valueToSelect + " from: " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError(" selecting from dropdownlist by text using Id - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean alertHandler() {
        try {
            Narrator.logDebug("Attempting to click OK in alert pop-up");
            // Get a handle to the open alert, prompt or confirmation
            Alert alert = Driver.switchTo().alert();
            // Get the text of the alert or prompt
            alert.getText();
            // And acknowledge the alert (equivalent to clicking "OK")
            alert.accept();
            Narrator.logDebug("Ok Clicked successfully...proceeding");
            return true;
        } catch (Exception e) {
            Narrator.logError(" clicking OK in alert pop-up - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean cancelAlertHandler() {
        try {
            Narrator.logDebug("Attempting to click OK in alert pop-up");
            // Get a handle to the open alert, prompt or confirmation
            Alert alert = Driver.switchTo().alert();
            // Get the text of the alert or prompt
            alert.getText();
            // And acknowledge the alert (equivalent to clicking "OK")
            alert.dismiss();
            Narrator.logDebug("Cancel Clicked successfully...proceeding");
            return true;
        } catch (Exception e) {
            Narrator.logError(" clicking Cancel in alert pop-up - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public String retrieveTextByXpath(String elementXpath) {
        String retrievedText = "";
        try {
            this.waitForElementPresentByXpath(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));
            retrievedText = elementToRead.getText();
            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from eloement - " + elementXpath);
            return retrievedText;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public String retrieveTextByCSSSelector(String elementCSSSelector) {
        String retrievedText = "";
        try {
            WebElement elementToRead = Driver.findElement(By.cssSelector(elementCSSSelector));
            retrievedText = elementToRead.getText();
            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from eloement - " + elementCSSSelector);
            return retrievedText;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public String retrieveListByXpath(String elementXpath) {
        String retrievedText = "";
        try {
            // this.waitForElementPresentByXpath(elementXpath);
            java.util.List<WebElement> elementToRead = Driver.findElements(By.xpath(elementXpath));

            WebElement newElement = Driver.findElement(By.xpath(elementXpath + "[" + elementToRead.size() + "]"));
            retrievedText = newElement.getText();
            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from element - " + elementXpath);
            return retrievedText;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public String retrieveListByXpath(String elementXpath, int column) {
        String retrievedText = "";
        try {
            this.waitForElementPresentByXpath(elementXpath);
            java.util.List<WebElement> elementToRead = Driver.findElements(By.xpath(elementXpath));

            WebElement newElement = Driver.findElement(By.xpath(elementXpath + "[" + column + "]"));
            retrievedText = newElement.getText();
            Narrator.logDebug("Text: " + retrievedText + " retrieved successfully from element - " + elementXpath);
            return retrievedText;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedText;
        }
    }

    public ArrayList retrieveAndOutputListByXpath(String elementXpath, int rowNumber) {
        ArrayList arrayList = new ArrayList();
        try {
            java.util.List values = new ArrayList();
            this.waitForElementByXpath(elementXpath);
            WebElement baseTable = SeleniumDriverInstance.Driver.findElement(By.xpath(elementXpath));
            java.util.List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            int rowCount = rows.size();
            if (rowNumber == 0) {
                rowCount = 1;
            }
            String[] headers = {"", "", "Contact Type", "Title", "Name", "Surname", "Cell No", "Tel No"};
            for (int row = rowNumber; row < rowCount; row++) {
                java.util.List<WebElement> columns_row = rows.get(row).findElements(By.tagName("td"));
                int columns_count = columns_row.size();
                for (int column = 0; column < columns_count; column++) {
                    String celtext = columns_row.get(column).getText();
                    arrayList.add(celtext);
                }
            }
            //Narrator.logDebug("Text: " + rows + " retrieved successfully from element - " + elementXpath);
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve List from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return null;
        }
        return arrayList;
    }

    public ArrayList retrieveAndOutputListByXpath(String elementXpath) {
        ArrayList arrayList = new ArrayList();
        try {
            java.util.List values = new ArrayList();
            this.waitForElementByXpath(elementXpath);
            WebElement baseTable = SeleniumDriverInstance.Driver.findElement(By.xpath(elementXpath));
            java.util.List<WebElement> rows = baseTable.findElements(By.tagName("tr"));
            int rowCount = rows.size();

            String[] headers = {"", "", "Contact Type", "Title", "Name", "Surname", "Cell No", "Tel No"};
            for (int row = 0; row < rowCount; row++) {
                java.util.List<WebElement> columns_row = rows.get(row).findElements(By.tagName("td"));
                int columns_count = columns_row.size();
                for (int column = 0; column < columns_count; column++) {
                    String celtext = columns_row.get(column).getText();
                    arrayList.add(celtext);
                }
            }
            //Narrator.logDebug("Text: " + rows + " retrieved successfully from element - " + elementXpath);
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve List from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return null;
        }
        return arrayList;
    }

    public ArrayList retrieveTextList(String elementXpath) {
        //============== PLengisi =======================
        ArrayList arrayList = new ArrayList();
        try {
            java.util.List<WebElement> items = SeleniumDriverInstance.Driver.findElements(By.xpath(elementXpath));
            for (WebElement extractedItems : items) {
                if (!extractedItems.getText().equalsIgnoreCase("")) {
                    arrayList.add(extractedItems.getText());
                }
            }
            Narrator.logDebug("Retrieved text list from element XPath" + elementXpath);
            return arrayList;
        }//end try
        catch (Exception e) {
            Narrator.logError(" Failed to retrieve List from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return null;
        }
    }//end method

    public String retrieveAttributeByXpath(String elementXpath, String Attribute) {
        String retrievedAttribute = "";
        try {
            this.waitForElementByXpath(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));
            retrievedAttribute = elementToRead.getAttribute(Attribute);
            Narrator.logDebug("Attribute retrieved successfully from element - " + elementXpath);
            return retrievedAttribute;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve attribute from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedAttribute;
        }
    }

    public void pressKey(Keys keyToPress) {
        try {
            Actions action = new Actions(Driver);
            action.sendKeys(keyToPress);
            action.perform();
        } catch (Exception e) {
            this.DriverExceptionDetail = e.getMessage();
            Narrator.logError(" Failed to send keypress to element - " + keyToPress);
        }
    }

    public Boolean ValidateByAttribute(String elementXpath, String Attribute, String testData) {
        try {
            String attribute = retrieveAttributeByXpath(elementXpath, Attribute);
            String data = testData;

            if (!attribute.equals(data)) {
                Narrator.logError("Failed to validate " + attribute + ", against " + data + ".");
                return false;
            }
            Narrator.logDebug("Validated by attribute value: " + attribute + ", successfully.");

            return true;

        } catch (Exception e) {
            Narrator.logError("Failed to Validate attribute against TestData - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public Boolean ValidateByText(String elementXpath, String testData) {
        try {
            String text = retrieveTextByXpath(elementXpath);
            String data = testData;

            if (!text.equals(data)) {
                Narrator.logError("Failed to validate " + text + ", against " + data + ".");
                return false;
            }
            Narrator.logDebug("Validated by text value: " + text + ", successfully.");

            return true;

        } catch (Exception e) {
            Narrator.logError("Failed to Validate Text against TestData - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public boolean switchToFrameByXpath(String frameXpath) {
        int waitCount = 0;
        try {
            while (waitCount < (ApplicationConfig.WaitTimeout())) {
                try {
                    Driver.switchTo().frame(frameXpath);
                    Narrator.logDebug("Switched to frame " + frameXpath);
                    return true;
                } catch (Exception e) {
                    //Thread.sleep(500);
                    waitCount++;
                }
            }
            return false;
        } catch (Exception e) {
            Narrator.logError(" switching to frame by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToDefaultContent() {
        try {
            Driver.switchTo().defaultContent();
            Narrator.logDebug("Switched to default content");
            return true;
        } catch (Exception e) {
            Narrator.logError(" switching to default content  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public LinkedList<DataColumn> captureTableInformation(String TableXpath) {
        DataRow newRow = new DataRow();

        try {
            //Finds the Table
            java.util.List<WebElement> Header = Driver.findElements(By.xpath(TableXpath + "//th"));
            java.util.List<String> Headers = new ArrayList<>();

            for (int i = 0; i < Header.size(); i++) {
                String Head = retrieveTextByXpath(TableXpath + "//th[" + (i + 1) + "]");
                Headers.add(Head);
            }

            java.util.List<WebElement> Rows = Driver.findElements(By.xpath(TableXpath + "//tr"));

            for (WebElement Row : Rows) {
                java.util.List<WebElement> Cells = Row.findElements(By.xpath(TableXpath + "//td"));

                for (int i = 0; i < Cells.size(); i++) {

                    DataColumn newColumn = new DataColumn("", "", Enums.ResultStatus.UNCERTAIN);

                    newColumn.columnHeader = Headers.get((i % (Headers.size())));
                    newColumn.columnValue = Cells.get(i).getText();
                    newColumn.resultStatus = Enums.ResultStatus.UNCERTAIN;

                    newRow.DataColumns.add(newColumn);
                }

                return newRow.DataColumns;
            }

        } catch (Exception e) {
            System.err.println("Error Creating Table" + e);
            this.DriverExceptionDetail = "Error Creating Table";

        }
        return newRow.DataColumns;
    }

    public int returnNumberOfTableRows(String TableXpath) {

        try {
            //Finds the Table
            java.util.List<WebElement> Header = Driver.findElements(By.xpath(TableXpath + "//th"));
            java.util.List<String> Headers = new ArrayList<>();

            for (int i = 0; i < Header.size(); i++) {
                String Head = retrieveTextByXpath(TableXpath + "//th[" + (i + 1) + "]");
                Headers.add(Head);
            }

            java.util.List<WebElement> Rows = Driver.findElements(By.xpath(TableXpath + "//tr"));

            return Rows.size();

        } catch (Exception e) {
            System.err.println("Error Creating Table" + e);
            this.DriverExceptionDetail = "Error Creating Table";

        }
        return -1;
    }

    public boolean SaveCookiesToFile(String filepath) {
        Set<Cookie> cookieSetReturn;

        Set<Cookie> cookies = Driver.manage().getCookies();

        String path = filepath + "/Cookies.ser";
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cookies);
            out.close();
            fileOut.close();
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to Add Cookies, error - " + e.getMessage());
            return false;
        }
    }

    public boolean AddCookiesFromFile(String filepath) {
        Set<Cookie> cookieSetReturn;

        try {
            //Reads the Cookie File
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cookieSetReturn = (Set<Cookie>) in.readObject();
            in.close();
            fileIn.close();

            for (Cookie getcookies : cookieSetReturn) {
                Driver.manage().addCookie(getcookies);

            }
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to Add Cookies, error - " + e.getMessage());
            return false;
        }
    }

    public void takeScreenShot(String screenShotDescription, boolean isError) {
        String imageFilePathString = "";

        try {
            StringBuilder imageFilePathBuilder = new StringBuilder();
            // add date time folder and test case id folder
            imageFilePathBuilder.append(this.reportDirectory + "/");

            relativeScreenShotPath = testCaseId + "/";

            if (isError) {
                relativeScreenShotPath += "FAILED_";
            } else {
                relativeScreenShotPath += "PASSED_";
            }

            relativeScreenShotPath += screenShotDescription + ".png";

            imageFilePathBuilder.append(relativeScreenShotPath);

            //imageFilePathBuilder.append(this.generateDateTimeString() + ".png");
            imageFilePathString = imageFilePathBuilder.toString();

            setScreenshotPath(imageFilePathString);

            File screenShot = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot, new File(imageFilePathString.toString()));
        } catch (Exception e) {
            Narrator.logError(" could not take screenshot - " + imageFilePathString.toString() + " - " + e.getMessage());
        }
    }

    public void shutDown() {
        retrievedTestValues = null;
        try {

            Driver.quit();

        } catch (Exception e) {
            Narrator.logError(" shutting down driver - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        _isDriverRunning = false;
    }

    public void copyKeys() {
        try {
            Actions action = new Actions(Driver);
            action.sendKeys(Keys.CONTROL, "c");
            action.perform();
        } catch (Exception e) {
            this.DriverExceptionDetail = e.getMessage();
            Narrator.logError(" Failed to send keypress to element - Contoll + C");

        }
    }

    public boolean downloadImgUsingURL(String imageURL, String destinationFile) {
        try {
            URL url = new URL(imageURL);
            System.setProperty("jsse.enableSNIExtension", "false");
            InputStream fis = url.openStream();
            OutputStream fos = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = fis.read(b)) != -1) {
                fos.write(b, 0, length);
            }

            fis.close();
            fos.close();
            return true;
        } catch (Exception e) {
            narrator.logError("Failed to download Image, error - " + e.getMessage());
            return false;
        }
    }

    public boolean switchToTabOrWindow() {
        try {
            //  String winHandleBefore = SeleniumDriverInstance.Driver.getWindowHandle();
            for (String winHandle : SeleniumDriverInstance.Driver.getWindowHandles()) {
                SeleniumDriverInstance.Driver.switchTo().window(winHandle);
            }
        } catch (Exception ex) {
            Narrator.logError("Could not switch to new tab" + ex.getMessage());
            this.DriverExceptionDetail = ex.getMessage();
            return false;
        }
        Narrator.logDebug("Switched to window " + Driver.getTitle());
        return true;
    }

    public boolean closeTabExcept(String orignaltabHandle) {
        Set<String> handles = Driver.getWindowHandles(); // Gets all the available windows
        for (String handle : handles) {
            // Compare title and if title matches stop loop and return true
            if (!handle.equals(orignaltabHandle)) {
                Driver.switchTo().window(handle).close();
                Narrator.logDebug("Close browser tab handle " + handle);
            }
        }
//
        Driver.switchTo().window(orignaltabHandle); // Switch back to original window handle
        return true; // Return false as failed to find window with given title.
    }

    public boolean switchToWindow(WebDriver driver, String title) {
        mainWindowsHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles(); // Gets all the available windows
        for (String handle : handles) {
            driver.switchTo().window(handle); // switching back to each window in loop
            if (driver.getTitle().equals(title)) // Compare title and if title matches stop loop and return true
            {
                Narrator.logDebug("Switched to window " + Driver.getTitle());
                return true; // We switched to window, so stop the loop and come out of funcation with positive response
            }
        }
        driver.switchTo().window(mainWindowsHandle); // Switch back to original window handle
        return false; // Return false as failed to find window with given title.
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public boolean scrollToElement(String elementXpath) {
        try {
            WebElement element = Driver.findElement(By.xpath(elementXpath));
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollIntoView()", element);
            Narrator.logDebug("Scrolled to element - " + elementXpath);
            return true;
        } catch (Exception e) {
            Narrator.logError("Error scrolling to element - " + elementXpath + " - " + e.getStackTrace());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public boolean scrollToTheLeft(String javaScriptPath, int scrollToPixel) {
        try {
            ((JavascriptExecutor) Driver).executeScript("arguments[0].scrollLeft = arguments[1];", javaScriptPath, scrollToPixel);
            Narrator.logDebug("Scrolled to the Left by '" + scrollToPixel + "' pixels.");
            return true;
        } catch (Exception e) {
            Narrator.logError("Error scrolling to the left of the element - " + javaScriptPath + " - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean scrollBarPresent() {
        try {
            ((JavascriptExecutor) Driver).executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
            Narrator.logDebug("Validating if a scrollbar is present");
            return true;
        } catch (Exception e) {
            Narrator.logError("Error scrollbar not present - " + e.getStackTrace());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }

    }

    public Boolean ValidateURL(String expectedUrl) {
        try {
            String url = Driver.getCurrentUrl();

            if (!expectedUrl.equals(url)) {
                Narrator.logError("Failed to validate " + expectedUrl + ", against " + url + ".");
                return false;
            }
            Narrator.logDebug("Validated by Url value: " + expectedUrl + ", successfully.");
            return true;

        } catch (Exception ex) {
            Narrator.logError("Failed to validate url: " + ex.getMessage());
            return false;
        }
    }

    public String getCurrentURL() {
        try {
            return SeleniumDriverInstance.Driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println("Could not find current URL - " + e.getMessage());
            return e.getMessage();
        }
    }

    public boolean isCheckboxSelectedByXpath(String xpath) {
        try {
            return Driver.findElement(By.xpath(xpath)).isSelected();
        } catch (Exception e) {
            System.out.println("Could not find the xpath - " + e.getMessage());
            return false;
        }
    }

    public boolean textToEnterViaClipboard(String text) {
        try {
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);

        } catch (AWTException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    public boolean maskHandlerLoadingBarWIA() {
        if (waitForElementPresentByXpath("//div//span[@class = 'k-loading-text']", 2)) {
            if (!waitForElementNoLongerPresentByXpath("//div//span[@class = 'k-loading-text']", 60)) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerLoadingBar() {
        if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']", 2)) {
            if (!SeleniumDriverInstance.waitForElementInvisibleByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']")) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerLoadingBarImportButtonTracker() {

        if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[@style='display: block; z-index: 10002; opacity: 0.5;']", 2)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//div[@style='display: block; z-index: 10002; opacity: 0.5;']")) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerLoadingBarImportButtonTracker(int i) {

        if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[@style='display: block; z-index: 10002; opacity: 0.5;']", i)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//div[@style='display: block; z-index: 10002; opacity: 0.5;']")) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerLoadingBarCreateWorkItem() {

        if (SeleniumDriverInstance.waitForElementPresentByXpath("//span[@id='CustomerInformation_crmsearch_message']/..//div//div//div[contains(@class,'loading-mask')]", 2)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//span[@id='CustomerInformation_crmsearch_message']/..//div//div//div[contains(@class,'loading-mask')]")) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerSearchResultsTrackerLoadingBar() {

        if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[@class='k-loading-image']", 3)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//div[@class='k-loading-image']")) {
                return false;
            }
        }
        return true;
    }

    public boolean maskHandlerSearchResultsTrackerLoadingBar(String xpath) {

        if (SeleniumDriverInstance.waitForElementPresentByXpath(xpath, 5)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(xpath)) {
                if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(xpath)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean workItemMessageHandler(String xpath) {

        if (SeleniumDriverInstance.waitForElementPresentByXpath(xpath)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(xpath)) {
                if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath(xpath)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean maskHandlerLoadingBar(int timeout) {

        if (SeleniumDriverInstance.waitForElementPresentByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']", 2)) {
            if (!SeleniumDriverInstance.waitForElementNoLongerPresentByXpath("//div[contains(@class,'block k-reorderable')]//span[text()='Loading...']", timeout)) {
                return false;
            }
        }
        return true;
    }

    public boolean waitForElementNoLongerPresentByXpath(String elementXpath, int timeout) {
        boolean elementNoLongerFound = false;

        try {
            int waitCount = 0;
            while (!elementNoLongerFound && waitCount < timeout) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementNoLongerFound = false;
                    }
                } catch (Exception e) {
                    elementNoLongerFound = true;
                }
                waitCount++;
                pause(1000);
            }
        } catch (Exception e) {
            Narrator.logError(" Failed to wait for element to no longer be present by Xpath  - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementNoLongerFound;
    }

    public boolean waitForElementByXpathForUnclickableElements(String elementXpath) {
        boolean elementFound = false;
        try {
            int waitCount = 0;

            while (!elementFound && waitCount < (ApplicationConfig.WaitTimeout())) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
//                    if (!isCucumberMobileTesting) {
//                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
//                    }
                    if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + elementXpath);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }
            if (waitCount == (ApplicationConfig.WaitTimeout())) {
                Narrator.logError("Reached TimeOut whilst waiting for element by Xpath '" + elementXpath + "'");

                return elementFound;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by Xpath '" + elementXpath + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public String retrieveAttributeByXpathForUnclickableElements(String elementXpath, String Attribute) {
        String retrievedAttribute = "";
        try {
            this.waitForElementByXpathForUnclickableElements(elementXpath);
            WebElement elementToRead = Driver.findElement(By.xpath(elementXpath));
            retrievedAttribute = elementToRead.getAttribute(Attribute);
            Narrator.logDebug("Attribute retrieved successfully from element - " + elementXpath);
            return retrievedAttribute;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve attribute from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return retrievedAttribute;
        }
    }

    public boolean clearTextBoxByXPath(String xpath) {
        try {
            SeleniumDriverInstance.Driver.findElement(By.xpath(xpath)).clear();
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to clear text box from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public java.util.List<WebElement> retrieveTableColumnsByXpath(String elementXpath) {
        java.util.List<WebElement> elementToRead = new ArrayList<>();
        try {
            // this.waitForElementPresentByXpath(elementXpath);
            elementToRead = Driver.findElements(By.xpath(elementXpath));
            Narrator.logDebug("Table elements retrieved successfully from element - " + elementXpath);
            return elementToRead;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return elementToRead;
        }
    }

    public java.util.List<WebElement> retrieveListOfElementsByXpath(String elementXpath) {
        java.util.List<WebElement> elementToRead = new ArrayList<>();
        try {
            // this.waitForElementPresentByXpath(elementXpath);
            elementToRead = Driver.findElements(By.xpath(elementXpath));
            Narrator.logDebug("Table elements retrieved successfully from element - " + elementXpath);
            return elementToRead;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve text from element Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return elementToRead;
        }
    }

    public boolean openLinkInNewTabByXpath(String xpath, String link) {
        try {
            Driver.findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL + "t");
            Driver.get(link);
            return true;
        } catch (Exception e) {
            Narrator.logError("Failed to open element Xpath in a new tab - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean switchToPreviousTab() {
        try {
            ArrayList<String> tabs = new ArrayList(Driver.getWindowHandles());
            Driver.switchTo().window(tabs.get(0));
            return true;

        } catch (Exception e) {
            Narrator.logError("Failed to switch to previous tab - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean closeTab(int tabNumber) {
        try {
            ArrayList<String> tabs = new ArrayList(Driver.getWindowHandles());
            Driver.switchTo().window(tabs.get(tabNumber));
            Driver.close();
            Driver.switchTo().window(tabs.get(0));
            return true;

        } catch (Exception e) {
            Narrator.logError(" Failed to close the tab - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public java.util.List<WebElement> getWebListByXpath(String xpath) {
        try {
            waitForElementPresentByXpath(xpath);
            java.util.List<WebElement> workListData = this.Driver.findElements(By.xpath(xpath));
            return workListData;

        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve WebElement List - " + e.getMessage());
            return null;
        }
    }

    public boolean enterKendoDate(String tableXpath, String calendarIconXpath, String centreYearXpath, String
            previousArrowXpath, String nextArrowXpath, String day, String month, String year, String startOrEnd) {
        try {
            java.util.List<String> months = Arrays.asList("", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
            List<String> asMonths = Arrays.asList("", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            clickElementbyXpath(calendarIconXpath);
            pause(2000);
            clickElementbyXpath(centreYearXpath);
            expectedYear = retrieveTextByXpath(centreYearXpath);

            //Selecting a year
            if (Integer.parseInt(expectedYear) > Integer.parseInt(year)) {
                int count = Integer.parseInt(year) - Integer.parseInt(expectedYear);

                for (int i = 0; i < count; i++) {
                    clickElementbyXpath(previousArrowXpath);
                }
            }//end if
            else if (Integer.parseInt(expectedYear) < Integer.parseInt(year)) {
                int count = Integer.parseInt(year) - Integer.parseInt(expectedYear);

                for (int i = 0; i < count; i++) {
                    clickElementbyXpath(nextArrowXpath);
                }
            }

            String dateFull = asMonths.get(Integer.parseInt(month)) + " " + day + ", " + year;
            if (Integer.parseInt(expectedYear) < Integer.parseInt(year)) {
                dateFull = Integer.parseInt(year) + "/" + month + "/" + day;
            } else {
                dateFull = expectedYear + "/" + month + "/" + day;
            }
            // dateFull = expectedYear+"/"+month+"/"+day;
            if (startOrEnd.toLowerCase().contains("start")) {
                startDate = dateFull;
            } else {
                endDate = dateFull;
            }
            //Selecting a month
            waitForElementPresentByLinkText(months.get(Integer.parseInt(month)));
            clickElementbyLinkText(months.get(Integer.parseInt(month)));
            //a[@data-value = '2019/9/17']
            narrator.stepInformation("Full Date is >>> " + dateFull);
            //Selecting a date(day)
            WebDriverWait wait = new WebDriverWait(Driver, (30));
            //   wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='StartDate_dateview']//a[@data-value='"+dateFull+"']")));
            if (Integer.parseInt(day) < 10) {
                day = day.substring(1);
            }

            int x = 0;
            for (x = 1; x <= 6; x++) {

                if (!SeleniumDriverInstance.waitForElementByXpath("//div[@id='" + startOrEnd + "']//tbody//tr[" + x + "]//a[text()='" + day + "']", 2)) {

                } else {
                    WebElement selected = Driver.findElement(By.xpath("//div[@id='" + startOrEnd + "']//tbody//tr[" + x + "]//a[text()='" + day + "']"));
                    ((JavascriptExecutor) Driver).executeScript("return arguments[0].click();", selected);
                    break;
                }
            }
            if (x > 6) {
                Narrator.logError(" Failed to find date.");
                return false;
            }

            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to retrieve WebElement List - " + e.getMessage());
            return false;
        }
    }

    public boolean clickElementbyLinkText(String text) {
        try {

            waitForElementPresentByLinkText(text);
            WebDriverWait wait = new WebDriverWait(Driver, (ApplicationConfig.WaitTimeout()));
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText(text)));
            WebElement elementToClick = Driver.findElement(By.linkText(text));
            elementToClick.click();
            Narrator.logDebug("Clicked element by Xpath : " + text);
            return true;
        } catch (Exception e) {
            Narrator.logError(" Failed to click on element by Xpath - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
            return false;
        }
    }

    public boolean waitForElementPresentByLinkText(String text) {
        boolean elementFound = false;
        try {
            int waitCount = 0;
            while (!elementFound && waitCount < (ApplicationConfig.WaitTimeout())) {
                try {
                    WebDriverWait wait = new WebDriverWait(Driver, (1));

                    wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(text)));
                    if (wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(text))) != null) {
                        elementFound = true;
                        Narrator.logDebug(" Found element by Xpath : " + text);
                        break;
                    }
                } catch (Exception e) {
                    elementFound = false;
                }
                //Thread.sleep(500);
                waitCount++;
            }

        } catch (Exception e) {
            Narrator.logError(" waiting for element by link text '" + text + "' - " + e.getMessage());
            this.DriverExceptionDetail = e.getMessage();
        }
        return elementFound;
    }

    public boolean enableTextAreaUsingCSS(String xpath) {
        //Find the element of the textarea first
        //Replace _driver with your Selenium WebDriver
        WebElement textArea = Driver.findElement(By.xpath(xpath));
        JavascriptExecutor jsExec = (JavascriptExecutor) Driver;

        //sets the target webelement style to nothing
        jsExec.executeScript("arguments[0].style=''", textArea);

        return true;
    }

    public boolean isDataFoundInTable(String expectedData) {
        WebElement table = Driver.findElement(By.cssSelector("table"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(expectedData)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] findDataIndexInTableWithIndex(String expectedData, int expectedIndex) {
        WebElement table = Driver.findElement(By.cssSelector("table selector"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));

        int rowIndex = 0;
        int cellIndex = 0;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            if (cells.size() < 2) {
                continue; // Skip row if it does not contain both data and index columns
            }
            int index = Integer.parseInt(cells.get(0).getText());
            if (index != expectedIndex) {
                continue; // Skip row if index does not match expected index
            }
            cellIndex = 0;
            for (WebElement cell : cells) {
                if (cellIndex == 0) {
                    cellIndex++;
                    continue; // Skip index column
                }
                if (cell.getText().equals(expectedData)) {
                    return new int[]{rowIndex, cellIndex};
                }
                cellIndex++;
            }
            rowIndex++;
        }

        return new int[]{-1, -1}; // Data not found in table
    }


    public int[] findDataIndexInTable(String expectedData) {
        WebElement table = Driver.findElement(By.cssSelector("table selector"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr"));

        int rowIndex = 0;
        int cellIndex = 0;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            cellIndex = 0;
            for (WebElement cell : cells) {
                if (cell.getText().equals(expectedData)) {
                    return new int[]{rowIndex, cellIndex};
                }
                cellIndex++;
            }
            rowIndex++;
        }

        return new int[]{-1, -1}; // Data not found in table
    }

    public static void validateFileDownloaded(String adviserName, String downloadDir) {
        String partialUsername = adviserName; // The partial username you want to check for
        File downloadDirectory = new File(downloadDir);

        File[] downloadedFiles = downloadDirectory.listFiles();
        boolean found = false;

        if (downloadedFiles != null) {
            for (File file : downloadedFiles) {
                if (file.getName().contains(partialUsername) && file.getName().endsWith(".pdf")) {
                    Narrator.logDebug("PDF file with username '" + partialUsername + "' has been successfully downloaded.");
                    found = true;
                    // You can add additional validation here, such as checking the PDF content.
                    break; // Exit the loop once a matching file is found
                }
            }
        }

        if (!found) {
            narrator.stepFailed("No PDF file with username '" + partialUsername + "' found in the download directory.");
        }
    }

    public static void waitForFileDownload(String fileName, String downloadDir) {
        File file = new File(downloadDir + File.separator + fileName);
        int waitTime = 0;
        int maxWaitTime = 30; // Maximum wait time in seconds

        while (!file.exists() && waitTime < maxWaitTime) {
            try {
                Thread.sleep(1000); // Wait for 1 second
                waitTime++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (file.exists()) {
            narrator.stepPassed("File downloaded successfully.");
        } else {
            narrator.stepFailed("File download failed within the specified time.");
        }
    }

}
//     public void pause(int seconds) {
//        try {
//            Thread.sleep((seconds * 1000));
//        } catch (InterruptedException ex) {
//            Logger.getLogger(LoginIntoIEvolve.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }