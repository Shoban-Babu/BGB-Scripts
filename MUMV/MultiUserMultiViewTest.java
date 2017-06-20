
/**
 * 
 */
package bg.framework.app.functional.test.Slingshot;

import static bg.framework.app.functional.entities.FunctionalCategory.Regression;
import static bg.framework.app.functional.entities.FunctionalCategory.Slingshot;

import org.testng.annotations.Test;

import bg.framework.app.functional.action.Slingshot.BgbRegistrationAction;
import bg.framework.app.functional.action.Slingshot.MultiUserMultiViewAction;
import bg.framework.app.functional.action.Slingshot.PaperlessBillingAction;
import bg.framework.app.functional.action.Slingshot.PreconditionAction;
import bg.framework.app.functional.action.Slingshot.SapCrmAction;
import bg.framework.app.functional.action.Slingshot.StatementOfAccountAction;
import bg.framework.app.functional.action.common.LoginAction;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.entities.SMRAccountDetails;
import bg.framework.app.functional.entities.UserProfile;
import bg.framework.app.functional.test.common.TestBase;
import bg.framework.app.functional.util.Report;
import bg.framework.app.functional.util.TestDataHelper;
import bg.framework.app.functional.entities.CrmUserProfile;
import bg.framework.app.functional.page.Slingshot.SapCrmPage;
import bg.framework.app.functional.action.common.HomePageAction;

/**
 * @author 255501
 *
 */
public class MultiUserMultiViewTest extends TestBase {

	// ********************************************* MuMV -Login&access manage users Journey **************************************************************


	// TS_MUMV_001 Check whether the user can login to the application
	// TS_MUMV_002 Check whether "Manage user" link is getting displayed in RHN panel for Superusers of  less than 15 accounts	
	// TS_MUMV_007 Verify the link navigations of "Welcome to manage users" Splash screen
	// TS_MUMV_008 Check whether the details of users are getting displayed in the Expanded state of "More about use status"
	// TS_MUMV_009 Check whether the "Manage user" landing page is getting displayed when the user closes the Splash screen 
	// TS_MUMV_004 Check whether the "Manage user" link is getting navigated to its respective page with "Welcome to manage users" Splash screen  less than 15 accounts
	//	a)First login of superuser
	//  b)Second time login of superuser	
	@Test(groups ={Slingshot,Regression})
	public void VerifySplashScreenforlessthan15Accts()  {
		Report.createTestLogHeader("MuMv", "Verify the Splash Overlay Screen and their Content"); 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVlessthanfifteenaccts"); 	      
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUser()
		.VerifyWelcomeToManageUserOverlay();     	
	} 	

	//TS_MUMV_003 Check whether "Manage user" link is getting displayed in RHN panel for Superusers of more than 15 accounts
	//TS_MUMV_005 Check whether the "Manage user" link is getting navigated to its respective page with "Welcome to manage users" Splash screen more than 15 accounts 	
	@Test(groups ={Slingshot,Regression})
	public void VerifySplashScreenforMorethan15Accts()  {
		Report.createTestLogHeader("MuMv", "Verify whether Manage user link is getting displayed in RHN panel for Superusers of more than 15 accounts");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVmorethan15accts");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUser()
		.VerifyWelcomeToManageUserOverlay();	 	       

	}

	// TS_MUMV_011 Verify the link navigations of "Manage user" landing page 	
	// TS_MUMV_012  Verify the functionality of of view details and back to summary 
	// TS_MUMV_016 Validate whether the overlay is getting displayed while clicking "What's this?" link of Account summary panel
	@Test(groups ={Slingshot,Regression})
	public void VerifyManageUserLinkNavigation()  {
		Report.createTestLogHeader("MuMv", "Verify MumV Navigation Links Manage user page");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvAddnewStdUser");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.verifyWhatisthisOverlay()
		.ManageUserNavigationVerificationLinks(); 	 	       	
	} 	 	

	// TS_MUMV_018 Check whether the user can edit details by clicking "View details" of Active accounts

	@Test(groups ={Slingshot,Regression})
	public void VerifyManageUserViewdetails()  {
		Report.createTestLogHeader("MuMv", "Check whether the user can edit details by clicking View details of Active accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");

		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewStduser(userProfile)
		.ClickEditdetails()
		.ChangeStandardUserview(userProfile); 	     	 	     		
	}		

	// TS_MUMV_015 Check whether the number of users in summary and user list are similar		
	@Test(groups ={Slingshot,Regression})
	public void verifyUsertable()  {
		Report.createTestLogHeader("MuMv", "Verify AddnewView Button Overlay"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.Verifyuserlistindb(userProfile);
	}

	// ********************************************* MuMV Add New User Journey **************************************************************                      


	// TS_MUMV_019 Check whether "Add new user" landing page is getting displayed when the user clicks "Add new users" link in Manage account page
	// TS_MUMV_021 Verify the link navigations of "Add new user" landing page
	// TS_MUMV_022 	Check whether overlay is getting displayed when the user clicks "What's this?" link of  "Add new user" page 
	// TS_MUMV_023 	Verify the link navigations of  "What's this?" overlay	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyAddNewUserLandingPage()  {
		Report.createTestLogHeader("MuMv", "Verify the Add new User Landing page and their Overlay");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.AddUsersWhatisThisverlay()
		.AddNewUserNavigationVerification(); //have to update navigation link
	}
	
	// TS_MUMV_024 Check whether "You are creating a super user" overlay is getting displayed when the user clicks "Yes" radio button
	// TS_MUMV_022 	Check whether overlay is getting displayed when the user clicks "What's this?" link of  "Add new user" page 
	// TS_MUMV_023 	Verify the link navigations of  "What's this?" overlay	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyNewUserDetailsactivation()  {
		Report.createTestLogHeader("MuMv", "Verify the Super User Overlay Yes Button Selection");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes(); 	 	 	  	       
	}
	

	//TS_MUMV_049 Verify the confirmation page of Add new user is getting displayed with prepopulated user details 	  	 	  	 		 	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyStdSpecificacctsPrepopulatedUserdetails()  {
		Report.createTestLogHeader("MuMv", "Verify the confirmation page of Add new user is getting displayed with prepopulated user details");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifySpecificAccountsViewNames(userProfile);	  	 		  	       		 	  	       	 		  	       		  	 		  	       		
	}

	// TS_MUMV_037	Check whether the user can successfully enter details in "Add new user" page and registers it 
	// TS_MUMV_038  Verify the confirmation page of Add new user is getting displayed with prepopulated user details	  	 		 	 	
	@Test(groups ={Slingshot,Regression})
	public void allAcctsStdUserCreation()  {
		for(int i=1;i<=195;i++){
		Report.createTestLogHeader("MuMv", "Verify whether the user can successfully enter details in Add new user"+i+" page and registers it"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation();
		new BgbRegistrationAction()
		.AddNewStdUser(userProfile);
		new MultiUserMultiViewAction()
		//.verifyAuditTable(userProfile)
		.AdduserConfirmationPage();
		//.confirmationPageVerificationLinks();
		}
	} 	 	  	 	 	 	 	 	 	       	

	//TS_MUMV_028  Check whether the user can successfully enter details in "Add new user" page and registers it 
	//TS_MUMV_081  Check whether the registration confirmation emails are triggered to Super user
	//TS_MUMV_080  Verify the link navigations of confirmation pages of Super user
	// TS_MUMV_029 Verify the confirmation page of Add new user is getting displayed with prepopulated user details
	//TS_MUMV_066  Verify the confirmation email to use OAM is triggered to user 
	//TC_MUMV_089 Verify whether details displayed in view user landing page is as per SAP CRM.

	@Test(groups ={Slingshot,Regression})
	public void allAcctsSuperUserCreation()  {
		 Report.createTestLogHeader("MuMv", "Verify the Super user creation and their Audit entry in DB"); 	 	   	   	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecifics");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsSuperuser");   
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes();
		new BgbRegistrationAction()
		.AddNewSuperUser(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile)
		.AdduserConfirmationPage()
	    .confirmationPageVerificationLinks();
		/*.UserJourney_SuperUserAccessVerification(userProfile,smrProfile);
		new SapCrmAction()
		.loginDetails(crmuserProfile)
		.searchCrmFieldsVerification(crmuserProfile, userProfile);
*/
	}

	//TS_MUMV_027 Check whether the error message is getting displayed when the user tries to add sixth super user	 	 	 	
	@Test(groups ={Slingshot,Regression})
	public void ValidateMaximumSuperUserExceeded()  {	
		Report.createTestLogHeader("MuMv", "Check the error message is getting displayed when the user tries to add sixth super user"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVmorethan5superuser");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes() 
		.SuperUserMaximumExceedErrorValidation(); 

	}

	// TS_MUMV_026 Verify the functionalities of cofirm and cancel	 	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyAddNewuserCancel()  {
		Report.createTestLogHeader("MuMv", "Verify the functionalities of cofirm and cancel"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.SuperUserCreation()
		.SuperUserOverlayNo();	       

	}

	// TS_MUMV_040 	Verify the link navigations of confirmation page  	
	// TS_MUMV_033 Check whether account view list of Add new user page is getting displayed when the user clicks  "No" radio button of Add new user page
	// TS_MUMV_036 Check whether the user can select All accounts from view list
	@Test(groups ={Slingshot,Regression})
	public void VerifyStandardUserallaccountsConfirmationPageNavigation()  {
		Report.createTestLogHeader("MuMv", "Verify the StandardUser All accounts Navigationlinks for the confirmation Page");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifyAllAccountsViewName(userProfile)
		.EnterValid_StandardUserdata(userProfile)     	 	       		
		.AddNewUserNavigationVerification() //have to update navigation link
		.UserConfirmationPageNavigations();	  	       		
	}
	// TS_MUMV_041	Check whether account view list of Add new user page is getting displayed when the user clicks  "No" radio button of Add new user page 	 	
	// TS_MUMV_051  Verify the link navigations of confirmation page 
	// TS_MUMV_053  Check whether view name field is prepopulated as respective normal users	 	 	

	@Test(groups ={Slingshot,Regression})
	public void VerifyStandardUserspecificaccountsConfirmationPageNavigation()  {
		Report.createTestLogHeader("MuMv", "Verify the Superuser Specific accounts Navigationlinks for the confirmation Page");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifySpecificAccountsViewName(userProfile)	  	       		  	       
		.AddNewUserNavigationVerification() 
		.UserConfirmationPageNavigations();	  	       		
	}


	// TS_MUMV_053  Check whether view name field is prepopulated as respective normal users
	// TS_MUMV_045  Verify whether 'Confirm' and 'cancel' links in 'Add a new view' overlay are navigated to the respective pages.	 	 	

	@Test(groups ={Slingshot,Regression})
	public void ValidatetheAddNewViewOverlay()  {
		Report.createTestLogHeader("MuMv", "Verify whether 'Confirm' and 'cancel' links in 'Add a new view' overlay are navigated to the respective pages");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.ValidateaddnewviewOverlay();	
	}
	// TS_MUMV_031 Verify the link navigations of confirmation page 	
	// TS_MUMV_052 Check whether Enter details fields are getting displayed in Add new user page	
	// TS_MUMV_053 Check whether view name field is prepopulated as All accounts (for super user)	 	 	
	// TS_MUMV_054 Check whether User type field is prepopulated as Super user (for super user) 	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifySuperUserallaccountsConfirmationPageNavigation()  {
		Report.createTestLogHeader("MuMv", "Verify whether view name field is prepopulated as All accounts for super user");  	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes()
		.EnterValid_SuperUserdata(userProfile)	
		.AddNewUserNavigationVerification() 
		.UserConfirmationPageNavigations();  	 

	}

	// TS_MUMV_032 Check whether error message is getting displayed when the user tries to add 21st Standard user by clicking "No" radio button of Add new user page 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyStduserMaximumuserLimit()  {
		Report.createTestLogHeader("MuMv", "Verify hether error message is getting displayed when the user tries to add 21st Standard user by clicking No radio button of Add new user page "); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvMaxStduserValidation");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreations()
		.StandardMaximumExceedErrorValidation();	  	        	
	} 			  

	// TS_MUMV_043 Verify the link navigations of account view list of Add new user page 
	// TS_MUMV_035 Verify the link navigations of account view list of Add new user page  Standard user_All accounts
	@Test(groups ={Slingshot,Regression})
	public void VerifyStdusespecificacctsbackandcancel()  {
		Report.createTestLogHeader("MuMv", "Verify the link navigations of account view list of Add new user page "); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()					 	 	 						 	 	  	
		.BackandCancelNavigation()
		.AddNewUserNavigationVerifications();
	}	
	// TS_MUMV_044  Check whether "Add new view" overlay is getting  displayed when there is no views 
	@Test(groups ={Slingshot,Regression})
	public void VerifyAddnewViewOverlay()  {
		Report.createTestLogHeader("MuMv", "Verify whether Add new view overlay is getting  displayed when there is no views "); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.AddnewViewOverlay();

	}
	// TS_MUMV_047 Check whether the Account list overlay of that particular view is displayed 
	@Test(groups ={Slingshot,Regression})
	public void VerifyAcctViewOverlay()  {
		Report.createTestLogHeader("MuMv", "Verify whether the Account list overlay of that particular view is displayed "); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifyViewNameAcctOverlay(userProfile);

	}

	//TS_MUMV_048 Verify the functionalities of Close link and Previous and next (if more than 10 accounts) in that overlay 	  	 	  	 		 	 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyNoofAcctsinViewname()  {
		Report.createTestLogHeader("MuMv", "Verify the functionalities of Close link and Previous and next if more than 10 accounts in that overlay ");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()
		.ViewAcctsOverlay(userProfile);
	}

	// ********************************************* MUMV - Registration journey **************************************************************

	// TS_MUMV_055 Verify the "Title" Field validations and its appropriate error message						
	@Test(groups ={Slingshot,Regression})
	public void verifyTitleField()  {
		Report.createTestLogHeader("MuMv", "Verify the Title Field validations and its appropriate error message"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation()
		.ValidateAddnewUserTitleField(userProfile);				  	       						
	}
	// TS_MUMV_059 Verify the "Email address" Field validations and its appropriate error message				
	@Test(groups ={Slingshot,Regression})
	public void ValidateAdduserEmailidField()  {
		Report.createTestLogHeader("MuMv", "Verify the Email address Field validations and its appropriate error message"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation()
		.validateEmailidField(userProfile);				  	       						
	}
	// TS_MUMV_061 Check whether error message is getting displayed when the user doesnot marks "Terms and conditions" check box			
	@Test(groups ={Slingshot,Regression})
	public void ValidateCheckboxField()  {
		Report.createTestLogHeader("MuMv", "Verify the whether error message is getting displayed when the user doesnot marks Terms and conditions check box "); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation()
		.validateCheckboxTermsAndCnds(userProfile);				  	       						
	}
	//TS_MUMV_064  Check whether "what's this?" overlay of usertype is getting displayed while clicking it						
	@Test(groups ={Slingshot,Regression})
	public void VerifyUserType_WhatsthisOverlay()  {
		Report.createTestLogHeader("MuMv", "Verify user is able to create Standard user and verify the audit details");					  	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes()
		.AddUsersWhatisThisverlay();				  	       	

	}
	// TS_MUMV_078	Check whether confirmation page is getting displayed for Reads & Bills user
	// TS_MUMV_080  Verify the link navigations of confirmation pages of Reads & Bills user
	// TS_MUMV_081  Check whether the registration confirmation emails are triggered to	Normal users
	// TS_MUMV_072  Check whether the User name  field is prefilled with new user email address.						
	// TS_MUMV_074  Verify the functionality of "Continue" button
	// TS_MUMV_046  Check whether the user can select Specific view from view list
	// TS_MUMV_069	Check whether the "Set up your password" page  is getting displayed while clicking "Set up password" link in email					
	// TS_MUMV_071  Verify the link navigations of "Set up your password" page 						
	@Test(groups ={Slingshot,Regression})
	public void VerifyStandardUserSpecificAccounts_RB()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads & Bills users");					  	   	  			  	       
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRB"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvTestdataforRB");

		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifySpecificAccountsViewName(userProfile);					  	       					  	       	
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile)
		.AdduserConfirmationPage();
		/*.confirmationPageVerificationLinks()
		.UserJourney_RBAccessVerification(userProfile);*/
		/*new SapCrmAction()
		.loginDetails(crmuserProfile)
		.searchCrmFieldsVerification(crmuserProfile, userProfile);*/					  	        	      

	}

	//TS_MUMV_078  Check whether confirmation page is getting displayed for	 Reads, Bills, Payments user
	//TS_MUMV_080  Verify the link navigations of confirmation pages of	 Reads, Bills, Payments user				 	
	@Test(groups ={Slingshot,Regression})
	public void VerifyStandardUserSpecificAccounts_RBP()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads, Bills, Payments users");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation()
		.addNewStandardUser(userProfile);
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTabledetails(userProfile)
		.AdduserConfirmationPage();
	
	}
	//TS_MUMV_078  Check whether confirmation page is getting displayed for	Full access user
	//TS_MUMV_080  Verify the link navigations of confirmation pages of	 Full access user				  	      
	@Test(groups ={Slingshot,Regression})
	public void VerifyStandardUserSpecificAccounts_FA()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Full access user");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsFA"); 
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUser_Creation()
		.VerifySpecificAccountsViewName(userProfile);					  	       					  	       	
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTabledetails(userProfile)
		.AdduserConfirmationPage()
		.confirmationPageVerificationLinks();
		/*.UserJourney_FA_AccessVerification(userProfile,smrProfile)
		   new SapCrmAction()
			.loginDetails(crmuserProfile)
			.searchCrmFieldsVerification(crmuserProfile, userProfile);     	*/	
	}
	// TS_MUMV_062 Verify the Functionalities of editdetails,submit,cancel,back		
	// TS_MUMV_063 Check whether the user can select user type from drop down for normal users						  	      
	@Test(groups ={Slingshot,Regression})
	public void ValidateBackcancelAndUserType()  {
		Report.createTestLogHeader("MuMv", "Verify the Functionalities of editdetails,submit,cancel,back"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatabackcancel");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTable(userProfile)
		.verifyAndValidateUserTypeFieldContent(userProfile)
		.Edituserdetailscancel();				  	       				  	       						
	}

	//****************************************************************** MUMV - user&view journey **************************************************************
	//***********************************************************************************************************************************************************

	//TC_MUMV_171  Verify whether user is able to navigate to Manage Views landing page 
	//TC_MUMV_174  Verify whether On clicking Add new view button , add a new view page is displayed
	//TC_MUMV_180  Verify whether user is able to create a new view successfully and it gets reflected in the list
	//TC_MUMV_181  Verify whether the user is able to add the New view created.

	@Test(groups ={Slingshot,Regression})
	public void VerifyMuMvManageView()  {
		Report.createTestLogHeader("MuMv", "Verify whether user is able to create a new view successfully and it gets reflected in the list");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatass");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()	     					  	       				  	       		
		.Addviewname(userProfile)
		.verifyLeadTabledata_AddnewViewAudit(userProfile)
		.AddnewUserslink()
		.AddUserRadioButton()
		.StandardUser_Creation()						  	       		
		.verifynewview(userProfile)
		.VerifyenterValidData_StandardUserdetails(userProfile);
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile);				  	       		

	}
	//TC_MUMV_181a  a)Verify whether super user can assign the new view created to standard user.
	//  b)Verify whether that standard user have access to the new view.

	@Test(groups ={Slingshot,Regression})
	public void VerifyMuMvManageViewStandarduser()  {
		Report.createTestLogHeader("MuMv", "Verify whether super user can assign the new view created to standard user");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("MeterReadCollectiveAndElec");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTablestdUser(userProfile)
		.viewtableHandle(userProfile,smrProfile);	  						  	       	

	}
	//TC_MUMV_193 Verify whether proper error message is diplayed when accounts added more than 50 on clicking the confirm button

	@Test(groups ={Slingshot,Regression})
	public void VerifyfiftyAcctsviewValidatation()  {
		Report.createTestLogHeader("MuMv", "Verify whether proper error message is diplayed when accounts added more than 50 on clicking the confirm button");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()
		.FiftyAcctsViewnameErrorValidation(userProfile);  	  						  	     			  	         	  						  	       		
	}

	//TC_MUMV_173  Verify the Functionalities of links present in Manage views page
	//TC_MUMV_185  Verify the Functionalities of links present in Manage views page
	//TC_MUMV_199  Verify the Functionalities of links present in Manage views page
	//TC_MUMV_211  Verify the Functionalities of links present in Manage views page

	@Test(groups ={Slingshot,Regression})
	public void VerifyManageviewLinks()  {
		Report.createTestLogHeader("MuMv", "Verify the Functionalities of links present in Manage views page");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()	     					  	       				  	       		
		.ManageviewNavigationLinks();			  	       		

	}	
	//TC_MUMV_177  Verify whether proper error message is displayed when continuing with out enabling the accounts check box

	@Test(groups ={Slingshot,Regression})
	public void ValidateAcctCheckbox()  {
		Report.createTestLogHeader("MuMv", "Verify whether proper error message is displayed when continuing with out enabling the accounts check box");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()	     					  	       				  	       		
		.ValidateCheckboxerror(userProfile);	 	       		
	}	

	//TC_MUMV_183  Verify whether user is able to navigate to Manage Views landing page 
	//TC_MUMV_186  Verify whether On clicking Add new view button , add a new view page is displayed					  	      
	//TC_MUMV_192  Verify whether new search section is displayed once the previous search is completed
	//TC_MUMV_194  Verify whether user is able to create a new view successfully and it gets reflected in the list
	//TC_MUMV_195  Verify whether the user is able to add the New view created 

	@Test(groups ={Slingshot,Regression})
	public void AddnewViewMorethan15Accts()  {
		Report.createTestLogHeader("MuMv", "Verify whether new search section is displayed once the previous search is completed and new view created Successfully");

		UserProfile userProfile = new TestDataHelper().getUserProfile("AddnewViewMorethan15Acctsmanage");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()     					  	       				  	       		
		.morethan15accts(userProfile)
		.verifyLeadTabledata_AddnewViewAudit(userProfile)
		.AddnewUserslink()
		.StandardUser_Creation()	
		.verifynewview(userProfile)
		.VerifyenterValidData_StandardUserdetails(userProfile);
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile);	}


	@Test(groups ={Slingshot,Regression})
	public void test1()  {
		Report.createTestLogHeader("MuMv", "Verify the MuMv Manage view");						  	   	  
		//   UserProfile userProfile = new TestDataHelper().getUserProfile("AddnewViewMorethan15Accts1");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRB");  
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		new SapCrmAction()
		.loginDetailsforpaperbilling(crmuserProfile)
		.searchCrmFieldsVerification(crmuserProfile, userProfile);



	}
	//TC_MUMV_197  Verify whether user is able to navigate to Manage Views landing page
	//TC_MUMV_200  Verify whether view details landing page is displayed on clicking any Views 'View details' link
	//TC_MUMV_202  Verify whether Edit view details page is displayed on clicking the Edit button with default enable of accounts associated to it
	//TC_MUMV_203  Verify whether user is able to selected all the accounts through enabling the Select all check box
	//TC_MUMV_206  Verify whether user is able to edit the existing view successfully and it gets reflected in the list		  	     
	//TC_MUMV_207  Verify whether the user is able to add the  view edited
	@Test(groups ={Slingshot,Regression})
	public void EditViewLessthan15Accts()  {
		Report.createTestLogHeader("MuMv","Verify whether user is able to edit the existing view successfully and it gets reflected in the list");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvTestdataforFAA");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()
		.VerifyEditviewname(userProfile)  						  	       		
		.EditViewNameforLessthan15Accts(userProfile)
		.ClickAddUserRHNLink()
		.StandardUser_Creation()						  	       		
		.verifyEditedview(userProfile)
		.enterValidData_StandardUserforEditview(userProfile);
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile);	
	}

	//TC_MUMV_205  Verify whether proper error message is displayed when continuing with out enabling the accounts check box 
	@Test(groups ={Slingshot,Regression})
	public void EditViewLessthan15AcctsErrorValidation()  {
		Report.createTestLogHeader("MuMv", "Verify whether proper error message is displayed in view when continuing with out enabling the accounts check box");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
			.clicktestacct();
		/*.ClickManageUserLink()
		.ManageViews()
		.VerifyEditviewname(userProfile)
		.EditViewNameErrorValidation(userProfile);  	  						  	       		
	}*/
	}
	//TC_MUMV_209  Verify whether user is able to navigate to Manage Views landing page 
	//TC_MUMV_211  Verify whether On clicking Add new view button , add a new view page is displayed
	//TC_MUMV_212  Verify whether view details landing page is displayed on clicking any Views 'View details' link
	//TC_MUMV_214  Verify whether user is able to remove the already existing accounts in the view to be edited
	//TC_MUMV_222  Verify whether user is able to edit the existing view successfully and it gets reflected in the list
	//TC_MUMV_223  Verify whether the user is able to add the  view edited 	  					  	      

	@Test(groups ={Slingshot,Regression})
	public void EditViewgreaterthan15Accts()  {
		Report.createTestLogHeader("MuMv", "Verify whether the user is able to add the view edited  in the list");

		UserProfile userProfile = new TestDataHelper().getUserProfile("AddnewViewMorethan15Acctsmanage");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()
		.ValidateRemoveviewname(userProfile)
		.ClickAddNewUserLink()
		.StandardUser_Creation()
		.EditViewAdduserValidation(userProfile)
		.VerifyenterValidData_StandardUsers(userProfile);
		new BgbRegistrationAction()
		.AddNewStdUserdata(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile);		
	}

	//TC_MUMV_220 Verify whether new search section is displayed once the previous search is completed 

	@Test(groups ={Slingshot,Regression})
	public void EditViewgreaterthan15AcctsNewSearch()  {
		Report.createTestLogHeader("MuMv", "Verify whether new search section is displayed once the previous search is completed ");

		UserProfile userProfile = new TestDataHelper().getUserProfile("AddnewViewMorethan15Acctsmanage");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()
		.ediviewValitationSearchAccts(userProfile)
		.EditAcctsTable(userProfile);  	  						  	       		
	}

	//TC_MUMV_225 Verify whether the user is able to navigate to View details landing page 
	//TC_MUMV_226 Verify whether proper error message is displayed on clicking the delete view link when the View is already assigned for a user
	@Test(groups ={Slingshot,Regression})
	public void ManageViewDeleteViewErroValidation()  {
		Report.createTestLogHeader("MuMv", "Verify the proper error message is displayed on clicking the delete view link when the View is already assigned for a user");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvTestdataforFAA");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()  	  	  						  	       		
		.DeleteViewOverlay(userProfile);   	  	  						  	       		
	}
	//TC_MUMV_227 Verify whether user is able to delete a view 	  	  					  	      
	@Test(groups ={Slingshot,Regression})
	public void ManageViewDeleteView()  {
		Report.createTestLogHeader("MuMv", "Verify whether user is able to delete a view");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvTestdataforFAA");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ManageViews()	     					  	       				  	       		
		.Addviewname(userProfile)
		.verifyLeadTabledata_AddnewViewAudit(userProfile)
		.backtoMangeruserlink()
		.ManageViews()
		.ViewDeletion(userProfile)
		.verifyLeadTable_ViewDeletionAudit(userProfile);

	}
	//TC_MUMV_227a Verify whether standard user does not have access to delete the view.  	  					  	      
	@Test(groups ={Slingshot,Regression})
	public void ManageViewDeleteViewStandardUserValidatation()  {
		Report.createTestLogHeader("MuMv", "Verify whether standard user does not have access to delete the view");

		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.StandardUserValidation();	      		

	}

	//********************************************************MUMV- View and edit user ****************************************************************
	//*************************************************************************************************************************************************
	//TC_MUMV_084 Verify whether super user is able to navigate to View user landing page on clicking 'View details' in Manage users landing page.
	//TC_MUMV_108 Verify the link navigations Edit user page when amended confirmation screen is displayed for Super User.
	//TC_MUMV_113 Verify whether super user can select the view as 'All accounts' for standard users in Edit user page.  					  	
	@Test(groups ={Slingshot,Regression})
	public void MuMV_ViewAndEditSuperUser()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify the Super user creation and their Audit entry in DB"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("ViewuserVerifynew");							
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewuser(userProfile)
		.ClickEditdetails()
		.Viewtableverification()
		.VerifyUseramendeddetailsTitle()
		.VerifyChangeProfileAudit();							 
	}
	//TC_MUMV_107 Verify the functionality of 'Back to Manage users' button in the amended confirmation screen(Super user).
	//TC_MUMV_117 Verify the link navigations in the confirmation page when standard user is given access to all accounts in Edit user page.
	//TC_MUMV_132 Verify whether confirmation email is triggered when standard user is given access to specific account in Edit user page.					 	 	
	//TC_MUMV_126 Verify whether super user is able to change to specific view for standard users in the edit view page.
	//TC_MUMV_127 Verify whether confirmation page is displayed on clicking 'Confirm' button when ‘specific view’ is selected in Edit user page. 					 	 	
	@Test(groups ={Slingshot,Regression})
	public void MuMV_ViewAndEditStdUser()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify whether confirmation email is triggered when standard user is given access to specific account in Edit user page."); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("ViewuserVerifynew");							
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewStduser(userProfile)
		.ClickEditdetails()
		.ChangeStandardUserview(userProfile) 	
		.VerifyChangeProfileAudit();
	}

	//TC_MUMV_096 Verify whether 'Back' link is navigated to view user page in Edit user landing page.
	//TC_MUMV_097 Verify whether 'Cancel' link is navigated to manage users page in Edit user landing page.
	//TC_MUMV_125 Verify whether user remains in the Edit user page on clicking 'Cancel' link.
	@Test(groups ={Slingshot,Regression})
	public void MuMV_ViewAndEditStdUserCancel()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify whether user remains in the Edit user page on clicking 'Cancel' link."); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMvManageView12");							
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewStduser(userProfile)
		.ClickEditdetails()
		.BackAndCancel();

	}
	//TC_MUMV_088 Verify the link navigations in the breadcrumb in View users landing page
	//TC_MUMV_092 Verify whether links navigations are as per the content spec in Edit user landing page.
	//TC_MUMV_093 Verify the link navigations in the breadcrumb in Edit user landing page
	//TC_MUMV_095 Verify whether current view is defaulted for the particular user in Edit user landing page. 			 	 	
	//TC_MUMV_100 Verify whether links navigations are as per the content spec in Edit user page when user type is selected as super user.
	//TC_MUMV_101 Verify the link navigations in the breadcrumb in Edit user page when user type is selected as super user.
	//TC_MUMV_103 Verify the link navigation in Edit user - Super user confirmation modal overlay. 		
	//TC_MUMV_086 Verify whether look and feel of View users landing page is displayed as per the content spec.					 	 	

	@Test(groups ={Slingshot,Regression})
	public void MuMV_ViewAndEditSuperUserNavigation()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify link navigations in the breadcrumb in Edit user page when user type is selected as super user"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDataspl");							
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewuser(userProfile)		
		.EditUserlinkNavigation()
		.ViewdetailsPageVerification()
		.ClickEditdetails()
		.Viewtableverification()
		.VerifyUseramendeddetailsTitle()
		.BreadCrumbNavigation()
		.backtoMangeruserlinkNavigation()
		.AddNewUserNavigationVerification();
	}
	//TC_MUMV_107 Verify the functionality of 'Back to Manage users' button in the amended confirmation screen(Super user).
	//TC_MUMV_129 Verify the link navigations in the confirmation page when standard user is given access to specific account in Edit user page.
	//TC_MUMV_130 Verify the functionality of 'Back to Manage users' link in the confirmation page when standard user is given access to specific account in Edit user page.
	//TC_MUMV_131 Verify the link navigations in breadcrumb in the confirmation page when standard user is given access to specific account in Edit user page.
	//TC_MUMV_119 Verify the link navigations in breadcrumb in the confirmation page when standard user is given access to all accounts in Edit user page.
	//TC_MUMV_118 Verify the functionality of 'Back to Manage users' link in the confirmation page when standard user is given access to all accounts in Edit user page.
	//TC_MUMV_115 Verify whether confirmation page is displayed on clicking 'Confirm' button when ‘all accounts view’ is selected in Edit user page.
	@Test(groups ={Slingshot,Regression})
	public void MuMV_ViewAndEditStdUserNavigation()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify link navigations in breadcrumb in the confirmation page when standard user is given access to specific account in Edit user page."); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");							
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewStduser(userProfile)
		.ClickEditdetails()
		.ChangeStandardUserview(userProfile)							
		.BreadCrumbNavigation()
		.backtoMangeruserlinkNavigation()
		.AddNewUserNavigationVerification();

	}	 

	//TC_MUMV_104 Verify the look and feel of Edit user page when user status changed to ‘Super User’ but already 5 Super Users exists.
	//TC_MUMV_105 Verify the link navigations in Edit user page when user status changed to ‘Super User’ but already 5 Super Users exists.					 	 	
	@Test(groups ={Slingshot,Regression})
	public void ValidateUserTypeMaximumSuperUserExceeded()  {
		Report.createTestLogHeader("MuMv_viewandedituser", "Verify Maximum SuperUser Exceeded Validation Error"); 	 	   	  
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVmorethan5superuser");					 	 	 	       	
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.viewDetailsTableViewuser(userProfile)
		.ClickEditdetails()
		.Viewtableverification()
		.SuperUserMaximumExceedErrorValidation()
		.BreadCrumbNavigation();

	}

	//TC_MUMV_138	Verify whether super user is able to navigate to View user landing page on clicking 'View details' in Manage users landing page.
	//TC_MUMV_140:	Verify whether 'Deactivate' link is displayed for active users in view users landing page.
	//TC_MUMV_141:	Verify whether 'Reactivate' link is displayed for inactive users in view users landing page.
	//TC_MUMV_145	Verify whether details are displayed in the View user landing page as per SAP CRM.
	//TC_MUMV_146	Verify whether 'Deactivate' user overlay is displayed when 'Deactivate' user link is clicked in View user page.
	//TC_MUMV_148	Verify whether confirmation screen is displayed on clicking 'Yes' in the 'Deactivate' user overlay.
	//TC_MUMV_149,TC_MUMV_150:Verify whether user is navigated back to view user page on clicking 'cancel' in the 'Deactivate' user overlay.
	//TC_MUMV_157	Verify whether 'Reactivate' overlay is displayed on clicking 'Reactivate user' link in view user page.
	//TC_MUMV_159	Verify whether confirmation screen is displayed on clicking 'Yes' in the 'Reactivate' overlay.
	//TC_MUMV_161	Verify the links navigation in the confirmation screen displayed on clicking 'Yes' in the 'Reactivate' overlay.
	//TC_MUMV_162	Verify whether user is navigated back to view user page on clicking 'cancel' in the ''Reactivate' user overlay.

	@Test(groups ={Slingshot,Regression})
	public void verifyDeAndReactivateUser(){
		Report.createTestLogHeader("Multi User Multi View", "Verifies whether Deactivate link is displayed for active users in view users landing page");
		UserProfile userProfile = new TestDataHelper().getUserProfile("DeleteAndReactivate");
		new LoginAction()			  			       
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();
		new MultiUserMultiViewAction()
		.manageUsersLink()	;	    
	//	.verifyReAndDeActiavtionUser(userProfile);			  			    
	}

	//TC_MUMV_167	Verify whether error message overlay is displayed when super user tries to reactivate a user when there are already 20 active users.
	//TC_MUMV_169	Verify the links navigation in the error message overlay displayed when super user tries to reactivate a user when there are already 20 active users.
	//Data needed
	@Test(groups ={Slingshot,Regression})
	public void verifyMorethan20ActiveUser(){
		Report.createTestLogHeader("Multi User Multi View", "Verifies error message overlay is displayed when super user tries to reactivate a user when there are already 20 active users");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Morethan20ActiveUser");
		new LoginAction()			  			       
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();
		new MultiUserMultiViewAction()
		.manageUsersLink()	;	    
		//.verifyReAndDeActiavtionUser(userProfile);			  			    
	}
	//	TC_MUMV_151	Verify whether overlay is displayed when super user cannot delete last Super user.(cannot delete themselves from the system if there is only one super user)
	//	TC_MUMV_153	Verify the link navigation in the overlay displayed when super user cannot delete last Super user.
	// Data needed
	@Test(groups ={Slingshot,Regression})
	public void verifySuperUserReActivation(){
		Report.createTestLogHeader("Multi User Multi View", "Verifies whether overlay is displayed when super user cannot delete last Super user");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Morethan20ActiveUser");
		new LoginAction()			  			       
		.BgbloginDetails(userProfile)
		.BgbverifyAfterLogin();
		new MultiUserMultiViewAction()
		.manageUsersLink()	;	    
	//	.verifyReAndDeActiavtionUser(userProfile);			  			    
	}		
	
	// ----------------------------------------------------------------- RP3 MuMv Add user--------------------------------------------------------------------------------
	// ----------------------------------------------------------------- -------------------------------------------------------------------------------------------------
//TS_MUMV_01(E to E)  Verify whether the customer is able to register successfully using MUMV with paperless billing option and verify all the backend details
//TS_MUMV_05          Verify whether the confirmation email is received when customer is registered successfully using MUMV when paperless billing is selected	
	
	@Test(groups ={Slingshot,Regression})
	public void VerifyPaperlessbillingAddnewUser()  {
		Report.createTestLogHeader("Multi User Multi View", "Verifies whether overlay is displayed when super user cannot delete last Super user");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");		
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes();						  	       					  	       	
		new BgbRegistrationAction()		
		.AddNewSuperUser_paperlessaction(userProfile);		
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile)
		.AdduserConfirmationPage();	
		new PaperlessBillingAction()
	  	.BgbnavigateToLogin()
		.PBDirectDebitLogin_Secondlogin(userProfile)
		.BgbverifyAfterLogin();					
	  	new PaperlessBillingAction()
	  	//.verifyManageAccountLink(userProfile)
	  	.specificpapersetupAction()	
	  	.VerifyAudit_EmailtriggeredAction(userProfile);
	}
//TS_MUMV_02(E to E) Verify whether the customer is able to register successfully using MUMV without paperless billing option.
//TS_MUMV_07 Verify whether the paper billing option is getting reflected in account summary if not selected during registration in MUMV journey for super user and full access user.	
	@Test(groups ={Slingshot,Regression})
	public void VerifyPaperbillingAddnewUser()  {
		Report.createTestLogHeader("MuMv", "Verify whether the customer is able to register successfully using MUMV without paperless billing option");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecificsdata");		
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.SuperUserCreation()
		.SuperUserOverlayyes();						  	       					  	       	
		new BgbRegistrationAction()		
		.AddNewSuperUser_paperaction(userProfile);		
		new MultiUserMultiViewAction()
		.verifyAuditTable(userProfile)
		.AdduserConfirmationPage();	
		new PaperlessBillingAction()
	  	.BgbnavigateToLogin()
		.PBDirectDebitLogin_Secondlogin(userProfile)
		.BgbverifyAfterLogin();					
	  	new PaperlessBillingAction()
	  	.verifyManageAccountLink(userProfile)
	  	.specificpaperlesssetupAction()	
	  	.VerifyAudit_EmailtriggeredAction(userProfile);
	}
//TS_MUMV_16 Verify the super user is able to perform below journeys
	@Test(groups ={Slingshot,Regression})
	public void VerifySuperUserAccessJourneys()  {
		Report.createTestLogHeader("MuMv", "Verify the super user is able to perform  journeys");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecificsdata");		
		new LoginAction()
		.BgbnavigateToLogin()
		.bgbloginDetails(userProfile);		
		new MultiUserMultiViewAction()
		.ManageAccountLink(userProfile)
		.verifySuperuserAccessjourneyverification();
	}
	
//TS_MUMV_17 Verify the Full user is able to perform below journeys
		@Test(groups ={Slingshot,Regression})
		public void VerifyFullUserAccessJourneys()  {
			Report.createTestLogHeader("MuMv", " Verify the Full user is able to perform  journeys");
			UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecificsdata");		
			new LoginAction()
			.BgbnavigateToLogin()
			.bgbloginDetails(userProfile);		
			new MultiUserMultiViewAction()
			.ManageAccountLink(userProfile)
			.verifyFullAccessuserjourneyverification();		
		}
		
//TS_MUMV_18 Verify the RBP user is able to perform below journeys	
	@Test(groups ={Slingshot,Regression})
	public void VerifyRBPAccessJourneys()  {
		Report.createTestLogHeader("MuMv", "Verify the RBP user is able to perform  journey");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecificsdata");		
		new LoginAction()
		.BgbnavigateToLogin()
		.bgbloginDetails(userProfile);		
		new MultiUserMultiViewAction()
		.ManageAccountLink(userProfile)
		.verifyRBPuserjourneyverification();
	
	}
//TS_MUMV_19 Verify the RB  user is able to perform below journeys
	@Test(groups ={Slingshot,Regression})
	public void VerifyRPAccessJourneys()  {
		Report.createTestLogHeader("MuMv", "Verify the RB  user is able to perform  journeys");
		UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecificsdata");		
		new LoginAction()
		.BgbnavigateToLogin()
		.bgbloginDetails(userProfile);		
		new MultiUserMultiViewAction()
		.ManageAccountLink(userProfile)
		.verifyRPuserjourneyverification();
	
	}
	/*@Test(groups ={Slingshot,Regression})
	public void VerifyCreationAccont_WithNonSuperUser()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads, Bills, Payments users");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton()
		.StandardUserCreation()
		.addNewStandardUser(userProfile)
		.verifyAuditTabledetailsNew()
		.openEncryptUrlandRegister1new(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTabledetailsNew(userProfile)
		.AdduserConfirmationPage()
		.verifyAuditTabledetailsNew_01();
		new LoginAction()
		.bgblogout();

	}	*/
	
	/*@Test(groups ={Slingshot,Regression})
	public void VerifyCreationAccont_As_SuperUser()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads, Bills, Payments users");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.ClickAddNewUserLink()
		.AddUserRadioButton() 
		.StandardUserCreation_SelectSuper() 
		.enterValidDatasuperUser(userProfile) 
		.verifyAuditTabledetailsNew()
		.openEncryptUrlandRegister1new(userProfile);
		new MultiUserMultiViewAction()
		.verifyAuditTabledetailsNew(userProfile)
		.AdduserConfirmationPage()
		.verifyAuditTabledetailsNew_01();
		new LoginAction()
		.bgblogout();
	}	*/
/*	@Test(groups ={Slingshot,Regression})
	public void VerifyAdd195Accts()  {
		Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads, Bills, Payments users");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.AccountStorageFunctionality(userProfile);
		
	}	*/
	
	
	@Test(groups ={Slingshot,Regression})
	public void VerifyAdd195Accts()  {

		Report.createTestLogHeader("MuMv", "Verify whether user is able to create 195 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		for(int i=1;i<=3;i++) {
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.AccountStorageFunctionality(userProfile);
		Report.updateTestLog("*****" +i +"acount is created sucessfuly ******************","Pass");
		Report.updateTestLog("*****************************************","Pass");
		
		}
	}
	
	@Test(groups ={Slingshot,Regression})
	public void VerifyAdd5SuperAccts()  {

		Report.createTestLogHeader("MuMv", "Verify whether user is able to create 195 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		for(int i=1;i<=5;i++) {
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.AccountStorageFunctionalitySuperUser(userProfile);
		Report.updateTestLog("*****" +i +"acount is created sucessfuly ******************","Pass");
		Report.updateTestLog("*****************************************","Pass");
		
		}
	}
	
	
	
	@Test(groups ={Slingshot,Regression})
	public void VerifyCreationOfNormalAccts_WithNonSuperUser()  {

		Report.createTestLogHeader("MuMv", "Verify whether user is able to create 195 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.AccountStorageFunctionality(userProfile);
		Report.updateTestLog("*****" +"acount is created sucessfuly ******************","Pass");
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.VerifyTable();
	}
	@Test(groups ={Slingshot,Regression})
	public void VerifyCreationOfNormalAccts_WithSuperUser()  {

		Report.createTestLogHeader("MuMv", "Verify whether user is able to create 195 accounts");
		UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
		SMRAccountDetails smrProfile = new TestDataHelper().getAllSMRUserProfile("SubmitMeterReadodb");
		CrmUserProfile crmuserProfile = new TestDataHelper().getCrmUserProfile("SlingshotCrmDetailsRBP"); 
		new LoginAction()
		.BgbnavigateToLogin()
		.BgbloginDetails(userProfile);
		new MultiUserMultiViewAction()
		.ClickManageUserLink()
		.AccountStorageFunctionality(userProfile);
		Report.updateTestLog("*****" +"acount is created sucessfuly ******************","Pass");
		
	}
	
	
	//changes in May Release
		
		
		@Test(groups ={Slingshot,Regression})
		public void VerifyPaperlessbillingAddnewUserNew()  {
			Report.createTestLogHeader("Multi User Multi View", "Verifies whether overlay is displayed when super user cannot delete last Super user");
			UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");		
			new LoginAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
			new MultiUserMultiViewAction()
			.ClickManageUserLink()
			.ClickAddNewUserLink()
			.AddUserRadioButton()
			.SuperUserCreation()
			.SuperUserOverlayyes();						  	       					  	       	
			new BgbRegistrationAction()		
			.AddNewSuperUser_paperlessaction(userProfile);		
			new MultiUserMultiViewAction()
			.verifyAuditTable(userProfile)
			.AdduserConfirmationPage();	
			new PaperlessBillingAction()
		  	.BgbnavigateToLogin()
			.PBDirectDebitLogin_Secondlogin(userProfile)
			.BgbverifyAfterLogin();					
		  	new PaperlessBillingAction()
		  	//.verifyManageAccountLink(userProfile)
		  	.specificpapersetupAction()	
		  	.VerifyAudit_EmailtriggeredAction(userProfile);
		}
	 	 	 	

		///////////////////////  WAVE - JULY RELEAE////////////////////////////////////////////////////////////////////
		//////////////////////// Add a new account through MUMV and then Deactivate the same account//////////////////////

		@Test(groups ={Slingshot,Regression})
		public void Create_RBP_DeactivateUser()  {
			Report.createTestLogHeader("MuMv", "Verify whether confirmation page is getting displayed for Reads, Bills, Payments users");
			UserProfile userProfile = new TestDataHelper().getUserProfile("MuMVTestDatas");
			new LoginAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
			new MultiUserMultiViewAction()
			.ClickManageUserLink()
			.ClickAddNewUserLink()
			.AddUserRadioButton()
			.StandardUserCreation()
			.addNewStandardUser(userProfile);
			new BgbRegistrationAction()
			.AddUrl_UpdateNewView_Name(userProfile);
			new MultiUserMultiViewAction()
			.verifyAuditTabledetails(userProfile)
			.AdduserConfirmationPage();
			new LoginAction()
	        .bgbloginDetails(userProfile);
			/*new MultiUserMultiViewAction()
			.ClickManageUserLink();
			new LoginAction()
			.bgblogout();*/
			new MultiUserMultiViewAction()
			.ClickManageUserLink()
			.verifydeactivateaccount(userProfile);
			
		}
		
		@Test(groups ={Slingshot,Regression})
		public void CreateSuperUserAccount_Deactivate()  {
			 Report.createTestLogHeader("MuMv", "Verify the Super user creation and their Audit entry in DB"); 	 	   	   	 	   	  
			UserProfile userProfile = new TestDataHelper().getUserProfile("Switchtopaperlessacctspecifics");
			new LoginAction()
			.BgbnavigateToLogin()
			.BgbloginDetails(userProfile);
			new MultiUserMultiViewAction()
			.ClickManageUserLink()
			.ClickAddNewUserLink()
			.AddUserRadioButton()
			.SuperUserCreation()
			.SuperUserOverlayyes();
			new BgbRegistrationAction()
			.AddNewSuperUser(userProfile);
			new MultiUserMultiViewAction()
			.verifyAuditTable(userProfile)
			.AdduserConfirmationPage()
		    .confirmationPageVerificationLinks();
			/*.UserJourney_SuperUserAccessVerification(userProfile,smrProfile);
			new SapCrmAction()
			.loginDetails(crmuserProfile)
			.searchCrmFieldsVerification(crmuserProfile, userProfile);
	*/
		}		
	
		
		
		

}




