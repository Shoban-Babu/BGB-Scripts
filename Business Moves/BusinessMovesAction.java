/**
 * 
 */
package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.DirectDebit;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.BusinessMovesPage;
import bg.framework.app.functional.page.Slingshot.MakingComplaintsPage;
import bg.framework.app.functional.page.Slingshot.ThankYouPage;

/**
 * @author 292238
 *
 */
public class BusinessMovesAction {
	
	public BusinessMovesAction enterMovingOutdate(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterMovingOutdate("3");
		businessMovesPage.enterPhoneNumber(userProfile);
		return this;
	}
	
	public BusinessMovesAction clickContinue(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickContinue();
		return this;
	}
	
	public BusinessMovesAction leavingPropertyDetails(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.leavingPropertyDetails(userProfile);
		return this;
	}
	
	public BusinessMovesAction verifyBillingAddressPage(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyBillingAddressPage();
		return this;
	}
	public BusinessMovesAction selectCurrentAddress(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.selectCurrentAddress();
		return this;
	}
	public BusinessMovesAction enterDifferentAddress(UserProfile userProfile, String addr){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterDifferentAddress(userProfile, addr);
		return this;
	}
	
	public BusinessMovesAction enterDifferentAddress(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterDifferentAddress(userProfile);
		return this;
	}
	
	public BusinessMovesAction clickContinueButton(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickContinueButton();
		return this;
	}
	public BusinessMovesAction verifyErrorPage(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyErrorPage();
		return this;
	}
	
	public BusinessMovesAction verifySummaryPage(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifySummaryPage();
		return this;
	}
	

	public BusinessMovesAction verifyAnnonymousSummaryPage(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyAnnonymousSummaryPage();
		return this;
	}
	
	public BusinessMovesAction checkMovingNewPremises(String premises/*,UserProfile userProfile*/){

		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.checkMovingNewPremises(premises/*,userProfile*/);
		return this;
	}
	
	public BusinessMovesAction verifyConfirmation(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyConfirmation();
		return this;
	}
	public BusinessMovesAction anonymousMovingPremisesHome(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.anonymousMovingPremisesHome();
		//businessMovesPage.clickMovingOutLink();
		return this;
	}
	public BusinessMovesAction clickMovingOutLink(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickMovingOutLink();
		return this;
	}
	
	public BusinessMovesAction enterYourDetailsMovingOut(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterYourDetailsMovingOut(userProfile);
		return this;
	}
		public BusinessMovesAction enterYourMovingOutAddress(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterYourMovingOutAddress(userProfile,"manual");
		businessMovesPage.clickConfirmAddress();
		return this;
	}
		public BusinessMovesAction clickConfirmAddress(){
			BusinessMovesPage businessMovesPage = new BusinessMovesPage();
			businessMovesPage.clickConfirmAddress();
			return this;
		}
	public BusinessMovesAction submitNewPropertyDetails(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.submitNewPropertyDetails();
		return this;
	}
	public BusinessMovesAction checkNewMovingInOption(String strMovingInOption){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.checkNewMovingInOption(strMovingInOption);
		return this;
	}
	public BusinessMovesAction clickMovingInLink(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickMovingInLink();
		return this;
	}
	public BusinessMovesAction enterYourDetailsMovingIn(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterYourDetailsMovingIn(userProfile);
		return this;
	}
	public BusinessMovesAction enterMovingInDate(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.enterMovingInDate();
		return this;
	}
	public BusinessMovesAction verifyAuditForConfirmation(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyAuditForConfirmation(userProfile);
		return this;
	}
	public BusinessMovesAction submitNpsSurvey(UserProfile userProfile){
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
	public BusinessMovesAction verifyThankYouSurveyPage(UserProfile userProfile){
		ThankYouPage thankyoupage=new ThankYouPage();
		thankyoupage.selectFeedbackOption(userProfile.getFeedbackoption());
		thankyoupage.feedbackReason(userProfile.getFeedbacktext());
		thankyoupage.clickFeedbackSendButton();
//		thankyoupage.verifySurveyTableInDb(userProfile.getEmail(),"THANKYOU",userProfile.getFeedbackoption(),userProfile.getFeedbacktext());
		thankyoupage.closeThankYouOverlay();
		return this;
	}
	
	public BusinessMovesAction clickCacelButton(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickCacelButton();
		return this;
	}
	public BusinessMovesAction verifyEditAddressLink(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyEditAddressLink(userProfile);
		businessMovesPage.verifyAddressInBillingSection(userProfile);
		return this;
	}
	/*public BusinessMovesAction verifyEditAddressLinkInMovingOutAndBillingAddrSec(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyEditAddressLinkInMovingOutAndBillingAddrSec();
		return this;
	}*/
	public BusinessMovesAction checkRequestCallBack(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.checkRequestCallBack();
		businessMovesPage.verifyCallBackThankYouPage();
		return this;
	}
	public BusinessMovesAction verifyAuditEntry(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyAuditEntry(userProfile);
		return this;
	}
	public BusinessMovesAction verifyPodsInConfirmationPage(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyPodsInConfirmationPage();
		return this;
	}
	public BusinessMovesAction verifyBusinessMoves_AnonymousCallBack(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyBusinessMoves_AnonymousCallBack(userProfile);
		businessMovesPage.verifyCallBackThankYouPage_Anonymous();
		return this;
	}
/*	public BusinessMovesAction verifyAuditEntry_Anony(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyAuditEntry_Anony(userProfile);
		return this;
	}*/
	public BusinessMovesAction verifyAuditEntryMovingOut(UserProfile userProfile){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifyAuditEntryMovingOut(userProfile);
		return this;
	}
	
	public BusinessMovesAction logout(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.logout();
		return this;
	}
	public BusinessMovesAction clickRequestCallBack(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.clickRequestCallBack();
		return this;
	}
	public BusinessMovesAction selectContinueButton(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
	//	businessMovesPage.selectContinueButton();
		return this;
	}
	public BusinessMovesAction linkVerificationMovingOut(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.linkVerificationMovingOut();
		return this;
	}
	public BusinessMovesAction verifySummaryPageEditDetails(){
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.verifySummaryPageEditDetails();
		return this;
	}

	public BusinessMovesAction browserback() {
		BusinessMovesPage businessMovesPage = new BusinessMovesPage();
		businessMovesPage.browserback();
		return null;
	}
}
