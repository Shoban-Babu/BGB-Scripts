package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.AccountSummaryPage;
import bg.framework.app.functional.page.Slingshot.ManagePersonalDetailsPage;
import bg.framework.app.functional.page.common.LegacyLoginPage;

/**
 * Created by IntelliJ IDEA.
 * User: !jithendb
 * Date: 03/04/12
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */
public class ManagePersonalDetailsAction {

	ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();

	private static int MPD_SAP_UPDATE_SUCCESS  = 37;
	private static int MPD_SAP_UPDATE_FAILURE  = 38;
	private static int MPD_DB_UPDATE_SUCCESS   = 39;
	private static int MPD_DB_UPDATE_FAILURE  = 40;		
	private static String strEmail="temp@cognizant.com";
	private static String strTempPwd = "temp1234";
	private static String mobileNumber = "0123456789";

	public ManagePersonalDetailsAction validateMPDPageFields(){
		managePersonalDetailsPage.verifyMPDPageFields();
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction validateEmailFieldError(){
		managePersonalDetailsPage.validateEmailAddressField();
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction fillValidDataInMPDPage(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		//managePersonalDetailsPage.fillValidDataInManagePersonalDetailsPage(userProfile);
		//managePersonalDetailsPage.verifyMPDPageFields();
		managePersonalDetailsPage.fillValidDataInManagePersonalDetailsPage(strEmail,strTempPwd,mobileNumber);
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction fillValidDataInEmailField(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		//managePersonalDetailsPage.fillValidDataInManagePersonalDetailsPage(userProfile);
		managePersonalDetailsPage.fillEmailChangeData(strEmail);
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction verifyEmailChangeDataWithDB(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.verifyEmailChangeWithDB(strEmail);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction verifyPasswordChangeDataWithDB(UserProfile userProfile) {
		//ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.verifyPasswordChangeWithDB(strEmail,strTempPwd);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction verifyFillDataWithSiebel(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.verifyFillDataWithSiebel(userProfile);
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction ClickSaveChangesButton() {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.ClickSaveChangesButton();
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction ClickSaveChangesButtonandwithoutAlert() {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.ClickSaveChangesButtonandwithoutAlert();
		return new ManagePersonalDetailsAction();
	}

	
	/*  public AccountOverViewAction VerifyConfirmationOverLayGoToMyAccount(UserProfile userProfile) {
        ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
       // managePersonalDetailsPage.VerifyConfirmationOverLayGotToMyAccount();
       // managePersonalDetailsPage.verifyEmailChangeAndConfirmationOverlay(userProfile);
        return new AccountOverviewAction();
    }*/

	public LoginAction verifyConfirmationOverLayClickLogin(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		//managePersonalDetailsPage.verifyConfirmationOverLayAndClickLogin();
		managePersonalDetailsPage.clickYesInConfirmationOverlay(userProfile);
		return new LoginAction();
	}

	public LoginAction logout() {
		managePersonalDetailsPage.clickLogout();
		return new LoginAction();
	}
	/* public ManagePersonalDetailsAction verifyFieldValidation() {
    	ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
    	managePersonalDetailsPage.verifyFieldValidation();
    	return new ManagePersonalDetailsAction();
    }*/

	//TC_MPD_015
	public ManagePersonalDetailsAction verifyPasswordChangeOverLay(UserProfile userProfile) {
		ManagePersonalDetailsPage managePersonalDetailsPage = new ManagePersonalDetailsPage();
		managePersonalDetailsPage.fillPasswordChangeData();
		managePersonalDetailsPage.ClickSaveChangesButton();
		managePersonalDetailsPage.clickYesInConfirmationOverlay(userProfile);    	
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction verifyThankYouPage(){
		managePersonalDetailsPage.verifyThankYouPage();
		//managePersonalDetailsPage.clickGotoLogin();
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction checkNameDetails(UserProfile userProfile){
		managePersonalDetailsPage.checkNameDetails(userProfile);
		return new ManagePersonalDetailsAction();
	}

	public ManagePersonalDetailsAction verifyAuditDetails(UserProfile userProfile){
		managePersonalDetailsPage.verifyAuditDetails(userProfile,MPD_SAP_UPDATE_SUCCESS);
		managePersonalDetailsPage.verifyAuditDetails(userProfile,MPD_DB_UPDATE_SUCCESS);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction fillNewEmailField(UserProfile userProfile){
		managePersonalDetailsPage.fillNewEmailField(strEmail);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction resetEmailField(){
		managePersonalDetailsPage.resetEmailField(strEmail);
		return new ManagePersonalDetailsAction();
	} 

	public ManagePersonalDetailsAction fillNewPasswordField(UserProfile userProfile){
		managePersonalDetailsPage.fillNewPasswordField(strTempPwd);
		return new ManagePersonalDetailsAction();
	} 
	public ManagePersonalDetailsAction updateTestPassword(UserProfile userProfile){
		managePersonalDetailsPage.updateTestPassword(userProfile);
		return new ManagePersonalDetailsAction();
	} 
	public ManagePersonalDetailsAction fillNewMobileNumberField(UserProfile userProfile){
		managePersonalDetailsPage.fillNewMobileNumberField(userProfile);
		return new ManagePersonalDetailsAction();
	} 
	public ManagePersonalDetailsAction verifyMobileNumberChangeDataWithSAPCRM(UserProfile userProfile){
		managePersonalDetailsPage.verifyMobileNumberChangeDataWithSAPCRM(userProfile);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction fillEmailAndPasswordFieldAlone(UserProfile userProfile){
		managePersonalDetailsPage.fillEmailAndPasswordFieldAlone(strEmail,strTempPwd);
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction checkPersonalizeSection(UserProfile userProfile){
		managePersonalDetailsPage.checkMRAndBNinEmailSection();
		managePersonalDetailsPage.checkMRAndBNinSMSSection();
		managePersonalDetailsPage.checkMRinEmailAndBNinEmailSection();
		managePersonalDetailsPage.checkBNinEmailAndMRinSMSSection();
		return new ManagePersonalDetailsAction();
	}
	public LoginAction clickLoginAndVerifyLoginPage(){
		LegacyLoginPage loginPage = new LegacyLoginPage();
		managePersonalDetailsPage.clickLogin();
	//	loginPage.loginWithMPDChangeData(strEmail,strTempPwd,userProfile,0);
		return new LoginAction();
	}
	public LoginAction verifyMPDUpdatePageAndClickLogout(UserProfile userProfile){
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		managePersonalDetailsPage.verifyMPDUpdatePageAndClickLogout();
		legacyLoginPage.loginWithUpdatedPassword(strTempPwd,userProfile);	    
		return new LoginAction();
	}
	public LoginAction verifyMPDUpdatePageAndMakeAnotherChange(UserProfile userProfile){
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		managePersonalDetailsPage.clickMakeAnotherChange();
		managePersonalDetailsPage.fillEmailAndPasswordFieldAlone(strEmail, strTempPwd);
		managePersonalDetailsPage.ClickSaveChangesButton();
		managePersonalDetailsPage.clickLogin(); 
		legacyLoginPage.loginWithMPDChangeData(strEmail, strTempPwd, userProfile, 0);    	
		return new LoginAction();
	}
	public LoginAction validateGotoMyAccountLink(UserProfile userProfile){
		managePersonalDetailsPage.validateGotoMyAccountLink(); 
		AccountSummaryPage accountSummaryPage=new AccountSummaryPage();
		LegacyLoginPage legacyLoginPage = new LegacyLoginPage();
		accountSummaryPage.clickMPDLink(userProfile);
		managePersonalDetailsPage.fillEmailAndPasswordFieldAlone(strEmail, strTempPwd);
		managePersonalDetailsPage.ClickSaveChangesButton();
		managePersonalDetailsPage.clickLogin(); 
		legacyLoginPage.loginWithMPDChangeData(strEmail, strTempPwd, userProfile, 0);  
		return new LoginAction();
	}
	public ManagePersonalDetailsAction validateRetypeEmailFieldError(){
		managePersonalDetailsPage.validateRetypeEmailAddress();
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction validateNewPasswordError(){
		managePersonalDetailsPage.validateNewPasswordField();
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction validateERetypeNewPasswordError(){
		managePersonalDetailsPage.validateRetypeNewPasswordField();
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction validateMobileNumberFieldError(){
		managePersonalDetailsPage.validateMobileNumberField();
		return new ManagePersonalDetailsAction();
	}
	public ManagePersonalDetailsAction validatePasswordMismatchError(){
		managePersonalDetailsPage.passwordMismatchError();
		return new ManagePersonalDetailsAction();
	}
	public  ManagePersonalDetailsAction updateEmailAndVerifyLogout(){
		managePersonalDetailsPage.logoutPresentOrNot();
		return  new ManagePersonalDetailsAction();
	}
	public  ManagePersonalDetailsAction fillNewEmail(UserProfile  userProfile){
		managePersonalDetailsPage.newEmail(userProfile);
		return  new ManagePersonalDetailsAction();
	}
	public  ManagePersonalDetailsAction verifyContractRenewalForFAUser(){
		managePersonalDetailsPage.verifyContractRenewalForFAUser();
		return  new ManagePersonalDetailsAction();
	}
	public  ManagePersonalDetailsAction VerifySAPCRM_MPDVerification(UserProfile  userProfile){
		managePersonalDetailsPage.VerifySAPCRM_MPDVerification(userProfile);
		return  new ManagePersonalDetailsAction();
	}
	
}

