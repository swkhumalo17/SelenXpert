package KeywordDrivenTestFramework.Testing.TestClasses.Workbench;

import KeywordDrivenTestFramework.Entities.KeywordAnnotation;
import KeywordDrivenTestFramework.Core.BaseClass;
import KeywordDrivenTestFramework.Entities.TestResult;
import KeywordDrivenTestFramework.Testing.PageObjects.DAE_PageObject;

/**
 * @author SKHUMALO on 2023/07/30.
 * @project DAE-Automation-Framework
 */

@KeywordAnnotation(Keyword = "workbench", createNewBrowserInstance = false)
public class workbench extends BaseClass {
    String error = "";
    String user = getData("User");
    String platform = getData("Platform");

    public workbench() {
    }

    public TestResult executeTest() {
        if (!myCalendar()) {
            return narrator.testFailed(error);
        }

        if (user.equalsIgnoreCase("Adviser")) {
            //This will test all segments for an adviser both MFC & PF
            if (!myLeads_Opportunities()) {
                return narrator.testFailed(error);
            }
            if (!myCustomers()) {
                return narrator.testFailed(error);
            }
        } else if (user.equalsIgnoreCase("Sales Manager")) {
            //This will test all segments for Sales Manager both MFC & PF
            if (!myLeads_Opportunities()) {
                return narrator.testFailed(error);
            }
            if (!myCampaigns()) {
                return narrator.testFailed(error);
            }
            if(!myTeam()) {
                return narrator.testFailed(error);
            }

        } else if (user.equalsIgnoreCase("Campaign Manager")) {
            if (!myCampaigns()) {
                return narrator.testFailed(error);
            }
            if (!CampaignsLeads()) {
                return narrator.testFailed(error);
            }
        }

        return narrator.finalizeTest("Successfully validated workbench services.");
    }

    public boolean myCalendar() {
        //validate my calendar div is visible
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myCalender())) {
            error = "Failed to wait for the title header 'My Calendar'.";
            return false;
        }

        //Total upcoming
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalUpcoming())) {
            error = "Failed to wait for the 'Total Upcoming'";
            return false;
        }
        if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalUpcoming(), "TOTAL UPCOMING")) {
            error = "Failed to validate header for My Calendar";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validate the header for My Calendar.");

        return true;
    }
    public boolean myLeads_Opportunities() {
        if (platform.equalsIgnoreCase("PF")) {
            if (user.equalsIgnoreCase("Adviser")) {
                //validate my Leads div is visible
                if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myLeads())) {
                    error = "Failed to wait for the title header 'My Leads'.";
                    return false;
                }
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myLeads())) {
                    error = "Failed to wait for the title header 'My Leads'.";
                    return false;
                }
            } else if (user.equalsIgnoreCase("Sales Manager")) {
                //validate my Opportunities div is visible
                if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myTeamLeads())) {
                    error = "Failed to wait for the title header 'My Team Leads'.";
                    return false;
                }
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myTeamLeads())) {
                    error = "Failed to wait for the title header 'My Team Leads'.";
                    return false;
                }
            }

            //Total Open Leads
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalOpenLeads())) {
                error = "Failed to wait for the 'Total Open Leads'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalOpenLeads(), "TOTAL OPEN LEADS")) {
                error = "Failed to validate header for My Leads.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully validate the header for My Leads.");

        } else if (platform.equalsIgnoreCase("MFC")) {
            if (user.equalsIgnoreCase("Adviser")) {
                //validate my Opportunities div is visible
                if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myOpportunities())) {
                    error = "Failed to wait for the title header 'My Opportunities'.";
                    return false;
                }
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myOpportunities())) {
                    error = "Failed to wait for the title header 'My Opportunities'.";
                    return false;
                }
            } else if (user.equalsIgnoreCase("Sales Manager")) {
                //validate my Opportunities div is visible
                if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myTeamOpportunities())) {
                    error = "Failed to wait for the title header 'My Team Opportunities'.";
                    return false;
                }
                if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myTeamOpportunities())) {
                    error = "Failed to wait for the title header 'My Team Opportunities'.";
                    return false;
                }
            }

            //Total Open Opportunities
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalOpenOpportunities())) {
                error = "Failed to wait for the 'Total Open Opportunities'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalOpenOpportunities(), "TOTAL OPEN OPPORTUNITIES")) {
                error = "Failed to validate header for My Opportunities.";
                return false;
            }
            narrator.stepPassedWithScreenShot("Successfully validate the header for My Opportunities.");
        }
        return true;
    }
    public boolean myCustomers() {
        //validate my Customers div is visible
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myCustomers())) {
            error = "Failed to wait for the title header 'My Customers'.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myCustomers())) {
            error = "Failed to wait for the title header 'My Customers'.";
            return false;
        }

        if (platform.equalsIgnoreCase("PF")) {
            //CUSTOMERS WITH OPEN LEADS/OPPORTUNITIES
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.customersWithOpenLeads())) {
                error = "Failed to wait for the 'CUSTOMERS WITH OPEN LEADS/OPPORTUNITIES'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.customersWithOpenLeads(), "CUSTOMERS WITH OPEN LEADS/OPPORTUNITIES")) {
                error = "Failed to validate header for My Customers.";
                return false;
            }
        } else if (platform.equalsIgnoreCase("MFC")) {
            //TOTAL CUSTOMERS
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalCustomers())) {
                error = "Failed to wait for the 'TOTAL CUSTOMERS'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalCustomers(), "TOTAL CUSTOMERS")) {
                error = "Failed to validate header for My Customers.";
                return false;
            }
        }
        narrator.stepPassedWithScreenShot("Successfully validate the header for My Customers.");


        return true;
    }
    public boolean myCampaigns() {
        if (user.equalsIgnoreCase("Campaign Manager")) {
            //validate my Campaigns div is visible
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myCampaigns())) {
                error = "Failed to wait for the title header 'My Campaigns'.";
                return false;
            }
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myCampaigns())) {
                error = "Failed to wait for the title header 'My Campaigns'.";
                return false;
            }

            //Active campaigns
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.activeMyCampaigns())) {
                error = "Failed to wait for the 'ACTIVE CAMPAIGNS'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.activeMyCampaigns(), "ACTIVE CAMPAIGNS")) {
                error = "Failed to validate header for My Campaigns.";
                return false;
            }

        } else {
            if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myTeamCampaigns())) {
                error = "Failed to wait for the title header 'My Team Campaigns'.";
                return false;
            }
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myTeamCampaigns())) {
                error = "Failed to wait for the title header 'My Team Campaigns'.";
                return false;
            }

            //My Team Campaigns Active Campaign
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.activeMyTeamCampaigns())) {
                error = "Failed to wait for the 'ACTIVE CAMPAIGNS'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.activeMyTeamCampaigns(), "ACTIVE CAMPAIGNS")) {
                error = "Failed to validate header for My Campaigns.";
                return false;
            }

        }


        narrator.stepPassedWithScreenShot("Successfully validate the header for My Campaigns.");

        return true;
    }
    public boolean myTeam() {
        //TOTAL ADVISERS
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.myTeam())) {
            error = "Failed to wait for the title header 'My Team'.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.myTeam())) {
            error = "Failed to wait for the title header 'My Team'.";
            return false;
        }

        if(platform.equalsIgnoreCase("PF")) {
            //TOTAL ADVISERS
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalAdvisers())) {
                error = "Failed to wait for the 'AVAILABLE ADVISERS'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalAdvisers(), "AVAILABLE ADVISERS")) {
                error = "Failed to validate header for My Team.";
                return false;
            }

        }else if(platform.equalsIgnoreCase("MFC")){
            //TOTAL ADVISERS
            if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalAdvisers())) {
                error = "Failed to wait for the 'TOTAL ADVISERS'.";
                return false;
            }
            if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalAdvisers(), "TOTAL ADVISERS")) {
                error = "Failed to validate header for My Team.";
                return false;
            }
        }
        narrator.stepPassedWithScreenShot("Successfully validate the header for My Team.");

        return true;
    }
    public boolean CampaignsLeads() {
        //Campaign Leads
        if (!SeleniumDriverInstance.scrollToElement(DAE_PageObject.campaignLeads())) {
            error = "Failed to wait for the title header 'Campaign Leads'.";
            return false;
        }
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.campaignLeads())) {
            error = "Failed to wait for the title header 'Campaign Leads'.";
            return false;
        }

        //TOTAL ADVISERS
        if (!SeleniumDriverInstance.waitForElementByXpath(DAE_PageObject.totalOpenCampaignLeads())) {
            error = "Failed to wait for the 'TOTAL OPEN LEADS'.";
            return false;
        }
        if (!SeleniumDriverInstance.ValidateByText(DAE_PageObject.totalOpenCampaignLeads(), "TOTAL OPEN LEADS")) {
            error = "Failed to validate header for Campaign Leads.";
            return false;
        }
        narrator.stepPassedWithScreenShot("Successfully validate the header for Campaign Leads.");

        return true;
    }

}
