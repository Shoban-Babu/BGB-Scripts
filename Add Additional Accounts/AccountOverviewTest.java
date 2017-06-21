package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.AccountOverview;
import static bg.framework.app.functional.entities.FunctionalCategory.AccountSummary;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AccountOverViewAction;
import bg.framework.app.functional.action.Slingshot.PaymentsAction;
import bg.framework.app.functional.action.Slingshot.SubmitMeterReadAction;
import bg.framework.app.functional.action.Slingshot.ViewBillAction;
import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class AccountOverviewTest extends TestBase{

	//TC_Account overview_01: Verify the "Account Summary" Page sorted as list format when the accounts for BP is less than 5.
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyAccountOverviewForLessthanFiveAccount(){
		Report.createTestLogHeader("Account Overview", "Verify the Account overview page when the accounts for BP is less than five");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.accountOverviewLogInUserValidation(userProfile)
		.logOut();		 
	}

	//TC_Account overview_02(a):Verify the "Account Summary" Page  with Search Functionality should be displayed with drop down, text field for keyword and Continue button  for Gas account holder with more than 15 accounts
	//TC_Account overview_02(d):Verify whether the Customer can able to Search the different accounts using the Search Functionality
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifySearchFunctionality(){
		Report.createTestLogHeader("Account Overview", "Verify the Account Summary Page  with search function for more than 15 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifySearchFunctionality(userProfile)
		.logOut();
	}

	//TC_Account overview_02(b),TC_Account overview_02(c) :Verify the "Account Summary" Page  with Pagination functions with clickable numbering , previous and next links should be present for Gas account holder with more than 5 accounts 
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyPagination(){
		Report.createTestLogHeader("Account Overview", "Verify the Account Summary Page  for pagination for less than 15 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)		
		.verifyPagination()
		.logOut();
	}

	//TC_Account overview_06: To verify whether all the accounts of the customer will be displayed for that particular BP number with Account Numbers(s), customer billing address, account balance, manage account link in the Account Summary page
	//@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyCustomerForBP(){
		Report.createTestLogHeader("Account Overview", "Verify the customer account summary page for less than 15 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile)
		.verifyCustomerAccountDetails(userProfile)
		.verifyDataVerification(userProfile)
		.logOut();
	}
	//TC_Account overview_09 :Verify whether the search option is available in the account summary page 
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifySearchFunctionalityAndAccountsDisplayed(){
		Report.createTestLogHeader("Account Overview", "Verify search option is available and which allows customer to search for Contract account number Billing address postcode and Site address postcode");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifySearchFunctionality(userProfile)
		.logOut();
	}
	//TC_Account overview_11:To verifies error message when the user selects Account number in search dropdown and enter invalid entries in keywords  
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void accountNumberErrorValidation(){
		Report.createTestLogHeader("Account Overview", "verifies error message when the user selects Account number in search dropdown and enter invalid entries in keywords");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.accountNumberErrorValidation()
		.logOut();
	}

	//TC_Account overview_14 : To verify the account summary layout when the user selects "Contract account number" in search dropdown and enter valid entries in keywords 
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyAccountOverviewForAccountNumber(){
		Report.createTestLogHeader("Account Overview", "Verifies Account overview page when the user selects Contract account number and enters valid entries");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.validatesAccountOverviewPageForContractAccountNumberSearch(userProfile)
		.logOut();
	}

	//TC_Account overview_24 : Verify the "Submit Meter read" and "View your bills" option in the "Quick links" section in Account summary  page and validate its functionality  
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyQuickLinks(){
		Report.createTestLogHeader("Account Overview", "Verifies and validates submit meter read and View your billing links in the Useful links section");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyUsefulLinks()
		.logOut();
	}  

	//TC_Account overview_32:Verify the browser back button functionality in Account Summary page
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyBrowserBackFunctionailty(){
		Report.createTestLogHeader("Account Overview", "Verifies the browser back button functionality in Account Summary page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new HomePageAction()
		.BgbnavigateToLogin()	            
		.BgbloginDetails(userProfile)
		.verifyBrowserBackFunctionality(userProfile)
		.logOut();
	} 
	//TC_Account overview 12 :To verifies error message when the user selects Billing address in search dropdown and enter invalid entries in keywords  
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void billingPostCodeErrorMessageValidation(){
		Report.createTestLogHeader("Account Overview", "verifies error message when the user selects Billing address in search dropdown and enter invalid entries in keywords");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.billingPostCodeErrorMessageValidation()
		.logOut();
	}
	//TC_Account overview 13 :To verifies error message when the user selects Site address in search dropdown and enter invalid entries in keywords  
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void sitePostCodeErrorMessageValidation(){
		Report.createTestLogHeader("Account Overview", "verifies error message when the user selects Site address in search dropdown and enter invalid entries in keywords");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.sitePostCodeErrorMessageValidation()
		.logOut();
	}

	//TC_Account overview_15 : To verify the account overview layout when the user selects "Billing address postcode" in search dropdown and enter valid entries in keywords
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyAccountOverviewForBillingAddress(){
		Report.createTestLogHeader("Account Overview", "Verifies Account overview page when the user selects Billing address and enters valid entries");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)			
		.validatesAccountOverviewPageForBillingPostCodeSearch(userProfile)
		.logOut();
	}

	//TC_Account overview_16 : To verify the account overview layout when the user selects "site  address postcode" in search dropdown and enter valid entries in keywords
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifyAccountOverviewForSiteAddress(){
		Report.createTestLogHeader("Account Overview", "Verifies Account overview page when the user selects Site address and enters valid entries");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.validatesAccountOverviewPageForSitePostCodeSearch(userProfile)
		.logOut();
	}
	//TC_Account overview_01: Verify the "Account Summary" Page sorted as list format when the accounts for BP is less than 5.
	@Test(groups ={Slingshot,Regression,AccountOverview})
	public void verifySurveyInAccountOverview(){
		Report.createTestLogHeader("Account Overview", "Verifies the survey for Account overview page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbverifyLogin(userProfile)
		.accountOverviewLogInUserValidation(userProfile)
		.submitNpsSurvey(userProfile)
		.logOut();		 
	}
	//TC_AccountSummary_003: Verify whether the functionality of 'Submit meter read' link in RHN in Account overview page.
			//TC_AccountSummary_006: Verify whether billing address is displayed in Account overview page if it is collective account.
			//TC_AccountSummary_010: Verify whether billing address is displayed in Account summary page if it is collective account.
			//TC_AccountSummary_011: Verify the functionality of 'View your last bill' link in Account summary page.
			//TC_AccountSummary_012: Verify the functionality of 'Pay your last bill' link in Account summary page.
			//TC_AccountSummary_015: Verify the functionality of 'View consumption' link in Account summary page.
			//TC_AccountSummary_016: Verify the functionality of 'View statement of account' link in Account summary page.
			//TC_AccountSummary_017: Verify the functionality of 'Make a payment' link in Account summary page.
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifyAccountSummaryPage(){
				Report.createTestLogHeader("Account Overview journey", "Account summary page verification");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyBillingAddress(userProfile)
				.verifySmrLink()	
				.verifyManageAccLink()
				.verifyBillingAddressInAccSummary(userProfile)
				.verifyViewYourLastBillLink()
				.verifyPayYourLastBillLink()
				.verifyEnergyConsumptionLink()
				.verifyStatementOfAccLink()
				.makeAPaymentLink();
			}
			
			//TC_Acct_sum_02:Verify whether the standard user able to access the below links in the account summary page.
			//TC_Acct_sum_03:Verify the 'Your Messages' section of the account summary page for the standard user.
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifyStandardUserLink() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the standard user able to access the below links in the account summary page.");
				UserProfile userProfile = new TestDataHelper().getUserProfile("StdUserLessthan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			//TC_Acct_sum_05:Verify whether the super user able to access the  below links in the account summary page.
			//TC_Acct_sum_06:Verify the 'Your Messages' section of the account summary page for the Super user.
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifySuperUserLink() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the super user able to access the  below links in the account summary page");
				UserProfile userProfile = new TestDataHelper().getUserProfile("SuperUserLessthan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			//TC_Acct_sum_07:Verify whether the below links are displayed in the account summary page for the Super User with EIDA customers.
			//TC_Acct_sum_08:Verify whether the super user with EIDA customers able to access the  below links in the account summary page
			//TC_Acct_sum_09:Verify the 'Your Messages' section of the account summary page for the Super user with EIDA.
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifySuperUserEIDACustLink() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the super user able to access the  below links in the account summary page");
				UserProfile userProfile = new TestDataHelper().getUserProfile("SuperUserEIDACusLessthan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			//TC_Acct_sum_02:Verify whether the standard user able to access the below links in the account summary page.
			//TC_Acct_sum_03:Verify the 'Your Messages' section of the account summary page for the standard user.
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifyStandardUserLink_Morethan15() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the standard user able to access the below links in the account summary page.");
				UserProfile userProfile = new TestDataHelper().getUserProfile("StdUserMorethan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			//TC_Acct_sum_05:Verify whether the super user able to access the  below links in the account summary page.
			//TC_Acct_sum_06:Verify the 'Your Messages' section of the account summary page for the Super user.
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifySuperUserLink_Morethan15() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the super user able to access the  below links in the account summary page");
				UserProfile userProfile = new TestDataHelper().getUserProfile("SuperUserLessthan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			//TC_Acct_sum_07:Verify whether the below links are displayed in the account summary page for the Super User with EIDA customers.
			//TC_Acct_sum_08:Verify whether the super user with EIDA customers able to access the  below links in the account summary page
			//TC_Acct_sum_09:Verify the 'Your Messages' section of the account summary page for the Super user with EIDA.
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void verifySuperUserEIDACustLink_Morethan15() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the super user able to access the  below links in the account summary page");
				UserProfile userProfile = new TestDataHelper().getUserProfile("SuperUserEIDACusLessthan15");       
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccOverview_StdUser()
				.verifyQuickLinks_stdUser();		
			}
			
///////////////////////////////////////////////////////////////////////////////////April Release BAU////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationSuperUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the account overview page of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
				new LoginAction()
				.BgbloginDetailscheck(userProfile)
			     .AccountOverViewlink(userProfile)
			    .AccountOverViewSMRQuickNavigation(userProfile)
				.AccountOverViewQuickNavigationViewBill(userProfile)
				.AccountOverViewQuickNavigationMAP(userProfile)
				.AccountOverviewRightPromo()
				.clickManageAccountLink(userProfile)
				.AccountSummaryVerification(userProfile);
			
			}
			

			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationStandardUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the account overview page of Standard User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");       
				new LoginAction()
				.BgbloginDetailscheck(userProfile)
				.AccountOverViewlink(userProfile)
				.AccountOverViewSMRQuickNavigation(userProfile)
				.AccountOverViewQuickNavigationViewBill(userProfile)
				.AccountOverViewQuickNavigationMAP(userProfile)
				.AccountOverviewRightPromoStandardUsers()
				.clickManageAccountLink(userProfile)
				.AccountSummaryVerification(userProfile);
			
			}			
			
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationSMRMorethan15Accounts() {
				Report.createTestLogHeader("Account Overview journey", "Verify whether the customer is able to view the Energy plan in the account overview page");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");  
				String strSearchByOption= "Account number";
				String strKeyword=userProfile.getAccNumber();
				new LoginAction()
				.BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.Morethan15(strSearchByOption, strKeyword)
				.AccountOverViewSMRQuickNavigation(userProfile)
				.AccountOverViewQuickNavigationViewBill(userProfile)
				.AccountOverViewQuickNavigationMAP(userProfile);
				
			}
			
/*
 * 
 * August Release BAU Zeeshan		new changes of BGB BAU	 */
			

			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationSuperUserRewrite() {
				Report.createTestLogHeader("Account Overview journey", "Verify the account overview page Qucik Links of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
				new LoginAction()
				.BgbloginDetailscheck(userProfile)
			     .AccountOverViewlink(userProfile)
			     .AccountOverViewQuickNavigationRenewals()
			     .AccountOverViewSMRQuickNavigation(userProfile)
				 .AccountOverViewQuickNavigationViewBill(userProfile)
			   	.AccountOverViewQuickNavigationMAP(userProfile)
				.AccountOverviewRightPromo()
			    .clickManageAccountLink(userProfile)
			 	.AccountSummaryVerification(userProfile);
			 
				
			
			}	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
/****************************************************************************************** october 21 Release Wave Changes**********************************************************************************************************************************************************************************************/
/*
 * 		BGBD_1729		SMR
 */
		
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationSMRSuperUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of SMR link from the account overview page of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyMprn1");
				
				new LoginAction()
				.BgbloginDetails(userProfile);
				
			 new AccountOverViewAction()
			    .AccountOverViewSMRQuickNavigation(userProfile);
			    new SubmitMeterReadAction()
			    .meterReadingDue()
			    .enterMeterDials(smrProfile);
  
		
			
			}
			
			
			
			
			
			
			
/*
 * Electricity Fuel			
 */
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationSMRStandardUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of SMR link from the account overview page of Standard User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
				SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SmrLoggedInJourneyMprn1");
				
				new LoginAction()
				.BgbloginDetails(userProfile);
				
			 new AccountOverViewAction()
			    .AccountOverViewSMRQuickNavigation(userProfile);
			    new SubmitMeterReadAction()
			    .meterReadingDue()
			    .enterMeterDialselec(smrProfile);
  
		
			
			}
			
			
			
			
			
			
			
			
/*
 *   BGBD 1731			Payment
 */
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationPaymentSuperUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of Payment link from the account overview page of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
				   String SaveCard = "No";
				
				new LoginAction()
				.BgbloginDetails(userProfile);
				 new AccountOverViewAction()
				    .AccountOverViewQuickNavigationtoviewMAP(userProfile);
				new PaymentsAction () 
				.paymentDue();
				//.paymentViaNewCard_NotClickSaveButton(userProfile);
				
				
			}
			/*******************Collective Account*********/		
			
			
			
			
			
			
			/*
			 *   BGBD 1733			Renewals
			 */		
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationRenewalsUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of Renewal link from the account overview page of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
			
				
				new LoginAction()
				.BgbloginDetails(userProfile);
				 new AccountOverViewAction()
				    .AccountOverViewQuickNavigationRenewals();
			
			}
			
			/*
			 *   BGBD 1730			View Bill
			 */		
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationViewBillSuperUser() {
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of View Bill link from the account overview page of Super User");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
				   
				
				new LoginAction()
				.BgbloginDetails(userProfile);
				 new AccountOverViewAction()
				    .AccountOverViewQuickNavigationViewBill(userProfile);
				 new ViewBillAction ()
				 .verifyViewBillDue();
			
				
				
			}
			
			
	/*******************Collective Account*********/		
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverViewQuickNavigationViewBillCollectiveAccount()
			{
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of View Bill link from the account overview page of collective Account");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");      
				String strSearchByOption= "Account number";
				String strKeyword=userProfile.getAccNumber();
				
				 new LoginAction()
				.BgbloginDetails(userProfile);
				
				 new AccountOverViewAction()
				 .Morethan15(strSearchByOption, strKeyword)
				 .AccountOverViewQuickNavigationtoViewBill(userProfile);
				 
				 new ViewBillAction ()
				 .verifyViewBillDue();

			}
			
			
	/************Account Overview Dashboard page for Single Account -  Shoban May 4 th Release ***************/
			
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
			public void AccountOverDashboardpage_Singleaccount() {
			
				Report.createTestLogHeader("Account Overview journey", "Verify the functionlaity of Account Overview Dashboard page of Single Account");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts"); 
			
				new LoginAction()
				.BgbloginDetails(userProfile);
				
				 new AccountOverViewAction()
				 .VerifyAccountOverviewDashboardpage()
				 .VerfiNavigatetoAccountSummarypage()
				 .NavigatetoSMRLandingpage()
				 .NavigatetoBillingLandingpage()
				 .NavigatetoPayments()
				 .NavigatetoMovingHomeLandingPage()
				 .NavigatetoGetAQuotePage()
				 .NavigatetoManageUsers()
				 .NavigatetoUpdateYourDetailsLandingpage();
				 
				 
				 
			}
			
			
			
			
			
			
			
			
}
