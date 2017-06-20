/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.MultiUserMultiViewPage;
import bg.framework.app.functional.page.Slingshot.PaperlessBillingPage;
import bg.framework.app.functional.page.Slingshot.SubmitMeterReadPage;
import bg.framework.app.functional.page.bgb.RegistrationPage;


/**
 * @author 255501
 *
 */
public class MultiUserMultiViewAction {
	
	public  String UserType=null;
	public String useremail=null;
	public String email =null;
 
	public MultiUserMultiViewAction ClickMangeruserNavigationPage(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ClickMangeruserNavigationPage();	
		return this;
	}
	
	public MultiUserMultiViewAction UserConfirmationPageNavigations(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();		
		MuMv.clickAddnewuserNavigation();
		MuMv.ClickMangeruserNavigationPage();		
		return this;
	} 
	 
		public MultiUserMultiViewAction AddUserRadioButton(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AddUserRadioButton();	
			return this;
		}
	 
	public MultiUserMultiViewAction ClickAddNewUserLink(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ClickAddUserLink();	
		return this;
	}
	public MultiUserMultiViewAction ClickAddUserRHNLink(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ClickAddUserRHNLink();	
		return this;
	}
	// have to update 
	public MultiUserMultiViewAction VerifyWelcomeToManageUserOverlay(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.WelcomeToManageUserOverlay();	
		return this;
	}
	public MultiUserMultiViewAction VerifyPageTitle(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.VerifyPageTitle();	
		return this;
	}
	public MultiUserMultiViewAction ManageUserNavigationVerification(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ManageUserNavigationVerification();	
		return this;
	}
	public MultiUserMultiViewAction AddNewUserNavigationVerification(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.AddNewUserNavigationVerification();		
		return this;
	}
	public MultiUserMultiViewAction clicktestacct(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.testacct();		
		return this;
	}
	
	public MultiUserMultiViewAction BreadCrumbNavigation(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.BreadCrumbNavigation();		
		return this;
	}
	public MultiUserMultiViewAction ManageviewNavigationLinks(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ManageviewNavigationLinks();		
		return this;
	}
	public MultiUserMultiViewAction AddNewUserNavigationVerifications(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.AddNewUserNavigationVerification();
		return this;
	}
	public MultiUserMultiViewAction BackandCancelNavigation(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.AddNewUserPageCancelLink();
		MuMv.AddNewUserPageBackLink();		
		return this;
	}
	public MultiUserMultiViewAction Verifyuserlistindb(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.VerifyTotalNumberOfUsers(userProfile);
		return this;
	}
	public MultiUserMultiViewAction SuperUserOverlayyes(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.SuperUserOverlayyes();	
		return this;
	}
	public MultiUserMultiViewAction EnterValid_StandardUserdata(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.enterValidData_StandardUser(userProfile);	
		return this;
	}
	public MultiUserMultiViewAction EnterValid_SuperUserdata(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.enterValidData_SuperUser(userProfile);	
		return this;
	}
	public MultiUserMultiViewAction EnterValid_SuperUserdatas(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.enterValidData_SuperUserdata(userProfile);	
		return this;
	}

	public MultiUserMultiViewAction SuperUserOverlayNo(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.SuperUserOverlayNo();	
		return this;
	}
	public MultiUserMultiViewAction BackAndCancel(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.BackAndCancel();	
		return this;
	}
	public MultiUserMultiViewAction SuperUserCreation(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.SuperUserCreation();	
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTable(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_AddUserLinkEmailStatus(userProfile);
		MuMv.verifyLeadTable1_RegisteredSuccessfully(userProfile);
		MuMv.verifyLeadTable1_ThankYouMail(userProfile);
		MuMv.verifyLeadTable1_RegisteredSuccessInformToALLSuperusers(userProfile);
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTabledetails(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_AddUserLinkEmailStatus(userProfile);
		MuMv.verifyLeadTable1_RegisteredSuccessfully(userProfile);
		MuMv.verifyLeadTable1_ThankYouMail(userProfile);		
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTabledetailsNew(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_AddUserLinkEmailStatusNew();
		//MuMv.verifyLeadTable1_RegisteredSuccessfullyNew();
		MuMv.verifyLeadTable1_ThankYouMailNew();		
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTabledetailsNew(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_AddUserLinkEmailStatusNew(email);
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTabledetailsNew_01(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_AddUserLinkEmailStatusNew_01(email);
		return this;
	}
	public MultiUserMultiViewAction verifyAuditTabledetailswithPaperlessbilling(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();		
		MuMv.verifyLeadTable_AddUserLinkEmailStatus(userProfile);
		MuMv.verifyLeadTable1_RegisteredSuccessfully(userProfile);
		MuMv.verifyLeadTable1_PaperlessBilling(userProfile);
		MuMv.verifyLeadTable1_ThankYouMail(userProfile);	
		MuMv.verifyLeadTable1_RegisteredSuccessInformToALLSuperusers(userProfile);
		return this;
	}
	public MultiUserMultiViewAction verifyMuMvAuditTabledetailswithPaperlessbilling(UserProfile userProfile){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();		
		MuMv.verifyLeadTable_AddUserLinkEmailStatus(userProfile);
		MuMv.verifyLeadTable1_RegisteredSuccessfully(userProfile);
		MuMv.verifyLeadTable2_PaperlessBilling(userProfile);
		MuMv.verifyLeadTable1_ThankYouMail(userProfile);	
		MuMv.verifyLeadTable1_RegisteredSuccessInformToALLSuperusers(userProfile);
		return this;
	}
	public MultiUserMultiViewAction VerifyChangeProfileAudit()
	{
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.verifyLeadTable_UserTypeChangeAudit(useremail);
		MuMv.verifyLeadTable_UserTypeChangeEmailAudit(useremail);
		return this;
	}
	
	public MultiUserMultiViewAction StandardUserCreation(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.StandardUserCreation();	
		return this;
	}
	public MultiUserMultiViewAction StandardUserCreation_SelectSuper(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.StandardUserCreation_SelectSuper();	
		return this;
	}
	public MultiUserMultiViewAction StandardUserCreations(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.StandardUserCreations();	
		return this;
	}
	public MultiUserMultiViewAction StandardUser_Creation(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.StandardUser_Creation();	
		return this;
	}
	public MultiUserMultiViewAction ViewdetailsPageVerification(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ViewdetailsPageVerification();	
		return this;
	}

	public MultiUserMultiViewAction AddUsersWhatisThisverlay(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.AddUsersWhatisThisverlay();	
		return this;
	}
	public MultiUserMultiViewAction AddUsersWhatisThisverlay_updated(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.AddUsersWhatisThisverlay_updated();	
		return this;
	}
		public MultiUserMultiViewAction verifyWhatisthisOverlay(){
		MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
		MuMv.ManageUsersWhatisThisverlay();	
		return this;
	}
		public MultiUserMultiViewAction ClickManageUsersWhatisThisverlay(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ClickManageUsersWhatisThisverlay();	
			return this;
		}
		
		public MultiUserMultiViewAction ManageUserNavigationVerificationLinks(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ManageUserNavigationVerificationLinks();	
			return this;
		}
	
		public MultiUserMultiViewAction viewDetailsTable(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.viewDetailsTable(userProfile);		
				
			return this;
		}
		public MultiUserMultiViewAction openEncryptUrlandRegister1new(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.openEncryptUrlandRegister1new(userProfile);		
				
			return this;
		}
		public MultiUserMultiViewAction DeleteViewOverlay(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.DeleteViewOverlay(userProfile);		
				
			return this;
		}
		public MultiUserMultiViewAction EditViewDetails(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.EditViewDetails(userProfile);
			return this;
		}
		public MultiUserMultiViewAction Edituserdetailscancel(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.EdituserDetailsCancelBack();			
			return this;
		}
		public MultiUserMultiViewAction verifyAndValidateUserTypeFieldContent(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifyAndValidateUserTypeFieldContent(userProfile);			
			return this;
		}
	
		/*public MultiUserMultiViewAction openurl(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.openurl();	
			return this;
		}*/
		/**
		 * 
		 */
		public MultiUserMultiViewAction VerifyAllAccountsViewName(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,1);	
			return this;
		}
		public MultiUserMultiViewAction VerifyEditviewname(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,5);	
			return this;
		}
		public MultiUserMultiViewAction ViewDeletion(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ViewDeletion(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction StandardUserValidation() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.StandardUserValidation();	
			return this;
		}
		
		public MultiUserMultiViewAction ValidateRemoveviewname(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcountchange(userProfile);	
			return this;
		}
				public MultiUserMultiViewAction validateEmailidField(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.validateEmailidField(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction validateCheckboxTermsAndCnds(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ValidatecheckboxTermsAndConditions(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction ValidateaddnewviewOverlay() {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ValidateaddnewviewOverlay();	
			return this;
		}
		public MultiUserMultiViewAction ManageViews() {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ManageViews();	
			return this;
		}
		public MultiUserMultiViewAction viewtableHandle(UserProfile userProfile,SMRAccountDetails smrProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			UserType=MuMv.viewtableHandle(userProfile);	
			new BgbRegistrationAction()
				.AddNewStdUserdata(userProfile);					
			 new MultiUserMultiViewAction()
	  	    	.verifyAuditTable(userProfile)
	  	        .AdduserConfirmationPage()
	  	        .confirmationPageVerificationLinks()
	  	        .UserJourney_Verification(userProfile,smrProfile,UserType);
			
			return this;
		}
		public MultiUserMultiViewAction ViewAcctsOverlay(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.Verifyviewaccounts(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction ChangeStandardUserview(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.viewstdtablehandle(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction VerifyViewNameAcctOverlay(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,3);	
			return this;
		}
		public MultiUserMultiViewAction VerifySpecificAccountsViewName(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,2);	
			return this;
		}
		public MultiUserMultiViewAction SelectpecificAccountsViewName(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,6);	
			return this;
		}
		public MultiUserMultiViewAction confirmationPageVerificationLinks() {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.confirmationPageVerificationLinks();	
			return this;
		}
		public MultiUserMultiViewAction AdduserConfirmationPage() {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AdduserConfirmationPage();	
			return this;
		}
		public MultiUserMultiViewAction VerifySpecificAccountsViewNames(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.transcount(userProfile,4);	
			return this;
		}
		public MultiUserMultiViewAction ValidateAddnewUserTitleField(UserProfile userProfile) {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.validateTitleField();	
			return this;
		}
		
	
		public MultiUserMultiViewAction StandardMaximumExceedErrorValidation() {
			// TODO Auto-generated method stub
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.StandardUserErrorValidation();	
			return this;
		}
	
		public MultiUserMultiViewAction SuperUserMaximumExceedErrorValidation() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.SuperUserErrorValidation();	
			return this;
		}
		public MultiUserMultiViewAction AddnewViewOverlay() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AddNewViewOverlay();	
			return this;
		}	
		public MultiUserMultiViewAction backtoMangeruserlink() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.backtoMangeruserlink();	
			return this;
		}
		public MultiUserMultiViewAction backtoMangeruserlinkNavigation() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.backtoMangeruserlinkNavigation();	
			return this;
		}
		
		public MultiUserMultiViewAction Addviewname(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.viewname(userProfile);	
			return this;
		}
	
		public MultiUserMultiViewAction acctnovalidatation(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.acctnovalidatation(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction ediviewValitationSearchAccts(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ediviewValitations(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction EditAcctsTable(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.EditAcctsTable(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction UserJourney_SuperUserAccessVerification(UserProfile userProfile,SMRAccountDetails smrProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			MuMv.UserJourney_AccessVerification(userProfile,"Super user");	
		//	smrpage.submiMeterRead(smrProfile);
	//		smrpage.enterMeterDialForMultipleCount(smrProfile);
			MuMv.odbVerification();
			return this;
		}
		public MultiUserMultiViewAction UserJourney_Verification(UserProfile userProfile,SMRAccountDetails smrProfile,String UserType) {
			System.out.println("while i am in verification method"+UserType);
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			MuMv.UserJourney_AccessVerification(userProfile,UserType);	
			
		//	smrpage.submiMeterRead(smrProfile);
		//	smrpage.enterMeterDialForMultipleCount(smrProfile);
		//	MuMv.odbVerification();
			return this;
		}
		public MultiUserMultiViewAction UserJourney_FA_AccessVerification(UserProfile userProfile,SMRAccountDetails smrProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			SubmitMeterReadPage smrpage = new SubmitMeterReadPage();
			MuMv.UserJourney_AccessVerification(userProfile,"Full access");	
			smrpage.submiMeterRead(smrProfile);
			smrpage.enterMeterDialForMultipleCount(smrProfile);
			MuMv.odbVerification();
			return this;
		}
		public MultiUserMultiViewAction UserJourney_RBAccessVerification(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserJourney_AccessVerification(userProfile,"Reads and Bills");	
			return this;
		}
		public MultiUserMultiViewAction UserJourney_RBPAccessVerification(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserJourney_AccessVerification(userProfile,"Reads, Bills and Payments");	
			return this;
		}
		public MultiUserMultiViewAction verifynewview(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifynewview(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction VerifyUseramendeddetailsTitle() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.Useramendeddetails();	
			return this;
		}
		public MultiUserMultiViewAction Viewtableverification() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.Viewtableverification();	
			return this;
		}
		
		public MultiUserMultiViewAction viewDetailsTableViewuser(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			useremail =MuMv.viewDetailsTableViewuser(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction ClickEditdetails() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ClickEditdetails();	
			return this;
		}
		public MultiUserMultiViewAction viewDetailsTableViewStduser(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			useremail =MuMv.viewDetailsTableViewStduser(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction verifyEditedview(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifyEditedview(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction odbVerification() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.odbVerification();	
			return this;
		}
		public MultiUserMultiViewAction morethan15accts(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.morethan15accts(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction FiftyAcctsViewnameErrorValidation(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.FiftyAcctsViewnameErrorValidation(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction ValidateCheckboxerror(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ValidateCheckboxerror(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction AddnewUserslink() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AddnewUserslink();	
			return this;
		}
		public MultiUserMultiViewAction specificView(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.specificView(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction dummy(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.dummy(userProfile);	
			return this;
		}
		public MultiUserMultiViewAction viewDetailsTablestdUser(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.viewDetailsTablestdUser(userProfile);	
			return this;
		}	
		public MultiUserMultiViewAction verifyLeadTabledata_AddnewViewAudit(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifyLeadTabledata_AddnewViewAudit(userProfile);		
			return this;
		}
		public MultiUserMultiViewAction verifyLeadTable_ViewDeletionAudit(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifyLeadTable_ViewDeletionAudit(userProfile);
					
			return this;
		}	
		
		public MultiUserMultiViewAction ClickManageUserLink() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ClickManageUserLink();	
			return this;			
		}
		public MultiUserMultiViewAction verifydeactivateaccount(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifydeactivateaccount(userProfile);	
			return this;			
		}
		public MultiUserMultiViewAction ClickManageUser() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.ClickManageUser();	
			return this;			
		}
		public MultiUserMultiViewAction VerifyenterValidData_StandardUserdetails(UserProfile userProfile)
				 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.enterValidData_StandardUsers(userProfile,userProfile.getviewname());	
			return this;			
		}
		public MultiUserMultiViewAction AccountStorageFunctionality(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AccountStorageFunctionality(userProfile);	
			return this;			
		}
		public MultiUserMultiViewAction AccountStorageFunctionalitySuperUser(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AccountStorageFunctionalitySuperUser(userProfile);	
			return this;			
		}
		
		
		
		
		public MultiUserMultiViewAction VerifyTable() {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.VerifyTable();	
			return this;			
		}
		
		
	/*	public MultiUserMultiViewAction AccountStorageFunctionality(UserProfile userProfile) {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.AccountStorageFunctionality(userProfile);	
			return this;			
		}
		*/
		
		public MultiUserMultiViewAction VerifyenterValidData_StandardUsers(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.enterValidData_StandardUserdetails(userProfile);	
			return this;			
		 }
		public MultiUserMultiViewAction enterValidData_StandardUserforEditview(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.enterValidData_StandardUserforEditview(userProfile);	
			return this;			
		 }
		public MultiUserMultiViewAction Move_premises()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.Move_premises();	
			return this;			
		 }
		public MultiUserMultiViewAction reports()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.reports();	
			return this;			
		 }
		public MultiUserMultiViewAction PaperlessBilling()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.PaperlessBilling();	
			return this;			
		 }
		public MultiUserMultiViewAction addsite()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.addsite();	
			return this;			
		 }
		public MultiUserMultiViewAction Renew_contract()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.Renew_contract();	
			return this;			
		 }
		public MultiUserMultiViewAction UserAndtheirJouenyAccessVerification_superuser(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserAndtheirJouenyAccessVerification(userProfile,"super user");	
			return this;			
		 }
		public MultiUserMultiViewAction UserAndtheirJouenyAccessVerification_fulluser(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserAndtheirJouenyAccessVerification(userProfile,"Full access");	
			return this;			
		 }
		public MultiUserMultiViewAction UserAndtheirJouenyAccessVerification_RBPuser(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserAndtheirJouenyAccessVerification(userProfile,"Reads, Bills and Payments");	
			return this;			
		 }
		public MultiUserMultiViewAction UserAndtheirJouenyAccessVerification_RBuser(UserProfile userProfile)
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.UserAndtheirJouenyAccessVerification(userProfile,"Reads and Bills");	
			return this;			
		 }
		
		public MultiUserMultiViewAction superUseralerts()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifytheSuperUser_journey();	
			return this;			
		 }
		public MultiUserMultiViewAction FullAccessUseralerts()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifytheFullAccessUser_journey();	
			return this;			
		 }
		public MultiUserMultiViewAction RBPUseralerts()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifytheRBPUser_journey();	
			return this;			
		 }
		public MultiUserMultiViewAction RBUseralerts()
		 {
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifytheRBUser_journey();	
			return this;			
		 }
		//========================================================================== sundar action===================================================================
		//===========================================================================================================================================================
		public  MultiUserMultiViewAction mangaeUserLink(){
			new MultiUserMultiViewPage().clickManageUserLink();
			return this;
		}
		public  MultiUserMultiViewAction viewDetailsLinkOfActive(UserProfile userProfile){
			new MultiUserMultiViewPage().clickViewDetailsLinkOfActive(userProfile);
			return this;
		}
		public  MultiUserMultiViewAction viewDetailsLinkOfInActive(UserProfile userProfile){
			new MultiUserMultiViewPage().clickViewDetailsLinkOfInActive(userProfile);
			return this;
		}
		public  MultiUserMultiViewAction viewUserPageLinkNavigations(){
			new MultiUserMultiViewPage().linkNavigationsOfViewUserPage();
			return this;
		}
		public  MultiUserMultiViewAction activeTextVerification(){
			new MultiUserMultiViewPage().activeTextVerification();
			return this;
		}
		public  MultiUserMultiViewAction DeactiveUserLinkVerification (){
			new MultiUserMultiViewPage().deactiveUserLinkVerification();
			return this;
		}
		public  MultiUserMultiViewAction DeactiveTextVerification(){
			new MultiUserMultiViewPage().deactiveTextVerification();
			return this;
		}
		public  MultiUserMultiViewAction ReactiveUserLinkVerification(){
			new MultiUserMultiViewPage().reactiveUserLinkVerification();
			return this;
		}
		public  MultiUserMultiViewAction EditDetailsLinkVsibleOrNot(){
			new MultiUserMultiViewPage().editDetailsVisibleOrNot();
			return this;
		}
		public  MultiUserMultiViewAction clickDeactivateUserAndConfirm(){
			new MultiUserMultiViewPage().clickDeactivateUser();
			return this;
		}
		public  MultiUserMultiViewAction backToManageUsersLink(){
			new MultiUserMultiViewPage().backToManageUsersLink();
			return this;
		}
		public  MultiUserMultiViewAction deactivateASuperUser(){
			new MultiUserMultiViewPage().deactivateASuperUser();
			return this;
		}
		public  MultiUserMultiViewAction clickReactiveAndVerify(){
			new MultiUserMultiViewPage().clickReactiveAndVerify();
			return this;
		}
		public  MultiUserMultiViewAction linkNavigationsOfReactiveConfirmationPage(){
			new MultiUserMultiViewPage().linkNavigationsOfReactiveConfirmationPage();
			return this;
		}
		public  MultiUserMultiViewAction EditUserlinkNavigation(){
			new MultiUserMultiViewPage().EditUserlinkNavigation();
			return this;
		}
		public  MultiUserMultiViewAction clickReactiveOfMaxAccountOverlayAndLinkVerification(){
			new MultiUserMultiViewPage().clickReactiveOfMaxAccountOverlayAndLinkVerification();
			return this;
		}
		public  MultiUserMultiViewAction addNewUser(){
			new MultiUserMultiViewPage().addNewUser();
			return this;
		}
		public MultiUserMultiViewAction addNewStandardUser(UserProfile userProfile){
	        new MultiUserMultiViewPage()
	        .enterValidDataStandardUsernormal(userProfile);         
	        //new RegistrationPage()
	   //     .openEncryptUrlandRegister1(userProfile);
	        return this;

	}
		public MultiUserMultiViewAction enterValidDatasuperUser(UserProfile userProfile){
	        new MultiUserMultiViewPage()
	        .enterValidDatasuperUser(userProfile);         
	        //new RegistrationPage()
	   //     .openEncryptUrlandRegister1(userProfile);
	        return this;

	}
		public MultiUserMultiViewAction loginAfterNewView(){
			new MultiUserMultiViewPage().loginAfterNewView();
			return this;
		}
		public  MultiUserMultiViewAction clickViewDetailsLinkOfActiveAndOverlayVerificationOfSuperUser(){
			new MultiUserMultiViewPage().clickViewDetailsLinkOfActiveAndOverlayVerificationOfSuperUser();
			return this;
		}
		public  MultiUserMultiViewAction manageUsersLink(){
			new MultiUserMultiViewPage().manageUsersLink();
			return this;
		}
		public  MultiUserMultiViewAction breadcrumbNavigationsOfViewUserPage(){
			new MultiUserMultiViewPage().breadcrumbNavigationsOfViewUserPage();
			return this;
		}
		public  MultiUserMultiViewAction clickEditDetailsLink(){
			new MultiUserMultiViewPage().clickEditDetailsLink();
			return this;
		}
		public  MultiUserMultiViewAction linkNavigationsOfEditDetailsPage(){
			new MultiUserMultiViewPage().linkNavigationsOfEditDetailsPage();
			return this;
		}
		public MultiUserMultiViewAction verifyUserTypeDropdownAndChangeToSuperUserCancel() {
			MultiUserMultiViewPage MultiUserMultiViewPage= new MultiUserMultiViewPage();
			MultiUserMultiViewPage.verifyUserTypeDropdown();
			MultiUserMultiViewPage.searchBy();
			return this;
		}
		public MultiUserMultiViewAction verifyUserTypeDropdownAndChangeToSuperUserConfirm() {
			MultiUserMultiViewPage MultiUserMultiViewPage= new MultiUserMultiViewPage();
			MultiUserMultiViewPage.selectUserTypeSuperUser();
			MultiUserMultiViewPage.searchBySuperUser();
			return this;
		}
		public  MultiUserMultiViewAction linkNavigationsOfEditDetailsConfirmationPage(){
			new MultiUserMultiViewPage().linkNavigationsOfEditDetailsConfirmationPage();
			return this;
		}
		public  MultiUserMultiViewAction userNameAndTypeComparison(){
			new MultiUserMultiViewPage().userNameAndTypeComparison();
			return this;
		}
		public  MultiUserMultiViewAction aboveFiveSuperUsersErrorPageVerification(){
			new MultiUserMultiViewPage().aboveFiveSuperUsersErrorPageVerification();
			return this;
		}
		public MultiUserMultiViewAction verifyUserTypeDropdownAndChangeToStandardFullAccess() {
			MultiUserMultiViewPage MultiUserMultiViewPage= new MultiUserMultiViewPage();
			MultiUserMultiViewPage.selectUserTypeStandardUser();
			MultiUserMultiViewPage.searchByStandardUser();
			return this;
		}
		public  MultiUserMultiViewAction addNewViewButton(){
			new MultiUserMultiViewPage().addNewViewButton();
			return this;
		}
		public MultiUserMultiViewAction changeUserTypeAndSelectView() {
			MultiUserMultiViewPage MultiUserMultiViewPage= new MultiUserMultiViewPage();
			MultiUserMultiViewPage.selectUserTypeStandardUser();
			MultiUserMultiViewPage.searchByStandardUserAndSelectView();
			return this;
		}
		public MultiUserMultiViewAction auditVerifcationForAccountChange(UserProfile userProfile){
			new MultiUserMultiViewPage().specificAccountAudit(userProfile);
			return this;
		}
		public MultiUserMultiViewAction auditForChangesEmail(UserProfile userProfile){
			new MultiUserMultiViewPage().auditForChangesEmail(userProfile);
			return this;
		}
		
		public MultiUserMultiViewAction userStatusRb(UserProfile userProfile){
			new MultiUserMultiViewPage().userStatusRb(userProfile);
			return this;
		}
		public MultiUserMultiViewAction userStatusRbp(UserProfile userProfile){
			new MultiUserMultiViewPage().userStatusRbp(userProfile);
			return this;
		}
		public MultiUserMultiViewAction userStatusSuper(UserProfile userProfile){
			new MultiUserMultiViewPage().userStatusSuper(userProfile);
			return this;
		}
		public MultiUserMultiViewAction userLogin(){
			new MultiUserMultiViewPage().userLogin();
			return this;
		}
		public MultiUserMultiViewAction userStatusFullAccess(UserProfile userProfile){
			new MultiUserMultiViewPage().userStatusFullAccess(userProfile);
			return this;
		}
		public MultiUserMultiViewAction EditViewNameforLessthan15Accts(UserProfile userProfile){
			new MultiUserMultiViewPage().EditViewNameforLessthan15Accts(userProfile);
			return this;
		}
		public MultiUserMultiViewAction EditViewAdduserValidation(UserProfile userProfile){
			new MultiUserMultiViewPage().EditViewAdduserValidation(userProfile);
			return this;
		}
		public MultiUserMultiViewAction EditViewNameErrorValidation(UserProfile userProfile){
			new MultiUserMultiViewPage().EditViewNameErrorValidation(userProfile);
			return this;
		}
		public MultiUserMultiViewAction VerifyAudit_EmailtriggeredAction(UserProfile userProfile){
			PaperlessBillingPage paperlesspace = new PaperlessBillingPage();
			paperlesspace.VerifyAudit_Emailtriggered(userProfile);
			paperlesspace.VerifyAudit_Emailtriggeredsaveddetails(userProfile);
			return this;
		}
		public MultiUserMultiViewAction ManageAccountLink(UserProfile userProfile){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.verifyManageAccountLink(userProfile);	
			return this;			
		}
		public MultiUserMultiViewAction verifySuperuserAccessjourneyverification(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.SuperuserAccessjourneyverification();	
			return this;			
		}
		public MultiUserMultiViewAction verifyFullAccessuserjourneyverification(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.FullAccessjourneyverification();	
			return this;			
		}
		public MultiUserMultiViewAction verifyRBPuserjourneyverification(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.RBPjourneyverification();	
			return this;			
		}
		public MultiUserMultiViewAction verifyRPuserjourneyverification(){
			MultiUserMultiViewPage MuMv = new MultiUserMultiViewPage();
			MuMv.RPjourneyverification();	
			return this;			
		}
}
