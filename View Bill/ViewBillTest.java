package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.ViewBill;
import java.text.ParseException;
import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AccountOverViewAction;
import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.action.Slingshot.ViewBillAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.app.functional.test.common.TestBase;

public class ViewBillTest extends TestBase{

	@Test(groups ={Slingshot,Regression,ViewBill})
	public void downloadAndVerifyBill()  {
		Report.createTestLogHeader("Search bill journey", "Verify search bill page on clicking view bill link");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");  
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile);
	  new AccountOverViewAction()
	  //.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
	  //.selectContractAccountAndVerifySearchFunctionality(userProfile)
	  //.verifyAccountDetails(smrProfile)
	  .clickAndVerifyManageAccountLink(userProfile);
		new ViewBillAction()
		/*.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()*/
		.downloadAndVerifyBill(smrProfile);
	}

	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifySearchByOption()  {
		Report.createTestLogHeader("View Bill Journey", "Verify SearchBy options in the ViewBills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifySearchOptionBill();

	}
	//TC_ViewBills_10
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyBillTableData()  {
		Report.createTestLogHeader("View Bill Journey", "Verify Bill table data in the ViewBills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyBillTable(smrProfile);

	}
	//TC_ViewBills_14
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyBackLinkInSearchBillPage()  {
		Report.createTestLogHeader("View Bill Journey", "Verify back button in the Search bills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyBackLink(smrProfile);

	}	
	//TC_ViewBills_21
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyFromToDateErrorMessage()  {
		Report.createTestLogHeader("View Bill Journey", "Verify From date To date error message in the ViewBills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyFromToDateError(smrProfile);
	}	

	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifySearchBill1()throws Exception{
		Report.createTestLogHeader("Search Bill Journey", "Verifies whether the customer able to search a bill");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");  
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile);
	
	new AccountOverViewAction()
	
	    //.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
		.selectContractAccountAndVerifySearchFunctionality(userProfile)
		//.verifyAccountDetails(smrProfile)
		//.clickAndVerifyManageAccountLink(smrProfile)
		.clickViewBillLink()
		.verifyViewBillPage()
		.verifyBackToAccountLink()
		.clickViewYourLastBill();
		     
	}

	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyPDFReport() throws ParseException{
		Report.createTestLogHeader("Search Bill Journey", "Verifies whether a PDF file downloads");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.verifyAccountDetails(smrProfile)
		.clickAndVerifyManageAccountLink(smrProfile)
		.clickViewBillLink()
		.verifyPDFFile();
	}
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyZipFile() throws ParseException{
		Report.createTestLogHeader("Search Bill Journey", "Verifies whether a Zip file downloads");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.verifyAccountDetails(smrProfile)
		.clickAndVerifyManageAccountLink(smrProfile)
		.clickViewBillLink()
		.openAndVerifyZipFile();
	}
	//TC_ViewBills_21
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void validateAccountNumberErrorMessage()  {
		Report.createTestLogHeader("View Bill Journey", "Verifies account number field error message in the ViewBills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyAccountNumberError(smrProfile);                
	}	
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyBillTableDataByMprn()  {
		Report.createTestLogHeader("View Bill Journey", "Verify Bill table data in the ViewBills page by searching Mprn");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyBillTableByMprn(smrProfile);                
	}
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyOverlayForMaxSelection()  {
		Report.createTestLogHeader("View Bill Journey", "Verify Bill table data for more than 10 bill selection and overlay content");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyBillTable(smrProfile)
		.verifyMaxNoOfBillSelection();                
	}
	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyDataSortingFields()  {
		Report.createTestLogHeader("View Bill Journey", "Verify sorting functionality in billing table");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new ViewBillAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.BgbverifyAfterLogin()
		.verifyDataSortingFields(smrProfile);         
	}

	@Test(groups ={Slingshot,Regression,ViewBill})
	public void verifyBackLinkInViewBillPage()  {
		Report.createTestLogHeader("View Bill Journey", "Verify back button in the ViewBills page");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
		new SubmitMeterReadAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(smrProfile)
		.verifyAccountDetails(smrProfile)
		.clickAndVerifyManageAccountLink(smrProfile)
		.clickViewBillLink()
		.verifyViewBillPage()
		.verifyBackToAccountLink();                
	}
	//TC_VBE_01: To verify whether the following can be filtered in "Search by" category in "View Bill" page and bills are displayed correspondingly 1. Account Number(for x years)2. Bill Number3.Supply Number(for X years)4.Meter Point Reference Number (for X years)
	//TC_VBE_02: To verify whether the statement of account page is displayed on clicking the "View your statement of Account" link in "View bill" page
	//TC_VBE_07: To verify whether the customer is able to view the latest bill(by selecting latest bill) and views meter details, readings, consumption, payments and value of bill. 'Copy' is displayed on the image and details should match SAP
	//TC_VBE_11: To verify whether the registered multi site electricity customer with more than 5 years billing history logs in to OAM.
		@Test(groups ={Slingshot,Regression,ViewBill})
		public void viewBill(){
			Report.createTestLogHeader("View Bill Journey", "View Bill Page functionalities");
			SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("BGBLessthanFiveAccounts");
			new ViewBillAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(smrProfile)
			.BgbverifyAfterLogin()
			.viewBillAuditDetailsEntry(smrProfile)
			.viewBillTimestampAuditEntry(smrProfile)
			.viewYourbillRHS()
			.EnergyMadeSimpleLink()
			.verifyBillByAccNumber(smrProfile)
			.verifyBillByMprn(smrProfile)
			.verifyBillByBillNumber(smrProfile);
			/* new SubmitMeterReadAction()
			 .verifyDataInSapIsu(smrProfile);*/
		}
		
		//TC_view_Bill _01:To verify whether the Paperless customer is able to view the latest bill in the view bill page.
		//TC_view_Bill _04:To verify whether the Paperless customer is able to view the latest bill in the view bill page.
		@Test(groups ={Slingshot,Regression,ViewBill})
		public void viewBillForPaperLessCustomer(){
			Report.createTestLogHeader("View Bill Journey", "View Bill Page functionalities");
			SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("BGBLessthanFiveAccounts");
			new ViewBillAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(smrProfile)
			.clickBillingLink(smrProfile);
		}

		//TC_view_Bill _08	Verify  all the link navigations for  both the paper less and non -paperless customers  the view bill page.
		//TC_Search _Bill _14	Verify  all the link navigations for  both the paper less and non -paperless customers  in the search bill page.

		@Test(groups ={Slingshot,Regression,ViewBill})
		public void verifyLinkNavigationsForPaperless(){
			Report.createTestLogHeader("View Bill Journey", "View Bill Page functionalities");
			SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("BGBLessthanFiveAccounts");
			new ViewBillAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(smrProfile)
			.clickBillingLink(smrProfile);
			//.verifyLinkNavigations(smrProfile);
		}
		
		
		/*View Bill New page verification on Debit customer with paybalance link - April 20th 2017 release*/
		
		@Test(groups ={Slingshot,Regression,ViewBill})
		
		public void verifynewviewbillpage_debitwithpaybalance(){
			Report.createTestLogHeader("View Bill Journey", "View Bill Page functionalities");
			SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SearchBill");
			
			new SubmitMeterReadAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(smrProfile);
		
		    new AccountOverViewAction()		
		   	.selectContractAccountAndVerifySearchFunctionalitynew(smrProfile)
			.clickViewBillLink();
		
		    new ViewBillAction()
		    .verifyviewbillpagetitlesection(smrProfile)
		    .verifyviewbillapybalancesection(smrProfile)
		    .verifyviewbilltablenew_Header()
		    .verifyviewbilltable_newcontents()
		    .verfiypagenation()
		    .verifyViewBillnew_Links(smrProfile);
		    
		}
		
}
