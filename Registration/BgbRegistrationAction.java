package bg.framework.app.functional.action.Slingshot;


import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.CustomerServiceAgentPage;
import bg.framework.app.functional.page.Slingshot.MultiUserMultiViewPage;
import bg.framework.app.functional.page.Slingshot.RegistrationPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;
import bg.framework.app.functional.util.OnlineDBConnector;

public class BgbRegistrationAction {

	public BgbRegistrationAction verifyRegisterPagebyCsa(){
		new RegistrationPage();
		return this;
	}

	public BgbRegistrationAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}

	public BgbRegistrationAction BgbloginDetails(UserProfile userProfile) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.BgbloginUser(userProfile);
		return this;
	}

	public BgbRegistrationAction BgbverifyAfterLogin(UserProfile userProfile) {
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}

	public BgbRegistrationAction registerdetails(UserProfile userProfile) {

		new RegistrationPage()
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister(userProfile)
		.registerFirstPageCsa(userProfile)
		//.registerDetailsFirstPage(userProfile)
		.registerDetailsSecondPage(userProfile)
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl()
		.waitTimeforAuditEntry();
		return this;
	}

	public BgbRegistrationAction verifyExpiredLink(UserProfile userProfile){
		new RegistrationPage()
		.openUrlandVerifyRegistration(userProfile)
		.verifyLinkExpiredPage();
		return this;

	}

	public BgbRegistrationAction verifyTitle(String title){
		new RegistrationPage().verifyTitle(title);
		return this;
	}
	public HomePageAction verifyLoginWithEmailasN(UserProfile userProfile){
		new RegistrationPage()	
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister(userProfile)
		.registerFirstPageCsa(userProfile)
		//.registerDetailsFirstPage(userProfile)
		.registerDetailsSecondPage(userProfile);
		return new HomePageAction();
	}

	public BgbRegistrationAction registerdetailsthroughBusiness(UserProfile userProfile) {

		new RegistrationPage()	
		.registerDetailsFirstPage(userProfile)
		.registerDetailsSecondPage(userProfile)
		.validateEmailValidationFlag(userProfile, "N")
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl()
		.validateEmailValidationFlag(userProfile, "Y"); 
		return this;
	}

	public HomePageAction clickLoginlinkAfterRegistration(){

		return new HomePageAction();
	}
	public BgbRegistrationAction updateAccountDetails(UserProfile userProfile,int status,String validation){

		new OnlineDBConnector().updateorRevertProfileRegistration(userProfile,status,validation);
		return this;

	}

	public BgbRegistrationAction openBusinessRegisterurl(){

		new RegistrationPage().openBusinessRegisterUrlPage(); 	
		return this;

	}
	
	/*public BgbRegistrationAction verifyAccountDetailsPage(UserProfile userProfile){

		new RegistrationPage().verifyAccountDetailsPage(userProfile); 	
		return this;

	}*/
	
/*	public BgbRegistrationAction registerForAnOnlineAccount(UserProfile userProfile){

		new RegistrationPage().registerForAnOnlineAccount(userProfile); 	
		return this;

	}*/
	
	
	

	public BgbRegistrationAction verifyErrorinBusinessRegisterPage(int status){

		new RegistrationPage().verifyErrorinBusinessRegisterFirstPage(status); 	
		return this;

	}

	public BgbRegistrationAction enterDetailsFirstPage(UserProfile userProfile){

		new RegistrationPage().registerDetailsFirstPage(userProfile);
		return this;

	}

	public BgbRegistrationAction enterDetailsSecondPage(UserProfile userProfile){

		new RegistrationPage().registerDetailsSecondPage(userProfile);
		return this;

	}
	public BgbRegistrationAction validateCustomerReference(UserProfile userProfile){

		new RegistrationPage().validateCustomerRefNo(userProfile);
		return this;
	}

	public BgbRegistrationAction validateInvoiceNumber(UserProfile userProfile){

		new RegistrationPage().validateInvoiceNumber(userProfile);
		return this;
	}

	public BgbRegistrationAction validatePostCode(UserProfile userProfile){

		new RegistrationPage().validatePostCode(userProfile);
		return this;
	}

	public BgbRegistrationAction validateEmailField(UserProfile userProfile){

		new RegistrationPage().validateEmailAddressField(userProfile);
		return this;
	}

	public BgbRegistrationAction validateCancelLink(UserProfile userProfile){

		new RegistrationPage().validateCancelLink(userProfile);
		return this;
	}

	public BgbRegistrationAction enterDetailsinLoginPage(String email, String password){

		new RegistrationPage().enterLoginDetails(email, password);
		return this;
	}

	public BgbRegistrationAction validateLoginPage(String validation){

		new RegistrationPage().validatePageTitle(validation);
		return this;
	}

	public BgbRegistrationAction validateInvalidAccount(UserProfile userProfile){

		new RegistrationPage().setDataForInvalidAccounts(userProfile);
		return this;
	}

	public BgbRegistrationAction verifyEnteredEmailAndSet(UserProfile userProfile){

		new RegistrationPage().verifyEmailIdInDb(userProfile);
		setDataForEmail(userProfile);
		return this;
	}

	public BgbRegistrationAction setDataForEmail(UserProfile userProfile){

		new RegistrationPage().setDataForEmailaddress(userProfile);
		return this;
	}  

	public BgbRegistrationAction verifyEnteredPasswordAndSet(UserProfile userProfile){

		new RegistrationPage().verifyPasswordIdInDb(userProfile);
		setDataForPassword(userProfile);
		return this;
	} 

	public BgbRegistrationAction setDataForPassword(UserProfile userProfile){

		new RegistrationPage().setDataForPassword(userProfile);
		return this;
	}

	public BgbRegistrationAction clickLoginAndVerify(UserProfile userProfile){
		new RegistrationPage().enterLoginDetails(userProfile);
		return this;
	}

	public BgbRegistrationAction checkTermsAndConditions(UserProfile userProfile){

		new RegistrationPage()
		.registerDetailsFirstPage(userProfile)
		.verifyTermsAndConditions(userProfile)
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl();
		return this;
	} 

	public BgbRegistrationAction valdiateTitleInRegistration(UserProfile userProfile){

		new RegistrationPage()	
		.verifyTitleContent()
		.enterDetailsSecondPage("Please select",userProfile.getFirstName(),userProfile.getLastName(),userProfile.getPassword())
		.clickRegister()
		.verifyTitleError();	      
		return this;
	}

	public BgbRegistrationAction validateFirstname (UserProfile userProfile){

		new RegistrationPage().validateFirstName(userProfile);      
		return this;
	}

	public BgbRegistrationAction validateSurname(UserProfile userProfile){

		new RegistrationPage().validateSurName(userProfile);      
		return this;
	}

	public BgbRegistrationAction validatePassword(UserProfile userProfile){

		new RegistrationPage().validatePassword(userProfile);      
		return this;
	}

	public BgbRegistrationAction validateBrowserBack(UserProfile userProfile,int pagination){

		new RegistrationPage().clickBrowserBackAndVerify(userProfile,pagination);
		return this;
	}

	public BgbRegistrationAction validateBrowserBackEmail(UserProfile userProfile){
		new RegistrationPage()
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl()
		.clickBrowserBackAndVerify(userProfile,3);
		return this;
	}

	public BgbRegistrationAction validateMoreThanThreeRegisterUser(UserProfile userProfile){
		new RegistrationPage().verifyMoreThanThreeRegistration(userProfile);
		return this;
	}

	public BgbRegistrationAction verifyLoginAfterRegistrationError(UserProfile userProfile){
		openBusinessRegisterurl();
		RegistrationPage register=new RegistrationPage();
		register.setDataForAccNumberRunTime(userProfile,"12"); 
		register.registerDetailsFirstPage(userProfile);
		register.clickLoginLink();
		new LegacyHomePage().BgbnavigateToLoginPage();
		LegacyLoginPage legacy=new LegacyLoginPage();
		legacy.BgbloginUser(userProfile);
		legacy.logOut();    
		return this;
	}

	public BgbRegistrationAction navigateToCsaRegister() {
		CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
		csapage.clickRegisteraUser();
		return new BgbRegistrationAction();
	}

	public BgbRegistrationAction verifyAuditdetails(UserProfile userProfile,String eventid) {

		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.verifyAuditDetailsInDb(userProfile, eventid);
		return new BgbRegistrationAction();
	}
	public LoginAction verifyAccountDetailsFieldsAndLoginLinkatRHS(){
		new RegistrationPage().verifyAccountDetailsFieldsAndLoginLinkatRHS();
		return new LoginAction();
	}

	public BgbRegistrationAction moreRegisterUser(UserProfile userProfile){
		new RegistrationPage().moreRegistration(userProfile);
		return this;
	}

	public BgbRegistrationAction checkMarketingConsentInRegister(UserProfile userProfile){

		RegistrationPage regpage=new RegistrationPage();
		regpage.registerDetailsFirstPage(userProfile);
		regpage.verifyMarketingConsent(userProfile);
		regpage.openUrlandVerifyRegistration(userProfile);
		regpage.verifyAfterEncryptedUrl();
		new LegacyHomePage().BgbnavigateToLoginPage();
		LegacyLoginPage legacy = new LegacyLoginPage();
		legacy.BgbloginUser(userProfile);
		new  AccountSummaryPage().clickMPDLink(userProfile);
		regpage.verifyMarketingConsentOption("Checked");
		legacy.logOut();
		regpage.updateMarketingConsentOptionInDb(userProfile, "Y");
		legacy.BgbloginUser(userProfile);
		new  AccountSummaryPage().clickMPDLink(userProfile);
		regpage.verifyMarketingConsentOption("NotChecked");
		return this;
	}

	public BgbRegistrationAction verifyActivation28Days(UserProfile userProfile) {	
		new RegistrationPage()	
		.registerDetailsFirstPage(userProfile)
		.registerDetailsSecondPage(userProfile)
		.verifyActivationLink28Days(userProfile)
		.openUrlandVerifyRegistration(userProfile)
		.verifyExpiredLinkPage();

		return this;
	}

	public BgbRegistrationAction changeAccountStatusAndVerify(UserProfile userProfile) {

		new RegistrationPage()	
		    .changeStatus(userProfile);
		return this;
	}
	
	public BgbRegistrationAction AddNewStdUser(UserProfile userProfile){
		new MultiUserMultiViewPage()
		.enterValidData_StandardUser(userProfile);		
		new RegistrationPage()
		.openEncryptUrlandRegister1(userProfile);		
		return this;
	}
	public BgbRegistrationAction AddNewStdUserdata(UserProfile userProfile){
		 new RegistrationPage()
		.openEncryptUrlandRegister1(userProfile);		
		return this;
	}
	
	

	/**
	 * @param userProfile
	 */
	public BgbRegistrationAction enterValidData_StandardUser(UserProfile userProfile) {
		// TODO Auto-generated method stub
		
		new MultiUserMultiViewPage()
		.enterValidData_StandardUser(userProfile);	
		return this;

	}

	/**
	 * @param userProfile
	 */
	public BgbRegistrationAction EnterValid_SuperUserdata(UserProfile userProfile) {
		// TODO Auto-generated method stub
		new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);	
		return this;

	} 
	public BgbRegistrationAction AddNewSuperUser(UserProfile userProfile){
		new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);		
		new RegistrationPage()
		.openEncryptUrlandRegister1(userProfile);
	   	return this;

	} 
	public BgbRegistrationAction verifyAuditForPaperLessOption(UserProfile userProfile) {
		new RegistrationPage()
		.verifyAuditForPaperLessOption(userProfile);	
		return this;

	} 
	
	public BgbRegistrationAction registerdetailsthroughBusiness_paperLessOptionUncheck(UserProfile userProfile,String termsCheck) {

		new RegistrationPage()	
		.registerDetailsFirstPage(userProfile)
		.register_PaperLessOptionNotSelected(userProfile,termsCheck)
		.validateEmailValidationFlag(userProfile, "N")
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl()
		.validateEmailValidationFlag(userProfile, "Y"); 
		return this;
	}
	public BgbRegistrationAction verifyWhatCanIdoLink() {
		new RegistrationPage()
		.verifyWhatCanIdoLink();	
		return this;

	}
///////////////Digital WAVE//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public BgbRegistrationAction navigateToAccountDetailsPage() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.navigateToAccountDetailsPage();
	    return this; 
		
	}
	public BgbRegistrationAction verifyAccountDetailsPage(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountDetailsPage(userProfile);
	    return this; 
		
	    
	}
	public BgbRegistrationAction verifyAccountDetailsPageWithMisMatchEmailandPassword(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountDetailsPageWithMisMatchEmailandPassword(userProfile);
	    return this; 
		
	    
	}
	public BgbRegistrationAction cancelButton() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.cancelButton();
	    return this; 
		
	}
	
	public BgbRegistrationAction registerForAnOnlineAccount(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.registerForAnOnlineAccount(userProfile);
	    return this; 
		
	}
	
	public BgbRegistrationAction regisration_dynamic_page_cash_cheque(UserProfile userProfile){
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.regisration_dynamic_page_cash_cheque(userProfile);
		return this;
	}
	
	public BgbRegistrationAction last_payment_amount_page(UserProfile userProfile){
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.last_payment_amount_page(userProfile);
		return this;
	}
	public BgbRegistrationAction credit_card_no_last_four_digits(UserProfile userProfile){
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.credit_card_no_last_four_digits(userProfile);
		return this;
	}
	
	
	public BgbRegistrationAction registerForAnOnlineAccountNew(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.registerForAnOnlineAccountNew(userProfile);
	    return this; 
		
	}
	public BgbRegistrationAction RegistrationActivationEmail() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.RegistrationActivationEmail();
	    return this; 
		
	}
	public BgbRegistrationAction verifyAccountOverviewForSuperUserNew() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountOverviewForSuperUserNew();
	    return this; 
	}
	public BgbRegistrationAction Activate_EmailUrl(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.Activate_EmailUrl(userProfile);
	    return this; 
	}
	
	public BgbRegistrationAction registerForAnOnlineAccountNew01(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.registerForAnOnlineAccountNew01(userProfile);
	    return this; 
		
	}
	public BgbRegistrationAction collectiveAccountOverlay (){
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.collectiveAccountOverlay();
	    return this; 
		
	}
	public BgbRegistrationAction thankYouRegistered() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.thankYouRegistered();
	    return this; 
	}
	public BgbRegistrationAction VerifyError() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.VerifyError();
	    return this; 
	}
	public BgbRegistrationAction verifyEmailAlreadyRegisteredPage() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyEmailAlreadyRegisteredPage();
	    return this; 
	}
	
	public BgbRegistrationAction emailAddressFieldValidation() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.emailAddressFieldValidation();
	    return this; 
	}
	public BgbRegistrationAction verifyBreadCrumbInYourdetails() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyBreadCrumbInYourdetails();
	    return this; 
	}
	public BgbRegistrationAction passwordLessThanEightValidation() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.passwordLessThanEightValidation();
	    return this; 
	}
	public BgbRegistrationAction emailMisMatch() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.emailMisMatch();
	    return this; 
	}
	public BgbRegistrationAction passwordMisMatch() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.passwordMisMatch();
	    return this; 
	}
	public BgbRegistrationAction confirmPasswordFieldValidation() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.confirmPasswordFieldValidation();
	    return this; 
	}
	
	public BgbRegistrationAction verifyCallBackSection() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyCallBackSection();
	    return this; 
	}
	/*public BgbRegistrationAction verifyAuditEntryForEmail(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAuditEntryForEmail(userProfile);
	    return this; 
	}*/
	public BgbRegistrationAction verifyAuditEntryForEmailNew(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAuditEntryForEmailNew(userProfile);
	    return this; 
	}
	
	
	public BgbRegistrationAction verifyAuditEntryForSuperUser(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAuditEntryForSuperUser(userProfile);
	    return this; 
	}
	public BgbRegistrationAction verifyAuditEntryForSuperUserNew(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAuditEntryForSuperUserNew(userProfile);
	    return this; 
	}
	
	public BgbRegistrationAction resetYourPasswordSucessful(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.resetYourPasswordSucessful(userProfile);
	    return this; 
	}
	
	public BgbRegistrationAction verifyFeedBack() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyFeedBack();
	    return this; 
	}
	public BgbRegistrationAction paperlessOptout() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.paperlessOptout();
	    return this; 
	}
	public BgbRegistrationAction verifyConfirmationPageForSuperUserCSA() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyConfirmationPageForSuperUserCSA();
	    return this; 
	}
	
	public BgbRegistrationAction feedbackAuditValidataion(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.feedbackAuditValidataion(userProfile);
	    return this; 
	}
	public BgbRegistrationAction barclaysAuditValidataion(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.barclaysAuditValidataion(userProfile);
	    return this; 
	}
	public BgbRegistrationAction registerAuditValidataion(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.registerAuditValidataion(userProfile);
	    return this; 
	}
	public BgbRegistrationAction verifyAuditEntryForEmail(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAuditEntryForEmail(userProfile);
	    return this; 
	}
	public BgbRegistrationAction registerAuditValidataionNew(UserProfile userProfile) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.registerAuditValidataionNew(userProfile);
	    return this; 
	}
	
	public BgbRegistrationAction verifyAccountOverviewForStandardUser() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountOverviewForStandardUser();
	    return this; 
	}
	public BgbRegistrationAction verifyAccountOverviewForStandardUserNew() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountOverviewForStandardUserNew();
	    return this; 
	}
	public BgbRegistrationAction verifyAccountOverviewForSuperUser() {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.verifyAccountOverviewForSuperUser();
	    return this; 
	}
	public BgbRegistrationAction barclaysDeeplink(String Barclays) {
		RegistrationPage registrationpage = new RegistrationPage();
		registrationpage.barclaysDeeplink(Barclays);
	    return this; 
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public BgbRegistrationAction AddPaperlessBillingUserdata(UserProfile userProfile){
		/*new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);	*/	
		new RegistrationPage()
		.register_PaperLessOptionisSelected(userProfile,"yes");
	   	return this;
	}
	public BgbRegistrationAction Addnewuser_paperless(UserProfile userProfile){
		new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);		
		new RegistrationPage()
		.register_PaperLessOptionisSelected(userProfile,"yes");
	   	return this;
	}
	public BgbRegistrationAction AddNewSuperUser_paperaction(UserProfile userProfile){
		new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);		
		new RegistrationPage()
		.register_PaperLessOptionisSelected(userProfile,"nooo");
	   	return this;

	}
	public BgbRegistrationAction navigateToLookupUser() {
		CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
		csapage.clickLookupUser();
		return new BgbRegistrationAction();
	}
	public BgbRegistrationAction AddUrl_UpdateNewView_Name(UserProfile userProfile){
		 new RegistrationPage()
		.AddUrl_UpdateNewView_Name(userProfile);		
		return this;
	}
	
	public BgbRegistrationAction AddNewSuperUser_paperlessaction(UserProfile userProfile){
		new MultiUserMultiViewPage()
		.enterValidData_SuperUser(userProfile);		
		new RegistrationPage()
		.register_PaperLessOptionisSelected(userProfile,"yes");
		//.register_PaperLessOptionisSelectedNew(userProfile,"yes");
				
	   	return this;

	}
	
	public BgbRegistrationAction registerdetailsnew(UserProfile userProfile) {

		new RegistrationPage()
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister_CSA_New(userProfile);
		return this;
	}
	public BgbRegistrationAction CSApageverify(UserProfile userProfile) {

		new RegistrationPage()
		.verifyRegistrationPageByCsapending(userProfile)
		.CSAinvalidlinkpageverify();
		
		return this;
	}
	
}
