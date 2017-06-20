package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.AddAdditionalAccounts;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.BusinessMovesPage;
import bg.framework.app.functional.page.Slingshot.ConnectionsandMeteringPage;
import bg.framework.app.functional.page.Slingshot.ContactUsBusinessPage;
import bg.framework.app.functional.page.Slingshot.ContactUsCorporatePage;
import bg.framework.app.functional.page.Slingshot.ManagePersonalDetailsPage;
import bg.framework.app.functional.page.Slingshot.PaymentsPage;
import bg.framework.app.functional.page.Slingshot.RegistrationPage;
import bg.framework.app.functional.page.Slingshot.RenewalsPage;
import bg.framework.app.functional.page.Slingshot.ThankYouPage;
import bg.framework.app.functional.page.common.LegacyLoginPage;



public class AccountSummaryAction{

	private static int login_Success_Id  = 4;
	private static int BGBUSINESS_LOGOUT_SUCCESSFUL = 25;
	private static String strEmail="temp@cognizant.com";
	private static String strTempPwd = "temp1234";

	public AccountSummaryAction(){

	}


	public AccountSummaryAction verifySlingshotCustomer(UserProfile userProfile) {
		//		   new AccountSummaryPage().verifyandUpdateDetails(userProfile);
		return this;
	}
	/*public AccountSummaryAction verifyMsOnlineCustomer(UserProfile userProfile) {
		   new AccountSummaryPage().verifyMsOnlineCustomer();
		   return this;
		}*/
	public AccountSummaryAction verifyEIDACustomer(UserProfile userProfile) {
		//new AccountSummaryPage().verifyandUpdateDetails(userProfile);
		return this;
	} 
	public AccountSummaryAction BgbverifyLogin(UserProfile userProfile) {
		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.AccountSummaryVerification(userProfile);
		accountsummary.getCustomerAddressAndAccountNumber(userProfile);
		accountsummary.getAccountBalanceAndTypeOfAccount();
		return new AccountSummaryAction();
	}

	public AccountSummaryAction verifyAuditdetails(UserProfile userProfile,String eventid) {

		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyAuditDetailsInDb(userProfile, eventid);
		return new AccountSummaryAction();
	}
	public LoginAction verifyLoginTryCount(UserProfile userProfile,String recCount) {

		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyLoginTryCountInDb(userProfile, recCount);

		return new LoginAction();
	} 

	public LoginAction browserClosewindowandVerify(UserProfile userProfile) {

		new  AccountSummaryPage().verifyPageafterReopened();		 
		return new LoginAction();

	}  

	public AccountSummaryAction verifyErrorMsginLoginScreen(){
		new LegacyLoginPage().verifyErrorMessageLogin();
		return this;
	}
	public ManagePersonalDetailsAction clickMPDLink(UserProfile userProfile){
		new  AccountSummaryPage().clickMPDLink(userProfile);
		return new ManagePersonalDetailsAction();
	}
	public AccountSummaryAction updateFreezeAccount(){
		new LegacyLoginPage().verifyErrorMessageFreezeaccount();
		return this;
	}

	public AccountSummaryAction verifyLoginTimeStamp(UserProfile userProfile) {

		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyLoginTimeStamp(userProfile);

		return new AccountSummaryAction();
	} 
	
	
	
	public AccountSummaryAction verifyAuditDetails(UserProfile userProfile){
		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyAuditDetails(userProfile, login_Success_Id);
		return new AccountSummaryAction();
	}
	public AccountSummaryAction verifyAuditDetails_Logout(UserProfile userProfile){
		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyAuditDetails(userProfile, BGBUSINESS_LOGOUT_SUCCESSFUL);
		return new AccountSummaryAction();
	}
	public AccountSummaryAction logOut() {
		AccountSummaryPage accountsummaryPage=new AccountSummaryPage();
		accountsummaryPage.logout();
		return new AccountSummaryAction();
	} 
	public AccountSummaryAction validateActiveAndInactiveCustomer() {
		AccountSummaryPage accountsummaryPage=new AccountSummaryPage();
		accountsummaryPage.validateActiveAndInactiveCustomer();
		return new AccountSummaryAction();
	}
	public ManagePersonalDetailsAction BgbverifyLoginAfterMPD(UserProfile userProfile) {
		//		new LegacyLoginPage().loginUser(userProfile);
		new  AccountSummaryPage().AccountSummaryVerification(userProfile);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction resetFields(UserProfile userProfile){
		ManagePersonalDetailsPage mpdPage = new ManagePersonalDetailsPage();    			
		new  AccountSummaryPage().clickMPDLink(userProfile);
		mpdPage.fillValidDataInManagePersonalDetailsPage(userProfile.getEmail(), userProfile.getPassword(), userProfile.getMobileNumber());
		mpdPage.ClickSaveChangesButton();
		mpdPage.clickLogin();
		return new ManagePersonalDetailsAction(); 
	}
	public AccountSummaryAction loginWithMPDChangeData(UserProfile userProfile,int loginType){
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.loginWithMPDChangeData(strEmail,strTempPwd,userProfile,loginType);	    	
		return new AccountSummaryAction();
	}
	public AccountSummaryAction enterPasswordIsCaseSensitive(UserProfile userProfile){
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.enterPasswordIsCaseSensitive(userProfile);
		return new AccountSummaryAction();
	}

	public AccountSummaryAction updateStatus(UserProfile userProfile){
		AccountSummaryPage accountsummaryPage=new AccountSummaryPage();
		accountsummaryPage.updateStatus(userProfile);
		return new AccountSummaryAction();
	}

	public AccountSummaryAction verifyMsOnlineCustomer(){
		new AccountSummaryPage().verifyMsOnlineCustomer();
		return this;
	}

	public AccountSummaryAction logoutMsOnlineCustomer(){
		new AccountSummaryPage().clickLogoutMsOnlineCustomer();
		return this;
	}
	public AccountSummaryAction accountOverviewLogInUserValidation(UserProfile userProfile) {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		//accOverviewpage .getCustomerReferenceNumber(userProfile);
		accOverviewpage.verifyAccountOverviewPage();		
		accOverviewpage.verifyCustomerRefNumber(userProfile);		
		accOverviewpage.verifyPagination();
		accOverviewpage.verifySearchFunctionality();
		// accOverviewpage.verifySearchDropDown();
		return new AccountSummaryAction();
	}
		
	public AccountSummaryAction VerifyAdditionalrequestLink() {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.VerifyAdditionalrequestLink();
		return new AccountSummaryAction();
	}
	public AccountSummaryAction VerifyAdditionalAccounts(UserProfile userProfile) {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.VerifyAdditionalAccounts(userProfile);
		return new AccountSummaryAction();
	}
	public AccountSummaryAction VerifyAdd50AdditionalAccounts(UserProfile userProfile) {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.VerifyAdd50AdditionalAccounts(userProfile);
		return new AccountSummaryAction();
	}
	public AccountSummaryAction VerifyAdd_removeAccount(UserProfile userProfile) {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.VerifyAdd_removeAccount(userProfile);
		return new AccountSummaryAction();
	}
	public AccountSummaryAction verifyEntry_In_additional_accounts_table(UserProfile userProfile) {
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.verifyEntry_In_additional_accounts_table(userProfile);
		return new AccountSummaryAction();
	}
	
	public AccountSummaryAction verifyBrowserBackFunctionality(UserProfile userProfile){
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.verifyBrowserBackFunctionality();
		return new AccountSummaryAction();			    	
	}
	public LoginAction logout() {
		AccountSummaryPage accountsummaryPage=new AccountSummaryPage();
		accountsummaryPage.logout();
		return new LoginAction();
	}
	public AccountSummaryAction verifySearchFunctionality(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.verifySearchFunctionalitySection();
		accOverviewpage .verifySearchDropDown();			 
		accOverviewpage .selectContractAccountAndVerifySearchFunctionality(userProfile);
		accOverviewpage .searchByBillingAddressAndVerifyAccounts(userProfile);
		accOverviewpage .searchBySiteAddressAndVerifyAccounts(userProfile);
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction verifySearchFunction(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.verifySearchFunctionalitySection();
		accOverviewpage .verifySearchDropDown();	
		return new AccountSummaryAction();	 
	}
	/*public AccountSummaryAction verifyPaginationForMorethan5Customer(){
    	 AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
    	 accOverviewpage.verifyPaginationForMorethan5Customer();
    	 accOverviewpage .();	
    	 return new AccountSummaryAction();	 
     }*/
	public AccountSummaryAction verifyPagination(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage. verifyPaginationLink();
		accOverviewpage.clickAndVerifyPagination();
		accOverviewpage .verifyPaginationOnBothSides();
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction verifyCustomerAccountDetails(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage. verifyPagination();
		accOverviewpage.verifyAccountDetails(userProfile);
		return this; 
	}
	public AccountSummaryAction accountNumberErrorValidation(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();    	
		accOverviewpage.accountNumberErrorValidation();
		return this;
	}
	public AccountSummaryAction validatesAccountOverviewPageForContractAccountNumberSearch(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage .selectContractAccountAndVerifySearchFunctionality(userProfile);		 
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction validatesAccountOverviewPageForBillingPostCodeSearch(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage .searchByBillingAddressAndVerifyAccounts(userProfile);		 
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction validatesAccountOverviewPageForSitePostCodeSearch(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();    	 		 
		accOverviewpage .searchBySiteAddressAndVerifyAccounts(userProfile);		 
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction verifyUsefulLinks(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();    	 		 
		accOverviewpage .verifyUsefulLinks();		 
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction verifyBrowserBackFunctionality(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();    	 		 
		accOverviewpage .verifyBrowserBackFunctionality();		 
		return new AccountSummaryAction();	 
	}
	public AccountSummaryAction verifyDataVerification(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();    	 		 
		accOverviewpage .verifyDataWithQTP(userProfile);		 
		return new AccountSummaryAction();	 
	}

	public AccountOverViewAction BgbVerifyLogin(UserProfile userProfile) {
		//  		new LegacyLoginPage().loginUser(userProfile);
		new  AccountSummaryPage().AccountSummaryVerification(userProfile);
		return new AccountOverViewAction();
	}

	public AccountSummaryAction verifyCustomerDetails(UserProfile userProfile){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.getCustomerAddressAndAccountNumberInAccountSummary(userProfile);
		accSummaryPage.getAccountBalanceAndTypeOfAccountInAccountSummary();
		//accSummaryPage.verifyAccountSummaryPageLinks(userProfile.getBpnumber()); 
		//accSummaryPage.verifyDataWithQTP(userProfile);    	
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifyCustomerDetailsForInactiveCustomer(UserProfile userProfile){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.getCustomerAddressAndAccountNumberInAccountSummary(userProfile);
		accSummaryPage.getAccountBalanceAndTypeOfAccountInAccountSummary();
		//accSummaryPage.verifyAccountSummaryPageLinksForInactiveAccounts();
		accSummaryPage.verifyInactiveAccountLinks();
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifySeeYourLastBill(){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.verifySeeyourLastBillLink();
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifySeeyourLatestStatementLink(){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.verifySeeyourLatestStatementLink();
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifyWhatsThisLink(){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.verifyWhatsThisLink();
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifyBrowserBackFunctionalityInAccountSummaryPage(){
		AccountSummaryPage accSummaryPage =   new AccountSummaryPage();   
		accSummaryPage.verifyBrowserBackFunctionalityInAccountSummaryPage();
		return new AccountSummaryAction();	  
	}
	public AccountSummaryAction verifyEIDACustomerLogin(UserProfile userProfile) {
		new AccountSummaryPage().verifyLoginEIDACustomer();
		return this;
	}  
	public AuditTrailAction bgbverifyLogin(UserProfile userProfile) {
		//		new LegacyLoginPage().loginUser(userProfile);
		new  AccountSummaryPage().AccountSummaryVerification(userProfile);
		return new AuditTrailAction();
	}


	public DirectDebitAction selectAccountForDirectDebit(){
		new AccountSummaryPage().clickLogoutMsOnlineCustomer(); 
		return new DirectDebitAction();

	}
	public AccountSummaryAction BgbverifyAfterLogin() {
		//		new LegacyLoginPage().loginUser(userProfile);
		// new  AccountSummaryPage().AccountOverviewVerification();
		return new AccountSummaryAction();
	}
	public AccountSummaryAction  verifyAccountDetails(UserProfile userProfile) {
		// 		new LegacyLoginPage().loginUser(userProfile);
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.verifyAccountDetails(userProfile);
		accOverviewpage.getNumberAccountsDisplayed();
		return new AccountSummaryAction();
	}
	public AccountSummaryAction clickAndVerifyManageAccountLink(SMRAccountDetails smrAccountDetails) {
		//  		new LegacyLoginPage().loginUser(userProfile);
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage.clickAndVerifyManageAccountLink(smrAccountDetails);
		return new AccountSummaryAction();
	}
	public ViewBillAction clickViewBillLink(){
		new AccountSummaryPage().clickViewBillLink(); 
		return new ViewBillAction();

	}
	public AccountSummaryAction billingPostCodeErrorMessageValidation(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage(); 
		accOverviewpage.billingPostCodeErrorValidation();
		return this;
	}
	public AccountSummaryAction sitePostCodeErrorMessageValidation(){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage(); 
		accOverviewpage.SitePostCodeErrorValidation();
		return this;
	}
	public AccountSummaryAction submitNpsSurvey(UserProfile userProfile){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(userProfile.getFeedbackoption());
		thankyoupage.selectNpsFeedbackText(userProfile.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(userProfile.getEmail(),"NPS",userProfile.getFeedbackoption(),userProfile.getFeedbacktext());
		return new AccountSummaryAction();
	}
	public AccountSummaryAction clickAreYourDetailsUptoLink(){
		AccountSummaryPage accSummarypage =   new AccountSummaryPage(); 
		accSummarypage.clickAreYourDetailsUptoLink();
		return this;
	}
	public AccountSummaryAction verifyUpdateMyDetailsLink(){
		AccountSummaryPage accSummarypage =   new AccountSummaryPage(); 
		accSummarypage.verifyUpdateMyDetailsLink();
		return this;
	}

public ConsumptionGraphAction clickEnergyConsumptionLink(UserProfile userProfile){
	AccountSummaryPage accSummarypage =   new AccountSummaryPage(); 
	accSummarypage.clickEnergyConsumptionGraphLink(userProfile);
	return new ConsumptionGraphAction();
}
	public AccountSummaryAction verifySmrLink(){
		new AccountSummaryPage().smrLink();
		return this;		
	}
	public AccountSummaryAction verifyBillingAddress(UserProfile userProfile){
		new AccountSummaryPage().smrCollectiveBillingAddressComparsion(userProfile);
		return this;		
	}
	public AccountSummaryAction verifyManageAccLink(){
		new AccountSummaryPage().manageAccLink();
		return this;		
	}
	public AccountSummaryAction verifyBillingAddressInAccSummary(UserProfile userProfile){
		new AccountSummaryPage().smrCollectiveBillingAddressComparsionInAccSummary(userProfile);
		return this;		
	}
	public AccountSummaryAction verifyViewYourLastBillLink(){
		new AccountSummaryPage().viewBillLink();
		return this;		
	}
	public AccountSummaryAction verifyPayYourLastBillLink(){
		new AccountSummaryPage().payBillLink();
		return this;		
	}
	public AccountSummaryAction verifyEnergyConsumptionLink(){
		new AccountSummaryPage().viewConsumptionLink();
		return this;		
	}
	public AccountSummaryAction verifyStatementOfAccLink(){
		new AccountSummaryPage().viewStatementOfAccLink();
		return this;		
	}
	public AccountSummaryAction makeAPaymentLink(){
		new AccountSummaryPage().makeAPaymentLink();
		return this;		
	}
	public ContactUsBusinessAction navigatetoContactUslink(){
		new ContactUsBusinessPage().navigatetoContactUslink();
		return new ContactUsBusinessAction();	
	}


	public ContactUsBusinessAction AddressCapturing() {
		new ContactUsBusinessPage().AddressCapturing();
		return new ContactUsBusinessAction();	
	}
	
	public ContactUsCorporateAction navigatetoContactUsCorporatelink() {
		new ContactUsCorporatePage().navigatetoContactUsCorporatelink();
		return new ContactUsCorporateAction();	
	}
	public AccountSummaryAction verifySearchFunctionality1(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		AccountSummaryPage accSummarypage =   new AccountSummaryPage();
		accOverviewpage.verifySearchFunctionalitySection();
		accOverviewpage .verifySearchDropDown();			 
		accOverviewpage .selectContractAccountAndVerifySearchFunctionality(userProfile);
		//accOverviewpage .searchByBillingAddressAndVerifyAccounts(userProfile);
	//	accOverviewpage .searchBySiteAddressAndVerifyAccounts(userProfile);
		accSummarypage.clickMPDLink1(userProfile);
		return new AccountSummaryAction();	 
	}
	 public AccountSummaryAction clickManageAccountLink(UserProfile userProfile){
			AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
			accountOverviewPage.clickManageAccountLink(userProfile);
			return new AccountSummaryAction();
		}
	/* public AccountSummaryAction clickRenewaldueButton(){
			AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
			accountOverviewPage.VerifyRenewaldueButton();
			return new AccountSummaryAction();
		}*/
		public AccountSummaryAction auditVerificationOfEmail(UserProfile userProfile){
			new RegistrationPage().verifyAndAuditEmailToCustomer(userProfile);
			return this;
		}

		public PaymentsAction navigatetoPayments(UserProfile userProfile) {
			new PaymentsPage().navigatetoPayments(userProfile);
			return new PaymentsAction();	
		}
		public BusinessMovesAction clickBusinessMovesLink(){
			BusinessMovesPage businessMoves = 	new BusinessMovesPage();
			businessMoves.clickBusinessMovesLink();
			return new BusinessMovesAction();
		}
		
		public ConnectionsandMeteringAction navigatetoConnectionsandMetering() {
			new ConnectionsandMeteringPage().navigatetoConnectionsandMetering();
			return new ConnectionsandMeteringAction();			
		}
		public ConnectionsandMeteringAction retrieveTelephoneNo(UserProfile userProfile) {
			new ConnectionsandMeteringPage().retrieveTelephoneNo(userProfile);
			return new ConnectionsandMeteringAction();	
			
		}
		public AccountSummaryAction accountSummaryPageverification(String Usertype, UserProfile userProfile){
			new AccountSummaryPage().accountSummaryPageverification(Usertype, userProfile);
			return this;
		}
		public AccountSummaryAction accountSummaryPageVerificationForSuperUserAndEIDA(String Usertype){
			new AccountSummaryPage().accountSummaryPageVerificationForSuperUserAndEIDA(Usertype);
			return this;
		}
		public AccountSummaryAction accountSummaryDDPodVerification(){
			new AccountSummaryPage().accountSummaryDDPodVerification();
			return this;
		}

		public RenewalsAction energyPlanRenewalLink() {
			new RenewalsPage().energyPlanRenewalLink();
			return new RenewalsAction();
		}
		
		
		public AccountSummaryAction VerifycustomerReferenceNumber(){
			AccountOverviewPage accountOverviewPage=new AccountOverviewPage();
			accountOverviewPage.VerifycustomerReferenceNumber();
			return new AccountSummaryAction();
		}
		
		
		
		
}
