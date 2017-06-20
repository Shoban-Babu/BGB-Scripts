package bg.framework.app.functional.action.Slingshot;


import java.util.Arrays;
import java.util.ArrayList;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.OnDemandBillingPage;
import bg.framework.app.functional.page.Slingshot.SapCrmPage;
import bg.framework.app.functional.page.Slingshot.SubmitMeterReadPage;
import bg.framework.app.functional.page.Slingshot.ThankYouPage;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountOverviewPage;
import bg.framework.app.functional.page.Slingshot.StatementOfAccountPage;
import bg.framework.app.functional.page.Slingshot.ViewBillPage;
import bg.framework.app.functional.page.common.LegacyHomePage;
import bg.framework.app.functional.page.common.LegacyLoginPage;
import bg.framework.app.functional.util.OnlineDBConnector;

public class SubmitMeterReadAction {

	public SubmitMeterReadAction BgbnavigateToLogin() {
		LegacyHomePage legacyHomePage = new LegacyHomePage();
		legacyHomePage.BgbnavigateToLoginPage(); 
		return this;
	}

	/*public SubmitMeterReadAction verifyAnonymousSAPGasCustomer(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();	
		 return this;
	}*/
	public SubmitMeterReadAction BgbloginDetails(SMRAccountDetails smrProfile) {
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		System.out.println(smrProfile.getEmail());
		legacyLoginPage.BgbLoginEmail(smrProfile.getEmail());
		legacyLoginPage.BgbLoginPassword(smrProfile.getPassword());
		legacyLoginPage.BgbClickSubmit();
		return this;
	}

	public SubmitMeterReadAction BgbverifyAfterLogin() {
		new  AccountSummaryPage().AccountOverviewVerification();
		return this;
	}

	public SubmitMeterReadAction clickSmrGlobalLink(){
		new  SubmitMeterReadPage().clickGlobalSmrLink();
		return this;
	}
	public SubmitMeterReadAction globalSubmitMeterRead(SMRAccountDetails smrProfile){
		new  SubmitMeterReadPage().searchBy(smrProfile);
		return this;
	}
	public SubmitMeterReadAction enterMeterDial(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchBy(smrProfile);
		smrpage.getDialsCountAndSubmitMeterRead(smrProfile,0);		
		smrpage.verifyGasConfirmation();
		return this;
	}
	/*public SubmitMeterReadAction selectSubmitMeterRead(SMRAccountDetails smraccount){
		new SubmitMeterReadPage().enterMeterRead(smraccount);
		return this;
	}*/
	public SubmitMeterReadAction selectSubmitMeterRead(SMRAccountDetails smrProfile){
		new  AccountSummaryPage().selectSmrForAccount(smrProfile);
		return this;
	}

	public SubmitMeterReadAction verifyAnonymousSAPGasCustomer(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		//smrpage.openSMRPage();
		smrpage.enterValidData_Anonymous(smrProfile);
		smrpage.getActualReadDate();
		smrpage.getPreviousMeterRead();
		smrpage.getDialsCountAndSubmitMeterRead(smrProfile);
		smrpage.verifyGasConfirmation();	
		return this;
	}

	public SubmitMeterReadAction verifyAndValidateSMRTitleFieldContent(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyAndValidateSMRTitleFieldContent(smrProfile);	
		return this;
	}
	public SubmitMeterReadAction openSMRpage(String typeOfAccount){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.openSMRPage(typeOfAccount);
		return this;
	}
	public SubmitMeterReadAction openSMRpageCRGas(String typeOfAccount){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.openSMRPageCR(typeOfAccount);
		return this;
	}
	public SubmitMeterReadAction openSMRpageforemailurl(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.opensmrpageforemailurl(smrProfile);
		return this;
	}
	public SubmitMeterReadAction openSMRpageCRElec(String typeOfAccount){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.openSMRPageCR(typeOfAccount);
		return this;
	}
	public SubmitMeterReadAction verifyServiceDeskCustomerGas(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		String meterReadValue = smrpage.verifyServiceDeskCustomerSMR(smrProfile);
		smrpage.verifyLEADTableForAnonymous(smrProfile,meterReadValue);
		return this;
	}
	public SubmitMeterReadAction verifyElectricityServiceDeskCustomer(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterElectricityData(smrProfile);
		String dayValue = smrpage.verifyElectricityMeterReads();
		smrpage.verifyLEADTableForAnonymous(smrProfile,dayValue);
		return this;
	}

	public SubmitMeterReadAction Bgblogout() {
		new  AccountSummaryPage().logout();
		return this;
	}
	public SubmitMeterReadAction unsuccessfulMeterReads(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchBy(smrProfile);
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Exceedvalue");
		smrpage.verifyImplausibleOverlay();
		smrpage.searchBy(smrProfile);
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Negativevalue");
		smrpage.verifyImplausibleOverlay();
		/*smrpage.getImpausibleErrorMsg();
		smrpage.verifyOverlayAndClose();*/
		//smrpage.verifyImplausibleOverlay();
		smrpage.searchBy(smrProfile);
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Exceedvalue");
		//smrpage.forceSubmit();
		smrpage.verifyOverlayAndSubmit();
		smrpage.verifyOverlayAndClose();
		smrpage.verifyGasConfirmation();
		return this;
	}
	public SubmitMeterReadAction verifyMeterReadAfterRelogin(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchBy(smrProfile);
		smrpage.verifyMeterAfterReglogin(smrProfile);
		return this;
	}
	public SubmitMeterReadAction verifyBackButtonInGlobalSmrPage() {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.clickBackLink();
		smrpage.verifyAccountSummaryTitle();
		return this;
	}
	public SubmitMeterReadAction verifySearchByInAccountSmrPage(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySearchByDropdown();
		smrpage.searchBy(smrProfile);
		return this;
	}
	public SubmitMeterReadAction verifyErrorMsgMprnNumber() {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.validateMprnNumber();
		return this;
	}
	public SubmitMeterReadAction verifyDataInSapIsu(SMRAccountDetails smrProfile){
		new  SubmitMeterReadPage().verifyDataThroughQTP(smrProfile);
		return this;
	}
	public SubmitMeterReadAction verifyImplausibleReads(SMRAccountDetails smrProfile){
        SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
        smrpage.getActualReadDate();
        smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
        smrpage.overlay();
        //smrpage.verifyOverlayAndClose();
 /*     smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Negativevalue");
        smrpage.getImpausibleErrorMsg();
        //smrpage.verifyOverlayAndClose();
        smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Exceedvalue");
        smrpage.verifyOverlayAndSubmit();
        //smrpage.verifyOverlayAndClose();
*/   //     smrpage.verifyGasConfirmation();
        return this;
 }
	public SubmitMeterReadAction enterAnonymousGasCustomerData(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterValidData_Anonymous(smrProfile);
		return this;
	}
	/*public SubmitMeterReadAction dbVerification(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyLEADTableForAnonymous(smrProfile);
		return this;
	}*/
	public SubmitMeterReadAction verifyBackToYourAccountLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyBackToYourAccountLink();
		return this;
	}
	public SubmitMeterReadAction verifySapIsu(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySAPForAnonymousSAPCustomer(smrProfile,"s","d");
		return this;
	}
	public SubmitMeterReadAction verifyMeterReadConfirmation(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGasConfirmation();		
		return this;
	}
	public AccountSummaryAction verifyAccountDetails(SMRAccountDetails smrProfile) {
		//new LegacyLoginPage().loginUser(userProfile);
		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.AccountSummaryVerification(smrProfile);
		accountsummary.getCustomerAddressAndAccountNumber(smrProfile);
		accountsummary.getAccountBalanceAndTypeOfAccount();
		return new AccountSummaryAction();
	}
	public SubmitMeterReadAction verifyThankYouSurveyPage(SMRAccountDetails smrProfile){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.selectFeedbackOption(smrProfile.getfeedbackoption());
		thankyoupage.feedbackReason(smrProfile.getFeedbacktext());
		thankyoupage.clickFeedbackSendButton();
		thankyoupage.verifySurveyTableInDb(smrProfile.getEmail(),"THANKYOU",smrProfile.getfeedbackoption(),smrProfile.getFeedbacktext());		
		return this;
	}

	public SubmitMeterReadAction submitNpsSurvey(SMRAccountDetails smrProfile){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.clickNpsFeedbackImage();
		thankyoupage.selectNpsFeedbackOption(smrProfile.getfeedbackoption());
		thankyoupage.selectNpsFeedbackText(smrProfile.getFeedbacktext());
		thankyoupage.submitNpsSurvey();
		thankyoupage.verifyNpsSurveySubmitted();
		thankyoupage.closeNpsSurveyOverlay();
		thankyoupage.verifySurveyTableInDb(smrProfile.getEmail(),"NPS",smrProfile.getfeedbackoption(),smrProfile.getFeedbacktext());
		return this;
	}
	public SubmitMeterReadAction firstNameErrorMsgValidation(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();		
		smrpage.firstNameErrorMsgValidation(smrProfile);
		return this;
	}
	public SubmitMeterReadAction surNameErrorMsgValidation(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.surNameErrorMsgValidation(smrProfile);
		return this;
	}
	public SubmitMeterReadAction validateAccountNumberField(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.validateAccountNumberField(smrProfile);	
		return this;
	}
	public SubmitMeterReadAction validateEmailAddressField(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.validateEmailAddressField(smrProfile);
		return this;
	}
	public SubmitMeterReadAction validateMSNField(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.validateMSNField(smrProfile);
		return this;
	}
	public SubmitMeterReadAction verifyCancelButtonInGlobalSmrPage() {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.clickCancelLink();
		smrpage.verifyAccountOverviewTitle();
		return this;
	}
	public SubmitMeterReadAction verifyAccountDetailsSMR(SMRAccountDetails smrProfile) {
		//new LegacyLoginPage().loginUser(userProfile);
		AccountSummaryPage accountsummary=new AccountSummaryPage();
		accountsummary.AccountSummaryVerification(smrProfile);
		accountsummary.getCustomerAddressAndAccountNumber(smrProfile);
		accountsummary.getAccountBalanceAndTypeOfAccount();
		return new SubmitMeterReadAction();
	}
	public SubmitMeterReadAction enterMeterDialInSMR(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getDialsCountAndSubmitMeterRead(smrProfile);	
		smrpage.verifyGasConfirmation();
		return this;
	}
	public SubmitMeterReadAction clickSMRLink(SMRAccountDetails smrProfile){
		new  SubmitMeterReadPage().clickSMRLink(smrProfile);
		return this;
	}
	public OnDemandBillingAction enterMeterRead(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySearchBy(smrProfile);
		smrpage.getDialsCountAndSubmitMeterRead(smrProfile);		
		return new OnDemandBillingAction();
	}
	public SubmitMeterReadAction clickManageAccountAndSMRLink(SMRAccountDetails smrProfile){
		new  SubmitMeterReadPage().clickManageAccountLink(smrProfile);
		return this;
	}
	public SubmitMeterReadAction verifyInactiveAccount(){
		new  SubmitMeterReadPage().verifyInactiveAccount();
		return this;
	}
	public OnDemandBillingAction validateMprnNumber(){
		new  OnDemandBillingPage().validateMprnNumber();
		return new OnDemandBillingAction();
	}
	public SubmitMeterReadAction bgbverifyAfterLogin(SMRAccountDetails smrProfile) {
		AccountSummaryPage accoSummPage = new  AccountSummaryPage();
		accoSummPage.AccountOverviewVerification();
		accoSummPage.getCustomerAddressAndAccountNumber(smrProfile);
	//	accoSummPage.verifyAccountDetails(smrProfile);		
		return this;
	}
//electric read confirmation title 
		public SubmitMeterReadAction verifyElectricityReadConfirmationTitle(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricityReadConfirmationTitle();
		return new SubmitMeterReadAction();	  
	}
	
	//why we need this action for electric
	public SubmitMeterReadAction verifyElectricWhyWeNeedThisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricWhyWeNeedThisLink();
		return new SubmitMeterReadAction();	  
	}
	
	public SubmitMeterReadAction verifyElectricAcctnoWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricAcctnoWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyElectricMeterPointWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricMeterPointWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyElectricMeterIDWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricMeterIDWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
		
	}
	// why we need this link for gas
	public SubmitMeterReadAction verifyGasWhyWeNeedThisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyGasWhyWeNeedThisLink();
		return new SubmitMeterReadAction();	  
	}
	
	public SubmitMeterReadAction verifyGasAcctnoWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyGasAcctnoWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyGasMeterPointWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyGasMeterPointWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyGasMeterIDWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyGasMeterIDWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyElectricsitenoWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.verifyElectricsitenoWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyGassitenoWhereCanIfindthisLink(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.enterGasValidData_Anonymous(smrProfile);
		smrpage.verifyGassitenoWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	
	public SubmitMeterReadAction verifyElectricitysitenoWhereCanIfindthisLink(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.enterElectricityData(smrProfile);
		smrpage.verifyElectricsitenoWhereCanIfindthisLink();
		return new SubmitMeterReadAction();	  
	}
	public SubmitMeterReadAction verifyElectricityYourDetailsTitle()
	{
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyElectricityYourDetailsTitle();
	    return this;
	}
	
	
	public SubmitMeterReadAction verifyGasYourDetailsTitle()
	{
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGasYourDetailsTitle();
	    return this;
	}
	
	public SubmitMeterReadAction verifySubmitMeterReadPageTitle()
	{
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySubmitMeterReadPageTitle();
	    return this;
	}
	
	public SubmitMeterReadAction ClickBackYourDetailsButton()
	{
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.ClickBackYourDetailsButton();
	    return this;
	}
	
	//
	/*public SubmitMeterReadAction verifyElectricityNonSAPCustomer(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterElectricityData(smrProfile);
		smrpage.VerifyElectricityMeterReadingPageTitle();

		return this;
	}*/
	public SubmitMeterReadAction verifyElectricNavigationLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyElectricNavigationLinks();
		return this;
	}
	/*public SubmitMeterReadAction verifyElectricNavigationLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyElectricNavigationLinks();
		return this;
	}*/
	public SubmitMeterReadAction verifyGasNavigationLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGasNavigationLinks();
		return this;
	}
	public SubmitMeterReadAction verifyGlobalNavigationLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGlobalNavigationLink();
		return this;
	}
	
	/*public SubmitMeterReadAction verifyGassitenoWhereCanIfindthisLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyGassitenoWhereCanIfindthisLink();
		return this;
	}*/

	public SubmitMeterReadAction VerifyElectricalAccountAndMeterSerialNumberforSAPCustomers(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
	//	smrpage.enterSAPCustomerElectricityData(smrProfile);
		smrpage.VerifyElectricityMeterReadingPageTitle();
		
		return this;
	}
	
	//test
	public SubmitMeterReadAction verifyServiceDeskCustomerforGas(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getDialsCountAndSubmitMeterRead(smrProfile,0);
		
		return this;
	}
	/*public SubmitMeterReadAction verifySAPForAnonymousSAPCustomer1(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.verifySAPForAnonymousSAPCustomer1(smrProfile);
			
			return this;*/
//	}
	//SAP Gas
		public SubmitMeterReadAction verifyAnonymousSAPElecCustomersRewrite(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.enterValidData_Anonymous(smrProfile);
			//smrpage.getDialsCountAndSubmitMeterReadAnonymous(smrProfile);
			smrpage.getDialsCountAndSubmitMeterReadAnonymousElect(smrProfile);
			smrpage.checkInMeterReadReminder(smrProfile);
		    smrpage.anonymousSubmitMeterRead();
		    smrpage.verifyAuditEntrySMRAnonymous(smrProfile);
			return this;
		}
		
		
		
		public SubmitMeterReadAction verifyAnonymousSAPGasCustomersRewrite(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.enterValidData_Anonymous(smrProfile);
			smrpage.getDialsCountAndSubmitMeterReadAnonymousgas(smrProfile);
			smrpage.checkInMeterReadReminder(smrProfile);
		    smrpage.anonymousSubmitMeterRead();
			return this;
		}
		
		
		// SAP electric
		
		public SubmitMeterReadAction verifyAnonymousSAPElectricCustomers(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.enterValidData_Anonymous(smrProfile);
			smrpage.getDialsCountAndSubmitMeterReadAnonymouselect(smrProfile);
			smrpage.getDialsCountAndSubmitMeterRead(smrProfile,2);
			smrpage.enterSAPElelctricCustomerData(smrProfile);
			smrpage.getDialsCountAndSubmitMeterRead(smrProfile,2);
		    smrpage.verifyElectricConfirmations();
			return this;
		}
	 
	public SubmitMeterReadAction verifySubmitMeterreadLandingPageNavigationLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySubmitMeterreadLandingPageNavigationLinks();
		return this;
	}
	public SubmitMeterReadAction verifyUploadMeterPageNavigationLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.uploadMeterNavigationlinks();
		return this;
	}
	public SubmitMeterReadAction test1(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.test1();
		return this;
	}

	/*public SubmitMeterReadAction verifyMeterReadingEmailConfirmation(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyMeterReadingEmailConfirmation();
		return this;
	}
	*/
	//mpan error msg validatation
	
	public SubmitMeterReadAction ValidatempanErrormsg(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.MPANErrorMsgValidation(smrProfile);
		return this;
	}
	//validation for Electricity reading details error message
	
	
	public SubmitMeterReadAction validateElectricityreadingFiledErrormsg(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.enterElectricityData(smrProfile);
		smrpage.ValidateDayReadValueforElectricityCustomer();
		return this;  
	}
	//validation for Gas reading details error Message
	public SubmitMeterReadAction validateGasreadingFiledErrormsg(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.validateGasreadingFiledErrormsg(smrProfile);
		//smrpage.verifyServiceDeskCustomerSMR(smrProfile);
		//smrpage.ValidateDayReadValueforElectricityCustomer();
		return this;  
	}
	// cancel button in your details page
	public SubmitMeterReadAction yourdetailsCancelButton(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.yourdetailsCancelButton();
		return this;  
	}
	public SubmitMeterReadAction clickSubmitMeterReadLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.clickSubmitMeterReadLink();
		return this;  
	}
	public SubmitMeterReadAction clickUploadMeterReadLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.clickUploadMeterReadLink();
		//smrpage.downloadPDF();
		return this;  
	}
	/*public SubmitMeterReadAction clickUploadMeterReadLink1(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.clickUploadMeterReadLinks();

		return this;  
	}*/
	/*public SubmitMeterReadAction ClickDownLoadExecl(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.downloadPDF();
		return this;  
	}*/
	public SubmitMeterReadAction SearchByAccoutnNumber(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.searchByAccountnumber(smrProfile);
		return this;  
	}
	
	
/*	public SubmitMeterReadAction enterMeterDial1(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCount1(smrProfile);	
		
	//	smrpage.searchBy(smrProfile);
	//	smrpage.verifyGasConfirmation();
		return this;
	}*/
	
	public SubmitMeterReadAction enterGlobalMeterDial(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getDialsCountAndSubmitMeterRead1(smrProfile,1);		
		return this;
	}
	public SubmitMeterReadAction enterGlobalMeterDials(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCount1(smrProfile);		
		return this;
	}
	public SubmitMeterReadAction searchByInvalidata(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchByInvalidata();		
		return this;
	}
	public SubmitMeterReadAction VerifyAndValidateSMRSearchField(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySearchTitleErrorValidation();		
		return this;
	}
	
	public SubmitMeterReadAction ValidatesearchByInvalidata(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchByInvalidata();		
		return this;
	}
	public SubmitMeterReadAction ValidateOverlay(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.OverlayValidation();		
		return this;
	}
	public SubmitMeterReadAction ClickupLoadCanceLink(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.upLoadCanceLink();		
		return this;
	}

	/**
	 * @param smrProfile
	 */
	public SubmitMeterReadAction enterMeterDialForMultipleCount(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCount(smrProfile);		
		return this;
	}
	
	/**
	 * @param smrProfile
	 * @return
	 */
	public SubmitMeterReadAction searchBySitepostcode(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchBySitepostcode(smrProfile);		
		return this;
	}
	public SubmitMeterReadAction searchByMeterpointreference(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchByMeterpointreference(smrProfile);		
		return this;
	}
	public SubmitMeterReadAction searchByAccountnumber(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.searchByAccountnumber(smrProfile);		
		return this;
	}
	public SubmitMeterReadAction verifyMeterReadConfirmationTitle() {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyMeterReadConfirmationTitle();		
		return this;
	}
	public SubmitMeterReadAction VerifyNumberofmetersmorethanmaximum() {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.Numberofmetersmorethanmaximum();		
		return this;
	}
	
	public SubmitMeterReadAction verifyImplausibleReads1(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getGlobalActualReadDate();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
	//	smrpage.verifyOverlayAndClose();
		smrpage.forceSubmit();
		smrpage.verifyPageTitle();
		return this;
	}
	public SubmitMeterReadAction verifyImplausibleReads2(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getGlobalActualReadDate();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
		smrpage.verifyOverlayAndClose();
		return this;
	}
	public SubmitMeterReadAction VerifySearchFieldOverlay(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.AcctOverLay();
		smrpage.sitePostcodeOverlay();
		smrpage.mpnOverlay();
		smrpage.mprnOverlay();
		return this;
	}
	public SubmitMeterReadAction SubmitMeterreadManage(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.submiMeterRead(smrProfile);		
		return this;
	}
//****************************************************** sundar script*****************************
	
	public  SubmitMeterReadAction manageAccountLink(){
		new SubmitMeterReadPage().clickManageAccountLink();
		return this;
	}

	public SubmitMeterReadAction enterMultiDialRead(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCount(smrProfile);
		return this;
	}
	public  SubmitMeterReadAction clickManageAccountLinkWithAccNo(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage().clickManageAccountLinkWithAccNo(smrProfile);
		return this;
	}
	public  SubmitMeterReadAction submitMeterReadLink(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage(). clickSubmitMeterReadlink(smrProfile);
		return this;
	}
	public  SubmitMeterReadAction verifySmrPage(){
		new SubmitMeterReadPage(). verifySmrPage();
		return this;
	}
	public SubmitMeterReadAction enterMeterDial1(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getDialsCountAndSubmitMeterRead5(smrProfile,0);
		return this;
	}
	
	// give status 0 for non-sap 
	// give status 1 for gas sap
	// give status 2 for elec sap
	public SubmitMeterReadAction enterMeterDial12(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getDialsCountAndSubmitMeterRead5(smrProfile,1);
		return this;
	}
	public SubmitMeterReadAction emailConfirmation(){
		new SubmitMeterReadPage().SubmitMeterRead();
		return this;
	}
	public SubmitMeterReadAction smrUpto3meters(){
		new SubmitMeterReadPage(). SubmitMeterReadUpto3MetersTitle();
		return this;
	}
	public SubmitMeterReadAction smrCollectiveBilling(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage().smrCollectiveBillingAddressComparsion(smrProfile);
		return this;
	}
	public SubmitMeterReadAction smrNormalSite(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage(). smrNormalSiteAddressComparsion(smrProfile);
		return this;
	}

	public SubmitMeterReadAction smrSubmitFunctionalities(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage().smrSubmitFunctionalities(smrProfile);
		return this;
	}

	public SubmitMeterReadAction smrMoreThan3MetersSearchOptions(){
		new SubmitMeterReadPage().smrMoreThan3MetersSearchOptions();
		return this;
	}
	public SubmitMeterReadAction smrDownloadTemplateLink(){
		new SubmitMeterReadPage().smrDownloadLinkVerificationAndTemplateDownload();
		return this;
	}
	public SubmitMeterReadAction smrHowToReadMyMeterLinkAndIcannotSeeAllOfMyMetersLink(){
	//	new SubmitMeterReadPage().smrHowToReadMyMeterAndsmrIcannotSeeAllOfMyMetersLink();
		return this;
	}
	public SubmitMeterReadAction smrAbove3MeterSearchAndLinks(){
		new SubmitMeterReadPage().smrAbove3MeterSearchAndLinks();
		return this;
	}
	public SubmitMeterReadAction smrUploadMeterRead(){
		new SubmitMeterReadPage().smrUploadMeterReadLink();
		return this;
	}
	public SubmitMeterReadAction smrWhatsThisElectricity(){
		new SubmitMeterReadPage().smrElectricityWhatsThisLink();
		return this;
	}
	public SubmitMeterReadAction smrWhatsThisGas(){
		new SubmitMeterReadPage().smrGasWhatsThisLink();
		return this;
	}
	public SubmitMeterReadAction smrBlankSearchClick(){
		new SubmitMeterReadPage().smrSearchBlankClickSearch();
		return this;
	}
	
	public SubmitMeterReadAction verifySearchByInAccountSmrPage1(SMRAccountDetails smrProfile) {
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySearchByDropdownbutton();
		smrpage.searchBy(smrProfile);
		return this;
	}

/*	public SubmitMeterReadAction smrLinkNavigationsFromMeterReadConfirmationPage(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage().smrlinkNavigationsInMeterReadConfirmationPage(smrProfile);
		return this;
}*/
	/*public SubmitMeterReadAction verifySearchByInAccountSmrPage1(SMRAccountDetails smrProfile) {
=======
	
	public SubmitMeterReadAction verifySearchByInAccountSmrPage1(SMRAccountDetails smrProfile) {
>>>>>>> .r4449
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifySearchByDropdownbutton();
		smrpage.searchBy(smrProfile);
		return this;
	}*/
	public SubmitMeterReadAction smrMoreThaMaximumErrorMessage(){
		new SubmitMeterReadPage().moreThanMaximumErrorMessage();
		return this;
	}
	public SubmitMeterReadAction verifyImplausibleReadsCloseLink(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getActualReadDate();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
		smrpage.verifyOverlayAndClose();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Negativevalue");
		//smrpage.getImpausibleErrorMsg();
		smrpage.verifyOverlayAndClose();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile,"Exceedvalue");
		//smrpage.verifyOverlayAndSubmit();
		//smrpage.verifyOverlayAndClose();
		smrpage.verifyGasConfirmation();
		return this;
	}
		public SubmitMeterReadAction smrAuditDetailsEntry(SMRAccountDetails smrProfile){
		new SubmitMeterReadPage().verifyAuditTable(smrProfile);
		return this;
	}
	public SubmitMeterReadAction enterMultiDialRead1(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCount(smrProfile);
		return this;
	}
	public SubmitMeterReadAction enterMeterDialForMultipleCountforglobal(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.enterMeterDialForMultipleCountforglobal(smrProfile);
		return this;
	}
	
	public SubmitMeterReadAction uploadMeterRead(){
		new SubmitMeterReadPage().uploadMeterRead();
		return this;
	}
	public SubmitMeterReadAction verifyMultiDialImplausibleReads(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getActualReadDate();
		smrpage.implausibleMultiDial(smrProfile);
		//smrpage.verifyOverlayAndSubmitMultiDial();
		return this;
}
	public SubmitMeterReadAction verifyselectCheckBox(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyDontRemindEmailCheckBox();		
		return this;
	}
public SubmitMeterReadAction openSMRpage1(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.openSMRPage1();
		return this;
	}
	public SubmitMeterReadAction verifyLeadTable(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyLeadTable(smrProfile);
		return this;
		
	}
	public SubmitMeterReadAction verifyLeadTable1(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyLeadTable1(smrProfile);
		return this;
		
	}
	public SubmitMeterReadAction yourdetailsCancelButtons(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.yourdetailsCancelButtons();
		return this;  
	}
	public SubmitMeterReadAction verifyAuditLeadTable(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.verifyAuditEntrySMRLoggedIn(smrProfile);
//		smrpage.verifyLeadTable1_MailVerification(smrProfile);
		return this;	
	}
	public SubmitMeterReadAction clickUploadMeterReadLinks(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();  
		smrpage.clickUploadMeterReadLinks();

		return this;  
	}
	public SubmitMeterReadAction cancelbutton(){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.cancelbutton();
		return this;	
	}
	public SubmitMeterReadAction verifyImplausibleReadds(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getGlobalActualReadDate();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
	//	smrpage.verifyOverlayAndClose();
		smrpage.forceSubmit();
		smrpage.verifyPageTitle();
		return this;
	}
	public SubmitMeterReadAction verifyImplausibleReadss(SMRAccountDetails smrProfile){
		SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
		smrpage.getGlobalActualReadDate();
		smrpage.getDialsCountAndSubmitIncorrectRead(smrProfile, "Exceedvalue");
		smrpage.verifyOverlayAndClose();
		return this;
	}
	// give status 0 for non-sap 
		// give status 1 for gas sap
		// give status 2 for elec sap
		public SubmitMeterReadAction enterMeterDialRead(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndSubmitMeterReadvalue(smrProfile,1);
			return this;
		}
		public SubmitMeterReadAction enterMeterDials(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndSubmitMeterReadchange(smrProfile,0);
			smrpage.clickSubmitMeterRead();
			smrpage.verifyAuditEntrySMRLoggedIn(smrProfile);
			return this;
		}
		public SubmitMeterReadAction enterMeterDialsGlobal(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndGlobalSubmitMeterReadchange(smrProfile,0);
			smrpage.clickSubmitMeterRead();			
			return this;
		}
		public SubmitMeterReadAction enterMeterDialsGlobalelec(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndGlobalElecSubmitMeterReadchange(smrProfile,0);
			smrpage.clickSubmitMeterRead();			
			return this;
		}
		public SubmitMeterReadAction enterMeterDialselec(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndSubmitMeterReadchangeElec(smrProfile,0);
			smrpage.clickSubmitMeterRead();			
			return this;
		}
		public SubmitMeterReadAction submitButton(){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.submitButton();
			return this;
		}
		
		public SubmitMeterReadAction meterReadingDue(){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.meterReadingDue();
			return this;
		}
		
		
		public SubmitMeterReadAction smrmeteroverlay(){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.smrMetersOverlay();
			return this;
		}
		/*public SubmitMeterReadAction getDialsCountAndSubmitMeterReadchangeElec(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.getDialsCountAndSubmitMeterReadchangeElec(smrProfile,0);
			return this;
		}*/
		public SubmitMeterReadAction overlay(){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.overlay();		
			return this;
		}
		public SubmitMeterReadAction overlayclose(){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.overlayclose();
			return this;
		}
		public SubmitMeterReadAction enterMultiDialReads(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.enterMeterDialForMultipleCount(smrProfile);
			return this;
		}
		//SAP Gas
		public SubmitMeterReadAction verifyAnonymousSAPGasCustomers_CR_singleregister(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Newuser");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile);
			return this;
		}
		// SAP electric
		
		public SubmitMeterReadAction verifyAnonymousSAPElectricCustomers_CR_singleregister(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Newuser");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile);
			return this;
		}
//SAP Gas
		public SubmitMeterReadAction verifyAnonymousSAPGasCustomers_CR_Multipleregister(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Newuser_with_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile);
			return this;
		}
		// SAP electric
		
		public SubmitMeterReadAction verifyAnonymousSAPElecCus_CR_Multiplereg_newuser(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR_multiple(smrProfile,"Newuser_with_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPElecCus_CR_Multiplereg_newuser_alretnonchecked(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR_multiple(smrProfile,"Newuser_without_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPGasCus_CR_Multiplereg_newuser_alretnonchecked(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Newuser_without_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPGasCus_CR_Multiplereg_newuser_alertchecked(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Newuser_with_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPElecCus_CR_Multiplereg_newuser_emailurl(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();			
			smrpage.enterMeterDialForMultipleCountforSMRCR_multiple(smrProfile,"Newuser_without_alretboxchecked");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPElec_collective(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();	
			smrpage.SMRyourdetails(smrProfile);
			smrpage.collective_error();			
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPElecCus_CR_Multiplereg_enrolleduser(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR_multiple(smrProfile,"Alreadyenrolleduser");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		public SubmitMeterReadAction verifyAnonymousSAPGasCus_CR_Multiplereg_enrolleduser(SMRAccountDetails smrProfile){
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			smrpage.SMRyourdetails(smrProfile);
			smrpage.enterMeterDialForMultipleCountforSMRCR(smrProfile,"Alreadyenrolleduser");// input 1 for sap verification and 0 for no need verify	
			smrpage.SMRconfirmationpage();
			smrpage.verifyAuditTable_SMRstatus(smrProfile);
			smrpage.verifyAuditTable_SMREmailstatus(smrProfile); 
			return this;
			
		}
		/*public SubmitMeterReadAction VerifySAPCRM_SMR(CrmUserProfile crmuserProfile,UserProfile userProfile,SMRAccountDetails smrProfile){
			SapCrmPage sapcrmpage=new SapCrmPage();
			sapcrmpage.openCRMUrl_QA7();
			sapcrmpage.enterLoginDetails(crmuserProfile);
			sapcrmpage.searchCrmFields_SMR(crmuserProfile,userProfile,smrProfile.getEmail());
			return this;
		}	
		public SubmitMeterReadAction VerifySAPCRM_SMR_noalret(SMRAccountDetails smrProfile,CrmUserProfile crmuserProfile,UserProfile userProfile){
			SapCrmPage sapcrmpage=new SapCrmPage();
			sapcrmpage.openCRMUrl_QA7();
			sapcrmpage.enterLoginDetails(crmuserProfile);
			sapcrmpage.searchCrmFieldsforpaperlessbilling_Bpcpverification(crmuserProfile,userProfile,smrProfile.getEmail());
			return this;
		}*/
		
		
}
