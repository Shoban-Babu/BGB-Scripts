/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.AccountSummary;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.ManagePersonalDetailsAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

/**
 * @author sundarg1
 *
 */

public class AccountSummaryTest extends TestBase{

	@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifyAccountSummaryForLessthan5Accounts() {
		Report.createTestLogHeader("Account summary journey", "verify account summary page for lessthan five account customer");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.verifyAccountOverviewActionForLessthanFiveAccount(userProfile);
		new AccountSummaryAction()
		.verifyCustomerDetails(userProfile)
		//.verifySeeYourLastBill()
		.verifySeeyourLatestStatementLink()
		//.verifyDataVerification(userProfile)
		.logout();
	}

	@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifyAccountSummaryForMorethan15accounts() {
		Report.createTestLogHeader("Account summary journey", "verify account summary page for morethan fifteen account customer");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");       
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
		.selectContractAccountAndVerifySearchFunctionality(userProfile)
		.verifyCustomerDetails(userProfile)
		.verifySeeYourLastBill()
		.verifySeeyourLatestStatementLink()
		.verifyWhatsThisLink()        	
		.logout();
	}	

	//@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifyAccountSummaryForInactiveAccountCustomer() {
		Report.createTestLogHeader("Account summary journey", "verifies account summary page for inactive account customer");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBInactiveAccounts");       
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
		.selectContractAccountAndVerifySearchFunctionality(userProfile)        	
		.verifyCustomerDetailsForInactiveCustomer(userProfile)
		.logout();
	}

	//TC_Drilldown Account Details_35: Verify the browser back button functionality in Account Summary page 
	@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifyBrowserBackFunctionality() {
		Report.createTestLogHeader("Account summary journey", "verifies browser back button functionality for account summary page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.verifyAccountOverviewActionForLessthanFiveAccount(userProfile)
		.verifyBrowserBackFunctionalityInAccountSummaryPage()
		.logout();
	}

	//TC_Drilldown Account Details_17: To verify whether Customer  can select Manage personal details for updating personal details and its page is displayed properly.  
	@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifyUpdateYourDetailsPage(){
		Report.createTestLogHeader("Account summary journey", "verifies whether customer can update their details in Update your details page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.clickMPDLink(userProfile)
		.fillValidDataInMPDPage(userProfile)
		.ClickSaveChangesButton()
		.verifyEmailChangeDataWithDB(userProfile)
		.verifyPasswordChangeDataWithDB(userProfile)
		.verifyThankYouPage();
		new ManagePersonalDetailsAction()
		.clickLoginAndVerifyLoginPage()
		.loginWithMPDChangeData(userProfile, 0)
		.BgbverifyLogin(userProfile)
		.resetFields(userProfile);			
	}
	
	@Test(groups = {Slingshot,Regression,AccountSummary})
	public void verifySurveyInAccountSummary() {
		Report.createTestLogHeader("Account summary journey", "Submits and verifies survey in account summary page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");       
		new LoginAction()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.verifyAccountOverviewActionForLessthanFiveAccount(userProfile)
		.submitNpsSurvey(userProfile)
		.logout();
	}
	
	//TC_Acct_sum_02 :Verify whether the standard user able to access the below links in the account summary page. 1.Message centre 2.Quick links (Submit meter read, View your bills, Account reports)3.Help and advice 
			//TC_Acct_sum_22 :a)Verify the "DD " pod is displayed instead of "get a quote" if a customer have broker relationship.
			
			@Test(groups = {Slingshot,Regression,AccountSummary})
				public void verifyAccSummaryStandardUser(){
					Report.createTestLogHeader("Account summary journey", "To verify account summary page of a standard user"); 
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");    // Provide standard user data for standard user verification and RB,RBP)
					new LoginAction()
					.BgbloginDetails(userProfile)
					.BgbVerifyLogin(userProfile);
					new AccountSummaryAction()
					.accountSummaryPageverification("Normal user", userProfile);
					//.accountSummaryDDPodVerification();
				}
			
			//TC_Acct_sum_05 :Verify whether the super/full user able to access the  below links in the account summary page. 1.Message centre 2.Paperless billing POD - not paperless (super user only)
			                 // 3.Quick links (Submit meter read, View your bills, Manage users, Account reports)4.Help and advice
				@Test(groups = {Slingshot,Regression,AccountSummary})
					public void verifyAccSummarySuperUser(){
						Report.createTestLogHeader("Account summary journey", "To verify account summary page of a super user Full access"); 
						UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");  // Provide standard user data for super user verification 
						String Usertype ="Super";
						new LoginAction()
						.BgbloginDetails(userProfile)
						.BgbVerifyLogin(userProfile);
						new AccountSummaryAction()
						.accountSummaryPageverification("Super User", userProfile);
					}
			
					
				//TC_Acct_sum_10 :Verify whether the below links are displayed in the account summary page for the standard user(RB,RBP).(more than 15 accounts )1.Message centre 2.Quick links (Submit meter read, View your bills, Account reports)3.Help and advice 
				//TC_Acct_sum_11 :Verify whether the  standard user(RB,RBP) able to access the  below links in the account summary page.(more than 15 accounts )1.Message centre 2.Quick links (Submit meter read, View your bills, Account reports)3.Help and advice 
				@Test(groups = {Slingshot,Regression,AccountSummary})
				public void verifyAccSummaryStandardUserMorethan15Accounts(){
					Report.createTestLogHeader("Account summary journey", "To verify account summary page of a standard user more than 15 accounts"); 
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");    // Provide more than 15 accounts standard user data for standard user verification and RB,RBP)   
					new LoginAction()
					.BgbloginDetails(userProfile)
					.BgbVerifyLogin(userProfile)
					.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
				    .selectContractAccountAndVerifySearchFunctionality(userProfile);
					new AccountSummaryAction()
					.accountSummaryPageverification("Standard User", userProfile);			
				}
				
				
				//TC_Acct_sum_14 :Verify whether the super user/full access able to access the  below links in the account summary page.(more than 15 accounts )1.Message centre 2.Paperless billing POD - not paperless (super user only)
		        // 3.Quick links (Submit meter read, View your bills, Manage users, Account reports)4.Help and advice
				@Test(groups = {Slingshot,Regression,AccountSummary})
				public void verifyAccSummarySuperUserMorethan15Accounts(){
					Report.createTestLogHeader("Account summary journey", "To verify account summary page of a super user / Full access more than 15 accounts"); 
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBMorethanFifteenAccounts");  // Provide more than 15 account super user data for appropriate verification  
					String Usertype ="Super";
					new LoginAction()
					.BgbloginDetails(userProfile)
					.BgbVerifyLogin(userProfile)
					.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
				    .selectContractAccountAndVerifySearchFunctionality(userProfile);
					new AccountSummaryAction()
					.accountSummaryPageverification("Super User", userProfile);
				}
				
				/*//TC_Acct_sum_16,17 :Verify whether the below links are displayed in the account summary page for the Super User with EIDA customers.(more than 15 accounts )1.Message centre 2.Paperless billing POD not paperless (super user only)
		        //3.Quick links (Submit meter read, View your bills, Manage users, Account reports, Energy dashboard) 4.Help and advice
				@Test(groups = {Slingshot,Regression,AccountSummary})
				public void verifyAccSummaryEIDAUserMoreThan15Accounts(){
					Report.createTestLogHeader("Account summary journey", "To verify account summary page of a super user / EIDA"); 
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");    
					String Usertype ="EIDA";
					new LoginAction()
					.BgbloginDetails(userProfile)
					.BgbVerifyLogin(userProfile)
					.verifyAccountOverviewActionForMorethanFiffteenAccount(userProfile)
				    .selectContractAccountAndVerifySearchFunctionality(userProfile);
					new AccountSummaryAction()
					.accountSummaryPageVerificationForSuperUserAndEIDA(Usertype)
					.accountSummaryPageverification();
				}*/

	
}
