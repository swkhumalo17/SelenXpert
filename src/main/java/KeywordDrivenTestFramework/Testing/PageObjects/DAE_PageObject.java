package KeywordDrivenTestFramework.Testing.PageObjects;

import KeywordDrivenTestFramework.Entities.Enums;

/**
 * @author SKHUMALO on 2022/11/21.
 * @project OM_Automation_Framework_Azure_DevOps
 */
public class DAE_PageObject {
    public static String QA_SSO_URL() {
        return Enums.Environment.QA.pageUrl;
    }

    public static String txt_Username() {
        return "//input[@id='username']";
    }

    public static String txt_Password() {
        return "//input[@id='password']";
    }

    public static String btn_LogIn() {
        return "//button[@id='login']";
    }

    public static String SSO_LogIn_Page() {
        return "//div[@class='divnotNewRegisteredUser ']";
    }

    public static String loggedIn_Username() {
        return "(//div[@class='om-ums-UserMenu-header om-ums-UserMenu-btn']//span)[3]";
    }

    public static String userProfile_dropDown() {
        return "(//div[@class='headerRight']//div[@class='om-ums-UserMenu-header om-ums-UserMenu-btn']//span//span)[2]";
    }

    public static String txt_logAgain() {
        return "//div[@class='loggedout form-shadowed form-thin form-register']//a[text()='Log in again']";
    }

    public static String btn_LogOut() {
        return "//span[text()='Logout']";
    }

    public static String campaignName() {
        return "(//div[@id='rc-tabs-0-panel-1']//table//div[@class='fullName'])[1]";
    }

    public static String workbenchSidebar() {
        return "//li[@title='Workbench']";
    }

    public static String campaignsSidebar() {
        return "//li[@title='Campaigns']";
    }

    public static String calendarSidebar() {
        return "//li[@title='Calendar']";
    }

    public static String communicationSidebar() {
        return "//li[@title='Communications']";
    }

    public static String campaignsList_Header() {
        return "//span[text()='Campaign List']";
    }


    public static String CreateNew_btn() {
        return "//span[contains(text(),'CREATE NEW')]";
    }

    public static String campaignName_txt() {
        return "//input[@class='omd-input omd-input-status-success campaign-info-custom-input']";
    }

    public static String campaignName_txtbx() {
        return "//input[@id='campaignName']";
    }

    public static String campaignName_drpdwn() {
        return "(//label[@for='campaignName']//..//div)[1]";
    }

    public static String Category_drpdwn() {
        return "(//label[@for='category']//..//div)[1]";
    }

    public static String startDate_drpdwn() {
        return "(//label[@for='startDate']//..//div)[1]";
    }

    public static String endDate_drpdwn() {
        return "(//label[@for='endDate']//..//div)[1]";
    }

    public static String Product_drpdwn() {
        return "(//label[@for='product']//..//div)[1]";
    }

    public static String Priority_drpdwn() {
        return "(//label[@for='priority']//..//div)[1]";
    }

    public static String AllocationGroup_drpdwn() {
        return "(//label[@for='orgCode']//..//div)[1]";
    }

    public static String Option(String data) {
        return "//div[text()='" + data + "']";
    }

    public static String categoryOption(String data) {
        return "//div[@id='category_list']//..//div[text()='" + data + "']";
    }

    public static String campaignName_Option(String data) {
        return "//div[@id='campaignName_list']//..//div[text()='" + data + "']";
    }

    public static String Option_Arrow(String data) {
        return "//div[text()='" + data + "']//..//div[@class='omd-cascader-menu-item-expand-icon']";
    }

    public static String Option_chkbx(String data) {
        return "//div[text()='" + data + "']//..//span[@class='omd-cascader-checkbox']";
    }

    public static String additionalGroup_txt() {
        return "//input[@id='campaignDesc']";
    }

    public static String next_btn() {
        return "(//span[text()='NEXT']//..//..//button)[1]";
    }

    public static String next_btn2() {
        return "(//span[text()='NEXT']//..//..//button)[2]";
    }

    public static String next_btn3() {
        return "(//span[text()='NEXT']//..//..//button)[3]";
    }

    public static String marketingMaterialNext_btn() {
        return "//div[text()='Marketing Material']//..//..//span[text()='NEXT']";
    }

    public static String confirm_btn() {
        return "(//span[text()='CONFIRM']//..//..//button)[2]";
    }

    public static String marketingMaterial_Header() {
        return "//div[@class='campaign-info campaignStepShow']//div[text()='Marketing Material']";
    }

    public static String uploadedFile() {
        return "(//div[@class='omd-upload-list-item-name omd-upload-hasFileSize']//span)[1]";
    }

    public static String confirmUploadedFile() {
        return "(//div[@class='campaign-comfrim campaignStepShow']//div[text()='Marketing Material']//..//span)[1]";
    }

    public static String expected_AllocationGroup() {
        return "(//div[text()='Allocation Group']//..//div)[2]";
    }

    public static String expected_AdditionalInformation() {
        return "(//div[text()='Additional Information']//..//div)[2]";
    }

    public static String expected_Priority() {
        return "(//div[text()='Priority']//..//div)[2]";
    }

    public static String expected_CampaignName() {
        return "(//div[text()='Campaign Name']//..//div)[2]";
    }

    public static String expected_Product() {
        return "(//div[text()='Product']//..//div)[2]";
    }

    public static String expected_Category() {
        return "(//div[text()='Category']//..//div)[2]";
    }

    public static String expected_StartDate() {
        return "(//div[text()='Start Date']//..//div)[2]";
    }

    public static String expected_EndDate() {
        return "(//div[text()='End Date']//..//div)[2]";
    }

    public static String CampaignStatus(String data) {
        return "(//div[text()='" + data + "']//..//span)[1]";
    }

    public static String CampaignStatus2(String data) {
        return "(//div[text()='" + data + "']//..//span)[2]";
    }

    public static String CampaignAdviserStatus(String data) {
        return "(//div[text()='" + data + "']//..//span)[1]";
    }

    public static String addTargetCustomer() {
        return "//span[text()='ADD TARGET CUSTOMER']//..//..//button";
    }

    public static String uploadTargetCustomer_btn() {
        return "(//span[text()='UPLOAD TARGET CUSTOMER']//..)[1]";
    }

    public static String bulkUpload_Header() {
        return "//div[text()='Bulk Upload']";
    }

    public static String bulkUpload_btn() {
        return "(//input[@id='templateMaterials']//..//div)[1]";
    }

    public static String uploadFile_xpath() {
        return "(//div[text()='Upload Files']//..//..//..//div)[1]";
    }

    public static String campaignLeadsType_drpdwn() {
        return "//input[@id='campaignLeadsType']//..";
    }

    public static String expected_SuccessfulMessage() {
        return "(//div[@class='omd-message-custom-content omd-message-success']//div//div)[1]";
    }

    public static String submittedSuccessfully() {
        return "(//div[@class='omd-message-custom-content omd-message-success']//span)[2]";
    }

    public static String submit_btn() {
        return "(//span[text()='SUBMIT']//..//..//button)[2]";
    }

    public static String submit_btn1() {
        return "(//span[text()='SUBMIT']//..//..//button)[1]";
    }

    public static String targetCustomer_tab() {
        return "(//div[text()='Target Customer']//..)[1]";
    }

    public static String targetCustomer_tab2() {
        return "(//span[text()='Target Customer']//..//..//..)[1]";
    }

    public static String targetAdviserCustomer_tab() {
        return "(//div[text()='Target Customer']//..)[1]";
    }

    public static String submitStatus_window() {
        return "//div[text()='Submit Status']";
    }

    public static String submitStatuMessage() {
        return "(//div[text()='Submit Status']//..//..//div//div)[4]";
    }

    public static String Done_btn() {
        return "//span[text()='DONE']//..//..//button";
    }

    public static String searchBar_btn() {
        return "(//div[@class='search']//span)[1]";
    }

    public static String closedLeadListSearchBar_btn() {
        return "(//div[@class='closedLeadsList']//div[@class='search']//span)[1]";
    }

    public static String myReferralsSearchBar_btn() {
        return "(//div[@class='myReferrals']//div[@class='search']//span)[1]";
    }

    public static String searchBar_txt() {
        return "//div[@class='search']//input";
    }

    public static String closedLeadListSearchBar_txt() {
        return "(//div[@class='closedLeadsList']//div[@class='search']//input)[1]";
    }

    public static String myReferralsSearchBar_txt() {
        return "(//div[@class='myReferrals']//div[@class='search']//input)[1]";
    }

    public static String confirmCustomerList_popup() {
        return "//div[@class='omd-modal-confirm-body']";
    }

    public static String closeTheLead_popup() {
        return "//div[text()='Close The Lead']";
    }

    public static String referTheLead_popup() {
        return "//div[text()='Refer Lead']";
    }

    public static String confirmCustomerList_popup_option(String data) {
        return "//div[@class='omd-modal-confirm-body']//..//span[text()='" + data + "']";
    }

    public static String campaignName_Filter_drpdwn() {
        return "(//div[text()='CAMPAIGN NAME']//..//..//..//..//div)[1]";
    }

    public static String categorization_Filter_drpdwn() {
        return "(//div[text()='CATEGORIZATION']//..//..//..//..//div)[1]";
    }

    public static String startDateEndDate_Filter_drpdwn() {
        return "(//div[text()='START DATE - END DATE']//..//..//..//..//div)[1]";
    }

    public static String priority_Filter_drpdwn() {
        return "(//div[text()='PRIORITY']//..//..//..//..//div)[1]";
    }

    public static String dateCreated_Filter_drpdwn() {
        return "(//div[text()='DATE CREATED']//..//..//..//..//div)[1]";
    }

    public static String creator_Filter_drpdwn() {
        return "(//div[text()='CREATOR']//..//..//..//..//div)[1]";
    }

    public static String status_Filter_drpdwn() {
        return "(//div[text()='STATUS']//..//..//..//..//div)[1]";
    }

    public static String campaignFilter_btn() {
        return "(//div[@class='leadsTablePage']//span[@class='anticon filterIcon cursor'])[1]";
    }

    public static String filter_btn() {
        return "(//span[@class='anticon filterIcon cursor']//..//..//..//div)[1]";
    }

    public static String closeFilter_btn() {
        return "//div[@class='tableTool-filter-content-header-close']";
    }

    public static String FilterOption_chckbx(String data) {
        return "//span[text()='" + data + "']//..//span[@class='omd-checkbox']";
    }

    public static String applyFilter_btn() {
        return "(//span[text()='APPLY']//..//..//button[@type='button'])[1]";
    }

    public static String clear_btn() {
        return "(//span[text()='CLEAR']//..//..//button[@type='button'])[2]";
    }

    public static String actionTrigger_btn() {
        return "(//table//span[@class='anticon omd-dropdown-trigger moreIcon'])[1]";
    }

    public static String actionFirstTrigger_btn() {
        return "(//table//span[@class='anticon'])[1]";
    }

    public static String actionOption(String data) {
        return "(//span[text()='" + data + "'])[1]";
    }

    public static String actionOption1(String data) {
        return "(//span[text()='" + data + "'])[1]";
    }

    public static String actionOption3(String data) {
        return "(//span[text()='" + data + "'])[2]";
    }

    public static String archived_Tab() {
        return "(//div[text()='Archived'])[1]";
    }

    public static String archivedPage() {
        return "//span[text()='Archived']";
    }

    public static String tableStatus() {
        return "//span[text()='Archived']//..//..//..//..//div[@class='omd-table-content']//..//tr[2]//td[10]";
    }

    public static String campaignListStatus(String data) {
        return "(//div[text()='" + data + "']//..//..//..//td[10])[1]";
    }

    public static String campaignArchiveMessage() {
        return "(//div[@class='omd-message-custom-content omd-message-success']//..//span)[2]";
    }

    public static String archivedStatus_Column() {
        return "//div[@id='rc-tabs-0-panel-2']//th[text()='Status']";
    }

    public static String campaignListStatus_Column() {
        return "//span[text()='Campaign List']//..//..//..//thead[@class='omd-table-thead']//th[text()='Status']";
    }

    public static String archivedTable() {
        return "document.querySelector(\".omd-table.omd-table-ping-right.omd-table-fixed-column.omd-table-scroll-horizontal.omd-table-has-fix-right\")";
    }

    public static String campaignCode() {
        return "(//div[@id='rc-tabs-0-panel-1']//td)[13]";
    }

    public static String reviveCampaign_Page() {
        return "//div[text()='Revive Campaign']";
    }

    public static String editCampaign_Page() {
        return "//div[text()='Edit Campaign']";
    }

    public static String newCampaignCode() {
        return "(//div[text()='Campaign code']//..//div)[2]";
    }

    public static String mandatory_rdbtn(String data) {
        return "(//div[@id='mandatory']//span[text()='" + data + "']//..//span[1])[1]";
    }

    public static String customerName_chckbx(int i) {
        return "(//input[@class='omd-checkbox-input']//..)[" + i + "]";
    }

    public static String confirmSelectedCustomers_btn() {
        return "(//div[text()='My Customer List']//..//div//button)[2]";
    }

    public static String customerName_txt(int i) {
        return "(//input[@class='omd-checkbox-input']//..//..//..//..//td[2])[" + i + "]";
    }

    public static String customerName_txt() {
        return "(//div[@class='customerList ']//tr[2]//td)[1]";
    }

    public static String targetCustomer_CustomerName(int i) {
        return "(//tbody[@class='omd-table-tbody']//tr[" + i + "]//td[1]//div)[1]";
    }

    public static String totalEllipsis() {
        return "//div[@title='Total']//..//div[@class='total ellipsis']";
    }

    public static String Overview_tab() {
        return "(//div[text()='Overview']//..)[1]";
    }

    public static String totalOpenEllipsis() {
        return "//div[@title='Open']//..//div[@class='total ellipsis']";
    }

    public static String campaignListHeader() {
        return "//div[@class='campaign-list-page-header']";
    }

    public static String Customers_SideBar() {
        return "//li[@title='Customers']";
    }

    public static String CustomerList_Page() {
        return "(//div[text()='Customers'])[2]";
    }

    public static String confirmOpportunity_msg() {
        return "(//div[@class='omd-message-custom-content omd-message-success']//div)[2]";
    }

    public static String Leads_SideBar() {
        return "//li[@title='Leads']";
    }

    public static String LeadList_Page() {
        return "//div[@class='leadsListPage']//div[text()='Leads']";
    }

    public static String myLeadsListStatus(String customerName) {
        return "(//div[text()='" + customerName + "']//..//..//..//..//div[@class='statusCell']//span)[1]";
    }

    public static String myLeadsCreate_btn() {
        return "(//span[text()='CREATE NEW']//..//..//button[@type='button'])[2]";
    }

    public static String myLeadsListTable() {
        return "//div[@class='myLeads']//..//..//div[@class='omd-table-container']";
    }

    public static String CustomerName() {
        return "(//div[@class='nameCell']//div[2])[1]";
    }

    public static String createNewOpportunity_page() {
        return "(//div[text()='Create New'])[1]";
    }

    public static String Title_dropdown() {
        return "(//input[@id='title']//..//..//..//..//..//..//..//div)[1]";
    }

    public static String firstName_txt() {
        return "//input[@id='firstName']";
    }

    public static String lastName_txt() {
        return "//input[@id='lastName']";
    }

    public static String identityNumber_txt() {
        return "//input[@id='identityNumber']";
    }

    public static String dateOfBirth_datePicker() {
        return "//input[@id='dateOfBirth']//..//..//..//div[@class='omd-picker']";
    }

    public static String contactNumber_txt() {
        return "//input[@id='phoneList_0_number']";
    }

    public static String numberType_dropdown() {
        return "(//input[@id='phoneList_0_numberType']//..//..//..//..//..//..//..//div)[1]";
    }

    public static String countryCode_dropdown() {
        return "(//input[@id='phoneList_0_countryCode']//..//..//..//..//..//..//..//div)[1]";
    }

    public static String workSite_dropdown() {
        return "(//input[@id='applicationScope']//..//..//..//..//..//..//div)[1]";
    }

    public static String declationToShareInfor_rdbtn() {
        return "(//input[@class='omd-radio-input']//..//..//span)[1]";
    }

    public static String workSite_option(String workSite) {
        return "//div[@id='applicationScope_list']//..//div[text()='" + workSite + "']";
    }

    public static String Title_option(String Title) {
        return "//div[@class='rc-virtual-list']//div[text()='" + Title + "']";
    }

    public static String numberType_option(String numberType) {
        return "//div[@parentname='phoneList']//div[text()='" + numberType + "']";
    }

    public static String description_txt() {
        return "//textarea[@id='notes']";
    }

    public static String closeTheLead_Reason_drpdwn() {
        return "(//input[@id='themeCode']//..//..//..//..//..//div)[1]";
    }

    public static String closeTheLead_Reason_option(String reason) {
        return "//div[@id='themeCode_list']//..//div[text()='" + reason + "']";
    }

    public static String closeTheLead_Description_drpdwn() {
        return "(//input[@id='subThemeCode']//..//..//..//..//..//div)[1]";
    }

    public static String closeTheLead_Description_option(String description) {
        return "//div[@id='subThemeCode_list']//..//div[text()='" + description + "']";
    }

    public static String closeTheLead_Description_txt() {
        return "//input[@id='otherReason']";
    }

    public static String Closed_Tab() {
        return "(//div[@role='tab']//..//div[text()='Closed']//..)[1]";
    }

    public static String closedCustomerName() {
        return "(//div[text()='Closed']//..//..//..//div[@class='nameCell']//..//div[2])[1]";
    }

    public static String myReferralsCustomerName() {
        return "(//div[text()='My Referrals List']//..//div[@class='nameCell']//..//div[2])[1]";
    }

    public static String closeReason() {
        return "(//div[text()='Closed']//..//..//..//div[@class='fullName']//..//..//..//span)[3]";
    }

    public static String customerStatus() {
        return "(//div[text()='Closed']//..//..//..//div[@class='fullName']//..//..//..//span)[2]";
    }

    public static String closeReason2(String customerName) {
        return "(//div[text()='Closed']//..//..//..//div[text()='" + customerName + "']//..//..//..//..//div[@class='statusCell']//..//..//td[10]//div)[1]";
    }

    public static String customerStatus2(String customerName) {
        return "(//div[text()='Closed']//..//..//..//div[text()='" + customerName + "']//..//..//..//..//div[@class='statusCell']//span)[1]";
    }

    public static String referType_option_rdbtn(String referType) {
        return "(//span[text()='" + referType + "']//..//span)[1]";
    }

    public static String chooseAnAdviser_placeholder() {
        return "//label[@for='referTo']";
    }

    public static String referTo_drpdwn() {
        return "(//label[@for='referObject']//..//..//div)[1]";
    }

    public static String referTo_option(String referTo) {
        return "//div[@id='referObject_list']//..//div[text()='" + referTo + "']";
    }

    public static String referLeadChoose_drpdwn() {
        return "(//label[@for='referTo']//..//..//div)[1]";
    }

    public static String referLeadChoose_option(String adviser) {
        return "//div[@id='referTo_list']//..//div[text()='" + adviser + "']";
    }

    public static String myReferrals_Tab() {
        return "(//div[@role='tab']//..//div[text()='My Referrals']//..)[1]";
    }

    public static String referralType() {
        return "(//div[text()='My Referrals List']//..//tr[@class='omd-table-row omd-table-row-level-0']//td)[2]";
    }

    public static String referTo() {
        return "(//div[text()='My Referrals List']//..//tr[@class='omd-table-row omd-table-row-level-0']//td)[3]";
    }

    public static String myReferralsStatus() {
        return "(//div[text()='My Referrals List']//..//tr[@class='omd-table-row omd-table-row-level-0']//td)[6]";
    }

    public static String myLeadsListTable_Option(String pipeLineType) {
        return "(//div[@class='pipelineType']//span[text()='" + pipeLineType + "']//..)[1]";
    }

    public static String callLog_popup() {
        return "//div[text()='Call Log']";
    }

    public static String addCallNote() {
        return "//textarea[@id='callLogNote']";
    }

    public static String leadSource_drpdwn() {
        return "//div[text()='LEAD SOURCE']//..//..//..//..//div[@class='omd-collapse-header']";
    }

    public static String leadStatus_drpdwn() {
        return "//div[text()='LEAD STATUS']//..//..//..//..//div[@class='omd-collapse-header']";
    }

    public static String overviewHeader() {
        return "(//div[@class='overViewHeaderLeft'])[1]";
    }

    public static String changeStatus_popup() {
        return "(//div[text()='Change Status'])[1]";
    }

    public static String changeStatus_drpdwn() {
        return "(//input[@id='toModifyStatus']//..//..//..//..//..//..//..//div)[1]";
    }

    public static String changeStatus_option(String status) {
        return "//div[text()='" + status + "']";
    }

    public static String changeStatus_option2(String status) {
        return "(//div[text()='" + status + "'])[2]";
    }

    public static String statusTile(String statusTile) {
        return "(//div[text()='" + statusTile + "']//..)[1]";
    }

    public static String toBeActionedByMe() {
        return "//div[@class='myLeads']//div[text()='To Be Actioned By Me']";
    }

    public static String leadsInProgress_tab() {
        return "(//div[text()='Leads In Progress']//..)[1]";
    }

    public static String allocateTo_option(String allocateTo) {
        return "//div[text()='" + allocateTo + "']";
    }

    public static String allocateTo_drpdwn() {
        return "(//input[@id='agentId']//..//..//..//..//..//..//div)[1]";
    }

    public static String leadAllocate_popup() {
        return "//div[@class='omd-modal-title']//..//div[text()='Lead Allocate']";
    }

    public static String leadReAssign_popup() {
        return "//div[@class='omd-modal-title']//..//div[text()='Lead Re-assign']";
    }

    public static String adviser_chckbx(String adviser) {
        return "(//span[text()='" + adviser + "']//..//span[@class='omd-checkbox'])[1]";
    }

    public static String leadAllocateWorksite(String worksiteTeam) {
        return "(//span[text()='" + worksiteTeam + "']//..//..//..//div)[1]";
    }

    public static String closedMyLeadsSearch_btn() {
        return "(//div[@class='myLeads']//div[@class='search']//span)[1]";
    }

    public static String closedMyLeadsSearchBar_txt() {
        return "(//div[@class='myLeads']//div[@class='search']//input)[1]";
    }

    public static String Opportunity_SideBar() {
        return "//li[@title='Opportunities']";
    }

    public static String myOpportunityList_Page() {
        return "//span[text()='My Opportunity List']";
    }

    public static String closedOpportunitySearch_btn() {
        return "(//div[@class='closedLeadsList']//div[@class='search']//span)[1]";
    }

    public static String closedOpportunitySearchBar_txt() {
        return "(//div[@class='closedLeadsList']//div[@class='search']//input)[1]";
    }

    public static String customerNeeds_drpdwn() {
        return "(//label[text()='Customer Needs']//..//..//div)[1]";
    }

    public static String customerNeeds_option(String customerNeeds) {
        return "//div[@title='" + customerNeeds + "']";
    }

    public static String leadReAssignedAdviser_radiobtn(String adviser) {
        return "(//span[text()='" + adviser + "']//..//span[@class='omd-radio'])[1]";
    }

    public static String leadReAssignWorksite(String worksiteTeam) {
        return "(//div[text()='Worksite/Team']//..//span[text()='" + worksiteTeam + "']//..)[1]";
    }

    public static String opportunitiesInProgress_tab() {
        return "(//div[@role='tab']//..//div[text()='Opportunities In Progress']//..)[1]";
    }

    public static String previewInsights_page() {
        return "//div[text()='Preview Insights']";
    }

    public static String previewInsightsDownload_btn() {
        return "//div[text()='Preview Insights']//..//..//button";
    }

    public static String previewInsights_pdf(String combinedNames) {
        return "//span[text()='" + combinedNames + "']";
    }

    public static String previewInsights_page_X() {
        return "//span[@class='anticon omd-modal-close-icon']";
    }

    public static String engagementPreference_drpdwn() {
        return "(//label[@for='engagementType']//..//div)[1]";
    }

    public static String reAllocateLeads_rdbtn(String reAllocateLeads) {
        return "(//div[@id='reAllocation']//span[text()='" + reAllocateLeads + "']//..//span[1])[1]";
    }

    public static String searchBar_closeBtn() {
        return "//span[@class='anticon anticon-close']";
    }

    public static String header() {
        return "//div[@class='tableTool-content-main']";
    }

    public static String toBeActionedByMe_Tab() {
        return "(//div[@class='omd-tabs-nav-list']//div[text()='To Be Actioned By Me']//..)[1]";
    }

    public static String myLeads_Tab() {
        return "(//div[text()='My Leads']//..//..//div[@class='omd-tabs-tab'])[1]";
    }

    public static String countryCode_option(String countryCode) {
        return "//div[@id='phoneList_0_countryCode_list']//..//div[text()='" + countryCode + "']";
    }

    public static String actionEdit_btn() {
        return "(//table//span[@class='anticon'])[1]";
    }

    public static String actionClose_btn() {
        return "(//table//span[@class='anticon'])[2]";
    }

    public static String actionMore_btn() {
        return "(//table//span[@class='anticon'])[3]";
    }

    public static String actionPreviewInsights_btn() {
        return "(//table//span[@class='anticon'])[1]";
    }

    public static String actionDownloadInsights_btn() {
        return "(//table//span[@class='anticon'])[2]";
    }

    public static String actionCreateOpportunityFromCustomerList_btn() {
        return "(//table//span[@class='anticon'])[3]";
    }

    public static String loadingMask() {
        return "//div[text()='Loading...']";
    }

    public static String myCalender() {
        return "//div[text()='My Calendar']";
    }

    public static String myLeads() {
        return "//div[text()='My Leads']";
    }

    public static String myOpportunities() {
        return "//div[text()='My Opportunities']";
    }

    public static String myTeamOpportunities() {
        return "//div[text()='My Team Opportunities']";
    }

    public static String myTeamLeads() {
        return "//div[text()='My Team Leads']";
    }

    public static String myCustomers() {
        return "//div[text()='My Customers']";
    }

    public static String myCampaigns() {
        return "//div[text()='My Campaigns']";
    }

    public static String myTeam() {
        return "//div[text()='My Team']";
    }

    public static String salesAndServicing() {
        return "//div[text()='Sales & Servicing']";
    }

    public static String totalUpcoming() {
        return "(//div[@class='statisticsBox']//div[@class='title ellipsis'])[2]";
    }

    public static String customersWithOpenLeads() {
        return "(//div[@class='statisticsBox']//div[@class='title ellipsis'])[4]";
    }

    public static String totalCustomers() {
        return "(//div[@class='statisticsBox']//div[@class='title ellipsis'])[3]";
    }

    public static String activeCampaigns() {
        return "(//div[@class='statisticsBox']//div[@class='title ellipsis'])[5]";
    }

    public static String totalOpenLeads() {
        return "(//div[@class='statisticesReport-overViewCard-title'])[1]";
    }

    public static String totalOpenOpportunities() {
        return "(//div[@class='statisticesReport-overViewCard-title'])[3]";
    }

    public static String salesSubmitted() {
        return "(//div[@class='statisReport-overViewCard-title'])[1]";
    }

    public static String totalAdvisers() {
        return "//div[text()='My Team']//..//..//..//div[@class='title ellipsis']";
    }

    public static String myTeamCampaigns() {
        return "//div[text()='My Team Campaigns']";
    }

    public static String totalOpenCampaignLeads() {
        return "(//div[text()='Campaign Leads']//..//..//..//div[@class='title ellipsis'])[1]";
    }

    public static String campaignLeads() {
        return "//div[text()='Campaign Leads']";
    }

    public static String activeMyTeamCampaigns() {
        return "(//div[text()='My Team Campaigns']//..//..//..//div[@class='title ellipsis'])[1]";
    }

    public static String activeMyCampaigns() {
        return "(//div[text()='My Campaigns']//..//..//..//div[@class='title ellipsis'])[1]";
    }

    public static String S_T_ClaimsSidebar() {
        return "//li[@title='S & T Claims']";
    }

    public static String S_T_ClaimsHeader() {
        return "//h3[text()='S & T Claims']";
    }

    public static String adviserName() {
        return "//tbody[@class='omd-table-tbody']//tr[3]//td[2]";
    }

    public static String waitingStatus() {
        return "//tbody[@class='omd-table-tbody']//tr[3]//td[7]";
    }

    public static String approvalStatus() {
        return "//tbody[@class='omd-table-tbody']//tr[3]//td[8]";
    }

    public static String waitingView_btn() {
        return "//tbody[@class='omd-table-tbody']//tr[3]//td//button";
    }

    public static String approve_btn() {
        return "(//tbody[@class='omd-table-tbody']//tr[3]//td//button)[1]";
    }

    public static String return_btn() {
        return "(//tbody[@class='omd-table-tbody']//tr[3]//td//button)[2]";
    }

    public static String approveClaim_btn() {
        return "(//span[text()='APPROVE CLAIM']//..)[1]";
    }

    public static String updateClaim_btn() {
        return "(//span[text()='UPDATE CLAIM']//..)[1]";
    }

    public static String saveClaim_btn() {
        return "(//span[text()='SAVE CLAIM']//..)[1]";
    }

    public static String declaration_popup() {
        return "//div[text()='Declaration']//..//..//..//div[@class='omd-modal-content']";
    }

    public static String declaration_chckbx() {
        return "//div[text()='Declaration']//..//..//..//span[@class='omd-checkbox']";
    }

    public static String declaration_OK_btn() {
        return "(//div[@class='omd-modal-footer']//span[text()='OK']//..)[1]";
    }

    public static String waitingApproval_Tab() {
        return "(//div[text()='Awaiting Approval']//..)[1]";
    }

    public static String returnToAdviser() {
        return "//div[text()='Return to Adviser']";
    }

    public static String reasonToReturn_drpdwn() {
        //return "//input[@id='reasonCode']";
        return "(//input[@id='reasonCode']//..//..//..//div)[1]";
    }

    public static String reasonToReturn_option(String reasonForReturning) {
        return "//div[text()='" + reasonForReturning + "']";
    }

    public static String returnToAdviser_Save_btn() {
        return "(//span[text()='SAVE']//..)[1]";
    }

    public static String attendance_Tab() {
        return "(//div[text()='Attendance']//..)[1]";
    }

    public static String attendance_Header() {
        return "(//span[text()='Confirm Attendance For The Meetings In The Past'])[1]";
    }

    public static String appointmentStatus() {
        return "//div[@class='appointmentCell']";
    }

    public static String actionChangeStatus_btn() {
        return "//div[@class='component-table-action-moreVertIcon']";
    }

    public static String actionSendTo_btn() {
        return "(//span[text()='Send To'])[1]";
    }

    public static String doClaims() {
        return "//label[text()='Do you want to claim S & T for this meeting?']";
    }

    public static String doClaims_option(String data) {
        return "(//label[text()='Do you want to claim S & T for this meeting?']//..//span[text()='" + data + "']//..//span)[1]";
    }

    public static String appointmentStatus_drpdwn() {
        return "(//input[@id='basic_appointmentStatus']//..//..//..//..//..//..//..//div)[1]";
    }

    public static String MyTeamSidebar() {
        return "(//li[@title='My Team'])[1]";
    }

    public static String MyTeamHeader() {
        return "(//div[text()='My Team List'])[1]";
    }

    public static String myTeamAdviserName() {
        return "//tbody[@class='omd-table-tbody']//tr[2]//td[1]//div";
    }

    public static String Adviser_PhoneNo() {
        return "//tbody[@class='omd-table-tbody']//tr[2]//td[3]";
    }

    public static String Adviser_Email() {
        return "//tbody[@class='omd-table-tbody']//tr[2]//td[4]";
    }

    public static String Adviser_Email2() {
        return "//tbody[@class='omd-table-tbody']//tr[2]//td[5]";
    }

    public static String Adviser_Staffcode() {
        return "//tbody[@class='omd-table-tbody']//tr[2]//td[5]";
    }

    public static String expected_AdviserName() {
        return "//div[@class='myTeam-detail-contentTop-userInfo-userName ellipsis']";
    }

    public static String expected_PhoneNo() {
        return "(//div[text()='Phone No.']//..//..//div[2])[1]";
        //return "//div[@class='OMTooltip-cls-ellipsis-more OMTooltip-cls-ellipsis1 list-item-bottom']";
    }

    public static String expected_PhoneNo2() {
        return "(//div[text()='Phone No.']//..//..//div[1]//span)[1]";
        //return "//div[@class='OMTooltip-cls-ellipsis-more OMTooltip-cls-ellipsis1 list-item-bottom']";
    }

    public static String expected_Email() {
        return "(//div[text()='Email']//..//span)[1]";
    }

    public static String expected_Staffcode() {
        return "(//div[text()='Personal Information']//..//..//..//..//span[@class='account-content-Female-gender'])[2]";
    }

    public static String weekClaimsContent() {
        return "//div[@class='claims-week-cont']";
    }

    public static String claim_drpdwn() {
        return "(//span[@class='anticon panel-icon'])[1]";
    }

    public static String StartOdometer_txt() {
        return "(//input[@id='openingOdoMeter'])[1]";
    }

    public static String EndOdometer_txt() {
        return "(//input[@id='closingOdoMeter'])[1]";
    }

    public static String totalClaimAmount_popup() {
        return "(//span[@class='amount'])[1]";
    }

    public static String totalOdometerToClaim_xpath() {
        return "(//td)[29]//div";
    }

    public static String ST_RateApplication_xpath() {
        return "(//div[@class='expanse-area']//span[@class='numb'])[1]";
    }

    public static String doneClaim_btn() {
        return "(//span[normalize-space()='DONE'])[1]";
    }

    public static String doneClaimMassage_btn() {
        return "(//span[normalize-space()='DONE'])[2]";
    }

    public static String adviserDeclaration_chckbx() {
        return "//div[@class='claims-week-cont-footer']//span[@class='omd-checkbox']";
    }

    public static String submitClaimForApproval_btn() {
        return "(//span[normalize-space()='SUBMIT CLAIM FOR APPROVAL']//..)[1]";
    }

    public static String claimSuccessfullyMessage() {
        return "//p[normalize-space()='Claim has been submitted Successfully']";
    }

    public static String AddNew_btn() {
        return "(//span[normalize-space()='ADD NEW']//..)[1]";
    }

    public static String Event_btn() {
        return "//span[@class='menuItemText']";
    }

    public static String eventHeader() {
        return "//div[text()='New Event']";
    }

    public static String LinkToAnOpportunity_Customer_drpdwn() {
        return "//input[@id='basic_client']//..";
    }

    public static String opportunityCustomer_rdbtn() {
        return "(//ul[@class='omd-list-items']//..//span[1])[1]";
    }

    public static String save_btn() {
        return "(//span[text()='SAVE']//..//..//button)[2]";
    }

    public static String save_btn1() {
        return "(//span[text()='SAVE']//..//..//button)[1]";
    }

    public static String distanceTravelled() {
        return "(//input[@id='closingOdoMeter']//..//..//..//..//..//..//..//div)[13]";
    }

    public static String history_Tab() {
        return "(//div[text()='History']//..)[1]";
    }

    public static String S_T_ClaimsHistoryHeader() {
        return "//div[@class='histroryClaimsList-pageTitle']//span[text()='S & T Claims']";
    }

    public static String viewDetails_btn() {
        return "(//tbody[@class='omd-table-tbody']//tr[2]//td//button)[1]";
    }

    public static String downloadDetails_btn() {
        return "(//tbody[@class='omd-table-tbody']//tr[2]//td//button)[2]";
    }

    public static String salesWeek() {
        return "(//span[@class='salesWeek'])[1]";
    }

    public static String expected_SalesWeek() {
        return "(//p[@class='cont-name'])[1]";
    }

    public static String claimDetailsDownload_btn() {
        return "//div[@class='page-top-header-breadcrumb']//button";
    }

    public static String claimReturned_Tab() {
        return "(//div[text()='Claim Returned ']//..)[1]";
    }

    public static String teamPerformanceHeader() {
        return "//div[@class='teamPerformanceTabTitle']";
    }

    public static String teamPerformance() {
        return "//li[@title='Team Performance']";
    }

    public static String myPerformance() {
        return "//li[@title='My Performance']";
    }

    public static String CAL_List() {
        return "//li[@title='CAL List']";
    }

    public static String performanceSidebar_drpdwn() {
        return "(//div[@class='om-ums-menu-submenu-title']//span[text()='Performance']//..//..//div)[1]";
    }

    public static String customersSidebar_drpdwn() {
        return "(//div[@class='om-ums-menu-submenu-title']//span[text()='Customers']//..//..//div)[1]";
    }

    public static String burgerMenu() {
        return "(//div[@class='om-ums-menu-collapse om-ums-menu-collapse-fold'])[1]";
    }

    public static String myPerformanceOverview() {
        return "//div[text()='My Performance Overview']";
    }

    public static String myPerformanceToDoList() {
        return "//div[text()='My Performance To Do List']";
    }

    public static String performanceList() {
        return "//div[text()='Performance List']";
    }

    public static String addTeamPerformance_btn() {
        return "//button[@type='button']//span[text()='ADD TEAM PERFORMANCE']";
    }

    public static String addTeamPerformanceGoal() {
        return "//div[@class='omd-card-head-wrapper']//div[text()='Add Team Performance Goal']";
    }

    public static String teamPerformanceGoalName_txt() {
        return "//input[@id='control-hooks_goalName']";
    }

    public static String belongsToItem_drpdwn() {
        return "//input[@id='control-hooks_itemId']//..//..//..//div";
    }

    public static String belongsToItem_option(String data) {
        return "//span[text()='" + data + "']";
    }

    public static String belongsToItem_option_arrow(String data) {
        return "(//span[text()='" + data + "']//..//..//span[@class='anticon anticon-caret-down omd-select-tree-switcher-icon']//..)[1]";
    }

    public static String effective_startDate_drpdwn() {
        return "(//input[@placeholder='Start date']//..)[1]";
    }

    public static String effective_endDate_drpdwn() {
        return "(//input[@placeholder='End date']//..)[1]";
    }

    public static String evaluationCycle_rdbtn(String evaluationCycle) {
        return "(//span[text()='" + evaluationCycle + "']//..//span)[1]";
    }

    public static String evaluationCycleDescription_txt() {
        return "//input[@id='control-hooks_goalDescription']";
    }

    public static String selectAll_chckbx() {
        return "//input[@class='omd-checkbox-input']";
    }

    public static String addMembersCard() {
        return "//div[@class='omd-card-head-wrapper']//div[text()='Add Members']";
    }

    public static String addMember_btn() {
        return "(//span[text()='ADD MEMBER']//..//..//button)[1]";
    }

    public static String confirmPage() {
        return "//div[@class='omd-card-head-wrapper']//div[text()='Confirm']";
    }

    public static String successMessage() {
        return "//div[@class='success-description']";
    }

    public static String expected_performanceGoalName() {
        return "//p[text()='Performance Goal Name']//..//p[@class='addContent']";
    }

    public static String expected_BelongsToItem() {
        return "(//p[text()='Belongs to Item']//..//p[@class='addContent'])[1]";
    }

    public static String backToTeamPerformance_btn() {
        return "//span[text()='BACK TO TEAM PERFORMANCE']//..//..//button";
    }

    public static String TeamPerformanceName(String data) {
        return "(//div[@class='OMTooltip-cls-ellipsis-more OMTooltip-cls-ellipsis1 test-goalName pointer'][normalize-space()='" + data + "'])[1]";
    }

    public static String sendToCustomer_tab() {
        return "(//div[text()='Send To Customer']//..//..//div//..//..)[1]";
    }

    public static String actionResend_btn() {
        return "(//button[@class='omd-btn omd-btn-circle omd-btn-default omd-btn-icon-only'])[1]";
    }

    public static String resendToRecipient_Card() {
        return "//div[@class='omd-modal-header']//div[text()='Resend to Recipient']";
    }

    public static String emailEdit_btn() {
        return "//a[@class='modal-resend_link']";
    }

    public static String recipientEmail() {
        return "//input[@id='recipientEmail']";
    }

    public static String Resend_btn() {
        return "(//span[text()='RESEND']//..//..//button)[2]";
    }

    public static String communicationEmailStatus() {
        return "//span[text()='EMAIL']//..//..//..//..//tbody//tr[2]//td[4]//div";
    }

    public static String emailEdit_txt() {
        return "//input[@id='recipientEmail']";
    }

    public static String templatePage() {
        return "//div[@class='template-library-grid']//p[text()='Templates']";
    }

    public static String templateLibrarySidebar() {
        return "(//li[@title='Template Library'])[1]";
    }

    public static String signatureHeader() {
        return "//div[@class='template-signature-grid']//p[text()='Signature']";
    }

    public static String signature_tab() {
        return "(//div[text()='Signature']//..//div//..)[1]";
    }

    public static String signatureCreateNew_btn() {
        return "//div[@class='template-signature-grid']//span[text()='CREATE NEW']//..//..//button";
    }

    public static String signatureName_txt() {
        return "//input[@id='signatureName']";
    }

    public static String signature_textbox() {
        return "//div[@role='textbox']";
    }

    public static String createdSignature(String signatureName) {
        return "//div[@class='signatureItem']//div[text()='" + signatureName + "']";
    }

    public static String templateLibrary_tab() {
        return "(//div[@class='omd-tabs-nav-list']//div[text()='Template Library']//..//div//..)[1]";
    }

    public static String gridToList_btn() {
        return "(//div[@class='handle-box-wrap']//span[text()='LIST']//..)[1]";
    }

    public static String actionEmail_btn() {
        return "(//th[text()='Action']//..//..//..//..//..//td//button)[2]";
    }

    public static String signatureInfo() {
        return "//div[text()='Signature Info']";
    }

    public static String templateContentSignature_drpdwn() {
        return "//span[@title='SIgnature For Emails Edited']//..//..//..//..//div[@class='omd-form-item-control-input-content']";
    }

    public static String selectSignature_popup() {
        return "//div[text()='Select Signature']";
    }

    public static String signature(String signatureName) {
        return "(//div[text()='" + signatureName + "']//..//..//..//div[@class='signatureItem'])[1]";
    }

    public static String recipientAdd_btn() {
        return "(//span[text()='ADD']//..//..//button)[1]";
    }

    public static String addLeadCustomerSearch_btn() {
        return "(//div[text()='Add Lead/Customer']//..//..//input[@placeholder='SEARCH'])[1]";
    }

    public static String customer_chckbx(String data) {
        return "//div[text()='" + data + "']//..//div[@class='emailItem']//..//span";
    }

    public static String send_btn() {
        return "(//span[text()='SEND']//..//..//button)[2]";
    }

    public static String send_btn2() {
        return "//button[@class='omd-btn omd-btn-primary']//span[contains(text(),'SEND')]";
    }

    public static String templates_tab() {
        return "(//div[text()='Templates']//..)[1]";
    }

    public static String customers_tab() {
        return "//div[text()='Customers']";
    }

    public static String actionSetUpAppointment_btn() {
        return "(//table//span[@class='anticon']//..//..//button)[2]";
    }

    public static String newEventName_txt() {
        return "//input[@id='basic_title']";
    }

    public static String manageAppointment_list() {
        return "(//span[text()='Manage Appointment']//..//..//li)[2]";
    }

    public static String manageAppointmentCardHeader() {
        return "//div[text()='Manage Appointment']";
    }

    public static String cancelAppointment() {
        return "(//div[@class='appointmentItemRight']//button[1])[1]";
    }

    public static String rescheduleAppointment() {
        return "(//div[@class='appointmentItemRight']//button[2])[1]";
    }

    public static String confirmAttendence() {
        return "(//div[@class='appointmentItemRight']//button[3])[1]";
    }

    public static String startTime_dropdown() {
        return "//input[@id='basic_startTime']";
    }

    public static String startTime_option(String hours) {
        return "(//li//div[@class='omd-picker-time-panel-cell-inner'][normalize-space()='" + hours + "'])[1]";
    }

    public static String editEventCard_popup() {
        return "//div[@class='omd-modal-header']//div[text()='Edit Event']";
    }

    public static String ok_btn() {
        return "//span[normalize-space()='OK']";
    }

    public static String appointmentSuccessMessage() {
        return "(//div[@class='omd-message-custom-content omd-message-success']//..//div[@class='omd-snackbar-content'])[1]";
    }

    public static String newAppointmentStatus() {
        return "(//span[@class='omd-badge myLead']//span)[1]";
    }

    public static String myLeadsListAppointmentStatus(String customerName) {
        return "(//div[text()='" + customerName + "']//..//..//..//..//td[12]//span//span)[1]";
    }

    public static String myLeadsListAppointmentStatus2(String customerName) {
        return "(//div[text()='" + customerName + "']//..//..//..//..//td[10]//span//span)[1]";
    }

    public static String actionAllocate_btn() {
        return "(//table//span[@class='anticon actionIcon'])[1]";
    }

    public static String actionCloseLead_btn() {
        return "(//table//span[@class='anticon actionIcon'])[1]";
    }

    public static String editRevivedCampaign_btn() {
        return "(//div[@class='campaign-detail-page-basicInfo-btnBox']//button)[2]";
    }

    public static String sendEmail_btn() {
        return "(//div[@class='campaign-detail-page-basicInfo-btnBox']//span[text()='SEND']//..)[1]";
    }

    public static String emailTemplateCard() {
        return "//div[text()='Please select an email template']";
    }

    public static String sendEmail_page() {
        return "//div[text()='Send Email']";
    }

    public static String emailTemplateOption(String emailTemplate) {
        return "(//span[text()='" + emailTemplate + "']//..//..)[1]";
    }

    public static String closeCustomerContactedFilters() {
        return "(//span[text()='Customer Contacted']//..//span)[2]";
    }

    public static String actionSendTo_option(String sendTo) {
        return "(//span[text()='" + sendTo + "']//..)[1]";
    }
}
