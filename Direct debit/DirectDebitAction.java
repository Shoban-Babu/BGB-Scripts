package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.DirectDebitPage;
import bg.framework.app.functional.page.Slingshot.ThankYouPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

public class DirectDebitAction {

	public  String billingaddress=null;
	public DirectDebitAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}

	public DirectDebitAction BgbloginDetails(DirectDebit directDebit) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		legacyLoginPage.BgbDirectDebitLogin(directDebit);
		return this;
	}

	public DirectDebitAction BgbverifyAfterLogin(DirectDebit directDebit) {
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}

	public DirectDebitAction selectAccount(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		 directdebit.selectAccountForDirectDebit(directDebit);
		return this;
	}
	public DirectDebitAction directDebitForm(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.directDebitForm(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction verifyPaymentLink(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();
		return this;
	}
	public DirectDebitAction directDebitLinkAndBackToSummary(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.clickBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		//directdebit.directDebitForm(directDebit);
		return this;
	}
	public DirectDebitAction DdSetUpAfter3TimesInvalidData(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("NO");
		directdebit.invalidDataTodirectDebitForm();
		// verify error message,after error message back to account summary and verify the same
		//account can be DD
		return this;
	}
	
	public DirectDebitAction amendDirectDebitPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickAmendFixedDirectDebit();
		//directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("YES");
		//directdebit.verifyExistingTextInDdForm();
		return this;
	}
	
	public DirectDebitAction amendFixedDirectDebitPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.AmendDirectDebit();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("YES");
		//directdebit.verifyExistingTextInDdForm();
		return this;
	}
	public DirectDebitAction dDLinkAndBackToSummaryInAmendPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.clickBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		//new  AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		return this;
	}
	public DirectDebitAction submitAndEditFieldsInAmendPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
	//	directdebit.clickContinueButtonInAmendPage1();
		//directdebit.clickEditTheseFields(); (Edit field is removed)
		
		directdebit.directDebitForm(directDebit);
		directdebit.summarypageVerification(directDebit);
		directdebit.clickDirectDebitAgreementLink();
				
		return this;
	}
	public DirectDebitAction submitAmendVariableDirectDebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyvariableAmendConfirmationText();
		//directdebit.verifyAudit_VariableDirectdebitAmenduccess(directDebit);
	//	directdebit.verifyAudit_Directdebitemailsentstatus(directDebit);
		directdebit.AmendVariableBreadCrumb();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction submitDirectDebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyvariableAmendConfirmationText();
		directdebit.verifyAudit_VariableDirectdebitAmenduccess(directDebit);
	//	directdebit.verifyAudit_Directdebitemailsentstatus(directDebit);
		directdebit.AmendVariableBreadCrumb();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction submitAmendFixedDirectDebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.AmendFixedConfirmationPage();
		directdebit.verifyAudit_AmendFixedDirectDebitSetupSuccess(directDebit);
		directdebit.verifyAudit_Directdebitemailsentstatus(directDebit);
		directdebit.AmendFixedBreadCrumb();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction submitDirectDebitWithSameInformation(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickContinueButtonInAmendPage1();
		directdebit.verifyBankDetailsForSameInfo();
		return this;
	}
	public DirectDebitAction verifyAmendBefore28Days(){

		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.verifyOverlayAfterManageDirectDebitLink();
		directdebit.verifyOverlayErrorMessageFor28Days();
		return this;
	}
	public DirectDebitAction verifyDirectDebitAgreeementOverlay(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("NO");
		directdebit.directDebitForm(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		return this;
	}
	public DirectDebitAction accSummaryAfterBackToYourAccount(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickBackToYourAccount();
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		directdebit.clickManageDirectDebitLink();
		directdebit.verifyOverlayErrorMessageFor28Days();
		return this;
	}
	public DirectDebitAction verifyYourAccountPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitForm(directDebit);
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction backToSelectAccountVerifyOverlay(DirectDebit directDebit){
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.verifyOverlayErrorMessageFor28Days();
		return this;
	}
	public DirectDebitAction invalidBankDetails(DirectDebit directDebit){
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("NO");
		directdebit.enterInvalidAccHolderName(directDebit);
		directdebit.enterInvalidSortCode(directDebit);
		directdebit.enterInvalidBankAccName(directDebit);
		directdebit.enterInvalidBankAccName(directDebit);
		directdebit.verifyLockErrorMessage();
		return this;
	}
	public DirectDebitAction verifyDdInvalidDetails24hours(DirectDebit directDebit){
	//	new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		DirectDebitPage directdebit=new  DirectDebitPage();
		//directdebit.clickDirectDebitLink();
		//directdebit.SelectSetupVariableDirectDebitLink();
		directdebit.clickManageDirectDebitPodLink();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.enterInvalidAccHolderName(directDebit);
		directdebit.enterInvalidSortCode(directDebit);
		directdebit.enterInvalidBankAccName(directDebit);
		directdebit.enterIncorrectDetails(directDebit);
		directdebit.verifyLockErrorMessage();
		directdebit.clickCancelButton();
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.verifyOverlay24Hours();
		return this;
	}
	public DirectDebitAction verifyAmendDdInvalidDetails24hours(DirectDebit directDebit){
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.enterInvalidAccHolderName(directDebit);
		directdebit.enterInvalidSortCode(directDebit);
		directdebit.enterInvalidBankAccName(directDebit);
		directdebit.enterIncorrectDetails(directDebit);
		directdebit.verifyLockErrorMessage();
		directdebit.clickCancelButton();
		new AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		directdebit.clickManageDirectDebitLink();
		directdebit.verifyOverlay24Hours();
		return this;
	}
	public DirectDebitAction clickAcordian() throws Exception{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickAccordian1();
		directdebit.clickAccordian2();
		return this;		
	}
	public DirectDebitAction verifyBankDetailsInIsu(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.verifyDataThroughQTP(directDebit,billingaddress);

		return this;

	}
	public DirectDebitAction directDebitForDifferentAccount(DirectDebit directDebit){
		new  AccountSummaryPage().selectAccountForDirectDebit(directDebit);

		return this;
	}
	public DirectDebitAction directDebitForDiffAccounts(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.verifyDirectDebitForDiffAccount(directDebit);

		return this;
	}
	public DirectDebitAction verifyThankYouSurveyPage(DirectDebit directDebit){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.selectFeedbackOption(directDebit.getfeedbackoption());
		thankyoupage.feedbackReason(directDebit.getFeedbacktext());
		thankyoupage.clickFeedbackSendButton();
		thankyoupage.verifySurveyTableInDb(directDebit.getEmail(),"THANKYOU",directDebit.getfeedbackoption(),directDebit.getFeedbacktext());
		thankyoupage.closeThankYouOverlay();
		return this;
	}
	public DirectDebitAction verifyErromessage(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		//directdebit.emailErrorMessageValidation();
		directdebit.bpnumberErrorMessageValidation();
		return this;
	}
	public DirectDebitAction submitNpsSurvey(DirectDebit directDebit){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(directDebit.getfeedbackoption());
		thankyoupage.selectNpsFeedbackText(directDebit.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(directDebit.getEmail(),"NPS",directDebit.getfeedbackoption(),directDebit.getFeedbacktext());
		return this;
	}
	public DirectDebitAction directDebitsubmitNpsSurvey(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitForm(directDebit);
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(directDebit.getfeedbackoption());
		thankyoupage.selectNpsFeedbackText(directDebit.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(directDebit.getEmail(),"NPS",directDebit.getfeedbackoption(),directDebit.getFeedbacktext());
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifySetupConfirmationText();
		return this;
	}
	public DirectDebitAction verifyAccountExist(DirectDebit directDebit){

		return this;
	}
	public DirectDebitAction submitAmendDebit(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyAmendConfirmationText();
		return this;
	}
	
//************************************************************************RP3***************************************************************************************
	public DirectDebitAction verifyVariableDirectdebitPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		//directdebit.clickDirectAmendDebitLink();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
		directdebit.selectDirectDebitForBank("YES");
	//	directdebit.clickVariableDirectDebitLink();
		directdebit.directDebitForm(directDebit);
	//	directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.summarypageVerification(directDebit);
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyAudit_VariableDirectdebitsetupsuccess(directDebit);
		// directdebit.verifyAudit_Directdebitemailsentstatus(directDebit); //mine
	//	directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifyVariableSetupConfirmationText();
		directdebit.AmendVariableBreadCrumb();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction verifySetupVariableDirectdebitPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		//directdebit.clickDirectAmendDebitLink();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
	//	billingaddress=directdebit.BillingAddress();
		directdebit.selectDirectDebitForBank("YES");
	//	directdebit.clickVariableDirectDebitLink();
		directdebit.directDebitForm(directDebit);
	//	directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.summarypageVerification(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		//directdebit.verifyAudit_VariableDirectdebitsetupsuccess(directDebit);
		// directdebit.verifyAudit_Directdebitemailsentstatus(directDebit); //mine
	//	directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifyVariableSetupConfirmationText();
		//directdebit.confirmationpagePodLinks();
		directdebit.Breadcrumbnavigationlink();		
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction verifySetupVariableDirectdebitPageSurvey(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();	
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
		directdebit.selectDirectDebitForBank("YES");	
		directdebit.directDebitForm(directDebit);
		
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(directDebit.getfeedbackoption());
		thankyoupage.selectNpsFeedbackText(directDebit.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(directDebit.getEmail(),"NPS",directDebit.getfeedbackoption(),directDebit.getFeedbacktext());
		
		directdebit.summarypageVerification(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyAudit_VariableDirectdebitsetupsuccess(directDebit);		
		directdebit.verifyVariableSetupConfirmationText();
		directdebit.Breadcrumbnavigationlink();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction prepopulated_verifyVariableDirectdebitPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.clickVariableDirectDebitLink();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitForm(directDebit);
		//directdebit.directdebitformpagevalidataion(directDebit);
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
	//	directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText();
		directdebit.clickBackToYourAccount();
		return this;
	}
	public DirectDebitAction Setup_FixeddirectDebitForm(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.clickFixedDirectDebitLink();
		//directdebit.pleasecallusOverlay_FixedDirectDebit();
		//billingaddress=directdebit.BillingAddress();
		directdebit.selectDirectDebitForBank("YES");
		//directdebit.verifyServicePromo();
		directdebit.directDebitFormpaymentdate(directDebit);
		directdebit.directDebitForm(directDebit);		
		directdebit.clickDirectDebitAgreementLink();
	//	directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit(); //have to update
	//	directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifyAudit_FixedDirectDebitSetupSuccess(directDebit);
		directdebit.verifyAudit_Directdebitemailsentstatus(directDebit);
		directdebit.verifySetupConfirmationText();
	//	directdebit.online_Thankyouemail_Auditentry();
		directdebit.FDDclickBackToYourAccount();
		directdebit.confirmationpagePodLinks();		
		return this;
	}
		
		public DirectDebitAction verifyVariableamendDirectDebitPage(DirectDebit directDebit){
			DirectDebitPage directdebit=new  DirectDebitPage();
	//	directdebit.clickManageDirectDebitLink();
	//	directdebit.closeOverlay();
		directdebit.clickManageDirectDebitPodLink();
		billingaddress=directdebit.BillingAddress();
		directdebit.selectDirectDebitForBank("YES");		
		//directdebit.verifyExistingTextInDdForm();
		return this;
	}
	public DirectDebitAction SetupFixedDirectDebitPagebackandcancel(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.SetupFixedDDLandingPagebackandcancel();		
		directdebit.directDebitForm(directDebit);
		directdebit.SetupFixedDDLandingPagebackandcancel();	
		return this;
	}
	public DirectDebitAction verifyFDBLinkAndBackToSummary(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction verifyFixedVariableLinkAndBackToSummary(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction verifybackandcancel(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction AmendVariabledirectdebitlink(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		//directdebit.clickDirectDebitLink();
		directdebit.clickManageDirectDebitPodLink();	
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitForm(directDebit);
	//	directdebit.editdetails();
	//  directdebit.directDebitForm(directDebit);
		directdebit.backandcancel(directDebit);
		return this;
	}
	public DirectDebitAction SetupVariabledirectdebitlink(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitForm(directDebit);
	  //directdebit.editdetails();
	  //directdebit.directDebitForm(directDebit);
		directdebit.backandcancel(directDebit);
		return this;
	}
	public DirectDebitAction SetupFixeddirectdebitlink(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickFixedDirectDebitLink();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.directDebitFormpaymentdate(directDebit);
		directdebit.directDebitForm(directDebit);
	//	directdebit.editdetails();
	//	directdebit.directDebitForm(directDebit);
		directdebit.backandcancel(directDebit);
		return this;
	}
	public DirectDebitAction AmendVariableDirectdebitpodlink(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitPodLink();	
		
		return this;
	}
	
	public DirectDebitAction verifyVDBLinkAndBackToSummary(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.clickVariableDirectDebitLink();
		directdebit.selectDirectDebitForBank("NO");
		//directdebit.BillingAddressVerification(); // have to verify the SAP
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();	
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction Prepopulated_directDebitForm(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.directdebitformpagevalidataion(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText_page();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction directDebitFormlinknavigation(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.directDebitForm(directDebit);
		directdebit.directdebitlinknavigation();

		return this;
	}
	public DirectDebitAction collectiveAcctOverlay(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.collectiveAcctOverlay();
		return this;
	}
	
	public DirectDebitAction ManageAccount(UserProfile userProfile)
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.ManageAccount(userProfile);
		return this;
	}
	public DirectDebitAction validatPDvWarrentuser()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.validatePDvWarrentusercustomer();
		return this;
	}
	public DirectDebitAction validatecollectivecustomer()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.ValidateCollectiveuser();
		return this;
	}
	
	
	public DirectDebitAction AmendDirectDebit()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.AmendDirectDebit();
		return this;
	}
	public DirectDebitAction ValidateAmendvariablesetup(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.AmendDirectDebit();	
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");		
		directdebit.ValidatedirectDebitFormOverlay(directDebit);
		directdebit.youraccountbreadcrumb();
		directdebit.selectAccountForDirectDebit(directDebit);
		directdebit.AmendDirectDebit();	
		directdebit.PleasecallusOverlay();	
		return this;
	}
	public DirectDebitAction ValidateAmendfixedsetup(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.clickFixedDirectDebitLink();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.ValidatedirectDebitFormOverlay(directDebit);
		directdebit.youraccountbreadcrumb();
		directdebit.selectAccountForDirectDebit(directDebit);
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.PleasecallusOverlay();		
		return this;
	}
	public DirectDebitAction validateSetupFixedDirectdebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.clickFixedDirectDebitLink();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");		
		directdebit.directDebitFormpaymentdate(directDebit);
		directdebit.ValidatedirectDebitFormOverlay(directDebit);
		directdebit.youraccountbreadcrumb();
		directdebit.selectAccountForDirectDebit(directDebit);
		directdebit.clickDirectDebitLink(directDebit);		
	//	directdebit.clickFixedDirectDebitLink();
		directdebit.PleasecallusOverlay();			
		return this;
	}
	public DirectDebitAction validateSetupvariableDirectdebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.ValidatedirectDebitFormOverlay(directDebit);
		directdebit.youraccountbreadcrumb();
		directdebit.selectAccountForDirectDebit(directDebit);
		directdebit.clickDirectDebitLink(directDebit);		
		directdebit.PleasecallusOverlay();			
		return this;
	}
	public DirectDebitAction clickDirectDebitLink()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitPodLink();	
		return this;
	} 
	public DirectDebitAction ClickSetupVariableDirectdebit(DirectDebit directDebit)
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.SelectSetupVariableDirectDebitLink();
		return this;
	}	
	public DirectDebitAction clickSetupVariableDirectDebitLink()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.SelectSetupVariableDirectDebitLink();
		return this;
	} 
	public DirectDebitAction clickSetupFixedDirectDebitLink()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickFixedDirectDebitLink();
		return this;
	} 
	public DirectDebitAction ErrorValidationAmendwithin28days()
	{
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.ErrorValidationAmendwithin28days();
		return this;
	}
	public DirectDebitAction DirectDebitLinkAndBackToSummaryInAmendPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();		
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction dDLinkAndBackToSummaryInamendPage(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.clickBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		//new  AccountSummaryPage().selectAccountForDirectDebit(directDebit);
		return this;
	}
	public DirectDebitAction amendDirectDebitPagebackandcancel(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickManageDirectDebitLink();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.AmendFixedDDLandingPagebackandcancel();
		return this;
	}
	public DirectDebitAction PleasecallusOverlay(){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.PleasecallusOverlay();	
		return this;
	}	
	public DirectDebitAction ValidateDDFixedbankdetails(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.ValidatedirectDebitForm(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();		
		return this;
	}
	public DirectDebitAction Prepopulated_AmenddirectDebitForm(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.AmendDirectDebit();
		directdebit.closeOverlay();
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		//directdebit.clickBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();
		return this;
	}
	public DirectDebitAction Prepopulated_AmenddirectDebitbackandcancel(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.directdebitformpagevalidataion(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText_page();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction Prepopulated_AmenddirectDebiteditdetails(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.AmendFixedDDEditdetailsPage(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText_page();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction AmendVariableDirectdebit(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
	//	directdebit.BillingAddressVerification(); // have to verify the SAP
		directdebit.selectDirectDebitForBank("NO");
		directdebit.verifyDirectDebitLink();
		directdebit.verifyBackToAccountSummary();
		directdebit.clickBackToAccountSummary();
		directdebit.verifyAccountSummaryTitle();		
		return this;
	}
	
	public DirectDebitAction prepopulated_verifyVariableDDBackandcancel(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.clickVariableDirectDebitLink();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.ValidatedirectDebitForm(directDebit);
		directdebit.directdebitformpagevalidataion(directDebit);
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();	
		return this;
	}
	public DirectDebitAction ValidateAmendDDVariablebankdetails(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.ValidatedirectDebitForm(directDebit);
		directdebit.directdebitformpagevalidataion(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.clickSetupDirectDebit();		
		return this;
	}
	public DirectDebitAction AmendVariabledirectDebitdetails(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.AmendFixedDDEditdetailsPage(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.clickSetupDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText_page();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction AmeneVariableDDBackandcancel(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.selectDirectDebitForBank("YES");
		directdebit.clickVariableDirectDebitLink();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		return this;
	}
	public DirectDebitAction AmendVariabledirectDebiteditdetails(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.AmendFixedDDEditdetailsPage(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();		
		directdebit.clickAmendDirectDebit();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifyvariableAmendConfirmationText();
		directdebit.confirmationpagePodLinks();
		directdebit.clickBackToYourAccount();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
	public DirectDebitAction AmendFixedDDErrorValidation(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();		
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.pleasecallusOverlay_FixedDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.ValidatedirectDebitForm(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.AmendDirectDebit();
		directdebit.AmendsamebankDDerrorvalidation();		
		return this;
	}
	
	public DirectDebitAction AmendVaribleDDErrorValidation(DirectDebit directDebit){
		DirectDebitPage directdebit=new  DirectDebitPage();
		directdebit.clickDirectDebitLink(directDebit);
		directdebit.AmendDirectDebit();
		directdebit.selectDirectDebitForBank("YES");
		directdebit.verifyServicePromo();
		directdebit.Directdebitformdata_entry(directDebit);
		directdebit.AmendFixedDDEditdetailsPage(directDebit);
		directdebit.clickDirectDebitAgreementLink();
		directdebit.verifyDirectDebitAgreeOVerlay();
		directdebit.checkTermsAndConditions();
		directdebit.AmendFixedDDLandingPageBackandcancel();
		directdebit.AmendDirectDebit();
		directdebit.AmendsamebankDDerrorvalidation();
		directdebit.verifyOverlayAfterDirectDebit();
		directdebit.verifySetupConfirmationText_page();
		/*directdebit.clickPaymentInfoLink();
		directdebit.verifyTextPaymentInfoClicked();*/
		return this;
	}
}

