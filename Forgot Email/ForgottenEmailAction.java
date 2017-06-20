package bg.framework.app.functional.action.Slingshot;

import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.page.Slingshot.ForgotEmailPage;

public class ForgottenEmailAction {

	ForgotEmailPage forgotemailpage = new ForgotEmailPage();
	private static String email;
	public ForgottenEmailAction verifyNavigateForgotEmail(){
		forgotemailpage.VerifyForgotEmailPageFields();
		return this;
	}

	public ForgottenEmailAction verifyErrorMessageInCustomerRefNumber(UserProfile userProfile){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.validateCustomerReferenceNumberError(userProfile);
		return this;
	}

	public ForgottenEmailAction verifyEmailRetrivalAndAuditDetails(UserProfile userProfile, int auditID){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.enterForgotEmailData(userProfile.getAccNumber(),userProfile.getTitle(),userProfile.getFirstName(),userProfile.getLastName());
		//forgotemailpage.verifyAuditDetails(userProfile);
		return this;
	}
	public ForgottenEmailAction clickGetEmailAddress(){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.clickGetEmailAddressButton();
		//forgotemailpage.verifyAuditDetails(userProfile);
		return this;
	}
	public ForgottenEmailAction enterValidData(UserProfile userProfile){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.enterForgotEmailData(userProfile.getAccNumber(),userProfile.getTitle(),userProfile.getFirstName(),userProfile.getLastName());		
		return this;
	}
	public ForgottenEmailAction verifyAuditDetails(UserProfile userProfile){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		//forgotemailpage.enterForgotEmailData(userProfile.getAccNumber(),userProfile.getTitle(),userProfile.getFirstName(),userProfile.getLastName());
		forgotemailpage.verifyAuditDetails(userProfile);
		return this;
	}
	//TC015
	public ForgottenEmailAction cancelButtonValidation(UserProfile userProfile){
		//forgotemailpage.clickAndValidateCancelButton();
		forgotemailpage.clickAndValidateCancelButtonWithValues(userProfile);
		return this;
	}

	/*public ForgottenEmailAction verifyForgottenEmailThroughForgottenPasswordLink(UserProfile userProfile, int auditID){
		forgotemailpage.clickAndValidateForgottenPasswordLink(userProfile);
		forgotemailpage.verifyResetPasswordPageFields();
		forgotemailpage.clickForgottenEmailLink();
		forgotemailpage.enterForgotEmailData(userProfile.getAccNumber(), userProfile.getTitle(), userProfile.getFirstName(), userProfile.getLastName());
		forgotemailpage.verifyAuditDetails(userProfile, auditID);
		return this;
	}*/
	public ForgottenEmailAction verifyResetPasswordPageFields(){
		forgotemailpage.verifyResetPasswordPageFields();	
		return this;
	}
	public ForgottenEmailAction clickForgottenEmailLinkInResetPasswordPage(){
		forgotemailpage.clickForgottenEmailLink();	
		return this;
	}

	public ForgottenEmailAction validateRetrievedEmailaddress(UserProfile userProfile){	
		email = forgotemailpage.validateForgotEmailaddress(userProfile);			
		//forgotemailpage.loginWithRetrievedEmail(email, userProfile);
		return this;
	}

	public ForgottenEmailAction clickLoginButton(){
		forgotemailpage.clickLoginButton();	
		return this;
	}
	public AccountSummaryAction loginWithRetrievedEmail(UserProfile userProfile){		
		forgotemailpage.loginWithRetrievedEmail(email, userProfile);	
		return new AccountSummaryAction();
	}
	public ForgottenEmailAction VerifyForgotEmailPageFields(){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.VerifyForgotEmailPageFields();
		return this;
	}
	public ForgotttenPasswordAction verifyResetPasswordPageFieldsFP(){
		forgotemailpage.verifyResetPasswordPageFields();	
		return new ForgotttenPasswordAction();
	}
	public ForgottenEmailAction validateErrorWhenMorethanOneEmailRetrieves(){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.validateErrorWhenMorethanOneEmailRetrieves();
		return this;
	}
	public ForgottenEmailAction clickLoginLink(){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.clickLoginLink();
		return this;
	}
	public ForgottenEmailAction verifyErrorMessageInTitleField(UserProfile userProfile){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.verifyAndValidateForgotEmailTitleFieldContent(userProfile);
		return this;
	}
	public ForgottenEmailAction verifyErrorMessageInFirstName(UserProfile userProfile){
		ForgotEmailPage forgotemailpage = new ForgotEmailPage();
		forgotemailpage.firstNameErrorMsgValidation(userProfile);
		return this;
	}
}
