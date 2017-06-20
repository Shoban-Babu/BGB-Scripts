package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.*;
import org.testng.annotations.Test;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.action.Slingshot.ForgotttenPasswordAction;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;

public class ForgotEmailTest extends TestBase{
	
	UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");	
	   
	    @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC002 : To verify whether the 'I've Forgotten my email' link present in Login page
	    public void verifyForgotEmailLink() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies whether the Forgotten my email link present in Login page");
	          new LoginAction()
	              .navigateToBgbLogin()
	              .verifyAndClickForgottenEmailLink()
	              .clickLoginLink();	                
	    }	    	   
	    
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC004: To verify error message thrown in Forgotten Email journey when customer enters invalid Contract account number
	    public void validateErrorMessageForContractAccountNumber() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies error message thrown in Forgotten Email journey when customer enters invalid contract account number");	        
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .VerifyForgotEmailPageFields()
	             .verifyErrorMessageInCustomerRefNumber(userProfile)
	             .clickLoginLink();;	                
	    }
	   
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC010 : Verify that user is able to successfully retrieve the email address in the Forgotten Email Journey  by providing valid user entries
	    public void emailRetrieveOnSuccessfulJourney() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies user is able to successfully retreive the email address in the Forgotten Email Journey");	        
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .enterValidData(userProfile)
	              //.clickGetEmailAddress()	              
	              .validateRetrievedEmailaddress(userProfile)	   
	              //.verifyAuditDetails(userProfile)
	              .clickLoginLink(); 
	          new LoginAction()
	          .bgbloginDetails(userProfile);	
	          
	    }
	  
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC015 : To verify the functionality of Cancel button in Forgotten Email journey landing page.
	    public void validateCancelButton() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies the functionality of Cancel button in Forgotten Email journey landing page");	       
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .enterValidData(userProfile)
	              .cancelButtonValidation(userProfile);	              
	    }
	   
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC016 : Verify that user is able to successfully complete Forgotten Email Journey from the "Forgotten password" page
	    //TC_Forgotten Email_TC018 : Validate Audit details on successful Forgotten Email journey in Online BGBUSINESS_TA_AUDIT_DETAILS table
	    //TC_Forgotten Email_TC021 : Verify whether "I've forgotten my email address" is accessible for reset your password page
	    public void emailRetrieveFromForgottenPasswordPage() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies that user is able to successfully complete Forgotten Email Journey from the Forgotten password page");
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenPasswordLink()
	              .verifyResetPasswordPageFields()
	              .clickForgottenEmailLinkInResetPasswordPage()
	              .enterValidData(userProfile)
	              .clickGetEmailAddress()	              
	              .validateRetrievedEmailaddress(userProfile)	               
	              .verifyAuditDetails(userProfile)
	              .clickLoginLink();
	              ;	    
	              new ForgotttenPasswordAction().resetToOldPassword(userProfile);
	    }
	  
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC011: Verify whether proper message is displayed to the user when more than one Email Id’s are fetched based on the personal details.
	    public void verifyErrorMessageForMorethanOneEmailRetrieve() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies whether proper message is displayed to the user when more than one Email Id are fetched based on the personal details");
	        //new HomePageAction().BgbnavigateToLogin();
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()	              	              
	              .validateErrorWhenMorethanOneEmailRetrieves()
	              .clickLoginLink();
	    }
	    
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC019 : Verify that user is able to login on successful completion of  Forgotten Email Journey 
	    //TC_Forgotten Email_TC020 : Verify whether the retrieved email address is displayed in the confirmation page of retrieve your email address journey
	    public void loginAfterSuccessfulRetrievelOfEmail() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies that user is able to successfully login after completion of  Forgotten Email Journey ");
	          new LoginAction()
	              .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .enterValidData(userProfile)
	              .clickGetEmailAddress()	              
	              .validateRetrievedEmailaddress(userProfile)	
	              .clickLoginLink()
	              .verifyAuditDetails(userProfile)
	              .loginWithRetrievedEmail(userProfile)
	              .BgbverifyLogin(userProfile)
	              .logOut()
	              ;	                
	    }
	  
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC022: Verify the "Forgotten Email" journey from the link in the error message displayed on Login page when user enters incorrect password for the first time or second time
	    public void loginWithIncorrectPasswordAndAccessForgottenEmailLink() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies the Forgotten Email journey from the link in the error message displayed on Login page when user enters incorrect password");	   
	          new LoginAction()	 
	              .navigateToBgbLogin() 
	          	  .EnterInvalidEmailorPwd(userProfile)
	              .verifyAndClickForgottenEmailLink()
	              .enterValidData(userProfile)
	              .clickGetEmailAddress()	              
	              .validateRetrievedEmailaddress(userProfile)	               
	              .verifyAuditDetails(userProfile)	 
	              .clickLoginLink()
	              ;	                
	    }
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC005 To verify error message thrown in Forgotten Email journey when customer enters invalid Title
	    public void validateErrorMessageForTitleField() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies error message thrown in Forgotten Email journey when customer enters invalid Title");	       
	          new LoginAction()
	          	  .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .VerifyForgotEmailPageFields()
	             .verifyErrorMessageInTitleField(userProfile)
	             .clickLoginLink();;	                
	    }
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC006 : To verify error message thrown in Forgotten Email journey when customer enters invalid First name
	    public void validateErrorMessageForFirstName() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies error message thrown in Forgotten Email journey when customer enters invalid First name");	       
	          new LoginAction()
	          .navigateToBgbLogin() 
	              .verifyAndClickForgottenEmailLink()
	              .VerifyForgotEmailPageFields()
	             .verifyErrorMessageInFirstName(userProfile)
	             .clickLoginLink();;	                
	    }
	   @Test(groups ={Slingshot,Regression,ForgotEmail})
	    //TC_Forgotten Email_TC006 : To verify error message thrown in Forgotten Email journey when customer enters invalid First name
	    public void verifyEmailLinkInErrorMessage() {
	        Report.createTestLogHeader("Forgot Email Journey", "Verifies the functionality of forgotten email link in error message displayed in login page");    
	          new LoginAction()
	          .navigateToBgbLogin() 
	          .EnterInvalidPassword(userProfile)
	          .verifyErrorMessageAndClickForgottenEmailLink(userProfile)
	          .VerifyForgotEmailPageFields()
	          .verifyErrorMessageInFirstName(userProfile)
	          .clickLoginLink();;                
	    }
}
