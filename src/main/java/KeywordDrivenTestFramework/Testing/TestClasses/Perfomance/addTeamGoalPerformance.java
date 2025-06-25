package KeywordDrivenTestFramework.Testing.TestClasses.Perfomance;

import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;
import KeywordDrivenTestFramework.Testing.TestClasses.Campaigns.CreateCampaign;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author SKHUMALO on 2023/09/13.
 * @project DAE-Automation-Framework
 */
@KeywordAnnotation(Keyword = "addTeamPerformanceGoal", createNewBrowserInstance = false)
public class addTeamGoalPerformance extends BaseClass {
    String error = "";
    LocalDate currentDate = LocalDate.now();
    LocalDate nextDay = currentDate.plusDays(1);
    String startDateToSelect = nextDay.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    String MFCstartDateToSelect = currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    LocalDate endDate = currentDate.plusDays(3);
    String endDateToSelect = endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

    public TestResult executeTest() {
        if (!NavigateToPerformance()) {
            return narrator.testFailed(error);
        }
        if (!performanceValidation()) {
            return narrator.testFailed(error);
        }
        if (!addTeamPerformance()) {
            return narrator.testFailed(error);
        }
        if (!addMembers()) {
            return narrator.testFailed(error);
        }
        if (!distributePerformanceGoal()) {
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

    public boolean performanceValidation() {
        if (getData("Username").equalsIgnoreCase("Adviser")) {
            //My Performance Overview
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myPerformanceOverview())) {
                error = "Failed to wait for 'My Performance Overview' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'My Performance Overview' panel is visible.");

            //My Performance To Do List
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.myPerformanceToDoList())) {
                error = "Failed to wait for 'My Performance To Do List' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'My Performance To Do List' panel is visible.");

            //Performance List
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.performanceList())) {
                error = "Failed to wait for 'Performance List' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'Performance List' panel is visible.");

        } else if (getData("Username").equalsIgnoreCase("Sales Manager")) {
            //Team Performance Overview
            if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.teamPerformanceHeader())) {
                error = "Failed to wait for 'Team Performance' panel.";
                return false;
            }
            narrator.stepPassed("Successfully validated 'Team Performance' panel is visible.");
        }
        return true;
    }

    public boolean addTeamPerformance() {
        //Add Team Performance button
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.addTeamPerformance_btn())) {
            error = "Failed to wait for 'Add Team Performance' button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.addTeamPerformance_btn())) {
            error = "Failed to click the 'Add Team Performance' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Add Team Performance' button.");

        //Add Team Performance Goal Card Wrapper
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.addTeamPerformanceGoal())) {
            error = "Failed to wait for the 'Add Team Performance Goal' card wrapper to appear.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigate to the 'Add Team Performance' page.");

        //Team Performance Goal Name
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.teamPerformanceGoalName_txt())) {
            error = "Failed to wait for the Team Performance Goal Name textbox.";
            return false;
        }if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.teamPerformanceGoalName_txt())) {
            error = "Failed to click the Team Performance Goal Name textbox.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.teamPerformanceGoalName_txt(), getData("Team Performance Goal Name"))) {
            error = "Failed to wait for the Team Performance Goal Name textbox.";
            return false;
        }
        pause(2000);
        narrator.stepPassedWithScreenShot("Successfully entered '" + getData("Team Performance Goal Name") + "' into Team Performance Goal Name textbox.");

        //Belongs to item
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.belongsToItem_drpdwn())) {
            error = "Failed to wait for the Belongs to item dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.belongsToItem_drpdwn())) {
            error = "Failed to click the Belongs to item dropdown.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.belongsToItem_option_arrow(getData("Belong to item")))) {
            error = "Failed to click the Belongs to item >> '" + getData("Belong to item") + "'.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.belongsToItem_option(getData("Belong to item 1")))) {
            error = "Failed to click the Belongs to item >> '" + getData("Belong to item 1") + "'.";
            return false;
        }
        pause(2000);
        narrator.stepPassedWithScreenShot("Successfully selected 'Belongs to item': '" + getData("Belong to item") + "' >> '" + getData("Belong to item 1") + "'.");

        //Effective Date
        //Start Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.effective_startDate_drpdwn())) {
            error = "Failed to click the Start Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Start Dropdown.");

        //Get the currentDate
        try {
            WebElement startDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("(//td[@title='" + currentDate + "'])[1]"));
            startDateToSelectElement.click();
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassed("Successfully captured startDate: '" + currentDate + "'");

        //Start Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.effective_startDate_drpdwn())) {
            error = "Failed to click the Start Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the Start Dropdown.");

        pause(2000);
        //End Date
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.effective_endDate_drpdwn())) {
            error = "Failed to click the End Date Dropdown.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the End Date Dropdown.");

        //Get the End date
        try {
            WebElement endDateToSelectElement = SeleniumDriverInstance.Driver.findElement(By.xpath("//td[@title='" + endDate + "']"));
            endDateToSelectElement.click();
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        narrator.stepPassed("Successfully captured startDate: '" + endDate + "'");

        //Evaluation Cycle
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.evaluationCycle_rdbtn(getData("Evaluation Cycle")))) {
            error = "Failed to wait for the 'Evaluation Cycle' radio button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.evaluationCycle_rdbtn(getData("Evaluation Cycle")))) {
            error = "Failed to click the 'Evaluation Cycle' radio button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Evaluation Cycle' radio button.");

        //Description
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.evaluationCycleDescription_txt())) {
            error = "Failed to scroll to the 'Evaluation Cycle' radio button.";
            return false;
        }
        if (!SeleniumDriverInstance.enterTextByXpath(DAE_PageObject.evaluationCycleDescription_txt(), getData("Description"))) {
            error = "Failed to enter the 'Evaluation Cycle' radio button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Evaluation Cycle' radio button.");

        //Next button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to wait for Next button.";
            return false;
        }

        return false;
    }

    public boolean addMembers() {
        //Add Members
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.addMembersCard())) {
            error = "Failed to wait for the Add Members Card.";
            return false;
        }
        //Add Member
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.addMember_btn())) {
            error = "Failed to wait for the Add Member button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.addMember_btn())) {
            error = "Failed to click the Add Member button.";
            return false;
        }
        narrator.stepPassed("Successfully clicked the 'Add Member' button.");

        //Add Members Pop Up
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.selectAll_chckbx())) {
            error = "Failed to wait for the 'Add Members' Organization Tree >> Select All checkbox.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.selectAll_chckbx())) {
            error = "Failed to click the 'Add Members' Organization Tree >> Select All checkbox.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully selected all Organization Tree >> Select All.");

        //Confirm button
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.confirm_btn())) {
            error = "Failed to wait for Confirm button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Confirm button.");

        //Next button
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.next_btn())) {
            error = "Failed to wait for Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click for Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Next button.");

        //Scroll down
        for (int i = 0; i < 2; i++) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.next_btn())) {
                error = "Failed to scroll to Next button.";
                return false;
            }
        }

        //Next button
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.next_btn())) {
            error = "Failed to wait for Next button.";
            return false;
        }
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.next_btn())) {
            error = "Failed to click for Next button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the Next button.");

        return true;
    }

    public boolean distributePerformanceGoal() {
        //Confirm Card Wrapper
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.confirmPage())) {
            error = "Failed to wait for the 'Confirm' card wrapper to appear.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully navigate to the 'Confirm' page.");

        //Add Team Performance Goal
        //Performance Goal Name
        String expectedPerformanceGoalName = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_performanceGoalName());
        try {
            //validate the Performance Goal Name
            if (!expectedPerformanceGoalName.isEmpty()) {
                narrator.ValidateEqual(expectedPerformanceGoalName, getData("Team Performance Goal Name"));
                narrator.stepPassedWithScreenShot("Successfully validated the Expected Goal Name: '" + expectedPerformanceGoalName + "' with Actual Goal Name: '" + getData("Team Performance Goal Name") + "'.");
            } else {
                error = "Failed due to Expected Performance Goal Name is empty.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }

        //Belongs to item
        String expected_BelongsToItem = SeleniumDriverInstance.retrieveTextByXpath(DAE_PageObject.expected_BelongsToItem());
        String actual_BelongsToItem = getData("Belong to item") + " - " + getData("Belong to item 1");
        try {
            //validate the Performance Goal Name
            if (!expected_BelongsToItem.isEmpty()) {
                narrator.ValidateEqual(expected_BelongsToItem, actual_BelongsToItem);
                narrator.stepPassedWithScreenShot("Successfully validated the Expected Goal Name: '" + expected_BelongsToItem + "' with Actual Goal Name: '" + actual_BelongsToItem + "'.");
            } else {
                error = "Failed due to Expected Belongs To Item is empty.";
                return false;
            }
        } catch (Exception e) {
            error = e.getMessage();
            return false;
        }
        //Scroll down
        for (int i = 0; i < 3; i++) {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.submit_btn1())) {
                error = "Failed to scroll to Submit button.";
                return false;
            }
        }

        //Successfully Added Success message
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.successMessage())) {
            error = "Failed to wait for the 'Successfully Added' message.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the success message.");

        //Back to Team Performance
        if (!SeleniumDriverInstance.clickElementbyXpath(DAE_PageObject.backToTeamPerformance_btn())) {
            error = "Failed to click the 'Back to team performance' button.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully clicked the 'Back to the team performance button.'");

        //Validate Team Performance Goal is created
        if (!SeleniumDriverInstance.waitForElementPresentByXpath(DAE_PageObject.TeamPerformanceName(getData("Team Performance Goal Name")))) {
            error = "Failed to wait for the Team Performance Goal: '" + getData("Team Performance Goal Name") + "' is visible.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validated the Team Performance Goal: '" + getData("Team Performance Goal Name") + "' has been added");

        return true;
    }
}
