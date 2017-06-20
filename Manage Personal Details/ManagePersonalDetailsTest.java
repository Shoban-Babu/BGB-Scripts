package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;
import static bg.framework.app.functional.entities.FunctionalCategory.ManagePersonalDetails;
import org.testng.annotations.Test;

import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.action.Slingshot.AccountSummaryAction;
import bg.framework.app.functional.action.Slingshot.ManagePersonalDetailsAction;
import bg.framework.app.functional.action.Slingshot.SapCrmAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.RobotSendKeys;
import bg.framework.app.functional.util.TestDataHelper;

public class ManagePersonalDetailsTest extends TestBase {

	UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
	CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetails");   

	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//TC_MPD_002 : To verify the content and layout of "Update your details" page
	public void validateMPDPageFields(){		
		Report.createTestLogHeader("Manage Personal Details", "Validates fields in Manage Personal Details page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateMPDPageFields();		
	}

	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//	TC_MPD_003: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validateEmailFieldError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the Email address field in MPD page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateEmailFieldError();		 
	}

	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void MPDJourneyEndtoEnd(){
		Report.createTestLogHeader("Manage Personal Details", "zFill valid data and verifies thank you page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillValidDataInMPDPage(userProfile)
		.ClickSaveChangesButton()
		.verifyEmailChangeDataWithDB(userProfile)
		.verifyPasswordChangeDataWithDB(userProfile)
		.verifyThankYouPage()
		.verifyAuditDetails(userProfile);			 			 		 	 

		/* new SapCrmAction()
		 .loginDetails(crmuserProfile)
		 .searchByAccountId(crmuserProfile, userProfile);*/

		new ManagePersonalDetailsAction()
		.clickLoginAndVerifyLoginPage()
		.loginWithMPDChangeData(userProfile,0)
		.verifyAccountDetails(userProfile)
		.resetFields(userProfile);
	}

	//TC_MPD_007 : To validate whether password  entered during MPD journey is case sensitive next login	 
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void validatePasswordForCaseSensitive(){
		Report.createTestLogHeader("Manage Personal Details", "Validate whether password  entered during MPD journey is case sensitive next login");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillValidDataInMPDPage(userProfile)
		.ClickSaveChangesButton()
		.verifyAuditDetails(userProfile)
		.verifyPasswordChangeDataWithDB(userProfile)		 
		.verifyThankYouPage()
		.clickLoginAndVerifyLoginPage()
		.loginWithMPDChangeData(userProfile,1)	
		.loginWithMPDChangeData(userProfile,0)
		.verifyAuditDetails(userProfile)	
		.resetFields(userProfile)
		;

	}

	//TC_MPD_010 : Checks with the 'your name and address' field in MPD journey  
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void validateYourNameAndAddress(){
		Report.createTestLogHeader("Manage Personal Details", "Checks with the your name and address field in MPD journey");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.checkNameDetails(userProfile)	 
		;			 
	}

	//TC_MPD_015, TC_MPD_024: Verify whether customer is able to change email address alone through MPD journey on clicking yes in confirmation overlay.
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void verifyThankyoupageForEmailChangeInMPD(){
		Report.createTestLogHeader("Manage Personal Details", "Verifies whether customer is able to change email address alone through MPD journey");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillNewEmailField(userProfile)
		.ClickSaveChangesButton()
		.verifyAuditDetails(userProfile)
		.verifyThankYouPage() //Overlay page should be updated once its available
		.clickLoginAndVerifyLoginPage()
		.loginWithUpdatedEmailAddress(userProfile)	
		.verifyAuditDetails(userProfile)
		.resetFields(userProfile)
		;
	}

	//	TC_MPD_016: Verify whether customer is able to change password  alone through MPD journey and thank you page.
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void verifyPasswordChangeInMPD(){
		Report.createTestLogHeader("Manage Personal Details", "Verifies whether customer is able to change password  alone through MPD journey and thank you page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillNewPasswordField(userProfile)
		.ClickSaveChangesButton()
		.verifyAuditDetails(userProfile)
		.verifyThankYouPage()
		.validateGotoMyAccountLink(userProfile)		 	 
		.resetPasswordFields(userProfile);			 
	}

	//TC_MPD_017: Verify whether customer is able to change contact numbers alone through MPD journey and thank you page
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void verifyThankyoupageForMobileNumberChangeInMPD(){
		Report.createTestLogHeader("Manage Personal Details", "Verifies whether customer is able to change contact numbers alone through MPD journey and thank you page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillNewMobileNumberField(userProfile)
		.ClickSaveChangesButton()		 
		.verifyMobileNumberChangeDataWithSAPCRM(userProfile)
		.verifyAuditDetails(userProfile)
		.verifyThankYouPage()//Overlay not available
		.verifyMPDUpdatePageAndMakeAnotherChange(userProfile)		 
		.resetPasswordFields(userProfile);			 		
	}

	//TC_MPD_018 : Verify that customer is able to change both email address and password through MPD journey and thank you page 
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void verifyThankyoupageForBothEmailAndPasswordChangeInMPD(){
		Report.createTestLogHeader("Manage Personal Details", "Verifies that customer is able to change both email address and password through MPD journey and thank you page");
		new LoginAction()
		.navigateToBgbLogin() 
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.fillEmailAndPasswordFieldAlone(userProfile)
		.ClickSaveChangesButton()
		.verifyThankYouPage()
		.clickLoginAndVerifyLoginPage()
		.loginWithMPDChangeData(userProfile,0)			 
		.verifyAuditDetails(userProfile)
		.resetFields(userProfile)
		;		 
	}
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//TC_MPD_004: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validateRetypeEmailFieldError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the Retype Email address field in MPD page");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateRetypeEmailFieldError();		 
	}

	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//TC_MPD_005: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validatePasswordFieldError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the New Password field in MPD page");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateNewPasswordError();		 
	}
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//TC_MPD_006: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validateRetypeNewPasswordFieldError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the retype new password field in MPD page");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateERetypeNewPasswordError();		 
	}
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//	TC_MPD_009: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validateMobileNumberFieldError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the Mobile number field in MPD page");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validateMobileNumberFieldError();		 
	}
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	//TC_MPD_020: To validate the 'Email address,Re-type Email address,New Password,Re-type New Password' field in MPD page
	public void validatePassowrdMismatchError(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the password mismatch error in MPD page");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickMPDLink(userProfile)
		.validatePasswordMismatchError();		 
	}
	//TC_MPD_022, TC_MPD_023 : To check whether Meter read reminders options can be chosen in SMS alerts section and bill notifications options can be choosen in Email alerts section
	// @Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	 public void checkPersonalizeSection(){
		 Report.createTestLogHeader("Manage Personal Details", "Checks the personalize section for meter read and bill notification selection");
		 new LoginAction()
		 .BgbloginDetails(userProfile)
		 .verifyAccountDetails(userProfile)
		 .clickMPDLink(userProfile)	
		 .fillValidDataInMPDPage(userProfile)	 
		 .checkPersonalizeSection(userProfile)
		 .ClickSaveChangesButton()
		 .verifyThankYouPage()		 
		 .verifyAuditDetails(userProfile);
		 new AccountSummaryAction().resetFields(userProfile);
 }
	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})	 
	public void validateAreYourDetailsUptoDtaeLink(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the Are your deatils upto date link");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.clickAreYourDetailsUptoLink();		 
	} 

	@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
	public void validateUpdateMyPersonalDetailsLink(){		 
		Report.createTestLogHeader("Manage Personal Details", "Validates the update your details ");
		new LoginAction()
		.BgbloginDetails(userProfile)
		.verifyAccountDetails(userProfile)
		.verifyUpdateMyDetailsLink();		 
	} 
	//TC_MPD 21: Verify whether proper error message is displayed when mobile number is less than 10 digits(Expected: should start with 0 Min 10 digits Max 11digits) : 'Please check the format of your mobile number' 	
		@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
		public void verifyMobileNo(){
			Report.createTestLogHeader("Manage Personal Details", "Validates Mobile number format ");
			UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
			new LoginAction()
			.BgbloginDetails(userProfile);
			new AccountSummaryAction()
			.verifyAccountDetails(userProfile)
			.clickAreYourDetailsUptoLink();
			new ManagePersonalDetailsAction()
			.validateMobileNumberFieldError();
		}
		//TC_MPD 04: Verify whether session is logged out when email address is updated.	
		@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
		public void updateEmailAndVerifyLogout(){
			Report.createTestLogHeader("Manage Personal Details", "Verifies whether customer is able to change email address and after saving changes whether it logged out or not ");
			UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
			new LoginAction()
			.BgbloginDetails(userProfile);
			new AccountSummaryAction()		
			.clickAreYourDetailsUptoLink();
			new ManagePersonalDetailsAction()
			.fillNewEmail(userProfile)
			.ClickSaveChangesButton()
			.updateEmailAndVerifyLogout();
			new ManagePersonalDetailsAction()
			.clickLoginAndVerifyLoginPage()
			.loginWithNewData(userProfile,0)
			.verifyAccountDetails(userProfile)
			.resetFields(userProfile);
		}

//*************************************************** RP3 *****************************************************************************
//*********************************************************************************************************************************************************
//TC_MPD_08  Verify whether customer is able to edit email address using MPD without alert options
				@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
				public void verifyReminderUpdateInSAPCRM(){
					Report.createTestLogHeader("Manage Personal Details", "Verify the updates done to Remainder alerts  in MPD page are sucessfully reflected in SAP CRM ");
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
					new LoginAction()
					.BgbloginDetails(userProfile);
					new AccountSummaryAction()		
					.clickAreYourDetailsUptoLink();
					new ManagePersonalDetailsAction()
					.fillNewEmail(userProfile)
					//.ClickSaveChangesButton()
					.updateEmailAndVerifyLogout();				
					new ManagePersonalDetailsAction()
					.clickLoginAndVerifyLoginPage()
					.loginWithNewData(userProfile,0)
					.verifyAccountDetails(userProfile)
					.resetFields(userProfile);
				}

//TC_MPD_02(E to E):  Verify the updates done to Remainder alerts " in MPD page are sucessfully reflected in SAP CRM. 
//TC_MPD 07: Verify whether customer is able to edit email address using MPD with alert options and verify 
 
				@Test(groups ={Slingshot,Regression,ManagePersonalDetails})
				public void verifyReminderUpdateandalertInSAPCRM(){
					Report.createTestLogHeader("Manage Personal Details", "Verify the updates done to Remainder alerts  in MPD page are sucessfully reflected in SAP CRM");
					UserProfile userProfile = new TestDataHelper().getUserProfile("BGBLessthanFiveAccounts");
					new LoginAction()
					.BgbloginDetails(userProfile);
					new AccountSummaryAction()		
					.clickAreYourDetailsUptoLink();
					new ManagePersonalDetailsAction()
					.fillNewEmail(userProfile)
				 	//.VerifySAPCRM_MPDVerification(userProfile)
					.clickLoginAndVerifyLoginPage()
					.loginWithMPDChangeData_new(userProfile)
					.verifyAccountDetails(userProfile)
					.resetFields(userProfile);
				}
				

}
