package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.CustomerServiceAgentPage;
import bg.framework.app.functional.page.Slingshot.PaperlessBillingPage;
import bg.framework.app.functional.page.Slingshot.RegistrationPage;
import bg.framework.app.functional.page.Slingshot.SapCrmPage;
import bg.framework.app.functional.page.Slingshot.ThankYouPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;


public class PaperlessBillingAction {
	
	public PaperlessBillingAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}
	public PaperlessBillingAction BgbloginDetails(UserProfile userProfile) {
		
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.PBDirectDebitLogin(userProfile);		
		return this;
	}
public PaperlessBillingAction PBDirectDebitLogin_Secondlogin(UserProfile userProfile) {
		
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.PBDirectDebitLogin_secondtime(userProfile);		
		return this;
	}	
	public PaperlessBillingAction BgbverifyAfterLogin() {
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}

	public PaperlessBillingAction gloablpaperlessnotsetupAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.gloablpaperlessnotsetup();
		return this;
	}
	public PaperlessBillingAction gloablpaperlesssetupAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.gloablpaperlesssetup();
		return this;
	}
	public PaperlessBillingAction specificpaperlesssetupAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.specificpaperlesssetup();
		return this;
	}
	public PaperlessBillingAction gloablpaperetupAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.globalpapersetup();
		return this;
	}
	public PaperlessBillingAction acctspecificpaperlesssetupcloseoverlay(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.acctspecificpaperlesssetupcloseoverlay();
		return this;
	}
	
	public PaperlessBillingAction specificpapersetupAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.specificpaperbillsetup();
		return this;
	}
	public PaperlessBillingAction PaperlessJouneyverification(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.PaperlessJouneyverification();
		return this;
	}
	
	public PaperlessBillingAction gloablpaperlesssetupcancelandcloseAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.gloablpaperlesssetupcloseoverlay();
		return this;
	}
	public PaperlessBillingAction VerifyAudit_EmailtriggeredforSpecificAcctPaperBilling(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.VerifyAudit_EmailtriggeredforSpecificAcctPaperBilling(userProfile);
		return this;
	}
	public PaperlessBillingAction VerifyAudit_EmailtriggeredforPaperlessBilling(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.VerifyAudit_EmailtriggeredforSpecificAcctPaperlessBilling(userProfile);
		return this;
	}
	public PaperlessBillingAction PBAcctSpecificoptedintopaperlesscancelandcloseAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.gloablpaperlesssetupcloseoverlay();
		return this;
	}
	public PaperlessBillingAction gloablpaperlessnotsetupcancelAndcloseAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.gloablpaperlessnotsetupcancelAndclose();
		return this;
	}
	public PaperlessBillingAction paperlesssetupforindivialacctsAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.paperlesssetupforindivialaccts();
		return this;
	}
	public PaperlessBillingAction VerifyAudit_EmailtriggeredAction(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.VerifyAudit_Emailtriggered(userProfile);
		paperlesspace.VerifyAudit_Emailtriggeredsaveddetails(userProfile);
		return this;
	}
	public PaperlessBillingAction paperlessBillsforAcctSpecificoptedintopaperlessAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.paperlessBillsforAcctSpecificoptedintopaperless();
		return this;
	}
	public PaperlessBillingAction paperlessBillsforAcctSpecificoptedoutofpaperlessAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.paperlessBillsforAcctSpecificoptedoutofpaperless();
		return this;
	}
	public PaperlessBillingAction papaerlesessetupWhatisThisverlayAction(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.papaerlesessetupWhatisThisverlay();
		return this;
	}
	public PaperlessBillingAction PBforAcctSpecificoptedintopaperlesswhatisthisOverlay(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.papaerlesessetupWhatisThisverlay();
		return this;
	}

	public PaperlessBillingAction verifyManageAccountLink(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.verifyManageAccount(userProfile);
		return this;
	}
	
/*	public PaperlessBillingAction PaperlessRegistrationAction(UserProfile userProfile)
	{		
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToBgbRegistrationPage();
		new RegistrationPage()
		 	.registerDetailsFirstPage(userProfile)
		    .registerDetailsSecondPage_paperlesschkbox(userProfile,"superorfullaccess")
		    .openUrlandVerifyRegistration(userProfile)
		    .verifyAfterEncryptedUrl();		
			return this;	
			
			
	}*/
	public PaperlessBillingAction PaperlessRegistrationPage(UserProfile userProfile)
	{
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToCsaRegistrationPage();
		CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
		csapage.clickRegisteraUser();
		new RegistrationPage()
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister(userProfile)
		.registerFirstPageCsa(userProfile)
		.registerDetailsSecondPage_paperlesschkbox(userProfile,"superorfullaccess","No")
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl();
		return this;
		
	}
	
	public PaperlessBillingAction PaperlessRegistrationPage_Enterprise(UserProfile userProfile)
	{
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToCsaRegistrationPage();
		CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
		csapage.clickRegisteraUser();
		new RegistrationPage()
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister(userProfile)
		.registerFirstPageCsa(userProfile)
		.registerDetailsSecondPage_paperlesschkbox(userProfile,"Enterprise","yes")
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl();
		return this;
		
	}
	public PaperlessBillingAction PaperlessRegistrationPage_corp(UserProfile userProfile)
	{
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToCsaRegistrationPage();
		CustomerServiceAgentPage csapage = new CustomerServiceAgentPage();
		csapage.clickRegisteraUser();
		new RegistrationPage()
		.verifyRegistrationPageByCsa(userProfile)
		.openEncryptUrlandRegister(userProfile)
		.registerFirstPageCsa(userProfile)
		.registerDetailsSecondPage_paperlesschkbox(userProfile,"corporate","yes")
		.openUrlandVerifyRegistration(userProfile)
		.verifyAfterEncryptedUrl();
		return this;
		
	}
	public PaperlessBillingAction PaperlessRegistration_EIcustomerAction(UserProfile userProfile)
	{		
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToBgbRegistrationPage();
		new RegistrationPage()
		 	.registerDetailsFirstPage(userProfile)
		  //  .registerDetailsSecondPage_paperlesschkbox(userProfile,"abc")
		    .openUrlandVerifyRegistration(userProfile)
		    .verifyAfterEncryptedUrl();		
			return this;		
	}
	public PaperlessBillingAction PaperlessRegistration_EDI_TVIcustomer(UserProfile userProfile)
	{		
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToBgbRegistrationPage();
		new RegistrationPage()
		 	.registerDetailsFirstPage(userProfile)
		//    .registerDetailsSecondPage_paperlesschkbox(userProfile,"EDITVI")
		    .openUrlandVerifyRegistration(userProfile)
		    .verifyAfterEncryptedUrl();		
			return this;		
	}
	public PaperlessBillingAction paperlessRegistration_corporate(UserProfile userProfile)
	{		
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.navigateToBgbRegistrationPage();
		new RegistrationPage()
		 	.registerDetailsFirstPage(userProfile)
		    .registerDetailsSecondPage_paperlesschkbox(userProfile,"corporate","no")
		    .openUrlandVerifyRegistration(userProfile)
		    .verifyAfterEncryptedUrl();		
			return this;		
	}
	public PaperlessBillingAction verifyLogin(UserProfile userProfile){
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage();
		LegacyLoginPage legacy= new LegacyLoginPage();
		legacy.BgbloginUser(userProfile);
		legacy.logOut();
		return this;

	}	
	public PaperlessBillingAction morethan15acctsglobally(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.morethan15accounts_globally();
		return this;
	}

	
	public PaperlessBillingAction Validatecorporateuser(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.corporateVerification();
		return this;
	}
	public PaperlessBillingAction ValidateEDITVIuser(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.EDITVIVerification();
		return this;
	}
	public PaperlessBillingAction morethan15Accts(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.searchBy(userProfile);
		return this;
	}
	
	public PaperlessBillingAction paperlesslink(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.paperlesslink(userProfile);
		return this;
	}
	public PaperlessBillingAction morethan15acctsacctgloabllevel(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.morethan15acctsacctoverview();
		return this;
	}	
	
	public AccountSummaryAction validatesAccountOverviewPageForContractAccountNumberSearch(UserProfile userProfile){
		AccountOverviewPage accOverviewpage =   new AccountOverviewPage();
		accOverviewpage .selectContractAccountAndVerifySearchFunctionality(userProfile);		 
		return new AccountSummaryAction();	 
	}
	public PaperlessBillingAction verifyThankYouSurveyPage(){
		PaperlessBillingPage.verifyThankyouInDb();		
		return this;
	}
	public PaperlessBillingAction verifyNPSSurveyPage(){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.submitNpsSurvey();		
		return this;
	}
	
	public PaperlessBillingAction VerifySAPISU_PBChange(UserProfile userProfile){
		PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
		paperlesspace.VerifySAPISU_PBChange(userProfile);		
		return this;
	}
	
	public PaperlessBillingAction submitNpsSurvey(UserProfile userProfile){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(userProfile.getFeedbackoption());
		thankyoupage.selectNpsFeedbackText(userProfile.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(userProfile.getEmail(),"NPS",userProfile.getFeedbackoption(),userProfile.getFeedbacktext());
		return this;
	}
	public PaperlessBillingAction VerifySAPCRM_PBChange(CrmUserProfile crmuserProfile, UserProfile userProfile,String paperstatus){
		SapCrmPage sapcrmpage=new SapCrmPage();
		sapcrmpage.openCRMUrl();
		sapcrmpage.enterLoginDetails(crmuserProfile);
		sapcrmpage.searchCrmFieldsforpaperbilling_acctspecific(crmuserProfile,userProfile,paperstatus);
		return this;
	}	
	
}
