package bg.framework.app.functional.test.Slingshot;

import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.CustomerServiceAgentAction;
import bg.framework.app.functional.action.Slingshot.ForgotttenPasswordAction;
import bg.framework.app.functional.action.Slingshot.PreconditionAction;
import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

import org.testng.annotations.Test;

import static bg.framework.app.functional.entities.FunctionalCategory.*;

public class LoginTest extends TestBase {
	// TC_Login_001 - Verify whether user is able to login to the application on
	// clicking "[Login]" in the home page
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void loginVerificationFromBusinessSite() {
		
		Report.createTestLogHeader("Login Verification",
				"Login for Gas Account from Goto Business Site link");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().GotoBusinessLink(userProfile)
				.BgbloginDetails(userProfile).BgbverifyLogin(userProfile)
				.verifyLoginTimeStamp(userProfile)
				.verifyAuditdetails(userProfile, "4").logout();
	}

	// TC_Login_003 - Verify whether user is able to login to the application on
	// clicking "[Log in to your account]" in the registration journey's landing page.
	@Test(groups = { Regression,Slingshot,Login})
	public void loginVerificationFromRegistrationPage() {
		Report.createTestLogHeader(
				"Login Verification",
				"Verify whether user is able to login to the application on clicking in the registration journey landing page");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new BgbRegistrationAction().openBusinessRegisterurl()
				.verifyAccountDetailsFieldsAndLoginLinkatRHS()
				.BgbloginDetails(userProfile).BgbverifyLogin(userProfile)
				.logOut();
	}

	// TC_Login_012 - Verify if user is able to login to the application by
	// clicking on "[Login]" in the overlay displayed when user updates his/her
	// email address in the "Update Your details" page using the LHN option in
	// Account Summary page
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void loginVerificationAfterDetailsUpdate() {
		Report.createTestLogHeader("Login Verification",
				"Update the email address and login with the new email address");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new LoginAction().navigateToBgbLogin().BgbloginDetails(userProfile)
				.verifyAccountDetails(userProfile).clickMPDLink(userProfile)
				.fillEmailAndPasswordFieldAlone(userProfile)
				.ClickSaveChangesButton().verifyThankYouPage()
				.clickLoginAndVerifyLoginPage()
				.loginWithMPDChangeData(userProfile, 0)
				.verifyAuditDetails(userProfile).resetFields(userProfile);

	}


	// TC_Login_015 - Verify whether login try count is reset to 0 after every successful login
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void loginVerificationTryCount() {
		Report.createTestLogHeader("Login Verification",
				"Verify the login try count is set to 0 after succesfull login");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin()
				.enterPasswordIsCaseSensitive(userProfile)
				.verifyLoginTryCount(userProfile, "1")
				.enterPasswordIsCaseSensitive(userProfile)
				.verifyLoginTryCount(userProfile, "2")
				.enterPasswordIsCaseSensitive(userProfile)
				.verifyLoginTryCount(userProfile, "3");
		new AccountSummaryAction().updateStatus(userProfile);
		new LoginAction().BgbloginDetails(userProfile)
				.BgbverifyLogin(userProfile).logOut()
				.verifyLoginTryCount(userProfile, "null");
	}

	// TC_Login_016 - Verify whether email address is prepopulated in email
	// address field in the Login page if the user has selected
	// "Remember my email" during the last login stamp.
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void loginRemembermyEmail() {
		Report.createTestLogHeader("Login Verification","Check Remember email and verify the prepopulated email");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("SOACollectiveElecAccount");
		new HomePageAction().BgbnavigateToLogin()
				.BgbloginDetails(userProfile)
				.logout().verifyPrepopulateEmailorPass(userProfile)
				.BgbloginDetails(userProfile).logout();
	}

	// TC_Login_017 - To validate the "Email address " field in login section..
	// - Empty email address
	// - incorrect email address
	// - invalid format of email address
	@Test(groups = { Regression,Slingshot,Login})
	public void loginEmailErrorMessageValidation() {
		Report.createTestLogHeader("Login Screen",
				"Email Error Message valdiation");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().emailErrorMsgValidation(
				userProfile);
	}

	// TC_Login_018 - To validate the "Password" field and its error messages in
	// Log in section .
	// - Empty password
	// - incorrect password
	// - Password length >16
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void loginPasswordErrorMessageValidation() {
		Report.createTestLogHeader("Login Screen",
				"Password Error Message valdiation");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin()
				.loginPasswordErrorMessageValidation(userProfile);

	}

	// TC_Login_028 - Verify whether user Slingshot customer able to login
	// Mandatory field : BP number in userprofile
	@Test(groups = { Login, Slingshot, Smoke })
	public void loginVerificationForSlingshotCustomer() {
		Report.createTestLogHeader("Login Verification",
				"Verify Login for Slingshot registered customer");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().GotoBusinessLink(userProfile)
				.BgbloginDetails(userProfile).BgbverifyLogin(userProfile)
				.verifySlingshotCustomer(userProfile).logout();
	}

	// TC_Login_029 - Verify whether the corresponding MS online account Summary
	// page is displayed when a MS online registered customer login.
	// Mandatory field : BP number in userprofile
	@Test(groups = { Login, Slingshot, Smoke })
	public void loginVerificationForMSonlineCustomer() {
		Report.createTestLogHeader("Login Verification",
				"Verify Login for MS Online registered customer");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("MSOnlineCustomer");
		new HomePageAction().BgbnavigateToLogin().GotoBusinessLink(userProfile)
				.BgbloginDetails(userProfile).verifyMsOnlineCustomer()
				.logoutMsOnlineCustomer();
	}

	// TC_Login_030 - Verify whether the corresponding EIDA account Summary page
	// is displayed when a EIDA registered customer login
	// Mandatory field : BP number in userprofile
	//@Test(groups = { Login, Slingshot, Smoke })
	public void loginVerificationForEIDACustomer() {
		Report.createTestLogHeader("Login Verification",
				"Verify Login for EIDA registered customer");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("EIDACustomer");
		new HomePageAction().BgbnavigateToLogin().GotoBusinessLink(userProfile)
				.BgbloginDetails(userProfile)
				.verifyEIDACustomerLogin(userProfile);
		// .logout();
	}

	// TC_Login_033 - Verify whether the email address field is case
	// insensitive.
	@Test(groups = { Login, Slingshot, Smoke })
	// public void loginVerifyAccountDetails() {
	public void verifyEmailIsCaseInSensitive() {
		Report.createTestLogHeader("Login Verification",
				"Login and verify the Email Case sensitive plus Account details");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		// GetCustomerDetailsOnlineDb(userProfile); //yet to frame the cases
		new HomePageAction().BgbnavigateToLogin().GotoBusinessLink(userProfile)
				.emailAddressInCaseSensitive(userProfile)
				.BgbverifyLogin(userProfile)
				// .verifyAccountDetails(userProfile);
				.logout();
	}

	// TC_Login_034 - Verify whether the password field is case sensitive
	@Test(groups = { Login, Slingshot, Smoke })
	// public void loginVerifyAccountDetails() {
	public void verifyPasswordIsCaseSensitive() {
		Report.createTestLogHeader("Login Verification",
				"Verify whether the password field is case sensitive");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		// GetCustomerDetailsOnlineDb(userProfile); 
		new HomePageAction().BgbnavigateToLogin().enterPasswordIsCaseSensitive(
				userProfile);
	}

	// TC_Logout_008 - Verify the Back functionality of the Logout page and to
	// check whether user is not navigated to the last active page.
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void logoutandVerifylastpage() {
		Report.createTestLogHeader("Logout Verification",
				"Logout press the back button and verify the page");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		// GetCustomerDetailsOnlineDb(userProfile);
		new HomePageAction().BgbnavigateToLogin().BgbloginDetails(userProfile)
				.BgbverifyLogin(userProfile).logout()
				.verifypageafterBrowserBack(userProfile);

	}

	// TC_Logout_010 - Verify that the Customer should be logged out when he/she
	// closes the browser window
	// Mandatory field : BP number in userprofile
	// @Test(groups ={Login,Slingshot})
	public void logoutandCloseBrowserVerify() {
		Report.createTestLogHeader("Logout Verification",
				"Logout Close the window navigate url and verify the login page");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().BgbloginDetails(userProfile)
				.BgbverifyLogin(userProfile)
				.browserClosewindowandVerify(userProfile)
				.BgbloginDetails(userProfile);

	}

	// TC_Login_020 - Verify that customer with FREEZE_ACCOUNT_FLAG is Y in
	// BGBUSINESS_TA_CUST_REGISTRATION table is not able to do login
	// TC_Login_026 - Verify that customer can not able to login when
	// email_validation flag is 'N'
	// Mandatory field : BP number in userprofile
	@Test(groups = { Regression,Slingshot,Login})
	public void verifyFreezeAccountUser() {
		Report.createTestLogHeader("Login Verification",
				"Verify Freeze account user and emailvalidation flag with N cannot logon");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().verifyFreezedAccount(
				userProfile);
	}

	// TC_Login_026 - Verify that customer can not able to login when
	// email_validation flag is 'N'
	@Test(groups = { Regression,Slingshot,Login})
	public void verifyInactiveAccountUser() {
		Report.createTestLogHeader("Login Verification",
				"Verifies that  customer with Inactive account is not able to do login");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		deregisterinBgbonline(userProfile); 
		new HomePageAction()
        .navigateToBgbRegistration().registerdetailsthroughBusiness(userProfile);
		new HomePageAction().BgbnavigateToLogin().verifyInActiveAccount(
				userProfile);
	}

	@SuppressWarnings("unchecked")
	@Test(groups = { Regression,Slingshot,Login})
	// TC_Login_013(A),TC_Login_039 :Verify whether user is able to login to
	// application from the confirmation page of Forgotten password journey
	public void verifyLoginThroughForgottenPassword() {
		Report.createTestLogHeader(
				"Login Journey",
				"Verifies whether user is able to login to application from the confirmation page of Forgotten password journey");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");

		new LoginAction().navigateToBgbLogin()
				.verifyAndClickForgottenPasswordLinkFP()
				.enterEmail(userProfile)
				.verifyPasswordRemainderSentPage(userProfile)
				.openResetPasswordLink(userProfile)
				.enterNewPasswordFields(userProfile)
				.verifyPasswordResetSuccessPage()
				.clickLoginLinkInResetPasswordSuccessPage()
				.verifyPasswordChangeInDB(userProfile)
				.verifyAuditDetails(userProfile)
				.loginWithNewPassword(userProfile)
				.logOut();
		new ForgotttenPasswordAction().resetToOldPassword(userProfile);

	}

	@SuppressWarnings("unchecked")
	@Test(groups = { Regression,Slingshot,Login})
	// TC_Login_013(B) :Verify whether user is able to login to application from
	// the Password Reset confirmation page of Forgotten password journey
	public void verifyLoginWithoutResetPassword() {
		Report.createTestLogHeader(
				"Login Journey",
				"Verifies whether user is able to login to application from the Password Reset confirmation page  of Forgotten password journey");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");

		new LoginAction().navigateToBgbLogin()
				.verifyAndClickForgottenPasswordLinkFP()
				.enterEmail(userProfile)
				.verifyPasswordRemainderSentPage(userProfile)
				.clickLoginLinkInEmailSentPage()
				.verifyAuditDetails(userProfile).BgbloginDetails(userProfile)
				.BgbverifyLogin(userProfile).verifyAuditDetails(userProfile)
				.logOut().verifyAuditDetails_Logout(userProfile);
		new ForgotttenPasswordAction().resetToOldPassword(userProfile);
	}

	@SuppressWarnings("unchecked")
	@Test(groups = { Login, Slingshot, Smoke })
	// TC_Login_031: Verify whether the customer with resetted password using
	// CSA is able to login to the BGB application
	public void validateLoginThroughResetPasswordLinkByCSA() {
		Report.createTestLogHeader(
				"Login Journey",
				"Verifies whether the customer with resetted  password using CSA  is able to login to the BGB application ");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new CustomerServiceAgentAction().navigateToCsaRegistration()
				.verifyFindUser(userProfile).verifyPasswordLink(userProfile);
	}

	// TC_Login_036 Validate whether after successful login, both active and
	// inactive(expired) accounts are displayed in "Account Overview" page
	@Test(groups = { Regression,Slingshot,Login})
	public void validateLoginForActiveAndInActiveAccount() {
		Report.createTestLogHeader(
				"Login verification",
				"Validates whether after successful login both active and inactive accounts are displayed in Account Overview page ");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().BgbloginDetails(userProfile)
				.validateActiveAndInactiveCustomer(); 
	}

	@SuppressWarnings("unchecked")
	@Test(groups = { Login, Slingshot, Smoke })
	// TC_Login_040: Verify the user is able to login successfully with the
	// Email ID and reseted password
	public void loginAfterSuccessfulRetrievelOfEmail() {
		Report.createTestLogHeader(
				"Login verification",
				"Verify the user is able to login successfully with the Email ID and reseted password");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		// new HomePageAction().BgbnavigateToLogin();
		new LoginAction().navigateToBgbLogin()
				.verifyAndClickForgottenEmailLink().enterValidData(userProfile)
				.clickGetEmailAddress()
				.validateRetrievedEmailaddress(userProfile).clickLoginLink()
				.verifyAuditDetails(userProfile)
				.loginWithRetrievedEmail(userProfile)
				.BgbverifyLogin(userProfile).logOut();
	}

	@SuppressWarnings("unchecked")
	@Test(groups = { Regression,Slingshot,Login})
	// TC_Login_011: Verify the browser back button functionality in Login
	// journey.
	public void verifyBrowserBackFunctionailty() {
		Report.createTestLogHeader("Login verification",
				"Verifies the browser back button functionality in Login journey");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin()
				.verifyBrowserBackFunctionality(userProfile)
				.BgbloginDetails(userProfile).BgbverifyLogin(userProfile)
				// .verifyBrowserBackFunctionality(userProfile)
				.logOut();
	}

	// TC_Login_032: Verify the account details of the customer after logging in
	// to the BGB application.
	@Test(groups = { Regression,Slingshot,Login})
	public void verifyAccountDetails() {
		Report.createTestLogHeader("Login verification",
				"Verifies the account details of the customer after logged in");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile)
				.verifyAccountOverviewActionForLessthanFiveAccount(userProfile)
				.verifyCustomerDetails(userProfile).logOut();
	}

	// TC_Login_036:Validate whether after successful login, both active and
	// inactive(expired) accounts are displayed in "Account Summary" page
	// available for the BP number
	@Test(groups = { Regression,Slingshot,Login})
	public void verifyAccountsDisplayed() {
		Report.createTestLogHeader(
				"Login verification",
				"Verifies the whether active and inactive accounts are displaying in account summary page");
		UserProfile userProfile = new TestDataHelper()
				.getUserProfile("BgbRegistration");
		new HomePageAction().BgbnavigateToLogin().BgbloginDetails(userProfile)
				.BgbVerifyLogin(userProfile).verifyNumberOfCustomerDisplayed();
	}
	
	//@Test(groups ={Slingshot})	
    public void beforeRegistration() throws Exception {
		Report.createTestLogHeader("Pre registration","Registers users when user does not exist");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new HomePageAction()
	        .verifyAndRegister(userProfile);
	       // .registerdetailsthroughBusiness(userProfile);     
	}
    
//June 9th Release ----> Verify the Last Login details and Welcome message is displayed in the Account Overview Page  
    
    @Test(groups = { Regression,Slingshot,Login})
	public void verifyLogindetailsDisplayed() {
		Report.createTestLogHeader("Login verification","Verify whether the Last Login details and Welcome message is displayed in account overview page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		new HomePageAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile)
		.BgbVerifyLogin(userProfile)
		.Verifywelcomemessage()
		.verifyNumberOfCustomerDisplayed();
		
	}
    
    
    
    
    
    
}
