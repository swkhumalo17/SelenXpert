package KeywordDrivenTestFramework.Testing.TestClasses.Perfomance;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/09/12.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "validatePerformance", createNewBrowserInstance = false)
public class validatePerformanceDashboard extends BaseClass {
    String error = "";

    public TestResult executeTest() {
        if (!NavigateToPerformance()) {
            return narrator.testFailed(error);
        }
        if (!performanceValidation()) {
            return narrator.testFailed(error);
        }
        return narrator.finalizeTest("Successfully validated the performance dashboard.");
    }

    public boolean NavigateToPerformance() {
        //Navigate to Performance
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.burgerMenu())) {
            error = "Failed to wait for the burger menu.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.burgerMenu())) {
            error = "Failed to click the burger menu to expand.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully expanded the burger menu.");

        //Performance dropdown
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.performanceSidebar_drpdwn())) {
            error = "Failed to wait for the Performance dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.performanceSidebar_drpdwn())) {
            error = "Failed to click the Performance dropdown.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully expanded the Performance dropdown");

        //CAL List
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.CAL_List())) {
            error = "Failed to wait for the Performance >> CAL List.";
            return false;
        }

        if (getData("Username").equalsIgnoreCase("Adviser")) {
            //My Performance
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myPerformance())) {
                error = "Failed to wait for the Performance >> My Performance.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.myPerformance())) {
                error = "Failed to click the Performance >> My Performance.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the 'My Performance'");
        } else if (getData("Username").equalsIgnoreCase("Sales Manager")) {
            //Team Performance
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.teamPerformance())) {
                error = "Failed to wait for the Performance >> Team Performance.";
                return false;
            }
            if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.teamPerformance())) {
                error = "Failed to click the Performance >> Team Performance.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully clicked the 'Team Performance'");
        }

        return true;
    }

    public boolean performanceValidation(){
        if (getData("Username").equalsIgnoreCase("Adviser")) {
            //My Performance Overview
            if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myPerformanceOverview())){
                error = "Failed to wait for 'My Performance Overview' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'My Performance Overview' panel is visible.");

            //My Performance To Do List
            if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myPerformanceToDoList())){
                error = "Failed to wait for 'My Performance To Do List' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'My Performance To Do List' panel is visible.");

            //Performance List
            if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.performanceList())){
                error = "Failed to wait for 'Performance List' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'Performance List' panel is visible.");

        }else if (getData("Username").equalsIgnoreCase("Sales Manager")) {
            //Team Performance Overview
            if(!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.teamPerformanceHeader())){
                error = "Failed to wait for 'Team Performance' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'Team Performance' panel is visible.");
        }
        return true;
    }

}
