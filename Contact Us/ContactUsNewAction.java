package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.ContactUsNewPage;

public class ContactUsNewAction {
	
	public ContactUsNewAction navigateToContactUsPage(){
		ContactUsNewPage contactUsNewPage= new ContactUsNewPage ();
		contactUsNewPage.navigateToContactUsPage();
		return this;
		
	}
	public ContactUsNewAction navigateToContactUsPageLogin(){
		ContactUsNewPage contactUsNewPage= new ContactUsNewPage ();
		contactUsNewPage.navigateToContactUsPageLogin();
		return this;
		
	}
	public ContactUsNewAction submitQuerry(UserProfile userProfile, String strType){
		ContactUsNewPage contactUsNewPage= new ContactUsNewPage ();
		contactUsNewPage.submitQuerry(userProfile,strType);
		return this;
		
	}
	public ContactUsNewAction verifyLoginLandingPageFunctionality(UserProfile userProfile){
		ContactUsNewPage contactUsNewPage= new ContactUsNewPage ();
		contactUsNewPage.verifyLoginLandingPageFunctionality(userProfile);
		return this;
		
	}


}
