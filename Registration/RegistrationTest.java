package bg.framework.app.functional.test.Slingshot;

import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.SapCrmAction;
import static bg.framework.app.functional.entities.FunctionalCategory.BGBRegistration;
import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Smoke;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import bg.framework.app.functional.action.common.HomePageAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{
	
   // TC_Registration_001 validate whether user will perform registration process from the invitation email triggered by customer service agent
   // TC_Registration_046 Validate Audit details on successful Registration journey in Online BGBUSINESS_TA_AUDIT_DETAILS table
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Smoke,BGBRegistration})	
    public void verifyRegistrationbyCsaAgent() throws Exception {
        Report.createTestLogHeader("Registration", "To Verify user perform registration Gas account from the CSA Agent");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
            .navigateToCsaRegistration()
            .navigateToCsaRegister()
            .registerdetails(userProfile)
            .verifyAuditdetails(userProfile, "26")
            .clickLoginlinkAfterRegistration()
            .BgbnavigateToLogin()
            .BgbloginDetails(userProfile)
            .logout();
    }
	
//  TC_Registration_003,4,5,18,40 Verify whether the customer with account status "freezed" ,Locked,Unlocked is not allowed to do a successful registration and a appropriate prompt  message is getting displayed
//  TC_Registration_018 Verify whether the error message displayed during registration journey for customers with no valid accounts.
//  TC_Registration_040 To verify whether an user can complete a Normal Registration journey for  Gas using xxx Customer reference number under yyy BP number for the first time with one user id	
// TC_Registration_055 Verify the status in Online DB as "still to be validated customer" when the customer hits the "Confirm" button after providing the user details for registration process
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void verifyInvalidAccountsInRegistration() throws Exception {
        Report.createTestLogHeader("Registration", "Verify Invalid account registration and its error messages");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
            .navigateToBgbRegistration()
            .registerdetailsthroughBusiness(userProfile)
            //status Freezed-1,Locked-2,Active-3,Invalid-4
            .updateAccountDetails(userProfile,1,"update")
            .openBusinessRegisterurl()
            .enterDetailsFirstPage(userProfile)
            .verifyErrorinBusinessRegisterPage(1)
            .updateAccountDetails(userProfile,1,"revert")
            .openBusinessRegisterurl()
            .updateAccountDetails(userProfile,2,"update")
            .enterDetailsFirstPage(userProfile)
            .verifyErrorinBusinessRegisterPage(2)
            .updateAccountDetails(userProfile,2,"revert")
            .openBusinessRegisterurl()
            .enterDetailsFirstPage(userProfile)
            .verifyErrorinBusinessRegisterPage(3)
            .openBusinessRegisterurl()
            .validateInvalidAccount(userProfile)
            .enterDetailsFirstPage(userProfile)
            .verifyErrorinBusinessRegisterPage(4); 
    }
	
//TC_Registration_007 To validate the "Customer reference number" field in Registration journey business detail section.
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void validateCustomerReferenceNo() throws Exception {
        Report.createTestLogHeader("Registration", "To validate the Customer reference number in the registration page");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
//        deregisterinBgbonline(userProfile); 
        new BgbRegistrationAction()
            .openBusinessRegisterurl()
            .validateCustomerReference(userProfile);

    }
     
// TC_Registration_008 To validate the "Invoice Number" field in Registration journey business detail section.
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void validateInvoiceNumber() throws Exception {
        Report.createTestLogHeader("Registration", "To validate the Invoice Number in the registration page");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
//        deregisterinBgbonline(userProfile); 
        new BgbRegistrationAction()
            .openBusinessRegisterurl()
            .validateInvoiceNumber(userProfile);

    }		
	
// TC_Registration_009 To validate the "Postal code" field in Registration journey business detail section.
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void validatePostCode() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the Post Code in the registration page");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");  
//	        deregisterinBgbonline(userProfile); 
	        new BgbRegistrationAction()
	            .openBusinessRegisterurl()
	            .validatePostCode(userProfile);

	    }	
	
// TC_Registration_010 To validate the "Email address" field in Registration journey business detail section.
// TC_Registration_011 To validate the "Confirm Email address" field in Registration journey  business detail section.		
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void validateEmailAddressField() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the Email address in the registration page");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
//	        deregisterinBgbonline(userProfile); 
	        new BgbRegistrationAction()
	            .openBusinessRegisterurl()
	            .validateEmailField(userProfile);
	    }		
    
// TC_Registration_012 To validate the functionality of the cancel link present in the  registration landing page under  business detail section.(Query - CL-89)
//		
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void validateCancelLinkInRegisterPage() throws Exception {
        Report.createTestLogHeader("Registration", "To validate the Cancel link in the registration page");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        new BgbRegistrationAction()
            .openBusinessRegisterurl()
            .validateCancelLink(userProfile);
    }
// TC_Registration_013,014 Validate whether the customer is not able to register for a BP which has got 3 registered user already.
// TC_Registration_13 Validate whether the customer is not able to register for a BP which has got 3 registered user already
// TC_Registration_024	
//	Verify that already registered (BP registered thrice) customer  is not able to do Registration journey again and they are displayed with appropriate message. 
	@SuppressWarnings("unchecked")		
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
	public void verifyMoreThanThreeRegistration() throws Exception {
	    Report.createTestLogHeader("Registration", "Validate whether the customer is not able to register for a BP which has got 3 registered user already");
	    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	    deregisterinBgbonline(userProfile);        
	       new BgbRegistrationAction().
	           validateMoreThanThreeRegisterUser(userProfile);
	        
}				

//TC_Registration_021 To validate the "Title" field and its error messages in Registration journey user detail section.  
		@SuppressWarnings("unchecked")		
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyTitleContentAndErrorMsg() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the Title field and its error messages in Registration journey user detail section.");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .enterDetailsFirstPage(userProfile)
	            .valdiateTitleInRegistration(userProfile);
		}

//TC_Registration_022 To validate the "First name" field and its error messages in Registration journey user detail section.  
		@SuppressWarnings("unchecked")		
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyFirstnameAndErrorMsg() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the First Name field and its error messages in Registration journey user detail section.");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .enterDetailsFirstPage(userProfile)
	            .validateFirstname(userProfile);
		}

//TC_Registration_023 To validate the "Sur name" field and its error messages in Registration journey user detail section.  
		@SuppressWarnings("unchecked")		
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifySurnameAndErrorMsg() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the Sur Name field and its error messages in Registration journey user detail section.");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .enterDetailsFirstPage(userProfile)
	            .validateSurname(userProfile);
		}

//TC_Registration_025 To validate the "Password" field and its error messages in Registration journey  user detail section.
//TC_Registration_26 To validate the " Confirm Password" field and its error messages in Registration journey user detail section.
		@SuppressWarnings("unchecked")		
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyPasswordAndErrorMsg() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate the Password field and its error messages in Registration journey user detail section.");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .enterDetailsFirstPage(userProfile)
	            .validatePassword(userProfile);
		}

	    
// TC_Registration_027 To validate whether password  entered during registration journey is case sensitive next login.
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void verifyPasswordIsCaseInsensitive() throws Exception {
        Report.createTestLogHeader("Registration", "To validate whether password  entered during registration journey is case sensitive");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
            .navigateToBgbRegistration()
            .setDataForPassword(userProfile)
            .registerdetailsthroughBusiness(userProfile)
            .verifyEnteredPasswordAndSet(userProfile)
            .clickLoginAndVerify(userProfile);
        new AccountSummaryAction().verifyAuditdetails(userProfile,"4");

    }
// TC_Registration_028 To validate whether Email address   entered during registration journey is case insensitive during next login.
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyEmailIdIsCaseInsensitive() throws Exception {
	        Report.createTestLogHeader("Registration", "To validate whether Email address   entered during registration journey is case insensitive during next login");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .setDataForEmail(userProfile)
	            .registerdetailsthroughBusiness(userProfile)
	            .verifyEnteredEmailAndSet(userProfile)
	            .clickLoginlinkAfterRegistration()
	            .BgbnavigateToLogin()
	            .BgbloginDetails(userProfile)
	            .logout();

	    }    	

// TC_Registration_034 validate whether customer cannot reuse the activation link after the validation process completed successfully
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void verifyActivationLinkNotReuse() throws Exception {
        Report.createTestLogHeader("Registration", "validate whether customer cannot reuse the activation link after the validation process completed");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
	        .navigateToCsaRegistration()
	        .navigateToCsaRegister()
            .registerdetails(userProfile)
            .verifyExpiredLink(userProfile);

    }		    

// TC_Registration_036 Verify that customer can not able to do login journey  again with same email address entered during registration journey
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void verifyLoginWithInactiveAccount() throws Exception {
        Report.createTestLogHeader("Registration", "Verify that customer can not able to do login journey  again with same email address entered during registration journey without validating the registration link");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
            .navigateToCsaRegistration()
	        .navigateToCsaRegister()
            .verifyLoginWithEmailasN(userProfile)
            .BgbnavigateToLogin()
            .BgbloginDetails(userProfile)
            .verifyErrorMsginLoginScreen();
        /*// Revert and open the link successfully
        new BgbRegistrationAction().verifyExpiredLink(userProfile);*/
    }
    

// TC_Registration_046 Validate Audit details on successful Registration journey in Online BGBUSINESS_TA_AUDIT_DETAILS table
	@SuppressWarnings("unchecked")
	@Test(groups ={Slingshot,Regression,BGBRegistration})	
    public void verifyAuditDetails() throws Exception {
        Report.createTestLogHeader("Registration", "Validate Audit details on successful Registration journey ");
        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
        deregisterinBgbonline(userProfile);        
        new HomePageAction()
            .navigateToCsaRegistration()
            .navigateToCsaRegister()
            .registerdetails(userProfile)
            .verifyAuditdetails(userProfile,"26");

    }  

// TC_Registration_050 Verify whether the Browser back functionality is working properly in all the pages of the Registration journey.  
		@SuppressWarnings("unchecked")		
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyBrowserBack() throws Exception {
	        Report.createTestLogHeader("Registration", "Verify whether the Browser back functionality is working properly in all the pages of the Registration journey");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .validateBrowserBack(userProfile,0)
	            .enterDetailsFirstPage(userProfile)
	            .validateBrowserBack(userProfile,1)
	            .enterDetailsFirstPage(userProfile)
	            .enterDetailsSecondPage(userProfile)
	            .validateBrowserBack(userProfile,2)
	            .validateBrowserBackEmail(userProfile);
	            
		}

// TC_Registration_058 Verify whether the Market consent option is  available for the customer during the registration process in user details screens  
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyTermsAndConditionsInRegistration() throws Exception {
	        Report.createTestLogHeader("Registration", "Verify whether the Terms and Conditions is available");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .checkTermsAndConditions(userProfile);

		}	
		
// Marketing consent
		// TC_Registration_058 Verify whether the Market consent option is  available for the customer during the registration process in user details screens  
		@SuppressWarnings("unchecked")
		@Test(groups ={Slingshot,Regression,BGBRegistration})	
	    public void verifyMarketingConsentInRegistration() throws Exception {
	        Report.createTestLogHeader("Registration", "Verify whether the Market consent is available");
	        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
	        deregisterinBgbonline(userProfile);        
	        new HomePageAction()
	            .navigateToBgbRegistration()
	            .checkMarketingConsentInRegister(userProfile);
		}	
			
// TC_Registration_059 Verify Activation link after 28 days  
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
		    public void verifyActivationLinkAfter28Days() throws Exception {
		        Report.createTestLogHeader("Registration", "Verify Activation link after 28 days");
		        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
		        deregisterinBgbonline(userProfile);        
		        new HomePageAction()
		            .navigateToBgbRegistration()
		            .verifyActivation28Days(userProfile);
			}	
// TC_Registration_60 Verify registration when the account is freeze and already registered thrice   
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
		    public void verifyRegistrationAfterMorethan3Registration() throws Exception {
		        Report.createTestLogHeader("Registration", "Verify registration when the account is freeze and already registered thrice");
		        UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");       
		        deregisterinBgbonline(userProfile);        
			       new BgbRegistrationAction()
			           .validateMoreThanThreeRegisterUser(userProfile)
			           .changeAccountStatusAndVerify(userProfile);
			        
			}	
			//BP2 - TC_Registration_001 Verify whether customer is able to register gas and electricity accounts and verify whether online customer created in SAP.
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyRegistrationforGasandElec() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify successfull Registration for Gas and Electricity Accounts");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbGasAccount");
			    UserProfile userProfile1 = new TestDataHelper().getUserProfile("BgbElecAccount");
			    deregisterinBgbonline(userProfile); 
			    deregisterinBgbonline(userProfile1);   
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness(userProfile);
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness(userProfile1);
			}
			
//BP2 - TC_Registration_015 Verify whether user is assigned super user role (RP01) when registered for the first time with BP_org in SAP in Online DB.
//BP2 - TC_Registration_016 Verify whether user is assigned standard user role (Reads, bills and payments) when registered after the first time with BP_org in Online DB.
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifySuperUserandStandardUserRoleforRegistration() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify user is assigned super user role (RP01) when registered for the first time and Standared User role for the rest with BP_org");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
			    deregisterinBgbonline(userProfile); 
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .moreRegisterUser(userProfile);
			}	
//BP2 - TC_Registration_020 Verify whether registered customer can login and able to see a summary of all their accounts including contracts
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifysummaryofAccountsAfterRegistration() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify user is assigned super user role (RP01) when registered for the first time and Standared User role for the rest with BP_org");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
//  			deregisterinBgbonline(userProfile); 
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness(userProfile)
			    	.BgbnavigateToLogin()
			    	.BgbloginDetails(userProfile);
		    		
			}	
			//RP3 - TC_Reg_01	"Verify the End to End flow when paperless billing is selected during registration.
			//	1. Updated Thankyou Email on Registration(BP3  template).
			//	2. Confirmation page with updated text. (BP3  template).
			//	3. Audit entry on paperless
			//  4. SAP CRM updation on Paperless status"

			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyPaperlessinRegistration_Selected() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify the End to End flow when paperless billing is selected during registration");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
			   // CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");  
			new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness(userProfile)
			        .verifyAuditForPaperLessOption(userProfile)
			    	.BgbnavigateToLogin()
			    	.BgbloginDetails(userProfile);
				/* new SapCrmAction()
				 .loginDetails(crmuserProfile)
				 .verifyPaperLessStatus(crmuserProfile, userProfile);*/
			}
			//RP3 - TC_Reg_02	"Verify the End to End flow when paperless billing is NOT selected during registration.
			//	1. Thankyou  Email on Registration(BP2  template).
			//  2. Confirmation page (BP2  template).
			//	3. Audit entry on paperless
			//	4. SAP CRM updation on Paperless status

			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyPaperlessStatusRegistration_NotSelected() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify the End to End flow when paperless billing is Not selected during registration");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
			    CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");  
			   new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness_paperLessOptionUncheck(userProfile, "No")
			        .verifyAuditForPaperLessOption(userProfile)
			    	.BgbnavigateToLogin()
			    	.BgbloginDetails(userProfile);
				 new SapCrmAction()
				 .loginDetails(crmuserProfile)
				 .verifyPaperLessStatus(crmuserProfile, userProfile);
			}
			
			//RP3 : TC_Reg_04	Verify the updated content of the ‘What can I do with my online account?’ link in 'Account details' page of  registration  Journey   
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyWhatCanIdoLink() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify the updated content of the What can I do with my online account link in Account details page of  registration  Journey");
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .verifyWhatCanIdoLink();
}
			//RP3 : TC_Reg_10	Verify the content of the "Terms and conditions" section  when the user is registered for the first time in the "Register for an online account" page(with dynamic check box)
			//TC_Reg_11	Verify the content of the "Terms and conditions" section  when the subsequent user is registered (with dynamic check box)
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyTermsAndConditionContent() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify the updated content of the What can I do with my online account link in Account details page of  registration  Journey");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .registerdetailsthroughBusiness_paperLessOptionUncheck(userProfile,"Yes");
}
			
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verify() throws Exception {
			    Report.createTestLogHeader("Registration", "Verify the updated content of the What can I do with my online account link in Account details page of  registration  Journey");
			    UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
			    new HomePageAction()
			        .navigateToBgbRegistration()
			        .verifyAccountDetailsPage(userProfile)
			        .registerForAnOnlineAccount(userProfile);
			        
			        
			}
	
/*****************************************************************************************************************************************************************************************/			
			
/** Digital Wave Project
 * August 2015
 * Created by -Zeeshan Ahamed N
 */
			
			
/******************************************DIGITAL WAVE***************************************************************************************************/			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyDemo() throws Exception {
				Report.createTestLogHeader("Registration", "verifyRegistration");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile);
			    /* .thankYouRegistered();*/
			     
				
				}
			
			
/**********************************************************************Email Already Registered Page************************************************************/			
//BGBD_68			
//TS_BGBD_68_01	 To verify if the customer is successfully navigated to the already registered page when trying to register with the email address that is already registered with british gas business.
//TS_BGBD_68_03  To verify if the customer is navigated to the login page when clicking on the "Log in" link from the already registered page.
//TS_BGBD_68_04  To verify if the customer is able to successfully reset the password on hitting the link "Reset Your Password" from already registered page.
//TS_BGBD_68_05  To verify if the customer is able to navigate back to the registration page on clicking the "Back to registration" link from the "Already Registered" page.
		   
//precondition Already registered email address in user profile/////////////////			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			    public void verifyAlreadyRegisteredPage() throws Exception {
				Report.createTestLogHeader("Registration", "verifyEmailAlreadyRegisteredPage");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.verifyEmailAlreadyRegisteredPage();
				
			
			}
/**********************************************************************Call Back Section in Email Existing************************************************************/
//BGBD_226		
//TS_BGBD_226_01	Verify whether the "call BGB to add missing accounts"pod is displayed in the Email address already registered page.
		
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			    public void verifyCallBackSection() throws Exception {
				Report.createTestLogHeader("Registration", "verifyAddMissingAccountSection");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.verifyCallBackSection();
			}
/********************************************************************************Reset password From Already Registered email address*****************************************************************************************************/			
//TS_BGBD_68_04  To verify if the customer is able to successfully reset the password on hitting the link "Reset Your Password" from already registered page.
	
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			    public void verifyPasswordResetFromEmailRegisteredSection() throws Exception {
				Report.createTestLogHeader("Registration", "verifyPasswordResetsection");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.resetYourPasswordSucessful(userProfile);
			}			
			
			
/**********************************************************************Account Details Validation************************************************************/			
// BGBD_221
// TS_BGBD_221_02   To verify if the customer is successfully navigated to the next page after entering the correct details in the registration page and also to the registration landing page when clicking on the "Cancel" button.

			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyAccountLoginDetails() throws Exception {
				Report.createTestLogHeader("Registration", "verifyAccountLoginDetailsPage");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.cancelButton();
				}
/**********************************************************************Empty Email And password Validation************************************************************/			
//TS_BGBD_221_03
// To verify if the customer is displayed with; error message "Please enter valid email address" when the email address;field is left empty and then the clicks on "Continue" button.
//To verify if the customer is displayed with the error message "Please confirm Your password" when the confirm password field is left empty and clicks on "Continue" button			
			
//Precondition -- Empty email and password field in user profile ////////////
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyEmailAndConfirmPsswordFieldValidation() throws Exception {
				Report.createTestLogHeader("Registration", "verifyEmailandConfirmFieldErrorMessage");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.emailAddressFieldValidation()
				.confirmPasswordFieldValidation();
				}
/**********************************************************************Password less Than Eight Characters************************************************************/			
// TS_BGBD_221_06 To verify if the customer is displayed with the error message "Please enter a password between 8 and 32 letters, numbers and special characters". When the user enters a password less than 8 characters.
	
///PreCondition ---Password should be less than eight Characters////			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void passwordLessThanEightCharactersValidation() throws Exception {
				Report.createTestLogHeader("Registration", "passwordLessThanEightCharactersValidation");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .passwordLessThanEightValidation();
				}

/***********************************************************************************Email And password Miss Match Validation************************************************************/			
//TS_BGBD_221_04       To verify if the customer is displayed with; error message "Emails dont match" when the customer clicks on "Continue" button and confirm email address field is not matching with the email address field.
//TS_BGBD_221_08       To verify if the customer is displayed with the error message "Your password doesn't match". Please check and try again, when the passwords entered in password and confirm password fields are different and the customer clicks on "Continue" button.
			
//Pre condition  Email And Password should not be same			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyEmailandPasswordMisMatchErrorMessage() throws Exception {
				Report.createTestLogHeader("Registration", "verifyEmailandPasswordMisMatchErrorMessage");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration");
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPageWithMisMatchEmailandPassword(userProfile)
			    .emailMisMatch()
				.passwordMisMatch();
				}

/****************************************************************************************** Your Details Page************************************************************/			
//TC_BGBD_215_01    To verify the look and feel and link navigation of the "Your Details"page of registration journey.
//TC_BGBD_215_05    a.To verify whether the customer is able to provide his telephone number in the telephone number field (optional)
//			        b.To verify whether the customer is able to navigate to "thanks for registering" page without entering the telephone number
					
//TC_BGBD_215_06    To verify if the customer is navigated to the "Thank Your" page after successful validations of personal details in the "Your Details" page.
//TS_BGBD_216_01    To verify the look and feel and also the breadcrumb navigation of the "Your Details" page in the registration journey.
			        
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyYourDetailsPage() throws Exception {
				Report.createTestLogHeader("Registration", "verifyYourDetailsPage");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile);
			    /* .thankYouRegistered();*/
			     
				
				}

/****************************************************************************************** Collective Account User************************************************************/			

//TS_BGBD_216_05     To verify if the collective account customer is successfully able to register for an online account after entering the correct account details in the "Your Details" page.
		
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyCollectiveAccountSucessfulRegister() throws Exception {
				Report.createTestLogHeader("Registration", "verifyCollectiveAccountSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .verifyFeedBack()
			    .thankYouRegistered();
			    new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAuditEntryForEmail(userProfile);
				}
			

/****************************************************************************************** Standard User************************************************************/			
			
//TS_BGBD_216_02     To verify if the standard user is able to successfully register for an online account after entering the correct account details and site postcode.

//TS_BGBD-217_04     Verify Look and Feel of "Accept Terms & Conditions" section in "Your details" page for Standard User customer.			
//TS_BGBD-115_01     Verify whether Standard User is displayed with "Thanks, you're now registered" page after completing "Your details" page via Self-registration journey.
//TS_BGBD-115_05     Verify Link navigations of "Thanks, you're now registered" page for Standard User customer.
	
/*
 * precondition user should be standard user			
 */
			
//TS_BGBD_116_218_10   Verify whether the paper less billing link is not displayed  for in the Thanks,You're registered page
//TS_BGBD_116_218_11   Verify whether the paper less billing link is not displayed  in the Thanks,You're registered page
//TS_BGBD_116_218_12   Verify whether the paper less billing link is not displayed  in the Thanks,You're registered page
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyStandardUserSucessfulRegister() throws Exception {
				Report.createTestLogHeader("Registration", "verifyStandardUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .thankYouRegistered();
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			      .verifyAccountOverviewForStandardUser()
			     .verifyAuditEntryForEmail(userProfile)
			     .registerAuditValidataion(userProfile);
				}			

/****************************************************************************************** Collective Account Overlay************************************************************/			
			
////TS_BGBD_222_01		To verify whether the Customer is able to view an Non-modal overlay on clicking the "collective account" link from the "If you manage a collective account,please enter the group account number." line in the your details page while doing self registration journey
///TS_BGBD_222_02	    To verify look and feel of the "Collective Account Number " Non-modal overlay from "your details " page while doing self registration journey
///TS_BGBD_222_03       To Verify the link navigations of the "Collective Account Number " Non-modal overlay from "your details " page while doing self registration journey

			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyCollectiveAccountOverlay() throws Exception {
				Report.createTestLogHeader("Registration", "verifyCollectiveAccountOverlayInSelfRegistration");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Register Now Button");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .collectiveAccountOverlay();	
				}
			
/****************************************************************************************** Super User************************************************************/
			
//TS_BGBD-115_03  	 Verify Look and Feel of "Thanks, you're now registered" page for Super User customer via Self-registration journey.
//TS_BGBD-115_06	Verify Link navigations of "Thanks, you're now registered" page for Super User customer.	
//TS_BGBD-217_01    Description:Verify whether Super User is displayed with following checkboxes in "Your details" page.
//			1. Terms & Conditions
//			3. Marketing Consent
			
			
			
			/*
			 * precondition user should be Super user			
			 */
			
			
//TS_BGBD_116_218_01 Verify Look and Feel of "Thanks, you're now registered" page for Super User customer via Self-registration journey.
//TS_BGBD_116_218_03 verify Link navigations of "Thanks, you're now registered" page for Super User customer
//TS_BGBD_116_218_04  To verify the Look and feel and link navigation of the `" Super user explained" overlay in the Thanks,You're registered page	
//TS_BGBD_116_218_05  Verify whether the look and feel of the PB pod displayed in the Thanks,You're registered page
//TS_BGBD_116_218_07  To verify whether the success message is pre-populated if the customer selected the papaerless billing and clicked continue your account link 			
//TS_BGBD_116_218_08   Verify  the end to end flow when paperless billing option is chosen in in the Thanks,You're registered page		
//TS_BGBD_116_218_09   Verify whether the paper less billing link is displayed only for super users in the Thanks,You're registered page			
			
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifySuperUserSucessfulRegister() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .verifyConfirmationPageForSuperUserCSA()
			    .verifyAuditEntryForEmail(userProfile)
			    .verifyAuditEntryForSuperUser(userProfile)
			     .registerAuditValidataion(userProfile);
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAccountOverviewForSuperUser();
			     
				}
///////////////////////////////////////////////////////////////////////TS_BGBD_116_218_17////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifySuperUserSucessfulRegisterwithPaperlessOptOut() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .verifyConfirmationPageForSuperUserCSA()
			    .verifyAuditEntryForEmail(userProfile)
			    .verifyAuditEntryForSuperUser(userProfile)
			     .registerAuditValidataion(userProfile);
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAccountOverviewForSuperUser()
			     .paperlessOptout();
			     
			     
				}			
			
			
/**********************************************************************Feedback Validation********************************************************************/			
//TS_BGBD-219_01   Verify whether Standard User is able to Submit Thank you survey in Registration Confirmation page.
//TS_BGBD-219_04   Verify Look and Feel of "Thanks, you're now registered" page for Standard User customer.			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyUserSucessfulRegisterAndFeedback() throws Exception {
				Report.createTestLogHeader("Registration", "verifyUserSucessfulRegisterAndFeedback");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .verifyFeedBack()
			    .thankYouRegistered()
				.feedbackAuditValidataion(userProfile);
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAuditEntryForEmail(userProfile);
				
				}
/*************************************************************************BARCLAYS DEEPLINK**************************************************************************************************/			
///TS_BGBD_220_01    a. Verify whether Standard user is able to register via "www.britishgas.co.uk/business/barclays" deeplink
///			         b. Verify whether Online DB is updated with tag of Barclay			
// TS_BGBD_220_02    a. Verify whether Super user is able to register via "www.britishgas.co.uk/business/your-account/register/?cid=BarclaysOAMRegister." deeplink
//			         b. Verify whether Online DB is updated with tag of Barclay			
		
//By using deeplink "/business/barclays"		
			
			
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyUserSucessfulRegisterByBarclaysDeeplink() throws Exception {
				Report.createTestLogHeader("Registration", "verifyUserSucessfulRegisterByBarclaysDeeplink");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new BgbRegistrationAction()
				.barclaysDeeplink("barclays1")
				//.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .verifyFeedBack()
			    .thankYouRegistered()
				.feedbackAuditValidataion(userProfile)
				.barclaysAuditValidataion(userProfile);
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAuditEntryForEmail(userProfile);
				
				}
			
			
			
//By using deeplink "/business/your-account/register/?cid=BarclaysOAMRegister."		
			
//TS_BGBD_220_02   a. Verify whether Super user is able to register via "www.britishgas.co.uk/business/your-account/register/?cid=BarclaysOAMRegister." deeplink
//			       b. Verify whether Online DB is updated with tag of Barclay			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyUserSucessfulRegisterByBarclaysDeeplink1() throws Exception {
				Report.createTestLogHeader("Registration", "verifyUserSucessfulRegisterByBarclaysDeeplink");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new BgbRegistrationAction()
				.barclaysDeeplink("barclays2")
				//.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    
			    .verifyFeedBack()
			    .verifyConfirmationPageForSuperUserCSA()
			    //.thankYouRegistered()
			    
				.feedbackAuditValidataion(userProfile)
				.barclaysAuditValidataion(userProfile);
			     new LoginAction()
			     .BgbloginDetails(userProfile);
			     new BgbRegistrationAction()
			     .verifyAuditEntryForEmail(userProfile);
				
				}
			
/*********************************************************************************Breadcrumb navigation***************************************************************************************************************************/			
///TS_BGBD_216_01  To verify the look and feel and also the breadcrumb navigation of the "Your Details" page in the registration journey.

				
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyBreadCrumbNavigationOfYourDetailsPage() throws Exception {
				Report.createTestLogHeader("Registration", "verifyBreadCrumbNavigationOfRegistration");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .verifyBreadCrumbInYourdetails();
			}		

/***************************************************************************************************************************************************************************/			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyBreadcrumbNavigationOfAccountDetails() throws Exception {
				Report.createTestLogHeader("Registration", "verifyBreadCrumbNavigationOfRegistration");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				 .verifyBreadCrumbInYourdetails();
			  
			     
				
				}
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyStandardUserSucessfulRegister_New() throws Exception {
				Report.createTestLogHeader("Registration", "verifyStandardUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccountNew01(userProfile)
			    .thankYouRegistered();
			     new LoginAction()
			     .BgbloginUserNew(userProfile);
			     new BgbRegistrationAction()
			      .verifyAccountOverviewForStandardUserNew()
			     .verifyAuditEntryForEmailNew(userProfile)
			     .registerAuditValidataionNew(userProfile);
				}			
			
			
			
			
////////////////////////////////////////////////////////////////////////////////////  July 23rd Release/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////Wave - Create a REgistration with Bill number ////////////////////////////////////////////////////////////////////
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyStandardUserSucessfulRegister_Billnumber() throws Exception {
				Report.createTestLogHeader("Registration", "verifyStandardUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccountNew(userProfile)
			    .thankYouRegistered();
			     new LoginAction()
			     .BgbloginUserNew(userProfile);
			     new BgbRegistrationAction()
			      .verifyAccountOverviewForStandardUserNew()
			     .verifyAuditEntryForEmailNew(userProfile)
			     .registerAuditValidataionNew(userProfile);
				}			

			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifySuperUserSucessfulRegister_Billnumber() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccountNew(userProfile)
			    .RegistrationActivationEmail()
				.Activate_EmailUrl(userProfile)
				.thankYouRegistered();
			    //.verifyConfirmationPageForSuperUserCSA();
			
			    new LoginAction()
			     .BgbloginUserNew(userProfile);
				new BgbRegistrationAction()
			    			    
			    .verifyAuditEntryForEmailNew(userProfile) 
			    .verifyAuditEntryForSuperUserNew(userProfile)
			     .registerAuditValidataionNew(userProfile);
			  
				}	
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyCollectivestandardUserSucessfulRegister_Billnumber() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccountNew(userProfile)
			    .thankYouRegistered();
			    new LoginAction()
			     .BgbloginUserNew(userProfile);
				new BgbRegistrationAction()
			      .verifyAccountOverviewForStandardUserNew()
			     .verifyAuditEntryForEmailNew(userProfile)
			     .registerAuditValidataionNew(userProfile);
			  
				}
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifyErrorMessage_InvalidBillnumber() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccountNew(userProfile)
			    .VerifyError();
			    
				}	
			
//////////////////////////////////// December 1st release ///////////////////////////////////////////////////////////
			
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifydynamicpagewhileRegister_cashcheque() throws Exception {
				Report.createTestLogHeader("Registration", "Verify Dynamic Page While Registering ");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .regisration_dynamic_page_cash_cheque(userProfile)
			    .thankYouRegistered();
			   new LoginAction()
			    .BgbloginDetails(userProfile);
			    new BgbRegistrationAction()
			    .verifyAccountOverviewForStandardUser()
			    .verifyAuditEntryForEmail(userProfile)
			    .registerAuditValidataion(userProfile);
				
			}				
			
		
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifydynamicpagewhileRegister_card_details_dd() throws Exception {
				Report.createTestLogHeader("Registration", "Verify Dynamic Page While Registering ");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .last_payment_amount_page(userProfile)
			    .thankYouRegistered();
			    new LoginAction()
			    .BgbloginDetails(userProfile);
			    new BgbRegistrationAction()
			    .verifyAccountOverviewForStandardUser()
			    .verifyAuditEntryForEmail(userProfile)
			    .registerAuditValidataion(userProfile);
				
			}	
			 
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifydynamicpagewhileRegister_last_payment_amount() throws Exception {
				Report.createTestLogHeader("Registration", "Verify Dynamic Page While Registering ");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");
				
				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
			    .registerForAnOnlineAccount(userProfile)
			    .credit_card_no_last_four_digits(userProfile)
			    .thankYouRegistered();
			    new LoginAction()
			    .BgbloginDetails(userProfile);
			    new BgbRegistrationAction()
			    .verifyAccountOverviewForStandardUser()
			    .verifyAuditEntryForEmail(userProfile)
			    .registerAuditValidataion(userProfile);
				
			}		
			
			@SuppressWarnings("unchecked")
			@Test(groups ={Slingshot,Regression,BGBRegistration})	
			public void verifySuperUserRegisterBillnumber() throws Exception {
				Report.createTestLogHeader("Registration", "verifySuperUserSucessfulRegister");
				UserProfile userProfile = new TestDataHelper().getUserProfile("BgbRegistration1");

				new HomePageAction()
				.clickRegistration("Header Registration Link");
				new BgbRegistrationAction()
				.navigateToAccountDetailsPage()
				.verifyAccountDetailsPage(userProfile)
				.registerForAnOnlineAccountNew(userProfile)
				.RegistrationActivationEmail()
				.Activate_EmailUrl(userProfile)
				.thankYouRegistered();
				new LoginAction()
				.BgbloginUserNew(userProfile);
				new BgbRegistrationAction()
				.verifyAccountOverviewForSuperUserNew()
				.verifyAuditEntryForEmailNew(userProfile);
				//.registerAuditValidataionNew(userProfile);
				}
}

